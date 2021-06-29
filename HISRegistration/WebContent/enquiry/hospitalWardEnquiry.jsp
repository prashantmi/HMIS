<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="registration.RegistrationConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<script language="Javascript" src="/AHIS/hisglobal/js/util.js"></script>
<script language="Javascript" src="/AHIMS/hisglobal/js/toolTip.js"></script>

<script>

function submitPage(mode)
{

 document.getElementsByName('hmode')[0].value=mode;
 document.forms[0].submit();
}

function submitForm(mode,wardCode,wardName)
{

 var objHospCode = document.getElementsByName('hospitalCode')[0];

 document.getElementsByName('hmode')[0].value=mode;
 document.getElementsByName('wardCode')[0].value=wardCode;
 document.getElementsByName('ward')[0].value=wardName;
 document.getElementsByName('hospitalName')[0].value=objHospCode.options[objHospCode.selectedIndex].text; 
 document.forms[0].submit();
}
function onChangeHospital(obj){
	document.getElementsByName("hospitalName")[0].value=obj.options[obj.selectedIndex].text;
	document.forms[0].hmode.value="ONCHANGEHOSPITAL";
	document.forms[0].submit();
}

function getBedDetail(e,departmentUnitCode,wardCode,department,departmentUnit){
	//alert("departmentUnitCode "+departmentUnitCode)
	document.getElementsByName('departmentUnitCode')[0].value=departmentUnitCode
	document.getElementsByName('department')[0].value=department
	document.getElementsByName('departmentUnit')[0].value=departmentUnit
	document.getElementsByName('wardCode')[0].value=wardCode
	document.getElementsByName('hmode')[0].value='BEDDETAIL'
	/*var mode="hmode=BEDDETAILS&departmentUnitCode=" + departmentUnitCode + "&wardCode=" + wardCode;	
	var path="/AHIMS/enquiry/hospitalWardEnquiry.cnt?"+ mode;
	openPopup(path,e,300,600);
	*/
	document.forms[0].submit();
	
}

</script>

<body>
<his:TransactionContainer>
<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src="/enquiry//hospitalWardEnquiry.cnt"/>" method="post">
</logic:equal>
<his:TitleTag name="Ward Enquiry">
	</his:TitleTag>
	<his:statusList>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<logic:equal name="hospitalWardEnquiryFB" property="isPageList" value="1">
					<td class="tdfonthead" nowrap width="25%">
						<div align="right"><font color="#FF0000"
							size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="hospname" /> </font></div>
					</td>
					<td class="tdfont" width="25%">
						<html:select name="hospitalWardEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
							<logic:present
								name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
								<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</td>
				</logic:equal>
				<logic:notEqual name="hospitalWardEnquiryFB" property="isPageList" value="1">
				
					<td class="tdfonthead" nowrap width="25%">
						<div align="right"><font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="hospname" /> </font></div>
					</td>
					<td class="tdfont" width="25%">
						<html:select name="hospitalWardEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
							<logic:present
								name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
								<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</td>
<!--					<td class="tdfont" width="25%">-->
<!--						<div align="left">-->
<!--							<bean:write name="hospitalWardEnquiryFB" property="hospitalName" />-->
<!--						</div>-->
<!--					</td>-->
					
				</logic:notEqual>
			</tr>
		</table>
	</his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<bean:define id="allWardTypeMap" name="<%=enquiryConfig.WARD_TYPE_MAP %>" type="java.util.LinkedHashMap"></bean:define>
		<% int tdWidth=1;
		if(allWardTypeMap.size()>0)
			tdWidth=allWardTypeMap.size();
		tdWidth=100/tdWidth;
		String oddFontColor="background-Color:#F1F6F6; text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
		String evenFontColor="background-Color:#E0EBEB; text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
		%>
		<logic:iterate indexId="idx" id="wardType" name="allWardTypeMap" type="java.util.Map.Entry">
		<%int indexValue=idx.intValue(); 
		String fontColor=oddFontColor;
		if(indexValue%2==0)
			fontColor=evenFontColor;
		%>
		<td style=position: valign="top"  width="<%=tdWidth %>%" >
		<bean:define id="wardTypeMapKey" name="wardType" property="key" type="java.lang.String"></bean:define>
		<bean:define id="wardList" name="wardType" property="value" type="java.util.List"></bean:define>
			<his:SubTitleTag name="<%=wardTypeMapKey%>">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" cellpadding="0">
			<logic:iterate id="wardEnquiryVO" name="wardList"  type="enquiry.vo.HospitalWardEnquiryVO">
				<tr style="height: 15px;">
					<td width="100%" style="<%=fontColor%>">
					<a onclick="submitForm('GETWARDDTL','<%=wardEnquiryVO.getWardCode()%>','<%=wardEnquiryVO.getWard()%>')" style="cursor:pointer">
						  <bean:write name="wardEnquiryVO" property="ward"/>
					</a>
					</td>
				</tr>
			</logic:iterate>
			</table>
			</his:ContentTag>
		</td>
		</logic:iterate>
		
	</tr>
	</table>
	</his:statusList>
	<his:statusTransactionInProcess>
		<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
		    <td width="25%" class="tdfonthead">
				<bean:message key="hospital" />
			</td>
			<td width="25%" class="tdfont">
				&nbsp;<bean:write name="hospitalWardEnquiryFB" property="hospitalName"/>
			</td>
			<td width="25%" class="tdfonthead">
				<bean:message key="ward" /> <bean:message key="name" />
			</td>
			<td width="25%" class="tdfont">
				&nbsp;<bean:write name="hospitalWardEnquiryFB" property="ward"/>
			</td>
		</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
	
	<!--<tr>
		<td width="33%"  class="tdfonthead">
			<div align="center">
			<b><bean:message key="building" /></b>
			</div>
		</td>
		<td width="33%"  class="tdfonthead">
		<div align="center">
			<b><bean:message key="block" /></b>
			</div>
		</td>
		<td width="33%"  class="tdfonthead">
		<div align="center">
			<b><bean:message key="floor" /></b>
			</div>
		</td>
		
	</tr>
	<tr>
		<td width="33%"  class="tdfont">
			<div align="center">
			<bean:write name="hospitalWardEnquiryFB" property="locationBuilding"/>
			</div>		
		</td>
		<td width="33%" class="tdfont">
			<div align="center">		
			<bean:write name="hospitalWardEnquiryFB" property="locationBlock"/>
			</div>		
		</td>
		<td width="33%" class="tdfont">
			<div align="center">		
			<bean:write name="hospitalWardEnquiryFB" property="locationFloor"/>
			</div>	
		</td>
	</tr>
	--></table>
	<his:SubTitleTag name="Ward Detail">
	</his:SubTitleTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td width="25%"  class="tdfonthead">
		<div align="center">
			<b><bean:message key="department" /></b>
			</div>
		</td>
		<td width="25%"  class="tdfonthead">
			<div align="center">
			<b><bean:message key="unit" /></b>
			</div>
		</td>
	</tr>
	<logic:iterate id="wardEnquiryVO" name="<%=enquiryConfig.ENQUIRY_WARD_DETAIL_VO %>" type="enquiry.vo.HospitalWardEnquiryVO">
	<tr>
		<td class="tdfont">
		<div align="center">
		<bean:write name="wardEnquiryVO" property="department" />
		</div>
		</td>
		<td class="tdfont">
		<div align="center">
		 <a onclick="getBedDetail(event,<%=wardEnquiryVO.getDepartmentUnitCode()%> ,<%=wardEnquiryVO.getWardCode()%>,
		 '<%=wardEnquiryVO.getDepartment()%>','<%=wardEnquiryVO.getDepartmentUnit()%>')" style="cursor:pointer"><bean:write name="wardEnquiryVO" property="departmentUnit" /></a>
		</div>
		</td>
	 </tr>
	</logic:iterate>
	</table>
	</his:ContentTag>
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
			   <tr>
			    <td width="25%" class="tdfonthead">
					<bean:message key="hospital" />
				</td>
				<td width="25%" class="tdfont">
					&nbsp;<bean:write name="hospitalWardEnquiryFB" property="hospitalName"/>
				</td>
				<td width="25%" class="tdfonthead">
					<bean:message key="ward" /> <bean:message key="name" />
				</td>
				<td width="25%" class="tdfont">
					&nbsp;<bean:write name="hospitalWardEnquiryFB" property="ward"/>
				</td>
			   </tr>
			</table>
			<table width="100%" cellspacing="1" cellpadding="0">
			 <tr>
				<td width="100%" class="tdfonthead">
					<div align="center"><font color="red"><b> No Ward Detail Found</b></font></div>
				</td>
				
			</tr>
			</table>
		</his:ContentTag>
	</his:statusUnsuccessfull>
	
	<html:hidden name="hospitalWardEnquiryFB" property="hmode"/>
	<html:hidden name="hospitalWardEnquiryFB" property="locationBlock"/>
	<html:hidden name="hospitalWardEnquiryFB" property="locationFloor"/>
	<html:hidden name="hospitalWardEnquiryFB" property="locationBuilding"/>
	<html:hidden name="hospitalWardEnquiryFB" property="wardCode"/>
	<html:hidden name="hospitalWardEnquiryFB" property="ward"/>
	<html:hidden name="hospitalWardEnquiryFB" property="departmentUnitCode"/>
	<html:hidden name="hospitalWardEnquiryFB" property="departmentUnit"/>
	<html:hidden name="hospitalWardEnquiryFB" property="department"/>
	<html:hidden name="hospitalWardEnquiryFB" property="isDirectCall"/>
	<html:hidden name="hospitalWardEnquiryFB" property="hospitalName"/>
	<logic:notEqual name="hospitalWardEnquiryFB" property="isPageList" value="1">
		<html:hidden name="hospitalWardEnquiryFB" property="hospitalCode"/>
		<html:hidden name="hospitalWardEnquiryFB" property="hospitalName"/>
	</logic:notEqual>	
	
<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>
<his:ButtonToolBarTag>
	<his:statusList>
	<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
	</logic:equal>
	<logic:notEqual name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');">
	</logic:notEqual>
	</his:statusList>
	<his:statusTransactionInProcess>
		<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1" onclick="submitPage('unspecified');">
		</logic:equal>
		<logic:notEqual name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
		</logic:notEqual>
	</his:statusTransactionInProcess>
	
	<his:statusUnsuccessfull>
		<logic:equal name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1" onclick="submitPage('unspecified');">
		</logic:equal>
		<logic:notEqual name="hospitalWardEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
		</logic:notEqual>
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>