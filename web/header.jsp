<%-- 
    Document   : header
    Created on : May 24, 2012, 10:10:40 PM
    Author     : Chrysohous
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% request.setAttribute("contextPath", request.getContextPath()); %>
        
        <script src="./scripts/prototype-1.4.0.js"
            type="text/javascript"></script>
            
        <script src="./scripts/scriptaculous.js"
            type="text/javascript"></script>
            
        <script src="./scripts/overlibmws.js"
            type="text/javascript"></script>
            
        <script src="./scripts/ajaxtags-1.2-beta2.js"
            type="text/javascript"></script>
            
            <link rel="stylesheet" href="CSS/styles.css"
                  type="text/css"/>
    </body>
</html>
