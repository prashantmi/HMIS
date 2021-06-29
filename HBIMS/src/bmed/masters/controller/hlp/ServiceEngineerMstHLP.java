package bmed.masters.controller.hlp;

import javax.sql.rowset.WebRowSet;

/**
 * @author  Amit Kumar 
 * Creation Date:- 17-Jan-2011 
 * Modifying Date:- 0-0-2011
 * Used For:-	
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ServiceEngineerMstHLP 
{
	/**
	 * Gets the added Task details.
	 * 
	 * @param wb the wb
	 * 
	 * @return the added Task details
	 */
	public static String getServiceEnggNameHlp(WebRowSet wb) 
	{
		StringBuffer br = new StringBuffer();
        int i=1;
		try {
			if (wb != null) {
				if (wb.size() != 0) 
				{
					br.append("<table class='TABLEWIDTH' align='center'  border='0' cellspacing ='1px' cellpadding='1px' bgcolor='black'>");
					br.append("<tr> ");
					br.append("<td WIDTH='25%' class='multiLabel'>Sl No. </td>");
					br.append("<td WIDTH='75%' class='multiLabel'>Service Enggineer Name</td>");
					

					br.append("</tr> ");
					br.append("</table> ");
					br.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px' cellpadding='1px'  bgcolor='#CC9966'>");
					
					while (wb.next())
					{ 						
						br.append("<TR>");
						br.append("<TD WIDTH='25%' CLASS='multiLabel'>"+ i + "</TD>");
						br.append("<TD WIDTH='75%' CLASS='multiControl'>"+ wb.getString(1) + "</TD>");
						
						br.append("</TR>");
						i++;
					}
					
					br.append("</table> ");

				}
			}
		}
		catch (Exception e) 
		{
			

		}
		return br.toString();
	}


}
