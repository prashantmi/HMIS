<%@page autoFlush="true" %>
<%@ page import="org.apache.struts.tiles.ComponentContext,registration.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function callThisOnload(){
	focusCrNo();
	}
//Submit Register by setting registermode
function submitRegister(registerMode){
	elem = document.getElementsByName('registermode')[0];
	elem.value = registerMode;
	//alert(elem.value);
	if((document.getElementsByName("mode")[0].value=="CMOREGISTER")&& (document.getElementsByName("registermode")[0].value=="DTL4STATUS")){
	if(document.forms[0].episodeActionCode.value!="-1")
	document.forms[0].submit();
	else
	alert("Select an Option");
	
	}
	else
	document.forms[0].submit();
	
}
function validateCMO()

{
var valid=true;

 if(document.forms[0].episodeActionCode.value=="3"){
 //alert("validation :"+document.forms[0].diagnosticCode);
	 if(validateObjects(document.forms[0].episodeActionDate,"Date")  && validateObjects(document.forms[0].episodeActionTime,"Time")&&
	 validateObjects(document.forms[0].remarks,"Remarks")&& validateTile())
	  valid=true;
	 else
	  valid=false;
 } 
  
  else if(document.forms[0].episodeActionCode.value=="5"){
  if(validateObjects(document.forms[0].episodeActionDate,"Date")  && validateObjects(document.forms[0].episodeActionTime,"Time")&&
	 validateObjects(document.forms[0].remarks,"Remarks")&&validateObjects(document.forms[0].departmentCode,"Department") 
  )
  valid=true;
  else
  valid=false;  
}
  
  else if(document.forms[0].episodeActionCode.value=="7"){
  if(validateObjects(document.forms[0].episodeActionDate,"Date")  && validateObjects(document.forms[0].episodeActionTime,"Time")&&
	 validateObjects(document.forms[0].remarks,"Remarks")&&  
	 validateObjects(document.getElementsByName("referringInstType")[0],"Referred Out Institution"))
	  valid=true;
  else
  valid=false;  
}
  
  else if(document.forms[0].episodeActionCode.value=="8"){
  if(validateObjects(document.forms[0].deathDate,"Death Date") && validateObjects(document.forms[0].deathTime,"Death Time") && 
  validateObjects(document.forms[0].deathCause,"Cause of Death")&& 
  validateObjects(document.forms[0].mannerOfDeath,"Manner of death")&& 
  validateObjects(document.forms[0].attendConsultantId,"Death verified by")&&   
  validateObjects(document.forms[0].bodyHandOverDate,"Hand Over Date") && 
  validateObjects(document.forms[0].bodyHandOverTime,"Hand Over Time")&&
  validateObjects(document.getElementsByName("bodyHandOverTo")[1],"Body Handed To") && validateObjects(document.forms[0].patRelativeName,"Relative Name")  &&
  validateObjects(document.forms[0].relativeCode,"Relative Type")  && validateObjects(document.forms[0].patRelativeAddress,"Relative Address")){
    
     valid=true;
   }
  else
  valid=false;  
 
}
  else if(document.forms[0].episodeActionCode.value=="6"){
   if(validateObjects(document.forms[0].episodeActionDate,"Date")&& validateObjects(document.forms[0].episodeActionTime,"Time")&& validateObjects(document.forms[0].remarks,"Remarks")&&
   validateObjects(document.forms[0].departmentCode,"Department")&& validateObjects(document.forms[0].wardCode,"Ward"))
   valid=true;
   else
   valid=false;  
}
  else if(document.forms[0].episodeActionCode.value=="2"){
   if(validateObjects(document.forms[0].episodeActionDate,"Date")&& validateObjects(document.forms[0].episodeActionTime,"Time")&& validateObjects(document.forms[0].remarks,"Remarks")
   && validateTile())
   valid=true;
   else 
   valid=false;   
}

if(valid==true){

 var x= confirm("Records once saved canot be modified!!!");
 if(x==true){ 
	var x=confirm("Are you sure you want to continue????");
	if(x==true)
	   valid =true;
	  	else  
	   valid=false;
	   }
	   else{
	   valid=false;
	   }
}
return valid;
}

function submitFormOnValidate(flag,mode){
	submitFormOnValidateCMO(flag,mode);
}

</script>
<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>

	<!-- Code to fetch CRNo -->
<his:TitleTag name="EMO Register">


		<b><font size="2" face="Verdana, Arial, Helvetica, sans-serif">
         </font></b>    

</his:TitleTag>

<his:InputCrNoTag name="registerFB"> </his:InputCrNoTag>   
<% 
	ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	String patientDtl = (String)compContext.getAttribute("patientDtl");
	String registerbody = (String)compContext.getAttribute("registerbody");
	System.out.println("patientDtl=="+patientDtl);
	System.out.println("registerbody=="+registerbody);
	//String str = request.getContextPath();
%>


    <jsp:include page="<%=patientDtl %>" flush="true"></jsp:include>	
<%
	if(!patientDtl.equals("/registration/blank.jsp")){
%>
	<!-- Code to fetch Options -->	
	<his:statusInProcess>
	<his:patientStatus emergency="true" opd="false">
	<his:ContentTag>
<table width="100%" colspacing="0" colpadding="0"  >
	
  <tr>         
    <td colspan="6" class="addtoolbar">
       <div align="center">
       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
       <bean:message key="selectoption"/></b>
       </font>
    
         
        <html:select name="registerFB" property="episodeActionCode" tabindex="1" onchange="submitRegister('DTL4STATUS');">
           <html:option value="-1">Select Value</html:option>
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_CMO_REGISTER %>" property = "value" labelProperty = "label" />
        </html:select></div>
      </td> 
  </tr>

</table></his:ContentTag>
</his:patientStatus>
</his:statusInProcess>
<%
	}
%>
<table width="100%">	
<%if(!registerbody.equals("/registration/blank.jsp")) 
{%>
<jsp:include page="<%=registerbody %>" flush="true"></jsp:include>
<%} %> 
<bean:define name="registerFB" property="episodeActionCode" id="actionCode" type="java.lang.String"/>

<%
System.out.println("actionCode:::::::::"+actionCode);

if((actionCode!=null && !(actionCode.equalsIgnoreCase("-1"))) && !(actionCode.trim().equalsIgnoreCase("")))
		{
	System.out.println("print buttons:::::::::");	
	 %>		
    
	<his:ButtonToolBarTag>
	    <img class="button" style=cursor:pointer src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' onclick="submitFormOnValidateCMO(validateCMO(),'SAVE')"  tabindex="1">
        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
        <img class="button" style=cursor:pointer src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:ButtonToolBarTag>


<%
}
else
{
%>
	<his:ButtonToolBarTag>

        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitRegister('NEW');" onkeypress="if(event.keyCode==13) submitRegister('NEW');">
	</his:ButtonToolBarTag>
<%

}
%>

	<html:hidden  name='registerFB' property='registermode'/>	
	
<his:status/>