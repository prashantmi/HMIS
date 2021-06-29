// Function Call from Dynamic Desk on Click on Menu
function submitFormOnValidateButton(event, _menuURL, _menuId, _islogin, _dutyRoleId)
{	
	var deskFrame=parent.document.getElementById("frmDynamicDeskCenter");
	var doc="";
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	var frm=doc.forms[0];

	frm.mode.value=_menuURL;
	frm.hmode.value="NEW";
	frm.deskMenuId.value=_menuId;
	unclickAllMenus();
	clickMenu(frm.mode.value, frm.deskMenuId.value);
	frm.submit();
}