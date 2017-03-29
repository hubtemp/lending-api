package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = HomeworkApplication.class)
@WebAppConfiguration
public class BaseTest {

    public static final String ACCESS_TOKEN_1 = "LdI0d0D2dQ9n3XGat6H5J2SUcgcSxnxBEiVlv9eQnIpB7VbrIZM2B3iKQzwd6nlNgynCz8BhV5cIY7aaCTpCMgtd2Vbwpu2JOrRlgRrSrUVyi1ucWkauJXESUvcM4bX3";
    public static final String ACCESS_TOKEN_2 = "PKmDJrHF0ypgDwtARTeeaDOWjlN5Cg4ddDu1TM8MFPAhH71n7FaqtLKMSATBKMAwQ7cZ1c3kk81aXxGhqp2ErA3JCcgxhwV5b0WpXKkLkVqlbttQ3C7n9rYmpMtCAZTd";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper jsonObjectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

    public MockMvc getMockMvc() {
        return this.mockMvc;
    }

    public ObjectMapper getJsonObjectMapper() {
        return this.jsonObjectMapper;
    }
}
