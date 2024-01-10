package com.example.demopost.service.user;

import com.example.demopost.data.enity.Account;
import com.example.demopost.data.request.UserRegisterRequest;

public interface IUserRegisterService {

  Account registerUser(UserRegisterRequest userRequest); // register user



}
