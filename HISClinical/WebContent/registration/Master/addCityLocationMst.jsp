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

window.history.forward()

function validateCityLocationMaster()
{
	var valid=true;   
	//alert("SAVE");	
	if(isEmpty(document.forms[0].locationName,"Location Name") 
	&& isSelected(document.forms[0].district,"District")
	&& validateMinimumLength(document.forms[0].pinCode,'Pin Number','6') 
	 && validatePinNumber(document.forms[0].pinCode)
	 && isSelected(document.forms[0].areaCategory,"Area Category")
	 && isEmpty(document.forms[0].cityName,"City")
	 && isEmpty(document.forms[0].pinCode,"Pin Number")
	 )       
	    {
	  	valid=true;
	  		
	  	submitTile('SAVE');
	  	}
	  	  
	else
	{
		valid=false;
		
	}
}


function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  
    document.forms[0].submit();
}

</script>

<body>
<html:form action="/master/addCityLocationMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
	
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	
	 varStatus="New";%>
</his:statusNew>

 

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
<his:TitleTag name="Add City Location">			
</his:TitleTag> 


<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <font color="#FF0000">*</font>
	  <b>
	  <bean:message key="location"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">	    
	  	<html:text name="CityLocationMstFB" tabindex="1" maxlength="60" styleClass="textbox" property="locationName" onkeypress="return validateAlphabetsOnly(event,this)"/>  	     
	  </div>      
       </td>
  </tr>
  
  <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  <font color="#FF0000">*</font>
	  <bean:message key="state"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	         
	         	<html:text name="CityLocationMstFB" tabindex="1" readonly="true" value="<%=(String)session.getAttribute(RegistrationConfig.DEFAULT_STATE_NAME)%>" property="state" styleClass="textbox" />	  
	               
	         </div>
      </td>                                           
  </tr>	   
  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>
		              <bean:message key="district"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="CityLocationMstFB" tabindex="1" property="district" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.DISTRICT_LIST_DEFAULTSTATE%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>
		              <bean:message key="areaCategory"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="CityLocationMstFB" tabindex="1" property="areaCategory" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>
   
   <tr>
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font>
		              <b><bean:message key="city"/></b>
		              </font>
		            </div>
	   </td>
	
		 <td width="20%"  class="tdfont">
	         <div align="left">
	         <html:text property="cityName" tabindex="1" name="CityLocationMstFB" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)" styleClass="textbox"/>	                
	          </div>
       </td>  
	      	  
  </tr> 
  <tr>
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font>
		              <b><bean:message key="pin"/></b>
		              </font>
		            </div>
	   </td>
	
		 <td width="20%"  class="tdfont">
	         <div align="left">
	         <html:text property="pinCode"  tabindex="1" name="CityLocationMstFB" maxlength="6" onkeypress="return validateNumeric(event)" styleClass="textbox"/>	                
	          </div>
       </td>  
	      	  
  </tr> 
  <tr>
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font>
		              <b><bean:message key="isActive"/></b>
		              </font>
		            </div>
	   </td>
	
		 <td width="20%"  class="tdfont">
	         <div align="left">
	         <html:select property="isValid" name="CityLocationMstFB" tabindex="1" styleClass="regCbo">
	         <html:option value="1">Active</html:option>
	         <html:option value="2">Inactive</html:option>
	         
	         </html:select>	                
	          </div>
       </td>  
	      	  
  </tr> 
  
<%--   
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>	 
	  <bean:message key="hl7Code"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	           
	         	<html:text name="CityLocationMstFB" tabindex="1" maxlength="10" styleClass="textbox" onkeypress="return validateNumeric(event)" property="hl7Code"/>	         
	         </div>
      </td>                                           
  </tr>  
--%> 
  	   
</table>
</his:ContentTag>  
<html:hidden name="CityLocationMstFB" property="transactionMode" />
<html:hidden name="CityLocationMstFB" property="entryDate" value="<%=sysDate%>" />
<his:ButtonToolBarTag>   
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateCityLocationMaster()" onkeypress="if(event.keyCode==13) validateCityLocationMaster()">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
</his:ButtonToolBarTag>

</his:TransactionContainer>
 <his:status/>
</html:form>
</body>
</html>