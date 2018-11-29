<%-- 
    Document   : login
    Created on : 22/05/2018, 19:54:57
    Author     : caiot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang = "en-us">
    <head>
        <meta charset ="UTF-8">
        <meta name= "viewport" content="width=device-width, initial-scale=1.0">
        <link href= "css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel = "stylesheet" href = "css/style.css" type="text/css">
        <title>Chat</title>
    </head>
    <body>
        <div class="container">
            <div class = "divLogin row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4 text-center">
                    <div class="search-box">
                        <div class="caption mt-3">
                            <h1>${msgs["greetings"]}</h1>
                            <p>${msgs["pleaseLogin"]}</p>
                        </div>
                            <div class="input-group">
                                <input type="text" id="name" name = "user" class="form-control mb-2" placeholder=${msgs["username"]} value="${errologin}">
                            </div>
                    </div>
                    <div class="search-box">
                        <div class="input-group">
                            <input type="password" id="paw" name = "senha" class="form-control mb-4" placeholder=${msgs["password"]} maxlength = "16">
                        </div>

                        <c:choose>
                            <c:when test="${not empty errologin}">
                                <p>${msgs["errologin"]}</p>
                            </c:when>
                        </c:choose>
                                
                        <c:choose>
                            <c:when test="${not empty usuarioapagado}">
                                <p>Usuario deletado com sucesso.</p>
                            </c:when>
                        </c:choose>
                        

                    </div>


                    <div class="search-box">
                        <div class="input-group">
                            <input type="button" id="submit" class="btn btn-primary" value="${msgs["login"]}" onclick="login()">
                        </div>
                       
                    </div>

                    <a id="new_acc" class ="ml-1" href = "Usuario">${msgs["cad"]}</a>
                </div>
            </div>
        </div>
                
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/usuarios.js" type="text/javascript"></script>
    </body>
</html>
