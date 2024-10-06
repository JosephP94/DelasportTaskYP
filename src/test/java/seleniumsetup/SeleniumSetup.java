package seleniumsetup;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.SessionId;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
public class SeleniumSetup extends BaseSetup{

    private static BrowserType browserType;
    private static final List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<>());
    private static final ThreadLocal<WebDriver> driverForThread = new ThreadLocal<>();

    // WebDriver and Page Objects
    public static WebDriver driver;

    @Before
    public void setUpTest() {
        String browser = loadProperty("browser").toUpperCase();
        try {
            browserType = BrowserType.valueOf(browser);
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown browser specified, defaulting to 'FIREFOX'...");
            browserType = BrowserType.CHROME; // Default if unknown
        }

        // Initialize WebDriver
        driver = loadWebDriver();
        driverForThread.set(driver);
        webDrivers.add(driver);
        driver.get(loadProperty("address"));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = getDriver();

        // Check if the scenario failed
        if (scenario.isFailed() && driver != null) {
            // Take a screenshot and save it
            takeScreenshot(scenario.getName().replace(" ",""));
        }

        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }

        // Clean up after all tests
        for (WebDriver webDriver : webDrivers) {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    protected static WebDriver getDriver() {
        return driverForThread.get();
    }

    // Generate the desired options based on the browser type
    private static AbstractDriverOptions<?> generateBrowserOptions(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-default-browser-check");
                chromeOptions.setExperimentalOption("prefs", Map.of("profile.password_manager_enabled", false));
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--incognito");
                return chromeOptions;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addPreference("browser.privatebrowsing.autostart", true);
                return firefoxOptions;
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
            case FIREFOX:
                return new FirefoxDriver((FirefoxOptions) browserOptions);
            default:
                return new ChromeDriver((ChromeOptions) browserOptions);
        }
    }

    // Set WebDriver binary paths based on the operating system
    private static void setWebDriverPath(String browserName) {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        String binaryRoot = loadProperty("binaryRootFolder");
        String driverName;
        String path;

        switch (browserName.toLowerCase()) {
            case "firefox":
                driverName = "geckodriver.exe";
                break;
            case "chrome":
                driverName = "chromedriver.exe";
                break;
            default:
                driverName = "chromedriver.exe"; // Fallback or unknown driver
                break;
                }
            if (os.contains("windows")) {
                path = String.format("%s%swindows%s%s%s%s%s%s",
                        binaryRoot,
                        File.separator,
                        File.separator,
                        browserName,
                        File.separator,
                        arch.contains("64") ? "64bit" : "32bit",
                        File.separator,
                        driverName);
                System.out.println("The path is "+path);
                System.out.println("The driver name is "+driverName);
                System.out.println("The browser name is "+browserName);

            } else if (os.contains("mac")) {
                path = String.format("%s/osx/%s/%s", binaryRoot, driverName, driverName);
            } else if (os.contains("linux")) {
                path = String.format("%s/linux/%s/%s", binaryRoot, driverName, driverName);
            } else {
                throw new IllegalArgumentException("Unknown OS: " + os);
            }
        System.setProperty("webdriver." + driverName + ".driver", path);
    }

    private void takeScreenshot(String testName) {
        WebDriver driver = getDriver();

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Define the screenshot file name with test name and timestamp
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotDir = "screenshots";
            String filePath = screenshotDir + "/" + testName + "_" + timestamp + ".png";

            // Create the screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get(screenshotDir));

            // Save the screenshot to the specified file
            File destFile = new File(filePath);
            Files.copy(screenshot.toPath(), destFile.toPath());

            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
