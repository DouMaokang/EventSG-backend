
package eventsg.backend.dao;

import eventsg.backend.model.Venue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VenueDao {

    int addVenue(Venue venue);

    int deleteVenueById(UUID venueId);

    int updateVenueById(UUID venueId, Venue venue);

    Optional<Venue> getVenueById(UUID venueId);

    List<Venue> getAllVenues();

    List<Venue> getVenuesByOwnerId(UUID ownerId);

    List<Venue> getVenueByName(String venueName);

    List<Venue> getVenueByArea(double area);

    List<Venue> getVenueByBudget(double budget);

    Venue getVenueByEventId(UUID eventId);

//    int addEventVenue(UUID eventId, UUID venueId);
//
//    int changeEventVenue(UUID eventId, UUID newVenueID);
}