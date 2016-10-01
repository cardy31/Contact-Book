package org.cardy.spring.dao;

import org.cardy.spring.model.Ref;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RefDAOImpl implements RefDAO {

    private JdbcTemplate jdbcTemplate;

    public RefDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Ref ref) {
        if (ref.getId() > 0) {
            // update
            String sql = "UPDATE referees SET firstname=?. lastname=?, email=?, hash=?, phone=?, "
                    + "level=?, area=?, active=? WHERE user_id=?";
            jdbcTemplate.update(sql, ref.getFirstname(), ref.getLastname(), ref.getEmail(),
                    ref.getHash(), ref.getPhone(), ref.getLevel(), ref.getArea(), ref.isActive(), ref.getId());
        }
        else {
            // insert
            String sql = "INSERT INTO referees (firstname, lastname, email, hash, phone, level, area, active)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, ref.getFirstname(), ref.getLastname(), ref.getEmail(),
                    ref.getHash(), ref.getPhone(), ref.getLevel(), ref.getArea(), ref.isActive());
        }
    }

    public void delete(int refId) {
        String sql = "DELETE FROM referees WHERE user_id=?";
        jdbcTemplate.update(sql, refId);
    }

    public List<Ref> list() {
        String sql = "SELECT * FROM referees";
        List<Ref> listRef = jdbcTemplate.query(sql, new RowMapper<Ref>() {

            public Ref mapRow(ResultSet rs, int rowNum) throws SQLException {
                return fillContract(rs);
            }

        });

        return listRef;
    }

    public Ref get(int refId) {
        String sql = "SELECT * FROM referees WHERE user_id=" + refId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Ref>() {
            public Ref extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return fillContract(rs);
                }

                return null;
            }
        });
    }

    private Ref fillContract(ResultSet rs) throws SQLException, DataAccessException{
        Ref ref = new Ref();
        ref.setId(rs.getInt("user_id"));
        ref.setFirstname(rs.getString("firstname"));
        ref.setLastname(rs.getString("lastname"));
        ref.setEmail(rs.getString("email"));
        ref.setHash(rs.getString("hash"));
        ref.setPhone(rs.getString("phone"));
        ref.setLevel(rs.getInt("level"));
        ref.setArea(rs.getString("area"));
        ref.setActive(rs.getBoolean("active"));
        return ref;
    }
}
