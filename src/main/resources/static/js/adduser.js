$(function () {
    let AgeHtml = "";
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
        ajaxCall("/users/email", "GET", {email}, function (data) {
                if (NotnullChk(email)) {
                    $(".emailChk").val("체크완료");
                    $("input[name=email]").attr("readonly",true);
                    alert("이메일 체크 완료")
                    return;
                }
                alert("이미 존재하는 이메일입니다");
            },
            function () {

            });
    });
    $(".adduser_btn").click(function () {
        if ($(".emailChk").val() == "체크완료") {
            $(".adduser_main_box").submit();
        } else {
            alert("이메일 체크를 진행하세요");
        }
    });


});