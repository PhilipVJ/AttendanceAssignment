/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nikla
 */
public class AdminViewController implements Initializable {

    private Stage stage;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton btnBack;

    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JLogin.fxml"));
                Parent root = loader.load();
                
                stage = (Stage) anchorPane.getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
