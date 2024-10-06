package pageobjects.DelaSportPOM;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BasePageDelaSportTask;

import java.time.Duration;

/**
 * Page Object Model for DelaSport Dashboard.
 * This class contains methods and elements related to the dashboard.
 */
public class DelaSportDashBoardPOM extends BasePageDelaSportTask {

    @FindBy(id = "mainContent")
    private WebElement mainContent;

    @FindBy(xpath = "//span[contains(.,'Balance')]/..//span[contains(@class,'amount')]")
    private WebElement balanceField;

    @FindBy(xpath = "//div[@id='sportsbookModal']//button[contains(@class,'close')]")
    private WebElement modalClosePopUp;

    /**
     * Constructor to initialize the WebDriver and the base page object.
     *
     * @param driver the WebDriver instance to use for page interactions
     */
    public DelaSportDashBoardPOM(WebDriver driver) {
        super(driver);
    }

    /**
     * Handles the pop-up that appears after login.
     * Closes the pop-up if it is displayed.
     */
    public void handlePopUpAfterLogin() {
        waitUntilVisible(mainContent);
        closeModalIfPresent();
    }

    /**
     * Retrieves the balance information displayed on the dashboard.
     *
     * @return the balance as a string
     */
    public String getBalanceInfo() {
        handlePopUpAfterLogin();
        return waitUntilVisible(balanceField).getText();
    }

    /**
     * Waits for an element to be visible.
     *
     * @param element the WebElement to wait for
     * @return the WebElement once it is visible
     */
    private WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Closes the modal pop-up if it is displayed.
     */
    private void closeModalIfPresent() {
        try {
            waitUntilClickable(modalClosePopUp).click();
        } catch (Exception e) {
            // Modal close button not found; no action required
            System.out.println("The pop-up was not displayed.");
            System.out.println("Stack trace:"+e);
        }
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param element the WebElement to wait for
     * @return the WebElement once it is clickable
     */
    private WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
