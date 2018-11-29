/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
        $.ajax({
        url: 'Usuario',
        type: 'GET',
        statusCode: {
            200: function (data) {
                var contatos = JSON.parse(data);
                document.querySelector("#contatos").innerHTML = '';
                
                for(var c in contatos){
                    document.querySelector("#contatos").innerHTML += '<div class="list-group-item list-group-item-action pointer" onclick = "abrirChat(\''+contatos[c]+'\')">'+contatos[c]+'</div>';
                }
            }
            
        }
    });
})

function abrirChat(data){
    var to = data;
    document.querySelector("#chatwith").innerHTML = 'Chat com ' + to + ':'
    document.querySelector("#enviarMsg").innerHTML = '<button class="btn btn-primary botaoenviar" onclick="escreverMsg(\''+to+'\')">Enviar</button>';
    $.ajax({
        url: 'Mensagem',
        type: 'GET',
        data: {to: to},
        statusCode:{
            200: function(data){
                var messages = JSON.parse(data);
                document.querySelector("#messages").innerHTML = '';
                
                for(var i in messages){
                    var date = messages[i].date;
                    var from = messages[i].from;
                    var msg = messages[i].message;
                   
                    document.querySelector("#messages").innerHTML += newMessage(date, from, msg);
                }
            },
            204: function(data){
                document.querySelector("#messages").innerHTML = '<p class = "mt-4" style="text-align:center"> Você não possui mensagens com <span class="font-weight-bold">'+ to +'</span>. </p>';
            }
        }
    });
}
