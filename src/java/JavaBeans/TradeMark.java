/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author Chrysohous
 */
public class TradeMark {
    
    double mark;
    String description;
    int tradeId;
    String emailDealer;
    String emailBuyer;

    public TradeMark(double mark, String description, int tradeId, String emailDealer, String emailBuyer) {
        this.mark = mark;
        this.description = description;
        this.tradeId = tradeId;
        this.emailDealer = emailDealer;
        this.emailBuyer = emailBuyer;
    }

    public TradeMark()
    {
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getEmailBuyer() {
        return emailBuyer;
    }

    public void setEmailBuyer(String emailBuyer) {
        this.emailBuyer = emailBuyer;
    }

    public String getEmailDealer() {
        return emailDealer;
    }

    public void setEmailDealer(String emailDealer) {
        this.emailDealer = emailDealer;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }
    
    
}

