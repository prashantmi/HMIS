function submitDesk(mode)
{
	document.forms[0].mode.value=mode;
	document.forms[0].submit();
}

function submitFormOnValidateButton(event, _menuURL, _menuId, _islogin, _dutyRoleId)
{
	var deskFrame=parent.document.getElementById("frmDynamicDeskCenter");
	var win=deskFrame.contentWindow;
	win.submitFormOnValidateButton(event, _menuURL, _menuId, _islogin, _dutyRoleId);
}

function submitToDesk(mode,hmode)
{
	unclickAllMenus();
	document.forms[0].mode.value=mode;
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}

function DynamicDeskButtonOnMouseOver(but)
{
	//alert("DynamicDeskButtonOnMouseOver");
	if(but.className=="tdDynamicDeskButtonNormal")
		but.className = "tdDynamicDeskButtonFocus";
}

function DynamicDeskButtonOnMouseOut(but)
{
	//alert("DynamicDeskButtonOnMouseUp");
	if(but.className=="tdDynamicDeskButtonFocus" || but.className=="tdDynamicDeskButtonPressed")
		but.className="tdDynamicDeskButtonNormal";
}

function DynamicDeskButtonOnMouseDown(but)
{
	//alert("DynamicDeskButtonOnMouseDown");
	but.className="tdDynamicDeskButtonPressed";
}

function DynamicDeskButtonOnMouseUp(but)
{
	//alert("DynamicDeskButtonOnMouseUp");
	if(but.className=="tdDynamicDeskButtonPressed")
		but.className = "tdDynamicDeskButtonNormal";
}

function DynamicDeskButtonOnMouseClicked(but)
{
	//alert("DynamicDeskButtonOnMouseClicked");
	//unclickAllMenus();
	//but.className = "tdDynamicDeskButtonClicked";
}

function unclickAllMenus()
{
	// Searching in All Menus that are Clicked for Unclicking
	var frmLeft=parent.document.getElementById("frmDynamicDeskLeftMenus");
	var frmRight=parent.document.getElementById("frmDynamicDeskRightMenus");
	var frmTop=parent.document.getElementById("frmDynamicDeskTopMenus");
	var frmBottom=parent.document.getElementById("frmDynamicDeskBottomMenus");

	var formDocLeft= null;	
	var formDocRight= null;	
	var formDocTop= null;	
	var formDocBottom= null;
	
	if(window.XMLHttpRequest) // Mozilla
	{
		formDocLeft=frmLeft.contentDocument;
		formDocRight=frmRight.contentDocument;
		formDocTop=frmTop.contentDocument;
		formDocBottom=frmBottom.contentDocument;
 	} 
 	else if (window.ActiveXObject)
 	{
 		formDocLeft=frmLeft.Document;
 		formDocRight=frmRight.Document;
 		formDocTop=frmTop.Document;
 		formDocBottom=frmBottom.Document;
	}
	
	var menusTds = formDocLeft.getElementsByTagName("TD");
	for(var i=0; i<menusTds.length; i++)
		if(menusTds[i].className=="tdDynamicDeskButtonClicked")
			menusTds[i].className="tdDynamicDeskButtonNormal";

	menusTds = formDocRight.getElementsByTagName("TD");
	for(var i=0; i<menusTds.length; i++)
		if(menusTds[i].className=="tdDynamicDeskButtonClicked")
			menusTds[i].className="tdDynamicDeskButtonNormal";

	menusTds = formDocTop.getElementsByTagName("TD");
	for(var i=0; i<menusTds.length; i++)
		if(menusTds[i].className=="tdDynamicDeskButtonClicked")
			menusTds[i].className="tdDynamicDeskButtonNormal";

	menusTds = formDocBottom.getElementsByTagName("TD");
	for(var i=0; i<menusTds.length; i++)
		if(menusTds[i].className=="tdDynamicDeskButtonClicked")
			menusTds[i].className="tdDynamicDeskButtonNormal";
}

function clickMenu(_url, _menuId)
{
	if(_url!="")
	{
		// Searching Menu for Clicking
		var frmLeft=parent.document.getElementById("frmDynamicDeskLeftMenus");
		var frmRight=parent.document.getElementById("frmDynamicDeskRightMenus");
		var frmTop=parent.document.getElementById("frmDynamicDeskTopMenus");
		var frmBottom=parent.document.getElementById("frmDynamicDeskBottomMenus");
	
		var formDocLeft= null;	
		var formDocRight= null;	
		var formDocTop= null;	
		var formDocBottom= null;
		
		if(window.XMLHttpRequest) // Mozilla
		{
			formDocLeft=frmLeft.contentDocument;
			formDocRight=frmRight.contentDocument;
			formDocTop=frmTop.contentDocument;
			formDocBottom=frmBottom.contentDocument;
	 	} 
	 	else if (window.ActiveXObject)
	 	{
	 		formDocLeft=frmLeft.Document;
	 		formDocRight=frmRight.Document;
	 		formDocTop=frmTop.Document;
	 		formDocBottom=frmBottom.Document;
		}
		
		var menusTds = formDocLeft.getElementsByTagName("TD");
		for(var i=0; i<menusTds.length; i++)
			if(menusTds[i].id==_url && (_menuId=="" || menusTds[i].headers==_menuId) )// && menusTds[i].className=="tdDynamicDeskButtonClicked")
				menusTds[i].className="tdDynamicDeskButtonClicked";
	
		menusTds = formDocRight.getElementsByTagName("TD");
		for(var i=0; i<menusTds.length; i++)
			if(menusTds[i].id==_url && (_menuId=="" || menusTds[i].headers==_menuId))// && menusTds[i].className=="tdDynamicDeskButtonClicked")
				menusTds[i].className="tdDynamicDeskButtonClicked";
	
		menusTds = formDocTop.getElementsByTagName("TD");
		for(var i=0; i<menusTds.length; i++)
			if(menusTds[i].id==_url && (_menuId=="" || menusTds[i].headers==_menuId))// && menusTds[i].className=="tdDynamicDeskButtonClicked")
				menusTds[i].className="tdDynamicDeskButtonClicked";
	
		menusTds = formDocBottom.getElementsByTagName("TD");
		for(var i=0; i<menusTds.length; i++)
			if(menusTds[i].id==_url && (_menuId=="" || menusTds[i].headers==_menuId))// && menusTds[i].className=="tdDynamicDeskButtonClicked")
				menusTds[i].className="tdDynamicDeskButtonClicked";
	}
}

function showData(elem)
{
	var h="";
	for(var p in elem)
	{
		h+=p+"  :  "+elem[p]+"\n";
		if(h.length>500)
		{
			alert(h);
			h="";
		}
	}
	alert(h);
}