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

    const markerPosition = new kakao.maps.LatLng(boardMapCordx, boardMapCordy);

// 마커를 생성합니다
    const marker = new kakao.maps.Marker({
        position: markerPosition
    });
    marker.setMap(map);

    const iwContent =`<div class="marker_naming"style="padding:5px;">${boardMapName}<div class="info_url"><a href="https://map.kakao.com/link/map/${boardMapName},${boardMapCordx},${boardMapCordy}" target="_blank">지도에서보기</a> <a href="https://map.kakao.com/link/to/${boardMapName},${boardMapCordy},${boardMapCordy}"target="_blank">길찾기</a></div></div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(boardMapCordx, boardMapCordy); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
    const infowindow = new kakao.maps.InfoWindow({
        position: iwPosition,
        content: iwContent
    });
    infowindow.open(map, marker);

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    const mapTypeControl = new kakao.maps.MapTypeControl();
// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

});