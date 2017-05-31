package parse;

import java.util.ArrayList;

import common.Common;

public class Get_PblctLtrtrePdicalInfo {
	public static void main(String[] args) throws Exception {
		String function = "getPblctLtrtrePdicalInfo";
		ArrayList<String> code = new ArrayList<>();
		code.add("a");
		code.add("b");
		
		ArrayList<String> url_list = Common.getURL(function, 0, code);
		Common.parsing(url_list, function);
		
		
	}
}
