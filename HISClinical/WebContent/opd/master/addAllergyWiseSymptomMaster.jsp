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


<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script>



function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Units
	
	if(listNo=="1")
	{
		source  = document.forms[0].symptomList;
		target = document.forms[0].selectedSymptoms;	
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
		source  = document.forms[0].symptomList;
		target = document.forms[0].selectedSymptoms;	
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
		target  = document.forms[0].symptomList;
		source = document.forms[0].selectedSymptoms;	
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
		target  = document.forms[0].symptomList;
		source = document.forms[0].selectedSymptoms;	
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
	if(document.forms[0].allergyTypeId && document.forms[0].allergyTypeId.value=="-1")
	{
		alert("Please Select Allergy Type ");
		document.forms[0].allergyTypeId.focus();
		return false;
	}
	if(document.forms[0].selectedSymptoms && document.forms[0].selectedSymptoms.options.length==0)
	{
		alert("Please Select Symptom");
		document.forms[0].symptomList.focus();
		return;
	}
	
		
	return true;
}

function FormValidate(mode)
{
	if(document.forms[0].selectedSymptoms && document.forms[0].selectedSymptoms.options.length==0)
	{
		alert("You Can't Modify This Record");
		document.forms[0].symptomList.focus();
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
	if(document.forms[0].symptomList)
	{	
		for(var i=0;i<document.forms[0].symptomList.options.length;i++)
			document.forms[0].symptomList.options[i].selected=false;
	}
	
	
	// Select All Symptoms in Selected
	if(document.forms[0].selectedSymptoms)
	{
		for(var i=0;i<document.forms[0].selectedSymptoms.options.length;i++)
			document.forms[0].selectedSymptoms.options[i].selected=true;
	}
		
}




function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function submitPage(mode)
{

	MoveToSelected();	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}



</script>

	<body>
		<html:form action="/master/AddAllergyWiseSymptomMaster.cnt">
			<html:hidden name="AllergyWiseSymptomMasterFB" property="hmode"/>
	
			<his:TransactionContainer>

			<his:TitleTag name="Allergy Wise Symptom Master">
			</his:TitleTag>
		<logic:notEqual name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFY">
		<logic:notEqual name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
		
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyType"/>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="AllergyWiseSymptomMasterFB" property="allergyTypeId" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.ALLERGY_TYPE%>">
									<html:options collection="<%=OpdConfig.ALLERGY_TYPE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>	
			</logic:notEqual>
			</logic:notEqual>	
			
			
			<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFY">
			
			<his:ContentTag>
			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyType"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
									<bean:write name="AllergyWiseSymptomMasterFB" property="allergyTypeName"/>
			                        	</b>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			
			<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
			
			<his:ContentTag>
			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyType"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
									<bean:write name="AllergyWiseSymptomMasterFB" property="allergyTypeName"/>
			                        	</b>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>
			<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="symptom"/>
								</font>
							</div>
						</td>
						
			
				<td width="50%"  class="tdfonthead">
							<div align="left">
								<html:select name="AllergyWiseSymptomMasterFB" property="selectedSymptoms" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>">
								<html:options collection="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
			</tr>
			
			</table>
			
			</logic:equal>
			
			<logic:notEqual name="AllergyWiseSymptomMasterFB" property="hmode" value="VIEW">
			<his:SubTitleTag name="Select Symptom">
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
									<bean:message key="symptom"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="AllergyWiseSymptomMasterFB" property="symptomList" multiple="true" size="6">
								<logic:notEqual name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFYSAVE">
								<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="ADD">
										<logic:present name="<%=OpdConfig.SYMPTOM_DESC_WHERE_SYMPTOM_TYPE_IS_ONE%>">
										<html:options collection="<%=OpdConfig.SYMPTOM_DESC_WHERE_SYMPTOM_TYPE_IS_ONE%>" property="value" labelProperty="label" />
										</logic:present>
								</logic:equal>
								<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="SAVE">
										<logic:present name="<%=OpdConfig.SYMPTOM_DESC_WHERE_SYMPTOM_TYPE_IS_ONE%>">
										<html:options collection="<%=OpdConfig.SYMPTOM_DESC_WHERE_SYMPTOM_TYPE_IS_ONE%>" property="value" labelProperty="label" />
										</logic:present>
								</logic:equal>
								</logic:notEqual>
								<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFY">
								<logic:present name="<%=OpdConfig.SYMPTOM_REMAINING_SYMPTOM_LIST%>">
								<html:options collection="<%=OpdConfig.SYMPTOM_REMAINING_SYMPTOM_LIST%>" property="value" labelProperty="label" />
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
								<html:select name="AllergyWiseSymptomMasterFB" property="selectedSymptoms" multiple="true" size="6">
								<logic:equal name="AllergyWiseSymptomMasterFB" property="hmode" value="MODIFY">
								<logic:present name="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>">
								<html:options collection="<%=OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST%>" property="value" labelProperty="label" />
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
				
					<logic:notEqual value="MODIFY" property="hmode" name="AllergyWiseSymptomMasterFB">
					<logic:notEqual value="VIEW" property="hmode" name="AllergyWiseSymptomMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="finalSubmit('SAVE')" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')">
					</logic:notEqual>
					</logic:notEqual>
				<logic:notEqual value="VIEW" property="hmode" name="AllergyWiseSymptomMasterFB">
					<logic:equal value="MODIFY" property="hmode" name="AllergyWiseSymptomMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="FormValidate('MODIFYSAVE')" onkeypress="if(event.keyCode==13) FormValidate('MODIFYSAVE')">
					</logic:equal>
					</logic:notEqual>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				<logic:notEqual value="VIEW" property="hmode" name="AllergyWiseSymptomMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
				</logic:notEqual>
    			</his:ButtonToolBarTag>
			<html:hidden name="AllergyWiseSymptomMasterFB" property="chk"/>
				<center><b><his:status/></b></center>

	</his:TransactionContainer>
		</html:form>
	</body>
</html>