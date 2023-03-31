$(function (){
    const html=timeList();
    const peopleHtml=selectRepaet();
    $("select[name=boardPromiseFrom]").html(html);
    $("select[name=boardPromiseUntil]").html(html);
    $("select[name=boardPeople]").html(peopleHtml);

});