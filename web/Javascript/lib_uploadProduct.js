/* Display functions */

function changeType()
{
    alert("lol")
    var type=document.getElementsByName("prdType").value;
    alert(type)
    if(type=="product")
        displayProduct();
    else
        displayService();
}

function displayProduct()
{
    document.getElementById("newPrd").disabled=false;
    document.getElementById("usedPrd").disabled=false;
    document.getElementById("newPrd").checked=true;
    
    document.getElementById("QuaMeter").innerHTML='<select name="QuaUnit" id="QuaUnit" >'
                        +'<option value="items">items</option>'
                        +'<option value="Kilos">Kilos</option>'
                        +'<option value="Liters">Liters</option>'
                        +'</select>'
    document.getElementById("PriceMeter").innerHTML='<select name="MoneyUnit" id="MoneyUnit" >'
                        +'<option value="euro">&#128;</option>'    //euro
                        +'<option value="dollar">&#36;</option>'      //dollar
                        +'<option value="pound">&#8356;</option>'  //pound
                        +'</select>'
                    
    document.getElementById("NameQua").innerHTML="Total quantity available:";
    document.getElementById("NamePrice").innerHTML="Cost:";
}

function displayService()
{
    document.getElementById("newPrd").disabled=true;
    document.getElementById("usedPrd").disabled=true;
    document.getElementById("newPrd").checked=false;
    document.getElementById("usedPrd").checked=false;
    
    document.getElementById("QuaMeter").innerHTML='<select name="QuaUnit" id="QuaUnit" >'
                        +'<option value="hours">Hours</option>'
                        +'<option value="days">Days</option>'
                        +'<option value="months">Months</option>'
                        +'</select>'
    document.getElementById("PriceMeter").innerHTML='<select name="MoneyUnit" id="MoneyUnit" >'
                        +'<option value="euro">&#128;</option>'    //euro
                        +'<option value="dollar">&#36;</option>'      //dollar
                        +'<option value="pound">&#8356;</option>'  //pound
                        +'</select>'
                    
    document.getElementById("NameQua").innerHTML="Total quantity available:";
    document.getElementById("NamePrice").innerHTML="Cost:";
    
    
}

function EnablePhotos()
{
    document.getElementById("RightSide").innerHTML="<h2>Upload a photo</h2><p>Number of photos:<input type='text' name='noPhotos' id='noPhotos' size='1' onkeyup='displayPhotoUpload()'>  Max:5 photos<p><div id='photoSpace'></div>"
    displayPhotoUpload();
    document.getElementById("NameQua").innerHTML="Total quantity available:";
}
       
function DisablePhotos()
{
    document.getElementById("RightSide").innerHTML="";
    document.getElementById("NameQua").innerHTML="Total quantity wanting:";
}

/* Check functions */

statusQuantity=false;
statusName=false;
statusCost=false;

function checkQuantity()
{
    var quantity=document.getElementById("prdQuantity").value;
    var filter =  /^[0-9]+$/;
    if( quantity.match(filter))
    {
        //alert("Quantity true");
        statusQuantity=true;
        document.getElementById("prdQuantity").style.background = '#FFF';
    }
    else
    {
        //alert("Quantity false");     
        statusQuantity=false;
        document.getElementById("prdQuantity").style.background = '#F93';
    }
}

function checkName()
{
    var quantity=document.getElementById("prdName").value;
    var filter =  /^[a-zA-Z0-9A-Ωα-ωάέίήύόώ ]+$/;
    if( quantity.match(filter))
    {
        //alert("Name true");
        statusName=true;
        document.getElementById("prdName").style.background = '#FFF';
    }
    else
    {
        //alert("Name false");     
        statusName=false;
        document.getElementById("prdName").style.background = '#F93';
    }
}

function checkCost()
{
    var quantity=document.getElementById("prdPrice").value;
    var filter =  /^[0-9.]+$/;
    if( quantity.match(filter))
    {
        //alert("Cost true");
        statusCost=true;
        document.getElementById("prdPrice").style.background = '#FFF';
    }
    else
    {
        //alert("Cost false");     
        statusCost=false;
        document.getElementById("prdPrice").style.background = '#F93';
    }
}

function checkProductUpload()
{
    checkQuantity();
    checkName();
    checkCost();
    if((statusQuantity==true)&&(statusName==true)&&(statusCost==true))
        return true;
    else
        return false;
}

/* Photos functions */
function displayPhotoUpload()
{
    document.getElementById("photoSpace").innerHTML="";
    var curNumber = document.getElementById("noPhotos").value;
    if(curNumber==0){curNumber=1;}
    if(curNumber<=5)
    {
        for(var i=0;i<curNumber;i++)
            document.getElementById("photoSpace").innerHTML+='Upload image '+eval(i+1)+':</td><td ><input type="file" name="photo'+i+'" id="photo'+i+'"/>';
    }
    else
    {
        for(var i=0;i<5;i++)
            document.getElementById("photoSpace").innerHTML+='Upload image '+eval(i+1)+':</td><td ><input type="file" name="photo'+i+'" id="photo'+i+'"/>';
    }    
}