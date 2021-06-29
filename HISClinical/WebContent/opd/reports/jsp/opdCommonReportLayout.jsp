<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %> 

<%@page import="org.apache.struts.tiles.ComponentContext"%>
<html:html> 
<head>    

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	<title><tiles:getAsString name="title"/></title> 	
	
	<script>

	function clearReport()
	{
    	document.getElementsByName('reportMode')[0].value="NEW";  	   	    
	    document.forms[0].submit();
    }
    function cancelReport()
    {
    	document.getElementsByName('mode')[0].value="CANCEL";  	   	    
	    document.forms[0].submit();
    }
    
    function submitReport()
    {
	   val=document.getElementsByName('mode')[0].value;
	   
	  //alert(">>>>>>>"+val);
	 	if(val=="OPDDEPTUNITWISESTATREPORT")
       {
       	if(opdDeptUnitWiseStatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
	 
	   if(val=="OPDPATREFERRALDTLREPORT")
       {
       	if(OPDPatReferralDtlReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTWISEREFERPATFROMREPORT")
       {
       	if(OpdDeptWiseRefPatFromReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTWISEREFERPATTOREPORT")
       {
       	if(OpdDeptWiseRefPatToReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTUNITCATWISESTATREPORT")
       {
       	if(opdDeptUnitCatWiseStatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTUNITUSERWISESTATREPORT")
       {
       	if(opdDeptUnitUserWiseStatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDCOUNTOLDPATNEXTVISITREPORT")
       {
       	if(opdCountOldPatNextVisitReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTUNITDIAGNOSISSTATREPORT")
       {
       	if(opdDeptUnitDiagnosisStatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTUNITREGCATPATCATREPORT")
       {
       	if(opdDeptUnitRegCatPatCatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTWISEDISEASECODELISTREPORT")
       {
       	if(opdDeptWiseDiseaseCodeListReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDISCREPANCYREPORT")
       {
       	if(opdDiscrepancyReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTUNITEPISODESTATUSPATLISTREPORT")
       {
       	if(opdDeptUnitEpisodeStatusPatListReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDREFERREDPATIENTLISTREPORT")
       {
       	if(opdReferredPatientListReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDROSTERREPORT")
       {
       	if(opdRosterReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDDEPTUNITDOCTORWISEREPORT")
       {
       	if(opdDeptUnitDoctorWiseStatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="OPDCASESHEETREPORT")
       {
       	if(opdCaseSheetReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
        if(val=="NUMBEROFPATIENTIMAGEUPLOADEDREPORT")
       {
       	if(numberOfPatientImageUploadedReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
         if(val=="OPDLISTINGOFPATIENTREPORT")
       {
       	if(opdListingOfPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
         if(val=="PATIENTLISTINGREPORTFOROPDVISIT")
       {
       	if(patientListingReportForOpdVisitHandler())
       	{
       		document.forms[0].submit();
       	}
       }
         
          if(val=="OPDDISEASEWISENOOFPATATTENDALLREPORT")
       {
        
       	if(opdDiseaseWiseNoOfPatAttendedReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
       
       if(val=="OPDDISEASEWISENOOFPATATTENDINOUTREPORT")
       {
       	if(opdDiseaseWiseNoOfPatAttendedInOutReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
         
         if(val=="AVERAGESTAYTIMEOFTHEPATIENT")
       {
       	if(averageStayTimeOfThePatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
         if(val=="EQUIPMENTISSUEREPORT")
       {
       	if(equipmentIssueReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="ICDCODEREPORT")
       {
       	if(icdCodeReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
       if(val=="DIAGNOSISWISEOPDANDIPDREPORT")
       {
		   if(diagnosisWiseOpdAndIpdReportHandler())
		   {
		   		document.forms[0].submit();
		   }
       }
       
       if(val=="COUNTNOOFPATIENTOFANORECTANGLEDISEASEREPORT")
       {
		   if(countNoOfPatientOfAnoRectangleDiseaseReportHandler())
		   {
		   		document.forms[0].submit();
		   }
       }
       if(val=="OPDPATIENTATTENDED")
       {
       	
		   if(opdPatientAttendedReportHandler())
		   {
		   		document.forms[0].submit();
		   }
       }
       
       
       
	}   
	
	</script>
	
</head>
<body>
<html:form action="/report" >
	<%
		ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
		String header=(String)compContext.getAttribute("header");
		String body=(String)compContext.getAttribute("body");
		String footer=(String)compContext.getAttribute("footer");
	%>

<his:TransactionContainer>

		<jsp:include page="<%=header%>" flush="true"></jsp:include>
		<jsp:include page="<%=body%>" flush="true"></jsp:include>
		<jsp:include page="<%=footer%>" flush="true"></jsp:include>

</his:TransactionContainer>

</html:form>
</body> 
</html:html> 