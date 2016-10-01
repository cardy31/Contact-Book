package org.cardy.spring.dao;

import org.cardy.spring.model.Exec;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExecDAOImpl implements ExecDAO{

    private JdbcTemplate jdbcTemplate;

    public ExecDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Exec exec) {
        if (exec.getId() > 0) {
            // update
            String sql = "UPDATE executives SET position=?, email=? WHERE user_id=?";
            jdbcTemplate.update(sql, exec.getPosition(), exec.getEmail(), exec.getId());
        }
        else {
            // insert
            String sql = "INSERT INTO executives (position, email) VALUES (?, ?)";
            jdbcTemplate.update(sql, exec.getPosition(), exec.getEmail());
        }
    }

    public void delete(int execId) {
        String sql = "DELETE FROM executives WHERE user_id=?";
        jdbcTemplate.update(sql, execId);
    }

    public List<Exec> list() {
        String sql = "SELECT * FROM executives";
        List<Exec> listExec = jdbcTemplate.query(sql, new RowMapper<Exec>() {

            public Exec mapRow(ResultSet rs, int rowNum) throws SQLException {
                return fillContract(rs);
            }

        });

        return listExec;
    }

    public Exec get(int execId) {
        String sql = "SELECT * FROM executives WHERE user_id=" + execId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Exec>() {
            public Exec extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return fillContract(rs);
                }

                return null;
            }
        });
    }


    private Exec fillContract(ResultSet rs) throws SQLException, DataAccessException {
        Exec exec = new Exec();
        exec.setId(rs.getInt("user_id"));
        exec.setPosition(rs.getString("position"));
        exec.setEmail(rs.getString("email"));

        return exec;
    }
}
