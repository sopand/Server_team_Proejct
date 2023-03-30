$(function () {
    ajaxCall("/sports/category", "GET", null, function (data) {
        const getSport=data;

            console.log(data);

        const getSportCategory=[];

        $(data).each(function (index,item){
        });

        },
        function () {
                alert("헤더정보 오류가 발생했습니다");
        })
});