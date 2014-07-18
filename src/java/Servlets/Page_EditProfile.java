
package Servlets;

import JavaBeans.Member;
import Utilities.GetJspFields;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Page_EditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member");
        
        if(member==null)
        {
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            rd = getServletContext().getRequestDispatcher("/EditProfile.jsp");
        }
        rd.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        RequestDispatcher rd;
        
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("member");
        
        if(member==null)
        {
            System.out.println("Edit_profile_go_to_signup2");
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            Member memberTMP = GetJspFields.getJspFieldEditProfile_Member(request, member);
            StoreToDatabase.UpdateBase_member(memberTMP);
            session.setAttribute("member", memberTMP);
            rd = getServletContext().getRequestDispatcher("/EditProfile.jsp");
        }     

       rd.forward(request, response);
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
