/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.JController;

import attendanceassignment.be.Student;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class JTeacherViewController implements Initializable
{

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label userNameTag;
    @FXML
    private Label numberOfRequests;
    @FXML
    private JFXTreeTableView<Student> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

TreeTableColumn<Student, String> firstNameCol= new TreeTableColumn<Student, String>("First name");
TreeTableColumn<Student, String> lastNameCol= new TreeTableColumn<Student, String>("Last name");
tableView.getColumns().addAll(firstNameCol, lastNameCol);
Student test1 = new Student("Hans", "Jensen");
Student test2 = new Student("Kaj", "Hansen");

 ObservableList<Student> students = FXCollections.observableArrayList();
 students.add(test1);
 students.add(test2);

firstNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("firstName"));
lastNameCol.setCellValueFactory(new TreeItemPropertyValueFactory<Student, String>("lastName"));

 final TreeItem<Student> root = new RecursiveTreeItem<>(students, RecursiveTreeObject::getChildren);

tableView.setRoot(root);
tableView.setShowRoot(false);

    }

    @FXML
    private void back(ActionEvent event)
    {
    }

    @FXML
    private void showRequests(ActionEvent event)
    {
    }

    @FXML
    private void showDetails(ActionEvent event)
    {
    }

    @FXML
    private void chooseClass(ActionEvent event)
    {
    }

}
