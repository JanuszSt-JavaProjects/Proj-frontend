package library.backend.library.service;

import library.backend.library.domain.Customer;
import library.backend.library.exception.clientException.ClientAlreadyExistsException;
import library.backend.library.exception.clientException.NoSuchClientException;
import library.backend.library.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            throw new ClientAlreadyExistsException();
        }
        customer.setCreateAccountDate(LocalDate.now());
        return customerRepository.save(customer);
    }

    public void remove(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(NoSuchClientException::new);
        customerRepository.delete(customer);
    }


    public Customer update(Customer customer) {
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElseThrow(NoSuchClientException::new);

        customer.setId(updatedCustomer.getId());
        customerRepository.save(customer);
        return customerRepository.findById(customer.getId()).get();
    }


    public Customer getOne(long id) {
        return customerRepository.findById(id).orElseThrow(NoSuchClientException::new);
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
