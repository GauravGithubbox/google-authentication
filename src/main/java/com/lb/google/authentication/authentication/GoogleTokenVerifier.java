package com.lb.google.authentication.authentication;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

@Slf4j
public class GoogleTokenVerifier {

    private final String clientId;
    private final NetHttpTransport netHttpTransport;
    private final GsonFactory gsonFactory;

    public GoogleTokenVerifier(final NetHttpTransport netHttpTransport, final GsonFactory gsonFactory, final String clientId) {
        this.clientId = clientId;
        this.gsonFactory = gsonFactory;
        this.netHttpTransport = netHttpTransport;
    }

    public Payload verifyToken(String authToken) throws GeneralSecurityException, IOException {
        final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(netHttpTransport, gsonFactory)
                .setAudience(Collections.singleton(this.clientId))
                .setIssuer("https://accounts.google.com")
                .build();
        final GoogleIdToken token = verifier.verify(authToken);
        if (token == null) {
            log.warn("authentication failed: no token or maybe client Id is wrong");
            throw new BadCredentialsException("Invalid token");
        }
        return token.getPayload();

    }
}