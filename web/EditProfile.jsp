<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*,java.text.*" %>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.Member" %>

<html>
<head>
<title>Edit Profile</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link type="text/css" href="./CSS/cssEditProfile.css" rel="stylesheet" media="screen" />
<script type="text/javascript" src="./Javascript/lib_countries.js"></script>
<script type="text/javascript" src="./Javascript/lib_date.js"></script>
<script type="text/javascript" src="./Javascript/lib_check.js"></script>
<script type="text/javascript" src="./Javascript/lib_utilities.js"></script>

<%
    Member member = (Member)session.getAttribute("member");
    
    String fname= member.getFname();
    String[] birthField = member.getBirthdate().split("/");
    String country = member.getCountry();
    String state = member.getState();
%>

<script type="text/javascript">

function init()
{
        displayDate_EditProfile(<%=birthField[0]%>,<%=birthField[1]%>,<%=birthField[2]%>);
        displayCountries_EditProfile("<%=country%>");
        displayCities_EditProfile(document.getElementById("country").selectedIndex,'<%=state%>');
        displayTypeSex("${member.sex}","${member.type}");
        EvaluateRights("${member.subright}","${member.memberright}","${member.viitright}");
}


</script>
</head>

<body onload="init()">

<div id="container">

    <jsp:include page="NavigationBar.jsp"/>

  	
    
    <div id="MainContext">
  
  		<h3> Welcome to edit profile page.</h3>
        <p> In this page you can change your profile info including your password, your email can not be changed.
  			Help : If you want the group (subscriber,member,visitor) be able to see the particular information tick
            the box. After you are done you can press "Edit profile" and save your changes.</p>
        <form action="./Page_EditProfile" method="post" enctype="multipart/form-data" >
    	<table cellspacing="10" >
        	<th colspan="2"> Edit your profile</th><th >Subscribers</th><th>Site Users</th><th>Visitor</th>
				<tr>
                                    <td>Email:</td><td><input type="text" id="SignEmail" name="SignEmail" 
                                                              value=${member.email} readonly/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="EmailSubRight" id="EmailSubRight" 	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="EmailUsrRight" id="EmailUsrRight"	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="EmailVisRight" id="EmailVisRight"	/></td>
            	</tr>
            	<tr>
               	  <td>Full name:</td><td><input type="text" id="SignFullName" name="SignFullName" 
                                                value="<%=fname %>" /></td>
            	</tr>
          <tr>
                    <td>Date of Birth:</td>
                    <td><select name="birthDay" id="birthDay"></select>
                        <select name="birthMonth" id="birthMonth"></select>
                        <select name="birthYear" id="birthYear"></select>
                    </td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="BirthSubRight" id="BirthSubRight"/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="BirthUsrRight" id="BirthUsrRight"/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="BirthVisRight" id="BirthVisRight"/></td>
                </tr>
          <tr>
           	<td>Type:</td>
                    <td ><input type="radio" name="type" id="indi" value="Individual" checked="checked"
                               onclick="change_type_status_indi()" />Individual	
                        <input type="radio" name="type" id="comp" value="Company"
                               onclick="change_type_status_company()" />Company							
           	</td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="TypeSubRight" id="TypeSubRight" 	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="TypeUsrRight" id="TypeUsrRight"	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="TypeVisRight" id="TypeVisRight"	/></td>
                </tr>
                <tr>
                    <td>Sex:</td>
                    <td>
                    	<input type="radio" name="sex" id="male" value="Male"    />Male
                    	<input type="radio" name="sex" id="female" value="Female" />Female
                        <input type="radio" name="sex" id="notdef" value="Notdef" />Not defined
                    </td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="SexSubRight" id="SexSubRight" /></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="SexUsrRight" id="SexUsrRight"	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="SexVisRight" id="SexVisRight"	/></td>
                </tr>
          <tr>
                    <td>Country:</td>
                    <td ><select name="country" id="country" 
                                 onchange="displayCities_EditProfile(this.selectedIndex,'<%=state%>')"></select>
                    </td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="CountrySubRight" id="CountrySubRight" /></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="CountryUsrRight" id="CountryUsrRight"	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="CountryVisRight" id="CountryVisRight"	/></td>
                </tr>
                <tr>
                    <td>State:</td><td ><select name="state" id="state"></select></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="StateSubRight" id="StateSubRight" /></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="StateUsrRight" id="StateUsrRight"	/></td>
                    <td class="CenterIt"><input type="checkbox" class="CheckBox" name="StateVisRight" id="StateVisRight"	/></td>
                </tr>
                <tr>
                    <td>Upload image:</td><td ><input type="file" name="UploadFile" id="ProfileImg" /></td>
                </tr>
                <th colspan="2">Change your password</th>
                <tr><td> Old password:</td><td> <input type="text" name="OldPass" id="OldPass" size="11"/></td></tr>
                <tr><td> New password:</td><td> <input type="text" name="NewPass" id="NewPass" size="11"/></td></tr>
                <tr ><td colspan="6" style="text-align:center" ><input type="submit" value="Edit Profile"></td></tr>
                   
    </table>
    </form>
    </div>
        <% out.println("Session id "+session.getId()); %>
	<div class="bottomBar">
    	<p>hy359 project.</p>
    </div>

</div>

</body>
</html>