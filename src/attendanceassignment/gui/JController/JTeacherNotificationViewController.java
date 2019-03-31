/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Student;
import attendanceassignment.gui.AttModel.AttendanceModel;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JTeacherNotificationViewController implements Initializable
{   
    
    private AttendanceModel aModel;
    private BorderPane rootLayout;
    
    @FXML
    private JFXTreeTableView<Student> tableView;
    private TreeTableColumn<Student, String> firstNameCol;
    @FXML
    private TreeTableColumn<Student, String> lastNameCol;
    @FXML
    private TreeTableColumn<Student, String> classNameCol;
    @FXML
    private TreeTableColumn<Student, String> absenceCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JTeacherView.fxml"));          
        AnchorPane root = loader.load();
        JTeacherViewController con = loader.getController();
        con.setModel(aModel);
        con.setRootLayout(rootLayout);
        con.setUser();   
        con.loadViews();
        rootLayout.setCenter(root);
    }

    @FXML
    private void rejectRequest(ActionEvent event){
    }

    @FXML
    private void acceptRequest(ActionEvent event){
    }
    public void setModel(AttendanceModel model) {
        this.aModel = model;
    }
    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }
//    public void loadTV() throws SQLException{
//        
//        ArrayList<Date> tvStudents = aModel.getStudentRequestForTeacher(0);
//            ObservableList<Student> students = FXCollections.observableArrayList(tvStudents);
//            TreeItem<Student> root = new RecursiveTreeItem<>(students, RecursiveTreeObject::getChildren);
//            tableView.setRoot(root);
//        
//        firstNameCol.setSortable(false);
//        lastNameCol.setSortable(false);
//        classNameCol.setSortable(false);
//        absenceCol.setSortable(false);
//
//        absenceCol.setSortType(TreeTableColumn.SortType.ASCENDING);
//
//        firstNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("firstName"));
//        lastNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("lastName"));
//        classNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("className"));
//        absenceCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("absence"));
//
//        tableView.setShowRoot(false);
//        tableView.getSortOrder().setAll(absenceCol);
//    
//    }
}
