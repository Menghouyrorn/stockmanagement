/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package forgetpassword;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ForGetPasswordController implements Initializable {

    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtnewPassword;
    @FXML
    private PasswordField txtCpassword;
    private String email, npassword, cpassword;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Hyperlink txttologin;
    @FXML
    private Hyperlink txtToRegister;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleFgPassword(ActionEvent event) {
        email = txtemail.getText();
        npassword = txtnewPassword.getText();
        cpassword = txtCpassword.getText();
        if (email.isEmpty() || npassword.isEmpty() || cpassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Input the value in the form");
        } else {
            if (npassword.equals(cpassword)) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("select * from register where email=?");
                    pst.setString(1, email);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        try {
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                            pst = con.prepareStatement("UPDATE register set password=? where email=?");
                            pst.setString(1, npassword);
                            pst.setString(2, email);
                            int status = pst.executeUpdate();
                            if (status == 1) {
                                JOptionPane.showMessageDialog(null, "Update Successfull");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error");
                            }
                        } catch (Exception e) {
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "The email is don't has in the system");
                    }
                } catch (Exception e) {
                }
            }
            txtemail.setText("");
            txtnewPassword.setText("");
            txtCpassword.setText("");
        }
    }

    @FXML
    private void handleToLogin(ActionEvent event) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("../login/LoginForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txttologin.getScene().getWindow().hide();
    }

    @FXML
    private void handleToRegister(ActionEvent event) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("../register/Register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtToRegister.getScene().getWindow().hide();
    }

}
