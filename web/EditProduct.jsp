<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.Advert"%>

<html>
<head>
    <link type="text/css" href="./CSS/cssUploadProduct.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="./Javascript/lib_editProduct.js"></script>
<title>Edit product</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" >
    function init()
    {
        EvaluateSellBuy('${product.sellbuy}');
        EvaluateProductService('${product.type}','${product.condition}');
        
    }
</script>

</head>
<body onload="init()">

<div id="container">

    <jsp:include page="NavigationBar.jsp"/>
    
    <form action="./Page_EditProduct" method="post" enctype="multipart/form-data"
          onsubmit="return checkProductUpload()">
        
    <div class="RightSide" id="RightSide">
        <h2>Upload a photo</h2>
        <p>Number of photos:<input type='text' name='noPhotos' id='noPhotos' size="1" onkeyup="displayPhotoUpload()">  Max:5 photos<p>
        <div id="photoSpace"></div>
    </div>
        
    <div id="MainContext">
        
         <td colspan="2" style="text-align:center;" > <input type="hidden" name="prdId" id="prdId" value="${product.id}"></td>
        
         <table id="ProductTable">
            <tr>
                <td > Name of product: </td>
                <td colspan="2" style="text-align:center;" > <input type="text" name="prdName" id="prdName" value="${product.title}"></td>
            </tr>
            <tr>
                <td > Category: </td>
                <td colspan="2" style="text-align:center;" > <input type="text" name="prdCategory" id="prdCategory" value="${product.category}" > </td>
            </tr>
            <tr>
                <td> Selling or buying: </td>
                <td style="text-align:center;"><input type="radio" name="prdSB" id="selling" value="Selling" onclick="EnablePhotos()" checked>Selling</td>
                <td><input type="radio" name="prdSB" id="buying" value="Buying" onclick="DisablePhotos()" >Buying</td>    
            </tr>
            <tr>
                <td> Type of advertisement: </td>
                <td style="text-align:center;"><input type="radio" name="prdType" id="product" value="Product" onclick="displayProduct()" checked>Product</td>
                <td><input type="radio" name="prdType" id="service" value="Service" onclick="displayService()" >Service</td>    
            </tr>
            <tr>
                <td> Status of advertisement: </td>
                <td style="text-align:center;"><input type="radio" name="prdStatus" id="newPrd" value="New" checked>New</td>
                <td><input type="radio" name="prdStatus" id="usedPrd" value="Used" >Used</td>    
            </tr>
            <tr>
                <td id="NameQua">Total quantity:</td>
                <td style="text-align:right;"> <input type='text' name='prdQuantity' id='prdQuantity' size="9" value="${product.availability}"></td>
                <td id="QuaMeter" style="text-align:center;"></td>
            </tr>
            <tr><td id="NamePrice">Price:</td>
                <td style="text-align:right;"> <input type='text' name='prdPrice' id='prdPrice' size="9" value="${product.price}"> </td>
                <td id="PriceMeter" style="text-align:center;"></td>
            </tr>
            <tr>
                <td colspan="2"> Description </td>
            </tr>
            <tr>
                <td colspan="2"> <textarea rows="4" cols="40" name="prdDesc" id="prdDesc">${product.description}</textarea> </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center; padding-top: 30px"><input type="submit" value="Upload" /></td>
            </tr>
        </table>
    </div>
    </form>
    <div class="bottomBar">
    	<p>hy359 project.</p>
    </div>
    
</div>
</body>
</html>