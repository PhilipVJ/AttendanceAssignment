/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.Controller;

import attendanceassignment.be.Student;
import attendanceassignment.gui.AttModel.AttendanceModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Andersddd
 */
public class StudentViewController implements Initializable {
    
    private AttendanceModel m_attendanceModel;

    String sick = "Sick"; 
    
    String reason;
    
    @FXML
    private JFXComboBox<String> comboReasons;
    @FXML
    private JFXTextField txtReasons;
    @FXML
    private Label lblJoke;
    @FXML
    private Label lblDate;
    @FXML
    private TableView<Student> TvAbsentDays;
    @FXML
    private TableColumn<Student, String> colDays;
    @FXML
    private Label lblSetPresent;

    /**
     * Initializes the controller class.
     * and initializes tableview + date label. 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showDate();
//        colDays.setCellValueFactory(
//                new PropertyValueFactory("absentDay"));
        colDays.setCellValueFactory(
        Produit -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Date date = Produit.getValue().getAbsentDay();
            SimpleDateFormat s = new SimpleDateFormat("dd/MM-yyyy");
            property.setValue(s.format(date));
            return property;
            }
        );
        absentTV();
        comboReasons.setItems(comBox());
    }    
    /**
     * button sets "present"
     */
    @FXML
    private void btnSubmit(ActionEvent event) {
        lblSetPresent.setText("Present");
    }
    /**
     * absence tableview
     */
    private void absentTV(){
        ObservableList<Student> student = m_attendanceModel.getAbsentTableview();
        
        TvAbsentDays.setItems(student);
    }
    /**
     * opens Overview window + builds barchard on initialize
     */
    @FXML
    private void btnOverview(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/Overview.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        OverviewController ow = fxmlLoader1.getController();
        ow.setUp(new AttendanceModel());
//        ow.buildTableView();
//            ow.setUp(m_attendanceModel);
        ow.buildBarchart();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    /**
     * ensures that a date in tableview is selected
     * "sends" notification to teacher
     */
    @FXML
    private void btnEdit(ActionEvent event) throws IOException {
        Student selectedStudent = TvAbsentDays.getSelectionModel().getSelectedItem();
        
        if(selectedStudent != null)
        {
            System.out.println(comboReasons.getSelectionModel().getSelectedIndex());
            System.out.println(txtReasons.getText());
            
            JOptionPane.showMessageDialog(null,"A notificated has been sent to your teacher, please wait for Confirmation");
            
        }else{
            JOptionPane.showMessageDialog(null,"Please select a date");
        }
    }
    
    /**
     * closes the window
     */
    @FXML
    private void btnClose(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendanceassignment/gui/View/Login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        LoginController lgc = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    /**
     * creates the date format and puts it into the label
     */
    private void showDate(){
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM-yyyy");
        lblDate.setText(s.format(date));
    }
    
    /**
     * ensures that the controller can call methods from model
     */
    public void setUp(AttendanceModel attendanceModel) {
        m_attendanceModel = attendanceModel;
    }
    
    public ObservableList comBox()
    {
        ObservableList<String> abscens = FXCollections.observableArrayList(
            "Sick",
            "Doctor appointment",
            "Too late",
            "Kids sick",
            "Other");
        return abscens;
    }
      
    @FXML
    void handleCombBox(ActionEvent event) {
        combTextSwitch();
    }
    
    public void combTextSwitch()
    {
        int selectedReason = comboReasons.getSelectionModel().getSelectedIndex();
        
        String reason; 
        
        switch(selectedReason){
            case 0:
                reason = "Sick";
                System.out.println(reason);
                break;
            case 1:
                reason = "Doctor appointment";
                System.out.println(reason);
                break;
            case 2:
                reason = "Slacker";
                System.out.println(reason);
                break;
            case 3:
                reason = "Kids sick";
                System.out.println(reason);
                break;
            case 4:
                reason = txtReasons.getText();
                System.out.println(reason);
                break;
        }
    }
    
    public void todaysJoke()
    {
        ObservableList<String> jokes = FXCollections.observableArrayList(
                "How do you make holy water? You boil the hell out of it",
                "Why did the policeman smell bad? He was on duty.",
                "How do you make a hormone? Don't pay her!",
                "What's brown and sticky? a stick!");
        
    }
    
}
