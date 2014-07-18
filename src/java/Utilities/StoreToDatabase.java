/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import JavaBeans.*;
import JavaBeans.TradeProcedure;
import Servlets.Form_SignUp;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chrysohous
 */
public class StoreToDatabase {
    /*
     * ksanaftiaxnw to member table giati to tropopiisa
     */
    public static boolean StoreToBase_Member(Member member) throws IOException
    {
        Connection Con;
        Statement stmt;
        boolean notFind = true;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from Members where email='" + member.getEmail() + "'";
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                notFind = false;
            }
            else
            {
                String sql =
                        "insert into MEMBERS values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = Con.prepareStatement(sql);
                pst.setString(1, member.getEmail());
                pst.setString(2, member.getPassword());
                pst.setString(3, member.getFname());
                pst.setInt(4, member.getType()? 1 : 0);
                pst.setString(5, member.getSex());
                pst.setString(6, member.getBirthdate());
                pst.setString(7, member.getCountry());
                pst.setString(8, member.getState());
                pst.setString(9, member.getSigndate());
                pst.setString(10, member.getImagepath()); 
                pst.setString(11, member.getSubright());
                pst.setString(12, member.getMemberright());
                pst.setString(13, member.getViitright());
                pst.executeUpdate();
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return notFind;
    }
    
    public static Member GetFromBase_member(String email)
    {
        Connection Con;
        Statement stmt;
        Member member = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from Members where email='" + email + "'";
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                member = new Member();                           
                
                member.setEmail((String) rs.getObject(1));
                member.setPassword((String) rs.getObject(2));
                member.setFname((String) rs.getObject(3));
                member.setType(rs.getInt(4) ==1 );
                member.setSex((String) rs.getObject(5));
                member.setBirthdate((String) rs.getObject(6));
                member.setCountry((String) rs.getObject(7));
                member.setState((String) rs.getObject(8));
                member.setSigndate((String) rs.getObject(9));
                member.setImagepath((String) rs.getObject(10));
                member.setSubright((String) rs.getObject(11));
                member.setMemberright((String) rs.getObject(12));
                member.setViitright((String) rs.getObject(13));
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return member;
    }
    
    public static ArrayList <Member> GetFromBase_AllMembers()
    {
        Connection Con;
        Statement stmt;
        
        ArrayList <Member> members = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from Members";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                Member member = new Member();                           
                
                member.setEmail((String) rs.getObject(1));
                member.setPassword((String) rs.getObject(2));
                member.setFname((String) rs.getObject(3));
                member.setType(rs.getInt(4) ==1 );
                member.setSex((String) rs.getObject(5));
                member.setBirthdate((String) rs.getObject(6));
                member.setCountry((String) rs.getObject(7));
                member.setState((String) rs.getObject(8));
                member.setSigndate((String) rs.getObject(9));
                member.setImagepath((String) rs.getObject(10));
                member.setSubright((String) rs.getObject(11));
                member.setMemberright((String) rs.getObject(12));
                member.setViitright((String) rs.getObject(13));
                
                members.add(member);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return members;
    } 
    
    public static void UpdateBase_member(Member member)
    {
        Connection Con;
        Statement stmt;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            System.out.println("IN GETFIELD " + member.toString());
            String str = "UPDATE MEMBERS SET PASSWORD='" + member.getPassword() + "',"
                    + " FNAME='"+ member.getFname() +  "', " + " TYPE="+ (member.getType()?"1":"0") +  ", " + " SEX='"+ member.getSex() +  "', "
                    + " AGE='"+ member.getBirthdate() +  "', " + " COUNTRY='"+ member.getCountry() +  "', " + " STATE='"+ member.getState() +  "', "
                    + " SUBSCRIBEDATE='"+ member.getSigndate() +  "', " + " IMAGEPATH='"+ member.getImagepath() +  "', " + " SUBSCRIPERIGHTS='"+ member.getSubright() +  "', "
                    + " MEMBERRIGHTS='"+ member.getMemberright() +  "', " + " VISITORSRIGHTS='"+ member.getViitright() +  "' WHERE EMAIL='" + member.getEmail() + "' ";
            System.out.println(str);
            stmt.executeUpdate(str);
            
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static ArrayList <Advert> GetFromBase_AdvertsOf(String email)
    {
        Connection Con;
        Statement stmt;
        ArrayList <Advert> adverts = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from Advert WHERE EMAILOWNED='"+ email +"'";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                Advert advert = new Advert();                          
                
                advert.setTitle((String)rs.getObject(1));
                advert.setType(rs.getInt(2)==1);
                advert.setDescription((String)rs.getObject(3));
                advert.setPrice(rs.getDouble(4));
                advert.setCoin((String)rs.getObject(5));
                advert.setMeter(rs.getString(6));
                advert.setAvailability(rs.getInt(7));
                advert.setCondition(rs.getInt(8)==1);
                advert.setCategory((String)rs.getObject(9));
                advert.setOwner((String)rs.getObject(10));
                advert.setId(rs.getInt(11));
                advert.setDate((String)rs.getObject(12));
                advert.setCommends((String)rs.getObject(13));
                advert.setEnable(rs.getInt(14)==1);
                advert.setVisitors(rs.getInt(15));
                advert.setSellbuy((String)rs.getObject(16));
                advert.setRating(rs.getDouble(17));
                advert.setPhotos(GetFromBase_AdvertPhoto(advert.getId()));
                
                adverts.add(advert);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adverts;
    }
    
    public static ArrayList <Advert> GetFromBase_AdvertsOfFollowingOf(String email)
    {
        ArrayList<Advert> adverts = GetFromBase_AllAdverts();
        ArrayList<Member> following = GetFromBase_Followers(email);
        for(Member mem:following)
        {
            System.out.println("FOLLOWING IS"+mem.getEmail());
        }
        ArrayList<Advert> NEWadverts = new ArrayList();
        
        if((adverts!=null)&&(following!=null)&&!(adverts.isEmpty())&&!(following.isEmpty()))
            for (int a=0;a<adverts.size();a++)
            {
                for ( int f=0;f<following.size();f++)
                {
                    if(adverts.get(a).getOwner().equals(following.get(f).getEmail()))
                    {
                        NEWadverts.add(adverts.get(a));
                    }
                        
                }
            }
        return NEWadverts;
    }
    
    
    public static Advert GetFromBase_AdvertOf(int id)
    {
        Connection Con;
        Statement stmt;
        Advert advert = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = (id!=-1? ( "select * from Advert WHERE ID="+ id) :
            ("select * from ADVERT WHERE ID= (select MAX(ID) from ADVERT)"));
            
            System.out.println(str);
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                advert = new Advert();                          
                
                advert.setTitle((String)rs.getObject(1));
                advert.setType(rs.getInt(2)==1);
                advert.setDescription((String)rs.getObject(3));
                advert.setPrice(rs.getDouble(4));
                advert.setCoin((String)rs.getObject(5));
                advert.setMeter(rs.getString(6));
                advert.setAvailability(rs.getInt(7));
                advert.setCondition(rs.getInt(8)==1);
                advert.setCategory((String)rs.getObject(9));
                advert.setOwner((String)rs.getObject(10));
                advert.setId(rs.getInt(11));
                advert.setDate((String)rs.getObject(12));
                advert.setCommends((String)rs.getObject(13));
                advert.setEnable(rs.getInt(14)==1);
                advert.setVisitors(rs.getInt(15));
                advert.setSellbuy((String)rs.getObject(16));
                advert.setRating(rs.getDouble(17));
                advert.setPhotos(GetFromBase_AdvertPhoto(advert.getId()));
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return advert;
    }
    
    public static boolean DeleteFromBase_Advert(int id)
    {
        Connection Con;
        Statement stmt;
        boolean ok=false;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "DELETE FROM ADVERT WHERE ID="+id;
                PreparedStatement pst = Con.prepareStatement(str);
                pst.executeUpdate();

            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "DELETE FROM IMAGEVIDEOPATH WHERE ADVERTID="+id;
                PreparedStatement pst = Con.prepareStatement(str);
                pst.executeUpdate();

            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return ok;
    }
            
    public static ArrayList <Advert> GetFromBase_AllAdverts()
    {
        Connection Con;
        Statement stmt;
        ArrayList <Advert> adverts = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from Advert ORDER BY DATE";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                Advert advert = new Advert();                          
                
                advert.setTitle((String)rs.getObject(1));
                advert.setType(rs.getInt(2)==1);
                advert.setDescription((String)rs.getObject(3));
                advert.setPrice(rs.getDouble(4));
                advert.setCoin((String)rs.getObject(5));
                advert.setMeter(rs.getString(6));
                advert.setAvailability(rs.getInt(7));
                advert.setCondition(rs.getInt(8)==1);
                advert.setCategory((String)rs.getObject(9));
                advert.setOwner((String)rs.getObject(10));
                advert.setId(rs.getInt(11));
                advert.setDate((String)rs.getObject(12));
                advert.setCommends((String)rs.getObject(13));
                advert.setEnable(rs.getInt(14)==1);
                advert.setVisitors(rs.getInt(15));
                advert.setSellbuy((String)rs.getObject(16));
                advert.setRating(rs.getDouble(17));
                advert.setPhotos(GetFromBase_AdvertPhoto(advert.getId()));
                
                adverts.add(advert);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adverts;
    }
    
    public static ArrayList <String> GetFromBase_Categories()
    {
        Connection Con;
        Statement stmt;
        ArrayList <String> categories = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select DISTINCT CATEGORY from Advert";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                categories.add((String) rs.getObject("CATEGORY"));
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categories;
    }
    
    public static boolean StoreToBase_Advert(Advert advert) throws IOException
    {
        Connection Con;
        Statement stmt;
        boolean ok = false;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String sql ="insert into ADVERT (TITLE,TYPE,DESCRIPTION,PRICE,COIN,METER,AVAILABILITY,"
                    + "CONDITION,CATEGORY,EMAILOWNED,DATE,COMMENDS,ENABLE,VISITORS,SELLBUY,RATING) values "
                    + "('"+advert.getTitle()+"',"+(advert.getType()?1:0)+","
                    + "'"+advert.getDescription()+"',"+advert.getPrice()+","
                    + "'"+advert.getCoin()+"','"+advert.getMeter()+"',"
                    + ""+advert.getAvailability()+","+(advert.getCondition()?1:0)+","
                    + "'"+advert.getCategory()+"','"+advert.getOwner()+"',"
                    + "'"+advert.getDate()+"','"+(advert.getCommends()==null?"no_comments":advert.getCommends())+"',"
                    + ""+(advert.getEnable()?1:0)+","+advert.getVisitors()+",'"+advert.getSellbuy()+"',"+advert.getRating()+")";
            
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.executeUpdate();
            
            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }
    
    public static void UpdateBase_Advert(Advert advert)
    {
        Connection Con;
        Statement stmt;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "UPDATE ADVERT SET TITLE='" + advert.getTitle() + "',"
                    + " TYPE="+ (advert.getType()?"1":"0") +  ", " + " DESCRIPTION='"+ advert.getDescription() +  "', " + " PRICE="+ advert.getPrice() +  ", "
                    + " COIN='"+ advert.getCoin() +  "', " + " AVAILABILITY="+ advert.getAvailability() +  ", " + " CONDITION="+ (advert.getCondition()?"1":"0") +", "
                    + " CATEGORY='"+ advert.getCategory() +  "', " + " EMAILOWNED='"+ advert.getOwner() +  "', " + " DATE='"+ advert.getDate() +  "', "
                    + " COMMENDS='"+ advert.getCommends() +  "', " + " ENABLE="+ (advert.getEnable()?"1":"0") +", "
                    + " VISITORS="+ advert.getVisitors() +  ", SELLBUY='"+ advert.getSellbuy() +  "', RATING="+ advert.getRating()+" WHERE ID=" + advert.getId();
            System.out.println(str);
            stmt.executeUpdate(str);
            
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static boolean StoreToBase_SetFollower(String memberEmail, String followerEmail ) 
            throws IOException
    {
        Connection Con;
        Statement stmt;
        boolean ok = false;
        System.out.println("Follower:"+memberEmail);
        System.out.println("Following:"+followerEmail);
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement(); 
            
            String sql ="insert into SUBSCRIPE_FOLLOWERS (EMAILMEMBER, EMAILFOLLOWER) values ('"+ memberEmail +"','"+  followerEmail +"')";
            stmt.executeUpdate(sql);
            
            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }
    
    public static int GetFromBase_IsFollower(String from, String to)
    {
        Connection Con;
        Statement stmt;
        int find=-1;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str ="select * from SUBSCRIPE_FOLLOWERS where EMAILMEMBER='"+ from +"' AND EMAILFOLLOWER='"+ to +"'";
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {   
                  find=rs.getInt(3);                      
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return find;
    }
    
    public static boolean DeletFromBase_Follower(String from, String to)
    {
        Connection Con;
        Statement stmt;
        
        boolean ok=false;
        
         try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "DELETE FROM SUBSCRIPE_FOLLOWERS WHERE EMAILMEMBER='"+ from +"' AND EMAILFOLLOWER='"+ to +"'";
            PreparedStatement pst = Con.prepareStatement(str);
            pst.executeUpdate();

            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return ok;
    }
    
    public static ArrayList <Member> GetFromBase_Followers(String email)
    {
        return GetFromBase_Following_Followers(email, true);
    }
    
    public static ArrayList <Member> GetFromBase_Following(String email)
    {
        return GetFromBase_Following_Followers(email, false);
    }
    
    private static ArrayList <Member> GetFromBase_Following_Followers(String email, boolean kind)
    {
        Connection Con;
        Statement stmt;
        ArrayList <Member> members = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str =kind? "select * from SUBSCRIPE_FOLLOWERS WHERE EMAILMEMBER='"+ email +"'    " :
                              "select * from SUBSCRIPE_FOLLOWERS WHERE EMAILFOLLOWER='"+ email +"'    ";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {   
                Member tmp = GetFromBase_member((String)rs.getObject(kind?2:1));
                members.add(tmp);                         
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return members;
    }    

    public static boolean StoreToBase_AdvertPhoto(String photos,int advertId)
    {
        Connection Con;
        Statement stmt;
        boolean ok = false;
        System.out.println(photos);
        String[] arrayPhotos = photos.split(",");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            for(String str : arrayPhotos)
            {
                System.out.println("AdvertPhotos:Now importing:"+str);
                String sql ="insert into IMAGEVIDEOPATH (ADVERTID,PATH) values ("+advertId+",'"+str+"')";
                PreparedStatement pst = Con.prepareStatement(sql);
                pst.executeUpdate();
            }
            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }
    
    public static String GetFromBase_AdvertPhoto(int advertId)
    {
        Connection Con;
        Statement stmt;
        
        String retval="";
        
         try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from IMAGEVIDEOPATH WHERE ADVERTID="+advertId;
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                if(retval.equals(""))
                    retval=(String)rs.getObject(2);
                else
                    retval+=","+(String)rs.getObject(2);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return retval;
    }
    
    public static boolean DeleteFromBase_AdvertPhoto(String photo)
    {
        Connection Con;
        Statement stmt;
        
        boolean ok=false;
        
         try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "DELETE FROM IMAGEVIDEOPATH WHERE PATH='"+ photo +"'";
                PreparedStatement pst = Con.prepareStatement(str);
                pst.executeUpdate();

            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return ok;
    }

    public static boolean StoreToBase_Comment(Comment comment)
    {
        Connection Con;
        Statement stmt;
        boolean ok = false;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String sql ="insert into COMMENTS (EMAILMEMBER, COMMENT, ADVERTID) "
                    + "values ('"+ comment.getOwner() +"','"+ comment.getComment() +"', " + comment.getAdvId() + " )";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.executeUpdate();
            
            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }
    
    public static ArrayList <Comment> GetFromBase_Comments(int advid)
    {
        Connection Con;
        Statement stmt;
        ArrayList <Comment> comments = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str ="select * from COMMENTS WHERE ADVERTID="+ advid;
 
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {   
                Comment tmp = new Comment();
                
                tmp.setOwner(rs.getString(1));
                tmp.setComment(rs.getString(2));
                tmp.setAdvId(rs.getInt(3));
                tmp.setId(rs.getInt(4));
                
                comments.add(tmp);                         
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return comments;
    }
    
    public static boolean StoreToBase_TradeProcedure(TradeProcedure tradeProcedure)
    {
        Connection Con;
        Statement stmt;
        boolean ok = false;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String sql ="insert into TRADE_PROCEDURE (EMAILBUYER, EMAILDEALER, ADVERTDEALER, ADVERTBUYER, AMOUNT, ENABLE, COMMENT) "
                    + "values ('"+ tradeProcedure.getMbuyer().getEmail() +"','"+ tradeProcedure.getMdealer().getEmail() +"', " + tradeProcedure.getAdealer().getId() 
                    + ", " + tradeProcedure.getAbuyer().getId() + ", " + tradeProcedure.getAmmount() + ", " + tradeProcedure.getEnable() 
                    + ", '"+ tradeProcedure.getComment() +"' )";
            
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.executeUpdate();
            
            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }   
    
    public static ArrayList<TradeProcedure> GetFromBase_BuyerTradeProcedures(String emailbuyer)
    {
        return GetFromBase_TradeProcedures(true, emailbuyer);
    }
    
    public static ArrayList<TradeProcedure> GetFromBase_DealerTradeProcedures(String emaildealer)
    {
        return GetFromBase_TradeProcedures(false, emaildealer);
    }
    
    
    private static ArrayList<TradeProcedure> GetFromBase_TradeProcedures(boolean kind, String email)
    {
        Connection Con;
        Statement stmt;
        ArrayList <TradeProcedure> tradeProcedures = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from TRADE_PROCEDURE WHERE " + ( kind ? "EMAILBUYER" : "EMAILDEALER") + "='" + email+"' ORDER BY ID" ;
            System.out.println(str);
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                TradeProcedure tradeProcedure = new TradeProcedure();
                
                Member member = GetFromBase_member(rs.getString(1));
                Member member1 = GetFromBase_member(rs.getString(2));
                Advert AdvertOf = GetFromBase_AdvertOf(rs.getInt(3));
                Advert AdvertOf1 = GetFromBase_AdvertOf(rs.getInt(4));
                
                if(member!=null && member1!=null && AdvertOf!=null && AdvertOf1!=null)
                {
                    tradeProcedure.setMbuyer(member);
                    tradeProcedure.setMdealer(member1);
                    tradeProcedure.setAdealer(AdvertOf);
                    tradeProcedure.setAbuyer(AdvertOf1);   
                    tradeProcedure.setAmmount(rs.getInt(5));
                    tradeProcedure.setId(rs.getInt(6));
                    tradeProcedure.setEnable(rs.getInt(7));
                    tradeProcedure.setComment(rs.getString(8));
                    
                    tradeProcedures.add(tradeProcedure);
                }
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tradeProcedures;
    }
    
    public static TradeProcedure GetFromBase_TradeProcedure(int id)
    {
        Connection Con;
        Statement stmt;
        TradeProcedure tradeProcedure = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from TRADE_PROCEDURE WHERE ID=" + id;
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                tradeProcedure = new TradeProcedure();
                
                Member member = GetFromBase_member(rs.getString(1));
                Member member1 = GetFromBase_member(rs.getString(2));
                Advert AdvertOf = GetFromBase_AdvertOf(rs.getInt(3));
                Advert AdvertOf1 = GetFromBase_AdvertOf(rs.getInt(4));
                
                if(member!=null && member1!=null && AdvertOf!=null && AdvertOf1!=null)
                {
                    tradeProcedure.setMbuyer(member);
                    tradeProcedure.setMdealer(member1);
                    tradeProcedure.setAdealer(AdvertOf);
                    tradeProcedure.setAbuyer(AdvertOf1);   
                    tradeProcedure.setAmmount(rs.getInt(5));
                    tradeProcedure.setId(rs.getInt(6));
                    tradeProcedure.setEnable(rs.getInt(7));
                    tradeProcedure.setComment(rs.getString(8));
                }
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tradeProcedure;
    }
    
    public static void UpdateBase_UnableTradeProcedures(int procId, int mode)
    {
        Connection Con;
        Statement stmt;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "UPDATE TRADE_PROCEDURE SET ENABLE="+mode+" WHERE ID=" + procId ;
            stmt.executeUpdate(str);
            
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    public static boolean StoreToBase_TradeMark(TradeProcedure tradeProcedure, double mark)
    {
        Connection Con;
        Statement stmt;
        boolean ok = false;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String sql ="insert into TRADE_MARK (TRADEID, EMAILDEALER, EMAILBUYER, MARK, DESCRIPTION) "
                    + "values ("+ tradeProcedure.getId() +",'"+ tradeProcedure.getMdealer().getEmail() +"', '" + tradeProcedure.getMbuyer().getEmail() 
                    + "', " + mark + ", 'tpt' )";
            
            System.out.println(sql);
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.executeUpdate();
            
            ok=true;
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }   
    
    public static void UpdateBase_TradeMark(TradeMark trademark)
    {
        Connection Con;
        Statement stmt;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "UPDATE TRADE_MARK SET EMAILDEALER='"+trademark.getEmailDealer()+"', "
                    + "EMAILBUYER='"+trademark.getEmailBuyer()+"', MARK="+trademark.getMark()+" , DESCRIPTION='"+trademark.getDescription()+"'  WHERE TRADEID=" + trademark.getTradeId() ;
            stmt.executeUpdate(str);
            
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static TradeMark GetFromBase_TradeMark(int id)
    {
        Connection Con;
        Statement stmt;
        TradeMark tradeProcedure = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from TRADE_MARK WHERE TRADEID=" + id;
            ResultSet rs = stmt.executeQuery(str);
            if (rs.next()) {
                tradeProcedure = new TradeMark();
                
                tradeProcedure.setTradeId(rs.getInt(1));
                tradeProcedure.setEmailDealer(rs.getString(2));
                tradeProcedure.setEmailBuyer(rs.getString(3));
                tradeProcedure.setMark(rs.getDouble(4));
                tradeProcedure.setDescription(rs.getString(5));
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tradeProcedure;
    } 
    
    public static ArrayList <Advert> GetFromBase_MostVisitedAdverts()
    {
        Connection Con;
        Statement stmt;
        ArrayList <Advert> adverts = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select * from Advert ORDER BY VISITORS";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                Advert advert = new Advert();                          
                
                advert.setTitle((String)rs.getObject(1));
                advert.setType(rs.getInt(2)==1);
                advert.setDescription((String)rs.getObject(3));
                advert.setPrice(rs.getDouble(4));
                advert.setCoin((String)rs.getObject(5));
                advert.setMeter(rs.getString(6));
                advert.setAvailability(rs.getInt(7));
                advert.setCondition(rs.getInt(8)==1);
                advert.setCategory((String)rs.getObject(9));
                advert.setOwner((String)rs.getObject(10));
                advert.setId(rs.getInt(11));
                advert.setDate((String)rs.getObject(12));
                advert.setCommends((String)rs.getObject(13));
                advert.setEnable(rs.getInt(14)==1);
                advert.setVisitors(rs.getInt(15));
                advert.setSellbuy((String)rs.getObject(16));
                advert.setRating(rs.getDouble(17));
                advert.setPhotos(GetFromBase_AdvertPhoto(advert.getId()));
                
                adverts.add(advert);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList <Advert> tmp = adverts;
        if(adverts!=null && !adverts.isEmpty())
        {
            
            Collections.reverse(adverts);
            tmp = new ArrayList();
            int i=0;
            for(Advert x : adverts)
            {
                
                if(++i>3)
                    break;
                tmp.add(x);
            }
        }
        return tmp;
    }
    
    public static ArrayList <Member> GetFromBase_MostFamousMembersByMark()
    {
        Connection Con;
        Statement stmt;
        
        ArrayList <Tmptrade> trade = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select  EMAILDEALER, avg(MARK)from TRADE_MARK group by EMAILDEALER";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                
                Tmptrade tradeProcedure = new Tmptrade();
                
                tradeProcedure.setMem(rs.getString(1));
                tradeProcedure.setMark(rs.getDouble(2));
                
                trade.add(tradeProcedure);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        ArrayList <Member> tmp = new ArrayList();
        if(trade!=null && !trade.isEmpty())
        {
            Collections.sort(trade, new CustomComparator());
            int i=0;
            for(Tmptrade x : trade)
            {
                tmp.add( GetFromBase_member(x.getMem()) );
                if(++i>3)
                    break;
            }
        }
        
        return tmp;
    } 
   


    public static ArrayList <Member> GetFromBase_MostFamousMembersByAdverts()
    {
        Connection Con;
        Statement stmt;
        
        ArrayList <Tmptrade> trade = new ArrayList();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Eagora", "root", "root"); 
            stmt = Con.createStatement();
            
            String str = "select  EMAILOWNED, COUNT(*) from ADVERT group by EMAILOWNED";
            ResultSet rs = stmt.executeQuery(str);
            while (rs.next()) {
                
                Tmptrade tradeProcedure = new Tmptrade();
                
                tradeProcedure.setMem(rs.getString(1));
                tradeProcedure.setMark(rs.getInt(2));
                
                trade.add(tradeProcedure);
            }
            stmt.close();
            Con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Form_SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList <Member> tmp = new ArrayList();
        if(trade!=null && !trade.isEmpty())
        {
            Collections.sort(trade, new CustomComparator());
            int i=0;
            for(Tmptrade x : trade)
            {
                tmp.add( GetFromBase_member(x.getMem()) );
                if(i++>3)
                    break;
            }
        }
        
        return tmp;
    } 

}



