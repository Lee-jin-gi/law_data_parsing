package parse;

import java.util.ArrayList;

import common.Common;

public class Get_LawordInfo {

	/**
	 * gubun 
	 * 법령 정보 ( 최근개정법령-규칙내규(15), 법령-헌법(21), 법령-헌법재판소법(22), 법령-헌법재판소내규(23),
	 * 법령-헌법재판소규칙(24))
	 */
	final public static int gubun = 21;

	/**
	 * 
	 * 법령목록 정보 조회 및 법령상세 정보 조회
	 * 
	 */
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
