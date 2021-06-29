function addTab(title, url, deskMenuId, deskMenuType)
{
	if ($('#tt').tabs('exists', title))
	{
		$('#tt').tabs('select', title);
	} 
	else 
	{
		//alert("deskMenuId = "+deskMenuId);
		
		var deskPatInfo = "", targetURL = "";
		if(getPatInfoStr())	deskPatInfo = getPatInfoStr();
		
		if(url=='DESKPATLIST')
			targetURL = "/HISInvestigationG5/hisglobal/utility/dynamicdesk/list.cnt";
		else
			targetURL = "/HISInvestigationG5/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+url+"&deskMenuId="+deskMenuId+"&hmode=NEW"+deskPatInfo;
		alert(targetURL);
		
		var height1= (window.innerHeight-40)+'px';
		var content = '<iframe scrolling="auto" frameborder="0" src="'+targetURL+'" style="width:100%;height:'+height1+'; margin-top:2px;"></iframe>';
		
		
		if(deskMenuType=-1)
		{
		    $('#tt').tabs('add',{
				title:title,
				content:content,
				border:true,
				fit:true,
			    tabPosition:bottom,
				selected:1, // set 0 if focus should not go to the new opened tab
			});
		}
		else
		{
		    $('#tt').tabs('add',{
				title:title,
				content:content,
				border:true,
				closable:true,
				fit:true,
			    tabPosition:bottom,
				selected:1, // set 0 if focus should not go to the new opened tab
			});
		}
		
	    
		$('#tt').tabs({
			onSelect:function(title)
			{
				var tab = $('#tt').tabs('getSelected');
				//tab.panel('refresh');
			}
		
		});
		
		$('#tt').tabs('select', title);
		
	}
}













function showDeskDetailFrame(dtlData)
{
	parent.document.getElementById("frmDynamicDeskList").height = "0px";
	parent.document.getElementById("frmDynamicDeskNonPatientCentric").height = "0px";
	parent.document.getElementById('frmDynamicDeskPatientCentric').src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/deskDetail.cnt"+dtlData;
	parent.document.getElementById("frmDynamicDeskPatientCentric").height = "440px";
}

/*
function submitToDesk(mode,hmode)
{
	document.forms[0].mode.value=mode;
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}
*/
function submitToDesk(mode,hmode)
{
	//alert("A--");
	if(goToDashBoard)	goToDashBoard();
		
	if(parent.gotToMainTab)
		parent.gotToMainTab("Patient Dashboard", true);
	else if(parent.parent.gotToMainTab)
		parent.parent.gotToMainTab("Patient Dashboard", false);
	
	/*if(parent.updateTab)
	{
		closeDialog(parent.diag);
		parent.updateTab();
	}
	else
	{
		document.forms[0].mode.value=mode;
		document.forms[0].hmode.value=hmode;
		document.forms[0].submit();
	}*/
}

function closeDialog(diag)
{
	if(diag!=null)
	{
		//diag.destroyRecursive();
		diag.hide();
	}
}












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
	//alert("menu clicked");
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