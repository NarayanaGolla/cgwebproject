package com.cog.repository;

import com.cog.dom.Login;
import com.cog.dom.Register;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface RegisterRepository extends ListCrudRepository<Register, Long> {
  Optional<Login> findByUsername(String username);
}
