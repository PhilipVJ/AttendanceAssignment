/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.gui.AttModel;

/**
 *
 * @author nikla
 */
public class ExceptionHandler
{

    public static void handleException(Exception ex)
    {
        switch (ex.getClass().getSimpleName())
        {
            case "SQLServerException":
                handleSqlServerException();
                break;
            case "SQLException":    
                handleSqlException();
                break;
            case "FileNotFoundException":
                handleIOException();
                break;
            default:
                handleUnknownError();
        }
    }
    
    private static void handleSqlServerException()
    {
        Utility.createErrorAlert("Der kan ikke oprettes forbindelse til serveren", "Kontakt support eller prøv igen senere!");
    }

    private static void handleSqlException()
    {
        Utility.createErrorAlert("Der er problemer i databasen", "Kontakt support eller prøv igen senere!");
    }

    private static void handleIOException()
    {
        Utility.createErrorAlert("Database filen kunne ikke findes", "Sikre at filen er i den rette mappe og prøv igen");
    }

    private static void handleUnknownError()
    {
        Utility.createErrorAlert("Ukendt fejl opstod", "Der opstod en ukendt fejl i systemet.");
    }
    
    
    
}
