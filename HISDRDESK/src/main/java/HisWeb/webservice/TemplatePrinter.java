package HisWeb.webservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface TemplatePrinter {
	

	public abstract byte[] genTempPrint(Map<String, String> tmpData,HttpServletRequest req);

}
