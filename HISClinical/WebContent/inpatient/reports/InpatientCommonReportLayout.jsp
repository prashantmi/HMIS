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
	   
	
	 if(val=="DOCTORCALLBOOKLOGREPORT")
       {
       	if(DoctorCallBookLogReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="LISTOFCASESHEETREPORT")
       {
       	if(ListOfCaseSheetReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
          if(val=="JSYREGISTRATIONREPORT")
       {
       	if(JSYRegistrationReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       if(val=="SPECIALITYWISEOPERATION")
       {
	       //alert("inside submit report");
       	if(SpecialityWiseOperationReportHandler())
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
       
       if(val=="DISEASECODEREPORT")
       {
       	if(DiseaseCodeReportHandler())
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
       
       if(val=="LISTOFABSCONDINGPATIENTREPORT")
       {
       	if(ListofAbscondingPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
       if(val=="LISTOFNONMLCPATIENTREPORT")
       {
       	if(ListOfNonMlcPatientReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       
       if(val=="DAILYINDOOREREGISTERFORWARDREPORT")
       {
       	if(DailyIndooreRegisterForWardReportHandler())
       	{
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