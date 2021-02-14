package org.kodluyoruz.mybank.Customers;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.CreditCards.CreditCard;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private int id;
    
    private String name;
    private String surname;
    private LocalDate birth;
    @Column(name = "birth_place")
    private String birth_place;

    @Column(name = "phoneNumber")
    private long phoneNumber;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Account> accounts;

    @OneToMany
    private Set<CreditCard> creditCards;

    @OneToMany
    private Set<BankCard> bankCards;

    public CustomerDto toCustomerDto(){
        return CustomerDto.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .birth(this.birth)
                .birth_place(this.birth_place)
                .phoneNumber(this.phoneNumber)
                .build();

    }
/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }*/
}
