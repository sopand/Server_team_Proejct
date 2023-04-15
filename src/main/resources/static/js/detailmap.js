$(function () {


    const boardMapCordx = $("input[name=boardMapCordx]").val();
    const boardMapCordy = $("input[name=boardMapCordy]").val();
    const boardMapName = $("input[name=boardMapName]").val();
    const container = $("#map")[0];
    const options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(boardMapCordx, boardMapCordy), //지도의 중심좌표.
        level: 5 //지도의 레벨(확대, 축소 정도)
    };

    const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    const markerPosition = new kakao.maps.LatLng(boardMapCordx, boardMapCordy); // 마커를 생성할 약속 장소의 좌표값,
    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({ // 해당 좌표의 위치에 마커를 생성하는 작업.
        position: markerPosition
    });
    marker.setMap(map); // Map에 실제로 marker를 셋팅


    const iwContent = `<div class="marker_naming"style="padding:5px;">${boardMapName}<div class="info_url"><a href="https://map.kakao.com/link/map/${boardMapName},${boardMapCordx},${boardMapCordy}" target="_blank">지도에서보기</a> <a href="https://map.kakao.com/link/to/${boardMapName},${boardMapCordy},${boardMapCordy}"target="_blank">길찾기</a></div></div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

    //마커에 인포 윈도우를 생성해준다.
    const infowindow = new kakao.maps.InfoWindow({
        position: markerPosition,  // 원래는 iwContent와함께 인포윈도우의 좌표를 설정하지만 나는 이미 만들어놓은 좌표값이 존재함. 그래서 div만생성
        content: iwContent
    });
    infowindow.open(map, marker);   // Map에 인포윈도우를 위치하는 실제 기능


    if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function (position) {

            const lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도
            const locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                message = '<div class="myMap">현재 위치</div>'; // 인포윈도우에 표시될 내용입니다
            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);
        });
    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
        const message = '현재 위치를 찾을수 없어 약속 장소만 표시.'
        displayMarker(markerPosition, message);
    }

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
    function displayMarker(locPosition, message) {
        // 마커를 생성합니다
        const marker = new kakao.maps.Marker({
            map: map,
            position: locPosition
        });
        const iwContent = message, // 인포윈도우에 표시할 내용
            iwRemoveable = true;
        // 인포윈도우를 생성합니다
        const infowindow = new kakao.maps.InfoWindow({
            content: iwContent,
            removable: iwRemoveable
        });
        // 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);
        map.setCenter(markerPosition);
    }


// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    const mapTypeControl = new kakao.maps.MapTypeControl();
// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

});


$(function () {
    const boardNo = $("input[name=boardNo]").val();
    const clubEmail = $("input[name=email]").val();

    ajaxCall("/boards/review", "GET", {boardNo}, function (data) {
        const dataChk=data;
        const html = reviewList(dataChk.list);
        $(".reviewListBox").html(html);
    }, function () {
        alert("모임 참가 신청 에러 발생");
    },)



    $(".groupAdd_btn").click(function () {
        if(emailChk(clubEmail)){
            return;
        }
        ajaxCall("/boards/club", "POST", {clubEmail, boardNo}, function (data) {
            if (NotnullChk(data)) {
                alert("모임 참가신청 성공");
            }
        }, function () {
            alert("모임 참가 신청 에러 발생");
        },)
    });
    $(".review_add_btn").click(function () {
        const reviewContent = $("textarea[name=reviewContent]").val();
        const reviewWriter = clubEmail;
        if(emailChk(reviewWriter)){
            return;
        }
        ajaxCall("/boards/review", "POST", {reviewWriter, boardNo, reviewContent}, function (data) {
            const html = reviewCreate(data);
            $(".reviewListBox").append(html);
        }, function () {
            alert("모임 참가 신청 에러 발생");
        },)
    });

    function reviewCreate(data) {
        const html = `
           <div class="reviewTag">
                    <div class="reviewText">
                        <span class="reviewEmail">${data.reviewWriter}</span>
                        <span class="reviewDate">${data.reviewDate}</span>
                    </div>
                    <div class="reviewContent">
                        ${data.reviewContent}
                    </div>
                    <div class="reviewBtn">
                        <a href="#">수정</a>
                        <a href="#">삭제</a>
                        <a href="#">대댓글</a>
                    </div>
                </div>
         `;

        return html;
    }

    function reviewList(data) {
        let html = "";
        $(data).each(function (index, item) {
            html += `
           <div class="reviewTag">
                    <div class="reviewText">
                        <span class="reviewEmail">${item.reviewWriter}</span>
                        <span class="reviewDate">${item.reviewDate}</span>
                    </div>
                    <div class="reviewContent">
                        ${item.reviewContent}
                    </div>
                    <div class="reviewBtn">
                        <a href="#">수정</a>
                        <a href="#">삭제</a>
                        <a href="#">대댓글</a>
                    </div>
                </div>
         `;
        });
        return html;
    }


})