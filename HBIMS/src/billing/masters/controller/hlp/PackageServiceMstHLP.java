package billing.masters.controller.hlp;
/* Package Service Master HLP
 * author: Debashis Sardar
 * Created on : 02-Sep-2011
 */
import javax.sql.rowset.WebRowSet;

public class PackageServiceMstHLP {

	public String getTariffDetails(WebRowSet wb) throws Exception

	{

		StringBuffer Br = new StringBuffer();
		try {

			int i = 0;

			Br.append("<table class='TABLEWIDTH' align='center'>");

			Br.append("<tr>");

			Br
					.append("<td class='LABEL' WIDTH='35%' align='LEFT'><div align=center>Tariff Name</div></td>");

			Br
					.append("<td class='LABEL' WIDTH='20%' align='LEFT'><div align=center>Quantity</div></td>");

			Br
					.append("<td class='LABEL' WIDTH='20%' align='LEFT'><div align=center>Unit Name</div></td>");

			Br
					.append("<td class='LABEL' WIDTH='25%' align='LEFT'><div align=center>Record Status</div></td>");

			while (wb.next())

			{
				
				if (wb.getString(4).equals("In-Active"))
				{
					Br.append("<TR >");
					Br.append("<TD WIDTH='35%' class='redMultiControl'>");
					Br.append(wb.getString(1)+ "<input type='hidden' name='tariffName' value='"
							+ wb.getString(1) + "'></TD>");
					Br.append("<TD WIDTH='20%' class='redMultiControl'>");
					Br.append(wb.getString(2) + "</TD>");
					Br.append("<TD WIDTH='20%' class='redMultiControl'>");
					Br.append(wb.getString(3) + "</TD>");
					Br.append("</div>");
					Br.append("<TD  nowrap class='redMultiControl'>");
					Br.append(wb.getString(4) + "</TD>");
					Br.append("</TR>");

				} 
				else 
				{
					Br.append("<TR class='errMsg'>");
					Br.append("<TD WIDTH='35%' CLASS='multiControl' >"
              					+ wb.getString(1)
				    			+ "<input type='hidden' name='tariffName' value='"
					    		+ wb.getString(1) + "'></TD>");
					Br.append("<TD WIDTH='20%' CLASS='multiControl'>"+ wb.getString(2) + "</TD>");

					Br.append("<TD WIDTH='20%' CLASS='multiControl'>"+ wb.getString(3) + "</TD>");

					Br.append("<TD CLASS='multiControl' nowrap>"+ wb.getString(4) + "</TD>");

					Br.append("</TR>");

				}
				Br.append("<input type='hidden' name='tariffd' value='"+ wb.getString(1) + "' >");
				i++;
			}

			Br.append("<input type='hidden' name='length' value='" + i + "'");

			Br.append("</table>");

		} catch (Exception e) {

	 

			throw new Exception("billing.HLPpackservMst.getTariffDetails()" + e

			.getMessage());

		}

		return Br.toString();

	}

}
