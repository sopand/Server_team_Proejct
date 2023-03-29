$(function () {
    let AgeHtml = "";
    const FromTime = $(".beFrom").val();
    const UntilTime = $(".beUntil").val();
    let html = "";
    for (let i = 13; i <= 100; i++) {
        AgeHtml += `<option value="${i}">${i}세</option>`;
    }

    for (let i = 1; i <= 24; i++) {
        if (i < 10) {
            html += `<option value="0${i}:00:00">0${i}시</option>`;
        } else {
            html += `<option value="${i}:00:00">${i}시</option>`;
        }
    }

    $("select[name=age]").html(AgeHtml);
    $("select[name=sportTimeFrom]").html(html);
    $("select[name=sportTimeUntil]").html(html);
    selectedControl("FromTime", FromTime);
    selectedControl("UntilTime", UntilTime);

    function selectedControl(id, str) {
        console.log(str);
        console.log(id);
        $("#" + id).val(str).prop("selected", true);
    }



});