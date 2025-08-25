package com.cog.service.repository;

import com.cog.service.dom.Login;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface LoginRepository extends ListCrudRepository<Login, Long> {

  Optional<Login> findByUsername(String username);
}
