<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>


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
<script language="Javascript" src="../js/patientListing.js">
<script type="text/javascript">
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
function validate1NA()
{
	var hisValidator = new HISValidator("DeskFB"); 
	hisValidator.addValidation("strCrNo", "req", "CR No.  is a Mandatory Field" );
	hisValidator.addValidation("strOccEmpName", "req", "Patient Name  is a Mandatory Field in Patient Occupation Detail" ); 
	hisValidator.addValidation("strOccRelation","dontselect=0","Please select a Relation in Patient Occupation Detail");
	hisValidator.addValidation("strOccBasic", "req", "Please Enter Basic Salary in Patient Occupation Detail" );
	hisValidator.addValidation("strOccDesc", "req", "Please Enter Designation in Patient Occupation Detail" );
	hisValidator.addValidation("strOccOrgType","dontselect=0","Please select a Orgnisation type in Patient Occupation Detail");
	hisValidator.addValidation("strOccOffName", "req", "Please Enter office name in Patient Occupation Detail" );
	hisValidator.addValidation("strOccAdd1", "req", "Please Enter office address in Patient Occupation Detail" );
	hisValidator.addValidation("strOccCity", "req", "Please Enter city in Patient Occupation Detail" );
	hisValidator.addValidation("strOccState","dontselect=0","Please select state in Patient Occupation Detail");
	hisValidator.addValidation("strOccOffPhNo", "req", "Please Enter office phone number in Patient Occupation Detail" );
	var retVal = hisValidator.validate(); 
		if(retVal)
		{
			
		if(document.forms[0].strOccRelation.disabled=true)
		{
			document.forms[0].strOccRelation.disabled=false;
			document.forms[0].strOccOrgType.disabled=false;
			document.forms[0].strOccState.disabled=false;
		}
		
	
		if(document.forms[0].strFlag.value==1)
		{
			document.forms[0].hmode.value = "INSERT";
			document.forms[0].deskmode.value="NEWADM";
			document.forms[0].submit();
			//window.close();
		}
		else
		{
			alert("There is no vacant bed in this ward.Please select another ward and room");
			return false;
		}
	}
	
	
}
function goRetFuncNA(obj)
{
	if(obj.keyCode==13)
	{
		var hisValidator = new HISValidator("DeskFB");  
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal){
					document.forms[0].hmode.value="GOPatient";
					document.forms[0].deskmode.value="NEWADM";
					document.forms[0].submit();
		}else{
		
		return false;
		}
	}
}
function goFuncNA(){
	
	var hisValidator = new HISValidator("DeskFB");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo", "minlen=13", "CR No. must be 13 Digits" );
	var retVal = hisValidator.validate(); 
	if(retVal){
				//document.forms[0].deskmode.value="GO"
				document.forms[0].hmode.value="GOPatient";
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
}
function bedDetails()
{
		var hmode = "BEDSTATUS"; 
		var url='PatientAdmissionTransCNT.cnt?hmode='+hmode+'&wardTypeCode='+document.forms[0].strWardTypeCode.value+'&roomTypeCode='+document.forms[0].strRoomTypeCode.value+
	    '&wardCode='+document.forms[0].strWardCode.value+'&bedTypeCode='+document.forms[0].strBedTypeCode.value
	    +'&deptCode='+document.forms[0].strDeptCode.value+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strDeptUnitCode.value;
		var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		if(document.forms[0].strMsApprovalStatus.value!=2)
		{
			 myWindow = window.open(createFHashAjaxQuery(url),'popupWindow',featuresList); 
			 myWindow.focus();
		}
		else
		{
			alert("Patient already get MS Approval");
		}

}
/*function viewA()
{
	alert("Patient Admission")
	if(document.forms[0].strCrNo.value!="")
	{
		if(document.forms[0].strAdmissionCharge.value=="1")
		{
			var o=document.getElementById("admissionId");
			o.style.display="block";
			document.forms[0].strAdmissionChargeValue.readOnly="false";
		}
		var o=document.getElementById("id4");
		o.style.display="block";
		var o=document.getElementById("patientDtlID");
		o.style.display="block";
		var o1=document.getElementById("PatientOccId");
		o1.style.display="block";
		var obj=document.getElementById("wardDivId");
		obj.style.display="block";
		o=document.getElementById("PatientOccDtl");
		o.style.display="none";
		o=document.getElementById("newModificationId");
		o.style.display="block";
		if(document.forms[0].strNewBorn.value==1)
		{
			var obj1=document.getElementById("id5");
			obj1.style.display="block";
			var obj2=document.getElementById("newAddressModiId");
			obj2.style.display="none";
		}
		else
		{
			var obj1=document.getElementById("plusAddModiId");
			obj1.style.display="none";
			var obj2=document.getElementById("minusAddModiId");
			obj2.style.display="block";
		}
		var o3=document.getElementById("patientCrEdId");
		var o4=document.getElementById("patientCrId");
		o3.style.display="none";
		o4.innerHTML=document.forms[0].strCrNo.value;
		o4.style.display="block";
	}
	else
	{
		document.forms[0].strCrNo.focus();
		var o=document.getElementById("id4");
		o.style.display="none";
		var o=document.getElementById("patientDtlID");
		o.style.display="none";
		o=document.getElementById("PatientOccId");
		o.style.display="none";
		var obj=document.getElementById("wardDivId");
		obj.style.display="none";
		var o3=document.getElementById("patientCrEdId");
		var o4=document.getElementById("patientCrId");
		o3.style.display="block";
		o4.style.display="none";
		o4=document.getElementById("id5");
		o4.style.display="none";
		o4=document.getElementById("minusAddModiId");
		o4.style.display="none";
		o4=document.getElementById("newModificationId");
		o4.style.display="none";
		o4=document.getElementById("newAddressModiId");
		o4.style.display="none";
		o4=document.getElementById("admissionId");
		o4.style.display="none";
		document.getElementById("PatientOccDtl").style.display="none";
	}
	if(document.forms[0].strPatStatusCode.value==13)
		{
			var o4=document.getElementById("plusAddModiId");
			o4.style.display="block";
			o4=document.getElementById("patientDtlID");
			o4.style.display="none";
			o4=document.getElementById("newModificationId");
			o4.style.display="none";
			o4=document.getElementById("newAddressModiId");
			o4.style.display="none";
			o4=document.getElementById("PatientOccId");
			o4.style.display="none";
			o4=document.getElementById("wardDivId");
			o4.style.display="none";
			document.getElementById("admissionId").style.display="none";
		}
		if(document.forms[0].strPatStatusCode.value==11)
		{
			var o4=document.getElementById("plusAddModiId");
			o4.style.display="block";
			o4=document.getElementById("patientDtlID");
			o4.style.display="none";
			o4=document.getElementById("newModificationId");
			o4.style.display="none";
			o4=document.getElementById("newAddressModiId");
			o4.style.display="none";
			o4=document.getElementById("PatientOccId");
			o4.style.display="none";
			o4=document.getElementById("wardDivId");
			o4.style.display="none";
			document.getElementById("admissionId").style.display="none";
		}
		if(document.forms[0].strAdviceStatus.value==0)
		{
			var o4=document.getElementById("plusAddModiId");
			o4.style.display="block";
			o4=document.getElementById("patientDtlID");
			o4.style.display="none";
			o4=document.getElementById("newModificationId");
			o4.style.display="none";
			o4=document.getElementById("newAddressModiId");
			o4.style.display="none";
			o4=document.getElementById("PatientOccId");
			o4.style.display="none";
			o4=document.getElementById("wardDivId");
			o4.style.display="none";
			document.getElementById("admissionId").style.display="none";
		}
}	*/
function view1()
{
	
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("PatientOccDtl");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";
	
	
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
 
</script>


	<div class="errMsg"><bean:write name="paientAdmissionTransBean_desk"
		property="strMsgString" /></div>
	<div class="normalMsg"><bean:write name="paientAdmissionTransBean_desk" property="strMsg"/></div>
<div class="warningMsg"><bean:write name="paientAdmissionTransBean_desk" property="strWarningMsg"/></div>
	

	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Patient Admission</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>CR No.</td>
		<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId" style="display: block;">
				<crNo:crNo value="${paientAdmissionTransBean_desk.strCrNo}" js="	onkeypress='return validateData(event,5);'  onkeyup='goRetFuncNA(event);'" id="strCrNoId"></crNo:crNo>
				<img src="../../hisglobal/images/Go.png"
				onClick="return goFuncNA();" align="top" style="cursor: pointer; cursor: hand" > 
			</div>
			<div id="patientCrId" style="display: none;"></div>
		</td>
		</tr>
	</table>
	
	<div id="id4" style="display:none;">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<pDtl:patDtl crNo="${paientAdmissionTransBean_desk.strCrNo}" address="false"></pDtl:patDtl>
		</table>
	</div>
		<div id="patientDtlID">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department/Unit</td>
			<td width="25%" class="CONTROL"><bean:write name="paientAdmissionTransBean_desk" property="strDeptUnitName"/></td>
			<td width="25%" class="LABEL">Consultant</td>
			<td width="25%" class="CONTROL">
			<bean:write name="paientAdmissionTransBean_desk" property="strConsultantName"/>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Treatment Category</td>
			<td width="25%" class="CONTROL">
				<bean:write name="paientAdmissionTransBean_desk" property="strTreatmentCategoryName"/>
			</td>
			<td width="25%" class="LABEL" colspan="2"></td>
		</tr>
	</table>
	</div>
	<div id="newModificationId" style="display:none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  colspan="4" class="TITLE">
			<div id="plusAddModiId" align="left" style="display:block;">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view3();" style="cursor: pointer; cursor: hand"/>
						New Modification
					</div>
					<div id="minusAddModiId" style="display:none;" align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view4();" style="cursor: pointer; cursor: hand"/>
								New Modification
					</div>
				</td>
		</tr>
	</table>
	</div>
	<div id="newAddressModiId">
		<bean:write name="paientAdmissionTransBean_desk" property="strAddressModi" filter="false"/>
	</div>
	
		<div id="id5" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="4" class="TITLE">New Born Baby Status</td>
		</tr>
		<tr>
			
			<td width="25%" class="LABEL" colspan="1">Mother Name</td>
			<td width="25%" class="CONTROL" colspan="3">
				<bean:write name="paientAdmissionTransBean_desk" property="strMotherName" filter="false"/>
			
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother CR No.</td>
			<td width="25%" class="CONTROL">
				<bean:write name="paientAdmissionTransBean_desk" property="strMotherCrNo" filter="false"/>
			</td>
			<td width="25%" class="LABEL">Mother Admission No.</td>
						<td width="25%" class="CONTROL">
							<bean:write name="paientAdmissionTransBean_desk" property="strMotherAdmissionNo" filter="false"/>
			</td>
			
		</tr>
		<tr>
			<td width="25%" class="LABEL">Mother Nationality</td>
			<td width="25%" class="CONTROL">
				<bean:write name="paientAdmissionTransBean_desk" property="strMotherNationality" filter="false"/>
			</td>
			<td width="25%" class="LABEL">Mother Religion</td>
			<td width="25%" class="CONTROL">
				<bean:write name="paientAdmissionTransBean_desk" property="strMotherReligion" filter="false"/>
			</td>
		</tr>
		</table>
		</div>
		<div id="PatientOccId" style="display:none">
			<!--  --><table class="TABLEWIDTH" align="center" cellspacing="1px">		
				<tr>
					<td  colspan="4" class="TITLE">
				<div id="id1" align="left" style="display:block;">
					<img src="../../hisglobal/images/plus.gif" 
									onClick="view1();" style="cursor: pointer; cursor: hand"/>
						Patient Occupation Details
					</div>
					<div id="id2" style="display:none;" align="left">
						<img src="../../hisglobal/images/minus.gif" 
								onClick="view2();" style="cursor: pointer; cursor: hand"/>
								Patient Occupation Details
					</div>
					</td>
				</tr>
			</table>
		</div>
			<div id ="PatientOccDtl" style="display:block; ">
				<bean:write name="paientAdmissionTransBean_desk" property="occupationDetailValues" filter="false"/>
			</div>
			<div id="wardDivId" style="display:none">	
			<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr>
				<td colspan="4" class="TITLE">Ward Bed Status</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%">Bed Status</td>
				<td  class="multiControl" width="25%">
				<img src="../../hisglobal/images/Bed_.gif" onClick="bedDetails();" align="middle" onmouseover="" style="cursor: pointer; cursor: hand"/>
				</td>
				<td  class="LABEL" width="25%" colspan="2"></td>
				
			</tr>
			<tr>
				<td width="25%" class="LABEL">Ward</td>
				<td width="25%" class="CONTROL">
				<div id="wardNameId"><bean:write name="paientAdmissionTransBean_desk" property="strWardName"/></div>
				</td>
				<td width="25%" class="LABEL">Room
				</td>
				<td width="25%" class="CONTROL">
					<div id="roomBedId">
						<bean:write name="paientAdmissionTransBean_desk" property="strRoom"/>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" colspan="1" class="LABEL">Remarks</td>
				<td width="25%" colspan="3" class="CONTROL"><textarea rows="3"
				cols="25" name="strRemarks"></textarea></td>
			</tr>
			
		</table>
	</div>
	<div id="admissionId" style="display: none">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class="LABEL" width="25%">Admission Charges</td>
				<td  class="CONTROL" width="25%"><input type="text" name="strAdmissionChargeValue" value="${paientAdmissionTransBean_desk.strAdmissionChargeValue }" class="txtFldMin"></td>
				<td  class="LABEL" width="25%"></td>
				<td  class="LABEL" width="25%"></td>
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
				onClick="return validate1NA();" style="cursor: pointer; cursor: hand"/>
			<img src="../../hisglobal/images/btn-clr.png"
				onClick="clearRecord();" style="cursor: pointer; cursor: hand"/>
			<img src="../../hisglobal/images/btn-ccl.png"
				onClick="cancelFunc();" /style="cursor: pointer; cursor: hand"/>
			</td>
		</tr>
	</table>

	<input type="hidden" name="hmode7"/>
	<input type="hidden" name="strWardTypeCode" value="${paientAdmissionTransBean_desk.strWardTypeCode}"/>
	<input type="hidden" name="strDeptUnitCode" value="${paientAdmissionTransBean_desk.strDeptUnitCode}"/>
	<input type="hidden" name="strTreatmentCategoryCode" value="${paientAdmissionTransBean_desk.strTreatmentCategoryCode}"/>
	<input type="hidden" name="strWardCode" value="${paientAdmissionTransBean_desk.strWardCode}"/>
	<input type="hidden" name="strBedTypeCode" value="${paientAdmissionTransBean_desk.strBedTypeCode}"/>
	<input type="hidden" name="strWardName" value="${paientAdmissionTransBean_desk.strWardName}"/>
	<input type="hidden" name="strRoomTypeCode" value="${paientAdmissionTransBean_desk.strRoomTypeCode}"/>
	<input type="hidden" name="strDeptCode" value="${paientAdmissionTransBean_desk.strDeptCode}"/>
	<input type="hidden" name="strConsultantCode" value="${paientAdmissionTransBean_desk.strConsultantCode}"/>
	<input type="hidden" name="strEpisodeCode" value="${paientAdmissionTransBean_desk.strEpisodeCode}"/>
	<input type="hidden" name="strVisitNo" value="${paientAdmissionTransBean_desk.strVisitNo}"/>
	<input type="hidden" name="strMlcNo" value="${paientAdmissionTransBean_desk.strMlcNo}"/>
	<input type="hidden" name="strAdviceAdmNo" value="${paientAdmissionTransBean_desk.strAdviceAdmNo}"/>
	<input type="hidden" name="strIsUrban" value="${paientAdmissionTransBean_desk.strIsUrban}"/>
	<input type="hidden" name="strBedCode" value="${paientAdmissionTransBean_desk.strBedCode}"/>
	<input type="hidden" name="strRoomCode" value="${paientAdmissionTransBean_desk.strRoomCode}"/>
	<input type="hidden" name="strBookingDate" value="${paientAdmissionTransBean_desk.strBookingDate}"/>
	<input type="hidden" name="strFlag" value="1"/>
	<input type="hidden" name="strMsApprovalFlag" value="${paientAdmissionTransBean_desk.strMsApprovalFlag}"/>
	<input type="hidden" name="strMsApprovalStatus" value="${paientAdmissionTransBean_desk.strMsApprovalStatus}"/>
	<input type="hidden" name="strNewBorn" value="${paientAdmissionTransBean_desk.strNewBorn}"/>
	<input type="hidden" name="strMotherCrNo" value="${paientAdmissionTransBean_desk.strMotherCrNo}"/>
	<input type="hidden" name="strMotherAdmissionNo" value="${paientAdmissionTransBean_desk.strMotherAdmissionNo}"/>
	<input type="hidden" name="strMotherNationalityCode" value="${paientAdmissionTransBean_desk.strMotherNationalityCode}"/>
	<input type="hidden" name="strMotherReligionCode" value="${paientAdmissionTransBean_desk.strMotherReligionCode}"/>
	<input type="hidden" name="strMotherName" value="${paientAdmissionTransBean_desk.strMotherName}"/>
	<input type="hidden" name="strAdmissionCharge" value="${paientAdmissionTransBean_desk.strAdmissionCharge}"/>
	<input type="hidden" name="strPatStatusCode" value="${paientAdmissionTransBean_desk.strPatStatusCode}"/>
	<input type="hidden" name="strAdviceStatus" value="${paientAdmissionTransBean_desk.strAdviceStatus}"/>
	<input type="hidden" name="strNoOfFreePass" value="${paientAdmissionTransBean_desk.strNoOfFreePass}"/>
	<input type="hidden" name="strFreePassValid" value="${paientAdmissionTransBean_desk.strFreePassValid}"/>
	
	

