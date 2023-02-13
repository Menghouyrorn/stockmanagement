/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EditItem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import viewitem.ViewItemController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EditItemController implements Initializable {

    @FXML
    private TextField txtname;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtqualiy;
    @FXML
    private TextField txtprice;
    @FXML
    private Button txtEdititem;
    String type;
    boolean edit;
    int productid;
    Connection con;
    PreparedStatement pst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void handleEditItem(ActionEvent event) {
        setEdit();
    }

    private void setEdit() {
        if (type.equals("Book")) {
            try {
                String name = txtname.getText();
                String description = txtdescription.getText();
                String quality = txtqualiy.getText();
                String price = txtprice.getText();
                if (name.isEmpty() || description.isEmpty() || quality.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Input the data in form !");
                } else {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("UPDATE `book` SET `Name`=?,`Description`=?,`Quality`=?,`Price`=? WHERE ID = '" + productid + "'");
                    pst.setString(1, name);
                    pst.setString(2, description);
                    pst.setString(3, quality);
                    pst.setString(4, price);
                    int rs = pst.executeUpdate();
                    if (rs == 1) {
                        JOptionPane.showMessageDialog(null, "Edit Successfull !");
                        txtname.getScene().getWindow().hide();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                }
            } catch (Exception e) {
            }
        } else if (type.equals("Drink")) {
            try {
                String name = txtname.getText();
                String description = txtdescription.getText();
                String quality = txtqualiy.getText();
                String price = txtprice.getText();
                if (name.isEmpty() || description.isEmpty() || quality.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Input the data in form !");
                } else {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("UPDATE `drink` SET `Name`=?,`Description`=?,`Quality`=?,`Price`=? WHERE ID = '" + productid + "'");
                    pst.setString(1, name);
                    pst.setString(2, description);
                    pst.setString(3, quality);
                    pst.setString(4, price);
                    int rs = pst.executeUpdate();
                    if (rs == 1) {
                        JOptionPane.showMessageDialog(null, "Edit Successfull !");
                        txtname.getScene().getWindow().hide();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                }
            } catch (Exception e) {
            }
        } else if (type.equals("Computer")) {
            try {
                String name = txtname.getText();
                String description = txtdescription.getText();
                String quality = txtqualiy.getText();
                String price = txtprice.getText();
                if (name.isEmpty() || description.isEmpty() || quality.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Input the data in form !");
                } else {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("UPDATE `computer` SET `Name`=?,`Description`=?,`Quality`=?,`Price`=? WHERE ID = '" + productid + "'");
                    pst.setString(1, name);
                    pst.setString(2, description);
                    pst.setString(3, quality);
                    pst.setString(4, price);
                    int rs = pst.executeUpdate();
                    if (rs == 1) {
                        JOptionPane.showMessageDialog(null, "Edit Successfull !");
                        txtname.getScene().getWindow().hide();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void setText(int id, String name, String description, String quality, String price) {
        productid = id;
        txtname.setText(name);
        txtdescription.setText(description);
        txtqualiy.setText(quality);
        txtprice.setText(price);
    }

    public void setType(String b) {
        this.type = b;
    }
}
