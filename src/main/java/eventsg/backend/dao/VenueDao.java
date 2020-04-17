
package eventsg.backend.dao;

import eventsg.backend.model.Venue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VenueDao {

    /**
     * insert a new venue record into the postgres database with the sql statement
     *
     * @param venue Venue object with the necessary values to be inserted
     * @return 1 if insertion is successful; else 0
     */
    int addVenue(Venue venue);

    /**
     * delete a venue record from the postgres database
     *
     * @param venueId venueId
     * @return 1 if deletion is successful; else 0
     */
    int deleteVenueById(UUID venueId);

    /**
     * update a record in the database
     *
     * @param venueId venueId
     * @param venue   Venue object with the necessary values used to perform update
     * @return 1 if update is successful; else 0
     */
    int updateVenueById(UUID venueId, Venue venue);

    /**
     * get the venue record with the venueId
     *
     * @param venueId venueId
     * @return the venue if found, else return null
     */
    Venue getVenueById(UUID venueId);

    /**
     * get all the venues in the database
     *
     * @return a list of Venue objects
     */
    List<Venue> getAllVenues();

    /**
     * return all the venues belonging to a certain owner
     *
     * @param ownerId onwerId
     * @return a list of Venue objects
     */
    List<Venue> getVenuesByOwnerId(UUID ownerId);

    /**
     * get all venues with similar name
     * @param venueName venue name
     * @return list of Venue objects
     */
    List<Venue> getVenueByName(String venueName);

    /**
     * get all venues that have area close to the input area
     *
     * @param area the demanded area
     * @return a list of Venue objects
     */
    List<Venue> getVenueByArea(double area);

    /**
     * get all venues with prices around the specified budget
     *
     * @param budget budget
     * @return a list of Venue objects
     */
    List<Venue> getVenueByBudget(double budget);

    /**
     * Get the venue of an event by eventID
     * @param eventId UUID of event.
     * @return the venue.
     */
    Venue getVenueByEventId(UUID eventId);

//    int addEventVenue(UUID eventId, UUID venueId);
//
//    int changeEventVenue(UUID eventId, UUID newVenueID);
}