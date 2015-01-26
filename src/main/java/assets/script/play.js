$(document).ready(function()
 {
 $("#btnHost").click(function (){

         $.ajax({
                     		type: "POST",
                            url: "/host"
                         }).done(function(data) {

                     		$('#btnHost').val('Start Game');

                     		$("#btnHost").click(function (){

                                     $.ajax({
                                               type: "POST",
                                                url: "/start"
                                                }).done(function(data) {

                                                 });
                                 }
                             );


                     	});
     }
 );

 $("#btnView").click(function (){

         $.ajax({
                     		type: "POST",
                            url: "/view"
                         }).done(function(data) {

                     	});
     }
 );



 });
