package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class for handling HTTP requests related to the Dashboard API.
 */
public class DashboardRequests {
        private static final String REQUEST_METHOD = "POST";
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
                        throw e;  // Re-throw the exception for further handling
                } finally {
                        if (connection != null) {
                                connection.disconnect();  // Ensure connection is closed
                        }
                }
        }

        /**
         * Sends an empty request body.
         *
         * @param connection the HttpURLConnection object
         * @throws IOException if an I/O error occurs
         */
        private void sendEmptyRequestBody(HttpURLConnection connection) throws IOException {
                try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = "".getBytes("utf-8");
                        os.write(input, 0, input.length);
                }
        }

        /**
         * Reads the response from the server.
         *
         * @param connection the HttpURLConnection object
         * @return the server response as a String
         * @throws IOException if an I/O error occurs
         */
        private String readResponse(HttpURLConnection connection) throws IOException {
                StringBuilder content = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                                content.append(inputLine);
                        }
                }
                System.out.println("The response of getBalanceInfoRequest is: " + content.toString());
                return content.toString();
        }
}
