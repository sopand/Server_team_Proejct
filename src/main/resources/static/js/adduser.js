$(function(){
    let html="";
    for(let i=1;i<=24;i++){
        if(i<10){
            html+=`
        <option value="0${i}:00:00">0${i}시</option>
        `;
        }else{
            html+=`
        <option value="${i}:00:00">${i}시</option>
        `;
        }

    }
    $("select[name=sportTimeFrom]").html(html);
    $("select[name=sportTimeUntil]").html(html)
});