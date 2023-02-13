/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EditUser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EditUserController implements Initializable {

    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtgender;
    @FXML
    private TextField txtlname;
    @FXML
    private Button txtEdit;
    boolean update;
    int userID;
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
    private void handleEdit(ActionEvent event) {
        setQuery();
    }
    private void setQuery() {
        if (update == true) {
            try {
                String fname = txtfname.getText();
                String lname = txtlname.getText();
                String email = txtemail.getText();
                String gender = txtgender.getText();
                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please input the data in the form");
                } else {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("UPDATE `register` SET `firstName`=?,`lastName`=?,`email`=?,`Gender`=? WHERE ID = '" + userID + "'");
                    pst.setString(1, fname);
                    pst.setString(2, lname);
                    pst.setString(3, email);
                    pst.setString(4, gender);
                    int rs = pst.executeUpdate();
                    if (rs == 1) {
                        JOptionPane.showMessageDialog(null, "Edit Successfull !");
                        txtfname.getScene().getWindow().hide();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                        txtfname.getScene().getWindow().hide();
                    }
                }
            } catch (Exception e) {
            }

        } else {
            System.out.println("Error");
        }
    }
    
    public void setTextFiled(int id, String fname, String lname, String email, String gender) {
        userID = id;
        txtfname.setText(fname);
        txtlname.setText(lname);
        txtemail.setText(email);
        txtgender.setText(gender);
    }

    public void getUpdate(boolean t) {
        this.update = t;
    }
}
