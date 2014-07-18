/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author o2
 */
 public class Tmptrade{
        String mem;
        double mark;
        
        public Tmptrade()
        {
            
        }

        public Tmptrade(String mem, double mark) {
            this.mem = mem;
            this.mark = mark;
        }
        
        

        public double getMark() {
            return mark;
        }

        public void setMark(double mark) {
            this.mark = mark;
        }

        public String getMem() {
            return mem;
        }

        public void setMem(String mem) {
            this.mem = mem;
        }
    
        
    }