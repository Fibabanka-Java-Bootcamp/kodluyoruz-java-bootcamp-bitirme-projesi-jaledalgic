package org.kodluyoruz.mybank.Debt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/creditcard")
public class DebtController {
    private final DebtService debtService;

    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    @GetMapping(value = "/debt", params = {"cardNo"})
    public List<Debt> debtInquiry(@RequestParam("cardNo") UUID cardNo) {
        return debtService.debtInquiry(cardNo);
    }
    @GetMapping(value = "/paymentFrom/{paymentType}", params = {"cardNo","payMoney","id"})
    public String payment(@RequestParam UUID cardNo , @RequestParam double payMoney,
                                           @RequestParam Integer id, @PathVariable String paymentType) {
        return debtService.payment(id,cardNo,paymentType,payMoney);
    }
}
