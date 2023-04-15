$(function (){

   $(".addBoard_btn").click(function (ignore){
        const email=$("input[name=email]").val();
        if(emailChk(email)){
            ignore.preventDefault();
        }
   });
});