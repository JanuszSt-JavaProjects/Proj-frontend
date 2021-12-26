package library.backend.library.mapper;

import library.backend.library.domain.dto.bookDto.BookDto;
import library.backend.library.domain.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book mapBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseDate(bookDto.getReleaseDate());
        return book;
    }


    public BookDto mapBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setReleaseDate(book.getReleaseDate());
        return bookDto;
    }
}
