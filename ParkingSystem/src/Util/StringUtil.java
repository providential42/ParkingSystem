package Util;
//ÅĞ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
public class StringUtil {
	//ÅĞ¶ÏÊÇ·ñÎª¿Õ
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str.trim()))
		{
			return true;
		}
		else {
			return false;
		}
	}
	//ÅĞ¶ÏÊÇ·ñ²»ÊÇ¿Õ
	public static boolean isNotEmpty(String str) {
		if(str!=null &&  !"".equals(str.trim()))
		{
			return true;
		}
		else {
			return false;
		}
	}

}
