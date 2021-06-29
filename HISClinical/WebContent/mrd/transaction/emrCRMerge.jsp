
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.CrNoMergeDtlVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>

function showSelCREMR(crNo)
{
	document.forms[0].hmode.value= "CALLFROMDESK";
	document.forms[0].patCrNo.value= crNo;
	var deptunitcode=document.forms[0].departmentUnitCode.value;
	//document.forms[0].submit();
	
	//parent.parent.document.getElementById("f2").src
	parent.parent.document.getElementById("frmMain").src = "/HISClinical/mrd/emrDesk.cnt?hmode=CRMERGEEMR&patCrNo="
		+ crNo + "&departmentUnitCode=" + deptunitcode;
}


</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">

	<body>
		<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
		
		<logic:present name="<%=MrdConfig.PATIENT_CR_MERGE_LIST%>" >
			<his:TitleTag name="CR Merged List">
			</his:TitleTag>
		<%
			List lstCRMerge = (List) session.getAttribute(MrdConfig.PATIENT_CR_MERGE_LIST);
			if(lstCRMerge!=null && lstCRMerge.size()>0)
			{
				if(lstCRMerge.size()==1 && ((CrNoMergeDtlVO)lstCRMerge.get(0)).getIsMerged().equals("0"))
				{
					CrNoMergeDtlVO voCRMerger = (CrNoMergeDtlVO)lstCRMerge.get(0);
		%>
			<his:ContentTag>
				<table width="100%" >
					<tr>
						<td class="tdfont" width="100%" nowrap>
						 	<div align="center">
					 		<b>
							 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<b> This CR No. i.e. "<%=voCRMerger.getPatNotUsedCrNo()%>" is Offline and merged with Final CR No. i.e. </b></font> <a onclick='showSelCREMR("<%=voCRMerger.getPatMainCrNo()%>")' style="cursor: pointer;">"<%=voCRMerger.getPatMainCrNo()%>"</a>
								
							</b>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		<%
				}
				else
				{
		%>
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Merged CR No.
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Date of Merger
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Reason
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Merged By
								</font>
							</div>
						</td>
					</tr>
					<%
						for(int i=0; i<lstCRMerge.size(); i++)
						{
							CrNoMergeDtlVO voCRMerger  = (CrNoMergeDtlVO) lstCRMerge.get(i);
							if(voCRMerger.getIsMerged().equals("1"))
							{
					%>
						<tr>
							<td class="tdfont" width="15%" >
								<div align="center">
									<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<a onclick='showSelCREMR("<%=voCRMerger.getPatNotUsedCrNo()%>")' style="cursor: pointer;">
											<%=voCRMerger.getPatNotUsedCrNo()%>
											</a>
										</font>
									</b>
								</div>
							</td>
							<td class="tdfont" width="15%" >
								<div align="center">
									<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<%=voCRMerger.getEntryDate()%>
										</font>
									</b>
								</div>
							</td>
							<td class="tdfont" width="40%" >
								<div align="center">
									<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<%=voCRMerger.getRemarks()%>
										</font>
									</b>
								</div>
							</td>
							<td class="tdfont" width="40%" >
								<div align="center">
									<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<%=voCRMerger.getMergedBy()%>
										</font>
									</b>
								</div>
							</td>
						</tr>
					<%
							}
						}
					%>
				</table>
			</his:ContentTag>		
			
		
		<%
				}
			}
			else
			{
		%>
			<his:ContentTag>
				<table width="100%" >
					<tr>
						<td class="tdfont" width="100%" nowrap>
						 	<div align="center">
					 		<b>
							 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noCRMerged"/> </b>
								</font>
							</b>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		<%
			}
		%>
		</logic:present>
	
		<logic:notPresent name="<%=MrdConfig.PATIENT_CR_MERGE_LIST%>" >
			<his:ContentTag>
				<table width="100%" >
					<tr>
						<td class="tdfont" width="100%" nowrap>
						 	<div align="center">
					 		<b>
							 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noCRMerged"/> </b>
								</font>
							</b>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:notPresent>
		
		
		
	</body>	
	<his:status/>

</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 