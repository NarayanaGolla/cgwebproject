package com.cog.service.repository;

import com.cog.service.dom.User;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
