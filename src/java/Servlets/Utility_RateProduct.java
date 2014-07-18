
package Servlets;

import JavaBeans.Advert;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utility_RateProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        String productID = request.getParameter("prdID");
        String rating = request.getParameter("star2");
        //System.out.println("id"+productID+" rating:"+rating);
        
        Advert product = StoreToDatabase.GetFromBase_AdvertOf(Integer.parseInt(productID));
        product.setRating( (product.getRating()+Double.parseDouble(rating))/2 );
        StoreToDatabase.UpdateBase_Advert(product);
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
