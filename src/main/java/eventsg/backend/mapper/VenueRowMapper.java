
package eventsg.backend.mapper;

import eventsg.backend.model.Venue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class VenueRowMapper implements RowMapper<Venue> {
    /**
     * a method searching through the postgres database, selecting all rows fitting the criteria and construct a
     * Venue object with the row values
     * @param resultSet all the resulting rows and selected columns fitting the criteria
     * @param rowNum number of rows found
     * @return a Venue object
     * @throws SQLException not found
     */
    @Override
    public Venue mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UUID venueId = UUID.fromString(resultSet.getString("venueId"));
        String address = resultSet.getString("address");
        int postalCode = Integer.parseInt(resultSet.getString("postalCode"));
        UUID ownerId = UUID.fromString(resultSet.getString("ownerId"));
        double rentalFee = Double.parseDouble(resultSet.getString("rentalFee"));
        double area = Double.parseDouble(resultSet.getString("area"));
        String description = resultSet.getString("description");
        String venueName = resultSet.getString("venueName");
        String image = resultSet.getString("image");
        return new Venue(venueId, address, postalCode, ownerId, rentalFee, area, description, venueName, image);
    }
}