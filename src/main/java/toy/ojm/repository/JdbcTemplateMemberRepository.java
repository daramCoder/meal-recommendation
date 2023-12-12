package toy.ojm.repository;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import toy.ojm.entity.RestaurantEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class JdbcTemplateMemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository() {
        DataSource dataSource = null;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RestaurantEntity save(RestaurantEntity RestaurantEntity) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("RestaurantEntity").usingGeneratedKeyColumns("restaurantName");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("restaurantName", RestaurantEntity.getRestaurantName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        RestaurantEntity.setBpLcNm(key.longValue());
        return RestaurantEntity;
    }

    public int[] batchInsert(List<RestaurantEntity> restaurants) {
        return jdbcTemplate.batchUpdate(
            "INSERT INTO restaurant(dtlStateNm, siteWhLaDdr) VALUES(?, ?)",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    RestaurantEntity restaurantEntity = restaurants.get(i);
                    ps.setString(1, restaurantEntity.getBusinessStatus());
                    ps.setString(2, restaurantEntity.getStreetNumberAddress());
                    ps.setString(3, restaurantEntity.getStreetNameAddress());
                    ps.setString(4, restaurantEntity.getRestaurantName());
                    ps.setString(5, restaurantEntity.getCategory());
                    ps.setString(6, restaurantEntity.getLongitude());
                    ps.setString(7, restaurantEntity.getLatitude());
                }

                @Override
                public int getBatchSize() {
                    return restaurants.size();
                }
            });
    }
}
