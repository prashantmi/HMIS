
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

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

function showResult(reqDNo,deptCode,otName,event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	//alert("patCrNo "+patCrNo)
	// alert("testName "+testName)
	 var path="/HISClinical/mrd/emrDesk.cnt?hmode=OPERATIONPOPUP&REQ_NO="+reqDNo+"&DEPT_CODE="+deptCode+"&OT_NAME="+otName;
	 //alert("path "+path)
	 openPopup(createFHashAjaxQuery(path),event,screen.availHeight-100,1000);
}

</script>

<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
	<his:TitleTag name="Operation Details">
		</his:TitleTag>
	<logic:present name="<%=MrdConfig.PATIENT_OPERATION_LIST%>" >
	<logic:notEmpty name="<%=MrdConfig.PATIENT_OPERATION_LIST%>" >

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
			
				<td width="45%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="operationDate"/>
						</font>
					</div>
				</td>
				<td width="55%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="operationName"/>
						</font>
					</div>
				</td>
		
				
			</tr>
	   <%String indexEpisodeOpen="-1"; %>
			<logic:iterate id="patientOperationDtlId" name="<%=MrdConfig.PATIENT_OPERATION_LIST%>" indexId="id" type="java.util.List" >
				<tr>
				 
						<td width="45%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=patientOperationDtlId.get(4)%>
							</font>
						</div>
					</td>   
					<td width="55%"  class="tdfont">
						<div align="center"> 
						
						<a   style="cursor: pointer;" onclick="showResult('<%=patientOperationDtlId.get(0) %>','<%=patientOperationDtlId.get(7) %>','<%=patientOperationDtlId.get(5) %>',event)" onkeypress="showResult('<%=patientOperationDtlId.get(0) %>','<%=patientOperationDtlId.get(7) %>','<%=patientOperationDtlId.get(5) %>',event)" >
							<font color="#0066FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
							<u>	<%=patientOperationDtlId.get(5)%> </u>
							</font>
						</a>
						</div>
					</td>   
				<%--	<td width="32%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="profileInvestigationVOs" property="resultStatus" />
								NA
							</font>
						</div>
					</td> --%>   
					
				</tr>
					<%indexEpisodeOpen=id.toString(); %>
			</logic:iterate>
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					
									<td class="tdfont" width="25%" colspan="5" nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noOperationDetailsFound" /></b></font></div></td>
					
				</tr>
			</his:ContentTag>
			<%} %>
		    </table>
		</his:ContentTag>
	</logic:notEmpty>
   	</logic:present>
    
    <logic:notPresent name="<%=MrdConfig.PATIENT_OPERATION_LIST%>" >
    	<his:ContentTag>
    	
				<tr>
					
									<td class="tdfont" width="100%"  nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noOperationDetailsFound" /></b></font></div></td>
					
				</tr>
		
			</his:ContentTag>
    </logic:notPresent>
	
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 