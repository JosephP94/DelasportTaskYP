package models;

import java.util.Map;

/**
 * Represents the response from the dashboard endpoint getMemberBalance().
 */
public class DashboardResponse {

    private String status;
    private Map<String, DataItem> data;

    // Getters and Setters

    /**
     * Gets the status of the dashboard response property
     *
     * @return the status as a readable text
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the dashboard response.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the data items of the dashboard response.
     *
     * @return a map of data items with their keys
     */
    public Map<String, DataItem> getData() {
        return data;
    }

    /**
     * Sets the data items of the dashboard response.
     *
     * @param data a map of data items to set
     */
    public void setData(Map<String, DataItem> data) {
        this.data = data;
    }
}
