package gymmgtsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.DB;

/**
 * FXML Controller class
 *
 * @author Mohammad Arefin
 */
public class PackagesFormController implements Initializable {


    @FXML
    private JFXTextField packageName;
    @FXML
    private JFXTextField packageType;
    @FXML
    private JFXTextField packageDuration;
    @FXML
    private JFXTextField packageFee;

    @FXML
    private JFXButton addBtn;

    @FXML
    private TableView<String> packageTable;
    @FXML
    private TableColumn<String, String> serialColumn;
    @FXML
    private TableColumn<String, String> packageNameColumn;
    @FXML
    private TableColumn<String, String> packageTypeColumn;
    @FXML
    private TableColumn<String, String> packageDurationColumn;
    @FXML
    private TableColumn<String, String> packageFeeColumn;
    @FXML
    private TableColumn<?, ?> serialColumn1;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private AnchorPane rootPane;
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    private String colorCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showPackages();
        con = DB.getConnection();
        changeThemeColor();
    }

    @FXML
    private void addPackage(ActionEvent event) {
        try {
           
            String sql = "INSERT INTO package(package_name, package_type, package_duration, package_fee) VALUES(?,?,?,?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, packageName.getText());
            ps.setString(2, packageType.getText());
            ps.setString(3, packageDuration.getText());
            ps.setString(4, packageFee.getText());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {

            }

        } catch (Exception e) {
            System.out.println("Can not added package");
        }

    }

    private void clearForm() {
        packageName.setText("");
        packageType.setText("");
        packageDuration.setText("");
        packageFee.setText("");
    }

    private void showPackages() {
        try {
     
            String sql = "SELECT * FROM package ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void closeBtnAction(ActionEvent event) {
        Stage current = (Stage) closeBtn.getScene().getWindow();
        current.close();
    }
    
    private void changeThemeColor() {
        try {
            String sql = "SELECT color_code FROM color where id=1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                colorCode = rs.getString("color_code");
                rootPane.setStyle("-fx-background-color:" + colorCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
