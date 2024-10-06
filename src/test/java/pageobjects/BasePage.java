package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * Base class for all page objects.
 */
public class BasePage {
    protected WebDriverWait wait;
    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver the WebDriver instance to use for page interactions
     */
    public BasePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
}
