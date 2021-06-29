onclickImage = function(imgObj)
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
};

function onchangeSelectNewNat(obj)
{
	if(obj.checked)
		submitFormOnValidate(true,'GETNEONATALDTL');
}

function onchangeAnomolyFlag()
{
	var objInducFlagYes = document.getElementsByName("isAnomolyPresent")[0];
	var objInducFlagNo = document.getElementsByName("isAnomolyPresent")[1];
	var divRow = document.getElementById("divAnomolyDtl");
	
	if(objInducFlagYes.checked)
	{
		divRow.style.display = "block";
		document.getElementsByName("anomolyRemarks")[0].value="";
	}
	else if(objInducFlagNo.checked)
	{
		divRow.style.display = "none";
		document.getElementsByName("anomolyRemarks")[0].value="";
	}		 
}

function onchangeBitrthTrumaFlag()
{
	var objInducFlagYes = document.getElementsByName("isBirthTrauma")[0];
	var objInducFlagNo = document.getElementsByName("isBirthTrauma")[1];
	var divRow = document.getElementById("divBitrthTrumaDtl");
	
	if(objInducFlagYes.checked)
	{
		divRow.style.display = "block";
		document.getElementsByName("traumaCauseId")[0].value="-1";
		document.getElementsByName("birthTraumaRemarks")[0].value="";
	}
	else if(objInducFlagNo.checked)
	{
		divRow.style.display = "none";
		document.getElementsByName("traumaCauseId")[0].value="-1";
		document.getElementsByName("birthTraumaRemarks")[0].value="";
	}		 
}

function getBirthCertificate(_serial, event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var gravidaNo=document.getElementsByName("gravidaNo")[0].value;
	var path='/HISClinical/inpatient/antenatalNeonatalDetail.cnt?hmode=GENERATEBIRTHCERT&patCrNo='+patCrNo+'&gravidaNo='+gravidaNo+'&selBabySerialCert='+_serial;
	openPopup(createFHashAjaxQuery(path),event,400,800);
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

function setTotalApgarScore()
{
	var objApgarScoreHeartRate = document.getElementsByName("heartRateApgar")[0];
	var objApgarScoreRespiration = document.getElementsByName("respirationApgar")[0];
	var objApgarScoreColor = document.getElementsByName("colorApgar")[0];
	var objApgarScoreActivity = document.getElementsByName("activityApgar")[0];
	var objApgarScoreGrimace = document.getElementsByName("grimaceApgar")[0];
	
	var objApgarScore = document.getElementsByName("apgarScore")[0]; 
	
	var score = 0;
	if(objApgarScoreHeartRate.value!="") score+=parseInt(objApgarScoreHeartRate.value);
	if(objApgarScoreRespiration.value!="") score+=parseInt(objApgarScoreRespiration.value);
	if(objApgarScoreColor.value!="") score+=parseInt(objApgarScoreColor.value);
	if(objApgarScoreActivity.value!="") score+=parseInt(objApgarScoreActivity.value);
	if(objApgarScoreGrimace.value!="") score+=parseInt(objApgarScoreGrimace.value);
	
	objApgarScore.value = score;
}

function popupApgarDetail(event)
{
	/*var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var gravidaNo=document.getElementsByName("gravidaNo")[0].value;
	var neonatalSlNo=document.getElementsByName("selectedNeoNat")[0].value;*/
	var path='/HISClinical/inpatient/antenatalNeonatalDetail.cnt?hmode=APGARDTLPOPUP';//&patCrNo='+patCrNo+'&gravidaNo='+gravidaNo+'&selectedNeoNat='+neonatalSlNo;
	openPopup(createFHashAjaxQuery(path),event,400,800);
}

function validateAdd()
{
	//******** div 'divNeoNatDtl'
		// Gender Code	
	if(document.getElementsByName("genderCode")[0].value=="-1")
	{
		alert("Please Select Baby Gender");
		openDiv("divNeoNatDtl");
		document.getElementsByName("genderCode")[0].focus();
		return false;
	}
		// Weight
	if(document.getElementsByName("weight")[0].value=="")
	{
		alert("Please Enter Baby Weight");
		openDiv("divNeoNatDtl");
		document.getElementsByName("weight")[0].focus();
		return false;
	}	
	if(document.getElementsByName("weight")[0].value!="")		
	{
		var wt=null;
		try
		{
			wt = parseFloat(document.getElementsByName("weight")[0].value);
		}
		catch(e)
		{
			wt==null;
		}
		if(wt==null || wt<0 || wt>9.9)
		{
			alert("Please Enter a proper Baby Weight in Range [0,9.9]");
			openDiv("divNeoNatDtl");
			document.getElementsByName("weight")[0].focus();
			return false;
		}
	}	
		// Length
	if(document.getElementsByName("babylength")[0].value=="")
	{
		alert("Please Enter Baby Length");
		openDiv("divNeoNatDtl");
		document.getElementsByName("babylength")[0].focus();
		return false;
	}
	if(document.getElementsByName("babylength")[0].value!="")		
	{
		var lth=null;
		try
		{
			lth = parseFloat(document.getElementsByName("babylength")[0].value);
		}
		catch(e)
		{
			lth==null;
		}
		if(lth==null || lth<0 || lth>999.99)
		{
			alert("Please Enter a proper Baby Length");// in Range [0,999.99)");
			openDiv("divNeoNatDtl");
			document.getElementsByName("babylength")[0].focus();
			return false;
		}
	}	
		// Head Circumference
	if(document.getElementsByName("headCircumferences")[0].value=="")
	{
		alert("Please Enter Head Circumference");
		openDiv("divNeoNatDtl");
		document.getElementsByName("headCircumferences")[0].focus();
		return false;
	}
	if(document.getElementsByName("headCircumferences")[0].value!="")		
	{
		var hc=null;
		try
		{
			hc = parseFloat(document.getElementsByName("headCircumferences")[0].value);
		}
		catch(e)
		{
			hc==null;
		}
		if(hc==null || hc<0 || hc>99.9)
		{
			alert("Please Enter a proper Baby Head Circumference");// in Range (0,99.9)");
			openDiv("divNeoNatDtl");
			document.getElementsByName("headCircumferences")[0].focus();
			return false;
		}
	}	
		// Anomoly Present
	/*if(document.getElementsByName("isAnomolyPresent")[0].checked==false && document.getElementsByName("isAnomolyPresent")[1].checked==false)
	{
		alert("Please whether Anomoly present or Not");
		openDiv("divNeoNatDtl");
		document.getElementsByName("isAnomolyPresent")[0].focus();
		return false;
	}*/
		// Anomoly Remarks
	if(document.getElementsByName("isAnomolyPresent")[0])
	{
		if(document.getElementsByName("isAnomolyPresent")[0].checked == true)
		{
			if(document.getElementsByName("anomolyRemarks")[0].value=="")
			{
				alert("Please Enter Anomoloy Remarks");
				openDiv("divNeoNatDtl");
				document.getElementsByName("anomolyRemarks")[0].focus();
				return false;
			}
		}
	}
		// Cry Date Time
	if(document.getElementsByName("cryTime")[0])
	{
		if(document.getElementsByName("cryTime")[0].value!="" && document.getElementsByName("cryDate")[0].value=="")
		{
			alert("Please Enter First Cry Date Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("cryDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("cryDate")[0].value!="" && document.getElementsByName("cryTime")[0].value=="")
		{
			alert("Please Enter First Cry Time Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("cryTime")[0].focus();
			return false;
		}
	}
		// Urine Date Time
	if(document.getElementsByName("cryTime")[0])
	{
		if(document.getElementsByName("urineTime")[0].value!="" && document.getElementsByName("urineDate")[0].value=="")
		{
			alert("Please Enter First Urine Date Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("urineDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("urineDate")[0].value!="" && document.getElementsByName("urineTime")[0].value=="")
		{
			alert("Please Enter First Urine Time Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("urineTime")[0].focus();
			return false;
		}
	}
		// Mother Feed Date Time
	if(document.getElementsByName("cryTime")[0])
	{
		if(document.getElementsByName("motherFeedTime")[0].value!="" && document.getElementsByName("motherFeedDate")[0].value=="")
		{
			alert("Please Enter First Mother Feed Date Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("motherFeedDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("motherFeedDate")[0].value!="" && document.getElementsByName("motherFeedTime")[0].value=="")
		{
			alert("Please Enter First Mother Feed Time Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("motherFeedTime")[0].focus();
			return false;
		}
	}
		// Feed Date Time
	if(document.getElementsByName("cryTime")[0])
	{
		if(document.getElementsByName("feedTime")[0].value!="" && document.getElementsByName("feedDate")[0].value=="")
		{
			alert("Please Enter First Feed Date Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("feedDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("feedDate")[0].value!="" && document.getElementsByName("feedTime")[0].value=="")
		{
			alert("Please Enter First Feed Time Also")
			openDiv("divNeoNatDtl");
			document.getElementsByName("feedTime")[0].focus();
			return false;
		}
	}
		// Is Birth Trauma 
	/*if(document.getElementsByName("isBirthTrauma")[0].checked==false && document.getElementsByName("isBirthTrauma")[1].checked==false)
	{
		alert("Please whether Birth Trauma present or Not");
		openDiv("divNeoNatDtl");
		document.getElementsByName("isBirthTrauma")[0].focus();
		return false;
	}*/
		// Trauma Cause Id
	if(document.getElementsByName("isBirthTrauma")[0].checked == true)
	{
		if(document.getElementsByName("traumaCauseId")[0].value=="-1")
		{
			alert("Please Select Birth Trauma Cause");
			openDiv("divNeoNatDtl");
			document.getElementsByName("traumaCauseId")[0].focus();
			return false;
		}
	}
	// Still Birth Validations
		// When Detected
	if(document.getElementsByName("whenStillBirthDetection")[0] && document.getElementsByName("whenStillBirthDetection")[0].value=="-1")
	{
		alert("Please Select When Still Birth Detected");
		openDiv("divNeoNatDtl");
		document.getElementsByName("whenStillBirthDetection")[0].focus();
		return false;
	}
		// Cause of Death
	if(document.getElementsByName("deathStillBirthCause")[0] && document.getElementsByName("deathStillBirthCause")[0].value=="")
	{
		alert("Please Enter Cause of Death");
		openDiv("divNeoNatDtl");
		document.getElementsByName("deathStillBirthCause")[0].focus();
		return false;
	}

	//******** div 'divNeoNatApgarDtl'
	// If Apgar Entered , Validations
	var flagApgarEnter = false;
	if(document.getElementsByName("apgarTime")[0])
	{
		if(document.getElementsByName("apgarTime")[0].value!="-1" || 
			document.getElementsByName("heartRate")[0].value!="" || 
			document.getElementsByName("heartRateApgar")[0].value!="" || 
			document.getElementsByName("respiration")[0].value!="" || 
			document.getElementsByName("respirationApgar")[0].value!="" || 
			document.getElementsByName("color")[0].value!="" || 
			document.getElementsByName("colorApgar")[0].value!="" || 
			document.getElementsByName("activity")[0].value!="" || 
			document.getElementsByName("activityApgar")[0].value!="" || 
			document.getElementsByName("grimace")[0].value!="" || 
			document.getElementsByName("grimaceApgar")[0].value!="")
			flagApgarEnter = true;
		if(flagApgarEnter)
		{
			if(!validateApgarDetail())
				return false;
		}
		document.getElementsByName("apgarScore")[0].disabled = false;	
	}
	return true;
}

function validateApgarDetail()
{
	//******** div 'divNeoNatApgarDtl'
	// APGAR Time
	if(document.getElementsByName("apgarTime")[0].value=="-1")
	{
		alert("Please Select APGAR Time");
		openDiv("divNeoNatApgarDtl");
		document.getElementsByName("apgarTime")[0].focus();
		return false;
	}
	// Activity APGAR
	if(document.getElementsByName("activityApgar")[0].value=="")
	{
		alert("Please Enter Activity APGAR Score");
		openDiv("divNeoNatApgarDtl");
		document.getElementsByName("activityApgar")[0].focus();
		return false;
	}
	// Pulse/Heart Rate APGAR
	if(document.getElementsByName("heartRateApgar")[0].value=="")
	{
		alert("Please Enter Pulse APGAR Score");
		openDiv("divNeoNatApgarDtl");
		document.getElementsByName("heartRateApgar")[0].focus();
		return false;
	}
	// Grimace APGAR
	if(document.getElementsByName("grimaceApgar")[0].value=="")
	{
		alert("Please Enter Grimace APGAR Score");
		openDiv("divNeoNatApgarDtl");
		document.getElementsByName("grimaceApgar")[0].focus();
		return false;
	}		
	// Appearance/Color APGAR
	if(document.getElementsByName("colorApgar")[0].value=="")
	{
		alert("Please Enter Appearance APGAR Score");
		openDiv("divNeoNatApgarDtl");
		document.getElementsByName("colorApgar")[0].focus();
		return false;
	}
	// Respiration APGAR
	if(document.getElementsByName("respirationApgar")[0].value=="")
	{
		alert("Please Enter Respiration APGAR Score");
		openDiv("divNeoNatApgarDtl");
		document.getElementsByName("respirationApgar")[0].focus();
		return false;
	}
	return true;
}

function addAgparDetail()
{
	if(!validateAdd())
		return false;
	document.getElementsByName("apgarScore")[0].disabled = true;
	if(!validateApgarDetail())
		return false;
	document.getElementsByName("apgarScore")[0].disabled = false;
	return true;
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
