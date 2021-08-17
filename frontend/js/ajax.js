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

                var machine = "";
                if (array1[i]["tractor"] != undefined)
                    machine += "트랙터 ";
                if (array1[i]["combine"] != undefined)
                    machine += "콤바인 ";
                if (array1[i]["rice_planting"] != undefined)
                    machine += "이앙기 ";
                if (array1[i]["fluid_fertilizer"] != undefined)
                    machine += "액상비료살포기 ";
                if (array1[i]["tree_crush"] != undefined)
                    machine += "나무분쇄기";

                var str = "<article class='article_1' id =" + array1[i]["id"] + ">\n" +
                    "        <img src='https://via.placeholder.com/190x190'>\n" +
                    "        <table>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>주소</td>\n" +
                    "                <td>" + array1[i]["address"] + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>연리스료</td>\n" +
                    "                <td>" + array1[i]["money"] + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class'td_width'>작물</td>\n" +
                    "                <td>" + array1[i]["crops"] + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>농기계</td>\n" +
                    "                <td>" + machine + "</td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='td_width'>면적</td>\n" +
                    "                <td>" + array1[i]["area_size"] + "</td>\n" +
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

            console.log("id : " + data["id"]);
            console.log("address : " + data["address"]);
            console.log("money : " + data["money"]);
            console.log("incentive : " + data["incentive"]);
            console.log("crops : " + data["crops"]);
            console.log("type : " + data["type"]);
            console.log("picture : " + data["picture"]);
            console.log("tractor : " + data["tractor"]);
            console.log("combine : " + data["combine"]);
            console.log("rice_planting : " + data["rice_planting"]);
            console.log("fluid_fertilizer : " + data["fluid_fertilizer"]);
            console.log("tree_crush : " + data["tree_crush"]);
            console.log("x : " + data["x"]);
            console.log("y : " + data["y"]);
        }
    })
}

