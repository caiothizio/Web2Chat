<%-- 
    Document   : index
    Created on : 22/10/2018, 17:23:55
    Author     : caiot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href= "css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel = "stylesheet" href = "css/style.css" type="text/css">
        <title>Web2Chat</title>
    </head>
    <body>
        <div class="container">
            <div class ="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4 text-center">
                    <h1 class="mt-5">Web2Chat</h1>
                    <a class="btn btn-primary mt-5" href="Login">${msgs["loginacc"]}</a>
                    <a class="btn btn-primary mt-2" href="Usuario">${msgs["createnew"]}</a>                   
                </div>
            </div>
        </div>
    </body>
</html>
