statusEmail     =false;
statusFullName  =false;
statusPassword  =false;
statusBirthDate =false;
statusCountry   =false;

function setCheckStateImg(id,status)
{
    if ( status=='true')
    {
        document.getElementById(id).innerHTML = 
            '<img src="./Images/true.png" alt="true" width="20" height="20" />'
    }
    else if ( status=='false')
    {
        document.getElementById(id).innerHTML = 
            '<img src="./Images/false.png" alt="true" width="20" height="20" />'
    }
    else if ( status=='empty')
    {
        document.getElementById(id).innerHTML = '';
    }
}

function getRequestObject() 
    {
        if (window.XMLHttpRequest) {return(new XMLHttpRequest());} 
        else if (window.ActiveXObject) {return(new ActiveXObject("Microsoft.XMLHTTP"));} 
        else {return(null);}
    }

function checkEmail()
{
    var request  = getRequestObject();
    var email = document.getElementById("SignEmail").value;
    var url = "ValidateForm";
    var params = "emailCheck="+email; // usrCheck= -> 9 letters
    request.open("POST",url,true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.setRequestHeader("Content-length", params.length);
    request.setRequestHeader("Connection", "close");

    request.onreadystatechange = function() 
    {
        if(request.readyState == 4 && request.status == 200) 
        {
            var availStatus = request.responseText;
            var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            var email = document.getElementById("SignEmail").value;
            if ( email.match(filter) )
            {lexStatus = "true";}
            else 
            {lexStatus = "false";}
            if ( (availStatus=="notfound")&&(lexStatus=="true") )
                { 
                    document.getElementById("SignEmail").style.background = '#FFF';
                    setCheckStateImg("EmailInfo","true"); 
                    //alert("email true")
                   statusEmail = true;
                }
            else 
                { 
                    document.getElementById("SignEmail").style.background = '#F93';
                    setCheckStateImg("EmailInfo","false"); 
                    //alert("email false")
                    statusEmail = false;
                }
        }
    }
    
    request.send(params);
}

function checkFullName()
{
    var accept_sign =  /^[a-zA-ZA-Ωα-ωάέίήύόώ ]+$/;
    var full_name = document.getElementById("SignFullName").value;
    if ( (full_name.length>3)&&(full_name.length<31)&&(full_name.match(accept_sign)) )
    {
        document.getElementById("SignFullName").style.background = '#FFF';
        setCheckStateImg("FullnameInfo","true");
        statusFullName= true;
    }
    else 
    {
        document.getElementById("SignFullName").style.background = '#F93';
        setCheckStateImg("FullnameInfo","false");
        statusFullName= false;
    }
}

function checkPassword()
{
    var pwd = document.getElementById("SignPassword").value;
    if ( (pwd.length>5)&&(pwd.length<11) )
    {
        document.getElementById("SignPassword").style.background = '#FFF';
        setCheckStateImg("PasswordInfo","true");
        statusPassword= true;
    }
    else 
    {
        document.getElementById("SignPassword").style.background = '#F93';
        setCheckStateImg("PasswordInfo","false");
        statusPassword= false;
    }
}

function checkBirthDate()
{
    
    if ( document.getElementById("indi").checked === true)
     {var min_age = 18;
        var day = document.getElementById("birthDay").value;
        var month = document.getElementById("birthMonth").value;
        var year = document.getElementById("birthYear").value;
        var usr_date = new Date((parseInt(year)+parseInt(min_age)),month-1,day);
        var today_date = new Date();

        if ( usr_date >= today_date )
        {
            document.getElementById("birthDay").style.background = '#F93';
            document.getElementById("birthMonth").style.background = '#F93';
            document.getElementById("birthYear").style.background = '#F93';
            setCheckStateImg("birthDateInfo","false");
            statusBirthDate= false;
        }
        else 
        {
            document.getElementById("birthDay").style.background = '#FFFFFF';
            document.getElementById("birthMonth").style.background = '#FFFFFF';
            document.getElementById("birthYear").style.background = '#FFFFFF';
            setCheckStateImg("birthDateInfo","true");
            statusBirthDate= true;
        }
    }
    else 
    {
        document.getElementById("birthDay").style.background = '#FFFFFF';
        document.getElementById("birthMonth").style.background = '#FFFFFF';
        document.getElementById("birthYear").style.background = '#FFFFFF';
        setCheckStateImg("birthDateInfo","true");
        statusBirthDate= true;
    }
    
}

function checkCountry()
{
    var country = document.getElementById("country").value;
    if ( country == "undefined_country")
    {
        document.getElementById("country").style.background = '#F93';
        setCheckStateImg("CountryInfo","false");
        statusCountry=false;
    }
    else 
    {
        document.getElementById("country").style.background = '#FFFFFF';
        setCheckStateImg("CountryInfo","true");
        statusCountry=true;
    }
}

function checkSignup()
{
    checkEmail();
    checkFullName();
    checkPassword();
    checkBirthDate();
    checkCountry();
    //alert(document.getElementById("notdef").checked);
    if( (statusEmail==true)&&(statusFullName==true)&&(statusPassword==true)
            &&(statusBirthDate=true)&&(statusCountry==true))
        return true;
    return false;
}

function checkPassword_EditProfile()
{
    var pwdOld = document.getElementById("OldPass").value;
    if ( (pwdOld.length>5)&&(pwdOld.length<11) )
    {
        document.getElementById("OldPass").style.background = '#FFF';
        statusPassword= true;
    }
    else 
    {
        document.getElementById("OldPass").style.background = '#F93';
        statusPassword= false;
    }
    
    var pwdNew = document.getElementById("NewPass").value;
    if ( (pwdNew.length>5)&&(pwdNew.length<11) )
    {
        document.getElementById("NewPass").style.background = '#FFF';
        statusPassword= true;
    }
    else 
    {
        document.getElementById("NewPass").style.background = '#F93';
        statusPassword= false;
    }
}

