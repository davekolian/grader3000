/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axiostheos.grader3000;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Dave
 */
public class SearchController implements Initializable {

    public static String rname;

    @FXML
    private TextField searchBar;

    @FXML
    private Label lbname;

    @FXML
    private Label lbnation;

    @FXML
    private Button searchBtn;

    @FXML
    private Button btn1;

    @FXML
    private Button listBtn;

    public String getRName() {
        return rname;
    }

    /*@FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage = null;
        Parent root = null;
       
        if(event.getSource()==btn1){
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     */
    String name = null;

    @FXML
    public void searchOption() {
        name = searchBar.getText();
        startFunc2();
        //if nothing typed in pull up a dialog
    }

    @FXML
    public void searchOptionEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchOption();
        } else {
            startFunc();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void listStudents() throws Exception {
        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) listBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/Table.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void wrongSearch() throws Exception {

        searchBar.setText("");

        searchBar.requestFocus();

        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) searchBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/Search.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Results not found!");
        alert.setHeaderText(null);
        alert.setContentText(name + " does not exist!");

        alert.show();

    }

    public String sPageSet() {

        return rname;
    }

    public void startFunc() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/grader3000database", "root", "");

            Collection<String> collection = new ArrayList<String>();

            int i;
            for (i = 1; i < 20; i++) {
                String ans2 = "SELECT * FROM studentdetails WHERE id='" + i + "'";
                ResultSet rs2 = con.createStatement().executeQuery(ans2);
                while (rs2.next()) {
                    collection.add(rs2.getString("name"));
                }
            }

            TextFields.bindAutoCompletion(searchBar, collection).setMaxWidth(searchBar.getMaxWidth());

        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void startFunc2() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/grader3000database", "root", "");

            String ans = "SELECT * FROM studentdetails WHERE name='" + name + "'";

            ResultSet rs = con.createStatement().executeQuery(ans);

            if (rs.next()) {
                rname = rs.getString("name");

                Stage stage = (Stage) null;
                Parent root = (Parent) null;

                stage = (Stage) searchBtn.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("/fxml/StudentPage.fxml"));

                Scene scene = new Scene(root);
                stage.setX(440);
                stage.setY(15);
                stage.setScene(scene);
                stage.show();

            } else {
                wrongSearch();
            }

        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(SearchController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
