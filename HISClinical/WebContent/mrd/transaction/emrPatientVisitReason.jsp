
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="mrd.MrdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

</script>

<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
 <his:TitleTag name="Visit Reasons" >
</his:TitleTag>

	<his:ContentTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="visitDate" />
							</font>
						</div>
					</td>
					
					
					<td width="80%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitRsn" />
							</font>
						</div>
					</td>
				
	   </tr>
	   	<logic:present name="<%=MrdConfig.PAT_VISIT_REASON_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_VISIT_REASON_ARRAY%>" >
	   <%String indexEpisodeOpen="-1"; %>
	   	<logic:iterate id="visitRsnVOid" indexId="idx" name="<%=MrdConfig.PAT_VISIT_REASON_ARRAY%>" type="hisglobal.vo.VisitReasonsVO">
			
					<%//String color="#000000"; %>
					<c:set var="color" value="#000000"></c:set>
					
					<tr>
						<td class="tdfontheadExam" width="20%"> 
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="visitRsnVOid"  property="entryDate" />
							</font>
							</b>
							</div>
						</td>
			
					<td class="tdfontheadExam" width="80%">
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="visitRsnVOid"  property="visitReasons" />
							</font>
							</b>
							</div>
						</td>
						
				</tr>
				<%indexEpisodeOpen=idx.toString(); %>
				
			</logic:iterate>
		
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	<bean:message key="noVisitReasonFound" /></b></font>
						</div>
					</td>
				</tr>
			</his:ContentTag>
			<%} %>
				</logic:notEmpty>
    		</logic:present>
    </table>
    
    <logic:notPresent name="<%=MrdConfig.PAT_VISIT_REASON_ARRAY%>" >
    	<his:ContentTag>
   			<tr>
				<td class="tdfont" width="100%"  nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="noVisitReasonFound" /></b></font>
					</div>
				</td>
			</tr>
		</his:ContentTag>
    </logic:notPresent>
   
</his:ContentTag>


<his:SubTitleTag>
		
	</his:SubTitleTag>
	
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 