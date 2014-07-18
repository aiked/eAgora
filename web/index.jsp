
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="JavaBeans.Advert"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*,java.text.*" %>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.Member" %>

<%

    String advertsHTML="";
    ArrayList<Advert> adverts = (ArrayList)request.getAttribute("adverts");
    ArrayList<Member> members = (ArrayList)request.getAttribute("members");
    ArrayList<String> categories = (ArrayList)request.getAttribute("categories");
    
    
    ArrayList<Member> famousmem = (ArrayList<Member>) request.getAttribute("famousmem");
    ArrayList<Member> markmem = (ArrayList<Member>) request.getAttribute("markmem");
    ArrayList<Advert> mostadv = (ArrayList<Advert>) request.getAttribute("mostadv");
    
    String mostVisited="";
    String mostFamous="";
    String mostRated="";
    String[] adPhoto;
    String adLink="http://localhost:8080/Project/Images/product-icon.png";
    String imagePath="";
    String email="";
    String name="";
    
    for(Advert ad : mostadv)
    {
        adPhoto=ad.getPhotos().split(",");
        adLink="http://localhost:8080/Project/Images/product-icon.png";
        for(int i=0;i<adPhoto.length;i++)
        {
            if(!(adPhoto[i].equals(""))&&!(adPhoto[i].equals("null")))
            {
                adLink="http://localhost:8080/Project/ProductFotos/"+adPhoto[i];
                break;
            }
        }
        mostVisited+="<table class='MemberTable'>";
        mostVisited+="<tr><td rowspan='2' width='52'><img src='"+adLink+"' width='50' height='50'></td></td><td>"+ad.getTitle()+"</td></tr>";
        mostVisited+="<tr><td><a <a class='navBarLinks' href='./Page_ViewProduct?prdID="+ad.getId()+"'>view ad</a></td></tr>";
        mostVisited+="</table>";
        
    }
    
    for(Member ad : famousmem)
    {
        if(ad.getImagepath().equals("default"))
            imagePath="http://localhost:8080/Project/Images/default.png";
        else
            imagePath="http://localhost:8080/Project/MembersFotos/"+ad.getImagepath();
        mostRated+="<table class='MemberTable'>";
        mostRated+="<tr><td rowspan='2' width='52'><img src='"+imagePath+"' width='50' height='50'></td></td><td>"+ad.getFname()+"</td></tr>";
        mostRated+="<tr><td><a <a class='navBarLinks' href='./Page_ViewProduct?prdID="+ad.getEmail()+"'>view ad</a></td></tr>";
        mostRated+="</table>";
        
    }
    
    for(Member ad : famousmem)
    {
        if(ad.getImagepath().equals("default"))
            imagePath="http://localhost:8080/Project/Images/default.png";
        else
            imagePath="http://localhost:8080/Project/MembersFotos/"+ad.getImagepath();
        mostFamous+="<table class='MemberTable'>";
        mostFamous+="<tr><td rowspan='2' width='52'><img src='"+imagePath+"' width='50' height='50'></td></td><td>"+ad.getFname()+"</td></tr>";
        mostFamous+="<tr><td><a <a class='navBarLinks' href='./Page_ViewProduct?prdID="+ad.getEmail()+"'>view ad</a></td></tr>";
        mostFamous+="</table>";
        
    }
    
    
    
    if(adverts!=null)
    {
        int size=adverts.size();
        for(int y=0;y<adverts.size();y++)
        {
            if(adverts.get(y).getAvailability()>0)
            {
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
                /*
            <table class='MemberTable'>
            <tr><td rowspan='2' width='52' ><img src='' width='50' height='50'></td><td>Michael Athanasakis</td></tr>
            <tr><td><a class='ProfileLink' href='./Page_ViewProfile?emailToView='>view profile</a></td></tr>
            </table>*/
                if(y%2==0)
                {
                    advertsHTML+="<table class='MemberTable'>";
                    advertsHTML+="<tr><td rowspan='2' width='52'><img src='"+adLink+"' width='50' height='50'></td></td><td>"+adverts.get(y).getTitle()+"</td></tr>";
                    advertsHTML+="<tr><td><a <a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view ad</a></td></tr>";
                    advertsHTML+="</table>";

                }
                else
                {
                    advertsHTML+="<table class='MemberTable'>";
                    advertsHTML+="<tr><td rowspan='2' width='52'><img src='"+adLink+"' width='50' height='50'></td></td><td>"+adverts.get(y).getTitle()+"</td></tr>";
                    advertsHTML+="<tr><td><a <a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view ad</a></td></tr>";
                    advertsHTML+="</table>";
                }
            }
        }
    }

%>

<html>  
<head>
<link type="text/css" href="./CSS/cssMain.css" rel="stylesheet" media="screen" />
<link type="text/css" href="./CSS/SlideshowMain/style.css" rel="stylesheet" media="screen" />
<script src="Javascript/SlideshowMain/jquery.min.js"></script>
<script src="Javascript/SlideshowMain/jquery.rs.slideshow.js"></script>
<script src="Javascript/SlideshowMain/bootstrap.js"></script>
<script src="Javascript/SlideshowMain/jquery.min.js.js"></script>
<script src="Javascript/SlideshowMain/jquery.rs.slideshow.min.js"></script>

<jsp:include page="header.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        $('#slideshow-div').rsfSlideshow();
    });
    
    function searchMember()
    {
       document.getElementById("resl").innerHTML="<h2>Member search results";
        
        document.getElementById("SearchCategory").style.display="none";
        var elem = document.getElementById('search').value;
        elem=elem.toUpperCase();
        ViewAllMembers="";
        
        <%
        if(members!=null)
        {
            int size2=members.size();
            for(int y=0;y<size2;y++)
            {
                if(members.get(y).getImagepath().equals("default"))
                    imagePath="http://localhost:8080/Project/Images/default.png";
                else
                    imagePath="http://localhost:8080/Project/MembersFotos/"+members.get(y).getImagepath();
                name=members.get(y).getFname();
                email=members.get(y).getEmail();

        %>

        var titl = '<%= members.get(y).getFname() %>';
        var imagePath = '<%=imagePath%>';
        var name= '<%=name%>';
        var email='<%=email%>';

        if( elem=="" || titl.indexOf(elem)!=-1  )
        {
            ViewAllMembers+="<table class='MemberTable'>"
            ViewAllMembers+="<tr><td rowspan='2' width='52'><img src='"+imagePath+"' width='50' height='50'></td></td><td>"+name+"</td></tr>"
            ViewAllMembers+="<tr><td><a class='ProfileLink' href='./Page_ViewProfile?emailToView="+email+"'>view profile</a></td></tr>"
            ViewAllMembers+="</table>"   
            
            
            /*
            <table class='MemberTable'>
            <tr><td rowspan='2' width='52' ><img src='' width='50' height='50'></td><td>Michael Athanasakis</td></tr>
            <tr><td><a class='ProfileLink' href='./Page_ViewProfile?emailToView='>view profile</a></td></tr>
            </table>*/
        }
        <%    }}%>
        
       document.getElementById('resl').innerHTML+=ViewAllMembers;
      

    }
    
    function searchProduct()
    {
        document.getElementById("resl").innerHTML="<h2>Products search results";
        
        document.getElementById("SearchCategory").style.display="block";
        var selection = document.getElementById("SelectCat");
        
        var counter=0;
        selection.options[counter++]=new Option('All','All',false,false);
        <% for(String cat:categories){ %>
                selection.options[counter++]=new Option('<%=cat%>','<%=cat%>',false,false);
        <% }%>    

        productOnKeyUp();
    }
    
    function productOnKeyUp()
    {
        var elem = document.getElementById('search').value;
        elem=elem.toUpperCase();
        adv="";
        var count=0;
        <%
            String adveLink="http://localhost:8080/Project/Images/product-icon.png";
            String[] adPhoto2;
            if(adverts!=null)
           {
            int size=adverts.size();
            for(int y=0;y<size;y++)
            {
                if(adverts.get(y).getAvailability()<=0)
                    continue;
                
                adPhoto2=adverts.get(y).getPhotos().split(",");
                for(int i=0;i<adPhoto2.length;i++)
                {
                    if(!(adPhoto2[i].equals(""))&&!(adPhoto2[i].equals("null")))
                    {
                        adveLink="http://localhost:8080/Project/ProductFotos/"+adPhoto2[i];
                        break;
                    }
                }

        %>

        var titl = '<%=adverts.get(y).getTitle()%>';
        titl=titl.toUpperCase();
        var cat = document.getElementById("SelectCat");
        var mar = cat.options[cat.selectedIndex].value;

        if( (titl.indexOf(elem)!=-1 && ( mar=='<%=adverts.get(y).getCategory()%>' || mar=='All' ))  )
        {
            adv+="<table class='MemberTable'>";
            adv+="<tr><td rowspan='2' width='52'><img src='<%=adveLink%>' width='50' height='50'></td></td><td><%=adverts.get(y).getTitle()%></td></tr>";
            adv+="<tr><td><a <a class='navBarLinks' href='./Page_ViewProduct?prdID=<%=adverts.get(y).getId()%>'>view ad</a></td></tr>";
            adv+="</table>";
        }
        <%    }}%>
        
       document.getElementById('resl').innerHTML=adv;
    }
    
    
    function choose()
    {
        
        if(document.getElementById('SearchProduct').checked)
        {
            productOnKeyUp();
        }
        else
        {
            searchMember();
        }
    }
    
    function init()
    {
       searchProduct();
    }
    
</script>


<title>eAgora</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body onload="init()">

<div id="container">

    <jsp:include page="NavigationBar.jsp"/>
    
    <div id="MainContext">
        <div class="IntroLeft">
            <h2>Welcome to eAgora.</h2>
            <p> Here you can post your advertisements for other people to see. We also provide a community environment so you can communicate with 
                other members, follow them and have transactions with them. <i>Follow it</i> enables you to follow another's member new advertisements
                and have latest feed with the hottest stuff. Just try it <a href="/SignUp">Sign up</a> now.
            </p>
            <table class="SearchTable">
            <tr>
                <td rowspan="2">Search</td>
                <td><input type="text" name="search" id="search" size='15' onkeyup="choose()"></td>
                <td><input type="radio" name="SearchType" id="SearchMember" value="SearchMember" onclick="searchMember()" >member</td>
            </tr>
            <tr>
                <td><div id="SearchCategory"><select name="SelectCat" onchange="productOnKeyUp()" id="SelectCat"></select></div></td>
                <td><input type="radio" name="SearchType" id="SearchProduct" value="SearchProduct" onclick="searchProduct()" checked>product</td>
            </tr>
            </table>
                <ajax:autocomplete 
                  source="search"
                  target="search"
                  baseUrl="ajax-find.ajax"
                  parameters="search={search},SearchProduct={SelectCat}"
                  className="autocomplete"
                  minimumCharacters="1"/>
        </div>
        <div class="IntroSlideshow">
            <div id="slideshow-div" class="rs-slideshow">

            <div class="slide-container">
                <img src="Images/SlideshowMain/slide1.png"
                    alt="The first image in a slideshow demo." title="This is the first slide" />
            </div>
            <ol class="slides">
                <li><a href="Images/SlideshowMain/slide1.png">slide1</a></li>
                <li><a href="Images/SlideshowMain/slide2.png">slide2</a></li>
                <li><a href="Images/SlideshowMain/slide3.png">slide3</a></li>
            </ol>
     
            </div>
        </div>
        
        <hr width='900'>
        <div id='resl' class="Adverts"></div>
        <hr width='900'>
        <div id='top3'>
            <div class="top3_1">
                <h2>Most visited adverts</h2>
                <div><%=mostVisited%></div>
            </div>
            <div class="top3_2">
                <h2>Highest rated members</h2>
                <div><%=mostRated%></div>
            </div>
            <div class="top3_3">
                <h2>Most famous members</h2>
                <div><%=mostFamous%></div>
            </div>
        </div>
        <hr width='900' >
        
        <div class="Adverts" >
        </div>
        
    </div>
    <div class="bottomBar">
    	<p>hy359 project.</p>
    </div>

</div>

</body>
</html>
