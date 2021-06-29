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
	<his:javascript src="/hisglobal/js/utilityFunctions.js" />
	<his:javascript src="/hisglobal/js/validation.js"/>
    <his:javascript src="/hisglobal/masterutil/js/master.js"/>
    <his:javascript src="/hisglobal/js/util.js"/> 
	<title><tiles:getAsString name="title"/></title> 	
	
	<script>
	
	function clearReport()
	{	
    	document.getElementsByName('reportMode')[0].value="NEW";  	   	    
	    document.forms[0].submit();
    }
    function cancelReport()
    {
    	cancelFunc();
    	/* document.getElementsByName('mode')[0].value="CANCEL";  	   	    
	    document.forms[0].submit(); */
    }
 
    function submitReport()
    {
       
	   val=document.getElementsByName('mode')[0].value;
	   
	 if(val=="SPECIALITYWISEOUTDOORPATIENTS")
       {
       	if(SpecicialityWiseOutdoorPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
        if(val=="DEPTWISEMONTHLYABSTRACTOFPATIENT")
       {
       	if(DeptWiseMonthlyAbstractOfPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="SPECIALITYWISEINDOORPATIENTS")
       {
       	if(SpecicialityWiseIndoorPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
       if(val=="SPECIALITYUNITWISEOPDPATIENTS")
       {
       	if(SpecicialityUnitWiseOPDPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
         if(val=="SPECIALITYGENDERWISEOPDPATIENTS")
       {
       	if(SpecicialityGenderWiseOPDPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
	
       if(val=="SPECIALITYWISESUNDAYCLINICPATIENTS")
       {
       	if(SpecicialityWiseSundayClinicPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="SPECIALITYUNITWISESPLCLINICOPDPAT")
       {
       	if(SpecicialityUnitWiseSplClinicOPDPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
      
       if(val=="SPECIALITYWISEOPERATION")
       {
       	if(SpecialityWiseOperationReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="COMMUNICABLEDISREPORT")
       {
       	if(CommunicableDisReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="SPECIALITYWISEINVESTIGATION")
       {
       	if(SpecialityWiseInvestigationReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
         if(val=="REGISTEREDMLCPATIENTREPORT")
       {
       	if(RegisteredMlcPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
          if(val=="BIRTHDETAILSREPORT")
       {
       	if(BirthDetailsReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
          if(val=="DEATHDETAILSREPORT")
       {
       	if(DeathDetailsReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="TWENTYPOINTREPORT")
       {
       	if(TwentyPointReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
           if(val=="PATIENTNOTDISCHARGEDREPORT")
       {
       	if(PatientNotDischargedReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="DISEASECODEREPORT")
       {
       	if(DiseaseCodeReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
         if(val=="LICREPORT")
       {
       	if(LICReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
         if(val=="GENDERWISEOUTDOORPATIENTS")
       {
       	if(GenderWiseOutdoorPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
         
       if(val=="AGESEXRELIGIONHOSREGREPORT")
       {
       	if(AgeSexReligionHosRegReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="AGESEXRELIGIONHOSPREGREPORT")
       {
       	if(ASRDORWiseHospitalRegistrationReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
        if(val=="AGESEXRELIGIONDEPTREGREPORT")
       {
       	if(AgeSexReligionDeptRegReportHandler())
       	{
       		document.forms[0].submit();
       	}
       } 
        if(val=="NONCOMMUNICABLEDISREPORT")
       {
       	if(NonCommunicableDisReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
        if(val=="INDOORPATIENTSBETWEENTWODATESREPORT")
       {
       	if(IndoorPatientsBetweenTwoDatesReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
        if(val=="SEXRATIOREPORT")
       {
       	if(SexRatioReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="FORMPREPORT")
       {
       	if(FormPReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="FLUOROSISERADICATIONPROGRAMMEREPORT")
       {
       	if(FluorosisEradicationProgrammeReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="REGISTRATIONCENSUSREPORT")
       {
       	if(RegistrationCensusReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="REGISTRATIONPATIENTLISTINGREPORT")
       {
       	if(RegistrationPatientListingReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
       if(val=="OPDSTATICREPORT")
       {
       		if(opdStaticReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="ADMISSIONANDDISCHARGESTATICREPORT")
       {
       		if(admissionAndDischargeStaticReportHandler()){
       			document.forms[0].submit();
       		}
       }
       if(val=="ADMISSIONANDDISCHARGELISTINGREPORT")
       {
       		if(admissionAndDischargeListingReportHandler()){
           		//alert("21");
       			document.forms[0].submit();
       		}
       }
       
       if(val=="DISEASEWISEPATIENTSTATICREPORT")
       {
       		if(diseaseWisePatientStaticReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="DISEASEWISEPATIENTLISTINGREPORT")
       {
       		if(diseaseWisePatientListingReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="DEATHPATIENTLISTINGREPORT")
       {
       		if(DeathPatientListingReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="MLCPATIENTLISTINGREPORT")
       {
       		if(mlcPatientListingReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="BROUGHTDEATHPATIENTLISTINGREPORT")
       {
       		if(broughtDeathPatientListingReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="WARDCENSUSREPORT")
       {
       		if(wardCensusReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="OPERATIONCENSUSREPORT")
       {
       		if(operationCensusReportHandler()){
       			document.forms[0].submit();
       		}
       }
       if(val=="LABCENSUSREPORT")
       {
       		if(labCensusReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="ISSUEANDRECEIPTOFCASEPAPERINTERFACEREPORT")
       {
       		if(issueAndReceiptOfCasePaperInterfaceReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       if(val=="MEDICALCAMPREPORT")
       {
       		if(medicalCampReportHandler()){
       			document.forms[0].submit();
       		}
       }
       
       
	}
</script>
	
</head>
<html:form action="/report" >
<body> 
<his:TransactionContainer>
	<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	String header=(String)compContext.getAttribute("header");
	String body=(String)compContext.getAttribute("body");
	String footer=(String)compContext.getAttribute("footer");
	%>
	  <jsp:include page="<%=header %>" flush="true"></jsp:include>
	  <jsp:include page="<%=body %>" flush="true"></jsp:include>
	  <jsp:include page="<%=footer %>" flush="true"></jsp:include>
	

</his:TransactionContainer>
</body> 
</html:form>
</html:html> 