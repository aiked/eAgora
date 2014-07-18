/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author Chrysohous
 */
public class Advert {
    
    String title;
    String description;
    double price;
    String sellbuy;

  
    
    String coin;
    String meter;
    
    int availability;
    boolean enable;
    String commends;
    
    boolean type;
    boolean condition;
    String category;
    String owner;
    String date;
    int visitors;
    int id;
    double rating;


    String photos;

    public Advert(Advert advert) {
        this.title = advert.getTitle();
        this.description = advert.getDescription();
        this.price = advert.getPrice();
        this.type = advert.getType();
        this.condition = advert.getCondition();
        this.category = advert.getCategory();
        this.owner = advert.getOwner();
        this.date = advert.getDate();
        this.visitors = advert.getVisitors();
        this.photos=advert.getPhotos();
        this.id=advert.getId();
    }

    public Advert(String title, String description, double price, boolean type, boolean condition, String category, String owner, String date, int visitors, String photos, int id) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
        this.condition = condition;
        this.category = category;
        this.owner = owner;
        this.date = date;
        this.visitors = visitors;
        this.photos=photos;
        this.id=id;
    }

    public Advert() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getCommends() {
        return commends;
    }

    public void setCommends(String commends) {
        this.commends = commends;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    } 
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }
    
    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }
    
     public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
    
    public String getSellbuy() {
        return sellbuy;
    }

    public void setSellbuy(String sellbuy) {
        this.sellbuy = sellbuy;
    }
    
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString()
    {
        return "\nTitle:"+this.title+"\n"
                + "Description:"+this.description+"\n"
                + "Selling or Buying:"+this.sellbuy+"\n"
                + "Category:"+this.category+"\n"
                + "Price:"+this.price+"\n"
                + "Meter:"+this.meter+"\n"
                + "Coin:"+this.coin+"\n"
                + "Quantity:"+this.availability+"\n"
                + "Type:"+this.type+"\n"
                + "Condition:"+this.condition+"\n"
                + "Owner:"+this.owner+"\n"
                + "Date:"+this.date+"\n"
                + "Commends:"+ this.commends+"\n"
                + "Id:"+ this.id+"\n"
                + "Name of photos available:"+this.photos+"\n"
                + "Rating:"+this.rating+"\n"
                ;
    }

}
