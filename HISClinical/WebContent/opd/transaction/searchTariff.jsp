<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate  Request
 ## Purpose						:Certificate Request
 ## Date of Creation			:22-Nov-2014 

 -->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.EstimateCertificateReqVO"%>
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
document.getElementsByName("tarrifId")[0].value=vals;
}

  
     function validateSave()
     {
    
    var checeked = 0;
	var c = document.getElementsByName('tarrifId');
	
	for(var i=0;i<c.length-1;i++)
	{
	if (c[i].checked)
	checeked++;
	}
	if(checeked<1)
	{
	//alert(checeked);
	document.forms[0].tarrifId[0].focus();
	alert('Select at least one Procedure for Request.');
	return false;
	}
	if(document.getElementsByName('advisedBy')[0].value=="-1")
	{
	document.forms[0].advisedBy[0].focus();
	alert('Please select Advised By.');
	return false;
	}	
	if( validateDuplicate())
	{
	alert('Request already Raised for this Procedure.');
	
	return false;
	}
	return true;
	}
	
	function validateDuplicate()
	{
	
	var n=0;
	var pretariffIds=document.getElementsByName('preTarrifId')[0].value.substring(1);
	
	//alert(pretariffIds);
	
	var c = document.getElementsByName('tarrifId');
	//alert("c is "+c);
	
	pretarif = new Array();
	pretarif=pretariffIds.split(",");
	
	//alert("checkbox length is "+c.length);
    
    for (var j=0, n=c.length-1;j<n;j++) {
    
    for (var i = 0; i < pretarif.length; i++) {    
   	 
   	if (c[j].checked) 
  	{
  	
    		if (pretarif[i]==c[j].value)
    		{
        	return true;
    		}
	}
   }
   }
	return false;
	}
	
	
	
	
	
	
	
</script>

<%@page import="mrd.MrdConfig"%>
	<his:statusTransactionInProcess>
	<his:TitleTag name="Search Tariff">		
	</his:TitleTag>
				
			<his:ContentTag>	
				<% int count=0; 
				boolean nextRowFlag=false;
				boolean endTRRequired=false;%>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0" >				
						<tr>
							<td width="100%" class="tdfonthead">
								<div align="left">
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										Request For Procedure(s)
									</font>
								</div>
							</td>
						</tr>
					</table>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0" >				
					<logic:iterate name="<%=MrdConfig.ESTIMATE_PROCEDURE_TARIFF_DETAIL_LIST %>" id="arrTariffDtl" type="hisglobal.vo.EstimateCertificateReqVO" indexId="idx">
					<% 
					if(count==0){
						%><tr><%}
					if(count%4==0||count==0)
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
								<div align="left" id="tariffCheckBox">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:define id="tariffIds" name="arrTariffDtl" property="tariffId" type="java.lang.String" ></bean:define>
									<his:checkbox name="EstimateRequestFB" value="<%=tariffIds %>" property="tarrifId" onClick="gettariffDetail()"></his:checkbox>
									</font>	
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrTariffDtl" property="tariffName"/>
									</font>	
								</div>
							</td>
							<%count++; %>
					</logic:iterate>	<% 
					if(endTRRequired){	%>	</tr>	
					<%} %>
				</table>				
			</his:ContentTag>
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<logic:equal name="EstimateRequestFB" property="isDirectCall" value="DESK">	
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
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
		
