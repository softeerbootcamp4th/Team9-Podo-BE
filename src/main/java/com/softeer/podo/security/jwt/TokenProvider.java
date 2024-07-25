package com.softeer.podo.security.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.softeer.podo.user.model.entity.Role;
import com.softeer.podo.security.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenProvider {

    private final long ACCESS_TOKEN_VALID_TIME = (1000*60*60*24); //day
    private final long REFRESH_TOKEN_VALID_TIME = (1000*60*60*24*7); //week
    private final String BEARER_TYPE = "Bearer ";

    @Value("${secret.jwt}")
    private String baseSecretKey;

    public TokenInfo createAccessToken(String name, String number, Role role) {
        return createEncryptedToken(name, number, role, ACCESS_TOKEN_VALID_TIME);
    }

    public TokenInfo createRefreshToken(String name, String number, Role role) {
        return createEncryptedToken(name, number, role, REFRESH_TOKEN_VALID_TIME);
    }

    /**
     * 암호화된 Jwt 토큰 만들기
     * @return TokenInfo
     */
    private TokenInfo createEncryptedToken(String name, String number, Role role, long validTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime()+validTime);
        System.out.println("baseSecretKey = " + baseSecretKey);
        byte[] secretKey = getSecretKey();

        try {
            // Jwt Claim Set 생성
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .claim("name", name)
                    .claim("number", number)
                    .claim("ROLE_", role)
                    .issueTime(now)
                    .expirationTime(expiration)
                    .build();

            // jwt 생성 후 [서명]
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(new MACSigner(secretKey));


            // jwe 생성
            JWEObject jweObject = new JWEObject(
                    new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A256GCM),
                    new Payload(signedJWT)
            );

            // jwe [암호화]
            jweObject.encrypt(new DirectEncrypter(secretKey));

            String token = BEARER_TYPE + jweObject.serialize();
            return new TokenInfo(token, expiration.getTime() - now.getTime());
        } catch (KeyLengthException e) {
            throw new InvalidTokenException("JWE Token length error - 비밀 키 길이 예외 발생 -> "+e.getMessage());
        } catch (JOSEException e) {
            throw new InvalidTokenException("JWE Token create error - 토큰 생성 중 예외 발생 -> "+e.getMessage());
        }
    }

    /**
     * 암호화된 토큰을 복호화하기
     */
    public JWTClaimsSet validateTokenAndGetClaimsSet(String token) {
        byte[] secretKey = getSecretKey();

        try {
            JWEObject jweObject = JWEObject.parse(token);
            jweObject.decrypt(new DirectDecrypter(secretKey));
            // payload 추출
            SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

            JWSVerifier verifier = new MACVerifier(secretKey);
            if (!signedJWT.verify(verifier)) {
                throw new InvalidTokenException("Token signature is invalid");
            }

            return signedJWT.getJWTClaimsSet();

        } catch (JOSEException | ParseException e) {
            throw new InvalidTokenException("JWE Token Decoding Error - 토큰 검증과정에서 오류 발생");
        }
    }

    public void setAttributesFromClaim(HttpServletRequest request, JWTClaimsSet claimsSet) {
        request.setAttribute("name", claimsSet.getClaim("name"));
        request.setAttribute("number", claimsSet.getClaim("number"));
        request.setAttribute("ROLE_", claimsSet.getClaim("ROLE_"));
    }

    /**
     * Bearer String 제외
     */
    public String resolveToken(String token) {
        if(token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }
        return null;
    }

    public Claims parseClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(baseSecretKey));
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private byte[] getSecretKey() {
        return Base64.getDecoder().decode(baseSecretKey);
    }
}
