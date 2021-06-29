<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page language="java" import="hisglobal.masterxml.masterworkshop.tools.*,java.util.*,hisglobal.hisconfig.Config"%>
<%
	try
	{
%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<html>
	<head>
		<title>Welcome to runApp.jsp</title>
<% 
  	MasterTO objMasterTo = (MasterTO)session.getAttribute("TOLstObject");   	
%>

		<his:css src="/hisglobal/css/masterXml.css" />
		<his:css src="/hisglobal/css/Color.css" />

		<script Language=javascript>
/*function printPage()
{
	var frameElement = parent.document.getElementById("f2");
	document.getElementById("noPrint").style.display="none";
	
	var win = frameElement.contentWindow;
	
	
	//alert("Before Print");
	win.print();
	//alert("Print Called");
	document.getElementById("noPrint").style.display="block";
}

function submitpage(mode)
{
    document.mstHandlerListForm.hmode.value=mode;
    document.mstHandlerListForm.submit();
}*/
		</script>
	</head>
	<body>	
<%
    ArrayList alcol=new ArrayList(((MasterListTO)objMasterTo).getAlColHeading()); 
    alcol.add(0,"Sl.No.");
%>

<html:form action="/masterWorkshop/mstHandlerListAction" method="post">
	<table cellpadding="0" cellspacing="1" width="100%">
		<thead style='DISPLAY: TABLE-HEADER-GROUP'>
			<%-- <tr>
				<td>
					<div align="right" id="noPrint">
						<a>
							<img src='<his:path src="/hisglobal/images/btn-pnt.png"/>' onKeyPress="if(window.event.keyCode==13)printPage();" onClick='printPage();' tabindex="1">
						</a>
						<a>
							<img src='<his:path src="/hisglobal/images/btn-ccl.png"/>' onKeyPress="if(window.event.keyCode==13)submitpage('CANCEL');" onClick='submitpage("CANCEL");' tabindex="1">
						</a>
					</div>
				</td>
			</tr>
			--%>
			<tr>
				<td colspan='alcol.size()'>
					<div align="center">
						<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>
							<b>
							<%
								String masterTitle=((MasterListTO)objMasterTo).getTitle();
								int ind= masterTitle.lastIndexOf("M");
								if(ind==-1)
								{
									ind=masterTitle.lastIndexOf("m");
								}
								String name=masterTitle.substring(0,ind);
							%>
							Report for <%=name%> Master
							</b>
						</font>
					</div>
				</td>
			</tr>
			<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");%>
			<tr>
				<td colspan='alcol.size()'>
					<div align="right">
						<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>
							<b>Report Date::</b> <%=sysdate%>
						</font>
					</div>
				</td>
			</tr>
			<bean:define id="lstBean" name="mstHandlerListForm" property="isActive" type="java.lang.String" />
			<%
				if(((MasterListTO)objMasterTo).getAlData().size()==0)
				{
			%>
			<tr>
				<td>
					<b><font color="#FF0000">No Records to display report</font></b>
				</td>
			</tr>
			<%
				}
				else
				{
			%>
			<tr>
			
			
				<%
					String status=request.getParameter("isActive");
					if(status.equals("1"))
					{
				%>
				<td colspan='alcol.size()'>
					<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>Record Status::<b>ACTIVE</b></font>
				</td>
				<%
					}
					else
					{
				%>
				<td colspan='alcol.size()'>
					<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>Record Status::<b>INACTIVE</b></font>
				</td>
				<%
					}
				%>
			</tr>
		</thead>
	</table>
	<table width="100%">
		<tr>
		<%
					System.out.println("alcol.size()"+alcol.size());
					for (int i=0;i<alcol.size();i++)
					{
		%>
			<td colspan='alcol.size()'>
				<font size='2' face='Verdana, Arial, Helvetica, sans-serif'><b><%=alcol.get(i)%></b></font>
			</td>
		<%
					}
		%>
		</tr>
		<%
					int sno=1;
					for (int i=0;i<((MasterListTO)objMasterTo).getAlData().size();)
					{
		%>
		<tr>
			<td><%=sno%></td>
			<%
						for(int j=0;j<((MasterListTO)objMasterTo).getNoOfColumn();j++)
						{
			%>
			<td>
				<%=(String)((MasterListTO)objMasterTo).getAlData().get(++i)%>
			</td>
			<%
						}
			%>
		</tr>
		<%
						sno++;
						i++;
					}
				}
		%>
	</table>
	<input type="hidden" name="hmode" value="LIST">
</html:form>

	</body>
</html>
<%
    }catch(Exception e){System.out.println("Exception in mstHandle..."+e);
    e.printStackTrace();}
%>