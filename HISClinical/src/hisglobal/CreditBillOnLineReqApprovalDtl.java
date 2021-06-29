package hisglobal;

import hisglobal.exceptions.HisException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.sql.rowset.WebRowSet;

import billing.BillingBO;
import billing.BillingVO;

public class CreditBillOnLineReqApprovalDtl implements Tag 
{
	PageContext pageContext;

	public String tmp = "";
	private String crNo = "0";
	private String mode = "12"; //12 for credit billing
	private String deptCode = "0";
	
	public String getDeptCode() 
	{
		return deptCode;
	}
	public void setDeptCode(String deptCode) 
	{
		this.deptCode = deptCode;
	}
	public String getCrNo() 
	{
		return crNo;
	}
	public void setCrNo(String crNo) 
	{
		this.crNo = crNo;
	}
	public int doEndTag() throws JspException 
	{
		return EVAL_PAGE;
	}
	public int doStartTag() throws JspException 
	{
		JspWriter jw = pageContext.getOut();

		try 
		{
			if (this.getMode().equals("12") ) 
			{
				if (this.getCrNo().length() > 1) 
					jw.print(this.getOnlineRequestDtls());
			} 
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return EVAL_BODY_INCLUDE;
	}

	public Tag getParent() 
	{
		return null;
	}
	public void release() 
	{
	}

	public void setPageContext(PageContext pageContext) 
	{
		this.pageContext = pageContext;
	}

	public void setParent(Tag tag) 
	{
	}

	public WebRowSet getOnLineRequestWs() throws Exception 
	{

		WebRowSet ws = null;
		BillingBO boObj = new BillingBO();
		BillingVO voObj = new BillingVO();
		voObj.setStrValue1(this.getCrNo());
		voObj.setStrValue3(pageContext.getSession().getAttribute("HOSPITAL_CODE").toString());
		voObj.setStrValue4(this.getDeptCode());
		try
		{
			boObj.getOnLineReqCreditBillApproval(voObj, this.getMode());
			
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
			else
			{
				ws = voObj.getGblWs2();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			new HisException("Billing","CreditBillOnLineReqDtlTag.getOnLineRequestWs() -->", e.getMessage());
		}		
		return ws;
	}

	public String getOnlineRequestDtls() 
	{
		String temp[] = new String[25];
		StringBuffer sb = new StringBuffer();
		WebRowSet wb = null;
		
		try 
		{
			wb = getOnLineRequestWs();
				if (wb != null && wb.size() > 0) 
				{
					try 
					{
						sb.append("<table class='TABLEWIDTH' align='center' border='0'> ");
						sb.append("<input type='hidden' name='tmpMsg' value="+tmp+">");
						sb.append("<tr><td colspan='6' class='TITLE'>Online Credit Bill Request</td></tr>");
						sb.append("<tr><td width='5%' class='multiLabel'></td>");
						sb.append("<td width='16%' class='multiLabel'>Req No</td>");
						sb.append("<td width='16%' class='multiLabel'>Req Date</td>");
						sb.append("<td width='16%' class='multiLabel'>Dept/Ward</td>");
						sb.append("<td width='16%' class='multiLabel'>Hospital Service</td>");
						sb.append("<td width='16%' class='multiLabel'>Request Type</td></tr>");
					
						for (int i = 0; wb.next(); i++) 
						{
							// Appended Value is   REQ_NO +"@"+ SBLNUM_BSERVICE_ID +"@"+HBLNUM_PATACCOUNT_NO+"@"+Hidden Value;
							String strChk = wb.getString(1) + "@" + wb.getString(5) + "@" + wb.getString(13)+"@"+wb.getString(20);
							String strTemp[] = strChk.replace('@', '#').split("#");
							String strReqNo = wb.getString(1);
							String strReqDate = wb.getString(2);
							String strDeptName = wb.getString(17);
							String strHospitalService = wb.getString(15);
							String strBId = wb.getString(5);
							String strReqType = wb.getString(16);
							String strHidVal = wb.getString(20);
							temp = strHidVal.replace("^", "#").split("#");
							String Temp[] = wb.getString(20).replace('^', '#').split("#");
							String strName = strDeptName+"/"+Temp[17];
					
							if (strReqNo == null)
								strReqNo = "";
							if (strReqDate == null)
								strReqDate = "";
							if (strHospitalService == null)
								strHospitalService = "";
							if (strReqType == null)
								strReqType = "";
							if (strName == null)
								strName = "";
							
							sb.append("<input type='hidden' name='strName'  value='"+strName+"'>");
							sb.append("<input type='hidden' name='strBId'  value='"+wb.getString(5)+"'>");
							sb.append("<input type='hidden' name='strReqNo' value="+wb.getString(1)+">");
							sb.append("<input type='hidden' name='strChk' value="+strChk+">");
							sb.append("<input type='hidden' name='strHospService' value="+wb.getString(4)+">");
							sb.append("<input type='hidden' name='strReqType' value="+wb.getString(19)+">");
							sb.append("<tr><td width='5%' class='multiLabel'><input type='radio' style='cursor:pointer;cursor:hand' name='strChk_values' id='strChk_values' value=\""+strChk+"\"  onClick='groupCheck(this,"+i+");'");
							if (i == 0) 
							{
								sb.append("checked></td>");
							} 
							else 
							{
								sb.append("</td>");
							}
							sb.append("<td width='16%' class='multiControl'>");
							sb.append(strReqNo);
							sb.append("</td>");
							sb.append("<td width='16%' class='multiControl'>");
							sb.append(strReqDate);
							sb.append("</td>");
							sb.append("<td width='16%' class='multiControl'>");
							sb.append(strName);
							sb.append("</td>");
							sb.append("<td width='16%' class='multiControl'>");
							sb.append(strHospitalService);
							sb.append("</td>");
							sb.append("<td width='16%' class='multiControl'>");
							sb.append(strReqType);
							sb.append("</td></tr>");
						}
						sb.append(" </table> ");
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
						new HisException("Billing","CreditBillOnLineReqDtlTag.doStartTag() -->", e.getMessage());
					}
				}else
				{
					sb.append("<table class='TABLEWIDTH' align='center' border='0'> ");
					sb.append("<tr><td colspan='6' class='TITLE'>Online Credit Bill Request</td></tr>");
					sb.append("<tr><td colspan='6' width='5%' class='multiControl'><font style='color:red'><b>No Credit Billing Request Available</b></font></td>");
					sb.append("</td></tr></table>");					
				}		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			new HisException("Billing","CreditBillOnLineReqDtlTag.doStartTag() -->", e.getMessage());
		}
		finally
		{
			if (wb != null)
			{
				try 
				{
					wb.close();
				}
				catch(Exception e) 
				{
				}
				wb = null;
			}
		}		
		return sb.toString();
	}
	public String getMode() 
	{
		return mode;
	}
	public void setMode(String mode) 
	{
		this.mode = mode;
	}
}