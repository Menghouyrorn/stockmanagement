/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package viewitemuser;

import helper.DataProduct;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.DriverManager;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ViewItemUserController implements Initializable {

    @FXML
    private Button txtbank;
    @FXML
    private ComboBox<String> txtcombox;
    ObservableList<String> list = FXCollections.observableArrayList("Book", "Drink", "Computer");
    String valueSelect;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TableView<DataProduct> txtDataTable;
    @FXML
    private TableColumn<DataProduct, String> txtid;
    @FXML
    private TableColumn<DataProduct, String> txtname;
    @FXML
    private TableColumn<DataProduct, String> txtdescription;
    @FXML
    private TableColumn<DataProduct, String> txtquality;
    @FXML
    private TableColumn<DataProduct, String> txtprice;
    ObservableList<DataProduct> listProductB = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtcombox.setItems(list);

    }

    @FXML
    private void handleBank(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../userpage/UserPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("User Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtbank.getScene().getWindow().hide();
    }

    private void table() {
        txtDataTable.setItems(listProductB);
        txtid.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtname.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        txtquality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        txtprice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void handleSelect(ActionEvent event) {
        valueSelect = txtcombox.getValue();
        if (valueSelect.equals("Drink")) {
            listProductB.clear();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `drink`");
                rs = pst.executeQuery();
                while (rs.next()) {
                    listProductB.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
                }
                table();
            } catch (Exception e) {
            }
        } else if (valueSelect.equals("Book")) {
            listProductB.clear();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `book`");
                rs = pst.executeQuery();
                while (rs.next()) {
                    listProductB.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));

                }
                table();
            } catch (Exception e) {
            }
        } else if (valueSelect.equals("Computer")) {
            listProductB.clear();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `computer`");
                rs = pst.executeQuery();
                while (rs.next()) {
                    listProductB.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));

                }
                table();

            } catch (Exception e) {
            }
        }
    }
}
