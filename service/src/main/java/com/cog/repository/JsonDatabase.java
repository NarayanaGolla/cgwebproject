package com.cog.repository;

import com.cog.bean.TraineeBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class JsonDatabase {

  private final ObjectMapper mapper = new ObjectMapper();
  private final File file = new File("users.json");

  // Load all users
  public List<TraineeBean> getAllUsers() {
    try {
      if (!file.exists()) return new ArrayList<>();
      return mapper.readValue(file, new TypeReference<List<TraineeBean>>() {});
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  // Save user
  public void saveUser(TraineeBean user) {
    try {
      List<TraineeBean> users = getAllUsers();
      users.add(user);
      mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
