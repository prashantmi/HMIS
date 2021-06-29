//************ Toggling Style *************************************************************************

function mouseOverAnchor(_anc)
{
	_anc.className = "anchorFocus";
}

function mouseOutAnchor(_anc)
{
	_anc.className = "anchorNormal";
}


//**************************************************************************************************

//************  Popup ******************************************************************************

var childWindow = null;

function xyz()
{
	opener.populate();
}

function populate(x)
{
	//alert("override this function");
}

function callToPopulate(x)
{
    for(i=0;i<x.length;i++)
    {
	   // alert("x..........."+x[i]);
    }
	opener.populate(x);
}

function populateEmpDtl(x)
{    
	opener.poulateFieldsWithEmpDtl(x);
}

function openPopup(url, eventObj)
{
	if(eventObj.type=="click" || eventObj.keyCode==13)
	{
		childWindow = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=200,width=350,left=10,top=10');  
		childWindow.moveTo(250,250);
		childWindow.focus(); 
		if(!childWindow.opener)
			childWindow.opener = self;
	}
}

function openPopup(url, eventObj, height, width)
{
	if(eventObj.type=="click" || eventObj.keyCode==13)
	{
		childWindow = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');
		childWindow.moveTo(250,250);
		childWindow.focus(); 
		if(!childWindow.opener)	childWindow.opener = self;
	}
	return childWindow;
}

function openPopupWide(id, url, eventObj)
{
	if(eventObj.type=="click" || eventObj.keyCode==13)
	{   
		childWindow = window.open(url+"?empIdChk="+id,'popupWindow','status=yes,scrollbars=yes,height=500,width=750,left=10,top=10,alwaysRaised=true');  
		childWindow.focus();
		if (!childWindow.opener)
			childWindow.opener = self;
	}
}

function openDependentPopup(url, eventObj, height, width, resize)
{
	if(eventObj.type=="click" || eventObj.keyCode==13)
	{
		alert(height+width);
		childWindow = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10,dependent=yes,resizable='+resize+'');
		childWindow.moveTo(250,250);
		childWindow.focus();
		
		if(!childWindow.opener)
			childWindow.opener = self;
	}
	return childWindow;
}
//************  End Popup *************************************************************************
