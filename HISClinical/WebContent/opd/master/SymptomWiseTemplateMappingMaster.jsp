<!-- 
/**
 * @author CDAC
 */
--> 
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<!--By Sandip on 20/06/17 for SNOMED CT implementation  -->

<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script>

var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

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
		 document.getElementsByName("isSnomedFlagValue")[0].value=1; 
		}
	if(document.getElementsByName("isSnomedConcept")[1].checked)
			{
		
		document.getElementById("divSnomed").style.display="none";
	document.getElementsByName("isSnomedFlagValue")[0].value=0; 
			
		}
		
	
}


function validate(mode)
{	
			if(document.getElementsByName("hmode")[0].value == "ADD")
			{
				alert("dfdg")
				if(document.getElementsByName("isSnomedFlagValue")[0].value==1)
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
			submitForm(mode);
		
	
}
function clearText(){
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
{	//alert("setvalue");
	//alert("selectedSNOMEDTerm:"+selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
		//alert("if");
	
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
		//alert(str);
		//alert(str1);

	document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
		}
	else
		{
		document.getElementsByName("prefferedTerm")[0].value="";
		document.getElementsByName("conceptId")[0].value="";
		}
}




window.onload = function()
{
	//if(document.getElementsByName("hmode")[0].value =="ADD")
	{
	//document.getElementsByName("isSnomedConcept")[0].checked=true;
 	//document.getElementsByName("isSnomedFlagValue")[0].value=1; 
	}
	load1('1','');
	
	//if(document.getElementsByName("hmode")[0].value =="MODIFY")
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






function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].templateList;
		target = document.forms[0].selectedTemplate;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}




function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].templateList;
		target = document.forms[0].selectedTemplate;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}




function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}




function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].templateList;
		source = document.forms[0].selectedTemplate;	
	}
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}




function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].templateList;
		source = document.forms[0].selectedTemplate;	
	}
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;
	
	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
	
		targetlen = target.length;							
		target.length = (targetlen+1);				
		
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}

	deleteThis(target,source);
}


function validateFinalSubmit()
{	
	//alert("validate");
	if(document.forms[0].selectedTemplate && document.forms[0].selectedTemplate.options.length==0)
	{
		alert("Please Select Template");
		document.forms[0].templateList.focus();
		return;
	}
	
		
	return true;
}

function FormValidate(mode)
{
	if(document.forms[0].selectedTemplate && document.forms[0].selectedTemplate.options.length==0)
	{
		alert("You Can't Modify This Record");
		document.forms[0].templateList.focus();
		return;
	}
	else
	{
		submitPage(mode);
	}
	
}



function MoveToSelected()
{
	
	 //Unselect Remaining Symptoms
	if(document.forms[0].templateList)
	{	//alert("if");
		for(var i=0;i<document.forms[0].templateList.options.length;i++)
			document.forms[0].templateList.options[i].selected=false;
	}
	
	
	// Select All Symptoms in Selected
	if(document.forms[0].selectedTemplate)
	{
		//alert("if2");
		for(var i=0;i<document.forms[0].selectedTemplate.options.length;i++)
			document.forms[0].selectedTemplate.options[i].selected=true;
	}
		
}




function finalSubmit(mode)
{
	//alert("finalsubmit");
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function submitPage(mode)
{
//alert("submitpage");
	MoveToSelected();	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}



</script>

	<body>
		<html:form action="/master/AddSymptomWiseTemplateMatser.cnt">
			<html:hidden name="SymptomWiseTemplateMasterFB" property="hmode"/>
	
			<his:TransactionContainer>

			<his:TitleTag name="Symptom Wise Template Mapping Master">
			</his:TitleTag>
		<logic:notEqual name="SymptomWiseTemplateMasterFB" property="hmode" value="MODIFY">
		<logic:notEqual name="SymptomWiseTemplateMasterFB" property="hmode" value="VIEW">
		
			<his:ContentTag>
		
               <table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 <font color="#ff0000">*</font>
									<b><bean:message key="snomedCtId"/>:</b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
						
						
				 	<html:hidden name="SymptomWiseTemplateMasterFB" property="prefferedTerm" ></html:hidden>
					<html:hidden name="SymptomWiseTemplateMasterFB" property="conceptId" ></html:hidden>
			
			 
			 
			 	<div align="left" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:60%;color:#000000;" type="text">
					</div>
					 <div id="norecorddiv_1">
<!-- 					 <label style="display: inline;" id="reccnt">No. of records : </label> -->
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
				</table>
			</his:ContentTag>	
			</logic:notEqual>
			</logic:notEqual>	
			
			
			<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="MODIFY">
			
			<his:ContentTag>
			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="snomedCtId"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
									<bean:write name="SymptomWiseTemplateMasterFB" property="symptomTypeName"/>
			                        	</b>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			
			<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="VIEW">
			
			<his:ContentTag>
			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="snomedCtId"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
									<bean:write name="SymptomWiseTemplateMasterFB" property="symptomTypeName"/>
			                        	</b>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="VIEW">
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tmpl"/>
								</font>
							</div>
						</td>
						
			
				<td width="50%"  class="tdfonthead">
							<div align="left">
								<html:select name="SymptomWiseTemplateMasterFB" property="selectedTemplate" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.SYMPTOM_SELECTED_Template_LIST_NEW%>">
								<html:options collection="<%=OpdConfig.SYMPTOM_SELECTED_Template_LIST_NEW%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
			</tr>
			
			</table>
			
			</logic:equal>
			
			
			<logic:notEqual name="SymptomWiseTemplateMasterFB" property="hmode" value="VIEW">
			<his:SubTitleTag name="Select Template">
			</his:SubTitleTag>
			<his:ContentTag>
			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="tmpl"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="SymptomWiseTemplateMasterFB" property="templateList" multiple="true" size="6">
								<logic:notEqual name="SymptomWiseTemplateMasterFB" property="hmode" value="MODIFYSAVE">
								<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="ADD">
										<logic:present name="<%=OpdConfig.Template_List%>">
										<html:options collection="<%=OpdConfig.Template_List%>" property="value" labelProperty="label" />
										</logic:present>
								</logic:equal>
								<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="SAVE">
										<logic:present name="<%=OpdConfig.Template_List%>">
										<html:options collection="<%=OpdConfig.Template_List%>" property="value" labelProperty="label" />
										</logic:present>
								</logic:equal>
								</logic:notEqual>
								<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="MODIFY">
								<logic:present name="<%=OpdConfig.SYMPTOM_REMAINING_Template_LIST_NEW%>">
								<html:options collection="<%=OpdConfig.SYMPTOM_REMAINING_Template_LIST_NEW%>" property="value" labelProperty="label" />
								</logic:present>
								</logic:equal>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="SymptomWiseTemplateMasterFB" property="selectedTemplate" multiple="true" size="6">
								<logic:equal name="SymptomWiseTemplateMasterFB" property="hmode" value="MODIFY">
								<logic:present name="<%=OpdConfig.SYMPTOM_SELECTED_Template_LIST_NEW%>">
								<html:options collection="<%=OpdConfig.SYMPTOM_SELECTED_Template_LIST_NEW%>" property="value" labelProperty="label" />
								</logic:present>
								</logic:equal>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
				</his:ContentTag>
			</logic:notEqual>
			
	
	
	
	
	
	<his:ButtonToolBarTag>
				
					<logic:notEqual value="MODIFY" property="hmode" name="SymptomWiseTemplateMasterFB">
					<logic:notEqual value="VIEW" property="hmode" name="SymptomWiseTemplateMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="finalSubmit('SAVE')" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')">
					</logic:notEqual>
					</logic:notEqual>
				<logic:notEqual value="VIEW" property="hmode" name="SymptomWiseTemplateMasterFB">
					<logic:equal value="MODIFY" property="hmode" name="SymptomWiseTemplateMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="FormValidate('MODIFYSAVE')" onkeypress="if(event.keyCode==13) FormValidate('MODIFYSAVE')">
					</logic:equal>
					</logic:notEqual>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				<logic:notEqual value="VIEW" property="hmode" name="SymptomWiseTemplateMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
				</logic:notEqual>
    			</his:ButtonToolBarTag>
			<html:hidden name="SymptomWiseTemplateMasterFB" property="chk"/>
			<html:hidden name="SymptomWiseTemplateMasterFB" property="symptomTypeName"/>
			
			
<%-- 		<html:hidden name="SymptomWiseTemplateMasterFB" property="hmode"/> --%>
							
				<center><b><his:status/></b></center>
				
		<table width="80%" cellspacing="1" cellpadding="0">
			<tbody><tr>
				<td class="MasterXMLDataLabel" style="vertical-align: middle">
					<div style="vertical-align: middle" align="right">
						</div></td></tr></tbody></table>
						
						
	</his:TransactionContainer>
	
		</html:form>
	</body>
</html>