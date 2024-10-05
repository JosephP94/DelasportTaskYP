package models;

import java.util.List;

/**
 * Represents information related to a specific credit type in the dashboard response.
 */
public class Info {

    private String creditType;
    private String key;
    private String amount;
    private String raw_amount;
    private int order;
    private List<Object> offers; // Assumed to be a list of offers (empty in your JSON)
    private int has_freespins_offer; // Not present for all objects
    private int has_monetary_offer;  // Not present for all objects

    /**
     * Gets the credit type associated with the info.
     *
     * @return the credit type as a String
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * Sets the credit type associated with the info.
     *
     * @param creditType the credit type to set
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    /**
     * Gets the key associated with the info.
     *
     * @return the key as a String
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key associated with the info.
     *
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the amount associated with the info.
     *
     * @return the amount as a String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount associated with the info.
     *
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the raw amount associated with the info.
     *
     * @return the raw amount as a String
     */
    public String getRawAmount() {
        return raw_amount;
    }

    /**
     * Sets the raw amount associated with the info.
     *
     * @param rawAmount the raw amount to set
     */
    public void setRawAmount(String rawAmount) {
        this.raw_amount = rawAmount;
    }

    /**
     * Gets the order associated with the info.
     *
     * @return the order as an int
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order associated with the info.
     *
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the offers associated with the info.
     *
     * @return a List of offers
     */
    public List<Object> getOffers() {
        return offers;
    }

    /**
     * Sets the offers associated with the info.
     *
     * @param offers the List of offers to set
     */
    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    /**
     * Gets the number of free spins offers associated with the info.
     *
     * @return the number of free spins offers as an int
     */
    public int getHasFreespinsOffer() {
        return has_freespins_offer;
    }

    /**
     * Sets the number of free spins offers associated with the info.
     *
     * @param hasFreespinsOffer the number of free spins offers to set
     */
    public void setHasFreespinsOffer(int hasFreespinsOffer) {
        this.has_freespins_offer = hasFreespinsOffer;
    }

    /**
     * Gets the number of monetary offers associated with the info.
     *
     * @return the number of monetary offers as an int
     */
    public int getHasMonetaryOffer() {
        return has_monetary_offer;
    }

    /**
     * Sets the number of monetary offers associated with the info.
     *
     * @param hasMonetaryOffer the number of monetary offers to set
     */
    public void setHasMonetaryOffer(int hasMonetaryOffer) {
        this.has_monetary_offer = hasMonetaryOffer;
    }
}
