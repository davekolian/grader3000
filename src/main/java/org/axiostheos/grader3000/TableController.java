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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dave
 */
public class TableController implements Initializable {

    @FXML
    private Button btn1;

    @FXML
    private VBox vbox1 = new VBox();

    @FXML
    private VBox vbox2 = new VBox();

    @FXML
    private VBox vbox3 = new VBox();

    @FXML
    private VBox vbox4 = new VBox();

    @FXML
    private VBox vbox5 = new VBox();

    @FXML
    private VBox vbox6 = new VBox();

    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        if (event.getSource() == btn1) {
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/grader3000database", "root", "");

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM studentdetails");

            while (rs.next()) {

                vbox1.getChildren().add(new Text(rs.getString("name")));
                vbox2.getChildren().add(new Text(rs.getString("nationality")));
                vbox3.getChildren().add(new Text(rs.getString("currentsem")));
                vbox4.getChildren().add(new Text(rs.getString("nepcode")));
                vbox5.getChildren().add(new Text(rs.getString("grade")));
                vbox6.getChildren().add(new Text(rs.getString("major")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void isSearch() throws Exception {
        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) vbox1.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/Search.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
