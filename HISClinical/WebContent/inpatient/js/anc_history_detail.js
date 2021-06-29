window.Pragyas_ANCHistoryPan = null;

// ******************************************************************************
	// ANC History Detail Pan Object
ANCHistoryPan = function(_container, _initalGravida, _initialParity, _initialAbortus, _entryDate,_maxNoOfBirth, _historyData)
{
	this.elemContainer=document.getElementById(_container); // History Table 
	this.elemContainer.id = "ANCHistoryPan";
	
	this.initalGravida = parseInt(_initalGravida);
	
	this.gravida = parseInt(_initalGravida);
	this.parity = parseInt(_initialParity);
	this.abortus = parseInt(_initialAbortus);
	this.maxNoOfBirth=parseInt(_maxNoOfBirth);
	
	this.historyCount = parseInt(_initalGravida)-1;
	this.historyData = null;
	
	this.entryDate = convertStrToDate(_entryDate,"dd-Mon-yyyy");
	
	if(this.initalGravida>1)
	{
		if(_historyData==null || typeof _historyData=='undefined')
		{
			alert("History Data is not present");
		}
		this.historyData = _historyData;		
	}	

	this.elemHistory = null;
	this.arrHistory = null;
};

	// ANC History Detail Pan Setup
ANCHistoryPan.setup = function(_container, _initalGravida, _initialParity, _initialAbortus, _entryDate,_maxNoOfBirth, _historyData)
{
	try	
	{
		// Validating Container existent
		/*if(!(_container && document.getElementById(_container).tagName.toLowerCase()=="DIV"))
			if(document.getElementById('divANCHistoryPan'))
			{
				_container = 'divANCHistoryPan';
				this.elemContainer=document.getElementById('divANCHistoryPan');
			}
			else
			{
				alert("Error : Container for ANC History Pan is not defined...");
				return;
			}*/
			
		/*// Validating Taraget Container existent
		if(!(_targetContainer && document.getElementsByName(_targetContainer)[0].tagName.toLowerCase()=="INPUT"))
			if(document.getElementsByName('parameterValuesList')[0])
			{
				_targetContainer = 'parameterValuesList';
				this.targetContainer=document.getElementsByName('parameterValuesList')[0];
			}
			else
			{
				alert("Error : Target Container for Template Vlaues is not defined...");
				return;
			}			*/
		/*if(!_tempType || _tempType==null) _tempType=ANCHistoryPan.TEMPLATE_TYPE["Normal"];
		if(!_rowCount || _rowCount==null) _rowCount=null;
		if(!_colCount || _colCount==null) _colCount=null;
		if(!_templateDesignData || _templateDesignData==null) _templateDesignData = null;
		if(!_maxRow || _maxRow==null) _maxRow = null;
		if(!_maxCol || _maxCol==null) _maxCol = null;*/
		
		//alert(_initalGravida+"--"+_gravida);
		
		var ancHistPan = window.Pragyas_ANCHistoryPan;
		window.Pragyas_ANCHistoryPan = ancHistPan = new ANCHistoryPan(_container, _initalGravida, _initialParity, _initialAbortus, _entryDate,_maxNoOfBirth, _historyData);
		ancHistPan.start();
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

ANCHistoryPan.prototype.start = function()
{	
	this.create(this.elemContainer);	
};

ANCHistoryPan.prototype.refreshGravida = function()
{	
	this.gravida = document.getElementsByName("gravidaNo")[0].value;	
};

ANCHistoryPan.prototype.create = function(_parent)
{
	this.elemHistory=_parent;	
	if(this.initalGravida>1)
	{
		// Setting History Data
		this.setHistoryData();
		
		// Inserting Header Row
		this.insertHeaderRow();
			
		for(var i=0;i<this.historyCount;i++)
		{
			this.arrHistory[i].create(this.elemContainer);
			if(this.arrHistory[i].noOfBirths>0)
			{
				//------ Add Children
			}			
		}
		
		/*if(document.getElementsByName("ancDetailGetFlag")[0].value == 1)
		{
			var objGravidaNo = document.getElementsByName("gravidaNo")[0];
			var ancHistPan = window.Pragyas_ANCHistoryPan;
			ancHistPan.setGravida(parseInt(objGravidaNo.value),objGravidaNo);
		}*/	
	}
};

ANCHistoryPan.prototype.setHistoryData = function()
{
	var rowsData = this.historyData.split(ANCHistoryPan.ROW_SEP);
	var rowsCount = rowsData.length;
	var ancPrevHistorysArr = new Array(rowsCount);
		
	for(var i=0;i<rowsCount;i++)
	{
		var childs = rowsData[i].split(ANCHistoryPan.ROW_CHILD_DATA_SEP);
		var pregInfo = childs[0];
		
		var pregInfoArr = pregInfo.split(ANCHistoryPan.ROW_DATA_SEP);
			
		var ancHistRow = new ANCHistory(this,pregInfoArr[0]);
			
		ancHistRow.clearValues();
		ancHistRow.deliveryStatus = pregInfoArr[1];
		ancHistRow.deliveryDate = pregInfoArr[2];
		ancHistRow.pregnancyDuration = pregInfoArr[3];
		ancHistRow.pregnancyRemarks = pregInfoArr[4];
		ancHistRow.noOfBirths = "0";
			
		if(ancHistRow.deliveryStatus == ANCHistoryPan.DELIVERY_STATUS["Delivery"])
		{
			ancHistRow.deliveryPlaceId = pregInfoArr[5];
			ancHistRow.labourDuration = pregInfoArr[6];
			ancHistRow.deliveryTypeId = pregInfoArr[7];
			ancHistRow.isAntiDGiven = pregInfoArr[8];
			ancHistRow.labourRemarks = pregInfoArr[9];
			ancHistRow.noOfBirths = pregInfoArr[10];
			ancHistRow.objANCChildren = new Array(ancHistRow.noOfBirths);

			if(ancHistRow.noOfBirths>0)
			{
				for(var j=0;j<ancHistRow.noOfBirths;j++)
				{
					var child = new ANCChildHistory(j+1, ancHistRow);
					child.clearValues();
					
					var childsData = childs[j+1].split(ANCHistoryPan.ROW_DATA_SEP);
										
					child.birthNatureId = childsData[0];
					child.genderCode = childsData[1];
					child.weight = childsData[2];
					child.babyBloodGroupCode = childsData[3];
					child.presentHealth = childsData[4];
					child.deathAge = childsData[5];
					child.deathCause = childsData[6];
					
					ancHistRow.objANCChildren[j]= child;
				}
			}
		}
		ancPrevHistorysArr[i] = ancHistRow;
	}
	this.arrHistory = ancPrevHistorysArr;
};

ANCHistoryPan.prototype.insertHeaderRow = function()
{
	// Clear Table
	while(this.elemContainer.rows.length>0)	this.elemContainer.deleteRow(0);
	
	var tr = this.elemContainer.insertRow(this.elemContainer.rows.length);
	var trMain = document.getElementById("trMainHead");
	for(var i=0;i<parseInt(trMain.childNodes.length);i++)
	{
		var tdMain = trMain.childNodes[i];
		var td= tdMain.cloneNode(true);
		tr.appendChild(td);
	}
};

/*ANCHistoryPan.showAddedHistory = function(_containerId, _gravida, _data)
{
	var _container = document.getElementById(_containerId);
	
	var rowsData = _data.split(ANCHistoryPan.ROW_SEP);
	var rowsCount = rowsData.length;
	
	var ancPrevHistorysArr = new Array(rowsCount);
	
	for(var i=0;i<rowsCount;i++)
	{
		var childs = rowsData[i].split(ANCHistoryPan.ROW_CHILD_DATA_SEP);
		var pregInfo = childs[0];
		
		var pregInfoArr = pregInfo.split(ANCHistoryPan.ROW_DATA_SEP);
		
		var ancHistRow = new ANCHistory(pregInfoArr[0]);
		ancHistRow.clearValues();
		ancHistRow.deliveryStatus = pregInfoArr[1];
		ancHistRow.deliveryDate = pregInfoArr[2];
		ancHistRow.pregnancyDuration = pregInfoArr[3];
		ancHistRow.pregnancyRemarks = pregInfoArr[4];
		
		if(ancHistRow.deliveryStatus == ANCHistoryPan.DELIVERY_STATUS["Delivery"])
		{
			ancHistRow.deliveryPlaceId = pregInfoArr[5];
			ancHistRow.labourDuration = pregInfoArr[6];
			ancHistRow.deliveryTypeId = pregInfoArr[7];
			ancHistRow.isAntiDGiven = pregInfoArr[8];
			ancHistRow.labourRemarks = pregInfoArr[9];
			ancHistRow.noOfBirths = pregInfoArr[10];
			ancHistRow.objANCChildren = new Array(ancHistRow.noOfBirths);

			if(ancHistRow.noOfBirths>0)
			{
				for(var j=0;j<ancHistRow.noOfBirths;j++)
				{
					var child = new ANCChildHistory(j+1, ancHistRow);
					child.clearValues();
					
					var childsData = childs[j+1].split(ANCHistoryPan.ROW_DATA_SEP);
										
					child.birthNatureId = childsData[0];
					child.genderCode = childsData[1];
					child.weight = childsData[2];
					child.babyBloodGroupCode = childsData[3];
					child.presentHealth = childsData[4];
					child.deathAge = childsData[5];
					child.deathCause = childsData[6];
					
					ancHistRow.objANCChildren[j]= child;
				}
			}
		}
		ancPrevHistorysArr[i] = ancHistRow;
	}

	while(_container.rows.length>0)	_container.deleteRow(0);	
	var tr = _container.insertRow(_container.rows.length);
	var trMain = document.getElementById("trMainHead");
	for(var i=0;i<parseInt(trMain.childNodes.length);i++)
	{
		var tdMain = trMain.childNodes[i];
		var td= tdMain.cloneNode(true);
		tr.appendChild(td);
	}

	for(var i=0;i<rowsCount;i++)
	{
		ancPrevHistorysArr[i].createReportRows(_container);
	}
	
	if(document.getElementsByName("ancDetailGetFlag")[0].value == 1)
	{
		var objGravidaNo = document.getElementsByName("gravidaNo")[0];
		//alert(objGravidaNo);
		var ancHistPan = window.Pragyas_ANCHistoryPan;
		ancHistPan.setGravida(parseInt(objGravidaNo.value),objGravidaNo);
	}	
};*/

ANCHistoryPan.prototype.setGravida = function(_gravida, obj)
{
	try
	{	
		if(this.isSetGravida(_gravida))
		{
			this.setHistoryRows(_gravida-1);
			this.gravida = _gravida;		
		}
		else
		{
			obj.value = this.gravida;
		}
	}
	catch(e)
	{
		alert("Error Message :- "+e.message);
	}
};

ANCHistoryPan.prototype.isSetGravida = function(_gravida)
{
	var flag = true;
	if(this.gravida > _gravida)
	{		
		if(!this.isEmptyLastHistory(_gravida))
		{
			if(!confirm("Are you sure to remove last entered history details ?"))
				flag = false; 
		}
	}
	else if(this.gravida == _gravida)
		flag = false;
	return flag;
};

ANCHistoryPan.prototype.isEmptyLastHistory = function(_gravida)
{
	var diff = this.gravida - _gravida;
	var flag = true;
	for(var i=0; i<diff; i++)
	{
		flag = (flag && this.arrHistory[this.historyCount-i-1].isEmpty());
	}
	return flag;
};

ANCHistoryPan.prototype.setGravidaRow = function()
{
	try
	{	
		this.setHistoryRows(this.gravida);		
	}
	catch(e)
	{
		alert("Error Message :- "+e.message);
	}
};

ANCHistoryPan.prototype.setHistoryRows = function(_rowCount)
{
	// Inserting Header Row
	if(this.historyCount == 0)	this.insertHeaderRow();

	var histRows = new Array(_rowCount);
	
	if(this.arrHistory!=null)
	{
		var len = (_rowCount < this.historyCount) ? _rowCount:(this.historyCount);
		for(var i=0; i<len;i++)
			histRows[i] = this.arrHistory[i];
	}
	
	var diff = _rowCount - this.historyCount;
	if(diff<0)
	{
		for(var i=0;i<Math.abs(diff);i++)
			this.arrHistory[this.historyCount+diff+i].deleteHistoryRow();
	}
	else if(diff>0)
	{
		for(var i=this.historyCount;i<_rowCount;i++)
		{
			var row = new ANCHistory(this,i+1);
			row.create(this.elemHistory);
			histRows[i]= row;
		}
	}
	
	this.arrHistory = histRows;
	this.historyCount = _rowCount;
};

ANCHistoryPan.prototype.validateHistoryRows = function(_rowCount)
{
	for(var i=0;i<this.historyCount; i++)
	{
		var histObj = this.arrHistory[i];
		if(!histObj.validateIfNotEmpty())
			return false;		
	}

	var parity = 0, abortus = 0;
	for(var i=0;i<this.historyCount; i++)
	{
		var histObj = this.arrHistory[i];
		if(histObj.elemDeliveryStatus.value==ANCHistoryPan.DELIVERY_STATUS["Delivery"])
		{
			parity += parseInt(histObj.elemNoOfBirths.value);
		}
		else if(histObj.elemDeliveryStatus.value==ANCHistoryPan.DELIVERY_STATUS["Abortion"])
		{
			abortus += 1; 
		}
	}
	document.getElementsByName("histParityNo")[0].value = parity;
	document.getElementsByName("histAbortusNo")[0].value = abortus;
	
	return true;
};

ANCHistoryPan.prototype.showANCTab = function()
{
	var imgObj = document.getElementById("imgANHisDtl");
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
		onclickImage(imgObj);
};

// ******************************************************************************


// ******************************************************************************
	// ANC History Detail Object
ANCHistory = function(_pan,_gravida)
{
	this.objPan = _pan;
	
	this.elemContainer = null;

	this.elemFirstRow = null;
	this.elemSecondRow = null;
		
	// Values
	this.gravidaNo = _gravida;
	this.deliveryStatus = null;			// 1-Delivery , 2-Abortus
	this.deliveryDate = null;			// Date of Delivery or Abortus
	this.pregnancyDuration = null;
	this.pregnancyRemarks = null;	
	this.deliveryPlaceId = null;
	this.deliveryPlace = null;	
	this.labourDuration = null;
	this.deliveryTypeId = null;
	this.isAntiDGiven = null;
	this.labourRemarks = null;
	this.noOfBirths = null;
	
	this.birthNatureId = null;
	this.genderCode = null;
	this.weight = null;
	this.babyBloodGroupCode = null;
	this.deathCause = null;
	this.deathAge = null;
	this.presentHealth = null;
	
	this.objANCChildren = null;
		
	// Elements
	this.elemGravidaNo = null;
	this.elemDeliveryStatus = null;			// 1-Delivery , 2-Abortus
	this.elemDeliveryDate = null;			// Date of Delivery or Abortus
	this.elemPregnancyDuration = null;
	this.elemPregnancyRemarks = null;	
	this.elemDeliveryPlaceId = null;
	this.elemDeliveryPlace = null;	
	this.elemLabourDuration = null;
	this.elemDeliveryTypeId = null;
	this.elemIsAntiDGiven = null;
	this.elemLabourRemarks = null;
	this.elemNoOfBirths = null;
	
	this.elemImgBirthList = null;
	this.elemChildContainer = null;
	
	this.elemBirthNatureId = null;
	this.elemGenderCode = null;
	this.elemWeight = null;
	this.elemBabyBloodGroupCode = null;
	this.elemDeathCause = null;
	this.elemDeathAge = null;
	this.elemPresentHealth = null;	
};

ANCHistory.prototype.clearValues = function()
{
	// Values
	this.deliveryStatus = "";			// 1-Delivery , 2-Abortus
	this.deliveryDate = "";			// Date of Delivery or Abortus
	this.pregnancyDuration = "";
	this.pregnancyRemarks = "";	
	this.deliveryPlaceId = "";
	this.deliveryPlace = "";	
	this.labourDuration = "";
	this.deliveryTypeId = "";
	this.isAntiDGiven = "";
	this.labourRemarks = "";
	this.noOfBirths = "";
	
	this.birthNatureId = "";
	this.genderCode = "";
	this.weight = "";
	this.babyBloodGroupCode = "";
	this.deathCause = "";
	this.deathAge = "";
	this.presentHealth = "";
	
	this.objANCChildren = new Array(0);
};
	
/*ANCHistory.prototype.create = function(_parent)
{
	this.elemContainer=_parent;
	var tr = _parent.insertRow(_parent.rows.length);
	//var tr = ANCHistoryPan.createElement("tr",_parent);
	tr.id = "Gravida#"+this.gravidaNo+"-1";
	this.elemFirstRow = tr;

	// Serial No and Gravida No
	var td = ANCHistoryPan.createElement("td",tr);
	td.width = "2%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	var div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var hidden = ANCHistoryPan.createInputElement("hidden", div);
	hidden.name = "histGravidaNo";
	hidden.value = this.gravidaNo;
	this.elemGravidaNo = hidden;
	hidden.Pragya_ANCHistoryControl = this;
	
	var font = ANCHistoryPan.createElement("font",div);
	font.color = "#000000";
	font.size = "2";
	font.face = "Verdana, Arial, Helvetica, sans-serif";
	
	font.innerHTML = "<b>"+this.gravidaNo+".</b>";
	
	// Delivery Status
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var select = ANCHistoryPan.createElement("select", div);
	select.name = "histDeliveryStatus";
	select.tabIndex = "1";
	var option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";
	for( opt in ANCHistoryPan.DELIVERY_STATUS)
	{
		option = ANCHistoryPan.createElement("option", select);
		option.value = ANCHistoryPan.DELIVERY_STATUS[opt];
		option.innerHTML = opt;
	}
	select.value = -1;
	this.elemDeliveryStatus = select;
	select.Pragya_ANCHistoryControl = this;
	ANCHistoryPan.addEvent(select, "change", ANCHistory.evtOnChangeDeliveryStatus);
	
	
	// Delivery Date
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var date = new DateValidator("histDeliveryDate");
	date.create(div);
	this.elemDeliveryDate = date;
	date.Pragya_ANCHistoryControl = this;
	
	// Pregnancy Duration
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var text = ANCHistoryPan.createElement("input",div);
	text.name = "histPregnancyDuration";
	text.size = "4";
	text.tabIndex = "1";
	text.maxLength = "2";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidatePositiveIntegerOnly);
	this.elemPregnancyDuration = text;
	text.Pragya_ANCHistoryControl = this;

	// Pregnancy Remarks
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	text = ANCHistoryPan.createElement("input",div);
	text.name = "histPregnancyRemarks";
	text.tabIndex = "1";
	text.maxLength = "100";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
	this.elemPregnancyRemarks = text;
	text.Pragya_ANCHistoryControl = this;

	// Delivery Place
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	select = ANCHistoryPan.createElement("select",div);
	select.name = "histDeliveryPlaceId";
	select.tabIndex = "1";
	var option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";
	
	var opts = document.getElementsByName("deliveryPlaceList")[0].options;
	for(var x=0; x<opts.length;x++)
	{
		var opt = opts[x];
		option = ANCHistoryPan.createElement("option", select);
		option.value = opt.value;
		option.innerHTML = opt.text;
	}
	select.value = -1;
	select.disabled = false;
	this.elemDeliveryPlaceId = select;
	select.Pragya_ANCHistoryControl = this;
	
	// Labour Duration
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	text = ANCHistoryPan.createElement("input",div);
	text.name = "histLabourDuration";
	text.size = "4";
	text.tabIndex = "1";
	text.maxLength = "2";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateNumericOnly);
	this.elemLabourDuration = text;
	text.Pragya_ANCHistoryControl = this;

	// Delivery Type
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	select = ANCHistoryPan.createElement("select",div);
	select.name = "histDeliveryTypeId";
	select.tabIndex = "1";
	var option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";
	
	var opts = document.getElementsByName("deliveryTypeList")[0].options;
	for(var x=0; x<opts.length;x++)
	{
		var opt = opts[x];
		option = ANCHistoryPan.createElement("option", select);
		option.value = opt.value;
		option.innerHTML = opt.text;
	}
	select.value = -1;
	select.disabled = false;
	this.elemDeliveryTypeId = select;
	select.Pragya_ANCHistoryControl = this;

	// Anti-D Given
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var select = ANCHistoryPan.createElement("select", div);
	select.name = "histIsAntiDGiven";
	select.tabIndex = "1";
	var option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";
	for( opt in ANCHistoryPan.YESNO_VALUE)
	{
		option = ANCHistoryPan.createElement("option", select);
		option.value = ANCHistoryPan.YESNO_VALUE[opt];
		option.innerHTML = opt;
	}
	select.value = -1;
	this.elemIsAntiDGiven = select;
	select.Pragya_ANCHistoryControl = this;

	// Labour Remarks
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	text = ANCHistoryPan.createElement("input",div);
	text.name = "histLabourRemarks";
	text.tabIndex = "1";
	text.maxLength = "100";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
	this.elemLabourRemarks = text;
	text.Pragya_ANCHistoryControl = this;

	// Number of Births
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	div.vAlign = "middle";
	
	text = ANCHistoryPan.createElement("input",div);
	text.name = "histNoOfBirths";
	text.size = "3";
	text.tabIndex = "1";
	text.maxLength = "1";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidatePositiveIntegerOnly);
	ANCHistoryPan.addEvent(text, "blur", ANCHistory.evtOnBlurSetChildrenList);
	this.elemNoOfBirths = text;
	text.Pragya_ANCHistoryControl = this;
	
	var img = ANCHistoryPan.createElement("img",div);
	img.src = "/HIS/hisglobal/images/avai/pl_small.gif";
	ANCHistoryPan.addEvent(img, "click", ANCHistory.evtOnClickShowHideChildrenList);
	this.elemImgBirthList = img;
	img.Pragya_ANCHistoryControl = this;

	// Birth Row
	tr = _parent.insertRow(_parent.rows.length);
	//tr = ANCHistoryPan.createElement("tr",_parent);
	tr.id = "Gravida#"+this.gravidaNo+"-2";	
	this.elemSecondRow = tr;
};*/

//ANCHistory.prototype.createReportRows = function(_parent)
ANCHistory.prototype.create = function(_parent)
{
	this.elemContainer=_parent;
	var tr = _parent.insertRow(_parent.rows.length);	
	tr.id = "Gravida#"+this.gravidaNo+"-1";
	this.elemFirstRow = tr;

	// Serial No and Gravida No
	var td = ANCHistoryPan.createElement("td",tr);
	td.width = "2%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	var div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var hidden = ANCHistoryPan.createInputElement("hidden", div);
	hidden.name = "histGravidaNo";
	hidden.value = this.gravidaNo;
	this.elemGravidaNo = hidden;
	hidden.Pragya_ANCHistoryControl = this;
	
	var font = ANCHistoryPan.createElement("font",div);
	font.color = "#000000";
	font.size = "2";
	font.face = "Verdana, Arial, Helvetica, sans-serif";
	font.innerHTML = "<b>"+this.gravidaNo+".</b>";
	
	// Delivery Status
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";

	if(!(this.deliveryStatus==null || this.deliveryStatus==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histDeliveryStatus";
		hidden.value = this.deliveryStatus;
		this.elemDeliveryStatus = hidden;
		hidden.Pragya_ANCHistoryControl = this;
	
		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		var flagOpt=false;
		for( opt in ANCHistoryPan.DELIVERY_STATUS)
		{
			if(ANCHistoryPan.DELIVERY_STATUS[opt]== this.deliveryStatus)
			{
				flagOpt=true;
				break;
			}
		}
		if(!flagOpt)	opt="";
		font.innerHTML = "<b>"+opt+"</b>";
	}
	else
	{
		var select = ANCHistoryPan.createElement("select", div);
		select.name = "histDeliveryStatus";
		select.tabIndex = "1";
		select.className = "ancComboSmallMedium";
		var option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select";
		for( opt in ANCHistoryPan.DELIVERY_STATUS)
		{
			option = ANCHistoryPan.createElement("option", select);
			option.value = ANCHistoryPan.DELIVERY_STATUS[opt];
			option.innerHTML = opt;
		}
		select.value = -1;
		this.elemDeliveryStatus = select;
		select.Pragya_ANCHistoryControl = this;
		ANCHistoryPan.addEvent(select, "change", ANCHistory.evtOnChangeDeliveryStatus);
	}
	
		
	// Delivery Date
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.deliveryDate==null || this.deliveryDate==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histDeliveryDate";
		var date = new DateValidator("histDeliveryDate",hidden);
		date.setDate(this.deliveryDate, "dd-Mon-yyyy");
		date.Pragya_ANCHistoryControl = this;
		this.elemDeliveryDate = date;
		hidden.Pragya_ANCHistoryControl = this;
		hidden.value = this.deliveryDate;
		ANCHistoryPan.prototype.getLastDeliveryDate();

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.deliveryDate+"</b>";
	}
	else
	{
		var date = new DateValidator("histDeliveryDate");
		date.create(div);
		date.addFuncOnBlur(ANCHistory.evtOnChangeDeliveryDate);
		this.elemDeliveryDate = date;
		date.Pragya_ANCHistoryControl = this;
	}
	
	// Pregnancy Duration
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.pregnancyDuration==null || this.pregnancyDuration==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histPregnancyDuration";
		hidden.value = this.pregnancyDuration;
		this.elemPregnancyDuration = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.pregnancyDuration+"</b>";
	}
	else
	{
		var text = ANCHistoryPan.createElement("input",div);
		text.name = "histPregnancyDuration";
		text.size = "4";
		text.tabIndex = "1";
		text.maxLength = "2";
		text.className = "ancTextboxVerySmall";
		this.objPan.refreshGravida();
		
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidatePositiveIntegerOnly);
		this.elemPregnancyDuration = text;
		text.Pragya_ANCHistoryControl = this;
	}

	// Pregnancy Remarks
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.pregnancyRemarks==null || this.pregnancyRemarks==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histPregnancyRemarks";
		hidden.value = this.pregnancyRemarks;
		this.elemPregnancyRemarks = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.pregnancyRemarks+"</b>";
	}
	else
	{	
		text = ANCHistoryPan.createElement("input",div);
		text.name = "histPregnancyRemarks";
		text.tabIndex = "1";
		text.maxLength = "100";
		text.className = "ancTextboxNormal";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
		this.elemPregnancyRemarks = text;
		text.Pragya_ANCHistoryControl = this;
	}

	// Delivery Place
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if( (!(this.deliveryStatus==null || this.deliveryStatus=="") && this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"]) 
		|| !(this.deliveryPlaceId==null || this.deliveryPlaceId==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histDeliveryPlaceId";
		hidden.value = this.deliveryPlaceId;
		this.elemDeliveryPlaceId = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		
		var opts = document.getElementsByName("deliveryPlaceList")[0].options;
		var val="";
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			if(this.deliveryPlaceId==opt.value)
			{
				val = opt.text;
				break;
			}
		}
		font.innerHTML = "<b>"+val+"</b>";
	}
	else
	{
		select = ANCHistoryPan.createElement("select",div);
		select.name = "histDeliveryPlaceId";
		select.tabIndex = "1";
		select.className = "ancComboSmallMedium";
		var option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select";
		
		var opts = document.getElementsByName("deliveryPlaceList")[0].options;
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			option = ANCHistoryPan.createElement("option", select);
			option.value = opt.value;
			option.innerHTML = opt.text;
		}
		select.value = -1;
		select.disabled = false;
		this.elemDeliveryPlaceId = select;
		select.Pragya_ANCHistoryControl = this;
	}
	
	// Labour Duration
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if( (!(this.deliveryStatus==null || this.deliveryStatus=="") && this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"]) 
		|| !(this.labourDuration==null || this.labourDuration==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histLabourDuration";
		hidden.value = this.labourDuration;
		this.elemLabourDuration = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.labourDuration+"</b>";
	}
	else
	{	
		text = ANCHistoryPan.createElement("input",div);
		text.name = "histLabourDuration";
		text.size = "4";
		text.tabIndex = "1";
		text.maxLength = "2";
		text.className = "ancTextboxVerySmall";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateNumericOnly);
		this.elemLabourDuration = text;
		text.Pragya_ANCHistoryControl = this;
	}
	
	// Delivery Type
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if( (!(this.deliveryStatus==null || this.deliveryStatus=="") && this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"]) 
		|| !(this.deliveryTypeId==null || this.deliveryTypeId==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histDeliveryTypeId";
		hidden.value = this.deliveryTypeId;
		this.elemDeliveryTypeId = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		
		var opts = document.getElementsByName("deliveryTypeList")[0].options;
		var val="";
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			if(this.deliveryTypeId==opt.value)
			{
				val = opt.text;
				break;
			}
		}
		font.innerHTML = "<b>"+val+"</b>";
	}
	else
	{
		select = ANCHistoryPan.createElement("select",div);
		select.name = "histDeliveryTypeId";
		select.tabIndex = "1";
		select.className = "ancComboNormal";
		var option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select Value";
		
		var opts = document.getElementsByName("deliveryTypeList")[0].options;
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			option = ANCHistoryPan.createElement("option", select);
			option.value = opt.value;
			option.innerHTML = opt.text;
		}
		select.value = -1;
		select.disabled = false;
		this.elemDeliveryTypeId = select;
		select.Pragya_ANCHistoryControl = this;
	}

	// Anti-D Given
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if( (!(this.deliveryStatus==null || this.deliveryStatus=="") && this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"]) 
		|| !(this.isAntiDGiven==null || this.isAntiDGiven==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histIsAntiDGiven";
		hidden.value = this.isAntiDGiven;
		this.elemIsAntiDGiven = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		var flagOpt=false;
		for( opt in ANCHistoryPan.YESNO_VALUE)
		{
			if(ANCHistoryPan.YESNO_VALUE[opt]== this.isAntiDGiven)
			{
				flagOpt=true;
				break;
			}
		}
		if(!flagOpt)	opt="";
		font.innerHTML = "<b>"+opt+"</b>";
	}
	else
	{
		var select = ANCHistoryPan.createElement("select", div);
		select.name = "histIsAntiDGiven";
		select.tabIndex = "1";
		select.className = "ancComboSmallMedium";
		var option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select";
		for( opt in ANCHistoryPan.YESNO_VALUE)
		{
			option = ANCHistoryPan.createElement("option", select);
			option.value = ANCHistoryPan.YESNO_VALUE[opt];
			option.innerHTML = opt;
		}
		select.value = -1;
		this.elemIsAntiDGiven = select;
		select.Pragya_ANCHistoryControl = this;
	}

	// Labour Remarks
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if( (!(this.deliveryStatus==null || this.deliveryStatus=="") && this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"]) 
		|| !(this.labourRemarks==null || this.labourRemarks==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histLabourRemarks";
		hidden.value = this.labourRemarks;
		this.elemLabourRemarks = hidden;
		hidden.Pragya_ANCHistoryControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.labourRemarks+"</b>";
	}
	else
	{	
		text = ANCHistoryPan.createElement("input",div);
		text.name = "histLabourRemarks";
		text.tabIndex = "1";
		text.maxLength = "100";
		text.className = "ancTextboxNormal";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
		this.elemLabourRemarks = text;
		text.Pragya_ANCHistoryControl = this;
	}

	// Number of Births
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	div.vAlign = "middle";
	
	if( (!(this.deliveryStatus==null || this.deliveryStatus=="") && this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"]) 
		|| !(this.noOfBirths==null || this.noOfBirths==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histNoOfBirths";
		hidden.value = this.noOfBirths;
		this.elemNoOfBirths = hidden;
		hidden.Pragya_ANCHistoryControl = this;
	
		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		if(parseInt(this.noOfBirths)<=0)
			font.innerHTML = "<b></b>&nbsp;&nbsp;";
		else
			font.innerHTML = "<b>"+this.noOfBirths+"</b>&nbsp;&nbsp;";
	}
	else
	{
		text = ANCHistoryPan.createElement("input",div);
		text.name = "histNoOfBirths";
		text.size = "3";
		text.tabIndex = "1";
		text.maxLength = "1";
		text.className = "ancTextboxVerySmall";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidatePositiveIntegerOnly);
		ANCHistoryPan.addEvent(text, "blur", ANCHistory.evtOnBlurSetChildrenList);
		this.elemNoOfBirths = text;
		text.Pragya_ANCHistoryControl = this;
		this.noOfBirths=0;
	}
	var img = ANCHistoryPan.createElement("img",div);
	this.elemImgBirthList = img;
	img.style.height=0;
	img.style.width=0;
	img.style.cursor="pointer";
	img.Pragya_ANCHistoryControl = this;
	
	// Birth Row
	tr = _parent.insertRow(_parent.rows.length);
	tr.id = "Gravida#"+this.gravidaNo+"-2";	
	this.elemSecondRow = tr;

	if(this.noOfBirths>0)
	{
		this.showHideImgBirthList(true);
		this.createChildHeader();

		for(var j=0;j<this.noOfBirths;j++)
		{
			this.objANCChildren[j].create(this.elemChildContainer);
		}		
	}
	if(this.deliveryStatus==ANCHistoryPan.DELIVERY_STATUS["Abortion"])
		this.hideDeliveryData(true);
	else
		this.hideDeliveryData(false);
	
};

ANCHistory.prototype.showHideImgBirthList = function(_flag)
{
	if(_flag)
	{
		this.elemImgBirthList.style.height="";
		this.elemImgBirthList.style.width="";
		this.elemImgBirthList.src = "/HIS/hisglobal/images/avai/pl_small.gif";
		ANCHistoryPan.addEvent(this.elemImgBirthList, "click", ANCHistory.evtOnClickShowHideChildrenList);
	}
	else
	{
		this.elemImgBirthList.src = "";
		this.elemImgBirthList.style.height=0;
		this.elemImgBirthList.style.width=0;
	}	
};

ANCHistory.prototype.deleteHistoryRow = function()
{
	this.elemContainer.deleteRow(this.elemFirstRow.rowIndex);
	this.elemContainer.deleteRow(this.elemSecondRow.rowIndex);
};

ANCHistory.prototype.isEmptyLastChildHistory = function(_childCount)
{
	var diff = this.noOfBirths - _childCount;
	var flag = true;
	for(var i=0; i<diff; i++)
	{
		flag = (flag && this.objANCChildren[this.noOfBirths-i-1].isEmpty());
	}
	return flag;
};

ANCHistory.prototype.isSetBirthCount = function(_childCount)
{
	var flag = true;
	if(this.noOfBirths > _childCount)
	{		
		if(!this.isEmptyLastChildHistory(_childCount))
		{
			if(!confirm("Are you sure to remove entered Birth history details ?"))
				flag = false;
		}
	}
	else if(this.noOfBirths == _childCount)
		flag = false;
	return flag;
};

ANCHistory.prototype.createBirthList = function(count)
{
	if(count==0)
	{
		ANCHistoryPan.removeAllChildren(this.elemSecondRow);
		this.objANCChildren = null;
		this.elemChildContainer = null;
		this.noOfBirths = parseInt(count);
		
		if(this.elemSecondRow.Control_div1 && this.elemSecondRow.Control_div1.style.display == "block")
		{
			ANCHistory.evtOnClickShowHideChildrenList(null,this.elemImgBirthList);
		}		
		return;	
	}
	if(this.noOfBirths == 0)
	{
		this.createChildHeader();
	}
	var histChildRows = new Array(count);
	if(this.objANCChildren!=null)
	{
		var len = (count<this.noOfBirths)? count:(this.noOfBirths);
		for(var i=0; i<len;i++)
			histChildRows[i] = this.objANCChildren[i];
	}
	var diff = count - this.noOfBirths;
	if(diff<0)
	{
		for(var i=0;i<Math.abs(diff);i++)
		{
			this.objANCChildren[parseInt(this.noOfBirths)+diff+i].deleteChildRow();
		}
	}
	else if(diff>0)
	{
		for(var i=parseInt(this.noOfBirths);i<count;i++)
		{
			var child = new ANCChildHistory(i+1, this);
			child.create(this.elemChildContainer);
			histChildRows[i]= child;
		}
	}
	this.objANCChildren = histChildRows;
	this.noOfBirths = count;

	/*var td = ANCHistoryPan.createElement("td",this.elemSecondRow);
	td.className="tdfonthead";

	td = ANCHistoryPan.createElement("td",this.elemSecondRow);
	td.colSpan="10";
	
	var tbl = ANCHistoryPan.createElement("table",td);
	tbl.width="100%";
	tbl.border="0";
	tbl.cellpadding="0";
	tbl.cellspacing="1";
	var thead = ANCHistoryPan.createElement("thead",tbl);
			
	var tr = tbl.insertRow(tbl.rows.length);
	//var tr = ANCHistoryPan.createElement("tr",tbl);
	tr.innerHTML = document.getElementById("trSubHead").innerHTML;*/

	/*var arrANCChildren = new Array(count);
	for(var i=0;i<count;i++)
	{
		var child = new ANCChildHistory(i+1, this);
		child.create(tbl);
		arrANCChildren[i]= child;
	}	
	this.objANCChildren = arrANCChildren;
	this.noOfBirths = count;*/
};

ANCHistory.prototype.createChildHeader = function()
{
	ANCHistoryPan.removeAllChildren(this.elemSecondRow);
	var td = ANCHistoryPan.createElement("td",this.elemSecondRow);		
	this.elemSecondRow.Control_td1 = td;		
	
	var div = ANCHistoryPan.createElement("div",td);
	div.align = "center";
	div.style.display = "none";
	this.elemSecondRow.Control_div1 = div;

	td = ANCHistoryPan.createElement("td",this.elemSecondRow);
	td.colSpan="10";
	
	div = ANCHistoryPan.createElement("div",td);
	div.align = "center";
	div.style.display = "none";
	this.elemSecondRow.Control_div2 = div;
	
	var tbl = ANCHistoryPan.createElement("table",div);
	tbl.width="100%";
	tbl.border="0";
	tbl.cellpadding="0";
	tbl.cellspacing="1";
	this.elemChildContainer = tbl;
	var tr = tbl.insertRow(tbl.rows.length);		
	var trSub =document.getElementById("trSubHead");
	for(var i=0;i<parseInt(trSub.childNodes.length);i++)
	{
		var tdSub = trSub.childNodes[i];
		var td= tdSub.cloneNode(true);
		tr.appendChild(td);
	}
};

ANCHistory.prototype.hideDeliveryData = function(flag)
{
	if(flag)
	{
		this.elemDeliveryPlaceId.value = "-1";
		this.elemLabourDuration.value = "";
		this.elemDeliveryTypeId.value = "-1";
		this.elemIsAntiDGiven.value = "-1";
		this.elemLabourRemarks.value = "";
		this.elemNoOfBirths.value = "";
		
		this.createBirthList(0);
	}
	this.elemDeliveryPlaceId.disabled = flag;
	this.elemLabourDuration.disabled = flag;
	this.elemDeliveryTypeId.disabled = flag;
	this.elemIsAntiDGiven.disabled = flag;
	this.elemLabourRemarks.disabled = flag;
	this.elemNoOfBirths.disabled = flag;
};

ANCHistory.prototype.setPregnancyDuration = function(_dur)
{
	this.elemPregnancyDuration.value = _dur;
};

ANCHistory.prototype.isEmpty = function()
{
	var flag = true;
	
	if(this.elemDeliveryStatus.value != "-1")
		flag = false;
	else if(this.elemDeliveryDate.element.value != this.elemDeliveryDate.format && this.elemDeliveryDate.element.value!="")
		flag = false;
	else if(this.elemPregnancyDuration.value != "")
		flag = false;
	else if(this.elemPregnancyRemarks.value != "")
		flag = false;
	else if(this.elemDeliveryPlaceId.value != "-1")
		flag = false;
	else if(this.elemLabourDuration.value != "")
		flag = false;
	else if(this.elemDeliveryTypeId.value != "-1")
		flag = false;
	else if(this.elemIsAntiDGiven.value != "-1")
		flag = false;
	else if(this.elemLabourRemarks.value != "")
		flag = false;
	else if(this.elemNoOfBirths.value != "")
		flag = false;
	
	return flag;
};

ANCHistory.prototype.validateIfNotEmpty = function()
{
	//if(!this.isEmpty())
	{
		if(this.elemDeliveryStatus.value == "-1")
		{
			this.objPan.showANCTab();
			alert("Please Enter Delivery Status");
			this.elemDeliveryStatus.focus();
			return false;
		}	
		//if(this.objPan.gravida == this.gravidaNo)
		{
			if(this.elemDeliveryDate.element.value == this.elemDeliveryDate.format || this.elemDeliveryDate.element.value=="")
			{
				this.objPan.showANCTab();
				alert("Please Enter Delivery/Abortion Date");
				this.elemDeliveryDate.element.focus();
				return false;
			}
		}
		if(!(this.elemDeliveryDate.element.value == this.elemDeliveryDate.format || this.elemDeliveryDate.element.value==""))
		{		
			if(!this.elemDeliveryDate.validateDate())
			{
				this.objPan.showANCTab();
				alert("Please Enter a Correct Delivery Date");
				this.elemDeliveryDate.element.focus();
				return false;
			}
			if(this.elemDeliveryDate.date>=this.objPan.entryDate)
			{
				this.objPan.showANCTab();
				alert("Delivery Date should be less than Current Date");
				this.elemDeliveryDate.element.focus();
				return false;
			}
		}
		// Pregnancy Duration
		if(this.elemPregnancyDuration.value=="")
		{
			this.objPan.showANCTab();
			alert("Please Enter Pregnancy Duration");
			this.elemPregnancyDuration.focus();
			return false;
		}	
		if(this.elemPregnancyDuration.value!="")
		{			
			if(parseInt(this.elemPregnancyDuration.value)<1)
			{
				this.objPan.showANCTab();
				alert("Pregnancy Duration should be greater than 0");
				this.elemPregnancyDuration.focus();
				return false;
			}
			
			if(this.elemDeliveryStatus.value==ANCHistoryPan.DELIVERY_STATUS["Delivery"] && parseInt(this.elemPregnancyDuration.value)<=ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_START_LIMIT)
			{
				this.objPan.showANCTab();
				alert("For Delivery, Pregnancy Duration should be greater than "+ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_START_LIMIT+" Weeks");
				this.elemPregnancyDuration.focus();
				return false;
			}
			if(parseInt(this.elemPregnancyDuration.value)>ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_MAX_LIMIT)
			{
				this.objPan.showANCTab();
				alert("Pregnancy Duration should be less than "+ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_MAX_LIMIT+" Weeks");
				this.elemPregnancyDuration.focus();
				return false;
			}
			/*// Abortion Duration Check
			if(this.elemDeliveryStatus.value==ANCHistoryPan.DELIVERY_STATUS["Abortion"] && this.elemPregnancyDuration.value>ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_START_LIMIT)
			{
				this.objPan.showANCTab();
				alert("For Delivery Pregnancy Abortion should be less than "+ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_START_LIMIT+" Weeks");
				this.elemPregnancyDuration.focus();
				return false;
			}*/
		}
		// Validate Delivery Date Correctness & Sequence Check
		if(this.gravidaNo>1)
		{ 
			var objUpperGravidaHistory = this.objPan.arrHistory[this.gravidaNo-2];
			var objPrevDelDate = objUpperGravidaHistory.elemDeliveryDate.date;
			var curDelDate = this.elemDeliveryDate.date;
			var curPregDur = parseInt(this.elemPregnancyDuration.value);
			curDelDate = addToDate(curDelDate,-(7*curPregDur),"D");
			
			if(curDelDate<=objPrevDelDate)
			{
				this.objPan.showANCTab();
				alert("Delivery/Abortion Date overlaps with Previous Gravida");
				this.elemDeliveryDate.focus();
				return false;
			}
		}
		
		/*if(this.elemPregnancyRemarks.value == "")
		{
			this.objPan.showANCTab();
			alert("Please Enter Pregnancy Remarks");
			this.elemPregnancyRemarks.focus();
			return false;
		}	
		if(this.elemDeliveryPlaceId.disabled==false && this.elemDeliveryPlaceId.value == "-1")
		{
			this.objPan.showANCTab();
			alert("Please Enter Delivery Place");
			this.elemDeliveryPlaceId.focus();
			return false;
		}	
		if(this.elemLabourDuration.disabled==false && this.elemLabourDuration.value == "")
		{
			this.objPan.showANCTab();
			alert("Please Enter Labour Duration");
			this.elemLabourDuration.focus();
			return false;
		}	
		if(this.elemDeliveryTypeId.disabled==false && this.elemDeliveryTypeId.value == "-1")
		{
			this.objPan.showANCTab();
			alert("Please Enter Delivery Type");
			this.elemDeliveryTypeId.focus();
			return false;
		}	
		if(this.elemIsAntiDGiven.disabled==false && this.elemIsAntiDGiven.value == "-1")
		{
			this.objPan.showANCTab();
			alert("Please Select Is Anti-D Given or Not");
			this.elemIsAntiDGiven.focus();
			return false;
		}	
		if(this.elemLabourRemarks.disabled==false && this.elemLabourRemarks.value == "")
		{
			this.objPan.showANCTab();
			alert("Please Enter Labour Remarks");
			this.elemLabourRemarks.focus();
			return false;
		}	*/
		if(this.elemNoOfBirths.disabled==false && this.elemNoOfBirths.value == "")
		{
			this.objPan.showANCTab();
			alert("Please Enter No. of Births");
			this.elemNoOfBirths.focus();
			return false;
		}
		
		if(this.elemNoOfBirths.disabled==false)
		{
			for(var i=0; i<this.elemNoOfBirths.value; i++)
			{
				if(!this.objANCChildren[i].validate())
				{
					/*this.objPan.showANCTab();
					if(this.elemSecondRow.Control_div1 && this.elemSecondRow.Control_div1.style.display == "none")
					{
						ANCHistory.evtOnClickShowHideChildrenList(null,this.elemImgBirthList);
					}*/
					return false;
				}
			}
		}
		if(this.objPan.gravida == this.gravidaNo)
		{
			// Validate Gravida Row
			if(!validateGravidaHistoryRowData())
				return false;
		}
	}
	return true;
};

ANCHistory.evtOnChangeDeliveryStatus = function(evnt)
{
	try
	{
		var elem = ANCHistoryPan.getTargetElement(evnt);
		while(elem.tagName.toLowerCase()!="select")
			elem=elem.parentNode;
		var aNCHistObj = elem.Pragya_ANCHistoryControl;
		
		if(elem.value==ANCHistoryPan.DELIVERY_STATUS["Abortion"])
			aNCHistObj.hideDeliveryData(true);
		else
			aNCHistObj.hideDeliveryData(false);
	}
	catch(e)
	{
		alert("Error Message :-"+e.message);
	}
};

ANCHistory.evtOnChangeDeliveryDate = function(evnt)
{
	try
	{
		var elem = DateValidator.getTargetElement(evnt);
		while(elem.tagName.toUpperCase()!="INPUT")
			elem=elem.parentNode;
		
		var datValObj = elem.Pragya_Control;
		var aNCHistObj = datValObj.Pragya_ANCHistoryControl;

		if(elem.value!="" && !ValidationFunctions.validateDateFormatValue(elem.value))
		{
			alert("Date is not in Proper Format.. \nPlease Enter in "+datValObj.format+" Format");
			datValObj.element.focus();
			return;
		}
		if(elem.value!="" && !datValObj.validateDate())
		{
			alert("Not a Proper Date");
			datValObj.element.focus();
		}		
		
		var pregDur = "";
		if(!(elem.value == aNCHistObj.elemDeliveryDate.format || elem.value==""))
		{		
			if(aNCHistObj.elemDeliveryDate.validateDate())
			{
				var delDate = aNCHistObj.elemDeliveryDate.date;
				if(document.getElementsByName("gestationStartDate")[0])
				{
					var gestdt = document.getElementsByName("gestationStartDate")[0];
					var gravidaNo = parseInt(document.getElementsByName("gravidaNo")[0].value);
					// If Current History raw is forcefully entered output of running gravida 
					// in that the Gravida NO on form will be same as Gravida No of History Row
					if(aNCHistObj.gravidaNo == gravidaNo)
					{
						var gestStrDt = convertStrToDate(gestdt.value,"dd-Mon-yyyy");
						var gestWeek = Math.ceil((delDate-gestStrDt)/(1000*60*60*24*7));
						pregDur = gestWeek;
					}
				} 
			}
		}
		aNCHistObj.setPregnancyDuration(pregDur);
	}
	catch(e)
	{
		alert("Error Message :-"+e.message);
	}
};

ANCHistory.evtOnBlurSetChildrenList = function(evnt)
{
	try
	{
		var ancHistPan = window.Pragyas_ANCHistoryPan;
		
		var elem = ANCHistoryPan.getTargetElement(evnt);
		while(elem.tagName.toLowerCase()!="input")
			elem=elem.parentNode;
		var aNCHistObj = elem.Pragya_ANCHistoryControl;
		if(elem.value<1)
		{
			alert("Number of Children should be greater than 0");
			aNCHistObj.showHideImgBirthList(false);
			elem.focus();
			if(aNCHistObj.noOfBirths==0)
				elem.value = "";
			else
				elem.value = aNCHistObj.noOfBirths;
			return;
		}
		else if(elem.value>ancHistPan.maxNoOfBirth)
		{
			alert("Number of Birth cannot be greater than "+ancHistPan.maxNoOfBirth+"");
			//elem.value = "";
			elem.value=ancHistPan.maxNoOfBirth;
			aNCHistObj.createBirthList(elem.value);
			aNCHistObj.showHideImgBirthList(true);
		}
		else if(aNCHistObj.isSetBirthCount(elem.value))
		{
			aNCHistObj.createBirthList(elem.value);
			aNCHistObj.showHideImgBirthList(true);
		}
		
		
		else
		{
			elem.value = aNCHistObj.noOfBirths;
			if(elem.value>0)
				aNCHistObj.showHideImgBirthList(true);
			else
				aNCHistObj.showHideImgBirthList(false);			
		}
	}
	catch(e)
	{
		alert("Error Message :-"+e.message);
	}
};

ANCHistory.evtOnClickShowHideChildrenList = function(evnt,_obj)
{
	try
	{
		var elem = _obj;
		if(evnt!=null)
		{
			var elem = ANCHistoryPan.getTargetElement(evnt);
			while(elem.tagName.toLowerCase()!="img")
				elem=elem.parentNode;
		}
			
		var aNCHistObj = elem.Pragya_ANCHistoryControl;
		if(typeof aNCHistObj.elemSecondRow.Control_div1 != 'undefined')
		{
			if(aNCHistObj.elemSecondRow.Control_div1.style.display == "none")
			{
				elem.src = "/HIS/hisglobal/images/avai/mi_small.gif";
				aNCHistObj.elemSecondRow.Control_td1.className = "tdfonthead";
				aNCHistObj.elemSecondRow.Control_div1.style.display = "block";
				aNCHistObj.elemSecondRow.Control_div2.style.display = "block";
			}
			else if(aNCHistObj.elemSecondRow.Control_div1.style.display == "block")
			{
				elem.src = "/HIS/hisglobal/images/avai/pl_small.gif";
				aNCHistObj.elemSecondRow.Control_td1.className = "";
				aNCHistObj.elemSecondRow.Control_div1.style.display = "none";
				aNCHistObj.elemSecondRow.Control_div2.style.display = "none";
			}
		}
	}
	catch(e)
	{
		alert("Error Message :-"+e.message);
	}
};
// ******************************************************************************


// ******************************************************************************
	// ANC History Child Detail Object
ANCChildHistory = function(_childNo, _objParent)
{
	this.objParentGravidaHist = _objParent;
	
	this.elemTable = null;
	this.elemContainer = null;

	// Values
	this.childNo = _childNo;
	this.birthNatureId = null;
	this.genderCode = null;
	this.weight = null;
	this.babyBloodGroupCode = null;
	this.presentHealth = null;
	this.deathAge = null;
	this.deathCause = null;
	
	// Elements
	this.elemChildNo = null;
	this.elemBirthNatureId = null;
	this.elemGenderCode = null;
	this.elemWeight = null;
	this.elemBabyBloodGroupCode = null;
	this.elemPresentHealth = null;	
	this.elemDeathAge = null;
	this.elemDeathCause = null;
};
	
ANCChildHistory.prototype.clearValues = function()
{
	// Values	
	this.birthNatureId = "";
	this.genderCode = "";
	this.weight = "";
	this.babyBloodGroupCode = "";
	this.presentHealth = "";
	this.deathAge = "";
	this.deathCause = "";
};
	
/*ANCChildHistory.prototype.create = function(_parent)
{
	this.elemTable = _parent;
	var tr = _parent.insertRow(_parent.rows.length);
	tr.id = "Child#"+this.childNo;
	this.elemContainer = tr;

	// Serial No and Child No
	var td = ANCHistoryPan.createElement("td",tr);
	td.width = "2%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	var div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var hidden = ANCHistoryPan.createInputElement("hidden", div);
	hidden.name = "histChildNo";
	hidden.value = this.childNo;
	this.elemChildNo = hidden;
	hidden.Pragya_ANCChildControl = this;
	
	var font = ANCHistoryPan.createElement("font",div);
	font.color = "#000000";
	font.size = "2";
	font.face = "Verdana, Arial, Helvetica, sans-serif";	
	font.innerHTML = "<b>"+this.childNo+".</b>";
	
	// Birth Narture Id
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var select = ANCHistoryPan.createElement("select", div);
	select.name = "histBirthNatureId";
	select.tabIndex = "1";
	var option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";
	for( opt in ANCHistoryPan.BIRTH_NATURE)
	{
		option = ANCHistoryPan.createElement("option", select);
		option.value = opt;
		option.innerHTML = ANCHistoryPan.BIRTH_NATURE[opt];
	}
	select.value = -1;
	this.elemBirthNatureId = select;
	select.Pragya_ANCChildControl = this;
	//ANCHistoryPan.addEvent(select, "change", ANCHistory.evtOnChangeBirthNature);
	
	// Gender
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	select = ANCHistoryPan.createElement("select",div);
	select.name = "histGenderCode";
	select.tabIndex = "1";
	option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";	
	var opts = document.getElementsByName("genderList")[0].options;
	for(var x=0; x<opts.length;x++)
	{
		var opt = opts[x];
		option = ANCHistoryPan.createElement("option", select);
		option.value = opt.value;
		option.innerHTML = opt.text;
	}
	select.value = -1;
	this.elemGenderCode = select;
	select.Pragya_ANCChildControl = this;

	// Weight at Birth
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var text = ANCHistoryPan.createElement("input",div);
	text.name = "histWeight";
	text.size = "6";
	text.tabIndex = "1";
	text.maxLength = "5";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateNumericOnly);	
	this.elemWeight = text;
	text.Pragya_ANCChildControl = this;
	
	// Baby Blood Group
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	select = ANCHistoryPan.createElement("select",div);
	select.name = "histBabyBloodGroupCode";
	select.tabIndex = "1";
	option = ANCHistoryPan.createElement("option",select);
	option.value = ANCHistoryPan.NO_SELECT;
	option.innerHTML = "Select Value";	
	var opts = document.getElementsByName("bloodGroupList")[0].options;
	for(var x=0; x<opts.length;x++)
	{
		var opt = opts[x];
		option = ANCHistoryPan.createElement("option", select);
		option.value = opt.value;
		option.innerHTML = opt.text;
	}
	select.value = -1;
	this.elemBabyBloodGroupCode = select;
	select.Pragya_ANCChildControl = this;

	// Present Health of Baby
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	text = ANCHistoryPan.createElement("input",div);
	text.name = "histPresentHealth";
	text.tabIndex = "1";
	text.maxLength = "100";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
	this.elemPresentHealth = text;
	text.Pragya_ANCChildControl = this;
	
	// Age at Death
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var text = ANCHistoryPan.createElement("input",div);
	text.name = "histDeathAge";
	text.size = "10";
	text.tabIndex = "1";
	text.maxLength = "10";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);	
	this.elemDeathAge = text;
	text.Pragya_ANCChildControl = this;
	
	// Death Cause
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	text = ANCHistoryPan.createElement("input",div);
	text.name = "histDeathCause";
	text.tabIndex = "1";
	text.maxLength = "50";
	ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
	this.elemDeathCause = text;
	text.Pragya_ANCChildControl = this;
};*/

//ANCChildHistory.prototype.createReportRow = function(_parent)
ANCChildHistory.prototype.create = function(_parent)
{
	this.elemTable = _parent;
	var tr = _parent.insertRow(_parent.rows.length);
	tr.id = "Child#"+this.childNo;
	this.elemContainer = tr;

	// Serial No and Child No
	var td = ANCHistoryPan.createElement("td",tr);
	td.width = "2%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	var div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	var hidden = ANCHistoryPan.createInputElement("hidden", div);
	hidden.name = "histChildNo";
	hidden.value = this.childNo;
	this.elemChildNo = hidden;
	hidden.Pragya_ANCChildControl = this;
	
	var font = ANCHistoryPan.createElement("font",div);
	font.color = "#000000";
	font.size = "2";
	font.face = "Verdana, Arial, Helvetica, sans-serif";
	font.innerHTML = "<b>"+this.childNo+".</b>";
	
	// Birth Narture Id
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.birthNatureId==null || this.birthNatureId==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histBirthNatureId";
		hidden.value = this.birthNatureId;
		this.elemBirthNatureId = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+ANCHistoryPan.BIRTH_NATURE[this.birthNatureId]+"</b>";
	}
	else
	{
		var select = ANCHistoryPan.createElement("select", div);
		select.name = "histBirthNatureId";
		select.tabIndex = "1";
		select.className = "ancComboSmallMedium";
		var option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select Value";
		for( opt in ANCHistoryPan.BIRTH_NATURE)
		{
			option = ANCHistoryPan.createElement("option", select);
			option.value = opt;
			option.innerHTML = ANCHistoryPan.BIRTH_NATURE[opt];
		}
		select.value = -1;
		this.elemBirthNatureId = select;
		select.Pragya_ANCChildControl = this;
		//ANCHistoryPan.addEvent(select, "change", ANCHistory.evtOnChangeBirthNature);
	}
	
	// Gender
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.genderCode==null || this.genderCode==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histGenderCode";
		hidden.value = this.genderCode;
		this.elemGenderCode = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";

		var opts = document.getElementsByName("genderList")[0].options;
		var val="";
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			if(this.genderCode== opt.value)
			{				
				val = opt.text;
				break;
			}
		}
		font.innerHTML = "<b>"+val+"</b>";
	}
	else
	{
		select = ANCHistoryPan.createElement("select",div);
		select.name = "histGenderCode";
		select.tabIndex = "1";
		select.className = "ancComboSmallMedium";
		option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select";	
		var opts = document.getElementsByName("genderList")[0].options;
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			option = ANCHistoryPan.createElement("option", select);
			option.value = opt.value;
			option.innerHTML = opt.text;
		}
		select.value = -1;
		this.elemGenderCode = select;
		select.Pragya_ANCChildControl = this;
	}

	// Weight at Birth
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.weight==null || this.weight==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histWeight";
		hidden.value = this.weight;
		this.elemWeight = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.weight+"</b>";
	}
	else
	{
		var text = ANCHistoryPan.createElement("input",div);
		text.name = "histWeight";
		text.size = "6";
		text.tabIndex = "1";
		text.maxLength = "3";
		text.className = "ancTextboxSmall";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidatePositiveNumericOnly);	
		this.elemWeight = text;
		text.Pragya_ANCChildControl = this;
	}
	
	// Baby Blood Group
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.babyBloodGroupCode==null || this.babyBloodGroupCode==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histBabyBloodGroupCode";
		hidden.value = this.babyBloodGroupCode;
		this.elemBabyBloodGroupCode = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";

		var opts = document.getElementsByName("bloodGroupList")[0].options;
		var val="";
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			if(this.babyBloodGroupCode== opt.value)
			{				
				val = opt.text;
				break;
			}
		}
		font.innerHTML = "<b>"+val+"</b>";		
	}
	else
	{
		select = ANCHistoryPan.createElement("select",div);
		select.name = "histBabyBloodGroupCode";
		select.tabIndex = "1";
		select.className = "ancComboSmallMedium";
		option = ANCHistoryPan.createElement("option",select);
		option.value = ANCHistoryPan.NO_SELECT;
		option.innerHTML = "Select Value";	
		var opts = document.getElementsByName("bloodGroupList")[0].options;
		for(var x=0; x<opts.length;x++)
		{
			var opt = opts[x];
			option = ANCHistoryPan.createElement("option", select);
			option.value = opt.value;
			option.innerHTML = opt.text;
		}
		select.value = -1;
		this.elemBabyBloodGroupCode = select;
		select.Pragya_ANCChildControl = this;
	}

	// Present Health of Baby
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.presentHealth==null || this.presentHealth==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histPresentHealth";
		hidden.value = this.presentHealth;
		this.elemPresentHealth = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.presentHealth+"</b>";
	}
	else
	{
		text = ANCHistoryPan.createElement("input",div);
		text.name = "histPresentHealth";
		text.tabIndex = "1";
		text.maxLength = "100";
		text.className = "ancTextboxNormal";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
		this.elemPresentHealth = text;
		text.Pragya_ANCChildControl = this;
	}
	
	// Age at Death
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.deathAge==null || this.deathAge==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histDeathAge";
		hidden.value = this.deathAge;
		this.elemDeathAge = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.deathAge+"</b>";
	}
	else
	{
		var text = ANCHistoryPan.createElement("input",div);
		text.name = "histDeathAge";
		text.size = "10";
		text.tabIndex = "1";
		text.maxLength = "10";
		text.className = "ancTextboxSmallMedium";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);	
		this.elemDeathAge = text;
		text.Pragya_ANCChildControl = this;
	}
	
	// Death Cause
	td = ANCHistoryPan.createElement("td",tr);
	td.width = "10%";
	td.className = "tdfont";
	td.vAlign = "middle";
	td.noWrap = true;
	
	div = ANCHistoryPan.createElement("div",td);	
	div.align = "center";
	
	if(!(this.deathCause==null || this.deathCause==""))
	{
		hidden = ANCHistoryPan.createInputElement("hidden", div);
		hidden.name = "histDeathCause";
		hidden.value = this.deathCause;
		this.elemDeathCause = hidden;
		hidden.Pragya_ANCChildControl = this;

		font = ANCHistoryPan.createElement("font",div);
		font.color = "#000000";
		font.size = "2";
		font.face = "Verdana, Arial, Helvetica, sans-serif";
		font.innerHTML = "<b>"+this.deathCause+"</b>";
	}
	else
	{
		text = ANCHistoryPan.createElement("input",div);
		text.name = "histDeathCause";
		text.tabIndex = "1";
		text.maxLength = "50";
		text.className = "ancTextboxNormal";
		ANCHistoryPan.addEvent(text, "keypress", ValidationFunctions.evtValidateAlphaNumOnly);
		this.elemDeathCause = text;
		text.Pragya_ANCChildControl = this;
	}
};

ANCChildHistory.prototype.deleteChildRow = function()
{
	this.elemTable.deleteRow(this.elemContainer.rowIndex);
};

ANCChildHistory.prototype.isEmpty = function()
{
	var flag = true;
	
	if(this.elemBirthNatureId.value != "-1")
		flag = false;
	if(this.elemGenderCode.value != "-1")
		flag = false;
	else if(this.elemWeight.value != "")
		flag = false;
	else if(this.elemBabyBloodGroupCode.value != "-1")
		flag = false;
	else if(this.elemPresentHealth.value != "")
		flag = false;
	else if(this.elemDeathAge.value != "")
		flag = false;
	else if(this.elemDeathCause.value != "")
		flag = false;
		
	return flag;
};

ANCChildHistory.prototype.validate = function()
{
	if(this.elemBirthNatureId.value == "-1")
	{
		this.showChildTab();
		alert("Please Enter Birth Nature");
		this.elemBirthNatureId.focus();
		return false;
	}
	if(this.elemWeight.value != "")
	{	
		var wt=null;
		try
		{
			wt = parseFloat(this.elemWeight.value);
		}
		catch(e)
		{
			wt==null;
		}
		if(wt==null || wt<0 || wt>9.9)
		{
			alert("Please Enter a proper Baby Weight in Range [0,9.9]");
			this.showChildTab();
			this.elemWeight.focus();
			return false;
		}
	}
	/*if(this.elemGenderCode.value == "-1")
	{
		this.showChildTab();
		alert("Please Enter Gender");
		this.elemGenderCode.focus();
		return false;
	}
	if(this.elemWeight.value == "")
	{	
		this.showChildTab();
		alert("Please Enter Weight");
		this.elemWeight.focus();
		return false;
	}
	if(this.elemBabyBloodGroupCode.value == "-1")
	{	
		this.showChildTab();
		alert("Please Select Baby Blood Group");
		this.elemBabyBloodGroupCode.focus();
		return false;
	}
	else if(this.elemPresentHealth.value != "")
		flag = false;
	else if(this.elemDeathAge.value != "")
		flag = false;
	else if(this.elemDeathCause.value != "")
		flag = false;*/		
	return true;
};

ANCChildHistory.prototype.showChildTab = function()
{
	this.objParentGravidaHist.objPan.showANCTab();
	if(this.objParentGravidaHist.elemSecondRow.Control_div1 && this.objParentGravidaHist.elemSecondRow.Control_div1.style.display == "none")
	{
		ANCHistory.evtOnClickShowHideChildrenList(null,this.objParentGravidaHist.elemImgBirthList);
	}
};

// ******************************************************************************

// ******************************************************************************
// *** ANCHistoryPan's Static Utility Functions

	// Is Browser is Internet Explorer ? 
	ANCHistoryPan.IS_IE =(/msie/i.test(navigator.userAgent));

	// Constants
	ANCHistoryPan.NO_SELECT = "-1";
	
	// Delivery Status Value
	ANCHistoryPan.DELIVERY_STATUS={Abortion:"2",Delivery:"1"};
	
	// Birth Nature Value
	ANCHistoryPan.BIRTH_NATURE={1:"Live Birth", 3:"Neonatal Death",2:"Still Birth"};
	
	// YES NO Value
	ANCHistoryPan.YESNO_VALUE={No:"0",Yes:"1"};

	// Separators
	ANCHistoryPan.ROW_SEP = "!!##!!";
	ANCHistoryPan.ROW_DATA_SEP = "!#!";
	ANCHistoryPan.ROW_CHILD_DATA_SEP = "!#@#!";
	
	// Cofiguration Value
	ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_START_LIMIT = 24;
	ANCHistoryPan.ANC_DELIVERY_GEST_WEEK_MAX_LIMIT = 45;

ANCHistoryPan.findIndex = function(obj)
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
}

// Getting Absolute Position of given Element
ANCHistoryPan.getAbsolutePosition = function(elem)
{
	var SL = 0, ST = 0; // Scroll Left, Scroll Top
	var isDiv = /^div$/i.test(elem.tagName.toLowerCase());
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

// Is given Element is related to the given Event
ANCHistoryPan.isRelated = function (elem, evnt)
{
	var related = evnt.relatedTarget;
	if (!related)
	{
		var type = evnt.type;
		if (type == "mouseover")
		{
			related = evnt.fromElement;
		}
		else if (type == "mouseout")
		{
			related = evnt.toElement;
		}
	}
	while (related)
	{
		if (related == elem)
		{
			return true;
		}
		related = related.parentNode;
	}
	return false;
};

// Removing Class from ClassName of given Element
ANCHistoryPan.removeClass = function(elem, className)
{
	if (!(elem && elem.className))
	{
		return;
	}
	var cls = elem.className.split(" ");
	var ar = new Array();
	for (var i = cls.length; i > 0;)
	{
		if (cls[--i] != className)
		{
			ar[ar.length] = cls[i];
		}
	}
	elem.className = ar.join(" ");
};

// Adding Class to ClassName of given Element
ANCHistoryPan.addClass = function(elem, className)
{
	ANCHistoryPan.removeClass(elem, className);
	elem.className += " " + className;
};

ANCHistoryPan.getElement = function(evnt,elem)  // Who's Event is called
{
	var f = ANCHistoryPan.IS_IE ? window.event.srcElement : evnt.currentTarget;
	return f;
};

ANCHistoryPan.getTargetElement = function(evnt)	// Which is targeted
{
	var f = ANCHistoryPan.IS_IE ? window.event.srcElement : evnt.target;
	return f;
};

// Stopping Event
ANCHistoryPan.stopEvent = function(evnt)
{
	evnt || (evnt = window.event);
	if (ANCHistoryPan.IS_IE)
	{
		evnt.cancelBubble = true;
		evnt.returnValue = false;
	}
	else
	{
		evnt.preventDefault();
		evnt.stopPropagation();
	}
	return false;
};

// Adding Event to Element
ANCHistoryPan.addEvent = function(elem, evntName, funcName)
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

// Adding Event to Element
ANCHistoryPan.addKeyEvent = function(elem, evntName, funcName)
{
	/*
	if( window.KeyEvent )
	{
		var evObj = document.createEvent('KeyEvents');
		evObj.initKeyEvent( evntName, true, true, window, false, false, false, false, 13, 0 );
	}
	else
	{
		var evObj = document.createEvent('UIEvents');
		evObj.initUIEvent( 'keyup', true, true, window, 1 );
		evObj.keyCode = 13;
	}
	elem.dispatchEvent(evObj);

	//var ev = document.createEvent ('KeyEvents');
	//ev.initKeyEvent(evntName,true, true, window, false, false, false, false,0,textareaObj.value.charCodeAt(startSrchPos));
	//textareaObj.dispatchEvent(ev);
	*/
	
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
	
	var evnt;
	if( document.createEventObject())
	{
		alert("Inside createEventObject");
		evnt = document.createEventObject();
	}
	else if(document.createEvent())
	{
		alert("Inside createEvent");
		evnt = document.createEvent();
	}
	
	if( window.KeyEvent )
	{
		alert("Inside createEvent");
		evnt = document.createEvent('KeyEvents');
		evnt.initEvent( evntName, true, true);//, window, false, false, false, false, 13, 0 );
	}
	else
	{
		evnt = document.createEvent('UIEvents');
		evnt.initUIEvent(evntName, true, true, window, 1 );
	}
	if(evnt)
	{
		elem.dispatchEvent(evnt);
	}
};

// Removing Event from Element
ANCHistoryPan.removeEvent = function(elem, evntName, funcName)
{
	if (elem.detachEvent) // IE
	{
		elem.detachEvent("on" + evntName, funcName);
	}
	else if (elem.removeEventListener) // Gecko / W3C
	{
		elem.removeEventListener(evntName, funcName, true);
	}
	else
	{
		elem["on" + evntName] = null;
	}
};

// Creating a HTML Element of given Type inside given Parent which is optional
ANCHistoryPan.createElement = function(type, parent)
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
	return newElem;
};

// Creating a HTML Input Element of given Type inside given Parent which is mandatory
ANCHistoryPan.createInputElement = function(_type, _parent)
{
	_parent.innerHTML = _parent.innerHTML + "<input type='" + _type + "' />";
	return _parent.lastChild;
};

ANCHistoryPan.removeAllChildren = function(elem)
{
	while(elem.childNodes.length>0)
		for(var i=0;i<elem.childNodes.length;i++)
			elem.removeChild(elem.childNodes[i]);
		
};

ANCHistoryPan.prototype.getLastDeliveryDate = function()
{
	var _lastDeliveryDate=document.getElementsByName("histDeliveryDate").length;
	for(var i=0;i<_lastDeliveryDate;i++)
	{
		var lastDeliveryDate=document.getElementsByName("histDeliveryDate")[i].value;
	}
	return lastDeliveryDate;
};

function showData(obj)
{
	var h="";
	for(var d in obj)
	{
		h+=d+" = "+obj[d]+"\n";
		if(h.length>300)
		{
			alert(h);
			h="";
		}
	}
	alert(h);
}
/**** End ANCHistoryPan's Static Utility Functions ****/
/******************************************************************************/
