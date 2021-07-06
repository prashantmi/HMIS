package HisWeb.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import application.filters.ParamObject;

public class TemplateUtil {

	public static Set<String> keys = new HashSet<String>();

	
	public static List<ParamObject> getParamsFromSimpleForm(HttpServletRequest request) {

		Enumeration<String> paramNames = request.getParameterNames();

		List<ParamObject> paramMap = new ArrayList<ParamObject>();

		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
		//	System.out.println(" param name is " + paramName);
			if (!keys.contains(paramName.toLowerCase())) {

				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() > 0)
						// paramValue= "108";
						paramMap.add(new ParamObject(paramName.toLowerCase(),
								paramValue));

				} else {
					for (int i = 0; i < paramValues.length; i++) {
						if (!("hmode".equalsIgnoreCase(paramName) && i == 0))
							paramMap.add(new ParamObject(paramName
									.toLowerCase(), paramValues[i]));
					}

				}

			}

		}
		System.out.println("paramMap ::::;" +paramMap.toString());
		return paramMap;
	}
	
}


