<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@page import="java.util.*,java.text.*" %>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="JavaBeans.Member" %>

<html>
<head>
<link type="text/css" href="./CSS/cssLogin.css" rel="stylesheet" media="screen" />
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<div id="container">

    <jsp:include page="NavigationBar.jsp"/>
    
    <div id="MainContext">
        
        <form action="./Page_Login" method="post">
        <table class="CenterIt">
            <th colspan="2" >${LoginInfo}</th>
        <tr>
        	<td>Username:</td><td><input type="text" id="LoginEmail" name="LoginEmail" size="15" /></td>
        </tr>
        <tr>
        	<td>Password:</td><td><input type="password" id="LoginPass" name="LoginPass" size="15"  /></td>
       	</tr>
        <tr>
        	<td colspan="2" style="text-align:center"><input type="submit" value="Login" /></td>
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