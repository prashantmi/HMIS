<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@ page import="java.util.*" %>
<%
List lst = (List)request.getAttribute("errorDetails");
String fromDate = (String)request.getAttribute("fromDate");
String toDate = (String)request.getAttribute("toDate");
String sysdate = (String)request.getAttribute("sysdate");

if(fromDate==null || fromDate.equals(""))
	fromDate = sysdate;
if(toDate==null || toDate.equals(""))
	toDate = sysdate;


%>




<HTML>
	<HEAD>
		<TITLE>Error Log Maintainance</TITLE>
		
		<link href="../../css/Color.css" 	 rel="stylesheet" type="text/css">
		<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		<link href="../../css/master.css" 	 rel="stylesheet" type="text/css">
		<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
		
		<script language="JavaScript" src="../js/calendar.js"></script>
		<script language="JavaScript" src="../js/investigation.js"></script>
		<script language="JavaScript" src="../js/popupWindow.js"></script>
		<script language="JavaScript" src="../js/functionality.js"></script>
		<script language="javascript">
		
		function ravi(){
	
		var a=document.errorLogForm.errorLogPath.value;
		if(a){
		document.forms[0].hmode.value="SAME";
			document.forms[0].submit();
		
		}else{
		alert("there is no value in combo");
		}
		}		
		
		function openReport(path)
		{	
			var url = "errorPopup.jsp";
			//alert(url);
			//alert(path);
			url += "?fullpath="+path;		
			child = window.open(url,'popupWindow',"status=yes,scrollbars=yes,height=450,width=780,left=10,top=10");			
		}
		
		function manik(){
	
		var mod_name=document.getElementsByName("errorLogPath")[0].value;
		//ajaxFunction(url,"1");
	}
		
		
		</script>
		
	</HEAD>
	
	<BODY background = ""../../images/cdac1.psd.gif>	
		<html:form action="/usermgmt/reports/errorLogController" name="errorLogForm" type="usermgmt.reports.errorLogActionForm" method="POST">

		
		<bean:define id="hmode" name="errorLogForm" property="hmode" type="java.lang.String"/>
		<bean:define id="hello" name="errorLogForm" property="errorLogPath" type="java.lang.String"/>
		<table width="100%" align="center">		
		<tr>
		<td width="100%" class="header" colspan="5"></td>
		</tr>
		<tr>
		<td width="100%" class="addheader" colspan="5"><font size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><font color='red'>*</font>Module Name</font>
					
		<html:select property="errorLogPath" styleId="errorLogForm" value="<%=hello%>">
		
		<html:options collection="deptComboList" property="optionValue" labelProperty="optionText"/>
		</html:select>
		
		<img src="../../images/go.gif" onClick="ravi(),manik();">
		
		</td>
		</tr>
		
	<%-- 	<tr>
		<td width="50%" class="addheader" colspan="3">From Date <otTags:date name='fromDate' value='<%=fromDate%>'/>
		</td>
		<td width="50%" class="addheader" colspan="2" nowrap>To Date <otTags:date name='toDate' value='<%=toDate%>'/>
		<img src="../../images/go.gif" onClick="submitPage(),ravi();" >
		</td>
		
		</tr>--%>
		
		<tr>
		<td width="5%" class="tdfonthead" colspan="1"><b>S.No<b></td>
		<td width="20%" class="tdfonthead" colspan="1"><b>File Name</b></td>
		<td width="20%" class="tdfonthead" colspan="1"><b>Date</b></td>		
		<td width="100%" class="tdfonthead" colspan="1"><b>View</b></td>
		</tr>
		<logic:equal name="hmode" value="SAME">
		<%
		int sNo = 1;

		if(lst!=null && lst.size()!=0)
		{
				for(int i=0;i<lst.size();i=i+3)
				{
					String fullPath = (String)lst.get(i+1);
					fullPath = fullPath.replace('\\','/');
					System.out.println("fullPath is "+fullPath);
			//ArrayList as=(ArrayList)lst.get(0);
				
				
				%>
				<tr>
			<td width="5%" class="tdfont" colspan="1"><%=sNo++%>.</td>
			<td width="20%" class="tdfont" colspan="1"><%=lst.get(i).toString()%></td>
			<td width="20%" class="tdfont" colspan="1"><%=lst.get(i+2)%></td>
			<td width="20%" class="tdfont" colspan="1">
						<a style="cursor:hand" >
						<img src="../../images/report_tab.gif" onclick="openReport('<%=fullPath%>')">
						</a>
						</td>	
				 </tr>
				
				 
				<%
				}
		}
		else
		{
			%>
				
						<tr>
						<td width="100%" class="tdfont" colspan="5" ><center><font color="red"><b>No Record(s) Found</b></font></center></td>
						
						</tr>
				<%
			
			
		}
		%>
		</logic:equal>
		
		<tr>
		<td width="100%" class="header" colspan="5"></td>
		</tr>
		
					
		<html:hidden property="hmode"/>
	
		</html:form>
	</BODY>
</HTML>
