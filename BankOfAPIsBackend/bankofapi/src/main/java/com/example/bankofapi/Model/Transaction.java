package com.example.bankofapi.Model;

import javax.persistence.*;
import java.util.Date;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="transactionsTable")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;

    private Date date;

    private String creditCardNumber;

    private String transactionDescription;

    private String transactionType;

    private double amountInRupees;
}
