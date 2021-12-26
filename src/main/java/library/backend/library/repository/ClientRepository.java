package library.backend.library.repository;


import library.backend.library.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Customer, Long> {
}
