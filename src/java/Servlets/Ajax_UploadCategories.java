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
public class Ajax_UploadCategories extends BaseAjaxServlet {
    

    @Override
    public String getXmlContent(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        String languagePrefix = hsr.getParameter("prdCategory");
        String languageList = makeLanguageList(languagePrefix);
        return languageList;
    }

    private String makeLanguageList(String languagePrefix)
    {
        AjaxXmlBuilder builder = new AjaxXmlBuilder();
        ArrayList<String> categories = StoreToDatabase.GetFromBase_Categories();
        
        if(categories!=null && !categories.isEmpty())
            for(String cat: categories)
            {
                if(cat.toUpperCase().startsWith(languagePrefix.toUpperCase()))
                {
                   
                    builder.addItem(cat, cat);
                }
            }
            
        return builder.toString();
    }

}
