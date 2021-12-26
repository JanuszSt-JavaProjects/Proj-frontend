package library.backend.library.mapper;

import library.backend.library.domain.Customer;
import library.backend.library.domain.dto.customerDto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {

    public Customer mapClientDtoToClient(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setId(customerDto.getClientId());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setCreateAccountDate(customerDto.getCreateAccountDate());
        return customer;
    }


    public CustomerDto mapClientToClientDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setClientId(customer.getId());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setCreateAccountDate(customer.getCreateAccountDate());

        return customerDto;
    }
}
