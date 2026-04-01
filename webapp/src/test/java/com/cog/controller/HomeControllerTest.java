package com.cog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cog.webapp.SpringBootLauncer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = SpringBootLauncer.class)
@AutoConfigureMockMvc
public class HomeControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void testIndexReturnsHomeView() throws Exception {
    mockMvc.perform(get("/index")).andExpect(status().isOk()).andExpect(view().name("home"));
  }
}
