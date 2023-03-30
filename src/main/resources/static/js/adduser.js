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



});