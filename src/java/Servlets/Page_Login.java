
package Servlets;

import JavaBeans.Member;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Page_Login extends HttpServlet {
    
//    @Override
//    public void init(ServletConfig config)
//	throws ServletException
//    {
//	super.init(config);  
//
//        Connection Con;
//        Statement stmt;
//        int find=-1;
//        
//        try {
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
//            stmt = Con.createStatement();
//            
//            String str ="select * from SUBSCRIPE_FOLLOWERS where EMAILMEMBER='"+ from +"' AND EMAILFOLLOWER='"+ to +"'";
//            ResultSet rs = stmt.executeQuery(str);
//            if (rs.next()) {   
//                  find=rs.getInt(3);                      
//            }
//            stmt.close();
//            Con.close();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return find; 
//
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Page_Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Method GET is not available.Page_Login</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        HttpSession session = request.getSession(true);
        String email = request.getParameter("LoginEmail");
        String password = request.getParameter("LoginPass");
        
        if ( (email==null)||(password==null))
        {
            System.out.println("Login failed.email||pwd null.");
            session.setAttribute("LoginInfo","Login failed.Email or password was empty.");
            rd = getServletContext().getRequestDispatcher("/Login.jsp");
        }
        else
        {
            Member member = StoreToDatabase.GetFromBase_member(email);
            if( (member!=null)&&(password.equals(member.getPassword())) )
            {
                session.setAttribute("member", member);
                session.setAttribute("LoginInfo","LoginSuccess");
                ArrayList <Integer> ids = new ArrayList();
                session.setAttribute("shopCart", ids);
                
                System.out.println("Http Login success.Created Session:"+session.getId());
                rd = getServletContext().getRequestDispatcher("/Page_Profile");
            }
            else
            {
                System.out.println("Login failed.Member not found.");
                session.setAttribute("LoginInfo","Login failed.Invalid email or password.");
                rd = getServletContext().getRequestDispatcher("/Login.jsp");
            }
        }
        rd.forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
