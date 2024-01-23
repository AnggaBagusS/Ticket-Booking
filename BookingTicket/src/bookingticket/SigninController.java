package bookingticket;

import db.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SigninController {

    @FXML
    private Button signin_Btnsignin;

    @FXML
    private Hyperlink signin_alreadyHaveAnAccount;

    @FXML
    private TextField signin_email;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) signin_alreadyHaveAnAccount.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void signin(ActionEvent event) {
        Connection conn = DBHelper.getConnection();
        String query = "INSERT INTO pengguna (username, password, email, role) VALUES (?, ?, ?, ?)";
        String role = "customer";
        
        Alert alert;

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, signin_username.getText());
            ps.setString(2, signin_password.getText());
            ps.setString(3, signin_email.getText());
            ps.setString(4, role);

            int result = ps.executeUpdate();
            if (result > 0) {
                if (signin_username.getText().isEmpty() || signin_password.getText().isEmpty() || signin_email.getText().isEmpty()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();
                }else{
                    showAlert(AlertType.INFORMATION, "Registration Successful", "User registered successfully!");
                    signin_username.setText("");
                    signin_password.setText("");
                    signin_email.setText("");
                }
            } else {
                showAlert(AlertType.ERROR, "Registration Failed", "Unable to register user. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Error while accessing the database. Please try again.");
        }
    }
    
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
