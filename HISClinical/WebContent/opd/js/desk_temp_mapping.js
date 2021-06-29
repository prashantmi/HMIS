// Submit Form
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

// On Final Selection in Different Addition Modes 
function showTemplateByDesk(additionMode)
{
	if(additionMode==1)	// Desk-Wise Mode
	{
		if(document.getElementsByName("deskId")[0].value=="-1")
		{
			//alert("Please Select a Desk");
			hideAllShowed(true);
			document.getElementsByName("deskId")[0].focus();
			return;
		}
	}
	else if(additionMode==2)	// Unit-Wise Mode
	{
		if(document.getElementsByName("unitCode")[0].value=="-1")
		{
			//alert("Please Select a Mapped Unit");
			hideAllShowed(false);
			document.getElementsByName("unitCode")[0].focus();
			return;
		}
	}
	else if(additionMode==3)	// Unit Seat-Wise Mode
	{
		if(document.getElementsByName("userSeatId")[0].value=="-1")
		{
			//alert("Please Select a Mapped Seat");
			hideAllShowed(false);
			document.getElementsByName("userSeatId")[0].focus();
			return;
		}
	}
	else if(additionMode==4)	// Unit Ward-Wise Mode
	{
		if(document.getElementsByName("wardCode")[0].value=="-1")
		{
			//alert("Please Select a Mapped Ward");
			hideAllShowed(false);
			document.getElementsByName("wardCode")[0].focus();
			return;
		}
	}
	else if(additionMode==5)	// Unit Ward Seat-Wise Mode
	{
		if(document.getElementsByName("userSeatId")[0].value=="-1")
		{
			//alert("Please Select a Mapped Seat");
			hideAllShowed(false);
			document.getElementsByName("userSeatId")[0].focus();
			return;
		}
	}
	submitPage('TEMPLATEBYDESK');
}

// Hide Mapped Template View Part on Selection of Desk Id in Addition Mode Desk-Wise 
function hideAllShowed(_hideAddButton)
{
	if(document.getElementById('viewDisplay'))
		document.getElementById('viewDisplay').innerHTML="";
	if(_hideAddButton && document.getElementById('addButton'))
		document.getElementById('addButton').style.display="none";
	if(document.getElementById('modifyButton'))
		document.getElementById('modifyButton').style.display="none";
	if(document.getElementById('deleteButton'))
		document.getElementById('deleteButton').style.display="none";
}

// Emptying a Combo
function doEmptyCombo(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		cbo.appendChild(op);
	}
}

// On Selection of Desk in Unit-Wise Addition Mode
function getUnitInUnitWise()
{
	if(document.getElementsByName("deskId")[0].value=="-1")
	{
		//alert("Please Select a Desk");
		doEmptyCombo(document.getElementsByName("unitCode")[0]);
		hideAllShowed(true);
		document.getElementsByName("deskId")[0].focus();
		return;
	}
	submitPage("SHOWMAPPEDUNITS");
}

// On Selection of Desk in Unit Seat-Wise Addition Mode
function getUnitInUnitSeatWise()
{
	if(document.getElementsByName("deskId")[0].value=="-1")
	{
		//alert("Please Select a Desk");
		doEmptyCombo(document.getElementsByName("unitCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		hideAllShowed(true);
		document.getElementsByName("deskId")[0].focus();
		return;
	}
	submitPage("SHOWMAPPEDUNITSFORUNITSEAT");
}

// On Selection of Unit in Unit Seat-Wise Addition Mode
function getSeatByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		//alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETSEATBYUNIT");
}

// On Selection of Desk in Unit Ward-Wise Addition Mode
function getUnitInUnitWardWise()
{
	if(document.getElementsByName("deskId")[0].value=="-1")
	{
		//alert("Please Select a Desk");
		doEmptyCombo(document.getElementsByName("unitCode")[0]);
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		hideAllShowed(true);
		document.getElementsByName("deskId")[0].focus();
		return;
	}
	submitPage("SHOWMAPPEDUNITSFORWARD");
}

// On Selection of Unit in Unit Ward-Wise Addition Mode
function getWardByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		//alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETWARDBYUNIT");
}

// On Selection of Desk in Unit Ward Seat-Wise Addition Mode
function getUnitInUnitWardSeatWise()
{
	if(document.getElementsByName("deskId")[0].value=="-1")
	{
		//alert("Please Select a Desk");
		doEmptyCombo(document.getElementsByName("unitCode")[0]);
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		hideAllShowed(true);
		document.getElementsByName("deskId")[0].focus();
		return;
	}
	submitPage("SHOWMAPPEDUNITSFORUNITSEATWARD");
}

// On Selection of Unit in Unit Ward Seat-Wise Addition Mode
function getWardByUnitForUnitWardSeat()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		//alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETWARDBYUNITFORUWS");
}

// On Selection of Ward in Unit Ward Seat-Wise Addition Mode
function getSeatByUnitNWard()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		//alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("wardCode")[0].value=="-1")
	{
		//alert("Please Select a Ward");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		hideAllShowed(false);
		document.getElementsByName("wardCode")[0].focus();
		return;
	}
	submitPage("GETSEATBYUNITNWARD");
}

// Getting Template on Selection of Desk Menu
function getTemplate()
{
	if(document.getElementsByName("deskMenuId")[0].value=="-1")
	{
		doEmptyCombo(document.getElementsByName("templateId")[0]);
		return;
	}
	submitPage('GETTEMPLATE');
}

// Validate Adding Template in Row
function validateAdd()
{
	if(document.getElementsByName("deskMenuId")[0].value=="-1")
	{
		alert("Please Select the Desk Menu");
		document.getElementsByName("deskMenuId")[0].focus();		
		return false;
	}
	if(document.getElementsByName("templateId")[0].value=="-1")
	{
		alert("Please Select the Template");
		document.getElementsByName("templateId")[0].focus();
		return false;
	}
	return true;
}

// Deleting Added Template from Row
function deleteRow(_tempId)
{
	document.getElementsByName("hiddenTemplateId")[0].value=_tempId;
	submitPage('DELETEROW');
}
/*function deleteRowForModify(obj1,obj2)
{
	document.getElementsByName("templateName")[0].value=obj1;
	document.getElementsByName("hiddenTemplateId")[0].value=obj2;
	submitPage('DELETEROWFORMODIFY');
}*/

// Validate Before Going to Add Template in Addition Mode Desk-Wise
function validateAddOne()
{
	if(document.getElementsByName("deskType")[0].value=="-1")
	{
		alert("Please Select the Desk Type");
		document.getElementsByName("deskType")[0].focus();
		return false;
	}	
	if(document.getElementsByName("deskId")[0].value=="-1")
	{
		alert("Please Select the Desk");
		document.getElementsByName("deskId")[0].focus();
		return false;
	}
	return true;
}

// Before Deleting/Removing Mapped Template Ask to Confirm 
function askToDelete()
{
	if(confirm("Are you sure to remove Mapped Templates"))
		return true;
	return false;
}

// Validate Saving the Template Mapping
function validateSave()
{
	if(parseInt(document.getElementsByName("addedLength")[0].value)==0)
	{
		alert("Map at least one Template");
		document.getElementsByName("deskMenuId")[0].focus();		
		return false;
	}
	if(document.getElementsByName("deskMenuId")[0].value!="-1" || document.getElementsByName("templateId")[0].value!="-1")
	{
		if(document.getElementsByName("deskMenuId")[0].value=="-1")
		{
			alert("Please Select the Desk Menu");
			document.getElementsByName("deskMenuId")[0].focus();		
			return false;
		}
		if(document.getElementsByName("templateId")[0].value=="-1")
		{
			alert("Please Select the Template");
			document.getElementsByName("templateId")[0].focus();
			return false;
		}
	}
	return true;
}

// Setting Units On Selection of Department 
function deptSelected(cboDept)
{
	var deptId = cboDept.value;
	
	var elemMainUnitList = document.getElementsByName("mainUnitsList")[0];
	var elemUnitList = document.getElementsByName("unitsList")[0];
	
	// Adding Units that are not in Main List but in Unit List
	for(var i=0;i<elemUnitList.options.length;i++)
	{
		var op=document.createElement("option");
		op.value=elemUnitList.options[i].value;
		op.innerHTML=elemUnitList.options[i].text;
		elemMainUnitList.appendChild(op);
	}	
	// Clean Existing Units
	elemUnitList.innerHTML = "";

	if(deptId != "-1")
	{
		// Adding Units of Selected Department
		var toBeRemoved = "";
		for(var i=0;i<elemMainUnitList.options.length;i++)
		{
			var str = elemMainUnitList.options[i].value.substr(0,3);
			/*var deskType = document.getElementsByName("deskType")[0].value;
			var isCasualty = elemMainUnitList.options[i].value.split("@")[1];
			if(deskType=="2")
			{
				if(str==deptId && isCasualty=="3")
				{
					var op=document.createElement("option");
					op.value=elemMainUnitList.options[i].value;
					op.innerHTML=elemMainUnitList.options[i].text;
					elemUnitList.appendChild(op);
					toBeRemoved += i+",";
				}
			}
			else
			{*/
				if(str==deptId)
				{
					var op=document.createElement("option");
					op.value=elemMainUnitList.options[i].value;
					op.innerHTML=elemMainUnitList.options[i].text;
					elemUnitList.appendChild(op);
					toBeRemoved += i+",";
				}
			/*}*/	
		}
		if(toBeRemoved.length>0)
		{
			toBeRemoved = toBeRemoved.substring(0,toBeRemoved.length-1);
			var arr = toBeRemoved.split(",");
			for(var i=0;i<arr.length;i++)
			{
				elemMainUnitList.options[arr[i]-i]=null;
			}
		}	
	}
}

// Deleting from Source that in Target
function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

// Moving Single in Lists fromSource to toTarget 
function moveSingleInLists(_fromSource,_toTarget) //listNo)
{
	var source = _fromSource;
	var target = _toTarget;

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

// Moving All in Lists fromSource to toTarget
function moveAllInLists(_fromSource,_toTarget) //listNo)
{
	var source = _fromSource;
	var target = _toTarget;

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		val = source.options[i].value;
		txt = source.options[i].text;			
		
		targetlen = target.length;							
		target.length = (targetlen+1);				
			
		target.options[targetlen].value = val;
		target.options[targetlen].text  = txt;													
	}
	deleteThis(target,source);
}

// Valdating Unit Selection
function validateUnitSelection()
{
	/*if(document.getElementsByName("deptId")[0].value=="-1")
	{
		alert("Please Select the Department");
		document.getElementsByName("deptId")[0].focus();
		return false;
	}*/
	if(document.getElementsByName("selectedUnits")[0].options.length==0)
	{
		alert("Select at Least One Unit");
		document.getElementsByName("unitsList")[0].focus();
		return false;
	}
	MoveToSelected();
	return true;
}

// Select Required & Unselect Not Required
function MoveToSelected()
{
	// Unselect Remaining Units
	if(document.getElementsByName("unitsList")[0])
	{	
		for(var i=0;i<document.getElementsByName("unitsList")[0].options.length;i++)
			document.getElementsByName("unitsList")[0].options[i].selected=false;
	}
	// Select All Units in Selected
	if(document.getElementsByName("selectedUnits")[0])
	{
		for(var i=0;i<document.getElementsByName("selectedUnits")[0].options.length;i++)
			document.getElementsByName("selectedUnits")[0].options[i].selected=true;
	}
	
	// Unselect Remaining Seats
	if(document.getElementsByName("seatsList")[0])
	{
		for(var i=0;i<document.getElementsByName("seatsList")[0].options.length;i++)
			document.getElementsByName("seatsList")[0].options[i].selected=false;
	}
	// Select All Seats in Selected
	if(document.getElementsByName("selectedSeats")[0])
	{
		for(var i=0;i<document.getElementsByName("selectedSeats")[0].options.length;i++)
			document.getElementsByName("selectedSeats")[0].options[i].selected=true;
	}

	// Unselect Remaining Wards
	if(document.getElementsByName("wardsList")[0])
	{
		for(var i=0;i<document.getElementsByName("wardsList")[0].options.length;i++)
			document.getElementsByName("wardsList")[0].options[i].selected=false;
	}
	// Select All Wards in Selected
	if(document.getElementsByName("selectedWards")[0])
	{
		for(var i=0;i<document.getElementsByName("selectedWards")[0].options.length;i++)
			document.getElementsByName("selectedWards")[0].options[i].selected=true;
	}
}

// Setting Seats On Selection of Group 
function groupSelected(cboGroup)
{
	var groupId = cboGroup.value;
	
	var elemMainSeatList = document.getElementsByName("mainSeatsList")[0];
	var elemSeatList = document.getElementsByName("seatsList")[0];
	
	// Adding Seats that are not in Main List but in Seat List
	for(var i=0;i<elemSeatList.options.length;i++)
	{
		var op=document.createElement("option");
		op.value=elemSeatList.options[i].value;
		op.innerHTML=elemSeatList.options[i].text;
		elemMainSeatList.appendChild(op);
	}	
	// Clean Existing Seats
	elemSeatList.innerHTML = "";

	if(groupId != "-1")
	{
		// Adding Seats of Group
		var toBeRemoved = "";
		for(var i=0;i<elemMainSeatList.options.length;i++)
		{
			var str = elemMainSeatList.options[i].value.split("@")[1];
			if(str==groupId)
			{
				var op=document.createElement("option");
				op.value=elemMainSeatList.options[i].value;
				op.innerHTML=elemMainSeatList.options[i].text;
				elemSeatList.appendChild(op);
				toBeRemoved += i+",";
			}
		}
		if(toBeRemoved.length>0)
		{
			toBeRemoved = toBeRemoved.substring(0,toBeRemoved.length-1);
			var arr = toBeRemoved.split(",");
			for(var i=0;i<arr.length;i++)
			{
				elemMainSeatList.options[arr[i]-i]=null;
			}
		}	
	}
}

// Validate Seat Selection
function validateSeatSelection()
{	
	/*if(document.getElementsByName("group")[0].value=="-1")
	{
		alert("Please Select The Group")
		document.getElementsByName("group")[0].focus();
		return false;
	}*/
	if(document.getElementsByName("selectedSeats")[0].options.length==0)
	{
		alert("Select at Least One Seat");
		document.getElementsByName("seatsList")[0].focus();
		return false;
	}
	MoveToSelected();
	return true;
}

// On Unit Selection in Unit Ward-Wise Mode 
function showWards()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		//alert("Please Select Unit")
		document.getElementsByName("wardsList")[0].innerHTML="";
		return;
	}
	submitPage('GETWARDSFORUNITWARD');
}

// Validate Ward Selection
function validateWardSelection()
{
	/*if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		alert("Please Select The Unit");
		document.getElementsByName("unitCode")[0].focus();
		return false;
	}*/
	if(document.getElementsByName("selectedWards")[0].options.length==0)
	{
		alert("Choose at Least One Ward");
		document.getElementsByName("wardsList")[0].focus();
		return false;
	}
	MoveToSelected();
 	return true;
}

// On Unit Selection in Unit Ward Seat-Wise Mode 
function showWardsInUnitWardSeatWise()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
		//alert("Please Select Unit")
		document.getElementsByName("wardsList")[0].innerHTML="";
		return;
	}
	submitPage('GETWARDSFORUNITWARDSEAT');
}
























function getTemplates()
{
	var valid=true;
	
	if(document.getElementsByName("group")[0].value=="-1")
	{
		alert("Please Select The Group")
		valid=false;
	}
	
	if(document.getElementsByName("group")[0].value!="-1")
	{	
		if(document.forms[0].selectedSeats.options.length==0)
		{
			alert("Choose at Least One Seat");
			document.forms[0].seatsList.focus();
			valid=false;
		}
	}
	
	if(valid)
	{
		MoveToSelected();
		submitPage("GETMENUSTEMPLATEFORUNITSEATWARD");
	}
	
	return valid;
}


















function showSeats()
{
	if(document.getElementsByName("group")[0].value=="-1")
	{
		alert("Please Select The Group")
	}
	else
	{
		submitPage('GROUPSEATFORUNITWARDSEAT');
	}
}





function showMenus()
{
MoveToSelected();
 document.getElementById('viewDisplay').style.display="block";
 submitForm('GETWARDSFORUNITWARDSEAT');
}
















































function  getDeskByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
	 
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	submitPage("GETDESKBYUNIT");
}

function getDeskByUnitNSeat()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("userSeatId")[0].value=="-1")
	{
	//	alert("Please Select a Seat");
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		return;
	}
	submitPage("GETDESKBYUNITNSEAT");
}

function getDeskByWard()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("wardCode")[0].value=="-1")
	{
	//	alert("Please Select a Ward");
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		document.getElementsByName("wardCode")[0].focus();
		hideAllShowed(false);
		return;
	}
	submitPage("GETDESKBYWARD");
}

/*function getWardForUnitWardSeatByUnit()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	
	submitPage("GETWARDBYUNITFORUWS");
}*/


function getDeskByUnitNWardNSeat()
{
	if(document.getElementsByName("unitCode")[0].value=="-1")
	{
	//	alert("Please Select a Unit");
		doEmptyCombo(document.getElementsByName("wardCode")[0]);
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("unitCode")[0].focus();
		return;
	}
	if(document.getElementsByName("wardCode")[0].value=="-1")
	{
	//	alert("Please Select a Ward");
		doEmptyCombo(document.getElementsByName("userSeatId")[0]);
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("wardCode")[0].focus();
		return;
	}
	if(document.getElementsByName("userSeatId")[0].value=="-1")
	{
	//	alert("Please Select a Seat");
		doEmptyCombo(document.getElementsByName("deskId")[0]);
		hideAllShowed(false);
		document.getElementsByName("userSeatId")[0].focus();
		return;
	}
	submitPage("GETDESKBYUNITNWARDNSEAT");
}

function getTemplateForModify()
{
	submitPage('GETTEMPLATEFORMODIFY');
}

function validateModify()
{alert("validateMod");
	var valid=true;
	var menuName=document.getElementsByName("deskMenuId")[0].value;
	var tempName=document.getElementsByName("templateId")[0].value;
	if(menuName!="-1" || tempName!="-1")
	{
		if(!validateAdd())
			valid=false;
			alert("valid="+valid);
	}
	alert("valid="+valid);
	return valid;
}
