package com.example.demopost.service.user;

import com.example.demopost.data.enity.Role;
import com.example.demopost.data.enity.Account;
import com.example.demopost.data.request.UserRegisterRequest;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.repository.IRoleRepository;
import com.example.demopost.repository.IUserRepository;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IUserRegisterServiceImpl implements IUserRegisterService {

  private final IRoleRepository roleRepository;

  private final IUserRepository userRepository;

  private final PasswordEncoder passwordEncoder;


  @Autowired
  public IUserRegisterServiceImpl(IRoleRepository roleRepository,
      IUserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public Account registerUser(@NotNull UserRegisterRequest userRequest) {

    if (userRepository.existsByUsername(userRequest.getUsername())) {
      throw new NotFoundException("username da ton tai");
    }
    if (userRepository.existsByEmail(userRequest.getEmail())) {
      throw new NotFoundException("email da ton tai");
    }
    if (userRequest.getPassword() == null) {
      throw new NotFoundException("password erroll null");
    }
    Account user = new Account(); // create new 1 account
    user.setUsername(userRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));  // ma hoa password sava database
    Role role = roleRepository.findByRoleName("USER").get(); // search Role USER sau do .get() lay ra
    user.setRoles(role); // gan Role = "USER"  vao
    user.setEmail(userRequest.getEmail());
    user.setDateTime(LocalDateTime.now());
    user.setUpdateTime(LocalDateTime.now());
    return userRepository.save(user);
  }

}
