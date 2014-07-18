
package Servlets;

import JavaBeans.Advert;
import JavaBeans.Comment;
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

public class Page_ViewCart extends HttpServlet {

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
            ArrayList <Integer> ids = (ArrayList <Integer>) session.getAttribute("shopCart");
            ArrayList <Advert> adverts=new ArrayList();
            ArrayList<Advert> loggedUserAds = StoreToDatabase.GetFromBase_AdvertsOf(member.getEmail());
            
            if(ids!=null && !ids.isEmpty())
            {
                for(Integer x: ids)
                {
                    Advert AdvertOf = StoreToDatabase.GetFromBase_AdvertOf(x);
                    adverts.add(AdvertOf);
                }
            }
            System.out.println("Cart number is :"+adverts.size());
            for(int i=0;i<adverts.size();i++)
                System.out.println(adverts.get(i).getTitle());
            request.setAttribute("carts", adverts);
            request.setAttribute("loggedUserAds",loggedUserAds);
            rd = getServletContext().getRequestDispatcher("/ViewCart.jsp");
        }

        rd.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
