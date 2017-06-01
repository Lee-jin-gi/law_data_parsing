package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVWriter;

public class Export_csv {

	public static Map<Integer, Object> hmap = null;
	public static ArrayList<String[]> list = new ArrayList<>();

	/**
	 * 각 function 별 csv Header 셋팅하는 메소드.
	 * 
	 * @param function
	 * @return
	 */
	public static ArrayList<String[]> Header_setting(String function) {
		hmap = new HashMap<Integer, Object>();

		switch (function) {
		case "getLawordInfo":
			hmap.put(0, "boardID");
			hmap.put(1, "lawType");
			hmap.put(2, "title");
			hmap.put(3, "revDate");
			hmap.put(4, "regDate");
			break;

		case "getLawordDetailInfo":
			hmap.put(0, "board_id");
			hmap.put(1, "lawType");
			hmap.put(2, "title");
			hmap.put(3, "fileNm");
			hmap.put(4, "fileLink");
			hmap.put(5, "content");
			hmap.put(6, "regDate");
			break;
		case "getOcprPrcdntInfo":
			hmap.put(0, "title");
			hmap.put(1, "exeCnt");
			hmap.put(2, "adjudgeYm");
			hmap.put(3, "atchNm");
			hmap.put(4, "atchFilePath");
			hmap.put(5, "atchNm2");
			hmap.put(6, "atchFilePath2");
			hmap.put(7, "regDate");
			hmap.put(8, "chgDate");
			break;

		case "getRealmMainPrcdntSearchInfo":
			hmap.put(0, "seq");
			hmap.put(1, "title");
			hmap.put(2, "attachSeq");
			hmap.put(3, "classNm");
			hmap.put(4, "regDate");
			hmap.put(5, "chgDate");
			break;

		case "getRealmMainPrcdntDetailInfo":
			hmap.put(0, "seq");
			hmap.put(1, "nick");
			hmap.put(2, "code");
			hmap.put(3, "eventNo");
			hmap.put(4, "eventNm");
			hmap.put(5, "adjudgeDt");
			hmap.put(6, "exeBook");
			hmap.put(7, "atchNm");
			hmap.put(8, "atchFilePath");
			hmap.put(9, "content");
			break;

		case "getPrcdntSearchInfo":
			hmap.put(0, "eventNum");
			hmap.put(1, "eventNo");
			hmap.put(2, "eventNm");
			hmap.put(3, "engEventNo");
			hmap.put(4, "engEventName");
			hmap.put(5, "panreType");
			hmap.put(6, "volumInfo");
			hmap.put(7, "seriesInfo");
			hmap.put(8, "pages");
			hmap.put(9, "jgdmtCort");
			hmap.put(10, "rstaRsta");
			hmap.put(11, "rstaDate");
			hmap.put(12, "apiRegDate");
			hmap.put(13, "apiChgDate");
			break;
			
		case "getPblctLtrtreSearchInfo":
			hmap.put(0, "bookInfoSeq");
			hmap.put(1, "title");
			hmap.put(2, "bookSection");
			hmap.put(3, "volumnInfo");
			hmap.put(4, "seriesInfo");
			hmap.put(5, "pageInfo");
			hmap.put(6, "publisher");
			hmap.put(7, "bookReporter");
			hmap.put(8, "pressDate");
			hmap.put(9, "apiRegDate");
			hmap.put(10, "apiChgDate");
			break;
			
		case "getPblctLtrtreDetailInfo":
			hmap.put(0, "book_seq_id");
			hmap.put(1, "xmlContent");
			hmap.put(2, "bookInfoSeq");
			hmap.put(3, "bookReporter");
			hmap.put(4, "title");
			hmap.put(5, "bookSection");
			hmap.put(6, "volumnInfo");
			hmap.put(7, "seriesInfo");
			hmap.put(8, "publisher");
			hmap.put(9, "pressDate");
			hmap.put(10, "startPage");
			hmap.put(11, "apiRegDate");
			hmap.put(12, "apiChgDate");
			break;
		case "getOcprOutlineSearchInfo":
			hmap.put(0, "prcdntNo");
			hmap.put(1, "title");
			hmap.put(2, "cntntpth");
			hmap.put(3, "regDate");
			hmap.put(4, "chgDate");
			break;
		default:
			break;
		}

		// list.add((String[]) hmap.keySet().toArray(new String[hmap.size()]));
		list.add((String[]) hmap.values().toArray(new String[hmap.size()]));

		return list;
	}

	// public static void map_set(String a, String b, String c, String d, String
	// e) {
	// hmap = new HashMap<Integer, Object>();
	// hmap.put(0, a);
	// hmap.put(1, b);
	// hmap.put(2, c);
	// hmap.put(3, d);
	// hmap.put(4, e);
	//
	// list.add(hmap);
	// }
	//
	// public static void map_set(String a, String b, String c, String d, String
	// e, String f) {
	// hmap = new HashMap<Integer, Object>();
	// hmap.put(0, a);
	// hmap.put(1, b);
	// hmap.put(2, c);
	// hmap.put(3, d);
	// hmap.put(4, e);
	// hmap.put(5, f);
	//
	// list.add(hmap);
	// }
	//
	// public static void map_set(String id, String a, String b, String c,
	// String d, String e, String f) {
	// hmap = new HashMap<Integer, Object>();
	// hmap.put(0, id);
	// hmap.put(1, a);
	// hmap.put(2, b);
	// hmap.put(3, c);
	// hmap.put(4, d);
	// hmap.put(5, e);
	// hmap.put(6, f);
	//
	// list.add(hmap);
	// }
	//
	// public static void map_set(String a, String b, String c, String d, String
	// e, String f, String g, String h,
	// String i) {
	// hmap = new HashMap<Integer, Object>();
	// hmap.put(0, a);
	// hmap.put(1, b);
	// hmap.put(2, c);
	// hmap.put(3, d);
	// hmap.put(4, e);
	// hmap.put(5, f);
	// hmap.put(6, g);
	// hmap.put(7, h);
	// hmap.put(8, i);
	//
	// list.add(hmap);
	// }
	//
	// public static void map_set(String id, String a, String b, String c,
	// String d, String e, String f, String g,
	// String h, String i) {
	// hmap = new HashMap<Integer, Object>();
	// hmap.put(0, id);
	// hmap.put(1, a);
	// hmap.put(2, b);
	// hmap.put(3, c);
	// hmap.put(4, d);
	// hmap.put(5, e);
	// hmap.put(6, f);
	// hmap.put(7, g);
	// hmap.put(8, h);
	// hmap.put(9, i);
	//
	// list.add(hmap);
	// }

	/**
	 * 파싱한 데이터를 csv 각 셀에 입력하는 메소드.
	 * 
	 * @param item
	 *            - MultiParameter
	 */
	public static void map_set(String... item) {
		hmap = new HashMap<Integer, Object>();
		for (int i = 0; i < item.length; i++) {
			hmap.put(i, item[i]);
		}
		// hmap.put(0, item[i]);
		// hmap.put(1, a);
		// hmap.put(2, b);
		// hmap.put(3, c);
		// hmap.put(4, d);
		// hmap.put(5, e);
		// hmap.put(6, f);
		// hmap.put(7, g);
		// hmap.put(8, h);
		// hmap.put(9, i);

		// list.add((String[]) hmap.keySet().toArray(new String[0]));
		// list.add((String[]) hmap.keySet().toArray(new String[hmap.size()]));
		list.add((String[]) hmap.values().toArray(new String[hmap.size()]));
	}

	/**
	 * csv 파일 생성하는 메소드.
	 * 
	 * @param function
	 */
	public static void make_csv(String function) {
		try {

			File downD = new File("./csv_files/");
			downD.mkdirs();

			CSVWriter cw = new CSVWriter(
					new OutputStreamWriter(new FileOutputStream("./csv_files/" + function + ".csv"), "EUC-KR"), ',',
					'"');
			try {

				cw.writeAll(list);
				// for (Map<Integer, Object> m : list) {
				// cw.writeNext(new String[] { String.valueOf(m.get(0)),
				// String.valueOf(m.get(1)),
				// String.valueOf(m.get(2)), String.valueOf(m.get(3)),
				// String.valueOf(m.get(4)),
				// String.valueOf(m.get(5)), String.valueOf(m.get(6)),
				// String.valueOf(m.get(7)),
				// String.valueOf(m.get(8)), String.valueOf(m.get(9)),
				// String.valueOf(m.get(10)), String.valueOf(m.get(11)) });

				// switch (function) {
				// case "getLawordInfo":
				// cw.writeNext(new String[] { String.valueOf(m.get(0)),
				// String.valueOf(m.get(1)),
				// String.valueOf(m.get(2)), String.valueOf(m.get(3)),
				// String.valueOf(m.get(4)) });
				// break;
				// case "getLawordDetailInfo":
				// cw.writeNext(new String[] { String.valueOf(m.get(0)),
				// String.valueOf(m.get(1)),
				// String.valueOf(m.get(2)), String.valueOf(m.get(3)),
				// String.valueOf(m.get(4)),
				// String.valueOf(m.get(5)), String.valueOf(m.get(6)) });
				// break;
				// case "getOcprPrcdntInfo":
				// cw.writeNext(new String[] { String.valueOf(m.get(0)),
				// String.valueOf(m.get(1)),
				// String.valueOf(m.get(2)), String.valueOf(m.get(3)),
				// String.valueOf(m.get(4)),
				// String.valueOf(m.get(5)), String.valueOf(m.get(6)),
				// String.valueOf(m.get(7)),
				// String.valueOf(m.get(8)) });
				// break;
				// case "getRealmMainPrcdntSearchInfo":
				// cw.writeNext(new String[] { String.valueOf(m.get(0)),
				// String.valueOf(m.get(1)),
				// String.valueOf(m.get(2)), String.valueOf(m.get(3)),
				// String.valueOf(m.get(4)),
				// String.valueOf(m.get(5)) });
				// break;
				// case "getRealmMainPrcdntDetailInfo":
				// cw.writeNext(new String[] { String.valueOf(m.get(0)),
				// String.valueOf(m.get(1)),
				// String.valueOf(m.get(2)), String.valueOf(m.get(3)),
				// String.valueOf(m.get(4)),
				// String.valueOf(m.get(5)), String.valueOf(m.get(6)),
				// String.valueOf(m.get(7)),
				// String.valueOf(m.get(8)), String.valueOf(m.get(9)) });
				// break;
				// default:
				// break;
				// }

				// }
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		hmap.clear();
		list.clear();
	}

}
