package com.webapp.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {

  private WebDriver driver;
  private WebElement textBox;
  private WebElement submitButton;

  // 1
  @Test(priority = 0)
  public void start_the_session() {

    // Set the path to chromedriver.exe
    // System.setProperty("webdriver.chrome.driver",
    // "C:\\Users\\91998\\OneDrive\\Desktop\\chrome\\chromedriver.exe");
    // Auto-downloads and sets the correct driver
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");
  }

  // 2
  @Test(priority = 1)
  public void take_action_on_browser() {
    driver.get("https://www.selenium.dev/selenium/web/web-form.html");
  }

  // 3
  @Test(priority = 2)
  public void get_title() {
    String title = driver.getTitle();
  }

  // 4
  @Test(priority = 3)
  public void establish_waiting_strategy() {
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
  }

  // 5
  @Test(priority = 4)
  public void find_an_element() {
    textBox = driver.findElement(By.name("my-text"));
    submitButton = driver.findElement(By.cssSelector("button"));
  }

  // 6
  @Test(priority = 5)
  public void take_action_on_element() {
    textBox.sendKeys("Selenium");
    submitButton.click();
  }

  // 7
  @Test(priority = 6)
  public void request_element_information() {
    WebElement message = driver.findElement(By.id("message"));
    message.getText();
  }

  // 8
  @Test(priority = 7)
  public void end_the_session() {
    driver.quit();
  }
}
