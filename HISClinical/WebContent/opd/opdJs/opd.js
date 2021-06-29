function comboValidation(obj, str)
{
	var valid= true;
	if(obj.value==-1)
	{
		alert("Please Select : "+str);
		valid=false;
		obj.focus();
	}
	return valid;
}
	
function validateTestAreaLenth(length)
{	
	var flag=true;
	var len=parseInt(length);
	var contentText=document.getElementsByName("content")[0].value;
	var textLength=contentText.length;
	if(textLength>len)
	{
		alert("Only 1000 Character can be send and your data is of "+contentText.length+" Character");
		flag=false;
	}
	return flag;
}

function submitCancleConsulatationInbox()
{
	//document.getElementsByName("patCrNo")[0].value=document.getElementsByName("selectedPatCrNo")[0].value;
	submitToDesk('NEW','NEW');
}

function moveToRightBox(elem, evt)
{
	val=elem.value;
	maxLength =elem.getAttribute('maxlength');
	i=0;
	if(val.length==maxLength)
	{
		for(i=0; i<prevValue.length;i++)
			if(prevValue.charAt(i)!=val.charAt(i))
				break;
	}
	if(i==maxLength-1)
	{
		if(elem.name == 'fromHour')
				document.getElementsByName("fromMin")[0].focus();
		else if(elem.name == 'fromMin')
			document.getElementsByName("toHour")[0].focus();
		else if(elem.name == 'toHour')
			document.getElementsByName("toMin")[0].focus();
	}
}

function setPreviosValue(elem, evt)
{
	prevValue = elem.value;
}
	
////////////////////////////////OPD PROFILE////////////////////////////////
function submitToCancle()
{
	self.close();
}

function saveHtml()
{	
	var ind=0;
	var tempHtml="";
	var checkBoxObj=document.getElementsByName("selectedRow");
	while(ind<checkBoxObj.length)
	{
		if(checkBoxObj[ind].checked)
			tempHtml=tempHtml+arrayObj[ind];
		ind=ind+1;
	}
	document.getElementsByName("htmlValue")[0].value=htmlText+tempHtml;
	submitForm('SHOWPROFILEMENU');
}

function selectAll(obj)
{	
	var flag=false;
	if(obj.checked)
		flag=true;
	var checkObj=document.getElementsByName("selectedRow");
	var index=0;
	while(index<checkObj.length)
	{
		checkObj[index].checked=flag;
		addRow(checkObj[index])
		index++;
	}	 
}

function checkEmpty(objValue,name)
{	
	var flag=false;
	if(objValue=="" || objValue==" ")
		alert("Please Enter "+name);
	else
		flag=true;
	return flag;
}