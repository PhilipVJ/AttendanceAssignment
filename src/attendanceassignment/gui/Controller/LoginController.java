/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.Controller;

import attendanceassignment.gui.Model.AttendanceModel;
import attendanceassignment.gui.Controller.TeacherViewController;
import attendanceassignment.gui.Controller.StudentViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author Anders
 */
public class LoginController implements Initializable {
    

    
    
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * closes window
     */
    @FXML
    private void btnExit(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    /**
     * log in for teacher and student
     * opens windows depending on login
     * if login data is wrong it opens message
     */
    @FXML
    private void btnLogin(ActionEvent event) throws IOException {
        String name = txtUsername.getText();
        String password = txtPassword.getText();
        
        if(name.equalsIgnoreCase("1") && password.equalsIgnoreCase("1")){ 
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/StudentView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            StudentViewController svc = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
        else if(name.equalsIgnoreCase("2") && password.equalsIgnoreCase("2")){
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/TeacherView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            TeacherViewController twc = fxmlLoader.getController();
            twc.setUp(new AttendanceModel());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            }
        else{
                JOptionPane.showMessageDialog(null,"Please make sure your username and password are correct");
            }
        
    }
//    
    
}
