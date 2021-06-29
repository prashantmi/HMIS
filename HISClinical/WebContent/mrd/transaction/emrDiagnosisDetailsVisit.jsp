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
<%@page import="hisglobal.vo.EpisodeDiagnosisVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="opd.OpdConfig"%>
<%@page import="ehr.diagnosis.vo.EHRSection_DiagnosisVO"%>
<script type="text/javascript">

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
 <his:TitleTag name="Diagnosis Detail" >
</his:TitleTag>

	<his:ContentTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="diagnosisDate" />
							</font>
						</div>
					</td>
					
					
					<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisName" />
							</font>
						</div>
					</td>
	
				
				<td width="17%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="diagonosisType" />
						</font>
					</div>
				</td>
				
				<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="DCodeType" />
							</font>
						</div>
					</td>
				
			<%--	<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks" />
						</font>
					</div>
			</td> --%>
	   </tr>
	   
	   <%
	   
	  // EHRSection_DiagnosisVO[] arrayPreviousDiagVO = (EHRSection_DiagnosisVO[]) WebUTIL.getSession(request).getAttribute(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
	  // String codeType=null;
	  // for(int i=0;i<arrayPreviousDiagVO.length;i++)
	  // {
		  // codeType=arrayPreviousDiagVO[i].getDiagnosisCodeLabel();
	  // }
	   
	   %>
	   	<logic:present name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" >
	   <%String indexEpisodeOpen="-1"; %>
	   	<logic:iterate id="diagnosisVOid" indexId="idx" name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" type="hisglobal.vo.EpisodeDiagnosisVO">
				
			<%--	<logic:equal name="diagnosisVOid" property="episodeIsOpen" value="1"> --%>
					<%//String color="#000000"; %>
					<c:set var="color" value="#000000"></c:set>
					<logic:equal name="diagnosisVOid"  property="isRepeat" value="1">
					<%// color="#000000"; %>
						<c:set var="color" value="#000000"></c:set>
					</logic:equal>
					<logic:equal name="diagnosisVOid" property="isRepeat" value="3">
					<%// color="#0000FF"; %>
					<c:set var="color" value="#0000FF"></c:set>
					</logic:equal>
					<tr>
						<td class="tdfontheadExam" width="13%"> 
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="entryDate" />
							</font>
							</b>
							</div>
						</td>
						
<!-- 						<td class="tdfontheadExam" width="10%"> -->
<!-- 							<div align="center"> -->
<!-- 							<b> -->
<%-- 							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif"> --%>
<%-- 								<bean:write  name="diagnosisVOid"  property="diagnosticCode" /> --%>
<!-- 							</font> -->
<!-- 							</b> -->
<!-- 							</div> -->
<!-- 						</td> -->
			
					<td class="tdfontheadExam" width="30%">
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="dignosisName" />
							</font>
							</b>
							</div>
						</td>
						<td class="tdfont" width="17%">
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="diagnosticTypeName" />
								
							</font>
							</b>
							</div>
						</td>
				<%--		<td class="tdfont" width="30%">
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="remarks" />
							</font>
							</b>
							</div>
						</td> --%>
						
						
						<td class="tdfontheadExam" width="10%">
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="diagnosisCodeType" />
								
								
							</font>
							</b>
							</div>
						</td>
				</tr>
				
				<%indexEpisodeOpen=idx.toString(); %>
			<%--	</logic:equal>--%>
				
			</logic:iterate>
		
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	<bean:message key="noDiagnosisDetailsFound" /></b></font>
						</div>
					</td>
				</tr>
			</his:ContentTag>
			<%} %>
				</logic:notEmpty>
    		</logic:present>
    </table>
    
    <logic:notPresent name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" >
    	<his:ContentTag>
   			<tr>
				<td class="tdfont" width="100%"  nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="noDiagnosisDetailsFound" /></b></font>
					</div>
				</td>
			</tr>
		</his:ContentTag>
    </logic:notPresent>
    <%--
    	<his:SubTitleTag key="closeDuration" >
	</his:SubTitleTag>
	<logic:present name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" >
	<logic:notEmpty name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" >
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="13%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="visitDate" />
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="diagnosisCode" />
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="diagnosisName" />
							</font>
						</div>
					</td>
	
				
				<td width="17%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagonosisType" />
						</font>
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks" />
						</font>
					</div>
			</td>
	   </tr>
	   <%String indexEpisodeClosed="0"; %>
	   	<logic:iterate id="diagnosisVOid" indexId="idx" name="<%=MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY%>" type="hisglobal.vo.EpisodeDiagnosisVO">
				
				<logic:equal name="diagnosisVOid" property="episodeIsOpen" value="0">
				<%String color="#000000"; %>
										<logic:equal name="diagnosisVOid"  property="isRepeat" value="1">
										<% color="#0000FF"; %>
										</logic:equal>
										<logic:equal name="diagnosisVOid" property="isRepeat" value="2">
										<% color="#000000"; %>
										</logic:equal>
				<tr>
						<td class="tdfont" width="13%">
							<div align="center">
							<b>
							<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="entryDate" />
							</font>
							</b>
							</div>
						</td>
						
						<td class="tdfont" width="10%">
							<div align="center">
							<b>
							<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="diagnosticCode" />
							</font>
							</b>
							</div>
						</td>
			
					<td class="tdfont" width="30%">
							<div align="center">
							<b>
							<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="dignosisName" />
							</font>
							</b>
							</div>
						</td>
						<td class="tdfont" width="17%">
							<div align="center">
							<b>
							<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="diagnosticTypeName" />
							</font>
							</b>
							</div>
						</td>
						<td class="tdfont" width="30%">
							<div align="center">
							<b>
							<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="diagnosisVOid"  property="remarks" />
							</font>
							</b>
							</div>
						</td>
				</tr>
				<%indexEpisodeClosed=idx.toString(); %>
				</logic:equal>
				
			</logic:iterate>
			
			<%if(indexEpisodeClosed.equals("0")){%>
			<his:ContentTag>
				<tr>
									<td class="tdfont" colspan="5" width="25%" nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noDiagnosisDetailsFound" /></font></div></td>
				</tr>
				</his:ContentTag>
			<%} %>
    </table>
    </logic:notEmpty>
    </logic:present>
   --%>
   
</his:ContentTag>


<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:block;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Black : 
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					
					Active Diagnosis
			
					</font>
							</div>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Blue : 
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					
					Inactive Diagnosis
				
					</font>
						</div>
				</td>				
			</tr>
			
	
		</table>
	</his:ContentTag>
	</div>
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 