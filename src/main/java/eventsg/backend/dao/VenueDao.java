
package eventsg.backend.dao;

import eventsg.backend.model.Venue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VenueDao {

    int addVenue(UUID venueId, Venue venue);

    default int addVenue(Venue venue) {
        UUID venueId = UUID.randomUUID();
        return addVenue(venueId, venue);
    }

    int deleteVenueById(UUID venueId);

    int updateVenueById(UUID venueId, Venue venue);

    Optional<Venue> getVenueById(UUID venueId);

    List<Venue> getAllVenues();

    List<Venue> getVenuesBasedOnOwnerId(UUID ownerId);

    List<Venue> getVenueByLocation(String location);

    List<Venue> getVenueByArea(double area);

    List<Venue> getVenueByBudget(double budget);

}