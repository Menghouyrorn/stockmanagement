/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package adminpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AdminPageController implements Initializable {

    @FXML
    private Button txtAddUser;
    @FXML
    private Button txtViewuser;
    @FXML
    private Button txtAddNewIteam;
    @FXML
    private Button txtExit;
    @FXML
    private Button txtViewIteam;
    @FXML
    private Button txtSearchadmin;
    @FXML
    private Button txtback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../viewuser/ViewUser.fxml"));
            Stage stage = new Stage();
            stage.setTitle("View user page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtViewuser.getScene().getWindow().hide();
    }

    @FXML
    private void handelExit(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Thank You for use my system");
        txtExit.getScene().getWindow().hide();
    }

    @FXML
    private void handleAddNewItem(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../additem/AddItem.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AddItem Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {

        }
        txtAddNewIteam.getScene().getWindow().hide();
    }

    @FXML
    private void handleAddUser(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../addnewuser/AddNewUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add New User Page");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtAddUser.getScene().getWindow().hide();
    }

    @FXML
    private void handleViewIteam(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../viewitem/ViewItem.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("ViewItem page");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtViewIteam.getScene().getWindow().hide();
    }

    @FXML
    private void handleSearchAdmin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../search/Search.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Search page");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtSearchadmin.getScene().getWindow().hide();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../login/LoginForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            txtback.getScene().getWindow().hide();
        } catch (Exception e) {
        }
    }

}
