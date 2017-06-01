package common;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Common {
	public static String endpoint = "http://openapi.ccourt.go.kr/openapi/services/";

	/** Operation Path **/
	public static String laword_path = "OrdinanceInfoSvc/";
	public static String Prcdnt_path = "PrecedentInfoSvc/";
	public static String prcdnt_search_path = "PrecedentSearchSvc/";

	public static String service_key = "hOMvRf90C4YKGKSnpwQ4AmHlM3%2FZkgEN46XOa%2BTsX2pV4KMTh%2B7SkIiWoDU%2BuA%2F6ACAiWq3hGP5EkysVDcaP7g%3D%3D";
	public static ArrayList<String> id_list = new ArrayList<String>();

	/**
	 * parse 패키지 참조.
	 *
	 * @param function
	 *            Operation Name
	 * @param gubun
	 *            법령 정보 ( 최근개정법령-규칙내규(15), 법령-헌법(21), 법령-헌법재판소법(22),
	 *            법령-헌법재판소내규(23), 법령-헌법재판소규칙(24))
	 * @param id
	 *            상세 검색할 때 필요한 id
	 */
	public static ArrayList<String> getURL(String function, int gubun, ArrayList<String> id) {
		ArrayList<String> final_url = new ArrayList<>();
		switch (function) {
		case "getLawordInfo":
			final_url.add(endpoint + laword_path + function + "?ServiceKey=" + service_key + "&gubun=" + gubun);
			break;
		case "getLawordDetailInfo":
			for (int i = 0; i < id.size(); i++) {
				final_url.add(endpoint + laword_path + function + "?ServiceKey=" + service_key + "&gubun=" + gubun
						+ "&boardId=" + id.get(i));
			}
			break;
		case "getOcprPrcdntInfo":
			final_url.add(endpoint + Prcdnt_path + function + "?ServiceKey=" + service_key);
			break;

		case "getRealmMainPrcdntSearchInfo":
			final_url.add(endpoint + Prcdnt_path + function + "?ServiceKey=" + service_key + "&numOfRows=500");
			break;
		case "getRealmMainPrcdntDetailInfo":
			for (int i = 0; i < id.size(); i++) {
				final_url.add(endpoint + Prcdnt_path + function + "?ServiceKey=" + service_key + "&seq=" + id.get(i));
			}
			break;
		case "getPblctLtrtrePdicalInfo":
			for (int i = 0; i < id.size(); i++) {
				final_url.add(
						endpoint + Prcdnt_path + function + "?ServiceKey=" + service_key + "&searchCode=" + id.get(i));
			}
			break;

		case "getPrcdntSearchInfo":
			final_url.add(endpoint + prcdnt_search_path + function + "?ServiceKey=" + service_key + "&numOfRows=45000");
			break;

		case "getPblctLtrtreSearchInfo":
			final_url.add(endpoint + prcdnt_search_path + function + "?ServiceKey=" + service_key + "&numOfRows=900");
			break;

		case "getPblctLtrtreDetailInfo":
			for (int i = 0; i < id.size(); i++) {
				final_url.add(endpoint + prcdnt_search_path + function + "?ServiceKey=" + service_key + "&bookInfoSeq="
						+ id.get(i));
			}
			break;
		case "getOcprOutlineSearchInfo":
			final_url.add(endpoint + prcdnt_search_path + function + "?ServiceKey=" + service_key + "&numOfRows=7000");
		default:

			break;
		}

		return final_url;
	}

	/**
	 * parse 패키지 참조.
	 *
	 * @param function
	 *            Operation Name
	 * @param url_list
	 *            Parameter에 따라 검색된 모든 url 리스트
	 */
	public static ArrayList<String> parsing(ArrayList<String> url_list, String function) throws Exception {
		URL url;
		ArrayList<String> id = new ArrayList<>();
		Export_csv.Header_setting(function);
		System.out.println("function ::::::::::::::: " + function);
		System.out.println("url_list_size :::::::::::: " + url_list.size());
		for (int a = 0; a < url_list.size(); a++) {
			url = new URL(url_list.get(a));
			System.out.println(url_list.get(a));
			URLConnection urlc = url.openConnection();

			HttpURLConnection httpConnection = (HttpURLConnection) urlc;
			int responseCode = httpConnection.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					InputStream in = httpConnection.getInputStream();
					Document doc = dBuilder.parse(in);

					System.out.println("item_size :::::::::::::::: " + doc.getElementsByTagName("item").getLength());

					/**
					 * getPblctLtrtrePdicalInfo 실행할 때 주석 해제.
					 */
//					String document = toString(doc);
//					document = document.replaceAll("bookList", "item");
//					// System.out.println(document);
//
//					DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//					InputSource is = new InputSource();
//					is.setCharacterStream(new StringReader(document));
//
//					doc = db.parse(is);

					System.out.println("item_size :::::::::::::::: " + doc.getElementsByTagName("item").getLength());
					int length = doc.getElementsByTagName("item").getLength();
					for (int i = 0; i < length; i++) {

						Node nNode = (Element) doc.getElementsByTagName("item").item(i);

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;

							switch (function) {
							case "getLawordInfo":
								id = getLawordInfoTag(eElement);
								break;

							case "getLawordDetailInfo":
								String board_id = url_list.get(a).split("boardId")[1];
								System.out.println(url_list.get(a));
								getLawordDetailInfoTag(board_id, eElement);
								break;

							case "getOcprPrcdntInfo":
								getOcprPrcdntInfoTag(eElement);
								break;
							case "getRealmMainPrcdntSearchInfo":
								id = getRealmMainPrcdntSearchInfoTag(eElement);
								break;

							case "getRealmMainPrcdntDetailInfo":
								String seq_id = url_list.get(a).split("seq")[1];
								getRealmMainPrcdntDetailInfoTag(seq_id, eElement);
								break;

							case "getPblctLtrtrePdicalInfo":
								getPblctLtrtrePdicalInfoTag(eElement);
								break;
							case "getPrcdntSearchInfo":
								getPrcdntSearchInfoTag(eElement);
								break;

							case "getPblctLtrtreSearchInfo":
								getPblctLtrtreSearchInfoTag(eElement);
								break;

							case "getPblctLtrtreDetailInfo":
								String book_seq_id = url_list.get(a).split("bookInfoSeq")[1];
								getPblctLtrtreDetailInfoTag(book_seq_id, eElement);
								break;
							case "getOcprOutlineSearchInfo":
								getOcprOutlineSearchInfoTag(eElement);
								break;
							default:
								System.out.println("stop");
								break;
							}
						}
					}

					System.out.println("Finished!");
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("");
				System.out.println("fail");
				System.out.println("responseCode : " + responseCode);
			}
		}
		Export_csv.make_csv(function);
		return id;

	}

	/**
	 * csv Export를 위한 value 값 셋팅하는 method.
	 */

	public static void getOcprOutlineSearchInfoTag(Element eElement) {
		// TODO Auto-generated method stub
		Export_csv.map_set(getTagValue("prcdntNo", eElement), getTagValue("title", eElement),
				getTagValue("cntntpth", eElement), getTagValue("regDate", eElement), getTagValue("chgDate", eElement));
	}

	public static void getPblctLtrtreDetailInfoTag(String book_seq_id, Element eElement) {
		// TODO Auto-generated method stub
		Export_csv.map_set(book_seq_id, getTagValue("xmlContent", eElement), getTagValue("bookInfoSeq", eElement),
				getTagValue("bookReporter", eElement), getTagValue("title", eElement),
				getTagValue("bookSection", eElement), getTagValue("volumnInfo", eElement),
				getTagValue("seriesInfo", eElement), getTagValue("publisher", eElement),
				getTagValue("pressDate", eElement), getTagValue("startPage", eElement),
				getTagValue("apiRegDate", eElement), getTagValue("apiChgDate", eElement));
	}

	public static void getPblctLtrtreSearchInfoTag(Element eElement) {
		// TODO Auto-generated method stub
		Export_csv.map_set(getTagValue("bookInfoSeq", eElement), getTagValue("title", eElement),
				getTagValue("bookSection", eElement), getTagValue("volumnInfo", eElement),
				getTagValue("seriesInfo", eElement), getTagValue("pageInfo", eElement),
				getTagValue("publisher", eElement), getTagValue("bookReporter", eElement),
				getTagValue("pressDate", eElement), getTagValue("apiRegDate", eElement),
				getTagValue("apiChgDate", eElement));
	}

	public static void getPrcdntSearchInfoTag(Element eElement) {
		Export_csv.map_set(getTagValue("eventNum", eElement), getTagValue("eventNo", eElement),
				getTagValue("eventNm", eElement), getTagValue("engEventNo", eElement),
				getTagValue("engEventName", eElement), getTagValue("panreType", eElement),
				getTagValue("volumInfo", eElement), getTagValue("seriesInfo", eElement), getTagValue("pages", eElement),
				getTagValue("jgdmtCort", eElement), getTagValue("rstaRsta", eElement),
				getTagValue("rstaDate", eElement), getTagValue("apiRegDate", eElement),
				getTagValue("apiChgDate", eElement));
	}

	public static void getPblctLtrtrePdicalInfoTag(Element eElement) {
		Export_csv.map_set(getTagValue("sign", eElement), getTagValue("pubFreq", eElement),
				getTagValue("pubDate", eElement), getTagValue("bookImg", eElement), getTagValue("pubYear", eElement),
				getTagValue("page", eElement), getTagValue("content", eElement), getTagValue("pageLink", eElement),
				getTagValue("regDate", eElement), getTagValue("orgLink", eElement), getTagValue("fileNm", eElement),
				getTagValue("fileLink", eElement));
	}

	public static void getRealmMainPrcdntDetailInfoTag(String id, Element eElement) {
		// id -> 참조하는 id값.
		Export_csv.map_set(id, getTagValue("nick", eElement), getTagValue("code", eElement),
				getTagValue("eventNo", eElement), getTagValue("eventNm", eElement), getTagValue("adjudgeDt", eElement),
				getTagValue("exeBook", eElement), getTagValue("atchNm", eElement),
				getTagValue("atchFilePath", eElement), getTagValue("content", eElement));
	}

	public static ArrayList<String> getRealmMainPrcdntSearchInfoTag(Element eElement) {
		// seq 별 상세정보 받기 위하여 list에 add.

		id_list.add(getTagValue("seq", eElement));
		Export_csv.map_set(getTagValue("seq", eElement), getTagValue("title", eElement),
				getTagValue("attachSeq", eElement), getTagValue("classNm", eElement), getTagValue("regDate", eElement),
				getTagValue("chgDate", eElement));

		return id_list;
	}

	private static void getOcprPrcdntInfoTag(Element eElement) {
		Export_csv.map_set(getTagValue("title", eElement), getTagValue("exeCnt", eElement),
				getTagValue("adjudgeYm", eElement), getTagValue("atchNm", eElement),
				getTagValue("atchFilePath", eElement), getTagValue("atchNm2", eElement),
				getTagValue("atchFilePath2", eElement), getTagValue("regDate", eElement),
				getTagValue("chgDate", eElement));
	}

	public static void getLawordDetailInfoTag(String id, Element eElement) {
		// id -> 참조하는 id값.
		Export_csv.map_set(id, getTagValue("lawType", eElement), getTagValue("title", eElement),
				getTagValue("fileNm", eElement), getTagValue("fileLink", eElement), getTagValue("content", eElement),
				getTagValue("regDate", eElement));
	}

	public static ArrayList<String> getLawordInfoTag(Element eElement) {
		// boardId 별 상세정보 받기 위하여 list에 add.
		id_list.add(getTagValue("boardId", eElement));
		Export_csv.map_set(getTagValue("boardId", eElement), getTagValue("lawType", eElement),
				getTagValue("title", eElement), getTagValue("revDate", eElement), getTagValue("regDate", eElement));

		return id_list;
	}

	/**
	 * xml Value 파싱하는 메소드.
	 **/
	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = null;
		String data = "";
		try {
			nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

			for (int index = 0; index < nlList.getLength(); index++) {

				// CDATA 값 파싱하기 위한 구문
				if (nlList.item(index) instanceof CharacterData) {
					CharacterData child = (CharacterData) nlList.item(index);
					data = child.getData();

					if (data != null && data.trim().length() > 0) {
						return data;
					}
				}
			}
		} catch (NullPointerException npe) {
			data = "";
		}
		return data;
	}

	/**
	 * doc -> String으로 변환하는 메소드
	 * 
	 * @param doc
	 * @return
	 */
	public static String toString(Document doc) {
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			transformer.transform(new DOMSource(doc), new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
			throw new RuntimeException("Error converting to String", ex);
		}
	}

}
