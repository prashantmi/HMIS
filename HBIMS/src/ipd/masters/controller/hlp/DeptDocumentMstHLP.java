package ipd.masters.controller.hlp;

import ipd.masters.vo.DeptDocumentMstVO;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class DeptDocumentMstHLP 
{
	public String getStrCFSRView(DeptDocumentMstVO vo) throws Exception
	{
		WebRowSet wb = vo.getWsComponent();
		StringBuffer br = new StringBuffer();
		try 
		{
			if (wb != null && wb.size()>0) 
			{
				   br.append("<table width='100%' align='center'> ");
				   br.append("<tr>");
				   br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>Component Name</td>");
				   br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>File Name</td>");
				   br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>Record Status</td>");
				   br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>Remarks</td>");
				   br.append("</tr>");
				   while(wb.next())
				   {
						br.append("<TR>");
					    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(1) + "</TD>");
					    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(2) + "</TD>");
					    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(3) + "</TD>");
					    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(4) + "</TD>");
					    br.append("</TR>");
				   }				
				br.append("</table>");				
			}
		} 
		catch (SQLException e)
		{
			//e.printStackTrace();
			throw new Exception("ipd.HLPDeptDocumentMst.getStrCFSRView()"+ e.getMessage()); 
		}
		return br.toString();
	}
	
	public String getAddedComponent(DeptDocumentMstVO vo) throws Exception
	{
		StringBuffer br = new StringBuffer();
		WebRowSet wb = vo.getComponent();
		try
		{
			if (wb != null) 
			{
				if(wb.size() != 0)
				{
					br.append("<table class='TABLEWIDTH' align='center'>");
					br.append("<tr> ");
                    br.append("<td colspan='6' class='TITLE'>");
                    br.append("<div id='plusComponentDivId'><img ");
                    br.append("src='../../hisglobal/images/plus.gif' name='plusonLine' ");
                    br.append("style='cursor:pointer;position: absolute;'");
                    br.append("onclick='showDetails(\"ComponentDivId\");' />" +
                    		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Added Component</div> ");
                    br.append("<div id='minusComponentDivId' style='display: none'><img ");
                    br.append("src='../../hisglobal/images/minus.gif' name='minusonLine' ");
                    br.append("style='cursor:pointer;position: absolute;'");
                    br.append("onclick='hideDetails(\"ComponentDivId\");'>" +
                    		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Added Component</div> ");
                    br.append("</td></tr> ");                    
                    br.append("</table>");
                    br.append("<div id='ComponentDivId' style='display: none'> ");
                    br.append("<table class='TABLEWIDTH' align='center'>");
                    br.append("<tr>");
                    br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>Component Name</td>");
 				    br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>File Name</td>");
 				    br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>Record Status</td>");
 				    br.append("<td WIDTH='25%' class='multiLabel' colspan='1'>Remarks</td>");
                    br.append("</tr> ");
                    while (wb.next())
                    {
                    	br.append("<TR>");
    				    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(1) + "</TD>");
    				    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(2) + "</TD>");
    				    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(3) + "</TD>");
    				    br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(4) + "</TD>");
    				    br.append("</TR>");
    				    br.append("<input type='hidden' name='file' value='"+wb.getString(2)+"'>");
    				    //br.append("<input type='hidden' name='status' value='"+wb.getString(3)+"' >");
    				    //br.append("<input type='hidden' name='remark' value='"+wb.getString(4)+"' >");
                     }
                   br.append("</table>");
                   br.append("</div>");
              }
        }
	}
	catch (Exception e) 
	{
			throw new Exception("ipd.HLPDeptDocumentMst.getAddedComponent()"+ e.getMessage()); 
	}
	return br.toString();
	}
}