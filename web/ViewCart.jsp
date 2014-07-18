<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.*"%>

<html>    
<head>
    <link type="text/css" href="./CSS/cssViewCart.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="./Javascript/lib_cart.js"></script>
<title>View cart</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%
    ArrayList <Advert> adverts = (ArrayList <Advert>) request.getAttribute("carts");
    ArrayList<Advert> loggedUserAds = (ArrayList)request.getAttribute("loggedUserAds");
    
    String BuyMenu = request.getParameter("BuyMenu");
    String OfferMenu = request.getParameter("OfferMenu");
    
    int sumEuro=0;
    int sumDollar=0;
    int sumPound=0;
    
    String coin="";
    String allCartsHTML="";
    String adLink="http://localhost:8080/Project/Images/product-icon.png";
    String[] adPhoto;
    String emptyCart="";
    String cartPrice="";
    
    if(adverts!=null && !adverts.isEmpty())
    {
        int size=adverts.size();
        for(int y=0;y<size;y++)
        {
            if(adverts.get(y).getCoin().equals("euro")){ coin="&#128;"; sumEuro+=adverts.get(y).getPrice();   }
            else if(adverts.get(y).getCoin().equals("dollar")){ coin="&#36;"; sumDollar+=adverts.get(y).getPrice();   }
            else if(adverts.get(y).getCoin().equals("pound")){ coin="&#163;"; sumPound+=adverts.get(y).getPrice();  } 
            
            adLink="http://localhost:8080/Project/Images/product-icon.png";
            adPhoto=adverts.get(y).getPhotos().split(",");
            for(int i=0;i<adPhoto.length;i++)
            {
                if(!(adPhoto[i].equals(""))&&!(adPhoto[i].equals("null")))
                {
                    adLink="http://localhost:8080/Project/ProductFotos/"+adPhoto[i];
                    break;
                }

            }
            allCartsHTML += "<div class='Ad'><table class='TableAd'>";
            allCartsHTML += "<tr><td rowspan='5'> <img src='"+adLink+"' height=60 width=60></td></tr>";
            allCartsHTML += "<tr><td>title:</td><td>"+adverts.get(y).getTitle()+"</td></tr>";
            allCartsHTML += "<tr><td>price:</td><td>"+adverts.get(y).getPrice()+" "+coin+"</td></tr>";
            allCartsHTML += "</tr></table>";
            allCartsHTML += "<div class='RightTableAd'>";
            allCartsHTML += "<table>";
            allCartsHTML += "<tr><td><a class='navBarLinks' href='./Page_ViewProduct?prdID="+adverts.get(y).getId()+"'> view advert </a></td></tr>";
            allCartsHTML += "<tr><td><a class='followLink' href='./Utility_AddToCart?prdID="+adverts.get(y).getId()+"'> remove </a></td></tr></tr>";
            allCartsHTML += "<tr><td><a class='followLink' href='./Page_ViewCart?prdID="+adverts.get(y).getId()+
                    "&BuyMenu="+adverts.get(y).getId()+"'> buy now menu </a></td></tr></tr>";
            allCartsHTML += "<tr><td><a class='followLink' href='./Page_ViewCart?prdID="+adverts.get(y).getId()+
                    "&OfferMenu="+adverts.get(y).getId()+"'> make offer menu </a></td></tr>";
            allCartsHTML += "</table></div>";
            if((BuyMenu!=null)&&(adverts.get(y).getId()==Integer.parseInt(BuyMenu))) // Emfanise to buy menu
            {
                allCartsHTML += "<div class='BuyMenuAd' id='BuyMenu"+adverts.get(y).getId()+"'>";
                allCartsHTML += "<form action='' method='POST'><table>";
                allCartsHTML += "<tr><td>Quantity:</td><td><input type='text' name='BuyNowQua' id='BuyNowQua' size='13'></td></tr>";
                allCartsHTML += "<tr><td>Comment:</td><td><textarea rows='1' cols='11' name='BuyComment' id='BuyComment'>Comment here</textarea></td></tr>";
                allCartsHTML += "<tr><td colspan='2' style='text-align:center'><input type='submit' value='buy now'></td></tr>";
                allCartsHTML += "</table></form></div>"; 
            }
            else if ((OfferMenu!=null)&&(adverts.get(y).getId()==Integer.parseInt(OfferMenu))) // Emfanise to offer menu
            {
                allCartsHTML += "<div class='BuyMenuAd' id='OfferMenu"+adverts.get(y).getId()+"'>";
                allCartsHTML += "<form action='' method='POST'><table>";
                allCartsHTML += "<tr><td>Quantity:</td><td><input type='text' name='BuyNowQua' id='BuyNowQua' size='3'></td>";
                allCartsHTML += "<td>Offer:</td>";
                allCartsHTML += "<td><select class='OfferAd' name='OfferAd' id='OfferAd'>";
                allCartsHTML += "<option value='NoOffer'>No offer</option>";
                for(int b=0;b<loggedUserAds.size();b++)
                    allCartsHTML+="<option value='"+loggedUserAds.get(b).getId()+"'>"+loggedUserAds.get(b).getTitle()+"</option>";
                allCartsHTML += "</select></td></tr>";
                allCartsHTML += "<tr><td>Comment:</td><td colspan='3'><textarea rows='1' cols='20' name='BuyComment' id='BuyComment'>Comment here</textarea></td></tr>";
                allCartsHTML += "<tr><td colspan='4' style='text-align:center'><input type='submit' value='make an offer'></td></tr>";
                allCartsHTML += "</table></form></div>"; 
            }
            allCartsHTML += "</div>";

        }
    
    }
    else
    {
      emptyCart="You should put something on your shopping cart. <a href='./Page_ViewAllAdverts'>view all our adverts</a>";
      
    }    
%>

<script type="text/javascript">
function init()
{
}
</script>

</head>



<body onload="init()">

<div id="container">
    
    <jsp:include page="NavigationBar.jsp"/>
    <div id="MainContext">
        <div class="Sum">Cart sum is: <%=sumEuro%>&#128; <%=sumDollar%>&#36; <%=sumPound%>&#163; <%=emptyCart%> </div>
        <%=allCartsHTML%>
    </div>
    
    <div class="bottomBar">
    	<p>hy359 project.</p>
    </div>
</div>
</body>
</html>
