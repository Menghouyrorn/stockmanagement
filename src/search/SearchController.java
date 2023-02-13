/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package search;

import EditItem.EditItemController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import helper.DataProduct;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SearchController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private TextField txtname;
    @FXML
    private ComboBox<String> txtcombo;
    ObservableList<String> list = FXCollections.observableArrayList("Book", "Drink", "Computer");

    private String select;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Button txtbank;
    @FXML
    private TableView<DataProduct> tableSearch;
    @FXML
    private TableColumn<DataProduct, String> txtidcol;
    @FXML
    private TableColumn<DataProduct, String> txtdescription;
    @FXML
    private TableColumn<DataProduct, String> txtquality;
    @FXML
    private TableColumn<DataProduct, String> txtprice;
    @FXML
    private TableColumn<DataProduct, String> txtedit;
    @FXML
    private TableColumn<DataProduct, String> txtdelete;
    @FXML
    private TableColumn<DataProduct, String> txtnamecol;
    ObservableList<DataProduct> listProduct = FXCollections.observableArrayList();
    DataProduct dataproduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtcombo.setItems(list);
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

    @FXML
    private void handleSearch(ActionEvent event) {
        SearchData();
    }

    @FXML
    private void handleselect(ActionEvent event) {
        select = txtcombo.getValue();
        System.out.println("The Select : " + select);
    }

    public void table() {
        tableSearch.setItems(listProduct);
        txtidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtnamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        txtdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        txtquality.setCellValueFactory(new PropertyValueFactory<>("quality"));
        txtprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        editBtn();
        deleteBtn();
    }

    public void SearchData() {
        String productid = txtid.getText();
        String name = txtname.getText();
        if (select.equals("Book")) {
            if (productid != "" && name != "") {
                System.out.println("The ID and Name");
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM book where ID=? and Name=?");
                    pst.setString(1, productid);
                    pst.setString(2, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            } else if (productid != "" && name.isEmpty()) {
                System.out.println("The ID");
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM book where ID=?");
                    pst.setString(1, productid);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            } else if (name != "" && productid.isEmpty()) {
                System.out.println("The name");
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM book where Name=?");
                    pst.setString(1, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            }

        } else if (select.equals("Drink")) {
            if (productid != "" && name != "") {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM drink where ID=? and Name=?");
                    pst.setString(1, productid);
                    pst.setString(2, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            } else if (productid != "" && name.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM drink where ID=?");
                    pst.setString(1, productid);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            } else if (name != "" && productid.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM drink where Name=?");
                    pst.setString(1, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            }

        } else if (select.equals("Computer")) {
            if (productid != "" && name != "") {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM computer where ID=? and Name=?");
                    pst.setString(1, productid);
                    pst.setString(2, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            } else if (productid != "" && name.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM computer where ID=?");
                    pst.setString(1, productid);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
            } else if (name != "" && productid.isEmpty()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                    pst = con.prepareStatement("SELECT * FROM computer where Name=?");
                    pst.setString(1, name);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        listProduct.add(new DataProduct(
                                rs.getInt("ID"),
                                rs.getString("Name"),
                                rs.getString("Description"),
                                rs.getString("Quality"),
                                rs.getString("Price")
                        ));
                        txtid.setText("");
                        txtname.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "Don't has in system !");
                    }
                    table();
                } catch (Exception e) {
                }
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
                            dataproduct = tableSearch.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("../EditItem/EditItem.fxml"));
                            try {
                                loader.load();
                            } catch (Exception e) {
                            }
                            EditItemController editproduct = loader.getController();
                            editproduct.setType(txtcombo.getValue());
                            editproduct.setText(dataproduct.getId(), dataproduct.getName(), dataproduct.getDescription(), dataproduct.getQuality(), dataproduct.getPrice());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
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
        tableSearch.setItems(listProduct);
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
                            if (select.equals("Drink")) {
                                try {
                                    dataproduct = tableSearch.getSelectionModel().getSelectedItem();
                                    System.out.println("Data is :" + dataproduct.getId());
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                    pst = con.prepareStatement("DELETE FROM `drink` WHERE ID =" + dataproduct.getId());
                                    pst.execute();
                                    if (pst != null) {
                                        JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                        listProduct.clear();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error");
                                    }

                                } catch (Exception e) {
                                }
                            } else if (select.equals("Book")) {
                                try {
                                    dataproduct = tableSearch.getSelectionModel().getSelectedItem();
                                    System.out.println("Data is :" + dataproduct.getId());
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                    pst = con.prepareStatement("DELETE FROM `book` WHERE ID =" + dataproduct.getId());
                                    pst.execute();
                                    if (pst != null) {
                                        JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                        listProduct.clear();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error");
                                    }

                                } catch (Exception e) {
                                }
                            } else if (select.equals("Computer")) {
                                try {
                                    dataproduct = tableSearch.getSelectionModel().getSelectedItem();
                                    System.out.println("Data is :" + dataproduct.getId());
                                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
                                    pst = con.prepareStatement("DELETE FROM `computer` WHERE ID =" + dataproduct.getId());
                                    pst.execute();
                                    if (pst != null) {
                                        JOptionPane.showMessageDialog(null, "Delete Successfull !");
                                        listProduct.clear();
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
        tableSearch.setItems(listProduct);
    }
}
