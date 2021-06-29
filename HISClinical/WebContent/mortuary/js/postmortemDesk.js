// Non-Patient Centric Menu URLs
var arrNonPatientCentricMenuURL = [];

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
	if(frm.postmortemId)
	{
		var postmortemIdArrayLength=frm.postmortemId.length;
	
		var selectedPostmortemId = null;
		if(frm.postmortemIdSelected.value!="")	selectedPostmortemId = frm.postmortemIdSelected.value;
		var postmortemIdElement = null;
		if(typeof frm.postmortemId.tagName != 'undefined' && frm.postmortemId.tagName.toUpperCase()=="INPUT")
		{
			// If Direct Open
			if(isMenuNonPatientCentric(_menuURL))		{	flag=true;	}
			// Single Patient CR No in List and Checked
			if(frm.postmortemId.type.toUpperCase()=="RADIO" && frm.postmortemId.checked)
			{
				flag=true;
				postmortemIdElement=frm.postmortemId;
			}
			if(frm.postmortemId.type.toUpperCase()=="HIDDEN" && frm.postmortemId.value!=null && frm.postmortemId.value!="")
			{
				flag=true;
				postmortemIdElement=frm.postmortemId;
			}
		}
		else
		{
			for(var i=0;i<postmortemIdArrayLength; i++)
			{
				// If Direct Open
				if(isMenuNonPatientCentric(_menuURL))		{	flag=true;	break;	}
				if(typeof frm.postmortemId[i].tagName != 'undefined' && frm.postmortemId[i].tagName.toUpperCase()=="INPUT")
				{
					if(frm.postmortemId[i].type.toUpperCase()=="RADIO" && frm.postmortemId[i].checked)
					{
						flag=true;
						postmortemIdElement=frm.postmortemId[i];
						break;
					}
					if(frm.postmortemId[i].type.toUpperCase()=="HIDDEN" && frm.postmortemId[i].value!=null && frm.postmortemId[i].value!="")
					{
						flag=true;
						postmortemIdElement=frm.postmortemId[i];
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

		activateSelectedPostmortemId(postmortemIdElement,frm);
		unclickAllMenus();
		clickMenu(frm.mode.value, frm.deskMenuId.value);
		frm.submit();
	}
	else
	{
		alert("Please Select Postmortem Request From The List");
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

// Setting Postmortem Id Selection
function activateSelectedPostmortemId(_elemPostmortemId,_frm)
{	
	var selectedPostmortemId = null;
	if(_frm.postmortemIdSelected.value!="")	selectedPostmortemId = _frm.postmortemIdSelected.value;

	if(_elemPostmortemId!=null)
		_frm.postmortemIdSelected.value=_elemPostmortemId.value;

	if(selectedPostmortemId==null || (_elemPostmortemId!=null && selectedPostmortemId!=_elemPostmortemId.value))
		activitiesOnChangePostmortemId();
}

function activitiesOnChangePostmortemId()
{
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
	var selectedPostmortemId = null;
	if(frm.postmortemIdSelected.value!="")	selectedPostmortemId = frm.postmortemIdSelected.value;

	if(obj.checked)
	{
		activateSelectedPostmortemId(obj,frm);
	}
}	

// For Ordering in Postmortem List
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