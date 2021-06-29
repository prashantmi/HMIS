<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="enquiry.transaction.controller.fb.HospitalLabEnquiryFB"%>
<%@page import="hisglobal.tools.tag.*"%>
<%@page import="java.util.*"%>
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
<script>
function submitPage(hmode)
{

 document.getElementsByName('hmode')[0].value=hmode
 document.forms[0].submit();
}
function onChangeHospital(obj){
	document.getElementsByName("hospitalName")[0].value=obj.options[obj.selectedIndex].text;
	submitPage("ONCHANGEHOSPITAL");
}
function getLabDetail(labCode,labType)
{
 document.getElementsByName('hmode')[0].value='GETLABTEST'
 document.getElementsByName('labCode')[0].value=labCode
 document.getElementsByName('labType')[0].value=labType
 document.forms[0].submit();
}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function getTestByName(obj)
{
	var testName=document.getElementsByName('test')[0].value
	//alert(testName)
	document.getElementsByName('testName')[0].value=testName;
	document.getElementsByName('hmode')[0].value='GETTESTSBYNAME';
	document.forms[0].submit();
}
function onLoadBody(){
	var objHospitalCode = document.getElementsByName("hospitalCode")[0];
	if(document.getElementsByName("isHospitalComboShown")[0].value=="1")
		document.getElementsByName("hospitalName")[0].value=objHospitalCode.options[objHospitalCode.selectedIndex].text;
}


</script>
<body onload="onLoadBody();">
<logic:equal name="hospitalLabEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/hospitalLabEnquiry.cnt'/>" method="post">
</logic:equal>
<his:TransactionContainer>

<his:TitleTag name="Laboratory Test Enquiry">
	</his:TitleTag>
	
	<his:statusList>
	<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td class="tdfonthead" nowrap width="50%">
						<div align="right"><font color="#FF0000"
							size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="hospname" /> </font></div>
					</td>
					<logic:equal name="hospitalLabEnquiryFB" property="isHospitalComboShown" value="1">
					<td class="tdfont" width="50%">
						<html:select name="hospitalLabEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
							<logic:present
								name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
								<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</td>
					</logic:equal>
					<logic:notEqual name="hospitalLabEnquiryFB" property="isHospitalComboShown" value="1">
						<td class="tdfont" width="50%">
<!--							<bean:write name="hospitalLabEnquiryFB" property="hospitalName"/>-->
						<html:select name="hospitalLabEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
							<logic:present
								name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
								<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
						</td>
					</logic:notEqual>
					
				</tr>
			</table>
		</his:ContentTag>
	<his:SubTitleTag name="Laboratory List">
	</his:SubTitleTag>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
	<bean:define id="labMap" name="<%=enquiryConfig.HOSPITAL_LABORATORY_MAP %>" type="java.util.LinkedHashMap"></bean:define>
	<%int noOfColumns=1;
	if(labMap.size()>0)
		noOfColumns=labMap.size();
	String width=100/noOfColumns+"%";%>
	<logic:iterate id="hospitalLabMap" name="labMap" type="java.util.Map.Entry">
	 <td width="<%=width%>" valign="top">
	 <bean:define id="labList" name="hospitalLabMap" property="value" type="java.util.List"></bean:define>
	
	 <logic:iterate id="hospitalLabVO" name="labList" type="enquiry.vo.HospitalLabEnquiryVO">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr style="height: 15px;">
			<td class="tdfont">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<a onclick="getLabDetail('<%=hospitalLabVO.getLabCode()%>','<%=hospitalLabVO.getLabType() %>')" style="cursor: pointer;">
				<bean:write name="hospitalLabVO" property="labName"/>
				<logic:notEmpty name="hospitalLabVO" property="department">
					( <bean:write name="hospitalLabVO" property="department"/> )
				</logic:notEmpty>
				</a></font>
			</td>
		</tr>
		</table>
	</logic:iterate>
	</td>
	</logic:iterate>
	</tr>
	</table>
	
	</his:ContentTag>
	</his:statusList>
	
	<his:statusTransactionInProcess>
			<%	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((HospitalLabEnquiryFB)request.getAttribute("hospitalLabEnquiryFB")).getCurrentPage());
				fbPage.setObjArrName(enquiryConfig.HOSPITAL_LAB_TEST_LIST_VIEW);
				fbPage.setAppendInTitle("<table width='100%'><tr><td width='50%'><div align='left'><b>Test List</b></div></td> "
										+ "<td width='50%'><div align='right'><b>Test Name</b> &nbsp;<input type='text' name=\"test\" "
										+"tabindex='1' onkeypress='if(event.keyCode==13) getTestByName(this)'/>"
										+"<td><img class=\"button\" src='/AHIMS/hisglobal/images/Search.png' style='cursor: pointer' tabindex='1' onkeypress='if(event.keyCode==13) getTestByName(this)' onclick=\"getTestByName(this)\">"
										+"<td></div></td></tr></table> ");
				fbPage.setTitleRequired(false);
				fbPage.setMaxRecords(15);
			%>
			
		<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
		<td width="50%" class="tdfonthead">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">			
			<bean:message key="labName" />
		</font>			
		</td>
		<td width="50%" class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:write name="hospitalLabEnquiryFB" property="labName"/>
		</font>
		</td>
	</tr>
	<tr>
		<td width="50%"  class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
						<bean:message key="labIncharge" />
			</font>		
		</td>
		<td width="50%" class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:write name="hospitalLabEnquiryFB" property="labIncharge"/>
		</font>
		</td>
	</tr>
	<tr>
		<td width="50%"  class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
						<bean:message key="labType" />
			</font>		
		</td>
		<td width="50%" class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:write name="hospitalLabEnquiryFB" property="labType"/>
		</font>
		</td>
	</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
	
	<!--<tr>
		<td width="25%"  class="tdfonthead">
			<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="building" /></font>
			</div>		
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
			<bean:message key="block" /></font>
			</div>		
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
			<bean:message key="floor" /></font>
			</div>	
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
			<bean:message key="room" /></font>
			</div>		
		</td>
	</tr>
	<tr>
		<td class="tdfont">
		<div align="center">
		<bean:write name="hospitalLabEnquiryFB" property="locationBuilding" />
		</div>
		</td>
		
		<td class="tdfont">
		<div align="center">
		<bean:write name="hospitalLabEnquiryFB" property="locationBlock" />
		</div>
		</td>
		<td class="tdfont">
		<div align="center">
		<bean:write name="hospitalLabEnquiryFB" property="locationFloor" />
		</div>
		</td>
		<td class="tdfont">
		<div align="center">
		<bean:write name="hospitalLabEnquiryFB" property="locationRoom" />
		</div>
		</td>
	</tr>-->
	
	</table>
	</his:ContentTag>
	
	<his:PaginationTag name="fbPagination"></his:PaginationTag>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td class="tdfonthead" width="25%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif">
		<b><bean:message key="test" /></b> </font></div>
		</td>
		<td class="tdfonthead" width="15%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
		key="charges" /> </b></font></div>
		</td>
		<td class="tdfonthead" width="30%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
		key="day" /> </b></font></div>
		</td>
		<td class="tdfonthead" width="10%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
		key="time" /> </b></font></div>
		</td>
		<td class="tdfonthead" width="30%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif">
		<b><bean:message key="prerequisite" /></b> </font></div>
		</td>
	
	</tr>
	<%try{
		List lst=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_LAB_TEST_LIST_VIEW);
		int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
		int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
		for(int i=startIndex;i<=endIndex;i++)
		{
			HospitalLabEnquiryVO testEnquiryVO=(HospitalLabEnquiryVO)lst.get(i);
		%>
			
		<tr>
			<td  class="tdfont">
				<div align="center">
				<%=testEnquiryVO.getTestName()==null?" ":testEnquiryVO.getTestName() %>
				
				</div>
			</td>
			<td  class="tdfont">
				<div align="center">
				<%=testEnquiryVO.getTestCharges()==null?"":testEnquiryVO.getTestCharges() %>
				</div>
			</td>
			<td  class="tdfont">
				<div align="center">
				<%=testEnquiryVO.getTestDay()==null?"":testEnquiryVO.getTestDay() %>
				</div>
			</td>
			
			<td  class="tdfont">
				<div align="center">
				<%=testEnquiryVO.getTimeRequired()==null?"":testEnquiryVO.getTimeRequired() %>
				</div>
			</td>
			<td  class="tdfont">
				<div align="left">
				<%=testEnquiryVO.getPreRequisite()==null?" ":testEnquiryVO.getPreRequisite() %>
				</div>
			</td>
		
		</tr>
	<%} }catch(Exception e){e.printStackTrace();}%>
	</table>
	</his:ContentTag>
	</his:statusTransactionInProcess>
	
	<html:hidden name="hospitalLabEnquiryFB" property="departmentCode"/>
	<html:hidden name="hospitalLabEnquiryFB" property="labCode"/>
	<html:hidden name="hospitalLabEnquiryFB" property="labName"/>
	<html:hidden name="hospitalLabEnquiryFB" property="labType"/>
	<html:hidden name="hospitalLabEnquiryFB" property="testName"/>
	<html:hidden name="hospitalLabEnquiryFB" property="hmode"/>
	<html:hidden name="hospitalLabEnquiryFB" property="labIncharge"/>
	<html:hidden name="hospitalLabEnquiryFB" property="locationBuilding"/>
	<html:hidden name="hospitalLabEnquiryFB" property="locationBlock"/>
	<html:hidden name="hospitalLabEnquiryFB" property="locationFloor"/>
	<html:hidden name="hospitalLabEnquiryFB" property="locationRoom"/>
	<html:hidden name="hospitalLabEnquiryFB" property="currentPage"/>
	<html:hidden name="hospitalLabEnquiryFB" property="isDirectCall"/>
	<html:hidden name="hospitalLabEnquiryFB" property="hospitalName"/>
	<logic:notEqual name="hospitalLabEnquiryFB" property="isHospitalComboShown" value="1">
		<html:hidden name="hospitalLabEnquiryFB" property="hospitalCode"/>
	</logic:notEqual>
	<html:hidden name="hospitalLabEnquiryFB" property="isHospitalComboShown"/>

</form>

<his:ButtonToolBarTag>
	<logic:equal name="hospitalLabEnquiryFB" property="isFinalCancelReqd" value="1">
		<logic:equal name="hospitalLabEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
		</logic:equal>
		<logic:notEqual name="hospitalLabEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');">
		</logic:notEqual>
	</logic:equal>
	<logic:equal name="hospitalLabEnquiryFB" property="isFinalCancelReqd" value="0">
		<logic:equal name="hospitalLabEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1" onclick="submitPage('unspecified');">
		</logic:equal>
		<logic:notEqual name="hospitalLabEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
		</logic:notEqual>
		
	</logic:equal>
	
	
</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>