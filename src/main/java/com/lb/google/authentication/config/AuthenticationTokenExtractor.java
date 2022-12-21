package com.lb.google.authentication.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.util.Optional;

import static com.lb.google.authentication.util.Constant.*;


@Slf4j
@AllArgsConstructor
@Component
public class AuthenticationTokenExtractor extends Authenticator implements AuthenticationConverter {


    private static final String ERROR_MESSAGE = "Auth token maybe null or incorrect";

    @Override
    public Authentication convert(HttpServletRequest request) {
        final String xTenantId = request.getHeader(TENANT_ID);
        if (xTenantId == null) throw getHeaderException();

        final String jwtToken = Optional.ofNullable(request.getHeader(AUTH_TOKEN_NAME))
                .map(id -> id.substring(7))
                .orElseThrow(this::getException);
        return new PreAuthenticatedAuthenticationToken(xTenantId, jwtToken);
    }
    private AuthenticationException getException() {
        return new BadCredentialsException(ERROR_MESSAGE);
    }

    private AuthenticationException getHeaderException() {
        return new BadCredentialsException(TENANT_ID_MISSING);
    }
}