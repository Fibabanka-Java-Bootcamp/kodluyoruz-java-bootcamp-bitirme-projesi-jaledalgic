package org.kodluyoruz.mybank.Accounts;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import org.kodluyoruz.mybank.Customers.CustomerDto;
import org.kodluyoruz.mybank.Customers.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class AccountController {

        private final AccountService accountService;

        public AccountController(AccountService accountService) {
            this.accountService = accountService;
        }
        @PostMapping("/{id}/account")
        @ResponseStatus(HttpStatus.CREATED)
        public AccountDto create(@PathVariable Integer id , @RequestBody AccountDto accountDto) throws JsonProcessingException {
            return accountService.create(id, accountDto.toAccountDto()).toAccount();
        }
        @DeleteMapping("/account/{account_number}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
         public void delete(@PathVariable("account_number") UUID iban){
           accountService.delete(iban);
    }


}
