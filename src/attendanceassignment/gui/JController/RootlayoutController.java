/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.AttendanceAssignment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class RootlayoutController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuBar menuBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AttendanceAssignment.class.getResource("/attendanceassignment/gui/JView/JLogin.fxml"));

        AnchorPane logIn = (AnchorPane) loader.load();
        JLoginController con = loader.getController();     
        con.setRootLayout(borderPane);
        borderPane.setCenter(logIn);
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void showAbout(ActionEvent event) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("Om");
        about.setContentText("Med det program kan man registrere sit fravær");
        about.showAndWait();
    }

    @FXML
    private void minimize(MouseEvent event) {
      Stage primaryStage = (Stage)borderPane.getScene().getWindow();
       primaryStage.setIconified(true);
    }

    @FXML
    private void closeWindows(MouseEvent event) {
        Platform.exit();
    }
    
    public Parent getMenuBar()
    {
        return menuBar;
    }



    
}
