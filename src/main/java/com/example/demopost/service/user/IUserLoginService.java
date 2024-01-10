package com.example.demopost.service.user;

import com.example.demopost.data.enity.Account;
import com.example.demopost.data.request.UserLoginRequest;


public interface IUserLoginService {

  Account login(UserLoginRequest loginRequest);

}
