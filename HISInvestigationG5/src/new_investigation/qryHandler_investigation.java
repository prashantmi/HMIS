/*
 	This file is used to load query resource file
*/

package new_investigation; 
import java.util.Locale;
import java.util.ResourceBundle;

public class qryHandler_investigation 
{
	static String mstQuery = "new_investigation.investigationMstQuerynew";		
	
	static ResourceBundle mstRes = null;
	
	/*
		index defined for master = 1
		index defined for transaction = 2
		index defined for report = 3
	*/
	
	public static String getQuery(int index,String qryIndex) {

		String qry = "";
		Locale Locale = new Locale("en","US");
		switch(index) {
			case 1:
				if(mstRes == null) mstRes = ResourceBundle.getBundle(mstQuery,Locale);
				System.out.println( "Locale is "  + Locale.getLanguage());
				qry = mstRes.getString(qryIndex); 
							
				
				break;
							
			
		}
				
		return qry;
	}
	
}
