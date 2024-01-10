package com.example.demopost.repository;

import com.example.demopost.data.enity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role , Long> {

  @Query( value = "select * from role where role_name = :role_name",nativeQuery = true)
  Optional<Role> findByRoleName(@Param("role_name") String roleName);
}
