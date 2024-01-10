package com.example.demopost.controller;

import com.example.demopost.data.request.UserLoginRequest;
import com.example.demopost.service.user.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

  private final IUserLoginService userLoginService;

  @Autowired
  public LoginController(IUserLoginService userLoginService) {
    this.userLoginService = userLoginService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest) {
    userLoginService.login(loginRequest);
    return ResponseEntity.status(HttpStatus.OK).body("login successful");
  }

}
