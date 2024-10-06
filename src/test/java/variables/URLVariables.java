package variables;

import java.util.ResourceBundle;

/**
 * Class to hold URL variables for the application.
 */
public class URLVariables {

    private static final String BASE_URL = ResourceBundle.getBundle("dev").getString("baseURL");
    // Endpoints
    public static final String LOGIN_ENDPOINT = BASE_URL + ResourceBundle.getBundle("dev").getString("loginEndpoint");
    public static final String GET_BALANCE_ENDPOINT = BASE_URL + ResourceBundle.getBundle("dev").getString("memberBalanceEndpoint");

    /**
     * Gets the base URL of the application.
     *
     * @return the base URL
     */
    public static String getBaseUrl() {
        return BASE_URL;
    }
}
