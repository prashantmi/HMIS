// Non-Patient Centric Menu URLs
var arrNonPatientCentricMenuURL = ["CONSULTATIONINBOX","AUDIOVIDEOPLAYER"];

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

		activateSelectedCRNO(crNOElement,frm);
		unclickAllMenus();
		clickMenu(frm.mode.value, frm.deskMenuId.value);
		frm.submit();
	}
	else
	{
		alert("Please Select Patient from List");
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

	//if(selectedCRNo==null || (_elemCrNo!=null && selectedCRNo!=_elemCrNo.value))
		//activitiesOnChangeCrNo();
}

function activitiesOnChangeCrNo()
{
	// For Consent Inbox callForConsnets()
	var headerFrame=parent.document.getElementById("frmDynamicDeskHeader");
	/*if(typeof headerFrame.contentWindow.callThisOnLoadConsentInbox != 'undefined')
		headerFrame.contentWindow.callThisOnLoadConsentInbox();*/
	if(typeof headerFrame.contentWindow.sendConsentInboxData != 'undefined')
		headerFrame.contentWindow.sendConsentInboxData();
	
	// Call Visit Summary Tab
	//submitFormOnValidateButton(window.event,"OPDNEXTVISITDETAIL","","0",null);
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
	var selectedCRNo = null;
	if(frm.crNoSelected.value!="")	selectedCRNo = frm.crNoSelected.value;
	
	if(obj.checked)
	{
		if(selectedCRNo!=null)
		{	
			var patArray=document.getElementsByName("patCrNo");
			var i=0;
			var prevCRNoElement=null;
			while(i<patArray.length)
			{
				if(patArray[i].value==selectedCRNo)
				{
					prevCRNoElement=patArray[i];
					break;
				}
				i=i+1;
			}
			if(prevCRNoElement!=null)
			{
				// Episosode Close Option Call on Click of Patient Row Radio Button 
				var isConfirmed = document.getElementsByName(prevCRNoElement.value)[0].value;
				if(selectedCRNo!=obj.value && isConfirmed == "false")
				{
					//alert("Please Give Next Visit Date & Visit Summary to the Patient of CR No. "+prevCRNoElement.value);
					/*if(confirm("Please Give Next Visit Date & Visit Summary to the Patient of CR No. "+prevCRNoElement.value))
					{
						prevCRNoElement.checked=true;
						submitFormOnValidateButton(evnt,"OPDNEXTVISITDETAIL","","0",null);
					}
					else*/
						activateSelectedCRNO(obj,frm);
				}
				else
					activateSelectedCRNO(obj,frm);
			}
			else
				activateSelectedCRNO(obj,frm);
		}
		else
			activateSelectedCRNO(obj,frm);
	}
}	

// On Change of Page Choice
function submitOnChangePageChoice(_hmode)
{
	var frm=document.forms[0];
	submitToDesk("NEW",_hmode);
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