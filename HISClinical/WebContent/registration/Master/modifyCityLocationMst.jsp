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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />


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
   //alert(document.getElementsByName('transactionMode')[0].value);
   document.forms[0].submit();
}


</script>

<body>
<html:form action="/master/modifyCityLocationMaster">
<his:TransactionContainer>
		<%@ page	import="java.util.*,registration.*,hisglobal.presentation.*"%>
		<%boolean varReadOnly = false;
		  String strReadOnly="false";
		%>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
		<%varReadOnly=true;
		strReadOnly="true";%>
		</logic:equal>
		
		<%String sysDate = WebUTIL
							.getCustomisedSysDate(
									(Date) session
											.getAttribute(RegistrationConfig.SYSADATEOBJECT),
									"dd-MMM-yyyy");

					%>

		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
			value="modify">

			<his:TitleTag name="Modify City Location">
			</his:TitleTag>
		</logic:equal>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">

			<his:TitleTag name="View City Location">
			</his:TitleTag>
		</logic:equal>

<%Date sysDateObject=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT); %>
<his:ContentTag>
			
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
						color="#FF0000">*</font> <bean:message key="location" /> </b> </font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:text name="CityLocationMstFB" maxlength="60" tabindex="1" property="locationName" onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=varReadOnly%>" styleClass="textbox"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:text name="CityLocationMstFB" property="locationName" tabindex="1" readonly="true" styleClass="textbox"/></div>
					</logic:equal>
				
						
					</td>
				</tr>		
				
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
						color="#FF0000">*</font> <bean:message key="state" /> </b> </font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:text name="CityLocationMstFB" property="state" tabindex="1" readonly="true" styleClass="textbox"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:text name="CityLocationMstFB" property="state" tabindex="1" readonly="true" styleClass="textbox"/></div>
					</logic:equal>
						
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
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	                
	                <html:select name="CityLocationMstFB" tabindex="1" property="district" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.DISTRICT_LIST_DEFAULTSTATE%>" property="value" labelProperty="label" />
	   		 	</html:select>
	   		 	
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:text name="CityLocationMstFB" tabindex="1" property="districtname" readonly="true" styleClass="textbox"/>	         
  		 	</logic:equal>	   		 	
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
	         <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	          
	                <html:select name="CityLocationMstFB" tabindex="1" property="areaCategory" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY%>" property="value" labelProperty="label" />
	   		 	</html:select>
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:text name="CityLocationMstFB" tabindex="1" property="areaCategoryName" readonly="true" styleClass="textbox"/>	         
  		 	</logic:equal>	   		 	
	          </div>
       </td>                                                 
  </tr>
  
  <tr>
					<td width="30%" class="tdfonthead">
					<div align="right">
					<b><font color="#FF0000">*</font></b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="city" /> </b> 
					</font>
					</div>
					</td>
					 <td width="20%"  class="tdfont">
	        		 <div align="left">
	        		 <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
	               		<html:text property="cityName" name="CityLocationMstFB" tabindex="1" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)" styleClass="textbox"/>
	   		 		</logic:equal>
  		 			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
  		 			 <html:text name="CityLocationMstFB" tabindex="1"  property="cityName" readonly="true" styleClass="textbox"/>
  		 			</logic:equal>
	         		 </div>
      				 </td>
			</tr>
			
			<tr>

					<td width="30%" class="tdfonthead">
					<div align="right">
					<b><font color="#FF0000">*</font></b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="pin" /> </b> 
					</font>
					</div>
					</td>
					<td width="30%" class="tdfont">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:text name="CityLocationMstFB" property="pinCode" tabindex="1" maxlength="6" onkeypress="return validateNumeric(event)" readonly="<%=varReadOnly%>" styleClass="textbox"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:text name="CityLocationMstFB" property="pinCode" tabindex="1" readonly="true" styleClass="textbox"/></div>
					</logic:equal>
					</td>
			</tr>
  	<tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>
		              <bean:message key="isActive"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	         <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	          
	                <html:select name="CityLocationMstFB" tabindex="1" property="isValid" styleClass="regCbo">
			 		<html:option value="1">Active</html:option>
	        		<html:option value="2">Inactive</html:option>      		 
	   		 	</html:select>
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:text name="CityLocationMstFB" tabindex="1" property="isValid" readonly="true" styleClass="textbox"/>	         
  		 	</logic:equal>	   		 	
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
	            <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	           		              
	         	  		<html:text name="CityLocationMstFB" maxlength="10" tabindex="1" property="hl7Code" onkeypress="return validateNumeric(event)" styleClass="textbox"/>	         
	         		 
	         	</logic:equal>       
	         	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">         
	         	  <html:text name="CityLocationMstFB" maxlength="10" tabindex="1" property="hl7Code" readonly="true" styleClass="textbox"/>	         
	         	</logic:equal>	         	
	         </div>
      </td>                                           
  </tr>	 
--%>
 
</table>  

 </his:ContentTag> 
<html:hidden name="CityLocationMstFB" property="transactionMode" />
		<html:hidden name="CityLocationMstFB" property="<%=RegistrationConfig.VIEWORMODIFY%>" />		
		<html:hidden name="CityLocationMstFB" property="chk" />
		<html:hidden name="CityLocationMstFB" property="locationCode" />
		<html:hidden name="CityLocationMstFB" property="entryDate" value="<%=sysDate%>"/>
		<his:ButtonToolBarTag>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
				value="modify">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
					style=cursor:pointer tabindex="1" onclick="validateCityLocationMaster()"
					onkeypress="if(event.keyCode==13) validateCityLocationMaster()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CLEAR')"
					onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
				
			</logic:equal>
		</his:ButtonToolBarTag>
		
</his:TransactionContainer>
</html:form>
<his:status/>
</body>
</html>