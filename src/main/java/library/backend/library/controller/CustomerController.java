package library.backend.library.controller;


import library.backend.library.domain.Customer;
import library.backend.library.domain.dto.customerDto.CustomerDto;
import library.backend.library.mapper.ClientMapper;
import library.backend.library.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    ClientMapper clientMapper;
    ClientService clientService;

    CustomerController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto customerDto) {

        Customer customer = clientMapper.mapClientDtoToClient(customerDto);
        customer = clientService.save(customer);
        return clientMapper.mapClientToClientDto(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        clientService.remove(id);
    }

    @PutMapping
    public CustomerDto update(@RequestBody CustomerDto customerDto) {
        Customer customer = clientMapper.mapClientDtoToClient(customerDto);
        customer = clientService.update(customer);
        return clientMapper.mapClientToClientDto(customer);
    }

    @GetMapping("/{id}")
    public CustomerDto getOne(@PathVariable long id) {
        return clientMapper.mapClientToClientDto(clientService.getOne(id));
    }

    @GetMapping
    public Set<CustomerDto> getAll() {

        Set<CustomerDto> customerDtos = new HashSet<>();
        clientService.getAll().forEach(client -> customerDtos.add(clientMapper.mapClientToClientDto(client)));
        return customerDtos;

    }
}
