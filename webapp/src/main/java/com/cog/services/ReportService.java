package com.cog.services;

import com.cog.bean.TraineeBean;
import com.cog.repository.JsonDatabase;
import java.util.List;
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
    List<TraineeBean> userList = jsonDatabase.getAllUsers();
    List<String> userNameList = userList.stream().map(TraineeBean::getName).toList();
    LOGGER.info("Trainee List - {} ", userNameList.size());
    return userNameList;
  }
}
