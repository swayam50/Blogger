package io.wulfcodes.blogger.rest.tokenizer;

import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;

@Component
public class JWTTokenizer {

    @Autowired
    private Algorithm algorithm;

    @Value("spring.security.token.issuer")
    private String issuer;

    @Value("spring.security.token.subject")
    private String subject;

    @Value("spring.security.token.audience")
    private String audience;

    public String createToken(Map<String, String> claims) {

        return JWT.create()
                  .withIssuer(issuer)
                  .withSubject(subject)
                  .withAudience(audience)
                  .withPayload(claims)
                  .sign(algorithm);
    }

    public Map<String, Claim> verifyToken(String token) {
        try {
            return JWT.require(algorithm)
                      .withIssuer(issuer)
                      .withSubject(subject)
                      .withAudience(audience)
                      .build()
                      .verify(token)
                      .getClaims();
        } catch (JWTVerificationException ex) {
            return Collections.emptyMap();
        }
    }
}
