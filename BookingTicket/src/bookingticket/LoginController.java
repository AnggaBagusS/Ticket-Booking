package bookingticket;

import model.user;
import static com.mysql.cj.util.SaslPrep.prepare;
import db.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javax.management.remote.JMXConnectorFactory.connect;

public class LoginController {

    @FXML
    private Hyperlink login_createAccount;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private TextField login_username;
    
    @FXML
    private PasswordField login_password;

    @FXML
    void goToSignin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) login_createAccount.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    public void login(ActionEvent event) {
        String email = login_username.getText();
        String password = login_password.getText();

        Alert alert;

        if (email.isEmpty() || password.isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return; // Tambahkan return untuk menghentikan eksekusi lebih lanjut jika kosong
        }

        try {
            // Call Authentication to get the customer by email
            user customer = Authentication.getCustomerByEmail(email);
            int custId = customer.getId();
            String emailNew = customer.getEmail();

            if (customer != null && password.equals(customer.getPassword()) && email.equals(customer.getEmail())) {
                // Check the retrieved Customer's role
                if ("customer".equals(customer.getRole())) {
                    // Redirect to the customer dashboard or appropriate page
                    redirectToHomepage(custId);
                    System.out.println("Logged in " + emailNew);
                } else if ("admin".equals(customer.getRole())) {
                    redirectToAdminPage();
                    System.out.println("Logged in as admin: " + emailNew);
                } else {
                    System.out.println("Not Logged in");
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Email/Password");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the SQL exception
        }
    }


    private void redirectToHomepage(int custId) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardUser.fxml"));
            Parent root = loader.load();
            DashboardUserController homeController = loader.getController();
            homeController.initialize(custId);
            System.out.println("CustId: " + custId);

            // Set up the stage
            Stage stage = (Stage) login_username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the IO exception
        }
    }

    private void redirectToAdminPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardAdmin.fxml"));
            Parent root = loader.load();

            // Set up the stage
            Stage stage = (Stage) login_username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the IO exception
        }
    }
        
}
