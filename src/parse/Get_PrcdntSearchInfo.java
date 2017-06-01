package parse;

import java.util.ArrayList;

import common.Common;

public class Get_PrcdntSearchInfo {
//	getPrcdntSearchInfo
	
	
	/**
	 * ------- 대략 50,000개 가량... 50분 대기 실패.
	 * 판례검색 목록 정보 조회
	 * 
	 */
	public static void main(String[] args) throws Exception {
		String function = "getPrcdntSearchInfo";
		
		ArrayList<String> url_list = Common.getURL(function, 0, null);
		Common.parsing(url_list, function);
		
		
	}
}
