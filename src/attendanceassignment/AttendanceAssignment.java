/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment;

import attendanceassignment.gui.AttModel.ExceptionHandler;
import attendanceassignment.gui.AttModel.Utility;
import attendanceassignment.gui.JController.JLoginController;
import attendanceassignment.gui.JController.RootlayoutController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Anders
 */
public class AttendanceAssignment extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Sets up the stage and scene
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/Rootlayout.fxml"));
            BorderPane rootLayout = loader.load();
            RootlayoutController rCon = loader.getController();

            SceneDragger sDragOne = new SceneDragger();
            Parent menuBar = rCon.getMenuBar();
            sDragOne.makeDraggable(menuBar, stage);

            Scene scene = new Scene(rootLayout);
            FXMLLoader loaderTwo = new FXMLLoader();
            loaderTwo.setLocation(AttendanceAssignment.class.getResource("/attendanceassignment/gui/JView/JLogin.fxml"));

            AnchorPane logIn = (AnchorPane) loaderTwo.load();
            JLoginController con = loaderTwo.getController();

            con.setRootLayout(rootLayout);
            rootLayout.setCenter(logIn);

            SceneDragger sDrag = new SceneDragger();
            sDrag.makeDraggable(rootLayout, stage);

            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
           ExceptionHandler.handleException(ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
