package com.twogenidentity.iam.identity.oauth2;

import com.twogenidentity.iam.identity.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class BearerTokenAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private String realmName;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {

        Map<String, String> parameters = new LinkedHashMap<>();
        if (this.realmName != null) {
            parameters.put("realm", this.realmName);
        }

        if (request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken) {

            // Just an example in the PoC for triggering insufficient_authentication_level error
            if (request.getRequestURI().equals("/api/v1/accounts") &&
                    request.getMethod().equals(HttpMethod.GET.toString())) {
                parameters.put("error", "insufficient_authentication_level");
                parameters.put("error_description", "A different level of authentication is required");
                parameters.put("acr_values", "loa2");
                // parameters.put("error_uri", "https://tools.ietf.org/html/rfcxxx"); RFC Approved but not released yet
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
            else {
                parameters.put("error", BearerTokenErrorCodes.INSUFFICIENT_SCOPE);
                parameters.put("error_description",
                        "The request requires higher privileges than provided by the access token.");
                parameters.put("error_uri", "https://tools.ietf.org/html/rfc6750#section-3.1");
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        }

        String wwwAuthenticate = computeWWWAuthenticateHeaderValue(parameters);
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, wwwAuthenticate);

    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    private String computeWWWAuthenticateHeaderValue(Map<String, String> parameters) {
        StringBuilder wwwAuthenticate = new StringBuilder();
        wwwAuthenticate.append("Bearer");
        if (!parameters.isEmpty()) {
            wwwAuthenticate.append(" ");
            int i = 0;
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                wwwAuthenticate.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
                if (i != parameters.size() - 1) {
                    wwwAuthenticate.append(", ");
                }
                i++;
            }
        }
        return wwwAuthenticate.toString();
    }
}