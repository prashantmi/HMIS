
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script type="text/javascript">
function load1(elmtId,semantictag)
{

	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	if(elmtId=="1")
		{
		var callbck_index =function(ret_OUT){setValue(ret_OUT);};
		}
	
	
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	if(elmtId==null || elmtId==undefined)
		{
		elmtId="1"; semantictag="";
	
		}
	//elmtId="2";
	//alert(elmtId);
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null', elmtId,callbck_index);

	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
	

	
}

function showSnomed()
{
	
	if(document.getElementsByName("isSnomedConcept")[0].checked)
		{
		document.getElementById("divSnomed").style.display="";
		 document.getElementsByName("isSnomedFlagValue")[0].value="<%=GenericTemplateConfig.SNOMED_CT_YES %>"; 
		}
	if(document.getElementsByName("isSnomedConcept")[1].checked)
			{
		
		document.getElementById("divSnomed").style.display="none";
	document.getElementsByName("isSnomedFlagValue")[0].value="<%=GenericTemplateConfig.SNOMED_CT_NO %>"; 
			
		}
		
	
}


function validate(mode)
{
	//alert("hi");
	//alert(document.getElementsByName("hmode")[0].value)
	if(document.getElementsByName('paraName')[0].value=="")
	{
		alert("Enter the Parameter Name ...");
		document.getElementsByName('paraName')[0].focus();
		return false;
	}
	
	if(document.getElementsByName('paraType')[0].value=="-1")
	{
		alert("Select Parameter Type ...");
		document.getElementsByName('paraType')[0].focus();
		return false;
	} 
	//alert(document.getElementsByName("hmode")[0].value)
	
			if(document.getElementsByName("hmode")[0].value == "ADD")
			{
				alert("dfdg")
				if(document.getElementsByName("isSnomedFlagValue")[0].value=="<%=GenericTemplateConfig.SNOMED_CT_YES %>")
				{
					if(document.getElementsByName('prefferedTerm')[0].value=="" || document.getElementsByName('conceptId')[0].value=="" )
					{
						alert("Select Snomed");
						document.getElementsByName('txt-snomed-ct-search_1')[0].focus();
						return false;
					}
				}
			}
			
			if(document.getElementsByName("hmode")[0].value =="MODIFY")
			{
				/*if(document.getElementsByName('prefferedTerm')[0].value != document.getElementsByName('paraName')[0].value)
					{
					document.getElementsByName('prefferedTerm')[0].value="";
					document.getElementsByName('conceptId')[0].value="";
					
					}*/
				if(document.getElementsByName('txt-snomed-ct-search_1')[0].value=="")			
			     {
				 document.getElementsByName('prefferedTerm')[0].value="";
			     document.getElementsByName('conceptId')[0].value="";
			      
			     }
			
			     
		    }
			submitForm21(mode);
		
	
}
function clearText(){
	document.getElementsByName('paraName')[0].value="";
//	document.getElementsByName('paraBound')[0].value="-1";
	document.getElementsByName('paraType')[0].value="-1";
	//document.getElementsByName('isActive')[0].value="-1";
	
	document.getElementsByName('prefferedTerm')[0].value="";
	document.getElementsByName('txt-snomed-ct-search_1')[0].value="";
	document.getElementsByName('conceptId')[0].value="";
	
	
}


//Parameter Master Javascript Functions ****************************************************

var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};


function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	/*var arr=selectedSNOMEDTerm.split(",");
	var str=arr[0];
	var str1=arr[1];
	*///alert(str[0]); alert(str1);
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;

	document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
	document.getElementsByName("paraName")[0].value=str;
		}
	else
		{
		document.getElementsByName("prefferedTerm")[0].value="";
		document.getElementsByName("conceptId")[0].value="";
		}
}




window.onload = function()
{
	if(document.getElementsByName("hmode")[0].value =="ADD")
	{
	document.getElementsByName("isSnomedConcept")[0].checked=true;
 document.getElementsByName("isSnomedFlagValue")[0].value="<%=GenericTemplateConfig.SNOMED_CT_YES %>"; 
	}
	load1('1','');
	
	if(document.getElementsByName("hmode")[0].value =="MODIFY")
	{
		document.getElementsByName("txt-snomed-ct-search_1")[0].value=document.getElementsByName("prefferedTerm")[0].value;
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
<title>Intake Output Para Master</title>

<body>
	<html:form action="/master/intakeOutputParaMstACT">
		<his:TransactionContainer>
	
		
				<his:TitleTag name="Intake Output Para Master >> MODIFY">
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
								<html:text name="IntakeOutputParaMasterFB" property="paraName"  onkeypress="return validateAlphabetsOnly(event,this)" size="40">
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
						
						
				<%-- 	<html:text readonly="" name="IntakeOutputParaMasterFB" property="prefferedTerm" ></html:text>
				 --%>	
				 	<html:hidden name="IntakeOutputParaMasterFB" property="prefferedTerm" ></html:hidden>
					<html:hidden name="IntakeOutputParaMasterFB" property="conceptId" ></html:hidden>
			<!-- 	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);" onclick="selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);" title="Click to add SNOMED-CT Term">							
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
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraType"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">		
							<html:select name="IntakeOutputParaMasterFB"  property="paraType">
							<html:option value="-1">Select Value</html:option>
								 <html:option value="1">Intake</html:option>
								<html:option value="3">Outtake</html:option>
								<html:option value="2">Intake and Outtake</html:option> 
								 
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
								<html:select name="IntakeOutputParaMasterFB" property="isActive">
										<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option> 
										<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option> 
								</html:select>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onclick="validate('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validate('MODIFYSAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick="clearText()" onkeypress="if(event.keyCode==13) clearText()">
			</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
			<%-- <his:ButtonToolBarTag>
			<logic:equal name="IntakeOutputParaMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validate('SAVE')" onkeypress="if(event.keyCode==13) validate('SAVE')">
			</logic:equal>
			
			<logic:equal name="IntakeOutputParaMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm('MODIFYSAVE')">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			
			<logic:equal name="IntakeOutputParaMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearText()" onkeypress="if(event.keyCode==13) clearText()">
			</logic:equal>
			<logic:equal name="IntakeOutputParaMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearText()" onkeypress="if(event.keyCode==13) clearText()">
			</logic:equal>
				
			</his:ButtonToolBarTag> --%>
			
			
			<html:hidden name="IntakeOutputParaMasterFB" property="hmode"/>
			
						
		</his:TransactionContainer>
		<html:hidden name="IntakeOutputParaMasterFB" property="inTakeOutParaId"/>
		<html:hidden name="IntakeOutputParaMasterFB" property="slNo"/>
		
	</html:form>
</body>
</html>			