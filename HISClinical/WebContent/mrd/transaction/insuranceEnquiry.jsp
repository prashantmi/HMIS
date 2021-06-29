<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function submitSearch()
{
	if(document.forms[0].patFirstName.value!="" ||
	document.forms[0].patMiddleName.value!="" ||
	document.forms[0].patLastName.value!="" ||
	document.forms[0].patCrNo.value!="" ||
	document.forms[0].companyId.value!="-1" ||
	document.forms[0].policyNo.value!="")
	{
		document.getElementsByName("hmode")[0].value='SEARCH';
		document.forms[0].submit();
	}
	else
	{
		alert("Please Enter Value In Atleast One Field")
	}
}

function getDetail(obj)
{
	document.getElementsByName("selectedIndex")[0].value=obj;
	
	document.getElementsByName("hmode")[0].value='GETDETAIL';
	document.forms[0].submit();
	//submitPage('GETDETAIL');
}



</script>

<body>
	<html:form action="/insuranceEnquiry">
		<his:TransactionContainer>
			<his:TitleTag name="Insurance Enquiry">
			</his:TitleTag>
		    <his:statusNew>
		    <his:SubTitleTag name="">
				<tr>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="firstName"/>
								</B>
							</font>
						</div>	
					</td>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="middleName"/>
								</B>
							</font>
						</div>
					</td>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="lastName"/>
								</B>
							</font>
						</div>	
					</td>
				</tr>
			</his:SubTitleTag>
			
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="InsuranceEnquiryFB" property="patFirstName" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="InsuranceEnquiryFB" property="patMiddleName" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="InsuranceEnquiryFB" property="patLastName" tabindex="1" onkeypress="return validateAlphabetsWithDotsOnly(event,this)"/>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			
			<his:SubTitleTag name="">
				<tr>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="crNo"/>
								</B>
							</font>
						</div>	
					</td>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="companyName"/>
								</B>
							</font>
						</div>
					</td>
					<td width="30%" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="policyNo"/>
								</B>
							</font>
						</div>	
					</td>
				</tr>
			</his:SubTitleTag>
			
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="InsuranceEnquiryFB" property="patCrNo" tabindex="1" maxlength="13" onkeypress="return validateNumeric(event)"/>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:select name="InsuranceEnquiryFB" property="companyId" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ALL_INSURANCE_COMPANY_LIST %>">
										<html:options collection="<%=MrdConfig.ALL_INSURANCE_COMPANY_LIST%>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<html:text name="InsuranceEnquiryFB" property="policyNo" tabindex="1" maxlength="20" onkeypress="return validateAlphaNumOnly(this,event)"/>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			 
			
			
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' tabindex="1" style="cursor:pointer" alt="Search" title="Search" onclick="submitSearch()" onkeypress="if(event.keyCode==13) submitSearch();" >
			 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('CANCEL');" onkeypress="if(event.keyCode==13) submitForm('CANCEL');"  >
		 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</his:ButtonToolBarTag>
		</his:statusNew>
		<his:statusList>
		<logic:present name="<%=MrdConfig.INSURANCE_DETAIL_VO_LIST_FOR_SEARCH %>">
		<logic:notEmpty name="<%=MrdConfig.INSURANCE_DETAIL_VO_LIST_FOR_SEARCH %>">
		<his:ContentTag>
		<his:SubTitleTag name="Search Detail">
		</his:SubTitleTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
				<td class="tdfonthead" width="15%">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="name"/>
						</font>	
					</div>	
				</td>
				<td class="tdfonthead" width="15%">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="gender/age"/>
						</font>	
					</div>	
				</td>
				<td class="tdfonthead" width="20%">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="companyName"/>
						</font>	
					</div>	
				</td>
				<td class="tdfonthead" width="15%">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="policyNo"/>
						</font>	
					</div>	
				</td>
				<td class="tdfonthead" width="15%">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="status"/>
						</font>	
					</div>	
				</td>
				
			</tr>
			<logic:iterate id="insuranceDetailVO" indexId="i" name="<%=MrdConfig.INSURANCE_DETAIL_VO_LIST_FOR_SEARCH %>" type="hisglobal.vo.InsuranceDetailVO">
			<%String idx=i.toString(); %>
			<tr>
			<td class="tdfont" width="15%" >
					 	<div align="center">
					 		 <a style="cursor:pointer" onclick="getDetail('<%=idx%>')" >
					 			<bean:write name="insuranceDetailVO" property="patName"/>
					 		</a>
					 	</div>
				 	</td>
				 <td class="tdfont" width="15%" >
					 	<div align="center">
					 		<font color="#000000">
					 			<bean:write name="insuranceDetailVO" property="patGender"/>/
					 			<bean:write name="insuranceDetailVO" property="patAge"/>
					 		</font>
					 	</div>
				 	</td>	
				 			
				 <td class="tdfont" width="20%" >
					 	<div align="center">
					 		<font color="#000000">
					 			<bean:write name="insuranceDetailVO" property="companyName"/>
					 		</font>
					 	</div>
				 	</td>
				 
				<td class="tdfont" width="15%" >
					 	<div align="center">
					 		<font color="#000000">
					 			<bean:write name="insuranceDetailVO" property="policyNo"/>
					 		</font>
					 	</div>
				 	</td>
				 <td class="tdfont" width="15%" >
					 	<div align="center">
					 		<font color="#000000">
					 			<bean:write name="insuranceDetailVO" property="status"/>
					 		</font>	
					 	</div>
				</td> 
			</tr>	
			</logic:iterate>
		</table>	
		</his:ContentTag>
		</logic:notEmpty>
		</logic:present>	
		</his:statusList>
			
		<his:statusTransactionInProcess>
			
			<logic:present name="<%=MrdConfig.INSURANCE_DETAIL_VO_FOR_DETAIL %>">
			<his:SubTitleTag name="Patient Detail">
			</his:SubTitleTag>
			<his:ContentTag>
			
			<logic:iterate id="insuranceDetailVO" indexId="i" name="<%=MrdConfig.INSURANCE_DETAIL_VO_FOR_DETAIL %>" type="hisglobal.vo.InsuranceDetailVO">	
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="patientName"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="patName"/>
				 		</font>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="genderAge"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" nowrap="nowrap">
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="patGender"/>/
					 			<bean:write name="insuranceDetailVO" property="patAge"/>
					 	</font>	
				 	</div>
				  </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="crNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="patCrNo"/>
				 		</font>	
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="admissionNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="patAdmNo"/>
				 		</font>	
				 	</div>
				 </td>
				 
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="admissionDeptUnit"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="deptName"/>( <bean:write name="insuranceDetailVO" property="unitName"/> )
				 		</font>
				 	</div>
				 </td>
				
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="admissionDate"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="admDate"/>
				 		</font>
				 	</div>
				 </td>
			</tr>
			<tr>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="dischargeDate"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="dischargeDate"/>
				 		</font>
				 	</div>
				 </td>
				 
				  <td class="tdfonthead" width="25%" >
				  </td>
				  <td class="tdfont" width="25%" >
				  </td>
				  
			</tr>
		</table>	
			<his:SubTitleTag name="Insurance Detail">
			</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="receivingDate&Time"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="receiveDateTime"/>
				 		</font>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="fileTrackingNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="CIDNo"/>
				 		</font>
				 	</div>
				 </td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="companyName"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="companyName"/>
				 		</font>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="policyNo"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<font color="#000000">
				 			<bean:write name="insuranceDetailVO" property="policyNo"/>
				 		</font>
				 	</div>
				 </td>
			</tr>
			
		   
		</table>
		</logic:iterate>
		</his:ContentTag>
		</logic:present>
		
		<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('SEARCH');" onkeypress="if(event.keyCode==13) submitForm('SEARCH');"  >
		</his:ButtonToolBarTag>
		<html:hidden name="InsuranceEnquiryFB" property="patFirstName"/>
		<html:hidden name="InsuranceEnquiryFB" property="patMiddleName"/>
		<html:hidden name="InsuranceEnquiryFB" property="patLastName"/>
		<html:hidden name="InsuranceEnquiryFB" property="patCrNo"/>
		<html:hidden name="InsuranceEnquiryFB" property="companyId"/>
		<html:hidden name="InsuranceEnquiryFB" property="policyNo"/>
		</his:statusTransactionInProcess>		
			
		</his:TransactionContainer>
		
		
		
		<html:hidden name="InsuranceEnquiryFB" property="hmode"/>
		<html:hidden name="InsuranceEnquiryFB" property="selectedIndex"/>
		
		
		<his:status/>
	</html:form>
</body>

</html>