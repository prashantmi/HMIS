var ie = document.all;
var nn6 = document.getElementById &&! document.all;

var isdrag = false;
var x, y,tx,ty;
var dobj;
///////1
function movemouse( e ) {
 //alert(e.clientX);
  if( isdrag ) {
    dobj.style.left = nn6 ?tx+ e.clientX-x+"px"  : tx+event.clientX-x+"px";
    dobj.style.top  = nn6 ?ty+ e.clientY-y+"px" :ty+ event.clientY-y+"px";
    
   // alert("mm: left:"+dobj.style.left+"top:"+dobj.style.top);
   dobj.style.display="none";
   display_popup_menu(this.document,"menu1",dobj.style.left,dobj.style.top);
    return false;
  }
}
///////2
function selectmouse( e ) {
  //alert("selectmouse");
  var fobj       = nn6 ? e.target : event.srcElement;
  var topelement = nn6 ? "HTML" : "BODY";

  while (fobj.tagName != topelement && fobj.className != "dragme") {
    fobj = nn6 ? fobj.parentNode : fobj.parentElement;
  }
  if (fobj.className=="dragme") {
    isdrag = true;
 //   alert("isDrag is true");
    dobj = document.getElementById("menu1");
    tx = parseInt(dobj.style.left+0);
    ty = parseInt(dobj.style.top+0);
    x = nn6 ? e.clientX : event.clientX;
    y = nn6 ? e.clientY : event.clientY;
    document.onmousemove=movemouse;
    return false;
  }
}
///////3
function styledPopupClose() {
  document.getElementById("menu1").style.display = "none";
}
///////4
function display_popup_menu(parent,divId,leftPos,topPos)
{
    var menu_element = document.getElementById(divId);
	//override the 'display:none;' style attribute
    menu_element.style.display = "";
	//get the placement of the element that invoked the menu...
    var placement = findPos(parent);
	//...and put the menu there
	menu_element.style.width="280px";
	menu_element.style.height="200px";
	menu_element.style.position="absolute";
	if(leftPos == ""){
		menu_element.style.left = placement[0]+300 + "px";
	}else{
		menu_element.style.left = leftPos ;
	}
	if(topPos == ""){
		menu_element.style.top = placement[1] + "px";
	}else{
		menu_element.style.top = topPos;
	}
}
///////5
function findPos(obj) {
	var curleft = curtop = 0;
	
	if (obj.offsetParent) {
		curleft = obj.offsetLeft
		curtop = obj.offsetTop
		while (obj = obj.offsetParent) {
			curleft += obj.offsetLeft
			curtop += obj.offsetTop
		}
	}
	return [curleft,curtop];
}
document.onmousedown=selectmouse;
    document.onmouseup=new Function("isdrag=false");
    
    
function changeMultiRows(obj){
	resetMultiRow('1');
	multiRowFunc();
}

function changeMultiRows1(obj){
	changeMultiRows();
}

	
function multiRowFunc(){
	var objTableId = document.getElementById('icd10Id');
	while(objTableId.rows.length>1)
		deleteLastRow('icd10Id',1,0);
	//createArray('icd10Id');
}
	function myFunc(mode){

		if(mode == '1'){
			var hmode = "FINALPDIAGNOSIS"; 
			var url = "PatientFinalDischargeCNT.cnt?hmode="+hmode;
			ajaxFunction(url,"1");
	
		}else if(mode == '2'){
	
			var hmode = "ICDDIAGNOSIS"; 
			var url = "PatientFinalDischargeCNT.cnt?hmode="+hmode;
			ajaxFunction(url,"2");
		}
	}
	
	function hidePrintableSlip()
	{
		//alert("hide");
		document.getElementById("printableSlip").style.display="none"; 
	}
	
	 function showPrintableSlip()
	{
		//alert("show");
		document.getElementById("printableSlip").style.display=""; 
	}
function getPatDischargeParameterDetail()
{   
	
	if(document.getElementsByName("strTmpFlag")[0].value!="1")
	{
		var hmode="getPatDischargeParameter";
		var url = "PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strDeptCode="+document.getElementsByName("curDept_Unt_RomCode")[0].value.split("^")[0];
		ajaxFunction(url,"3");
	}
	else
	{
		showDetails3();
	}
}

function hideShowDeathDetails(these){
	if(these.value=='12'){
		document.getElementsByName("strFOfflineDeath")[0].value="true";
		document.getElementById("deathDetails").style.display="block";
	}else{
		document.getElementsByName("strFOfflineDeath")[0].value="";
		document.getElementById("deathDetails").style.display="none";
	}
}
function shiftMortuaryClk(these){
	if(these.value=='1'){
		document.getElementById("isSiftMortID").style.display="block";
		document.getElementById("isNotSiftMortID").style.display="none";
	}else{
		document.getElementById("isSiftMortID").style.display="none";
		document.getElementById("isNotSiftMortID").style.display="block";
	}
}
function isPregnantClick(these){
	if(these.value=='1')
		document.getElementById("isDeliveryID").style.display="block";
	else
		document.getElementById("isDeliveryID").style.display="none";
}
function hour(obj, eve){

	if(eve.keyCode != 13){
		if (parseInt(obj.value ) > 12 || parseInt(obj.value) < 00 ){
			alert("hours should be in between 00-12");
			obj.value="";
		}
	}
}
function min1(obj, eve){
	//alert("1");
	if(eve.keyCode != 13){
		if (parseInt(obj.value ) > 59 || parseInt(obj.value) < 00 ){
			alert("Mintues should be in between 00-59");
			obj.value="";
		}
	}
}
function sec1(obj, eve){
	if(eve.keyCode != 13){
		if (parseInt(obj.value ) > 59 || parseInt(obj.value) < 00 ){
			alert("Seconds should be in between 00-59");
			obj.value="";
		}
	}
}
function maxLength(these,maxLen){
	var curValue=these.value;
	if(curValue.length>maxLen){
		these.value=curValue.substr(0,maxLen);
	}
}
function startValidation1()
{ 
	var dischargeDate=document.getElementsByName("strDisDate")[0].value;
	if(dischargeDate == "")
	{
		alert("Discharge Date is Mandatory Field");
		return false;
	}
	//alert((document.getElementsByName("strAdmDateAndTime")[0].value).split("1")[0]);
	var admissionDate=(document.getElementsByName("strAdmDateAndTime")[0].value).split("/")[0];
	/*var admissionTime=(document.getElementsByName("strAdmDateAndTime")[0].value).split("/")[1];
	var disHr=document.forms[0].strAbsHour.value;
	var disMin=document.forms[0].strAbsMin.value;
	var disSec=document.forms[0].strAbsSec.value;
	if(document.forms[0].strAbsAmPm.value == 'PM')
		{
			disHr=parseInt(disHr) + 12;
		}
	*/
	//var strDisDateTime=new Date(dischargeDate.split("-")[1]+" "+dischargeDate.split("-")[0]+", "+dischargeDate.split("-")[2]+" "+disHr+":"+disMin+":"+disSec);
	var strDisDateTime=new Date(dischargeDate.split("-")[1]+" "+dischargeDate.split("-")[0]+", "+dischargeDate.split("-")[2]);
	//var strAdmDateTime=new Date(admissionDate.split("-")[1]+" "+admissionDate.split("-")[0]+", "+admissionDate.split("-")[2]+" "+admissionTime);
	var strAdmDateTime=new Date(admissionDate.split("-")[1]+" "+admissionDate.split("-")[0]+", "+admissionDate.split("-")[2]);
	
	if(strDisDateTime < strAdmDateTime)
		{
		alert("Invalid Discharge Date");
			return false;
		}
	
	if(!validateThroughRegExp(document.forms[0].strCrNo,1))
			return;
	
	if(document.forms[0].strDischargeType.value==2)//---Offline
	{
		//alert("1");
		var diag = document.getElementsByName("strProvisionDiagnosis");
		var hiddendiag = document.getElementsByName("strICD10CodeHidden");
		var strIcdCode = document.getElementsByName("strIcdCode");
		for(var nTmpI =0 ; nTmpI<diag.length-1; nTmpI++)
		{
			if(diag[nTmpI].value!=hiddendiag[nTmpI].value)
			{
				alert("Please select proper ICD10 Code");
				return;
			}
			if(document.getElementsByName("strDiagTypeCode")[nTmpI].value=='0')
			{
				alert("Please Select Diagnostic Type");
				document.getElementsByName("strDiagTypeCode")[nTmpI].focus();
				return;
			}
		}
		for(var nTmpI =0 ; nTmpI<diag.length-1; nTmpI++)
		{
			if(diag[nTmpI].value.length==0)
			{
				alert("Please Enter ICD10 Diagnosis Code");
				diag[nTmpI].focus();
				return;
			}
		}
		for(var nTmpI =0 ; nTmpI<strIcdCode.length-1; nTmpI++)
		{
			if(strIcdCode[nTmpI].value.length==0)
			{
				alert("Please Enter ICD10 Diagnosis");
				strIcdCode[nTmpI].focus();
				return;
			}
		}
		//alert("2");
		var hisValidator = new HISValidator(document.forms[0].name);
		hisValidator.clearAllValidations();
		// Discharge Type Wise Validation 2-LAMA, 3-Absconded
		var strLAMA=true;
		var strAbsconded=true;
		var strTransfer=true;
		var strDeath=true;
		if(document.forms[0].strDischargeTypeLAMA.value==0)
		   strLAMA=false;
		if(document.forms[0].strDischargeTypeAbsconded.value==0)
		   strAbsconded=false;
		if(document.forms[0].strDischargeTypeTransfer.value==0)
		   strTransfer=false;
		if(document.forms[0].strDischargeTypeDeath.value==0)
		   strDeath=false;
		//alert("3");
		/*if(document.forms[0].strTransferUnit.value==(strLAMA?document.forms[0].strDischargeTypeLAMA.value : "2") || document.forms[0].strTransferUnit.value==(strAbsconded?document.forms[0].strDischargeTypeAbsconded.value : "3"))
		{
			if(document.getElementsByName("strRsn")[0].value=="")
			{
				hisValidator.addValidation("strRsn", "req", "Remarks is Mandatory Field." );
				document.getElementsByName("strDeathFlag")[0].value="0";
			}
		}*/
		
		if(document.forms[0].strTransferUnit.value==(strLAMA?document.forms[0].strDischargeTypeLAMA.value : "2") )
		{
			if(document.getElementsByName("strRsn")[0].value=="")
			{
				hisValidator.addValidation("strRsn", "req", "Remarks is Mandatory Field." );
				document.getElementsByName("strDeathFlag")[0].value="0";
			}
		}	
	
		else if(document.getElementsByName("strTransferUnit")[0].value ==(strAbsconded?document.forms[0].strDischargeTypeAbsconded.value : "12"))
		{
			
			if(document.getElementsByName("strAbscondedDate")[0].value == "")
			{
				alert("Please Enter Absconded Date");
				return false;
			}
			if(document.getElementsByName("strLastSeenDate")[0].value == "")
			{
				alert("Please Enter Last Seen Date");
				return false;
			}
			if(document.getElementsByName("strAbscondedHour")[0].value == "")
			{
				alert("Please Enter Absconded Hour");
				return false;
			}
			if(document.getElementsByName("strAbscondedMin")[0].value == "")
			{
				alert("Please Enter Absconded Minutes");
				return false;
			}
			
			/*if(document.getElementsByName("strRmk")[0].value == "0")
			{
				alert("Please Select Dischrage Advised By");
				return false;
			}*/
			
			if(document.getElementsByName("strRsn")[0].value == "")
			{
			    alert("Please Enter Remarks");
			    return false;
			}
			var obj = compareDate(document.getElementsByName("strAbscondedDate")[0].value , document.getElementsByName("strDisDate")[0].value);
			  
		 	  
			 if(obj.mode == '2'){
			  	
				 alert("Absconded Date cannot be greater than Discharge Date");
				    return false;
			  	
			  }
			
		}
		else if(document.forms[0].strTransferUnit.value==(strTransfer?document.forms[0].strDischargeTypeTransfer.value : "8"))	// Transfer-8
		{
			hisValidator.addValidation("strTransferedSummary", "req", "Transfered Summary is Mandatory Field." );
			hisValidator.addValidation("strTransferTo", "dontselect=0", "Transfer To is Mandatory Field." );
			document.getElementsByName("strDeathFlag")[0].value="0";
		}
		else if(document.forms[0].strTransferUnit.value==(strDeath?document.forms[0].strDischargeTypeDeath.value : "13"))	// Death 13
		{
			
			if(document.getElementsByName("strDeathDetailsRequired")[0].value == 1)
			{
			hisValidator.addValidation("strDeathDate", "req", "Date of Death is Mandatory Field." );
			hisValidator.addValidation("strDeathHour", "req", "Time of Death is Mandatory Field." );
			hisValidator.addValidation("strDeathMin", "req", "Time of Death is Mandatory Field." );
			hisValidator.addValidation("strDeathCauseID", "dontselect=0", "Immediate Cause of Death is Mandatory Field." );
			hisValidator.addValidation("strVerifyDate", "req", "Verify Date is Mandatory Field." );
			hisValidator.addValidation("strVerifyHour", "req", "Verify Time is Mandatory Field." );
			hisValidator.addValidation("strVerifyMin", "req", "Verify Time is Mandatory Field." );
			if(document.getElementsByName("strIsSiftToMortuary")[1].checked){
				hisValidator.addValidation("strHandoverTo", "req", "Body Handover To is Mandatory Field." );
				hisValidator.addValidation("strHandoverDate", "req", "Handover Date is Mandatory Field." );
				hisValidator.addValidation("strHandoverHour", "req", "Handover Time is Mandatory Field." );
				hisValidator.addValidation("strHandoverMin", "req", "Handover Time is Mandatory Field." );
			}
			else
			{
				hisValidator.addValidation("strShiftDate", "req", "Shift Date is Mandatory Field." );
				hisValidator.addValidation("strShiftHour", "req", "Shift Time is Mandatory Field." );
				hisValidator.addValidation("strShiftMin", "req", "Shift Time is Mandatory Field." );
			}
			}
			hisValidator.addValidation("strRmk", "dontselect=0", "Discharge Advised By is Mandatory Field." );
			hisValidator.addValidation("strRsn", "req", "Remarks is Mandatory Field." );
			document.getElementsByName("strFOfflineDeath")[0].value=true;
			document.getElementsByName("strDeathFlag")[0].value="1";
			
			
		}
		else
		{
			hisValidator.addValidation("strRmk", "dontselect=0", "Discharge Advised By is Mandatory Field." );
			hisValidator.addValidation("strRsn", "req", "Remarks is Mandatory Field." );
		}
		//alert("4");
		if(!hisValidator.validate())
			return; 
	}
	//alert("5");
	if(!confirm("Do You Want to Save the Data."))
		return;
	if(document.forms[0].strCrNo.value.length==document.forms[0].strCrNo.maxLength)
	{
		//alert("6");
		document.forms[0].hmode.value="SAVE";
		document.forms[0].submit();
		
	}
	else
	{
		alert("Enter Valid CR No.");
	}
}

function combineDateAndTime(param){
	var strDateField = document.getElementsByName("str"+param+"Date")[0].value;
	var strHourField = document.getElementsByName("str"+param+"Hour")[0].value;
	var strMinField = document.getElementsByName("str"+param+"Min")[0].value;
	var strSecField = document.getElementsByName("str"+param+"Sec")[0].value;
	var strAmPmField = strAmPmField = document.getElementsByName("str"+param+"AmPm")[0].value=="1"?"AM":"PM";
	var strCombinedValue = strDateField +" " + strHourField+":"+strMinField+":"+strSecField+" "+strAmPmField;
	return strCombinedValue;
}

function copyDateAndTimeInHFields(){
	var strDeath=true;
	if(document.forms[0].strDischargeTypeDeath.value==0)
		   strDeath=false;
	if(document.getElementsByName("strTransferUnit")[0].value==(strDeath?document.forms[0].strDischargeTypeDeath.value : "13")){
		if(document.getElementsByName("strFOfflineDeath")[0].value=="true"){
			document.getElementsByName("strDeathDateAndTime")[0].value=combineDateAndTime("Death");
			if(document.getElementsByName("strOnsetDeathDate")[0].value=='')
			document.getElementsByName("strOnsetDeathDateAndTime")[0].value=combineDateAndTime("Death");
			else
			document.getElementsByName("strOnsetDeathDateAndTime")[0].value=combineDateAndTime("OnsetDeath");
			if(document.getElementsByName("strVerifyDate")[0].value=='')
			document.getElementsByName("strVerifyDateAndTime")[0].value=combineDateAndTime("Death");
			else
			document.getElementsByName("strVerifyDateAndTime")[0].value=combineDateAndTime("Verify");
		}
		if(document.getElementsByName("strIsSiftToMortuary")[1].checked){
			document.getElementsByName("strShiftDateAndTime")[0].value = "";
			document.getElementsByName("strHandoverDateAndTime")[0].value=combineDateAndTime("Handover");
		}else{
			document.getElementsByName("strHandoverDateAndTime")[0].value = "";
			document.getElementsByName("strShiftDateAndTime")[0].value=combineDateAndTime("Shift");
		}
	}
}
function hideDetails3()
{
 document.getElementById("minusonLineId3").style.display="none";
 document.getElementById("plusonLineId3").style.display="block";
 document.getElementById("dischargeParam").style.display="none";
 }
function showDetails3(){
	document.getElementById("plusonLineId3").style.display="none";
	document.getElementById("minusonLineId3").style.display="block";
	if(document.getElementById("dischargeParam").innerHTML!=""){
		document.getElementById("dischargeParam").style.display="block";
	}
}
 function hideDetails4()
{
 document.getElementById("finalDetail").style.display="none";
 document.getElementById("plusImg").style.display="block";
 document.getElementById("minusImg").style.display="none";
 }
function showDetails4()
{
 document.getElementById("finalDetail").style.display="block";
 document.getElementById("plusImg").style.display="none";
 document.getElementById("minusImg").style.display="block";
 }
function cancelPage()
{
 document.forms[0].hmode.value="INITIALPAGE";
 document.forms[0].strCrNo.value="";
 document.forms[0].submit();
}
	
function clearPage()
{
 document.forms[0].hmode.value="CLEAR";
 document.forms[0].strCrNo.value="";
 document.forms[0].strSaveStatus.value="";
 document.forms[0].submit();
}
function hlpOnLoadDisParam()
{     
	if(typeof(document.forms[0].strConsultantId)=="undefined")
	document.forms[0].strRmk.value=0;
	else
	document.forms[0].strRmk.value=document.forms[0].strConsultantId.value;
    if(document.forms[0].strCrNo.value>0)
    {
    	if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No. Entered
		{
               document.getElementById("disBnR").style.display="block";
               document.getElementById("patTldglbdiv").style.display="block";
               //document.getElementById("admDtlTldglbdiv").style.display="block";
               //document.getElementById("patDtlTld").style.display="none";
               document.getElementById("admDtlTld").style.display="block";
                document.getElementById("patDtlHeader").style.display="block";
               document.getElementById("transChng").style.display="";
               document.getElementById("discDtlHeader").style.display="block";
               document.getElementById("finalDiag").style.display="block";
               document.getElementById("finalDischParam").style.display="block";
               document.getElementById("dischargeParam").style.display="block";
               //document.getElementById("mandCRId").style.display="none";
               //document.getElementById("patDemDtlId").style.display="block";
               //document.getElementById("plusonLineId").style.display="";
               //document.getElementById("minusonLineId").style.display="none";
               //alert("paramReq->"+document.forms[0].strDischrg_Param_Req.value);
               if(document.forms[0].strDischrg_Param_Req.value!="1")
               {
                 var o1=document.getElementById("finalDischParam");
                 var o2=document.getElementById("dischargeParam");
                 o1.style.display="none";
                 o2.style.display="none";
               }
               if(document.forms[0].strDischargeDiagnosisRequired.value!="1")
               {
                 var o3=document.getElementById("finalDetail");
      			 var o4=document.getElementById("finalDiag");	
                 o3.style.display="none";
                 o4.style.display="none";                 
               }
               if(document.forms[0].strDischargeAdviceFieldRequired.value!="1")
               {
                 var o5=document.getElementById("dischargeAdvice");      			 	
                 o5.style.display="none";
               }
               document.getElementById("goBox").style.display = 'none';
               document.getElementById("savebutton").style.display = "block";
		   }
		   else//only predefined Digits in CR Field
		   {
			       //SetCursorToTextEnd('strCrNoId');
			   		SetSelectedCrNo();
			       //document.forms[0].strErrMsgString.value=document.forms[0].strGlbErrMsgTLD.value;
			       document.getElementById("patTldglbdiv").style.display="block";
			       //document.getElementById("admDtlTldglbdiv").style.display="none";
	               //document.getElementById("patDtlTld").style.display="none";
	               document.getElementById("transChng").style.display="none";
	               document.getElementById("discDtlHeader").style.display="none";
	               document.getElementById("admDtlTld").style.display="block";
	               document.getElementById("patDtlHeader").style.display="none";
	               document.getElementById("disBnR").style.display="none";
	               document.getElementById("finalDiag").style.display="none";
	               document.getElementById("finalDischParam").style.display="none";
	               //document.getElementsByName("strErrMsgString")[0].value="Invalid CR No./Data Not Found";
	               //document.getElementById("errMsg").style.display="block";
	               //document.getElementById("mandCRId").style.display="block";
	               //document.getElementById("patDemDtlId").style.display="none";
	               document.getElementById("finalDetail").style.display="none";
	               document.getElementById("goBox").style.display = 'block';
	               document.getElementById("savebutton").style.display = "none";
		   }
    }
    
}
function tmp()
{
            var disMode=document.forms[0].strDischrg_Mode.value;
        //    if(disMode=="2")  //offline
            {   
               var curDept_Unt_RomCode=document.forms[0].curDept_Unt_RomCode.value;
             //  alert("isMLC->"+document.forms[0].strIsMLC.value);
               var myArray=new Array();
               myArray=curDept_Unt_RomCode.split("^");
               var isMLC=document.forms[0].strIsMLC.value;
               var disMLCdtl=document.forms[0].strDisplay_MLC_Dtls.value;
               var disOPDVisit=document.forms[0].strDisplay_OPD_Visit_Dtls.value;
               var deptunitCod=myArray[1];
               //alert("paramReq->"+document.forms[0].strDischrg_Param_Req.value);
               if(document.forms[0].strDischrg_Param_Req.value!="1")
               {
                 var o1=document.getElementById("finalDischParam");
                 var o2=document.getElementById("dischargeParam");
                 o1.style.display="none";
                 o2.style.display="none";
               }
               var strVal=disMode+"^"+isMLC+"^"+disMLCdtl+"^"+disOPDVisit+"^"+deptunitCod;
               
               var mode="transOf";
		       var url="PatientFinalDischargeCNT.cnt?hmode="+mode+"&modName="+strVal;
		      // alert("strVal->"+url);
		       ajaxFunction(url,"9");
		    } 
}
function getDisBy()
{
            var disMode=document.forms[0].strDischrg_Mode.value;
        	if(disMode=="2")  //offline
            {   
               document.forms[0].strCurrentDeptUnitRoom.value=document.forms[0].curDept_Unt_RomCode.value;
               var mode="DISCHARGEADVISEDBY";
               var temp=document.forms[0].strCurrentDeptUnitRoom.value.split("^");
               
               		var temp1=temp[0];
               		for(var i=0;i<temp.length-1;i++)
               			temp1+="@"+temp[i+1];

	       var url="PatientFinalDischargeBSCNT.cnt?hmode="+mode+"&curDept="+temp1;
		    ajaxFunction(url,"10");
		    } 
}

function butdis1(){
	if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No. Entered
		{
		document.forms[0].strCrNo.readOnly=true;
	}
 document.forms[0].strErrMsgString.value="";
 document.forms[0].strNormalMsgString.value="";
}
function isEnter(obj)
{


	var flag=validateData(obj,5);
	
	if(flag){
			if(obj.keyCode==13)
			{
				var flag1=goFunc();
				if(flag1)
				{
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
				}
				else
				{
					return false;
				}
				
			}
		}
		else
		{
			return false;
		}
}
  
  
function goFunc()
{
		var hisValidator = new HISValidator("patientDischargeBean");
		if(!validateThroughRegExp(document.forms[0].strCrNo,1))
		{
			return;
		}
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	    hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
        var retVal = hisValidator.validate(); 
	   
	    if(retVal){
	    	// document.forms[0].strTempVal.value=document.forms[0].strCrNo.value;
				// return true;
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
		}else{
		    return false;
		}
}


function hideDetails()
{
 document.getElementById("minusonLineId").style.display="none";
 document.getElementById("plusonLineId").style.display="block";
 document.getElementById("patDtlTld").style.display="none";
}
function showDetails()
{
 document.getElementById("plusonLineId").style.display="none";
 document.getElementById("minusonLineId").style.display="block";
 document.getElementById("patDtlTld").style.display="block";
}
function hideDetails1()
{
 document.getElementById("minusonLineId1").style.display="none";
 document.getElementById("plusonLineId1").style.display="block";
 document.getElementById("admDtlTld").style.display="none";
}
function showDetails1()
{
 document.getElementById("plusonLineId1").style.display="none";
 document.getElementById("minusonLineId1").style.display="block";
 document.getElementById("admDtlTld").style.display="block";
 document.getElementById("patDtlHeader").style.display="block";
}
function hideDetails2()
{
 document.getElementById("minusonLineId2").style.display="none";
 document.getElementById("plusonLineId2").style.display="block";
 document.getElementById("id2").style.display="none";
 document.getElementById("disBnR").style.display="none";
}
function showDetails2()
{
 document.getElementById("plusonLineId2").style.display="none";
 document.getElementById("minusonLineId2").style.display="block";
 document.getElementById("id2").style.display="block";
 document.getElementById("disBnR").style.display="block";
}

function getICDDetails(){
	var hmode="ICDDIAGNOSIS";
	var url = "PatientFinalDischargeCNT.cnt?hmode="+hmode;
	if(!(document.forms[0].strSaveStatus.value == "1" || document.forms[0].strSaveStatus.value == "2"))
		ajaxFunction(url,"4");
}
function printReport(){
	
	if(document.forms[0].strSaveStatus.value == "1")
	{
		//alert(document.forms[0].strProfileId.value);
		if(document.forms[0].strSaveStatus.value == "1")
		{
			document.getElementById("printableSlip").style.display="";
			
		}
		else{
			document.getElementById("printableSlip").style.display="none";
			
		}
	}
	if(document.forms[0].strSaveStatus.value == "2")
	{
		if(document.forms[0].strProfileId.value == null || document.forms[0].strProfileId.value == "")
		{
			alert("Discharge Profile is not generated");
		}
		else
		{
				var url='/HBIMS/opd/opdPatientProfile.cnt?hmode=VIEWPRINTPROFILE&profileId='+document.forms[0].strProfileId.value;	
				child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=550,width=450,left=400,top=100');  
				child.moveTo(900,250);
				child.focus();			
		}
	}
	if(document.forms[0].strSaveStatus.value == "3")
	{
		document.forms[0].hmode.value = "ABSCONDEDREPORT";
		document.forms[0].strSaveStatus.value = "0";
		document.getElementById("normalMsg").innerHTML="Wait...Generating Absconded Discharge Report";
		document.forms[0].submit();	
	}
		
	

	/* if(document.forms[0].strDischargeSummaryPrintRequired.value=="1")
        {
        	if(document.forms[0].strSaveStatus.value == "1")
        	{
				document.forms[0].hmode.value = "SHOWRPT";
				document.forms[0].strSaveStatus.value = "0";
				document.getElementById("normalMsg").innerHTML="Wait...Generating Discharge Summary Report";
				document.forms[0].submit();			
			}
		else if(document.forms[0].strSaveStatus.value == "2")
			{		
				document.forms[0].hmode.value = "SHOWRPT1";
				document.forms[0].strSaveStatus.value = "0";
				document.getElementById("normalMsg").innerHTML="Wait...Generating Absconded Discharge Report";
				document.forms[0].submit();		
			}
        } */
	}
	
		
function onLoadHideMLC()
{
	if("${patientDischargeBean.strHlpMLC}"=="")
		document.getElementById("infoMLC").style.display="none";
	else
		document.getElementById("infoMLC").style.display="block";
} 




var keyupTimer;
var searchMode1;
var parentObj1
var eve1;
var vindex1;
function getIcdCodeValues1(searchMode , parentObj, eve , vindex)
{
   clearTimeout(keyupTimer);
   searchMode1=searchMode;
   parentObj1=parentObj;
   eve1=eve;
   vindex1=vindex;   
   // will activate when the user has stopped typing for 1 second
   keyupTimer = setTimeout("getIcdCodeValues2()",500); 
} 
function getIcdCodeValues2()
{
   var temp=vindex1.split("-")[1]-1;
   var tempId="strIcdCode1-"+temp;
   if(vindex1.split("-")[1]-(temp+1)=='0')
   {
   		getIcdCodeValues(searchMode1 , parentObj1, eve1 , vindex1);
   }
   else
   {
	   if(document.getElementById(tempId).value=="")
	   alert("Please Enter Data in Previous Row");
	   else
	   {
	   		getIcdCodeValues(searchMode1 , parentObj1, eve1 , vindex1);
	   }
   }
}
var tempCode = "";
var gblParentObj = "";
var gblIndex = "";
var child = null;
var popIndex = 0;
var gblCntrlObj = null;

function getIcdCodeValues(searchMode , parentObj , eve , vindex)
{
	gblParentObj = parentObj;
	gblIndex = vindex;
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
	{
		if (eve)
			key = eve.which;
	}
	tempCode = key;//single quotes
 		if(tempCode == 222)
 		{
 			parentObj.value = parentObj.value.substring(0,parentObj.value.length-1);
 			return false;
 		}
		var url="";
		var hmode="";
		var searchContent = parentObj.value;
		if(parentObj.value.length>1)
		{
 			searchContent = parentObj.value.substring(0,1);
 		}
		if(searchContent.length == 1)
		{
		 	if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		 	{
				hmode = "ICDDIAGNOSIS"; 
				url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{
				hmode = "HOSITALPDIAGNOSIS";
				url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
			{
				if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
			 	{
					hmode = "ICDDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
				if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
			 	{
					hmode = "HOSITALPDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
			}
			//alert("url :"+url);
			ajaxFunction(url,"20");
		}
		else
		{
				if(document.getElementById("dropdown1").innerHTML.length <=0)
				{
					var input = document.getElementById(parentObj.name+""+gblIndex).value;
					document.getElementById(parentObj.name+""+gblIndex).value = input.substring(0,input.length-1);
 					return false;					
				}
		searchSel(eve,parentObj.name+""+vindex,'1',parentObj);
	}
}


function getIndex(_these){
	var nTmpI;
	var strSelectedObjName=document.getElementsByName(_these.name)
	for(nTmpI=0;nTmpI<strSelectedObjName.length;nTmpI++)
		if(_these==strSelectedObjName[nTmpI])
			break;
	return nTmpI;
}
/*
function copyCode(these){
	document.getElementsByName("strIcdCode")[getIndex(these)].value=these.value;
}*/
/*
var gblParentObj = "";
var tempCode = "";
var gblIndex = "";
	 
function getIcdCodeValues(searchMode , parentObj , eve , vindex)
{
	gblParentObj = parentObj;
	gblIndex = vindex;	
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
	{
		if (eve)
		key = eve.which;
	}		
	tempCode = key;
 	if(tempCode == 222)
 	{
 			parentObj.value = parentObj.value.substring(0,parentObj.value.length-1);
 			return false;
	}
	var searchContent = parentObj.value;
	if(searchContent.length == 1 )
	{
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		{	
				hmode = "ICDDIAGNOSIS"; 
				url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
		}
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		{	
				hmode = "FINALPDIAGNOSIS";
				url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
		}
		if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
		{
				if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
			 	{	
					hmode = "ICDDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
				if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
			 	{	
					hmode = "FINALPDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
		}
		
		
		//var hmode = "ICDDIAGNOSIS"; 
		//var url = "/HBIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
		//alert("url :"+url);
		ajaxFunction(url,"20");
	}
	else
	{
		if(document.getElementById("dropdown1").innerHTML.length <=0)
		{
					var input = document.getElementById(parentObj.name+""+gblIndex).value;
					document.getElementById(parentObj.name+""+gblIndex).value = input.substring(0,input.length-1);
 					return false;
		}
		searchSel(eve,parentObj.name+""+vindex,'1',parentObj);		
	} 		
}*/
function setIcdCodes(userValue , resultValue)
	{
		var resVals = resultValue.split('^');		
		document.getElementById("strProvisionDiagnosis"+userValue).value = resVals[0];
		document.getElementById("strIcdCode"+userValue).value = resVals[1];
		document.getElementById("strICD10CodeHidden"+userValue).value = resVals[0];
		//ICD
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		{
			document.getElementById("strDiagCodeType"+userValue).value = "0";
		}
		//Hospital
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		{
			document.getElementById("strDiagCodeType"+userValue).value = "1";
		}
		//Both
		if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
		{
			//ICD
			if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
		 	{
		 		document.getElementById("strDiagCodeType"+userValue).value = "0";	
			}
			//Hospital
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{
		 		document.getElementById("strDiagCodeType"+userValue).value = "1";
			}
		}
		//defined in multirow jsp
		document.getElementById("tariffFullNameDiv").style.display="none";
		
	}
	
function consnetDetails(obj)
{
	 if(obj.checked)
	 {
	 	document.getElementById("consentDiv").style.display="";
	 	document.getElementById("consentSignDiv1").style.display="";
	 	document.getElementById("consentSignDiv2").style.display="";
	 }
	 else
	 {
	 	document.getElementById("consentDiv").style.display="none";
	 	document.getElementById("consentSignDiv1").style.display="none";
	 	document.getElementById("consentSignDiv2").style.display="none";
	 }
}
	
function showConsentRelativeDiv(obj)
{
	 if(obj.value==1)//Patient
	 {
	 	document.getElementById("consentSignDiv1").style.display="none";
	 	document.getElementById("consentSignDiv2").style.display="none";
	 }
	 else
	 {
	 	document.getElementById("consentSignDiv1").style.display="";
	 	document.getElementById("consentSignDiv2").style.display="";
	 }
}
function openLAMAConsent(consentTemplateId)
{
	var crNo=document.forms[0].strCrNo.value;
	var str=document.forms[0].strHiddenPatDtl.value;
	var patName=str.split('^')[0];
	var patAgeGender=str.split('^')[3];
	var patAdd=str.split('^')[4];
	var patFatherSpouse=str.split('^')[1];
	var url='PatientFinalDischargeCNT.cnt?hmode=LAMACONSENT&consentTemplateId='+consentTemplateId+'&crNo='+crNo+'&patName='+patName+'&patAgeGender='+patAgeGender+'&patAdd'+patAdd+'&patFatherSpouse='+patFatherSpouse;
	child = window.open(createFHashAjaxQuery(url,'popupWindow','status=yes,scrollbars=yes,height=600,width=500,left=400,top=100'));    
	child.moveTo(900,250);
	child.focus();
	
}


function openPrintPopUp()
{

	if(document.forms[0].strSaveStatus.value=='1'  )
	{
		
		printSlip();
		//document.forms[0].hmode.value="PRINTSLIP";
		//alert(document.forms[0].strPatientCrNo.value);
		//document.forms[0].strCrNo.value=document.forms[0].strPatientCrNo.value;

		window.print();
		//document.forms[0].submit();
		//alert(document.forms[0].strCrNo.value);
		//alert(document.forms[0].hmode.value);
		
			//window.close();
		
	
	 }
	
	//alert(window.matchMedia('print'));
	 document.forms[0].strSaveStatus.value=0;
	 //window.onbeforeprint = beforePrint;
	 //window.onafterprint = hidePrintableSlip();
	 //setTimeout("hidePrintableSlip()",2000);
	 //document.getElementById("printableSlip").style.display="none"; 
}



function printSlip()
{

	get_object("divBarCodeControlCrNo").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlCrNo").innerHTML, 0);
	get_object("divBarCodeControlAdmNo").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlAdmNo").innerHTML, 0);
	//window.print();
	//var t=setTimeout("printSlip1()",2000);
}
function hidePrintableSlip()
{
	document.getElementById("printableSlip").style.display="none"; 
}
function showPrintableSlip()
{
	document.getElementById("printableSlip").style.display=""; 
}

function checkMsg() {
	var err=document.getElementById("errMsg");
	var nor=document.getElementById("normalMsg");
	console.log(nor.innerHTML);
	if (err.innerHTML != "") {
		
		err.innerHTML="<i class='fas fa-exclamation-circle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		err.style.display = "";
		
	}
	if (nor.innerHTML != "") {
		//console.log(nor.innerHTML);
		nor.innerHTML="<i class='far fa-thumbs-up'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		nor.style.display = "";

	}
	

	
}

function modalSlipPrint()
{
	printElement(document.getElementById("printableSlip"));

}
function printElement(elem) {
    var domClone = elem.cloneNode(true);
    
    var $printSection = document.getElementById("printSection");
    
    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }
    
    $printSection.innerHTML = "";
    $printSection.appendChild(domClone);
    window.print();
}