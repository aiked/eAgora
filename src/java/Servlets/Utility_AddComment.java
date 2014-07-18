
package Servlets;

import JavaBeans.Comment;
import JavaBeans.Member;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utility_AddComment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member"); 

        String name=member.getEmail();
        String comment=request.getParameter("Comment");
        int product=Integer.parseInt(request.getParameter("prdID"));
        
        Comment comt=new Comment(comment, name, product, -1);
        StoreToDatabase.StoreToBase_Comment(comt);
        
        rd = getServletContext().getRequestDispatcher("/Page_ViewProduct?prdID="+product);
        rd.forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
