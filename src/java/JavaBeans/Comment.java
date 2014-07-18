/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author Chrysohous
 */
public class Comment {
    
    String comment;
    String owner;
    int advId;
    int id;
    
    public Comment(){
    }

    public Comment(String comment, String owner, int advId, int id) {
        this.comment = comment;
        this.owner = owner;
        this.advId = advId;
        this.id = id;
    }

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    

    
    
}
