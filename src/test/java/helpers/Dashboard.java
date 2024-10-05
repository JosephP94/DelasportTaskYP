package helpers;

import requests.DashboardRequests;
import variables.URLVariables;

import java.io.IOException;

public class Dashboard {

    private static final URLVariables URL_VARIABLES = new URLVariables();
    private static final DashboardRequests DASHBOARD_REQUESTS = new DashboardRequests();
    /**
     * Retrieves the dashboard balance for the given session ID.
     *
     * @return the balance information in JSON format
     * @throws IOException if there is an error during the request
     */
    public static String getDashboardBalance(String cookies) throws IOException {
        return DASHBOARD_REQUESTS.getBalanceInfoRequest( URL_VARIABLES.GET_BALANCE_ENDPOINT, cookies);
    }
}
