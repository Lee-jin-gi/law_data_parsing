package parse;

import java.util.ArrayList;

import common.Common;

public class Get_PblctLtrtrePdicalInfo {
	
	/**
	 * 
	 * 발간문헌 정보 조회
	 * Code
	 * a : 연속간행물, b: 단행본
	 * 
	 */
	public static void main(String[] args) throws Exception {
		String function = "getPblctLtrtrePdicalInfo";
		ArrayList<String> code = new ArrayList<>();
		code.add("a");
		code.add("b");
		
		ArrayList<String> url_list = Common.getURL(function, 0, code);
		Common.parsing(url_list, function);
		
		
	}
}
