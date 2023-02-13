package additem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AddItemController implements Initializable {

    @FXML
    private TextField txtQuality;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDiscription;
    @FXML
    private TextField txtPrice;
    @FXML
    private ComboBox<String> txtcombobox;
    ObservableList<String> list = FXCollections.observableArrayList("Book", "Drink", "Computer");
    private String valueClick;
    private String quality, name, description, price;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Button txtbank;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtcombobox.setItems(list);
    }

    @FXML
    private void handleCLickCombo(ActionEvent event) {

    }

    @FXML
    private void handleBank(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../adminpage/AdminPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtbank.getScene().getWindow().hide();
    }


    @FXML
    private void handleSave(ActionEvent event) {
        quality = txtQuality.getText();
        name = txtName.getText();
        description = txtDiscription.getText();
        price = txtPrice.getText();

        valueClick = txtcombobox.getValue();

        if (valueClick.equals("Book")) {
            if (quality.isEmpty() || name.isEmpty() || description.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input data in form");
            } else {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("insert into book (Name,Description,Quality,Price) values (?,?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, description);
                    pst.setString(3, quality);
                    pst.setString(4, price);
                    int status = pst.executeUpdate();
                    if (status == 1) {
                        JOptionPane.showMessageDialog(null, "Successfull !");
                        txtQuality.setText("");
                        txtName.setText("");
                        txtDiscription.setText("");
                        txtPrice.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                        txtQuality.setText("");
                        txtName.setText("");
                        txtDiscription.setText("");
                        txtPrice.setText("");
                    }
                } catch (Exception e) {
                }
            }
        } else if (valueClick.equals("Drink")) {
            if ( quality.isEmpty() || name.isEmpty() || description.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input data in form");
            } else {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("insert into drink (Name,Description,Quality,Price) values (?,?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, description);
                    pst.setString(3, quality);
                    pst.setString(4, price);
                    int status = pst.executeUpdate();
                    if (status == 1) {
                        JOptionPane.showMessageDialog(null, "Successfull !");
                        txtQuality.setText("");
                        txtName.setText("");
                        txtDiscription.setText("");
                        txtPrice.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                        txtQuality.setText("");
                        txtName.setText("");
                        txtDiscription.setText("");
                        txtPrice.setText("");
                    }
                } catch (Exception e) {
                }
            }
        } else if(valueClick.equals("Computer")){
            if ( quality.isEmpty() || name.isEmpty() || description.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input data in form");
            } else {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("insert into computer (Name,Description,Quality,Price) values (?,?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, description);
                    pst.setString(3, quality);
                    pst.setString(4, price);
                    int status = pst.executeUpdate();
                    if (status == 1) {
                        JOptionPane.showMessageDialog(null, "Successfull !");
                        txtQuality.setText("");
                        txtName.setText("");
                        txtDiscription.setText("");
                        txtPrice.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                        txtQuality.setText("");
                        txtName.setText("");
                        txtDiscription.setText("");
                        txtPrice.setText("");
                    }
                } catch (Exception e) {
                }
            }
        }
    }

}
