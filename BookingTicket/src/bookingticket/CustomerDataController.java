package bookingticket;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerDataController implements Initializable{

    @FXML
    private Button confirm_backBtn;

    @FXML
    private PasswordField confirm_email;

    @FXML
    private Button confirm_nextBtn;

    @FXML
    private PasswordField confirm_password;

    @FXML
    private TextField confirm_username;

    @FXML
    private AnchorPane login_form;
    
    @FXML
    void backToDashbardUser(MouseEvent event) {

    }

    @FXML
    void backToDashboardUser(ActionEvent event) {
//        Stage stage = (Stage) confirm_backBtn.getScene().getWindow();
//        stage.close();
//
//        // Jika Anda ingin beralih ke halaman login, gunakan kode berikut:
//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardUser.fxml"));
//             Parent root = loader.load();
//        
//             Stage loginStage = new Stage();
//             Scene scene = new Scene(root);
//        
//             loginStage.setScene(scene);
//             loginStage.show();
//        
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
    }

    @FXML
    void nextToConfirmPayment(ActionEvent event) {
//        Stage stage = (Stage) confirm_nextBtn.getScene().getWindow();
//        stage.close();
//
//        // Jika Anda ingin beralih ke halaman login, gunakan kode berikut:
//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboardUser.fxml"));
//             Parent root = loader.load();
//        
//             Stage loginStage = new Stage();
//             Scene scene = new Scene(root);
//        
//             loginStage.setScene(scene);
//             loginStage.show();
//        
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
    }

    
    private int eventId;
    private int totalHarga;
    private int userId;
    
    public void initialize(int eventId, int totalHarga, int userId) {
        this.eventId = eventId;
        this.totalHarga = totalHarga;
        this.userId = userId;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
