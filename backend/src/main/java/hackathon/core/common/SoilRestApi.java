package hackathon.core.common;


import hackathon.core.domain.Soil;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class SoilRestApi {

    @GetMapping(value = "/soil_api")
    public Soil callSoil(@RequestParam("code") String code) {

        Soil soil = new Soil();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        String result = "";
        String serviceKey = "d5%2FyMFcBzVvwDgyGMghgGUfLkVb8RFJdoENpvDFW9XrHwTOgM2VnuFwhaLWxRR79nrA2CxpkmZvXxiwpghC8lw%3D%3D";

        try {
            String urlstr = "http://apis.data.go.kr/1390802/SoilEnviron/SoilExam/getSoilExam?" +
                    "ServiceKey=" + serviceKey +
                    "&PNU_Code=" + code;

            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result = result + returnLine.trim();
            }

            urlConnection.disconnect();

            // xml 파싱
            InputSource is = new InputSource(new StringReader(result));
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            XPathExpression expr = (XPathExpression) xpath.compile("//items/item");
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList child = nodeList.item(i).getChildNodes();

                soil.setPNU_Code(child.item(0).getTextContent());
                soil.setAny_Year(child.item(1).getTextContent());
                soil.setExam_Day(child.item(2).getTextContent());
                soil.setExam_Type(child.item(3).getTextContent());
                soil.setPnu_Nm(child.item(4).getTextContent());

                soil.setACID(Double.parseDouble(child.item(5).getTextContent()));
                soil.setVLDPHA(Double.parseDouble(child.item(6).getTextContent()));
                soil.setVLDSIA(Double.parseDouble(child.item(7).getTextContent()));
                soil.setOM(Double.parseDouble(child.item(8).getTextContent()));
                soil.setPOSIFERT_MG(Double.parseDouble(child.item(9).getTextContent()));
                soil.setPOSIFERT_K(Double.parseDouble(child.item(10).getTextContent()));
                soil.setPOSIFERT_CA(Double.parseDouble(child.item(11).getTextContent()));
                soil.setSELC(Double.parseDouble(child.item(12).getTextContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soil;
    }
}
