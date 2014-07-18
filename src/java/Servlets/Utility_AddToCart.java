
package Servlets;

import JavaBeans.Advert;
import JavaBeans.Comment;
import JavaBeans.Member;
import JavaBeans.TradeProcedure;
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

public class Utility_AddToCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member"); 
        
        if(member==null) /* den to exw kanei akoma*/
        {
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            int advId = Integer.parseInt( request.getParameter("prdID") );
            
            ArrayList <Integer> ids = (ArrayList <Integer>) session.getAttribute("shopCart");
            
            if(ids==null)
            {
                ids = new ArrayList();
                session.setAttribute("shopCart", ids);
            }
            else    
            {
                boolean find=false;
                
                for(Integer x: ids )
                    if(x==advId)
                    {  
                        ids.remove(x);
                        find=true;
                        break;
                    }
                
                if(find==false)
                {
                    ids.add(advId);
                }
            }
            
            rd = getServletContext().getRequestDispatcher("/Page_ViewProduct?prdID="+advId);

        }
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
