<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.SummonInboxFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.SummonDetailVO"%>
<%@page import="java.util.List"%>
<%@page import="mrd.transaction.controller.fb.InsuranceClaimRecordEntryFB"%>
<%@page import="hisglobal.vo.InsuranceDetailVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<%@page import="mrd.MrdConfig"%>

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function doPagination(e,p)
{	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

function clearForm()
{
	document.getElementsByName("opinionBy")[0].value="-1";
	document.getElementsByName("opinionDate")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("sendingMode")[0].checked=false;
	document.getElementsByName("sendingMode")[1].checked=false;
	document.getElementsByName("opinionRemarks")[0].value="";
}

function validateForm(mode)
{
	if(document.getElementsByName("opinionBy")[0].value=="-1")
	{
		alert("Please select Opinion By");
		document.getElementsByName("opinionBy")[0].focus();
		return false;
	}
	
	var noOfday=noOfDays(document.getElementsByName("opinionDate")[0].value,document.getElementsByName("sysDate")[0].value);
	if(noOfday>0)
	{
		alert("Opinion Date can not be greater than Today Date");
		document.getElementsByName("opinionDate")[0].focus();
		return false;
	}
	
	var day=noOfDays(document.getElementsByName("opinionDate")[0].value,document.getElementsByName("receiveDateTime")[0].value.substring(0,12));
	if(day<0)
	{
		alert("Opinion Date can not be less than Received Date "+document.getElementsByName("receiveDateTime")[0].value.substring(0,12));
		document.getElementsByName("opinionDate")[0].focus();
		return false;
	}
		
	if(document.getElementsByName("sendingMode")[0].checked==false && document.getElementsByName("sendingMode")[1].checked==false)
	{
		alert("Please select Send To");
		document.getElementsByName("sendingMode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("opinionRemarks")[0].value=="")
	{
		alert("Please enter Opinion Remarks");
		document.getElementsByName("opinionRemarks")[0].focus();
		return false;
	}
	
	submitPage(mode);
}

function showInsuranceDetail(mode)
{
	submitPage(mode);
}
 
</script>
<body>
		

<his:TransactionContainer>
	<html:form action="/insuranceClaimRecordEntry">
	<his:TitleTag name="Insurance Claim Record Entry">
	</his:TitleTag>
	
	<logic:present name="<%=MrdConfig.INSURANCE_RECEIVED_DTL_LIST %>">
	<logic:notEmpty name="<%=MrdConfig.INSURANCE_RECEIVED_DTL_LIST %>">
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((InsuranceClaimRecordEntryFB)request.getAttribute("InsuranceClaimRecordEntryFB")).getCurrentPage());
				fbPage.setObjArrName(MrdConfig.INSURANCE_RECEIVED_DTL_LIST);
				fbPage.setAppendInTitle("Record");
				int maxRecord=15;
				fbPage.setMaxRecords(maxRecord);
		%>
		<html:hidden name="InsuranceClaimRecordEntryFB" property="currentPage"/>
		<his:PaginationTag name="fbPagination"></his:PaginationTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="5%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="select"/>
			  			</font>
			  		</div>
		      	</td>
				<td class="tdfonthead" width="15%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="policyNo"/>	
			  			</font>
			  		</div>
		      	</td>
				<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="companyName"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="15%" nowrap="nowrap">
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="receiveDate&Time"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="15%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="crNo"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="patName"/>
			  			</font>
			  		</div>
		      	</td>
		    </tr>
			
			<%
				List insuranceDetailVOLst=(List)session.getAttribute(MrdConfig.INSURANCE_RECEIVED_DTL_LIST );
						
				int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX));
				int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX));
						
				for(int j=startIndex;j<=endIndex;j++)
				{
					Integer ind=new Integer(j);
					int indd=ind;
					for(int i=0;i<maxRecord;i++)
					{
						if(indd%maxRecord==i) ind=i;
					}
							
					String index=ind.toString();
					InsuranceDetailVO insuranceDetailVO=(InsuranceDetailVO)insuranceDetailVOLst.get(j);
			%>
			<tr>
				<td  width="5%" class="tdfont">
				 	<div align="center">
				 		<html:radio  property="selectedInsuranceId" value="<%=insuranceDetailVO.getInsuranceId() %>" onclick="showInsuranceDetail('GETINSURANCEDTL')" ></html:radio>	
				 	</div>
				 </td>	
				 <td width="15%" class="tdfont">
				 	<div align="center">
				 		<%
				 			if(insuranceDetailVO.getPolicyNo()!=null)
				 			{
				 		%>
				 		<%=insuranceDetailVO.getPolicyNo() %>
				 		<%		
				 			}
				 		%>
				 	</div>
				 </td>	
				 <td width="20%" class="tdfont">
				 	<div align="center">
				 		<%
				 			if(insuranceDetailVO.getCompanyName()!=null)
				 			{
				 		%>
				 		<%=insuranceDetailVO.getCompanyName() %>
				 		<%		
				 			}
				 		%>
				 	</div>
				 </td>
				 <td width="15%" class="tdfont">
				 	<div align="center">
				 		<%
				 			if(insuranceDetailVO.getReceiveDateTime()!=null)
				 			{
				 		%>
				 		<%=insuranceDetailVO.getReceiveDateTime() %>
				 		<%		
				 			}
				 		%>
				 	</div>
				 </td>
				 <td width="15%" class="tdfont">
				 	<div align="center">
				 		<%
				 			if(insuranceDetailVO.getPatCrNo()!=null)
				 			{
				 		%>
				 		<%=insuranceDetailVO.getPatCrNo() %>
				 		<%		
				 			}
				 		%>
				 	</div>
				 </td>
				 <td width="20%" class="tdfont">
				 	<div align="center">
				 		<%
				 			if(insuranceDetailVO.getPatName()!=null)
				 			{
				 		%>
				 		<%=insuranceDetailVO.getPatName() %>
				 		<%		
				 			}
				 		%>
				 	</div>
				 </td>
			</tr>			
			<%
				}
			%>
		</table>
		</logic:notEmpty>
		</logic:present>
		
		<logic:present name="<%=MrdConfig.SELECTED_INSURANCE_DETAIL_VO %>">
		<logic:notEmpty name="<%=MrdConfig.SELECTED_INSURANCE_DETAIL_VO %>">
		<%
			InsuranceDetailVO insuranceDetailVO=(InsuranceDetailVO)session.getAttribute(MrdConfig.SELECTED_INSURANCE_DETAIL_VO);
		%>	
		
		<his:SubTitleTag name="Insurance Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="policyNo"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont">
					<div align="left">
						<%
							if(insuranceDetailVO.getPolicyNo()!=null)
							{
						%>
						<%=insuranceDetailVO.getPolicyNo() %>
						<%		
							}
						%>
					</div>
				</td>
				<td class="tdfonthead" width="25%" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="companyName"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont">
					<div align="left">
						<%
							if(insuranceDetailVO.getCompanyName()!=null)
							{
						%>
						<%=insuranceDetailVO.getCompanyName() %>
						<%		
							}
						%>
					</div>
				</td>
		    </tr>
			<tr>
				<td class="tdfonthead" width="25%" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="receiveDate&Time"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont">
					<div align="left">
						<%
							if(insuranceDetailVO.getReceiveDateTime()!=null)
							{
						%>
						<%=insuranceDetailVO.getReceiveDateTime() %>
						<%		
							}
						%>
					</div>
				</td>
				<td class="tdfonthead" width="25%" nowrap="nowrap">
			  	</td>
		      	<td width="25%" class="tdfont">
				</td>
		    </tr>
	   </table>	       	
		</his:ContentTag>
		
		<his:SubTitleTag name="Patient Detail">
		</his:SubTitleTag>
		<his:ContentTag>
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
				 		<%
							if(insuranceDetailVO.getPatName()!=null)
							{
						%>
						<%=insuranceDetailVO.getPatName() %>
						<%		
							}
						%>
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
				 		<%
							if(insuranceDetailVO.getPatGender()!=null && insuranceDetailVO.getPatAge()!=null)
							{
						%>
						<%=insuranceDetailVO.getPatGender() %>/<%=insuranceDetailVO.getPatAge() %>
						<%		
							}
						%>
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
				 		<%
							if(insuranceDetailVO.getPatCrNo()!=null)
							{
						%>
						<%=insuranceDetailVO.getPatCrNo() %>
						<%		
							}
						%>
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
				 		<%
							if(insuranceDetailVO.getPatAdmNo()!=null)
							{
						%>
						<%=insuranceDetailVO.getPatAdmNo() %>
						<%		
							}
						%>
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
				 		<%=insuranceDetailVO.getDeptName()+"("+insuranceDetailVO.getUnitName()+")" %>
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
				 		<%
							if(insuranceDetailVO.getAdmDate()!=null)
							{
						%>
						<%=insuranceDetailVO.getAdmDate() %>
						<%		
							}
						%>
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
				 		<%
							if(insuranceDetailVO.getDischargeDate()!=null)
							{
						%>
						<%=insuranceDetailVO.getDischargeDate() %>
						<%		
							}
						%>
				 	</div>
				</td>
				<td class="tdfonthead" width="25%" >
				</td>
				<td class="tdfont" width="25%" >
				</td>
			</tr>
		</table>	 
		</his:ContentTag>
		
		<his:SubTitleTag name="Opinion Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="opinionBy"/>
			 			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:select name="InsuranceClaimRecordEntryFB" property="opinionBy" tabindex="1" > 
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MrdConfig.LIST_OF_EMP_DOCTOR %>">
								<html:options collection="<%=MrdConfig.LIST_OF_EMP_DOCTOR%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="opinionDate"/>
			 			</font>
			  		</div>
		      	</td>
		      	<td class="tdfont" width="25%" >
					<div align="left">
				 		<bean:define id="sysdateId" name="InsuranceClaimRecordEntryFB" property="sysDate" type="java.lang.String"></bean:define>
				 		<his:date name="opinionDate" dateFormate="%d-%b-%Y" value="<%=sysdateId %>"></his:date>
				 	</div>
				 </td>
			</tr>
			<tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="sendTo"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<div align="left">	
						<font color="#000000">Company<html:radio property="sendingMode" value="<%=MrdConfig.SENDING_MODE_COMPANY %>" tabindex='1' name="InsuranceClaimRecordEntryFB"></html:radio></font>      
						<font color="#000000">Patient/Relative<html:radio property="sendingMode" value="<%=MrdConfig.SENDING_MODE_PATIENT_OR_RELATIVE %>" tabindex='1' name="InsuranceClaimRecordEntryFB"></html:radio></font>
					</div>
				</td>
			</tr>
			<tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="opinionRemarks"/>
			 			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="InsuranceClaimRecordEntryFB" tabindex="1" rows="2" cols="95" property="opinionRemarks" onkeypress="return (validateTextArea(event,this,'500'))">
						</html:textarea>
					</font>
				</td>
			</tr>
		</table>		 
		</his:ContentTag>
		</logic:notEmpty>
		</logic:present>
		
		
		<his:ButtonToolBarTag>
			<his:statusDone>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			</his:statusDone>
			<his:statusList>
				<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm();" onkeypress="if(event.keyCode==13) clearForm();">
			</his:statusList>
		</his:ButtonToolBarTag>
	
		<html:hidden name="InsuranceClaimRecordEntryFB" property="hmode" />
		<html:hidden name="InsuranceClaimRecordEntryFB" property="sysDate" />
		<html:hidden name="InsuranceClaimRecordEntryFB" property="insuranceId" />
		<html:hidden name="InsuranceClaimRecordEntryFB" property="receiveDateTime" />
</html:form>

<his:status/>
</his:TransactionContainer>
</body>

</html>