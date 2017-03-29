package com.company.controller;

import com.company.BaseTest;
import com.company.domain.HttpHeaders;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class ExtensionControllerTests extends BaseTest {

    public static final String TEST_LOAN_ID = "3";
    private static final int TEST_EXTENSION_DAYS = 7;
    private static final String TEST_EXTENSION_FEE = "75.00";

    @Test
    public void testGetExtensions() throws Exception {
        getMockMvc().perform(get("/loans/{loadId}/extensions", TEST_LOAN_ID)
                .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(IsNull.notNullValue())))
                .andExpect(jsonPath("$[0].extensionDate", is("2017-03-05")))
                .andExpect(jsonPath("$[0].fee", is(TEST_EXTENSION_FEE)))
                .andExpect(jsonPath("$[0].termDays", is(TEST_EXTENSION_DAYS)))
                .andExpect(jsonPath("$[0].termDate", is("2017-03-28")));
    }

    @Test
    public void testCreateExtension() throws Exception {
        getMockMvc().perform(post("/loans/{loanId}/extensions", TEST_LOAN_ID)
                .param("loanId", TEST_LOAN_ID)
                .param("days", "" + TEST_EXTENSION_DAYS)
                .header(HttpHeaders.COMPANY_ACCESS_TOKEN, ACCESS_TOKEN_2))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(any(Integer.class))))
                .andExpect(jsonPath("$.extensionDate", is(IsNull.notNullValue())))
                .andExpect(jsonPath("$.fee", is(TEST_EXTENSION_FEE)))
                .andExpect(jsonPath("$.termDays", is(TEST_EXTENSION_DAYS)))
                .andExpect(jsonPath("$.termDate", is(IsNull.notNullValue())));
    }



}


