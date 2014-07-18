
package Servlets;

import JavaBeans.Advert;
import JavaBeans.TradeMark;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utility_RateTrade extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        String tradeID = request.getParameter("tradeID");
        String rating = request.getParameter("rating");
        //System.out.println("id"+productID+" rating:"+rating);
        
        if(tradeID!=null && rating!=null)
        {
            TradeMark TradeMark = StoreToDatabase.GetFromBase_TradeMark(Integer.parseInt(tradeID));
            TradeMark.setMark(Double.parseDouble(rating));
            StoreToDatabase.UpdateBase_TradeMark(TradeMark);
  
        }
        else
        {
            
        }
        
        rd = getServletContext().getRequestDispatcher("/Page_Profile");
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
