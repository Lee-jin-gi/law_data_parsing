package parse;

import java.util.ArrayList;

import common.Common;

public class Get_LawordInfo {

	/**
	 * gubun 
	 * ���� ���� ( �ֱٰ�������-��Ģ����(15), ����-���(21), ����-������Ǽҹ�(22), ����-������Ǽҳ���(23),
	 * ����-������Ǽұ�Ģ(24))
	 */
	final public static int gubun = 21;

	/**
	 * 
	 * ���ɸ�� ���� ��ȸ �� ���ɻ� ���� ��ȸ
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
