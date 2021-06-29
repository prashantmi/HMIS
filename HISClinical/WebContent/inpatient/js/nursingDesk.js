// Non-Patient Centric Menu URLs
var arrNonPatientCentricMenuURL = ["DOCWARDROUNDDTL","PENDINGTASKLIST","WARDENQUIRY","NURSEROLEDTL","PACKINGLISTGENERATION"];

// Function Call from Dynamic Desk on Click on Menu
function submitFormOnValidateButton(event, _menuURL, _menuId, _islogin, _dutyRoleId)
{	
	var deskFrame=parent.document.getElementById("frmDynamicDeskCenter");
	var doc="";
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	
	var flag=false;

	var frm=doc.forms[0];
	if(frm.patCrNo)
	{
		var crNoArrayLength=frm.patCrNo.length;
		
		var selectedCRNo = null;
		if(frm.crNoSelected.value!="")	selectedCRNo = frm.crNoSelected.value;
		var crNOElement = null;
		if(typeof frm.patCrNo.tagName != 'undefined' && frm.patCrNo.tagName.toUpperCase()=="INPUT")
		{
			// If Direct Open
			if(isMenuNonPatientCentric(_menuURL))		{	flag=true;	}
			// Single Patient CR No in List and Checked
			if(frm.patCrNo.type.toUpperCase()=="RADIO" && frm.patCrNo.checked)
			{
				flag=true;
				crNOElement=frm.patCrNo;
			}
			if(frm.patCrNo.type.toUpperCase()=="HIDDEN" && frm.patCrNo.value!=null && frm.patCrNo.value!="")
			{
				flag=true;
				crNOElement=frm.patCrNo;
			}
		}
		else
		{
			for(var i=0;i<crNoArrayLength; i++)
			{
				// If Direct Open
				if(isMenuNonPatientCentric(_menuURL))		{	flag=true;	break;	}
				if(typeof frm.patCrNo[i].tagName != 'undefined' && frm.patCrNo[i].tagName.toUpperCase()=="INPUT")
				{
					if(frm.patCrNo[i].type.toUpperCase()=="RADIO" && frm.patCrNo[i].checked)
					{
						flag=true;
						crNOElement=frm.patCrNo[i];
						break;
					}
					if(frm.patCrNo[i].type.toUpperCase()=="HIDDEN" && frm.patCrNo[i].value!=null && frm.patCrNo[i].value!="")
					{
						flag=true;
						crNOElement=frm.patCrNo[i];
						break;
					}
				}
			}
		}
	}
	else
		return;
	
	if(flag)
	{
		frm.mode.value=_menuURL;
		frm.hmode.value="NEW";
		frm.deskMenuId.value=_menuId;
		
		frm.menuMode.value=_menuURL;	// Target Menu Mode
		frm.menuId.value=_menuId;	// Desk Menu Id

		activateSelectedCRNO(crNOElement,frm);
		unclickAllMenus();
		clickMenu(frm.mode.value, frm.deskMenuId.value);

		if(_islogin=="1")
			enableBlanket(_dutyRoleId);
		else	
		{
			frm.enableBlanket.value="NO"; 
			frm.submit();
		}				
	}
	else
	{
		alert("Please Select Patient From List");
		unclickAllMenus();
		clickMenu(frm.mode.value, frm.deskMenuId.value);
	}
}

// Non Patient-Centric Menus Check
function isMenuNonPatientCentric(_url)
{
	var flag = false;
	try
	{
		if(arrNonPatientCentricMenuURL && arrNonPatientCentricMenuURL!=null)
			for(var i=0; i<arrNonPatientCentricMenuURL.length; i++)
				if(arrNonPatientCentricMenuURL[i] == _url)
				{
					flag = true;
					break;
				}
	}
	catch(e)
	{
		flag = false;
	}
	return flag;
}

// Setting Cr No Selection
function activateSelectedCRNO(_elemCrNo,_frm)
{	
	var selectedCRNo = null;	
	if(_frm.crNoSelected.value!="")	selectedCRNo = _frm.crNoSelected.value;
	
	if(_elemCrNo!=null)
		_frm.crNoSelected.value=_elemCrNo.value;

	if(selectedCRNo==null || (_elemCrNo!=null && selectedCRNo!=_elemCrNo.value))
		activitiesOnChangeCrNo();
}

function activitiesOnChangeCrNo()
{
	// For Consent Inbox callForConsnets()
	var headerFrame=parent.document.getElementById("frmDynamicDeskHeader");
	/*if(typeof headerFrame.contentWindow.callThisOnLoadConsentInbox != 'undefined')
		headerFrame.contentWindow.callThisOnLoadConsentInbox();*/
	if(typeof headerFrame.contentWindow.sendConsentInboxData != 'undefined')
		headerFrame.contentWindow.sendConsentInboxData();
}

// Check For On Select Desk List Item 
function checkForOnSelectDeskListItem(obj, evnt)
{	
	var deskFrame=parent.document.getElementById("frmDynamicDeskCenter");
	var doc="";
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	var frm=doc.forms[0];
	
	if(obj==null)
	{
		var headerFrame=parent.document.getElementById("frmDynamicDeskHeader");
		if(typeof headerFrame.contentWindow.setDeskMenuColorsOnNursingData != 'undefined')
			headerFrame.contentWindow.setDeskMenuColorsOnNursingData();	
		return;		
	}

	var selectedCRNo = null;
	if(frm.crNoSelected.value!="")	selectedCRNo = frm.crNoSelected.value;

	if(obj.checked)
	{
		activateSelectedCRNO(obj,frm);
		var headerFrame=parent.document.getElementById("frmDynamicDeskHeader");
		if(typeof headerFrame.contentWindow.setDeskMenuColorsOnNursingData != 'undefined')
			headerFrame.contentWindow.setDeskMenuColorsOnNursingData();	
	}
}	

// For Ordering in Patient List
function getOrderBy(mode,order)
{
	var frm=document.forms[0];
	frm.orderBy.value=order;
	submitToDesk("NEW",mode);
}

// Call Function onload 
function callThisOnload()
{
	var frmCenter=parent.document.getElementById("frmDynamicDeskHeader");
	var frmDoc="";	
	if(window.XMLHttpRequest) // Mozilla
	{
 		frmDoc=frmCenter.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 		frmDoc=frmCenter.Document;
	}
	if(frmCenter.contentWindow.callThisOnload)
		frmCenter.contentWindow.callThisOnload();
	else if(frmDoc.callThisOnload)
		frmDoc.callThisOnload();
	else if(frmDoc.forms[0] && frmDoc.forms[0].callThisOnload)
		frmDoc.forms[0].callThisOnload();
}

function enableBlanket(_dutyRoleId)
{
	document.getElementsByName("enableBlanket")[0].value="YES";
	document.forms[0].mode.value="VALIDATEUSER";
 	document.forms[0].hmode.value="NEW";
 	document.forms[0].transactionMode.value="NEW";
 	document.getElementsByName("roleId")[0].value=_dutyRoleId;
 	document.forms[0].submit();
}

function cancel(e)
{
	if(e.type=="click" || e.keyCode==13)
	{
		if(window.XMLHttpRequest) // Mozilla
		{
			parent.document.getElementById("frmDynamicDeskCenter").contentDocument.getElementById('blanket').style.display="none";
			parent.document.getElementById("frmDynamicDeskCenter").contentDocument.getElementById('userIdDiv').style.display="none";
		}
		else //if (window.ActiveXObject)
		{
			parent.document.getElementById("frmDynamicDeskCenter").Document.getElementById('blanket').style.display="none";
			parent.document.getElementById("frmDynamicDeskCenter").Document.getElementById('userIdDiv').style.display="none";
		}	
	}
}



function finalSubmit(e)
{
	if(e.type=="click" || e.keyCode==13)
 	{
		//alert("hi");
		if(document.getElementById("userName").value=="-1")
		{
			alert("Please Select User Id");
 			document.getElementById("userName").focus();
		}
		else
		{			
			var hashValue = "";
			var pwd=document.getElementById("userName");
		//	alert(document.getElementById("password").value);
			//alert(pwd.options[pwd.selectedIndex].text);
			//var objPassHash = new jsSHA(document.getElementById("password").value+document.getElementById("userName").value, "ASCII");
			var objPassHash = new jsSHA(document.getElementById("password").value+pwd.options[pwd.selectedIndex].text, "ASCII");
			//alert(objPassHash);
			try 
			{
				hashValue = objPassHash.getHash("SHA-1", "HEX");
				//alert(hashValue);
				document.getElementById("password").value = hashValue;
			} 
			catch(e)
			{
				alert(e);
				return;
			}
			
			//alert(hashvalue);
			//document.getElementsByName("varConfirmPassword")[0].value = hashValue;
			//return true;
			//alert("GO");
			/*document.forms[0].mode.value="VALIDATEUSER";
		 	//document.forms[0].transactionMode.value="VALIDATEUSER";
		 	document.forms[0].hmode.value="VALIDATEUSER";
			*/
			//document.getElementsByName("mode")[0].value = "VALIDATEUSER";
			document.getElementsByName("hmode")[0].value = "VALIDATEUSER";
			document.forms[0].submit();
		}
	}
}



