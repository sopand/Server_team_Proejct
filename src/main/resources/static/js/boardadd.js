$(function (){
    const html=timeList();
    $("select[name=boardPromiseFrom]").html(html);
    $("select[name=boardPromiseUntil]").html(html);

});