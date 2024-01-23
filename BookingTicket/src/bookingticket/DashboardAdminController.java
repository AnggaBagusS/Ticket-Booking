package bookingticket;

import model.transaksi;
import db.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DashboardAdminController implements Initializable{

    @FXML
    private TableView<transaksi> adminTable;

    @FXML
    private TableColumn<transaksi, Integer> colEventId;

    @FXML
    private TableColumn<transaksi, Integer> colTaransactionId;

    @FXML
    private TableColumn<transaksi, Integer> colTotal;
    
    @FXML
    private TableColumn<transaksi, String> colTransactionStatus;

    @FXML
    private TableColumn<transaksi, Integer> colUserId;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button terimaBtn;

    @FXML
    private Button tolakBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTransactions();
    }
    
    public ObservableList<transaksi> getTransactionData() {
        ObservableList<transaksi> transactions = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM transaksi";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            transaksi temp;
            while (rs.next()) {
                temp = new transaksi(rs.getInt("transaksiId"), rs.getInt("totalHarga"), rs.getInt("eventId"), rs.getInt("userId"), rs.getString("statusTransaksi"));
                transactions.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return transactions;
    }

    public void showTransactions(){
        ObservableList<transaksi> list = getTransactionData();

        colTaransactionId.setCellValueFactory(new PropertyValueFactory<>("transaksiId"));
        colEventId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalHarga"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colTransactionStatus.setCellValueFactory(new PropertyValueFactory<>("statusTransaksi"));
        

        adminTable.setItems(list);
    }
    
        @FXML
    void signout(ActionEvent event) {
        // Menutup jendela saat tombol signout ditekan
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();

        // Jika Anda ingin beralih ke halaman login, gunakan kode berikut:
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
             Parent root = loader.load();
        
             Stage loginStage = new Stage();
             Scene scene = new Scene(root);
        
             loginStage.setScene(scene);
             loginStage.show();
        
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
    
    public static void updateStatusTransaction(transaksi selectedTransaction, String newStatus) throws SQLException {
        String query = "UPDATE transaksi SET statusTransaksi = ? WHERE transaksiId = ?";
        try (Connection conn = DBHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newStatus);
            ps.setInt(2, selectedTransaction.getTransaksiId());
            ps.executeUpdate();
        } 
    }
    
    public void deleteTransaction(transaksi selectedTransaction) {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM transaksi WHERE transaksiId = ?")) {
            ps.setInt(1, selectedTransaction.getTransaksiId());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Transaksi berhasil dihapus.");
            } else {
                System.out.println("Gagal menghapus transaksi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Terjadi kesalahan SQL: " + e.getMessage());
        }

        // Refresh tabel setelah menghapus transaksi
        showTransactions();
    }

    Alert alert;
    
    @FXML
    void terima(ActionEvent event) {
        transaksi selectedTransaction = adminTable.getSelectionModel().getSelectedItem();

        if (selectedTransaction != null) {
            int transaksiId = selectedTransaction.getTransaksiId();
            // Lakukan logika terima transaksi sesuai kebutuhan Anda
            // ...

            // Ubah status transaksi menjadi "Verified"
            try {
                updateStatusTransaction(selectedTransaction, "Verified");
                selectedTransaction.setStatusTransaksi("Verified");

                // Refresh tabel setelah mengubah status transaksi
                adminTable.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Terjadi kesalahan SQL: " + e.getMessage());
                return; // Hentikan eksekusi lebih lanjut jika terjadi kesalahan
            }

            // Setelah melakukan operasi terima, hapus transaksi dari tabel
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Massage");
            alert.setHeaderText(null);
            alert.setContentText("Pembayaran Terverifikasi");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Massage");
            alert.setHeaderText(null);
            alert.setContentText("Pilih transaksi yang akan diverifikasi");
            alert.showAndWait();
        }
    }



    @FXML
    void tolak(ActionEvent event) {
        transaksi selectedTransaction = adminTable.getSelectionModel().getSelectedItem();

        if (selectedTransaction != null) {
            // Lakukan logika tolak transaksi sesuai kebutuhan Anda
            // ...

            // Setelah melakukan operasi tolak, hapus transaksi dari tabel
            deleteTransaction(selectedTransaction);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Massage");
            alert.setHeaderText(null);
            alert.setContentText("Data Berhasil Dihapus");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Massage");
            alert.setHeaderText(null);
            alert.setContentText("Pilih transaksi yang akan dihapus");
            alert.showAndWait();
        }
    }

    

}
