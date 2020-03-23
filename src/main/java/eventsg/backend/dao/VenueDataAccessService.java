package eventsg.backend.dao;

import eventsg.backend.mapper.VenueRowMapper;
import eventsg.backend.model.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresVenue")
public class VenueDataAccessService implements VenueDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VenueDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addVenue(UUID venueId, Venue venue) { //tested
        String sql = "" +
                "INSERT INTO venue (" +
                "venueId, " +
                "address, " +
                "postalCode, " +
                "ownerId, " +
                "rentalFee, " +
                "area, " +
                "description, " +
                "location )" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                venueId,
                venue.getAddress(),
                venue.getPostalCode(),
                venue.getOwnerId(),
                venue.getRentalFee(),
                venue.getArea(),
                venue.getDescription(),
                venue.getLocation()
        );
    }

    @Override
    public int deleteVenueById(UUID venueId) { // tested
        final String sql = "" + "DELETE FROM venue WHERE venueId = ?";
        return jdbcTemplate.update(sql, venueId);
    }

    @Override
    public int updateVenueById(UUID venueId, Venue venue) { // tested
        String sql = "" +
                "UPDATE venue " +
                "SET " +
                "venueId = ?, " +
                "address = ?, " +
                "postalCode = ?, " +
                "ownerId = ?, " +
                "rentalFee = ?, " +
                "area = ?, " +
                "description = ?, " +
                "location = ?" +
                "WHERE venueId = ?";
        return jdbcTemplate.update(
                sql,
                venueId,
                venue.getAddress(),
                venue.getPostalCode(),
                venue.getOwnerId(),
                venue.getRentalFee(),
                venue.getArea(),
                venue.getDescription(),
                venue.getLocation(),
                venueId
        );
    }

    @Override
    public Optional<Venue> getVenueById(UUID selectedVenueId) { // tested
        final String sql = "SELECT * FROM venue WHERE venueId = ?";
        Venue venue = jdbcTemplate.queryForObject(
                sql,
                new Object[]{selectedVenueId},
                new VenueRowMapper());
        return Optional.ofNullable(venue);
    }

    @Override
    public List<Venue> getAllVenues() { // tested
        final String sql = "SELECT * FROM venue";
        List<Venue> venues = jdbcTemplate.query(sql, new VenueRowMapper());
        return venues;
    }

    @Override
    public List<Venue> getVenuesBasedOnOwnerId(UUID selectedOwnerId) { // tested
        final String sql = "SELECT * FROM venue WHERE ownerId = ?";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{selectedOwnerId},
                new VenueRowMapper());
        return venues;

    }

    @Override
    public List<Venue> getVenueByLocation(String selectedLocation) {  // tested
        final String sql = "SELECT * FROM venue WHERE location = ?";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{selectedLocation},
                new VenueRowMapper());
        return venues;
    }

    // todo rowMapper?
    @Override
    public List<Venue> getVenueByArea(double selectedArea) { // tested
        final String sql = "SELECT * FROM venue WHERE ABS(area - ?) <= 10";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{selectedArea},
                new VenueRowMapper());
        return venues;
    }

    @Override
    public List<Venue> getVenueByBudget(double budget) { //
        final String sql = "SELECT * FROM venue WHERE ABS(rentalFee - ?) <= 50";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{budget},
                new VenueRowMapper());
        return venues;
    }
}
