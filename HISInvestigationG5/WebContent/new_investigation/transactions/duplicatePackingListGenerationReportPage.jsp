
<%@page import="new_investigation.vo.SampleAcceptanceVO"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_SampleCollectionVO"%>
<%@page import="new_investigation.transactions.controller.fb.PackingListGenerationFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>


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

<style type="text/css">
   <!--
   @page {margin-top: 125 ;margin-bottom: 20}
   -->
</style>

<script>

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


function getDetails()
{
	
		document.getElementById('goButton').style.display="";
		
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
		
		document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	
	
	
	//var sampleAreaCode=document.getElementByName("sampleAreaCode")[0].value;
}

 
</script>


</head>
<html:form action="/packingListGeneration">
<body>

	<% 
	
	
	String collname="";
	
	if(session.getAttribute("collareaname")!=null)
	collname=(String)session.getAttribute("collareaname");

	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	HospitalMstVO hospVo=(HospitalMstVO)ControllerUTIL.getHospitalVO(request);
	Map<String,List<SampleAcceptanceVO>> mpPackingList = (Map<String,List<SampleAcceptanceVO>>) session.getAttribute(InvestigationConfig.MAP_PACKING_LIST_SAVE);
	String strHospitalName="",strHospitalContact="",strHospitalAddress="",strFooter1="",strFooter2="";
	if(mpPackingList!=null && mpPackingList.size()>0)
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
				
<%-- <his:ContentTag> --%>
<div id="pdfPrintingHTMLData">			
			<table width="100%" align="center" cellspacing="1" cellpadding="0">
					<tr>
						<td valign="middle" width="50%">
							<div align="left">
								<b><font color="#000000" size="2" face="Verdana" class="">
										
								</font></b>	
							</div>
						</td>
						<td valign="middle" width="50%">
							<%-- <div align="right">
								<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="reportDate"/>&nbsp; <%=sysDate%>
								</font></b>
							</div> --%>
						</td>
					</tr>
					</table>
					<br>
					
					<!-- commented by ashu -->
					
					<%-- <div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#000000" size="3"><%=strHospitalName%></font>
							<br><font color="#000000" size="2"><%=strHospitalAddress%></font>
							<br><font color="#000000" size="2"><%=strHospitalContact%></font>
							</b>
						</font>
					</div> --%>
					
					
					<!-- <br><br> -->
					<!-- <div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<br><font color="#000000" size="3"></font>
							<br><font color="#000000" size="2"></font>
							<br><font color="#000000" size="2"></font>
							<br>
							</b>
						</font>
					</div> -->
					
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><u>
								<bean:message key="packListGenReportDuplicate"/>
							</u></b>
						
							<br><b><u>
								(<bean:write name="PackingListGenerationFB" property="fromDate"/>-
								<bean:write name="PackingListGenerationFB" property="toDate"/>)
							</u></b>
						</font>
					</div><br>
						<% if(collname!=null && !collname.equals("")) {%>
					<div align="right" style="float:right;">
								<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Collection Area &nbsp;&nbsp;&nbsp;&nbsp;<%=collname%>
								</font></b>
							</div>
							<%} %>
							
					<div align="right"  style="float:left;">
								<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="reportDate"/>&nbsp; <%=sysDate%>
								</font></b>
							</div>
		<br>
		<% 
		Iterator itr=mpPackingList.keySet().iterator();
		//int pNo = 0;
		while(itr.hasNext())
		{
			
		 	String packingListNo=(String)itr.next();
		 	
		  if(packingListNo.equals("100031907121324"))
		  {
			  System.out.println("asdsadf");
		  }
		 	List<SampleAcceptanceVO> lstSampleVO=(List<SampleAcceptanceVO>)mpPackingList.get(packingListNo);
		 	
		 	 List<String> lstsampleNo=new ArrayList<String>();
		 	 List<String> mainlstsampleNo=new ArrayList<String>();

		 	 List<SampleAcceptanceVO> sampleNoBasedPackList= new ArrayList<SampleAcceptanceVO>();
		 	 List<SampleAcceptanceVO> traversedVO= new ArrayList<SampleAcceptanceVO>();

		 	 
		 	 //logic to group by sample number
		 	 for(SampleAcceptanceVO mainVO : lstSampleVO )
		 	 {
		 		 
		 		 if(traversedVO.contains(mainVO))
		 			 ;
		 		 else
		 		 {
		 		 
		 		 String sampleno=mainVO.getTempSampleNo()+mainVO.getSampleNo()+mainVO.getPatCRNo();
		 		 
		 		 
		 	 for(SampleAcceptanceVO tempVO : lstSampleVO )
		 	 {
		 		
		 		if(tempVO.getTempSampleNo().equals("0281"))
		 		{
		 			System.out.println("sdf");
		 		}
		 			
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		 if(lstsampleNo.contains(tempVO.getTempSampleNo()+tempVO.getSampleNo()+tempVO.getPatCRNo()) && sampleno.equals(tempVO.getTempSampleNo()+tempVO.getSampleNo()+tempVO.getPatCRNo()))
		 		 {		 			
		 			 if(tempVO.getPatCRNo().equals("109111900938051"))
		 			 {
		 				System.out.println("sdf");
		 			 }
		 			 
		 			 mainVO.setTestName(mainVO.getTestName());
		 			 if(mainVO.getReportalltest()==null)
		 			 mainVO.setReportalltest(tempVO.getTestName());
		 			 else
		 				mainVO.setReportalltest(mainVO.getReportalltest()+","+tempVO.getTestName());
		 			 
		 			traversedVO.add(tempVO);
		 		 }
		 		 else if(sampleno.equals(tempVO.getTempSampleNo()+tempVO.getSampleNo()+tempVO.getPatCRNo()))
		 			 lstsampleNo.add(sampleno);
		 		 else
		 			 ;
		 		 
		 		 
		 	 }
		 	 
		 	 
		 	if(mainlstsampleNo.contains(mainVO.getTempSampleNo()+mainVO.getSampleNo()+mainVO.getPatCRNo()))
		 		;
		 	else
		 	{
		 		mainlstsampleNo.add(mainVO.getTempSampleNo()+mainVO.getSampleNo()+mainVO.getPatCRNo());
		 		sampleNoBasedPackList.add(mainVO);
		 	}
		 		 }
		 	}
		 	 
		 	 
		 	String labName=sampleNoBasedPackList.get(0).getLabName(); 
		 	%>
		<table width="100%" align="center" cellspacing="0" cellpadding="1" border="1">
			 	<tr valign="top">
					<td width="15%" valign="top" bgcolor="lightgrey">
							<div align="center"><font color="#000000" size="2" face="Arial">
								<bean:message key="labName"/>&nbsp;</font>
							</div>
					</td>	
					<td width="15%" valign="top" class="underline">
							<div align="center"><font color="#000000" size="2" face="Arial">
								<b><%=labName%></b></font>
							</div>
					</td>		
					<td width="15%" valign="top" bgcolor="lightgrey">
							<div align="center"><font color="#000000" size="2" face="Arial">
								<bean:message key="packListNo"/></font>
							</div>
					</td>	
					<td width="15%" valign="top" class="underline">
							<div align="center"><font color="#000000" size="2" face="Arial">
								<b><%=packingListNo%></b></font>
							</div>
					</td>	
					<td width="15%" valign="top" bgcolor="lightgrey">
							<div align="center"><font color="#000000" size="2" face="Arial">
								<bean:message key="GenerationDateTime"/></font>
							</div>
					</td>	
					<td width="25%" valign="top" class="underline">
							<div align="center"><font color="#000000" size="2" face="Arial">
								<b><%=sampleNoBasedPackList.get(0).getPackingListDate()%></b></font>
							</div>
					</td>	
				</tr>			
		</table>
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td width="5%" valign="top" >
							<div align=left><font color="#000000" size="2" face="Arial">
								<font color="#000000" size="2" face="Arial"><b><b><bean:message key="S.No"/></b></font>
							</div>
					</td>	
					<td width="13%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="crNO"/>&nbsp;</b></font>
							</div>
					</td>		
					<td width="11%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="patientName"/>&nbsp;</b></font>
							</div>
					</td>
					
					<td width="10%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="age/gender"/>&nbsp;</b></font>
							</div>
					</td>
					
					<td width="14%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="wardName"/>&nbsp;</b></font>
							</div>
					</td>
					
					<td width="14%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="TestName"/>&nbsp;</b></font>
							</div>
					</td>	
					<td width="14%" valign="top"  >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="sampleNo"/>&nbsp;</b></font>
							</div>
					</td>		
					<td width="19%" valign="top">
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><b><bean:message key="sampleCollDateTime"/></b></font>
							</div>
					</td>		
				</tr>			
		 <% int count=1;
		
		 
		 	for(SampleAcceptanceVO voSample:sampleNoBasedPackList)
		 	{
		 		/* pNo++;
		 		if(pNo == 12){
					pNo = 0;
				} */
				
			%>
				<tr valign="top">
					<td width="5%" valign="top">
							<div align="left"><font color="#000000" size="2" face="Arial">
								<font color="#000000" size="2" face="Arial"><%=count++%></font>
							</div>
					</td>	
					<td width="13%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getPatCRNo()%></font>
							</div>
					</td>		
					<td width="11%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getPatName()%></font>
							</div>
					</td>		
					
					
					<td width="10%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getPatAge()+"/"+ voSample.getPatGender() %></font>
							</div>
					</td>
					
					<%
					
					if(  voSample.getPatAdmittedStatus()!=null  && voSample.getPatAdmittedStatus().equals("12")) // for pat admitted status check
					{
					
					%>
					
					<td width="14%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getPatWardName()%></font>
							</div>
					</td>
					
					
					<%}else{%>
					<td width="14%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= "-"%></font>
							</div>
					</td>
					
					
					<% } %>
					
					
					
					<td width="14%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getTestName()%></font>
							</div>
					</td>	
					<td width="14%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getTempSampleNo()%></font>
							</div>
					</td>		
					<td width="19%" valign="top" >
							<div align="left"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getCollDate()%></font>
							</div>
					</td>			
				</tr>	
				<tr height="0px"></tr>	
				<%if(voSample.getReportalltest()!=null){ %>
				<tr>
				<td width="5%" valign="top" ></td>
				<td width="13%"  >
							<div align="left" style="margin-left:-40px;"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Test Continue&nbsp;</b></font>
							</div>
					</td>	
					<td colspan="6"  width="82%" valign="top" >
							<div align="left" ><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getReportalltest()%></font>
							</div>
					</td>
				</tr>
				<%}else{} %>
				
					<tr height="0px"></tr>	
				<%if(voSample.getDiagnosis()!=null && (voSample.getHospitalcode().equals("96101") || voSample.getHospitalcode().equals("21917"))){ %>
				<tr>
				<td width="5%" valign="top" ></td>
				<td width="13%"  >
							<div align="left" style="margin-left:-40px;"><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Diagnosis&nbsp;</b></font>
							</div>
					</td>	
					<td colspan="6"  width="82%" valign="top" >
							<div align="left" ><font color="#000000" size="2" face="Arial" >
							<font color="#000000" size="2" face="Arial"><%= voSample.getDiagnosis()%></font>
							</div>
					</td>
				</tr>
				<%}else{} %>
	
				
				<tr valign="top">
				<td colspan="8" class="linebottom">
				</tr>
				<%} %>
				
					
		</table>
		<br>
		
		 <%--  <%if(pNo == 11) // for page break -- ashu
			{ %>
					
					<p style="page-break-before: always">
					<br><br><br><br><br><br>
					<!-- <div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<br><font color="#000000" size="3"></font>
							<br><font color="#000000" size="2"></font>
							<br><font color="#000000" size="2"></font>
							<br>
							</b>
						</font>
					</div> -->
					
			<%}%> --%>
		
		
		<%} %>
		<!-- FOOTER START -->	
	    <table width="100%" align="center" cellspacing="1" cellpadding="0">    				
				<tr>
					<%-- <td valign="middle" width="30%">
						<div align="left">
							<br><br><br><b><font color="#000000" size="2" face="Verdana"><%=strFooter1 %></font></b>
						</div>
					</td>
					<td valign="middle" width="40%">
						<div align="center">
							<br><br><br><b><font color="#000000" size="2" face="Verdana"><%=strFooter2 %></font></b>
						</div>
					</td> --%>
					<%-- <td valign="middle" width="30%">
						<div align="right">
							<br><br><br><b><font color="#000000" size="2" face="Verdana"><bean:message key="PageNo-1"/></font></b>
						</div>								
					</td> --%>
				</tr>							
		</table>
		
		 
		<!-- FOOTER CLOSED -->
</div>
		
<%-- </his:ContentTag> --%>
<%} 
else{%>
<div align="left">
 <h1> <bean:message key="detailsNotFound"/> </h1>
</div>
<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
<%} %>
<html:hidden name="PackingListGenerationFB" property="hmode"/>
<html:hidden name="PackingListGenerationFB" property="packingListGenerationType"/> 
</body>
</html:form>
</html>
