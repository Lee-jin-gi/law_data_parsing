package parse;

import java.net.URL;
import java.net.URLConnection;

import common.Common;

public class Get_Laword_Info {
	public static String function = "getLawordInfo";
	public static int gubun = 21;

	public static int responseCode = 0;

	public static void main(String[] args) throws Exception {

		String law_list_info = Common.getURL(function, gubun);
		URL url = new URL(law_list_info);
		URLConnection urlc = url.openConnection();

		Common.parsing(urlc, function);
	}

}
