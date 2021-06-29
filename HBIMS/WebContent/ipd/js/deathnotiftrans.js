
	function validate1()
	{
	 	var flag=document.forms[0].strFGnctdFlag.value;
	
		
		var hisValidator = new HISValidator("deathNotificationTransBean");  
		
		hisValidator.addValidation("strDeathDate", "req", "Date Of death is a Mandatory Field" );
		if(flag!=1){
			hisValidator.addValidation("strOnsetDeathDate", "req", "Onset Date Of death is a Mandatory Field" );
			hisValidator.addValidation("strOnsetDeathDate","dtgtet="+document.forms[0].strDeathDate.value,"Please Select Onset Death Date Greater Than Or Equal To Death Date");
		}
		hisValidator.addValidation("strDeathHour","req","Hour in Death is a Mandatory Field" );
		hisValidator.addValidation("strDeathHour","minlen=2","Hour in Death should be in hh format");
		hisValidator.addValidation("strDeathMin","req","Minute in Death is a Mandatory Field" );
		hisValidator.addValidation("strDeathMin","minlen=2","Minute in Death should be in mm format");
		hisValidator.addValidation("strDeathSec","req","Second in Death  is a Mandatory Field" );
		hisValidator.addValidation("strDeathSec","minlen=2","Second in Death should be in ss format");
		
		if(flag!=1){
			hisValidator.addValidation("strOnsetDeathHour","req","Hour in onset Death is a Mandatory Field" );
			hisValidator.addValidation("strOnsetDeathHour","minlen=2","Hour in onset Death should be in hh format");
			hisValidator.addValidation("strOnsetDeathMin","req","Minute in onset Death is a Mandatory Field" );
			hisValidator.addValidation("strOnsetDeathMin","minlen=2","Minute onset in Death should be in mm format");
			hisValidator.addValidation("strOnsetDeathSec","req","Second in onset Death  is a Mandatory Field" );
			hisValidator.addValidation("strOnsetDeathSec","minlen=2","Second onset in Death should be in ss format");
		}
		
		hisValidator.addValidation("strCauseDeath","dontselect=0","Please select a value from Immediate Cause of Death Combo" );
		
		if(flag!=1){
			hisValidator.addValidation("strDeathManner","dontselect=0","Please select a value from Death Manner Combo" );
		}
		
		hisValidator.addValidation("strDescpCauseDeath","maxlen=500","Immediate Cause of Death should have less than or equal to 500 characters");           
		hisValidator.addValidation("strAntecedentCauseDeath","maxlen=500","Antecedent Cause of Death should have less than or equal to 500 characters");
		hisValidator.addValidation("strInjuryDetail","maxlen=200","Injury Detail should have less than or equal to 500 characters");
		hisValidator.addValidation("strConsultant","dontselect=0","Please select a value from Verified by Combo" );
		hisValidator.addValidation("strVerifyDate", "req", "Date Of Verification is a Mandatory Field" );
		hisValidator.addValidation("strVerifyDate","dtgtet="+document.forms[0].strDeathDate.value,"Please Select Verify Date Greater Than Or Equal To Death Date");
		hisValidator.addValidation("strVerifyHour","req","Hour in Verification is a Mandatory Field" );
		hisValidator.addValidation("strVerifyHour","minlen=2","Hour in Verification should be in hh format");
		hisValidator.addValidation("strVerifyMin","req","Minute in Verfication is a Mandatory Field" );
		hisValidator.addValidation("strVerifyMin","minlen=2","Minute in Verification should be in mm format");
		hisValidator.addValidation("strVerifySec","req","Second in Verification  is a Mandatory Field" );
		hisValidator.addValidation("strVerifySec","minlen=2","Second in Verification should be in ss format");
		
	if(document.forms[0].strIsSiftToMortuary[0].checked == true)
			{
			
					
		hisValidator.addValidation("strShiftDate", "req", "Shift Date is a Mandatory Field" );
		hisValidator.addValidation("strShiftDate","dtgtet="+document.forms[0].strDeathDate.value,"Please Select Shift Date Greater Than Or Equal To Death Date");
		hisValidator.addValidation("strShiftHour","req","Shift Hour is a Mandatory Field");
		hisValidator.addValidation("strShiftHour","minlen=2","Shift Hour should be in hh format");
		hisValidator.addValidation("strShiftMin","req","Shift Minute is a Mandatory Field");
		hisValidator.addValidation("strShiftMin","minlen=2","Shift Minute should be in mm format");
		hisValidator.addValidation("strShiftSec","req","Shift Second is a Mandatory Field");
		hisValidator.addValidation("strShiftSec","minlen=2","Shift Second should be in ss format");
		
		}else
		{
		
		hisValidator.addValidation("strHandOverTo", "req", "Hand Over To is a Mandatory Field" );
		hisValidator.addValidation("strHandOverDate", "req", "Hand Over Date is a Mandatory Field" );
		hisValidator.addValidation("strHandOverDate","dtgtet="+document.forms[0].strDeathDate.value,"Please Select Hand Over Date Greater Than Or Equal To Death Date");
		hisValidator.addValidation("strHandOverHour","req","Hand Over Hour is a Mandatory Field");
		hisValidator.addValidation("strHandOverHour","minlen=2","Hand Over Hour should be in hh format");
		hisValidator.addValidation("strHandOverMin","req","Hand Over Minute is a Mandatory Field");
		hisValidator.addValidation("strHandOverMin","minlen=2","Hand Over Minute should be in mm format");
		hisValidator.addValidation("strHandOverSec","req","Hand Over Second is a Mandatory Field");
		hisValidator.addValidation("strHandOverSec","minlen=2","Hand Over Second should be in ss format");
		}
		
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
		if(retVal)
		{
			var date=document.forms[0].strDeathDate.value;
			var hr=document.forms[0].strDeathHour.value;
			var min=document.forms[0].strDeathMin.value;
			var sec=document.forms[0].strDeathSec.value;
			var time=hr+":"+min+":"+sec+" "+document.forms[0].strDeathAmPm[document.forms[0].strDeathAmPm.selectedIndex].text;
			var datetime=date+" "+time;
			document.forms[0].strDeathDateTime.value=datetime;
			
			if(flag!=1){
				date=document.forms[0].strOnsetDeathDate.value;
				hr=document.forms[0].strOnsetDeathHour.value;
				min=document.forms[0].strOnsetDeathMin.value;
				sec=document.forms[0].strOnsetDeathSec.value;
				time=hr+":"+min+":"+sec+" "+document.forms[0].strDeathAmPm[document.forms[0].strDeathAmPm.selectedIndex].text;
				datetime=date+" "+time;
				document.forms[0].strOnsetDeathDateTime.value=datetime;
			}
			
			date=document.forms[0].strShiftDate.value;
			hr=document.forms[0].strShiftHour.value;
			min=document.forms[0].strShiftMin.value;
			sec=document.forms[0].strShiftSec.value;
			time=hr+":"+min+":"+sec+" "+document.forms[0].strShiftAmPm[document.forms[0].strShiftAmPm.selectedIndex].text;
			datetime=date+" "+time;
			document.forms[0].strShiftDateTime.value=datetime;
			date=document.forms[0].strVerifyDate.value;
			hr=document.forms[0].strVerifyHour.value;
			min=document.forms[0].strVerifyMin.value;
			sec=document.forms[0].strVerifySec.value;
			time=hr+":"+min+":"+sec+" "+document.forms[0].strVerifyAmPm[document.forms[0].strVerifyAmPm.selectedIndex].text;
			datetime=date+" "+time;
			document.forms[0].strVerifyDateTime.value=datetime;
			date=document.forms[0].strHandOverDate.value;
			hr=document.forms[0].strHandOverHour.value;
			min=document.forms[0].strHandOverMin.value;
			sec=document.forms[0].strHandOverSec.value;
			time=hr+":"+min+":"+sec+" "+document.forms[0].strHandOverAmPm[document.forms[0].strHandOverAmPm.selectedIndex].text;
			datetime=date+" "+time;
			document.forms[0].strHandOverDateTime.value=datetime;
			
			if(document.forms[0].strIsSiftToMortuary.value=="0")
			{
				
				document.forms[0].strShiftDateTime.value="";
				
			}
			if(document.forms[0].strIsPregnant.value=="0")
			{
				
				document.forms[0].strIsDelivery.value="0";
			}
			
			
			
				document.forms[0].hmode.value="INSERT";
				document.forms[0].submit();
			
		}
		else
		{
			return false;
		}
	}
	function menuPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();
	}
	
	
	function goFuncOnEnter(e)
	{
		if(e.keyCode == 13){
		 goFunc();
		 }else{
		 	return false;
		 }
	}
	
	function goFunc()
	{
		var hisValidator = new HISValidator("deathNotificationTransBean");  
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		//hisValidator.addValidation("strCrNo", "minlen=12", "Cr No. must be 12 Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal){
				document.forms[0].hmode.value="GO";
				document.forms[0].submit();
		}else{
		
		return false;
		}
	}
	function view()
	{
		if(document.forms[0].strCrNo.value!="" )
		{
			document.forms[0].strIsPregnant.value="0";
			document.forms[0].strIsSiftToMortuary.value="0";
			var oBJE=document.getElementById("isHandOverID");
			oBJE.style.display="block";
			var oBJE=document.getElementById("patientDemographicId");
			oBJE.style.display="block";
			var oBJE=document.getElementById("patientAdmissionId");
			oBJE.style.display="block";
			var oBJE=document.getElementById("deathNotiFicationId1");
			oBJE.style.display="block";
			var oBJE=document.getElementById("deathNotiFicationId2");
			oBJE.style.display="block";
			document.getElementById("mandCRId").style.display="none";
		}
	}
	function hour(obj, eve){
 	 	if(eve.keyCode != 13){
 		if (parseInt(obj.value ) > 12 || parseInt(obj.value) < 00 ){
     		alert("hours should be in between 00-12");
     		obj.value="";
 	 		}
 	 		else
 	 		{
 	 		//	focusDeathMin(obj);
 	 		}
 		}
 	}
 	function min(obj, eve){
 	 	if(eve.keyCode != 13)
 	 	{
 			if (parseInt(obj.value ) > 59 || parseInt(obj.value) < 00 )
 			{
     			alert("Mintues should be in between 00-59");
     			obj.value="";
 	 		}
 	 		else
 	 		{
 	 			//focusDeathSec(obj);
 	 		}
 	 	
 		}
 	}
 	function sec(obj, eve){
 	 	if(eve.keyCode != 13){
 			if (parseInt(obj.value ) > 59 || parseInt(obj.value) < 00 ){
     			alert("Seconds should be in between 00-59");
     			obj.value="";
 	 		}
 	 			 	
 		}
 	}
 	function focusDeathMin(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strDeathMin.focus();
 		}
 	}
 	
 	function focusOnsetDeathSec(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strOnsetDeathSec.focus();
 		}
 	}
 	
 	function focusOnsetDeathMin(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strOnsetDeathMin.focus();
 		}
 	}
 	
 	function focusDeathSec(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strDeathSec.focus();
 		}
 	}
 	
 	function verifyTimeMin(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strVerifyMin.focus();
 		}
 	}
 	
 	function verifyTimeSec(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strVerifySec.focus();
 		}
 	}
 	function focusShiftMin(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strShiftMin.focus();
 		}
 	}
 	function focusShiftSec(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strShiftSec.focus();
 		}
 	}
 	function focusHandOverMin(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strHandOverMin.focus();
 		}
 	}
 	function focusHandOverSec(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strHandOverSec.focus();
 		}
 	}
 	
	function focusDeathAmPm(obj)
 	{
 		if(obj.value.length=="2")
 		{
 			document.forms[0].strDeathAmPm[0].select();
 		}
 	} 
 	
 	function isPregnant(){
 	if(document.forms[0].strIsPregnant.value==true)
 	{
 	isPregnantClick();
 		}
 	}
 	
 	function setisPregnantValYes()
 	{
 		document.forms[0].strIsPregnant.value="1";
 		isPregnantClick();
 	}
 	function setisPregnantValNo()
 	{
 		document.forms[0].strIsPregnant.value="0";
 		isPregnantClick();
 	}
 	function isPregnantClick(obj)
 	{
 		
 		if(obj.value=="1")
 		{
 			var obj=document.getElementById("isDeliveryID");
 			obj.style.display="block";
 		}
 		if(obj.value=="0")
 		{
 			var obj=document.getElementById("isDeliveryID");
 			obj.style.display="none";
 		}
 	}
 	
 	function shiftMortuaryClk(obj)
 	{
 		if(obj.value=="1")
 		{
 			var obj=document.getElementById("isSiftMortID");
 			obj.style.display="block";
 			document.getElementById("isHandOverID").style.display="none";
 		}
 		if(obj.value=="0")
 		{
 			var obj=document.getElementById("isHandOverID");
 			obj.style.display="block";
 			document.getElementById("isSiftMortID").style.display="none";
 		}
 	}
 	function clearRecord()
 	{
 			document.forms[0].strCrNo.value="";
 			var oBJE=document.getElementById("patientDemographicId");
			oBJE.style.display="none";
			var oBJE=document.getElementById("patientAdmissionId");
			oBJE.style.display="none";
			var oBJE=document.getElementById("deathNotiFicationId1");
			oBJE.style.display="none";
			var oBJE=document.getElementById("deathNotiFicationId2");
			oBJE.style.display="none";
			document.getElementById("isHandOverID").style.display="none";
			document.getElementById("mandCRId").style.display="block";
			
 	}