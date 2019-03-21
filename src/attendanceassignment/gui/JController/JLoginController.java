/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.User;
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
import javafx.scene.control.Alert;
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

        try {
            atModel = new AttendanceModel();
        } catch (IOException ex) {
            Logger.getLogger(JLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {

        User user = atModel.login(username.getText(), password.getText());
        if (user==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Login fejlede");
            alert.setContentText("Brugernavn eller adgangskode er ugyldigt. Prøv igen.");
            alert.showAndWait();
            return;
            // der skal gøres noget mere her
        }
        if (user.getType().equals("Teacher")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JTeacherView.fxml"));          
            AnchorPane root = loader.load();
            JTeacherViewController con = loader.getController();
            con.setModel(atModel);
            con.setUser();         
            rootLayout.setCenter(root);
        }
        
        if (user.getType().equals("Student")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JStudentMainView.fxml"));
            AnchorPane root = loader.load();
            JStudentMainViewController con = loader.getController();
            con.setModel(atModel);
            con.setUser();
            rootLayout.setCenter(root);
        }

    }

    @FXML
    private void exit(ActionEvent event) {

    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

}
