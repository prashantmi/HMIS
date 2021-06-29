function seatSelected(val)
{
	if(val != "-1")
	{
		document.forms[0].deptId.value="-1";
		document.forms[0].setMenuFlag.value="true";
		submitPage("SETMENUS");
	}
}

function deptSelected(val)
{
	if(val != "-1")
	{
		document.forms[0].userSeatId.value="-1";
		document.forms[0].setMenuFlag.value="true";
		submitPage("SELECTSEATS");
	}
}

function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}


function moveUP(listNo)
{
	var list;	
	if(listNo=="2")
		list = document.forms[0].leftMenuList;	
	if(listNo=="3")
		list = document.forms[0].rightMenuList;	
	if(listNo=="4")
		list = document.forms[0].topMenuList;	
	if(listNo=="5")
		list = document.forms[0].bottomMenuList;
		
	var len=list.length;
	for(var i=0;i<len;i++)
	{
		if(list.options[i].selected)
		{
			if(i==0) return;
			
			var temp;
			temp=list.options[i-1].value;
			list.options[i-1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i-1].text;
			list.options[i-1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i-1].selected=true;;
			list.options[i].selected=false;
		}
	}
}

function moveDOWN(listNo)
{
	var list;	
	if(listNo=="2")
		list = document.forms[0].leftMenuList;	
	if(listNo=="3")
		list = document.forms[0].rightMenuList;	
	if(listNo=="4")
		list = document.forms[0].topMenuList;	
	if(listNo=="5")
		list = document.forms[0].bottomMenuList;
		
	var len=list.length;
	for(var i=len-1;i>=0;i--)
	{
		if(list.options[i].selected)
		{
			if(i==(len-1)) return;
			
			var temp;
			temp=list.options[i+1].value;
			list.options[i+1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i+1].text;
			list.options[i+1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i+1].selected=true;;
			list.options[i].selected=false;
		}
	}
}


function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Seats
	// 2 -> Left Menu
	// 3 -> Right Menu
	// 4 -> Top Menu
	// 5 -> Bottom Menu
	
	if(listNo=="1")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].leftMenuList;	
	}
	if(listNo=="3")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].rightMenuList;	
	}
	if(listNo=="4")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].topMenuList;	
	}
	if(listNo=="5")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].bottomMenuList;	
	}

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

function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].seatsList;
		target = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].leftMenuList;	
	}
	if(listNo=="3")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].rightMenuList;	
	}
	if(listNo=="4")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].topMenuList;	
	}
	if(listNo=="5")
	{
		source  = document.forms[0].menusList;
		target = document.forms[0].bottomMenuList;	
	}

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

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].seatsList;
		source = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].leftMenuList;	
	}
	if(listNo=="3")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].rightMenuList;	
	}
	if(listNo=="4")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].topMenuList;	
	}
	if(listNo=="5")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].bottomMenuList;	
	}

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

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].seatsList;
		source = document.forms[0].selectedSeats;	
	}
	if(listNo=="2")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].leftMenuList;	
	}
	if(listNo=="3")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].rightMenuList;	
	}
	if(listNo=="4")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].topMenuList;	
	}
	if(listNo=="5")
	{
		target = document.forms[0].menusList;
		source = document.forms[0].bottomMenuList;	
	}

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

function clearPreview()
{
	document.forms[0].preview.value="";
	document.forms[0].preview.style.backgroundColor="";
	document.forms[0].mnuColor.value="#FFDEAD";
	document.forms[0].mnuName.value="";

	document.getElementsByName("isMnuLoginBound")[0].checked = false;
	document.getElementsByName("isMnuLoginBound")[1].checked = false;
	document.getElementsByName("mnuDutyRoleId")[0].value = "-1";
	document.getElementById("divDutyRole").style.display = "none";
}

function setPreview(name,color,code,loginBound,dutyRoleId)
{
	document.forms[0].preview.value=name;
	document.forms[0].preview.style.backgroundColor=color;
	document.forms[0].mnuColor.value=color;
	document.forms[0].mnuName.value=name;
	document.forms[0].mnuCode.value=code;

	//alert(loginBound +"   "+dutyRoleId);
	if(loginBound==document.getElementsByName("isMnuLoginBound")[0].value)
	{
		document.getElementsByName("isMnuLoginBound")[0].checked = true;
		document.getElementById("divDutyRole").style.display="block";
	}
	else if(loginBound==document.getElementsByName("isMnuLoginBound")[1].value)
	{
		document.getElementsByName("isMnuLoginBound")[1].checked = true;
		document.getElementById("divDutyRole").style.display="none";
	}
	//alert("Yes--"+document.getElementsByName("isMnuLoginBound")[0].checked);
	//alert("No--"+document.getElementsByName("isMnuLoginBound")[1].checked);
	document.getElementsByName("mnuDutyRoleId")[0].value = dutyRoleId;
	//alert(document.getElementsByName("mnuDutyRoleId")[0].value);
}

function setPreviewMenu(list)
{
	var i=-1;
	if(list.name=="leftMenuList" || list.name=="rightMenuList" || list.name=="topMenuList" || list.name=="bottomMenuList" )
	{
		if(list.selectedIndex>=0)	i=list.selectedIndex;
	}
	else
		return;
	
	if(i==-1)
		clearPreview();
	else
		setPreview(getMenuName(list,i),getMenuColor(list,i),list.options[i].value,getLoginBoundFlag(list,i),getDutyRoleId(list,i));
}

function setPreviewMenuColor(color)
{
	var menuName=document.forms[0].mnuName.value;
	if(menuName=="")
	{
		clearPreview();
		return;
	}	
	setPreview(menuName, color, document.forms[0].mnuCode.value, getCurrentLoginBoundFlag(), document.forms[0].mnuDutyRoleId.value);
	setMenuColor(document.forms[0].mnuCode.value, color);
}

function setPreviewMenuName(name)
{
	var mnuName=document.forms[0].mnuName.value;
	if(mnuName=="")	clearPreview(); 

	var menuName=document.forms[0].preview.value;
	if(menuName=="") 
	{
		clearPreview();
	}
	if(name=="") 
	{
		alert("Menu Name can't be Empty ...");
		document.forms[0].mnuName.focus();
		return;
	}
	setPreview(name, document.forms[0].preview.style.backgroundColor, document.forms[0].mnuCode.value, getCurrentLoginBoundFlag(), document.forms[0].mnuDutyRoleId.value);
	setMenuName(document.forms[0].mnuCode.value,name);
}

function setPreviewLoginBound(val)
{
	var menuName=document.forms[0].mnuName.value;
	if(menuName=="") 
	{
		clearPreview();
	}
	setPreview(menuName, document.forms[0].preview.style.backgroundColor, document.forms[0].mnuCode.value, val, document.forms[0].mnuDutyRoleId.value);
	setLoginBound(document.forms[0].mnuCode.value, val);
}

function setPreviewDutyRoleId(val)
{
	var menuName=document.forms[0].mnuName.value;
	if(menuName=="") 
	{
		clearPreview();
	}
	setPreview(menuName, document.forms[0].preview.style.backgroundColor, document.forms[0].mnuCode.value, getCurrentLoginBoundFlag(), val);
	setDutyRole(document.forms[0].mnuCode.value, val);
}

function onchangeLoginBound()
{
	var menuName=document.forms[0].mnuName.value;
	if(menuName=="")	clearPreview(); 

	var objYes = document.getElementsByName("isMnuLoginBound")[0];
	var objNo = document.getElementsByName("isMnuLoginBound")[1];
	var divRow = document.getElementById("divDutyRole");
	var val=null;	
	if(objYes.checked)
	{
		val=objYes.value;
		divRow.style.display = "block";
	}
	else if(objNo.checked)
	{
		val=objNo.value;
		divRow.style.display = "none";
		document.getElementsByName("mnuDutyRoleId")[0].value="-1";
	}
	//alert(val);
	if(val!=null)	setPreviewLoginBound(val); 
}

function setMenuColor(_menuId,color)
{
	var lst=getMenuList(_menuId);
	var i=getMenuIndex(lst,_menuId);
	if(lst=="")
	{
		clearPreview();
		return;
	}	
	var myArray = lst.options[i].value.split( "@" );
	var menuId = myArray[0];
	var menuName=myArray[2];
	var loginBound=myArray[3];
	var dutyrole=myArray[4];	
	if(color!="")
		lst.options[i].value=menuId+"@"+color+"@"+menuName+"@"+loginBound+"@"+dutyrole;
	else
		lst.options[i].value=menuId+"@#FFDEAD@"+menuName+"@"+loginBound+"@"+dutyrole;
	//alert(lst.options[i].value);
	document.forms[0].mnuCode.value=lst.options[i].value;
}

function setMenuName(_menuId,name)
{
	var lst=getMenuList(_menuId);
	var i=getMenuIndex(lst,_menuId);
	if(lst=="")
	{
		clearPreview();
		return;
	}	
	var myArray = lst.options[i].value.split( "@" );
	var menuId = myArray[0];
	var menuColor=myArray[1];
	var loginBound=myArray[3];
	var dutyrole=myArray[4];	
	if(name!="")
		lst.options[i].value=menuId+"@"+menuColor+"@"+name+"@"+loginBound+"@"+dutyrole;
	else
		lst.options[i].value=menuId+"@"+menuColor+"@"+lst.options[i].text+"@"+loginBound+"@"+dutyrole;
	//alert(lst.options[i].value);
	document.forms[0].mnuCode.value=lst.options[i].value;
}

function setLoginBound(_menuId,val)
{
	var lst=getMenuList(_menuId);
	var i=getMenuIndex(lst,_menuId);
	if(lst=="")
	{
		clearPreview();
		return;
	}	
	var myArray = lst.options[i].value.split( "@" );
	var menuId = myArray[0];
	var menuColor=myArray[1];
	var menuName=myArray[2];
	var dutyrole=myArray[4];	
	if(val!="")
		lst.options[i].value=menuId+"@"+menuColor+"@"+menuName+"@"+val+"@"+dutyrole;
	else
		lst.options[i].value=menuId+"@"+menuColor+"@"+menuName+"@"+"0"+"@"+dutyrole;
	//alert(lst.options[i].value);
	document.forms[0].mnuCode.value=lst.options[i].value;
}

function setDutyRole(_menuId,val)
{
	var lst=getMenuList(_menuId);
	var i=getMenuIndex(lst,_menuId);
	if(lst=="")
	{
		clearPreview();
		return;
	}	
	var myArray = lst.options[i].value.split( "@" );
	var menuId = myArray[0];
	var menuColor=myArray[1];
	var menuName=myArray[2];
	var loginBound=myArray[3];
	if(val!="")
		lst.options[i].value=menuId+"@"+menuColor+"@"+menuName+"@"+loginBound+"@"+val;
	else
		lst.options[i].value=menuId+"@"+menuColor+"@"+menuName+"@"+loginBound+"@"+"-1";
	//alert(lst.options[i].value);
	document.forms[0].mnuCode.value=lst.options[i].value;
}

function getMenuList(menuId)
{
	var lst=document.forms[0].leftMenuList;
	var len=lst.length;
	for(var i=0;i<len;i++)
		if(lst.options[i].value==menuId)
			return lst;

	lst=document.forms[0].rightMenuList;
	len=lst.length;
	for(var i=0;i<len;i++)
		if(lst.options[i].value==menuId)
			return lst;

	lst=document.forms[0].topMenuList;
	len=lst.length;
	for(var i=0;i<len;i++)
		if(lst.options[i].value==menuId)
			return lst;

	lst=document.forms[0].bottomMenuList;
	len=lst.length;
	for(var i=0;i<len;i++)
		if(lst.options[i].value==menuId)
			return lst;
	return "";
}

function getMenuIndex(lst,menuId)
{
	var len=lst.length;
	for(var i=0;i<len;i++)
		if(lst.options[i].value==menuId)
			return i;
}

function getMenuColor(lst,i)
{
	var myArray = lst.options[i].value.split( "@" );
	if( myArray.length != 5)
	{
		//lst.options[i].value=myArray[0]+"@#FFDEAD@"+lst.options[i].text;
		//myArray = lst.options[i].value.split( "@" );
		lst.options[i].value=myArray[0]+"@#FFDEAD@"+lst.options[i].text+"@0@-1";
		myArray = lst.options[i].value.split( "@" );
	}
	return myArray[1];
}

function getMenuName(lst,i)
{
	var myArray = lst.options[i].value.split( "@" );
	if( myArray.length != 5)
	{
		//lst.options[i].value=myArray[0]+"@#FFDEAD@"+lst.options[i].text;
		//myArray = lst.options[i].value.split( "@" );
		lst.options[i].value=myArray[0]+"@#FFDEAD@"+lst.options[i].text+"@0@-1";
		myArray = lst.options[i].value.split( "@" );
	}
	return myArray[2];
}

function getLoginBoundFlag(lst,i)
{
	var myArray = lst.options[i].value.split( "@" );
	if( myArray.length != 5)
	{
		lst.options[i].value=myArray[0]+"@#FFDEAD@"+lst.options[i].text+"@0@-1";
		myArray = lst.options[i].value.split( "@" );
	}
	return myArray[3];
}

function getDutyRoleId(lst,i)
{
	var myArray = lst.options[i].value.split( "@" );
	if( myArray.length != 5)
	{
		lst.options[i].value=myArray[0]+"@#FFDEAD@"+lst.options[i].text+"@0@-1";
		myArray = lst.options[i].value.split( "@" );
	}
	return myArray[4];
}

function getCurrentLoginBoundFlag()
{
	var objYes = document.getElementsByName("isMnuLoginBound")[0];
	var objNo = document.getElementsByName("isMnuLoginBound")[1];
	if(objYes.checked)	return objYes.value;
	else if(objNo.checked)	return objYes.value;
}

function MoveToSelected()
{
	// Unselect Remaining Menus
	if(document.forms[0].menusList)
		for(var i=0;i<document.forms[0].menusList.length;i++)
			document.forms[0].menusList.options[i].selected=false;

	// Select All Left Menu in Selected
	if(document.forms[0].leftMenuList)
		for(var i=0;i<document.forms[0].leftMenuList.length;i++)
			document.forms[0].leftMenuList.options[i].selected=true;

	// Select All Right Menus in Selected
	if(document.forms[0].rightMenuList)
		for(var i=0;i<document.forms[0].rightMenuList.length;i++)
			document.forms[0].rightMenuList.options[i].selected=true;

	// Select All Top Menus in Selected
	if(document.forms[0].topMenuList)
		for(var i=0;i<document.forms[0].topMenuList.length;i++)
			document.forms[0].topMenuList.options[i].selected=true;

	// Select All Bottom Menus in Selected
	if(document.forms[0].bottomMenuList)
		for(var i=0;i<document.forms[0].bottomMenuList.length;i++)
			document.forms[0].bottomMenuList.options[i].selected=true;
}

function validateFinalSubmit()
{
	if(document.forms[0].leftMenuList.length==0 && document.forms[0].rightMenuList.length==0 && document.forms[0].topMenuList.length==0 && document.forms[0].bottomMenuList.length==0 )
	{
		alert("Choose at least One Menu to Add ... ");
		document.forms[0].menusList.focus();
		return false;
	}
	return true;
}

function finalSubmit(mode)
{
	setIsDefault();
	if(!validate())	return;

	MoveToSelected();
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function validate()
{
	if(document.forms[0].deskName.value=="")
	{
		alert("Enter Desk Name ... ");
		document.forms[0].deskName.focus();
		return false;
	}
	if(document.forms[0].deskType.value=="-1")
	{
		alert("Select Desk Type ... ");
		document.forms[0].deskType.focus();
		return false;
	}
	if(document.forms[0].isActive.value=="-1")
	{
		alert("Select Is Active Mode ... ");
		document.forms[0].isActive.focus();
		return false;
	}
	return true;	
}

function submitMyForm(mode)
{
	setIsDefault();
	if(validate()==true)	submitPage(mode);
}

function setIsDefault()
{
	if(document.getElementsByName("chkIsDefault")[0].checked)
		document.getElementsByName("isDefault")[0].value="1";
	else
		document.getElementsByName("isDefault")[0].value="0";		
}



function callonload()
{

	if (document.getElementsByName("hmode")[0].value=="MODIFY")
	{
		if(document.getElementsByName("isDefault")[0].value == 1)
			document.getElementsByName("chkIsDefault")[0].checked=true;
		else if(document.getElementsByName("isDefault")[0].value==0)
			document.getElementsByName("chkIsDefault")[0].checked=false;
	}
	
	else
	{
		
	if(document.getElementsByName("chkIsDefault")[0].checked)
		{
			document.getElementsByName("isDefault")[0].value=1;
			document.getElementsByName("chkIsDefault")[0].checked=true;
		}
	else
	{
			document.getElementsByName("isDefault")[0].value=0;
			document.getElementsByName("chkIsDefault")[0].checked=false;
	}
	}
}


function callonloadGlobal()
{
	//if(document.getElementsByName("isDefault")[0].value == 1)
		document.getElementsByName("chkIsDefault")[0].checked=true;
		document.getElementsByName("isDefault")[0].value = 1;
	//else if(document.getElementsByName("isDefault")[0].value==0)
	//	document.getElementsByName("chkIsDefault")[0].checked=false;
}
