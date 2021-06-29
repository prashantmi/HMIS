function onclickImage(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function openDiv(divId)
{
	var divObj=document.getElementById(divId);
	var imgObj=document.getElementById("img"+divId.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
}

function ageOfMarriageSelection()
{
	if(document.getElementsByName("isActualDateOfMarriage")[0].checked)
	{
		document.getElementById("divAgeOfMarriage").style.display="block";
		document.getElementById("divDateOfMarriage").style.display="none";
		document.getElementsByName("patDateOfMarriage")[0].value="";
	}
	else
	{
		document.getElementsByName("patAgeOfMarriage")[0].value="";
		document.getElementById("divAgeOfMarriage").style.display="none";
		document.getElementById("divDateOfMarriage").style.display="block";
	}
}

function ageOfMenarcheSelection()
{
	if(document.getElementsByName("isActualAgeOfMenarche")[0].checked)
	{
		document.getElementById("divAgeOfMenarche").style.display="block";
		document.getElementById("divDateOfMenarche").style.display="none";
		//document.getElementsByName("patDateOfMenarche")[0].value="";
	}
	else
	{
		document.getElementsByName("patAgeOfMenarche")[0].value="";
		document.getElementById("divAgeOfMenarche").style.display="none";
		//document.getElementById("divDateOfMenarche").style.display="block";
	}
}

// On change Detection Date
function onchangeDetectionDate(obj)
{
	if(obj.value!="")
	{
		/*// Can't be greater than sysdate
		if(!validateDateEqualNBefore(obj,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("Pregnancy Detection Date can't be greater than Current Date");
			obj.value="";
			obj.focus();
			return;
		}
		// Can't less than LMP Date
		if(document.getElementsByName("lmpDate")[0].value!="")
		{
			if(!validateDateAfterOnly(obj,"dd-Mon-yyyy",document.getElementsByName("lmpDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Pregnancy Detection Date can't be less than LMP Date");
				obj.value="";
				obj.focus();
				return;
			}
		}
		// Can't less than Gestaion Start Date
		if(document.getElementsByName("gestationStartDate")[0].value!="")
		{
			if(!validateDateAfterOnly(obj,"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Pregnancy Detection Date can't be less than Gestation Start Date");
				obj.value="";
				obj.focus();
				return;
			}
		}
		// should be less than EDD //------ at least 7.5 months
		if(document.getElementsByName("ultraSoundEDD")[0].value!="")
		{
			if(!validateDateBeforeOnly(obj,"dd-Mon-yyyy",document.getElementsByName("ultraSoundEDD")[0],"dd-Mon-yyyy"))
			{			
				alert("Pregnancy Detection Date should be less than Ultra Sound EDD");
				obj.value="";
				obj.focus();
				return;
			}
		}*/
	}
}

// On Change LMP Date
function onchangelmpdate(obj)
{
	if(obj.value!="")
	{
		/*// Can't be greater than sysdate
		if(!validateDateEqualNBefore(obj,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("LMP Date can't be greater than Current Date");
			obj.value="";
			obj.focus();
			return;
		}
		// Should less than Detection Date
		if(document.getElementsByName("detectionDate")[0].value!="")
		{
			if(!validateDateBeforeOnly(obj,"dd-Mon-yyyy",document.getElementsByName("detectionDate")[0],"dd-Mon-yyyy"))
			{			
				alert("LMP Date should be less than Pregnancy Detection Date");
				obj.value="";
				obj.focus();
				return;
			}
		}*/
		/*/////////// Can't be greater than Gestation Start Date
		if(document.getElementsByName("gestationStartDate")[0].value!="")
		{
			if(!validateDateEqualNBefore(obj,"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
			{			
				alert("LMP Date can't be greater than Gestation Start Date");
				obj.value="";
				obj.focus();
				return;
			}
		}*/
		/*// should be less than EDD //------ at least 8.5 months
		if(document.getElementsByName("ultraSoundEDD")[0].value!="")
		{
			if(!validateDateBeforeOnly(obj,"dd-Mon-yyyy",document.getElementsByName("ultraSoundEDD")[0],"dd-Mon-yyyy"))
			{			
				alert("LMP Date should be less than Ultra Sound EDD");
				obj.value="";
				obj.focus();
				return;
			}
		}*/
	}
	var gestdt = document.getElementsByName("gestationStartDate")[0];
	//if(gestdt.value=="")
	{
		gestdt.value= obj.value;
		setEDD(gestdt);
	}
}

// On change Gestation Date
function onchangeGestStartdate(obj)
{
	if(obj.value!="")
	{
		/*// Can't be greater than Sysdate
		if(!validateDateEqualNBefore(obj,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("Gestation Start Date can't be greater than Current Date");
			obj.value="";
			obj.focus();
			return;
		}
		// Should less than Detection Date
		if(document.getElementsByName("detectionDate")[0].value!="")
		{
			if(!validateDateBeforeOnly(obj,"dd-Mon-yyyy",document.getElementsByName("detectionDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Gestation Start Date should be less than Pregnancy Detection Date");
				obj.value="";
				obj.focus();
				return;
			}
		}
		// Can't be less than LMP Date
		if(document.getElementsByName("lmpDate")[0].value!="")
		{
			if(!validateDateEqualNAfter(obj,"dd-Mon-yyyy",document.getElementsByName("lmpDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Gestation Start Date can't be less than LMP Date");
				obj.value="";
				obj.focus();
				return;
			}
		}
		// should be less than EDD //------ at least 8.5 months
		if(document.getElementsByName("ultraSoundEDD")[0].value!="")
		{
			if(!validateDateBeforeOnly(obj,"dd-Mon-yyyy",document.getElementsByName("ultraSoundEDD")[0],"dd-Mon-yyyy"))
			{			
				alert("Gestation Start Date should be less than Ultra Sound EDD");
				obj.value="";
				obj.focus();
				return;
			}
		}*/
	}
	setEDD(obj);
}

// Set EDD
function setEDD(obj)
{
	var gestStrDt = convertStrToDate(obj.value,"dd-Mon-yyyy");
	if(gestStrDt!=null)
	{
		var edd = addToDate(gestStrDt,9,"M");
		edd = addToDate(edd,7,"D");
		//alert(edd);
		document.getElementsByName("expectedDeliveryDate")[0].value= convertDateToStr(edd,"dd-Mon-yyyy");
		var gestStrDt = convertStrToDate(obj.value,"dd-Mon-yyyy");
		var entryDate = convertStrToDate(document.getElementsByName("entryDate")[0].value,"dd-Mon-yyyy");
		//alert(gestStrDt);
		//alert(entryDate);
		//alert(entryDate-gestStrDt);
		//alert((entryDate-gestStrDt)/(1000*60*60*24*7));
		var gestWeek = Math.ceil((entryDate-gestStrDt)/(1000*60*60*24*7));
		document.getElementsByName("gestationPeriod")[0].value=gestWeek;
	}
}

// On Change US EDD
function onchangeUSEDD(obj)
{
	if(obj.value!="")
	{
		/*// Can't be less than Sysdate
		if(!validateDateBeforeOnly(obj,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("US EDD can't be less than Current Date");
			obj.value="";
			obj.focus();
			return;
		}
		// Should greater than Detection Date
		if(document.getElementsByName("detectionDate")[0].value!="")
		{
			if(!validateDateAfterOnly(obj,"dd-Mon-yyyy",document.getElementsByName("detectionDate")[0],"dd-Mon-yyyy"))
			{			
				alert("US EDD should be greater than Pregnancy Detection Date");
				obj.value="";
				obj.focus();
				return;
			}
		}
		// Should greater than LMP Date
		if(document.getElementsByName("lmpDate")[0].value!="")
		{
			if(!validateDateAfterOnly(obj,"dd-Mon-yyyy",document.getElementsByName("lmpDate")[0],"dd-Mon-yyyy"))
			{			
				alert("US EDD should be greater than LMP Date");
				obj.value="";
				obj.focus();
				return;
			}
		}
		// Should greater than Gestation Start Date
		if(document.getElementsByName("gestationStartDate")[0].value!="")
		{
			if(!validateDateAfterOnly(obj,"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
			{			
				alert("US EDD should be greater than Gestation Start Date");
				obj.value="";
				obj.focus();
				return;
			}
		}*/
	}
	//effectOfUSEDD(obj);//------
}

// Change on US EDD
function effectOfUSEDD(obj)
{
	if(obj.value!="")
	{
		//alert(document.getElementsByName("lmpDate")[0].type.toUpperCase());
		if(document.getElementsByName("lmpDate")[0].type.toUpperCase()!="HIDDEN")
		{
			var objLmpDate = document.getElementsByName("lmpDate")[0];			
			//alert(obj.value);
			var usedd = convertStrToDate(obj.value,"dd-Mon-yyyy");
			if(usedd!=null)
			{
				var gestStrDt = addToDate(usedd,-9,"M");
				gestStrDt = addToDate(gestStrDt,-7,"D");
				//alert(gestStrDt);
				var gestDtStr = convertDateToStr(gestStrDt,"dd-Mon-yyyy");
				//alert(gestDtStr);
				objLmpDate.value = gestDtStr;
				onchangelmpdate(objLmpDate);
			}
		}
	}
}

// On Change Contraceptive Use Flag 
function onchangeContraceptiveUseFlag()
{
	var objFlagYes = document.getElementsByName("isContraceptiveUsed")[0];
	var objFlagNo = document.getElementsByName("isContraceptiveUsed")[1];
	var divRow = document.getElementById("divContraceptiveUseDtl");
	
	if(objFlagYes.checked)
	{
		divRow.style.display = "block";
		document.getElementsByName("contraceptiveRemarks")[0].value="";
	}
	else if(objFlagNo.checked)
	{
		divRow.style.display = "none";
		document.getElementsByName("contraceptiveRemarks")[0].value="";
	}		 
}

// On Blur Queckening Week 
function onblurQueckenkening(obj)
{
	var divRow = document.getElementById("divQueckeningRemarksDtl");
	
	if(obj.value!="")
	{
		divRow.style.display = "block";
		document.getElementsByName("queckeningRemarks")[0].value="";
	}
	else
	{
		divRow.style.display = "none";
	}		 
}

// On Change High Risk Flag 
function checkHighRiskPreg(obj)
{
	var divRow = document.getElementById("divComplications");
	if(obj.checked)
	{
		divRow.style.display = "block";
		document.getElementsByName("complications")[0].value="";
	}
	else
	{
		divRow.style.display = "none";
		document.getElementsByName("complications")[0].value="";
	}		 
}

// Validate Dates Detection Date, LMP, Gestation Start Date, EDD, US EDD
function validateDates()
{
	var objDectectDate = document.getElementsByName("detectionDate")[0];
	var objLMPDate = document.getElementsByName("lmpDate")[0];
	var objGestStartDate = document.getElementsByName("gestationStartDate")[0];
	var objEDDDate = document.getElementsByName("expectedDeliveryDate")[0];
	var objGestPerd = document.getElementsByName("gestationPeriod")[0];
	var objUSEDD = document.getElementsByName("ultraSoundEDD")[0];
	
	/*if(document.getElementsByName("lmpDate")[0].value=="")
	{
		alert("Please Enter Last Menstrual Date");
		openDiv("divANDtl");
		document.getElementsByName("lmpDate")[0].focus();
		return false;
	}
	if(document.getElementsByName("gestationStartDate")[0].value=="")
	{
		alert("Please Enter Gestation Start Date");
		openDiv("divANDtl");
		document.getElementsByName("gestationStartDate")[0].focus();
		return false;
	}*/
	// Detection Date
	if(objDectectDate.value!="")
	{
		// Can't be greater than sysdate
		if(!validateDateEqualNBefore(objDectectDate,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("Pregnancy Detection Date can't be greater than Current Date");
			objDectectDate.value="";
			openDiv("divANDtl");
			objDectectDate.focus();
			return;
		}
		// Can't less than LMP Date
		if(document.getElementsByName("lmpDate")[0].value!="")
		{
			if(!validateDateAfterOnly(objDectectDate,"dd-Mon-yyyy",document.getElementsByName("lmpDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Pregnancy Detection Date can't be less than LMP Date");
				objDectectDate.value="";
				openDiv("divANDtl");
				objDectectDate.focus();
				return;
			}
		}
		// Can't less than Gestaion Start Date
		if(document.getElementsByName("gestationStartDate")[0].value!="")
		{
			if(!validateDateAfterOnly(objDectectDate,"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Pregnancy Detection Date can't be less than Gestation Start Date");
				objDectectDate.value="";
				openDiv("divANDtl");
				objDectectDate.focus();
				return;
			}
		}
		// should be less than EDD //------ at least 7.5 months
		if(document.getElementsByName("ultraSoundEDD")[0].value!="")
		{
			if(!validateDateBeforeOnly(objDectectDate,"dd-Mon-yyyy",document.getElementsByName("ultraSoundEDD")[0],"dd-Mon-yyyy"))
			{			
				alert("Pregnancy Detection Date should be less than Ultra Sound EDD");
				objDectectDate.value="";
				openDiv("divANDtl");
				objDectectDate.focus();
				return;
			}
		}
	}
	// On Change LMP Date 
	if(objLMPDate.value!="")
	{
		// Can't be greater than sysdate
		if(!validateDateEqualNBefore(objLMPDate,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("LMP Date can't be greater than Current Date");
			objLMPDate.value="";
			openDiv("divANDtl");
			objLMPDate.focus();
			return;
		}
		// Should less than Detection Date
		if(document.getElementsByName("detectionDate")[0].value!="")
		{
			if(!validateDateBeforeOnly(objLMPDate,"dd-Mon-yyyy",document.getElementsByName("detectionDate")[0],"dd-Mon-yyyy"))
			{			
				alert("LMP Date should be less than Pregnancy Detection Date");
				objLMPDate.value="";
				openDiv("divANDtl");
				objLMPDate.focus();
				return;
			}
		}
		/*// Can't be greater than Gestation Start Date
		if(document.getElementsByName("gestationStartDate")[0].value!="")
		{
			if(!validateDateEqualNBefore(objLMPDate,"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
			{			
				alert("LMP Date can't be greater than Gestation Start Date");
				objLMPDate.value="";
				openDiv("divANDtl");
				objLMPDate.focus();
				return;
			}
		}*/
		// should be less than EDD //------ at least 8.5 months
		if(document.getElementsByName("ultraSoundEDD")[0].value!="")
		{
			if(!validateDateBeforeOnly(objLMPDate,"dd-Mon-yyyy",document.getElementsByName("ultraSoundEDD")[0],"dd-Mon-yyyy"))
			{			
				alert("LMP Date should be less than Ultra Sound EDD");
				objLMPDate.value="";
				openDiv("divANDtl");
				objLMPDate.focus();
				return;
			}
		}
	}

	// Validate Gestation Start Date
	if(objGestStartDate.value!="")
	{
		// Can't be greater than Sysdate
		if(!validateDateEqualNBefore(objGestStartDate,"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{			
			alert("Gestation Start Date can't be greater than Current Date");
			objGestStartDate.value="";
			openDiv("divANDtl");
			objGestStartDate.focus();
			return;
		}
		// Should less than Detection Date
		if(document.getElementsByName("detectionDate")[0].value!="")
		{
			if(!validateDateBeforeOnly(objGestStartDate,"dd-Mon-yyyy",document.getElementsByName("detectionDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Gestation Start Date should be less than Pregnancy Detection Date");
				objGestStartDate.value="";
				openDiv("divANDtl");
				objGestStartDate.focus();
				return;
			}
		}
		// Can't be less than LMP Date
		if(document.getElementsByName("lmpDate")[0].value!="")
		{
			if(!validateDateEqualNAfter(objGestStartDate,"dd-Mon-yyyy",document.getElementsByName("lmpDate")[0],"dd-Mon-yyyy"))
			{			
				alert("Gestation Start Date can't be less than LMP Date");
				objGestStartDate.value="";
				openDiv("divANDtl");
				objGestStartDate.focus();
				return;
			}
		}
		// should be less than EDD //------ at least 8.5 months
		if(document.getElementsByName("ultraSoundEDD")[0].value!="")
		{
			if(!validateDateBeforeOnly(objGestStartDate,"dd-Mon-yyyy",document.getElementsByName("ultraSoundEDD")[0],"dd-Mon-yyyy"))
			{			
				alert("Gestation Start Date should be less than Ultra Sound EDD");
				objGestStartDate.value="";
				openDiv("divANDtl");
				objGestStartDate.focus();
				return;
			}
		}
	}

	// Gestation Start Period
	if(objGestPerd.value!="")
	{
		var gestPer = parseInt(objGestPerd.value);
		if(gestPer<=0)
		{
			alert("Gestation Period Can't be less 1 \nPlease change LMP or Gestation Start Date or US EDD");
			objGestPerd.value="";
			openDiv("divANDtl");
			document.getElementsByName("lmpDate")[0].focus();
			return false;
		}
	}
	
	// US EDD
	if(objUSEDD.value!="")
	{
		/*// Can't be less than Sysdate
		if(!validateDateBeforeOnly(document.getElementsByName("entryDate")[0],"dd-Mon-yyyy",objUSEDD,"dd-Mon-yyyy"))
		{			
			alert("US EDD can't be less than Current Date");
			objUSEDD.value="";
			openDiv("divANDtl");
			objUSEDD.focus();
			return;
		}*/
		// Should greater than Detection Date
		if(document.getElementsByName("detectionDate")[0].value!="")
		{
			if(!validateDateAfterOnly(objUSEDD,"dd-Mon-yyyy",document.getElementsByName("detectionDate")[0],"dd-Mon-yyyy"))
			{			
				alert("US EDD should be greater than Pregnancy Detection Date");
				objUSEDD.value="";
				openDiv("divANDtl");
				objUSEDD.focus();
				return;
			}
		}
		// Should greater than LMP Date
		if(document.getElementsByName("lmpDate")[0].value!="")
		{
			if(!validateDateAfterOnly(objUSEDD,"dd-Mon-yyyy",document.getElementsByName("lmpDate")[0],"dd-Mon-yyyy"))
			{			
				alert("US EDD should be greater than LMP Date");
				objUSEDD.value="";
				openDiv("divANDtl");
				objUSEDD.focus();
				return;
			}
		}
		// Should greater than Gestation Start Date
		if(document.getElementsByName("gestationStartDate")[0].value!="")
		{
			if(!validateDateAfterOnly(objUSEDD,"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
			{			
				alert("US EDD should be greater than Gestation Start Date");
				objUSEDD.value="";
				openDiv("divANDtl");
				objUSEDD.focus();
				return;
			}
		}
	}
	return true;
}

// Validate Common
function validateCommon()
{
	// Queckening Week
	if(document.getElementsByName("queckeningWeek")[0].value!="" && document.getElementsByName("gestationPeriod")[0].value!="")
	{
		var queckWeek=parseInt(document.getElementsByName("queckeningWeek")[0].value);
		var gestWeek=parseInt(document.getElementsByName("gestationPeriod")[0].value);
		if(queckWeek>gestWeek)
		{
			alert("Queckening Week can never greater than Gestation Week");
			openDiv("divANDtl");
			document.getElementsByName("queckeningWeek")[0].focus();
			return false;
		}
	}
	/*if(document.getElementsByName("patHusbandName")[0].value=="")
	{
		alert("Please Enter Spouse Name");
		openDiv("divOtherDtl");
		document.getElementsByName("patHusbandName")[0].focus();
		return false;
	}*/
	var patage=parseInt(document.getElementsByName("patAge")[0].value);
	if(document.getElementsByName("isActualDateOfMarriage")[0])
	{
		/*if(document.getElementsByName("patAgeOfMarriage")[0].value=="" && document.getElementsByName("patDateOfMarriage")[0].value=="")
		{
			alert("Please Enter Date of Marriage");
			openDiv("divOtherDtl");
			if(document.getElementsByName("isActualDateOfMarriage")[0].value=="0")
				document.getElementsByName("patAgeOfMarriage")[0].focus();
			else
				document.getElementsByName("patDateOfMarriage")[0].focus();
			return false;
		}*/
		if(document.getElementsByName("isActualDateOfMarriage")[0].value=="0" && document.getElementsByName("patAgeOfMarriage")[0].value!="")
		{
			var patMarriageAge=parseInt(document.getElementsByName("patAgeOfMarriage")[0].value);
			if(patMarriageAge>patage)
			{
				alert("Date of Marriage can't be greater than Patient Age");
				openDiv("divOtherDtl");
				document.getElementsByName("patAgeOfMarriage")[0].focus();
				return false;
			}
		}// else check for entered Date of Marriage Age
	}
	
	if(document.getElementsByName("patAgeOfMenarche")[0].value=="" /*&& document.getElementsByName("patDateOfMenarche")[0].value==""*/)
	{
		alert("Please Enter Age of Menarche");
		openDiv("divOtherDtl");
		document.getElementsByName("patAgeOfMenarche")[0].focus();
		return false;
	}
	var patMenarcheAge=parseInt(document.getElementsByName("patAgeOfMenarche")[0].value);
	if(patMenarcheAge>patage)
	{
		alert("Age of Menarche can't be greater than Patient Age");
		openDiv("divOtherDtl");
		document.getElementsByName("patAgeOfMenarche")[0].focus();
		return false;
	}
	 
	if(document.getElementsByName("menstrualCycleId")[0].value=="-1")
	{
		alert("Please Select Menstrual Cycle Type");
		openDiv("divOtherDtl");
		document.getElementsByName("menstrualCycleId")[0].focus();
		return false;
	}
	// Menstrual Cycle Days inbertween 15 to 40
	if(document.getElementsByName("menstrualCycleDays")[0].value!="")
	{
		var mensDays=parseInt(document.getElementsByName("menstrualCycleDays")[0].value);
		if(!(mensDays>=15 && mensDays<=40))
		{
			alert("Not Proper Menstrual Cycle Days");
			openDiv("divOtherDtl");
			document.getElementsByName("menstrualCycleDays")[0].focus();
			return false;
		}
	}
	return true;
}

// Validate on Save First
function validateAdd()
{
	//alert("1");
	var ancHistPan = window.Pragyas_ANCHistoryPan;
	var deliveryDate=ancHistPan.getLastDeliveryDate();
	
	//alert("inside validate add")
	//alert("entry"+convertStrToDate(document.getElementsByName("lmpDate")[0].value,"dd-Mon-yyyy"))
	//alert("entry del"+convertStrToDate(deliveryDate,"dd-Mon-yyyy"))
	if(document.getElementsByName("gravidaNo")[0].value=="")
	{
		alert("Please Enter Gravida");
		openDiv("divANDtl");
		document.getElementsByName("gravidaNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("gravidaNo")[0].value!="")
	{
			if(document.getElementsByName("gravidaNo")[0].value<parseInt(document.getElementsByName("initalGravida")[0].value))
			{
					alert("Gravida Can't be less than " +document.getElementsByName("initalGravida")[0].value+ " for this Patient");
					openDiv("divANDtl");
					document.getElementsByName("gravidaNo")[0].focus();
					return false;
			}
	}
	
	if(document.getElementsByName("parityNo")[0].value=="")
	{
		alert("Please Enter Parity");
		openDiv("divANDtl");
		document.getElementsByName("parityNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("abortusNo")[0].value=="")
	{
		alert("Please Enter Abortus");
		openDiv("divANDtl");
		document.getElementsByName("abortusNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("lmpDate")[0].value=="")
	{
		alert("Please Enter LMP Date");
		openDiv("divANDtl");
		document.getElementsByName("lmpDate")[0].focus();
		return false;
	}
	if(!validateDates())	return false;
	if(!validateCommon())	return false;
	
	document.getElementById("divOtherDtl").style.display="block";
	//document.getElementById("divGynaeDtl").style.display="block";
	document.getElementById("divANDtl").style.display="block";
	//document.getElementsByName("expectedDeliveryDate")[0].disabled = false;
	
	
	
	
	if(!validateHistoryAdd())
		return false;	
		
	
	enableAllDisableValues();
	return true;
}

function validateAddFollow()
{
	var ancHistPan = window.Pragyas_ANCHistoryPan;
	var deliveryDate=ancHistPan.getLastDeliveryDate();
	
	//alert("inside foollowup")
	//alert("entry"+convertStrToDate(document.getElementsByName("lmpDate")[0].value,"dd-Mon-yyyy"))
	//alert("entry del"+convertStrToDate(deliveryDate,"dd-MM-yyyy"))
	if(!validateDates())	return false;
	if(!validateCommon())	return false;
	
	document.getElementById("divOtherDtl").style.display="block";
	//document.getElementById("divGynaeDtl").style.display="block";
	document.getElementById("divANDtl").style.display="block";
	//document.getElementsByName("expectedDeliveryDate")[0].disabled = false;
	
	
	if(!validateHistoryAdd())
		return false;	
	enableAllDisableValues();
	if(document.getElementsByName("episodeCode")[0].value != document.getElementsByName("ancEpisodeCode")[0].value)
	{
		if(confirm("This ANC Detail is initiated in Unit '"+document.getElementsByName("ancEpisodeUnit")[0].value+"'. \nDo you want to continue ?"))
			return true;
		else
			return false;
	}
	
	
	
	
	return true;
}

function enableAllDisableValues()
{
	//document.getElementsByName("expectedDeliveryDate")[0].disabled = false;
	//document.getElementsByName("gestationPeriod")[0].disabled = false;	
}

function validateHistoryAdd()
{
	var ancHistPan = window.Pragyas_ANCHistoryPan;
	if(!ancHistPan.validateHistoryRows())
		return false;
	return true;
}

function setHistoryRows(obj)
{
	try
	{
		if( parseInt(obj.value) < parseInt(document.getElementsByName("initalGravida")[0].value))
		{
			alert("Gravida Can't be less than " +document.getElementsByName("initalGravida")[0].value+ " for this Patient");
			openDiv("divANDtl");
			obj.focus();
			return; 
		}		
		var ancHistPan = window.Pragyas_ANCHistoryPan;
		ancHistPan.setGravida(parseInt(obj.value),obj);
	}
	catch(e)
	{
		alert("Error Message >> "+e.message);
	}
}

function showData(obj)
{
	var h="";
	for(var d in obj)
	{
		h+=d+" = "+obj[d]+"\n";
		if(h.length>300)
		{
			alert(h);
			h="";
		}
	}
	alert(h);
}



function cloneObject(obj)
{
	if(typeof(obj) != 'object') return obj;
	if(obj == null) return obj;
	var cloneObj = new Object();
	for(var i in obj)	cloneObj[i] = obj[i];
	return cloneObj;
}

function endANCDetail()
{
	// make all necessary fileds mandatory at SAVE 
	if(document.getElementsByName("lmpDate")[0].value=="")
	{
		alert("Please Enter Last Menstrual Date");
		openDiv("divANDtl");
		document.getElementsByName("lmpDate")[0].focus();
		return false;
	}

	var objEndFlag = document.getElementsByName("endANCDetailFlag")[0];
	// alert("anc flag"+document.getElementsByName("endANCDetailFlag")[0].value)
	if(objEndFlag.value!="1")
	{
		if(confirm("Are you sure to end the ANC Detail Forcefully"))
		{
			objEndFlag.value="1";	
			// Add a Row at the end of the History for addition
			addNFillGravidaHistRow();			
		}
	}
}

function addNFillGravidaHistRow()
{
	var ancHistPan = window.Pragyas_ANCHistoryPan;
	ancHistPan.setGravidaRow();
	ancHistPan.showANCTab();
}

function getAgeFromStr(_age)
{
	var age = -1;
	if(_age!=null && trimData(_age)!="")
	{
		_age = trimData(_age);
		_age = trimData(_age.replace("Yr",""));
		try
		{
			age = parseInt(age);
		}
		catch (e)
		{
			age=-1;
		}
		if(age==-1)	age=null;
	}
	return age;
}

// Validate Gravida History Row (at the time of forcefully end)
function validateGravidaHistoryRowData()
{
	//---------------
	return true;
}

function openANCMacros(processId,event)
{
	var path="/HISClinical/inpatient/antenatalDetail.cnt?hmode=ADDMACRO&macroProcessId="+processId;
	openPopup(createFHashAjaxQuery(path),event,300,600);
}


