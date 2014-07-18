function change_type_status_company()
{
    //alert("Changed to company.");
    document.getElementById("male").disabled = true;
    document.getElementById("female").disabled = true;
    document.getElementById("notdef").checked = true;
}

function change_type_status_indi()
{
    //alert("Changed to indi");
    document.getElementById("male").disabled = false;
    document.getElementById("female").disabled = false;
    document.getElementById("notdef").disabled = true;
    document.getElementById("male").checked = true;
}

function displayTypeSex(sex,type)
{
    if(type=="false") //Individual
    {

         document.getElementById("notdef").disabled = true;
         
         if(sex=="Male")
         {
             document.getElementById("male").checked = true;
             document.getElementById("female").checked = false;
         }
         else
         {
             document.getElementById("male").checked = false;
             document.getElementById("female").checked = true;
         }
    }
    else
    {
        document.getElementById("comp").checked = true;
        change_type_status_company();
    }
        
}

function EvaluateRights(subRight,memRight,visRight)
{
    var subCnt=0;
    var memCnt=0;
    var visCnt=0;
    
    //Subscribers rights
    if(subRight[subCnt++]==1)
        document.getElementById("EmailSubRight").checked=true;
    subCnt++; //Ayto gia na prosperasoume to fullname right pou einai panta 1
    if(subRight[subCnt++]==1)
        document.getElementById("BirthSubRight").checked=true;    
    if(subRight[subCnt++]==1)
        document.getElementById("TypeSubRight").checked=true;
    if(subRight[subCnt++]==1)
        document.getElementById("SexSubRight").checked=true;
    if(subRight[subCnt++]==1)
        document.getElementById("CountrySubRight").checked=true;    
    if(subRight[subCnt++]==1)
        document.getElementById("StateSubRight").checked=true;   
   
    //Members right
    if(memRight[memCnt++]==1)
        document.getElementById("EmailUsrRight").checked=true;
    memCnt++;
    if(memRight[memCnt++]==1)
        document.getElementById("BirthUsrRight").checked=true;    
    if(memRight[memCnt++]==1)
        document.getElementById("TypeUsrRight").checked=true;
    if(memRight[memCnt++]==1)
        document.getElementById("SexUsrRight").checked=true;
    if(memRight[memCnt++]==1)
        document.getElementById("CountryUsrRight").checked=true;    
    if(memRight[memCnt++]==1)
        document.getElementById("StateUsrRight").checked=true;   
    
    //Members rights
    if(visRight[visCnt++]==1)
        document.getElementById("EmailVisRight").checked=true;
    visCnt++;
    if(visRight[visCnt++]==1)
        document.getElementById("BirthVisRight").checked=true;    
    if(visRight[visCnt++]==1)
        document.getElementById("TypeVisRight").checked=true;
    if(visRight[visCnt++]==1)
        document.getElementById("SexVisRight").checked=true;
    if(visRight[visCnt++]==1)
        document.getElementById("CountryVisRight").checked=true;    
    if(visRight[visCnt++]==1)
        document.getElementById("StateVisRight").checked=true;   
}

function EvaluateViewProfile(email,fname,type,sex,country,state,
                             birthdate,subRight,memRight,visRight,Status)
{
    // Status1=subscriber Status2=member Status3=visitor Status4=self
    if(Status==1)
    {
        document.getElementById("ProfileName").innerHTML=fname;
        document.getElementById("FollowInfo").innerHTML="<a href='./Utility_FollowMember?EmailToFollow="+email+"'>unfollow</a>"
        
        //Email
        if(subRight[0]==1)
            document.getElementById("ProfileEmail").innerHTML=email;
        else
            document.getElementById("ProfileEmail").innerHTML="Not available";
        //Birthdate
        if(subRight[2]==1)
            document.getElementById("ProfileBirthDate").innerHTML=birthdate;
        else
            document.getElementById("ProfileBirthDate").innerHTML="Not available";
        //Type
        if(subRight[3]==1)
            if(type=="true")
                {document.getElementById("ProfileType").innerHTML="Individual";}
            else
                {document.getElementById("ProfileType").innerHTML="Company"}
        else
            document.getElementById("ProfileType").innerHTML="Not available";
        //Sex
        if(subRight[4]==1)
            document.getElementById("ProfileSex").innerHTML=sex;
        else
            document.getElementById("ProfileSex").innerHTML="Not available";
        //Country
        if(subRight[5]==1)
            document.getElementById("ProfileCountry").innerHTML=country;
        else
            document.getElementById("ProfileCountry").innerHTML="Not available";
        //State
        if(subRight[6]==1)
            document.getElementById("ProfileState").innerHTML=state;
        else
            document.getElementById("ProfileState").innerHTML="Not available";
    }
    else if(Status==2)
    {
        document.getElementById("ProfileName").innerHTML=fname;
        document.getElementById("FollowInfo").innerHTML="<a href='./Utility_FollowMember?EmailToFollow="+email+"'>follow</a>"
        //Email
        if(memRight[0]==1)
            document.getElementById("ProfileEmail").innerHTML=email;
        else
            document.getElementById("ProfileEmail").innerHTML="Not available";
        //Birthdate
        if(memRight[2]==1)
            document.getElementById("ProfileBirthDate").innerHTML=birthdate;
        else
            document.getElementById("ProfileBirthDate").innerHTML="Not available";
        //Type
        if(memRight[3]==1)
            if(type=="true")
                {document.getElementById("ProfileType").innerHTML="Individual";}
            else
                {document.getElementById("ProfileType").innerHTML="Company"}
        else
            document.getElementById("ProfileType").innerHTML="Not available";
        //Sex
        if(memRight[4]==1)
            document.getElementById("ProfileSex").innerHTML=sex;
        else
            document.getElementById("ProfileSex").innerHTML="Not available";
        //Country
        if(memRight[5]==1)
            document.getElementById("ProfileCountry").innerHTML=country;
        else
            document.getElementById("ProfileCountry").innerHTML="Not available";
        //State
        if(memRight[6]==1)
            document.getElementById("ProfileState").innerHTML=state;
        else
            document.getElementById("ProfileState").innerHTML="Not available";
    }
    else if(Status==3)
    {
        document.getElementById("ProfileName").innerHTML=fname;
        document.getElementById("FollowInfo").innerHTML="Follow is not available."
        //Email
        if(visRight[0]==1)
            document.getElementById("ProfileEmail").innerHTML=email;
        else
            document.getElementById("ProfileEmail").innerHTML="Not available";
        //Birthdate
        if(visRight[2]==1)
            document.getElementById("ProfileBirthDate").innerHTML=birthdate;
        else
            document.getElementById("ProfileBirthDate").innerHTML="Not available";
        //Type
        if(visRight[3]==1)
            if(type=="true")
                {document.getElementById("ProfileType").innerHTML="Individual";}
            else
                {document.getElementById("ProfileType").innerHTML="Company"}
        else
            document.getElementById("ProfileType").innerHTML="Not available";
        //Sex
        if(visRight[4]==1)
            document.getElementById("ProfileSex").innerHTML=sex;
        else
            document.getElementById("ProfileSex").innerHTML="Not available";
        //Country
        if(visRight[5]==1)
            document.getElementById("ProfileCountry").innerHTML=country;
        else
            document.getElementById("ProfileCountry").innerHTML="Not available";
        //State
        if(visRight[6]==1)
            document.getElementById("ProfileState").innerHTML=state;
        else
            document.getElementById("ProfileState").innerHTML="Not available";
    }
    else if(Status==4)
    {
        document.getElementById("ProfileName").innerHTML=fname;
        document.getElementById("FollowInfo").innerHTML="Can not follow yourself"
        
        //Email
        if(subRight[0]==1)
            document.getElementById("ProfileEmail").innerHTML=email;
        else
            document.getElementById("ProfileEmail").innerHTML="Not available";
        //Birthdate
        if(subRight[2]==1)
            document.getElementById("ProfileBirthDate").innerHTML=birthdate;
        else
            document.getElementById("ProfileBirthDate").innerHTML="Not available";
        //Type
        if(subRight[3]==1)
            if(type=="true")
                {document.getElementById("ProfileType").innerHTML="Individual";}
            else
                {document.getElementById("ProfileType").innerHTML="Company"}
        else
            document.getElementById("ProfileType").innerHTML="Not available";
        //Sex
        if(subRight[4]==1)
            document.getElementById("ProfileSex").innerHTML=sex;
        else
            document.getElementById("ProfileSex").innerHTML="Not available";
        //Country
        if(subRight[5]==1)
            document.getElementById("ProfileCountry").innerHTML=country;
        else
            document.getElementById("ProfileCountry").innerHTML="Not available";
        //State
        if(subRight[6]==1)
            document.getElementById("ProfileState").innerHTML=state;
        else
            document.getElementById("ProfileState").innerHTML="Not available";
    }
}
