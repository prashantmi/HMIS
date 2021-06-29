// Generic Patient Profile Javascript Function


function moveRightSingle(listNo)
{
	var source;
	var target;
	// 1 -> Units
	if(listNo=="1")
	{
		source  = document.forms[0].unitList;
		target = document.forms[0].selectedUnits;	
	}	
	else if(listNo=="2")
	{
		source  = document.forms[0].usersList;
		target = document.forms[0].selectedUsers;	
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
		source  = document.forms[0].unitList;
		target = document.forms[0].selectedUnits;	
	}
	else if(listNo=="2")
	{
		source  = document.forms[0].usersList;
		target = document.forms[0].selectedUsers;	
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
		target  = document.forms[0].unitList;
		source = document.forms[0].selectedUnits;	
	}
	else if(listNo=="2")
	{
		target  = document.forms[0].usersList;
		source = document.forms[0].selectedUsers;	
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
		target  = document.forms[0].unitList;
		source = document.forms[0].selectedUnits;	
	}
	else if(listNo=="2")
	{
		target  = document.forms[0].usersList;
		source = document.forms[0].selectedUsers;	
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

function MoveToSelected()
{
	// Unselect Remaining Units
	
	
	if(document.forms[0].unitList)
	{
		for(var i=0;i<document.forms[0].unitList.options.length;i++)
			document.forms[0].unitList.options[i].selected=false;
	}

	// Select All Units in Selected
	if(document.forms[0].selectedUnits)
	{	
		for(var i=0;i<document.forms[0].selectedUnits.options.length;i++)
			document.forms[0].selectedUnits.options[i].selected=true;
	}
	
	// Select All Users
	if(document.forms[0].usersList)
	{	
		for(var i=0;i<document.forms[0].usersList.options.length;i++)
			document.forms[0].usersList.options[i].selected=true;
	}

	// Select All Users in Selected
	if(document.forms[0].selectedUsers)
	{	
		for(var i=0;i<document.forms[0].selectedUsers.options.length;i++)
			document.forms[0].selectedUsers.options[i].selected=true;
	}
}


function validateSearch(code)
{
	document.getElementsByName("searchMode")[0].value = code;
	var str="";
	if(code==1)
	{
		if(document.getElementsByName("searchUserName")[0].value=="")
		{
			alert("Please Enter User Name for Search");
			document.getElementsByName("searchUserName")[0].focus();
			return false;
		}
		str=document.getElementsByName("searchUserName")[0].value;
		document.getElementsByName("searchEmpId")[0].value="";
		document.getElementsByName("searchEmpName")[0].value="";
	}
	else if(code==2)
	{
		if(document.getElementsByName("searchEmpId")[0].value=="")
		{
			alert("Please Enter Employee Id for Search");
			document.getElementsByName("searchEmpId")[0].focus();
			return false;
		}
		str=document.getElementsByName("searchEmpId")[0].value;
		document.getElementsByName("searchUserName")[0].value="";
		document.getElementsByName("searchEmpName")[0].value="";
	}
	else if(code==3)
	{
		if(document.getElementsByName("searchEmpName")[0].value=="")
		{
			alert("Please Enter Employee Name for Search");
			document.getElementsByName("searchEmpName")[0].focus();
			return false;
		}
		str=document.getElementsByName("searchEmpName")[0].value;
		document.getElementsByName("searchUserName")[0].value="";
		document.getElementsByName("searchEmpId")[0].value="";
	}
	document.getElementsByName("searchString")[0].value = str;
	MoveToSelected();
	return true;
}

function validateAddUser()
{
	var chks = document.getElementsByName("selectedUserIndex");
	var flag=false;
	for(var i=0; i<chks.length; i++)
	{
		if(chks[i].checked)
		{
			flag=true;
			break;
		}
	}
	if(!flag)
	{
		alert("Please Select at least One User to Give Add");
		chks[0].focus();
		return false;
	}
	MoveToSelected();
	return true;
}

function validateIfAdded(chk)
{
	if(chk.checked)
	{
		var added = document.getElementsByName("usersList")[0];
		if(added && added.options.length>0)
		{
			for(var i=0; i<added.options.length; i++)
			{
				if(added.options[i].value==chk.value)
				{
					alert("User Already Selected to give Priviledge");
					chk.checked = false;
					return;
				}
			}
		}
		added = document.getElementsByName("selectedUsers")[0];
		if(added && added.options.length>0)
		{
			for(var i=0; i<added.options.length; i++)
			{
				if(added.options[i].value==chk.value)
				{
					alert("User Already Selected to give Priviledge");
					chk.checked = false;
					return;
				}
			}
		}
	}
}