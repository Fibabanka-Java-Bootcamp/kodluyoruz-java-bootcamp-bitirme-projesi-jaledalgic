package org.kodluyoruz.mybank.Shopping;

import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/shop")
@RestController
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/creditcard")
    public ShopDto shopWithCreditCard(@RequestBody ShopDto shopDto){
        return shopService.creditCardShop(shopDto.toShop()).toShopDto();
    }
    @PostMapping("/bankcard")
    public ShopDto shopWithBankCard(@RequestBody ShopDto shopDto){
        return shopService.bankCardShop(shopDto.toShop()).toShopDto();
    }
}
