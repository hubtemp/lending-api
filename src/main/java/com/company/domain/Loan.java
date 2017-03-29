package com.company.domain;

import com.company.utility.Converter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.company.utility.MoneySerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Client client;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date applicationDate = new Date();
    private int termDays;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date termDate = new Date();
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal amount;
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal outstandingAmount;
    private String ipAddress;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private List<Extension> extension = new ArrayList<>();

    public Loan() {
    }

    public Loan(Client client, int termDays, LocalDate applicationDate, LocalDate termDate, BigDecimal amount, String ipAddress) {
        this.client = client;
        this.termDays = termDays;
        this.applicationDate = com.company.utility.Converter.toDate(applicationDate);
        this.termDate = Converter.toDate(termDate);
        this.outstandingAmount = this.amount = amount.setScale(2);
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    public LocalDate getApplicationLocalDate() {
        return Converter.toLocalDate(applicationDate);
    }

    public void setApplicationDate(Date date) {
        this.applicationDate = date;
    }

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    public void setApplicationDate(LocalDate date) {
        this.applicationDate = Converter.toDate(date);
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
    @Temporal(TemporalType.DATE)
    public LocalDate getTermLocalDate() {
        return Converter.toLocalDate(termDate);
    }

    public void setTermDate(Date termDate) {
        this.termDate = termDate;
    }

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    public void setTermLocalDate(LocalDate termDate) {
        this.termDate = Converter.toDate(termDate);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<Extension> getExtension() {
        return extension;
    }

    public void setExtension(List<Extension> extension) {
        this.extension = extension;
    }

}
