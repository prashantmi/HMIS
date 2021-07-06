package HisWeb.util;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HisWeb.dao.TemplateDao;

public class TemplateSaveUtil {

	public static String GetData(String JsonData , HttpServletResponse _response) {
	
		TemplateDao.SaveTemplateJson(JsonData);
		return "";
	}
	
	
}
