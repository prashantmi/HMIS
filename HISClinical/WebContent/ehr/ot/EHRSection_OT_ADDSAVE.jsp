<!-- 
@Developed by : Vasu
Date : 14.Feb.2019
Purpose: OT section at SPD
Validated By :
Remarks:
 -->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="ehr.treatmentdetail.dataentry.EHRSection_TreatmentFB"%>
<%@page import="ehr.ot.presentation.EHRSection_OTDetailsFB" %>
<%@page import ="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO" %>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>
<%@page import="ehr.treatmentdetail.vo.EHRSection_TreatmentVO"%>
<%@page import="ehr.ot.vo.EHRSection_OTDetailsVO"%>

<his:javascript src="/hisglobal/js/wz_tooltip.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/ehr/js/EHR_spp_operationDetails.js" />

    
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtool.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<!-- <script type="text/javascript" src="/HISClinical/opd/selectsize/js/standalone/selectize.js"></script> -->

<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link href="https://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
 -->
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">

<script>

/* function validate_ENC_OT_DTL()
{
	//alert("inside OT section validations");
	var previousOperationName = document.getElementsByName("prevOperation")[0].value;
	//alert(previousOperationName);

	var operationCombo = document.getElementsByName('operations')[0];
	var selectedOperationName = operationCombo.options[operationCombo.selectedIndex].text;
	
	//alert(selectedOperationName);
	if(previousOperationName == selectedOperationName)
		{ 
		  alert("operation already added");
		  return false;
		}
	return true; 
}
 */</script>
</head>
<script type="text/javascript">
var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

window.onload= function()
{
	//selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','1',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','2',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','3',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','4',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','5',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','6',callbck_index);
}


function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    clear(id);
        
});

function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	var targetElement=document.getElementsByName("targetId")[0].value;
	var targetPrefferedTerm="";
	var targetConceptId="";
	//alert(targetElement);
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{

	 var str=selectedSNOMEDTerm.term;
	 var str1=selectedSNOMEDTerm.conceptId;
	
	
	if(targetElement == "operativeFindings") // Operative Findings
	{
		 targetPrefferedTerm=document.getElementsByName("snomdPTOperativeFindings")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdOperativeFindings")[0];   //concept Id
		 document.getElementsByName("operativeFindings")[0].value=document.getElementsByName("operativeFindings")[0].value + ' ' + str;
		
	}
	 if(targetElement == "operationSummary") // Operation Summary
	{
		  targetPrefferedTerm=document.getElementsByName("snomedPTOperationSummary")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomedCidOperationSummary")[0];  //concept Id
		  document.getElementsByName("operationSummary")[0].value=document.getElementsByName("operationSummary")[0].value + ' ' + str;
			
	}


	 if(targetElement == "operationImplant") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationImplantSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationImplantSummary")[0];  //concept Id
			  document.getElementsByName("operationImplant")[0].value=document.getElementsByName("operationImplant")[0].value + ' ' + str;
				
		}

	 if(targetElement == "operationPreOp") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationPreOpSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationPreOpSummary")[0];  //concept Id
			  document.getElementsByName("operationPreOp")[0].value=document.getElementsByName("operationPreOp")[0].value + ' ' + str;
				
		}

	 if(targetElement == "operationPostOp") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationPostOpSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationPostOpSummary")[0];  //concept Id
			  document.getElementsByName("operationPostOp")[0].value=document.getElementsByName("operationPostOp")[0].value + ' ' + str;
				
		}
	 
	    if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "# " + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "# " + str1;
		
	    document.getElementsByName("targetId")[0].value="";
	    targetPrefferedTerm="";
	    targetConceptId="";
	
}
}
function setTarget(id)
{
	if(id=="operativeFindings")
	{
	document.getElementsByName("targetId")[0].value="operativeFindings";}
	if(id=="operationSummary")
	{
	document.getElementsByName("targetId")[0].value="operationSummary";}

	if(id=="operationImplant")
	{
	document.getElementsByName("targetId")[0].value="operationSummary";}
	if(id=="operationPreOp")
	{
	document.getElementsByName("targetId")[0].value="operationPreOp";}
	if(id=="operationPostOp")
	{
	document.getElementsByName("targetId")[0].value="operationPostOp";}
	
}

function setfreeText()
{
	document.getElementsByName("operativeFindings")[0].value=document.getElementsByName("search_2")[0].value;//for free text
	document.getElementsByName("operationSummary")[0].value=document.getElementsByName("search_3")[0].value;
	//alert(document.getElementsByName("operationSummary")[0].value);
   document.getElementsByName("operationImplant")[0].value=document.getElementsByName("search_4")[0].value;//for free text
	document.getElementsByName("operationPreOp")[0].value=document.getElementsByName("search_5")[0].value;
	document.getElementsByName("operationPostOp")[0].value=document.getElementsByName("search_6")[0].value;//for free text
	
	
}

function clear(id)
{
	//alert(id); 
	var targetPrefferedTerm ="";
	var targetConceptId="";
	var elmtid=id.split('_');
	var trgt=elmtid[1];
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 document.getElementById(id).value="";
	 
	 if(trgt=="1")// Operative Findings
		{
			 targetPrefferedTerm=document.getElementsByName("snomdPTOperativeFindings")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdOperativeFindings")[0];   //concept Id
			 document.getElementsByName("operativeFindings")[0].value="";
			 
		}
	 if(trgt=="2") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationSummary")[0];  //concept Id
			  document.getElementsByName("operationSummary")[0].value="";

		}

	 if(trgt=="4") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationImplantSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationImplantSummary")[0];  //concept Id
			  document.getElementsByName("operationImplant")[0].value="";

		}

	 if(trgt=="5") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationPreOpSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationPreOpSummary")[0];  //concept Id
			  document.getElementsByName("operationPreOp")[0].value="";

		}

	 if(trgt=="6") // Operation Summary
		{
			  targetPrefferedTerm=document.getElementsByName("snomedPTOperationPostOpSummary")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomedCidOperationPostOpSummary")[0];  //concept Id
			  document.getElementsByName("operationPostOp")[0].value="";

		}
		 

		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}
function selectValue(value, callback) 
{
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}

window.document.onload = function ()
{
	document.getElementsByName("operationDate")[0].valueAsDate = new Date();
}

$(document).ready(function(){
	 $('#operationDate').attr('value',setCurrentDate());
}); 

function setCurrentDate()
{
	
			var today = new Date(); 
			return today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);		
}

/* $(function () {
    $('[name="operations"]').multiselect({
        includeSelectAllOption: true
    });
});    */  

 /* $('[name="operations"]').selectize({
    maxItems: 3
});  */



/* $(function () { 
    $('[name="operations"]').multiselect({       
        buttonText: function(options, select) {
            console.log(select[0].length);
            if (options.length === 0) {
                return 'None selected';
            }
            if (options.length === select[0].length) {
                return 'All selected ('+select[0].length+')';
            }
            else if (options.length >= 4) {
                return options.length + ' selected';
            }
            else {
                var labels = [];
                console.log(options);
                options.each(function() {
                    labels.push($(this).val());
                });
                return labels.join(', ') + '';
            }
        }
    
    });
});

 */
</script>


<script>
	var text = '';
	function multiSelectKeyUp(e)
	{
		if($('#myList option').filter(function(){
	 		   return $(this).val() === $(e).val();       
	 		  }).length)
		{ 
			text += $(e).val();
			text += ';'; 
			$('#results').text(text);
			$(e).val(''); 
		} 
	} 
</script>





<STYLE TYPE="text/css">
	.highlight {
		line-height: 16px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		padding-top: 1px;
		vertical-align: middle;
		padding-left: 6px;
   		/*background:#FFEBD5;*/
   		background:#96BAEA;
   		   		
   		border-style:solid;
		border-width:1px;
		
	
   		}
</STYLE>
<STYLE TYPE="text/css">
	.dehighlite {
	font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: black;
   /* background-color: #FFEBD5;*/
    background-color: #E0EBEB;
    white-space: nowrap;
    background:#E0EBEB;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>


 <%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
<%--  <html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="<%=sysDate%>"/> --%>

<input name="entryDate" value="<%=sysDate%>" type="hidden"/>
 <his:statusTransactionInProcess> 
		
 		<%-- <html:hidden name="EHRSection_TreatmentFB" property="drugDetailRows" /> --%>
		<table id="operationTable" class="table table-condensed table-responsive">
			<tr>
					<td style="font-size:1.2em;font-weight:bold;"width="100%" colspan="2">
	Operation Details&nbsp;:
	<img id="ImageRefreshForServiceProcedure" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;display:none; cursor:hand;"/> 
	</td>
			</tr>
					<tr>
					<td width="15%"  >
						<div align="right">
							
								<font color="#FF0000">*</font>
								<b>
									Operation Date
								</b>
								
						</div>
					</td>
					<td width="85%"  >
						<div align="left">
							  <input type="date" class="form-control" style="width:150px;padding-top:1px;" id="operationDate" name="operationDate">
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%"  >
						<div align="right">
							
								<font color="#FF0000">*</font>
								<b>
									Operation
								</b>
								
						</div>
					</td>
					<td width="85%"  >
						<div align="left">
							  <html:select name="EHRSection_OTDetailsFB" property="operations" styleClass="form-control" style="width:150px;" tabindex="1" onchange="setOperationName()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=EHRConfig.OPERATIONS_LIST %>" >
									<html:options collection="<%=EHRConfig.OPERATIONS_LIST  %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select> 
							
														
						</div>
						
						<div align = "left">
						 <p id = "operationName">
						</div>
						<div align = "left">
						 <p id = "operationCode" style="display:none">
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" >
						<div align="right">
							
								<font color="#FF0000">*</font>
								<b>
									Surgeon
								</b>
							
						</div>
					</td>
					<td width="85%"  >
						<div align="left">
							   <html:select name="EHRSection_OTDetailsFB" property="surgeons" tabindex="1" styleClass="form-control"  style="width:150px;" onchange="setSurgeonName()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=EHRConfig.SURGEON_LIST %>" >
									<html:options collection="<%=EHRConfig.SURGEON_LIST  %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
						<div align = "left">
						 <p id = "surgeonName">
						</div>
						<div align = "left">
						 <p id = "surgeonCode" style="display:none">
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%" >
						<div align="right">
							
								<b>
									Operative Findings
								</b>
								
						</div>
					</td>
					<td width="85%" >
						<div align="left">
							   <html:hidden name="EHRSection_OTDetailsFB" property="snomdPTOperativeFindings" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="snomdCIdOperativeFindings" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="operativeFindings" ></html:hidden>  
					           
					        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="search_2" name="search_2" maxlength="2000" onkeypress="return validateText(event)" onfocus="setTarget('operativeFindings');"onchange="setfreeText()"  autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_2').val('');" class="btn btn-sm btn-default BtnCleanOT"><i class="fa fa-times"></i></button>
							</div>
                        </div>	
                        
                           <!-- <textarea  id="search_2" class="form-control" name="search_2" tabindex="1" maxlength = "2000" style="width:80%;color:#000000;" autocomplete="off" rows="1" cols="20"  onfocus="setTarget('operativeFindings');"onchange="setfreeText()" ></textarea> -->
						</div>
					</td>
				</tr>
				<tr>
					<td width="15%"  >
						<div align="right">
							
								<b>
									Complications(if any)
								</b>
							
					</td>
					<td width="85%" >
						<div align="left">
							   <html:hidden name="EHRSection_OTDetailsFB" property="snomedPTOperationSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="snomedCidOperationSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="operationSummary" ></html:hidden> 
					        
					        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="clearable ui-autocomplete-input x onX pull-right form-control" id="search_3" name="search_3" maxlength="2000" onkeypress="return validateText(event)" onchange="setPresentHistory();" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','3',callbck_index4);"  autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_3').val('');" class="btn btn-sm btn-default BtnCleanDiagnosis"><i class="fa fa-times"></i></button>
							</div>
                        </div>
						 <!-- <textarea  id="txt-snomed-ct-search_3" class="form-control" name="txt-snomed-ct-search_3"  maxlength = "2000" tabindex="1"  style="width:80%;color:#000000;" autocomplete="off" rows="1" cols="20"  onfocus="setTarget('operationSummary');" onchange="setfreeText()"></textarea> -->
						 </div>
					</td>
				</tr>
				
				
				<tr>
					<td width="15%" >
						<div align="right">
							
								<b>
									Implant
								</b>
								
						</div>
					</td>
					<td width="85%" >
						<div align="left">
							 <html:hidden name="EHRSection_OTDetailsFB" property="snomedPTOperationImplantSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="snomedCidOperationImplantSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="operationImplant" ></html:hidden> 
					           
					        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="search_4" name="search_4" maxlength="2000" onkeypress="return validateText(event)" onfocus="setTarget('operationImplant');"onchange="setfreeText()"  autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_4').val('');" class="btn btn-sm btn-default BtnCleanOT"><i class="fa fa-times"></i></button>
							</div>
                        </div>	
                        
                           <!-- <textarea  id="search_2" class="form-control" name="search_2" tabindex="1" maxlength = "2000" style="width:80%;color:#000000;" autocomplete="off" rows="1" cols="20"  onfocus="setTarget('operativeFindings');"onchange="setfreeText()" ></textarea> -->
						</div>
					</td>
				</tr>
				
				
				<tr>
					<td width="15%" >
						<div align="right">
							
								<b>
									Pre-op Preparation
								</b>
								
						</div>
					</td>
					<td width="85%" >
						<div align="left">
							 <html:hidden name="EHRSection_OTDetailsFB" property="snomedPTOperationPreOpSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="snomedCidOperationPreOpSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="operationPreOp" ></html:hidden> 
					        
					           
					        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="search_5" name="search_5" maxlength="2000" onkeypress="return validateText(event)" onfocus="setTarget('operationPreOp');"onchange="setfreeText()"  autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_5').val('');" class="btn btn-sm btn-default BtnCleanOT"><i class="fa fa-times"></i></button>
							</div>
                        </div>	
                        
                           <!-- <textarea  id="search_2" class="form-control" name="search_2" tabindex="1" maxlength = "2000" style="width:80%;color:#000000;" autocomplete="off" rows="1" cols="20"  onfocus="setTarget('operativeFindings');"onchange="setfreeText()" ></textarea> -->
						</div>
					</td>
				</tr>
				
				
				<tr>
					<td width="15%" >
						<div align="right">
							
								<b>
									Post-op Hospital Stay Course
								</b>
								
						</div>
					</td>
					<td width="85%" >
						<div align="left">
							 <html:hidden name="EHRSection_OTDetailsFB" property="snomedPTOperationPostOpSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="snomedCidOperationPostOpSummary" ></html:hidden>
					        <html:hidden name="EHRSection_OTDetailsFB" property="operationPostOp" ></html:hidden> 
					        
					        
					           
					        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="search_6" name="search_6" maxlength="2000" onkeypress="return validateText(event)" onfocus="setTarget('operationPostOp');"onchange="setfreeText()"  autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_6').val('');" class="btn btn-sm btn-default BtnCleanOT"><i class="fa fa-times"></i></button>
							</div>
                        </div>	
                        
                           <!-- <textarea  id="search_2" class="form-control" name="search_2" tabindex="1" maxlength = "2000" style="width:80%;color:#000000;" autocomplete="off" rows="1" cols="20"  onfocus="setTarget('operativeFindings');"onchange="setfreeText()" ></textarea> -->
						</div>
					</td>
				</tr>
				
				
			
				
				</table>
				
 				<logic:notEmpty name="<%=EHRConfig.PREV_OT_DETAIL_LIST%>">
 				 <p align = "left"> <font><b>Previous OT Details</b></font></p>
 				 <table class="table table-condensed table-responsive"   width = "100%">
 				 <tr>
				<!-- <td width="5%"  class="tdfonthead">
					<div align="center">
					<input type ="checkbox" name = "selectChk" checked>	
 				</div>
				</td> -->
				<td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
							<b><font color="#FF0000">*</font>Operation Date</b>
						</font>
					</div>
				</td>
				<td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>Operation</b>
						</font>
					</div>
				</td>
				<td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
							<b>Surgeon</b>
						</font>
					</div>
				</td>
				<td width="15%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font>Operative Findings</b>
						</font>
					</div>
				</td>
				 <td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Complications</b>
						</font>
					</div>
				</td> 
				
				 <td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Implant</b>
						</font>
					</div>
				</td> 
				 <td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Pre-op Preparation</b>  
						</font>
					</div>
				</td> 
				 <td width="10%"  >
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Post-op Hospital Stay Course</b>
						</font>
					</div>
				</td> 
				
				<td width="5%"  >
					<div align="center">	           
						
					</div>
				</td> 
		
			</tr>
 				 
				<logic:iterate id="prevOTDtlVO" indexId="idx" name="<%=EHRConfig.PREV_OT_DETAIL_LIST%>" type="ehr.ot.vo.EHRSection_OTDetailsVO">
				<%String ind=idx.toString(); %>
				<tr id="otRow#<%=idx.intValue()+2%>">
				    <td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selOperationDate"/>
							</b></font>
						</div> 
					</td>
					<td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<input name='prevOperation' value='<%=prevOTDtlVO.getSelOperationName()%>' type='hidden'></input>
							 <bean:write name="prevOTDtlVO" property="selOperationName"/>
							</b></font>
						</div> 
					</td>
					<td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selSurgeonName"/>
							</b></font>
						</div> 
					</td>
					<td width="15%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selOperativeFindings"/>
							</b></font>
						</div> 
					</td>
					<td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selOperatonComplications"/>
							</b></font>
						</div> 
					</td>
					
					<td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selOperatonImplant"/>
							</b></font>
						</div> 
					</td>
					<td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selOperatonPreOp"/>
							</b></font>
						</div> 
					</td>
					<td width="10%"  >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							 <bean:write name="prevOTDtlVO" property="selOperatonPostOp"/>
							</b></font>
						</div> 
					</td>
					
					<td width="5%"  >
						<div align="center">
						  <button type="button"  id="revokeButton"  class="btn btn-info btn-sm"  onclick="deleteOperations('<%= prevOTDtlVO.getSlno()%>','<%=idx.toString()%>')">Delete</button>
						</div> 
					</td>
				</tr>
				</logic:iterate>
				</table>
				</logic:notEmpty> 
		
 		<script>
 		
 		</script>
</his:statusTransactionInProcess>

<input type="hidden" name="targetId" />
 <html:hidden name="EHRSection_OTDetailsFB" property="patCrNo"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="episodeCode"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="episodeVisitNo"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="admissionNo"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="operationCode"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="surgeonCode"></html:hidden>
 
  <html:hidden name="EHRSection_OTDetailsFB" property="selOperationName"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="selSurgeonName"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="selSergeonCode"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="selOperationCode"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="selOperativeFindings"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="selOperationDate"></html:hidden> 
 

  <html:hidden name="EHRSection_OTDetailsFB" property="selOperatonImplant"></html:hidden>
 <html:hidden name="EHRSection_OTDetailsFB" property="selOperatonPostOp"></html:hidden> 
 <html:hidden name="EHRSection_OTDetailsFB" property="selOperatonPreOp"></html:hidden> 
 
  <input type="hidden" name = "prevOperation"/> 


