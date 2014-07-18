/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import JavaBeans.Advert;
import JavaBeans.Member;
import JavaBeans.TradeMark;
import JavaBeans.TradeProcedure;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
public class Page_Profile extends HttpServlet {

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
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member member=(Member) session.getAttribute("member");
        if(member==null)
        {
            System.out.println("PageProfile.servlet: member:null");
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            ArrayList<Advert> adverts = StoreToDatabase.GetFromBase_AdvertsOf(member.getEmail());
            System.out.println("PageViewProfile.servlet: Adverts Loaded");
            ArrayList<Member> followers = StoreToDatabase.GetFromBase_Followers(member.getEmail());
            System.out.println("PageViewProfile.servlet: Followers Loaded");
            ArrayList<Member> following = StoreToDatabase.GetFromBase_Following(member.getEmail());
            System.out.println("PageViewProfile.servlet: Following Loaded");
            ArrayList <TradeProcedure> buyProceres = StoreToDatabase.GetFromBase_BuyerTradeProcedures(member.getEmail());
            System.out.println("PageVieduwProfile.servlet: tradeProcedures Loaded");
            ArrayList <TradeProcedure> dealeProcedusres = StoreToDatabase.GetFromBase_DealerTradeProcedures(member.getEmail());
            ArrayList <Advert> followingAdverts = StoreToDatabase.GetFromBase_AdvertsOfFollowingOf(member.getEmail());

        ArrayList <String> infos = new ArrayList();
        
        Iterator<TradeProcedure> iterbuy = dealeProcedusres.iterator();
        Iterator<TradeProcedure> iterdeale = buyProceres.iterator();
        Iterator<Member> iterfollowers = followers.iterator();
        Iterator<Member> iterfollowing = following.iterator();
        int counter=0;
        while(iterbuy.hasNext() || iterdeale.hasNext() || iterfollowers.hasNext() || iterfollowing.hasNext() ||(counter<6))
        {
            String tmp = "";
            counter++;
            if(iterbuy.hasNext())
            {
                TradeProcedure next = iterbuy.next();
                if(next.getEnable()==1)
                {
                    if(next.getAdealer().getId()==next.getAbuyer().getId()) //buy now
                    {
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td colspan='2'>"
                            +"<a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                            +" wants to buy "+next.getAmmount()+" <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td></tr>"
                            +" <tr><td><a class='followLink' href='./Utility_AcceptOffer?TraceID="+next.getId()+"&choice=true'>Accept</a>"
                            + "</td><td><a class='followLink' href='./Utility_AcceptOffer?TraceID="+next.getId()+"&choice=false'>Deny</a></td>"
                            +"</tr></table></div>";
                    }
                    else // offer
                    {
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td colspan='2'>"
                            +"<a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                            +" wants to buy  "+next.getAmmount()+" <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                            + " with an exchange of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td></tr>"
                            +" <tr><td><a class='followLink' href='./Utility_AcceptOffer?TraceID="+next.getId()+"&choice=true'>Accept</a>"
                            + "</td><td><a class='followLink' href='./Utility_AcceptOffer?TraceID="+next.getId()+"&choice=false'>Deny</a></td>"
                            +"</tr></table></div>";
                    }
                }
                else if(next.getEnable()==2) 
                {
                    TradeMark mark = StoreToDatabase.GetFromBase_TradeMark(next.getId());
                    
                   
                    if(next.getAdealer().getId()==next.getAbuyer().getId())
                    {
                        
                        if(mark.getMark()==-1)
                        {
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td>you have accepted offer of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="
                                +next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                                + " for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td>"
                                +"</tr></table></div>" 
                                + "you are waiting your trade mark"; //FTIAKSE TA ANIGOKLISMATA
                        }
                        else
                        {
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td>you have accepted offer of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="
                                +next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                                + " for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td>"
                                +"</tr></table></div>" 
                                + "your mark for this trade is "+mark.getMark(); //FTIAKSE TA ANIGOKLISMATA
                        }
                    }
                    else
                    {
                        if(mark.getMark()==-1)
                        {
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td>you have accepted offer of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                                + " for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                                + " with an exchange of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td></tr>"
                                +"</tr></table></div>"
                                + "you are waiting your trade mark"; //FTIAKSE TA ANIGOKLISMATA
                        }
                        else
                        {
                            
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td>you have accepted offer of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                                + " for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                                + " with an exchange of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td></tr>"
                                +"</tr></table></div>"
                                + "your mark for this trade is "+mark.getMark(); //FTIAKSE TA ANIGOKLISMATA
                        }
                    }
                }
                else
                {
                    if(next.getAdealer().getId()==next.getAbuyer().getId())
                    {
                       tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td>you have refused offer of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                            + " for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td>"
                            +"</tr></table></div>";
                    }
                    else
                    {
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td>you have refused offer of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMbuyer().getEmail()+"'>"+next.getMbuyer().getFname()+"</a>"
                            + " for "+next.getAmmount()+"  <a class='followLink'  href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                            + " with an exchange of <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a></td></tr>"
                            +"</tr></table></div>";
                    }
                }
                infos.add(tmp);
            }
            
            if(iterdeale.hasNext())
            {
                TradeProcedure next = iterdeale.next();
                
               
                if(next.getEnable()==1)
                {
                    if(next.getAdealer().getId()==next.getAbuyer().getId())
                    {
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td>you made an offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                            +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a>"
                            + "</td></tr></table></div>";
                    }
                    else
                    {
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td>you made an offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                            +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                            +" with an exange with <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a> "
                            + "</td></tr></table></div>";
                    }
                }
                else if(next.getEnable()==2)
                {
                    TradeMark mark = StoreToDatabase.GetFromBase_TradeMark(next.getId());
                    
                    if(next.getAdealer().getId()==next.getAbuyer().getId())
                    {
                        tmp="";                   
                        if(mark.getMark()==-1)
                        {
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td colspan='2'>your offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                                +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a> was accepted</td></tr>"
                                + "<tr><td>Rate dealer:</td>"
                                + "<td><form action='./Utility_RateTrade'>"
                                + "<input type='hidden' name='tradeID' value='"+next.getId()+"'>"
                                + "<input type='radio' name='rating' value='0.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='1' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='1.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='2' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='2.5' class='star {split:2}' checked>"
                                + "<input type='radio' name='rating' value='3' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='3.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='4' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='4.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='5' class='star {split:2}'></td></tr>"
                                + "<tr><td colspan='2' style='text-align:center'><input type='submit' value='send rating' /></form></td></tr>"  
                                + "</table></div>";
                        }
                        else
                        {
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td colspan='2'>your offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                                +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a> was accepted</td></tr>"
                                + " you rated the trade with "+ mark.getMark()+"</td></tr></table></div>";
                        }
                    }
                    else
                    {
                        if(mark.getMark()==-1)
                        {
                            tmp="";
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td colspan='2'>your offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                                +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                                +" with an exange with <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a> was accepted</td></tr>"
                                + "<tr><td>Rate dealer:</td>"
                                + "<td><form action='./Utility_RateTrade'>"
                                + "<input type='hidden' name='tradeID' value='"+next.getId()+"'>"
                                + "<input type='radio' name='rating' value='0.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='1' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='1.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='2' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='2.5' class='star {split:2}' checked>"
                                + "<input type='radio' name='rating' value='3' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='3.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='4' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='4.5' class='star {split:2}'>"
                                + "<input type='radio' name='rating' value='5' class='star {split:2}'></td></tr>"
                                + "<tr><td colspan='2' style='text-align:center'><input type='submit' value='send rating' /></form></td></tr>"  
                                + "</table></div>";
                        }
                        else
                        {
                            tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                                +"<tr><td colspan='2'>your offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                                +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                                +" with an exange with <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a> was accepted"
                                + " you rated the trade with "+ mark.getMark()+"</td></tr></table></div>";
                        }
                    }
                }
                else
                {
                    if(next.getAdealer().getId()==next.getAbuyer().getId())
                    {
                        
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td>your offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                            +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a> was refused"
                            + "</td></tr></table></div>";
                    }
                    else
                    {
                        tmp="<div class='BuyNowNotification'><table class='BuyNowNotificationTable'>"
                            +"<tr><td>your offer to <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getMdealer().getEmail()+"'>"+next.getMdealer().getFname()+"</a>"
                            +" for "+next.getAmmount()+"  <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAdealer().getId()+"'>"+next.getAdealer().getTitle()+"</a>"
                            +" with an exange with <a class='followLink' href='http://localhost:8080/Project/Page_ViewProduct?prdID="+next.getAbuyer().getId()+"'>"+next.getAbuyer().getTitle()+"</a> was refused"
                            + "</td></tr></table></div>";
                    }
                }
                
                infos.add(tmp);
            }
                
            if(iterfollowers.hasNext())
            {
                Member next = iterfollowers.next();
                
                tmp = "<div class='FollowNotification'>"
                        +"<a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getEmail()+"'>"+next.getFname()+"</a> follows you</div>";
                infos.add(tmp);
            }
            
            if(iterfollowing.hasNext())
            {
                Member next = iterfollowing.next();
                
                tmp = "<div class='FollowNotification'>"
                        +"now you follow <a class='followLink' href='http://localhost:8080/Project/Page_ViewProfile?emailToView="+next.getEmail()+"'>"+next.getFname()+"</a></div>";
                infos.add(tmp);
            }
        } 
  
            request.setAttribute("adverts", adverts);
            request.setAttribute("followers", following);
            request.setAttribute("following", followers);
            request.setAttribute("buyProcedures", buyProceres);
            request.setAttribute("dealeProcedusres", dealeProcedusres);
            request.setAttribute("infos", infos);
            request.setAttribute("followingAdverts", followingAdverts);
            rd = getServletContext().getRequestDispatcher("/Profile.jsp");
        }
        rd.forward(request, response);
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
        doGet(request, response);
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
