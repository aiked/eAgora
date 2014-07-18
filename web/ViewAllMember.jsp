<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaBeans.Member"%>

<html>
<head>
<link type="text/css" href="./CSS/cssViewAllMembers.css" rel="stylesheet" media="screen" />
<title>View all members</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<%
    String ViewAllMembers="";
    ArrayList<Member> members = null;
    members = (ArrayList)request.getAttribute("allMembers");
    if(members!=null)
    {
        int size=members.size();
        String imagePath="";
        for(int i=0;i<size;i++)
        {
            if(members.get(i).getImagepath().equals("default"))
                    imagePath="http://localhost:8080/Project/Images/default.png";
                else
                    imagePath="http://localhost:8080/Project/MembersFotos/"+members.get(i).getImagepath();
            
            ViewAllMembers+="<table class='MemberTable'>";
            ViewAllMembers+="<tr><td rowspan='2' width='52'><img src='"+imagePath+"' width='50' height='50'></td></td><td>"+members.get(i).getFname()+"</td></tr>";
            ViewAllMembers+="<tr><td><a class='ProfileLink' href='./Page_ViewProfile?emailToView="+members.get(i).getEmail()+"'>view profile</a></td></tr>";
            ViewAllMembers+="</table>" ;
        }
    }

%>

<body>

<div id="container">

    <jsp:include page="NavigationBar.jsp"/>
    
    <div class="Members">
        <h2>List of our members</h2>
        <div>
            <%=ViewAllMembers%>
        </div>
    
    </div>
</div>
</body>
</html>