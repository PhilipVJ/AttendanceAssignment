/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Student;
import attendanceassignment.gui.AttModel.AttendanceModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class JTeacherViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label userNameTag;
    @FXML
    private Label numberOfRequests;
    @FXML
    private JFXTreeTableView<Student> tableView;

    private AttendanceModel aModel;
    private BorderPane rootLayout;
    @FXML
    private JFXComboBox<String> classChooser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void showRequests(ActionEvent event) {
    }

    @FXML
    private void showDetails(ActionEvent event) {
    }

    public void setModel(AttendanceModel model) {
        this.aModel = model;
    }

    public void setUser() {
        userNameTag.setText(aModel.getUser().getFirstname());
    }

    public void setRootLayout(BorderPane toSet) {
        rootLayout = toSet;
    }

    public void loadViews() {
        // Loads the combobox with all the teachers classes
        ObservableList<String> allClasses = FXCollections.observableArrayList(aModel.getUser().getAllClasses());
        classChooser.setItems(allClasses);

        // Setup tableview
        TreeTableColumn<Student, String> firstNameCol = new TreeTableColumn<Student, String>("First name");
        TreeTableColumn<Student, String> lastNameCol = new TreeTableColumn<Student, String>("Last name");
        TreeTableColumn<Student, String> classNameCol = new TreeTableColumn<Student, String>("Class name");
        firstNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("firstName"));
        lastNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("lastName"));
        classNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("className"));
        tableView.getColumns().addAll(firstNameCol, lastNameCol, classNameCol);

        tableView.setShowRoot(false);

    }

    @FXML
    private void chooseClass(ActionEvent event) {
        String className = classChooser.getValue();
        try {
            ArrayList<Student> classStudents = aModel.getClassStudents(className);
            System.out.println(""+classStudents.size());
            ObservableList<Student> students = FXCollections.observableArrayList(classStudents);
            TreeItem<Student> root = new RecursiveTreeItem<>(students, RecursiveTreeObject::getChildren);
            tableView.setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(JTeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
