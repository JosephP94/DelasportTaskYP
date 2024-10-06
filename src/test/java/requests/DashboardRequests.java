package requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class for handling HTTP requests(Dashboard)
 */
public class DashboardRequests extends BaseRequest {
    private static final String CONTENT_TYPE = "application/json, text/plain, */*";

    /**
     * Sends a POST request to the specified endpoint to retrieve balance information.
     *
     * @param endPoint the URL endpoint to send the request to
     * @return the response from the server as a String
     * @throws IOException if an I/O error occurs during the request
     */
    public String getBalanceInfoRequest(String endPoint, String cookies) throws IOException {
        HttpURLConnection connection = null;
        try {
            // Initialize the connection
            URL url = new URL(endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setDoOutput(true);

            // Set request headers
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.setRequestProperty("Accept", CONTENT_TYPE);
            connection.setRequestProperty("Cookie", cookies);

            // Send the request body (empty in this case)
            sendEmptyRequestBody(connection);

            // Get and return the response
            return readResponse(connection);
        } catch (IOException e) {
            System.err.println("Error while making request to " + endPoint + ": " + e.getMessage());
            throw e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
