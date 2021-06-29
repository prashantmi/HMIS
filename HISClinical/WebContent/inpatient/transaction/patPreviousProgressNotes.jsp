<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="inpatient.transaction.controller.fb.NurseRoundFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.NurseRoundDtlVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

function viewPrevProgress(obj)
{
	document.getElementsByName("viewNotes")[0].value=document.getElementsByName("hiddenProgNotes")[obj].value;
	document.getElementById("divViewNotes").style.display="block"; 
}

function doPagination(e,p)
{	
	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='POPUPPAGINATION';
	document.forms[0].submit();
}

</script>

<html:form action="/nurseRound">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
				
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((NurseRoundFB)request.getAttribute("NurseRoundFB")).getCurrentPage());
				fbPage.setObjArrName(InpatientConfig.ARR_PAT_PREVIOUS_PRORESSNOTES);
				fbPage.setAppendInTitle("Patient Previous Progress Notes");
				int maxRecord=5;
				fbPage.setMaxRecords(maxRecord);
				
		%>
			<html:hidden name="NurseRoundFB" property="currentPage"/>

			<his:PaginationTag name="fbPagination"></his:PaginationTag>
		
	<his:statusTransactionInProcess>	
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					
					<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="slNo"/>
							</font>
						</div>
					</td>
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
							</font>
						</div>
					</td>
					
					<td width="45%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="enterBy"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="progNote"/>
							</font>
						</div>
					</td>
				</tr>
			
				<%
				NurseRoundDtlVO[] previousProgNotes=(NurseRoundDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_PREVIOUS_PRORESSNOTES);
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
												
						for(int j=startIndex;j<=endIndex;j++)
						{
							Integer ind=new Integer(j);
							int slNo=ind;
							int indd=ind;
							for(int i=0;i<maxRecord;i++)
							{
								if(indd%maxRecord==i) ind=i;
							}
							
							// if(indd%4==0) ind=0;
							// if(indd%4==1) ind=1;
							// if(indd%4==2) ind=2;
							// if(indd%4==3) ind=3;
								
							String index=ind.toString();
							
							NurseRoundDtlVO nurseRoundDtlVO=(NurseRoundDtlVO)previousProgNotes[j];
	%>	
				
				<tr>
					<td class="tdfont" width="10%" >
						<div align="center">
							<%=slNo+1 %>
						</div>
					</td>
					<td class="tdfont" width="25%" >
						<div align="center">
							<%=nurseRoundDtlVO.getRoundTime() %>
						</div>
					</td>
					<td class="tdfont" width="45%" >
						<div align="center">
							<%=(nurseRoundDtlVO.getUserName()==null)?"_":nurseRoundDtlVO.getUserName() %>
						</div>
					</td>
					<td class="tdfont" width="20%" >
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="NurseRoundFB" property="hiddenProgNotes" rows="5" cols="130"  value="<%=nurseRoundDtlVO.getProgressNote()%>"></html:textarea>
						</div>
						<div align="center">
							<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="View Progress Notes" 
							onclick="viewPrevProgress('<%=ind%>')" onkeypress="if(event.keyCode==13) viewPrevProgress('<%=ind%>')">								
						</div>
					</td>		
				</tr>
			<%} %>
			</table>	
		</his:ContentTag>
	</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
       
	       <div id="divViewNotes" style="display: none;">
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="NurseRoundFB" property="viewNotes" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
		
		<html:hidden name="NurseRoundFB" property="viewNotes"/>
		<html:hidden name="NurseRoundFB" property="hmode"/>
	</body>
<his:status/>
</html:form>