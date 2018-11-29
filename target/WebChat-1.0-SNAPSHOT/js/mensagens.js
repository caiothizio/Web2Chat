/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(document).ready(function () {
    var user1 = getUrlParameter('user1');
    var user2 = getUrlParameter('user2');
    
    setInterval(function () {
        getMessages(user1, user2);
    }, 5000);
    
});

function getMessages(user1, user2){
    $.ajax({
        url:'Mensagem',
        type: 'GET',
        data:{user1, user2},
        statusCode:{
            200: function(messages){
               
            },
            400: function(){
                
            }
        }
    });
}
*/
function newMessageFrom(date, from, message){
    return '<p class="ml-2 mt-2 text-left"><span class="msg-from">['+ date +']<span class="font-weight-bold"> '+ from +' diz:</span> '+ message +'</span></p>';
}

function newMessageTo(date, from, message){
    return '<p class="mr-2 mt-2 text-right"><span class="msg-to">['+ date +']<span class="font-weight-bold"> '+ from +' diz:</span> '+ message +'</span></p>';
}

function escreverMsg(to){
    var msg = $('textarea#msg-text').val();
    
    if(msg === ""){
        return;
    }
    
    $('textarea#msg-text').val('');
    
    var para = to;
    $.ajax({
        url: 'Mensagem',
        type: 'POST',
        data: {to: to, msg: msg},
        statusCode:{
            200: function(data){
                abrirChat(para);
            },
            400: function(data){
                document.querySelector("#messages").innerHTML += '<p class="text-danger font-weight-bold">Houve um erro ao enviar a mensagem! Tente novamente depois.</p>'
            }
        }
    });
}