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
<%@page import="mrd.transaction.controller.fb.EstimateCertificateIssuetFB;"%>
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

<html:form action="/estimateCertificateIssue">
<body>  
	<his:TitleTag name="Estimate Certificate Issue">	
	</his:TitleTag>
	<his:SubTitleTag name="Estimate Certificate Requests">
	</his:SubTitleTag>
	<% 	PaginationFB fbPage= new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((EstimateCertificateIssuetFB)request.getAttribute("EstimateCertificateIssuetFB")).getCurrentPage());
		fbPage.setObjArrName(MrdConfig.ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL);
		fbPage.setAppendInTitle("Records");
		fbPage.setMaxRecords(15);
	%>
    <logic:present name="<%=MrdConfig.ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL%>">
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
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
									<bean:message key="requestNo"/>
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
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="advisedBy"/>
								</b>
							</font>
						</div>
					</td>
				
				</tr>
				<logic:notEmpty name="<%=MrdConfig.ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL %>">
					<% String str="";	
					List estimateCertificateReqtDtl=(List)session.getAttribute(MrdConfig.ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL);		
					
					
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					String fontCss="";
					boolean disabled=false;
					for(int i=startIndex;i<=endIndex;i++)
					{
						List lst=(List)estimateCertificateReqtDtl.get(i);
						
					%>
				
						<tr>
							<%
							String val=(String)lst.get(0);
						for(int j=0;j<lst.size();j++)
						{
							
							if(j>0)
							{
								val+="@"+lst.get(j);	
							}
							 System.out.println("val:::"+val);
							
						}
							%>
							
							<td width="5%" class="tdfontheadExam" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="EstimateCertificateIssuetFB" property="selectRecord" value='<%=val %>' onclick="submitPage('GETDTL')" ></html:radio>
								</font>
							</div>
							</td>
						<%
						for(int j=0;j<5;j++)
						{
							String n="0%";	
							if(j==0)
								{
									 n="5%";
								}
								else if(j==1)
								{
									 n="30%";
								}
								else if(j==2)
								{
									 n="25%";
								}
								else if(j==3)
								{
									 n="5%";
								}
								else if(j==4)
								{
									 n="30%";
								}
									
									%>									
									<td width="<%=n%>" class="tdfonthead">
										<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=(String)lst.get(j) %>
											</font>
										</div>	
									</td>
									<%} %>
									</tr>
						<%} %>
					</logic:notEmpty>
					<logic:empty name="<%=MrdConfig.ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL %>">
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
    <html:hidden name="EstimateCertificateIssuetFB" property="hmode"/>
    <html:hidden name="EstimateCertificateIssuetFB" property="selectRecord"/>
    <html:hidden name="EstimateCertificateIssuetFB" property="certificateId"/>
    <html:hidden name="EstimateCertificateIssuetFB" property="patCrNo"/>
    <html:hidden name="EstimateCertificateIssuetFB" property="currentPage"/>
</body>
<his:status/>
</html:form>    