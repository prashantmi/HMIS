// shiftToright function starts here//////////////////////////////////////////////////////

var oDP = null;

function shiftToright(lobj,robj)
{
	var element=new Array();
	var elementvalue=new Array();
	var index=0;
	var count=0;
	if(lobj.length > 0)
	{
		for(i=0;i<lobj.length;i++)
		{
			if(lobj.options[i].selected)
			{
				robj.length++;
				robj.options[robj.length-1].text=lobj.options[i].text;
				robj.options[robj.length-1].value=lobj.options[i].value;
				count++;
			}
			else
			{
					element[index]=lobj.options[i].text;
					elementvalue[index++]=lobj.options[i].value;					
			}
		}		

		for(i=0;i<element.length;i++)
		{	
			lobj.options[i].text=element[i];
			lobj.options[i].value=elementvalue[i];
		}
		
		for(i=0;i<count;i++)
		{
			lobj.length--;
		}
	}
	
}	
	
	// shiftToleft functionstarts here////////////////////////////////////////////////////
	
function shiftToleft(lobj,robj)
{
	
	var element=new Array();
	var elementvalue=new Array();
	var index=0;
	var count=0;
	
	if(robj.length > 0)
	{
		for(i=0;i<robj.length;i++)
		{
			if(robj.options[i].selected && robj.options[i].value!=0) 
			{
				lobj.length++;
				lobj.options[lobj.length-1].text=robj.options[i].text;
				lobj.options[lobj.length-1].value=robj.options[i].value;
				count++;
			}
			else
			{		
				element[index]=robj.options[i].text;
				elementvalue[index++]=robj.options[i].value;
			}
		}		

		for(i=0;i<element.length;i++)
		{	
			robj.options[i].text=element[i];
			robj.options[i].value=elementvalue[i];
		}
		
		for(i=0;i<count;i++)
		{
			robj.length--;
		}
	}
	
}	


function shiftAllToRight(lobj,robj)
{
	
	if(lobj.length > 0)
	{
		for(i=0;i<lobj.length;i++)
		{
			robj.length++;
			robj.options[robj.length-1].text=lobj.options[i].text;
			robj.options[robj.length-1].value=lobj.options[i].value;
		}

		lobj.length=0;
	}
	
}	

// shiftAllToLeft() starts here ////////////////////////////////////////

function shiftAllToLeft(lobj,robj)
{
	if(robj.length > 0)
	{
		var count=0;
		for(i=0;i<robj.length;i++)
		{
			if(robj.options[i].value==0)
			count++;
			
		}
		
		for(i=count;i<robj.length;i++)
		{
			lobj.length++;
			lobj.options[lobj.length-1].text=robj.options[i].text;
			lobj.options[lobj.length-1].value=robj.options[i].value;				
		}

		for(i=robj.length-1;i>=count;i--)
		{
			robj.length--;
		}		
	}
	
}

//for searching item in list box
function searchme(textref,listref)
{
	flag=0;
	var submitFlag = true;
	
	if(window.event.keyCode==13 &&  textref.value != "")
	{
		submitFlag = false;
		ln=textref.value.length;
		for(i=0;i<listref.length;i++)
		{
			str=listref.options[i].text.substring(0,ln);
			
			if(str.toUpperCase()==textref.value.toUpperCase())
			{				
				listref.selectedIndex=listref.options[i].index;
				flag=1;
				break;
			}
			flag=0;				
		}
		
		if(flag==0)
			alert("Not Found !");
	}
	
	return submitFlag;
}

//select the item and then submits the form if mode = 1
function validate(rlistObj,mode)
{
	var count=0;
	var retVal = false;
	
	if(rlistObj.length >= 1)
	{
		for(i=0;i<rlistObj.length;i++)
		{
			rlistObj.options[i].selected = false;
		}
			
		for(i=0;i<rlistObj.length;i++)
		{
			if(rlistObj.options[i].value != 0)
			{
				rlistObj.options[i].selected = true;
				count++;
			}
		}
			
		if(count != 0)
			retVal = true;
		else
			alert("Select new items");
	}
	else
		alert("Select items");
		
	if(retVal == true)
	{
		if(mode == 1)
			document.form1.submit();
	}
	return retVal;
	
}

//used for date time picker	
function init()
{
   oDate = new Date();
   oDP   = new frameDatePicker.DatePicker("divDatePicker", oDate.getFullYear()-60, oDate.getFullYear()+60);

   // If you want Sunday as first day of the week use this construction call instead:
   // oDP = new frameDatePicker.DatePicker("divDatePicker", oDate.getFullYear()-2, oDate.getFullYear()+5, true);

   // Use another init year/month than todays. 0=Jan, 11=Dec
   //oDP.setInitDate(2003, 0);
}