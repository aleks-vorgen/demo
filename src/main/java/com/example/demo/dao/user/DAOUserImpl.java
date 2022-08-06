package com.example.demo.dao.user;

import com.example.demo.dao.PostgresFactory;
import com.example.demo.model.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUserImpl implements DAOUser {
    private static final Logger LOGGER = Logger.getLogger(DAOUserImpl.class);
    private static final String GET_USER_LIST =
            "SELECT *" +
            " FROM lab3_ko_users";

    private static final String GET_USER =
            "SELECT *" +
            " FROM lab3_ko_users" +
            " WHERE id = ?";

    private static final String INSERT_USER =
            "INSERT INTO lab3_ko_users" +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER =
            "UPDATE lab3_ko_users" +
            " SET id = ?, username = ?, email = ?," +
            " password = ?, permissions = ?" +
            " WHERE id = ?";

    private static final String DELETE_USER =
            "DELETE FROM lab3_ko_users" +
            " WHERE id = ?";
    
    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_USER_LIST)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(parseUser(rs));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get user list.\n Message: " + e.getLocalizedMessage());
        }
        return userList;
    }

    @Override
    public User getUser(int id) {
        User user = null;
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_USER)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = parseUser(rs);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get user.\n Message: " + e.getLocalizedMessage());
        }
        return user;
    }

    @Override
    public boolean insertUser(User user) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(INSERT_USER)) {

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.isPermissions());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not insert user.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(UPDATE_USER)) {

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.isPermissions());
            ps.setInt(6, user.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not update user.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(DELETE_USER)) {
            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not delete user.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    private User parseUser(ResultSet rs) {
        User user = null;
        try {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            boolean permissions = rs.getBoolean("permissions");
            user = new User(id, username, email, password, permissions);
        } catch (SQLException e) {
            LOGGER.error("Could not parse user.\n Message: " + e.getLocalizedMessage());
        }

        return user;
    }
}
