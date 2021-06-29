<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:22-Nov-2014 

 -->
 <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.transaction.controller.fb.MedicalCertificateFB"%>
<%@page import="hisglobal.vo.PatMedicalDtlVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


</script>

<html:form action="/medicalCertificate">
<body>  
	<his:TitleTag name="Medical and Fitness Certificate Issue">	
	</his:TitleTag>
	<his:SubTitleTag name="Medical and Fitness Requests">
	</his:SubTitleTag>
		<% 	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination",fbPage);
			System.out.print("adsadadd:::"+((MedicalCertificateFB)request.getAttribute("medicalCertificateFB")).getCurrentPage());
			fbPage.setCurrentPage(((MedicalCertificateFB)request.getAttribute("medicalCertificateFB")).getCurrentPage());
			fbPage.setObjArrName(MrdConfig.MEDICAL_CERTIFICATE_PAT_REQ_DTL);
			fbPage.setAppendInTitle("Records");
			fbPage.setMaxRecords(10);
		%>
	
    <logic:present name="<%=MrdConfig.MEDICAL_CERTIFICATE_PAT_REQ_DTL%>">
    		<his:PaginationTag name="fbPagination"></his:PaginationTag>		
    	<his:ContentTag>
    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="crNo"/>
								</b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="patientName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagonosis"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="duration"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="requestedDate"/>
								</b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="advisedBy"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="certificateType"/>
								</b>
							</font>
						</div>
					</td>
				
				</tr>
				<logic:notEmpty name="<%=MrdConfig.MEDICAL_CERTIFICATE_PAT_REQ_DTL %>">
					<% String str="";
					PatMedicalDtlVO[] medicalCertificateRequestVo=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.MEDICAL_CERTIFICATE_PAT_REQ_DTL);
					
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					String fontCss="";
					boolean disabled=false;
					for(int i=startIndex;i<=endIndex;i++)
					{
						PatMedicalDtlVO voMedicalCertificateRequest =(PatMedicalDtlVO)medicalCertificateRequestVo[i];
						
						str=voMedicalCertificateRequest.getCertificateId()+"@"+voMedicalCertificateRequest.getRecordType()+"@"+voMedicalCertificateRequest.getPatCrNo()+"@"+voMedicalCertificateRequest.getDiagnosis()+"@"
							+voMedicalCertificateRequest.getSurgery()+"@"+voMedicalCertificateRequest.getAdviceDays()+"@"+voMedicalCertificateRequest.getFitnessDate()+"@"+voMedicalCertificateRequest.getAdvisedBy()+"@"
								+voMedicalCertificateRequest.getDuration()+"@"+voMedicalCertificateRequest.getRemarks()+"@"+voMedicalCertificateRequest.getFromDate()+"@"+voMedicalCertificateRequest.getToDate()+"@"
							+voMedicalCertificateRequest.getPreviousMcNo()+"@"+voMedicalCertificateRequest.getGdt_entry_date()+"@"+voMedicalCertificateRequest.getEmpDesig()+"@"+voMedicalCertificateRequest.getEmp_dept();
						
					%>
				
						<tr>
							    
							
							
							<td width="5%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="medicalCertificateFB" property="selectRecord" value='<%=str %>' onclick="submitPage('GETDTL')" ></html:radio>
								</font>
							</div>
							</td>													
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getPatCrNo() %>
									</font>
								</div>	
							</td>
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getStrPatName() %>
									</font>
								</div>	
							</td>
							<td width="15%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getDiagnosis() %>
									</font>
								</div>	
							</td>
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getDuration() %>
									</font>
								</div>	
							</td>
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getRequestDate() %>
									</font>
								</div>	
							</td>
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getAdvisedBy() %>
									</font>
								</div>	
							</td>
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=voMedicalCertificateRequest.getCertificateType() %>
									</font>
								</div>	
							</td>
							
									
									</tr>
									<%} %>
						
					</logic:notEmpty>
					<logic:empty name="<%=MrdConfig.MEDICAL_CERTIFICATE_PAT_REQ_DTL %>">
						<tr>
							<div align="center">
								<td colspan="5">
								No Record Found							
								</td>
							<div align="left">
						</tr>
					</logic:empty>
					
			</table>		
    	</his:ContentTag>
    </logic:present>	
    	<his:ButtonToolBarTag>					
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
    	</his:ButtonToolBarTag>    
    <html:hidden name="medicalCertificateFB" property="hmode"/>
     <html:hidden name="medicalCertificateFB" property="currentPage"/>
      <html:hidden name="medicalCertificateFB" property="selectRecord"/>
     
</body>
<his:status/>
</html:form>    