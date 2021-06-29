package billing.masters.controller.hlp;


import javax.sql.rowset.WebRowSet;

import billing.masters.vo.GroupMstVO;
public class GroupMstHLP {

	public String getHospitalServiceDetails(GroupMstVO vo) throws Exception
	{
		
		WebRowSet wb = null;
		StringBuffer sb = new StringBuffer();
		wb=vo.getHospitalServiceCheckboxwb();
		try
		{
			sb.append("<table CLASS ='TABLEWIDTH' align ='center' cellpadding='1px' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td class='LABEL'  width='13%'><span class='style1'>*</span>Hospital Services</td>");
			if (wb.size() != 0)
			{
				
				while (wb.next()) 
				{
				
					//sb.append("<td width='3%' class ='CONTROL'> <input type='checkbox' name='hospServiceValue' id='hospService' value='"+wb.getString(1)+ "'>"+ wb.getString(2)
					sb.append("<td width='3%' class ='CONTROL'> <input type='checkbox' name='hospServiceValue' id='hospService' value='"+wb.getString(1)+ "'>"+ wb.getString(2)

					+ "</td>");
			    }
		    }
			sb.append("</tr>");
			sb.append("</table>");
		}
		catch (Exception e) {

			throw new Exception("HLPChargeMst.getDataFrmPack() --> "
					+ e.getMessage());
		}

		return sb.toString();
	}
	
	public String getHospitalServiceValues(GroupMstVO vo) throws Exception
	{
		
		WebRowSet wb = null;
		StringBuffer sb = new StringBuffer();
		wb=vo.getHospitalServiceCheckboxwb();
		try
		{
			sb.append("<table CLASS ='TABLEWIDTH' align ='center' cellpadding='1px' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td class='LABEL'  width='13%'><span class='style1'>*</span> Hospital Services</td></td>");
			if (wb.size() != 0)
			{
				
				while (wb.next()) 
				{
				
					if(wb.getInt(3)==1)
					{
					sb.append("<td width='3%' class ='CONTROL'> <input type='checkbox' name='hospServiceValue' id='hospService' value='"+wb.getString(1)+ "' checked disabled>"+ wb.getString(2)
							+ "</td>");
					}
					else
					{
						sb.append("<td width='3%' class ='CONTROL'> <input type='checkbox'  name='hospServiceValue' id='hospService' value='"+wb.getString(1)+ "'>"+ wb.getString(2)
								+ "</td>");
					}
			    }
		    }
			sb.append("</tr>");
			sb.append("</table>");
		}
		catch (Exception e) {

			throw new Exception("HLPChargeMst.getDataFrmPack() --> "
					+ e.getMessage());
		}

		return sb.toString();
	}
	
}
