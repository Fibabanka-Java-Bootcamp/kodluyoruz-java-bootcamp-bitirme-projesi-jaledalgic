package org.kodluyoruz.mybank.CreditCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.kodluyoruz.mybank.BankCards.BankCardDto;
import org.kodluyoruz.mybank.BankCards.BankCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.validation.constraints.Min;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/creditcard")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping(value = "/{iban}",params = {"boundary"})
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardDto create(@PathVariable UUID iban, @RequestParam double boundary) {
        return creditCardService.create(iban, boundary);
    }

    @GetMapping(value = "/ekstre", params = {"cardNo"})
    public @JsonIgnore Map<String, LocalDate> ekstract(@RequestParam("cardNo") UUID cardNo) {
        return creditCardService.extract(cardNo);
    }



}
