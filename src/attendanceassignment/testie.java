/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment;

import attendanceassignment.dal.AbscensData;
import attendanceassignment.gui.AttModel.Utility;
import attendanceassignment.gui.JController.JStudentDaysStatestikController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Anders
 */
public class testie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        
        boolean checkNetwork = Utility.checkNetwork();
        boolean check2 = Utility.checkNetworkOnlyByName();
        
            
    }
    
}
