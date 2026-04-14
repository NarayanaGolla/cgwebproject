package com.cog.bean;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBean {

  private String username;
  private String password;

  private Set<String> roles;
}
