var elemICDList = null;
function getICDDiseaseCodeList()
{
	if(elemICDList==null)
	{
		var deskFrame = parent.document.getElementById("frameFooter");
		var doc="";
		if(window.XMLHttpRequest)
			doc=deskFrame.contentDocument;
		else if (window.ActiveXObject)
			doc=deskFrame.Document;
		elemICDList = doc.getElementsByName('diseaseCodeList')[0];
	}
	return elemICDList;
}

function validateSearch(sz)
{
	if(trimData(document.getElementsByName("patCrNo")[0].value)!="")
		if(!validateCRNoCHECK(sz))
			return false;
	return true;
}

function showLegends()
{
	document.getElementById("divLegends").style.display="block"; 
}

function showLegendsNone()
{
	document.getElementById("divLegends").style.display="none";
}

function getOrderBy(mode, order)
{
	if(!validateAddDiagnosis())	return;
	var frm= document.forms[0];
	frm.orderBy.value=order;
	submitForm(mode);
}

function getRoom(unit) // Populate Room 
{
	var elemRoom = document.getElementsByName("roomCode")[0];
	if(unit.value!='-1')
	{
		var roomList = getUnitRoomList(unit.value.split("@")[0]);
		if(roomList!=null)
		{
			var roomCode = document.getElementsByName("hiddenRoomCode")[0].value;
			elemRoom.innerHTML = "";
			var op=document.createElement("option");
			op.value="-1";
			op.innerHTML="Select Value";
			elemRoom.appendChild(op);
			
			for(var i=0; i<roomList.length; i++)
			{
				var op=document.createElement("option");
				op.value=roomList[i].roomCode;
				op.innerHTML=roomList[i].roomName;
				elemRoom.appendChild(op);
			}
			elemRoom.value = roomCode;
		}
		else
			PragyaDesigner.clearCombo(elemRoom);
	}
	else
	{
		PragyaDesigner.clearCombo(elemRoom);
	}
}

function doPagination(e,p)
{
	if(!validateAddDiagnosis())	return;
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName("hmode")[0].value = "PAGINATION";
	document.forms[0].submit();
}

function getScanDocPopup(flg, patCRNo, episodeCode, episodeVisitNo, episodeVisitDocDate)
{
	var tdScannedDocs = document.getElementById("tdScannedDocs");
	var divScannedDocs = document.getElementById("divScannedDocs");
	tdScannedDocs.width = "50%"
	divScannedDocs.innerHTML="";
	if(flg)
	{
		try
		{
			var frame = PragyaDesigner.createElement("iframe",divScannedDocs);
			frame.id = "ifrmDoc";
			frame.width = 750;//frame.width = 950;
			frame.height = 400;
			frame.src = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&strDocumentDate="+episodeVisitDocDate+"&strDocTypeId=10&strIsPopupClose=0"; 
			//frame.src = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&strDocTypeId=10&strIsPopupClose=0"; 
			//"/HISClinical/scanning/viewDocWithFTP?strFileName="+objDoc.strDocumentId+"&strFileExt="+objDoc.strFileExt;--------------------
		}
		catch(e)
		{
			alert(e);
			alert("Scan Document not Avalaible!!!!");
			divScannedDocs.innerHTML ="";
		}
		
		/*var url = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&strDocTypeId=10";
		var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=800,width=350,left=10,top=10');  
		child.moveTo(2,2);
		child.opener.focus();*/
	}
	else
	{
		alert("Scan Document not Avalaible!!!!");
		divScannedDocs.innerHTML="";
		tdScannedDocs.width = "0%"
		return false;
	}  	
}

function callThisOnload()
{
	// Enabling Pat Rows
	var chks = document.getElementsByName("selectedPatCrNo");
	for(var i=0; i<chks.length; i++)
		enablePatRow(chks[i], chks[i].value.split("#")[0], chks[i].value.split("#")[1], chks[i].value.split("#")[2], false);
		
	// Get Room
	if(document.getElementsByName("departmentUnitCode") && document.getElementsByName("departmentUnitCode")[0] 
		&& document.getElementsByName("departmentUnitCode")[0].value!="-1")
		getRoom(document.getElementsByName("departmentUnitCode")[0]);

	// ICD disease Code List
	/*var elemCodes = document.getElementsByName("diseaseCodeList")[0];
	// Fetch ICD Codes
	var lst = getICDCodesList();
	if(lst !=null)
	{
		for(var i=0; i<lst.length; i++)
		{
			var op=document.createElement("option");
			op.value=lst[i].strICDCode;
			op.innerHTML=lst[i].strICDCode;
			elemCodes.appendChild(op);
		}
	}*/
	
}



function setICDCodesLists(patCrNo, episodeCode, episodeVisitNo)
{
	var divICDShow = document.getElementById("divICD#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	var divICDDtl = document.getElementById("divICDHide#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	
	var elemICDDtl = divICDDtl.getElementsByTagName("input").selectedPatICD;
	var elemICDTxtLen = divICDDtl.getElementsByTagName("input").selectedPatICDTxtLen;
	
	var lenPresentICDCodes = 0; 
	var icdCodes = [];
	if(elemICDDtl.value!=null && elemICDDtl.value!='' && elemICDDtl.value!=' ')
	{
		icdCodes = elemICDDtl.value.split("#");
		lenPresentICDCodes = icdCodes.length;
	}
	
	divICDShow.innerHTML = "";
	var htmlCode = "";
	
	if(lenPresentICDCodes>3)
		elemICDTxtLen.value = lenPresentICDCodes;
	else
		elemICDTxtLen.value = 3;
		
	for (var i=0; i<elemICDTxtLen.value; i++)
	{
		htmlCode += "<input name='icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(i+1)+"' tabindex='1' maxlength='6' size='6' disabled='disabled' onkeypress='if(event.keyCode!=13) return validateAlphaNumericOnly(event,this); else return false;' onchange='updatePatICDDtl(\""+patCrNo+"\",\""+episodeCode+"\",\""+episodeVisitNo+"\")' />"
			+ "&nbsp;";
	}
	divICDShow.innerHTML = htmlCode;
	
	var elemDiseaseCodeList = getICDDiseaseCodeList();
	for (var i=0; i<elemICDTxtLen.value; i++)
	{
		var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(i+1);
		var elemTxtICD = document.getElementsByName(elemName)[0];
		if(i<lenPresentICDCodes)
		{
			elemTxtICD.value = icdCodes[i];
		}
		NewDropDownSearch.setup(elemTxtICD,elemDiseaseCodeList,NewDropDownSearch.SEARCH_TYPE["START"],true,true);
	}
}

function updatePatICDDtl(patCrNo, episodeCode, episodeVisitNo)
{
	var divICDShow = document.getElementById("divICD#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	var divICDDtl = document.getElementById("divICDHide#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	
	var elemICDDtl = divICDDtl.getElementsByTagName("input").selectedPatICD;
	var elemICDTxtLen = divICDDtl.getElementsByTagName("input").selectedPatICDTxtLen;
	
	var icdCodes = "";
	for (var i=0; i<elemICDTxtLen.value; i++)
	{
		var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(i+1);
		var elemTxtICD = document.getElementsByName(elemName)[0];
		if(elemTxtICD.value!="")
			icdCodes+=elemTxtICD.value+"#";
	}
	if(icdCodes!="")	icdCodes = icdCodes.substring(0,icdCodes.length-1);
	elemICDDtl.value = icdCodes;
}

function addMoreICD(patCrNo, episodeCode,episodeVisitNo)
{
	var divICDShow = document.getElementById("divICD#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	var divICDDtl = document.getElementById("divICDHide#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	
	var elemICDDtl = divICDDtl.getElementsByTagName("input").selectedPatICD;
	var elemICDTxtLen = divICDDtl.getElementsByTagName("input").selectedPatICDTxtLen;
	
	var lenPresentICDCodes = 0; 
	var icdCodes = [];
	if(elemICDDtl.value!=null && elemICDDtl.value!='' && elemICDDtl.value!=' ')
	{
		icdCodes = elemICDDtl.value.split("#");
		lenPresentICDCodes = icdCodes.length;
	}
	
	divICDShow.innerHTML = "";
	var htmlCode = "";
	elemICDTxtLen.value = parseInt(elemICDTxtLen.value) + 1;
	for (var i=0; i<elemICDTxtLen.value; i++)
	{
		htmlCode += "<input name='icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(i+1)+"' tabindex='1' maxlength='6' size='6' onkeypress='if(event.keyCode!=13) return validateAlphaNumericOnly(event,this); else return false;' onchange='updatePatICDDtl(\""+patCrNo+"\",\""+episodeCode+"\",\""+episodeVisitNo+"\")' />"
			+ "&nbsp;";
	}
	divICDShow.innerHTML = htmlCode;
	
	var elemDiseaseCodeList = getICDDiseaseCodeList();
	for (var i=0; i<elemICDTxtLen.value; i++)
	{
		var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(i+1);
		var elemTxtICD = document.getElementsByName(elemName)[0];
		NewDropDownSearch.setup(elemTxtICD,elemDiseaseCodeList,NewDropDownSearch.SEARCH_TYPE["START"],true,true);
		if(i<lenPresentICDCodes)
		{
			elemTxtICD.value = icdCodes[i];
		}
	}
}

function enablePatRow(chk, patCrNo, episodeCode, episodeVisitNo, flgChecked, episodeVisitDocDate)
{
	var divICDShow = document.getElementById("divICD#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	var divICDDtl = document.getElementById("divICDHide#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	var divICDPlus = document.getElementById("divICDPlus#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
	
	var elemICDDtl = divICDDtl.getElementsByTagName("input").selectedPatICD;
	var elemICDTxtLen = divICDDtl.getElementsByTagName("input").selectedPatICDTxtLen;
	var elemSeenPat = divICDDtl.getElementsByTagName("input").seenPat;
	
	for (var i=0; i<elemICDTxtLen.value; i++)
	{
		var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(i+1);
		var elemTxtICD = document.getElementsByName(elemName)[0];
		if(chk.checked)
		{
			elemICDDtl.disabled = false;
			elemTxtICD.disabled = false;
			divICDPlus.style.display = "block";
		}
		else
		{
			elemICDDtl.disabled = true;
			elemTxtICD.disabled = true;
			divICDPlus.style.display = "none";
		}
	}
	var divScannedDocs = document.getElementById("divScannedDocs");
	divScannedDocs.innerHTML = "";
	if(flgChecked && chk.checked)
	{
		var rowElemIndex = PragyaDesigner.findIndex(chk);
		var elemIsDoc = document.getElementsByName("patIsDocPresent")[rowElemIndex];
		if(elemIsDoc && elemIsDoc.value>0)
		{
			getScanDocPopup(true,patCrNo,episodeCode,episodeVisitNo,episodeVisitDocDate);
		}
		
		var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#1";
		document.getElementsByName(elemName)[0].focus();
	}
	if(chk.checked)	elemSeenPat.checked = true;
}


function validateDuplicateDiag()
{
	var chks = document.getElementsByName("selectedPatCrNo");
	for(var i=0; i<chks.length; i++)
	{
		if(chks[i].checked)
		{
			patCrNo = chks[i].value.split("#")[0];
			episodeCode = chks[i].value.split("#")[1];
			episodeVisitNo = chks[i].value.split("#")[2];
			var divICDDtl = document.getElementById("divICDHide#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
			
			var elemICDDtl = divICDDtl.getElementsByTagName("input").selectedPatICD;
			var lenPresentICDCodes = 0; 
			var icdCodes = [];
			icdCodes = elemICDDtl.value.split("#");
			lenPresentICDCodes = icdCodes.length;
			
			for (var j=0; j<(lenPresentICDCodes-1); j++)
				for(var k=j+1; k<lenPresentICDCodes; k++)
					if(icdCodes[j]==icdCodes[k])
					{
						alert("Duplicate Diagnosis Code :" + icdCodes[k]);
						var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#1";
						var elemTxtICD = document.getElementsByName(elemName)[0];
						elemTxtICD.focus();
						return false;
					}
			}
		}
	return true;
}

function validateAddDiagnosis()
{
	var chks = document.getElementsByName("selectedPatCrNo");
	for(var i=0; i<chks.length; i++)
		if(chks[i].checked)
		{
			patCrNo = chks[i].value.split("#")[0];
			episodeCode = chks[i].value.split("#")[1];
			episodeVisitNo = chks[i].value.split("#")[2];
			var divICDShow = document.getElementById("divICD#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
			var divICDDtl = document.getElementById("divICDHide#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo);
			
			var elemICDTxtLen = divICDDtl.getElementsByTagName("input").selectedPatICDTxtLen;
			var addedDiag = false;
			for (var j=0; j<elemICDTxtLen.value; j++)
			{
				var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#"+(j+1);
				var elemTxtICD = document.getElementsByName(elemName)[0];
				if(trimData(elemTxtICD.value)!="")
				{
					addedDiag = true;
					break;
				}
			}
			if(!addedDiag)
			{
				alert("Enter at least One ICD Code for Patient CR No. :'" +patCrNo+ "' !");
				var elemName = "icd#"+patCrNo+"#"+episodeCode+"#"+episodeVisitNo+"#1";
				document.getElementsByName(elemName)[0].focus();
				return false;
			}
		}
	if(!validateDuplicateDiag())	return false;
	return true;
}

function validateSave()
{
	if(!validateAddDiagnosis())	return false;
	
	var chks = document.getElementsByName("selectedPatCrNo");
	var toAdd = false;
	for(var i=0; i<chks.length; i++)
		if(chks[i].checked)
		{
			toAdd = true;
			break;
		}
		
	if(!toAdd)
		if(parseInt(document.getElementsByName("selectedPatLen")[0].value)==0)
		{
			alert("Add Diagnosis for at least one Patient!");
			return false;
		}
		else
		{
			if(!confirm("No Patient is selected from this Page!"))
				return false;
		}
	return true;
}



/*************************************** AJAX Functions ***************************/
// Gettting List of ICD Codes 
function getICDCodesList()
{
	var flg = false;
	var objICDCodeList = null;
	var _mode = "AJX_G_ICDCODES";
	var objXHR = {url: "/HISClinical/opd/icdEntryForm.cnt?hmode="+_mode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objICDCodeList = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objICDCodeList == 'undefined' || objICDCodeList==null || objICDCodeList=="" || objICDCodeList.length==0)
            	//alert("No Meal Time found for Meal Type");
            //alert(error+"Error while populating Meal Time Information");
            objICDCodeList = null;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return objICDCodeList;
}

// Gettting List of ICD Codes 
function getUnitRoomList(_unitCode)
{
	var flg = false;
	var objRoomList = null;
	var _mode = "AJX_G_ROOMS";
	var objXHR = {url: "/HISClinical/opd/icdEntryForm.cnt?hmode="+_mode+"&departmentUnitCode="+_unitCode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objRoomList = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objRoomList == 'undefined' || objRoomList==null || objRoomList=="" || objRoomList.length==0)
            	//alert("No Meal Time found for Meal Type");
            //alert(error+"Error while populating Meal Time Information");
            objRoomList = null;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return objRoomList;
}
