
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page info="Used for Department Counter" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.hisconfig.*" %>
<%@page import="registration.RegistrationConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="hisglobal/js/utilityFunction.js" />

<script>





function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Billing category
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].unit;
		target = document.forms[0].selectedUnit;	
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
		source  = document.forms[0].unit;
		target = document.forms[0].selectedUnit;	
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
		target  = document.forms[0].unit;
		source = document.forms[0].selectedUnit;	
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
		target  = document.forms[0].unit;
		source = document.forms[0].selectedUnit;	
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

function MoveToSelected()
{
	// Select All Shift in Selected
	if(document.forms[0].unit)
		for(var i=0;i<document.forms[0].selectedUnit.length;i++)
			document.forms[0].selectedUnit.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].selectedUnit)
	{	
		for(var i=0;i<document.forms[0].unit.options.length;i++)
			document.forms[0].unit.options[i].selected=false;
	}
}


function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode)
{
	if(comboValidation(document.getElementsByName('counter')[0],"Counter"))
	{
		return true;
	}
	else{
		return false;
	}
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit(mode)) 
	return;
	submitPage(mode);
	
}
function clearForm(){

	document.getElementsByName('counter')[0].value="-1";
	document.getElementsByName('unit')[0].length=0;
	document.getElementsByName('selectedUnit')[0].length=0;
}
 
function getFetchedList(){

	var fetchedList=document.forms[0].selectedUnit;
	var selectedData=document.forms[0].fetchedList.value;
	for(var i=0;i<fetchedList.length;i++){
		selectedData=selectedData + fetchedList.options[i].value +"%";
	}
	document.forms[0].fetchedList.value=selectedData;
}

function getUnit(obj){

	var processTypeCode=obj.value;
	if(processTypeCode=="-1"){
		document.getElementsByName('unit')[0].length=0;
		document.getElementsByName('selectedUnit')[0].length=0;
	}
	else{
		document.getElementsByName("hmode")[0].value="GETUNIT"
		document.forms[0].submit()
	}	
}







</script>


<his:TransactionContainer>
<body>
<html:form action="master/unitCounterMapping" > 
    <html:hidden name="unitCounterFB" property="hmode"/>
    <html:hidden name="unitCounterFB" property="chk"/>
    
     <his:ContentTag>

  	  	 <his:TitleTag name="Unit Counter Mapping">
			</his:TitleTag>
       
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		   	<tr>
		  	 	<td width="50%" class="tdfonthead">
					<div align="right">
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="counter"/>&nbsp;
						</font>
					  </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						&nbsp;<html:select name="unitCounterFB" property="counter" tabindex="1" styleClass="regcbo" onchange="getUnit(this)">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=RegistrationConfig.COUNTER_NAME_LIST %>" property="value" labelProperty="label"  />
								</html:select>
					</div>
				</td>
		   	</tr>
		   	</table>
		   	
		   	
		   	
		   	
		   	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">Unit
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="unitCounterFB" property="unit" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="unitCounterFB" property="hmode"  value="NEW">
										<logic:present name="<%=RegistrationConfig.UNIT_NOT_ADDED_TO_COUNTER_UNIT_LIST%>">
										<html:options  collection="<%=RegistrationConfig.UNIT_NOT_ADDED_TO_COUNTER_UNIT_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>		
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");' /> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:select name="unitCounterFB" property="selectedUnit" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="unitCounterFB" property="hmode"  value="NEW">
										<logic:present name="<%=RegistrationConfig.UNIT_ADDED_TO_COUNTER_UNIT_LIST%>">
										<html:options  collection="<%=RegistrationConfig.UNIT_ADDED_TO_COUNTER_UNIT_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>
								
							</div>
						</td>
					</tr>
			</table>
		   	
		   	   	
		   	
		   	
		   	</his:ContentTag>
		   	
		   	
		   	<his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
<!--				 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer" onclick="finalSubmit('VIEW')" onkeypress="if(event.keyCode==13) finalSubmit('VIEW')">-->
				 
			</span>
		</his:ButtonToolBarTag>
		   	
</html:form>
</body>
<his:status/>
</his:TransactionContainer>
</html>