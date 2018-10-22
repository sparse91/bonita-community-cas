package com.mybonita.cas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.console.common.server.auth.AuthenticationFailedException;
import org.bonitasoft.console.common.server.auth.impl.standard.StandardAuthenticationManagerImpl;
import org.bonitasoft.console.common.server.login.HttpServletRequestAccessor;
import org.bonitasoft.console.common.server.login.credentials.Credentials;
import org.bonitasoft.engine.authentication.AuthenticationConstants;

public class CASAuthenticationManagerImpl extends StandardAuthenticationManagerImpl {

    @Override
    public Map<String, Serializable> authenticate(final HttpServletRequestAccessor request,
                                                  final Credentials credentials)
            throws AuthenticationFailedException {

        // local login:
        if (request.getParameterMap().get("ticket") == null) {
            return super.authenticate(request, credentials);
        }

        // cas login:
        final String ticket = (request.getParameterMap().get("ticket")[0]);

        Map<String, Serializable> credentialsMap = new HashMap<String, Serializable>();
        credentialsMap.put(AuthenticationConstants.CAS_TICKET, ticket);

        return credentialsMap;
    }
}
