package org.kodluyoruz.mybank.Customers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto save(@RequestBody CustomerDto customerDto){
       return customerService.create(customerDto.toCustomer()).toCustomerDto();
    }

    @PutMapping(value = "/{id}")
    public CustomerDto update(@RequestBody CustomerDto customerDto, @PathVariable("id") int id){
        return customerService.update(customerDto.toCustomer(),id).toCustomerDto();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }


}
