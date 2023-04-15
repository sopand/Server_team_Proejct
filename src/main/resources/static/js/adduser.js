$(function () {
    let AgeHtml = "";
    let emailChkCode="";
    const FromTime = $(".beFrom").val();
    const UntilTime = $(".beUntil").val();
    let html = timeList();
    for (let i = 13; i <= 100; i++) {
        AgeHtml += `<option value="${i}">${i}세</option>`;
    }

    $("select[name=age]").html(AgeHtml);
    $("select[name=sportTimeFrom]").html(html);
    $("select[name=sportTimeUntil]").html(html);
    selectedControl("FromTime", FromTime);
    selectedControl("UntilTime", UntilTime);

    function selectedControl(id, str) {
        $("#" + id).val(str).prop("selected", true);
    }


    $(".emailChk").click(function () {
        const email = $("input[name=email]").val();
        if(regex.test(email)){
            ajaxCall("/users/email", "GET", {email}, function (data) {
                    if (NotnullChk(data)) {
                        emailChkCode=data;
                        $("input[name=email]").attr("readonly",true);
                        alert("이메일 인증코드 전송완료");
                        return;
                    }
                    alert("이미 존재하는 이메일입니다");
                },
                function () {
                    alert("이메일 인증 에러입니다");
                });
        }else{
            alert("이메일 양식이 틀렸습니다");
        }
    });
    
    $(".emailCodeChk").click(function (){
        if(NotnullChk(emailChkCode)){
            return;
        }
       if(emailChkCode==$(".emailCodeChk").val()){
           $(".emailChk").val("체크완료");
            alert("이메일 인증이 완료되었습니다");
            $(".emailCode").attr("readonly",true);
       }else{
           alert("인증코드가 일치하지 않습니다");
       }
    });

    $(".adduser_btn").click(function () {
        if ($(".emailChk").val() == "체크완료") {
            $(".adduser_main_box").submit();
        } else {
            alert("이메일 체크를 진행하세요");
        }
    });


});