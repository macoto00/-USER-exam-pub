package security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import repositories.UserRepository;
import security.SecurityConstants;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private final UserRepository userRepository;

    public ResponseCookie generateJwtCookie(Authentication userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getName());
        return generateCookie("token", jwt, "/", (int) (SecurityConstants.JWT_EXPIRATION_TIME / 1000));
    }

    public ResponseCookie generateJwtCookie(String username) {
        String jwt = generateTokenFromUsername(username);
        return generateCookie("token", jwt, "/", (int) (SecurityConstants.JWT_EXPIRATION_TIME / 1000));
    }

    public ResponseCookie generateRefreshJwtCookie(Authentication userPrincipal) {
        String jwt = generateRefreshTokenFromUsername(userPrincipal.getName());
        return generateCookie("refreshToken", jwt, "/", (int) (SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME / 1000));
    }

    private ResponseCookie generateCookie(String name, String value, String path, int maxAge) {
        ResponseCookie cookie = ResponseCookie.from(name, value).path(path).maxAge(maxAge).httpOnly(true).build();
        return cookie;
    }

    public String generateRefreshTokenFromUsername(String username) {
        Claims extraClaims = new DefaultClaims();
        UUID uuid = UUID.randomUUID();
        User user = userRepository.findByEmail(username).get();
        user.setUuid(uuid);
        extraClaims.put("sub", username);
        extraClaims.put("jti", uuid.toString());
        userRepository.save(user);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + SecurityConstants.JWT_EXPIRATION_TIME))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        try {
            return getCookieValueByName(request, "token");
        } catch (Exception e) {
            return null;
        }
    }

    public String getRefreshJwtFromCookies(HttpServletRequest request) {
        try {
            return getCookieValueByName(request, "refreshToken");
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserNameFromJwtToken(String token) {

        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserNameFromJwtRefreshToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody();
        String username = claims.get("sub", String.class);
        return username;
    }

    public String getRefreshTokenValidateAndGenerateAccessToken(HttpServletRequest request) {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }
        String refreshToken = getRefreshJwtFromCookies(request);
        if (validateJwtToken(refreshToken)) {

            String username = getUserNameFromJwtRefreshToken(refreshToken);
            return username;
        }
        return null;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from("token", "Clean").path("/").build();
        return cookie;
    }

    public ResponseCookie getCleanJwtRefreshCookie() {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "Cleaner").path("/").build();
        return cookie;
    }

    private String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET);
    }
}
