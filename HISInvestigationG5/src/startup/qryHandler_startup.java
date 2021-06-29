/*
 	This file is used to load query resource file
*/

package startup; 
import java.util.ResourceBundle;

public class qryHandler_startup 
{
	static String Query = "startup.Startup_Query";		
	static ResourceBundle QryRes = null;

	public static String getQuery(String qryIndex) {

		String qry = "";
		if(QryRes == null) QryRes = ResourceBundle.getBundle(Query);
		//System.out.println("QryRes"+QryRes);
		qry = QryRes.getString(qryIndex); 
		return qry;
	}
}
