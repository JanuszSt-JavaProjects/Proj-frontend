package library.backend.library.repository;

import library.backend.library.domain.Copy;
import library.backend.library.domain.Book;
import library.backend.library.domain.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {

    LinkedList<Copy> findAllByBookAndStatus(Book book, Status status);

    @Override
    List<Copy> findAll();
}
