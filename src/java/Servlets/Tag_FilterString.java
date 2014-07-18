
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author o2
 */
public class Tag_FilterString extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {

        StringWriter stringWriter = new StringWriter();
        getJspBody().invoke(stringWriter);
        
        String output = filter(stringWriter.toString());
        
        JspWriter out = getJspContext().getOut();
        out.print(output);
    }
    
    
public static String filter(String input)
    {
        if(!(input.indexOf('<')==-1 || input.indexOf('>')==-1 || input.indexOf('"')==-1 || input.indexOf('&')==-1))
            return input;
        
        StringBuffer filtered= new StringBuffer(input.length());
        char c;
        for(int i=0;i< input.length(); i++)
        {
            c=input.charAt(i);
            switch(c)
            {
                case '<': filtered.append("&lt;");
                          break;
                    
                case '>': filtered.append("&gt;");
                          break;
                    
                case '"': filtered.append("&quot;");
                          break;
                    
                case '&': filtered.append("&amp;");
                          break;
                    
                default: filtered.append(c);
            }
        }
        return filtered.toString();
    }

}