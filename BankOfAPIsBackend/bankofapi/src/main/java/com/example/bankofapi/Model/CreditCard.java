package com.example.bankofapi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="creditCardsTable")
public class CreditCard {
    @Id
    private String creditCardNumber;

    private String creditCardHolderName;

    private String phoneNumber;


    private String bankName;


    private String creditCardType;


    private String status;


    private String reasonForHotlisting;


    private double availableCredit;


    private double statementBalance;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }

    public void setCreditCardHolderName(String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonForHotlisting() {
        return reasonForHotlisting;
    }

    public void setReasonForHotlisting(String reasonForHotlisting) {
        this.reasonForHotlisting = reasonForHotlisting;
    }

    public double getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(double availableCredit) {
        this.availableCredit = availableCredit;
    }

    public double getStatementBalance() {
        return statementBalance;
    }

    public void setStatementBalance(double statementBalance) {
        this.statementBalance = statementBalance;
    }

    public double getTotalUnbilledDebits() {
        return totalUnbilledDebits;
    }

    public void setTotalUnbilledDebits(double totalUnbilledDebits) {
        this.totalUnbilledDebits = totalUnbilledDebits;
    }

    private double totalUnbilledDebits;
}
