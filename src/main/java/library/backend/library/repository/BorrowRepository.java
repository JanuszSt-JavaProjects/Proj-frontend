package library.backend.library.repository;

import library.backend.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    @Override
    List<Borrow> findAll();
}
