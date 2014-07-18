<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String LoginInfo = (String)session.getAttribute("LoginInfo");
    String menubarLeft=null;
    String menubarRight=null;
    //If logged in menu.
    if ((LoginInfo!=null)&&(LoginInfo.equals("LoginSuccess"))) 
    {
        menubarLeft  =  "<a class='navBarLinks' href='./Page_Main'>main  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href='./Page_Profile'>profile  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href='EditProfile.jsp'>edit profile  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href='./Page_ViewAllMembers'>view all members  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href='./Page_ViewAllAdverts'>view all ads  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href=''>view statistics  </a>";
        menubarRight =   "<a class='navBarLinks' href='./Page_ViewCart'>shoping list </a>";
        menubarRight +=  "<a class='navBarLinks' href='./Page_Logout'>logout  </a>";
    }
    else //If NOT logged in user
    {
        menubarLeft  =  "<a class='navBarLinks' href='./Page_Main'>main  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href='./Page_ViewAllMembers'>view all members  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href='./Page_ViewAllAdverts'>view all ads  </a>";
        menubarLeft  +=  "<a class='navBarLinks' href=''>view statistics  </a>";
        menubarRight =  "<a class='navBarLinks' href='Login.jsp'>login  </a>";
        menubarRight +=  "<a class='navBarLinks' href='SignUp.jsp'>signup  </a>";
    }                

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="./CSS/cssNavigationBar.css" rel="stylesheet" media="screen" />
    </head>
    <body>
    
        <div id="container">
            <div id="banner" >
                <img src="./Images/banner.png"/>
            </div>
            <div id="navigationBar">
                <div class="NavLeftSide">
                    <table><% out.print(menubarLeft);%></table>
                </div>
                <div class="NavRightSide">
                    <table><% out.print(menubarRight);%></table>
                </div>
            </div>
        </div>
    </body>
</html>
