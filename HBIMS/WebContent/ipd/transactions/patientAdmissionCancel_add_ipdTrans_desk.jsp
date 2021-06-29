
<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>

<html>
<head>
<meta charset=utf-8>
<title>Patient Admission Cancellation</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script type="text/javascript">
function validate1()
{
	
	var hisValidator = new HISValidator("DeskFB"); 
	hisValidator.addValidation("strRemarks", "req", "Cancel Reason is Mandatory Field" ); 
	hisValidator.addValidation("strConsultantName","dontselect=0","Please Select Approved By");
	
	var retVal = hisValidator.validate(); 
	if(retVal)
	{
		document.forms[0].hmode.value = "INSERT";
		document.forms[0].deskmode.value="ADMCANCEL";
		document.forms[0].submit();
		window.close();
	}
}
function getAjaxResponse(res,mode){
		if(mode==1)
		{
			document.getElementById("unitDivId").innerHTML="<select name='strUnitNewBorn'>"+res+"</select>";
			
		}
		
	}
	
function goRetFunc(obj)
{
	if(obj.keyCode==13)
	{
		var hisValidator = new HISValidator("DeskFB");  
		hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
		//hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal){
					document.forms[0].hmode.value="GO";
					document.forms[0].deskmode.value="ADMCANCEL";
					document.forms[0].submit();
		}else{
		
		return false;
		}
	}
}
function goFunc(){
	
	var hisValidator = new HISValidator("DeskFB");  
	hisValidator.addValidation("strCrNo", "req", "Cr No. is a Mandatory Field" );
	//hisValidator.addValidation("strCrNo", "minlen=13", "Cr No. must be 13 Digits" );
	var retVal = hisValidator.validate(); 
	if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].deskmode.value="ADMCANCEL";
				document.forms[0].submit();
				
		}else{
		
		return false;
		}
		
}
function bedDetails()
{
		var hmode = "BEDSTATUS"; 
		var url='PatientAdmissionModiTransCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strWardTypeCode.value+'&roomTypeCode='+document.forms[0].strMotherRoomTypeCode.value+
	    '&wardCode='+document.forms[0].strWardCode.value+'&deptCode='+document.forms[0].strDeptCode.value+'&crNo='+document.forms[0].strCrNo.value;
		var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		 myWindow = window.open(url,'popupWindow',featuresList); 
		myWindow.focus();

}
function view()
{   
	
	if(document.forms[0].strCrNo.value!="")
	{
		if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered
		{
			document.getElementById("id4").style.display="block";
			document.getElementById("wardDivId").style.display="block";
			//document.getElementById("newModificationId").style.display="block";
			//document.getElementById("PatientOccId").style.display="block";
			document.getElementById("patientCrEdId").style.display="none";
			document.getElementById("patientCrId").style.display="block";
			document.getElementById("newModificationFormId").style.display="block";
			
			document.getElementById("approvedById").style.display="block";
			//document.getElementById("PatientOccId").style.display="block";
			if(document.forms[0].strNewBorn.value==0)
			{
				
				document.getElementById("DeparmentUnitId").style.display="block";
			}
			if(document.forms[0].strNewBorn.value==1)
			{
				
				document.getElementById("DeparmentUnitModiId").style.display="block";
			}
		}
		else//only predefined Digits in CR Field
		{
			SetCursorToTextEnd('strCrNoId');
			document.getElementById("patientCrId").style.display="none";
			
			document.getElementById("newModificationFormId").style.display="none";
			document.getElementById("newAddressModiId").style.display="none";
			document.getElementById("DeparmentUnitModiId").style.display="none";
			document.getElementById("DeparmentUnitId").style.display="none";
			document.getElementById("wardDivId").style.display="none";
			document.getElementById("approvedById").style.display="none";
			document.getElementById("id4").style.display="none";
			document.getElementById("patientCrEdId").style.display="block";
			//document.forms[0].strCrNo.focus();
		}
	}
	else
	{
		//SetCursorToTextEnd('strCrNoId');
		document.getElementById("patientCrId").style.display="none";
		
		document.getElementById("newModificationFormId").style.display="none";
		document.getElementById("newAddressModiId").style.display="none";
		document.getElementById("DeparmentUnitModiId").style.display="none";
		document.getElementById("DeparmentUnitId").style.display="none";
		document.getElementById("wardDivId").style.display="none";
		document.getElementById("approvedById").style.display="none";
		document.getElementById("id4").style.display="none";
		document.getElementById("patientCrEdId").style.display="block";
		//document.forms[0].strCrNo.focus();
	}
	
}	
function view1()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
}
function getUnitCombo()
{
	var hmode = "GETUNIT"; 
	alert("Hello"+document.forms[0].StrDeptNameNewBorn.value);
	var url = "NewBornBabyTransCNT.cnt?hmode="+hmode+"&deptCode="+document.forms[0].StrDeptNameNewBorn.value;
	ajaxFunction(url,"1");

}
function view2()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
	var obj=document.getElementById("wardDivId");
	obj.style.display="block";
	
}
function view3()
{
	
	var o1=document.getElementById("plusAddModiId");
	var o2=document.getElementById("minusAddModiId");
	var o3=document.getElementById("newAddressModiId");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
}
function view4()
{
	
	var o1=document.getElementById("plusAddModiId");
	var o2=document.getElementById("minusAddModiId");
	var o3=document.getElementById("newAddressModiId");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
	var obj=document.getElementById("wardDivId");
	obj.style.display="block";
	
}
function clearRecord()
{
	document.forms[0].strCrNo.value="";
	view();
}
function funUnit()
{
	alert("Unit"+document.forms[0].strUnitNewBorn.value);
}
 
</script>
</head>
<body onload="view();">
	<html:form action="/transactions/AdmissionDeskCNT" method ="post" type="ipd.transactions.AdmissionDeskFB" >
	<div class="errMsg" id="errMsg"><bean:write name="patientAdmissionCanceTransBean_desk"
		property="strMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="patientAdmissionCanceTransBean_desk" property="strMsg"/>
	<div class="warningMsg"><bean:write name="patientAdmissionCanceTransBean_desk" property="strWarningMsg" /></div></div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Patient Admission Cancellation</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
		<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;">
				<crNo:crNo value="${patientAdmissionCanceTransBean_desk.strCrNo}" js="	onkeypress='return validateData(event,5);'  onkeyup='goRetFunc(event);'" id="strCrNoId"></crNo:crNo>
				<img src="../../hisglobal/images/Go.png"
				onClick="return goFunc();" align="top" style="cursor: pointer; cursor: hand" > 
			</div>
			<div id="patientCrId" style="display: none;">
				<bean:write name="patientAdmissionCanceTransBean_desk" property="strCrNo"/>
			</div>
		</td>
		</tr>
	</table>
	<div id="id4" style="display:none;">
	   	<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<pDtl:patDtl crNo="${patientAdmissionCanceTransBean_desk.strCrNo}" address="false"></pDtl:patDtl>
		</table>
		</div>
		<div id="newModificationFormId" style="display: none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td colspan="4" class="TITLE">
					Address Details
				</td>
			</tr>	
		</table>
		</div>
	<div id="newAddressModiId">
		<bean:write name="patientAdmissionCanceTransBean_desk" property="strAddressModi" filter="false"/>
	</div>
	<div id='DeparmentUnitModiId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
				<td width="25%" class="LABEL">Department</td>
				<td width="25%" class="CONTROL">
					<select name="StrDeptNameNewBorn" onchange="getUnitCombo();" class="comboMax">
						<bean:write property="strDeptValue" name="patientAdmissionCanceTransBean_desk" filter="false"/>
					</select>
				</td>
				<td width="25%" class="LABEL">Unit</td>
				<td width="25%" class="CONTROL">
					<div id="unitDivId">
						<select name="strUnitNewBorn"  class="comboNormal" onchange="funUnit()" >
							<bean:write property="strUnitValue" name="patientAdmissionCanceTransBean_desk" filter="false"/>
						</select>
					</div>
				</td>
			</tr>
	</table>
	</div>
	<div id='DeparmentUnitId' style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
				<td width="25%" class="LABEL">Department</td>
				<td width="25%" class="CONTROL">
					<bean:write property="strDeptName" name="patientAdmissionCanceTransBean_desk" filter="false"/>
				</td>
				<td width="25%" class="LABEL">Unit</td>
				<td width="25%" class="CONTROL">
					<bean:write property="strUnitName" name="patientAdmissionCanceTransBean_desk" filter="false"/>
				
				</td>
			</tr>
	</table>
	</div>
		
		
			<div id="wardDivId" style="display:none">	
			<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td colspan="4" class="TITLE">Ward Bed Status</td>
			</tr>
			
			
			<tr>
				<td width="25%" class="LABEL">Ward</td>
				<td width="25%" class="CONTROL">
				<div id="wardNameId"><bean:write name="patientAdmissionCanceTransBean_desk" property="strWardName"/></div>
				</td>
				<td width="25%" class="LABEL">Room
					</td>
				<td width="25%" class="CONTROL">
					<div id="roomBedId">
						<bean:write name="patientAdmissionCanceTransBean_desk" property="strRoom"/>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" colspan="1" class="LABEL"><font color="red">*</font>Cancel Reason</td>
				<td width="25%" colspan="3" class="CONTROL"><textarea rows="3"
				cols="25" name="strRemarks"></textarea></td>
			</tr>
			
		</table>
	</div>
	<div id="approvedById" style='display: none;'>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class="LABEL" width="25%"><font color="red">*</font>Approved By</td>
				<td  class="CONTROL" width="25%">
					<select name="strConsultantName">
						<bean:write name="patientAdmissionCanceTransBean_desk" property="strConsultantValues" filter='false'/>
					</select>
				</td>
				<td  class="CONTROL" width="25%" colspan="2"></td>
		</tr>
	</table>
	</div>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px"> 
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields		
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><img src="../../hisglobal/images/btn-sv.png"
				onClick="return validate1();" style="cursor: pointer; cursor: hand"/>
			<img src="../../hisglobal/images/btn-clr.png"
				onClick="clearRecord();" style="cursor: pointer; cursor: hand"/>
			<img src="../../hisglobal/images/btn-ccl.png"
				onClick="window.close();" /style="cursor: pointer; cursor: hand"/>
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode"/>
	<input type="hidden" name="deskmode"/>
	<input type="hidden" name="strWardTypeCode" value="${patientAdmissionCanceTransBean_desk.strWardTypeCode}"/>
	<input type="hidden" name="strDeptUnitCode" value="${patientAdmissionCanceTransBean_desk.strDeptUnitCode}"/>
	<input type="hidden" name="strTreatmentCategoryCode" value="${patientAdmissionCanceTransBean_desk.strTreatmentCategoryCode}"/>
	<input type="hidden" name="strWardCode" value="${patientAdmissionCanceTransBean_desk.strWardCode}"/>
	<input type="hidden" name="strBedTypeCode" value="${patientAdmissionCanceTransBean_desk.strBedTypeCode}"/>
	<input type="hidden" name="strWardName" value="${patientAdmissionCanceTransBean_desk.strWardName}"/>
	<input type="hidden" name="strRoomTypeCode" value="${patientAdmissionCanceTransBean_desk.strRoomTypeCode}"/>
	<input type="hidden" name="strDeptCode" value="${patientAdmissionCanceTransBean_desk.strDeptCode}"/>
	<input type="hidden" name="strConsultantCode" value="${patientAdmissionCanceTransBean_desk.strConsultantCode}"/>
	<input type="hidden" name="strEpisodeCode" value="${patientAdmissionCanceTransBean_desk.strEpisodeCode}"/>
	<input type="hidden" name="strVisitNo" value="${patientAdmissionCanceTransBean_desk.strVisitNo}"/>
	<input type="hidden" name="strMlcNo" value="${patientAdmissionCanceTransBean_desk.strMlcNo}"/>
	<input type="hidden" name="strAdviceAdmNo" value="${patientAdmissionCanceTransBean_desk.strAdviceAdmNo}"/>
	<input type="hidden" name="strIsUrban" value="${patientAdmissionCanceTransBean_desk.strIsUrban}"/>
	<input type="hidden" name="strBedCode" value="${patientAdmissionCanceTransBean_desk.strBedCode}"/>
	<input type="hidden" name="strRoomCode" value="${patientAdmissionCanceTransBean_desk.strRoomCode}"/>
	<input type="hidden" name="strBookingDate" value="${patientAdmissionCanceTransBean_desk.strBookingDate}"/>
	<input type="hidden" name="strFlag" value="1"/>
	<input type="hidden" name="strMsApprovalFlag" value="${patientAdmissionCanceTransBean_desk.strMsApprovalFlag}"/>
	<input type="hidden" name="strMsApprovalStatus" value="${patientAdmissionCanceTransBean_desk.strMsApprovalStatus}"/>
	<input type="hidden" name="strNewBorn" value="${patientAdmissionCanceTransBean_desk.strNewBorn}"/>
	<input type="hidden" name="strDeptName" value="${patientAdmissionCanceTransBean_desk.strDeptName}"/>
	<input type="hidden" name="strUnitName" value="${patientAdmissionCanceTransBean_desk.strUnitName}"/>
	<input type="hidden" name="strAdmNo" value="${patientAdmissionCanceTransBean_desk.strAdmNo}"/>
	</html:form>
	<tag:autoIndex></tag:autoIndex>
</body>
</html>