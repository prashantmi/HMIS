<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Manisha Gangwar
 ## Module Name					: MRD
 ## Process/Database Object Name:Certificate B Entry Form
 ## Purpose						:Certificate B View Requests
 ## Date of Creation			:20-Jan-2015 

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
<%@page import="mrd.vo.CertificateBEntryFormReqVO"%>
<%@page import="mrd.transaction.controller.fb.CertificateBEntryFormFB;"%>
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
<script type="text/javascript">

function submitPage(mode)
{
	var elmt=document.getElementsByName("hmode")[0];  
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

<html:form action="/certificateBEntryForm">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>  
	<his:TitleTag name="Certificate B Entry Form">	
	</his:TitleTag>
	<his:SubTitleTag name="Certificate B Entry Form Requests">
	</his:SubTitleTag>
		<% 	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination",fbPage);
			System.out.print("adsadadd:::"+((CertificateBEntryFormFB)request.getAttribute("CertificateBEntryFormFB")).getCurrentPage());
			fbPage.setCurrentPage(((CertificateBEntryFormFB)request.getAttribute("CertificateBEntryFormFB")).getCurrentPage());
			fbPage.setObjArrName(MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST);
			fbPage.setAppendInTitle("Records");
			fbPage.setMaxRecords(10);
		%>
	
    <logic:present name="<%=MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST%>">
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
									<bean:message key="patCrno"/>
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
					
				
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="requestedDate"/>
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="approvedBy"/>
								</b>
							</font>
						</div>
					</td>
				
				</tr>
				<logic:notEmpty name="<%=MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST%>">
					
					<% String str="";
					CertificateBEntryFormReqVO[] certBRequestVo=(CertificateBEntryFormReqVO[])session.getAttribute(MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST);
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					String fontCss="";
					boolean disabled=false;
					for(int i=startIndex;i<=endIndex;i++)
					{
						CertificateBEntryFormReqVO vocertBRequest =(CertificateBEntryFormReqVO)certBRequestVo[i];
						
					/* 	str=vocertBRequest.getPatCrNo()+"@"+vocertBRequest.getPatName()+"@"+vocertBRequest.getEntryDate()+"@"+vocertBRequest.getApprovedBy()+"@"+vocertBRequest.getRemarks()+"@"+vocertBRequest.getCertificateId();
					 */
						str=vocertBRequest.getCertificateId();
					%>
													
								
						<tr>
							<td width="5%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="CertificateBEntryFormFB" property="selectRecord" value='<%=str %>' onclick="submitPage('GETDTL')" ></html:radio>
								</font>
							</div>
							</td>	
							
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=vocertBRequest.getPatCrNo() %>
									</font>
								</div>	
							</td>
																			
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=vocertBRequest.getPatName() %>
									</font>
								</div>	
							</td>
							
												
							<td width="10%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=vocertBRequest.getEntryDate() %>
									</font>
								</div>	
							</td>
							
							<td width="20%" class="tdfonthead">
								<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=vocertBRequest.getApprovedBy() %>
									</font>
								</div>	
							</td>
							
							
									<%} %>
						
					</logic:notEmpty>
					<logic:empty name="<%=MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST %>">
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
    	<%-- 
    	 <logic:equal name="CertificateBEntryFormFB" property="reqType" value="0"> --%>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style=cursor:pointer  onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13)  submitPage('ADD')">
									
<%-- 		</logic:equal> --%>
				
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
       			   
    	</his:ButtonToolBarTag>  
    	  
    <html:hidden name="CertificateBEntryFormFB" property="hmode"/>
    <html:hidden name="CertificateBEntryFormFB" property="currentPage"/>
    <html:hidden name="CertificateBEntryFormFB" property="selectRecord"/>
    
     
</body>
<his:status/>
</html:form>   