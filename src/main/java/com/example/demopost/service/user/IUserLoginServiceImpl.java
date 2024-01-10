package com.example.demopost.service.user;

import com.example.demopost.data.enity.Account;
import com.example.demopost.data.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IUserLoginServiceImpl implements IUserLoginService {

  private final AuthenticationManager authenticationManager;


  @Autowired
  public IUserLoginServiceImpl(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Account login(UserLoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
            loginRequest.getPassword())); // lay ra thong tin username and password de xac thuc
    SecurityContextHolder.getContext().setAuthentication(authentication);  // bao mat thong tin and xem Role
    return (Account) authentication.getPrincipal();
  }
}
