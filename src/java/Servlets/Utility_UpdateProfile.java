
package Servlets;

import JavaBeans.Advert;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utility_UpdateProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Utility_UpdateProfile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Utility_UpdateProfile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println("Update profile adverts:"+email);
        ArrayList <Advert> foladverts = StoreToDatabase.GetFromBase_AdvertsOfFollowingOf(email);
        int foladvertsSize=0;
        String folHTML="";
        String coin="";
        String imagePath;
        String[] adPhoto;
        String adLink="http://localhost:8080/Project/Images/product-icon.png";
        
        if(foladverts.size()>10)
            foladvertsSize=10;
        else
            foladvertsSize=foladverts.size();
        
        for(int y=0;y<foladverts.size();y++)
        {
            if(foladverts.get(y).getAvailability()>0)
            {
                adPhoto=foladverts.get(y).getPhotos().split(",");
                adLink="http://localhost:8080/Project/Images/product-icon.png";

                if(foladverts.get(y).getCoin().equals("euro")){ coin="&#128;"; }
                else if(foladverts.get(y).getCoin().equals("dollar")){ coin="&#36;"; }
                else if(foladverts.get(y).getCoin().equals("pound")){ coin="&#163;"; } 

                for(int i=0;i<adPhoto.length;i++)
                {
                    if(!(adPhoto[i].equals(""))&&!(adPhoto[i].equals("null")))
                    {
                        adLink="http://localhost:8080/Project/ProductFotos/"+adPhoto[i];
                        break;
                    }

                }
                folHTML += "<div class='LeftSideAd'><table class='RightAdTable'>";
                folHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
                folHTML += "<tr><td>title:</td><td>"+foladverts.get(y).getTitle()+"</td></tr>";
                folHTML += "<tr><td>price:</td><td>"+foladverts.get(y).getPrice()+" "+coin+"</td></tr>";
                folHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+foladverts.get(y).getId()+"'>view more</a></td>";
                folHTML += "<td><a class='navBarLinks' href='./Utility_DeleteAdvert?prdID="+foladverts.get(y).getId()+"'>delete</a></td>";
                folHTML += "</tr></table></div>";
            }
        }
        
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(folHTML);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
