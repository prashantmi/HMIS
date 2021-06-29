package ipd.masters.controller.hlp;

import hisglobal.exceptions.HisException;
import ipd.masters.vo.DUWRBedMstVO;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class DUWRBedMstHLP {

	/**
	 * By this method user get view page of selected beds<br>
	 * How many beds are selected in DepartmentUnitWardRoom.
	 * @param vo - FormBean Object
	 * @return - Beds in table Form.
	 */
	public String getStrBedView(DUWRBedMstVO vo) {
		WebRowSet wb = vo.getWstemp();
		StringBuffer br = new StringBuffer();
		try {
			if (wb != null) 
			{
				br.append(" <table align='center' cellspacing='1px' width='100%'>");
				while(wb.next())
				{
					br.append("<tr>");
					br.append("<td width='100%' class='CONTROL'><div align='center'>");
					br.append(wb.getString(1));
					br.append("</td></div>");
					br.append("</tr>");
				}
			br.append("</table>");
			}
		}
		 catch (SQLException e) {
			 new HisException("IPD", "HLPDUWRBedMst.getStrBedView", e.getMessage());
			 //e.printStackTrace();
		 }
		 return br.toString();
	}
}
