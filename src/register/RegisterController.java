/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtfname;
    @FXML
    private TextField txtlname;
    @FXML
    private RadioButton txtfmale;
    @FXML
    private RadioButton txtmale;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtpassword;
    @FXML
    private TextField txtcpassword;
    private String Fname, Lname, fmale, male, email, password, Gender, CPassword;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Hyperlink txtToLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Fname = "";
        Lname = "";
        fmale = "";
        male = "";
        email = "";
        password = null;
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        Fname = txtfname.getText();
        Lname = txtlname.getText();
        fmale = txtfmale.getText();
        male = txtmale.getText();
        email = txtemail.getText();
        password = txtpassword.getText();
        CPassword = txtcpassword.getText();
        try {
            if (txtfmale.isSelected()) {
                Gender = txtfmale.getText();
            } else {
                Gender = txtmale.getText();
            }
        } catch (Exception e) {
        }
        if (Fname.isEmpty() || Lname.isEmpty() || fmale.isEmpty() || male.isEmpty() || email.isEmpty() || password.isEmpty() || CPassword.isEmpty() || Gender.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Plase Input the data in form");
        } else {
            if (password.equals(CPassword)) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("select * from register where email=?");
                    pst.setString(1, email);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "The Email is use alert,Please Input the new Email");
                    } else {
                        try {
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                            pst = con.prepareStatement("Insert into register (firstName,lastName,email,password,Gender) values (?,?,?,?,?)");
                            pst.setString(1, Fname);
                            pst.setString(2, Lname);
                            pst.setString(3, email);
                            pst.setString(4, password);
                            pst.setString(5, Gender);
                            int status = pst.executeUpdate();
                            if (status == 1) {
                                JOptionPane.showMessageDialog(null, "Add Successfull");
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("../login/LoginForm.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Login Page");
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (Exception e) {
                                }
                                txtfmale.setText("");
                                txtmale.setText("");
                                txtlname.setText("");
                                txtfname.setText("");
                                txtemail.setText("");
                                txtpassword.setText("");
                                txtcpassword.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error");
                            }
                        } catch (Exception e) {
                        }
                        txtfname.getScene().getWindow().hide();

                    }
                } catch (Exception e) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password is not must");
            }
        }
    }

    @FXML
    private void handleToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../login/LoginForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtToLogin.getScene().getWindow().hide();
    }

}
