package com.example.demo.service;

import com.example.demo.model.dto.AuthenticationResult;
import com.example.demo.requests.AuthenticationRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticateService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecretThisIsASecretThisIsASecretThisIsASecretThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    private static final List<String> ALLOWED_ACCOUNT = List.of("trung-0411");

    public AuthenticationResult login(AuthenticationRequest request) {
        if (!ALLOWED_ACCOUNT.contains(String.format("%s-%s", request.getUserName(), request.getPassword()))) {
            return new AuthenticationResult("", "failed");
        }

        return new AuthenticationResult(TOKEN_PREFIX + " " + generateJWT(request.getUserName()), "success");
    }

    public boolean validateToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim());

            Claims claims = claimsJws.getBody();
            Date expirationDate = claims.getExpiration();
            return !expirationDate.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    private String generateJWT(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
