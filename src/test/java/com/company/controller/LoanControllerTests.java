package com.company.controller;

import com.company.BaseTest;
import com.company.domain.HttpHeaders;
import com.company.domain.Loan;
import com.company.repository.LoanRepository;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class LoanControllerTests extends BaseTest {

    private static final String TEST_LOAN_ID = "2";
    private static final String TEST_LOAN_AMOUNT = "200.00";
    private static final int TEST_LOAN_DAYS = 25;
    private static final String TEST_LOAN_APPLICATION_DATE = "2017-03-01";
    private static final String TEST_LOAN_TERM_DATE = "2017-03-11";

    @Autowired
    private LoanRepository loanRepository;

    @Test
    public void testGetLoans() throws Exception {
        getMockMvc().perform(get("/loans")
                .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(any(Integer.class)))
                .andExpect(jsonPath("$[0].applicationDate", is(TEST_LOAN_APPLICATION_DATE)))
                .andExpect(jsonPath("$[0].termDays", is(30)))
                .andExpect(jsonPath("$[0].termDate", is("2017-04-07")))
                .andExpect(jsonPath("$[0].amount", is("100.00")))
                .andExpect(jsonPath("$[0].outstandingAmount", is("150.00")))
                .andExpect(jsonPath("$[0].ipAddress", is(any(String.class))))
                .andExpect(jsonPath("$[0].extension", hasSize(1)))
                .andExpect(jsonPath("$[0].extension[0].id", is(1)))
                .andExpect(jsonPath("$[0].extension[0].extensionDate", is("2017-03-05")))
                .andExpect(jsonPath("$[0].extension[0].fee", is("50.00")))
                .andExpect(jsonPath("$[0].extension[0].termDays", is(7)))
                .andExpect(jsonPath("$[0].extension[0].termDate", is("2017-04-07")))
                .andExpect(jsonPath("$[1].id").value(any(Integer.class)))
                .andExpect(jsonPath("$[1].applicationDate", is(TEST_LOAN_APPLICATION_DATE)))
                .andExpect(jsonPath("$[1].termDays", is(10)))
                .andExpect(jsonPath("$[1].termDate", is("2017-03-11")))
                .andExpect(jsonPath("$[1].amount", is("50.00")))
                .andExpect(jsonPath("$[1].outstandingAmount", is("50.00")))
                .andExpect(jsonPath("$[1].ipAddress", is(any(String.class))))
                .andExpect(jsonPath("$[1].extension", hasSize(0)));
    }

    @Test
    public void testGetLoan() throws Exception {
        getMockMvc().perform(get("/loans/{loanId}", TEST_LOAN_ID)
                .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(any(Integer.class)))
                .andExpect(jsonPath("$.applicationDate", is(TEST_LOAN_APPLICATION_DATE)))
                .andExpect(jsonPath("$.termDays", is(10)))
                .andExpect(jsonPath("$.termDate", is(TEST_LOAN_TERM_DATE)))
                .andExpect(jsonPath("$.amount", is("50.00")))
                .andExpect(jsonPath("$.outstandingAmount", is("50.00")))
                .andExpect(jsonPath("$.ipAddress", is(any(String.class))))
                .andExpect(jsonPath("$.extension", hasSize(0)));
    }

    @Test
    public void testCreateLoan() throws Exception {
        MvcResult result = getMockMvc().perform(post("/loans")
                .param("amount", TEST_LOAN_AMOUNT)
                .param("days", "" + TEST_LOAN_DAYS)
                .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(any(Integer.class)))
                .andExpect(jsonPath("$.applicationDate", is(IsNull.notNullValue())))
                .andExpect(jsonPath("$.termDays", is(TEST_LOAN_DAYS)))
                .andExpect(jsonPath("$.termDate", is("2017-04-05")))
                .andExpect(jsonPath("$.amount", is(TEST_LOAN_AMOUNT)))
                .andExpect(jsonPath("$.outstandingAmount", is(TEST_LOAN_AMOUNT)))
                .andExpect(jsonPath("$.ipAddress", is(any(String.class))))
                .andExpect(jsonPath("$.extension", hasSize(0))).andReturn();
        loanRepository.delete(getJsonObjectMapper().readValue(result.getResponse().getContentAsString(), Loan.class));
    }

    @Test
    public void testCreateRejectedLoan() throws Exception {
        List<Loan> createdLoans = new ArrayList<>();
        MvcResult result;
        try {
            result = getMockMvc().perform(post("/loans")
                    .param("amount", TEST_LOAN_AMOUNT)
                    .param("days", "" + TEST_LOAN_DAYS)
                    .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
            createdLoans.add(getJsonObjectMapper().readValue(result.getResponse().getContentAsString(), Loan.class));
            result = getMockMvc().perform(post("/loans")
                    .param("amount", TEST_LOAN_AMOUNT)
                    .param("days", "" + TEST_LOAN_DAYS)
                    .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
            createdLoans.add(getJsonObjectMapper().readValue(result.getResponse().getContentAsString(), Loan.class));
            result = getMockMvc().perform(post("/loans")
                    .param("amount", TEST_LOAN_AMOUNT)
                    .param("days", "" + TEST_LOAN_DAYS)
                    .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
            createdLoans.add(getJsonObjectMapper().readValue(result.getResponse().getContentAsString(), Loan.class));
            getMockMvc().perform(post("/loans")
                    .param("amount", TEST_LOAN_AMOUNT)
                    .param("days", "" + TEST_LOAN_DAYS)
                    .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
        } finally {
            createdLoans.forEach(loan -> loanRepository.delete(loan));
        }
    }

    @Test
    public void testFailGetLoans() throws Exception {
        getMockMvc().perform(get("/loan/{loanId}", "9999")
                .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_1))
                .andExpect(status().isNotFound());
    }

}

