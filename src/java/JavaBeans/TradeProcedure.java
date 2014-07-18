/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author Chrysohous
 */
public class TradeProcedure {
    Member Mbuyer;
    Member Mdealer;
    Advert Adealer;
    Advert Abuyer;
    int ammount;
    int enable;
    String comment;
    int id;

    public TradeProcedure(Member Mbuyer, Member Mdealer, Advert Adealer, Advert Abuyer, int ammount, int id, int enable, String comment) {
        this.Mbuyer = Mbuyer;
        this.Mdealer = Mdealer;
        this.Adealer = Adealer;
        this.Abuyer = Abuyer;
        this.ammount = ammount;
        this.id=id;
        this.enable=enable;
        this.comment=comment;
    }

    public TradeProcedure(){
        
    }

    public Advert getAbuyer() {
        return Abuyer;
    }

    public void setAbuyer(Advert Abuyer) {
        this.Abuyer = Abuyer;
    }

    public Advert getAdealer() {
        return Adealer;
    }

    public void setAdealer(Advert Adealer) {
        this.Adealer = Adealer;
    }

    public Member getMbuyer() {
        return Mbuyer;
    }

    public void setMbuyer(Member Mbuyer) {
        this.Mbuyer = Mbuyer;
    }

    public Member getMdealer() {
        return Mdealer;
    }

    public void setMdealer(Member Mdealer) {
        this.Mdealer = Mdealer;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
}
