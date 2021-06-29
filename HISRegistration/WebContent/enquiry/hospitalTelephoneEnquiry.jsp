<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="registration.RegistrationConfig;"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<script>
window.onload=function(){
	toggleLabel(document.getElementsByName('searchBy')[0]);
}

function submitPage(hmode)
{

 document.getElementsByName('hmode')[0].value=hmode
 document.forms[0].submit();
}
function getTelephoneDetail()
{
 var hmode
 //alert(document.getElementsByName('searchBy')[0].value)
 if(document.getElementsByName('searchBy')[0].value=="1"){
 	hmode='GETTELEPHONEBYEMP';
 }	
 else{
 	hmode='GETTELEPHONEBYDEPTNAME';
 }
 	
 document.getElementsByName('hmode')[0].value=hmode
 document.forms[0].submit();
}

function toggleLabel(obj)
{
 if(obj.value=="2"){
 	document.getElementById('departmentDiv').style.display="block"
 	document.getElementById('employee').style.display="none"
 	document.getElementById('searchTextboxTD').style.display="block"
 	document.getElementById('searchButtonTD').style.display="block"
 }	
 else if(obj.value=="1"){
 	document.getElementById('departmentDiv').style.display="none"
 	document.getElementById('employee').style.display="block"
 	document.getElementById('searchTextboxTD').style.display="block"
 	document.getElementById('searchButtonTD').style.display="block"
 }
 else{
	 document.getElementById('departmentDiv').style.display="none"
 	document.getElementById('employee').style.display="none"
 	document.getElementById('searchTextboxTD').style.display="none"
 	document.getElementById('searchButtonTD').style.display="none"
 }	
}

function onChangeHospital(obj){
	document.getElementsByName("hospitalName")[0].value=obj.options[obj.selectedIndex].text;
	document.getElementsByName('hmode')[0].value="ONCHANGEHOSPITAL";
 	document.forms[0].submit();
}
</script>

<body>
<his:TransactionContainer>
<logic:equal name="hospitalTelephoneEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/hospitalTelephoneEnquiry.cnt'/>" method="post">
</logic:equal>

<his:TitleTag name="Hospital Telephone Enquiry">
	</his:TitleTag>
	<table width="100%">
		<tr>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right"><font color="#FF0000"
					size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="hospname" /></font></div>
			</td>
			<td class="tdfont" width="25%">
				<html:select name="hospitalTelephoneEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
					<logic:present
						name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
						<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
			<td class="tdfonthead" width="50%" colspan="3"></td>
		</tr>
		<tr>
			<td width="25%" class="tdfonthead">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="apt_search_by"/>
				</font>
			</td>
			<td width="25%" class="tdfont">
				<html:select name="hospitalTelephoneEnquiryFB" property="searchBy" onchange="toggleLabel(this)" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:option value="1">Employee Name</html:option>
				</html:select>
			</td>
			<td width="20%" class="tdfonthead">
				<div id="employee" style="display: none">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="employeename"/>
					</font>
				</div>
				<div id="departmentDiv" style="display: none;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="department"/>
					</font>	
				</div>
			</td>
			<td width="15%" class="tdfont" >
				<div id="searchTextboxTD" style="display: none">
				<html:text name="hospitalTelephoneEnquiryFB" property="employeeName" tabindex="1" onkeypress="if(event.keyCode==13) getTelephoneDetail();"/>
				</div>
			</td>
			<td width="15%" class="tdfont">
				<div id="searchButtonTD" style="display: none">
				<img class="button" src='<his:path src="/hisglobal/images/Search.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) getTelephoneDetail();" tabindex="1" onclick="getTelephoneDetail();">
				</div>
			</td>
		</tr>
	</table>
	
	<his:statusTransactionInProcess>
	<logic:present name="<%=enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW %>">
	<logic:notEmpty name="<%=enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW %>">
	<his:SubTitleTag name="Employee"> 
	</his:SubTitleTag>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="employeename"/>
				</font>
				</div>
			</td>
			<td width="50%" class="tdfonthead">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="telephoneNo"/>
				</font>
				</div>
			</td>
		</tr>

		<logic:iterate id="hospitalTelephoneEnquiryVO" name="<%=enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW %>" type="enquiry.vo.HospitalTelephoneEnquiryVO" >
			<tr >
				<td width="50%" class="tdfont">
				<div align="center">
					
					<b><bean:write name="hospitalTelephoneEnquiryVO" property="employeeName"/></b>
				</div>
			 </td>
				<td width="50%" class="tdfont">
				<div align="center">
					
					<b><bean:write name="hospitalTelephoneEnquiryVO" property="telephoneNo"/></b>
				</div>
			 </td>
			</tr>
		</logic:iterate>
		
	</table>
	</his:ContentTag>
	</logic:notEmpty>	
	</logic:present>
	</his:statusTransactionInProcess>
	
	<html:hidden name="hospitalTelephoneEnquiryFB" property="hmode"/>
	<html:hidden name="hospitalTelephoneEnquiryFB" property="isDirectCall"/>
	<html:hidden name="hospitalTelephoneEnquiryFB" property="hospitalName"/>
	
<logic:equal name="hospitalTelephoneEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>
<his:ButtonToolBarTag>
	<!--<his:statusList>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
	</his:statusList>
	<his:statusDone>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
	</his:statusDone>-->
	
	<his:statusTransactionInProcess>
		<logic:equal name="hospitalTelephoneEnquiryFB" property="isFinalCancelReqd" value="1">
			<logic:equal name="hospitalTelephoneEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
			</logic:equal>
			<logic:notEqual name="hospitalTelephoneEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');">
			</logic:notEqual>
		</logic:equal>
		<logic:equal name="hospitalTelephoneEnquiryFB" property="isFinalCancelReqd" value="0">
			<logic:equal name="hospitalTelephoneEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1" onclick="submitPage('unspecified');">
			</logic:equal>
			<logic:notEqual name="hospitalTelephoneEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
			</logic:notEqual>
			
		</logic:equal>
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>
<his:status/>
</his:TransactionContainer>
</body>
</html>