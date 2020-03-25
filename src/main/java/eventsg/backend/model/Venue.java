package eventsg.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Venue {

    /**
     * unique id assigned to the venue upon creation
     */
    private UUID venueId;

    /**
     * address of this venue
     */
    private String address;

    /**
     * postal code of this venue
     */
    private int postalCode;

    /**
     * userId of the user who owns this venue
     */
    private UUID ownerId;

    /**
     * rental fee of this venue
     */
    private double rentalFee;

    /**
     * area of this venue
     */
    private double area;

    /**
     * a brief description of this venue; a overview
     */
    private String description;

    /**
     * location of the venue; as in the neighborhood;
     */
    private String location;

    public Venue(@JsonProperty("venueId") UUID venueId, @JsonProperty("address") String address, @JsonProperty("postalCode") int postalCode,
                 @JsonProperty("ownerId") UUID ownerId, @JsonProperty("rentalFee") double rentalFee, @JsonProperty("area") double area,
                 @JsonProperty("description") String description, @JsonProperty("location") String location) {
        this.venueId = venueId;
        this.address = address;
        this.postalCode = postalCode;
        this.ownerId = ownerId;
        this.rentalFee = rentalFee;
        this.area = area;
        this.description = description;
        this.location = location;
    }

    /**
     * get area of the instance
     * @return area
     */
    public double getArea() {
        return area;
    }

    /**
     * get rentalFee of the instance
     * @return rentalFee
     */
    public double getRentalFee() {
        return rentalFee;
    }

    /**
     * set rentalFee of the instance
     * @param rentalFee rentalFee
     */
    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    /**
     * get postalCode of the instance
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * get address of the instance
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * get description of the instance
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description of the instance
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get ownerId of the instance
     * @return ownerId
     */
    public UUID getOwnerId() {
        return ownerId;
    }

    /**
     * get venueId of the instance
     * @return venueId
     */
    public UUID getVenueId() {
        return venueId;
    }

    /**
     * get location of the instance
     * @return location
     */
    public String getLocation() {
        return location;
    }
}