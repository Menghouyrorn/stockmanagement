/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package viewuser;

import EditUser.EditUserController;
import helper.Data;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ViewUserController implements Initializable {

    @FXML
    private Button txtbank;
    @FXML
    private TableView<Data> txttable;
    @FXML
    private TableColumn<Data, String> txtFirstName;
    @FXML
    private TableColumn<Data, String> txtLastName;
    @FXML
    private TableColumn<Data, String> txtemail;
    @FXML
    private TableColumn<Data, String> txtgender;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ObservableList<Data> datalist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Data, String> txtEdit;
    Data data;
    @FXML
    private TableColumn<Data, String> txtDelete;
    @FXML
    private TableColumn<Data, String> txtId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loaddata();
    }

    @FXML
    private void handleBank(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../adminpage/AdminPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin Page");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
        }
        txtbank.getScene().getWindow().hide();
    }

    public void refressdata() {
        datalist.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
            pst = con.prepareStatement("SELECT `ID`,`firstName`, `lastName`, `email`, `Gender` FROM `register` ORDER BY ID DESC");
//            pst = con.prepareStatement("SELECT `ID`,`firstName`, `lastName`, `email`, `Gender` FROM `register` ORDER BY ID DESC limit 4");
            rs = pst.executeQuery();
            while (rs.next()) {
                datalist.add(new Data(
                        rs.getInt("ID"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("Gender")));
                txttable.setItems(datalist);
            }

        } catch (Exception e) {
        }
    }

    public void loaddata() {
        refressdata();
        txtId.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtFirstName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        txtLastName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        txtgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        editBtn();
        deleteBtn();
    }

    public void editBtn() {
        Callback<TableColumn<Data, String>, TableCell<Data, String>> cellFoctory = (TableColumn<Data, String> param) -> {
            final TableCell<Data, String> cell = new TableCell<Data, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button editIcon = new Button("Edit");

                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                        );

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            data = txttable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("../EditUser/EditUser.fxml"));
                            try {
                                loader.load();
                            } catch (Exception e) {
                            }
                            EditUserController edituser = loader.getController();
                            edituser.getUpdate(true);
                            edituser.setTextFiled(data.getId(), data.getFname(), data.getLname(), data.getEmail(), data.getGender());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setTitle("Edit User Page");
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });

                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        txtEdit.setCellFactory(cellFoctory);
        txttable.setItems(datalist);
    }

    public void deleteBtn() {
        Callback<TableColumn<Data, String>, TableCell<Data, String>> cellFoctory = (TableColumn<Data, String> param) -> {
            final TableCell<Data, String> cell = new TableCell<Data, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button deleteIcon = new Button("Delete");
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                data = txttable.getSelectionModel().getSelectedItem();
                                System.out.println("Data is :" + data.getId());
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                pst = con.prepareStatement("DELETE FROM `register` WHERE ID =" + data.getId());
                                pst.execute();
                                if (pst != null) {
                                    JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                    refressdata();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error");
                                }

                            } catch (Exception e) {
                            }

                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        txtDelete.setCellFactory(cellFoctory);
        txttable.setItems(datalist);
    }

    @FXML
    private void handleReload(ActionEvent event) {
        refressdata();
    }
}
