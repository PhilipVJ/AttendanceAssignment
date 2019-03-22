/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceassignment.dal;

import java.io.IOException;

/**
 *
 * @author Philip
 */
public class UserDBFactory {



  // There will be a method for each DAO that can be 
  // created. The concrete factories will have to 
  // implement these methods.

  public static UserDB getUserDB() throws IOException
  {
      return new UserDB();
  }
}
