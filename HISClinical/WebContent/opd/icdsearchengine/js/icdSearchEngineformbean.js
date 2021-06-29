
//**** ICD Search Engine ***********************************************************************************
ICDSearchEngine = function(_defOption)
{
	this.strCurOption = _defOption;
	this.flgSearchStart = false;
	this.flgSearchAdvanced = true;
	this.objEngineFrameSet = null;
	this.strSearchType = null;
};

	// Static
ICDSearchEngine.idDivMainFrameSet = "frmsetICDSearchEngine";
ICDSearchEngine.idDivFrameHeader = "frmICDEngineHeader";
ICDSearchEngine.idDivFrameSearching = "frmICDEngineSearching";
ICDSearchEngine.idDivFrameResultVol1 = "frmICDEngineResultVol1";
ICDSearchEngine.idDivFrameResultVol3 = "frmICDEngineResultVol3";
ICDSearchEngine.idDivFrameFooter = "frmICDEngineFooter";

		// Search Options
ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_BOTH = "0";
ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1 = "1";
ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_3 = "2";
ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_TABULAR_LIST = "3";

		// Search Type
ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_NOT_KNOWN = "0";
ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME = "1";
ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_ICD_CODE = "2";
ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE = "3";

		// Submit Modes
ICDSearchEngine.SUBMIT_MODE_INITIATE_SEARCH = "INITSEARCH";	
ICDSearchEngine.SUBMIT_MODE_NEW = "NEW";	


ICDSearchEngine.setup = function(_defOption,_doc)
{
	try	
	{
		var icdEngine = window.Pragyas_ICDSearchEngine;
		if(!icdEngine)
			window.Pragyas_ICDSearchEngine = _doc.Pragyas_ICDSearchEngine = icdEngine = new ICDSearchEngine(_defOption);
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

ICDSearchEngine.getICDSearchEngine = function(doc)
{
	if(window.Pragyas_ICDSearchEngine)
		return window.Pragyas_ICDSearchEngine;
	if(parent.document && parent.document.Pragyas_ICDSearchEngine)
		return parent.document.Pragyas_ICDSearchEngine;
	if(doc && doc.OBJ_ICDSearchEngine)
		return doc.OBJ_ICDSearchEngine;
};

ICDSearchEngine.submitForm = function(_doc, _mode)
{
	_doc.getElementsByName("strMainMode")[0].value = _mode;
	_doc.forms[0].submit();
};

ICDSearchEngine.setAJAXLoading = function(_elem)
{	
	_elem.innerHTML="";
	var tbl = PragyaDesigner.createElement("table",_elem);
	tbl.width="100%";
	tbl.height="100%";
	tbl.border="0";
	tbl.cellpadding="0";
	tbl.cellspacing="0";
	tbl.id = "tblLoading";
	var tr = PragyaDesigner.createElement("tr",tbl);
	var td = PragyaDesigner.createElement("td",tr);
	var img = PragyaDesigner.createElement("img",td);
	img.id = "imgLoading";
	img.name = "imgLoading";
	img.alt = "Loading";
	img.title = "Loading";
	img.src = "/HISClinical/hisglobal/images/loading.gif";
};

	// Non-static
ICDSearchEngine.prototype.populate = function()
{
	if(this.objEngineFrameSet==null)
		this.objEngineFrameSet = new ICDEngineFrameSet(this,ICDSearchEngine.idDivMainFrameSet);
	this.objEngineFrameSet.populate();
};

ICDSearchEngine.prototype.initiateSearch = function(_searchType)
{
	//alert("Start Search   " +_searchType);
	this.populate();
	this.strSearchType = _searchType;
	this.flgSearchStart=true;
	this.objEngineFrameSet.initiateSearch(_searchType);
};

ICDSearchEngine.prototype.setFrameSizes = function()
{
	this.populate();
	if(!this.flgSearchStart)
		this.objEngineFrameSet.setFramesForStartingSearch();
};

ICDSearchEngine.prototype.setChangeCurrentOption = function(_newOption)
{
	this.flgSearchStart = false;
	this.flgSearchAdvanced = true;
	// Set Frame Sizes
	this.setFrameSizes();
	// Submit Searching 
	this.strCurOption = _newOption;
	this.objEngineFrameSet.objFrmHeader.frmDocument.getElementsByName("strCurOption")[0].value = _newOption;
	
	ICDSearchEngineFormBean.populateBean(this.objEngineFrameSet.objFrmHeader.frmDocument, 
		this.objEngineFrameSet.objFrmSearching.frmDocument, ICDSearchEngineFormBean.INIT_OPTION_DATA);
	ICDSearchEngine.submitForm(this.objEngineFrameSet.objFrmSearching.frmDocument,ICDSearchEngine.SUBMIT_MODE_NEW);

	if(_newOption==ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_BOTH)
	{
		// Submit Result Windows
		ICDSearchEngineFormBean.populateBean(this.objEngineFrameSet.objFrmHeader.frmDocument, 
			this.objEngineFrameSet.objFrmResultVol1.frmDocument, ICDSearchEngineFormBean.INIT_OPTION_DATA);
		ICDSearchEngine.submitForm(this.objEngineFrameSet.objFrmResultVol1.frmDocument,ICDSearchEngine.SUBMIT_MODE_NEW);
		// Submit Result Windows
		ICDSearchEngineFormBean.populateBean(this.objEngineFrameSet.objFrmHeader.frmDocument, 
			this.objEngineFrameSet.objFrmResultVol3.frmDocument, ICDSearchEngineFormBean.INIT_OPTION_DATA);
		ICDSearchEngine.submitForm(this.objEngineFrameSet.objFrmResultVol3.frmDocument,ICDSearchEngine.SUBMIT_MODE_NEW);
	}
	else if(_newOption==ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1)
	{
		// Submit Result Windows
		ICDSearchEngineFormBean.populateBean(this.objEngineFrameSet.objFrmHeader.frmDocument, 
			this.objEngineFrameSet.objFrmResultVol1.frmDocument, ICDSearchEngineFormBean.INIT_OPTION_DATA);
		ICDSearchEngine.submitForm(this.objEngineFrameSet.objFrmResultVol1.frmDocument,ICDSearchEngine.SUBMIT_MODE_NEW);
	}
	else if(_newOption==ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_3)
	{
		// Submit Result Windows
		ICDSearchEngineFormBean.populateBean(this.objEngineFrameSet.objFrmHeader.frmDocument, 
			this.objEngineFrameSet.objFrmResultVol3.frmDocument, ICDSearchEngineFormBean.INIT_OPTION_DATA);
		ICDSearchEngine.submitForm(this.objEngineFrameSet.objFrmResultVol3.frmDocument,ICDSearchEngine.SUBMIT_MODE_NEW);
	}
	else if(_newOption==ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_TABULAR_LIST)
	{
	}
};

//********** End ICD Search Engine ************************************************************************



// **************  Frame Set 
ICDEngineFrameSet = function(_objEngine,_id)
{
	this.objEngine = _objEngine;
	this.id = _id;
	
	// Children Frame Info
	this.frmCntTotal = 5;
	this.frmCntHeader = 0;
	this.frmCntSearching = 1;
	this.frmCntResultVol1 = 2;
	this.frmCntResultVol3 = 3;
	this.frmCntFooter = 4;
	
	this.rows = "";
	this.prevRows = "";
	this.frmRowSpan = null;	// Array
	this.prevFrmRowSpan = null;	// Array
	this.rowSpanHeader = 0;
	this.rowSpanSearching = 1;
	this.rowSpanResultVol1 = 2;
	this.rowSpanResultVol3 = 3;
	this.rowSpanFooter = 4;

	this.arrFrames = new Array(this.frmCntTotal);

	this.objFrmHeader = null;
	this.objFrmSearching = null;
	this.objFrmResultVol1 = null;
	this.objFrmResultVol3 = null;
	this.objFrmFooter = null;

	this.element = null;
	this.frmstDocument = null;
	
	this.fb = null;
};

	// Static
ICDEngineFrameSet.initialFrameSizes = ["52","25","40%","40%","18"];
ICDEngineFrameSet.SearchStartFrameSizes = ["52","100%","0","0","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesVol1 = ["52","25","100%","0","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesVol1Advanced = ["52","25","100%","0","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesVol1AdvancedDisName = ["52","73","100%","0","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesVol1AdvancedIcdCode = ["52","51","100%","0","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesVol1AdvancedExactIcdCode = ["52","51","100%","0","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesVol3 = ["52","25","0","100%","18"];
ICDEngineFrameSet.SearchInitiateFrameSizesAll = ["52","25","50%","50%","18"];


	// Non-static
ICDEngineFrameSet.prototype.populate = function()
{
	// Bean
	if(this.fb==null)
		this.fb = new ICDSearchEngineFormBean();

	// Main Frameset Element
	if(this.element==null)
	{
		if(document.getElementById(this.id))
		{
			this.element = document.getElementById(this.id);
			this.element.OBJ_ICDSearchEngine = this.objEngine;
			
			// Setting Doc
			this.frmstDocument = document;
			this.frmstDocument.OBJ_ICDSearchEngine = this.objEngine;
			this.frmstDocument.OBJ_ICDSearchEngine_FRAME = this;
		}
		else
			return;
		//alert(this.frmstDocument);
	}
	
	if(this.frmRowSpan==null)
	{
		this.prevRows = this.rows = this.element.rows;
		this.prevFrmRowSpan = this.frmRowSpan = this.rows.split(",");
		this.setIndividualRowSpans();
	}	

	// Header Frame Element
	if(this.objFrmHeader==null)
	{
		this.objFrmHeader = new ICDEngineFrame(this,ICDSearchEngine.idDivFrameHeader, this.frmCntHeader);
		this.arrFrames[this.frmCntHeader] = this.objFrmHeader;
	}
	this.objFrmHeader.populate();
	
	// Searching Frame Element
	if(this.objFrmSearching==null)
	{
		this.objFrmSearching = new ICDEngineFrame(this,ICDSearchEngine.idDivFrameSearching,this.frmCntSearching);
		this.arrFrames[this.frmCntSearching] = this.objFrmSearching;
	}
	this.objFrmSearching.populate();
	
	// Result Vol 1 Frame Element
	if(this.objFrmResultVol1==null)
	{
		this.objFrmResultVol1 = new ICDEngineFrame(this,ICDSearchEngine.idDivFrameResultVol1,this.frmCntResultVol1);
		this.arrFrames[this.frmCntResultVol1] = this.objFrmResultVol1;
	}
	this.objFrmResultVol1.populate();
	
	// Result Vol 3 Frame Element
	if(this.objFrmResultVol3==null)
	{
		this.objFrmResultVol3 = new ICDEngineFrame(this,ICDSearchEngine.idDivFrameResultVol3,this.frmCntResultVol3);
		this.arrFrames[this.frmCntResultVol3] = this.objFrmResultVol3;
	}
	this.objFrmResultVol3.populate();

	// Footer Frame Element
	if(this.objFrmFooter==null)
	{
		this.objFrmFooter = new ICDEngineFrame(this,ICDSearchEngine.idDivFrameFooter,this.frmCntFooter);
		this.arrFrames[this.frmCntFooter] = this.objFrmFooter;
	}
	this.objFrmFooter.populate();
};

ICDEngineFrameSet.prototype.setFramesForStartingSearch = function()
{
	this.setFramesRowSpans(ICDEngineFrameSet.SearchStartFrameSizes);
};

ICDEngineFrameSet.prototype.setFramesForSearchInitiateVol1 = function()
{
	if(this.objEngine.flgSearchStart)
		this.setFramesRowSpans(ICDEngineFrameSet.SearchInitiateFrameSizesVol1);
};

ICDEngineFrameSet.prototype.setFramesForSearchInitiateVol3 = function()
{
	if(this.objEngine.flgSearchStart)
		this.setFramesRowSpans(ICDEngineFrameSet.SearchInitiateFrameSizesVol3);
};

ICDEngineFrameSet.prototype.setFramesForSearchInitiateAll = function()
{
	if(this.objEngine.flgSearchStart)
		this.setFramesRowSpans(ICDEngineFrameSet.SearchInitiateFrameSizesAll);
};

ICDEngineFrameSet.prototype.setFramesForSearchInitiateVol1Advanced = function()
{
	if(this.objEngine.flgSearchStart)
		this.setFramesRowSpans(ICDEngineFrameSet.SearchInitiateFrameSizesVol1Advanced);
};

ICDEngineFrameSet.prototype.setFramesRowSpans = function(arrRowSpan)
{
	this.prevFrmRowSpan = this.frmRowSpan;
	this.prevRows = this.rows;

	this.frmRowSpan = arrRowSpan;
	this.implementRowSpan();
	this.setIndividualRowSpans();
};

ICDEngineFrameSet.prototype.implementRowSpan = function()
{
	var rows = this.frmRowSpan[0];
	for(var i=1;i<this.frmCntTotal;i++)
		rows += ","+this.frmRowSpan[i];
	this.rows = rows;
	this.element.rows = this.rows;
};

ICDEngineFrameSet.prototype.setIndividualRowSpans = function()
{
	this.rowSpanHeader = this.frmRowSpan[this.frmCntHeader];
	this.rowSpanSearching = this.frmRowSpan[this.frmCntSearching];
	this.rowSpanResultVol1 = this.frmRowSpan[this.frmCntResultVol1];
	this.rowSpanResultVol3 = this.frmRowSpan[this.frmCntResultVol3];
	this.rowSpanFooter = this.frmRowSpan[this.rowSpanFooter];
};

ICDEngineFrameSet.prototype.setFrameSizeOnAdvSearchType = function(_searchType)
{
	if(this.objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1)
	{
		if(_searchType == ICDSearchEngine.ENGINE_SEARCH_TYPE_NOT_KNOWN)
			ICDEngineFrameSet.SearchInitiateFrameSizesVol1Advanced = 
				ICDEngineFrameSet.SearchInitiateFrameSizesVol1;
		else if(_searchType == ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME)
			ICDEngineFrameSet.SearchInitiateFrameSizesVol1Advanced = 
				ICDEngineFrameSet.SearchInitiateFrameSizesVol1AdvancedDisName;
		else if(_searchType == ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_ICD_CODE)
			ICDEngineFrameSet.SearchInitiateFrameSizesVol1Advanced = 
				ICDEngineFrameSet.SearchInitiateFrameSizesVol1AdvancedIcdCode;
		else if(_searchType == ICDSearchEngine.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE)
			ICDEngineFrameSet.SearchInitiateFrameSizesVol1Advanced = 
				ICDEngineFrameSet.SearchInitiateFrameSizesVol1AdvancedExactIcdCode;

		if(!this.objEngine.flgSearchAdvanced)	this.setFramesForSearchInitiateVol1();
		else	this.setFramesForSearchInitiateVol1Advanced();
	}	
};

ICDEngineFrameSet.prototype.initiateSearch = function(_searchType)
{
	// Volume 1 Search
	if(this.objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_1)
	{
		ICDSearchEngineFormBean.populateBean(this.objFrmSearching.frmDocument, 
			this.objFrmResultVol1.frmDocument, ICDSearchEngineFormBean.SEARCHING_DATA);
		this.objFrmResultVol1.formSubmit(ICDSearchEngine.SUBMIT_MODE_INITIATE_SEARCH);
		this.setFrameSizeOnAdvSearchType(_searchType);
	}
	// Volume 3 Search
	else if(this.objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_3)
	{
		ICDSearchEngineFormBean.populateBean(this.objFrmSearching.frmDocument, 
			this.objFrmResultVol3.frmDocument, ICDSearchEngineFormBean.SEARCHING_DATA);
		this.objFrmResultVol3.formSubmit(ICDSearchEngine.SUBMIT_MODE_INITIATE_SEARCH);
		this.setFramesForSearchInitiateVol3();
	}
	// All Search
	else if(this.objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_BOTH)
	{
		ICDSearchEngineFormBean.populateBean(this.objFrmSearching.frmDocument, 
			this.objFrmResultVol1.frmDocument, ICDSearchEngineFormBean.SEARCHING_DATA);
		this.objFrmResultVol1.formSubmit(ICDSearchEngine.SUBMIT_MODE_INITIATE_SEARCH);

		ICDSearchEngineFormBean.populateBean(this.objFrmSearching.frmDocument, 
			this.objFrmResultVol3.frmDocument, ICDSearchEngineFormBean.SEARCHING_DATA);
		this.objFrmResultVol3.formSubmit(ICDSearchEngine.SUBMIT_MODE_INITIATE_SEARCH);

		this.setFramesForSearchInitiateAll();
	}
};

ICDEngineFrameSet.prototype.getIndexTermElem = function(_name)
{
	// Volume 3 Search
	if(this.objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_3)
	{
		if(this.objFrmFooter.frmDocument.getElementsByName(_name))
		{
			var elem = this.objFrmFooter.frmDocument.getElementsByName(_name)[0];
			this.objFrmFooter.frmDocument.INDEX_LIST = elem;
			return elem;
		}
	}
	return null;
};

ICDEngineFrameSet.prototype.getIndexTermSearchElem = function(_name)
{
	// Volume 3 Search
	if(this.objEngine.strCurOption == ICDSearchEngine.ICD_ENGINE_SEARCH_OPTION_VOLUME_3)
	{
		if(this.objFrmSearching.frmDocument.getElementsByName(_name))
		{
			var elem = this.objFrmSearching.frmDocument.getElementsByName(_name)[0];
			return elem;
		}
	}
	return null;
};
// *************** End Frame Set *************************************************************************


// *************** Engine Frame
ICDEngineFrame = function(_objFrmSet, _id, _counter)
{
	this.objFrameSet = _objFrmSet;
	this.objEngine = this.objFrameSet.objEngine;
	this.id = _id;
	this.frameCounter = _counter;

	this.fb = null;

	this.element = null;
	//this.rowSpan = "";
	this.src = "";
	this.frmDocument = null;
};
	// Static
ICDEngineFrame.getICDEngineFrame = function(doc)
{
	if(doc.OBJ_ICDSearchEngine_FRAME)
		return doc.OBJ_ICDSearchEngine_FRAME;
	else
		return null;
};

	// Non-static
ICDEngineFrame.prototype.populate = function()
{
	// Bean
	if(this.fb==null)
		this.fb = new ICDSearchEngineFormBean();

	//if(this.element==null)
	{
		if(document.getElementById(this.id))
		{
			this.element = this.objFrameSet.frmstDocument.getElementById(this.id);
			this.element.OBJ_ICDSearchEngine = this.objEngine;
		}
		else
			return;
	}
	if(this.src=="")
		this.src = this.element.src;

	// Setting Doc
	if(window.XMLHttpRequest) // Mozilla
		this.frmDocument = this.element.contentDocument;
 	else if (window.ActiveXObject)
		this.frmDocument = this.element.Document;
	this.frmDocument.OBJ_ICDSearchEngine = this.objEngine;
	this.frmDocument.OBJ_ICDSearchEngine_FRAME = this;
};

ICDEngineFrame.prototype.formSubmit = function(_mode)
{
	ICDSearchEngine.setAJAXLoading(this.frmDocument.getElementById("divMainResult"));
	ICDSearchEngine.submitForm(this.frmDocument,_mode);
};

ICDEngineFrame.prototype.setBeanValue = function(_field, _val)
{
	this.fb.setBeanValue(_field,_val,this.frmDocument);
};
// ***************** End Engine Frame ******************************************************************










// **** ICD Search Engine Form Bean ************************

	// Constructor
ICDSearchEngineFormBean = function()
{
	this.strSearchStart = false;
	
	// Searching
	this.strSearchString = "";
	this.strIcdDiseaseName = "";	
	this.strIcdCodeName = "";	
	this.strIcdCodeMain = "";
	this.strIcdCodeSub = "";
	
	this.strRecordType = "";
	this.strDiseaseTypeCode = "";
	this.strChronicDiseaseCode = "";
	this.strOrderBy = "";	
	
};

// Static Members
ICDSearchEngineFormBean.SERCH_TYPE = "strSearchType";
ICDSearchEngineFormBean.INIT_OPTION_DATA = {	strCurOption:"", strSearchStart:"", strSearchType:""
												};

ICDSearchEngineFormBean.SEARCHING_DATA = {	strSearchStart:"", strSearchType:"", strIcdDiseaseName:"",	strIcdCodeName:"",
											strExactIcdCode:"",	strIcdCodeMain:"", strIcdCodeSub:"", 
											strRecordType:"", strMainDiseaseTypeCode:"", strDiseaseTypeCode:"", 
											strChronicDiseaseCode:"", strOrderBy:"", strIndexSearchString:"",
											strAutoCompleteString:"", strIndexCode:"", strDiseaseName:""};

ICDSearchEngineFormBean.populateBean = function(_fromDoc, _toDoc, _arrFields)
{
	for(var f in _arrFields)
	{
		//if(f=="strCurOption")	alert("strCurOption--"+_fromDoc.getElementsByName(f)[0].value)
		if(_fromDoc.getElementsByName(f) && _fromDoc.getElementsByName(f)[0])
		{
			var elem = null;
			if(_toDoc.getElementsByName(f) && _toDoc.getElementsByName(f)[0])
				elem = _toDoc.getElementsByName(f)[0];
			else
			{
				var div = _toDoc.getElementById("divExtraHidden");
				elem = PragyaDesigner.createInputElement("hidden",div);
				elem.name = f;
			}
			if(elem!=null)
				elem.value = _fromDoc.getElementsByName(f)[0].value;
		}
	}
};

// Non-static Members
	// Non-static
ICDSearchEngineFormBean.prototype.populate = function(document)
{
	// Bean
};

ICDSearchEngineFormBean.prototype.setBeanValue = function(_field, _val, _doc)
{
	this[_field] = _val;
	_doc.getElementsByName(_field)[0].value = _val;
};

// **** End ICD Search Engine Form Bean ************************

