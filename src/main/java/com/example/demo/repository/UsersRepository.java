package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository
public class UsersRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class UsersRowMapper implements RowMapper < Users > {
        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
            Users Users = new Users();
            Users.setId(rs.getInt("ID"));
            Users.setUserName(rs.getString("USERNAME"));
            Users.setPassword(rs.getString("PASSWORD"));
            Users.setStatus(rs.getInt("STATUS"));
            return Users;
        }
    }

    public List < Users > findAll() {
        return jdbcTemplate.query("SELECT * FROM USERS", new UsersRowMapper());
    }

    public Optional < Users > findById(Integer id) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE ID=?",
            new BeanPropertyRowMapper < Users > (Users.class), id));
    }

    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM USERS WHERE ID=?", new Object[] {
            id
        });
    }

    public int insert(Users Users) {
        return jdbcTemplate.update("INSERT INTO USERS (USERNAME, PASSWORD, STATUS) VALUES( ?, ?, ?)",
            new Object[] {
                Users.getUserName(), Users.getPassword(), Users.getStatus()
            });
    }

    public int update(Users Users) {
        return jdbcTemplate.update("UPDATE USERS SET USERNAME = ?, STATUS = ? WHERE ID = ?",
            new Object[] {
                Users.getUserName(), Users.getStatus(), Users.getId()
            });
    }

    //Code having issue
     public List<Users> searchUserError(String userName, String password) {
        return jdbcTemplate.query("SELECT * FROM USERS WHERE USERNAME = '"+userName+"' AND PASSWORD = '"+password+"'", new UsersRowMapper());
    }

    //Correct code
     public List<Users> searchUser(String userName, String password) {
        return jdbcTemplate.query("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?", new PreparedStatementSetter() {
              public void setValues(PreparedStatement preparedStatement) throws
                SQLException {
                  preparedStatement.setString(1, userName);
                  preparedStatement.setString(2, password);
              }
            }, new UsersRowMapper());
    }
}
