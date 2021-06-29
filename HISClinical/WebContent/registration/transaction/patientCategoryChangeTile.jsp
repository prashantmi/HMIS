<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="registration.*,java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function showEmployeepopup(e)
{

	openPopup('<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e,300,600);
}

function validatePrimaryCategoryChange(){
// 	alert('inside validatePrimaryCategoryChange()....');
	var categoryCode=document.getElementsByName("patPrimaryCatCode")[0]
	var remarks=document.getElementsByName('remarks')[0]
	if(isSelected(categoryCode,'Primary Category') &&
		isEmpty(remarks,'Remarks') &&
		validateEmployee() &&
		validateForSeniorCitizen()&&
		validateOther())
		{
			return true
		}
	else
		{
			return false
		}
	
}

function validateEmployee()
{	
	//alert("validate Employee")
	var valid=true
	//var categoryCode=document.getElementsByName("patPrimaryCatCode")[0].value
	var catCodeWithIDReq=document.getElementsByName("patPrimaryCatCode")[0].value;
	var x=catCodeWithIDReq.split("#");
	var categoryCode=x[0];
	var idRequired=x[1];
	if(categoryCode==<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%> )
	{
	
		if(<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>==<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>)
			{
			valid=isEmpty(document.getElementsByName('empIdChk')[0],'Employee ID')
			}
		if(<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>==<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_TRUE%>)
			{
			valid=isEmpty(document.getElementsByName('patIdNo')[0],'Employee ID')
			}
			
	}
	//alert("return validate employee"+valid)
	return valid
}

function validateOther()
{	
	//alert("validate other")
	var valid=true
//	var categoryCode=document.getElementsByName("patPrimaryCatCode")[0].value
	var catCodeWithIDReq=document.getElementsByName("patPrimaryCatCode")[0].value;
	var x=catCodeWithIDReq.split("#");
	var categoryCode=x[0];
	var idRequired=x[1];
	if(idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%> && categoryCode!=<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>)
	{
		if(isEmpty(document.getElementsByName('cardNo')[0],'Card No') &&
			isEmpty(document.getElementsByName('issuingAuthority')[0],'Issuing Authority') &&
			comboValidation(document.getElementsByName('verificationDocument')[0],'Verification Document'))
			{
				valid=true
			}
		else
			{
				valid=false
			}	
	}
	//alert("return validate other"+valid)
	return valid
}

function poulateFieldsWithEmpDtl(selectedElem){
//alert("inside populate")
	document.getElementsByName("empIdChk")[0].value=selectedElem;
	document.getElementsByName("patIdNo")[0].readOnly=true;	
	//alert(document.getElementsByName("patIdNo")[0].value)
	//alert("document.getElementsByName("patIdNo")[0].value"+document.getElementsByName("patIdNo")[0].value)
	//submitForm("GETEMPDEPENDENTDTL");
}

function openPopup(url,eventObj, height, width)
{//alert("inside openpop"+eventObj.type)
if(eventObj.type=="change" || eventObj.keyCode==13 || eventObj.type=="click")
 {
   	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 

if(!child.opener)
   child.opener = self;
 }
 return child
}


/*function checkCategory(e){
	getExpiryDays()
	var categoryCode=document.getElementsByName("patPrimaryCatCode")[0].value
	if(categoryCode==<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>)
	{
		//alert('enter employee code');
		if(<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>==<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>)
			{
			openPopup('<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e,300,600);
			}
		if(<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>==<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_TRUE%>)
			{
			document.getElementById("showEmployee").style.display="block";
			document.getElementById("showVerificationDocument").style.display="none";
			}
		//alert("after open popup")
		//document.getElementById("showEmployee").style.display="block";
		//document.getElementById("showVerificationDocument").style.display="none";		
	}
	if(categoryCode!="-1" && categoryCode!=<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>) 
	{
		if(categoryCode==<%=Config.PRIMARY_CATEGORY_NORMAL_CODE%>)
		{
			//alert("categoryCode>>>>>"+categoryCode);
			document.getElementById("showEmployee").style.display="none";	
			document.getElementById("showVerificationDocument").style.display="none";
		}
		else
		{
			//alert('enter card ');
			document.getElementById("showEmployee").style.display="none";	
			document.getElementById("showVerificationDocument").style.display="block";
		}
			
	}
	if(categoryCode=="-1" ) 
	{
		//alert('enter nothing');
		document.getElementById("showEmployee").style.display="none";	
		document.getElementById("showVerificationDocument").style.display="none";
			
	}
	return true;
}*/

function checkCategory(e)
{
//alert("cjeckcategory")
	getExpiryDays()
	//alert("after expirydays")
	var catCodeWithIDReq=document.getElementsByName("patPrimaryCatCode")[0].value;
	var x=catCodeWithIDReq.split("#");
	var categoryCode=x[0];
	var idRequired=x[1];
	//alert("categoryCode>>>>>"+categoryCode+"idRequired>>>>>"+idRequired);
	if(categoryCode=="-1")
	{
		document.getElementById("showEmployee").style.display="none";	
		document.getElementById("showVerificationDocument").style.display="none";
	}
	else
	{
	//alert("categoryCode="+categoryCode)
		if(categoryCode==<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%> && idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES %>)
		{
		//alert("1")
			if(<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>==<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>)
				{
					document.getElementById("showEmployee").style.display="";	
					document.getElementById("showVerificationDocument").style.display="none";
				//	openPopup('<his:path src="/registration/employeeDtlWithDependents.cnt"/>',e,300,600);
				}
			if(<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>==<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_TRUE%>)
				{
					document.getElementById("showEmployee").style.display="block";
					document.getElementById("showVerificationDocument").style.display="none";
				}
		}
		if(categoryCode!=<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%> && idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES %>)
		{
		//alert("2")
			document.getElementById("showEmployee").style.display="none";	
			document.getElementById("showVerificationDocument").style.display="";
		}
		if(idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_NO %>)
		{
		//alert("3")
			document.getElementById("showEmployee").style.display="none";	
			document.getElementById("showVerificationDocument").style.display="none";
		}
	}
	
}
function getExpiryDays()
{
	//var categoryCode=document.getElementsByName("patPrimaryCatCode")[0].value
	var catCodeWithIDReq=document.getElementsByName("patPrimaryCatCode")[0].value;
	var x=catCodeWithIDReq.split("#");
	var categoryCode=x[0];
	//alert("categoryCode>>>"+categoryCode);
	var ExpiryDays;
	
	var priCatCodeAndExpiryDayObj=document.forms[0].primaryCatAndExpiryDay.value
	//alert("priCatCodeAndExpiryDayObj>>>"+priCatCodeAndExpiryDayObj);
	var arrayObj=priCatCodeAndExpiryDayObj.split(':')
	
	
	if((categoryCode!='-1')  )
	{
	var i=0
	while(i<arrayObj.length)
	{//alert("arrayObjvalue"+arrayObj[i])
		if(arrayObj[i].substring(0,arrayObj[i].indexOf('#'))==categoryCode)
		{
			ExpiryDays=arrayObj[i].substring(arrayObj[i].indexOf('#')+1,arrayObj[i].length)
			//alert(">>>>"+ExpiryDays);
			break
		}
		i++
	}
	//alert("ExpiryDays"+ExpiryDays)
	if(categoryCode==<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>)
	{
			document.getElementsByName("employeeValidUpto")[0].value=ExpiryDays
			//alert("document.getElementsByName("employeeValidUpto")[0].value"+document.getElementsByName("employeeValidUpto")[0].value)
	}
	if(categoryCode!=<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>) 
	{
		document.getElementsByName("cardValidUpto")[0].value=ExpiryDays
		//alert("document.getElementsByName("cardValidUpto")[0].value"+document.getElementsByName("cardValidUpto")[0].value)
	}
	//alert("employeeValidUpto"+document.getElementsByName("employeeValidUpto")[0].value)
	//alert("cardValidUpto"+document.getElementsByName("cardValidUpto")[0].value)
	}
}

function submitTile(mode){
	//alert("employeeValidUpto"+document.getElementsByName("employeeValidUpto")[0].value)
	//alert("cardValidUpto"+document.getElementsByName("cardValidUpto")[0].value)
	document.getElementsByName("hmode")[0].value = mode;
	document.forms[0].submit();
}
	
function callThisOnload(){
	focusCrNo();
}

function validateForSeniorCitizen(){
	
	//alert("validateForSeniorCitizen")
	var valid=true
	var age=document.getElementsByName("patAge")[0].value
	var categoryCode=document.getElementsByName("patPrimaryCatCode")[0].value
	//alert(categoryCode)
var seniorCitizenCodeString = '<%=Config.PRIMARY_CATEGORY_SENIOR_CITIZEN%>';
	
	var seniorCitizenAge = '<%=Config.SENIOR_CITIZEN_AGE%>';
	//alert(seniorCitizenAge);
	var seniorCitizenCode = seniorCitizenCodeString.split(",");
	for(i=0;i<seniorCitizenCode.length;i++)
	{
		if((categoryCode.split("#")[0])==seniorCitizenCode[i])
		{
			if(age<seniorCitizenAge){
				alert("Age is less than " + seniorCitizenAge + ".Cannot change category to Senior Citizen")
				valid=false;
			}else
			{
				valid=true;
			}
		}
	}
	
	return valid	
}

function clearForm(){
	document.getElementsByName("patPrimaryCatCode")[0].value="-1"
	checkCategory(document.getElementsByName("patPrimaryCatCode")[0].event)
	document.getElementsByName("remarks")[0].value=""
	document.getElementsByName("patIdNo")[0].value=""
	document.getElementsByName("cardNo")[0].value=""
	document.getElementsByName("issuingAuthority")[0].value=""
	document.getElementsByName("verificationDocument")[0].value="-1"



}

window.onload=function()
{
	focusCrNo();
	checkCategory(document.getElementsByName("patPrimaryCatCode")[0].event);
	//alert("inside onload");
}

function showVerDoc()
{
	var catCode=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[0];
	var idRequired=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[1];
	idRequired=<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES %>

	
	if(catCode!="-1" && idRequired==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES %>)
	{
		submitForm('VERDOC');
	}	
	//else
	//{
		//submitForm('VERDOC');
	//}
}

</script>

<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>

<his:TitleTag>
	<his:name>
		<bean:message key="changePatientPrimaryCategory" />
	</his:name>
	<b> <font size="2"
		face="Verdana, Arial, Helvetica, sans-serif">
	 </font> </b>
</his:TitleTag>


			
<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				varStatus = "New";%>
</his:statusNew>

<his:InputCrNoTag name="PrimaryCategoryChangeFB">
</his:InputCrNoTag>

<%if (!flagIsStatusNew) {

				%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<%-- 
<%if(session.getAttribute(RegistrationConfig.ARRAY_PRIMARY_CATEGORY_CHANGE_VOS)!=null){ %>
<his:SubTitleTag name="Category Change Detail">
</his:SubTitleTag>
				
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="previousCategory"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="newCategory"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="effectiveFrom"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="effectiveTo"/></b>
				</font>
				</div>
	  		</td>
		  	<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="remarks"/></b>
					</font>
					</div>
		  	</td>
	  		
	  		
	  		
	  	</tr>
	
	<logic:iterate id="primaryCatChangeVO" indexId="idx" name="<%=RegistrationConfig.ARRAY_PRIMARY_CATEGORY_CHANGE_VOS%>" type="hisglobal.vo.PrimaryCategoryChangeVO">
		<tr>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=primaryCatChangeVO.getPatPrevPrimaryCat() %>	
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<%= primaryCatChangeVO.getPatNewPrimaryCat()%>
	  			</div>
	  			
	  			
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=primaryCatChangeVO.getEffectiveFrom() %>	
	  			</div>
	  			
	  			
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<%=primaryCatChangeVO.getEffectiveTo() %>	
	  			</div>
	  			
	  			
	  		</td>
	  		<td class="tdfont">
		  		<div align="center">
		  		<%=primaryCatChangeVO.getRemarks() %>
	  			</div>
	  		</td>
	  		
	  		
	  	</tr>
	  
	 </logic:iterate>
	</table>
	</his:ContentTag>
	
	<%} %>
	--%>



<his:statusTransactionInProcess>
<!-- .......Code for Selection of Primary Category......... -->
<his:SubTitleTag name="Select Patient Category">
	<his:name>
		<bean:message key="selPrimCat"/>
	</his:name>
</his:SubTitleTag>

<his:ContentTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB">
	
	<tr>
		<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="primary" /> <bean:message key="category" /> </font></div>
				</td>

				

				<td width="75%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<%-- <html:select
					name="PrimaryCategoryChangeFB" property="patPrimaryCatCode"
					tabindex="1" styleClass="regcbo" >--%>
					<html:select
					name="PrimaryCategoryChangeFB" property="patPrimaryCatCode"
					tabindex="1" styleClass="regcbo" onchange="showVerDoc();checkCategory(event)"> 
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_LIST_EXCEPT_PATIENT_CATEGORY%>">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_LIST_EXCEPT_PATIENT_CATEGORY%>"
						property="value" labelProperty="label" />
						</logic:present>
				</html:select> </font></td>
			</tr>		
			<tr>	
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="remarks" /> </font></div>
				</td>

			
				<td width="75%" class="tdfont">
					<div align="left"> 
						<html:textarea name="PrimaryCategoryChangeFB" tabindex="1" property="remarks" rows="1" cols="80"  onkeypress="return (validateTextArea(event,this,'148') && validateAlphaNumericOnly(event,this))"></html:textarea>
					</div>
				</td>
	</tr>
	
	</table>
	
	<div id="showEmployee" style="display:none">
	<his:SubTitleTag name="Employee Id">
		</his:SubTitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB">
	
	<tr>
		<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="employeeid" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:text name="PrimaryCategoryChangeFB" tabindex="1"
							property="patIdNo" maxlength="16"
							styleClass="textboxBig" size="18" readonly="true"  />
							<img class="button"
						src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1"
						border="0" style="cursor:pointer" 
						onkeypress="if(event.keyCode==13) showEmployeepopup(event);" onclick="showEmployeepopup(event);" >
					 </font></td>
					 
					
				
				
				<!--<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="validUpto" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:text name="PrimaryCategoryChangeFB" tabindex="1"
							property="employeeValidUpto" maxlength="30"
							styleClass="textboxBig" size="20" readonly="true" />
					 </font></td>
	
	
	
		
	--></tr>
	
	</table>
	</his:ContentTag>
	</div>
	
	
	<div id="showVerificationDocument" style="display:none">
		<his:SubTitleTag name="Verification Document">
		</his:SubTitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#EBEBEB">
	
	<tr>
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patCardNo" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:text name="PrimaryCategoryChangeFB" tabindex="1"
							property="cardNo" maxlength="16"
							styleClass="textboxBig" size="20" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"/>
					 </font></td><!--
				
				
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="validUpto" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:text name="PrimaryCategoryChangeFB" tabindex="1"
							property="cardValidUpto" maxlength="30"
							styleClass="textboxBig" size="20" readonly="true" />
					 </font></td>
				
				--></tr>
				
	<tr>
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="issuingAuthority" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:text name="PrimaryCategoryChangeFB" tabindex="1"
							property="issuingAuthority" maxlength="30"
							styleClass="textboxBig" size="20" onkeypress="return validateAlphaNumericOnly(event,this)"/>
					 </font></td>
	
								
				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="verificationDocument" /> </font></div>
				</td>

				

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:select
					name="PrimaryCategoryChangeFB" property="verificationDocument"
					tabindex="1" styleClass="regcbo" > 
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%= RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS%>"> 
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
					 </font></td>
	</tr>
		
	</table>
	</his:ContentTag>
	</div>
		

</his:ContentTag>

<!-- ........End...Code for Selection of Primary Category....... -->

</his:statusTransactionInProcess>
<%}%>	


<!-- ...............Code for Button Tool Bar.......... -->
<his:ButtonToolBarTag>
	
	<his:statusInProcessWithJsp>
		<%
		varStatus="InProcess";
	%>
	</his:statusInProcessWithJsp>
	<%if(varStatus.equals("InProcess")){%>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) if (validatePrimaryCategoryChange()) submitTile('SAVE');"
		tabindex="1" onclick=" if (validatePrimaryCategoryChange()) submitTile('SAVE');">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
		onclick="submitForm('NEW');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="clearForm()"
		onkeypress="if(event.keyCode==13) clearForm();"></div>
	<%} else{ %>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} %>
</his:ButtonToolBarTag>
<!-- .......End........Code for Button Tool Bar.......... -->

<his:status />
<input type="hidden" name="hmode" value="unspecified" />	
<html:hidden name="PrimaryCategoryChangeFB" property="primaryCatAndExpiryDay"/>
<html:hidden name="PrimaryCategoryChangeFB" property="empIdChk"/>
<html:hidden name="PrimaryCategoryChangeFB" property="cardValidUpto"/>
<html:hidden name="PrimaryCategoryChangeFB" property="employeeValidUpto"/>
<html:hidden name="PrimaryCategoryChangeFB" property="patAge"/>
	

