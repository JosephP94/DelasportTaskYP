package models;

/**
 * Represents a data item in the dashboard response.
 */
public class DataItem {

    private String action;
    private Info info;

    /**
     * Gets the action associated with the data item.
     *
     * @return the action as a String
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action associated with the data item.
     *
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the info associated with the data item.
     *
     * @return the Info object
     */
    public Info getInfo() {
        return info;
    }

    /**
     * Sets the info associated with the data item.
     *
     * @param info the Info object to set
     */
    public void setInfo(Info info) {
        this.info = info;
    }
}
