package com.cog.service.repository;

import com.cog.service.dom.Login;
import com.cog.service.dom.Register;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface RegisterRepository extends ListCrudRepository<Register, Long> {
  Optional<Login> findByUsername(String username);
}
