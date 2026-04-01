package com.cog.dom;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<Role> roles = new HashSet<>();
}
