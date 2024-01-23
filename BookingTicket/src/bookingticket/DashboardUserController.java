package bookingticket;

import model.event;
import db.DBHelper;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DashboardUserController implements Initializable{
    
    @FXML
    private Button buyBtn;

    @FXML
    private Button clearBtn;
    
    @FXML
    private Button signout;
    
    @FXML
    private TableColumn<event, String> colCategory;

    @FXML
    private TableColumn<event, Date> colDate;

    @FXML
    private TableColumn<event, String> colName;

    @FXML
    private TableColumn<event, Integer> colId;
    
    @FXML
    private TableView<event> tvData;
    
    @FXML
    private Button selectBtn;
    
    @FXML
    private Label totalPrice;
    
    @FXML
    private Label priceNormal;

    @FXML
    private Label priceSpesial;
    
    @FXML
    private Spinner<Integer> normalQuantity;
    
    @FXML
    private Spinner<Integer> specialQantity;
    
    @FXML
    private ImageView imageView;

    private int userId;
    
    public void initialize(int userId) {
        this.userId = userId;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showSpinnerValue();
        showEvents();
    }


    @FXML
    void signout(ActionEvent event) {
        // Menutup jendela saat tombol signout ditekan
        Stage stage = (Stage) signout.getScene().getWindow();
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
    
    public ObservableList<event> getEventData() {
        ObservableList<event> events = FXCollections.observableArrayList();
        Connection conn = DBHelper.getConnection();
        String query = "SELECT * FROM acara";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            event temp;
            
            while (rs.next()) {
                temp = new event(rs.getInt("eventId"), rs.getString("eventName"), rs.getString("eventCategory"), rs.getDate("eventDate"), rs.getInt("harga"), rs.getInt("harga_spesial"), rs.getString("imageUrl"));
                events.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public void showEvents() {
        ObservableList<event> list = getEventData();

        colId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("eventCategory"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));

        tvData.setItems(list);
    }
    
    private int calculateSpecialClassPrice(event selectedEvent) {
        Connection conn = DBHelper.getConnection();
        String query = "SELECT harga_spesial FROM acara WHERE eventId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, selectedEvent.getEventId());
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("harga_spesial");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Selalu tutup statement dan result set setelah digunakan
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0; // Nilai default jika terjadi kesalahan
    }
    
    private int calculateNormalClassPrice(event selectedEvent) {
        Connection conn = DBHelper.getConnection();
        String query = "SELECT harga FROM acara WHERE eventId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, selectedEvent.getEventId());
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("harga");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Selalu tutup statement dan result set setelah digunakan
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0; // Nilai default jika terjadi kesalahan
    }
    
    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;
    
    private float price1=0;
    private float price2=0;
    private int total=0;
    private int qty1 = 0;
    private int qty2 = 0;
    
    
    @FXML
    void clear(ActionEvent event) {
        // Hapus nilai total dan harga
        priceSpesial.setText("Rp0");
        priceNormal.setText("Rp0");
        totalPrice.setText("Rp0");

        // Reset nilai Spinner ke 0
        specialQantity.getValueFactory().setValue(0);
        normalQuantity.getValueFactory().setValue(0);
    }
    

    
    public void showSpinnerValue(){
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        
        specialQantity.setValueFactory(spinner1);
        normalQuantity.setValueFactory(spinner2);
        
    }
    
    @FXML
    void selectAvailabelMovie(ActionEvent event) {
        event selectedEvent = tvData.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            int specialClassPrice = calculateSpecialClassPrice(selectedEvent);
            int normalClassPrice = calculateNormalClassPrice(selectedEvent);
            
            // Menampilkan gambar pada ImageView
            showEventImage(selectedEvent.getImageUrl());


            // Gunakan harga yang diambil dari database untuk melakukan operasi lainnya
            // Misalnya, menetapkan harga di label atau melakukan perhitungan total harga
            

            priceSpesial.setText("Rp" + specialClassPrice);
            priceNormal.setText("Rp" + normalClassPrice);

            // Contoh perhitungan total harga
            int total = (int) specialQantity.getValue() * specialClassPrice + (int) normalQuantity.getValue() * normalClassPrice;
            totalPrice.setText("Rp" + total);
        }
    }
    
    private void showEventImage(String imageUrl) {
        ImageView imageView = new ImageView();

        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                // Try loading the image
                Image image = new Image(imageUrl);
                imageView.setImage(image);
                // Set ImageView to the existing ImageView in your FXML
                this.imageView.setImage(imageView.getImage());
            } catch (IllegalArgumentException e) {
                // Handle the case where the image URL is invalid
                System.out.println("Invalid image URL: " + imageUrl);
                e.printStackTrace();
                // You might want to set a default image here or handle the error in some other way
            }
        }
    }

    @FXML
    void buy(ActionEvent event) throws SQLException {
        event selectedEvent = tvData.getSelectionModel().getSelectedItem();
        String statusTransaksi = "Not Verified";

        if (selectedEvent != null) {
            try (Connection conn = DBHelper.getConnection()) {
                // Mendapatkan harga dari event yang dipilih
                int specialClassPrice = calculateSpecialClassPrice(selectedEvent);
                int normalClassPrice = calculateNormalClassPrice(selectedEvent);

                // Mendapatkan nilai dari Spinner
                int qty1 = specialQantity.getValue();
                int qty2 = normalQuantity.getValue();

                // Contoh perhitungan total harga
                int total = qty1 * specialClassPrice + qty2 * normalClassPrice;

                // Menampilkan peringatan jika total adalah nol
                if (total == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Tidak ada tiket yang dipilih!");
                    alert.showAndWait();
                    return; // Keluar dari metode jika total adalah nol
                }

                // Memasukkan transaksi ke dalam database
                String query = "INSERT INTO transaksi (eventId, totalHarga, userId, statusTransaksi) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, selectedEvent.getEventId());
                    ps.setInt(2, total);
                    ps.setInt(3, userId);
                    ps.setString(4, statusTransaksi);

                    int affectedRows = ps.executeUpdate();
                    Alert alert;

                    if (affectedRows > 0) {
                        // Mendapatkan nilai eventId yang baru saja dimasukkan
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int eventId = generatedKeys.getInt(1);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Transaksi berhasil disimpan!");
                            alert.showAndWait();
                        } else {
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Gagal mendapatkan eventId");
                            alert.showAndWait();
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Gagal menyimpan transaksi");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
