package org.kodluyoruz.mybank.Debt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Debt {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "debtDate")
    private LocalDate debtDate;
    @Column(name = "debtMoney")
    private double debtMoney;
    @Column(name = "debtMessage")
    private String debtMessage;
    @Column(name = "cardNo")
    private UUID cardNo;
}
