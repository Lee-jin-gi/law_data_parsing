package common;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Common {
	public static String endpoint = "http://openapi.ccourt.go.kr/openapi/services/OrdinanceInfoSvc/";
	public static String service_key = "hOMvRf90C4YKGKSnpwQ4AmHlM3%2FZkgEN46XOa%2BTsX2pV4KMTh%2B7SkIiWoDU%2BuA%2F6ACAiWq3hGP5EkysVDcaP7g%3D%3D";

	public static String getURL(String function, int gubun) {
		String final_url = endpoint + function + "?ServiceKey=" + service_key + "&gubun=" + gubun;
		return final_url;
	}

	public static void parsing(URLConnection urlc, String function) throws Exception {

		HttpURLConnection httpConnection = (HttpURLConnection) urlc;
		int responseCode = httpConnection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				InputStream in = httpConnection.getInputStream();
				Document doc = dBuilder.parse(in);
				int length = doc.getElementsByTagName("item").getLength();

				Export_csv.Header_setting();

				for (int i = 0; i < length; i++) {
					Node nNode = (Element) doc.getElementsByTagName("item").item(i);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;

						switch (function) {
						case "getLawordInfo":
							getLawordInfoTag(eElement);
							break;
						default:
							break;
						}
					}
				}

				Export_csv.make_csv(function);

				System.out.println("Finished!");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("");
			System.out.println("fail");

		}
	}

	public static void getLawordInfoTag(Element eElement) {
		Export_csv.map_set(getTagValue("boardId", eElement), getTagValue("lawType", eElement),
				getTagValue("title", eElement), getTagValue("revDate", eElement), getTagValue("regDate", eElement));
	}

	public static String getTagValue(String sTag, Element eElement) {
		// TODO Auto-generated method stub
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();

	}
}
