/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function deleteUser(user) {
    $.ajax({
        url: 'Usuario/' + user,
        type: 'DELETE',
        success: function (user) {
            window.location.reload();
        }
    });
}

function addUser() {
    var email = document.getElementById("email").value;
    var user = document.getElementById("user").value;
    var pw = document.getElementById("senha").value;
    var pw_conf = document.getElementById("senha_conf").value;

    $.ajax({
        url: 'Usuario',
        type: 'POST',
        data: {emailUser: email, userUser: user, pwUser: pw, pwConf: pw_conf},
        statusCode: {
            201: function (data) {
                location.replace("/Login");
            },
            400: function (data) {
                location.reload();
            }
        }
    });
}

function login() {
    var user = document.getElementById("name").value;
    var password = document.getElementById("paw").value;

    $.ajax({
        url: 'Login',
        type: 'POST',
        data: {userUser: user, pwUser: password},
        statusCode: {
            200: function (data) {
                location.replace("/ChatPrincipal")
            },
            401: function (data) {
                location.reload();
            }
        }
    });
}