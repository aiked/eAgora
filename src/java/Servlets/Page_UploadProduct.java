
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

public class Page_UploadProduct extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Page_UploadProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Page upload product: GET is NOT Available.");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member");

        if(member==null)
        {
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            Advert advert = GetJspFields.getJspField_Product(request);
            advert.setOwner(member.getEmail());
            System.out.println(advert.toString());
            StoreToDatabase.StoreToBase_Advert(advert);
            
            Advert tmp = StoreToDatabase.GetFromBase_AdvertOf(-1);
            System.out.println("bLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"+advert.getPhotos());
            if((advert.getPhotos()!=null)&&(!advert.getPhotos().equals("null")))
                StoreToDatabase.StoreToBase_AdvertPhoto(advert.getPhotos(),tmp.getId());
            
            rd = getServletContext().getRequestDispatcher("/Page_ViewProduct?prdID="+tmp.getId());
        }
        
        rd.forward(request, response);
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
