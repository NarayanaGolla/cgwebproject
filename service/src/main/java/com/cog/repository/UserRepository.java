package com.cog.repository;

import com.cog.dom.User;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
