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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/easyui.css" />
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/newHisRadioStyle.css" />
<his:css src="/hisglobal/css/tutorstyle.css" />	
<his:css src="/hisglobal/css/flexcrollstyles.css" />


<%
	String jsonTariffList ="";
	StringBuffer sb = new StringBuffer("[");
	List<EstimateCertificateReqVO> lstTariffs =(List<EstimateCertificateReqVO>)session.getAttribute(MrdConfig.TARIFF_LIST_ESTIMATE_CERTIFICATE_REQUEST);
	for(EstimateCertificateReqVO vo : lstTariffs)
	{
		sb.append("{");sb.append("label:");sb.append("\"");sb.append(vo.getTariffName());sb.append("\"");sb.append(",");
		sb.append("value:");sb.append("\"");sb.append(vo.getTariffName());sb.append("\"");sb.append(",");
		sb.append("id:");sb.append(vo.getTariffId());sb.append("}");sb.append(",");
	}
	if(sb.length()>2) sb.setLength(sb.length()-1);
	sb.append("]");
	jsonTariffList = sb.toString();
%>
<script type="text/javascript">


<%--  { label:<%=arrPatDtl.getTariffName()%>, value: <%=arrPatDtl.getTariffId()%>">  } --%>

$(function() {
    $( "#autocomplete-4" ).autocomplete({
       source: <%=jsonTariffList%>,
       focus: function( event, ui ) {
           //$( "#autocomplete-4" ).val( ui.item.label );
              return false;
           },       
           select: function( event, ui ) {
           //alert("ddf");
    	   //$( "#autocomplete-4" ).val( ui.item.label );
           $( "#searchTariffId" ).val( ui.item.id );}
    });
 });
 
 
 
function addSelectedTariff()
{
	
	var trfid=document.getElementsByName("searchTariffId")[0].value;
	var trfname=document.getElementsByName("searchTariff")[0].value;
//	alert(trfid);alert(trfname);
	
	var chk= document.getElementsByName("tarrifId");
	for(var i=0; i<chk.length;i++)
		{
			if(chk[i].value==trfid)
				{
					alert("Procedure / Investigation Already added or No Match Found");
					 document.getElementsByName("searchTariffId")[0].value="";
					 document.getElementsByName("searchTariff")[0].value="";
					 document.getElementsByName("searchTariff")[0].focus();
					return;
				}
		}
		

      if(trfid!= null && trfid!="")
	  {
    	  $('#tdTariffId').append("<input type='checkbox' name='tarrifId'  checked='true' value='" + trfid + "' &nbsp; />" + trfname+"&nbsp;&nbsp;&nbsp;");
	  }
	  document.getElementsByName("searchTariffId")[0].value="";
	  document.getElementsByName("searchTariff")[0].value="";
	  document.getElementsByName("searchTariff")[0].focus();
	  
	
}



function openTariffListPopup(e)
{
	var path='/HISClinical/opd/estimateCertificateRequest.cnt?hmode=POPUP';
	openPopup(createFHashAjaxQuery(path),e,300,700);
}

function submitForm(mode)
{
  gettariffDetail();
  document.getElementsByName("hmode")[0].value=mode;
  document.forms[0].submit();


}

function gettariffDetail()
{
var checkboxes = document.getElementsByName('tarrifId');
var vals = ""; 
var id = "";
var dupFlag = "";
for (var i=0, n=checkboxes.length;i<n;i++) {
  if (checkboxes[i].checked) 
  {
  vals += ","+checkboxes[i].value;
 // alert("val::"+vals);
  }
}
vals = vals.substring(1);
document.getElementsByName('tarrifId')[0].value=vals;
//alert(document.getElementsByName('tarrifId')[0].value);

/* var values=vals.split(",");
//alert(vals.length);
//alert(values.length);
for(var i = 0;i<values.length;i++) 
{
	dupflag="0";
	for(var j=i+1;j<=values.length;j++)  // checking duplicate tariffs
	{
		if(values[i]==values[j])
		{
			dupflag="1";
		     break;
	     }
	}
	if(dupflag!="1") 
	{
		 id += ","+values[i];
	}

}
id = id.substring(1);
document.getElementsByName("tarrifId")[0].value=id; */
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
	//document.forms[0].tarrifId[0].focus();
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
								<b>	<bean:message key="requestNo"/></b>
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
					String pretariff="";
					List prevestimateCertificateReqtDtl=(List)session.getAttribute(MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL);		
					for(int i=0;i<prevestimateCertificateReqtDtl.size();i++)
					{
						List lst=(List)prevestimateCertificateReqtDtl.get(i);
						if(i==0)
							pretariff = (String)lst.get(4);
						else
							pretariff += ","+(String)lst.get(4);
						%>
						
						<tr>
						
						<%
						for(int j=0;j<lst.size()-1;j++)
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
					
						<html:hidden name="EstimateCertificateIssuetFB" property="preTarrifId"  value="<%=pretariff%>"/>
				</table>		
			</his:ContentTag>
			</logic:notEmpty>					
			<logic:empty name="<%=MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL %>">			
			  <html:hidden name="EstimateCertificateIssuetFB" property="preTarrifId"  value=" "/>	
			</logic:empty>	
			
			<his:SubTitleTag name="Request Detail">
			
								<div align="center" id="divSearchTraiff" class="ui-widget">
							<input type="text" style="display: none;" id="searchTariffId" name="searchTariffId" size="30" >
					
							<input type="text"  name="searchTariff"  id="autocomplete-4" tabindex="1" size="30" 
																	onkeypress="if(event.keyCode==13) addSelectedTariff();"> &nbsp;&nbsp;
								
								<img class="button" id="goButton" tabindex="1"
																	src="/HIS/hisglobal/images/GoNew.png"
																	style="cursor: pointer;" 
																	onclick="addSelectedTariff();" >
								</div> 
						

			
					<%-- <div align="right">
						<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openTariffListPopup(event)">
					</div> --%>
				
			</his:SubTitleTag>			
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
						</tr><tr id="trTariffId">
						<%}else{ %>
						<tr>
						<%}}%>
							<td class="tdfontheadExam" width="25%" >
								<div align="left" id="tariffCheckBox">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:define id="tariffIds" name="arrTariffDtl" property="tariffId" type="java.lang.String" ></bean:define>
									<his:checkbox name="EstimateRequestFB" value="<%=tariffIds %>" property="tarrifId" ></his:checkbox>
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
					
					
					<tr><td class="tdfontheadExam" width="100%" id="tdTariffId" colspan="4">
					</td></tr>
					
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
									onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericWithDotsOnly(event))"/>
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
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateSave()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm21('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
				</his:statusTransactionInProcess>
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
				</his:statusUnsuccessfull>	
			</logic:equal>
			
		</his:ButtonToolBarTag>	
		
		

<div id="divTariffList" style="display: none; position: absolute;">
 <select name="tarrifList" id="tariffList" multiple="multiple" size="4">
 <logic:present name="<%=MrdConfig.TARIFF_LIST_ESTIMATE_CERTIFICATE_REQUEST %>" >
		 	<logic:iterate name="<%=MrdConfig.TARIFF_LIST_ESTIMATE_CERTIFICATE_REQUEST %>" id="arrPatDtl" type="hisglobal.vo.EstimateCertificateReqVO" indexId="idx">
				 	<option value="<%=arrPatDtl.getTariffId()%>"> <%=arrPatDtl.getTariffName()%> </option>
						 	
				            </logic:iterate>
						 </logic:present>
						
		</select>
</div>
	
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
		
