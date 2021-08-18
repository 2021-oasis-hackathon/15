// 파라미터 가져옴
function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
};


// 검색
function findLandByAddress(landForm) {
    var params = '?address=' + landForm.address
        + '&min_area=' + landForm.min_area
        + '&max_area=' + landForm.max_area
        + '&min_money=' + landForm.min_money
        + '&max_money=' + landForm.max_money
        + '&type=' + landForm.type;

    $.ajax({
        url: 'http://54.180.1.215:8080/land/address' + params,
        type: 'GET',

        success: (data) => {
            var array1 = eval(data);


            for (var i = 0; i < array1.length; i++) {
                var div = document.createElement('div');

                var str = "<article style=\" cursor: pointer;\" onclick=\"location.href='detail.html?id=" + array1[i]["id"] + "';\" class='article_1' id =" + array1[i]["id"] + ">\n" +
                    "        <img src='https://via.placeholder.com/155x155'>\n" +
                    "        <table>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>주소</td>\n" +
                    "                <td>" + array1[i]["address"] + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>면적</td>\n" +
                    "                <td>" + array1[i]["area_size"] + " m<sup>2</sup></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>연리스료</td>\n" +
                    "                <td>" + array1[i]["money"] + " 만원</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class'td_width'>작물</td>\n" +
                    "                <td>" + array1[i]["crops"] + "</td>\n" +
                    "            </tr>\n" +
                    "        </table>\n" +
                    "    </article>";
                div.innerHTML = str;
                document.getElementById("list").append(div);
            }
        }
    })
}


function findLandById(id) {

    var params = '?id=' + id;

    $.ajax({
        url: 'http://54.180.1.215:8080/land/id' + params,
        type: 'GET',

        success: (data) => {
            console.log('success');

            var str = "";
            if (data["tractor"] != undefined)
                str += " 트랙터 " + data["tractor"] + ",";
            if (data["combine"] != undefined)
                str += " 콤바인 " + data["combine"] + ",";
            if (data["rice_planting"] != undefined)
                str += " 이앙기 " + data["rice_planting"] + ",";
            if (data["fluid_fertilizer"] != undefined)
                str += " 액상비료살포기 " + data["fluid_fertilizer"] + ",";
            if (data["tree_crush"] != undefined)
                str += " 나무분쇄기 " + data["tree_crush"] + ",";
            if (str.charAt(str.length - 1) == ",")
                str = str.substring(0, str.length - 1);

            document.getElementById("head_address").innerText = data["address"];
            document.getElementById("head_money").innerText = "연 리스료 : " + data["money"] + " 만원";
            document.getElementById("head_area_money").innerText = "면적 당 가격 : " + data["money"] / data["area_size"] * 10000 + " 원";
            document.getElementById("land_area").innerHTML = "면적 : " + data["area_size"] + " m<sup>2</sup>";
            document.getElementById("land_area_money").innerText = "면적 당 가격 : " + data["money"] / data["area_size"] * 10000 + " 원";
            document.getElementById("land_type").innerText = "용도 : " + data["type"];
            document.getElementById("land_crops").innerText = "재배작물 : " + data["crops"];
            document.getElementById("land_incentive").innerText = "농사 인센티브 : " + data["incentive"] + " %";
            document.getElementById("land_machine").innerText = "보유 농기계 : " + str;

            createMap(data["y"], data["x"]);
        }
    })
}


function booking(land_id, booking_date, booking_time, phone) {
    let bookingForm = {
        land_id: land_id,
        booking_date: booking_date,
        booking_time: booking_time + ":00",
        phone: phone
    };


    $.ajax({
        url: 'http://54.180.1.215:8080/date/save',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(bookingForm),
        success: function (data, textStatus, jqXHR) {
            console.log('success.');
            alert("예약이 완료되었습니다. \n예약 번호 : " + data["id"]
                + "\n예약하신 날짜 : " + data["booking"].substring(0, 10)
                + "\n예약하신 시간 : " + data["booking"].substring(10, 16)
                + "\n전화번호 : " + phone
            );
            window.close();
        },
    });

}


function bookingOpen() {
    var popupX = (window.screen.width / 2) - (500 / 2);

    var popupY = (window.screen.height / 2) - (300 / 2);

    window.open('date.html?id=' + searchParam('id'),
        'PopupWin', 'height=300, width=500, left=' + popupX + ', top=' + popupY + ', screenX=' + popupX + ', screenY= ' + popupY
    )
    ;
}


function jbSubmit() {
    var id = searchParam('id');
    var booking_date = document.getElementById('booking_date').value;
    var booking_time = document.getElementById('booking_time').value;
    var phone = document.getElementById('phone').value;

    booking(id, booking_date, booking_time, phone);
}