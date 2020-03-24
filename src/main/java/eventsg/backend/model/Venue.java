package eventsg.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Venue {
    private UUID venueId;
    private String address;
    private int postalCode;
    private UUID ownerId;
    private double rentalFee;
    private double area;
    private String description;
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

    public double getArea() {
        return area;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public UUID getVenueId() {
        return venueId;
    }

    public String getLocation() {
        return location;
    }
}