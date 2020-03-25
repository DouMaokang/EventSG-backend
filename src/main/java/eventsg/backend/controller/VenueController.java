package eventsg.backend.controller;

import eventsg.backend.model.Venue;
import eventsg.backend.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/venue")
@RestController
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
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
    public Optional<Venue> getVenueById(@PathVariable UUID venueId) {
        return venueService.getVenueById(venueId);
    }

    /**
     * A method answering to Http GET request
     * @return Returning all venue records in the database as a list of Venue objects
     */
    @GetMapping
    public List<Venue> getAllVenues() {
        return venueService.getAllVenues();
    }

    /**
     * A method answering to Http GET request with path as "ownerId/{ownerId}"
     * @param ownerId the ownerId used as the searching criteria
     * @return a list of Venue objects with the above mentioned ownerId
     */
    @GetMapping(path = "ownerId/{ownerId}")
    public List<Venue> getVenuesByOwnerId(@PathVariable UUID ownerId) {
        return venueService.getVenuesByOwnerId(ownerId);
    }

    /**
     * A method answering to the Http GET request with path as "location/{location}"
     * @param location the location used as the searching criteria
     * @return Returning all selected venues as a list of Venue objects
     */
    @GetMapping(path = "location/{location}")
    public List<Venue> getVenueByLocation(@PathVariable String location) {
        return venueService.getVenueByLocation(location);
    }

    /**
     * A method answering to the Http GET request with path as "area/{area}"
     * @param area the area used as the searching criteria
     * @return Returning all selected venues as a list of Venue objects
     */
    @GetMapping(path = "area/{area}")
    public List<Venue> getVenueByArea(@PathVariable double area) {
        return venueService.getVenueByArea(area);
    }

    /**
     * A method answering to the Http GET request with path as "budget/{budget}"
     * @param budget the budget used as the searching criteria on the price of a venue
     * @return Returning all selected venues as a list of Venue objects
     */
    @GetMapping(path = "budget/{budget}")
    public List<Venue> getVenueByBudget(@PathVariable double budget) {
        return venueService.getVenueByBudget(budget);
    }


}