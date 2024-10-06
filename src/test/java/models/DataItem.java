package models;

/**
 * Represents a data item in the dashboard response.
 */
public class DataItem {

    private String action;
    private Info info;
    // Getters and Setters
    /**
     * Gets the action from the response.
     *
     * @return the action as a String
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action property.
     *
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the info from the response.
     *
     * @return the Info object
     */
    public Info getInfo() {
        return info;
    }

    /**
     * Sets the info property.
     *
     * @param info the Info object to set
     */
    public void setInfo(Info info) {
        this.info = info;
    }
}
