package org.kodluyoruz.mybank.Customers;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String name;

    @Temporal(TemporalType.DATE)
    private LocalDate birth;

    private long phoneNumber;
    private String surname;
    private String birth_place;

    public Customer toCustomer(){
        return Customer.builder()
                .name(this.name)
                .surname(this.surname)
                .birth(this.birth)
                .birth_place(this.birth_place)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
