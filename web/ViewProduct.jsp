<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.*"%>

<%@ taglib uri='/WEB-INF/tlds/tag_library.tld' prefix='filter' %>

<html>    
<head>
<link type="text/css" href="./CSS/cssViewProduct.css" rel="stylesheet" media="screen" />
<link type="text/css" href="./CSS/Slideshow/jquery.lightbox-0.5.css" rel="stylesheet" media="screen" />

<script type="text/javascript" src="./Javascript/lib_utilities.js"></script>
<script type="text/javascript" src="./Javascript/Slideshow/jquery.js"></script>
<script type="text/javascript" src="./Javascript/Slideshow/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="./Javascript/Slideshow/jquery.lightbox-0.5.min.js"></script>
<script type="text/javascript" src="./Javascript/Slideshow/jquery.lightbox-0.5.pack.js"></script>

<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox();
    });
    
$(function() {
   $('#gallery a').lightBox({
	imageLoading: 'http://localhost:8080/Project/Images/Slideshow/lightbox-ico-loading.gif',
	imageBtnClose: 'http://localhost:8080/Project/Images/Slideshow/lightbox-btn-close.gif',
	imageBtnPrev: 'http://localhost:8080/Project/Images/Slideshow/lightbox-btn-prev.gif',
	imageBtnNext: 'http://localhost:8080/Project/Images/Slideshow/lightbox-btn-next.gif'
   });
});
</script>

<title>View product</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<%
    Advert product=(Advert)request.getAttribute("product");
    Member member = (Member)session.getAttribute("member");
    ArrayList<Advert> loggedUserAds = (ArrayList)request.getAttribute("loggedUserAds");
    ArrayList <Integer> carts = (ArrayList <Integer>) session.getAttribute("shopCart");
    String buy=request.getParameter("Buy");
    String offer=request.getParameter("Offer");
    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");

    String commentingAreaHTML="";
    String commentAreaHTML="";
    String BuyOfferHTML="";
    String SlideShowHTML="";
    String coin="";
    String Make_an_offer;
    String Buy_now;
    String AddToCart;
    boolean find=false;
    boolean findImages=false;
    
    String[] images=product.getPhotos().split(",");

    if(!(images[0].equals("")))
    {
        for(int i=0;i<images.length;i++)
        {
            if(!images[i].equals("null"))
            {
                findImages=true;
                SlideShowHTML+="<li>";
                SlideShowHTML+=" <a href='http://localhost:8080/Project/ProductFotos/"+images[i]+"' title='"+images[i]+"'>";
                SlideShowHTML+="<img src='http://localhost:8080/Project/ProductFotos/"+images[i]+"' width='72' height='72' alt='"+images[i]+"' />";
                SlideShowHTML+="</a></li>";
            }
        }
    }
    if(findImages==false){ SlideShowHTML="Advertisment does not have images"; }
        
    
    if(product.getCoin().equals("euro")){ coin="&#128;"; }
    else if(product.getCoin().equals("dollar")){ coin="&#36;"; }
    else if(product.getCoin().equals("pound")){ coin="&#163;"; }   
       
    String ViewProfile = "<a class='followLink' href='./Page_ViewProfile?emailToView="+product.getOwner()+"'>view owner profile</a>";

    if(carts!=null)
        for(Integer x: carts )
            if(x==product.getId()) 
                find=true;

    if(find==true)
        AddToCart = "<a class='followLink' href='./Utility_AddToCart?prdID="+product.getId()+"'>remove from cart</a>";
    else
        AddToCart = "<a class='followLink' href='./Utility_AddToCart?prdID="+product.getId()+"'>add to cart</a>";
    
    
    if(buy!=null) // To buy menu
    {
        BuyOfferHTML="<tr><td>Quantity:</td><td><input type='text' name='BuyNowQua' id='BuyNowQua' size='3'></td></tr>";
        BuyOfferHTML+="<tr><td colspan='2' style='text-align:center'><textarea rows='3' cols='16' name='BuyComment' id='BuyComment'>Comment here</textarea></td></tr>";
        BuyOfferHTML+="<tr><td colspan='2' style='text-align:center'><input type='submit' value='Buy Now'></td></tr>";
        Buy_now = "<a class='followLink' href='./Page_ViewProduct?prdID="+product.getId()
            + "'>remove buy menu</a>";  
        Make_an_offer = "<a class='followLink' href='./Page_ViewProduct?prdID="+product.getId()
            + "&Offer=1'>make an offer</a>";
    }
    else if(offer!=null) // To offer menu
    {
        BuyOfferHTML="<tr><td>Quantity:</td><td><input type='text' name='BuyNowQua' id='BuyNowQua' size='3'></td></tr>";
        BuyOfferHTML+="<tr><td>Your offer:</td><td><select class='OfferAd' name='OfferAd' id='OfferAd'>";
        BuyOfferHTML+=" <option value='NoOffer' id='NoOffer'>No offer</option>";
        for(int b=0;b<loggedUserAds.size();b++)
            BuyOfferHTML+="<option value='"+loggedUserAds.get(b).getId()+"'>"+loggedUserAds.get(b).getTitle()+"</option>";
        BuyOfferHTML+="</select></td><tr>";
        BuyOfferHTML+="<tr><td colspan='2' style='text-align:center'><textarea rows='3' cols='16' name='BuyComment' id='BuyComment'>Comment here</textarea></td></tr>"; 
        BuyOfferHTML+="<tr><td colspan='2' style='text-align:center'><input type='submit' value='Make offer'></td></tr>";
        Buy_now = "<a class='followLink' href='./Page_ViewProduct?prdID="+product.getId()+""
            + "&Buy=1'>buy now</a>"; 
        Make_an_offer = "<a class='followLink' href='./Page_ViewProduct?prdID="+product.getId()
            + "'>remove offer menu</a>";
    }
    else  // Kanena apo ta menu
    {
        Buy_now = "<a class='followLink' href='./Page_ViewProduct?prdID="+product.getId()+""
            + "&Buy=1'>buy now</a>";
        Make_an_offer = "<a class='followLink' href='./Page_ViewProduct?prdID="+product.getId()
            + "&Offer=1'>make an offer</a>";
    }
    if(member!=null)
    {
        commentingAreaHTML = "<h2>Submit your comments:</h2>";
        commentingAreaHTML += "<form method='post' action='./Utility_AddComment?prdID="+product.getId()+"'><table>";       
        commentingAreaHTML += "<tr><td><div><textarea cols='40' rows='4' name='Comment'></textarea></div></td></tr>";
        commentingAreaHTML += "<tr><td style='text-align:right'><input type='submit' value='Comment'></td><tr>";
        commentingAreaHTML += "</table></form>";
    }
    else
    {
        commentingAreaHTML = "<h2>Submit your comments:</h2>";
        commentingAreaHTML += "<p>Comments are not available for visitors. <a class='navBarLinks' href='/SignUp.jsp'>Sign up</a></p>";         
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
    
    <div class="RightSide">
        <table>
            <tr><td><%=ViewProfile%></td></tr>
            <tr><td><%=AddToCart%></td></tr>
            <tr><td><%=Buy_now%></td></tr>
            <tr><td><%=Make_an_offer%></td></tr>
        </table>
        <form action='./Utility_MakeOffer?prdID=<%=product.getId()%>' method='post'>
            <table id="BuyOfferMenu"> <%=BuyOfferHTML%> </table>
        </form>
 
    </div>
    
    <div class="ProductInfo">
        <table>
            <tr><td>Title:</td><td><div id="prdTitle">  ${product.title}    </div></td></tr>
            <tr><td>Category:</td><td><div id="prdTitle">  ${product.category}    </div></td></tr>
            <tr><td>Type:</td><td><div id="prdType">${(product.type==false)?"Product":"Service"}         </div></td></tr>
            <tr><td>Status:</td><td><div id="prdStatus">    ${(product.condition==false)? "New":"Used"}</div></td></tr>
            <tr><td>Available quantity:</td><td><div id="prdQuantity">      ${product.availability} ${product.meter} </div></td></tr>
            <tr><td>Cost pes unit:</td><td><div id="prdCost">${product.price} <%=coin%>	</div></td></tr>
            <tr><td>Owner:</td><td><div id="prdOwner">  ${product.owner}	</div></td></tr>
            <tr><td>Logged date:</td><td><div id="prdDate">  ${product.date}</div></td></tr>
            <tr><td>Number of visitors:</td><td><div id="prdVisitors">  ${product.visitors}</div></td></tr>
        </table>
        <h3>Description</h3>
        <table class="Description"><tr><td>${product.description}</td></tr></table>
    </div>
    
    <div id="gallery">
    <ul>
        <%=SlideShowHTML%>
    </ul>
    </div>
    
    <div class="CommentArea">
        <c:forEach var="exa" items="${comments}">
            <h4>comment from:  <a class='followLink' href='./Page_ViewProfile?emailToView=${exa.owner}'>${exa.owner}</a></h4>
            <p>comment:  <filter:Tag_FilterString>${exa.comment}</filter:Tag_FilterString></p>
        </c:forEach>
        
    </div>
    
    <div class="CommentingArea">
        <%=commentingAreaHTML%>
    </div>
    
                    
    <div class="bottomBar">
    	<p>hy359 project.</p>
    </div>

       
</div>
</body>
</html>
