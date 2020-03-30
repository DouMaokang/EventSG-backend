package eventsg.backend.controller;

import eventsg.backend.model.User;
import eventsg.backend.model.Venue;
import eventsg.backend.service.VenueService;
import eventsg.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RequestMapping("api/venue")
@RestController
public class VenueController {

    private final VenueService venueService;
    private final UserService userService;

    @Autowired
    public VenueController(VenueService venueService, UserService userService) {
        this.venueService = venueService;
        this.userService = userService;
    }

    /**
     * A method answering to Http POST request
     * Adding a new Venue into the database
     * @param venue the venue to be added
     */
    @PostMapping
    public void addVenue(@Valid @NotNull @RequestBody Venue venue) {
        venueService.addVenue(venue);
    }

    /**
     * A method answering to Http DELETE request
     * Deleting a record in the Venue database which has the input venueId
     * @param venueId the record with this venueId would be deleted
     */
    @DeleteMapping(path = "{venueId}")
    public void deleteVenueById(@PathVariable("venueId") UUID venueId) {
        venueService.deleteVenueById(venueId);
    }

    /**
     * A method answering to Http PUT request with path as "{venueId}"
     * Updating the record in the Venue database
     * @param venueId the record in the venue database with this venueId would be updated
     * @param venue the venue info used to update the existing record in the database
     */
    @PutMapping(path = "{venueId}")
    public void updateVenueById(@PathVariable UUID venueId, @Valid @NotNull @RequestBody Venue venue) {
        venueService.updateVenueById(venueId, venue);
    }

    /**
     * A method answering to Http GET request with path as "venueId/{venueId}"
     * @param venueId venueId used to search the database
     * @return returning a Venue object; If no such record exists, the null value would be returned
     */
    @GetMapping(path = "venueId/{venueId}")
    public Map<String, Object> getVenueById(@PathVariable UUID venueId) {
        Venue venue = venueService.getVenueById(venueId);
        return (generateResponse(venue));
    }

    /**
     * A method answering to Http GET request
     * @return Returning all venue records in the database as a list of Venue objects
     */
    @GetMapping
    public List<Map<String, Object>> getAllVenues() {
        List<Venue> venues =  venueService.getAllVenues();
        return generateResponseList(venues);
    }

    /**
     * A method answering to Http GET request with path as "ownerId/{ownerId}"
     * @param ownerId the ownerId used as the searching criteria
     * @return a list of Venue objects with the above mentioned ownerId
     */
    @GetMapping(path = "ownerId/{ownerId}")
    public List<Map<String, Object>> getVenuesByOwnerId(@PathVariable UUID ownerId) {
        List<Venue> venues = venueService.getVenuesByOwnerId(ownerId);
        return generateResponseList(venues);
    }

    /**
     * A method answering to the Http GET request with path as "venueName/{venueName}"
     * @param venueName the venue name used as the searching criteria
     * @return Returning all selected venues as a list of Venue objects
     */
    @GetMapping(path = "venueName/{venueName}")
    public List<Map<String, Object>> getVenueByName(@PathVariable String venueName) {
        List<Venue> venues = venueService.getVenueByName(venueName);
        return generateResponseList(venues);
    }


    /**
     * A method answering to the Http GET request with path as "area/{area}"
     * @param area the area used as the searching criteria
     * @return Returning all selected venues as a list of Venue objects
     */
    @GetMapping(path = "area/{area}")
    public List<Map<String, Object>> getVenueByArea(@PathVariable double area) {
        List<Venue> venues = venueService.getVenueByArea(area);
        return generateResponseList(venues);
    }

    /**
     * A method answering to the Http GET request with path as "budget/{budget}"
     * @param budget the budget used as the searching criteria on the price of a venue
     * @return Returning all selected venues as a list of Venue objects
     */
    @GetMapping(path = "budget/{budget}")
    public List<Map<String, Object>> getVenueByBudget(@PathVariable double budget) {
        List<Venue> venues = venueService.getVenueByBudget(budget);
        return generateResponseList(venues);
    }


    private Map<String, Object> generateResponse(Venue venue) {
        User owner;
        try {
            owner = userService.getUserById(venue.getOwnerId()).orElse(null);
        } catch (Exception e) {
            owner = null;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("venue", venue);
        response.put("owner", owner);
        return response;
    }

    private List<Map<String, Object>> generateResponseList (List<Venue> venueList) {
        List<Map<String, Object>> responseList = new ArrayList<>();
        for (Venue venue : venueList) {
            responseList.add(generateResponse(venue));
        }
        return responseList;
    }

}