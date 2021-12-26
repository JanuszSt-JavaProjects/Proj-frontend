package library.backend.library.repository;

import library.backend.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    boolean existsByAuthorAndTitle(String author, String title);

    @Override
    List <Book> findAll();
}
