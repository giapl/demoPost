package com.example.demopost.security;

import com.example.demopost.data.enity.Account;
import com.example.demopost.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {

  private final Account account;
  private final JwtService jwtService;

  public AuthenticationFilter(Account account, JwtService jwtService) {
    this.account = account;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

//    try {
//      String Jwt = getJwtFromRequest(request);
//    } catch ()

  }
  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    // Kiểm tra xem header Authorization có chứa thông tin jwt không
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}

