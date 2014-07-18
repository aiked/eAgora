
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
<head>
<title>Sign Up</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link type="text/css" href="./CSS/cssSignUp.css" rel="stylesheet" media="screen" />
<script type="text/javascript" src="./Javascript/lib_countries.js"></script>
<script type="text/javascript" src="./Javascript/lib_date.js"></script>
<script type="text/javascript" src="./Javascript/lib_check.js"></script>
<script type="text/javascript" src="./Javascript/lib_utilities.js"></script>

<script type="text/javascript">

function init()
{
	display_countries();
        display_date();
        change_type_status_indi();
}


</script>
</head>

<body onload="init()">

<div id="container">

    <jsp:include page="NavigationBar.jsp"/>
    
    <div class="LeftSide">
    
    	<h2>Already Member? Login in.</h2>
        <form action="./Page_Login" method="post">
        <table class="CenterIt">
            <th colspan="2" >${LoginInfo}</th>
        <tr>
        	<td>Username:</td><td><input type="text" id="LoginEmail" name="LoginEmail" size="15" /></td>
        </tr>
        <tr>
        	<td>Password:</td><td><input type="text" type="password" name="LoginPass" size="15"  /></td>
       	</tr>
        <tr>
        	<td colspan="2" style="text-align:center"><input type="submit" value="Login" /></td>
       	</tr>
        </table>
        </form>
    </div>
    
    <div id="MainContext">
    
        <form action="./Form_SignUp" method="post" enctype="multipart/form-data" onsubmit="return checkSignup()" >
        <table width="650">
            <tr>
                <td>Email:</td><td><input type="text" id="SignEmail" name="SignEmail" onkeyup="checkEmail()" />
                </td>
                <td><div id="EmailInfo"></div></td>
            </tr>
            <tr>
                <td>Full name:</td><td><input type="text" id="SignFullName" name="SignFullName" onkeyup="checkFullName()" /></td>
                <td><div id="FullnameInfo"></div></td>
            </tr>
            <tr>
                <td>Password:</td><td><input type="password" id="SignPassword" name="SignPassword" onkeyup="check_password()" /></td>
                <td><div id="PasswordInfo"></div></td>
            </tr>
            <tr>
                    <td>Date of Birth:</td>
                    <td><select name="birthDay" id="birthDay"></select>
                        <select name="birthMonth" id="birthMonth" ></select>
                        <select name="birthYear" id="birthYear"></select>
                    </td><td id="birthDateInfo"></td>
            </tr>
            <tr>
            	<td>Type:</td>
                <td ><input type="radio" name="type" id="indi" value="Individual" checked="checked"
                            onclick="change_type_status_indi()" />Individual
                    <input type="radio" name="type" id="comp" value="Company" 
                           onclick="change_type_status_company()" />Company</td>
            </tr>
            <tr>
                <td>Sex:</td><td>
                    <input type="radio" name="sex" id="male"    value="male" checked />Male
                    <input type="radio" name="sex" id="female"  value="female" />Female
                    <input type="radio" name="sex" id="notdef"  value="notdef" />Not Defined</td>
            </tr>
            <tr>
                <td>Country:</td><td ><select name="country" id="country" onChange="display_cities(this.selectedIndex)"></select></td>
                <td id="CountryInfo"></td>
            </tr>
            <tr>
                <td>State:</td><td ><select name="state" id="state"></select></td>
            </tr>
            <tr>
                <td>Upload image:</td><td ><input type="file" name="up_file" id="profileImg" /></td>
            </tr>
            <tr>
            	<td colspan="2"><input type="submit" /></td>
           	</tr>
     	</table>
        </form>
       
    </div>


	<div class="bottomBar">
    	<p>hy359 project.</p>
    </div>

</div>

</body>
</html>        