package com.example.demopost.service.user;

import com.example.demopost.data.enity.Account;
import com.example.demopost.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final IUserRepository iUserRepository;

  @Autowired
  public CustomUserDetailsService(IUserRepository iUserRepository) {
    this.iUserRepository = iUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account user = iUserRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("no username" + username));
    return user; // tra ve thong tin username , password , role de su dung xac thuc
  }


}
