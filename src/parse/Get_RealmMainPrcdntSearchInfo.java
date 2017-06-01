package parse;

import java.util.ArrayList;

import common.Common;

public class Get_RealmMainPrcdntSearchInfo {
	
	/**
	 * 
	 * 분야별주요판례 목록 정보 조회 및 분야별주요판례 상세 정보 조회
	 * 
	 */
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
