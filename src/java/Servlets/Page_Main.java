/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import JavaBeans.Advert;
import JavaBeans.Member;
import Utilities.CreateDataBase;
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

/**
 *
 * @author o2
 */
public class Page_Main extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CreateDataBase.CheckAndCreateBase();
        
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member");

        ArrayList<Advert> adverts = StoreToDatabase.GetFromBase_AllAdverts();
        ArrayList<String> categories = StoreToDatabase.GetFromBase_Categories();
        ArrayList<Member> members = StoreToDatabase.GetFromBase_AllMembers();
        ArrayList<Member> famousmem = StoreToDatabase.GetFromBase_MostFamousMembersByAdverts();
        ArrayList<Member> markmem = StoreToDatabase.GetFromBase_MostFamousMembersByMark();
        ArrayList<Advert> mostadv = StoreToDatabase.GetFromBase_MostVisitedAdverts();

        request.setAttribute("members", members);
        request.setAttribute("adverts", adverts);
        request.setAttribute("categories", categories);
        
        request.setAttribute("famousmem", famousmem);
        request.setAttribute("markmem", markmem);
        request.setAttribute("mostadv", mostadv);

        rd = getServletContext().getRequestDispatcher("/index.jsp");
        
        
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
