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
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dave
 */
public class StudentPageController implements Initializable {

    @FXML
    private Text tname;

    @FXML
    private Text tnep;

    @FXML
    private Text tcour;

    @FXML
    private Text tsem;

    @FXML
    private Text tgrade;

    public static SearchController s1 = new SearchController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            String test = s1.sPageSet();

            tname.setText(test);

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/grader3000database", "root", "");

            String ans = "SELECT * FROM studentdetails WHERE name='" + test + "'";

            ResultSet rs = con.createStatement().executeQuery(ans);

            while (rs.next()) {
                tname.setText(rs.getString("name"));
                tnep.setText(rs.getString("nepcode"));
                tsem.setText(rs.getString("currentsem"));
                tcour.setText(rs.getString("major"));
                tgrade.setText(rs.getString("grade"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentPageController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(StudentPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeGrade() {
        //For future builds
    }

    public void isSearch() throws Exception {
        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) tname.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/Search.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void isList() throws Exception {
        Stage stage = (Stage) null;
        Parent root = (Parent) null;

        stage = (Stage) tname.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/Table.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
