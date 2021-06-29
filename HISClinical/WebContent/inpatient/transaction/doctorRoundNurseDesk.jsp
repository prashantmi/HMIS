<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitForm21(mode)
{
    
//   alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}
function validateNurseAdd()
{
	var valid=true;
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		alert("Please Select The Unit");
		document.getElementsByName("unitCode")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("employeeNo")[0].value=="-1")
	{
		alert("Please Select The Doctor Name");
		document.getElementsByName("employeeNo")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("visitNote")[0].value=="")
	{
		alert("Please Enter The Visit Notes");
		document.getElementsByName("visitNote")[0].focus();
		valid=false;
	}
	return valid;
}

function getDoctorInstruction(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/doctorRoundByOther.cnt?hmode=DOCINSTRUCTION&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,700);
}

function addVisitNotes(event)
{
	var path='/HISClinical/inpatient/doctorRoundByOther.cnt?hmode=ADDNOTES&processId=3';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

</script>
<his:TitleTag>
	<his:name>
		<bean:message key="docVisitDtl" />
	</his:name>
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
		<his:SubTitleTag name="Visit Notes">
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					
					<td>
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<a style="cursor:pointer" onclick="getDoctorInstruction(event)" >	
										<bean:message key="docInstruction"/>
									</a>	
								</b>
							</font>
						</div>
					</td>
				</tr>
			</table>					
		</his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="20%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>
								<bean:message key="unit"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="80%" class="tdfont" colspan="2">
			          <div align="left">
			            <html:select name="DoctorRoundFB" property="unitCode" style="width:160;" onkeypress="if(event.keyCOde==13)submitForm21('GETCONSULTANT');" onchange="submitForm21('GETCONSULTANT');" styleClass="registrationCmb" > 
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=InpatientConfig.DEPT_UNIT_LIST %>" labelProperty="label" property="value"  />
						</html:select> 
					</div>				
			    </td>
			</tr>
			<tr>
				<td width="20%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="doctor"/>
								<bean:message key="name"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="80%"  class="tdfont" colspan="2">
					<div align="left">
						<html:select name="DoctorRoundFB" property="employeeNo">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE%>">
							<html:options  collection="<%=InpatientConfig.ESSENTIAL_EMPLOYEE_LIST_UNIT_WISE%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
			<tr>
			<td width="20%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="visitNote"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="60%"  class="tdfont">
					<div align="left">
						<html:textarea name="DoctorRoundFB" property="visitNote" rows="3" cols="80" onkeypress="return (validateTextArea(event,this,'1000') && validateAlphaNumericOnly(event))"></html:textarea>
						
					</div>
				</td>
				<td width="20%" class="tdfont">
					<div align="center">
						<html:button value="Add Visit Notes"  property="mybutton" onclick="addVisitNotes(event)" style='cursor:pointer'  tabindex='1'/>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>

	
	
	<his:ButtonToolBarTag>
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateNurseAdd(),'NURSESAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateNurseAdd(),'NURSESAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:ButtonToolBarTag>
	
	
<html:hidden name="DoctorRoundFB" property="hmode"/>
<html:hidden name="DoctorRoundFB" property="patCrNo"/>
<html:hidden name="DoctorRoundFB" property="processId"/>	

