
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.PatientVO"%>
<%@page import="java.util.List"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
window.onload=function(){
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
}

function validateAdditionOfMergedCRNumber()
{
	var mainCRNo=document.getElementsByName("patCrNo")[0].value;
	var notUsedCRNo=document.getElementsByName("patNotUsedCrNo")[0].value;
	document.getElementsByName("concatedCrNo")[0].value="";
	
		if(mainCRNo==notUsedCRNo)
		{
			alert("You Cannot Add This CR Number");
			document.getElementsByName("patNotUsedCrNo")[0].value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>";
			document.getElementsByName("patNotUsedCrNo")[0].focus();
			return false;
		}
		else 
		{
			for(var i=0;i<document.getElementsByName("hiddenNotUsedCRNo").length;i++)
			{
				if(notUsedCRNo==document.getElementsByName("hiddenNotUsedCRNo")[i].value)
				{
					alert(notUsedCRNo + " is Already Added")
					document.getElementsByName("patNotUsedCrNo")[0].focus();
					document.getElementsByName("patNotUsedCrNo")[0].value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>";
					return false;
				}
			}
			
			for(var i=0;i<document.getElementsByName("hiddenMergedCRNo").length;i++)
			{
				if(notUsedCRNo==document.getElementsByName("hiddenMergedCRNo")[i].value)
				{
					alert(notUsedCRNo + " is Already Merged")
					document.getElementsByName("patNotUsedCrNo")[0].focus();
					document.getElementsByName("patNotUsedCrNo")[0].value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>";
					return false;
				}
			}
		}
	return true;
} 

function validateCRNumber(size)
{
	var valid=true;
	if(validateMinLength(document.getElementsByName('patNotUsedCrNo')[0],size) &&
		validateCheckSum(size))
	{
		valid=true
	}
	else
	{	
		alert("invalid Cr no")
		valid=false
	}

	return valid
}

function validateCheckSum(size)
{
	var valid=true

	if(size==12)
	{
		valid=checkSum12()
	}
	if(size==13)
	{
		valid=checkSum13()
	}
	return valid
}

//////////////////////for 12 digit cr no
function checkSum12(){
	
	var crNum  = document.getElementsByName("patNotUsedCrNo")[0].value;
	var checkSum = 0;
	var power  = 12;
		 
	for(var i=0;i<crNum.length-1;i++)
		 
	checkSum += parseInt(crNum.substring(i,i+1)* power--);
		
	checkSum=checkSum%11;
	if((checkSum!=0 && checkSum!=1))
		checkSum=11-checkSum;
	if(checkSum==crNum.substring(11))
		return true;
	else
	{
	//  alert("InValid CR No");
		return false;
	}
}
	
/////////////////////For 13 digit cr no
function checkSum13()
{
	var isValid = true;
	crNo=document.getElementsByName("patNotUsedCrNo")[0].value;
			
	modulo_1=crNo.substring(0,1)*7 + crNo.substring(1,2)*6 + 
			crNo.substring(2,3)*5 + crNo.substring(3,4)*4 +  
			crNo.substring(4,5)*3 + crNo.substring(5,6)*2 +  
			crNo.substring(6,7)*7 + crNo.substring(7,8)*6 +
			crNo.substring(8,9)*5 + crNo.substring(9,10)*4 +  
			crNo.substring(10,11)*3 + crNo.substring(11,12)*2 ;

	modulo=modulo_1 % 11;
		checksum = (modulo==0)?1:(11-modulo)%10;
	if(checksum==crNo.charAt(crNo.length-1)){
		isValid = true;
	}
		else
	{
		//alert("InValid CR No");
		isValid = false;
	}
	return isValid;
}

function deleteRow(obj)
{
	document.getElementsByName("deleteCrNo")[0].value=obj;
	submitForm('DELETEROW')
}

function openSearchPopup(event)
{
	var path='/HISClinical/mrd/crNoMerged.cnt?hmode=POPUP&patCrNo='+document.getElementsByName("patCrNo")[0].value;
	openPopup(createFHashAjaxQuery(path),event,400,800);
}

function revokePatientCRNo(crNo,index)
{
	document.getElementsByName("revokedCrNo")[0].value=crNo;
	if(document.getElementsByName("reason")[index].value=="")
	{
		alert("Please Enter The Revoke Reason");
		document.getElementsByName("reason")[index].focus();
		return false;
	}
	submitForm('REVOKE')
}

function validateSave()
{
	if(typeof document.getElementsByName("hiddenNotUsedCRNo")[0] == 'undefined')
	{
		alert("At Least Add a CR Number To Merged");
		document.getElementsByName("patNotUsedCrNo")[0].focus();
		return false;
	}
	return true;
}
</script>

<body>
	<html:form action="/crNoMerged">
		<his:TransactionContainer>
			<his:TitleTag name="Merging of CR Number">
			</his:TitleTag>
			
			<his:statusNew>
				<logic:empty name="CrNoMergeDtlFB" property="patCrNo" >
					<his:InputCrNoTag name="CrNoMergeDtlFB"></his:InputCrNoTag>
				</logic:empty>
			</his:statusNew>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/registration/patientDetail.cnt" flush="true" />
				<%if(session.getAttribute(MrdConfig.ARR_MERGED_PATIENT)!=null);
					List lstMergedPat=(List)session.getAttribute(MrdConfig.ARR_MERGED_PATIENT);
					if(lstMergedPat.size()>0){
					%>
					<his:SubTitleTag name="Merged Patient CR Number">
					</his:SubTitleTag>
					
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" >
							<tr>
								<td width="12%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="crNumber"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="17%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="name"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="10%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="age" />
												<bean:message key="slash" />
												<bean:message key="gender" />
											</b>	
										</font>
									</div>
								</td>
								<td width="13%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="fatherName"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="30%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="address"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="3%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<font color="#FF0000">*</font>
												<bean:message key="reason"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="5%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="revoke"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate id="patDtlVO" name="<%=MrdConfig.ARR_MERGED_PATIENT %>" type="hisglobal.vo.PatientVO" indexId="idx">
								<tr>
									<td width="12%" class="tdfont">
										<div align="center">
											<%=patDtlVO.getPatCrNo() %>
											<html:hidden name="CrNoMergeDtlFB" property="hiddenMergedCRNo" value="<%=patDtlVO.getPatCrNo() %>"/>
										</div>
									</td>
									<td width="17%" class="tdfont">
										<div align="center">
											<%=(patDtlVO.getPatFirstName()==null)?"":patDtlVO.getPatFirstName() %>
											<%=(patDtlVO.getPatMiddleName()==null)?"":patDtlVO.getPatMiddleName() %>
											<%=(patDtlVO.getPatLastName()==null)?"":patDtlVO.getPatLastName()%>
										</div>
									</td>
									<td width="10%" class="tdfont">
										<div align="center">
											<%=patDtlVO.getPatAge() %>/
											<%=patDtlVO.getPatGender()%>
										</div>
									</td>
									<td width="13%" class="tdfont">
										<div align="center">
											<%=(patDtlVO.getPatGuardianName()==null)?"":patDtlVO.getPatGuardianName()%>
										</div>
									</td>
									<td width="30%" class="tdfont">
										<div align="center">
											<%=(patDtlVO.getPatAddHNo()==null)?"":patDtlVO.getPatAddHNo() %>
											<%=(patDtlVO.getPatAddCityLoc()==null)?"":patDtlVO.getPatAddCityLoc() %>
											<%=(patDtlVO.getPatAddCity()==null)?"":patDtlVO.getPatAddCity() %>
											<%=(patDtlVO.getPatAddDistrict()==null)?"":patDtlVO.getPatAddDistrict()%>
										</div>
									</td>
									<td width="13%" class="tdfont">
										<div align="center">
											<html:text name="CrNoMergeDtlFB" property="reason" maxlength="50" onkeypress="return validateAlphaNumericWithDotsOnly(event)"></html:text>
										</div>
									</td>
									<td width="5%" class="tdfont">
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/Minus.png"/>'  onkeypress="if(event.keyCode==13) revokePatientCRNo(<%=patDtlVO.getPatCrNo() %>,<%=idx.toString() %>) " onclick="revokePatientCRNo(<%=patDtlVO.getPatCrNo() %>,<%=idx.toString() %>)  " tabindex="1">
										</div>
									</td>
												
								</tr>
							</logic:iterate>
						</table>
					</his:ContentTag>		
					<%} %>
				<his:SubTitleTag name="CR No To Be Merged">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="17%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="crNumber"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="17%" class="tdfont">
								<div align="left">
									 <%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
								 	<html:text name="CrNoMergeDtlFB" property="patNotUsedCrNo" tabindex="1" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>" value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>" onkeypress="return validateNumeric(event)">
									</html:text>
								</div>
							</td>
							<td width="49%" class="tdfont">
								<div align="left">
									<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateCRNumber('<%=Config.CR_NO_FORMAT_SIZE%>'))if(validateAdditionOfMergedCRNumber()) submitForm('GO')" onkeypress="if(event.keyCode==13) if(validateCRNumber('<%=Config.CR_NO_FORMAT_SIZE%>'))if(validateAdditionOfMergedCRNumber()) submitForm('GO')" >
								</div>
							</td>
							<td width="17%" class="tdfont">
								<div align="center">
									<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style=cursor:pointer tabindex="1" onclick="openSearchPopup(event) " onkeypress="if(event.keyCode==13) openSearchPopup(event)">
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<%
					if(session.getAttribute(MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO)!=null)
					{
					List lst=(List)session.getAttribute(MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
					if(lst.size()>0){
				%>
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" >
							<tr>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="crNumber"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="name"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="10%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="age" />
												<bean:message key="slash" />
												<bean:message key="gender" />
											</b>	
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="fatherName"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="address"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="5%" class="tdfonthead">
									<div align="center">
									
									</div>
								</td>	
							</tr>
							<logic:iterate id="patDtlVO" name="<%=MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO %>" type="hisglobal.vo.PatientVO">
								<tr>
									<td width="15%" class="tdfont">
										<div align="center">
											<%=patDtlVO.getPatCrNo() %>
											<html:hidden name="CrNoMergeDtlFB" property="hiddenNotUsedCRNo" value="<%=patDtlVO.getPatCrNo() %>"/>
										</div>
									</td>
									<td width="20%" class="tdfont">
										<div align="center">
											<%=(patDtlVO.getPatFirstName()==null)?"":patDtlVO.getPatFirstName() %>
											<%=(patDtlVO.getPatMiddleName()==null)?"":patDtlVO.getPatMiddleName() %>
											<%=(patDtlVO.getPatLastName()==null)?"":patDtlVO.getPatLastName()%>
										</div>
									</td>
									<td width="10%" class="tdfont">
										<div align="center">
											<%=patDtlVO.getPatAge() %>/
											<%=patDtlVO.getPatGender()%>
										</div>
									</td>
									<td width="15%" class="tdfont">
										<div align="center">
											<%=(patDtlVO.getPatGuardianName()==null)?"":patDtlVO.getPatGuardianName()%>
										</div>
									</td>
									<td width="35%" class="tdfont">
										<div align="center">
											<%=(patDtlVO.getPatAddHNo()==null)?"":patDtlVO.getPatAddHNo() %>
											<%=(patDtlVO.getPatAddCityLoc()==null)?"":patDtlVO.getPatAddCityLoc() %>
											<%=(patDtlVO.getPatAddCity()==null)?"":patDtlVO.getPatAddCity() %>
											<%=(patDtlVO.getPatAddDistrict()==null)?"":patDtlVO.getPatAddDistrict()%>
										</div>
									</td>
									<td width="5%" class="tdfont">
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow(<%=patDtlVO.getPatCrNo() %>) " onclick="deleteRow(<%=patDtlVO.getPatCrNo() %>)  " tabindex="1">
										</div>
									</td>			
								</tr>
							</logic:iterate>
						</table>
					</his:ContentTag>
				<%}} %>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="15%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="remarks"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="85%" class="tdfont">
								<div align="left">
									<html:text name="CrNoMergeDtlFB" property="remarks" tabindex="1" maxlength="50" size="35" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)"></html:text>
								</div>
							</td>
						</tr>
					</table>
							
				</his:ContentTag>
				
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusNew>		
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('CANCEL')" onkeypress="if(event.keyCode==13)submitForm('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusNew>
				
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusTransactionInProcess>
				
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
				</his:statusUnsuccessfull>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>
		
		
		
		<html:hidden name="CrNoMergeDtlFB" property="hmode" value="unspecified"/>
		<html:hidden name="CrNoMergeDtlFB" property="patCrNo"/>
		<html:hidden name="CrNoMergeDtlFB" property="deleteCrNo"/>
		<html:hidden name="CrNoMergeDtlFB" property="revokedCrNo"/>
		<html:hidden name="CrNoMergeDtlFB" property="concatedCrNo" value=""/>
		
	</html:form>
	<his:status/>
</body>
</html>
