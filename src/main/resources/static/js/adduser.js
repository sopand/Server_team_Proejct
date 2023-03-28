$(function(){
    let html="";
    for(let i=1;i<=24;i++){
        html+=`
        <option value="${i}:00:00">${i}시</option>
        `;
    }
    $("select[name=time1]").html(html);
    $("select[name=time2]").html(html)
});