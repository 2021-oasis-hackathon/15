function createMap(x, y) {

    var openStreetMapType = new naver.maps.ImageMapType({
        name: 'OSM',
        minZoom: 0,
        maxZoom: 19,
        tileSize: new naver.maps.Size(256, 256),
        projection: naver.maps.EPSG3857,
        repeatX: true,
        tileSet: [
            "https://a.tile.openstreetmap.org/{z}/{x}/{y}.png",
            "https://b.tile.openstreetmap.org/{z}/{x}/{y}.png",
            "https://c.tile.openstreetmap.org/{z}/{x}/{y}.png"
        ],
        provider: [{
            title: " /OpenStreetMap",
            link: "http://www.openstreetmap.org/copyright"
        }]
    });

    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(x, y), // 여기는 받아오는 방식으로 할거임. 백엔드에서 보내준 좌표 지도 중앙.
        zoom: 12, //여기 값 어느정도로 할지 정하기.
        mapTypeControl: true, //
        mapTypeControlOptions: { //
            style: naver.maps.MapTypeControlStyle.BUTTON
        }
    });

    map.mapTypes.set('osm', openStreetMapType);

    var korea = new naver.maps.LatLngBounds(
        new naver.maps.LatLng(30.1391277, 115.0607471),
        new naver.maps.LatLng(48.940448, 139.7803655));

    var listener = null,
        projectionChanged = false;

    function checkBounds() {
        if (projectionChanged) {
            projectionChanged = false;

            return;
        }

        var bounds = map.getBounds();

        if (korea.hasBounds(bounds)) {
            if (map.getMapTypeId() === "osm") {
                map.setMapTypeId("normal");
            }
        } else {
            if (map.getMapTypeId() !== "osm") {
                map.setMapTypeId("osm");
            }
        }
    }

    function listen() {
        listener = naver.maps.Event.addListener(map, 'idle', checkBounds);
    }

    function unlisten() {
        naver.maps.Event.removeListener(listener);
        listener = null;
    }

    naver.maps.Event.addListener(map, 'zooming', function (e) {
        unlisten();

        naver.maps.Event.once(map, 'idle', function () {
            listen();
        });
    });

    listen();

    var marker = new naver.maps.Marker({
     //   position: new naver.maps.LatLng(37.3595704, 127.105399), //여기도 백엔드에서 보내준 좌표를 넣으면 됨. 여기는 마커.
        position: new naver.maps.LatLng(x, y), //여기도 백엔드에서 보내준 좌표를 넣으면 됨. 여기는 마커.
        map: map
    });


}

