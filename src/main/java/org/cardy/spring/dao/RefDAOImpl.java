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

public class RefDAOImpl implements RefDAO{

    private JdbcTemplate jdbcTemplate;

    public RefDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Ref ref) {
        if (ref.getId() > 0) {
            // update
            String sql = "UPDATE contact SET firstname=?. lastname=?, email=?, phone=?, "
                    + "level=?, area=?, active=? WHERE contact_id=?";
            jdbcTemplate.update(sql, ref.getFirstname(), ref.getLastname(), ref.getEmail(),
                    ref.getPhone(), ref.getLevel(), ref.getArea(), ref.isActive(), ref.getId());
        }
        else {
            // insert
            String sql = "INSERT INTO contact (firstname, lastname, email, phone, level, area, active)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, ref.getFirstname(), ref.getLastname(), ref.getEmail(),
                    ref.getPhone(), ref.getLevel(), ref.getArea(), ref.isActive());
        }
    }

    public void delete(int refId) {
        String sql = "DELETE FROM ref WHERE id=?";
        jdbcTemplate.update(sql, refId);
    }

    public List<Ref> list() {
        String sql = "SELECT * FROM ref";
        List<Ref> listRef = jdbcTemplate.query(sql, new RowMapper<Ref>() {

            public Ref mapRow(ResultSet rs, int rowNum) throws SQLException {
                return fillContract(rs);
            }

        });

        return listRef;
    }

    public Ref get(int refId) {
        String sql = "SELECT * FROM ref WHERE id=" + refId;
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
        ref.setId(rs.getInt("id"));
        ref.setFirstname(rs.getString("firstname"));
        ref.setLastname(rs.getString("lastname"));
        ref.setEmail(rs.getString("email"));
        ref.setPhone(rs.getString("phone"));
        ref.setLevel(rs.getInt("level"));
        ref.setArea(rs.getString("area"));
        ref.setActive(rs.getBoolean("active"));
        return ref;
    }
}
