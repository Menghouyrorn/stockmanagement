/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package addnewuser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AddNewUserController implements Initializable {

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
    private PasswordField txtpassword;
    @FXML
    private PasswordField txtcpassword;
    private String Fname, Lname, fmale, male, email, password, cpassword, Gender;
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
    }

    @FXML
    private void handleAddUser(ActionEvent event) {
        Fname = txtfname.getText();
        Lname = txtlname.getText();
        fmale = txtfmale.getText();
        male = txtmale.getText();
        email = txtemail.getText();
        password = txtpassword.getText();
        cpassword = txtcpassword.getText();
        if (txtfmale.isSelected()) {
            Gender = txtfmale.getText();
        } else {
            Gender = txtmale.getText();
        }
        if (Fname.isEmpty() || Lname.isEmpty() || fmale.isEmpty() || male.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty() || Gender.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter the value in the Text input");
        } else {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("select * from register where email=?");
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "The Email is use alert,Please Input the new Email");
                } else {
                    if (password.equals(cpassword)) {
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
                                    Parent root = FXMLLoader.load(getClass().getResource("../adminpage/AdminPage.fxml"));
                                    Stage stage = new Stage();
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (Exception e) {
                                }
                                txtfmale.getScene().getWindow().hide();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error");
                                txtfmale.getScene().getWindow().hide();
                            }
                        } catch (Exception e) {
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Password is not must");
                    }
                }  
            } catch (Exception e) {
            }
        }
    }

    @FXML
    private void handleBank(ActionEvent event) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("../adminpage/AdminPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtbank.getScene().getWindow().hide();
    }

}
