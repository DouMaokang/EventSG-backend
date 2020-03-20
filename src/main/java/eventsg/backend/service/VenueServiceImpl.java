package eventsg.backend.service;

import eventsg.backend.dao.VenueDao;
import eventsg.backend.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VenueServiceImpl {

    private final VenueDao venueDao;

    @Autowired
    public VenueServiceImpl(@Qualifier("postgresVenue") VenueDao venueDao) {
        this.venueDao = venueDao;
    }

    public int addVenue(Venue venue) {
        return venueDao.addVenue(venue);
    }

    public int deleteVenueById(UUID venueId) {
        return venueDao.deleteVenueById(venueId);
    }

    public int updateVenueById(UUID venueId, Venue venue) {
        return venueDao.updateVenueById(venueId, venue);
    }

    public Optional<Venue> getVenueById(UUID venueId) {
        return venueDao.getVenueById(venueId);
    }

    public List<Venue> getAllVenues() {
        return venueDao.getAllVenues();
    }

    public List<Venue> getVenuesBasedOnOwnerId(UUID ownerId) {
        return venueDao.getVenuesBasedOnOwnerId(ownerId);
    }

    public List<Venue> getVenueByLocation(String location) {
        return venueDao.getVenueByLocation(location);
    }

    public List<Venue> getVenueByArea(double area) {
        return venueDao.getVenueByArea(area);
    }

    public List<Venue> getVenueByBudget(double budget) {
        return venueDao.getVenueByBudget(budget);
    }

}
