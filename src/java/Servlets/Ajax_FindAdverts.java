/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import JavaBeans.Advert;
import Utilities.StoreToDatabase;
import java.util.ArrayList;
import javax.servlet.http.*;
import org.ajaxtags.helpers.*;
import org.ajaxtags.servlets.*;
/**
 *
 * @author Chrysohous
 */
public class Ajax_FindAdverts extends BaseAjaxServlet {
    

    @Override
    public String getXmlContent(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        String languagePrefix = hsr.getParameter("search");
        String languageList = makeLanguageList(languagePrefix);
        return languageList;
    }

    private String makeLanguageList(String languagePrefix)
    {
        AjaxXmlBuilder builder = new AjaxXmlBuilder();
        ArrayList<Advert> adverts = StoreToDatabase.GetFromBase_AllAdverts();
        
        if(adverts!=null && !adverts.isEmpty())
            for(Advert adv: adverts)
            {
                if(adv.getTitle().toUpperCase().startsWith(languagePrefix.toUpperCase()))
                {
                    builder.addItem(adv.getTitle()+" - "+adv.getCategory(), adv.getTitle());
                }
            }
        
        return builder.toString();
    }

}
