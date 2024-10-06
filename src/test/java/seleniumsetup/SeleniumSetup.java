package seleniumsetup;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import java.util.*;
public class SeleniumSetup extends BaseSetup{

    private static BrowserType browserType;
    private static final List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<>());
    private static final ThreadLocal<WebDriver> driverForThread = new ThreadLocal<>();

    // WebDriver and Page Objects
    public static WebDriver driver;

    // --- Refactored Cucumber Hooks --- //

    // Cucumber @Before hook to handle setup logic (replaces @BeforeSuite and @BeforeMethod)
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

    // Cucumber @After hook to handle teardown logic (replaces @AfterMethod and @AfterSuite)
    @After
    public void tearDown() {
        WebDriver driver = getDriver();
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
                return chromeOptions;
            case FIREFOX:
                return new FirefoxOptions();
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
    private static void setWebDriverPath(String driverName) {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        String binaryRoot = loadProperty("binaryRootFolder");
        System.out.println("The base directory is: "+binaryRoot);

        String path;
        if (os.contains("windows")) {
            path = String.format("%s/windows/%s/%s/%s.exe", binaryRoot, driverName, arch.contains("64") ? "64bit" : "32bit", driverName + "driver");
        } else if (os.contains("mac")) {
            path = String.format("%s/osx/%s/%s", binaryRoot, driverName, driverName);
        } else if (os.contains("linux")) {
            path = String.format("%s/linux/%s/%s", binaryRoot, driverName, driverName);
        } else {
            throw new IllegalArgumentException("Unknown OS: " + os);
        }

        System.setProperty("webdriver." + driverName + ".driver", path);
    }
}
