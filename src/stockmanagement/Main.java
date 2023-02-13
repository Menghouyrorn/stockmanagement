/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 *
 * @author Menghouy
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Login Page");
            Parent roottes = FXMLLoader.load(getClass().getResource("../login/LoginForm.fxml"));
            Scene scene = new Scene(roottes);
            scene.getStylesheets().add(getClass().getResource("../login/loginform.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    

    public static void main(String[] args) {
        launch(args);
    }
}
