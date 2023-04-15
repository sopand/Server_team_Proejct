    function ajaxCall(url,method,param,successFn,errorFn){
        $.ajax({
            url: url,
            method: method,
            data: param,
            success: function (data) {
                if(typeof successFn =="function"){
                    successFn(data);
                }
            },
            error: function () {
                if(typeof errorFn =="function"){
                    errorFn();
                }
            }
        });
    };

    function timeList(){
        let html="";
        for (let i = 1; i <= 24; i++) {
            if (i < 10) {
                html += `<option value="0${i}:00:00">0${i}시</option>`;
            } else {
                html += `<option value="${i}:00:00">${i}시</option>`;
            }
        }
        return html;
    }
    function selectRepaet(){
        let html="";
        for(let i=1;i<=20;i++){
            html += `<option value="${i}">${i}명</option>`;
        }
        return html;
    }

    function NotnullChk(check){
        if(check!=null && check!=""){
            return true;
        }
    }
    function NullChk(check){
        if(check==null || check==""){
            return true;
        }
    }

    function emailChk(email) {
        if (email == null) {
            alert("로그인 후 사용 가능합니다.");
            return true;
        }
    }
    let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
