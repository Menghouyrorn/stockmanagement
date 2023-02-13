/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package userpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class UserPageController implements Initializable {

    @FXML
    private Button txtView;
    @FXML
    private Button txtSearch;
    @FXML
    private Button txtExit;
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
    private void handleSearch(ActionEvent event) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("/searchiteamuser/searchIteamUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Search Item page");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
       txtSearch.getScene().getWindow().hide();
    }

    @FXML
    private void handleView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../viewitemuser/ViewItemUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("View Item User page");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtView.getScene().getWindow().hide();
    }

    @FXML
    private void handelExit(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Thank You for use my system");
        txtExit.getScene().getWindow().hide();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../login/LoginForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            txtback.getScene().getWindow().hide();
        } catch (Exception e) {
        }
    }

    
}
