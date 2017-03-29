package com.company.domain;

import com.company.utility.Converter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.company.utility.MoneySerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Extension {

    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date extensionDate = new Date();
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal fee;
    private int termDays;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date termDate;
    @JsonIgnore
    private BigDecimal dayFactor;

    public Extension() {
    }

    public Extension(Loan loan, BigDecimal fee, int termDays, Date termDate, BigDecimal dayFactor) {
        this.loan = loan;
        this.fee = fee;
        this.termDays = termDays;
        this.termDate = termDate;
        this.dayFactor = dayFactor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Date getExtensionDate() {
        return extensionDate;
    }

    @JsonIgnore
    public LocalDate getExtensionLocalDate() {
        return com.company.utility.Converter.toLocalDate(extensionDate);
    }

    public void setExtensionDate(Date date) {
        this.extensionDate = date;
    }

    @JsonIgnore
    public void setExtensionDate(LocalDate date) {
        this.extensionDate = Converter.toDate(date);
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public int getTermDays() {
        return termDays;
    }

    public void setTermDays(int termDays) {
        this.termDays = termDays;
    }

    public Date getTermDate() {
        return termDate;
    }

    @JsonIgnore
    public LocalDate getTermLocalDate() {
        return Converter.toLocalDate(termDate);
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    @JsonIgnore
    public void setTermLocalDate(LocalDate termDate) {
        this.termDate = Converter.toDate(termDate);
    }

    public BigDecimal getDayFactor() {
        return dayFactor;
    }

    public void setDayFactor(BigDecimal dayFactor) {
        this.dayFactor = dayFactor;
    }
}
