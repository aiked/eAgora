function ViewBuyMenu(menuID)
{
    d= document.getElementById("BuyMenu"+menuID);
    d.innerHTML ="<form action='' method='POST'>";
    d.innerHTML +="Quantity:<input type='text' name='BuyNowQua' id='BuyNowQua' size='3'><br>";
    d.innerHTML +="Comment:<textarea rows='1' cols='11' name='BuyComment' id='BuyComment'>Comment here</textarea><br>";
    d.innerHTML +="<input type='submit' value='buy now'>";
    d.innerHTML +="</form>"; 
}

function ViewOfferMenu(menuID)
{
    d= document.getElementById("OfferMenu"+menuID);
    d.innerHTML ="<form action='' method='POST'>";
    d.innerHTML +="Quantity:<input type='text' name='BuyNowQua' id='BuyNowQua' size='3'><br>";
    d.innerHTML +="Comment:<textarea rows='1' cols='11' name='BuyComment' id='BuyComment'>Comment here</textarea><br>";
    d.innerHTML +="<input type='submit' value='buy now'>";
    d.innerHTML +="</form>"; 
}
