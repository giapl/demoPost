package com.example.demopost.service.jwt;

import com.example.demopost.data.enity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtService {

  private final String SECRET_KEY = "lucky"; // key bi mat
  private Long jwtExpiration = 86400l; // thoi gian co hieu luc token

  private Long refreshExpiration = 604800l; // thoi gian co hieu luc token khi lam moi

  // method generateToken
  private String generateToken(Account account) {
    return Jwts.builder()
        .setSubject(account.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis())) //thoi gian tao token
        .setExpiration(
            new Date(System.currentTimeMillis() + jwtExpiration)) // thoi gian token het hieu luc
        .signWith(SignatureAlgorithm.ES256, getSignInKey()) // ma hao bang ES256 va base64
        .compact();
  }

  // lay thong tin nguoi dung tu jwt
  private Claims extraClaimsAll(String token) {
    return Jwts
        .parser()
        .setSigningKey(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getBody();

  }

  //xac minh token co hop le hay ko
  private Boolean validateToken(String authToken) {
    try {
      Jwts
          .parser() // phân tích cú pháp trong chuoi jwt như header paload sing và xác thuc thông tin
          .setSigningKey(getSignInKey()) // khoa bi mật để parser xác thực
          .build() // buld ra
          .parseClaimsJws(authToken); // phân tích và xác thực chuỗi jwt authToken
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid Jwt Token"); // Xảy ra khi JWT không đúng định dạng.
    } catch (IllegalArgumentException ex) {
      log.error("Jwt token is empty"); // Xảy ra khi chuỗi các thông tin của JWT là rỗng.
    } catch (ExpiredJwtException ex) {
      log.error("Expired Jwt Token"); //Xảy ra khi JWT đã hết hạn.
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token"); // Xảy ra khi JWT không được hỗ trợ.
    }
    return false;
  }

  // method ma hoa key bi mat
  private Key getSignInKey() {
    byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(bytes);
  }

}
