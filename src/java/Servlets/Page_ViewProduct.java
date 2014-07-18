
package Servlets;

import JavaBeans.*;
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

public class Page_ViewProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member"); 
        
        String prdID=request.getParameter("prdID");
        if(member==null)
        {
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            int advid = Integer.parseInt( prdID );
            
            Advert product = StoreToDatabase.GetFromBase_AdvertOf(advid);
            product.setPhotos(StoreToDatabase.GetFromBase_AdvertPhoto(advid));
            ArrayList<Comment> comments = StoreToDatabase.GetFromBase_Comments(advid);
            ArrayList<Advert> loggedUserAds = StoreToDatabase.GetFromBase_AdvertsOf(member.getEmail());
            
            product.setVisitors(product.getVisitors()+1);
            StoreToDatabase.UpdateBase_Advert(product);
            
            request.setAttribute("loggedUserAds",loggedUserAds);
            request.setAttribute("product",product);
            request.setAttribute("comments", comments);
            rd = getServletContext().getRequestDispatcher("/ViewProduct.jsp");
        }

        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
