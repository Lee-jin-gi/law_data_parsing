package parse;

import java.util.ArrayList;

import common.Common;

/**
 * 
 * �Ƿʿ����� �˻� ��� ���� ��ȸ
 *
 */
public class Get_OcprOutlineSearchInfo {
	public static void main(String[] args) throws Exception {
		String function = "getOcprOutlineSearchInfo";
		
		ArrayList<String> url_list = Common.getURL(function, 0, null);
		Common.parsing(url_list, function);
		
		
	}
}
