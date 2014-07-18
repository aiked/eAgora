/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import JavaBeans.Advert;
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

/**
 *
 * @author Chrysohous
 */
public class Utility_MakeOffer extends HttpServlet {

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
        
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member"); 
        
        if(member==null) /* douleuei mono gia apli prosfora*/
        {
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {   /*  TO kanw mono gia sketo buy ara advdealer==advbuyer */ 

            String comment = request.getParameter("BuyComment");
            int advid = Integer.parseInt(request.getParameter("prdID"));
            int amount = Integer.parseInt(request.getParameter("BuyNowQua"));
            String mode = request.getParameter("OfferAd");
            System.out.println("TO MODE EINAI "+mode);
            Advert advDealer = StoreToDatabase.GetFromBase_AdvertOf(advid);
            Member memDealer = StoreToDatabase.GetFromBase_member(advDealer.getOwner());
            Member memBuyer = StoreToDatabase.GetFromBase_member(member.getEmail());
            Advert advBuyer;
            
            if(mode!=null && !mode.equals("NoOffer"))
            {
                advBuyer = StoreToDatabase.GetFromBase_AdvertOf(Integer.parseInt(mode));
            }
            else
            {
                advBuyer = advDealer;
            }
            
            TradeProcedure tradeProcedure = new TradeProcedure(memBuyer, memDealer, advDealer, advBuyer, amount, -1, 1, comment);
            StoreToDatabase.StoreToBase_TradeProcedure(tradeProcedure);
            System.out.println("Utility_MakeOffer done");
            rd = getServletContext().getRequestDispatcher("/Page_ViewProduct?prdID=" + advid);
        }
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
