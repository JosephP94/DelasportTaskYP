package helpers;

import requests.LoginRequests;
import variables.URLVariables;

import java.io.IOException;

public class Login {

    private static final URLVariables URL_VARIABLES = new URLVariables();
    private static final LoginRequests LOGIN_REQUESTS = new LoginRequests();

    /**
     * Authenticates a user with the given username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the response from the login request in JSON format
     * @throws IOException if there is an error during the request
     */
    public static String login(String username, String password) throws IOException, InterruptedException {
        return LOGIN_REQUESTS.platformLoginRequest(username, password, URL_VARIABLES.LOGIN_ENDPOINT);
    }
}
