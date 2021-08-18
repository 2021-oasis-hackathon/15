package hackathon.core.service;

import hackathon.core.domain.Coordinates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;


public class CoordinateConversionService {
    @Value("${kakaoApiKey}")
    private String key;

    public Coordinates getJsonData(String address) throws Exception {

        Coordinates coordinates = new Coordinates();

        address = URLEncoder.encode(address, "UTF-8");

        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

        String jsonString = new String();

        String buf;

        URL Url = new URL(url);


        HttpsURLConnection conn = (HttpsURLConnection) Url.openConnection();
        String auth = "KakaoAK " + key;
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Requested-With", "curl");
        conn.setRequestProperty("Authorization", auth);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        while ((buf = br.readLine()) != null) {
            jsonString += buf;
        }

        JSONParser paser = new JSONParser();

        JSONObject J = (JSONObject) paser.parse(jsonString);
        JSONObject meta = (JSONObject) J.get("meta");

        JSONArray data = (JSONArray) J.get("documents");
        long size = (long) meta.get("total_count");

        if (size > 0) {
            JSONObject jsonX = (JSONObject) data.get(0);
            coordinates.setX(Double.parseDouble(jsonX.get("x").toString()));
            coordinates.setY(Double.parseDouble(jsonX.get("y").toString()));
        }

        return coordinates;
    }
}
