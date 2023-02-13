/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Menghouy
 */
public class LoginFormController implements Initializable {
    private Connection conn;
    private PreparedStatement pst;
    ResultSet rs;
    @FXML
    private ImageView linkToRegister;
    @FXML
    private PasswordField txtpassword;
    private String email, password;
    @FXML
    private TextField txtemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handelLinkToRegister(MouseEvent event) {
        try {
            Parent roottes = FXMLLoader.load(getClass().getResource("../register/Register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register page");
            Scene scene = new Scene(roottes);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtemail.getScene().getWindow().hide();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        email = txtemail.getText();
        password = txtpassword.getText();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
            pst = conn.prepareStatement("select * from register where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                if (email.equals("admin@gmail.com") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Welcome to admin page");
                    try {
                        Parent roottes = FXMLLoader.load(getClass().getResource("../adminpage/AdminPage.fxml"));
                        Scene scene = new Scene(roottes);
                        Stage stage = new Stage();
                        stage.setTitle("Admin Page");
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Welcome to System");
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("../userpage/UserPage.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("User Page");
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {

                    }
                }
                txtemail.getScene().getWindow().hide();
                txtemail.setText("");
                txtpassword.setText("");
            } else {
                txtemail.setText("");
                txtpassword.setText("");
                JOptionPane.showMessageDialog(null, "Password or email is not much !");
            }
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void handelLinkForgetPassword(ActionEvent event) throws SQLException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../forgetpassword/ForGetPassword.fxml"));
            Stage stage = new Stage();
            stage.setTitle("ForGetPassword page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
        }
    txtemail.getScene().getWindow().hide();
    }


}
