package org.kodluyoruz.mybank.MoneyTransfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.internal.Collections;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public Transfer transaction(@RequestBody TransferDto transferDto){
        return transferService.transaction(transferDto.toTransfer());
    }

}
