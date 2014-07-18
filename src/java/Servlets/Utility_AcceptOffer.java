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

//import emailManager.EmailManager;
//import emailManager.MailerException;

/**
 *
 * @author Chrysohous
 */
public class Utility_AcceptOffer extends HttpServlet {

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
        
        if(member==null)
        {
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            int mode;
            String choice = request.getParameter("choice");
            int procId = Integer.parseInt( request.getParameter("TraceID") );
            TradeProcedure tradeProcedure = StoreToDatabase.GetFromBase_TradeProcedure(procId);
            System.out.println("CHOISE  "+ choice);
            if(choice.equals("true"))
            {
                mode=2;
                
                int result = tradeProcedure.getAdealer().getAvailability()-tradeProcedure.getAmmount();
                tradeProcedure.getAdealer().setAvailability(result<0?0:result);
                
                StoreToDatabase.UpdateBase_Advert(tradeProcedure.getAdealer());
                
                if(tradeProcedure.getAbuyer()!=tradeProcedure.getAdealer())
                {
                    int res = tradeProcedure.getAbuyer().getAvailability()-1;
                    tradeProcedure.getAbuyer().setAvailability(res<0?0:result);
                    
                    StoreToDatabase.UpdateBase_Advert(tradeProcedure.getAbuyer());
                }
                
                StoreToDatabase.StoreToBase_TradeMark(tradeProcedure, -1);               
                
                //EmailManager.sendMail(StoreToDatabase.getA..getMail.., "a subject here", "The body of the message here\nMore text here", "mailhost.csd.uoc.gr");
                //EmailManager.sendMail("mkabour@csd.uoc.gr", "a subject here", "The body of the message here\nMore text here", "mailhost.csd.uoc.gr");
            }
            else
            {
                mode=3;
            }

            StoreToDatabase.UpdateBase_UnableTradeProcedures(procId, mode);
            
            rd = getServletContext().getRequestDispatcher("/Page_Profile");
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
