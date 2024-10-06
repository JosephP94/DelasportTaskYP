package pageobjects.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BasePage;


public class Login extends BasePage {
    @FindBy(xpath = "//button[contains(.,'Log In')]")
    private WebElement loginButton;

    @FindBy(id = "login_form[username]")
    private WebElement userNameField;

    @FindBy(id = "login-modal-password-input")
    private WebElement passwordField;

    @FindBy(xpath = "//form[@id='login-modal-form']//button[contains(.,'Log In')]")
    private WebElement submitLoginButton;

    /**
     * Constructor to initialize the WebDriver and the base page object.
     *
     * @param driver the WebDriver instance to use for page interactions
     */
    public Login(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks on the Log In button from the header
     */
    public void goToLoginPage() {
        waitUntilClickable(loginButton).click();
    }

    /**
     * Clicks on the login button from the Account Login page
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
     * @return the WebElement
     */
    private WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param element the WebElement to wait for
     * @return the WebElement
     */
    private WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
