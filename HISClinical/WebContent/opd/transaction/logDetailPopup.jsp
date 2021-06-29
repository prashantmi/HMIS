<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="hisglobal.vo.PatDrugTreatmentDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.PatientTreatmentDetailFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@ page import="opd.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

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

<html:form action="/patTreatmentDetailTile">
	<body >
	<his:TitleTag name="All Previous Drug Detail">
	</his:TitleTag>	
	
	<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((PatientTreatmentDetailFB)request.getAttribute("PatientTreatmentDetailFB")).getCurrentPage());
				fbPage.setObjArrName(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG);
				fbPage.setAppendInTitle("Record"); 
				int maxRecord=10;
				fbPage.setMaxRecords(maxRecord);
				
		%>
			<html:hidden name="PatientTreatmentDetailFB" property="currentPage"/>
			<his:PaginationTag name="fbPagination"></his:PaginationTag>	
				
	<table id="prevDrugDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
	<tr>
					
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="prescriptionDate" />
							</b>	
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="drugname" />
							</b>	
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="dosage" />
							</b>	
							</font>
						</div>
					</td>
					<td width="7%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="frequency" />
							</b>	
							</font>
						</div>
					</td>
					
					<td width="13%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="startDate" />
							</b>	
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="endDate" />
							</b>	
							</font>
						</div>
					</td>
				
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="drugStatus" />
						</b>	
						</font>
					</div>
				</td>
					
				</tr>
	
	
	
	<%
	List drugDetailVoList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG );
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
												
						for(int j=startIndex;j<=endIndex;j++)
						{
							int ind=j;
							int indd=ind;
							for(int i=0;i<maxRecord;i++)
							{
								if(indd%maxRecord==i) ind=i;
							}
							
							// if(indd%4==0) ind=0;
							// if(indd%4==1) ind=1;
							// if(indd%4==2) ind=2;
							// if(indd%4==3) ind=3;
								
							String index=Integer.toString(ind);
							
							PatDrugTreatmentDetailVO drugDtlVO=(PatDrugTreatmentDetailVO)drugDetailVoList.get(j);
			%>
	<tr>
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getEntryDate()!=null){%>
					<%=drugDtlVO.getEntryDate() %>
					<%}%>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getDrugName()!=null){%>
					<%=drugDtlVO.getDrugName() %>
					<%}%>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getDoseName()!=null){%>
					<%=drugDtlVO.getDoseName() %>
					<%}%>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getFrequencyName()!=null){%>
					<%=drugDtlVO.getFrequencyName() %>
					<%}%>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getStartDate()!=null){%>
					<%=drugDtlVO.getStartDate() %>
					<%}%>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getEndDate()!=null){%>
					<%=drugDtlVO.getEndDate() %>
					<%}%>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<% if(drugDtlVO.getRxContinueDesc()!=null){%>
					<%=drugDtlVO.getRxContinueDesc() %>
					<%}%>
				</font>	
			</div>
		</td>
	
	</tr>
	 <%} %>
	</table>	
		
	
	
	<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
	 <html:hidden name="PatientTreatmentDetailFB" property="hmode"/>
	 
	</body>
	
</html:form>		