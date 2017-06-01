package parse;

import java.util.ArrayList;

import common.Common;

public class Get_PblctLtrtreSearchInfo {
	
	/**
	 * 
	 * 발간문헌 검색 목록 정보 조회 및 발간문헌 검색 상세 정보 조회
	 * 
	 */
	public static void main(String[] args) throws Exception {
		ArrayList<String> book_seq = new ArrayList<>();
		String function = "getPblctLtrtreSearchInfo";
		
		ArrayList<String> url_list = Common.getURL(function, 0, null);
		Common.parsing(url_list, function);
		
		
		book_seq = Common.parsing(url_list, function);
		
		function = "getPblctLtrtreDetailInfo";
		url_list.clear();
		
		url_list = Common.getURL(function, 0, book_seq);
		Common.parsing(url_list, function);
		
	}
}
