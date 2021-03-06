/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment;

import attendanceassignment.gui.AttModel.ExceptionHandler;
import attendanceassignment.gui.AttModel.LoaderFactory;
import attendanceassignment.gui.AttModel.ViewEnum;
import attendanceassignment.gui.JController.JLoginController;
import attendanceassignment.gui.JController.RootlayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
            LoaderFactory factory = LoaderFactory.getInstance();

            FXMLLoader loader = factory.createFXMLLoader(ViewEnum.Rootlayout);
            BorderPane rootLayout = loader.load();
            RootlayoutController rCon = loader.getController();

            SceneDragger sDragOne = new SceneDragger();
            Node menuBar = rCon.getMenuBar();
            sDragOne.makeDraggable(menuBar, stage);

            Scene scene = new Scene(rootLayout);
            FXMLLoader loaderTwo = factory.createFXMLLoader(ViewEnum.JLogin);

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
