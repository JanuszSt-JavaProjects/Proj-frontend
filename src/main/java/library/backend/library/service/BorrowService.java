package library.backend.library.service;

import library.backend.library.domain.*;
import library.backend.library.exception.clientException.NoSuchClientException;
import library.backend.library.exception.copyException.NoAvailableCopyException;
import library.backend.library.exception.copyException.NoSuchCopyException;
import library.backend.library.repository.BorrowRepository;
import library.backend.library.repository.CustomerRepository;
import library.backend.library.repository.CopyRepository;
import library.backend.library.domain.dto.borrowDto.BorrowReturnDto;
import library.backend.library.exception.bookException.NoSuchBookException;
import library.backend.library.exception.borrowException.NoSuchBorrowException;
import library.backend.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    BorrowRepository borrowRepository;
    BookRepository bookRepository;
    CustomerRepository customerRepository;
    CopyRepository copyRepository;

    public BorrowService(BorrowRepository borrowRepository,
                         BookRepository bookRepository,
                         CustomerRepository customerRepository,
                         CopyRepository copyRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
        this.copyRepository = copyRepository;
    }


    public Borrow save(Borrow borrow) {

        Book book = bookRepository.findById(borrow.getBookId()).orElseThrow(NoSuchBookException::new);
        Customer customer = customerRepository.findById(borrow.getCustomer().getId()).orElseThrow(NoSuchClientException::new);

        long bookId = book.getId();
        Copy availableCopy = getFirstAvailableCopy(book);
        availableCopy.setStatus(Status.IN_USE);

        Borrow newBorrow = new Borrow();

        newBorrow.setCopyId(availableCopy.getId());
        newBorrow.setCustomer(customer);
        newBorrow.setBookId(bookId);
        newBorrow.setBorrowDate(LocalDate.now());

        copyRepository.save(availableCopy);
        return borrowRepository.save(newBorrow);
    }

    private Copy getFirstAvailableCopy(Book book) {
        return Optional.ofNullable(
                        copyRepository
                                .findAllByBookAndStatus(book, Status.AVAILABLE)
                                .pollFirst())
                .orElseThrow(NoAvailableCopyException::new);
    }


    public void remove(long id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(NoSuchBorrowException::new);
        borrowRepository.delete(borrow);
    }

    public Borrow update(Borrow borrow) {
        borrowRepository.findById(borrow.getId()).orElseThrow(NoSuchBorrowException::new);
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAll() {
        return borrowRepository.findAll();
    }

    public Borrow getOne(long id) {
        return borrowRepository.findById(id).orElseThrow(NoSuchBorrowException::new);
    }

    public BorrowReturnDto returnBook(long id) {
        Borrow borrow = borrowRepository.findById(id).orElseThrow(NoSuchBorrowException::new);
        Copy copy = copyRepository.findById(borrow.getCopyId()).orElseThrow(NoSuchCopyException::new);


        if (copy.getStatus().equals(Status.AVAILABLE)) {
            throw new NoSuchBorrowException();
        }
        copy.setStatus(Status.AVAILABLE);
        borrow.setReturnDate(LocalDate.now());

        copyRepository.save(copy);
        borrowRepository.save(borrow);
        return new BorrowReturnDto(id,Status.AVAILABLE);
    }
}
