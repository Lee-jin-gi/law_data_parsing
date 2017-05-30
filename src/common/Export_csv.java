package common;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVWriter;

public class Export_csv {

	public static Map<Integer, Object> hmap = null;
	public static ArrayList<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();

	public static ArrayList<Map<Integer, Object>> Header_setting() {
		hmap = new HashMap<Integer, Object>();

		
			hmap.put(0, "boardID");
			hmap.put(1, "lawType");
			hmap.put(2, "title");
			hmap.put(3, "revDate");
			hmap.put(4, "regDate");
		
		list.add(hmap);

		return list;
	}

	public static void map_set(String a, String b, String c, String d, String e) {
		hmap = new HashMap<Integer, Object>();
		hmap.put(0, a);
		hmap.put(1, b);
		hmap.put(2, c);
		hmap.put(3, d);
		hmap.put(4, e);

		list.add(hmap);
	}

	public static void make_csv(String function) {
		try {

			CSVWriter cw = new CSVWriter(
					new OutputStreamWriter(new FileOutputStream("D:\\test/" + function + ".csv"), "EUC-KR"), ',', '"');
			try {
				for (Map<Integer, Object> m : list) {
					
					cw.writeNext(new String[] { String.valueOf(m.get(0)), String.valueOf(m.get(1)),
							String.valueOf(m.get(2)), String.valueOf(m.get(3)), String.valueOf(m.get(4)) });
				}
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
