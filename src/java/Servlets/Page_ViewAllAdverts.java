
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

public class Page_ViewAllAdverts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        RequestDispatcher rd;
        ArrayList<Advert> adverts = StoreToDatabase.GetFromBase_AllAdverts();
        
        ArrayList<Advert> chooseAdv= new ArrayList();
        String adv = request.getParameter("search");
        if(adv!=null)
        for(Advert x:adverts)
        {
            if(x.getTitle().toUpperCase().startsWith(adv.toUpperCase()))
            {
                chooseAdv.add(x);
            }
        }
        
        request.setAttribute("adverts", chooseAdv.isEmpty()?adverts:chooseAdv);
        rd = getServletContext().getRequestDispatcher("/ViewAllAdverts.jsp");
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
