package com.cog.services;

import com.cog.bean.TraineeBean;
import com.cog.repository.JsonDatabase;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

  private JsonDatabase jsonDatabase;

  public ReportService(JsonDatabase jsonDatabase) {
    this.jsonDatabase = jsonDatabase;
  }

  public List<String> readUserData() {
    // List<TraineeBean> userList = jsonDatabase.getAllUsers();
    // List<String> userNameList = userList.stream().map(TraineeBean::getName).toList();

    Optional<List<TraineeBean>> userList = Optional.ofNullable(jsonDatabase.getAllUsers());
    List<String> userNameList =
        userList.orElse(Collections.emptyList()).stream()
            .map(TraineeBean::getName)
            .distinct()
            .toList();

    // Set<String> userNameSet = new HashSet<>();

    // Check if the list is present and non-empty
    //    userList.ifPresent(
    //        list -> {
    //          if (!list.isEmpty()) {
    //            list.forEach(
    //                user -> {
    //                  userNameSet.add(user.getName()); // Example operation
    //                });
    //          } else {
    //            System.out.println("No users found.");
    //          }
    //        });
    LOGGER.info("Trainee List - {} ", userNameList.size());
    return userNameList;
  }
}
