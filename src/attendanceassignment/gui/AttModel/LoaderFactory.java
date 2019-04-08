/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author Philip
 */
public class LoaderFactory {
    
    private static LoaderFactory factory = null;
    
    private LoaderFactory()
    {
    
    }

    public FXMLLoader createFXMLLoader(ViewEnum fxmlName) {

        String filePath = "/attendanceassignment/gui/JView/";
        String extension = ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath + fxmlName + extension));
        return loader;
    }
    
    public static LoaderFactory getInstance()
    {
        if(factory==null)
        {
            factory = new LoaderFactory();
        }
        return factory;
    }
}
