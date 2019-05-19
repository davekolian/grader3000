/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.axiostheos.grader3000;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Dave
 */
public class LoginController implements Initializable {

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button login;

    /*@FXML
    private AnchorPane bg;*/
    String username = null;
    String password = null;

    @FXML
    public void getUsername() {
        username = user.getText();
        pass.requestFocus();
    }

    @FXML
    public void getUsernameEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            getUsername();
            pass.requestFocus();
        }
    }

    @FXML
    public void getPassword() {
        password = pass.getText();
        pass.requestFocus();
    }

    @FXML
    public void getBoth() {
        getUsername();
        getPassword();

        startFunc();
    }

    @FXML
    public void getPasswordEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            getBoth();
            login.requestFocus();
        }

    }

    public void changeScene() throws Exception {
        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) login.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/Search.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void wrongLogin() throws Exception {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Incorrect Credentials!");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect username or password");

        alert.show();

        user.setText("");
        pass.setText("");

        user.requestFocus();

        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) login.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void startFunc() {

        String userCheck = null;
        String passCheck = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grader3000database", "root", "");

            String ans = "SELECT * FROM logindetails WHERE username='" + username + "'";

            ResultSet rs = con.createStatement().executeQuery(ans);

            if (!rs.next() && userCheck == null) {
                userCheck = "";
                passCheck = "";
            } else {
                userCheck = rs.getString("username");
                passCheck = rs.getString("password");

            }

            if ((userCheck.equals(username)) && (passCheck.equals(password))) {
                changeScene();
            } else {
                wrongLogin();
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
