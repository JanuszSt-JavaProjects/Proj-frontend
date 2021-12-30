package library.backend.library.service;

import library.backend.library.domain.Copy;
import library.backend.library.exception.bookException.NoNeededFieldException;
import library.backend.library.domain.Book;
import library.backend.library.domain.dto.bookDto.BookCreateDto;
import library.backend.library.exception.bookException.BookAlreadyExistsException;
import library.backend.library.exception.bookException.NoSuchBookException;
import library.backend.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class BookService {
    BookRepository bookRepository;
    CopyService copyService;

    public BookService(BookRepository bookRepository, CopyService copyService) {
        this.bookRepository = bookRepository;
        this.copyService = copyService;
    }

    public Book save(Book book, Copy copy) {
        if (checkIfExists(book)) {
            throw new BookAlreadyExistsException();
        }

        Book bookToSave = bookRepository.save(book);
        copy.setBook(bookToSave);
        copyService.save(copy);

        return bookToSave;
    }

    public void delete(long Id) {
        Book book = bookRepository.findById(Id).orElseThrow(NoSuchBookException::new);
        bookRepository.delete(book);
    }

    public Book update(Book book) {
        Book updatedBook = bookRepository.findById(book.getId()).orElseThrow(NoSuchBookException::new);

        updatedBook.setId(book.getId());
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setReleaseDate(book.getReleaseDate());
        return updatedBook;
    }

    public Book getOne(long Id) {
        return bookRepository.findById(Id).orElseThrow(NoSuchBookException::new);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book extractBook(BookCreateDto bookCreateDto) {

        Book book = new Book();
        book.setTitle(bookCreateDto.getTitle());
        book.setAuthor(bookCreateDto.getAuthor());
        book.setReleaseDate(bookCreateDto.getReleaseDate());

        return book;
    }

    public Copy extractCopy(BookCreateDto bookCreateDto) {

        Copy copy = new Copy();
        copy.setSignature(bookCreateDto.getSignature());
        copy.setStatus(bookCreateDto.getStatus());

        return copy;
    }

    private boolean checkIfExists(Book book) {
        return bookRepository.existsByAuthorAndTitle(book.getAuthor(), book.getTitle());
    }

    public void checkIfComplete(BookCreateDto bookCreateDto) {

        boolean check = Stream.of(
                        bookCreateDto.getTitle(),
                        bookCreateDto.getAuthor(),
                        bookCreateDto.getStatus(),
                        bookCreateDto.getReleaseDate(),
                        bookCreateDto.getSignature())
                .allMatch(Objects::nonNull);

        if (!check) {
            throw new NoNeededFieldException();
        }
    }


    public List<Book> getByAuthor(String value) {
        return bookRepository.findByAuthor(value);
    }
}

