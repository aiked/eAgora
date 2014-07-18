<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.*"%>

<html>    
<head>
<link type="text/css" href="./CSS/cssViewOtherProfile.css" rel="stylesheet" media="screen" />
<script type="text/javascript" src="./Javascript/lib_utilities.js"></script>
<title>View User Profile</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%

    ArrayList<Member> followers = (ArrayList)request.getAttribute("Viewfollowers");
    ArrayList<Member> following = (ArrayList)request.getAttribute("Viewfollowing");
    ArrayList<Advert> adverts = (ArrayList)request.getAttribute("Viewadverts");
    Member memberToView = (Member)request.getAttribute("memberToView");
    Member member = (Member)session.getAttribute("member");
    
    String AdvertsHTML="";
    String followersHTML=null;
    String followingHTML=null;
    int ViewStatus=3; //Sub=1 Mem=2 Vis=3 Self=4
    String imagePath;
    String[] adPhoto;
    String adLink;
    
    for(int y=0;y<adverts.size();y++)
    {
        adPhoto=adverts.get(y).getPhotos().split(",");
        if(adPhoto[0].equals("")||adPhoto[0].equals("null"))
        {
            adLink="http://localhost:8080/Project/Images/product-icon.png";
        }
        else
            adLink="http://localhost:8080/Project/ProductFotos/"+adPhoto[0];
            
        
        if(y%2==0)
        {
            AdvertsHTML += "<div class='LeftSideAd'><table class='RightAdTable'>";
            AdvertsHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
            AdvertsHTML += "<tr><td>Title:</td><td colspan='3'>"+adverts.get(y).getTitle()+"</td></tr>";
            AdvertsHTML += "<tr><td>Quantity:</td><td> "+adverts.get(y).getAvailability()+" </td><td>Price:</td><td> "+adverts.get(y).getPrice()+" </td></tr>";
            AdvertsHTML += "<tr><td colspan='2'>Description:</td><td colspan='2' class='box'> "+adverts.get(y).getDescription()+" </td></tr>";
            AdvertsHTML += "<tr> <td></td> <td></td> ";
            AdvertsHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view ad</a></td>";
            AdvertsHTML += "<td><a class='navBarLinks' href='./Page_EditProduct?prdID="+adverts.get(y).getId()+"'>edit ad</a></td>";
            AdvertsHTML += "</tr></table></div>";
        }
        else
        {
            AdvertsHTML += "<div class='RightSideAd'><table class='RightAdTable'>";
            AdvertsHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
            AdvertsHTML += "<tr><td>Title:</td><td colspan='3'>"+adverts.get(y).getTitle()+"</td></tr>";
            AdvertsHTML += "<tr><td>Quantity:</td><td> "+adverts.get(y).getAvailability()+" </td><td>Price:</td><td> "+adverts.get(y).getPrice()+" </td></tr>";
            AdvertsHTML += "<tr><td colspan='2'>Description:</td><td colspan='2' class='box'>"+adverts.get(y).getDescription()+"</td></tr>";
            AdvertsHTML += "<tr> <td></td> <td></td> ";
            AdvertsHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view ad</a></td>";
            AdvertsHTML += "<td><a class='navBarLinks' href='./Page_EditProduct?prdID="+adverts.get(y).getId()+"'>edit ad</a></td>";
            AdvertsHTML += "</tr></table></div>";
        }
    }        
            
    if(memberToView.getImagepath().equals("default"))
        imagePath="http://localhost:8080/Project/Images/default.png";
    else
        imagePath="http://localhost:8080/Project/MembersFotos/"+memberToView.getImagepath();
    
    //View Status
    if((member!=null)&&(followers.isEmpty()==true)) //Logged in + no followers
        ViewStatus=2;
    else if((member!=null)&&(member.getEmail().equals(memberToView.getEmail())))
        ViewStatus=4;
    else if((member!=null)&&(followers.isEmpty()==false)) // Logged in + followers
    {
        int size=followers.size();
        for(int i=0;i<size;i++)
        {
            if(followers.get(i).getEmail().equals(member.getEmail())) // Logged in + subscriber
                ViewStatus=1;
            else
                ViewStatus=2;
        }
    }
    else // Not logged in
        ViewStatus=3;
               
    //Followers
    if ( followers.isEmpty()==false)
    {
        int size=followers.size();
     
        followersHTML="<ul>";
        for(int i=0;i<size;i++)
            followersHTML += "<li> <a class='followLink' href='./Page_ViewProfile?emailToView="+followers.get(i).getEmail()+"'>"+followers.get(i).getFname()+"</a></li>";
        followersHTML+="</ul>";
    }
    else
        followersHTML="Followers not available";
    
    //Following
    if ( following.isEmpty()==false)
    {
        int size=following.size();
        followingHTML="<ul>";
        for(int i=0;i<size;i++)
        {
            followingHTML += "<li> <a class='followLink' href='./Page_ViewProfile?emailToView="+following.get(i).getEmail()+"'>"+following.get(i).getFname()+"</a></li>";
        }
        followingHTML+="</ul>";
    }
    else
        followingHTML="Following's not available";
    
    
%>

<script type="text/javascript">

function init()
{
    EvaluateViewProfile("${memberToView.email}","${memberToView.fname}","${memberToView.type}",
                            "${memberToView.sex}","${memberToView.country}","${memberToView.state}",
                            "${memberToView.birthdate}","${memberToView.subright}","${memberToView.memberright}",
                            "${memberToView.viitright}",<%=ViewStatus%>);
}
</script>

</head>



<body onload="init()">

<div id="container">
    
    <jsp:include page="NavigationBar.jsp"/>
    <div id="MainContext">
        <div class="Info">
            <div class="InfoPhoto">
                    <img src="<%=imagePath%>" width="190" height="190" />
            </div>
            <div class="InfoProfile">
                    <table>
                <tr><td>Email:</td><td><div id="ProfileEmail">          </div></td></tr>
                <tr><td>Full name:</td><td><div id="ProfileName">       </div></td></tr>
                <tr><td>Type:</td><td><div id="ProfileType">            </div></td></tr>
                <tr><td>Sex:</td><td><div id="ProfileSex">              </div></td></tr>
                <tr><td>Country:</td><td><div id="ProfileCountry">      </div></td></tr>
                <tr><td>State:</td><td><div id="ProfileState">          </div></td></tr>
                <tr><td>Birthday:</td><td><div id="ProfileBirthDate">   </div></td></tr>
                <tr><td>Sign date:</td><td><div id="ProfileSignDate"> ${memberToView.signdate}   </div></td></tr>
                </table>
            </div>

            <div class="InfoFollowers">
                <h3><i>Followers</i></h3>
                <%=followersHTML%>
            </div>
            <div class="InfoFollowing">
                <h3><i>Following</i></h3>
                <%=followingHTML%>
            </div>
        </div>
    
        <div class="FollowInfo" id="FollowInfo"></div>

        <div class="Adverts">
            <%=AdvertsHTML%>
        </div>
    </div>
    <div class="bottomBar"><p>hy359 project.</p></div>
</div>
</body>
</html>
