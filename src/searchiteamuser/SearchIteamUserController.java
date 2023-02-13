/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package searchiteamuser;

import helper.DataProduct;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SearchIteamUserController implements Initializable {

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> txtcombox;
    @FXML
    private Button txtBank;
    @FXML
    private Button txtSearch;
    ObservableList<String> list = FXCollections.observableArrayList("Book", "Drink", "Computer");
    private String valueSelect;
    @FXML
    private TableView<DataProduct> tbluserSearch;
    @FXML
    private TableColumn<DataProduct, String> idcol;
    @FXML
    private TableColumn<DataProduct, String> namecol;
    @FXML
    private TableColumn<DataProduct, String> descriptioncol;
    @FXML
    private TableColumn<DataProduct, String> qualitycol;
    @FXML
    private TableColumn<DataProduct, String> pricecol;
    ObservableList<DataProduct> listProduct = FXCollections.observableArrayList();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtcombox.setItems(list);
    }

    @FXML
    private void handleSelect(ActionEvent event) {
        valueSelect = txtcombox.getValue();
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
        txtBank.getScene().getWindow().hide();
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        searchData();
    }

    public void table() {
        tbluserSearch.setItems(listProduct);
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        qualitycol.setCellValueFactory(new PropertyValueFactory<>("quality"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void searchData() {
        String name = txtName.getText();
        String id = txtID.getText();
        if (valueSelect.equals("Book")) {
            if (id != "" && name != "") {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM book where ID=? and Name=?");
                    pst.setString(1, id);
                    pst.setString(2, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            } else if (id != "" && name.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM book where ID=?");
                    pst.setString(1, id);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            } else if (name != "" && id.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM book where Name=?");
                    pst.setString(1, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            }

        } else if (valueSelect.equals("Drink")) {
            if (id != "" && name != "") {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM drink where ID=? and Name=?");
                    pst.setString(1, id);
                    pst.setString(2, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            } else if (id != "" && name.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM drink where ID=?");
                    pst.setString(1, id);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            } else if (name != "" && id.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM drink where Name=?");
                    pst.setString(1, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            }
        } else if (valueSelect.equals("Computer")) {
            if (id != "" && name != "") {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM computer where ID=? and Name=?");
                    pst.setString(1, id);
                    pst.setString(2, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            } else if (id != "" && name.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM computer where ID=?");
                    pst.setString(1, id);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            } else if (name != "" && id.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM computer where Name=?");
                    pst.setString(1, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    txtName.setText("");
                    txtID.setText("");
                    table();
                } catch (Exception e) {
                }
            }
        }
    }

}
