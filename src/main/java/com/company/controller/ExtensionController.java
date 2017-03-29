package com.company.controller;

import com.company.domain.Extension;
import com.company.service.ExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.domain.Client.AUTH_CLIENT_ID;

@RestController
@RequestMapping("/loans/{loanId}/extensions")
public class ExtensionController {

    @Autowired
    private ExtensionService extensionService;

    @RequestMapping
    public List<Extension> getExtensions(@RequestAttribute(AUTH_CLIENT_ID) Long clientId, @PathVariable long loanId) {
        return extensionService.getExtensions(clientId, loanId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Extension createExtension(@RequestAttribute(AUTH_CLIENT_ID) Long clientId, @PathVariable long loanId, @RequestParam int days) {
        return extensionService.createExtension(clientId, loanId, days);
    }

}
