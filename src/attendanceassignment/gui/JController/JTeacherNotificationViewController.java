/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Attendance;
import attendanceassignment.be.StudentNotification;
import attendanceassignment.gui.AttModel.AttendanceModel;
import attendanceassignment.gui.AttModel.Utility;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private JFXTreeTableView<StudentNotification> tableView;
    @FXML
    private TreeTableColumn<StudentNotification, String> firstNameCol;
    @FXML
    private TreeTableColumn<StudentNotification, String> lastNameCol;
    @FXML
    private TreeTableColumn<StudentNotification, String> absentDayCol;
    @FXML
    private TreeTableColumn<StudentNotification, String> classNameCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void back(ActionEvent event)
    {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/JView/JTeacherView.fxml"));
        AnchorPane root = loader.load();
        JTeacherViewController con = loader.getController();
        con.setModel(aModel);
        con.setRootLayout(rootLayout);
        con.setUser();
        con.loadViews();
        rootLayout.setCenter(root);
        } catch (IOException ex) {
            Utility.createErrorAlert("Database filen kunne ikke fines", "Sikre at filen er i den rette mappe og prøv igen");
        }
    }

    @FXML
    private void rejectRequest(ActionEvent event)
    {
        try {
        StudentNotification sn = tableView.getSelectionModel().getSelectedItem().getValue();
        if (sn == null)
        {
            Utility.createErrorAlert("Der er ikke valgt en elev", "Vælg venligt den elev du vil rette fravær på");
        } else
        {
            aModel.deleteNotificationRequests(sn.getStudentID(), sn.getAbsentDay());
            tableView.getRoot().getChildren().remove(tableView.getSelectionModel().getSelectedItem());
        }
        } catch (SQLException ex) {
            Utility.createErrorAlert("Programmet kan ikke få kontakt til serveren", "Prøv venligst igen senere eller kontakt support!");
        }
    }

    @FXML
    private void acceptRequest(ActionEvent event)
    {
        try {
        StudentNotification sn = tableView.getSelectionModel().getSelectedItem().getValue();

        if (sn == null)
        {
            Utility.createErrorAlert("Der er ikke valgt en elev", "Vælg venligt den elev du vil rette fravær på");
            return;
        }
        Attendance att = new Attendance(sn.getStudentID(), sn.getAbsentDay());
        aModel.acceptAttendance(att);
        tableView.getRoot().getChildren().remove(tableView.getSelectionModel().getSelectedItem());
        } catch (SQLException ex) {
            Utility.createErrorAlert("Programmet kan ikke få kontakt til serveren", "Prøv venligst igen senere eller kontakt support!");
        }
    }

    public void setModel(AttendanceModel model)
    {
        this.aModel = model;
    }

    public void setRootLayout(BorderPane toSet)
    {
        rootLayout = toSet;
    }

    public void loadTV()
    {
        ArrayList<StudentNotification> tvStudents = aModel.getNotifications();
        ObservableList<StudentNotification> students = FXCollections.observableArrayList(tvStudents);
        TreeItem<StudentNotification> root = new RecursiveTreeItem<>(students, RecursiveTreeObject::getChildren);

        firstNameCol.setSortable(false);
        lastNameCol.setSortable(false);
        classNameCol.setSortable(false);
        absentDayCol.setSortable(false);

        firstNameCol.setResizable(false);
        lastNameCol.setResizable(false);
        classNameCol.setResizable(false);
        absentDayCol.setResizable(false);

        absentDayCol.setSortType(TreeTableColumn.SortType.ASCENDING);

        firstNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<StudentNotification, String>("firstName"));
        lastNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<StudentNotification, String>("lastName"));
        classNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<StudentNotification, String>("className"));
        absentDayCol.setCellValueFactory(new TreeItemPropertyValueFactory<StudentNotification, String>("absentDay"));
        tableView.setShowRoot(false);
        tableView.setRoot(root);
     }

}
