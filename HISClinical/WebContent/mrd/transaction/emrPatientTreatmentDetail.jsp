<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

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

	<his:TitleTag name="Treatment Detail">
	</his:TitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				
				<td width="30%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drugName"/>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="frequency"/>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="startDate"/>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="endDate"/>
						</font>
					</div>
				</td>
			</tr>
		
		<logic:present name="<%=MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY%>" >
		 <%String indexEpisodeOpen="-1"; %>
			<logic:iterate id="patDrugTreatmentDtlVO" name="<%=MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY%>" indexId="index" type="hisglobal.vo.PatDrugTreatmentDetailVO" >
				<tr>
				 
					
					<td width="30%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="drugName" />
							</font>
						</div>
					</td>   
					<td width="15%"  class="tdfont">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="patDrugTreatmentDtlVO" property="doseName" />
							</font>
						</div>
					</td>   
					<td width="15%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="frequencyName" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="startDate" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="endDate" />
							</font>
						</div>
					</td>   
				</tr>
				<%indexEpisodeOpen=index.toString(); %>
			</logic:iterate>
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	<bean:message key="noDrugAdviceDetailsFound" /></b></font>
						</div>
					</td>
				</tr>
			</his:ContentTag>
			<%} %>
			</logic:notEmpty>
    		</logic:present>

    <logic:notPresent name="<%=MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY%>" >
    	<his:ContentTag>
    		<tr>
				<td class="tdfont" width="100%"  nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="noDrugAdviceDetailsFound" /></b></font>
					</div>
				</td>
			</tr>
		</his:ContentTag>
    </logic:notPresent>
		</table>
	</his:ContentTag>
	<his:TitleTag name="Other Advice Detail">
	</his:TitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				
				<td width="25%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Ext Treatment Name 
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="frequency"/>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="startDate"/>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="endDate"/>
						</font>
					</div>
				</td>
			</tr>
		
		<logic:present name="<%=MrdConfig.PAT_EXT_TREATMENT_DETAILS_VO_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_EXT_TREATMENT_DETAILS_VO_ARRAY%>" >
		 <%String indexEpisodeOpen="-1"; %>
			<logic:iterate id="patDrugTreatmentDtlVO" name="<%=MrdConfig.PAT_EXT_TREATMENT_DETAILS_VO_ARRAY%>" indexId="index" type="hisglobal.vo.PatDrugTreatmentDetailVO" >
				<tr>
				 
					
					<td width="25%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="extTreatment" />
							</font>
						</div>
					</td>   
					
					<td width="15%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="frequencyName" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="startDate" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="endDate" />
							</font>
						</div>
					</td>   
				</tr>
				<%indexEpisodeOpen=index.toString(); %>
			</logic:iterate>
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	No Other Advice Detail Found</b></font>
						</div>
					</td>
				</tr>
			</his:ContentTag>
			<%} %>
			</logic:notEmpty>
    		</logic:present>

    <logic:notPresent name="<%=MrdConfig.PAT_EXT_TREATMENT_DETAILS_VO_ARRAY%>" >
    	<his:ContentTag>
    		<tr>
				<td class="tdfont" width="100%"  nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No Other Treatment Advice Detail Found</b></font>
					</div>
				</td>
			</tr>
		</his:ContentTag>
    </logic:notPresent>
		</table>
	</his:ContentTag>
	
<%-- 	<% if(MrdConfig.MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW.equals(MrdConfig.MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW_YES)) { %>
	<his:TitleTag name="Offline Issued Drug Detail">
	</his:TitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				
				<td width="30%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drugName"/>
						</font>
					</div>
				</td>
				<td width="20%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="issueQuantity"/>
						</font>
					</div>
				</td>
				<td width="25%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="issueDate"/>
						</font>
					</div>
				</td>
				<td width="25%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					<div align="center">	   
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="givenBy"/>
						</font>
					</div>
				</td>
			</tr>
		
		<logic:present name="<%=MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY%>" >
		 <%String indexEpisodeOpenOff="-1"; %>
			<logic:iterate id="patDrugTreatmentDtlVO" name="<%=MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY%>" indexId="index" type="hisglobal.vo.PatDrugTreatmentDetailVO" >
				<tr>
				 
					
					<td width="30%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patDrugTreatmentDtlVO" property="drugName" />
							</font>
						</div>
					</td>   
					<td width="20%"  class="tdfont">
						<div align="center"> 
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">          
								<bean:write name="patDrugTreatmentDtlVO" property="issueQty" />
							</font>
						</div>
					</td>   
					<td width="25%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="entryDate" />
							</font>
						</div>
					</td>   
					<td width="25%"  class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	           
								<bean:write name="patDrugTreatmentDtlVO" property="empName" />
							</font>
						</div>
					</td>   
				</tr>
				<%indexEpisodeOpenOff=index.toString(); %>
			</logic:iterate>
			<%if(indexEpisodeOpenOff.equals("-1")){%>
			<his:ContentTag>
				<tr>
					<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>No Offline Issued Drug Detail Found</b>
							</font>
						</div>
					</td>
				</tr>
			</his:ContentTag>
			<%} %>
			</logic:notEmpty>
   		</logic:present>

    <logic:notPresent name="<%=MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY%>" >
    	<his:ContentTag>
    		<tr>
				<td class="tdfont" width="100%"  nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No Offline Issued Drug Detail Found</b></font>
					</div>
				</td>
			</tr>
		</his:ContentTag>
    </logic:notPresent>
		</table>
	</his:ContentTag>
	<%	} %> --%>


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
					OD : 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Once a Day (Once Daily)
						</font>
					</div>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					BD : 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Twice a Day (Bi-Daily)
						</font>
					</div>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					TDS : 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Thrice a Day (Ter Die Sumendus)
						</font>
					</div>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					QID : 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Four Times a Day (Quarter In Die)
						</font>
					</div>
				</td>				
			</tr>

			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					SOS : 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						As Per Requirement (Si Opus Sit)
						</font>
					</div>
				</td>				
			</tr>
	
		</table>
	</his:ContentTag>
	</div>
	</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 