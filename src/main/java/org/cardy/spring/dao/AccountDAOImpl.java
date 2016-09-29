package org.cardy.spring.dao;

import org.cardy.spring.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{

    private JdbcTemplate jdbcTemplate;

    public AccountDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Account account) {
        if (account.getId() > 0) {
            // update
            String sql = "UPDATE login SET email=?. hash=?, refid=?. "
                    + "telephone=? WHERE contact_id=?";
            jdbcTemplate.update(sql, account.getEmail(), account.getHash());
        }
        else {
            // insert
            String sql = "INSERT INTO login (email, hash, refId, admin)"
                    + "VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, account.getEmail(), account.getHash(),
                    account.getRefId(), account.isAdmin());
        }
    }

    public void delete(int accountId) {
        String sql = "DELETE FROM login WHERE accountId=?";
        jdbcTemplate.update(sql, accountId);
    }

    public List<Account> list() {
        String sql = "SELECT * FROM login";
        List<Account> listAccount = jdbcTemplate.query(sql, new RowMapper<Account>() {

            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                return fillContract(rs);
            }

        });

        return listAccount;
    }

    public Account get(int accountId) {
        String sql = "SELECT * FROM login WHERE accountId=" + accountId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Account>() {
            public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return fillContract(rs);
                }

                return null;
            }
        });
    }

    private Account fillContract(ResultSet rs) throws SQLException, DataAccessException{
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setEmail(rs.getString("email"));
        account.setHash(rs.getString("hash"));
        account.setRefId(rs.getInt("refid"));
        account.setAdmin(rs.getBoolean("admin"));
        return account;
    }
}
