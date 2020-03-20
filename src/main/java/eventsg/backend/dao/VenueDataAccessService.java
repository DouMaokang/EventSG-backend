package eventsg.backend.dao;

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
    public int addVenue(UUID venueId, Venue venue) {
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
    public int deleteVenueById(UUID venueId) {
        final String sql = "" + "DELETE FROM venue WHERE id = ?";
        return jdbcTemplate.update(sql, venueId);
    }

    @Override
    public int updateVenueById(UUID venueId, Venue venue) {
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
    public Optional<Venue> getVenueById(UUID selectedVenueId) {
        final String sql = "SELECT * FROM venue WHERE venueId = ?";
        Venue venue = jdbcTemplate.queryForObject(
                sql,
                new Object[]{selectedVenueId},
                (resultSet, i) -> {
                    UUID venueId = UUID.fromString(resultSet.getString("venueId"));
                    String address = resultSet.getString("address");
                    int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
                    UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
                    double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
                    double area = Double.parseDouble(resultSet.getString("area"));
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, location);
                });
        return Optional.ofNullable(venue);
    }

    @Override
    public List<Venue> getAllVenues() {
        final String sql = "SELECT * FROM venue";
        List<Venue> venues = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID venueId = UUID.fromString(resultSet.getString("venueId"));
            String address = resultSet.getString("address");
            int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
            UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
            double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
            double area = Double.parseDouble(resultSet.getString("area"));
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, location);
        });
        return venues;
    }

    @Override
    public List<Venue> getVenuesBasedOnOwnerId(UUID selectedOwnerId) {
        final String sql = "SELECT * FROM venue WHERE ownerId = ?";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{selectedOwnerId},
                (resultSet, i) -> {
                    UUID venueId = UUID.fromString(resultSet.getString("venueId"));
                    String address = resultSet.getString("address");
                    int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
                    UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
                    double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
                    double area = Double.parseDouble(resultSet.getString("area"));
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, location);
                });
        return venues;

    }

    @Override
    public List<Venue> getVenueByLocation(String selectedLocation) {
        final String sql = "SELECT * FROM venue WHERE location = ?";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{selectedLocation},
                (resultSet, i) -> {
                    UUID venueId = UUID.fromString(resultSet.getString("venueId"));
                    String address = resultSet.getString("address");
                    int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
                    UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
                    double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
                    double area = Double.parseDouble(resultSet.getString("area"));
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, location);
                });
        return venues;
    }

    @Override
    public List<Venue> getVenueByArea(double selectedArea) {
        final String sql = "SELECT * FROM venue WHERE area < ? + 10 AND area > ? - 10";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{selectedArea},
                (resultSet, i) -> {
                    UUID venueId = UUID.fromString(resultSet.getString("venueId"));
                    String address = resultSet.getString("address");
                    int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
                    UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
                    double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
                    double area = Double.parseDouble(resultSet.getString("area"));
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, location);
                });
        return venues;
    }

    @Override
    public List<Venue> getVenueByBudget(double budget) {
        final String sql = "SELECT * FROM venue WHERE rentalFee > ? - 50 AND rentalFee < ? + 50";
        List<Venue> venues = jdbcTemplate.query(
                sql,
                new Object[]{budget},
                (resultSet, i) -> {
                    UUID venueId = UUID.fromString(resultSet.getString("venueId"));
                    String address = resultSet.getString("address");
                    int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
                    UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
                    double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
                    double area = Double.parseDouble(resultSet.getString("area"));
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, location);
                });
        return venues;
    }
}
