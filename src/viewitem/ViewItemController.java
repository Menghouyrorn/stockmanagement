/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package viewitem;

import EditItem.EditItemController;
import helper.DataProduct;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javax.swing.JOptionPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ViewItemController implements Initializable {

    @FXML
    private Button txtbank;
    @FXML
    private ComboBox<String> txtcombox;
    ObservableList<String> list = FXCollections.observableArrayList("Book", "Drink", "Computer");
    String valueSelect;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DataProduct dataproduct;
    @FXML
    private TableView<DataProduct> txttableitem;
    @FXML
    private TableColumn<DataProduct, String> txtID;
    @FXML
    private TableColumn<DataProduct, String> txtname;
    @FXML
    private TableColumn<DataProduct, String> txtdescription;
    @FXML
    private TableColumn<DataProduct, String> txtQuality;
    @FXML
    private TableColumn<DataProduct, String> txtPrice;
    @FXML
    private TableColumn<DataProduct, String> txtedit;
    @FXML
    private TableColumn<DataProduct, String> txtdelete;
    ObservableList<DataProduct> listProduct = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtcombox.setItems(list);
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
    
    public void refressdataD() {
        listProduct.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
            pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `drink` ORDER BY ID DESC");
            rs = pst.executeQuery();
            while (rs.next()) {
                listProduct.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
            }

        } catch (Exception e) {
        }
    }
    
    public void refressdataB() {
        listProduct.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
            pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `book` ORDER BY ID DESC");
            rs = pst.executeQuery();
            while (rs.next()) {
                listProduct.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
            }

        } catch (Exception e) {
        }
    }
    
    public void refressdataC() {
        listProduct.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
            pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `computer` ORDER BY ID DESC");
            rs = pst.executeQuery();
            while (rs.next()) {
                listProduct.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
            }

        } catch (Exception e) {
        }
    }

    @FXML
    private void handleSelect(ActionEvent event) {
        valueSelect = txtcombox.getValue();
        if (valueSelect.equals("Drink")) {
            listProduct.clear();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `drink` ORDER BY ID DESC");
                rs = pst.executeQuery();
                while (rs.next()) {
                    listProduct.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
                }
                txttableitem.setItems(listProduct);
                txtID.setCellValueFactory(new PropertyValueFactory<>("id"));
                txtname.setCellValueFactory(new PropertyValueFactory<>("name"));
                txtdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                txtQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
                txtPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                editBtn();
                deleteBtn();

            } catch (Exception e) {
            }
        } else if (valueSelect.equals("Book")) {
            listProduct.clear();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `book` ORDER BY ID DESC");
                rs = pst.executeQuery();
                while (rs.next()) {
                    listProduct.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
                }
                txttableitem.setItems(listProduct);
                txtID.setCellValueFactory(new PropertyValueFactory<>("id"));
                txtname.setCellValueFactory(new PropertyValueFactory<>("name"));
                txtdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                txtQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
                txtPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                editBtn();
                deleteBtn();

            } catch (Exception e) {
            }
        } else if (valueSelect.equals("Computer")) {
            listProduct.clear();
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                pst = con.prepareStatement("SELECT `ID`, `Name`, `Description`, `Quality`, `Price` FROM `computer` ORDER BY ID DESC");
                rs = pst.executeQuery();
                while (rs.next()) {
                    listProduct.add(new DataProduct(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("Description"),
                            rs.getString("Quality"),
                            rs.getString("Price")
                    ));
                }
                txttableitem.setItems(listProduct);
                txtID.setCellValueFactory(new PropertyValueFactory<>("id"));
                txtname.setCellValueFactory(new PropertyValueFactory<>("name"));
                txtdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                txtQuality.setCellValueFactory(new PropertyValueFactory<>("quality"));
                txtPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                editBtn();
                deleteBtn();
            } catch (Exception e) {
            }
        }
    }

    public void editBtn() {
        Callback<TableColumn<DataProduct, String>, TableCell<DataProduct, String>> cellFoctory = (TableColumn<DataProduct, String> param) -> {
            final TableCell<DataProduct, String> cell = new TableCell<DataProduct, String>() {
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
                            dataproduct = txttableitem.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("../EditItem/EditItem.fxml"));
                            try {
                                loader.load();
                            } catch (Exception e) {
                            }
                            EditItemController editproduct = loader.getController();
                            editproduct.setType(txtcombox.getValue());
                            editproduct.setText(dataproduct.getId(), dataproduct.getName(), dataproduct.getDescription(), dataproduct.getQuality(), dataproduct.getPrice());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setTitle("Edit Item Page");
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
        txtedit.setCellFactory(cellFoctory);
        txttableitem.setItems(listProduct);
    }

    public void deleteBtn() {
        Callback<TableColumn<DataProduct, String>, TableCell<DataProduct, String>> cellFoctory = (TableColumn<DataProduct, String> param) -> {
            final TableCell<DataProduct, String> cell = new TableCell<DataProduct, String>() {
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
                            if (valueSelect.equals("Drink")) {
                                try {
                                    dataproduct = txttableitem.getSelectionModel().getSelectedItem();
                                    System.out.println("Data is :" + dataproduct.getId());
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                    pst = con.prepareStatement("DELETE FROM `drink` WHERE ID =" + dataproduct.getId());
                                    pst.execute();
                                    if (pst != null) {
                                        JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                        refressdataD();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error");
                                    }

                                } catch (Exception e) {
                                }
                            } else if (valueSelect.equals("Book")) {
                                try {
                                    dataproduct = txttableitem.getSelectionModel().getSelectedItem();
                                    System.out.println("Data is :" + dataproduct.getId());
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                    pst = con.prepareStatement("DELETE FROM `book` WHERE ID =" + dataproduct.getId());
                                    pst.execute();
                                    if (pst != null) {
                                        JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                        refressdataB();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error");
                                    }

                                } catch (Exception e) {
                                }
                            } else if (valueSelect.equals("Computer")) {
                                try {
                                    dataproduct = txttableitem.getSelectionModel().getSelectedItem();
                                    System.out.println("Data is :" + dataproduct.getId());
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                    pst = con.prepareStatement("DELETE FROM `computer` WHERE ID =" + dataproduct.getId());
                                    pst.execute();
                                    if (pst != null) {
                                        JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                        refressdataC();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error");
                                    }

                                } catch (Exception e) {
                                }
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
        txtdelete.setCellFactory(cellFoctory);
        txttableitem.setItems(listProduct);
    }

    public void select(){
        valueSelect=txtcombox.getValue();
    }

    @FXML
    private void handlereload(ActionEvent event) {
        if(valueSelect.equals("Book")){
            refressdataB();
        }else if(valueSelect.equals("Drink")){
            refressdataD();
        }else if(valueSelect.equals("Computer")){
            refressdataC();
        }
        
    }
}
