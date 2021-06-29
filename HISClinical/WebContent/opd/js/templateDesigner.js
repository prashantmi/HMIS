/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */


//  ******************************************************************************
// *** Class Definitions

var tempDesigner=null;

// **** Template Designer ************************

	// Template Designer Constructor
TemplateDesigner = function(_tempType,_rowCount,_colCount,_paraList)
{
	this.template = new Template();
	if(_tempType) this.template.tempType=_tempType;
	if(_rowCount) this.template.rowCount=_rowCount;
	if(_colCount) this.template.colCount=_colCount;
	
}

	


// **** End Template Designer ************************


	// Template
function Template()
{
	this.tempType=0;
	this.rowCount=0;
	this.colCount=0;
	this.paraArray=new Array(0);
	this.curCell="";
	this.templateCode="";
}


	// Parameter
function Parameter()
{
	this.paraId="";
	this.paraName="";
	this.paraParent="";
	this.row="";
	this.col="";
	this.sourceFlag="1";//1-Static & 2-Dynamic
	this.tableQuery="";
	this.isCompulsory="";
	this.isRange="";

	this.paraType="0"; 
	// -1 :none 0:default 1:label 2:textbox 3:textarea 4:combo 5:radio 6:yesno7:checkbox 8:popup 9:file 10:datetime 11:list
	
	this.locationId="0"; 
	// 1 -> Row, 2 -> LefT/Right, 3 -> Top , 0 -> Cell
	// 0 - colspan 1
	// 1- colspan 9 from 1 to 9
	// 2- colspan 4 from 2 to 5 or from 6 to 9
	// 3 - colspan 2  from 2-3, 4-5, 6-7, 8-9
	
	//Label Related
	this.bold=false;
	this.italic=false;
	this.underlined=false;
	this.color="#000000";
	this.paraValue=""; // for Combo,List also
	this.paraOptions="";
	this.align="center";
	this.colspan=1;
	//TextBox Related
	this.maxlength="5";
	this.validationFunction="validateNone";
	this.defaultValue="";
	this.regularExpression="";
	this.format="";
	//TextArea Related	//Combo Related	//List Related	//Radio Related	//Yes-No Related//Check Box Related	
	//Popup Related//File Related//DateTime Related	
	
	this.blank="";
}

// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
function getShowControlWiseProp(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams={ bold:"",italic:"",underlined:"",color:"",align:""};
			break;
		case '2':
			cParams={maxlength:"",isCompulsory:"",defaultValue:"",validationFunction:"",format:"",regularExpression:"",isRange:""};
			break;
		case '3':
			cParams={isCompulsory:"",defaultValue:"",validationFunction:""};
			break;
		case '4':
		case '5':
		case '7':
			cParams={isCompulsory:"",defaultValue:"",sourceFlag:""};
			break;
		case '6':
			cParams={isCompulsory:"",defaultValue:""};
			break;
	}
	return cParams;
}

// Notes -> i) Always keep in mind the sequence bcoz same sequence will be used in fetching prop at run time 
//			ii) Last Prop should not be such as to result in Null bcoz it can create Array Index Oout Of Vound Exception at run time 
function getValueWiseProp(type)
{
	switch(TemplateProp.templateType)
	{
		case '1':
			return getValueWisePropNormal(type);
		case '2':
			return getValueWisePropMatrix(type);
		case '3':
			return getValueWisePropConsent(type);
	}
}

// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
function getValueWisePropNormal(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams={bold:"",italic:"",underlined:"",color:"",align:""};
			break;
		case '2':	
			cParams={bold:"",italic:"",underlined:"",color:"",maxlength:"",isCompulsory:"",defaultValue:"",validationFunction:"",format:"",regularExpression:"",isRange:""};
			break;
		case '3':
			cParams={bold:"",italic:"",underlined:"",color:"",isCompulsory:"",defaultValue:"",validationFunction:""};
			break;
		case '4':
		case '5':
		case '7':
			cParams={bold:"",italic:"",underlined:"",color:"",defaultValue:"",isCompulsory:""};
			break;
		case '6':
			cParams={bold:"",italic:"",underlined:"",color:"",defaultValue:"",isCompulsory:""};
			break;
	}
	return cParams;
}

function getValueWisePropMatrix(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams={bold:"",italic:"",underlined:"",color:"",align:""};
			break;
		case '2':	
			cParams={maxlength:"",isCompulsory:"",defaultValue:"",validationFunction:"",format:"",regularExpression:"",isRange:""};
			break;
		case '3':
			cParams={isCompulsory:"",defaultValue:"",validationFunction:""};
			break;
		case '4':
		case '5':
		case '7':
			cParams={defaultValue:"",isCompulsory:""};
			break;
		case '6':
			cParams={defaultValue:"",isCompulsory:""};
			break;
	}
	return cParams;
}
function getValueWisePropConsent(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams={ bold:"",italic:"",underlined:"",color:"",align:""};
			break;
		case '2':	
			cParams={maxlength:"",isCompulsory:"",defaultValue:"",validationFunction:"",format:"",regularExpression:"",isRange:""};
			break;
		case '3':
			cParams={isCompulsory:"",defaultValue:"",validationFunction:""};
			break;
		case '4':
		case '5':
		case '7':
			cParams={defaultValue:"",isCompulsory:""};
			break;
		case '6':
			cParams={defaultValue:"",isCompulsory:""};
			break;
	}
	return cParams;
}

function getShowTypeWiseProp(type)
{
	switch(TemplateProp.templateType)
	{
		case '1':
			return getShowTypeWisePropNormal(type);
		case '2':
			return getShowTypeWisePropMatrix(type);
		case '3':
			return getShowTypeWisePropConsent(type);
	}
}

function getShowTypeWisePropNormal(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams= {paraValue:"",locationId:"",paraParent:""};
			break;
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			cParams={paraValue:"",bold:"",italic:"",underlined:"",color:"",blank:"",paraParent:""};
			break;
	}
	return cParams;
}

function getShowTypeWisePropMatrix(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams={paraValue:"",locationId:"",paraParent:""};
			break;
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			cParams={paraParent:""};
			break;
	}
	return cParams;
}

function getShowTypeWisePropConsent(type)
{
	var cParams=null;
	switch(type)
	{
		case '0':
			break;
		case '1':
			cParams={paraValue:"",locationId:"",colspan:"",blank:"",paraParent:""};
			break;
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			cParams={paraParent:""};
			break;
	}
	return cParams;
}

function controlCode()
{
	this.bold="<td width='25%' class='tdfonthead'> Bold </td>"+
				"<td width='25%' class='tdfont'> <select id='bold'>"+
				"<option value='false'> False </option><option value='true'> True </option>"+
				"</select> </td>";
	this.italic="<td width='25%' class='tdfonthead'> Italic </td>"+
				"<td width='25%' class='tdfont'> <select id='italic'>"+
				"<option value='false'> False </option><option value='true'> True </option>"+
				"</select> </td>";
	this.underlined="<td width='25%' class='tdfonthead'> Underlined </td>"+
				"<td width='25%' class='tdfont'> <select id='underlined'>"+
				"<option value='false'> False </option><option value='true'> True </option>"+
				"</select> </td>";
	this.color="<td width='25%' class='tdfonthead'> Color </td>"+
				"<td width='25%' class='tdfont'> <select id='color' onchange=\"document.getElementById('colorTabView').style.backgroundColor=this.value;\"' >"+
				"<option value='#000000'> Black </option><option value='#FF0000'> Red </option>"+
				"<option value='#00FF00'> Green </option><option value='#0000FF'> Blue </option>"+
				"<option value='#FFFFFF'> White </option><option value='#DDFFFF'> Yellow </option>"+
				"</select> <label id='colorTabView'>&nbsp;&nbsp;&nbsp;&nbsp;</label></td>";
	
	this.align="<td width='25%' class='tdfonthead'> Alignment </td>"+
				"<td width='25%' class='tdfont'> <select id='align'>"+
				"<option value='center'> Center </option><option value='left'> Left </option><option value='right'> Right </option>"+
				"</select> </td>";


	this.locationId="<td width='25%' class='tdfonthead'> Location </td>"+
				"<td width='25%' class='tdfont'> <select id='locationId' onchange='changedLocation(this);'>"+
				"<option value='1'> Row </option><option value='2'> Left/Right </option>"+
				"<option value='3'> Top </option><option value='0' selected > Cell </option>"+
				"<option value='4'> Customized </option></select><input type='hidden' id='colspan'/></td>";
	
	this.colspan="<td width='25%' class='tdfonthead'> Colspan </td>"+
				"<td width='25%' class='tdfont'> <input id='secondColspan' maxlength='1' onchange='setCustomColspan(this);' onkeypress='return validatePositiveIntegerOnly(this,event);' /> </td>";

	this.paraValue="<td width='25%' class='tdfonthead'> Display Value </td>"+
					"<td width='25%' class='tdfont'> <input id='paraValue' maxlength='50' onkeypress='return notSpecChar(this,event);'/> </td>";
	
	this.maxlength="<td width='25%' class='tdfonthead'> Max Length </td>"+
				"<td width='25%' class='tdfont'> <input id='maxlength' maxlength='3' onkeypress='return validatePositiveIntegerOnly(this,event);' onchange=\"if( document.getElementById('defaultValue') ){ document.getElementById('defaultValue').maxLength=parseInt(this.value);}\"  /> </td>";
				
	this.validationFunction="<td width='25%' class='tdfonthead'> Validation Function </td>"+
				"<td width='25%' class='tdfont'> <select id='validationFunction'>"+
				"<option value='validateNone'> None </option><option value='validateAlphaOnly'> Alphabetic </option>" +
				"<option value='validateNumericOnly'> Numeric </option><option value='validateAlphaNumOnly'> Alphanumeric </option>" +
				"<option value='validateIntegerOnly'> Integers Only </option><option value='validatePositiveIntegerOnly'> Positive Integers Only </option>" +
				"</select> </td>";

	this.width="<td width='25%' class='tdfonthead'> Width </td>"+
				"<td width='25%' class='tdfont'> <input id='width' value='5' maxlength='2' onkeypress='return validatePositiveIntegerOnly(this,event);' /> </td>";

	this.sourceFlag="<td width='25%' class='tdfonthead'> Source Flag </td>"+
				"<td width='25%' class='tdfont'> <select id='sourceFlag' onchange='fillSourceFlag(this.value);'>"+
				"<option value='1' seleced> Static </option><option value='2'> Dynamic </option></select></td>"+
				"<td width='25%' class='tdfonthead'><input type='hidden' id='blank'/></td><td width='25%' class='tdfont'></td>"+
				"</tr><tr><td colspan='4' width='100%' class='tdfonthead'><div id='divSourceFlag'></div></td></tr>";
	
	this.paraParent="<td colspan='4' width='100%' class='tdfonthead'><div id='divParaParent'></div></td></tr>";
	
	this.defaultValue="<td width='25%' class='tdfonthead'> Default Value </td>"+
				"<td width='25%' class='tdfont'> <input id='defaultValue' onkeypress='return notSpecChar(this,event);' /> </td>";
	
	this.blank="<td width='25%' class='tdfonthead'><input type='hidden' id='blank'/></td><td width='25%' class='tdfont'></td>";
	
	this.isCompulsory="<td width='25%' class='tdfonthead'> Is Compulsory </td>"+
						"<td width='25%' class='tdfont'> <select id='isCompulsory'>"+
						"<option value='0' selected='true' > No </option> <option value='1'> Yes </option> "+
						"</select> </td>";

	this.isRange="<td width='25%' class='tdfonthead'> Is Range Based </td>"+
						"<td width='25%' class='tdfont'> <select id='isRange'>"+
						"<option value='0' selected='true' > No </option> <option value='1'> Yes </option>"+
						"</select> </td>";
	this.regularExpression="<td width='25%' class='tdfonthead'> Regular Expression </td>"+
				"<td width='25%' class='tdfont'> <input id='regularExpression' onkeypress='return notSpecCharRegEx(this,event);' /> </td>";
	this.format="<td width='25%' class='tdfonthead'> Format </td>"+
				"<td width='25%' class='tdfont'> <input id='format' onkeypress='return notSpecChar(this,event);'/> </td>";
}

function fillSourceFlag(val)
{
	switch(parseInt(val))
	{
		case 1:
			var pType=document.getElementById('paraType').value;
			var div=document.getElementById('divSourceFlag');
			var h="<table width='100%' border='0' cellspacing='1' cellpadding='0'><tr>";
			// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
			switch(pType)
			{
				case '4':
					h+="<td colspan='4' width='100%' class='tdfonthead'><input type='hidden' id='paraOptions'/><input type='hidden' id='countOptions' value='1' /><table width='100%' id ='tblParaOptions' border='0' cellspacing='1' cellpadding='0'><tr>"+
						"<td width='25%' class='tdfonthead'>Enter Options </td>"+
						"<td width='25%' class='tdfonthead'>Value&nbsp;&nbsp;<input id='optionValue1' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
						"<td width='25%' class='tdfonthead'>Text&nbsp;&nbsp;<input id='optionText1' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
						"<td width='25%' class='tdfonthead'><b>1&nbsp;&nbsp;&nbsp;&nbsp;</b><input type='button' value=' + ' onclick='addOptionRow();'> <input type='button' value=' - ' onclick='subOptionRow();'></td>"+
						"</tr></table></td></tr>";
					break;
				case '5':
					h+="<td colspan='4' width='100%' class='tdfonthead'><input type='hidden' id='paraOptions'/><input type='hidden' id='countOptions' value='1' /><table width='100%' id ='tblParaOptions' border='0' cellspacing='1' cellpadding='0'><tr>"+
						"<td width='25%' class='tdfonthead'>Enter Options </td>"+
						"<td width='25%' class='tdfonthead'>Value&nbsp;&nbsp;<input id='optionValue1' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
						"<td width='25%' class='tdfonthead'>Text&nbsp;&nbsp;<input id='optionText1' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
						"<td width='25%' class='tdfonthead'><b>1&nbsp;&nbsp;&nbsp;&nbsp;</b><input type='button' value=' + ' onclick='addOptionRow();'> <input type='button' value=' - ' onclick='subOptionRow();'></td>"+
						"</tr></table></td></tr>";
					break;
				case '7':
					h+="<td colspan='4' width='100%' class='tdfonthead'><input type='hidden' id='paraOptions'/><input type='hidden' id='countOptions' value='1' /><table width='100%' id ='tblParaOptions' border='0' cellspacing='1' cellpadding='0'><tr>"+
						"<td width='25%' class='tdfonthead'>Enter Option </td>"+
						"<td width='25%' class='tdfonthead'>Value&nbsp;&nbsp;<input id='optionValue1' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
						"<td width='25%' class='tdfonthead'>Text&nbsp;&nbsp;<input id='optionText1' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
						"<td width='25%' class='tdfonthead'></td>"+
						"</tr></table></td>";
					break;
			}
			h+="</tr></table>";
			div.innerHTML=h;
			break;
		case 2:
			var pType=document.getElementById('paraType').value;
			var div=document.getElementById('divSourceFlag');
			var h="<table width='100%' border='0' cellspacing='1' cellpadding='0'><tr>";
			h+="<td width='25%' class='tdfonthead'> Table Query </td>"+
				"<td width='75%' class='tdfont'><input id='tableQuery' maxlength='500' onkeypress='return notSpecChar(this,event);' size='90'/></td></tr>"+
				"<tr><td colspan='2' class='tdfont'>"+
				"<p>The query should be complete in itself with two Facts keeping in mind :   "+
				"<br>i) Query should return two columns in which First Column will map to 'Value' field and Second Column will map to 'Text' field of the Combo"+
				"<br>ii) There should be one '?' mark in Where Clause where Hospital Code will be placed.   "+
				"<br>Syntax:  (e.g.)  SELECT &lt;Value-Field&gt;,&lt;Text-Filed&gt; FROM &lt;Table-List&gt; WHERE &lt;Condition Before&gt; &lt;Hospital Code = ? &gt; &lt;Condition After&gt;  "+
				"<br> Ex. Department List ->  SELECT GNUM_DEPT_CODE, INITCAP(GSTR_DEPT_NAME) FROM GBLT_DEPARTMENT_MST WHERE  "+
				" GNUM_HOSPITAL_CODE=? AND GNUM_ISVALID=1 ORDER BY INITCAP(GSTR_DEPT_NAME) </p></td>";
			h+="</tr></table>";
			div.innerHTML=h;
			break;
	}
}

function SetSourceFlag(pObj)
{
 if(pObj.paraType=="4" || pObj.paraType=="5" || pObj.paraType=="7")
 {
	fillSourceFlag(pObj.sourceFlag);
	switch(pObj.sourceFlag)
	{
		case '1':
			document.getElementById('paraOptions').value=pObj.paraOptions;
			SetParaOptions(pObj);
			break;
		case '2':
			document.getElementById('tableQuery').value=pObj.tableQuery;
			break;
	}
 }
}


function setCorresspondingOption(txt)
{
	var name=txt.id;
	if(/Value/.test(name)) name=name.replace(/Value/,'Text');
	else if(/Text/.test(name))name=name.replace(/Text/,'Value');
	if(document.getElementById(name).value=="")
		document.getElementById(name).value=txt.value; 
}

function setCustomColspan(txt)
{
	if(parseInt(txt.value)>0)
	{
		var val=txt.value;
		var col=document.getElementById('col').value;
		if( (parseInt(col)+parseInt(val)) > 9 )
		{
			alert(" Colspan can be Maximum "+(9-col+1)+" for this Cell ...");
			txt.value=9- parseInt(col) +1;
		}
		else
			txt.value=parseInt(val);
		
		document.getElementById('locationId').value="4";
		document.getElementById('colspan').value=txt.value;
		
		changedLocation(document.getElementById('locationId'));
		
	}
	else
	{
		txt.value="0";
	}
}

function addOptionRow()
{
	var count = document.getElementById('countOptions').value;

	var h="";
	document.getElementById('countOptions').value=parseInt(count)+1;
	
	count++;
	
	var m="<td width='25%' class='tdfonthead'></td>"+
		"<td width='25%' class='tdfonthead'>Value&nbsp;&nbsp;<input id='optionValue"+count+"' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
		"<td width='25%' class='tdfonthead'>Text&nbsp;&nbsp;<input id='optionText"+count+"' onchange='setCorresspondingOption(this);' onkeypress='return notSpecChar(this,event);' /></td>"+
		"<td width='25%' class='tdfonthead'><b>"+count+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>";
			
	var n="trOptionRow"+count;
	if(document.getElementById(n))
		document.getElementById(n).innerHTML=m;
	else
	{
		var val= new Array(count-1);
		var txt= new Array(count-1);
		for(var i=1;i<count;i++)
		{
			val[i-1]=document.getElementById('optionValue'+i).value;
			txt[i-1]=document.getElementById('optionText'+i).value;
		}
		h="<tr id='trOptionRow"+count+"'>"+m+"</tr>";
		document.getElementById('tblParaOptions').innerHTML+=h;
		for(var i=1;i<count;i++)
		{
			document.getElementById('optionValue'+i).value=val[i-1];
			document.getElementById('optionText'+i).value=txt[i-1];
		}
	}
}

function subOptionRow()
{
	var count = document.getElementById('countOptions').value;
	if(parseInt(count) > 1)
	{
		var n="trOptionRow"+count;
		document.getElementById(n).innerHTML="";
		count--;
		document.getElementById('countOptions').value=count;
	}
}

function validateSourceFlag()//Source Flag & Default Value
{
	if(document.getElementById('sourceFlag'))
	{
		switch(document.getElementById('sourceFlag').value)
		{
			case '1':
				return validateParaOptions();
				
				break;
			case '2':
				if(document.getElementById('tableQuery').value=="")
				{
					alert("Enter Table Query ...");
					document.getElementById('tableQuery').focus();
					return false;
				}
				if(document.getElementById('defaultValue').value!="")
					alert("Ensure Once Again that you entered the Correct Default Value ... ");
				break;
		}
	}
	//Validate Default Value
	if(document.getElementById('defaultValue') && document.getElementById('defaultValue').value!="")
	{
		switch(document.getElementById('paraType').value)
		{
			case '2'://TextBox
				if(document.getElementById('defaultValue').value.length > document.getElementById('maxlength').value)  
				{
					alert("Default Value can't be larger than the Entered Max Length ...");
					document.getElementById('defaultValue').focus();
					return false;
				}
			case '3'://TextArea
				var fun=document.getElementById('validationFunction').value;
				if(fun !="")
				{
					var funFlag=true;
					switch(fun)
					{
						case 'validateNone':
							break;
						case 'validateAlphaOnly':
							funFlag=validateAlphaValue(document.getElementById('defaultValue').value);
							break;
						case 'validateNumericOnly':
							funFlag=validateNumericValue(document.getElementById('defaultValue').value);
							break;
						case 'validateAlphaNumOnly':
							funFlag=validateAlphaNumValue(document.getElementById('defaultValue').value);
							break;
						case 'validateIntegerOnly':
							funFlag=validateIntegerValue(document.getElementById('defaultValue').value);
							break;
						case 'validatePositiveIntegerOnly':
							funFlag=validatePositiveIntegerValue(document.getElementById('defaultValue').value);
							break;
					}
					/*fun=fun.replace(/Only/,'Value');
					alert(fun);
					alert(document.forms[0][fun]);*/
					if(funFlag==false)
					{
						alert("Entered Default Value is Invalid ...");
						document.getElementById('defaultValue').focus();
						return false;
					}
					if(document.getElementById('paraType').value=="2" && !validateRegularExpression(document.getElementById('defaultValue').value,document.getElementById('regularExpression').value))
					{
						alert("Entered Default Value should satisfy the Regular Expression ...");
						document.getElementById('defaultValue').focus();
						return false;
					}
				}
				break;
			case '6':
				if(document.getElementById('defaultValue').value!='yes' && document.getElementById('defaultValue').value!='no')
				{
					alert("Deafault Value can only be either yes or no ...");
					document.getElementById('defaultValue').focus();
					return false;
				}
				break;
		}
	}
	return true;
}

function validateParaOptions()
{
	if(document.getElementById('paraOptions'))
	{
		var h="";
		var dv=document.getElementById('defaultValue').value;
		var dvFlag=false;
		for (var i=1;i<=document.getElementById('countOptions').value;i++)
		{
			if(dv=="" || document.getElementById('optionValue'+i).value==dv)
				dvFlag=true;
			if(document.getElementById('optionValue'+i).value=="")
			{
				alert("Enter Value for Option Row "+i+" ...");
				document.getElementById('optionValue'+i).focus();
				return false;
			}
			if(document.getElementById('optionText'+i).value=="")
			{
				alert("Enter Text for Option Row "+i+" ...");
				document.getElementById('optionText'+i).focus();
				return false;
			}
			h+=document.getElementById('optionValue'+i).value+":"+document.getElementById('optionText'+i).value+"~";
		}
		if(h!="")h=h.substr(0,h.length-1);
		document.getElementById('paraOptions').value=h;

		if(dvFlag==false)
		{
			alert("Default Value should be one of the Entered Para Option Value ...");
			document.getElementById('defaultValue').focus();
			return false;
		}
	}
	return true;
}

function SetParaOptions(pObj)
{
	if(pObj.paraOptions!="")
	{
		var arr=pObj.paraOptions.split("~");
		for (var i=1;i<arr.length;i++) addOptionRow();
		for (var i=1;i<=arr.length;i++)
		{
			document.getElementById('optionValue'+i).value=arr[i-1].split(":")[0];
			document.getElementById('optionText'+i).value=arr[i-1].split(":")[1];
		}
	}
}

var ControlCodes=new controlCode();

// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
function getControlCodeFromClass(cName,type)
{
	return ControlCodes[cName];
}

function generateTemplate()
{
	//tblTemplate
	var h="";
	var paraList=TemplateProp.paraArray;
		for(var i=0;i<TemplateProp.totalRows;i++)
		{
			h+="\n\t<tr>";
			for(var j=0;j<9;j++)
			{
				h+=getViewControlCode(paraList[i][j]);
			}
			h+="\n\t</tr>";
		}
		h+="\n</table>";
	//alert(h);
	document.getElementById('tblTemplate').innerHTML=h;
}

function getViewControlCode(pObj)
{
	var h="";
	switch(TemplateProp.templateType)
	{
		case '1':
			h=getViewControlCodeNormal(pObj);
			break;
		case '2':
			h=getViewControlCodeMatrix(pObj);
			break;
		case '3':
			h=getViewControlCodeConsent(pObj);
			break;
	}
	return h;
}

function getViewControlCodeNormal(pObj)
{
	var h="";
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list	
	switch(pObj.paraType)
	{
		case '-1':
					var m="";
					return m;
					break;
		case '0' :
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='center' >";
					h+="\n\t\t\t\t\t\tClick Here";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '1':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%' colspan='"+pObj.colspan+"'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='"+pObj.align+"'>";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '2':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<input type='text' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' readonly='readonly' maxlength='"+pObj.maxlength+"' value='"+pObj.defaultValue+"' size='10' onkeypress='return ("+pObj.validationFunction+"(this,event) && notSpecChar(this,event) )' onchange=\"validatePARAMETERRegExp(this,event,'"+pObj.format+"','"+pObj.regularExpression+"');\" />";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '3':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<textarea name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' readonly='readonly' onkeypress='return "+pObj.validationFunction+"(this,event)' rows='1' cols='10' >"+pObj.defaultValue+"</textarea>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '4':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<select name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' >";
					h+="\n\t\t\t\t\t\t\t<option value=''>Select Value</option>";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split("&");
						for(var j=0;j<arrTwo.length;j++)
						{
							h+="\n\t\t\t\t\t\t\t<option value='"+arrTwo[j].split(":")[0]+"' ";
							if(arrTwo[j].split(":")[0]==pObj.defaultValue)
								h+=" selected='true' ";
							h+=">"+arrTwo[j].split(":")[1]+"</option>";
						}
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<option value='2' selected='true' >Dynamic Data</option>";
					h+="\n\t\t\t\t\t\t</select>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '5':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split("~");
						for(var j=0;j<arrTwo.length;j++)
						{
							h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' value='"+arrTwo[j].split(":")[0]+"' ";
							if(arrTwo[j].split(":")[0]==pObj.defaultValue)
								h+=" checked='true' ";	
							h+=">"+arrTwo[j].split(":")[1]+"</input>";
							if( (j+1) <arrTwo.length) h+="<br>";
						}
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<div align='center'><b> Dynamic Data </b></div>";
					
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '6':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.defaultValue=='yes')
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='yes'  checked='true' >Yes</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='yes'>Yes</input>";
					if(pObj.defaultValue=='no')
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='no'  checked='true' >No</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='no'>No</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '7':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='right' >";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";

					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split(":");
						h+="\n\t\t\t\t\t\t\t<input type='checkbox' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' value='"+arrTwo[0]+"' ";
						if(arrTwo[0]==pObj.defaultValue)
							h+=" checked='true' ";	
						h+=">"+arrTwo[1]+"</input>";
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<div align='center'><b> Dynamic Data </b></div>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
	}
	//alert("CODE ->"+h);
	return h;
}

function getViewControlCodeMatrix(pObj)
{
	var h="";
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list	
	switch(pObj.paraType)
	{
		case '-1':
					var m="";
					return m;
					break;
		case '0' :
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='center' >";
					h+="\n\t\t\t\t\t\tClick Here";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '1':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%' colspan='"+pObj.colspan+"'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='"+pObj.align+"'>";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '2':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<input type='text' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' readonly='readonly' maxlength='"+pObj.maxlength+"' value='"+pObj.defaultValue+"' size='10' onkeypress='return ("+pObj.validationFunction+"(this,event) && notSpecChar(this,event))' onchange=\"validatePARAMETERRegExp(this,event,'"+pObj.format+"','"+pObj.regularExpression+"');\" />";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '3':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<textarea name='PARA"+pObj.paraId+"' readonly='readonly' id='C:"+pObj.isCompulsory+"' onkeypress='return ("+pObj.validationFunction+"(this,event) && notSpecChar(this,event))' rows='1' cols='10' >"+pObj.defaultValue+"</textarea>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '4':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<select name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' >";
					h+="\n\t\t\t\t\t\t\t<option value=''>Select Value</option>";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split("~");
						for(var j=0;j<arrTwo.length;j++)
						{
							h+="\n\t\t\t\t\t\t\t<option value='"+arrTwo[j].split(":")[0]+"' ";
							if(arrTwo[j].split(":")[0]==pObj.defaultValue)
								h+=" selected='true' ";
							h+=">"+arrTwo[j].split(":")[1]+"</option>";
						}
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<option value='2' selected='true' >Dynamic Data</option>";
					h+="\n\t\t\t\t\t\t</select>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '5':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split("~");
						for(var j=0;j<arrTwo.length;j++)
						{
							h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' value='"+arrTwo[j].split(":")[0]+"' ";
							if(arrTwo[j].split(":")[0]==pObj.defaultValue)
								h+=" checked='true' ";	
							h+=">"+arrTwo[j].split(":")[1]+"</input>";
							if( (j+1) < arrTwo.length) h+="<br>";
						}
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<div align='center'><b> Dynamic Data </b></div>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '6':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.defaultValue=='yes')
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='yes'  checked='true' >Yes</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='yes'>Yes</input>";
					if(pObj.defaultValue=='no')
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='no'  checked='true' >No</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='no'>No</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '7':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split(":");
						h+="\n\t\t\t\t\t\t\t<input type='checkbox' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' value='"+arrTwo[0]+"' ";
						if(arrTwo[0]==pObj.defaultValue)
							h+=" checked='true' ";	
						h+=">"+arrTwo[1]+"</input>";
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<div align='center'><b> Dynamic Data </b></div>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
	}
	return h;
}

function getViewControlCodeConsent(pObj)
{
	var h="";
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list	
	switch(pObj.paraType)
	{
		case '-1':
					var m="";
					return m;
					break;
		case '0' :
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='center' >";
					h+="\n\t\t\t\t\t\tClick Here";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '1':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%' colspan='"+pObj.colspan+"'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='"+pObj.align+"'>";
					h+="\n\t\t\t\t\t\t";
					if(pObj.bold=='true')h+="<b>";
					if(pObj.italic=='true')h+="<i>";
					if(pObj.underlined=='true')h+="<u>";
					h+="<font color='"+pObj.color+"'>"+pObj.paraValue+"</font>";
					if(pObj.underlined=='true')h+="</u>";
					if(pObj.italic=='true')h+="</i>";
					if(pObj.bold=='true')h+="</b>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '2':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<input type='text' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' readonly='readonly' maxlength='"+pObj.maxlength+"' value='"+pObj.defaultValue+"' size='10' onkeypress='return ("+pObj.validationFunction+"(this,event) && notSpecChar(this,event))' onchange=\"validatePARAMETERRegExp(this,event,'"+pObj.format+"','"+pObj.regularExpression+"');\" />";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '3':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<textarea name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' readonly='readonly' onkeypress='return ("+pObj.validationFunction+"(this,event) && notSpecChar(this,event))' rows='1' cols='10' >"+pObj.defaultValue+"</textarea>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '4':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					h+="\n\t\t\t\t\t\t<select name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' >";
					h+="\n\t\t\t\t\t\t\t<option value=''>Select Value</option>";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split("~");
						for(var j=0;j<arrTwo.length;j++)
						{
							h+="\n\t\t\t\t\t\t\t<option value='"+arrTwo[j].split(":")[0]+"' ";
							if(arrTwo[j].split(":")[0]==pObj.defaultValue)
								h+=" selected='true' ";
							h+=">"+arrTwo[j].split(":")[1]+"</option>";
						}
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<option value='2' selected='true' >Dynamic Data</option>";
					h+="\n\t\t\t\t\t\t</select>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '5':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split("~");
						for(var j=0;j<arrTwo.length;j++)
						{
							h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' value='"+arrTwo[j].split(":")[0]+"' ";
							if(arrTwo[j].split(":")[0]==pObj.defaultValue)
								h+=" checked='true' ";	
							h+=">"+arrTwo[j].split(":")[1]+"</input>";
							if((j+1) < arrTwo.length) h+="<br>";
						}
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<div align='center'><b> Dynamic Data </b></div>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '6':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.defaultValue=='yes')
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='yes'  checked='true' >Yes</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='yes'>Yes</input>";
					if(pObj.defaultValue=='no')
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='no'  checked='true' >No</input>";
					else
						h+="\n\t\t\t\t\t\t\t<input type='radio' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"' value='no'>No</input>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
		case '7':
					h+="\n\t\t<td align='center' width='"+(100/9)+"%'>";
					h+="\n\t\t\t<table width='100%' id='"+pObj.row+"&"+pObj.col+"' onclick='CellOnFocus(this)' border='0' cellspacing='1' cellpadding='0'>";
					h+="\n\t\t\t\t<tr>";
					h+="\n\t\t\t\t\t<td id='tdTemplate@"+pObj.row+"&"+pObj.col+"' align='left' >";
					if(pObj.sourceFlag=="1")
					{
						var arrTwo=pObj.paraOptions.split(":");
						h+="\n\t\t\t\t\t\t\t<input type='checkbox' name='PARA"+pObj.paraId+"' id='C:"+pObj.isCompulsory+"&R:"+pObj.isRange+"' value='"+arrTwo[0]+"' ";
						if(arrTwo[0]==pObj.defaultValue)
							h+=" checked='true' ";	
						h+=">"+arrTwo[1]+"</input>";
					}
					else if(pObj.sourceFlag=="2")
						h+="\n\t\t\t\t\t\t\t<div align='center'><b> Dynamic Data </b></div>";
					h+="\n\t\t\t\t\t</td>";
					h+="\n\t\t\t\t</tr>";
					h+="\n\t\t\t</table>";
					h+="\n\t\t</td>";
					break;
	}
	return h;
}


// *** End Class Definitions
//  ******************************************************************************


//  ******************************************************************************
// Other Functions

function changedLocation(cbo)
{
	var rw=document.getElementById('row').value;
	var cl=document.getElementById('col').value;
	
	var colspanCtrl=document.getElementById('colspan');
	
	// 1 -> Row, 2 -> LefT/Right, 3 -> Top , 0 -> Cell
	switch(cbo.value)
	{
		case '1':
				colspanCtrl.value=9;
				if(document.getElementById('secondColspan'))
					document.getElementById('secondColspan').value="0";
				break;
		case '2':
				if(cl!=1)
				{	
					colspanCtrl.value=4;	
					if(document.getElementById('secondColspan')) 
						document.getElementById('secondColspan').value="0";	
				}
				else if(cl==1)	{ alert(" Column 1 can't be either Left or Right..."); cbo.value='0';	}
				break;
		case '3':
				if(cl!=1)
				{	
					colspanCtrl.value=2;
					if(document.getElementById('secondColspan'))
						document.getElementById('secondColspan').value="0";	
				}
				else if(cl==1)	{ alert(" Column 1 can't be either Top..."); cbo.value='0';	}
				break;
		case '4':
				if(TemplateProp.templateType!="3")
				{ alert(" Customized Location is only for Consent Type ..."); cbo.value='0';	}
				else
				{
					if( parseInt(document.getElementById('secondColspan').value)<=0 )
						document.getElementById('secondColspan').value="0";
					document.getElementById('secondColspan').focus();
				}
				break;
		case '0':
				colspanCtrl.value=1;
				if(document.getElementById('secondColspan'))
					document.getElementById('secondColspan').value="0";
				break;
	}
}

function copyParameterTo(pObj,r,c)
{
	var obj=new Parameter();
	for ( var F in obj)
		TemplateProp.paraArray[parseInt(r)-1][parseInt(c)-1][F]=pObj[F];
	TemplateProp.paraArray[parseInt(r)-1][parseInt(c)-1].row=r;
	TemplateProp.paraArray[parseInt(r)-1][parseInt(c)-1].col=c;
	document.getElementById('row').value=r;
	document.getElementById('col').value=c;
	TemplateProp.curCell=r+"&"+c;
	return TemplateProp.paraArray[parseInt(r)-1][parseInt(c)-1];
}

function applyLocation(pObj,old,oldColSpan)
{
	var rw=parseInt(pObj.row);
	var cl=parseInt(pObj.col);
	var loc=pObj.locationId;

	// 1 -> Row, 2 -> LefT/Right, 3 -> Top , 0 -> Cell , 4 -> Customized
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
	switch(loc)
	{
		case '1':
				pObj.colspan=9;
				pObj=copyParameterTo(pObj,rw,1);
				for (var k=2;k<10;k++)
					hideParaObj(rw-1,k-1);
				break;
		case '2':
				if(cl==1)	{	pObj.colspan=1;	pObj.locationId='0';	applyLocation(pObj,old,oldColSpan);	break;	}
				if(old=='1')
					for (var k=2;k<10;k++)
						clearParaObj(rw-1,k-1);
				else if(old=='4')
					for (var k=1;k<oldColSpan;k++)
						clearParaObj(rw-1,cl+k-1);
						
				if(cl>1 && cl<=5)	
				{
					pObj.colspan=4;
					pObj=copyParameterTo(pObj,rw,2);
					for (var k=3;k<6;k++)
						hideParaObj(rw-1,k-1);
				}
				else if(cl>5 && cl<=9)
				{
					pObj.colspan=4;
					pObj=copyParameterTo(pObj,rw,6);
					for (var k=7;k<10;k++)
						hideParaObj(rw-1,k-1);
				}
				break;
		case '3':
				if(cl==1)	{	pObj.colspan=1;	pObj.locationId='0';	applyLocation(pObj,old,oldColSpan);	break;	}
				if(old=='1')
					for (var k=2;k<10;k++)
						clearParaObj(rw-1,k-1);
				else if(old=='2')
				{
					if(cl>1 && cl<=5)	
						for (var k=3;k<6;k++)
							clearParaObj(rw-1,k-1);
					else if(cl>5 && cl<=9)
						for (var k=7;k<10;k++)
							clearParaObj(rw-1,k-1);
				}					
				else if(old=='4')
					for (var k=1;k<oldColSpan;k++)
						clearParaObj(rw-1,cl+k-1);

				var k=0;
				if(cl==1)	break;
				else if(cl<=3) k=2;
				else if(cl<=5) k=4;
				else if(cl<=7) k=6;
				else if(cl<=9) k=8;
				pObj.colspan=2;
				pObj=copyParameterTo(pObj,rw,k);
				hideParaObj(rw-1,k);
				break;
		case '4':
				if(old=='1')
					for (var k=2;k<10;k++)
						clearParaObj(rw-1,k-1);
				else if(old=='2')
				{
					if(cl>1 && cl<=5)	
						for (var k=3;k<6;k++)
							clearParaObj(rw-1,k-1);
					else if(cl>5 && cl<=9)
						for (var k=7;k<10;k++)
							clearParaObj(rw-1,k-1);
				}
				else if(old=='3')
				{
					var k=0;
					if(cl==1)	break;
					else if(cl<=3) k=2;
					else if(cl<=5) k=4;
					else if(cl<=7) k=6;
					else if(cl<=9) k=8;
					clearParaObj(rw-1,k);
				}
				else if(old=='4')
					for (var k=1;k<oldColSpan;k++)
						clearParaObj(rw-1,cl+k-1);
				for (var k=1;k<pObj.colspan;k++)
					hideParaObj(rw-1,cl+k-1);
				break;
		case '0':
				if(old=='1')
					for (var k=2;k<10;k++)
						clearParaObj(rw-1,k-1);
				else if(old=='2')
				{
					if(cl>1 && cl<=5)	
						for (var k=3;k<6;k++)
							clearParaObj(rw-1,k-1);
					else if(cl>5 && cl<=9)
						for (var k=7;k<10;k++)
							clearParaObj(rw-1,k-1);
				}
				else if(old=='3')
				{
					var k=0;
					if(cl==1) break;
					else if(cl<=3) k=2;
					else if(cl<=5) k=4;
					else if(cl<=7) k=6;
					else if(cl<=9) k=8;
					clearParaObj(rw-1,k);
				}
				else if(old=='4')
					for (var k=1;k<oldColSpan;k++)
						clearParaObj(rw-1,cl+k-1);
				pObj.colspan=1;
				break;
	}
}

function setgetParameter(obj,e)
{
	var t=document.getElementById('paraType').value;
 if(!( TemplateProp.templateType=="2" &&  t!="-1" && t!="0" && t!="1" ))
 {
	//alert("OnKeyPress");
	//alert(e.keyCode); 
	var div=document.getElementById('divParaList');
	if(div.style.display=="none")
	{
		var o=obj;
		var l=0,t=0;
		while(o.offsetParent)
		{
			l+=o.offsetLeft;
			t+=o.offsetTop;
			o=o.offsetParent;
		}
		div.style.left=l;
		div.style.top=t+obj.offsetHeight;
		div.style.pixelWidth=obj.style.pixelWidth;
		div.style.display="inherit";	
	}
	var lst=document.getElementById('paraList');
	var i=0;
	//alert(lst.selectedIndex);
	switch(e.keyCode)
	{
		case 13://Enter Key
			if(lst.selectedIndex>=0) i=lst.selectedIndex;
			obj.value=lst.options[i].text;
			document.getElementById('paraId').value=lst.options[i].value;
			div.style.display="none";
			break;
		case 38: //Arrow Up
			if(lst.selectedIndex>=0)	i=lst.selectedIndex - 1;
			else i=lst.options.length - 1;			
			if(i<lst.options.length)
			{				
				if(lst.selectedIndex>=0) lst.options[i+1].selected=false;
				lst.selectedIndex=i;
				lst.options[i].selected=true;
				obj.value=lst.options[i].text;
				document.getElementById('paraId').value=lst.options[i].value;
			}
			break;
		case 40: //Arrow Down
			if(lst.selectedIndex>=0)	i=lst.selectedIndex+1;
			else i=0;			
			if(i<lst.options.length)
			{
				if(lst.selectedIndex>=0) lst.options[i-1].selected=false;	
				lst.selectedIndex=i;
				lst.options[i].selected=true;
				obj.value=lst.options[i].text;
				document.getElementById('paraId').value=lst.options[i].value;
			}
			break;
			
		default :
			
			if(lst.selectedIndex>=0)	i=lst.selectedIndex;
			else i=0;
			
			var flag=false,len=1,fm=0;
			do
			{
				a: for(j=fm;j<lst.options.length;j++)
					if(obj.value.substr(0,len).toUpperCase()==lst.options[j].text.substr(0,len).toUpperCase())
					{
						flag=true;
						fm=j;
						break a;
					}
				len++;				
			}while(flag && len < obj.value.length);
			if(lst.selectedIndex>=0)	lst.options[lst.selectedIndex].selected=false;
			lst.selectedIndex=fm;
			if(flag)	lst.options[fm].selected=true;
			
	}
 }	
}

function paraNameBlur(obj,e)
{
	document.getElementById('divParaList').style.display="none";
}



// End Other
//  ******************************************************************************



//  ******************************************************************************
// *** Global Variables Declaration

var TemplateProp=new Template();

// *** End Global Variables Declaration
//  ******************************************************************************

//  ******************************************************************************
// *** Global Variables Declaration


function addTemplateRow()
{
	if(document.getElementsByName('noOfRows')[0].value<99)
	{
		if(TemplateProp.totalRows==0)		setTemplateFirstTime();
		else
			if(document.getElementById('row'))
				applyTemplateChanges(TemplateProp.curCell);
		
		document.getElementsByName('noOfRows')[0].value++;
		TemplateProp.totalRows=parseInt(document.getElementsByName('noOfRows')[0].value);

		var oldParaList=TemplateProp.paraArray;
		var l=TemplateProp.totalRows;
		TemplateProp.paraArray=new Array(l);

		for(var i=0;i<l;i++)
		{
			if(i!=(l-1))
			{
				var temp = new Array(9);
				for(var j=0;j<9;j++)	temp[j]=oldParaList[i][j];
				TemplateProp.paraArray[i]=temp;
			}
			else
			{
				var temp = new Array(9);
				for(var j=0;j<9;j++)
				{
					temp[j]=new Parameter();
					temp[j].row=i+1;
					temp[j].col=j+1;
				}
				TemplateProp.paraArray[i]=temp;
			}
		}
		generateTemplate();
	}
}

function minusTemplateRow()
{
	if(document.getElementsByName('noOfRows')[0].value>1)
	{
		if(TemplateProp.totalRows!=0)
			if(document.getElementById('row'))
				if(document.getElementById('row').value==TemplateProp.totalRows)
				{
					TemplateProp.curCell="";
					document.getElementById('paraSetter').innerHTML="";
				}
				else
					applyTemplateChanges(TemplateProp.curCell);
		document.getElementsByName('noOfRows')[0].value--;
		if(TemplateProp.totalRows==0)		setTemplateFirstTime();
		TemplateProp.totalRows=parseInt(document.getElementsByName('noOfRows')[0].value);
		
		generateTemplate();
	}
}


// *** End Global Variables Declaration
//  ******************************************************************************



//  ******************************************************************************
function CellOnFocus(sC) // sC -> selectCell
{
	var id=sC.id;	// Id of Cell in "Row&Col" format 
	var pC=TemplateProp.curCell;

	if(pC != "" && pC!=id)
	{
		if(! applyTemplateChanges(pC)) return;
		document.getElementById(pC).border="0";
		document.getElementById(pC).borderColor="";
	}
	else if( pC==id)
	{
		document.getElementById(pC).border="1";
		document.getElementById(pC).borderColor="#0000FF";
		return;
	}
		

	if( TemplateProp.curCell == pC ) TemplateProp.curCell=sC.id;
	// **** Focusing Clicked Cell
	sC=document.getElementById(TemplateProp.curCell);
	sC.border="1";
	sC.borderColor="#0000FF";

	if(TemplateProp.totalRows==0)		setTemplateFirstTime();
	
	//Setting & Getting Element Parameter Object
	var paramObj=getSetParaObj(sC.id);
	
	// For Showing and Changing the Parameter Setter
	setParameterSetter(paramObj);
}

function getSetParaObj(id)
{
	var row=id.split("&")[0];
	var col=id.split("&")[1];

	var paraObj=TemplateProp.paraArray[parseInt(row)-1][parseInt(col)-1];

	if(paraObj==null)
	{
		paraObj=new Parameter();
		paraObj.row=row;
		paraObj.col=col;
		TemplateProp.paraArray[parseInt(row)-1][parseInt(col)-1]=paraObj;
	}
	return paraObj;
}

function clearParaObj(r,c)
{
	var obj=new Parameter();
	for ( var F in obj)
		TemplateProp.paraArray[r][c][F]=obj[F];
	TemplateProp.paraArray[r][c].paraType="0";
	TemplateProp.paraArray[r][c].row=parseInt(r)+1;
	TemplateProp.paraArray[r][c].col=parseInt(c)+1;
}

function hideParaObj(r,c)
{
	var obj=new Parameter();
	for ( var F in obj)
		TemplateProp.paraArray[r][c][F]=obj[F];
	TemplateProp.paraArray[r][c].paraType="-1";
	TemplateProp.paraArray[r][c].row=parseInt(r)+1;
	TemplateProp.paraArray[r][c].col=parseInt(c)+1;
}

function setTemplateFirstTime()
{
	//alert("Rows Control : "+document.getElementsByName('noOfRows')[0]);
	//alert("Total Rows : "+TemplateProp.totalRows);
	//alert("Template Code : <br>"+TemplateProp.templateCode);

	TemplateProp.templateType=document.getElementsByName('templateType')[0].value;
	TemplateProp.totalRows=parseInt(document.getElementsByName('noOfRows')[0].value);
	TemplateProp.templateCode=document.getElementById('tblTemplate').innerHTML;
	
	var l=parseInt(TemplateProp.totalRows);
	TemplateProp.paraArray=new Array(l);
	for(var i=0;i<l;i++)
	{
		var temp = new Array(9);
		for(var j=0;j<9;j++)
		{
			temp[j]=new Parameter();
			temp[j].row=i+1;
			temp[j].col=j+1;
		}
		TemplateProp.paraArray[i]=temp;
	}

	//alert("Para Array : "+TemplateProp.paraArray);
}

function applyTemplateChanges(id)
{
	if(document.getElementById('paraId'))
	{
		var mode=false;
		switch(TemplateProp.templateType)
		{
			case "1":
				mode= applyTemplateChangesNormal(id);
				break;
			case "2":
				mode= applyTemplateChangesMatrix(id);
				break;
			case "3":
				mode= applyTemplateChangesConsent(id);
				break;
		}
		// Generate Template
		if(mode)
			generateTemplate();
		return mode;
	}
}

function applyTemplateChangesNormal(id)
{
	//-- Validation of Entries
	if(document.getElementById('paraType').value!="-1" && document.getElementById('paraType').value!="0" )
		//-- Parameter Name for a Valid Paramter is mandatory
		if(document.getElementById('paraName').value=="")
		{
			alert("Enter Parameter Name ... ");
			document.getElementById('paraName').focus();
			return false;
		}
	
		//-- Checking Duplicate Parameter Name 
	if(document.getElementById('paraName').value!="")
	{
		var someFlag=false;
		for(var i=0;i<document.getElementById('paraList').options.length;i++)
			if( document.getElementById('paraName').value.toUpperCase()== document.getElementById('paraList').options[i].text.toUpperCase())
			{
				document.getElementById('paraName').value=document.getElementById('paraList').options[i].text;
				document.getElementById('paraId').value=document.getElementById('paraList').options[i].value;
				someFlag=true;
				break;
			}
		if(someFlag==false)document.getElementById('paraId').value=""; 

		a: for(var i=0;i<TemplateProp.totalRows;i++)
			for(var j=0;j<9;j++)
				if( TemplateProp.paraArray[i][j].paraType != "0" && TemplateProp.paraArray[i][j].paraType != "-1" && !(document.getElementById('row').value == (i+1) && document.getElementById('col').value == (j+1) ) )
					if(document.getElementById('paraName').value.toUpperCase()== TemplateProp.paraArray[i][j].paraName.toUpperCase() )//&& document.getElementById('paraParent').value.toUpperCase()== TemplateProp.paraArray[i][j].paraParent.toUpperCase())
					{
						alert("Duplicate Parameter .. Either Change Name or Change Parent... ");
						document.getElementById('paraId').value="";
						document.getElementById('paraName').focus();
						return false;
					}
	}
	
		//-- Invidual Control Essential Properties
	switch(document.getElementById('paraType').value)
	{
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
		case '0':
			break;
		case '1':
		case '4':
		case '5':
		case '6':
		case '7':
			if( document.getElementById('paraValue').value == "" )
			{
				alert("Enter Parameter Display Value ... ");
				document.getElementById('paraValue').focus();
				return false;
			}
			break;
		case '2':
			if(document.getElementById('paraValue').value=="")
			{
				alert("Enter Parameter Display Value ... ");
				document.getElementById('paraValue').focus();
				return false;
			}
			if(document.getElementById('maxlength').value=="")
			{
				alert("Enter Max Length ... ");
				document.getElementById('maxlength').focus();
				return false;
			}
			if(document.getElementById('regularExpression').value!="" && document.getElementById('format').value=="")
			{
				alert("Enter Format Corresponding to the Regular Expression ... ");
				document.getElementById('format').focus();
				return false;
			}
			if(document.getElementById('regularExpression').value=="" && document.getElementById('format').value!="")
			{
				alert("Enter Regular Expression Corresponding to the Format ... ");
				document.getElementById('regularExpression').focus();
				return false;
			}
			break;
		case '3':
			if(document.getElementById('paraValue').value=="")
			{
				alert("Enter Parameter Display Value ... ");
				document.getElementById('paraValue').focus();
				return false;
			}
			break;
	}

	if( !validateSourceFlag() ) return false;
	
	//-- get para Obj from id
	var pObj=getSetParaObj(id);

	var oldLoc=pObj.locationId;
	var oldColspan=parseInt(pObj.colspan);
	var oldType=pObj.paraType;

	// set prop for para
	for ( var F in pObj)
	{
		if(document.getElementById(F))
		{
			pObj[F]=document.getElementById(F).value;
		}
	}
	
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
	switch(pObj.paraType)
	{
		case '0':
			if(oldType=='1')
			{
				if(oldColspan>1)
					for(var i=1;i<oldColspan;i++)
					{
						clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)+i-1);
					}
			}
			else if(oldType=='2' || oldType=='3' || oldType=='4' || oldType=='5' || oldType=='7' || oldType=='6')
				clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col));
			break;		

		case '1':
			if(oldType=='2' || oldType=='3' || oldType=='4' || oldType=='5' || oldType=='7'  || oldType=='6')
				clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col));
			applyLocation(pObj,oldLoc,oldColspan);
			break;
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			if(oldType=='1')
			{
				if(oldColspan>1)
					for(var i=1;i<oldColspan;i++)
					{
						clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)+i-1);
					}
			}
			hideParaObj(pObj.row-1,pObj.col);
			pObj.colspan=2;
			break;		
	}
	return true;
}

function applyTemplateChangesMatrix(id)
{
	//-- Validation of Entries
	if( document.getElementById('paraType').value=="1" ) // for Label
	{	//-- Parameter Name for a Valid Paramter is mandatory
		if(document.getElementById('paraName').value == "" )
		{
			alert("Enter Parameter Name ... ");
			document.getElementById('paraName').focus();
			return false;
		}
	}
	else if(document.getElementById('paraType').value!="-1" && document.getElementById('paraType').value != "0" && document.getElementById('paraType').value != "1") 
		if(document.getElementById('paraParent').value=="" )
		//-- At Least One Parent is mandatory for a Non-Label Control
		{
			alert("Select At Least One Parent ... ");
			document.getElementById('paraName').focus();
			return false;
		}
	
		//-- Checking Duplicate Parameter Name & Parent 
	if(document.getElementById('paraName').value!="")
	{
		var someFlag=false;
		for(var i=0;i<document.getElementById('paraList').options.length;i++)
			if( document.getElementById('paraName').value.toUpperCase()== document.getElementById('paraList').options[i].text.toUpperCase())
			{
				document.getElementById('paraName').value=document.getElementById('paraList').options[i].text;
				document.getElementById('paraId').value=document.getElementById('paraList').options[i].value;
				someFlag=true;
				break;
			}
		if(someFlag==false)document.getElementById('paraId').value=""; 

		a: for(var i=0;i<TemplateProp.totalRows;i++)
			for(var j=0;j<9;j++)
				if( TemplateProp.paraArray[i][j].paraType != "0" && TemplateProp.paraArray[i][j].paraType != "-1" && !(document.getElementById('row').value == (i+1) && document.getElementById('col').value == (j+1) ) )
					if(document.getElementById('paraName').value.toUpperCase()== TemplateProp.paraArray[i][j].paraName.toUpperCase() && document.getElementById('paraParent').value.toUpperCase()== TemplateProp.paraArray[i][j].paraParent.toUpperCase())
					{
						alert("Duplicate Parameter .. Either Change Name or Change Parent... ");
						document.getElementById('paraId').value="";
						document.getElementById('paraName').focus();
						return false;
					}
	}
	
		//-- Invidual Control Essential Properties
	switch(document.getElementById('paraType').value)
	{
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
		case '0':
			break;
		case '1':
			if( document.getElementById('paraValue').value == "" )
			{
				alert("Enter Parameter Display Value ... ");
				document.getElementById('paraValue').focus();
				return false;
			}
			break;
		case '4':
		case '5':
		case '6':
		case '7':
			break;
		case '2':
			if(document.getElementById('maxlength').value=="")
			{
				alert("Enter Max Length ... ");
				document.getElementById('maxlength').focus();
				return false;
			}
			if(document.getElementById('regularExpression').value!="" && document.getElementById('format').value=="")
			{
				alert("Enter Format Corresponding to the Regular Expression ... ");
				document.getElementById('format').focus();
				return false;
			}
			if(document.getElementById('regularExpression').value=="" && document.getElementById('format').value!="")
			{
				alert("Enter Regular Expression Corresponding to the Format ... ");
				document.getElementById('regularExpression').focus();
				return false;
			}
			break;
		case '3':
			break;
	}
	if( !validateSourceFlag() ) return false;

	//-- get para Obj from id
	var pObj=getSetParaObj(id);
	
	var oldLoc=pObj.locationId;
	var oldColspan=parseInt(pObj.colspan);
	var oldType=pObj.paraType;
	
	// set prop for para
	for ( var F in pObj)
	{
		if(document.getElementById(F))
		{
			pObj[F]=document.getElementById(F).value;
		}
	}
	
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
	switch(pObj.paraType)
	{
		case '0':
			if(oldType=='1')
			{
				if(oldColspan>1)
					for(var i=1;i<oldColspan;i++)
					{
						clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)+i-1);
					}
			}
			clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)-1);
			break;		
		case '1':
			applyLocation(pObj,oldLoc,oldColspan);
			break;
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			if(oldType=='1')
			{
				if(oldColspan>1)
					for(var i=1;i<oldColspan;i++)
					{
						clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)+i-1);
					}
			}
			break;		
	}
	
	return true;
}

function applyTemplateChangesConsent(id)
{
	//-- Validation of Entries
	if( document.getElementById('paraType').value=="1" ) // for Label
	{	//-- Parameter Name for a Valid Paramter is mandatory
		if(document.getElementById('paraName').value == "" )
		{
			alert("Enter Parameter Name ... ");
			document.getElementById('paraName').focus();
			return false;
		}
	}
	else if(document.getElementById('paraType').value!="-1" && document.getElementById('paraType').value != "0" && document.getElementById('paraType').value != "1") 
		if(document.getElementById('paraParent').value=="" )
		//-- At Least One Parent is mandatory for a Non-Label Control
		{
			alert("Select At Least One Parent ... ");
			document.getElementById('paraName').focus();
			return false;
		}
	
		//-- Checking Duplicate Parameter Name & Parent 
	if(document.getElementById('paraName').value!="")
	{
		var someFlag=false;
		for(var i=0;i<document.getElementById('paraList').options.length;i++)
			if( document.getElementById('paraName').value.toUpperCase()== document.getElementById('paraList').options[i].text.toUpperCase())
			{
				document.getElementById('paraName').value=document.getElementById('paraList').options[i].text;
				document.getElementById('paraId').value=document.getElementById('paraList').options[i].value;
				someFlag=true;
				break;
			}
		if(someFlag==false)document.getElementById('paraId').value=""; 

		a: for(var i=0;i<TemplateProp.totalRows;i++)
			for(var j=0;j<9;j++)
				if( TemplateProp.paraArray[i][j].paraType != "0" && TemplateProp.paraArray[i][j].paraType != "-1" && !(document.getElementById('row').value == (i+1) && document.getElementById('col').value == (j+1) ) )
					if(document.getElementById('paraName').value.toUpperCase()== TemplateProp.paraArray[i][j].paraName.toUpperCase() && document.getElementById('paraParent').value.toUpperCase()== TemplateProp.paraArray[i][j].paraParent.toUpperCase())
					{
						alert("Duplicate Parameter .. Either Change Name or Change Parent... ");
						document.getElementById('paraId').value="";
						document.getElementById('paraName').focus();
						return false;
					}
	}

		//-- Invidual Control Essential Properties
	switch(document.getElementById('paraType').value)
	{
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
		case '0':
			break;
		case '1':
			if( document.getElementById('paraValue').value == "" )
			{
				alert("Enter Parameter Display Value ... ");
				document.getElementById('paraValue').focus();
				return false;
			}
			break;
		case '4':
		case '5':
		case '6':
		case '7':
			break;
		case '2':
			if(document.getElementById('maxlength').value=="")
			{
				alert("Enter Max Length ... ");
				document.getElementById('maxlength').focus();
				return false;
			}
			if(document.getElementById('regularExpression').value!="" && document.getElementById('format').value=="")
			{
				alert("Enter Format Corresponding to the Regular Expression ... ");
				document.getElementById('format').focus();
				return false;
			}
			if(document.getElementById('regularExpression').value=="" && document.getElementById('format').value!="")
			{
				alert("Enter Regular Expression Corresponding to the Format ... ");
				document.getElementById('regularExpression').focus();
				return false;
			}
			break;
		case '3':
			break;
	}
	if( !validateSourceFlag() ) return false;

	//-- get para Obj from id
	var pObj=getSetParaObj(id);

	var oldLoc=pObj.locationId;
	var oldColspan=parseInt(pObj.colspan);
	var oldType=pObj.paraType;
	
	// set prop for para
	for ( var F in pObj)
	{
		if(document.getElementById(F))
		{
			pObj[F]=document.getElementById(F).value;
		}
	}
	
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
	switch(pObj.paraType)
	{
		case '0':
			if(oldType=='1')
			{
				if(oldColspan>1)
					for(var i=1;i<oldColspan;i++)
					{
						clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)+i-1);
					}
			}
			clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)-1);
			break;		
		case '1':
			applyLocation(pObj,oldLoc,oldColspan);
			break;
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			if(oldType=='1')
			{
				if(oldColspan>1)
					for(var i=1;i<oldColspan;i++)
					{
						clearParaObj(parseInt(pObj.row)-1,parseInt(pObj.col)+i-1);
					}
			}
			break;		
	}
	
	return true;
}

function SetTypeProperties(val)
{
	// Restricting Unsuitable Controls
	var cl=document.getElementById("col").value;
	if(val=='2' || val=='3' || val=='4' || val=='5' || val=='6' || val=='7')
	{
		if( cl==1 )
		{
			alert("Column 1 can only have a Label ..");
			document.getElementById('paraType').value='0';
			SetTypeProperties('0');
			return;
		}

		if( cl==9 && TemplateProp.templateType=="1" )
		{
			alert("Column 9 can not contain a Control except Label ..");
			document.getElementById('paraType').value='0';
			SetTypeProperties('0');
			return;
		}
	
		if(TemplateProp.templateType=="1")
		{
			var nextType=TemplateProp.paraArray[parseInt(document.getElementById("row").value)-1][parseInt(document.getElementById("col").value)].paraType;
			if(nextType=='2' || nextType=='3' || nextType=='4' || nextType=='5' || nextType=='6' || nextType=='7' )
			{
				alert("Improper Place for a Control ..");
				document.getElementById('paraType').value='0';
				SetTypeProperties('0');
				return;
			}
		}

		// Hiding Paramtere Name when not a Label in Matrix
		if(TemplateProp.templateType=="2" )
		{
			document.getElementById('paraId').value="";
			document.getElementById('paraName').value="";
			document.getElementById('paraName').readOnly=true;
		}
	}
	else
		document.getElementById('paraName').readOnly=false;

	// Type Wise Setter
	var Setter=document.getElementById("typeWiseSetter");
	var h="";
	var Pramas=getShowTypeWiseProp(val);
	if(Pramas!=null)
	{
		var c=1;
		h+="<table width='100%' border='0' cellspacing='1' cellpadding='0'>";
		for ( var i in Pramas )
		{
			if(c%2==1)h+="<tr>";
			h+=getControlCodeFromClass(i,val); //ControlCodes[i];
			// If The Whole Row is covered by the Control for this just put </tr> at the end of the code
			if(h.substring(h.length-5,h.length)=="</tr>") c++;
			else if(c%2==0)	h+="</tr>";
			c++;
		}
		if(c%2==0)h+="<td width='25%' class='tdfonthead'></td><td width='25%' class='tdfont'></td></tr>";
		h+="</table>";
	}
	Setter.innerHTML=h;
	
	
	// Control Wise Setter
	Setter=document.getElementById("controlSetter");
	h="";
	Pramas=getShowControlWiseProp(val);
	if(Pramas!=null)
	{
		var c=1;
		h+="<table width='100%' border='0' cellspacing='1' cellpadding='0'>";
		for ( var i in Pramas )
		{
			if(c%2==1)h+="<tr>";
			h+=getControlCodeFromClass(i,val); //ControlCodes[i];
			// If The Whole Row is covered by the Control for this just put </tr> at the end of the code
			if(h.substring(h.length-5,h.length)=="</tr>") c++;
			else if(c%2==0)	h+="</tr>";
			c++;
		}
		if(c%2==0)h+="<td width='25%' class='tdfonthead'></td><td width='25%' class='tdfont'></td></tr>";
		h+="</table>";
	}
	Setter.innerHTML=h;
		
	//Setting Parameters Type
	fillParaParents();
	//Setting SourceFlag;
	if(document.getElementById('sourceFlag'))fillSourceFlag(document.getElementById('sourceFlag').value);
	//Setting para Name as Initial Para Value
	if(document.getElementById('paraValue'))
		document.getElementById('paraValue').value=document.getElementById('paraName').value;
}

function setParameterSetter(pObj)
{
	var setter=document.getElementById("paraSetter");

	var h="<table width='100%' border='0' cellspacing='1' cellpadding='0'>";
	h+="<tr>";
	h+="<td width='25%' class='tdfonthead'> Parameter Name </td>";
	h+="<td width='25%' class='tdfont'><input id='paraId' type='hidden'/> <input id='paraParent' type='hidden' /> <input id='paraName' onkeyup='setgetParameter(this,event)' onblur='paraNameBlur(this,event)' onkeypress='return validateAlphaNumOnly(this,event);' /> </td>";
	h+="<td width='25%' class='tdfonthead'> Parameter Type </td>";
	// -1:none,0:default,1:label,2:textbox,3:textarea,4:combo,5:radio,6:yesno,7:checkbox,8:popup,9:file,10:datetime,11:list
	h+="<td width='25%' class='tdfont'> <select id='paraType' onchange='SetTypeProperties(this.value)' ><option value='0'>Select One</option><option value='1'>Label</option>";
	h+="<option value='2'>Text Box</option> <option value='3'>Text Area</option><option value='4'>Combo</option>";
	h+=" <option value='5'>Radio Buttons</option> <option value='6'>Yes No</option> <option value='7'>Check Box</option>";
	//h+="<option value='8'>Pop Up</option> <option value='9'>File</option><option value='10'>Date Time</option><option value='11'>List</option> ";
	h+="</select></td>";
	h+="</tr>";
	h+="<tr>";
	h+="<td width='25%' class='tdfonthead'> Row Number </td>";
	h+="<td width='25%' class='tdfont'> <input id='row' value='"+pObj.row+"' readonly='readonly'/> </td>";
	h+="<td width='25%' class='tdfonthead'> Column Number </td>";
	h+="<td width='25%' class='tdfont'> <input id='col' value='"+pObj.col+"' readonly='readonly' /> </td>";
	h+="</tr>";
	h+="<tr><td colspan='4' width='100%'><div id='typeWiseSetter' width='100%'>";
	h+="</div></td></tr>";
	h+="<tr><td colspan='4' width='100%'><div id='controlSetter' width='100%'>";
	h+="</div></td></tr>";
	h+="<tr><td colspan='4' class='tdfont'><div align='center'> <input type='button' value='Apply' onclick=\"pressedApplyButton()\" ></div> </td></tr>";
	h+="<tr><td colspan='4' class='tdfont'><div align='right'> <input type='button' value='Blank the Template' onclick='getBlankTemplate()' ></div> </td></tr>";
	h+="</table>";
	setter.innerHTML=h;

	document.getElementById('paraId').value=pObj.paraId;
	document.getElementById('paraName').value=pObj.paraName;
	document.getElementById('paraType').value=pObj.paraType;
	document.getElementById('row').value=pObj.row;
	document.getElementById('col').value=pObj.col;

	setControlSetter(pObj);
}

function pressedApplyButton()
{
	var pC=TemplateProp.curCell;
	return applyTemplateChanges(pC);	
}

function setControlSetter(pObj)
{
	SetTypeProperties(pObj.paraType);
	
	var cPramas=getShowTypeWiseProp(pObj.paraType);
	for( var cp in cPramas)
	{
		//alert("cp = "+cp);
		//alert("Element : "+document.getElementById(cp));
		document.getElementById(cp).value=pObj[cp];
	}
	cPramas=getShowControlWiseProp(pObj.paraType);
	for( var cp in cPramas)
	{
		//alert("cp = "+cp);
		//alert("Element : "+document.getElementById(cp));
		document.getElementById(cp).value=pObj[cp];
	}
	
	SetParaParents(pObj);
	SetSourceFlag(pObj);
	//SetParaOptions(pObj);
}

function fillParaParents()
{
 if(document.getElementById('divParaParent') )
 {
 	var r=document.getElementById("row").value;
 	var c=document.getElementById("col").value;
	var h="";
	for(var i=0;i<r;i++)
		for(var j=0;j<c;j++)
			if( !(i==(r-1) && j==(c-1)) && TemplateProp.paraArray[i][j].paraType=="1")
			{
				//alert(TemplateProp.templateType);
				//alert(document.getElementById("paraType").value);
				if(TemplateProp.templateType=="1" || document.getElementById("paraType").value =="1")//Normal or Label
					h+="<input type='radio' name='chkParents' value='"+TemplateProp.paraArray[i][j].row+"&"+TemplateProp.paraArray[i][j].col+"' onclick='clickParentChk();' >"+TemplateProp.paraArray[i][j].paraName+"</input>&nbsp;&nbsp;&nbsp;";
				else //if(TemplateProp.templateType=="2")//Matrix
					h+="<input type='checkbox' name='chkParents' value='"+TemplateProp.paraArray[i][j].row+"&"+TemplateProp.paraArray[i][j].col+"' onclick='clickParentChk();' >"+TemplateProp.paraArray[i][j].paraName+"</input>&nbsp;&nbsp;&nbsp;";
			}
	if(h!="")
	{
		h="<tr><td class='tdfont' width='100%'><div align='left'>"+h;
		h="<tr><td class='tdfonthead' width='100%'><div align='left'> Select Parent(s) : </div></td></tr>"+h;
		h="<table width='100%' border='0' cellspacing='1' cellpadding='0'>"+h;
		h+="</div></td></tr></table>";
	}
	document.getElementById('divParaParent').innerHTML=h;
 }
}

function SetParaParents(pObj)
{
 if(document.getElementById('divParaParent'))
 {
	if(pObj.paraParent!="")
	{
		var parents=pObj.paraParent.split("#");
		for(var i=0;i<parents.length;i++)
		{
			document.getElementById(parents[i]).bgColor="cyan";
			var chks=document.getElementsByName('chkParents');
			for(var j=0;j<chks.length;j++)
			{
				if(chks[j].value==parents[i])
				{
					chks[j].checked=true;
					break;
				}
			}
		}
	}
 }
}

function clickParentChk()
{
	var chks=document.getElementsByName('chkParents');
	var h="";
	for(var i=0;i<chks.length;i++)
		if(chks[i].checked)
		{
			h+=chks[i].value+"#";
			document.getElementById(chks[i].value).bgColor="cyan";
		}
		else
			document.getElementById(chks[i].value).bgColor="";
	if(h!="")	h=h.substr(0,h.length-1);
	document.getElementById('paraParent').value=h;
}

function getBlankTemplate()
{
	if(!confirm("Sure to Remove the Current Changes to the Template ... ? ")) return;
	
	for(var r=0;r<TemplateProp.totalRows;r++)
		for(var c=0;c<9;c++)
		{
			//TemplateProp.paraArray[r][c].paraType='0';
			var p=new Parameter();
			p.row=r+1;
			p.col=c+1;
			TemplateProp.paraArray[r][c]=p;
		}

	TemplateProp.curCell="";
	// Generate Template
	generateTemplate();
	var setter=document.getElementById("paraSetter");
	setter.innerHTML="";
}


//************************************************************************



//************************************************************************
// Getting Template Parameter Values List

function getTemplateParameterValuesList()
{
	// Applying Last Done Changes...
	if(! applyTemplateChanges(TemplateProp.curCell) ) return false;
	
	var target=document.getElementsByName('parameterValuesList')[0];
	target.value="";
	
	var tr=TemplateProp.totalRows;
	document.getElementsByName('noOfRows').value=tr;
	
	for(var i=0;i<tr;i++)
	{
		for(var j=0;j<9;j++)
		{
			var pObj=TemplateProp.paraArray[i][j];
			if(pObj.paraType!='-1' && pObj.paraType!='0')
			{
				var temp="";
				temp+=pObj.paraId+"#"+pObj.paraName +"%";
				temp+=pObj.paraParent+"%";
				temp+=pObj.locationId+"%";
				temp+=pObj.row+"%";
				temp+=pObj.col+"%";
				
				temp+=pObj.paraType+"%";

				var cParams=getValueWiseProp(pObj.paraType);
				var t="";
				if(cParams!=null)
				{
					for ( var F in cParams )
					{
						t+=pObj[F]+"&";
					}
					t=t.substr(0,t.length-1);
					
					//alert("Param Value - > "+t);
				}
				temp+=t+"%";
				temp+=pObj.colspan+"%";
				temp+=pObj.paraValue+"@"+pObj.paraOptions+"%";
				temp+=pObj.isCompulsory+"%";
				temp+=pObj.isRange+"%";
				temp+=pObj.sourceFlag+"%";
				if(pObj.tableQuery!="") temp+=pObj.tableQuery;	else  temp+=" ";
				//alert("Para meter -->  " +temp);
				target.value+=temp+"%#%";
			}
		}
	}
	target.value=target.value.substr(0,target.value.length-3);

	//alert("Target - > "+target.value);
	return true;
}



//************************************************************************




// ******************************************************************************
// *** TemplateDesigner's Static Utility Functions

// TemplateDesigner

TemplateDesigner.renderTemplateFromString = function(_tempType,_rowCount,_colCount,_strParaList)
{
	//tempDesigner= new TemplateDesigner(_tempType,_rowCount,_colCount, );

	var code="";
	
	var r=parseInt(_rowCount);
	var c=parseInt(_colCount);

	var paraArray=new Array(r);
	for(var i=0;i<r;i++)
	{
		var temp = new Array(c);
		for(var j=0;j<c;j++)
		{
			temp[j]=new Parameter();
			temp[j].row=i+1;
			temp[j].col=j+1;
		}
		TemplateProp.paraArray[i]=temp;
	}
	
	var target=document.getElementsByName('parameterValuesList')[0].value;
	var rowData=target.split("%#%");
	
	for(var i=0;i<rowData.length;i++)
	{
		var paraData=rowData[i].split("%");
		
		var pObj=TemplateProp.paraArray[paraData[3]-1][paraData[4]-1];
		
		pObj.paraId=paraData[0].split("#")[0];
		pObj.paraName=paraData[0].split("#")[1];
		pObj.paraParent=paraData[1];
		pObj.locationId=paraData[2];
		pObj.paraType=paraData[5];

		var cParams=getValueWiseProp(pObj.paraType);
		
		var t="";
		var k=0;
		if(cParams!=null)
		{
			for ( var F in cParams )
			{
				pObj[F]=paraData[6].split("&")[k];
				k++;
			}
		}

		pObj.colspan=paraData[7];
		pObj.paraValue=paraData[8].split("@")[0];
		pObj.paraOptions=paraData[8].split("@")[1];
		pObj.isCompulsory=paraData[9];
		pObj.isRange=paraData[10];
		pObj.sourceFlag=paraData[11];
		pObj.tableQuery=paraData[12];
	}
	
	for(var i=0;i<TemplateProp.totalRows;i++)
	{
		for(var j=0;j<9;j++)
		{
			var pObj=TemplateProp.paraArray[i][j];
			for(var k=0;k<(pObj.colspan-1);k++)
			{
				TemplateProp.paraArray[i][j+1].paraType="-1";
				j++;
			} 
		}
	}
	var h="\n<table width='100%' id='tblTemplate' border='0' cellspacing='1' cellpadding='0'>\n</table>";
	document.getElementById('htmlModifyTarget').innerHTML=h;
	// Generate Template 
	generateTemplate();
	

}

// *** End TemplateDesigner's Static Utility Functions
// ******************************************************************************




// ******************************************************************************
// *** Constants

// TemplateDesigner

	// Is Browser is Internet Explorer ? 
	TemplateDesigner.isIE =(/msie/i.test(navigator.userAgent);

	// Is Browser is Firefox ?
	TemplateDesigner.isFirefox =(/firefox/i.test(navigator.userAgent);


// *** End Constants
// ******************************************************************************
