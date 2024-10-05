package pageobjects.DelaSportPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BasePageDelaSportTask;

/**
 * Page Object Model for DelaSport Login.
 * This class contains methods and elements related to the login page.
 */
public class DelaSportLoginPOM extends BasePageDelaSportTask {

    @FindBy(xpath = "//button[contains(.,'Log In')]")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='login_form[username]']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='login-modal-password-input']")
    private WebElement passwordField;

    @FindBy(xpath = "//form[@id='login-modal-form']//button[contains(.,'Log In')]")
    private WebElement submitLoginButton;

    /**
     * Constructor to initialize the WebDriver and the base page object.
     *
     * @param driver the WebDriver instance to use for page interactions
     */
    public DelaSportLoginPOM(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the login page by clicking the login button.
     */
    public void goToLoginPage() {
        waitUntilClickable(loginButton).click();
    }

    /**
     * Submits the login form by clicking the submit login button.
     */
    public void submitLoginButtonClick() {
        waitUntilClickable(submitLoginButton).click();
    }

    /**
     * Populates the login details in the username and password fields.
     *
     * @param email    the user's email address
     * @param password the user's password
     */
    public void populateLoginDetails(String email, String password) {
        waitUntilVisible(userNameField).sendKeys(email);
        waitUntilVisible(passwordField).sendKeys(password);
    }

    /**
     * Performs the login action by navigating to the login page,
     * populating login details, and submitting the form.
     *
     * @param userName the user's email address
     * @param password the user's password
     */
    public void logIn(String userName, String password) {
        goToLoginPage();
        populateLoginDetails(userName, password);
        submitLoginButtonClick();
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
     * Waits for an element to be clickable.
     *
     * @param element the WebElement to wait for
     * @return the WebElement once it is clickable
     */
    private WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
