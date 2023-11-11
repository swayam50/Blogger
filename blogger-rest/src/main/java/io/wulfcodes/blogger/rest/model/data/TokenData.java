package io.wulfcodes.blogger.rest.model.data;

import java.util.Arrays;
import java.util.Objects;
import io.wulfcodes.blogger.rest.model.value.AuthenticationFormat;

public class TokenData {
    private String scheme;
    private String credentials;

    private TokenData(String scheme, String credentials) {
        this.scheme = scheme;
        this.credentials = credentials;
    }

    public static TokenData fetchTokenData(String token) {
        if (Objects.isNull(token) || token.isBlank())
            return null;

        String[] parts = token.split(" ");
        if (parts.length != 2)
            return null;

        if (Arrays.stream(AuthenticationFormat.values()).noneMatch(authenticationFormat -> authenticationFormat.getScheme().equals(parts[0])))
            return null;

        return new TokenData(parts[0], parts[1]);
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getCredentials() {
        return this.credentials;
    }
}
