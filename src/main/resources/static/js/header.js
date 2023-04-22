$(function () {
    const CategoryWalk = [];
    const CategoryBall = [];
    const CategoryRacket = [];
    const CategoryFitness = [];
    const getSportCategory = [];
    ajaxCall("/sports/category", "GET", null, function (data) {
            $(data).each(function (index, item) {
                if (!getSportCategory.includes(item.spoCategory) && item.spoCategory != null) {
                    getSportCategory.push(item.spoCategory);
                }
                switch (item.spoCategory) {
                    case "트레킹":
                        CategoryWalk.push(item.spoName);
                        break;
                    case "구기종목":
                        CategoryBall.push(item.spoName);
                        break;
                    case  "라켓스포츠":
                        CategoryRacket.push(item.spoName);
                        break;
                    case "피트니스":
                        CategoryFitness.push(item.spoName);
                        break;
                    default:
                        break;
                }
            });
            getSportCategory.push("기타 종목");
            let html = `<div href="javascript:void(0)" class="headerTop"><a href="/boards/list">전체 게시글</a></div>`
            $(getSportCategory).each(function (index, item) {
                html += `<div href="javascript:void(0)" class="headerTop"id="${item}">${item}</div>`;
            });
            $(".header_menu").html(html);
        },
        function () {
            alert("헤더정보 오류가 발생했습니다");
        })


    $(document).on('click', ".headerTop", function () {
        const id = $(this).attr("id");
        const OnOffCheck = $("#listOn").attr("class");
        if (typeof OnOffCheck != "undefined") {
            $('ul').remove('#listOn');
        }
        if(id==OnOffCheck){
            $('ul').remove('#listOn');
            return;
        }
            let html = "";
            switch (id) {
                case "트레킹":
                    html = CategoryList("트레킹", CategoryWalk);
                    $("#" + id).append(html);
                    break;
                case "구기종목":
                    html = CategoryList("구기종목", CategoryBall);
                    $("#" + id).append(html);
                    break;
                case  "라켓스포츠":
                    html = CategoryList("라켓스포츠", CategoryRacket);
                    $("#" + id).append(html);
                    break;
                case "피트니스":
                    html = CategoryList("피트니스", CategoryFitness);
                    $("#" + id).append(html);
                    break;
                default:
                    break;
            }



    });

    function CategoryList(type, List) {
        let html = `<ul class="${type}" id="listOn">`;
        $(List).each(function (index, item) {
            html += `<li><a href="#">${item}</a></li>`;
        });
        html += "</ul>";
        return html;
    }


});