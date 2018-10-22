package com.mybonita.cas;

import org.bonitasoft.engine.authentication.AuthenticationConstants;
import org.bonitasoft.engine.authentication.GenericAuthenticationService;
import org.bonitasoft.engine.authentication.impl.AuthenticationServiceImpl;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Map;

public class CASAuthenticationService implements GenericAuthenticationService {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    public String checkUserCredentials(Map<String, Serializable> credentials) {

        // local login:
        if (credentials.get(AuthenticationConstants.CAS_TICKET) == null) {
            return authenticationService.checkUserCredentials(credentials);
        }

        // cas login:
        Cas30ServiceTicketValidator validator =
                new Cas30ServiceTicketValidator("https://your.cas.server/cas");

        try {
            return validator.validate((String) credentials.get(AuthenticationConstants.CAS_TICKET),
                    "https://your.bonita.server/bonita/loginservice")
                    .getPrincipal().getName();

        } catch (TicketValidationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
