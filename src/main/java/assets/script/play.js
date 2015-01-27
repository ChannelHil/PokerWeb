$(document).ready(function()
 {
 $("#btnHost").click(function (){

        window.location.href = '/host';
     });
  $("#btnStart").click(function (){

         window.location.href = '/start';
  });

   $("#btnView").click(function (){

                window.location.href = '/view';
         });
            $("#btnViewPlay").click(function (){

                   window.location.href = '/joinplay';
            });

      $("#joinBtn").delegate('*','click',function (e){
            var id= e.target.id;
            window.location.href = '/join/' + id;
      });

 });
