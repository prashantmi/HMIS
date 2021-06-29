
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="opd.OpdConfig"%>
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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />

<script>

function mappedDiseasePopUp(e)
{
	var selectedDisease=document.getElementsByName("selecteHospitaldDisease")[0].value;
	var child = window.open(createFHashAjaxQuery('/HISClinical/opd/master/DeptUnitIcdMapping.cnt?hmode=MAPPEDDISEASE'),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus();
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}
function moveRightSingle(listNo)
{
	var source;
	var target;
	if(listNo=="1")
	{
		source  = document.forms[0].hosdiseaseCode;
		target = document.forms[0].selecteHospitaldDisease;	
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
		source  = document.forms[0].hosdiseaseCode;
		target = document.forms[0].selecteHospitaldDisease;	
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
		target  = document.forms[0].hosdiseaseCode;
		source = document.forms[0].selecteHospitaldDisease;	
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
		target  = document.forms[0].hosdiseaseCode;
		source = document.forms[0].selecteHospitaldDisease;	
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
	// Select All para in Selected
	if(document.forms[0].hosdiseaseCode)
		for(var i=0;i<document.forms[0].selecteHospitaldDisease.length;i++)
			document.forms[0].selecteHospitaldDisease.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].selecteHospitaldDisease)
	{	
		for(var i=0;i<document.forms[0].hosdiseaseCode.options.length;i++)
			document.forms[0].hosdiseaseCode.options[i].selected=false;
	}
}



function validateAdd()
{	
	if(document.forms[0].selecteHospitaldDisease.options.length==0)
	{
		alert("Choose at Least One Disease");
		document.forms[0].selecteHospitaldDisease.focus();
		return false;
	}
	return true;
	
	
}
function validateModify()
{	
	if(document.forms[0].selecteHospitaldDisease.options.length==0)
	{
		alert("Choose at Least One Disease");
		document.forms[0].selecteHospitaldDisease.focus();
		return false;
	}
	return true;
	
	
}
function modifyClear()
{
	moveLeftAll("1");
}
function submitForm(mode)
{
  	  MoveToSelected();
     document.getElementsByName("hmode")[0].value=mode;
	 document.forms[0].submit();
	 
}
function clearForm(){

	document.getElementsByName('hosdiseaseCode')[0].length=0;
	document.getElementsByName('selecteHospitaldDisease')[0].length=0;
}
</script>

<body>
<html:form action="/master/DeptUnitHospitalDisease">
<html:hidden name="DeptUnitHospitalDiseaseFB" property="hmode"/>
<html:hidden name="DeptUnitHospitalDiseaseFB" property="selectedUnit"/>
	<his:TransactionContainer>
		<his:ContentTag>
				<his:TitleTag name="Department Unit Hospital Disease>>Add ">
				</his:TitleTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
							<html:select name="DeptUnitHospitalDiseaseFB" property="selectedUnit" multiple="true" style="width:200px;" size="6">
								<logic:present name="<%=OpdConfig.LIST_OF_UNIT_MAPPED_WITH_DEPT%>" >
								<html:options collection="<%=OpdConfig.LIST_OF_UNIT_MAPPED_WITH_DEPT %>" property="value" labelProperty="label" />
								</logic:present>
								
								
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfonthead">
						</td>
						</tr>
						</table>
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="hospitaldiseases"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfonthead"></td>
						
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedHospitalDisease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="10%"  class="tdfonthead"></td>
					</tr>
						
					<tr>
						<td width="30%"  class="tdfont">
							<div align="center"  >
								<html:select name="DeptUnitHospitalDiseaseFB" property="hosdiseaseCode"  multiple="true" size="6"  >
										<logic:present name="<%=OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE %>" >
										<html:options collection="<%=OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
						</td>
						
						<td width="30%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<div align="center">
								<html:select name="DeptUnitHospitalDiseaseFB" property="selecteHospitaldDisease" multiple="true" size="6">
									</html:select>
							</div>
						</td>
						<td width="5%"  class="tdfont">
						
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfonthead"></td>
						<td width="35%" class="tdfonthead"></td>
						<td width="10%" class="tdfonthead"></td>
					</tr>
					</table>
				
		</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd() && submitForm('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="DeptUnitHospitalDiseaseFB" property="hmode"/>
				<html:hidden name="DeptUnitHospitalDiseaseFB" property="deptUnitName"/>
				<html:hidden name="DeptUnitHospitalDiseaseFB" property="deptUnitCode"/>
				<div id="status" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No disease Found</b>	
					</font>
				</div>
				
			</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
		</html:form>
	</body>
</html>