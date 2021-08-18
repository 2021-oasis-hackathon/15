var arr_zone = ['군산', '익산', '완주', '진안', '무주', '장수', '김제', '전주', '부안', '정읍', '임실', '장수', '나주', '고창', '순창', '남원', '영광', '장성', '담양', '곡성', '구례', '함평', '광주', '화순', '순천', '진도', '광양', '무안', '신안', '목포', '영암', '장흥', '강진', '보성', '고흥', '여수', '해남'];//지역
var start_area = 0; // 시작가격
var end_area = 1;   // 마지막가격
var area_price = 1; // 평단가
var i = 0;
var arr_use = ['논', '밭', '과수원', '기타'];
var e;
var button_click_rgb = "rgb(242, 242, 242)";     // 지역 버튼 클릭 시 색상 (rgb 값으로 넣어주세요)

function table_create() {//테이블 생성
    document.write("<table class='big_table' style=\"border-bottom: none;\"><tr><td class=table_A>" + "지역" + "</td>");
    for (; i < arr_zone.length; i++) {
        if ((i % 13 == 0) && !(i == 0)) {
            document.write("<tr></tr><td class=table_A></td>");
        }

        document.write("<td><input class='zone' id=arr_zone" + i + " type='button' onclick='button_click_arr_zone(" + i + ")' value=" + arr_zone[i] + "></td>");

    }
    for (; i % 13 != 0; i++) {
        document.write("<td></td>");
    }
    document.write("</tr>");

    document.write("<table class='big_table' style=\"border-bottom: none;\"><tr><td class=table_A>" + "면적" + " m<sup>2</sup></td><td class=empty_input></td>");
    document.write("<td><input class=input id=min_area type=number onchange='text_change(min_area)'></td>" + "<td>~</td>" + "<td><input class=input id=max_area type=number onchange='text_change(max_area)'></td>");
    document.write("<td class=td_B></td>");
    document.write("</tr>");

    document.write("<table class='big_table'  style=\"border-bottom: none;\"><tr><td class=table_A>" + "리스료(연)" + "</td><td class=empty_input></td>");
    document.write("<td><input class=input id=min_money placeholder='만원' type=number onchange='text_change(min_money)'></td>" + "<td>~</td>" + "<td><input class=input id=max_money placeholder='만원' type=number onchange='text_change(max_money)'></td>");
    document.write("<td class=td_B></td>");
    document.write("</tr>");

    document.write("<table class='big_table'><tr><td class=table_A>" + "용도" + "</td>");
    for (i = 0; i < arr_use.length; i++) {
        document.write("<td><input class='zone' id=arr_use" + i + " type='button' onclick='button_click_arr_use(" + i + ")' value=" + arr_use[i] + "></td>");
    }
    for (; i % 13 != 0; i++) {
        document.write("<td></td>");
    }
    document.write("</tr></table>");
}


// 지역 클릭 시 색 변화, 넘겨줄 값 변화
function button_click_arr_zone(i) {
    var property = document.getElementById("arr_zone" + i);
    if (property.style.backgroundColor === button_click_rgb) {                     // 클릭 시 변화할 색
        property.style.backgroundColor = "white";              // 기존 색
        var text = document.getElementById("p_address").textContent;
        if (text.indexOf("," + property.value)) {
            var str = text.replace("," + property.value, "");
            document.querySelector("#p_address").innerText = str;
        }
        if (text.indexOf("," + property.value) == -1) {
            document.querySelector("#p_address").innerText = "";
        }
    } else {
        property.style.backgroundColor = button_click_rgb;                          // 클릭 시 변화할 색
        var text = document.getElementById("p_address").textContent;
        if (text === "") {
            document.querySelector("#p_address").innerText = property.value;
        } else {
            document.querySelector("#p_address").innerText = text + "," + property.value;
        }

    }
}

// 연간 리스 비용, 면적 변경 시 넘겨줄 값 변화
function text_change(input) {
    var text = document.getElementById("p_" + input.id);
    text.innerText = input.value;
}

// 용도 클릭 시 색, 넘겨줄 값 변화
function button_click_arr_use(i) {
    var text = document.getElementById("p_type");
    for (var j = 0; j < arr_use.length; j++) {
        document.getElementById("arr_use" + j).style.backgroundColor = "white";    // 용도 초기 색
    }

    document.getElementById("arr_use" + i).style.backgroundColor = button_click_rgb;    // 지목 클릭시 색
    text.innerText = document.getElementById("arr_use" + i).value;
}


function button_click_search() {
    var p_address = document.getElementById("p_address");
    var p_min_area = document.getElementById("p_min_area");
    var p_max_area = document.getElementById("p_max_area");
    var p_min_money = document.getElementById("p_min_money");
    var p_max_money = document.getElementById("p_max_money");
    var p_type = document.getElementById("p_type");

    window.location.href = 'select.html?p_address=' + p_address.textContent
        + '&p_min_area=' + p_min_area.textContent
        + '&p_max_area=' + p_max_area.textContent
        + '&p_min_money=' + p_min_money.textContent
        + '&p_max_money=' + p_max_money.textContent
        + '&p_type=' + p_type.textContent;
}


function searchLoad(landForm) {
    document.getElementById("p_address").innerText = landForm.address;
    document.getElementById("p_min_area").innerText = landForm.min_area;
    document.getElementById("p_max_area").innerText = landForm.max_area;
    document.getElementById("p_min_money").innerText = landForm.min_money;
    document.getElementById("p_max_money").innerText = landForm.max_money;
    document.getElementById("p_type").innerText = landForm.type;

    var addressArray = landForm.address.split(",");

    for (var i = 0; i < addressArray.length; i++) {
        if (arr_zone.indexOf(addressArray[i]) != -1) {
            document.getElementById("arr_zone" + arr_zone.indexOf(addressArray[i])).style.backgroundColor = button_click_rgb;      // 클릭 시 변화할 색
        }
    }

    document.getElementById("min_area").value = landForm.min_area;
    document.getElementById("max_area").value = landForm.max_area;
    document.getElementById("min_money").value = landForm.min_money;
    document.getElementById("max_money").value = landForm.max_money;

    document.getElementById("arr_use" + arr_use.indexOf(landForm.type)).style.backgroundColor = button_click_rgb;                         // 클릭 시 변화할 색
}

function input_event() {    //입력 받은 값 출력
    window.onload = function () {
        document.getElementById(test).onclick = function () {
            this.style.background = 'red';
            window.alert("경고 메세지");
        }
    };
}