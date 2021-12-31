package library.backend.library.mapper;


import library.backend.library.domain.Customer;
import library.backend.library.domain.dto.borrowDto.BorrowDto;
import library.backend.library.exception.clientException.NoSuchClientException;
import library.backend.library.repository.CustomerRepository;
import library.backend.library.domain.Borrow;
import org.springframework.stereotype.Service;

@Service
public class BorrowMapper {

    CustomerRepository customerRepository;

    public BorrowMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Borrow mapBorrowDtoToBorrow(BorrowDto borrowDto) {

        Borrow borrow = new Borrow();
        Customer customer = customerRepository.findById(borrowDto.getClientId()).orElseThrow(NoSuchClientException::new);

        borrow.setCustomer(customer);
        borrow.setBookId(borrowDto.getBookId());

        return borrow;
    }

    public BorrowDto mapBorrowToBorrowDto(Borrow borrow) {

        BorrowDto borrowDto = new BorrowDto();

        borrowDto.setId(borrow.getId());
        borrowDto.setClientId(borrow.getCustomer().getId());
        borrowDto.setBookId(borrow.getBookId());
        borrowDto.setCopyId(borrow.getCopyId());
        borrowDto.setBorrowDate(borrow.getBorrowDate());

        return borrowDto;
    }
}
