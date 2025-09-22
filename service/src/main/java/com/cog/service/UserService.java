package com.cog.service;

import com.cog.bean.RegisterBean;
import com.cog.dom.Login;
import com.cog.dom.User;
import com.cog.repository.LoginRepository;
import com.cog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final LoginRepository loginRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(
      UserRepository userRepository,
      LoginRepository loginRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.loginRepository = loginRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void createUser(Login login) {
    login.setUsername(login.getUsername());
    login.setPassword(passwordEncoder.encode(login.getPassword()));
    loginRepository.save(login);
  }

  public User registerUser(RegisterBean registerBean) {
    User register = new User();
    register.setUsername(registerBean.getUsername());
    register.setPassword(passwordEncoder.encode(registerBean.getPassword()));
    userRepository.save(register);

    return register;
  }
}
