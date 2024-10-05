package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base class for all DelaSport page objects.
 * Provides common functionality such as WebDriver initialization and WebDriverWait.
 */
public class BasePageDelaSportTask {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver the WebDriver instance to use for page interactions
     */
    public BasePageDelaSportTask(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the WebDriver instance.
     *
     * @return the WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * Gets the WebDriverWait instance.
     *
     * @return the WebDriverWait instance
     */
    protected WebDriverWait getWait() {
        return wait;
    }
}
