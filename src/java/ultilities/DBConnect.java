/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class DBConnect {    
    public static Connection makeConnection() {
        try {
            //JBDC type 4 Native Pottocol
            
            //1. khai bao Driver dung de ket noi CSDL
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //2. Tao doi tuong ket noi CSDL gom ê parameters: url, username,  
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1502_Assignment_Group09";
            String username="sa";
            String password="123456";
            Connection c = DriverManager.getConnection(url, username, password);
            
            return c;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
