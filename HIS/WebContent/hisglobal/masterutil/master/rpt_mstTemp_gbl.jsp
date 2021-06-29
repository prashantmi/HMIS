
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% 
String temp="";
if(request.getParameter("masterName")!=null)
temp=(String)request.getParameter("masterName");
else
	temp="Master";
String masterName=temp.split("#")[0];
%>
<title><%=masterName%> Report Page</title>
</head>    
<%    
String p[]=request.getParameterValues("combo");
String comboString="";
String cnt_page=request.getParameter("cnt_page"); 
if(p !=null)
{
for(int i=0;i<p.length;i++){
	comboString +="<input type='hidden' name='combo' value='"+p[i]+"'>	";
	} 
}
String isGlobal="";
String hospitalName="";
String hospitalAdd="";
String hospitalAddExt="";
String hospitalCity="";
String hospitalState="";

if(request.getParameter("isGlobal")!=null)
	isGlobal=(String)request.getParameter("isGlobal");
else
	isGlobal="0";
if(!isGlobal.equals("1"))
{
	HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
	hospitalName=hospitalVO.getHospitalName();
	hospitalAdd=hospitalVO.getAddress1();
	hospitalAddExt=hospitalVO.getAddress2();
	hospitalCity=hospitalVO.getCity();
	hospitalState=hospitalVO.getState();
}

%>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../hisglobal/js/validation.js"></script>
<link href="../hisglobal/masterutil/css/master.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../hisglobal/css/layout.css" rel="stylesheet"
	type="text/css">
<%@ taglib uri="/struts-tags" prefix="s"%>
<body onLoad="fetchReportsData('<%=request.getSession().getAttribute("cnt_page") %>');">
<s:form>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="69">
  <tr>
	<td>
		<div id="loadId" align="right" style="display: none"><b><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="RED">
		Loading...			
		</font></b></div>
	</td>
	<td>
		<div id="id1" class="hidecontrol" align="right">
			<img src='../hisglobal/images/printer_tab.jpg' title='Click here to Print the Report' style="cursor:pointer" id="printId"  tabindex=1 style="cursor:pointer;" onClick='printData();' onKeyPress='printData();'>
			<img src='../hisglobal/images/back_button.gif' title='Click here to Go Back' style="cursor:pointer"  tabindex=1 style="cursor:pointer;" onClick="submitCancelAction('<%=request.getSession().getAttribute("cnt_page") %>');" onKeyPress="submitCancelAction('<%=request.getSession().getAttribute("cnt_page") %>');">			
		</div>
	</td>
  </tr>
 </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="69">  
  <tr> 
    <td width="100%"><div align="center"><b><font face="Verdana, Arial, Helvetica, sans-serif" size="4">
		<%=hospitalName %></font></b></div></td>
  </tr> 
  <tr> 
    <td width="100%"><div align="center"><b><font face="Verdana, Arial, Helvetica, sans-serif" size="4">
		</font></b></div></td>
  </tr>
  <tr>
    <td width="100%"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="2">
		<%=hospitalAdd %></font></div></td>
  </tr>
   <tr>
    <td width="100%"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="2">
		<%=hospitalAddExt %></font></div></td>
  </tr>
   <tr>
    <td width="100%"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="2">
		<%=hospitalCity %></font></div></td>
  </tr>
  <tr>
  <td width="100%"><div align="center"><font face="Verdana, Arial, Helvetica, sans-serif" size="2">
		<%=hospitalState %></font></div></td>
  </tr>
</table>

<TABLE ALIGN='CENTER' WIDTH='100%' ><TR> <td align='center' ><b><font face="Verdana, Arial, Helvetica, sans-serif" size="2">Reports For&nbsp;<%=masterName%></font></b></td></tr></table>
<TABLE width='98%' ALIGN="center"  CELLPADDING="0" CELLSPACING="0" BORDER="0">
			<TR>
				<TD ALIGN="center">
					<TABLE width='98%'>
						<TR>
							<TD>
								<DIV ID="reportdata" ALIGN="top"></DIV>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
	</TABLE>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="69">
  <tr>
	<td>
		<div id="id2" class="hidecontrol" align="right">
			<img src='../hisglobal/images/printer_tab.jpg' title='Click here to Print the Report' style="cursor:pointer" id="printId"  tabindex=1 style="cursor:pointer;" onClick='printData();' onKeyPress='printData();'>
			<img src='../hisglobal/images/back_button.gif' title='Click here to Go Back' style="cursor:pointer"  tabindex=1 style="cursor:pointer;" onClick="submitCancelAction('<%=request.getSession().getAttribute("cnt_page") %>');" onKeyPress="submitCancelAction('<%=request.getSession().getAttribute("cnt_page") %>');">			
		</div>
	</td>
  </tr>
 </table>
<input type="hidden" name="hmode">
<input type="hidden" name="search" 			value ="<%=request.getParameter("search")%>">
<input type='hidden' name='searchColumn' 	value="<%=request.getParameter("searchColumn")%>">
<input type='hidden' name='prevNext' 		value='<%=request.getParameter("prevNext")%>'>
<input type='hidden' name='minrow' 			value='<%=request.getParameter("minrow")%>'>
<input type='hidden' name='blockNo' 		value='<%=request.getParameter("blockNo")%>'> 
<input type='hidden' name='comboValue' 		value='<%=request.getParameter("comboValue")%>'> 
<input type='hidden' name='no_of_combo'		value='<%=request.getParameter("no_of_combo")%>'> 
<input type='hidden' name='cnt_page'	value='<%=request.getParameter("cnt_page")%>'> 
<input type='hidden' name='module_id'	value='<%=request.getParameter("module_id")%>'> 
<input type="hidden" name="strReportConfig">
<input type='hidden' name='orderByName' id='orderById' value='<%=request.getParameter("orderByName")%>'>
<%=comboString%>
</s:form>
</body>
</html>