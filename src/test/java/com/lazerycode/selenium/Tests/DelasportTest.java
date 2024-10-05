package com.lazerycode.selenium.Tests;

import ExtensionMethods.MainExtensions;
import com.lazerycode.selenium.SeleniumSetup;
import helpers.Dashboard;
import helpers.Login;
import models.DashboardResponse;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.RequestId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Optional;

public class DelasportTest extends SeleniumSetup {

    private static final String CURRENCY_SYMBOL = "€";
    private static final String BALANCE_KEY = "balance";
    private static final String BALANCE_NOT_FOUND_MESSAGE = "Not found";
    /**
     * Verifies that the balance in the header is the same as the balance from getBalanceMethod() after login.
     */
    @Test(description = "Verify balance in the header is correct after login")
    public void verifyBalanceAfterLogin() throws InterruptedException, IOException {
        // Add the login helper here
        String cookies = Login.login(loginVariables.getUserName(), loginVariables.getPassword());
        String response = Dashboard.getDashboardBalance(cookies);

        // Deserialize the response into a DashboardResponse object
        DashboardResponse dashboardResponse = MainExtensions.fromJson(response, DashboardResponse.class);
        // Perform login
        loginPOM.logIn(loginVariables.getUserName(), loginVariables.getPassword());

        // Retrieve the raw amount for the balance
        String rawAmount = dashboardResponse.getData().values().stream()
                .filter(data -> BALANCE_KEY.equals(data.getInfo().getKey()))
                .map(data -> data.getInfo().getRawAmount())
                .findFirst()
                .orElse(BALANCE_NOT_FOUND_MESSAGE);

        // Validate that the raw amount matches the dashboard balance
        Assert.assertEquals(rawAmount, dashBoardPOM.getBalanceInfo().replace(CURRENCY_SYMBOL, ""),
                "The balance from the getMemberBalance request is different from the balance shown on the dashboard.");
    }
}