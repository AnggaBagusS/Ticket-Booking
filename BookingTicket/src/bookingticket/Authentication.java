/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingticket;

import model.user;
import db.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author owner
 */
public class Authentication {
    public static user getCustomerByEmail(String email_) throws SQLException {
        String sql = "SELECT * FROM pengguna WHERE email = ?";
        user customer = null;

        try (Connection connection = DBHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email_);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetch customer details including password from the result set
                    int userId_ = resultSet.getInt("userId");
                    String role_ = resultSet.getString("role");
                    String password_ = resultSet.getString("password");
                    String username_ = resultSet.getString("username");
                    
                    

                    // Create a Customer object including the password
                    customer = new user(userId_, username_, email_, password_, role_);
                }
            }
        }

        return customer;
    }
}
