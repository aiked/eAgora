<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.*"%>

<%
    String advertsHTML="";
    ArrayList<Advert> adverts = null;
    adverts = (ArrayList)request.getAttribute("adverts");
    
    String[] adPhoto;
    String adLink="http://localhost:8080/Project/Images/product-icon.png";
    
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
                if(y%2==0)
                {
                    advertsHTML += "<div class='LeftSideAd'><table class='RightAdTable'>";
                    advertsHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
                    advertsHTML += "<tr><td>Title:</td><td colspan='3'>"+adverts.get(y).getTitle()+"</td></tr>";
                    advertsHTML += "<tr><td>Quantity:</td><td> "+adverts.get(y).getAvailability()+" </td><td>Price:</td><td> "+adverts.get(y).getPrice()+" </td></tr>";
                    advertsHTML += "<tr><td colspan='2'>Description:</td><td colspan='2' class='box'> "+adverts.get(y).getDescription()+" </td></tr>";
                    advertsHTML += "<tr> <td></td> <td></td> ";
                    advertsHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view ad</a></td>";
                    //advertsHTML += "<td><a class='navBarLinks' href='./Page_EditProduct?prdID="+adverts.get(y).getId()+"'>edit ad</a></td>";
                    advertsHTML += "</tr></table></div>";
                }
                else
                {
                    advertsHTML += "<div class='RightSideAd'><table class='RightAdTable'>";
                    advertsHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
                    advertsHTML += "<tr><td>Title:</td><td colspan='3'>"+adverts.get(y).getTitle()+"</td></tr>";
                    advertsHTML += "<tr><td>Quantity:</td><td> "+adverts.get(y).getAvailability()+" </td><td>Price:</td><td> "+adverts.get(y).getPrice()+" </td></tr>";
                    advertsHTML += "<tr><td colspan='2'>Description:</td><td colspan='2' class='box'>"+adverts.get(y).getDescription()+"</td></tr>";
                    advertsHTML += "<tr> <td></td> <td></td> ";
                    advertsHTML += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'>view ad</a></td>";
                    //advertsHTML += "<td><a class='navBarLinks' href='./Page_EditProduct?prdID="+adverts.get(y).getId()+"'>edit ad</a></td>";
                    advertsHTML += "</tr></table></div>";
                }
            }
        }
    }

%>

<html>

    
<head>
<link type="text/css" href="./CSS/cssViewAllAdverts.css" rel="stylesheet" media="screen" />
<title>View all adverts</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<jsp:include page="header.jsp"/>

    
<script type="text/javascript">  
    
    
    function google()
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

                if( titl.indexOf(elem)!=-1 )
                {
                    adv += "<div class='"+ (count++%2==0?"LeftSideAd":"RightSideAd")+"'><table class='RightAdTable'>";
                    adv += "<tr><td rowspan='5'> <img src='<%=adveLink%>' height=60 width=60></td></tr>";
                    adv += "<tr><td>Title:</td><td colspan='3'><%=adverts.get(y).getTitle()%></td></tr>";
                    adv += "<tr><td>Quantity:</td><td> <%=adverts.get(y).getAvailability()%> </td><td>Price:</td><td> <%=adverts.get(y).getPrice()%> </td></tr>";
                    adv += "<tr><td colspan='2'>Description:</td><td colspan='2' class='box'> <%=adverts.get(y).getDescription()%> </td></tr>";
                    adv += "<tr> <td></td> <td></td> ";
                    adv += "<td><a class='navBarLinks' href='./Page_ViewProduct?prdID=<%=adverts.get(y).getId()%>'>view ad</a></td>";
                    adv += "</tr></table></div>";                  
                }
        <%    }}%>
        
       document.getElementById('resl').innerHTML=adv;
    }
</script>

</head>



<body>

<div id="container">

    <jsp:include page="NavigationBar.jsp"/>
    
    <div id="MainContext">
                <fieldset>
                    <legend> search </legend>
                    <form action='./Page_ViewAllAdverts' method='get' > 
                        <label for="search"> advert:</label>
                        <input type="text" name="search" id="search" onkeyup="google()">
                    </form>
                <ajax:autocomplete 
                  source="search"
                  target="search"
                  baseUrl="ajax-findAdverts.ajax"
                  parameters="search={search}"
                  className="autocomplete"
                  minimumCharacters="1"/>
                </fieldset>
        <div class="Adverts">
            <h2>Here is a list of our ads.</h2>
            
               <div id='resl'  > <%=advertsHTML%> </div>

        </div>
    </div> 
               <div class="bottomBar"><p>hy359 project.</p></div>
</div>
</body>
</html>