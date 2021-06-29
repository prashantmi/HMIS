<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="registration.controller.fb.YellowSlipMonitoringFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.YellowSlipEntryDtlVO"%>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/registration/js/yellowSlipMonitoring.js"/> 
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function getYellowSlipEntryUser(){
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	var sysdate=document.getElementsByName("sysdate")[0]
	if(!compareDate(fromDate,sysdate,2)){
		alert("From Date cannot be greater than Current Date")
		return false;
	}	
	if(!compareDate(fromDate,toDate,2)){
		alert("From Date cannot be greater than To Date")
		return false;
	}	
	document.getElementsByName("hmode")[0].value="GETYELLOWSLIPENTRYUSERS";
	document.forms[0].submit()
}

function getYellowSlipEntryByUser(){
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	var sysdate=document.getElementsByName("sysdate")[0]
	if(!compareDate(fromDate,sysdate,2)){
		alert("From Date cannot be greater than Current Date")
		return false;
	}	
	if(!compareDate(fromDate,toDate,2)){
		alert("From Date cannot be greater than To Date")
		return false;
	}	
	document.getElementsByName("hmode")[0].value="GETYELLOWSLIPENTRYBYUSER";
	document.forms[0].submit()
}

function getYellowSlipEntryDetail(obj){
	document.getElementsByName("patCrNo")[0].value=obj.value.split("#")[0];
	document.getElementsByName("hmode")[0].value="GETPATYELLOWSLIPDTL";
	document.forms[0].submit()
}
function disableForm(){
	document.getElementsByName("episodeIsOpen")[0].disabled=true;
	document.getElementsByName("episodeIsOpen")[1].disabled=true;
	document.getElementsByName("complainDetail")[0].disabled=true;
	document.getElementsByName("episodeNextVisitDate")[0].disabled=true;
	document.getElementsByName("unitConsultantCode")[0].disabled=true;
	
	var length=document.getElementsByName("diagnosisRemoved").length
	//for(var i=0;i<length;i++){
		//document.getElementById("checkBoxDiv"+i).style.display="none"
	//}
	//	document.getElementById("addDiv").style.display="none";
}

function enableForm(){
	document.getElementsByName("episodeIsOpen")[0].disabled=false;
	document.getElementsByName("episodeIsOpen")[1].disabled=false;
	document.getElementsByName("complainDetail")[0].disabled=false;
	document.getElementsByName("episodeNextVisitDate")[0].disabled=false;
	document.getElementsByName("unitConsultantCode")[0].disabled=false;
	
	var length=document.getElementsByName("diagnosisRemoved").length
	//for(var i=0;i<length;i++){
		//document.getElementById("checkBoxDiv"+i).style.display="block"
	//}
	///	document.getElementById("addDiv").style.display="block";
}
function toggleFormDisplay(obj){
	if(obj.checked){
		document.getElementsByName("modificationRequired")[0].value="1"
		enableForm();
		submitForm('ENABLEFORM');
	}
	else{
		//alert("unchecked")
		document.getElementsByName("modificationRequired")[0].value="0"
		disableForm()
		submitForm('DISABLEFORM');
	}
}

function validateEpisodeOpen()
{
var valid=true
	if(document.getElementsByName("episodeIsOpen")[0].checked)
	{
		if(document.getElementsByName("episodeNextVisitDate")[0].value!=""){
			if(!compareDate(document.getElementsByName("episodeDate")[0],document.getElementsByName("episodeNextVisitDate")[0],2)){
				alert("Next visit Data cannot be less than Episode Date")
				return false;
			}
		}	
	}
	return valid
}


function validateYellowSlipEntry(mode)
{	
	var valid=false
	if(document.getElementsByName("episodeIsOpen")[0].checked==false 
		&& document.getElementsByName("episodeIsOpen")[1].checked==false){
		alert("Please Select Episode Status");
		return false;
	}
	
	if(validateEpisodeOpen() &&
		comboValidation(document.getElementsByName("unitConsultantCode")[0],"Consulatant Name") &&
		validateAdd(mode) && validateMonitoringDtl())
		{
		valid=true
		submitForm(mode)
		}
	else
	{
	valid=false
	}
	return valid
}

function validateMonitoringDtl(){
	valid=false
	if(document.getElementsByName("monitoringMode")[0].value=="1"){
		if(isSelected(document.getElementsByName("monitoringFlag")[0],"Entry Quality")&&
		isEmpty(document.getElementsByName("monitoringRemarks")[0],"Remarks")){
			valid=true;
		}
	
	}
	else{	
		valid=true;
	}
	return valid
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

function validateAdd(mode)
{
	var valid=true
	var Code;
	var name;
	
	if((document.getElementsByName("modificationRequired")[0] && document.getElementsByName("modificationRequired")[0].value==1)  
		|| document.getElementsByName("monitoringMode")[0].value==0){
	
	if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==<%=OpdConfig.CHOICE_ICD_CODE %>)
		{
	
			Code		= document.getElementsByName('icdCode')
			name		= document.getElementsByName('disease')
		}
		else
		{
	
			Code		= document.getElementsByName('hospitalDiseaseCode')
			name		= document.getElementsByName('hospitalDiseaseName')
		}
		
		if(mode=='SAVE'){
		if(Code.length==1){
			if(Code[0].value=="" && document.getElementsByName("diagonisticTypeCode")[0].value=="-1"){
				document.getElementsByName("isAddDiagnosis")[0].value="0"
				return true;
			}	
		}
			if(!checkDuplicateCode()){
				return false;
			}
		
		}
		for(var i=0;i<Code.length;i++){
		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[i]
		disFlag		= document.getElementsByName("displayFlag")[i]
		
		if(isEmpty(Code[i],"Code") &&
			//isEmpty(name,"Disease Name") &&
			comboValidation(diaTypeCode,"Diagnostic Type Code") 
			//&& comboValidation(disFlag,"Display Flag")
			)
			{
				valid=true
				document.getElementsByName("isAddDiagnosis")[0].value="1"
			}
			else
			{
				document.getElementsByName("isAddDiagnosis")[0].value="0"
				return false
			}	
		}	
	}	
	if(!checkDuplicateCode()){
		return false;
	}
	return valid
	//return false
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

function addDiagnosisRow()
{
  var index=0;
	var nRow=0;
	var tableObj=document.getElementById('diagnosisTable');
	var numRows=tableObj.rows.length;
	var diagnosisType=document.getElementsByName("diagonisticTypeHtml")[0].value
	var icdCode;
	var disease;
	var maxlength;
	if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==<%=OpdConfig.CHOICE_ICD_CODE %>){
		icdCode="icdCode"
		disease="disease"
		maxlength=3
	}
	else{
		icdCode="hospitalDiseaseCode"
		disease="hospitalDiseaseName"
		maxlength=6
	}
	//alert(numRows)
	//numRows=index;
	nRow=numRows
		var tabRow=tableObj.insertRow(numRows);
		tabRow.id="row"+(parseInt(nRow)+1);
	
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		var td5=document.createElement("TD");
		
		td1.innerHTML="<div align=\"center\">"
	  				+ "<input type=text name=\"" + icdCode + "\" maxlength=\"" + maxlength + "\" size=\"5\" tabindex=\"1\" onkeypress=\"return validateAlphaNumericOnly(event,this)\">"
	  				+ "</div>";
		td1.className='tdfont';																													
		td1.colspan="1";
		td1.width="10%";

		td2.innerHTML="<div align=\"center\">"
	  				+ "<input type=text name=\"" + disease + "\"  maxlength=\"3\" size=\"50\" tabindex=\"1\" onkeypress=\"return validateAlphaNumericOnly(event,this)\">"
	  				+ "</div>";
		td2.className='tdfont';																													
		td2.colspan="1";
		td2.width="40%";

		td3.innerHTML="<div align=\"center\">"
	  				+ diagnosisType
	  				+ "</div>";
		td3.className='tdfont';																													
		td3.colspan="1";
		td3.width="20%";
		
		td4.innerHTML="<div align=\"center\">"
	  				+ "<select name=\"displayFlag\" class=\"regCbo\" tabindex=\"1\" >"
	  				+ "<option value=\"<%=RegistrationConfig.DISPLAY_FLAG_NONE %>\">None</option>"
  					+ "<option value=\"<%=RegistrationConfig.DISPLAY_FLAG_RIGHT %>\">Right</option>"
  					+ "<option value=\"<%=RegistrationConfig.DISPLAY_FLAG_LEFT %>\">Left</option>"
	  				+ "</select></div>";
		td4.className='tdfont';																													
		td4.colspan="1";
		td4.width="20%";
		
		var imagePath="<div align='center'><img class=\"button\" src='/HISClinical/hisglobal/images/icn-min.png' tabindex='1' style='cursor: pointer' tabindex='1' onclick=\"removeDiagnosisRow(" + tabRow.id + ")\"> </div>";
		
		td5.innerHTML=imagePath ;
		td5.className='tdfont';
		td5.colspan="1";
		td5.width="10%";
		
		tabRow.appendChild(td1);
		tabRow.appendChild(td2);
		tabRow.appendChild(td3);
		tabRow.appendChild(td4);
		tabRow.appendChild(td5);
		
		document.getElementsByName('noOfRows')[0].value=index+1;

}
function removeDiagnosisRow(rowid){
	var index=document.getElementsByName('noOfRows')[0].value
	var tableObj=document.getElementById('diagnosisTable');
	var len=tableObj.rows.length
	var rows=tableObj.rows
	//alert("rowid ;"+ rowid.rowIndex)
	//alert(rows.namedItem(rowid).id)
	tableObj.deleteRow(rowid.rowIndex);
	document.getElementsByName('noOfRows')[0].value=index-1;
}

function clearForm(){
	if(document.getElementsByName('monitoringMode')[0].value==0 || document.getElementsByName('modificationRequired')[0].value==1){
		document.getElementsByName('complainDetail')[0].value=""
		document.getElementsByName('unitConsultantCode')[0].value="-1"
		for(var i=0;i<document.getElementsByName('diagnosisRemoved').length;i++){
			document.getElementsByName('diagnosisRemoved')[i].checked=false;
		}	
		if(document.getElementsByName('icdCode')[0])
			document.getElementsByName('icdCode')[0].value="";
		if(document.getElementsByName('disease')[0])
			document.getElementsByName('disease')[0].value="";
		if(document.getElementsByName('hospitalDiseaseCode')[0])
			document.getElementsByName('hospitalDiseaseCode')[0].value="";
		if(document.getElementsByName('hospitalDiseaseName')[0])
		document.getElementsByName('hospitalDiseaseName')[0].value="";
		if(document.getElementsByName('diagonisticTypeCode')[0]){
			document.getElementsByName('diagonisticTypeCode')[0].value="-1";
			document.getElementsByName('displayFlag')[0].value="1";
		}	
	}
	if(document.getElementsByName('monitoringMode')[0].value==1){
		document.getElementsByName('monitoringFlag')[0].value="-1";
		document.getElementsByName('monitoringRemarks')[0].value="";
		document.getElementsByName('modificationRequired')[0].checked=false;
		disableForm();
	}	
}

function addRow()
{
	if(validateAdd('ADDROW')){
		setCheckedItem()
		submitForm('ADD');
	 }
	 else{
	 	return false;
	 }
		
}

function remove(str,mode)
{
	setCheckedItem()
	document.forms[0].removeField.value=str;
	submitForm(mode);
}

function openSearchPopup(event){
	setCheckedItem()	
	openPopup('<his:path src="/registration/YellowSlipDiagnosisPopUp.cnt"/>',event,300,600);
}

function setCheckedItem(){
	var checkedItem="";
	var diagnosisRemoved=document.getElementsByName("diagnosisRemoved")
	for(var i=0;i<diagnosisRemoved.length;i++){
		if(diagnosisRemoved[i].checked){	
			checkedItem=checkedItem+"#"+diagnosisRemoved[i].value;
		}	
	}	
	checkedItem=checkedItem.substring(1);
	document.getElementsByName("checkedItem")[0].value=checkedItem

}

 </script>

<%@page
	import="registration.*,hisglobal.presentation.*"%>
<%try{ %>
<html:form action="/yellowSlipMonitoring">
<his:TransactionContainer>
<his:TitleTag name="Yellow Slip Monitoring">
</his:TitleTag>
<% String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	String fromDate=(String)session.getAttribute("fromDate");
	String toDate=(String)session.getAttribute("toDate");
	if(fromDate!=null){
	if(fromDate.equals("")){
		fromDate=systemDate;
	}
	}
	else{
		fromDate=systemDate;
	}
	if(toDate!=null){
	if(toDate.equals("")){
		toDate=systemDate;
	}
	}
	else
		toDate=systemDate;	
%>
<%YellowSlipMonitoringFB fb=(YellowSlipMonitoringFB)pageContext.findAttribute("yellowSlipMonitoringFB"); %>
<his:ContentTag>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	   	 <tr>
	   	 	<% boolean disabled=false;%>
			<logic:notEqual name="yellowSlipMonitoringFB" property="hmode" value="GETPATYELLOWSLIPDTL">
				<%disabled=false; %>
			</logic:notEqual>
			<logic:equal name="yellowSlipMonitoringFB" property="mode" value="DIRECT">
			<%if(fb.getModificationRequired().equals("1") || fb.getHmode().equals("GETPATYELLOWSLIPDTL")
					|| fb.getHmode().equals("ADD") || fb.getHmode().equals("REMOVE") 
					|| fb.getHmode().equals("POPULATE") || fb.getHmode().equals("DISABLEFORM")){ %>
				<%disabled=true; %>
				<html:hidden name="yellowSlipMonitoringFB" property="monitoringMode"/>
			<%} %>
			</logic:equal>
			<%--<td class="tdfonthead" width="50%">
				<b><bean:message key="chooseMode"/></b>
			</td> --%>
			<logic:equal name="yellowSlipMonitoringFB" property="mode" value="DIRECT">
			<td class="tdfonthead" width="100%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<html:radio name="yellowSlipMonitoringFB" property="monitoringMode" value="0" tabindex="1" disabled="<%=disabled %>"></html:radio>
				<b><bean:message key="modification" /></b>
				<html:radio name="yellowSlipMonitoringFB" property="monitoringMode" value="1" tabindex="1" disabled="<%=disabled %>"></html:radio>
				<b><bean:message key="monitoring" /></b>
				</font>
			</div>
			</td>
			</logic:equal>
			<logic:notEqual name="yellowSlipMonitoringFB" property="mode" value="DIRECT">
				<logic:equal name="yellowSlipMonitoringFB" property="hmode" value="GETPATYELLOWSLIPDTL">
				<html:hidden name="yellowSlipMonitoringFB" property="monitoringMode" value="0"/>
				</logic:equal>
			</logic:notEqual>
		</tr>
	</table>	
	<table width="100%" cellspacing="1">	
       	 <tr>
			<td class="tdfonthead" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="fromDate" /></b>
				</font>
				</div>
			</td>
			 <td class="tdfont" width="25%">   
		    	<div  align="left">
		    	<his:date name="fromDate" dateFormate="%d-%b-%Y" value='<%=fromDate %>'/>
		    	</div>
		   	</td>
		
	
			<td width="25%"  class="tdfonthead">
			<div align="right">	           
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><font color="#FF0000">*</font>
			<bean:message key="toDate"/></b>
			</font>
			</div>
		   </td>  
		    <td class="tdfont" width="25%">   
		    	<div  align="left">
		    	<his:date name="toDate" dateFormate="%d-%b-%Y" value='<%=toDate %>'/>
		    	</div>
		   	</td>
		</tr>
	</table>
</his:ContentTag>
<his:statusDone>
	<his:ContentTag>
		<table width="100%" cellspacing="1">
			<tr>
				<td width="25%"  class="tdfonthead">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="entryUser" /></b>
					</font>
				</td>
				<td width="25%"  class="tdfont">
			   <div align="left">
			   <html:select name="yellowSlipMonitoringFB" tabindex="1" property="seatId" styleClass="regcbo" >
					<html:option value="%">All User</html:option>
		  			<html:options collection="<%=RegistrationConfig.YELLOW_SLIP_ENTRY_USER_LIST%>" property="value" labelProperty="label" />
			   </html:select>
			   </div>
			   </td>  
		       <td width="50%"  class="tdfonthead">
				<div align="left">
				<img class="button"
				src='<his:path src="/hisglobal/images/GO.png"/>' style="cursor:pointer"
				tabindex="1" onclick="getYellowSlipEntryByUser()"
				onkeypress="if(event.keyCode==13) onchange=getYellowSlipEntryByUser() ">
				</div>
				</td>
		     </tr>
		 </table>       
	</his:ContentTag>
</his:statusDone>

<his:statusList>
	<logic:notEqual name="yellowSlipMonitoringFB" property="seatId" value="%">
	<his:SubTitleTag name="Monitoring Statistics">
		<table >
			<tr>
				<td width="100%" align="right">
					<b><bean:write name="yellowSlipMonitoringFB" property="userName"/></b>
				</td>
			</tr>	
		</table>	
	</his:SubTitleTag>
		<table width="100%" cellspacing="1">
			<tr>
				<td width="25%"  class="tdfonthead">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="majorError"/></b>
					</font>
				</td>
				<td width="25%"  class="tdfont">
					<bean:write name="yellowSlipMonitoringFB" property="majorError"/>
				</td>
				<td width="25%"  class="tdfonthead">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="minorError"/></b>
					</font>
				</td>
				<td width="25%"  class="tdfont">
					<bean:write name="yellowSlipMonitoringFB" property="minorError"/>
				</td>
			</tr>
		</table>		
	</logic:notEqual>
		<% 	PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((YellowSlipMonitoringFB)request.getAttribute("yellowSlipMonitoringFB")).getCurrentPage());
						fbPage.setObjArrName(RegistrationConfig.YELLOW_SLIP_ENTRY_VO_LIST);
						fbPage.setAppendInTitle("Records");
						fbPage.setMaxRecords(15);
		%>
		<%String seatId=((YellowSlipMonitoringFB)request.getAttribute("yellowSlipMonitoringFB")).getSeatId(); %>
	<bean:define id="seatIdValue" value="<%=seatId %>"></bean:define>
	<his:PaginationTag name="fbPagination"></his:PaginationTag>	
	<his:ContentTag>
		<logic:present name="<%=RegistrationConfig.YELLOW_SLIP_ENTRY_VO_LIST %>">
		<table width="100%" cellspacing="1">
			<tr>
				<td width="5%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="select"/></b>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="crNo"/></b>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="name"/></b>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="visitDate"/></b>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="dept/unit"/></b>
						</font>
					</div>
				</td>
				<logic:equal name="seatIdValue" value="%">
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="enterBy"/></b>
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="entryDate"/></b>
						</font>
					</div>
				</td>
				</logic:equal>
			</tr>
			<%		List list=(List)session.getAttribute(RegistrationConfig.YELLOW_SLIP_ENTRY_VO_LIST);
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					for(int i=startIndex;i<=endIndex;i++)
					{	
						YellowSlipEntryDtlVO yellowSlipEntryVO=(YellowSlipEntryDtlVO)list.get(i);
			%>
			<tr>
				<td width="5%"  class="tdfont">
					<div align="center">
						<html:radio name="yellowSlipMonitoringFB" property="selectedRecord" value="<%= yellowSlipEntryVO.getPatCrNo()+"#"+ yellowSlipEntryVO.getEpisodeCode()+"#"+
						yellowSlipEntryVO.getEpisodeVisitNo()+"#"+yellowSlipEntryVO.getDepartmentUnitCode()+"#"+yellowSlipEntryVO.getSeatId()+"#"+yellowSlipEntryVO.getSerialNo()%>"
						 tabindex="1" onclick="getYellowSlipEntryDetail(this)"></html:radio>
					</div>
				</td>
				<td width="15%"  class="tdfont">
					<div align="center">
						<%= yellowSlipEntryVO.getPatCrNo()%>
					</div>
				</td>
				<td width="15%"  class="tdfont">
					<div align="center">
						<%= yellowSlipEntryVO.getPatName()%>
					</div>
				</td>
				<td width="15%"  class="tdfont">
					<div align="center">
						<%= yellowSlipEntryVO.getVisitDate()%>
					</div>
				</td>
				<td width="15%"  class="tdfont">
					<div align="center">
						<%= yellowSlipEntryVO.getDepartment()+"/"+yellowSlipEntryVO.getDepartmentUnit()%>
					</div>
				</td>
				<logic:equal name="seatIdValue" value="%">
				<td width="15%"  class="tdfont">
					<div align="center">
						<%= yellowSlipEntryVO.getUserName()%>
					</div>
				</td>
				<td width="15%"  class="tdfont">
					<div align="center">
						<%= yellowSlipEntryVO.getEntryDate()%>
					</div>
				</td>
				</logic:equal>
			</tr>
			<%} %>
		</table>
		</logic:present>		
	</his:ContentTag>

</his:statusList>
<his:statusTransactionInProcess>
 <jsp:include page="/registration/patientDetail.cnt" flush="true" /> 
<his:SubTitleTag name="Episode Detail"> 
	<b><bean:message key="episodeDate"/>
	<bean:write name="yellowSlipMonitoringFB" property="episodeDate"/></b>
</his:SubTitleTag>
<his:ContentTag>
<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<%boolean readonly=true; %>
		<%if(fb.getModificationRequired().equals("1") || fb.getMonitoringMode().equals("0")){
			readonly=false;	
		}
		else{
			readonly=true;		
		}%>	
		<tr>
			<td width="25%" class="tdfonthead"  >
				
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="department"/>
				</font>
				
			</td>
			<td width="25%" class="tdfont">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<bean:write name="yellowSlipMonitoringFB" property="department"/>
				</font>
			</td>
			<td width="25%" class="tdfonthead">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="unit"/>
				</font>
			</td>
			<td width="25%" class="tdfont">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<bean:write name="yellowSlipMonitoringFB" property="departmentUnit"/>
				</font>
			</td>
		</tr>	
		<tr>  
						
			<td width="25%" class="tdfonthead" align="left" >
				<b><font color="#FF0000">*</font></b> 
				<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="episodeStatus" /> </font>
			</td>
			<td width="25%" class="tdfont">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				&nbsp;<bean:message key="open" /> </font> 
				<html:radio name="yellowSlipMonitoringFB" property="episodeIsOpen" tabindex="1"
				value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onclick="showEpisodeOpenDiv()" disabled="<%=readonly%>"/> <font color="#000000"
				size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="close" /> </font> 
				<html:radio name="yellowSlipMonitoringFB" property="episodeIsOpen" tabindex="1"
				value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onclick="showEpisodeOpenDiv()" disabled="<%=readonly%>"/>
			</td>
		 
			   <td  class="tdfonthead" >
						<div   align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font></b>
						<bean:message key="con" />
						</font>
						</div>
				</td>
				<td  class="tdfont">
			   <div align="left">
			   &nbsp;<html:select name="yellowSlipMonitoringFB" tabindex="1" property="unitConsultantCode" styleClass="regcbo" disabled="<%=readonly %>">
					<html:option value="-1">Select Value</html:option>
		  			<html:options collection="<%=RegistrationConfig.UNIT_CONSULTANT_LIST%>" property="value" labelProperty="label" />
			   </html:select>
			   </div>
		       </td>   
        
			
		</tr>
	<tr>
		
		<bean:define name="yellowSlipMonitoringFB" property="episodeNextVisitDate" id="nextVisitDate" type="java.lang.String" />
		<% if(nextVisitDate==null || nextVisitDate.equalsIgnoreCase("")){ 
       	 
        	
			nextVisitDate = "";
       	 }%>
		<td  class="tdfonthead" >
				<div  id="nextVisitDatelabel" align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<!--				<b><font color="#FF0000">*</font>-->
				<bean:message key="nextVisitDate" />
				</font>
				</div>
		</td>
		 <td  class="tdfont">   
	    	<div id="nextVisitDateControl" align="left">
	    	&nbsp;<his:date name="episodeNextVisitDate" dateFormate="%d-%b-%Y" value="<%=nextVisitDate %>"/>	</div>
	    </td>
	 	 <td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<!--				<b><font color="#FF0000">*</font>-->
				<bean:message key="complaint" />
				</font>
				</div>
		</td>
		<td width="25%" class="tdfont">
			<div>
				&nbsp;<html:textarea name="yellowSlipMonitoringFB" property="complainDetail" cols="30" tabindex="1" onkeypress="return CheckMaxLength(event,this,500,3)" readonly="<%=readonly%>"></html:textarea>
			</div>
		</td>
	  
	</tr>
	</table>
	</his:ContentTag>
	
	<his:SubTitleTagBroad name="Diagnosis Detail">
		<%if(fb.getModificationRequired().equals("1") || fb.getMonitoringMode().equals("0")){ %>
		<table>
			<tr>
				<td width="95%" ></td>
				<td  align="right" width="5%" >
					<div align="center">	           
					<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openSearchPopup(event)" onkeypress=" openSearchPopup(event);" tabindex="1">
					</div>
		  		</td>   
			</tr>
		</table>
		<%} %>
	</his:SubTitleTagBroad>	
	<his:ContentTag>
	<table id="diagnosisTable" width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
			<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="icdCode"/></b>
				</font>
				</div>
	  		</td>
	  		<td  width="40%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="diagnosisName"/></b>
				</font>
				</div>
	  		</td>
	  	</logic:equal>	
	  	<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE%>">
	  		<td  width="10%" class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="hospitalDiagnosisCode"/></b>
					</font>
					</div>
		  	</td>
	  		<td  width="40%" class="tdfonthead">
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
	  		<td width="20%" class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="displayFlag"/></b>
				</font>
				</div>
	  		</td>
	  		 --%>
	  		<td  width="20%" class="tdfonthead">
				<div align="center">	           
					<b>Remove/Add</b>
				</div>
	  		</td>   
	  		
	  	</tr>  
	  	<logic:present name="<%=RegistrationConfig.EPISODE_DIAGNOSIS_VO_LIST %>">
		<logic:iterate id="episodeDiagnosisVO" name="<%=RegistrationConfig.EPISODE_DIAGNOSIS_VO_LIST %>" type="hisglobal.vo.EpisodeDiagnosisVO" indexId="index">
		<tr id="<%="row"+(index+1) %>">
	  		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
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
	  		</logic:equal>
	  		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">
	  		
	  		<td  class="tdfont">
	  			<div align="center">
	  			<bean:write name="episodeDiagnosisVO" property="diagnosticCode"/>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<bean:write name="episodeDiagnosisVO" property="dignosisName"/>
	  			</div>
	  		</td>
	  		</logic:equal>
	  		<td class="tdfont">
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
	  		<td width="5%" class="tdfont">
	  		<%if(fb.getModificationRequired().equals("1") || fb.getMonitoringMode().equals("0")){ %>
			<div id="checkBoxDiv<%=index%>" align="center">
				<html:checkbox name="yellowSlipMonitoringFB" property="diagnosisRemoved" tabindex="1" value="<%=episodeDiagnosisVO.getSerialNo()%>" title="Check to remove diagnosis"> </html:checkbox>
			</div>
			<%} %>
			</td> 
	  	</tr>
		</logic:iterate>
		</logic:present> 
		
		<%if(fb.getModificationRequired().equals("1") || fb.getMonitoringMode().equals("0")){ %>
		<tr>
	  		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
	  		<td class="tdfont">
	  			<div align="center">
	  			 <html:text name="yellowSlipMonitoringFB"  maxlength="3" size="5" property="icdCode" tabindex="1" 
	  							onkeypress="return validateAlphaNumericOnly(event,this)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text> 
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="disease" size="50" tabindex="1" 
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
	  		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB"  maxlength="6" size="6" property="hospitalDiseaseCode" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"
	  							onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="hospitalDiseaseName" size="50" tabindex="1" 
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
	  			 <html:select name="yellowSlipMonitoringFB" tabindex="1" property="diagonisticTypeCode" styleClass="regCbo" >
					<html:option value="-1">Select Value</html:option>
  					<html:options  collection="<%=RegistrationConfig.DIAGNOSIS_LIST%>" property="value" labelProperty="label" />
	  			</html:select> 
	  			</div>
	  		</td>
	  		<html:hidden name="yellowSlipMonitoringFB" property="displayFlag" />
	  		<%-- 
	  		<td class="tdfont">
	  		<div align="center">
	  			 <html:select name="yellowSlipMonitoringFB" tabindex="1" property="displayFlag" styleClass="regCbo">
	  				<html:option value="<%=RegistrationConfig.DISPLAY_FLAG_NONE %>">None</html:option>
  					<html:option value="<%=RegistrationConfig.DISPLAY_FLAG_RIGHT %>">Right</html:option>
  					<html:option value="<%=RegistrationConfig.DISPLAY_FLAG_LEFT %>">Left</html:option>
	  			</html:select> 
	  			</div>
	  		</td>
	  		--%>
	  		<td  class="tdfont">
	  		<div id="addDiv" align="center">
				<%-- <img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) if (validateAdd()) addDiagnosisRow() ;" onclick="if (validateAdd()) addDiagnosisRow();" tabindex="1"> --%>
				<%-- <img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) if (validateAdd('ADD')) submitForm('ADD') ;" onclick="if (validateAdd('ADD')) submitForm('ADD')" tabindex="1"> --%>
				<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) return addRow();" onclick="return addRow();" tabindex="1">
			</div>
			</td> 
	  	</tr>
	  <%} %>
	  	
	  	<% Map outerMap=(Map)session.getAttribute("outerMap");
		if (outerMap!=null){%>
		<logic:iterate id="outerMapID" name="outerMap" indexId="j" type="java.util.Map.Entry"  >
							
			<bean:define  name="outerMapID" property="value"  id="innerList" type="java.util.List"/> 
	  	<tr>
	  		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_ICD_CODE%>">
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="icdCode1" value="<%=(String) innerList.get(0) %>" readonly="true"  size="5"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="disease1" size="50" value="<%=(String) innerList.get(1) %>" readonly="true"></html:text>
	  			</div>
	  		</td>
	  		</logic:equal>
	  		
	  		<logic:equal name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE%>">
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="icdCode1" value="<%=(String) innerList.get(0) %>" readonly="true" size="6"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="disease1" size="50" value="<%=(String) innerList.get(1) %>" readonly="true"></html:text>
	  			</div>
	  		</td>
	  		</logic:equal>
	  		
	  		<html:hidden name="yellowSlipMonitoringFB"  property="diagonisticTypeCode1" value="<%=(String) innerList.get(2) %>"  />
	  		
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="diagonisticTypeName" value="<%=(String) innerList.get(3) %>" readonly="true" >
					</html:text>
	  			</div>
	  		</td>
	  		
	  			<html:hidden name="yellowSlipMonitoringFB"  property="displayFlag1" value="<%=(String) innerList.get(4) %>"  />
	  		<%--	
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:text name="yellowSlipMonitoringFB" property="displayFlagLabel" value="<%=(String) innerList.get(5) %>" readonly="true"></html:text>
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
		<%} %>
	 </table>
</his:ContentTag>
<logic:equal name="yellowSlipMonitoringFB" property="monitoringMode" value="1">
<div id="monitoringDiv" style="display: block">
<his:SubTitleTag name="Monitoring Detail">
	<b><bean:message key="isModificationRequired"/></b>
	<html:checkbox name="yellowSlipMonitoringFB" property="modificationRequired" value="<%=fb.getModificationRequired()%>" tabindex="1" onclick="toggleFormDisplay(this)" />
</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellspacing="1">
			<tr>
				<td width="25%" class="tdfonthead">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font></b>
					<bean:message key="entryQuality"/>
					</font>
				</td>
				<td width="25%" class="tdfont">
					<html:select name="yellowSlipMonitoringFB" property="monitoringFlag" styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=RegistrationConfig.YELLOW_SLIP_MONITORING_FLAG_NO_ERROR %>">No Error</html:option>
						<html:option value="<%=RegistrationConfig.YELLOW_SLIP_MONITORING_FLAG_MINOR_ERROR%>">Minor Error</html:option>
						<html:option value="<%=RegistrationConfig.YELLOW_SLIP_MONITORING_FLAG_MAJOR_ERROR%>">Major Error</html:option>
					</html:select>
				</td>
				<td width="25%" class="tdfonthead">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font></b>
					<bean:message key="remarks"/>
					</font>
				</td>
				<td width="25%" class="tdfont">
					<html:textarea name="yellowSlipMonitoringFB" property="monitoringRemarks" tabindex="1" cols="30" onkeypress="return CheckMaxLength(event,this,500,3)"></html:textarea>
				</td>
			</tr>
			
		</table>
	</his:ContentTag>
	</div>
</logic:equal>	
</his:statusTransactionInProcess>



<his:ButtonToolBarTag>
		<div align="center"> 
			<his:statusNew>	
				<img class="button"
				src='<his:path src="/hisglobal/images/btn-go.png"/>' style="cursor:pointer"
				tabindex="1" onclick="return getYellowSlipEntryUser()"
				onkeypress="if(event.keyCode==13) return getYellowSlipEntryUser()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor:pointer" 	onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1"
				onclick="submitForm('CANCEL');"> 
			</his:statusNew>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor:pointer" 	onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
				onclick="submitForm('NEW');"> 
			</his:statusList>	
			<his:statusDone>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor:pointer" 	onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
				onclick="submitForm('NEW');"> 
			</his:statusDone>	
		</div>
		<his:statusTransactionInProcess>
			<div align="center">
			<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateYellowSlipEntry('SAVE');"
			tabindex="1"
			onclick="validateYellowSlipEntry('SAVE')"> 
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
			onclick="submitForm('NEW')"> <img class="button"
			src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer"
			tabindex="1" onclick="clearForm()"
			onkeypress="if(event.keyCode==13) clearForm();">
			</div>
		</his:statusTransactionInProcess>	
</his:ButtonToolBarTag>
<div id="sid" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="selid" size="10" tabindex="1" onKeyDown="hideta(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
</div>

<his:status/>
<html:hidden name="yellowSlipMonitoringFB" property="hmode"/>
<html:hidden name="yellowSlipMonitoringFB" property="mode"/>
<html:hidden name="yellowSlipMonitoringFB" property="patCrNo"/>
<html:hidden name="yellowSlipMonitoringFB" property="unitDiagnosisTypeCode"/>
<html:hidden name="yellowSlipMonitoringFB" property="seatId"/>
<html:hidden name="yellowSlipMonitoringFB" property="departmentUnitCode"/>
<html:hidden name="yellowSlipMonitoringFB" property="departmentUnit"/>
<html:hidden name="yellowSlipMonitoringFB" property="department"/>
<html:hidden name="yellowSlipMonitoringFB" property="episodeCode"/>
<html:hidden name="yellowSlipMonitoringFB" property="episodeVisitNo"/>
<html:hidden name="yellowSlipMonitoringFB" property="currentPage"/>
<html:hidden name="yellowSlipMonitoringFB" property="diagonisticTypeHtml"/>
<html:hidden name="yellowSlipMonitoringFB" property="episodeDate"/>
<html:hidden name="yellowSlipMonitoringFB" property="isAddDiagnosis"/>
<html:hidden name="yellowSlipMonitoringFB" property="episodeNextVisitDeptUnitCode"/>
<html:hidden name="yellowSlipMonitoringFB" property="episodeTypeCode"/>
<html:hidden name="yellowSlipMonitoringFB" property="removeField"/>
<html:hidden name="yellowSlipMonitoringFB" property="userName"/>
<html:hidden name="yellowSlipMonitoringFB" property="checkedItem"/>
<html:hidden name="yellowSlipMonitoringFB" property="serialNo"/>
<input type="hidden" name="noOfRows"/>
<input type="hidden" name="sysdate" value="<%=systemDate%>"/>

</his:TransactionContainer>
</html:form>
<%}catch (Exception e){e.printStackTrace();}%>