
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

    List<Venue> getVenuesByOwnerId(UUID ownerId);

    List<Venue> getVenueByName(String venueName);

    List<Venue> getVenueByArea(double area);

    List<Venue> getVenueByBudget(double budget);

}