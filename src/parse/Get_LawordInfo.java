package parse;

import java.util.ArrayList;

import common.Common;

public class Get_LawordInfo {
	
	public static int gubun = 21;

	public static int responseCode = 0;

	public static void main(String[] args) throws Exception {
		ArrayList<String> board_id = new ArrayList<>();
		String function = "getLawordInfo";
		
		ArrayList<String> url_list = Common.getURL(function, gubun, null);
		
		board_id = Common.parsing(url_list, function);
		
		
		function = "getLawordDetailInfo";
		url_list.clear();
		url_list = Common.getURL(function, gubun, board_id);
		Common.parsing(url_list, function);
		
		
	}

}
