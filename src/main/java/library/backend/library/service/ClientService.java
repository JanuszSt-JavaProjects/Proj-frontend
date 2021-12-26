package library.backend.library.service;

import library.backend.library.domain.Customer;
import library.backend.library.exception.clientException.ClientAlreadyExistsException;
import library.backend.library.exception.clientException.NoSuchClientException;
import library.backend.library.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    ClientRepository clientRepository;

    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Customer save(Customer customer) {
        if (clientRepository.existsById(customer.getId())) {
            throw new ClientAlreadyExistsException();
        }
        return clientRepository.save(customer);
    }

    public void remove(long id) {
        Customer customer = clientRepository.findById(id).orElseThrow(NoSuchClientException::new);
        clientRepository.delete(customer);
    }


    public Customer update(Customer customer) {
        Customer updatedCustomer = clientRepository.findById(customer.getId()).orElseThrow(NoSuchClientException::new);

        customer.setId(updatedCustomer.getId());
        clientRepository.save(customer);
        return clientRepository.findById(customer.getId()).get();
    }


    public Customer getOne(long id) {
        return clientRepository.findById(id).orElseThrow(NoSuchClientException::new);
    }

    public Iterable<Customer> getAll() {
        return clientRepository.findAll();
    }
}
