//******************************************************************************
// *** Pragya Designer Constants

PragyaDesigner = function(){};

	// Is Browser is Internet Explorer ? 
	PragyaDesigner.IS_IE =(/msie/i.test(navigator.userAgent));

	// Is Browser is Firefox ?
	PragyaDesigner.IS_FIREFOX =(/firefox/i.test(navigator.userAgent));

	//***** Show Hide Display Value
	PragyaDesigner.DISPLAY_TYPE={};
		// Show, Hide
		PragyaDesigner.DISPLAY_TYPE["Hide"]="none";
		PragyaDesigner.DISPLAY_TYPE["Show"]="block";
		
	//***** Event Key Codes
	PragyaDesigner.EVENT_KEYCODES = {};
		PragyaDesigner.EVENT_KEYCODES["Enter"] = 13;
		PragyaDesigner.EVENT_KEYCODES["ArrowUp"] = 38;
		PragyaDesigner.EVENT_KEYCODES["ArrowDown"] = 40;
		PragyaDesigner.EVENT_KEYCODES["Esc"] = 27;		
		PragyaDesigner.EVENT_KEYCODES["Tab"] = 9;
		
		//PragyaDesigner.EVENT_KEYCODES["Enter"] = 13;

// *** End Pragya Designer Constants
//******************************************************************************

//******************************************************************************
// *** Pragya Designer Static Functions

// Getting Absolute Position of given Element
PragyaDesigner.getAbsolutePosition = function(elem)
{
	var SL = 0, ST = 0; // Scroll Left, Scroll Top
	var isDiv = /^div$/i.test(elem.tagName);
	if (isDiv && elem.scrollLeft)
		SL = el.scrollLeft;
	if (isDiv && elem.scrollTop)
		ST = elem.scrollTop;
	var r = { x: elem.offsetLeft - SL, y: elem.offsetTop - ST };
	if (elem.offsetParent)
	{
		var tmp = this.getAbsolutePosition(elem.offsetParent);
		r.x += tmp.x;
		r.y += tmp.y;
	}
	return r;
};

// Creating a HTML Element of given Type inside given Parent which is optional
PragyaDesigner.createElement = function(type, parent)
{
	var newElem = null;
	if (document.createElementNS)
	{
		// use the XHTML namespace; IE won't normally get here unless
		// _they_ "fix" the DOM2 implementation.
		newElem = document.createElementNS("http://www.w3.org/1999/xhtml", type);
	}
	else
	{
		newElem = document.createElement(type);
	}
	if (typeof parent != "undefined")
	{
		parent.appendChild(newElem);
	}
	else
	{
		document.body.appendChild(newElem);
	}
	return newElem;
};

// Creating a HTML Input Element of given Type inside given Parent which is mandatory
PragyaDesigner.createInputElement = function(_type, _parent)
{
	_parent.innerHTML = _parent.innerHTML + "<input type='" + _type + "' />";
	return _parent.lastChild;
};

// Adding Event to Element
PragyaDesigner.addEvent = function(elem, evntName, funcName)
{
	if (elem.attachEvent) // IE
	{
		elem.attachEvent("on" + evntName, funcName);
	}
	else if (elem.addEventListener) // Gecko / W3C
	{
		elem.addEventListener(evntName, funcName, true);
	}
	else
	{
		elem["on" + evntName] = funcName;
	}
};

PragyaDesigner.getTargetElement = function(evnt)	// Which is targeted
{
	var f = PragyaDesigner.IS_IE ? window.event.srcElement : evnt.target;
	return f;
};

PragyaDesigner.showData = function(obj)
{
	var h="";
	for(var d in obj)
	{
		h+=d+" = "+obj[d];
		/*if(typeof obj[d] == "object" && obj[d] && obj[d]!=null 
		&& obj[d].tagName 
		&& (obj[d].tagName.toUpperCase() == 'SELECT' 
//			|| obj[d].tagName.toUpperCase() == 'INPUT' 
			|| obj[d].tagName.toUpperCase() == 'OPTION'))
		{
			h+="\n-------\n";
			for(var k in obj[d])
			{
				h+="\t\t\t"+k+" = "+obj[d][k];
				h+="\n";
				if(h.length>300)
				{
					alert(h);
					h="";
				}
			}
		
		}*/
		h+="\n";
		if(h.length>300)
		{
			alert(h);
			h="";
		}
	}
	alert(h);
};

PragyaDesigner.findIndex = function(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
};

PragyaDesigner.trim = function(val)
{
	if(val && val!=null && val!="" && (typeof val).toUpperCase() == 'STRING')
	{
		while(val.substr(0,1)==' ')	val=val.substr(1);
		while(val.substr(val.length-1,1)==' ')	val=val.substr(0,val.length-1);			
	}
	return val;
};

PragyaDesigner.clearCombo = function(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		cbo.appendChild(op);
	}
};

PragyaDesigner.replaceAllOcc = function(str,srchstr,tarstr)
{
	var re = new RegExp(srchstr,'i');
	while(re.test(str))
	{
		str = str.replace(srchstr,tarstr);
	}
	return str;
	
};

// *** End Pragya Designer Static Functions
//******************************************************************************