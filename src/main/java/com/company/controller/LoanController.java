package com.company.controller;


import com.company.domain.Loan;
import com.company.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

import static com.company.domain.Client.AUTH_CLIENT_ID;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @RequestMapping
    public List<Loan> getLoans(@RequestAttribute(AUTH_CLIENT_ID) Long clientId) {
        return loanService.getLoans(clientId);
    }

    @RequestMapping("/{loanId}")
    @ResponseBody
    public Loan getLoan(@RequestAttribute(AUTH_CLIENT_ID) Long clientId, @PathVariable Long loanId) {
        return loanService.getLoan(clientId, loanId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@RequestAttribute(AUTH_CLIENT_ID) Long clientId, @RequestParam BigDecimal amount, @RequestParam int days, HttpServletRequest request) {
        return loanService.createLoan(request.getRemoteAddr(), clientId, amount, days);
    }

}
