<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_SampleCollectionVO"%>
<%@page import="new_investigation.transactions.controller.fb.testAvailabilityFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="new_investigation.vo.testAvailabilityVO"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>

<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />


<html>

<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<style type="text/css">
@media print { #noPrint { display: none; }} 


.underline {
    text-decoration: underline;
}
 
 
.linebottom {
    border-top-width: thin;
    border-right-width: thin;
    border-bottom-width: thin;
    border-left-width: thin;
    border-top-style: solid;
    border-right-style: none;
    border-bottom-style: none;
    border-left-style: none;
    border-top-color: #330000;
    border-right-color: #330000;
    border-bottom-color: #330000;
    border-left-color: #330000;
}



</style>
<script>
function openHosDetails(hospitalCode,event,reportFlag)
{
	var reportMode="HOSDETAILS";
	var fromDate=document.getElementsByName("fromDate")[0].value;
	var toDate=document.getElementsByName("toDate")[0].value;
	//alert(fromDate+"-"+toDate);
	var url="/BLDHISInvestigationG5/bloodbank/donorStatisticsReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&fromDate="+fromDate+"&toDate="+toDate+"&reportFlag="+reportFlag;
	openPopup(url,event,700,700);
	
}
function openPopup(url,height, width)
{
 
  //ert("url "+url)
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 

if(!child.opener)
   child.opener = self;
 
 return child
}


function submitPage(mode)
{
		document.getElementsByName("reportMode")[0].value=mode;
		document.forms[0].submit();	
}
function loadData(e)
{
	//alert(document.getElementsByName('hmode')[0].value);
	if(document.getElementsByName('hmode')[0].value=="PRINTDAILYBLOODUNITSTOCKFORM")
	{
		var url="/BLDHISInvestigationG5/hisglobal/utility/generictemplate/printingTile.cnt";
		openPopup(url,e,400,600);
		return;
	}
	
}
function printPDFPage(e)
{ 
	document.getElementsByName('htmlCode')[0].value=document.getElementById('pdfPrintingHTMLData').innerHTML;
	//submitPage('PRINTUTILITY');
	var url="/BLDHISInvestigationG5/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=TRY";
	openPopup(url,e,700,700);
	
}

function cancelFunc()
{
	window.parent.closeTab();
}
 
</script>


</head>
<html:form action="/testAvailability">
	<% 
	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	HospitalMstVO hospVo=(HospitalMstVO)ControllerUTIL.getHospitalVO(request);
	 List<testAvailabilityVO> lstPatVO=(List<testAvailabilityVO>)session.getAttribute(InvestigationConfig.TEST_AVAILABILITY_DETAILS);
	String strHospitalName="",strHospitalContact="",strHospitalAddress="",strFooter1="",strFooter2="";
	if(lstPatVO!=null && lstPatVO.size()>0)
	{
	strHospitalName= hospVo.getHospitalName();
	strHospitalAddress= hospVo.getAddress1()+" "+hospVo.getAddress2();
	strHospitalContact=hospVo.getPhone();
	strFooter1= hospVo.getEmail();
	strFooter2= hospVo.getFax();
	//int size=lstBldRptVO.size();
	//int count=1;
	%>
			
	<div id="noPrint"  align="right">
		<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="window.print();" onkeypress="if(event.keyCode==13) window.print();">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
	</div>	
				
<his:ContentTag>
<div id="pdfPrintingHTMLData">			
			<table width="100%" align="center" cellspacing="1" cellpadding="0">
					<tr>
						<td valign="middle" width="50%">
							<div align="left">
								<b><font color="#000000" size="2" face="Verdana" class="">
									<%-- <bean:message key="nims"/> --%>
								</font></b>	
							</div>
						</td>
						<td valign="middle" width="50%">
							<div align="right">
								<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="reportDate"/> &nbsp;<%=sysDate%>
								</font></b>
							</div>
						</td>
					</tr>
					</table>
					<br>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#000000" size="3"><%=strHospitalName%></font>
							<br><font color="#000000" size="2"><%=strHospitalAddress%></font>
							<br><font color="#000000" size="2"><%=strHospitalContact%></font>
							</b>
						</font>
					</div>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><u>
								Test Availability Report
							</u></b>
						
							<br><%-- <b><u>
								(<bean:write name="testAvailabilityFB" property="fromDate"/>-
								<bean:write name="testAvailabilityFB" property="toDate"/>)
							</u></b> --%>
						</font>
					</div><br>
		<br>
		
		
					<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
					
					</td>
					<td width="22%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/> </font></b>
					</td>
					<td width="25%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="testAvailability"/> </font></b>
					</td>
						<td width="25%" align="left"   >
						
					</td>
						<td width="25%" align="left"   >
					
					</td>

				</tr>
			</table>
			
						<table   width="100%"  cellspacing="0" style="border-spacing: 0;">
			
			
		<% 
		int size=0;
		if(lstPatVO!=null && lstPatVO.size()>0 )
 			size=lstPatVO.size();
		
			for(int j=0;j<size;j++)
		{
			
			
		
						
			testAvailabilityVO voPat=lstPatVO.get(j);
			String  chkVal=voPat.getLabCode()+"#"+voPat.getTestCode();
            
		 	%>
			<tr>
						<td width="3%" align="left"  >
						
						</td>
						
							<td width="22%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getTestName()%></font> 
						 
				  		</td>
				  		
				  		
				  				<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getAvailableValue()%></font> 
						 
				  		</td>
				  	
				  	
				  	
				  	
				  	
				  	<%if(voPat.getIsAvailable().equals("1"))
				  		{%>
						<td width="25%" align="left"   >
						
					</td>
						<td width="25%" align="left"   >
					
					</td>
				  		
					<%}
				  	else
				  	{%>
				  	
				  	
				  		<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	From: <%=voPat.getFromDate()%></font> 
						 
				  		</td>
				  	
				  	
				  		<td width="25%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	To: <%=voPat.getToDate()%></font> 
						 
				  		</td>
				  	
				  	
				  	
				  	
				  	
				  	<%} %>
					</tr>
				
				
				<%} %>
				
				</table>
				
		<!-- FOOTER START -->	
	    <table width="100%" align="center" cellspacing="1" cellpadding="0">    				
				<tr>
				
					<td valign="middle" width="30%">
						<div align="right">
							<br><br><br><b><font color="#000000" size="2" face="Verdana"><bean:message key="PageNo-1"/> </font></b>
						</div>								
					</td>
				</tr>							
		</table>
		<!-- FOOTER CLOSED -->
</div>
		
</his:ContentTag>
<%} 
else{%>
<div align="left">
 <h1> Sorry, No details found! </h1>
</div>
<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
<%} %>
<html:hidden name="testAvailabilityFB" property="hmode"/> 
</html:form>
</html>
