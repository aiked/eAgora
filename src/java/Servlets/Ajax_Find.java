/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import JavaBeans.Advert;
import JavaBeans.Member;
import Utilities.StoreToDatabase;
import java.util.ArrayList;
import javax.servlet.http.*;
import org.ajaxtags.helpers.*;
import org.ajaxtags.servlets.*;
/**
 *
 * @author Chrysohous
 */
public class Ajax_Find extends BaseAjaxServlet {
    

    @Override
    public String getXmlContent(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        String languagePrefix = hsr.getParameter("search");
        String searchPrefix = hsr.getParameter("SearchProduct");
        System.out.println("Ti kaneiiiiiiiiiis  "+ searchPrefix);
        if(searchPrefix!=null && !searchPrefix.equals(""))
        {
            return makeAdvList(languagePrefix, searchPrefix);
        }
        else
        {
            return makeMemList(languagePrefix);
        }

       
    }
    
    private String makeMemList(String languagePrefix)
    {
        AjaxXmlBuilder builder = new AjaxXmlBuilder();
        ArrayList<Member> members = StoreToDatabase.GetFromBase_AllMembers();
        
        if( members!=null && !members.isEmpty())
            for(Member mem: members)
            {
                if(mem.getFname().toUpperCase().startsWith(languagePrefix.toUpperCase()))
                {
                    builder.addItem(mem.getFname(), mem.getFname());
                }
            }
        
        return builder.toString();
    }

    private String makeAdvList(String languagePrefix, String mode)
    {
        AjaxXmlBuilder builder = new AjaxXmlBuilder();
        ArrayList<Advert> adverts = StoreToDatabase.GetFromBase_AllAdverts();
        
        if(adverts!=null && !adverts.isEmpty())
            for(Advert adv: adverts)
            {
                if( (mode.equals("All") || adv.getCategory().equals(mode) ) && adv.getTitle().toUpperCase().startsWith(languagePrefix.toUpperCase()))
                {
                    builder.addItem(adv.getTitle()+" - "+adv.getCategory(), adv.getTitle());
                }
            }
        
        return builder.toString();
    }

}
