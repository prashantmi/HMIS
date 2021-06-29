<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>    
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>


<script>
function submitForm(transactionMode)
{
document.forms[0].transactionMode.value=transactionMode;
document.forms[0].submit();
}

</script>

<body>
<html:form action="/master/disclaimerMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
	
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
  String  currentDate="";
    currentDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:statusNew>
	<%varIsNewStatus=true;
	
	 varStatus="New";%>
</his:statusNew>

 <%boolean varReadOnly=false;%>	


<his:TitleTag name="Default Disclaimer Add">			

</his:TitleTag> 

<his:SubTitleTag name="" >
</his:SubTitleTag>
<his:ContentTag>
<table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
  	 <td width="40%" class="tdfonthead">
	  <div align="center">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><bean:message key="location"/></b>
	  </font>
	  </div>
      </td>  
  	 <td width="30%" class="tdfonthead">
	  <div align="center">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><bean:message key="alignment"/></b>
	  </font>
	  </div>
      </td>  
  	 <td width="30%" class="tdfonthead">
	  <div align="center">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><bean:message key="isActive"/></b>
	  </font>
	  </div>
      </td>  

  </tr>
  <tr>
  	  <td width="30%" class="tdfont">
	  <div align="center">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 	<b><bean:message key="header"/></b>
	 	<html:radio name="AddDisclaimerFB" property="isHeader" value="1"></html:radio>
	 	<b><bean:message key="footer"/></b>
	 	<html:radio name="AddDisclaimerFB" property="isHeader" value="0"></html:radio>
	  </font>
	  </div>
      </td>  <td width="30%" class="tdfont">
	  <div align="center">	           
		  <html:select name="AddDisclaimerFB" tabindex="1" property="alignment" styleClass="regCbo">
			 	<html:option value="1">Center</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Left</html:option>
		  </html:select>
	  </div>
      </td>  
  	  <td width="30%" class="tdfont">
	  <div align="center">	           
	  	<html:select name="AddDisclaimerFB" tabindex="1" property="isValid" styleClass="regCbo">
		 	<html:option value="1">Active</html:option>
			<html:option value="2">In Active</html:option>
		</html:select>
	  </div>
      </td>  
  </tr>
</table>
	   <!--<div align="right">
	   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	   
	    <b>
	  	 <bean:message key="isActive"/>
	 	 </b>
	 	 <html:select name="AddDisclaimerFB" tabindex="1" property="isValid" styleClass="regCbo">
			 	<html:option value="1">Active</html:option>
				<html:option value="2">In Active</html:option>
	   		 	</html:select>
	 	 	</div>

-->
</his:ContentTag>

 <his:SubTitleTag name="Normal Card Disclaimer">
 </his:SubTitleTag>
  <his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer1"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="generalDisclaimerDesc1" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)" />  
	     
	  </div>
      
       </td>
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer2"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="generalDisclaimerDesc2" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer3"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="generalDisclaimerDesc3" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
   	   
</table>
</his:ContentTag>


<his:SubTitleTag name="Special Card Disclaimer">
</his:SubTitleTag>
  <his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer1"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="specialDisclaimerDesc1" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer2"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="specialDisclaimerDesc2" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer3"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="specialDisclaimerDesc3" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
   	   
</table>
</his:ContentTag>


<his:SubTitleTag name="Casuality Card Disclaimer">
</his:SubTitleTag>
  <his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer1"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="casualityDisclaimerDesc1" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer2"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddDisclaimerFB"  maxlength="100" size="130" property="casualityDisclaimerDesc2" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="left">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="disclaimer3"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   		<html:text name="AddDisclaimerFB"  maxlength="100" size="130"  property="casualityDisclaimerDesc3" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
   	   
</table>
</his:ContentTag>




<html:hidden name="AddDisclaimerFB" property="transactionMode" />
<html:hidden name="AddDisclaimerFB" property="entryDate"  value="<%=currentDate%>" />

<his:ButtonToolBarTag>   
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('SAVE')" onkeypress="if(event.keyCode==13)submitForm('SAVE')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('CANCEL_ADD')" onkeypress="if(event.keyCode==13) submitForm('CANCEL_ADD')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('CLEAR')" onkeypress="if(event.keyCode==13) submitForm('CLEAR');">          	
</his:ButtonToolBarTag>

</his:TransactionContainer>
 <his:status/>
</html:form>
</body>
</html>