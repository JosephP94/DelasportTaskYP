package com.lazerycode.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pageobjects.DelaSportPOM.DelaSportDashBoardPOM;
import pageobjects.DelaSportPOM.DelaSportLoginPOM;
import variables.LoginVariables;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SeleniumSetup {

  protected static ResourceBundle prop = ResourceBundle.getBundle("dev");
  private static BrowserType browserType;
  private static List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<>());
  private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<>();

  // WebDriver and Page Objects
  public static WebDriver driver;
  public DelaSportLoginPOM loginPOM;
  public DelaSportDashBoardPOM dashBoardPOM;
  public LoginVariables loginVariables;

  @BeforeSuite
  public static void setUpTest() {
    String browser = loadProperty("browser", "FIREFOX").toUpperCase();
    try {
      browserType = BrowserType.valueOf(browser);
    } catch (IllegalArgumentException e) {
      System.err.println("Unknown browser specified, defaulting to 'FIREFOX'...");
      browserType = BrowserType.FIREFOX; // Default if unknown
    }
  }

  private static String loadProperty(String key) {
    // Calls the overloaded method with a default value of an empty string
    return loadProperty(key, "");
  }

  private static String loadProperty(String key, String defaultValue) {
    Properties properties = new Properties();
    String value = defaultValue; // Default value
    try (InputStream inputStream = SeleniumSetup.class.getClassLoader().getResourceAsStream("config.properties")) {
      if (inputStream == null) {
        System.err.println("Could not find properties file. Defaulting to " + defaultValue + ".");
        return value;
      }
      properties.load(inputStream);
      value = properties.getProperty(key, defaultValue); // Get property or use default
    } catch (IOException e) {
      System.err.println("Error loading properties file. Defaulting to " + defaultValue + ".");
    }
    return value;
  }


  @BeforeMethod
  public void startTest() {
    // Initialize WebDriver
    WebDriver driver = loadWebDriver();
    driverForThread.set(driver);
    webDrivers.add(driver);
    this.driver = driver;
    driver.get(loadProperty("address", "https://luckybandit.club.test-delasport.com/en/sports"));
    driver.manage().window().maximize();

    // Initialize Page Objects with WebDriver
    loginPOM = new DelaSportLoginPOM(driver);
    dashBoardPOM = new DelaSportDashBoardPOM(driver);
    loginVariables = new LoginVariables(loadProperty("userName"), loadProperty("password"));
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriver driver = getDriver();
    if (driver != null) {
      driver.manage().deleteAllCookies();
      driver.quit();
    }
  }

  @AfterSuite(alwaysRun = true)
  public void cleanUp() {
    for (WebDriver driver : webDrivers) {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  protected static WebDriver getDriver() {
    return driverForThread.get();
  }

  // Generate the desired options
  private static AbstractDriverOptions<?> generateBrowserOptions(BrowserType browserType) {
    switch (browserType) {
      case CHROME:
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-default-browser-check");
        chromeOptions.setExperimentalOption("prefs", Map.of("profile.password_manager_enabled", false));
        chromeOptions.addArguments("--headless");
        return chromeOptions;
      case FIREFOX:
        return new FirefoxOptions();
      case IE:
        return new InternetExplorerOptions();
      default:
        throw new IllegalArgumentException("Unknown browser type: " + browserType);
    }
  }

  private static WebDriver loadWebDriver() {
    System.out.println("Operating System: " + System.getProperty("os.name"));
    System.out.println("Architecture: " + System.getProperty("os.arch"));
    System.out.println("Browser: " + browserType);

    AbstractDriverOptions<?> browserOptions = generateBrowserOptions(browserType);
    setWebDriverPath(browserType.name().toLowerCase());

    switch (browserType) {
      case CHROME:
        return new ChromeDriver((ChromeOptions) browserOptions);
      case IE:
        return new InternetExplorerDriver((InternetExplorerOptions) browserOptions);
      case FIREFOX:
        return new FirefoxDriver((FirefoxOptions) browserOptions);
      default:
        throw new IllegalArgumentException("Unsupported browser: " + browserType);
    }
  }

  // Method to dynamically set WebDriver binary paths
  private static void setWebDriverPath(String driverName) {
    String os = System.getProperty("os.name").toLowerCase();
    String arch = System.getProperty("os.arch").toLowerCase();
    String binaryRoot = prop.getString("binaryRootFolder");
    String path;
    if (os.contains("windows")) {  // Use contains to handle all variations of "Windows"
      path = String.format("%s/windows/%s/%s/%s.exe", binaryRoot, driverName, arch.contains("64") ? "64bit" : "32bit", driverName + "driver");
    } else if (os.contains("mac")) { // Handles variations like "mac os x"
      path = String.format("%s/osx/%s/%s", binaryRoot, driverName, driverName);
    } else if (os.contains("linux")) { // Handles variations like "linux"
      path = String.format("%s/linux/%s/%s", binaryRoot, driverName, driverName);
    } else {
      throw new IllegalArgumentException("Unknown OS: " + os);
    }

    System.setProperty("webdriver." + driverName + ".driver", path);
  }
}
