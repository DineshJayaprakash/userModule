package UserModule.UserModule.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * class JwtUtil
 * 
 * @created By Dinesh J
 * @created Date 09/12
 * @description used to generate and validate the token
 */
@Service
public class JwtUtil {
	
	/**
	 * Secret key for encryption
	 */
	@Value("${jwt.secret.key}")
    private String secret;

    /**
     * function extractUsername
     * @param token
     * @return userName
     * @description extracted userName from token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    
    /**
     * function extractExpiration
     * 
     * @param token
     * @return expireDate
     * @description extracted expireDate from token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
  
    /**
     * function extractClaim
     * 
     * @param token,claimsResolver
     * @return T type
     * @description used to extract the claim from token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * function extractAllClaims
     * 
     * @param token
     * @return Claims
     * @description extract all the claims from token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * function isTokenExpired
     * 
     * @param token
     * @return boolean
     * @description check whether token expired or not
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * function generateToken
     * 
     * @param username
     * @return token
     * @description used to generate the token
     */    
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * function createToken
     * 
     * @param claims,subject
     * @return token
     * @description used to generate the token based on the given claims and username from create token
     */   
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    /**
     * function validateToken
     * 
     * @param token,userDetails
     * @return boolean
     * @description used to check the token is valid or not 
     */  
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
