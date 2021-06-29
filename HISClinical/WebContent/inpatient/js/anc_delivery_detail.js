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

function onchangeDelDate(objDelDate)
{
	var objGestStrtDate = document.getElementsByName("gestationStartDate")[0];
	var gestStrtDate = convertStrToDate(objGestStrtDate.value,"dd-Mon-yyyy");
	var deliveryDate = convertStrToDate(objDelDate.value,"dd-Mon-yyyy"); 
	if(deliveryDate!=null && gestStrtDate!=null)
	{
		var gestWeek = Math.ceil((deliveryDate-gestStrtDate)/(1000*60*60*24*7));
		document.getElementsByName("pregnancyDuration")[0].value=gestWeek;
	}
}

function onchangeRupDateTime()
{
	var objRupDate = document.getElementsByName("ruptureDate")[0];
	var objRupTime = document.getElementsByName("ruptureTime")[0];
	var objRupDatTime = document.getElementsByName("ruptureDateTime")[0];
	
	var dateTime = "";
	if(objRupDate.value!="")	dateTime+=objRupDate.value;
	if(objRupTime.value!="" && validateTime(document.getElementsByName("ruptureTime")[0]))	dateTime+=+" "+objRupTime.value;
	objRupDatTime.value = dateTime;
}

function onchangeLaborStageDur()
{
	var objLabourDurStg1 = document.getElementsByName("labourStage1Duration")[0];
	var objLabourDurStg2 = document.getElementsByName("labourStage2Duration")[0];
	var objLabourDurStg3 = document.getElementsByName("labourStage3Duration")[0];
	
	var objLabourDur = document.getElementsByName("labourDuration")[0]; 
	
	var labDur = 0;
	if(objLabourDurStg1.value!="") labDur+=parseInt(objLabourDurStg1.value);
	if(objLabourDurStg2.value!="") labDur+=parseInt(objLabourDurStg2.value);
	if(objLabourDurStg3.value!="") labDur+=parseInt(objLabourDurStg3.value);
	
	objLabourDur.value = labDur;
}



function onChangeBirthNature(obj,_livebirth)
{
	var index = findIndex(obj);
	if(obj.value==_livebirth)
	{
		document.getElementsByName("birthDeathAge")[index].disabled = true;
		document.getElementsByName("birthDeathCause")[index].disabled = true;
	}
	else
	{
		document.getElementsByName("birthDeathAge")[index].disabled = false;
		document.getElementsByName("birthDeathCause")[index].disabled = false;
	}
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

function setOutComeTable(count)
{
	var tbl = document.getElementById("tblOutcome");
	var trheader = document.getElementById("trHeaderOutcome");
	var trData = document.getElementById("trDataOutcome");
	
	if(count<=0)
	{
		for(var i=0; i<prevDelCount; i++)
			tbl.deleteRow(tbl.rows.length-1);
		prevDelCount = 0;
		tbl.style.display = "none";
	}
	else
	{
		if(count>prevDelCount)
		{	
			for(var i=0; i<(count-prevDelCount); i++)
			{				
				var tr = tbl.insertRow(tbl.rows.length);				
				for(var j=0;j<parseInt(trData.childNodes.length);j++)
				{
					var tdData = trData.childNodes[j];
					var td= tdData.cloneNode(true);					
					tr.appendChild(td);
					setSLNoInsideBold(td,prevDelCount+i+1);
					var objdelDate = convertStrToDate(document.getElementsByName("deliveryDate")[0].value,'dd-Mon-yyyy');
					var delDate = convertDateToStr(objdelDate,'dd-MM-yyyy');
					if(delDate!="")
						setBirthDateAsDelDate(td,delDate);
				}
			}
		}
		else if(count<prevDelCount)
		{
			for(var i=0; i<(prevDelCount-count); i++)
				tbl.deleteRow(tbl.rows.length-1);
		}
		tbl.style.display = "block";
	}
}

function setSLNoInsideBold(obj,_slno)
{
	if(obj.id && obj.id=="boldSlNo#")
	{
		obj.id=="boldSlNo#"+_slno;
		obj.innerHTML=_slno+".";
		return true;
	}
	else
	{
		if(obj.childNodes)
		{
			for(var i=0;i<parseInt(obj.childNodes.length);i++)
			{
				if(setSLNoInsideBold(obj.childNodes[i],_slno))
					return true;
			}
		}
	}
	return false;
}

function setBirthDateAsDelDate(obj,_delDate)
{
	if(typeof obj=="object" && obj.tagName && obj.tagName.toUpperCase()=="INPUT" && obj.name=="birthDate")
	{
		obj.value=_delDate;
		return true;
	}
	else
	{
		if(obj.childNodes)
		{
			for(var i=0;i<parseInt(obj.childNodes.length);i++)
			{
				if(setBirthDateAsDelDate(obj.childNodes[i],_delDate))
					return true;
			}
		}
	}
	return false;
}

function validateOutComeDetail()
{
	for(var i=1; i<prevDelCount+1; i++)
	{
			// Birth Date
		if(document.getElementsByName("birthDate")[i].value=="")
		{
			alert("Please Enter Birth Date");
			openDiv("divANDtl");
			document.getElementsByName("birthDate")[i].focus();
			return false;
		}
		if(!validateDateEqualNAfter(document.getElementsByName("birthDate")[i],"dd-MM-yyyy",document.getElementsByName("deliveryDate")[0],"dd-Mon-yyyy"))
		{
			alert("Birth Date can't be less than Delivery Date...");
			openDiv("divANDtl");
			document.getElementsByName("birthDate")[0].focus();
			return false;
		}
		if(!validateDateEqualNBefore(document.getElementsByName("birthDate")[i],"dd-MM-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{
			alert("Birth Date can't be greater than Current Date...");
			openDiv("divANDtl");
			document.getElementsByName("birthDate")[0].focus();
			return false;
		}
			// Birth Time
		if(document.getElementsByName("birthTime")[i].value=="")
		{
			alert("Please Enter Birth Time");
			openDiv("divANDtl");
			document.getElementsByName("birthTime")[i].focus();
			return false;
		}
		if(validateDateEqualTo(document.getElementsByName("birthDate")[i],"dd-MM-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy") &&
			!validateTimeEqualNLess(document.getElementsByName("birthTime")[i],document.getElementsByName("entryTime")[0]))
		{
			alert("Birth Time can't be greater than Current Time...");
			openDiv("divANDtl");
			document.getElementsByName("deliveryDate")[0].focus();
			return false;
		}
		
		document.getElementsByName("birthDateTime")[i].value = document.getElementsByName("birthDate")[i].value + " " 
			+ document.getElementsByName("birthTime")[i].value;	
			// Birth Nature
		if(document.getElementsByName("birthNatureId")[i].value=="-1")
		{
			alert("Please Select Birth Nature");
			openDiv("divANDtl");
			document.getElementsByName("birthNatureId")[i].focus();
			return false;
		}			
			// Birth Gender
		if(document.getElementsByName("birthGenderCode")[i].value=="-1")
		{
			alert("Please Select Birth Gender");
			openDiv("divANDtl");
			document.getElementsByName("birthGenderCode")[i].focus();
			return false;
		}
			// Weigth
		if(document.getElementsByName("birthWeight")[i].value!="")
		{	
			var wt=null;
			try
			{
				wt = parseFloat(document.getElementsByName("birthWeight")[i].value);
			}
			catch(e)
			{
				wt==null;
			}
			if(wt==null || wt<0 || wt>9.9)
			{
				alert("Please Enter a proper Baby Weight in Range [0,9.9]");
				this.showChildTab();
				document.getElementsByName("birthWeight")[i].focus();
				return false;
			}
		}

			// Present Health
			// Birth Death Age
			// Death Cause
	}
	return true;
}

function validateAdd()
{
	//******** div 'divANDtl'
	// Validate compulsory		
		// Delivery Date	
	if(document.getElementsByName("deliveryDate")[0].value=="")
	{
		alert("Please Enter Delivery Date");
		openDiv("divANDtl");
		document.getElementsByName("deliveryDate")[0].focus();
		return false;
	}
	/*if(!validateDateEqualNDayBefore(document.getElementsByName("deliveryDate")[0],"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
	{
		alert("Delivery Date can be only Today or Day before Today...");
		openDiv("divANDtl");
		document.getElementsByName("deliveryDate")[0].focus();
		return false;
	}*/
	
		// Pregnancy Duration
	if(document.getElementsByName("pregnancyDuration")[0].value=="")
	{
		alert("Please Enter Pregnancy Duration");
		openDiv("divANDtl");
		document.getElementsByName("pregnancyDuration")[0].focus();
		return false;
	}
	
		// Labor Room
	if(document.getElementsByName("labourRoomId")[0].value=="-1")
	{
		alert("Please Select Labor Room");
		openDiv("divANDtl");
		document.getElementsByName("labourRoomId")[0].focus();
		return false;
	}
		// Labor Room Area
	if(document.getElementsByName("labourRoomAreaId")[0].value=="-1")
	{
		alert("Please Select Labor Room Area");
		openDiv("divANDtl");
		document.getElementsByName("labourRoomAreaId")[0].focus();
		return false;
	}

		// Rupture Date Time
	if(document.getElementsByName("ruptureTime")[0].value!="" && document.getElementsByName("ruptureDate")[0].value=="")
	{
		alert("Please Enter Date Also")
		openDiv("divANDtl");
		document.getElementsByName("ruptureDate")[0].focus();
		return false;
	}
	if(document.getElementsByName("ruptureTime")[0].value!="" && !validateTime(document.getElementsByName("ruptureTime")[0]))
	{
		alert("Please Enter a Valid Rupture Time")
		openDiv("divANDtl");
		document.getElementsByName("ruptureTime")[0].focus();
		return false;
	}
		
		// Rupture type
		// Rupture Duration
		
		// Is Induced
	/*if(document.getElementsByName("isInduced")[0].checked==false && document.getElementsByName("isInduced")[1].checked==false)
	{
		alert("Please whether Induced or not");
		openDiv("divANDtl");
		document.getElementsByName("isInduced")[0].focus();
		return false;
	}*/
		// Indication of Induction
		// Induction Method
	if(document.getElementsByName("isInduced")[0].checked == true)
	{
		if(document.getElementsByName("indicationOfInduction")[0].value=="")
		{
			alert("Please Enter Indication of Induction");
			openDiv("divANDtl");
			document.getElementsByName("indicationOfInduction")[0].focus();
			return false;
		}
		if(document.getElementsByName("inductionMethodId")[0].value=="-1")
		{
			alert("Please Select Induction Method Used");
			openDiv("divANDtl");
			document.getElementsByName("inductionMethodId")[0].focus();
			return false;
		}
	}
		
		// Labour Stage 1 Duration
	/*if(document.getElementsByName("labourStage1Duration")[0].value=="")
	{
		alert("Please Enter Labor Duration for Stage 1");
		openDiv("divANDtl");
		document.getElementsByName("labourStage1Duration")[0].focus();
		return false;
	}*/
		// Labour Stage 1 Remarks
		// Labour Stage 2 Duration
	/*if(document.getElementsByName("labourStage2Duration")[0].value=="")
	{
		alert("Please Enter Labor Duration for Stage 2");
		openDiv("divANDtl");
		document.getElementsByName("labourStage2Duration")[0].focus();
		return false;
	}*/
		// Labour Stage 2 Remarks
		// Labour Stage 3 Duration
	/*if(document.getElementsByName("labourStage3Duration")[0].value=="")
	{
		alert("Please Enter Labor Duration for Stage 3");
		openDiv("divANDtl");
		document.getElementsByName("labourStage3Duration")[0].focus();
		return false;
	}*/
		// Labour Stage 3 Remarks
		// Labour Duration
	/*if(document.getElementsByName("labourDuration")[0].value=="")
	{
		alert("Please Enter Labor Duration");
		openDiv("divANDtl");
		document.getElementsByName("labourDuration")[0].focus();
		return false;
	}*/
		// Labour Remarks
		
		// Foetal Distress
		// EAS

	//******** div ''

		// Delivery Type
	if(document.getElementsByName("deliveryTypeId")[0].value=="-1")
	{
		alert("Please Select Delivery Type");
		openDiv("divANDtl");
		document.getElementsByName("deliveryTypeId")[0].focus();
		return false;
	}
		// Indication of Delivery Method
		
		// Complications
		// Complications Remarks
		// Pregnancy Remarks

		// Mother Status
		// Death Cause 
	
	//******** div ''

		// Placenta Weight
		if(document.getElementsByName("placentaWeight")[0].value!="")
		{	
			var wt=null;
			try
			{
				wt = parseFloat(document.getElementsByName("placentaWeight")[0].value);
			}
			catch(e)
			{
				wt==null;
			}
			if(wt==null || wt<0 || wt>9.9)
			{
				alert("Please Enter a proper Placenta Weight in Range [0,9.9]");
				openDiv("divANCDelOutcome");
				document.getElementsByName("placentaWeight")[0].focus();
				return false;
			}
		}
		// Placenta Type
		// Placenta Morphology
		// Placenta Histopathplogy
		// Placenta Removal Method
		
		// No of Outcomes
	if(document.getElementsByName("deliveryCount")[0].value=="")
	{
		alert("Please Enter Delivery Count");
		openDiv("divANDtl");
		document.getElementsByName("deliveryCount")[0].focus();
		return false;
	}
	if(document.getElementsByName("deliveryCount")[0].value <=0)
	{
		alert("Delivery Count should be greater than Zero");
		openDiv("divANDtl");
		document.getElementsByName("deliveryCount")[0].focus();
		return false;
	}
	if(!validateOutComeDetail())
		return false;
		
	enableAllDisabled();
	//document.getElementById("divANDtl").style.display = "block";
	//document.getElementsByName("pregnancyDuration")[0].disabled = false;	
	return true;
}

function enableAllDisabled()
{
	var delCount = document.getElementsByName("deliveryCount")[0].value;
	for(var i=0; i<delCount; i++)
	{
		document.getElementsByName("birthDeathAge")[i].disabled = false;
		document.getElementsByName("birthDeathCause")[i].disabled = false;
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

var valLaborRoom=null;
function fillLaborRoomArea(objLR)
{
	if(valLaborRoom==null || valLaborRoom!=objLR.value)
	{
		valLaborRoom=objLR.value;
		
		var objLRA = document.getElementsByName("labourRoomAreaId")[0];
		objLRA.innerHTML="";
		
		var option = document.createElement("option");
		option.value="-1";
		option.innerHTML="Select Value";
		objLRA.appendChild(option);		
		
		var opts = document.getElementsByName("listLaborRoomArea")[0].options;
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			option = document.createElement("option");
			var optValue = opt.value;
			var ids = optValue.split("\^");
			if(ids[1]==valLaborRoom)
			{
				option.value = ids[0];
				option.innerHTML = opt.text;
				objLRA.appendChild(option);
			}
		}
		objLRA.selectedIndex = 0;
	}		
}

function onchangeInducedFlag()
{
	var objInducFlagYes = document.getElementsByName("isInduced")[0];
	var objInducFlagNo = document.getElementsByName("isInduced")[1];
	var divRow = document.getElementById("divInductionDtl");
	
	if(objInducFlagYes.checked)
	{
		divRow.style.display = "block";
	}
	else if(objInducFlagNo.checked)
	{
		divRow.style.display = "none";
		document.getElementsByName("indicationOfInduction")[0].value="";
		document.getElementsByName("inductionMethodId")[0].value="-1";
	}		 
}

function onchangeMotherStatus(objMomSta,val)
{
	var divRow = document.getElementById("divDeathCause");
	if(objMomSta.value==val)
	{
		divRow.style.display = "block";
	}
	else
	{
		divRow.style.display = "none";
		document.getElementsByName("deathCause")[0].value="-1";
	}		 
}