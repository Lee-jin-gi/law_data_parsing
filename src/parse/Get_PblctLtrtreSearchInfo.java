package parse;

import java.util.ArrayList;

import common.Common;

public class Get_PblctLtrtreSearchInfo {
	
	/**
	 * 
	 * �߰����� �˻� ��� ���� ��ȸ �� �߰����� �˻� �� ���� ��ȸ
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
