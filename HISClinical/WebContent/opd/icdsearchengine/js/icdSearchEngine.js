function viewList()
{
	var div = document.getElementById("divList");
	if(div.style.display=="none")
		div.style.display="block";
	else
		div.style.display="none";
}
function viewDetail()
{
	var div = document.getElementById("divDetail");
	var img = document.getElementById("imgDetail");
	if(div.style.display=="none")
	{
		div.style.display="block";
		img.src="/HIS/hisglobal/images/avai/arr-up.png";
	}
	else
	{
		div.style.display="none";
		img.src="/HIS/hisglobal/images/avai/arr-dwn.png";
	}
}


// Toggling Visibility of Sub Disease
function toggleSubDiseases(_mode, _recordType, _icdCode)
{
	//alert("divSub"+PragyaDesigner.trim(_icdCode));
	var divSub = document.getElementById("divSub"+PragyaDesigner.trim(_icdCode));
	var imgSub = document.getElementById("imgSub"+PragyaDesigner.trim(_icdCode));
	if(divSub.style.display == "none")
		showHideSubDiseases(true,divSub, imgSub);
	else
		showHideSubDiseases(false,divSub, imgSub);
	if(divSub.innerHTML == "")
	{
		setAJAXLoading(divSub);
		getSubDiseaseList(_mode, _recordType, _icdCode);
	}
}

function showHideSubDiseases(_flg, divSub, imgSub)
{
	if(_flg)
	{
		divSub.style.display = "block";
		imgSub.src = "/HIS/hisglobal/images/Mi_Green_Sqr.png";
	}	
	else
	{
		divSub.style.display = "none";
		imgSub.src = "/HIS/hisglobal/images/Pl_Green_Sqr.png";
	}
}

// Toggling Visibility of Advanced Search Tab
function viewSearchAdvanced(_flg)
{
	if(typeof _flg == 'undefined')	_flg = true;
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);
	
	var div = document.getElementById("divAdvancedSearch");
	var img = document.getElementById("imgSearchAdvanced");
	if(_flg && div.style.display=="none")
	{
		div.style.display="block";
		img.src="/HISClinical/hisglobal/images/arrdouble-up.png";
		objEngine.flgSearchAdvanced = true;
		objEngine.objEngineFrameSet.setFramesForSearchInitiateVol1Advanced();
	}
	else
	{
		div.style.display="none";
		img.src="/HISClinical/hisglobal/images/arrdouble-down.png";
		objEngine.flgSearchAdvanced = false;
		objEngine.objEngineFrameSet.setFramesForSearchInitiateVol1();
	}
}


// Searching  Volume 1
function GOForSearch(_srchType) // search Type ICD or Disease Name
{
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);
	//alert(objEngine.strCurOption);
	if(validateSearch(objEngine.strCurOption,_srchType))
	{		
		//alert(_srchType);
		document.getElementsByName("strSearchType")[0].value = _srchType;
		document.getElementsByName("strSearchStart")[0].value = "true";
		objEngine.initiateSearch(_srchType);
		if(objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1)
			viewSearchAdvanced(false);
	}	
}

function validateSearch(_curOption, _srchType)
{
	// Volume 1 Disease Name
	if(_curOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1)	
	{
		if(_srchType == ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME)
		{
			if(document.getElementsByName("strIcdDiseaseName")[0].value=="")
			{
				if(!(document.getElementsByName("strMainDiseaseTypeCode")[0].value!="-1" ||
					document.getElementsByName("strDiseaseTypeCode")[0].value!="-1" ||
					document.getElementsByName("strChronicDiseaseCode")[0].value!="-1"))
				{
					alert("Please Enter Disease Name for Searching");
					document.getElementsByName("strIcdDiseaseName")[0].focus();
					return false;
				}				
			}
		}
		else if(_srchType == ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_ICD_CODE)
		{
			if(document.getElementsByName("strIcdCodeMain")[0].value=="")
			{
				alert("Please Enter ICD Code for Searching");
				document.getElementsByName("strIcdCodeMain")[0].focus();
				return false;
			}
			if(!validateICDCode(document.getElementsByName("strIcdCodeMain")[0].value))
			{
				alert("Please Enter Valid ICD Code in format 'A00' (One Alphabet followed by two digits)");
				document.getElementsByName("strIcdCodeMain")[0].focus();
				return false;
			}
		}
		else if(_srchType == ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE)
		{
			if(document.getElementsByName("strIcdCodeMain")[0].value=="")
			{
				alert("Please Enter ICD Code for Searching");
				document.getElementsByName("strIcdCodeMain")[0].focus();
				return false;
			}
			if(!validateICDCode(document.getElementsByName("strIcdCodeMain")[0].value))
			{
				alert("Please Enter Valid ICD Code in format 'A00' (One Alphabet followed by two digits)");
				document.getElementsByName("strIcdCodeMain")[0].focus();
				return false;
			}
		}
	}
	else if(_curOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_3)	
	{
		if(document.getElementsByName("strIndexSearchString")[0].value=="")
		{
			alert("Please Disease Term for Searching");
			document.getElementsByName("strIndexSearchString")[0].focus();
			return false;
		}
	}
	else if(_curOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_BOTH)	
	{
		if(document.getElementsByName("strDiseaseName")[0].value=="")
		{
			alert("Please Disease Term for Searching");
			document.getElementsByName("strDiseaseName")[0].focus();
			return false;
		}
	}
	return true;
}


// Viewing ICD Disease Detail 
function viewICDDiseaseDetail(_event, _icdCode, _recordType, _slNo)
{
	var path='/HISClinical/opd/icdsearchengine/icdEngineDiseaseDtl.cnt?strDetailMode=NEW';
	path = path + '&diseaseCode=' + _icdCode + '&recordType='+_recordType + '&slNo='+ _slNo;
	openPopup(path, _event, 500, 600);
}

function seeICDDiseaseDetail(_icdCode)
{
	var arr = _icdCode.split("~");
	document.getElementsByName("diseaseCode")[0].value = arr[0];
	document.getElementsByName("recordType")[0].value = arr[1]; 
	document.getElementsByName("slNo")[0].value = arr[2];
	document.getElementsByName("strDetailMode")[0].value = "SHOW";
	document.forms[0].submit();
}

function nextICDDiseaseDetail()
{
	document.getElementsByName("strDetailMode")[0].value = "NEXT";
	document.forms[0].submit();
}

function prevICDDiseaseDetail()
{
	document.getElementsByName("strDetailMode")[0].value = "PREV";
	document.forms[0].submit();
}

// Toggling Visibility of Advanced Search Tab
function viewMoreDiseaseDtl()
{
	var div = document.getElementById("divMore");
	var divDtl = document.getElementById("divMoreDtl");
	div.style.display="none";
	divDtl.style.display="block";
}
function changeSearchOpt(chk,_searchType,_firstVal)
{
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);

	var divSrchName = document.getElementById("divSrchName");
	var divSrchID = document.getElementById("divSrchId");
	var divSrchNameImg = document.getElementById("divSrchNameImg");
	var divSrchIDImg = document.getElementById("divSrchIdImg");
	var divSrchOpt = document.getElementById("divOptName");
	if(chk.checked)
	{
		if(_searchType==_firstVal)
		{
			divSrchName.style.display = "block";
			divSrchNameImg.style.display = "block";
			divSrchID.style.display = "none";
			divSrchIDImg.style.display = "none";
			divSrchOpt.style.display = "block";
		}
		else
		{
			divSrchName.style.display = "none";
			divSrchNameImg.style.display = "none";
			divSrchID.style.display = "block";
			divSrchIDImg.style.display = "block";
			divSrchOpt.style.display = "none";
		}

		objEngine.objEngineFrameSet.setFrameSizeOnAdvSearchType(_searchType);
	}
}

// Toggling Disease Type Main & Sub
function toggleDiseaseType(_val)
{
	var radios = document.getElementsByName("radDisMainType");
	var rad = null;
	for(var i=0; i<radios.length; i++)
		if(radios[i].checked)
		{
			rad = radios[i];
			break;
		}
	if(rad != null)
	{
		var divMainDisType = document.getElementById("divDisTypeMain");
		var divSubDisType = document.getElementById("divDisTypeSub");
		var cboDisMainType = document.getElementsByName("strMainDiseaseTypeCode")[0];
		var cboDisSubType = document.getElementsByName("strDiseaseTypeCode")[0];
		if(rad.value==_val)
		{
			divMainDisType.style.display = "block";
			divSubDisType.style.display = "none";
			cboDisSubType.value = "-1";
		}
		else
		{
			divMainDisType.style.display = "none";
			divSubDisType.style.display = "block";
			cboDisMainType.value = "-1";
		}
	}
}

function activateTab(_newOption)
{
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);
	if(objEngine)
	{
		document.getElementsByName("strCurOption")[0].value = _newOption;
		objEngine.setChangeCurrentOption(_newOption);
		// Submitting Header
		submitFrame("NEW");
	}
}

function submitFrame(_mode)
{
	document.getElementsByName("strMainMode")[0].value = _mode;
	document.forms[0].submit();
}

// Setting on selection of index term drop down
var elemIndexTermList = null;
var elemIndexTermSearchTxt = null;
function setCorrIndexTermID(elemTarget)
{
	var elemName = elemTarget;
	var elemCode = document.getElementsByName("strIndexCode")[0];
	var elemFlag = document.getElementsByName("strAutoCompleteString")[0];

	if(elemTarget.SELECTED_VALUE!=null)
	{
		elemCode.value = elemTarget.SELECTED_VALUE;
		elemFlag.value = "1";
	}
	else
	{
		elemCode.value = "";
		elemFlag.value = "0";
	}
}

function setSerachDropDownLists()
{
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);
	if(typeof objEngine=='undefined')	return false;
	var elem = objEngine.objEngineFrameSet.getIndexTermElem("cboMainIndexTermList");
	if(elem == null)
	{
		elemIndexTermList = null;
		return false;
	}
	else
	{
		elemIndexTermList = elem;
		return true;
	}
}

function checkIsSerachVolume3()
{
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);
	if(typeof objEngine=='undefined')	return false;
	var elemSr = objEngine.objEngineFrameSet.getIndexTermSearchElem("strIndexSearchString");
	if(elemSr == null)
	{
		elemIndexTermSearchTxt = null;
		return false;
	}
	else
	{
		elemIndexTermSearchTxt = elemSr;
		return true;
	}
}

// Toggling Visibilty of Sub Tree
function toggleViewTree(_mode, _indexCode)
{
	var divTree = document.getElementById("divTree"+PragyaDesigner.trim(_indexCode));
	var divSeeAlso = document.getElementById("divSeeAlso"+PragyaDesigner.trim(_indexCode));
	var imgTree = document.getElementById("imgTree"+PragyaDesigner.trim(_indexCode));
	if(divTree.style.display == "none")
		showHideTree(true, divTree, divSeeAlso, imgTree);
	else
		showHideTree(false, divTree, divSeeAlso, imgTree);
	if(divTree.innerHTML == "")
	{
		setAJAXLoading(divTree);
		getIndexTermTree(_mode, _indexCode);
	}
}

function showHideTree(_flg, _divTree, _divSeeAlso, _imgTree)
{
	if(_flg)
	{
		_divTree.style.display = "block";
		_divSeeAlso.style.display = "block";
		_imgTree.src = "/HIS/hisglobal/images/Mi_Green_Sqr.png";
	}	
	else
	{
		_divTree.style.display = "none";
		_divSeeAlso.style.display = "none";
		_imgTree.src = "/HIS/hisglobal/images/Pl_Green_Sqr.png";
	}
}

// See & see Also Terms
function viewIndexTree(_indexCode, _indexModifierCode)
{
	//alert("This is in process....");
	var objEngine = ICDSearchEngine.getICDSearchEngine(document);

	if(validateSearch(objEngine.strCurOption,_srchType))
	{		
		//alert(_srchType);
		document.getElementsByName("strSearchType")[0].value = _srchType;
		document.getElementsByName("strSearchStart")[0].value = "true";
		objEngine.initiateSearch(_srchType);
		if(objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1)
			viewSearchAdvanced(false);
	}	
}

// Toggling Visibilty of Sub Tree
function toggleViewFurtherTree(_mode, _indexCode, _modifierCode)
{
	//alert("In Process");
	var divTree = document.getElementById("divTree"+PragyaDesigner.trim(_modifierCode));
	var imgTree = document.getElementById("imgTree"+PragyaDesigner.trim(_modifierCode));
	if(divTree.style.display == "none")
		showHideSubTree(true, divTree, imgTree);
	else
		showHideSubTree(false, divTree, imgTree);
	if(divTree.innerHTML == "")
	{
		setAJAXLoading(divTree);
		getModofierTermTree(_mode, _indexCode, _modifierCode);
	}
}

function showHideSubTree(_flg, _divTree, _imgTree)
{
	if(_flg)
	{
		_divTree.style.display = "block";
		_imgTree.src = "/HIS/hisglobal/images/Mi_Green_Sqr.png";
	}	
	else
	{
		_divTree.style.display = "none";
		_imgTree.src = "/HIS/hisglobal/images/Pl_Green_Sqr.png";
	}
}

// Setting Reports Criteria
function setCriteria()
{
	var opts = document.getElementsByName("strReportOption");
	var selectedOption = -1;
	for(var i=0; i<opts.length; i++)
		if(opts[i].checked)
		{
			selectedOption = parseInt(opts[i].value);
			break;
		}
	if(selectedOption!=-1)
	{
		var divCriMain = document.getElementById("divReportsCriteria");
		var divCriDisease = document.getElementById("divDiseaseRpt");
		var divCriSubDisease = document.getElementById("divSubDiseaseRpt");
		var cboSubGroup = document.getElementsByName("strIcdSubGroupCode")[0];
		var cboDisease = document.getElementsByName("strIcdDiseaseCode")[0];
		if(selectedOption>1)
		{
			divCriMain.style.display = "block";
			if(selectedOption>2)
			{
				divCriDisease.style.display = "block";
				if(selectedOption>3)
					divCriSubDisease.style.display = "block";
				else
					divCriSubDisease.style.display = "none";
			}
			else
			{
				divCriDisease.style.display = "none";
				divCriSubDisease.style.display = "none";				
			} 
			
		}
		else
			divCriMain.style.display = "none";
		//doEmptyAllCombo(cboSubGroup);
		//doEmptyAllCombo(cboDisease);
	}
}

// Emptying a Combo
function doEmptyAllCombo(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var opt=document.createElement("option");
		opt.value="0";
		opt.innerHTML="All";
		cbo.appendChild(opt);
	}
}

function populateSubGroups(mode)
{
	var opts = document.getElementsByName("strReportOption");
	var selectedOption = -1;
	for(var i=0; i<opts.length; i++)
		if(opts[i].checked)
		{
			selectedOption = parseInt(opts[i].value);
			break;
		}
	var cboGroup = document.getElementsByName("strIcdGroupCode")[0];
	var cboSubGroup = document.getElementsByName("strIcdSubGroupCode")[0];
	var cboDisease = document.getElementsByName("strIcdDiseaseCode")[0];
	//alert(selectedOption);
	//alert(cboGroup.value)
	if(cboGroup.value!="0")
		getAllIcdSubGroupList(mode,cboGroup.value);
	else
	{
		doEmptyAllCombo(cboSubGroup);
		doEmptyAllCombo(cboDisease);
	}
}

function populateDiseases(mode)
{
	var opts = document.getElementsByName("strReportOption");
	var selectedOption = -1;
	for(var i=0; i<opts.length; i++)
		if(opts[i].checked)
		{
			selectedOption = parseInt(opts[i].value);
			break;
		}
	var cboGroup = document.getElementsByName("strIcdGroupCode")[0];
	var cboSubGroup = document.getElementsByName("strIcdSubGroupCode")[0];
	var cboDisease = document.getElementsByName("strIcdDiseaseCode")[0];
	//alert(selectedOption);
	//alert(cboGroup.value)
	if(cboSubGroup.value!="0")
		getAllIcdDiseaseList(mode,cboSubGroup.value);
	else
		doEmptyAllCombo(cboDisease);
}

function validateReportCriteria()
{
	var opts = document.getElementsByName("strReportOption");
	var selectedOption = -1;
	for(var i=0; i<opts.length; i++)
		if(opts[i].checked)
		{
			selectedOption = parseInt(opts[i].value);
			break;
		}
	var cboGroup = document.getElementsByName("strIcdGroupCode")[0];
	var cboSubGroup = document.getElementsByName("strIcdSubGroupCode")[0];
	var cboDisease = document.getElementsByName("strIcdDiseaseCode")[0];
	
	if(document.getElementsByName("strReportType")[0].value=="-1")
	{
		alert("Please Select either Acrobat Reader or HTML");
		document.getElementsByName("strReportType")[0].focus();
		return false;
	}
	return true;
}

function generateReport()
{
	if(!validateReportCriteria())
		return false;
	//alert("Here to generate Report");
	
	var opts = document.getElementsByName("strReportOption");
	var selectedOption = -1;
	for(var i=0; i<opts.length; i++)
		if(opts[i].checked)
		{
			selectedOption = parseInt(opts[i].value);
			break;
		}
	var strReportType = document.getElementsByName("strReportType")[0].value;
	var strIcdGroupCode = document.getElementsByName("strIcdGroupCode")[0].value;
	var strIcdSubGroupCode = document.getElementsByName("strIcdSubGroupCode")[0].value;
	var strIcdDiseaseCode = document.getElementsByName("strIcdDiseaseCode")[0].value;
	
	var url="/HISClinical/opd/icdsearchengine/icdEngineSearching.cnt?strMainMode=REPORT&strReportType=" + strReportType
		+ "&strReportOption=" + selectedOption + "&strIcdGroupCode=" + strIcdGroupCode + "&strIcdSubGroupCode=" + strIcdSubGroupCode
		+ "&strIcdDiseaseCode=" + strIcdDiseaseCode;
		
	window.open(createFHashAjaxQuery(url),'report','scrollbars=yes,height=900,width=900');
}
