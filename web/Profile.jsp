
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
<title>Profile</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="./CSS/cssProfile.css" rel="stylesheet" media="screen" />
<link rel="stylesheet" href="CSS/RatingSystem/jquery.rating.css">
<script src="Javascript/lib_profile.js"></script>
<script src="Javascript/RatingSystem/jquery.js"></script>
<script src="Javascript/RatingSystem/jquery.rating.js"></script>
<script src="Javascript/RatingSystem/jquery.MetaData.js"></script>

<%

    ArrayList<Member> followers = (ArrayList)request.getAttribute("followers");
    ArrayList<Member> following = (ArrayList)request.getAttribute("following");
    ArrayList<Advert> adverts = (ArrayList)request.getAttribute("adverts");
    ArrayList<Advert> foladverts = (ArrayList)request.getAttribute("followingAdverts");
    ArrayList <String> infos = (ArrayList <String>) request.getAttribute("infos");
    Member member = (Member)session.getAttribute("member");
    
    int foladvertsSize=0;
    
    String followersHTML="";
    String followingHTML="";
    String yourAdvertsHTML="";
    String folHTML="";
    String infosHTML="";
    String coin="";
    String imagePath;
    String[] adPhoto;
    String imageURL=null;
    String adLink="http://localhost:8080/Project/Images/product-icon.png";
    String email = member.getEmail();
    
    /*
    for(int i=0;i<foladverts.size();i++)
        out.print(foladverts.get(i).getTitle());
    */
    
    
    if(member.getImagepath().equals("default"))
        imagePath="http://localhost:8080/Project/Images/default.png";
    else
        imagePath="http://localhost:8080/Project/MembersFotos/"+member.getImagepath();
    
    for(int y=0;y<adverts.size();y++)
    {
        if(adverts.get(y).getAvailability()>0)
        {
            if(adverts.get(y).getCoin().equals("euro")){ coin="&#128;"; }
            else if(adverts.get(y).getCoin().equals("dollar")){ coin="&#36;"; }
            else if(adverts.get(y).getCoin().equals("pound")){ coin="&#163;"; } 

            adPhoto=adverts.get(y).getPhotos().split(",");
            adLink="http://localhost:8080/Project/Images/product-icon.png";
            for(int i=0;i<adPhoto.length;i++)
            {
                if(!(adPhoto[i].equals(""))&&!(adPhoto[i].equals("null")))
                {
                    adLink="http://localhost:8080/Project/ProductFotos/"+adPhoto[i];
                    break;
                }

            }
            yourAdvertsHTML += "<div class='LeftSideAd'><table class='RightAdTable'>";
            yourAdvertsHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
            yourAdvertsHTML += "<tr><td>title:</td><td>"+adverts.get(y).getTitle()+"</td></tr>";
            yourAdvertsHTML += "<tr><td>price:</td><td>"+adverts.get(y).getPrice()+" "+coin+"</td></tr>";
            yourAdvertsHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view more</a></td>";
            yourAdvertsHTML += "<td><a class='navBarLinks' href='./Page_EditProduct?prdID="+adverts.get(y).getId()+"'>edit</a></td>";
            yourAdvertsHTML += "<td><a class='navBarLinks' href='./Utility_DeleteAdvert?prdID="+adverts.get(y).getId()+"'>delete</a></td>";
            yourAdvertsHTML += "</tr></table></div>";
        }
    }
    
    if(foladverts.size()>10)
        foladvertsSize=10;
    else
        foladvertsSize=foladverts.size();
        
    for(int y=0;y<foladverts.size();y++)
    {
        if(foladverts.get(y).getAvailability()>0)
        {
            adPhoto=foladverts.get(y).getPhotos().split(",");
            adLink="http://localhost:8080/Project/Images/product-icon.png";

            if(foladverts.get(y).getCoin().equals("euro")){ coin="&#128;"; }
            else if(foladverts.get(y).getCoin().equals("dollar")){ coin="&#36;"; }
            else if(foladverts.get(y).getCoin().equals("pound")){ coin="&#163;"; } 

            for(int i=0;i<adPhoto.length;i++)
            {
                if(!(adPhoto[i].equals(""))&&!(adPhoto[i].equals("null")))
                {
                    adLink="http://localhost:8080/Project/ProductFotos/"+adPhoto[i];
                    break;
                }

            }
            folHTML += "<div class='LeftSideAd'><table class='RightAdTable'>";
            folHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
            folHTML += "<tr><td>title:</td><td>"+foladverts.get(y).getTitle()+"</td></tr>";
            folHTML += "<tr><td>price:</td><td>"+foladverts.get(y).getPrice()+" "+coin+"</td></tr>";
            folHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+foladverts.get(y).getId()+"'>view more</a></td>";
            folHTML += "<td><a class='navBarLinks' href='./Utility_DeleteAdvert?prdID="+foladverts.get(y).getId()+"'>delete</a></td>";
            folHTML += "</tr></table></div>";
        }
    }
    //Followers
    if ( followers.isEmpty()==false)
    {
        int size=followers.size();
     
        followersHTML="<ul>";
        for(int i=0;i<size;i++)
        {
            followersHTML += "<li> <a class='followLink' href='./Page_ViewProfile?emailToView="+followers.get(i).getEmail()+"'>"+followers.get(i).getFname()+"</a></li>";
        }
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
    
    for(String inf: infos)
    {
        infosHTML+=inf;
    }

%>
<script type="text/javascript" >
    var timer;

    function getRequestObject() 
    {
        if (window.XMLHttpRequest) {return(new XMLHttpRequest());} 
        else if (window.ActiveXObject) {return(new ActiveXObject("Microsoft.XMLHTTP"));} 
        else {return(null);}
    }

    function SwapMember()
    {
        clearTimeout(timer);
        document.getElementById("adHTML").innerHTML="<%=yourAdvertsHTML%>";
    }

    function SwapFollowing()
    {
        updateAdverts("<%=email%>");

        document.getElementById("adHTML").innerHTML="<%=folHTML%>";
    }

    function updateAdverts(email)
    {
        //alert("Inside update");
        var request  = getRequestObject();
        var url = "Utility_UpdateProfile";
        var params = "email="+email; // usrCheck= -> 9 letters
        request.open("POST",url,true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.setRequestHeader("Content-length", params.length);
        request.setRequestHeader("Connection", "close");

        request.onreadystatechange = function() 
        {
            if(request.readyState == 4 && request.status == 200) 
            {
                var response = request.responseText;
                document.getElementById("adHTML").innerHTML=response;
            }
        }
        request.send(params);
        timer=setTimeout('updateAdverts("<%=email%>")',30000);
    }       

    
    function init()
    {
        SwapMember();
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
                <tr><td>Email:</td><td><div id="ProfileEmail">  ${member.email}     </div></td></tr>
                <tr><td>Full name:</td><td><div id="ProfileName">${member.fname}	</div></td></tr>
                <tr><td>Type:</td><td><div id="ProfileType">    ${(member.type==false)? "Individual":"Company"}</div></td></tr>
                <tr><td>Sex:</td><td><div id="ProfileSex">      ${member.sex}       </div></td></tr>
                <tr><td>Country:</td><td><div id="ProfileCountry">${member.country}	</div></td></tr>
                <tr><td>State:</td><td><div id="ProfileState">  ${member.state}	</div></td></tr>
                <tr><td>Birthday:</td><td><div id="ProfileBirthDate">  ${member.birthdate}</div></td></tr>
                <tr><td>Sign date:</td><td><div id="ProfileSignDate">  ${member.signdate}</div></td></tr>
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
        
        <div id="ProfileBar">
                <div class="ProfileLeftSide">
                    <table></table>
                </div>
                <div class="ProfileRightSide">
                    <table><tr>
                            <td><a class='navBarLinks' href='./UploadProduct.jsp'>Upload Product</a></td>
                            <td>
                                <input type="radio" name="AdNav" id="AdNavMember"  onclick="SwapMember()" checked />View your ads
                                <input type="radio" name="AdNav" id="AdNavFollowing" onclick="SwapFollowing()" />View following ads
                            </td>
                        </tr></table>
                </div>
        </div>

        <div class="Adverts" id="adHTML"></div>
        
        <div class="Notifications">
            <%=infosHTML%>
            
        </div>

    
    </div>
        
    
        
    <div class="bottomBar"><p>hy359 project.</p></div>

                    
</div>
</body>
</html>
