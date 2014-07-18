package ValidationFunc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ValidateForm</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Error: doGet is not available on this servlet.");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailCheck = request.getParameter("emailCheck");

        if ( emailCheck!=null )
        {
            Connection Con;
            Statement stmt;
            boolean find = false;
            try 
            {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
                stmt = Con.createStatement();
                String str = "select * from MEMBERS where email='" + emailCheck + "'";
                ResultSet rs = stmt.executeQuery(str);
                if (rs.next()) {
                    find = true;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ValidateForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ValidateForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            PrintWriter out = response.getWriter();
            String answer = null;
            if ( find==true ) { answer = "found"; }
            else { answer = "notfound"; }
            out.print(answer);
            
        }

    }
    


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
