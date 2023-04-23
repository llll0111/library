package com.group.libraryapp.repository.user;

import com.group.libraryapp.DTO.user.reponse.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJDBCRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNot(long id) {
        String read = "SELECT *" +
                " FROM user" +
                " WHERE id = ?";
        return jdbcTemplate.query(read, (rs, rowNum) -> 0, id).isEmpty();

    }

    public void updateUser(String name, long id) {

        String sql = "UPDATE user" +
                " SET name = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(sql, name, id);

    }

    public boolean isUserNot(String name) {
        String read = "SELECT *" +
                " FROM user" +
                " WHERE name = ?";
        return jdbcTemplate.query(read, (rs, rowNum) -> 0, name).isEmpty();

    }

    public void deleteuser(String name) {

        String sql = "DELETE FROM user" +
                " WHERE name = ?";
        jdbcTemplate.update(sql, name);


    }

    public void saveUser(String name, Integer age) {
        String sql = "INSERT INTO user(name,age)" +
                " VALUES(?,?)";
        jdbcTemplate.update(sql, name, age);

    }

    public List<UserResponse> getUsers() {
        String sql = "SELECT *" +
                " FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

}
















