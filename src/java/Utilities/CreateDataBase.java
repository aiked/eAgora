/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import JavaBeans.Member;
import JavaBeans.TradeMark;
import Servlets.Form_SignUp;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chrysohous
 */
public class CreateDataBase {
    
    public static void CheckAndCreateBase()
    {
        Connection Con;
        Statement stmt;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby:TIKANEIS;create=true", "root", "root"); 
            stmt = Con.createStatement();

            String str = "select * from TRADE_MARK";
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            }

            
            //.executeUpdate("");
            
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("here");
        }
        System.out.println("ok");
    }
    
    
}
