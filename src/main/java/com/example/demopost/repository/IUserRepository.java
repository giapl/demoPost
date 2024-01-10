package com.example.demopost.repository;

import com.example.demopost.data.enity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Account, Long> {

  @Query(value = "select * from account where username = :username",nativeQuery = true)
  Optional<Account> findByUsername(@Param("username") String username);

  Boolean existsByEmail(@Param("email") String email);

  Boolean existsByUsername(@Param("username") String username);
}
