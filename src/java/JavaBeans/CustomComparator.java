/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

import java.util.Comparator;

/**
 *
 * @author o2
 */
public class CustomComparator implements Comparator<Tmptrade> {

    @Override
    public int compare(Tmptrade o1, Tmptrade o2) {
        return ((int)(o1.getMark()-o2.getMark()));
    }
    
}
