
// Gettting List of Sub Disease 
function getSubDiseaseList(_mode, _recordType, _icdCode)
{
	var objSubDiseaseList = null;
	var objXHR = {url: "/HISClinical/opd/icdsearchengine/ajax?mode="+_mode+"&strRecordType="+_recordType+"&strIcdCodeMain="+_icdCode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objSubDiseaseList = data;
			var divSub = document.getElementById("divSub"+PragyaDesigner.trim(data[0].strParentCode));
			var imgSub = document.getElementById("imgSub"+PragyaDesigner.trim(data[0].strParentCode));
			var code = "";
			for(var i=0;i<objSubDiseaseList.length;i++)
			{
				var rowData = createSubDisesaeRow(i+1,objSubDiseaseList[i]);
				code = code + rowData;
				
			}
			divSub.innerHTML = code;
			showHideSubDiseases(true,divSub, imgSub);
		},
        error: function(error)
        {
            alert(error+"Error while loading Sub Disease Information");
            objSubDiseaseList = null;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	if(objSubDiseaseList==null)
	{
		setErrorMsgRow(document.getElementById("divSub"+PragyaDesigner.trim(_icdCode)),"No Sub Disease Found");
	}
}

// Creating Row for Sub Diseases
//	#strRecordType-Record Type
//	#strIcdCode-Disease Code
//	#strSearialNo-Serial No
//	#strDiseaseName-Disease Name
//	#Seq-Seq No
//	strParentCode
function createSubDisesaeRow(_num, _objSubDis)
{
	var divSubSample = document.getElementById("divSubDisSample");
	var code = divSubSample.innerHTML;
	
	var rePattern = new RegExp("#Seq", "i");
	code = code.replace(rePattern, _num);
		for(var f in _objSubDis)
		{
			rePattern = new RegExp("#"+f, "i");
			while(rePattern.test(code))
				code = code.replace(rePattern, _objSubDis[f]);
		}
	return code;
}

function setAJAXLoading(elem)
{
	elem.innerHTML="<table id='tblLoading' width='100%' height='100%' cellpadding='0' cellspacing='0' border='0'>" + 
		"<tr><td><img id='imgLoading' name='imgLoading' alt='Loading' title='Loading' src='/HISClinical/hisglobal/images/loading.gif'>" + 
		"</td></tr></table>";
}

// Getting Main Index Term List
function getMainIndexTermList(_mode)
{
	var objIndexList = null;
	var objXHR = {url: "/HISClinical/opd/icdsearchengine/ajax?mode="+_mode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objIndexList = data;
			
			var cboList = document.getElementsByName("cboMainIndexTermList")[0];
			for(var i=0;i<objIndexList.length;i++)
			{
				var opt = document.createElement("option");
				opt.value = objIndexList[i].indexCode;
				opt.innerHTML= objIndexList[i].diagnosticTerm;
				cboList.appendChild(opt);
			}
		},
        error: function(error)
        {
            objIndexList = null;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	if(setSerachDropDownLists() && checkIsSerachVolume3())
		NewDropDownSearch.setup(elemIndexTermSearchTxt,elemIndexTermList,NewDropDownSearch.SEARCH_TYPE["ANY_WHERE"],false,true);
	
}


// Gettting List of Sub Tree Modifiers 
function getIndexTermTree(_mode, _indexCode)
{
	var objIndexTree = null;
	var objXHR = {url: "/HISClinical/opd/icdsearchengine/ajax?mode="+_mode+"&strIndexCode="+_indexCode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objIndexTree = data;
			var divTree = document.getElementById("divTree"+PragyaDesigner.trim(data[0].indexCode));
			var divSeeAlso = document.getElementById("divSeeAlso"+PragyaDesigner.trim(data[0].indexCode));
			var imgTree = document.getElementById("imgTree"+PragyaDesigner.trim(data[0].indexCode));
			//var code = "";
			divTree.innerHTML = "";
			for(var i=0;i<objIndexTree.length;i++)
			{
				//var rowData = 
				createTreeRow(divTree, objIndexTree[i]);
				//code = code + rowData;
				
			}
			//divTree.innerHTML = code;
			showHideTree(true, divTree, divSeeAlso, imgTree);
		},
        error: function(error)
        {
            alert(error+"Error while loading Index Tree Information");
            objIndexTree = null;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	if(objIndexTree==null)
	{
		setErrorMsgRow(document.getElementById("divTree"+PragyaDesigner.trim(_indexCode)),"No Tree Found");
	}
}
// Creating Row for Sub Tree
//	#indexModifierID - Index Modifier ID
//	#indexCode - Index Code
//	#parentIndexModifierCode - Parent Mod Id
//	#modifierLevel-Modifier Level
//	#modifier-Modifer Name
//	#isWith-Is With
//	#diseaseCode-Disease Code
//	#dualDiseaseCode-Dual Disease Code
//	#seeTerm-See 
//	#seeTermCode-See Code
//	#seeIndexModifierId-See modi Code
//	#seeAlsoTerm-See Also
//	#seeAlsoTermCode-See Also Code
//	#seeAlsoIndexModifierId-See Also Modi Code
//	#remark-Remark
//	#haveTree-have further Tree
function createTreeRow(_divTree, _objTree)
{
	var tbl = PragyaDesigner.createElement("table",_divTree);
	tbl.width = "100%";
	tbl.border = "0";
	tbl.cellpadding = "0";
	tbl.cellspacing = "0";
	var tr = PragyaDesigner.createElement("tr",tbl);
	tr.className = "ResRowVol1_0";

	var td = PragyaDesigner.createElement("td",tr);
	td.width = "5%";
	td.className = "label";
	td.innerHTML = "&nbsp;";

	td = PragyaDesigner.createElement("td",tr);
	td.width = "80%";
	td.className = "hyperlink";
	var div = PragyaDesigner.createElement("div",td);
	div.align = "left";
	
	var code = "";
	
	for(var i=0; i<parseInt(_objTree.modifierLevel); i++)
		code += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
	if(_objTree.haveTree!=0)
	{
		code += "<img id='imgTree"+_objTree.indexModifierID+"' name='imgTree' alt='+' title='Show Tree' src='/HIS/hisglobal/images/Pl_Green_Sqr.png'"
			+ " tabindex='1' onclick=\"toggleViewFurtherTree('SUBSUBTREE','" + _objTree.indexCode + "','" + _objTree.indexModifierID + "');\"" 
			+ " onkeypress=\"if(event.keyCode==13) toggleViewFurtherTree('SUBSUBTREE','" + _objTree.indexCode + "','" + _objTree.indexModifierID + "');\" >"
			+ "&nbsp;&nbsp;";
	}
	else
	{
		code += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	code += _objTree.modifier;
	
	if(_objTree.seeTerm!="")
	{
		code += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<div id='idSeeTerm'>see</div>&nbsp;"
			+ "<a id='ancIndexTerm' name='ancIndexTerm' tabindex='1' class='anchorNormal' onmouseover='mouseOverAnchor(this)' "
			+ "onmouseout='mouseOutAnchor(this)' onclick=\"viewIndexTree(event,'"+ _objTree.seeTermCode + "','" 
			+ _objTree.seeIndexModifierId +"')\">" + _objTree.seeTerm + "</a>";
	}
	
	if(_objTree.seeAlsoTerm!="")
	{
		code += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			+ "<div id='idSeeTerm'>see</div>&nbsp;"
			+ "<a id='ancIndexTerm' name='ancIndexTerm' tabindex='1' class='anchorNormal' onmouseover='mouseOverAnchor(this)' "
			+ "onmouseout='mouseOutAnchor(this)' onclick=\"viewIndexTree(event,'"+ _objTree.seeAlsoTermCode + "','" 
			+ _objTree.seeAlsoIndexModifierId +"')\">" + _objTree.seeAlsoTerm + "</a>";
	}
	div.innerHTML = code;
	
	td = PragyaDesigner.createElement("td",tr);
	td.width="15%";
	td.className="hyperlink";
	div = PragyaDesigner.createElement("div",td);
	div.align = "left";
	code = "";
	if(_objTree.diseaseCode!="")
	{
		code += "<a id='ancDtlDisease' name='ancDtlDisease' tabindex='1' class='anchorNormal' onmouseover='mouseOverAnchor(this)' "
			+ "onmouseout='mouseOutAnchor(this)' onclick=\"viewICDDiseaseDetail(event,'" + _objTree.diseaseCode + "','0','1')\">"
			+ _objTree.diseaseCode;
		if(_objTree.dualDiseaseCode!="")	code += "&dagger";
		code += "</a>";
	}
	if(_objTree.dualDiseaseCode!="")
	{
		code += "<a id='ancDtlDisease' name='ancDtlDisease' tabindex='1' class='anchorNormal' onmouseover='mouseOverAnchor(this)' "
			+ "onmouseout='mouseOutAnchor(this)' onclick=\"viewICDDiseaseDetail(event,'" + _objTree.dualDiseaseCode + "','0','1')\">"
			+ _objTree.dualDiseaseCode + "*</a>";
	}
	div.innerHTML = code;

	if(_objTree.haveTree!=0)
	{
		var div = PragyaDesigner.createElement("div",_divTree);
		div.id = "divTree" + _objTree.indexModifierID;
	}
	return code;
}

function setErrorMsgRow(_elem,_msg)
{
	_elem.innerHTML = "<table width='100%' cellpadding='0' cellspacing='1'><tr><td class='errorMsg'>"
		+ _msg + "</td></tr></table>";
}


// Gettting List of Sub Sub Tree Modifiers 
function getModofierTermTree(_mode, _indexCode, _modifierCode)
{
	var objIndexTree = null;
	var objXHR = {url: "/HISClinical/opd/icdsearchengine/ajax?mode="+_mode+"&strIndexCode="+_indexCode+"&strIndexModifierID="+_modifierCode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objIndexTree = data;
			var divTree = document.getElementById("divTree"+PragyaDesigner.trim(data[0].parentIndexModifierCode));
			var imgTree = document.getElementById("imgTree"+PragyaDesigner.trim(data[0].parentIndexModifierCode));
			//var code = "";
			divTree.innerHTML = "";
			for(var i=0;i<objIndexTree.length;i++)
			{
				//var rowData = 
				createTreeRow(divTree, objIndexTree[i]);
				//code = code + rowData;
			}
			//divTree.innerHTML = code;
			showHideSubTree(true, divTree, imgTree);
		},
        error: function(error)
        {
            alert(error+"Error while loading Index Tree Information");
            objIndexTree = null;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	if(objIndexTree==null)
	{
		setErrorMsgRow(document.getElementById("divTree"+PragyaDesigner.trim(_modifierCode)),"No Tree Found");
	}
}


// Gettting List of Sub Groups 
function getAllIcdSubGroupList(_mode, _icdGroupCode)
{
	var objSubGroupList = null;
	var objXHR = {url: "/HISClinical/opd/icdsearchengine/ajax?mode="+_mode+"&strIcdGroupCode="+_icdGroupCode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objSubGroupList = data;
			var cbo = document.getElementsByName("strIcdSubGroupCode")[0];
			cbo.innerHTML="";
			var opt = document.createElement("option");
			opt.value = "0";
			opt.innerHTML = "All";
			cbo.appendChild(opt);
			
			for(var i=0;i<objSubGroupList.length;i++)
			{
				opt = document.createElement("option");
				opt.value = objSubGroupList[i].value;
				opt.innerHTML = objSubGroupList[i].label;
				cbo.appendChild(opt);
			}
		},
        error: function(error)
        {
            alert(error+"Error while populating Sub Group Information");
            objSubGroupList = null;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
}

// Gettting List of Disease 
function getAllIcdDiseaseList(_mode, _icdSubGroupCode)
{
	var objDiseaseList = null;
	var objXHR = {url: "/HISClinical/opd/icdsearchengine/ajax?mode="+_mode+"&strIcdSubGroupCode="+_icdSubGroupCode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objDiseaseList = data;
			var cbo = document.getElementsByName("strIcdDiseaseCode")[0];
			cbo.innerHTML="";
			var opt = document.createElement("option");
			opt.value = "0";
			opt.innerHTML = "All";
			cbo.appendChild(opt);
			
			for(var i=0;i<objDiseaseList.length;i++)
			{
				opt = document.createElement("option");
				opt.value = objDiseaseList[i].value;
				opt.innerHTML = objDiseaseList[i].label;
				cbo.appendChild(opt);
			}
		},
        error: function(error)
        {
            alert(error+"Error while populating Disease Information");
            objDiseaseList = null;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
}
