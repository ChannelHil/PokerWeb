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

      $("#joinBtn").delegate('*','click',function (e){
            var id= e.target.id;
            window.location.href = '/join/' + id;
      });

 });
