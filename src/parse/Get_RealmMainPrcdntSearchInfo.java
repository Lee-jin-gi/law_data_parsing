package parse;

import java.util.ArrayList;

import common.Common;

public class Get_RealmMainPrcdntSearchInfo {
	public static void main(String[] args) throws Exception {
		ArrayList<String> seq = new ArrayList<>();
		String function = "getRealmMainPrcdntSearchInfo";

		ArrayList<String> url_list = Common.getURL(function, 0, null);
		seq = Common.parsing(url_list, function);
		
		
		function = "getRealmMainPrcdntDetailInfo";
		url_list.clear();
		url_list = Common.getURL(function, 0, seq);
		Common.parsing(url_list, function);
		
	}

}
