
package Servlets;

import JavaBeans.Advert;
import JavaBeans.Member;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Page_ViewProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member"); 
        
        String emailToView = request.getParameter("emailToView");
        System.out.println("Profile to view:"+emailToView);

        Member memberToView = StoreToDatabase.GetFromBase_member(emailToView);
        if (memberToView!=null)
        {
            ArrayList<Advert> adverts = StoreToDatabase.GetFromBase_AdvertsOf(emailToView);
            System.out.println("PageViewProfile.servlet: Adverts Loaded");
            ArrayList<Member> followers = StoreToDatabase.GetFromBase_Followers(emailToView);
            System.out.println("PageViewProfile.servlet: Followers Loaded");
            ArrayList<Member> following = StoreToDatabase.GetFromBase_Following(emailToView);
            System.out.println("PageViewProfile.servlet: Following Loaded");

            request.setAttribute("memberToViewStatus","LoadSuccess");
            request.setAttribute("memberToView",memberToView);
            request.setAttribute("Viewadverts", adverts);
            request.setAttribute("Viewfollowers", following);
            request.setAttribute("Viewfollowing", followers);

            rd = getServletContext().getRequestDispatcher("/ViewOtherProfile.jsp");
        }
        else
        {
            request.setAttribute("memberToViewStatus","LoadUnsuccess");
            rd = getServletContext().getRequestDispatcher("/ViewOtherProfile.jsp");
        }
        rd.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
