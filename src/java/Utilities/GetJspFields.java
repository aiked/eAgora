
package Utilities;

import JavaBeans.Member;
import JavaBeans.Advert;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class GetJspFields {
    
    public static Member getJspField_Member(HttpServletRequest request) throws IOException
    {
        Member member=new Member();
        String uploadFilename=null;
        
        String file_folder = "MembersFotos";
        String subRights = new String("11111111");
        String usrRights = new String("11111111");
        String visRights = new String("11111111");
        String day=null,month=null,year=null;
        
        String dir = request.getServletContext().getRealPath("/") + file_folder;
            
            new File(dir).mkdir();
            MultipartRequest multi =
            new MultipartRequest(request, dir, 50 *1024 * 1024,
              new com.oreilly.servlet.multipart.DefaultFileRenamePolicy());

            Enumeration params = multi.getParameterNames();

             while (params.hasMoreElements()) {
                String pname = (String)params.nextElement();
                String pvalue = multi.getParameter(pname);
                if(pname.equals("SignFullName"))
                    member.setFname(pvalue);
                else if(pname.equals("SignPassword"))
                    member.setPassword(pvalue);
                else if(pname.equals("SignEmail"))
                    member.setEmail(pvalue);
                else if(pname.equals("type"))
                {
                    if (pvalue.equals("Individual"))
                        member.setType(false);
                    else if (pvalue.equals("Company"))
                    {
                        member.setSex("Not defined");
                        member.setType(true);
                    }
                }                 
                else if(pname.equals("sex"))
                {
                    if (pvalue.equals("male"))
                        member.setSex("Male");
                    else if (pvalue.equals("female"))
                        member.setSex("Female");    
                }           
                else if(pname.equals("country"))
                    member.setCountry(pvalue);                 
                else if(pname.equals("state"))
                    member.setState(pvalue);   
                else if(pname.equals("birthDay"))
                    day=pvalue;
                else if(pname.equals("birthMonth"))
                    month=pvalue;
                else if(pname.equals("birthYear"))
                    year=pvalue;
            }
             
            Enumeration files = multi.getFileNames();
            while (files.hasMoreElements()) {
                String pname = (String)files.nextElement();
                String filename = multi.getFilesystemName(pname);
                uploadFilename=filename;
            }
            
            if(uploadFilename==null)
            {
                member.setImagepath("default");
            }
            else
            {
                member.setImagepath(uploadFilename);
            }
            
            Date today=new Date();
            member.setBirthdate(day+"/"+month+"/"+year);
            member.setSigndate(today.getDate()+"/"+(today.getMonth()+1)+"/"+(1900+today.getYear())); 
            member.setViitright(visRights);
            member.setSubright(subRights);
            member.setMemberright(usrRights);
            
            return member;
    }
    
    
    public static Member getJspFieldEditProfile_Member(HttpServletRequest request,Member oldMember) throws IOException
    {
        Member member=new Member(oldMember);
        String uploadFilename=null;
        
        String file_folder = "MembersFotos";
        char [] subRights = {'0','0','0','0','0','0','0','0'};
        char [] usrRights = {'0','0','0','0','0','0','0','0'};
        char [] visRights = {'0','0','0','0','0','0','0','0'};
        String day=null,month=null,year=null;
        
        String newPass=null;
        String oldPass=null;

        String dir = request.getServletContext().getRealPath("/") + file_folder;
            
            new File(dir).mkdir();
            MultipartRequest multi =
            new MultipartRequest(request, dir, 50 *1024 * 1024,
            new com.oreilly.servlet.multipart.DefaultFileRenamePolicy());

            Enumeration params = multi.getParameterNames();

             while (params.hasMoreElements()) {
                String pname = (String)params.nextElement();
                String pvalue = multi.getParameter(pname);
                 System.out.println(pname+" "+pvalue);
                if(pname.equals("SignFullName"))
                    member.setFname(pvalue);
                else if(pname.equals("OldPass"))
                    oldPass=pvalue;
                else if(pname.equals("NewPass"))
                    newPass=pvalue;
                else if(pname.equals("SignEmail"))
                    member.setEmail(pvalue);
                else if(pname.equals("type"))
                {
                    if (pvalue.equals("Individual"))
                        member.setType(false);
                    else if (pvalue.equals("Company"))
                    {
                        member.setSex("Not defined");
                        member.setType(true);
                    }
                }                 
                else if(pname.equals("sex"))
                {
                    if (pvalue.equals("Male"))
                        member.setSex("Male");
                    else if (pvalue.equals("Female"))
                        member.setSex("Female");  
                    else
                        member.setSex("Notdef");  
                }           
                else if(pname.equals("country"))
                    member.setCountry(pvalue);                 
                else if(pname.equals("state"))
                    member.setState(pvalue);   
                else if(pname.equals("birthDay"))
                    day=pvalue;
                else if(pname.equals("birthMonth"))
                    month=pvalue;
                else if(pname.equals("birthYear"))
                    year=pvalue;
                else if(pname.equals("EmailUsrRight"))
                     usrRights[0]='1';
                else if(pname.equals("EmailSubRight"))
                     subRights[0] ='1';
                else if(pname.equals("EmailVisRight"))
                     visRights[0]='1';
                else if(pname.equals("FullNameUsrRight"))
                     usrRights[1]='1';
                else if(pname.equals("FullNameVisRight"))
                     visRights[1]='1';
                else if(pname.equals("FullNameSubRight"))
                     subRights[1] ='1';
                else if(pname.equals("BirthUsrRight"))
                     usrRights[2]='1';
                else if(pname.equals("BirthVisRight"))
                     visRights[2]='1';
                else if(pname.equals("BirthSubRight"))
                     subRights[2] ='1';
                else if(pname.equals("TypeUsrRight"))
                     usrRights[3]='1';
                else if(pname.equals("TypeVisRight"))
                    visRights[3]='1';
                else if(pname.equals("TypeSubRight"))
                     subRights[3] ='1';
                else if(pname.equals("SexUsrRight"))
                     usrRights[4]='1';
                else if(pname.equals("SexVisRight"))
                     visRights[4]='1';
                else if(pname.equals("SexSubRight"))
                     subRights[4] ='1';
                else if(pname.equals("CountryUsrRight"))
                     usrRights[5]='1';
                else if(pname.equals("CountryVisRight"))
                     visRights[5]='1';
                else if(pname.equals("CountrySubRight"))
                     subRights[5] ='1';
                else if(pname.equals("StateUsrRight"))
                     usrRights[6]='1';
                else if(pname.equals("StateVisRight"))
                     visRights[6]='1';
                else if(pname.equals("StateSubRight"))
                     subRights[6] ='1';
                else if(pname.equals("ImgUsrRight"))
                     usrRights[7]='1';
                else if(pname.equals("ImgVisRight"))
                     visRights[7]='1';
                else if(pname.equals("ImgSubRight"))
                     subRights[7] ='1';
            }
             
            Enumeration files = multi.getFileNames();
            while (files.hasMoreElements()) {
                String pname = (String)files.nextElement();
                String filename = multi.getFilesystemName(pname);
                uploadFilename=filename;
            }
            
            if(uploadFilename==null)
            {
                member.setImagepath("default");
            }
            else
            {
                member.setImagepath(uploadFilename);
            }
            Date today=new Date();
            member.setBirthdate(day+"/"+month+"/"+year);
            member.setSigndate(today.getDate()+"/"+(today.getMonth()+1)+"/"+(1900+today.getYear()));  
            member.setViitright(new String(visRights));
            member.setSubright(new String(subRights));
            member.setMemberright(new String(usrRights));
            
            if(oldMember.getPassword().equals(oldPass))
                member.setPassword(newPass);
            else
                member.setPassword(oldMember.getPassword());
            
            return member;
    }

    public static Advert getJspField_Product(HttpServletRequest request) throws IOException
    {
        Advert retval=new Advert();
        
        String file_folder="ProductFotos";
        String dir = request.getServletContext().getRealPath("/") + file_folder;    
        new File(dir).mkdir();
        MultipartRequest multi =
        new MultipartRequest(request, dir, 50 *1024 * 1024,
            new com.oreilly.servlet.multipart.DefaultFileRenamePolicy());

        Enumeration params = multi.getParameterNames();
             
        while (params.hasMoreElements()) {
            String pname = (String)params.nextElement();
            String pvalue = multi.getParameter(pname);
            if(pname.equals("prdName"))
                retval.setTitle(pvalue);
            else if(pname.equals("prdDesc"))
                retval.setDescription(pvalue);
            else if(pname.equals("prdPrice"))
                retval.setPrice(Double.parseDouble(pvalue));
            else if(pname.equals("MoneyUnit"))
                retval.setCoin(pvalue);
            else if(pname.equals("QuaUnit"))
                retval.setMeter(pvalue);
            else if(pname.equals("prdQuantity"))
                retval.setAvailability(Integer.parseInt(pvalue));
            else if(pname.equals("prdType"))
                if(pvalue.equals("Product"))
                    retval.setType(false);
                else
                    retval.setType(true);
            else if(pname.equals("prdStatus"))
                if(pvalue.equals("New"))
                    retval.setCondition(false);
                else
                    retval.setCondition(true);
            else if(pname.equals("prdSB"))
                retval.setSellbuy(pvalue);
            else if(pname.equals("prdCategory"))
                retval.setCategory(pvalue);
            
        }
        
        Enumeration files = multi.getFileNames();
        String uploadFilename="";
        String pname = "";
        String filename="";
        while (files.hasMoreElements()) 
        {
            pname = (String)files.nextElement();
            filename = multi.getFilesystemName(pname);
            if(retval.getPhotos()==null)
                retval.setPhotos(filename);
            else
                retval.setPhotos(retval.getPhotos()+","+filename);
            System.out.println("JSP CODE PHOTO:"+retval.getPhotos());
        }
        
        Date today=new Date();
        retval.setDate(today.getDate()+"/"+(today.getMonth()+1)+"/"+(1900+today.getYear()));
        retval.setEnable(true);
        retval.setCommends("");
        retval.setVisitors(0);
        retval.setRating(0);
        if( retval.getCategory()==null)
            retval.setCategory("Uncategorized");
        
        return retval;
    }
    
    public static Advert getJspField_EditProduct(HttpServletRequest request) throws IOException
    {
        Advert retval=new Advert();
        
        String file_folder="ProductFotos";
        String dir = request.getServletContext().getRealPath("/") + file_folder;    
        new File(dir).mkdir();
        MultipartRequest multi =
        new MultipartRequest(request, dir, 50 *1024 * 1024,
            new com.oreilly.servlet.multipart.DefaultFileRenamePolicy());

        Enumeration params = multi.getParameterNames();
             
        while (params.hasMoreElements()) {
            String pname = (String)params.nextElement();
            String pvalue = multi.getParameter(pname);
            if(pname.equals("prdName"))
                retval.setTitle(pvalue);
            else if(pname.equals("prdDesc"))
                retval.setDescription(pvalue);
            else if(pname.equals("prdPrice"))
                retval.setPrice(Double.parseDouble(pvalue));
            else if(pname.equals("MoneyUnit"))
                retval.setCoin(pvalue);
            else if(pname.equals("QuaUnit"))
                retval.setMeter(pvalue);
            else if(pname.equals("prdQuantity"))
                retval.setAvailability(Integer.parseInt(pvalue));
            else if(pname.equals("prdType"))
                if(pvalue.equals("Product"))
                    retval.setType(false);
                else
                    retval.setType(true);
            else if(pname.equals("prdStatus"))
                if(pvalue.equals("New"))
                    retval.setCondition(false);
                else
                    retval.setCondition(true);
            else if(pname.equals("prdSB"))
                retval.setSellbuy(pvalue);
            else if(pname.equals("prdCategory"))
                retval.setCategory(pvalue);
            else if(pname.equals("prdId"))
            {
                System.out.println("prdId:"+pvalue);
                retval.setId(Integer.parseInt(pvalue));
            }
        }
        
        Advert oldAdvert = StoreToDatabase.GetFromBase_AdvertOf(retval.getId());
        
        retval.setPhotos(oldAdvert.getPhotos());
        retval.setDate(oldAdvert.getDate());
        retval.setEnable(oldAdvert.getEnable());
        retval.setCommends(oldAdvert.getCommends());
        retval.setVisitors(oldAdvert.getVisitors());
        retval.setOwner(oldAdvert.getOwner());
        retval.setRating(oldAdvert.getRating());
        if( retval.getCategory()==null)
            retval.setCategory(oldAdvert.getCategory());       
        
        Enumeration files = multi.getFileNames();
        String uploadFilename="";
        String pname = "";
        String filename="";
        while (files.hasMoreElements()) 
        {
            pname = (String)files.nextElement();
            filename = multi.getFilesystemName(pname);
            if(retval.getPhotos()==null )
                retval.setPhotos(filename);
            else
                retval.setPhotos(retval.getPhotos()+","+filename);
            System.out.println(retval.getPhotos());
        }
        
        return retval;
    }

}