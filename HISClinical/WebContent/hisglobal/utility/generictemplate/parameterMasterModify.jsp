<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import = "hisglobal.hisconfig.Config"%>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/parameterMasterAdd.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>


<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

</head>
<script>




var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

/*
* setValue() function is called in the callback function.This function is used to display the return value in the HTML control.In
* index.html page,the value is displayed in the "snomedcttext" textarea.The result includes- Concept id, Description-id and Description  
* param selectedSNOMEDTerm(var)-is the concept selected by the user from the search result list 
* 
*/
//Added by prachi
function validateAlphabetswithspecialchacters(e,obj)
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

	keychar = keychar.toUpperCase();
	//alert(key);
	//alert(keychar)
	//alert("indexof="+('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ').indexOf(keychar))
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./- ").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	
	else
	   return false;
}

function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	//var arr=selectedSNOMEDTerm.split(",");
	//var str=arr[0];
	//var str1=arr[1];
	//alert(str[0]); alert(str1);
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	
	document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
	//document.getElementsByName("paraName")[0].value=str; ----commented by prachi
		}
	else
		{
		document.getElementsByName("prefferedTerm")[0].value="";
		document.getElementsByName("conceptId")[0].value="";
		}
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


</script>


</script>
<body>
	<html:form action="/master/ParameterMaster.cnt">
	
		<html:hidden name="ParameterMasterFB" property="hmode"/>		
	<his:TransactionContainer>	
		<his:TitleTag name="Parameter Master>>Modify">
		</his:TitleTag>
		<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%"  class="tdfonthead" >
								<div align="right">
									
									<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#ff0000">*</font>
										<b><bean:message key="paraName"/></b>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<html:text name="ParameterMasterFB" property="paraName"  onkeypress="return validateAlphabetswithspecialchacters(event,this)">
								</html:text>
							</td>
						</tr>
						
						<tr>
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<!-- <font color="#ff0000">*</font> -->
									<b><bean:message key="snomedCtConceptId"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
						
						
				<%-- 	<html:text readonly="" name="ParameterMasterFB" property="prefferedTerm" ></html:text>
				 --%>	
				 	<html:hidden name="ParameterMasterFB" property="prefferedTerm" ></html:hidden>
					<html:hidden name="ParameterMasterFB" property="conceptId" ></html:hidden>
			<!-- 	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) selectSNOMEDCT('ACTIVE','','SYNONYMS','10',callbck_index);" onclick="selectSNOMEDCT('ACTIVE','','SYNONYMS','10',callbck_index);" title="Click to add SNOMED-CT Term">							
			 -->	
			 
			 
			 
			 	<div align="left" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:60%;color:#000000;" type="text">
					</div>
					 <div id="norecorddiv_1">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
					 </div>         
                     <div class="concept" id="conceptdiv_1">                 
                    </div>
                         </div>
					
					</div>
			 
			 
			 			
						</td>
					</tr>
						
						<tr>	
							<td width="25%"  class="tdfonthead" align="right">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraBound"/></b>
								</font>
								</div>
							</td>
							<td width="25%"  class="tdfont" >
								<html:select name="ParameterMasterFB" property="paraBound">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=GenericTemplateConfig.PARAMETER_BOUND_NON_PATIENT_CENTRIC %>">Non Patient Centric</html:option>
									<html:option value="<%=GenericTemplateConfig.PARAMETER_BOUND_PATIENT_CENTRIC %>">Patient Centric</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="25%"  class="tdfonthead" align="right">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraType"/></b> 
								</font>
								</div>
							</td>
							<td width="25%"  class="tdfont" align="right">
								<html:select name="ParameterMasterFB" property="paraType">
									<html:option value="-1">Select Value</html:option>
								 	<html:options collection="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_GROUP%>" property="templateGroupID" labelProperty="templateGroupName"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead"  width="25%" align="right">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#ff0000">*</font>
										<b><bean:message key="isActive"/></b> 
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<html:select name="ParameterMasterFB" property="isActive">
										<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option> 
										<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option> 
								</html:select>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onclick="validate('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validate('MODIFYSAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick="clearText()" onkeypress="if(event.keyCode==13) clearText()">
			</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
		</his:TransactionContainer>		
		<html:hidden name="ParameterMasterFB" property="paraId"/>
	</html:form>	
</body>
</html>