package Servlets;

import JavaBeans.Member;
import Utilities.GetJspFields;
import Utilities.StoreToDatabase;
import com.oreilly.servlet.MultipartRequest;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class Form_SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Form_SignUp</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Error: doGet is not available on this servlet --Form_SignUp.");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Member member = GetJspFields.getJspField_Member(request);
        System.out.println(member.toString());
        StoreToDatabase.StoreToBase_Member(member);

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/Login.jsp");
        rd.forward(request, response);
    }
    
 
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
