package library.backend.library.controller;


import library.backend.library.domain.Copy;
import library.backend.library.domain.dto.bookDto.BookCreateDto;
import library.backend.library.domain.dto.bookDto.BookDto;
import library.backend.library.mapper.BookMapper;
import library.backend.library.service.BookService;
import library.backend.library.domain.Book;
import library.backend.library.mapper.CopyMapper;
import library.backend.library.service.CopyService;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("library/books")
public class BookController {

    BookMapper bookMapper;
    BookService bookService;
    CopyService copyService;
    CopyMapper copyMapper;

    public BookController(BookMapper bookMapper,
                          BookService bookService,
                          CopyService copyService,
                          CopyMapper copyMapper) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
        this.copyService = copyService;
        this.copyMapper = copyMapper;
    }

    @PostMapping
    public BookDto create(@RequestBody BookCreateDto bookCreateDto) {
        bookService.checkIfComplete(bookCreateDto);
        Book book = bookService.extractBook(bookCreateDto);
        Copy copy = bookService.extractCopy(bookCreateDto);

        return bookMapper.mapBookToBookDto(bookService.save(book, copy));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.delete(id);
    }

    @PutMapping
    public BookDto update(@RequestBody BookDto bookDto) {
        System.out.println(bookDto.getId());
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        System.out.println(book.getId());
        book = bookService.update(book);
        return bookMapper.mapBookToBookDto(book);
    }

    @GetMapping("/{id}")
    public BookDto getOne(@PathVariable long id) {
        Book book = bookService.getOne(id);
        return bookMapper.mapBookToBookDto(book);
    }

    @GetMapping
    public List<BookDto> getAll() {

        List<BookDto> bookDtos = new LinkedList<>();

        bookService.getAll().forEach(book -> bookDtos.add(bookMapper.mapBookToBookDto(book)));
        return bookDtos;
    }
}
