package variables;

/**
 * Class to hold URL variables for the application.
 */
public class URLVariables {

    private static final String BASE_URL = "https://luckybandit.club.test-delasport.com/";
    // Endpoints
    public static final String LOGIN_ENDPOINT = BASE_URL + "en/euro/operation/login";
    public static final String GET_BALANCE_ENDPOINT = BASE_URL + "en/index/operation/getMemberBalance";

    /**
     * Gets the base URL of the application.
     *
     * @return the base URL
     */
    public static String getBaseUrl() {
        return BASE_URL;
    }
}
