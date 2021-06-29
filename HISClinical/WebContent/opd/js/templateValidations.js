
//  ******************************************************************************
// *** Validation Functions

function validateNone(obj,e)
{
	return true;
}

function validateNumericOnly(obj,e)
{// Ascii Code 0 - 48 To 9 - 57 , for . - 46
	var k=e.charCode;
	//alert(k);
	var pattern=/\./;
	if( e.keyCode!=0 || ( !pattern.test(obj.value) && k==46) ||(obj.value.length==0 && k==45) || (k>=48 && k<= 57))
		return true;
	else
		return false;
}

function validateNumericValue(val)
{// Ascii Code 0 - 48 To 9 - 57 , for . - 46
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
}

function validateIntegerOnly(obj,e)
{// Ascii Code 0 - 48 To 9 - 57 Minus - 45 is also here to add
	var k=e.charCode;
	//alert(e.keyCode+"  " +e.charCode+"\n");
	if( e.keyCode!=0 || (obj.value.length==0 && k==45) || (k >= 48 && k <= 57) )
		return true;
	else
		return false;
}

function validateIntegerValue(val)
{// Ascii Code 0 - 48 To 9 - 57 Minus - 45 is also here to add
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
}

function validatePositiveIntegerOnly(obj,e)
{// Ascii Code 0 - 48 To 9 - 57
	var k=e.charCode;
	//alert(e.keyCode+"  " +e.charCode+"\n");
	if( e.keyCode!=0 || ( k>=48 && k<= 57 ) )
		return true;
	else
		return false;
}

function validatePositiveIntegerValue(val)
{// Ascii Code 0 - 48 To 9 - 57
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
}

function validateAlphaOnly(obj,e)
{// Ascii Code 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, . -46, for , - 44
	var k=e.charCode;
	//alert(k);
	if( e.keyCode!=0 || k==32 || k==46 || (k>=65 && k<=90) || (k>=97 && k<=122) )
		return true;
	else
		return false;
}

function validateAlphaValue(val)
{// Ascii Code 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, . -46, for , - 44
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
}

function validateAlphaNumOnly(obj,e)
{// Ascii Code 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
	var k=e.charCode;
	//alert(k);
	if( e.keyCode!=0 || k==44 || k==32 || k==46 || (k>=48 && k<=57) || (k>=65 && k<=90) || (k>=97 && k<=122) )
		return true;
	else
		return false;
}

function validateAlphaNumValue(val)
{// Ascii Code 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
}

function notSpecChar(obj,e) // Restrict to enter ~!@#$%^& in all fileds  allow ^ and  $ in Regular Expression only
{// ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38
	var k=e.charCode;
	//alert(k);
	if( k==126 || k==33 || k==64 || k==35 || k==36 || k==37 || k==94 || k== 38)
		return false;
	else
		return true;
}

function notSpecCharValue(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
}

function notSpecCharRegEx(obj,e) // Restrict to enter ~!@#$%^& in all fileds  allow ^ and  $ in Regular Expression only
{// ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38
	var k=e.charCode;
	//alert(k);
	if( k==126 || k==33 || k==64 || k==35 || k==37 || k== 38)
		return false;
	else
		return true;
}

function notSpecCharRegEx(val)
{
	var pattern=/^[^~!@#%&]*$/;
	return pattern.test(val);
}

function validatePARAMETERRegExp(obj,e,format,re)
{
	if(re!="")
	{
		//alert(re);
		var rep=new RegExp(re,"");
		if(!rep.test(obj.value))
		{
			alert("The Value in not in the Format  \n "+format+" \n Please Enter the value in Proper Format   ...");
			obj.focus();
		}
	}
}

function validateRegularExpression(val,re)
{
	if(re!="")
	{ 
		var rep=new RegExp(re,"");
		return rep.test(val);
	}
	return true;
}

function validatePARAMETERCompulsoryField()
{
	var frm=document.forms[0];
	for(var i in frm)
	{
		if(i.substr(0,10)=='PARAMETER@')
		{
			var obj=document.getElementsByName(i)[0];//frm[i];
			//alert("Object = "+obj+"  Name = "+obj.name +" Id = "+obj.id+" Tag = "+ obj.tagName +" Type = "+obj.type);
			if(obj.id.substr(0,3)=="C:1")
			{
				if(obj.tagName=="INPUT" && obj.type=="radio" )
				{
					var flag=false;
					var supObj=document.getElementsByName(i);
					for(var j=0;j<supObj.length;j++)
						if(supObj[j].checked) flag=true;
					if(!flag)
					{
						alert(" Value is Compulsory ... ");
						obj.focus();
						return false;
					}
				}
/*				else if(obj.tagName=="INPUT" && obj.type=="checkbox" && !obj.checked)
				{
					alert(" Value is Compulsory ... ");
					obj.focus();
					return false;
				}*/
				else if(obj.value=="")
				{
					alert(" Value is Compulsory ... ");
					obj.focus();
					return false;
				}
			}
		}
	}
	return true;
}

// *** End Validation Functions
//  ******************************************************************************
