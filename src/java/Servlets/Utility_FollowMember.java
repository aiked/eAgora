
package Servlets;

import JavaBeans.Member;
import Utilities.StoreToDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utility_FollowMember extends HttpServlet {

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
            out.println("<title>Servlet Utility_FollowMember</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Utility_FollowMember at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        HttpSession session = request.getSession();
        Member memberFollower = (Member)session.getAttribute("member");
        String emailToFollow= request.getParameter("EmailToFollow");
        
        if(memberFollower==null)
        {
            System.out.println("PageProfile.servlet: member:null");
            rd = getServletContext().getRequestDispatcher("/SignUp.jsp");
        }
        else
        {
            if(!memberFollower.getEmail().equals(emailToFollow))
            {
                System.out.println(memberFollower.getEmail());
                System.out.println(emailToFollow);
                
                int index = StoreToDatabase.GetFromBase_IsFollower(memberFollower.getEmail(), emailToFollow);
                
                if(index==-1)
                    StoreToDatabase.StoreToBase_SetFollower(memberFollower.getEmail(), emailToFollow);
                else
                    StoreToDatabase.DeletFromBase_Follower(memberFollower.getEmail(), emailToFollow);
                
            }
            rd = getServletContext().getRequestDispatcher("/Page_ViewProfile?emailToView="+emailToFollow);
        }
        rd.forward(request, response);
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
