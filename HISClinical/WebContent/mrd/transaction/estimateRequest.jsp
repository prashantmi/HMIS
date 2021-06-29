<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate  Request
 ## Purpose						:Certificate Request Process
 ## Date of Creation			:22-Nov-2014 

 -->


<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="java.util.List"%>
<%@page import="mrd.vo.EstimateCertificateReqVO"%>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function gettariffDetail()
{
var checkboxes = document.getElementsByName('tarrifId');
var vals = "";
for (var i=0, n=checkboxes.length;i<n;i++) {
  if (checkboxes[i].checked) 
  {
  vals += ","+checkboxes[i].value;
  //alert("val::"+vals);
  }
}
vals = vals.substring(1);
//alert(vals);
document.getElementsByName("tarrifId")[0].value=vals;
}

  
     function validateSave()
     {
    var checeked = 0;
	var c = document.getElementsByName('tarrifId');
	for(var i=0;i<c.length;i++)
	{
	if (c[i].checked)
	checeked++;
	}
	if(checeked<1)
	{
	//alert(checeked);
	alert('Please select atleast one Procedure.');
	return false;
	}
	if(document.getElementsByName('advisedBy')[0].value=="-1")
	{
	alert('Please select Advised By.');
	return false;
	}
	return true;
	}
	
	
	
	
	
</script>

<%@page import="mrd.MrdConfig"%>
	<his:statusTransactionInProcess>
	<his:TitleTag name="Estimate Certificate Request">		
	</his:TitleTag>
			<bean:define id="patCrNo" name="EstimateRequestFB" property="patCrNo" type="java.lang.String" ></bean:define>
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true">
			    <jsp:param name="patCrNo" value="<%=patCrNo %>" />
			</jsp:include>
			<logic:notEmpty name="<%=MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL %>">
			<his:SubTitleTag name="Previous Estimate Certificate Request">
			</his:SubTitleTag>
			<his:ContentTag>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
					<tr>	
						<td  class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="requestedProcedure"/></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message key="requestDate"/></b>
									</font>
							</div>		
						</td>
						<td width="25%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message key="advisedBy"/></b>
									</font>
							</div>	
						</td>
						<td width="25%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message key="certificateStatus"/></b>
									</font>
							</div>	
						</td>
						
					</tr>
					
					<% String str="";	
					List prevestimateCertificateReqtDtl=(List)session.getAttribute(MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL);		
					for(int i=0;i<prevestimateCertificateReqtDtl.size();i++)
					{
						List lst=(List)prevestimateCertificateReqtDtl.get(i);
						%>
						<tr>
						<%
						for(int j=0;j<lst.size();j++)
						{
									%>
									
									<td width="25%" class="tdfonthead">
										<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=(String)lst.get(j) %>
											</font>
										</div>	
									</td>
									<%} %>
									</tr>
						<%} %>
					
						
				</table>		
			</his:ContentTag>
			</logic:notEmpty>
			<his:SubTitleTag name="Request For Procedure Estimate">
			</his:SubTitleTag>			
			<his:ContentTag>	
				<% int count=0; 
				boolean nextRowFlag=false;
				boolean endTRRequired=false;%>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0" >				
					<logic:iterate name="<%=MrdConfig.ESTIMATE_PROCEDURE_TARIFF_DETAIL_LIST %>" id="arrTariffDtl" type="mrd.vo.EstimateCertificateReqVO" indexId="idx">
					<% count++;
					if(count==1){
						%><tr><%}
					if(count%4==0||count==1)
					{
						if(count==1)
							endTRRequired=false;
						else
							endTRRequired=true;
						nextRowFlag=true;
					}
					else
					{
						endTRRequired=true;
						nextRowFlag=false;
					}
						if(nextRowFlag){
						if(endTRRequired){%>
						</tr><tr>
						<%}else{ %>
						<tr>
						<%}}%>
							<td class="tdfontheadExam" width="25%" >
								<div align="right" id="tariffCheckBox">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:define id="tariffIds" name="arrTariffDtl" property="tariffId" type="java.lang.String" ></bean:define>
									<his:checkbox name="EstimateRequestFB" value="<%=tariffIds %>" property="tarrifId" onClick="gettariffDetail()"></his:checkbox>
									</font>	
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrTariffDtl" property="tariffName"/>
									</font>	
								</div>
							</td>
							
					</logic:iterate>	<% 
					if(endTRRequired){	%>	</tr>	
					<%} %>
				</table>				
			<div id="newAttendantDiv">			
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="advisedBy"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="EstimateRequestFB" property="advisedBy" onclick="checkExisting()" tabindex="1">
										<html:option value="-1">Select value</html:option>
										<logic:present name="<%=MrdConfig.ESTIMATE_REQUEST_ADVISEDBY_DETAIL %>">
											<html:options collection="<%=MrdConfig.ESTIMATE_REQUEST_ADVISEDBY_DETAIL %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="50%" class="tdfonthead" colspan="2">
							</td>							
						</tr>
						<tr>						
							<td width="25%" class="tdfonthead">
								<div align="right">
									
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea rows="3" cols="38" name="EstimateRequestFB" property="remarks" tabindex="1"  style="vertical-align: middle;" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
								</div>
							</td>
							<td width="50%" class="tdfonthead" colspan="2">
							</td>
						</tr>
					</table>
			</div>
			</his:ContentTag>
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<logic:equal name="EstimateRequestFB" property="isDirectCall" value="DESK">	
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
				</his:statusTransactionInProcess>
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
				</his:statusUnsuccessfull>	
			</logic:equal>
			
		</his:ButtonToolBarTag>	

	
	    <html:hidden name="EstimateRequestFB" property="hmode" />
		<html:hidden name="EstimateRequestFB" property="patCrNo"/>
		<html:hidden name="EstimateRequestFB" property="episodeCode"/>
		<html:hidden name="EstimateRequestFB" property="episodeVisitNo"/>
		<html:hidden name="EstimateRequestFB" property="isDirectCall" />
		<html:hidden name="EstimateRequestFB" property="tarrifId" />
		<html:hidden name="EstimateRequestFB" property="advisedBy" />
		<html:hidden name="EstimateRequestFB" property="remarks" />
		<html:hidden name="EstimateRequestFB" property="departmentUnitCode" />
		<html:hidden name="EstimateRequestFB" property="strCategoryCode" />
		
