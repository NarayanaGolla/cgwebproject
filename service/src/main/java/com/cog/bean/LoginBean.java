package com.cog.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBean {

  @NotNull(message = "Id is required")
  private Long id;

  private String username;

  @NotBlank(message = "password must not be blank")
  private String password;
}
