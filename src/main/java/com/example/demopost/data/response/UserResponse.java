package com.example.demopost.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

  private Long id;
  private String username;
  private String password;
  private String roles;
  @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
  private LocalDateTime dateTime;
}
