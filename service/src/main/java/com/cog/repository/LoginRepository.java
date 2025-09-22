package com.cog.repository;

import com.cog.dom.Login;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface LoginRepository extends ListCrudRepository<Login, Long> {

  Optional<Login> findByUsername(String username);
}
