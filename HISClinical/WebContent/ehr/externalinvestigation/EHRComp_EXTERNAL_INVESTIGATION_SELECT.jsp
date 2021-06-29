<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>
<%@page import="ehr.EHRConfig"%>

<%@page import="hisglobal.vo.EpisodeExtInvDtlVO"%>
<%@page import="ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO"%>
<%@page import="org.json.JSONArray"%> 
<%@page import="org.json.JSONObject"%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
</head>
<!-- <link rel="stylesheet" href="/HISClinical/mrd/js/flexdatalist/jquery.flexdatalist.min.css">  -->
 
<%-- <logic:equal name="EHRSection_EHRSection_ExternalInvestigationFB" property="isDirectCall" value="DIRECT">
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	
	<his:javascript src="/registration/js/registration.js" />
	<his:javascript src="/registration/js/validationCalls.js" />
	<his:javascript src="/registration/js/validationCommon.js" />
	<his:javascript src="/registration/js/commonFunctions.js" />
	<his:javascript src="/hisglobal/js/calendar.js"/>
	<his:javascript src="/registration/js/popup.js"/>
	
</logic:equal> --%>
<%-- <his:javascript src="/opd/js/externalInvestigation.js" />  --%>
<!--  <script src="/HISClinical/mrd/js/flexdatalist/jquery.flexdatalist.min.js"></script>   -->
<his:javascript src="/ehr/js/EHR_spp_extInv.js"/> 

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">
<%-- <%  String obj2 = (String) request.getSession().getAttribute(EHRConfig.TESTS_FOR_EXTERNAL_INVESTIGATION); 
System.out.println("drugJSON:"+obj2);
JSONArray extInvTests = new JSONArray(obj2);
//System.out.println("mapDrugLst:"+extInvTests);
%>
var drugJsonObj = <%=extInvTests.toString() %>;  --%>
$(document).ready(function(){
/* $('.flexdatalist').flexdatalist({
    minLength: 1
}); */


	 $('#recordDateId').attr('value',setCurrentDate12());  
/* $('#FlexExternalTestList').flexdatalist({
	minLength: 1,
	focusFirstResult: true,
	searchByWord: true,
	maxShownResults: 50,
	selectionRequired: true,
	searchIn: 'label',
//visibleProperties: ["drugName","drugQuan"], 
	data: drugJsonObj
});    
$('input[name=externalTestList]').on('select:flexdatalist', function(event, set, options) {
$('input[name=externalTestList]').val(set.label);
}); */
	 
});




//});
function setDrugRoute(drugName)
{
//alert(drugName);
}

function submitForm21(mode)
{
    
   // alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}
function validateLabContact(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789,").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}//end of validateNumericWithCommaOnly
</script>


	
	<bean:define name="EHRSection_ExternalInvestigationFB" property="recordDate" id="toDate" type="java.lang.String"/>
	<% if(toDate==null||toDate.equalsIgnoreCase(""))
	   {
			toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	   }	 
					
	%>
			<%-- <his:TitleTag name="Externnal Examination">
			</his:TitleTag>
			
			<his:statusNew>
				<logic:equal name="EHRSection_ExternalInvestigationFB" property="hmode" value="GETCRNO">
					<logic:empty name="EHRSection_ExternalInvestigationFB" property="patCrNo" >
						<his:InputCrNoTag name="EHRSection_ExternalInvestigationFB"></his:InputCrNoTag>
					</logic:empty>
				</logic:equal>		
			</his:statusNew> --%>
			
			<%-- <his:statusTransactionInProcess> --%>
				<%-- <jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
				<%EpisodeExtInvDtlVO[] epiExtVO=(EpisodeExtInvDtlVO[])session.getAttribute(OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL);
					if(epiExtVO.length>0){ %>
					<his:SubTitleTag name="Added External Examination Detail">
					</his:SubTitleTag>
					
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="20%" class="">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="date"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="time"/>
												<bean:message key="timeFormat"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="para"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="40%" class="">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="value"/>
											</b>	
										</font>
									</div>
								</td>
								
							</tr>
							<logic:iterate id="epiExtInvVO" name="<%=OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL %>" type="hisglobal.vo.EpisodeExtInvDtlVO">
								<tr>
									<td width="20%" class="">
										<div align="center">
											<%=epiExtInvVO.getRecordDate() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="addedRecordDate" value="<%=epiExtInvVO.getRecordDate() %>"/>											
										</div>
									</td>
									<td width="20%" class="">
										<div align="center">
											<%=epiExtInvVO.getRecordTime()  %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="addedRecordTime" value="<%=epiExtInvVO.getRecordTime()  %>"/>
										</div>
									</td>
									<td width="20%" class="">
										<div align="center">
											<%=epiExtInvVO.getParaName() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="addedParaId" value="<%=epiExtInvVO.getParaName() %>"/>
										</div>
									</td>
									<td width="40%" class="">
										<div align="center">
											<%=epiExtInvVO.getParaValue() %>
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>	
							
					</his:ContentTag>
				<%}%> --%>

		<%-- <his:ContentTag> --%>
		
		<table class="table">
							<tr>
								<td style="font-size:1.2em;font-weight:bold;" width="100%">
									External Investigation&nbsp;:
								<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
								</td>
							</tr>
							</table>
							
							<%if(session.getAttribute(EHRConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL)!=null){ %>
					
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" CLASS="table">
							<logic:iterate id="arrExtInv" name="<%=EHRConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL %>" type="ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO" indexId="idx">
							<%String ind=idx.toString(); %>
								<tr id="ExtInvRow#<%=idx.intValue()+2%>">
									<td class="" width="15%" >
										<div align="center">
											<%=arrExtInv.getSelTestName() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedParaId" value="<%=arrExtInv.getParaName() %>"/>
											<input name = "currentAddedTest" value = "<%=arrExtInv.getSelTestName() %>" type="hidden">
										</div>
									</td>
									<td class="" width="20%" >
										<div align="center">
										<%if(arrExtInv.getParaName()!=null){ %>
											<%=arrExtInv.getParaName() %>
											<%}else %>-
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedParaId" value="<%=arrExtInv.getParaName() %>"/>
										</div>
									</td>
									
									<td class="" width="30%" >
										<div align="center">
											<%=arrExtInv.getParaValue() %>
										</div>
									</td>
									<td class="" width="15%" >
										<div align="center">
											<%=arrExtInv.getRecordDate() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedRecordDate" value="<%=arrExtInv.getRecordDate() %>"/>
										</div>
									</td>
									<td class="" width="15%" >
										<div align="center">
											<%=arrExtInv.getRecordTime() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedRecordTime" value="<%=arrExtInv.getRecordTime() %>"/>
										</div>
									</td>
									<td class="" width="5%" >
										<div align="center">
											<button type="button"  id="revokeButton"  class="btn btn-info btn-sm"  onclick="deleteExtInvRow('<%= arrExtInv.getSlno()%>','<%=idx.toString()%>'); ">Delete</button>
										</div>	
									</td>
								</tr>	
							</logic:iterate>
						</table>
						
				<%} %>
			<table id = "extInvDtlTable" class ="table table-condensed table-responsive">
				
				<tr style="background: linear-gradient(to bottom, #74a9d8 , white); font-weight:bold;">
				
					<td width="15%" class="">
						<div align="center">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> 
									Test Name
							</b>
							</font>
						</div>
					</td>
					<td width="20%" class="">
						<div align="center">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b>  <bean:message key="para" />
							</b>
							</font>
						</div>
					</td>
					
					<td width="30%" class="">
						<div align="center">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="value" />
							</b>
							</font>
						</div>
					</td>
						<td width="15%" class="">
						<div align="center">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="date" />
							</b>
							</font>
						</div>
					</td>
					<td width="15%" class="">
						<div align="center">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message key="time" /> <bean:message
										key="timeFormat" />
							</b>
							</font>
						</div>
					</td>
					<td width="5%" class=""></td>
				</tr>
				<tr>
				
					<td width="15%" class="">
						<div id="divTestList"
							style="display: none; position: absolute;">
							<select name="externalTestList" id="externalTestList"
								multiple="multiple" size="4">
								<logic:present name="<%=EHRConfig.TESTS_FOR_EXTERNAL_INVESTIGATION%>">
								<logic:iterate name="<%=EHRConfig.TESTS_FOR_EXTERNAL_INVESTIGATION%>" 
									id="list">
					
					<!-- <datalist id="externalTestList"> -->
    									<option value="<bean:write name='list' property='value'/>">
										<bean:write name='list' property='label' /></option>
									<!-- </datalist> -->
									
								</logic:iterate>
								</logic:present>
							</select>
						</div>
						<!-- <input type='text' placeholder='' class='flexdatalist form-control'	data-min-length='1' list='externalTestList'	id ="FlexExternalTestList" name='externalTestList'/> -->
						 <div align="center">
							<html:text name="EHRSection_ExternalInvestigationFB"
							styleClass="form-control"
								property="testId"
								onkeypress="return validateText(event)"
								onkeyup="gettextForTestName(event,this);" onblur=""
								tabindex="1"></html:text>
						</div> 
					</td>
					<td width="20%" class="">
						<div id="divParameterList"
							style="display: none; position: absolute;">
							<select name="externalParameterList" id="externalParameterList"
								multiple="multiple" size="4">
								<logic:present name="<%=OpdConfig.EXT_INV_PARAMETER_LIST%>">
								<logic:iterate name="<%=OpdConfig.EXT_INV_PARAMETER_LIST%>"
									id="list">
									<option value="<bean:write name='list' property='value'/>">
										<bean:write name='list' property='label' /></option>
								</logic:iterate>
								</logic:present>
							</select>
							
						</div>
						<!-- <input type='text' placeholder='' class='flexdatalist form-control'	data-min-length='1' list='externalTestList'	id ="FlexExternalTestList" name='externalTestList'/> -->
						<div align="center">
							<html:text name="EHRSection_ExternalInvestigationFB" styleClass="form-control" property="paraId"	onkeypress="return validateText(event)" onkeyup="gettext(event,this);" onblur=""
								tabindex="1"></html:text>
						</div>
					</td>
					
					<td width="30%" class="">
						<div align="center">
							<html:text name="EHRSection_ExternalInvestigationFB"
								styleClass="form-control" property="paraValue" maxlength="500" size="50" tabindex="1"></html:text>
						</div>
					</td>
						<td width="15%" class="">
						<div align="center">
							<%-- <his:date  name="recordDate" dateFormate="%d-%b-%Y" 
								value="<%=toDate %>"></his:date> --%>
								
								<input type="date" name="recordDate" value="" id="recordDateId" class="form-control" onchange="checkRecordDate()" style="width:150px;padding-top:1px;">
						</div>
					</td>
					<td width="15%" class="">
					<!-- <div class="row">
						<div align="center"> -->
						<div class="col-xl-1 col-lg-1 col-md-1 col-sm-1 col-xs-1">
						
						<html:text name="EHRSection_ExternalInvestigationFB" styleClass="form-control "
							style="width:45px "
								property="recordTimeHr" size="2" maxlength="2"
								onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
							
						</div>
							<div class="col-xl-1 col-lg-1 col-md-1 col-sm-1 col-xs-1"></div>	
								<div class="col-xl-1 col-lg-1 col-md-1 col-sm-1 col-xs-1">
								
								<html:text name="EHRSection_ExternalInvestigationFB" styleClass="form-control " style="width:45px;"
								property="recordTimeMin" size="2" maxlength="2"
								onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
								</div>
							<div class="col-xl-9 col-lg-9 col-md-9 col-sm-9 col-xs-9"></div>
						
					</td>
					<td width="5%" class="">
						<div align="center">
							<%-- <img class="button" id="addButton" style="cursor: pointer"
								src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>'
								onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm21('ADDROW') ;"
								onclick="if(validateAdd()) submitForm21('ADDROW')" tabindex="1"> --%>
								
								<button type="button"  id="addButtonForExternalInvestigation"  class="btn btn-info btn-sm" onkeypress="" onclick="addRowToTableExtInv();">Add</button>
						</div>
					</td>
				</tr>
			</table>
		<%-- </his:ContentTag> --%>
		 <%-- <%if(session.getAttribute(EHRConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL)!=null){ %>
					
						<table  width="100%" border="0" cellspacing="1" cellpadding="0" CLASS="table">
							<logic:iterate id="arrExtInv" name="<%=EHRConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL %>" type="ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO" indexId="idx">
							<%String ind=idx.toString(); %>
								<tr id="ExtInvRow#<%=idx.intValue()+2%>">
									<td class="" width="15%" >
										<div align="center">
											<%=arrExtInv.getRecordDate() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedRecordDate" value="<%=arrExtInv.getRecordDate() %>"/>
										</div>
									</td>
									<td class="" width="15%" >
										<div align="center">
											<%=arrExtInv.getRecordTime() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedRecordTime" value="<%=arrExtInv.getRecordTime() %>"/>
										</div>
									</td>
									<td class="" width="15%" >
										<div align="center">
											<%=arrExtInv.getSelTestName() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedParaId" value="<%=arrExtInv.getParaName() %>"/>
											<input name = "currentAddedTest" value = "<%=arrExtInv.getSelTestName() %>" type="hidden">
										</div>
									</td>
									<td class="" width="20%" >
										<div align="center">
											<%=arrExtInv.getParaName() %>
											<html:hidden name="EHRSection_ExternalInvestigationFB" property="currentAddedParaId" value="<%=arrExtInv.getParaName() %>"/>
										</div>
									</td>
									
									<td class="" width="30%" >
										<div align="center">
											<%=arrExtInv.getParaValue() %>
										</div>
									</td>
									<td class="" width="5%" >
										<div align="center">
											<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow(<%=idx.toString() %>) ;" onclick=" deleteRow(<%=idx.toString() %>)" tabindex="1">
											<button type="button"  id="revokeButton"  class="btn btn-info btn-sm"  onclick="deleteExtInvRow('<%= arrExtInv.getSlno()%>','<%=idx.toString()%>'); ">Delete</button>
										</div>	
									</td>
								</tr>	
							</logic:iterate>
						</table>
						
				<%} %>  --%>

		<%--  <his:SubTitleTag name="Test Conducted"> --%>
					<%-- <table width="100%" border="0"  cellspacing="1" cellpadding="0" >
						<tr style="background: linear-gradient(to bottom, #74a9d8 , white); font-weight:bold;">
						<td width = "20%">
						 <div align="left">
						 <font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											Test Conducted
										</b>	
									</font>
						 </div>
						  
						</td>
							<td width="80%" >
								<div align="left">
									<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="inHouse"/>
										</b>	
									</font>
									<html:radio name="EHRSection_ExternalInvestigationFB" property="testConductedFrom" value="<%=OpdConfig.TEST_CONDUCTED_IN_HOUSE%>" onclick="showLabInfo()" ></html:radio>
									<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="from"/>
											<bean:message key="extLab"/>
										</b>
									</font>	
									<html:radio name="EHRSection_ExternalInvestigationFB" property="testConductedFrom" value="<%=OpdConfig.TEST_CONDUCTED_FROM_EXT_LAB%>" onclick="showLabInfo()" ></html:radio>
								</div>
							</td>
						</tr>
					</table>		
				</his:SubTitleTag>
				<div id="extLabInfoId" style="display: none;">
					
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="">
									<div align="right" style="margin-top:10px;">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="labName"/>&nbsp;
										</font>	
									</div>
								</td>
								<td width="25%" class="">
									<div align="left" style="margin-top:10px;">
										<html:text name="EHRSection_ExternalInvestigationFB" property="extLabName" maxlength="50" size="30" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
									</div>
								</td>
								<td width="25%" class="">
									<div align="right" style="margin-top:10px;">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="labContactNo"/>&nbsp;
										</font>	
									</div>
								</td>
								<td width="25%" class="">
									<div align="left" style="margin-top:10px;">
										<html:text name="EHRSection_ExternalInvestigationFB" property="extLabContactNo" maxlength="30" onkeypress="return validateLabContact(event)"></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="">
									<div align="right" style="margin-top:12px;">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="labAddress"/>&nbsp;
										</font>	
									</div>
								</td>
								<td width="25%" class="">
									<div align="left" style="margin-top:12px;">
										<html:text name="EHRSection_ExternalInvestigationFB" property="extLabAdd" maxlength="50" size="30" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
									</div>
								</td>
								<td width="25%" class="">
								</td>
								<td width="25%" class="">
								</td>
							</tr>
						</table>		
					
				</div> --%>
			<%-- -</his:statusTransactionInProcess>		
			
			<his:ButtonToolBarTag>
				<logic:equal name="EHRSection_ExternalInvestigationFB" property="isDirectCall" value="DESK">	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>
				<logic:equal name="EHRSection_ExternalInvestigationFB" property="isDirectCall" value="DIRECT">
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) if(validateExtLabAdd()) submitForm21('SAVE')">
					</his:statusTransactionInProcess>	
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
					<his:statusNew>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO')">
					</his:statusNew>
					<his:statusTransactionInProcess>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
					</his:statusTransactionInProcess>
				</logic:equal>
			</his:ButtonToolBarTag> --%>

		<div id="sid" class="hisStyle.css"
			style="display: none; position: absolute;">
			<select id="selid" size="10" tabindex="1" onKeyDown="hideta(event)"
				onchange="onChangeDrop()" ondblclick="setClickedValue()"
				onclick="setClickedValue()">
				<option value="-1"></option>
			</select>
		</div>
		
		<div id="extTestId" class="hisStyle.css"
			style="display: none; position: absolute;">
			<select id="selExtTestId" size="10" tabindex="1" onKeyDown="hidetaForExtTestName(event)"
				onchange="onChangeDropForExtTest()" ondblclick="setClickedValue()"
				onclick="setClickedValueForExtTest()">
				<option value="-1"></option>
			</select>
		</div>

		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="hmode" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="tempMode" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="patCrNo" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="deleteIndex" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="sysDate" value="<%=toDate%>" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="hiddenTimeHr" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="hiddenTimeMin" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="testConductedFrom" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="isDirectCall" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="paraCode" />
		<html:hidden name="EHRSection_ExternalInvestigationFB"
			property="testCode" />	
			