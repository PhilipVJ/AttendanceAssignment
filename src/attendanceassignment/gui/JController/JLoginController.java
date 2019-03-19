/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import attendanceassignment.gui.Model.AttendanceModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JLoginController implements Initializable {

    AttendanceModel atModel;
    private Stage stage;

    @FXML
    private JFXComboBox<String> comboboxChoose;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    private BorderPane rootLayout;

    public JLoginController() throws IOException {

    }

    public ObservableList chooseYouRole() {
        ObservableList<String> role = FXCollections.observableArrayList(
                "Student",
                "Teacher",
                "Admin");
        return role;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxChoose.getItems().addAll(chooseYouRole());

        try {
            atModel = new AttendanceModel();
        } catch (IOException ex) {
            Logger.getLogger(JLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {

        boolean isUserFound = atModel.loginToStudentTeacher(
                username.getText(),
                password.getText(),
                comboboxChoose.getSelectionModel().getSelectedIndex());
        
        if (isUserFound == true && comboboxChoose.getSelectionModel().isSelected(0))
        {
            
        }
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentMainView.fxml"));
//        AnchorPane root = loader.load();
//        rootLayout.setCenter(root);


        
        else if (isUserFound == true && comboboxChoose.getSelectionModel().isSelected(1))
        {
            System.out.println("username: " + username.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JTeacherView.fxml"));
            Parent root = loader.load();

            stage = (Stage) anchorPane.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
        else if(isUserFound == true && comboboxChoose.getSelectionModel().isSelected(2))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/AdminView.fxml"));
            Parent root = loader.load();

            stage = (Stage) anchorPane.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("forkert et eller andet");
        }
    }

    @FXML
    private void exit(ActionEvent event) {

    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }



}
