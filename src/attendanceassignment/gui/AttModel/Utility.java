/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

import javafx.scene.control.Alert;

/**
 *
 * @author Philip
 */
public class Utility {

    public static void createErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
