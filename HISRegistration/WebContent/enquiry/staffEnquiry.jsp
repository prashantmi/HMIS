<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.*,opd.*,enquiry.*"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<%-- <his:javascript src="/registration/js/registration.js" /> --%>
 <his:javascript src="/registration/js/popup.js" /> 
<his:javascript src="/registration/js/dateFunctions.js" />
<!--<his:javascript src="/opd/opdJs/opdAjax.js"/>-->
<!-- <his:javascript src="/opd/opdJs/opd.js"/> -->

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<script>
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}

function onChangeHospital(obj){
	document.getElementsByName("hospitalName")[0].value=obj.options[obj.selectedIndex].text;
	submitPage("ONCHANGEHOSPITAL");
}

</script>

<body>
<logic:equal name="staffEnquiryFB" property="isDirectCall" value="DIRECT">
	<form action="<his:path src='/enquiry/staffEnquiry.cnt'/>" method="post">
</logic:equal>

<his:TitleTag>
		<his:name>
			<bean:message key="staffEnquiry" />
		</his:name>
</his:TitleTag>

<his:SubTitleTag name="Staff Detail">
	<bean:message key="hospname" />
	<html:select name="staffEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
		<logic:present
			name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
			<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
		</logic:present>
	</html:select>
</his:SubTitleTag>

<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="fname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="staffEnquiryFB"  maxlength="32" size="30" property="str_first_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="mname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="staffEnquiryFB"  maxlength="32" size="30" property="str_middle_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="lname"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
				<html:text name="staffEnquiryFB"  maxlength="32" size="30" property="str_last_name" styleClass ="textbox" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" />
	  			</div>
	  		</td>
	  		
	  	</tr>
	
		<tr>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="designation"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left">
	  			<html:select name="staffEnquiryFB" tabindex="1" property="num_desig_id" styleClass="regcbo" >
					<html:option value="%">All</html:option>
  					<html:options  collection="<%=enquiryConfig.ENQUIRY_OPTION_DESIGNATION%>" property="value" labelProperty="label" />
	  			</html:select>
	  			</div>
	  		</td>
			<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="department"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont">
	  			<div align="left"><b>
	  			<html:select name="staffEnquiryFB" tabindex="1" property="num_dept_id" styleClass="regcbo" >
					<html:option value="%">All</html:option>
  					<html:options  collection="<%=enquiryConfig.ENQUIRY_BO_OPTION_ALLDEPT%>" property="value" labelProperty="label" />
	  			</html:select></b>
	  			</div>
	  		</td>
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="gender"/></b></font></div>
	  		</td>
	  		<td width="17%" class="tdfont"> 
	  			<div align="left"><b>
	  			<html:select name="staffEnquiryFB" tabindex="1" property="str_gender_name" styleClass="regcbo" >
					<html:option value="%">All</html:option>
  					<html:options  collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>" property="value" labelProperty="label" />
	  			</html:select></b>
	  			</div>
	  		</td>
	  	</tr>
	</table>
</his:ContentTag>

<his:ButtonToolBarTag>
 		<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' tabindex="1" alt="Search" title="Search" onclick="submitPage('SEARCH');" >
 		<logic:equal name="staffEnquiryFB" property="isDirectCall" value="DIRECT">
 		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
 		</logic:equal>
 		<logic:equal name="staffEnquiryFB" property="isDirectCall" value="DESK">
 		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick ="submitDesk('NEW');">
 		</logic:equal>
 		<his:statusTransactionInProcess>
 		<logic:equal name="staffEnquiryFB" property="isDirectCall" value="DIRECT">
 			<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style=cursor:pointer onclick ="submitForm('unspecified')" onkeypress="if(event.keyCode==13) submitForm('unspecified');">
 		</logic:equal>
 		<logic:equal name="staffEnquiryFB" property="isDirectCall" value="DESK">
 			<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
 		</logic:equal>
 		</his:statusTransactionInProcess>
</his:ButtonToolBarTag>
 <his:statusTransactionInProcess>
 <b><strong>Search Result</strong></b>
 <his:ContentTag>
 	<table width="100%"  cellspacing="1" cellpadding="0">                    
		 <tr>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           	<b><bean:message key="empNo"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="name"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="department"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="designation"/></b>
	            </font>
	            </div>
             </td>
                    
             <td  class="tdfonthead" width="5%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           	 <b><bean:message key="gender/age"/></b>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="10%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <b><bean:message key="location"/></b>
	            </font>
	            </div>
             </td>
            </tr> 
 		 <logic:iterate id="staffEnqVO" name="<%=enquiryConfig.STAFF_ENQUIRY_STAFF_ENQUIRY_VO %>" type="enquiry.vo.StaffEnquiryVO">
 			<tr>
 			<td class="tdfont">
	  			<div align="center">
	  				<bean:write name="staffEnqVO" property="str_emp_no"/>	
	  			</div>
	  		</td>
	  		<td class="tdfont"> 
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="str_emp_full_name"/>	
<%-- 	  			<bean:write name="staffEnqVO" property="str_middle_name"/>	 --%>
<%-- 	  			<bean:write name="staffEnqVO" property="str_last_name"/>	 --%>
	  			</div>
	  		</td>
            <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="num_dept_id"/>	
	  			</div>
	  		</td>
            <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="num_desig_id"/>	
	  			</div>
	  		</td>
	  		 <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="staffEnqVO" property="str_gender_name"/>/	
	  			<bean:write name="staffEnqVO" property="age"/>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  				<bean:write name="staffEnqVO" property="hgnum_location_code"/>	
	  			</div>
	  		</td>
	  	 </tr>
 		
 		</logic:iterate>
 		
 	</table>
 </his:ContentTag>
 
 </his:statusTransactionInProcess>
<html:hidden name="staffEnquiryFB" property="hmode"/>
<html:hidden name="staffEnquiryFB" property="isDirectCall"/>
<html:hidden name="staffEnquiryFB" property="hospitalName"/>

<logic:equal name="staffEnquiryFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>
<his:status/>
</body>
</html>