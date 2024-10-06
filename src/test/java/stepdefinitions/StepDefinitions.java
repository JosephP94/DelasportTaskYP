package stepdefinitions;

import extension.MainExtensions;
import helpers.DashboardHelpers;
import helpers.LoginHelpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.DashboardResponse;
import org.testng.Assert;
import pageobjects.pom.Dashboard;
import pageobjects.pom.Login;
import seleniumsetup.BaseSetup;

import java.io.IOException;

import static seleniumsetup.SeleniumSetup.driver;

public class StepDefinitions extends BaseSetup {
    private static final String CURRENCY_SYMBOL = "€";
    private static final String BALANCE_KEY = "balance";
    private static final String BALANCE_NOT_FOUND_MESSAGE = "Not found";
    private Login login;
    private Dashboard dashboard;


    @Given("the user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        login = new Login(driver);
        login.goToLoginPage();
    }

    @Given("the user logs in with valid username and password")
    public void theUserLogsInWithValidUsernameAndPassword() {
        login = new Login(driver);
        login.populateLoginDetails(loadProperty("validUserName"), loadProperty("validPassword"));
        login.submitLoginButtonClick();
    }

    @When("the user closes any modals")
    public void the_user_closes_any_modals() {
        dashboard = new Dashboard(driver);
        dashboard.handlePopUpAfterLogin();
    }

    @Then("the balance in the header should match the balance from the API response of getMemberBalance")
    public void theBalanceInTheHeaderShouldMatchTheBalanceFromTheAPIResponseOfGetMemberBalance() throws IOException, InterruptedException {
        dashboard = new Dashboard(driver);
        String cookies = LoginHelpers.login(loadProperty("validUserName"), loadProperty("validPassword"));
        String response = DashboardHelpers.getDashboardBalance(cookies);
        DashboardResponse dashboardResponse = MainExtensions.fromJson(response, DashboardResponse.class);

        String expectedBalance = dashboardResponse.getData().values().stream()
                .filter(data -> BALANCE_KEY.equals(data.getInfo().getKey()))
                .map(data -> data.getInfo().getRawAmount())
                .findFirst()
                .orElse(BALANCE_NOT_FOUND_MESSAGE);

        Assert.assertEquals(dashboard.getBalanceInfo().replace(CURRENCY_SYMBOL, ""), expectedBalance,
                "The balance from the getMemberBalance request is different from the balance shown on the dashboard.");
    }
}
