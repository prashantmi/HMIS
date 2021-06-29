/*
Project			: HIS			
File Name 		: functionality.js
Version	  		: 1.0
Developer 		: HIS Team
*/
/*
Note --> 
1. please name your form as form1;
3. Add message in getMsg() function

Warning : NO CHANGE WILL BE REFLECTED IN ORIGINAL VALIDATION FILE WITHOUT AJAY,JITU,LOKESH OR VAIBHAV
PERMISSION	 
*/
/*
	List of functions in this file

1> function getmsgStr(index)
2> function submitpage(mode)
3> function add()
4> function edit()
5> function del()
6> function view()
7> function cancel(filename)
8> function checkCounter(obj)
9> function save()
10> function update()		
11> function callEnter(filename)
12> selectAll(chkref)
13> getRowColorBack(index)
14> assignRowColor(obj)
15> setChkFocus(index)
16>	setChkBlur(index)
17>	duplicateClick(eObj,obj)
18> primaryKeyAssignment(pKey)
19>	contains(objName,searchArr)
20> replace(sourceStr,what,with)
21> report(event)
22> function checkBrowser()
23> function changeState()
24> function onDisplayColor
25> function sort(filename,strtemp2,val)	
26>	function pagination(filename,page_no,var3)
27> function comboSubmit(filename)
28>	function opRecord()

*/

/*this function will have the user defined messages
1*/

function myMethod(i,j,k)
{
   
    document.forms[0].pageNo.value=i;
    document.forms[0].blockNo.value=j;
    document.forms[0].pagePerBlock.value=k;
    var bn=document.forms[0].blockNo.value;
    var ppb=document.forms[0].pagePerBlock.value;
    document.forms[0].minPage.value=(ppb*(bn-1))+1;
    document.forms[0].maxPage.value=bn*ppb;
    document.forms[0].submit(); 
}

function myNext(i,j)
{
    document.forms[0].blockNo.value=i+1;
    document.forms[0].pagePerBlock.value=j; 
    var bn=document.forms[0].blockNo.value;
    var ppb=document.forms[0].pagePerBlock.value;
    document.forms[0].minPage.value=(ppb*(bn-1))+1;
    document.forms[0].pageNo.value=document.forms[0].minPage.value;
    document.forms[0].maxPage.value=bn*ppb;
    document.forms[0].submit();
      
}
 
function myPrev(i,j)
{ 
    document.forms[0].blockNo.value=i-1;
    document.forms[0].pagePerBlock.value=j; 
    var bn=document.forms[0].blockNo.value;
    var ppb=document.forms[0].pagePerBlock.value;
    document.forms[0].minPage.value=(ppb*(bn-1))+1;
    document.forms[0].maxPage.value=bn*ppb;
    document.forms[0].pageNo.value=bn*ppb;
    document.forms[0].submit();

}
function getmsgStr(index)
{
	var msgStr = "";
	
	switch(index)
	{
		case 0:
			msgStr = "Select the record";//delete
			break;
		case 1:
			msgStr = "Select the record";//modify,view
			break;
		case 2:
			msgStr = "Select only one record";
			break;
		case 3:	
			msgStr = "Select only one record";
			break;
		case 4:	
			msgStr = "Cannot Add multiple records at a time(select only one record)!";
			break;
		case 5:	
			msgStr = "select only one record";
			break;
	}	//end of switch statement
	
	alert(msgStr); 
	return;
}

/*this function submits the page after setting the value in the hidden field
2*/


function submitpage(mode)
{
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
   

}

function submitpage1(e,form1,mode)
{

if(e.type=="click" || e.keyCode==13)
					{
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
                    }

}

/*this function submits the page after setting the value in the hidden field
3*/ 
function add(e)
{
if(e.type=="click" || e.keyCode==13)
	submitpage("ADD");
}

/*this function submits the page only if the user has selected only one checkbox
4*/ 
function edit(e)
{
if(e.type=="click" || e.keyCode==13)
  {
	len = checkCounter();
	if(len > 0)
	{
		if(len>1)
			getmsgStr(3);
		else
			submitpage("EDIT");
	}
	else
		getmsgStr(1);
  }		
}

/*this function submits the page only if the user has selected at least one checkbox
5*/
function del(e)
{
if(e.type=="click" || e.keyCode==13)
  {

	var len=checkCounter();
	var errStr;
	
	if(len!=0)
	{
		errStr = "You are going to delete " + len + " record(s)\nAre you sure?"
		if(confirm(errStr))
			submitpage("DELETE");
			}
	else
		getmsgStr(0);
   }
}

/*this function submits the page only if the user has selected only one checkbox
6*/
function view(e)
{
if(e.type=="click" || e.keyCode==13)
  {

	len=checkCounter();

	if(len!=0)
		if(len>1)
			getmsgStr(2);
		else
			submitpage("VIEW");
	else
		getmsgStr(1);
  }
}

/*this function submits the page after setting action property for th form
7*/
function cancel(filename)
{
	
	document.forms[0].page_no.value='1';
	document.forms[0].action = filename;
	document.forms[0].submit();

}

/*this function increments or decrements the hcounter field as the user selects
or deselects the check box
 
8*/
function checkCounter()
{
	
    var chks=document.all.item("chk");
	var count=0;
	
	if(isNaN(chks.length))
	{
		if(chks.checked)
			count++;
	}
	else
	{
		for(var i=0;i<chks.length;i++)
			if(chks[i].checked)
				count++;
	}
	
	return count;
}

/*
9*/
function save()
{
	submitpage("SAVE");

}

/*
10*/
function update()
{
	submitpage("UPDATE");
}

/*
function for trapping enter key and if it finds, it submits the page
11*/
function callEnter(filename,e)
{
	if(e.keyCode==13)
		cancel(filename);
}


/* function for selecting/deselecting all checkbox which are currently visible
12*/
function selectAll(chkref)
{
	
    var obj;
	
	if(chkref.checked)
	{
		for(i=0;i<document.forms[0].elements.length;i++)
		{
			obj = document.forms[0].elements[i];
			if(obj.type=="checkbox" && !obj.checked)
			{
				obj.checked=true;
				checkCounter(obj);
			}	
		}
	}
	else
	{
		for(i=0;i<document.forms[0].elements.length;i++)
		{
			obj = document.forms[0].elements[i];
			if(obj.type=="checkbox" && obj.checked)
			{
				obj.checked=false;
				checkCounter(obj);
			}	
		}
	}
}

/* function for getting previous row color if checkbox is not selected
13*/
function getRowColorBack(index)
{
	var chkObjs=document.all.item("chk");
	if(isNaN(chkObjs.length))
	{
		if(!chkObjs.checked)
			document.all.dtr.bgColor='#f5f3f3';
	}
	else
		if(!chkObjs[index].checked)
			document.all.dtr[index].bgColor='#f5f3f3';
}

/* function for settin/getting row color  select all checkbox is selected
14*/
function assignRowColor(obj)
{
	if(obj.checked)
		for(var i=0;i<document.all.dtr.length;i++)//setting row color to new one 
			document.all.dtr[i].bgColor='#e0e0e0';
	else
		for(var i=0;i<document.all.dtr.length;i++)//getting row color back 
			getRowColorBack(i);
}

/* function for settin row color by select the checkbox by key
15*/
function setChkFocus(index)
{
	if(isNaN(document.all.dtr.length) && index==0)
		document.all.dtr.bgColor='#e0e0e0';
	else
		document.all.dtr[index].bgColor='#e0e0e0';
}

/* function for gettin row color back by select the checkbox by key
16*/
function setChkBlur(index)
{
	getRowColorBack(index);
}

/* function for duplicate of click event on keyPress
17*/
function duplicateClick(eObj,obj)
{
	if(eObj.keyCode==13 || eObj.keyCode==32)
		obj.click();
}

/* function for agsigning primary key value to hiden field
18*/
function primaryKeyAssignment(pKey)
{
	for(var i=0;i<document.forms[0].elements.length;i++)
	{
		if(contains(document.forms[0].elements[i].name,pKey))
			document.forms[0].hPrimary.value += ","+document.forms[0].elements[i].value;
	}
	
	if(document.forms[0].hPrimary.value.length > 0)
		document.forms[0].hPrimary.value = document.forms[0].hPrimary.value.substring(1);
	
}

/* function for getting given name is present in array
19*/
function contains(objName,searchArr)//helper of above
{
	for(var i=0;i<searchArr.length;i++)
	{
		if(searchArr[i]==objName)
			return true;
	}
	return false;
}

/*function gor replacing string with given string
20*/
function replaceStr(sourceStr,val1,val2)
{
	var arr=sourceStr.split(val1);
	var replaced="";
	
	for(var i=0;i<arr.length;i++)
	{
		if(i!=arr.length-1)
			replaced	+= arr[i]+val2;
		else
			replaced	+= arr[i];
	}

	return replaced;
}

/*
Function for report 
21*/

function report(e)
{

	 if(e.type=="click" || e.keyCode==13)
	{
		submitpage("REPORT");
	}
}

/*
function that checks the browser. called it on body's onLoad. this function
requires when layer is hided/showed

22*/

function checkBrowser()

{

	if(navigator.userAgent.indexOf("MSIE") != -1)

	{

		layer = ".all";

		style = ".style";

	}

	else if(navigator.userAgent.indexOf("Nav") != -1)

	{

		layer = ".layers" ;

		style = "" ;

	}
	else
	{
		// alert("navigator.userAgent "+)
		layer = ".all";

		style = ".style";

	}

}

/*function that hides/shows the layer
23*/ 

function changeState(layerRef, state)
{
	//eval("alert('inside changeState()')")
	eval("document" + layer + "['" + layerRef + "']" + style + ".visibility = '" + state + "'");
}

function onDisplayColor()
{
	var chks=document.all.item("chk");
	
	if(chks!=null)
	{
		if(isNaN(chks.length))
		{
			if(chks.checked)
			{
				//alert("in");
				setChkFocus(0);
			}
		}
		else
		{
			for(var i=0;i<chks.length;i++)
				if(chks[i].checked)
				{
					setChkFocus(i);
				}
		}
	}
	
	function cancel(filename,e)
	{
		if(e.type=="click" || e.keyCode==13)
		{
			document.forms[0].action = filename;
			document.forms[0].submit();
		}
	}	
}/*24*/
// Function added by Preeti for Master Template
function sort(filename,var1,var2)
{
	document.forms[0].action = filename;
	document.forms[0].var1.value=var1;
	document.forms[0].var2.value=var2;
	document.forms[0].submit();	
}
// Function added by Preeti for Master Template
function pagination(e,filename,page_no,var3)
{

  if(e.type=="click" || e.keyCode==13)
		{
	document.forms[0].action = filename;
	document.forms[0].page_no.value=page_no;
	document.forms[0].var3.value=var3;
	document.forms[0].submit();
	    }	
}
// Function added by Preeti for Master Template
function comboSubmit(filename)
{
	document.forms[0].action = filename;
	document.forms[0].submit();
}
// Function added by Preeti for Master Template
function comboLstSubmit(mode)
{
	document.forms[0].page_no.value=1;
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}
// Function added by Preeti for OPRecord
function opRecord()
{
	len = checkCounter();	
	if(len > 0)
	{
		if(len>1)
			getmsgStr(4);
		else
			submitpage("ADD");
	}
	else
		getmsgStr(1);

}
/*=========================================end of file===============================================*/

