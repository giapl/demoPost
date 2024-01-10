package com.example.demopost.controller;

import com.example.demopost.data.request.UserRegisterRequest;
import com.example.demopost.service.user.IUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registers")
public class RegisterAndLoginController {

  private final IUserRegisterService userRegisterService;


  @Autowired
  public RegisterAndLoginController(IUserRegisterService userRegisterService) {
    this.userRegisterService = userRegisterService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> RegisterUser( @RequestBody UserRegisterRequest userRequest) {
    userRegisterService.registerUser(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("register successful");
  }
}
