package eventsg.backend.controller;

import eventsg.backend.model.Venue;
import eventsg.backend.service.VenueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/venue")
@RestController
public class VenueController {

    private final VenueServiceImpl venueService;

    @Autowired
    public VenueController(VenueServiceImpl venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public void addVenue(@Valid @NotNull @RequestBody Venue venue) {
        venueService.addVenue(venue);
    }

    @DeleteMapping(path = "{venueId}")
    public void deleteVenueById(@PathVariable("venueId") UUID venueId) {
        venueService.deleteVenueById(venueId);
    }

    @PutMapping(path = "{venueId}")
    public void updateVenueById(@PathVariable UUID venueId, @Valid @NotNull @RequestBody Venue venue) {
        venueService.updateVenueById(venueId, venue);
    }

    @GetMapping(path = "{venueId}")
    public Optional<Venue> getVenueById(@PathVariable UUID venueId) {
        return venueService.getVenueById(venueId);
    }

    @GetMapping
    public List<Venue> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping(path = "{ownerId}")
    public List<Venue> getVenuesBasedOnOwnerId(@PathVariable UUID ownerId) {
        return venueService.getVenuesBasedOnOwnerId(ownerId);
    }

    @GetMapping(path = "{location}")
    public List<Venue> getVenueByLocation(@PathVariable String location) {
        return venueService.getVenueByLocation(location);
    }

    @GetMapping(path = "{area}")
    public List<Venue> getVenueByArea(@PathVariable double area) {
        return venueService.getVenueByArea(area);
    }

    @GetMapping(path = "{budget}")
    public List<Venue> getVenueByBudget(@PathVariable double budget) {
        return venueService.getVenueByBudget(budget);
    }
}
