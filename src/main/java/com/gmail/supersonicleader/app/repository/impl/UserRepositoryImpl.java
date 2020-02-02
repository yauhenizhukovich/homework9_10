package com.gmail.supersonicleader.app.repository.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.supersonicleader.app.repository.UserRepository;
import com.gmail.supersonicleader.app.repository.model.User;
import com.gmail.supersonicleader.app.repository.model.UserInformation;

public class UserRepositoryImpl extends GeneralRepositoryImpl<User> implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO user (username,password,is_active,age) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isActive());
            statement.setInt(4, user.getAge());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Integer id = generatedKeys.getInt(1);
                    user.setId(id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return user;
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT u.id, u.username, u.password, u.is_active, u.age, ui.telephone, ui.address " +
                                "FROM user u " +
                                "LEFT JOIN user_information ui ON u.id = ui.user_id"
                )
        ) {
            List<User> users = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User person = getUser(rs);
                    users.add(person);
                }
                return users;
            }
        }
    }

    @Override
    public void delete(Connection connection, Serializable id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user WHERE id=?"
                )
        ) {
            statement.setInt(1, (Integer) id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        int id = rs.getInt("id");
        user.setId(id);

        String username = rs.getString("username");
        user.setUsername(username);

        String password = rs.getString("password");
        user.setPassword(password);

        boolean isActive = rs.getBoolean("is_active");
        user.setActive(isActive);

        int age = rs.getInt("age");
        user.setAge(age);

        UserInformation userInformation = new UserInformation();
        String address = rs.getString("address");
        userInformation.setAddress(address);

        String telephone = rs.getString("telephone");
        userInformation.setTelephone(telephone);
        user.setUserInformation(userInformation);
        return user;
    }

}
