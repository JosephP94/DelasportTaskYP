package models;

import java.util.List;

/**
 * Represents the info object from getMemberBalance()
 */
public class Info {

    private String creditType;
    private String key;
    private String amount;
    private String raw_amount;
    private int order;
    private List<Object> offers;
    private int has_freespins_offer;
    private int has_monetary_offer;

    /**
     * Gets the credit type from the response
     *
     * @return the credit type as a readable text
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * Sets the credit type from the response.
     *
     * @param creditType the credit type to set
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    /**
     * Gets the key from the info object.
     *
     * @return the key as a String
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key associated with the info object.
     *
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the amount associated with the info object.
     *
     * @return the amount as a String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the amount associated with the info object.
     *
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the raw amount associated with the info object.
     *
     * @return the raw amount as a readable text
     */
    public String getRawAmount() {
        return raw_amount;
    }

    /**
     * Sets the raw amount associated with the info object.
     *
     * @param rawAmount the raw amount to set
     */
    public void setRawAmount(String rawAmount) {
        this.raw_amount = rawAmount;
    }

    /**
     * Gets the order associated with the info object.
     *
     * @return the order as an int
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the order associated with the info object.
     *
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets the offers associated with the info object.
     *
     * @return a List of offers
     */
    public List<Object> getOffers() {
        return offers;
    }

    /**
     * Sets the offers associated with the info object.
     *
     * @param offers the List of offers to set
     */
    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    /**
     * Gets the number of free spins offers associated with the info object.
     *
     * @return the number of free spins offers as an int
     */
    public int getHasFreespinsOffer() {
        return has_freespins_offer;
    }

    /**
     * Sets the number of free spins offers associated with the info object.
     *
     * @param hasFreespinsOffer the number of free spins offers to set
     */
    public void setHasFreespinsOffer(int hasFreespinsOffer) {
        this.has_freespins_offer = hasFreespinsOffer;
    }

    /**
     * Gets the number of monetary offers associated with the info object.
     *
     * @return the number of monetary offers as an int
     */
    public int getHasMonetaryOffer() {
        return has_monetary_offer;
    }

    /**
     * Sets the number of monetary offers associated with the info object.
     *
     * @param hasMonetaryOffer the number of monetary offers to set
     */
    public void setHasMonetaryOffer(int hasMonetaryOffer) {
        this.has_monetary_offer = hasMonetaryOffer;
    }
}
