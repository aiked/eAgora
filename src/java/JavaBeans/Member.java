/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBeans;

/**
 *
 * @author Chrysohous
 */
public class Member {
    
    String email;
    String fname;
    String password;
    boolean type;
    String sex;
    String country;
    String state;
    String birthdate;
    String signdate;
    String imagepath;
    String subright;
    String memberright;
    String viitright;
    
    public Member()
    {
        
    }
    
    public Member(Member member)
    {
        this.email = member.getEmail();
        this.fname = member.getFname();
        this.type = member.getType();
        this.sex = member.getSex();
        this.country = member.getCountry();
        this.state = member.getState();
        this.birthdate = member.getBirthdate();
        this.signdate = member.signdate;
        this.imagepath = member.getImagepath();
        this.subright = member.getSubright();
        this.memberright = member.getMemberright();
        this.viitright = member.getViitright();
        this.password=member.getPassword();
    }

    public Member(String email, String fname, String password, boolean type, String sex, String country, String state, String birthdate, String signdate, String imagepath, String subright, String memberright, String viitright) {
        this.email = email;
        this.fname = fname;
        this.type = type;
        this.sex = sex;
        this.country = country;
        this.state = state;
        this.birthdate = birthdate;
        this.signdate = signdate;
        this.imagepath = imagepath;
        this.subright = subright;
        this.memberright = memberright;
        this.viitright = viitright;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getMemberright() {
        return memberright;
    }

    public void setMemberright(String memberright) {
        this.memberright = memberright;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubright() {
        return subright;
    }

    public void setSubright(String subright) {
        this.subright = subright;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getViitright() {
        return viitright;
    }

    public void setViitright(String viitright) {
        this.viitright = viitright;
    }
    

    @Override
    public String toString()
    {
        return "Email:"+this.email+"\n"
                + "Full name:"+this.fname+"\n"
                + "Password:"+this.password+"\n"
                + "Type:"+this.type+"\n"
                + "Sex:"+this.sex+"\n"
                + "country:"+this.country+"\n"
                + "state:"+this.state+"\n"
                + "birthdate:"+this.birthdate+"\n"
                + "signdate:"+this.signdate+"\n"
                + "subright:"+this.subright+"\n"
                + "memberright:"+this.memberright+"\n"
                + "viitright:"+this.viitright+"\n"
                ;
    }        

}
