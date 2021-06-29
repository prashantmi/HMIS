<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*"%>
<%@page import="registration.controller.fb.YellowSlipEntryFB"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>
var elemSelText = null;
var popupList =null;
var elemDiagnosisCode = null;
var elemDiagnosisName = null;
var selectIndexVal = -1;
var selectedRowIndex=null;

window.onload = function()
{
	if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==0)
	{
		elemDiagnosisCode = "icdCode";
		elemDiagnosisName = "disease";
	}
	else if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==1)
	{
		elemDiagnosisCode = "hospitalDiseaseCode";
		elemDiagnosisName = "hospitalDiseaseName";
	}

	if(document.getElementsByName("previousDiagnosisCode")){
		var previousDiagnosisCode=document.getElementsByName("previousDiagnosisCode")
		var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#")
		for(var i=0;i<previousDiagnosisCode.length;i++){
			for(var j=0;j<checkedItem.length;j++){
				if(checkedItem[j]==previousDiagnosisCode[i].value){
					previousDiagnosisCode[i].checked=true
				}
			}		
		}
	}
}

function setCheckedItem(){
	var checkedItem="";
	var previousDiagnosisCode=document.getElementsByName("previousDiagnosisCode")
	for(var i=0;i<previousDiagnosisCode.length;i++){
		if(previousDiagnosisCode[i].checked){	
			checkedItem=checkedItem+"#"+previousDiagnosisCode[i].value;
		}	
	}	
	checkedItem=checkedItem.substring(1);
	document.getElementsByName("checkedItem")[0].value=checkedItem
}

function validateEpisodeOpen()
{
	var valid=true
	if(document.getElementsByName("episodeIsOpen")[0].checked)
	{
		if(document.getElementsByName("episodeNextVisitDate")[0].value!=""){
			if(!compareDate(document.getElementsByName("episodeDate")[0],document.getElementsByName("episodeNextVisitDate")[0],2)){
				alert("Visit Date cannot be less than Episode Date");
				return false;
			}
		}	
	}
	return valid
}


function validateYellowSlipEntry(mode){
	var valid=false
	//alert("validateYellowSlipEntry")
	if(validateEpisodeOpen() &&
		comboValidation(document.getElementsByName("unitConsultantCode")[0],"Consultant Name") &&
		validateAdd(mode))
		{
		valid=true
		//alert("true")
		submitForm(mode)
		}
	else
	{
	valid=false
	}
	return valid;
}

function comboValidation(obj, str)
{	var valid= true
	if(obj.value==-1)
	{
		alert("Please Select : "+str)
		obj.focus()
		valid=false
	}
	return valid
}

function checkDuplicateCode()
{	
	var previousDiagnosisCode=document.getElementsByName("diagnosisCode")
	if(!document.getElementsByName("diagnosisCode")){
		previousDiagnosisCode[0].value=""
	}
	var diagnosisCode=document.getElementsByName("icdCode1")
	for(var i=0;i<previousDiagnosisCode.length;i++){
		if(previousDiagnosisCode[i].value==document.getElementsByName("icdCode")[0].value && document.getElementsByName("previousDiagnosisCode")[i].checked==true){
			alert("Diagnosis Already Added");
			return false;
		}	
	}
	for(var j=0;j<diagnosisCode.length;j++){
		if(diagnosisCode[j].value==document.getElementsByName("icdCode")[0].value){
			alert("Diagnosis Already Added");
			return false;
		}
	}
	
	return true;
}

function validateAdd(mode)
{
	var valid=true
	
	if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==<%=OpdConfig.CHOICE_ICD_CODE %>)
		{
	
			Code		= document.getElementsByName('icdCode')[0]
			name		= document.getElementsByName('disease')[0]
		}
		else
		{
	
			Code		= document.getElementsByName('hospitalDiseaseCode')[0]
			name		= document.getElementsByName('hospitalDiseaseName')[0]
		}
	
	
	diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[0]
	disFlag		= document.getElementsByName("displayFlag")[0]
	
	if(mode=='SAVE'){
		if(Code.value=="" && document.getElementsByName("diagonisticTypeCode")[0].value=="-1"){
			return true;
		}
		else{
			if(isEmpty(Code,"Code") && comboValidation(diaTypeCode,"Diagnostic Type Code")){
				if(!checkDuplicateCode()){
					return false;
				}
				else{
					return true;
				}
			}
			else{
				return false;
			}
		}	
	}
	if(isEmpty(Code,"Code") &&
			//isEmpty(name,"Disease Name") &&
			comboValidation(diaTypeCode,"Diagnostic Type Code") 
			//&& comboValidation(disFlag,"Display Flag")
			)
			{
				valid=true
			}
			else
			{
				return false
			}
	if(!checkDuplicateCode()){
		valid=false;
	}
				
	return valid 
	
}

function showEpisodeOpenDiv()
{
	if(document.getElementsByName("episodeIsOpen")[0].checked)
	{
	//alert("block")
	document.getElementById("nextVisitDatelabel").style.display="";
	document.getElementById("nextVisitDateControl").style.display="";
	}
	else
	{
	document.getElementById("nextVisitDatelabel").style.display="none";
	document.getElementById("nextVisitDateControl").style.display="none";
	}
	return true;
}


function validateComplaint()
{	var valid=true;
	if(document.getElementsByName('complainDetail')[0].value.length>500)
	 {
	 	alert("Characters in Complaint Detail greater than 500");
	 	valid= false;
	 }
	 return valid;
}


function remove(str,mode)
{
setCheckedItem()
document.forms[0].removeField.value=str;
submitForm(mode);
}

function submitPage(mode){
	setCheckedItem()
	elmt=document.getElementsByName("mode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function openSearchPopup(event){
	setCheckedItem()	
	openPopup('<his:path src="/registration/YellowSlipDiagnosisPopUp.cnt"/>',event,300,600);
}


//********* Selected TextBox Functions
// Getting AJAX Data
function gettext(eve, obj, varType)
{
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = -2;
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		if(obj.value!=" " && obj.value!="")
		//	sendDataForList(obj.value,varType);
		sendDataForCodeList(obj.value,varType);
	}
	if(eve.keyCode==40)
		changeControl();
}

function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}
// Move Control To Drop Down on Down Arrow Press
function changeControl()
{
	var elemDropDown=document.getElementById("selid");	
	if(typeof elemDropDown == 'undefined') return;
	
	var innerValue=elemDropDown.options[0].value;
	if(innerValue=="-1")
	{
		document.getElementById("sid").style.display="none";
		popupList = null;
		selectIndexVal = -1;
		return;
	}
	selectIndexVal = -1;
	elemDropDown.focus();
}

// Call On Blur of DropDown Sense TextBoxes
function callOnBlur()
{
	if(selectIndexVal==-1) 
	{
		selectIndexVal = document.getElementById("selid").selectedIndex;
		return;
	}
	var flag = false;
	var arr = popupList;
	if(popupList==null || typeof popupList == 'undefined' || popupList=="" || popupList.length<=0 || selectIndexVal<0)
		removeValues();
	else
		setValues();

	if(document.getElementById("sid") && document.getElementById("selid"))
	{
		document.getElementById("sid").style.display="none";
	}
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

function removeValues()
{
	document.getElementsByName(elemDiagnosisName)[selectedRowIndex].value = "";
	document.getElementsByName(elemDiagnosisCode)[selectedRowIndex].value = "";
}

function setValues()
{
	//alert(selectedRowIndex);
	if(selectIndexVal!=null && selectIndexVal>=0 && selectedRowIndex!=null)
	{
		//alert(selectIndexVal)
		var elemDiagCode = document.getElementsByName(elemDiagnosisName)[selectedRowIndex];
		var elemDiagName = document.getElementsByName(elemDiagnosisCode)[selectedRowIndex];
		var elemDropDown = document.getElementById("selid");
		selectIndexVal = elemDropDown.selectedIndex;
		if(elemSelText.name==elemDiagCode.name)
		{
			elemDiagName.value=elemDropDown.value;
		}
		else if(elemSelText.name==elemDiagName.name)
		{
			elemDiagCode.value=elemDropDown.value;
		}
		elemSelText.value=elemDropDown.options[selectIndexVal].text;
	}
}

//********** Drop Down Functions
// OnClick & Double click of Drop Downs
function setClickedValue()
{
	setValues();
	//alert("after setValues")
	document.getElementById("sid").style.display="none";
	document.getElementById("selid").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

// On Key Down of Drop Down
function hideta(eve)
{
	// 9 Tab
	if(eve.keyCode==13 || eve.keyCode==9)
	{
		setValues();
		document.getElementById("sid").style.display="none";
		document.getElementById("selid").value="";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
	if(eve.keyCode==27)
	{
		setValues();
		document.getElementById("sid").style.display="none";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
}

function onChangeDrop()
{
	var elemDropDown = document.getElementById("selid");
	selectIndexVal = elemDropDown.selectedIndex;
	elemSelText.value=elemDropDown.options[selectIndexVal].text;
}

function sendDataForCodeList(searchText, searchType)
{
	handleCodeList(searchText, searchType);
}


function sendRequestForCodeList(text,type)
{
	if(document.getElementsByName("unitDiagnosisTypeCode")[0].value=="0")
	{
		var values = document.getElementsByName("icdCodeList")[0].options;
	
		var result="";
		var txtlen = text.length;
		if(type=="CODE")
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].value.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].text+"$"+values[i].value+"$";
				}
			}
		}
		else
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].text.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].value+"$"+values[i].text+"$";
				}
			}
		}
		
		if(result.length>0) result = result.substr(0,result.length-1);
	}
	else
	{
		var values = document.getElementsByName("hospitalCodeList")[0].options;
	
		var result="";
		var txtlen = text.length;
		if(type=="CODE")
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].value.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].text+"$"+values[i].value+"$";
				}
			}
		}
		else
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].text.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].value+"$"+values[i].text+"$";
				}
			}
		}
		
		if(result.length>0) result = result.substr(0,result.length-1);
	}
	
	return result;
}

function handleCodeList(searchText, searchType)
{
	popupList=null;
	var list = sendRequestForCodeList(searchText, searchType);

	var elemDropDown=document.getElementById("selid");
	document.getElementById("sid").style.display="none";
	var o=elemSelText;
	var l=0,t=0;
	while(o.offsetParent)
	{
		l+=o.offsetLeft;
		t+=o.offsetTop;
		o=o.offsetParent;
	}
	document.getElementById("sid").style.left=l;
	document.getElementById("sid").style.top=t+elemSelText.offsetHeight;
	document.getElementById("sid").style.pixelWidth=elemSelText.style.pixelWidth;
			
	var optionValueTextArray=list.split('$');
	elemDropDown.innerHTML="";
	popupList = optionValueTextArray;
	if(optionValueTextArray!="")
	{
		document.getElementById("sid").style.display="block";
		for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
		{
			var op=document.createElement("option");
			op.value=optionValueTextArray[i];
			op.innerHTML=optionValueTextArray[j];
			elemDropDown.appendChild(op);
		}
		selectIndexVal = 0;
		elemDropDown.selectedIndex = selectIndexVal;
	}
	else
	{
		document.getElementById("sid").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		elemDropDown.appendChild(op);
		selectIndexVal = -2;
	}
}

 </script>

<%@page
	import="registration.*,hisglobal.presentation.*"%>
<%String strVisitDate=(String) session.getAttribute("VISITDATE");

String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");

String varStatus="NEW";
if(strVisitDate==null)
{
	strVisitDate="";	
}
%>

<his:TitleTag>
	<his:name>
		<bean:message key="yellowSlipEntry" />
	</his:name>
	<b> <font  size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		
		</font> 
	</b>
</his:TitleTag>

<his:ContentTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	
		<bean:define name="YellowSlipEntryFB" property="visitDate" id="visitDt" type="java.lang.String" />
		<% if(visitDt==null || visitDt.equalsIgnoreCase("")){ 
       	 
        	
			visitDt = "";
       	 }%>
       	 <tr>
		<td class="tdfonthead" width="25%">
		<div id="visitDateLabel"  align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<b><font color="#FF0000">*</font>
		<bean:message key="visitDate" /></b>
		</font>
		</div>
		</td>
		 <td class="tdfont" width="25%">   
	    	<div id="visitDateControl" align="left">
	    	<his:date name="visitDate" dateFormate="%d-%b-%Y" value='<%=visitDt %>'/>
	    	</div>
	   </td>
		
	
		<td width="25%"  class="tdfonthead">
		<div align="right">	           
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<b><font color="#FF0000">*</font>
		<bean:message key="unit"/></b>
		</font>
		</div>
	   </td>      
	   <td width="25%"  class="tdfont">
	   <div align="left">
	   <html:select name="YellowSlipEntryFB" tabindex="1" property="unitCode" styleClass="regcbo">
			<html:option value="-1">Select Value</html:option>
  			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_SEATID%>" property="value" labelProperty="label" />
	   </html:select>
	   </div>
       </td>    
      
	</tr>
	</table>
</his:ContentTag>

<his:statusNew>
<his:InputCrNoTag name="YellowSlipEntryFB" >
</his:InputCrNoTag>
</his:statusNew>
<his:statusInProcess>

<jsp:include page="/registration/patientDetail.cnt" flush="true" />

</his:statusInProcess>

<his:statusTransactionInProcess>

<his:SubTitleTag name="Diagnosis Detail"> 
</his:SubTitleTag>

<his:ContentTag>
	<table id="diagnosisTable" width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
						
			<td width="25%" class="tdfonthead" align="left" >
						<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b>
						<bean:message key="episodeStatus" /> </b> </font>
					</td>
					<td width="25%" class="tdfont">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="open" /> </font> 
						<html:radio name="YellowSlipEntryFB" property="episodeIsOpen" tabindex="1"
						value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onclick="showEpisodeOpenDiv()" /> <font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="close" /> </font> 
						<html:radio name="YellowSlipEntryFB" property="episodeIsOpen" tabindex="1"
						value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onclick="showEpisodeOpenDiv()" />
					</td>
					 <td  class="tdfonthead" width="25%">
							<div   align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font>
							<bean:message key="con" /></b>
							</font>
							</div>
					</td>
					<td  class="tdfont" width="25%">
					   <div align="left">
					   <html:select name="YellowSlipEntryFB" tabindex="1" property="unitConsultantCode" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
				  			<html:options collection="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>" property="value" labelProperty="label" />
					   </html:select>
					   </div>
			       </td>   
				
	</tr>
	<tr>
			<bean:define name="YellowSlipEntryFB" property="episodeNextVisitDate" id="nextVisitDate" type="java.lang.String" />
				<% if(nextVisitDate==null || nextVisitDate.equalsIgnoreCase("")){ 
		       	 
		        	
					nextVisitDate = "";
		       	 }%>
		       	 
		       	 
			<td  class="tdfonthead" width="25%">
					<div  id="nextVisitDatelabel" align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<!--						<b><font color="#FF0000">*</font>-->
					<b><bean:message key="nextVisitDate" /></b>
					</font>
					</div>
			</td>
			 <td  class="tdfont" width="25%">   
		    	<div id="nextVisitDateControl" align="left">
		    	<his:date name="episodeNextVisitDate" dateFormate="%d-%b-%Y" value="<%=nextVisitDate %>"/>	</div>
		   </td>
		<td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<!--			<b><font color="#FF0000">*</font>-->
			<b><bean:message key="complaint" /></b>
			</font>
			</div>
		</td>
		<td width="25%" class="tdfont">
			<div>
				<html:textarea name="YellowSlipEntryFB" property="complainDetail" tabindex="1" cols="30" onkeypress="return CheckMaxLength(event,this,500,1)"></html:textarea>
			</div>
		</td>
		
	  
        
	</tr>
	
	</table>
</his:ContentTag>

<logic:present name="<%=RegistrationConfig.PREVIOUS_DIAGNOSIS_VO_LIST %>">
	<his:SubTitleTag name="Previous Diagnosis">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
			<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="icdCode"/></b>
				</font>
				</div>
	  		</td>
	  		<td  width="55%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="diagnosisName"/></b>
				</font>
				</div>
	  		</td>
	  	</logic:equal>	
	  	<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE%>">
	  		<td  width="10%" class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="hospitalDiagnosisCode"/></b>
					</font>
					</div>
		  	</td>
	  		<td  width="55%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="hospitalDiagnosisName"/></b>
				</font>
				</div>
	  		</td>
	  		</logic:equal>	
			<td  width="20%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="diagonosisType"/></b>
				</font>
				</div>
	  		</td>  
	  		<%--
	  		<td width="15%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="displayFlag"/></b>
				</font>
				</div>
	  		</td> 
	  		--%>
	  		<td  width="15%" class="tdfonthead">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">	
					<b>Repeat</b>
					</font>
				</div>
	  		</td>   
	  	</tr>  
	  		
	  	<logic:iterate id="episodeDiagnosisVO" name="<%=RegistrationConfig.PREVIOUS_DIAGNOSIS_VO_LIST %>" type="hisglobal.vo.EpisodeDiagnosisVO" indexId="index">
		<tr>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<bean:write name="episodeDiagnosisVO" property="diagnosticCode"/>
	  				<input type="hidden" name="diagnosisCode" value="<%=episodeDiagnosisVO.getDiagnosticCode() %>"/>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<bean:write name="episodeDiagnosisVO" property="dignosisName"/>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  		<div align="center">
	  			<bean:write name="episodeDiagnosisVO" property="diagnosticTypeName"/>
	  			</div>
	  		</td>
	  		<%--
	  		<td class="tdfont">
	  			<div align="center">
	  			None
	  			</div>
	  		</td>
	  		 --%>
	  		<td class="tdfont">
	  			<div align="center">
				<input type="checkbox" name="previousDiagnosisCode" tabindex="1" value="<%=index+""%>" > 
				</div>
			</td> 
	  	</tr>
		</logic:iterate>
		
	  </table>	
	</his:ContentTag>

</logic:present>

<his:SubTitleTag name="Add Diagnosis">
</his:SubTitleTag>
<his:ContentTag>
	<table style="display:block" width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
			<td width="5%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="icdCode"/></b>
				</font>
				</div>
	  		</td>
	  		<td  width="15%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="diagnosisName"/></b>
				</font>
				</div>
	  		</td>
	  	</logic:equal>	
	  	<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE%>">
	  		<td  width="5%" class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="hospitalDiagnosisCode"/></b>
					</font>
					</div>
		  	</td>
	  		<td  width="15%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="hospitalDiagnosisName"/></b>
				</font>
				</div>
	  		</td>
	  		</logic:equal>	
			<td  width="10%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="diagonosisType"/></b>
				</font>
				</div>
	  		</td>  
	  		<%--
	  		<td width="15%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="displayFlag"/></b>
				</font>
				</div>
	  		</td> 
	  		--%>
	  		<td  width="5%" class="tdfonthead">
				<div align="center">	           
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openSearchPopup(event);" onkeypress=" if(event.keyCode==13) openSearchPopup(event);" tabindex="1">
				</div>
	  		</td>   
	  		
	  	</tr>    
		<tr>
	  		<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB"  maxlength="3" size="5" property="icdCode" tabindex="1" 
	  							onkeypress="return  validateAlphaNumericOnly(event,this)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="disease" size="50" tabindex="1" 
	  						onkeypress="return validateAlphaNumericOnly(event,this)" 
	  						onkeyup="gettext(event,this,'LABLE');" 
							onblur="callOnBlur(this)"></html:text>
	  			</div>
	  			<div id="divIcdCodeList" style="display: none; position: absolute;">
					<select name="icdCodeList" id="icdCodeList" multiple="multiple" size="4">
						<logic:iterate name="<%=RegistrationConfig.ESSENTIALBO_OPTION_ICD_CODE_LIST%>" id="list">
							<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
						</logic:iterate>
					</select>
				</div>
	  			
	  		</td>
	  		</logic:equal>
	  		<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB"  maxlength="3" size="5" property="hospitalDiseaseCode" tabindex="1" 
	  							onkeypress="return validateAlphaNumericOnly(event,this)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="hospitalDiseaseName" size="50" tabindex="1"
  							onkeypress="return validateAlphaNumericOnly(event,this)" 
  							onkeyup="gettext(event,this,'LABLE');" 
							onblur="callOnBlur(this)"></html:text>
	  			
	  			</div>
	  			<div id="divHospitalCodeList" style="display: none; position: absolute;">
					<select name="hospitalCodeList" id="hospitalCodeList" multiple="multiple" size="4">
						<logic:iterate name="<%=RegistrationConfig.ESSENTIALBO_OPTION_HOSPITAL_DIAGNOSOSIS_CODE_LIST%>" id="list">
							<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
						</logic:iterate>
					</select>
				</div>
	  			
	  		</td>
	  		</logic:equal>
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:select name="YellowSlipEntryFB" tabindex="1" property="diagonisticTypeCode" styleClass="regCbo">
					<html:option value="-1">Select Value</html:option>
  					<html:options  collection="<%=RegistrationConfig.DIAGNOSIS_LIST%>" property="value" labelProperty="label" />
	  			</html:select>
	  			</div>
	  		</td>
	  		<html:hidden name="YellowSlipEntryFB" property="displayFlag" />
	  		<%--  
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:select name="YellowSlipEntryFB" tabindex="1" property="displayFlag" styleClass="regCbo">
	  				<html:option value="<%=RegistrationConfig.DISPLAY_FLAG_NONE %>">None</html:option>
  					<html:option value="<%=RegistrationConfig.DISPLAY_FLAG_RIGHT %>">Right</html:option>
  					<html:option value="<%=RegistrationConfig.DISPLAY_FLAG_LEFT %>">Left</html:option>
	  			</html:select>
	  			</div>
	  		</td>
	  		--%>
	  		<td  class="tdfont">
			<div align="center">   
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) if (validateAdd('ADD')) {setCheckedItem(); submitForm('ADD') };" onclick="if (validateAdd('ADD')) {setCheckedItem(); submitForm('ADD')}" tabindex="1">
			</div>
			</td> 
			
	  	</tr>
	
		<% Map outerMap=(Map)session.getAttribute("outerMap");
					
				
		%>
				
				
				<logic:iterate id="outerMapID" name="outerMap" indexId="j" type="java.util.Map.Entry"  >
										
				 
			  
							
			<bean:define  name="outerMapID" property="value"  id="innerList" type="java.util.List"/> 
	  	<tr>
	  		<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE%>">
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="icdCode1" value="<%=(String) innerList.get(0) %>" readonly="true"  size="5"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="disease1" size="50" value="<%=(String) innerList.get(1) %>" readonly="true"></html:text>
	  			</div>
	  		</td>
	  		</logic:equal>
	  		
	  		<logic:equal name="YellowSlipEntryFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE%>">
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="icdCode1" value="<%=(String) innerList.get(0) %>" readonly="true"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="disease1" size="50" value="<%=(String) innerList.get(1) %>" readonly="true"></html:text>
	  			</div>
	  		</td>
	  		</logic:equal>
	  		
	  		<html:hidden name="YellowSlipEntryFB"  property="diagonisticTypeCode1" value="<%=(String) innerList.get(2) %>"  />
	  		
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="diagonisticTypeName" value="<%=(String) innerList.get(3) %>" readonly="true" >
					</html:text>
	  			</div>
	  		</td>
	  		
	  			<html:hidden name="YellowSlipEntryFB"  property="displayFlag1" value="<%=(String) innerList.get(4) %>"  />
	  		
	  		<%-- 	
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:text name="YellowSlipEntryFB" property="displayFlagLabel" value="<%=(String) innerList.get(5) %>" readonly="true"></html:text>
	  			</div>
	  		</td>
	  		--%>
	  		<td  class="tdfont">
	  		<div align="center">
	  		<img class="button" src='<his:path src="/hisglobal/images/icn-min.png"/>' style=cursor:pointer tabindex="1" onclick="remove('<bean:write name="outerMapID" property="key"/>','REMOVE')" onkeypress="if(event.keyCode==13) remove('<bean:write name="outerMapID" property="key"/>','REMOVE')">
			</div>
			</td>
		  
	  	</tr>
	  	</logic:iterate>
	
	
		  	
	  	<html:hidden name="YellowSlipEntryFB" property="removeField"/>
	 </table>
</his:ContentTag>
</his:statusTransactionInProcess>


<his:ButtonToolBarTag>
<his:statusTransactionInProcess>
<%varStatus="INPROCESS";%>
</his:statusTransactionInProcess>
<%if(varStatus.equals("NEW")){%>	

<div align="center"> 
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style=cursor:pointer 	onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
<%}else{ %> 
<div align="center"><img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateYellowSlipEntry('SAVE');"
	tabindex="1"
	onclick="validateYellowSlipEntry('SAVE')"> 
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style=cursor:pointer 	onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
	onclick="submitPage('CANCEL');"> <img class="button"
	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
	tabindex="1" onclick="submitForm('NEW')"
	onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
<%} %>
</his:ButtonToolBarTag>
<div id="sid" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="selid" size="10" tabindex="1" onKeyDown="hideta(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
</div>

<his:status/>
<html:hidden name="YellowSlipEntryFB" property="hmode"/>
<html:hidden name="YellowSlipEntryFB" property="patCrNo"/>
<html:hidden name="YellowSlipEntryFB" property="unitDiagnosisTypeCode"/>
<html:hidden name="YellowSlipEntryFB" property="episodeDate"/>
<html:hidden name="YellowSlipEntryFB" property="checkedItem"/>

