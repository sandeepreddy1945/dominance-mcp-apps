package com.ai.app.application.cas.security;

import org.springaicommunity.mcp.security.server.apikey.ApiKeyEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenProvider {

    private volatile String cachedToken;   // Thread-safe

    public String getToken() {
        // TODO imporve this part to be implemented  by cache
        if (cachedToken == null) {
            cachedToken = fetchTokenFromSource();
        }
        return cachedToken;
    }

    public void updateToken(String newToken) {
        this.cachedToken = newToken;
    }

    private String fetchTokenFromSource() {
        // TODO use the in memory apikey repo with id and fetch the security token
        // authenticate with user and then cache the token on redis for further processing
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof ApiKeyEntity entity) {
            System.out.println(entity.getId());
        }
        // call keycloak, database, vault etc.
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NjQ0NDk1NDQsImlhdCI6MTc2Mzc1ODM0NCwic3ViIjoiZDRjMDllMmQtMWYwYy00M2VlLWJlMTgtNDhlODI3MTA4MzdiIiwiaXNfc3VwZXJ1c2VyIjpmYWxzZSwicm9sZXMiOlsidXNlciJdfQ.mc4IbjFTKwRvVuIqkaGjRFZKvRxpPhr8SJ0rNuCBJm4";
    }
}
