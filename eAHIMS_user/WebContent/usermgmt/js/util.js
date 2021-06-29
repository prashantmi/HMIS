/*
File Name 		: util.js
Version	  		: 2.0
*/

/*
List of functions in this file

 1>	Showlayer(id,mode)
 2> roundValue(original_number, decimals) 
 3> manipulateValue(value1,value2,mode)
 4> trimAll(strValue)
 5> openPopUp(url,width,height,top_pos,left_pos,mode)
 6>	selectAll(chkName,mode)
 7> shiftToright(left_list_name,right_list_name,left_list_remove)
 8> shiftToleft(left_list_name,right_list_name,left_list_insert)
 9> shiftAllToRight(left_list_name,right_list_name,left_list_remove)
 10> shiftAllToLeft(left_list_name,right_list_name,left_list_insert)
 11> selectListRecords(list_name)
 12> searchInListBox(list_name,value)		 						
*/


/*
	This function displays/hides the layer's contents. 
	It accepts
		id --> layer ID
		mode --> 1  >> Display
				 0	>> Hide
*/	
function Showlayer(id,mode)
{
	var objValue = document.getElementById(id);
	
	if(objValue != null)
	{
		if(mode == 1)	//display
			objValue.style.display = "BLOCK";
		else	
			objValue.style.display = "NONE";
	}
} //end Showlayer()

/*
	This function is used to round a value upto given decimal point
	e.g. 10.235 (2 decimal point) --> 10.24
	It returns rounded value
*/
function roundValue(original_number, decimals) 
{
    var result1 = original_number * Math.pow(10, decimals);
    var result2 = Math.round(result1);
    var result3 = result2 / Math.pow(10, decimals);
	
    return pad_with_zeros(result3, decimals);
}

/*
	This function is used to add/substract two values. It is required b'coz JS gives 
	wrong result while adding/substracting two decimal value
	e.g. 8.245-4 = 4.2449999999999992 (something returned by JS)
		 = 4.245 [Returned by this function]
	
	It accepts
		value1/value2 --> values
		mode --> 0 --> Addition
				 1 --> Substraction
	It returns manipulated value			 
*/
function manipulateValue(value1,value2,mode)
{
	var index1,index2;
	var strVal1="",strVal2="";
	var strLen1 = 0,strLen2 = 0;
	var value = 0;
	
	strVal1 = value1.toString();
	strVal2 = value2.toString();
	
	index1 = strVal1.indexOf(".");
	index2 = strVal2.indexOf(".");
	
	if(index1 > -1 || index2 > -1)
	{
		if(index1 > -1)
			strLen1 = (strVal1.substr(index1 + 1)).length; 
		
		if(index2 > -1)
			strLen2 = (strVal2.substr(index2 + 1)).length;
		
		if(strLen2 > strLen1)
			strLen1 = strLen2;
		
		if(mode == 0)
			value = ((value1 * Math.pow(10,strLen1)) + (value2 * Math.pow(10,strLen1)))/Math.pow(10,strLen1); 
		else
			value = ((value1 * Math.pow(10,strLen1)) - (value2 * Math.pow(10,strLen1)))/Math.pow(10,strLen1);			
	}
	else
	{
		if(mode == 0)
			value = value1+value2;
		else	
			value = value1-value2;
	}
	
	return value;
}

/* 
	Function For Removing spaces in string
	e.g. ajay kumar gupta
	return value = ajaykumargupta
*/
function trimAll(strValue)
{
	var j;
	var retStr = "";
	var len = strValue.length;
	
	for(j = 0;j < len;j++)
	{
		if(strValue.charAt(j) != " ") retStr += strValue.charAt(j);
	}	
	
	return retStr;		
}

/*
	This function is used to open/close pop up window
	mode = 1  --> used for open pop up window
		 = 0  --> used for close pop up window
*/
function openPopUp(url,width,height,top_pos,left_pos,mode)
{
	var myPopUp;
	var prmStr = "";
	
	if(mode == 1)		//open pop up
	{
		//parameter string
		prmStr = "width=" + width + ",height=" + height + ",status=yes,resizable=0,scrollbars=yes";
	
		if(top_pos > 0) prmStr+= ",top=" + top_pos;
		if(left_pos > 0) prmStr+= ",left=" + left_pos;
	
		if(self.closed)
		{
			myPopUp = window.open(url,'PopUp',prmStr);
			if (!myPopup.opener)	myPopup.opener = self;
		}
	}
	else		//close pop up
	{
		if(!self.closed) self.close();
	}
}//end openPopUp()

/* 
	This function is used to selecting/deselecting all those checkbox that have similar name
	It accepts
		CheckBox Name --> Name of check box 
		mode --> 1  --> has to select
				 0  --> has to deselect
	
	It returns no of checkbox that have been selected/unselected			 
*/
function selectAll(chkName,mode)
{
	var obj;
	var len = 0,i = 0;
	
	obj = document.getElementsByName(chkName);
	if(obj.length > 0)
	{
		for(i=0;i<obj.length;i++)
		{
			if(obj[i].type=="checkbox")
			{
				if(mode == 1)	//select
				{
					if(!obj[i].checked) 
					{
						obj[i].checked = true;
						len++;
					}
				}
				else			//un-select
				{
					if(obj[i].checked) 
					{
						obj[i].checked = false;
						len++;
					}
				}
			}
		}
	}
	
	return len;	
}//end selectAll()
				
/*
	This function is used to move/copy the selected record(s) from the left list box to right list box.
	It accepts the following parameters
		left_list_name		--> Name of Left List Box
		right_list_name		--> Name of Right List Box
		left_list_remove	--> Whether moved record(s) has to be deleted from the left list box. It having
								1 --> Remove the moved records from the left list box
								0 --> don't remove	 
	
	It return no of records moved/copied.	
*/	
function shiftToRight(left_list_name,right_list_name,left_list_remove)
{
	var index=0;
	var count=0, i = 0;
	var lobj,robj;
	
	//getting object
	lobj = document.getElementsByName(left_list_name);
	robj = document.getElementsByName(right_list_name);
	
	if(lobj.length > 0)		//left list box exist
	{
		for(i=0;i<lobj[0].length;i++)
		{
			if(lobj[0].options[i].selected)
			{
				robj[0].length++;
				robj[0].options[robj[0].length-1].text=lobj[0].options[i].text;
				robj[0].options[robj[0].length-1].value=lobj[0].options[i].value;
				count++;
			}
			else
			{
				if(left_list_remove == 1)		//whether remove the data from the left list box
				{
					lobj[0].options[index].text = lobj[0].options[i].text;
					lobj[0].options[index++].value = lobj[0].options[i].value;
				}			
			}
		}
		
		//decrement 
		if(left_list_remove == 1)
		{
			for(i=0;i<count;i++)
				lobj[0].length--;
		}
	}
	
	return count;
} //end shiftToright() function	

/*
	This function is used to move the selected record(s) from the right list box to left list box.
	It accepts the following parameters
		left_list_name		--> Name of Left List Box
		right_list_name		--> Name of Right List Box
		left_list_insert	--> Whether selected record(s) in right list box has to be inserted 
								into the left list box. It having
								1 --> Insert the selected records into left list box
								0 --> don't insert	 
	
	It return no of records moved.
	Note >> 1. This function does not move those records whose id is zero
			2. This function in every case removes the records from the right list box
*/	
function shiftToLeft(left_list_name,right_list_name,left_list_insert)
{
	var index=0;
	var count=0, i = 0;
	var lobj,robj;
	
	//getting object
	lobj = document.getElementsByName(left_list_name);
	robj = document.getElementsByName(right_list_name);
	
	if(robj.length > 0)		//right list box exists
	{
		for(i=0;i<robj[0].length;i++)
		{
			if(robj[0].options[i].selected && robj[0].options[i].value!=0) 
			{
				if(left_list_insert == 1)		//whether inserted into left list box
				{
					lobj[0].length++;
					lobj[0].options[lobj[0].length-1].text = robj[0].options[i].text;
					lobj[0].options[lobj[0].length-1].value = robj[0].options[i].value;
				}
				count++;
			}
			else
			{
				robj[0].options[index].text = 	robj[0].options[i].text;
				robj[0].options[index++].value	= robj[0].options[i].value;
			}
		}		
	
		for(i=0;i<count;i++)
			robj[0].length--;
	}
	
	return count;
}	//end shiftToleft() function

/*
	This function is used to move/copy all record(s) from the left list box to right list box.
	It accepts the following parameters
		left_list_name		--> Name of Left List Box
		right_list_name		--> Name of Right List Box
		left_list_remove	--> Whether moved record(s) has to be deleted from the left list box. It having
								1 --> Remove the moved records from the left list box
								0 --> don't remove	 
	
	It return no of records moved/copied.	
*/
function shiftAllToRight(left_list_name,right_list_name,left_list_remove)
{
	var count = 0, i = 0;
	var lobj,robj;
	
	//getting object
	lobj = document.getElementsByName(left_list_name);
	robj = document.getElementsByName(right_list_name);
	
	if(lobj.length > 0)		//left list box exists
	{
		for(i=0;i<lobj[0].length;i++)
		{
			robj[0].length++;
			robj[0].options[robj[0].length-1].text = lobj[0].options[i].text;
			robj[0].options[robj[0].length-1].value = lobj[0].options[i].value;
			count++;
		}
		
		//remove the records from the left list box
		if(left_list_remove == 1) lobj[0].length = 0;
	}
	
	return count;
} //end shiftAllToRight() function	


/*
	This function is used to move all record(s) from the right list box to left list box.
	It accepts the following parameters
		left_list_name		--> Name of Left List Box
		right_list_name		--> Name of Right List Box
		left_list_insert	--> Whether record(s) in right list box has to be inserted 
								into the left list box. It having
								1 --> Insert the records into left list box
								0 --> don't insert	 
	
	It return no of records moved.
	Note >> 1. This function does not move those records whose id is zero
			2. This function in every case removes the records from the right list box
*/
function shiftAllToLeft(left_list_name,right_list_name,left_list_insert)
{
	var lobj,robj;
	var count = 0, i = 0;
	
	//getting object
	lobj = document.getElementsByName(left_list_name);
	robj = document.getElementsByName(right_list_name);
	
	if(robj.length > 0)	//right list box exists
	{
		for(i=0;i<robj[0].length;i++)
		{
			if(robj[0].options[i].value != 0) 
			{
				if(left_list_insert == 1)		//whether inserted into left list box
				{
					lobj[0].length++;
					lobj[0].options[lobj[0].length-1].text = robj[0].options[i].text;
					lobj[0].options[lobj[0].length-1].value = robj[0].options[i].value;
				}
				count++;
			}
			else
			{
				robj[0].options[index].text = 	robj[0].options[i].text;
				robj[0].options[index++].value	= robj[0].options[i].value;
			}
		}		
	
		for(i=0;i<count;i++)
			robj[0].length--;
	}
	
	return count;
} //end shiftAllToLeft() function

/*
	This function is used to select the records in list box. It is required b'coz un-selected
	records are not being set into java beans.
	It accepts the following parameter
	
	It returns no of records that has been selected
	Note >> This function will not select those records whose ID is zero
*/	
function selectListRecords(list_name)
{
	var count = 0,i = 0;
	var lobj;
	
	//getting object
	lobj = document.getElementsByName(list_name);
	
	if(lobj.length > 0)		//list box exists
	{
		for(i=0;i<lobj[0].length;i++)
		{
			if(lobj[0].options[i].value != 0)
			{
				lobj[0].options[i].selected = true;
				count++;
			}
		}
	}
		
	return count;
} //end selectListRecords()

/*
	This function is used to search value in the list box.
	It accepts the following parameters
		list_name	--> Name of the list box
		value		--> value that is to be searched
	
	it selects the matched record and returns true/false	
*/	
function searchInListBox(list_name,value)
{
	flag=0;
	var lobj;
	var i = 0;
	var retValue = false;
		
	if( value != "")
	{ 
		//getting object
		lobj = document.getElementsByName(list_name);
		if(lobj.length > 0)		//list box exists
		{
			for(i=0;i<lobj[0].length;i++)
			{
				listValue = (lobj[0].options[i].text).toUpperCase();
				if (listValue.indexOf((value.toString()).toUpperCase()) == 0)	//matched
				{
					lobj[0].selectedIndex = i;
					retValue = true;
					break;
				}
			}
		}
	}
		
	return retValue;
} //end searchInListBox() function


/*
	This is internal function called from roundValue() function
*/
function pad_with_zeros(rounded_value, decimal_places) {

    // Convert the number to a string
    var value_string = rounded_value.toString()
    
    // Locate the decimal point
    var decimal_location = value_string.indexOf(".")

    // Is there a decimal point?
    if (decimal_location == -1) {
        
        // If no, then all decimal places will be padded with 0s
        decimal_part_length = 0
        
        // If decimal_places is greater than zero, tack on a decimal point
        value_string += decimal_places > 0 ? "." : ""
    }
    else {

        // If yes, then only the extra decimal places will be padded with 0s
        decimal_part_length = value_string.length - decimal_location - 1
    }
    
    // Calculate the number of decimal places that need to be padded with 0s
    var pad_total = decimal_places - decimal_part_length
    
    if (pad_total > 0) {
        
        // Pad the string with 0s
        for (var counter = 1; counter <= pad_total; counter++) 
            value_string += "0"
    }
	
    return value_string
}

/************************************ AJAX FUNCTION ***************************************/
/* */
/******************************************************************************************/
/*
* objXmlHttp - global variable.
* userFunction - global variable.
*/

var objXmlHttp = null;
var userMode = "";

/**
* ajaxFunction is the global function to retrieve data using ajax.
* param 1. myurl - url to be given by the user.
* param 2. mode - there will be hardcoded function i.e. getAjaxResponse(res,mode). This function will be defined by 
the developer. Mode specifies unique value provided by user at the time of calling ajaxfunction().
* This function will recieve the final response(to be defined at user end).
*/

function ajaxFunction(myurl,mode)
{
	userMode = mode;

	// checking browser 
	if (navigator.userAgent.indexOf("Opera")>=0){
		alert("This example doesn't work in Opera"); 
		return; 
	}

	if (navigator.userAgent.indexOf("MSIE")>=0) { 
		var strName="Msxml2.XMLHTTP"
		
		if (navigator.appVersion.indexOf("MSIE 5.5")>=0){
			strName="Microsoft.XMLHTTP"
		} 

		try { 
			objXmlHttp=new ActiveXObject(strName)
			objXmlHttp.onreadystatechange=sendReq
		} 
		catch(e){ 
			alert("Error. Scripting for ActiveX might be disabled");
			return; 
		} 
	}
	else {
		if (navigator.userAgent.indexOf("Mozilla")>=0){
			objXmlHttp=new XMLHttpRequest();
			objXmlHttp.onload=sendReq
			objXmlHttp.onerror=sendReq
		}
	}

	objXmlHttp.open("GET",myurl,true)
	objXmlHttp.send(null)
} 

//internal function called from ajaxFunction() function
function sendReq()
{
	if (objXmlHttp.readyState==4 || objXmlHttp.readyState==200) {
		var res = objXmlHttp.responseText; 
		eval("getAjaxResponse" + '(res' + "," + userMode + ")"); // evel is a keyword - it calls the function passed in it as argument.
	} 
}

/**
	This function will disable/enable the anchor tag based on disable parameter.
	if its value is true then it will disable otherwise it will enable
	url --> in case of enable, url is required
	
	Note >> In anchor tag href field is required for this function 
*/
function disableAnchor(disable,url){

	var obj = document.getElementById("id1");

  if(disable){
    var href = obj.getAttribute("href");
    obj.removeAttribute('href');
    obj.style.color="red";
  }
  else{
    obj.setAttribute('href', url);
    obj.style.color="blue";
  }
  
}
