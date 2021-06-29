<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.*"%>

<%@page import="enquiry.enquiryConfig"%>


<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />


<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />


<script>
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}
function submitForDepartment()
{
	var flag=false;
	if(document.getElementsByName("gnum_dept_code")[0].value!="-1")
	{
		flag=true;
	}
	return flag;
}
</script>
<his:TransactionContainer>
<logic:equal name="hospitalEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/hospitalEnquiry.cnt' />" method="post">
</logic:equal>
	<his:TitleTag name="Hospital Enquiry">
		
	</his:TitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td class="tdfonthead" nowrap width="50%">
					<div align="right"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospname" /> </font></div>
				</td>
				<td class="tdfont" width="50%">
					<html:select name="hospitalEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="submitPage('ONHOSPITALCHANGE');"> 
						<logic:present
							name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<logic:present name="<%=enquiryConfig.VO_OF_HOSPITAL_MST %>">
	<bean:define id="hospitalMstVO" name="<%=enquiryConfig.VO_OF_HOSPITAL_MST %>" type="hisglobal.vo.HospitalMstVO"></bean:define>
	<his:ContentTag>
	
	<table width="100%" cellpadding="0" cellspacing="1">
	<tr>
	<td width="50%" class="tdfonthead">
		<b><bean:message key="hospital"/><bean:message key="name"/></b>
	</td>	
	<td  width="50%"  class="tdfont">
	<div align="left">
		&nbsp;<bean:write name="hospitalMstVO" property="hospitalName"/>
	</div>	
	</td>	
	</tr>
	
	<tr>
	<td  width="50%"  class="tdfonthead" valign="top">
		<b><bean:message key="address"/></b>
	</td>	
	<td  width="50%"  class="tdfont">
	<div align="left">
		&nbsp;<bean:write name="hospitalMstVO" property="address1"/>,
		<bean:write name="hospitalMstVO" property="address2"/>
	</div>
	</td>
	</tr>
	<tr>
	<td  width="50%"  class="tdfonthead">
		<b><bean:message key="city"/></b>
	</td>	
	<td  width="50%"  class="tdfont">
	 <div align="left">
		&nbsp;<bean:write name="hospitalMstVO" property="city"/>
	</div>
	</td>
	</tr>
	<tr>
	<td  width="50%"  class="tdfonthead" >
		<b><bean:message key="state"/></b>
	</td>	
	<td  width="50%"  class="tdfont">
	 <div align="left">
		&nbsp;<bean:write name="hospitalMstVO" property="state"/>
	</div>
	</td>
	</tr>
	<tr>
	  <td class="tdfonthead" >
		<div align="right">
			<b><bean:message key="phoneNo" /></b>
		</div>	
	 </td>
	 <td class="tdfont">	
	 	<div align="left">		
		&nbsp;<bean:write name="hospitalMstVO" property="phone"/>
		</div>
		</td>
	 </tr>
	 <tr>
	 	<td class="tdfonthead" >
		<div align="right">
			<b><bean:message key="faxNo" /></b>
		</div>	
	     </td>
		<td class="tdfont" >
			<div align="left">	
			&nbsp;<bean:write name="hospitalMstVO" property="fax"/>
			</div>
		</td>
	</tr>
	<tr>
		<td class="tdfonthead" >
		<div align="right">
			<b><bean:message key="email" /></b>
		</div>	
	     </td>
		<td class="tdfont">
			<div align="left">
			&nbsp;<bean:write name="hospitalMstVO" property="email"/>
			</div>
		</td>
	 </tr>
	 <tr>
		<td class="tdfonthead" >
		<div align="right">
			<b><bean:message key="contactPerson" /></b>
		</div>	
	     </td>
		<td class="tdfont" >
			<div align="left">
			&nbsp;<bean:write name="hospitalMstVO" property="contactPerson"/>
			</div>
		</td>
		</tr>
		<tr>
			<td class="tdfonthead" >
			<div align="right">
				<b><bean:message key="hospitalType" /></b>
			</div>	
		     </td>
			<td class="tdfont" >
				<div align="left">
				&nbsp;<bean:write name="hospitalMstVO" property="hospitalTypeName"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" >
		<div align="right">
			<b><bean:message key="busRouteNo" /></b>
		</div>	
	     </td>	
			<td class="tdfont" align="left" >
			<div align="left">
			&nbsp;<bean:write name="hospitalMstVO" property="busRouteNo"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" >
			<div align="right">
				<b><bean:message key="bedCapacity" /></b>
			</div>	
	     	</td>
			<td class="tdfont" align="left" >
				<div align="left">
				&nbsp;<bean:write name="hospitalMstVO" property="bedCapacity"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" >
			<div align="right">
				<b><bean:message key="weekDayTimings" /></b>
			</div>	
		     </td>
			<td class="tdfont" align="left" >
				<div align="left">
				&nbsp;<bean:write name="hospitalMstVO" property="weekdayTimings"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" >
			<div align="right">
				<b><bean:message key="saturDayTimings" /></b>
			</div>	
		     </td>	
			<td class="tdfont" align="left" >
				<div align="left">
				&nbsp;<bean:write name="hospitalMstVO" property="saturdayTimings"/>
			</div>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" >
			<div align="right">
				<b><bean:message key="lunchTimings" /></b>
			</div>	
		     </td>		
			<td class="tdfont" align="left" >
				<div align="left">
				&nbsp;<bean:write name="hospitalMstVO" property="lunchTimings"/>
			</div>
			</td>
		</tr>
			
	</table>	
		

				
		
<logic:present name="<%=enquiryConfig.MAP_OF_HOSPITAL_CATEGORY %>">

<his:TitleTag name="Registration Timings">
</his:TitleTag>		
		
<table width="100%" border="1" >
 <tr>
<!--	 <td width="50%" class="tdfont">-->
<!--	 -->
<!--	<div align="right"><font color="#000000" size="2"-->
<!--					face="Verdana, Arial, Helvetica, sans-serif"> <b>-->
<!--	<bean:message key="registrationTimings" /></b></font></div>-->
<!--	-->
<!--	 </td>-->
   <td >
		<logic:iterate id="regCategMapEntry" name="<%=enquiryConfig.MAP_OF_HOSPITAL_CATEGORY %>" type="java.util.Map.Entry">
		
    <table  width="100%" >
		
	<tr>
	  <td  align="left">	
		<bean:define id="regCategoryKey"  name="regCategMapEntry" property="key"></bean:define>
		<bean:define id="regSeasonMap"  name="regCategMapEntry" property="value" type="java.util.Map"></bean:define>
<%
String [] regCategoryArray=((String)regCategoryKey).split("#");
%>
		

<his:SubTitleTag name="<%=regCategoryArray[1] %>">
</his:SubTitleTag>


			<logic:iterate id="regSeasonEntry" name="regSeasonMap" type="java.util.Map.Entry">
		
		<table  width="100%" cellspacing="1" >
		<tr>
	    	<td class="tdfont" width="100%">
			<bean:define id="regSeasonKey" name="regSeasonEntry" property="key"></bean:define>
			<bean:define id="regShiftMap" name="regSeasonEntry" property="value" type="java.util.Map"></bean:define>
	
<%
String [] regSeasonArray=((String)regSeasonKey).split("#");
%>	
			
	<div  style="position: absolute;left:20%;height: 15px;" >
	<%=regSeasonArray[1]%>
	</div>	
			     			
			     <logic:iterate id="regShiftEntry" name="regShiftMap" type="java.util.Map.Entry">
			
			<table width="100%" cellspacing="1">
			<tr>
		
			      
			        <bean:define id="regShiftKey" name="regShiftEntry" property="key"/>
			       <bean:define id="regDays" name="regShiftEntry" property="value" type="java.lang.String"/>
<%
String [] regShiftArray=((String)regShiftKey).split("#");

%>		
		
	<td class="tdfont" valign="top" style="height: 15px;" >
	   <div  style="position: absolute;left:50%;">
			<%if(regShiftArray.length>1) out.println(regShiftArray[1]);%>
		</div>
	</td>
	
	<td class="tdfont" valign="top" style="height: 15px; ">
	<div  style="position: absolute;left: 65% ;height: 15px;">
		<bean:write name="regDays"/>
	</div>	
	</td>
			     
			     
			     
			     </tr>
			     </table> 
			     </logic:iterate>
			
			
			</td>
			</tr>
			</table>
			
			</logic:iterate>
		
			  </td>		
			</tr>		
		</table>
		
		</logic:iterate>
</td>		
</tr>		
</table>
</logic:present>
	
	</his:ContentTag>
</logic:present>
	
	<html:hidden name="hospitalEnquiryFB" property="hmode" />
<logic:equal name="hospitalEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>
<his:ButtonToolBarTag>
	<logic:equal name="hospitalEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button"
		src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
		style="cursor: pointer"
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> 
	</logic:equal>
		
	<logic:notEqual name="hospitalEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button"
		src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
		style="cursor: pointer"
		onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1"
		onclick="submitDesk('NEW');"> 
	</logic:notEqual>
		
<!--		<his:statusTransactionInProcess>-->
<!--		<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'-->
<!--			tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')"-->
<!--			onkeypress="if(event.keyCode==13) submitForm('NEW');">-->
<!--		-->
<!--</his:statusTransactionInProcess>-->

</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>