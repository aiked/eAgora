
package Servlets;

import JavaBeans.Advert;
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

public class Page_EditProduct extends HttpServlet {

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
            String prdID=request.getParameter("prdID");
            Advert product = StoreToDatabase.GetFromBase_AdvertOf(Integer.parseInt(prdID));
            product.setPhotos(StoreToDatabase.GetFromBase_AdvertPhoto(Integer.parseInt(prdID)));

            request.setAttribute("product",product);
            rd = getServletContext().getRequestDispatcher("/EditProduct.jsp");
        }
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
            Advert newAdvert = GetJspFields.getJspField_EditProduct(request);
            StoreToDatabase.UpdateBase_Advert(newAdvert);
            
            if((newAdvert.getPhotos()!=null)&&(!newAdvert.getPhotos().equals("null")))
                StoreToDatabase.StoreToBase_AdvertPhoto(newAdvert.getPhotos(),newAdvert.getId());

            request.setAttribute("product", newAdvert);
            rd = getServletContext().getRequestDispatcher("/EditProduct.jsp");
        }
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
