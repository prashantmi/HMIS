<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="templateTag"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.TestParameterVO"%>

<html>


<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/inv_popup.js" />
<his:javascript src="/new_investigation/js/wysiwyg.js" />
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<script type="text/javascript">
	var djConfig = {
		baseScriptUri : "/HISInvestigationG5/new_investigation/js/dojotoolkit/dojo/",
		parseOnLoad : true
	};
</script>

<link rel="stylesheet" type="text/css"
	href="/HISInvestigationG5/new_investigation/js/dojotoolkit/dijit/themes/tundra/tundra.css" />
<script type="text/javascript"
	src="/HISInvestigationG5/new_investigation/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true">
	
</script>


<style type="text/css">
<!--
.sub5 {
	Background-color: #E0EEEE;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	-moz-box-shadow: 2px 2px 1px #999;
	/*-moz-box-shadow: 2px 2px 1px #999; -webkit-box-shadow: 3px 3px 1px #999;*/
	Border: solid 1px #CCC;
	Font-family: "Eurostile", "Arial";
	Font-weight: bold;
	Font-size: 12px;
	Color: black;
	/*text-shadow: 0 1px 2px #000000; text-shadow: 1px 1px 2px #000000;*/
}

.sub5:hover {
	Background-color: #C1CDCD;
}

Fieldset {
	Border: 2px solid black;
	Background-color: transparent;
	-moz-border-radius: 8px;
	Border-radius: px;
	Margin: 0 0 1em 0;
	Position: relative;
}

.sub6 {
	width: 75px;
	Background-color: #E0EEEE;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	-moz-box-shadow: 2px 2px 1px #999;
	/*-moz-box-shadow: 2px 2px 1px #999; -webkit-box-shadow: 3px 3px 1px #999;*/
	Border: solid 1px #CCC;
	Font-family: "Eurostile", "Arial";
	Font-weight: bold;
	Font-size: 12px;
	Color: black;
	/*text-shadow: 0 1px 2px #000000; text-shadow: 1px 1px 2px #000000;*/
}

.sub6:hover {
	Background-color: #C1CDCD;
}

td.highlighted {
	background-color: pink;
}

td.previouslyHighlighted {
	background-color: lightgreen;
}

ul { /* remove bullets and list indents */
	list-style: none;
	position: relative;
	margin: 0;
	padding: 0;
	background-color: lightgreen;
}

li { /* remove bullets and list indents */
	list-style: none;
	position: relative;
	margin: 1px;
	padding: 0;
	background-color: #FFEBD5;
}
-->
</style>


<script type="text/javascript">

dojo.require("dijit.layout.AccordionContainer");
dojo.require("dojo.dnd.Moveable");
dojo.require("dojo.parser");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.form.Button");
dojo.require("dijit.Menu");
dojo.require("dijit.MenuItem");
dojo.require("dijit.Tree");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.Dialog");

var selectedElement=null;
var selectedColumnId=null;
var selectedColumn=null;
var selectedColumnClass=null;

var selectedTable=null;
var selectedColumnNo=null;
var selectedRowNo=null;

var previouslySelectedTable=null;
var previouslySelectedColumnNo=null;
var previouslySelectedRowNo=null;

var previouslySelectedColumn=null;
var previouslySelectedColumnId=null;
var previouslySelectedColumnClass=null;
function selectTheSelected()
{
	if(previouslySelectedColumn!=null)
		{
			previouslySelectedColumn=document.getElementById(previouslySelectedColumnId);
			document.getElementById(previouslySelectedColumnId).className='previouslyHighlighted';
		}
	
	if(selectedColumn!=null)
	{
		selectedColumn=document.getElementById(selectedColumnId);
		document.getElementById(selectedColumnId).className='highlighted';
	}
		
	}

dojo.addOnLoad(function(  ) {
	 /* dojo.connect(dojo.byId("accordButton"), "onclick", function(evt) {
		 dijit.byId("leftAccordion").selectChild(dijit.byId("parameterId"));
        //dojo.byId("parameterId").selected="true";
    });
   dojo.connect(dijit.byId("accordButton"), "onClick", function(evt) {
    	alert("onClick");	
        dijit.byId("leftAccordion").selectChild(dijit.byId("parameterId"));
    });*/
   
	
    /*dojo.connect(dijit.byId("goId"), "onClick", function(evt) {
       fetchTemplate();
       fetchTemplateList();
    });*/
    dojo.connect(dojo.byId("goId"), "onclick", function(evt) {
        fetchTemplate();
        fetchTemplateList();
     });
    dojo.connect(dojo.byId("goAdd"), "onclick", function(evt) {
    	getTestnTestGroup();        
     });
    dojo.connect(dojo.byId("goNew"), "onclick", function(evt) {
    	getNewTemplateDefinition();      
     });
	
    adjustMainSplit();
});
var windowPageWidth=window.innerWidth;
var windowPageHeight= window.innerHeight;
function adjustMainSplit()
{
	 var tc = dijit.byId('mainSplit');
	 var arg = {"w" : (windowPageWidth-10), "h" :(windowPageHeight-180)}; 
	 tc.resize(arg);


	 
	 tc.layout();
}

function getNewTemplateDefinition()
{
	
	
	var urlStr="/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=NEWTEMPLATE";
	
	var xhrArgs = {url:urlStr, postData: "",handleAs: "text",load: function(data) {
					document.getElementById("resultEntryParameterListDiv").innerHTML="";
					document.getElementById("requisitionFormParameterListDiv").innerHTML="";
					document.getElementById("divNewTestList").innerHTML="";
					document.getElementById("columnPropertiesDiv").innerHTML="";
					document.getElementById("elementListDiv").innerHTML="";
					document.getElementById("elementPropertiesDiv").innerHTML="";
					document.getElementById('addOrModifyMode').value='ADDSAVEMODE';
					document.getElementById('templateSeqIdForTest').value="";
					document.getElementById('divNewTestList').innerHTML="";
					document.getElementById('DesignerDiv').innerHTML="";
					document.getElementById("headerHeight").value="";
					document.getElementById("headerWidth").value="";
					document.getElementById("footerHeight").value="";
					document.getElementById("footerWidth").value="";
					
					dijit.byId("leftAccordion").selectChild(dijit.byId("addTestOrGroupId"));
        },
        error: function(error) {
            alert(error+"Error while loading User Infomation please login again or may be user noth authorized to raise tests");
        }};
	
        var deferred1 = dojo.xhrGet(xhrArgs);
}

function getParameterDetails(testGroupCode)
{
	
	var obj=testGroupCode;
	var urlStr="/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=SHOWMODIFICATIONPARAMETER&labTestCode="+obj;
	
	var xhrArgs = {url:urlStr, postData: "",handleAs: "text",load: function(data) {
		//alert(data);
		var str = data.split("#$#");			
				if(str[0]!=null)
					document.getElementById("resultEntryParameterListDiv").innerHTML=str[0];
					
				if(str[1]!=null)
					document.getElementById("requisitionFormParameterListDiv").innerHTML=str[1];
					
				if(str[2]!=null)		
					document.getElementById("sampleFormListDiv").innerHTML=str[2];
					
			//document.getElementById("DesignerDiv").innerHTML=str[1];
			document.getElementById('addOrModifyMode').value='MODIFYSAVEMODE';
			if(document.getElementsByName("exsistingTemplate")[0] != null)
				document.getElementById('templateSeqIdForTest').value=document.getElementsByName("exsistingTemplate")[0].value;
			else
				document.getElementById('templateSeqIdForTest').value = null;

			dijit.byId("leftAccordion").selectChild(dijit.byId("requlstParameterId"));
        },
        error: function(error) {
            alert(error+"Error while loading User Infomation please login again or may be user not authorized to raise tests");
        }};
	
        var deferred1 = dojo.xhrGet(xhrArgs);
	
	
	
}
/*Exsisting Template Function start*/
function fetchTemplate()
{
	
	var strul="<ul id='tree_menu'>";
	var strulend="</ul>";
	
	var obj=document.getElementsByName("exsistingTemplate")[0];
	var urlStr="/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=SHOWMODIFICATIONPROCESS&templateIdRadio="+obj.value;
	var objValue = null;
	if(obj != null)
		objValue = obj.value;
	var xhrArgs = {url:urlStr, postData: "",handleAs: "text",load: function(data) {
		
		var str = data.split("#$#");		
			document.getElementById("divNewTestList").innerHTML=strul+str[0]+strulend;
			document.getElementById("DesignerDiv").innerHTML=str[1];
			document.getElementById("headerHeight").value=str[2];
			document.getElementById("headerWidth").value=str[3];
			document.getElementById("footerHeight").value=str[4];
			document.getElementById("footerWidth").value=str[5];
			document.getElementById("pageHeight").value=str[6];
			document.getElementById("pageWidth").value=str[7];
			document.getElementById('addOrModifyMode').value='MODIFYSAVEMODE';
			document.getElementById('templateSeqIdForTest').value=objValue;
        },
        error: function(error) {
            alert(error+"Error while loading User Infomation please login again or may be user noth authorized to raise tests");
        }};
        var deferred1 = dojo.xhrGet(xhrArgs);
	
	
}
function fetchTemplateList()
{
	//alert("fetchTemplateList");
}
 /*var templateRadioId;
function submitPage(obj)
{

	 templateRadioId=obj.value;
	var urlAjax="/AHIMS/investigation/transaction/CreateNewPrintingTemplateSer?hmode=SHOWMODIFICATIONPROCESS&templateIdRadio="+obj.value;
	init(urlAjax);
	req.onreadystatechange = getResponseForTemplateModification;
	req.send(null);
	
}*/
 
/*Exsisting Template Function end*/
var tableHtml="<input type='text' name='tableNo' value='2'>";
var alignHtml="<select name='tableAlign' ><option value='left'>left</option><option value='center'>center</option><option value='right'>right</option> </select>";
var alignH="<select name='align' ><option value='left'>left</option><option value='center'>center</option><option value='right'>right</option> </select>";
var voSelectHTML="<select name='voValue'>"+'<%=(String)session.getAttribute("parameterOptionText")%>'+"</select>";
	function addVOObjectValues() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "VO Parameters";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = voSelectHTML;//select element of vo object

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='addVoValuesTODocument()'/>";

	}
	function createDivForcreatingTable() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell spacing";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellspacing' value='0'size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell padding";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellpadding' value='0'size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Height";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableHeight' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableWidth' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Align";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = alignHtml;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "border";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='border' value='0' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "border color";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='borderColor' value='black' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5'  value='SAVE' onClick='createTable()'/>";

	}
	function writeLabelToTable() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		//alert(divElement.innerHTML);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Label value";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<textarea name='label' rows='1' cols='9' value=''></textarea>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "align";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = alignH;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "font color";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='fontColor' value='black' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "font size";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='fontSize' value='2' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "bold";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<select name='bold'><option value='1'>Yes</option> <option value='0'>No</option></select>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "under line";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<select name='underLine'><option value='1'>Yes</option> <option value='0'>No</option></select>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='writeLabel()'/>";

	}
	function deleteDivForTable() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='deleteTable()'/>";

	}
	function createDivForModifyingTable() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell spacing";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellspacing' value='0' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell padding";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellpadding' value='0' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Height";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableHeight' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableWidth' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = alignHtml;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "border";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='border' value='0' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "border color";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='borderColor' value='black' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5'  value='SAVE' onClick='modifyTable()'/>";

	}
	function addHRToTable() {

		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "row";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='rows' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Col";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cols' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "size";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='size' value='1' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "color";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='color' value='black' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='addHR()'/>";
	}
	function deleteLabelFromTable() {

		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button'  value='SAVE' onClick='deleteLabel()'/>";

	}
	function addHeaderToTable() {

		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell spacing";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellspacing' value='0'size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell padding";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellpadding' value='0' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Height";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableHeight' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableWidth' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = alignHtml;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "class";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='styleClass' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='addHeader()'/>";
	}
	function addFooterToTable() {

		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell spacing";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellspacing' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cell padding";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cellpadding' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Height";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableHeight' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableWidth' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Align";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = alignHtml;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "class";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='styleClass' value=''size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='addFooter()'/>";
	}
	function deleteElement() {

		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "table id";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableId' value='"
				+ ((selectedTable == null) ? "" : selectedTable)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "rows";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rows' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "cols";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cols' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='deleteElementFromDocument()'/>";

	}

	function acceptChanges() {

		var templateName = null;
		var templateId = null;
		if(document.getElementsByName("exsistingTemplate")[0] != null) {
				if (document.getElementsByName("exsistingTemplate")[0].selectedIndex != 0) {
					templateName = document.getElementsByName("exsistingTemplate")[0].options[document
							.getElementsByName("exsistingTemplate")[0].selectedIndex].text;
					templateId = document.getElementsByName("exsistingTemplate")[0].options[document
							.getElementsByName("exsistingTemplate")[0].selectedIndex].value;
				}
		}

		document.getElementsByName("templateSeqId")[0].value = templateId;
		//alert(templateId);
		//alert(templateName);
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = null;
		var colElement = null;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Template Name";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='templateName1' value='"
				+ ((templateName == null) ? "" : templateName) + "' />";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Header Height";
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='templateHeaderHeight' value='"
				+ document.getElementsByName("headerHeight")[0].value
				+ "' size='8'/>";

		//rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		//colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Header Width";
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='templateHeaderWidth' value='"
				+ document.getElementsByName("headerWidth")[0].value
				+ "' size='8'/>";

		//rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		//colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Footer Height";
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='templateFooterHeight' value='"
				+ document.getElementsByName("footerHeight")[0].value
				+ "' size='8'/>";

		//rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		//colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Footer Width";
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='templateFooterWidth' value='"
				+ document.getElementsByName("footerWidth")[0].value
				+ "' size='8'/>";

		//rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		//colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Page Height";
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='templatePageHeight' value='"
				+ document.getElementsByName("pageHeight")[0].value
				+ "' size='8'/>";

		//rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		//colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Page Width";
		colElement = document.createElement("td");
		//rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='templatePageWidth' value='"
				+ document.getElementsByName("pageWidth")[0].value
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='saveForAcceptChanges()'/>";
		miscellaneousDialog();
	}

	function saveForAcceptChanges() {
		//alert("saving called");
		var urlStr = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=SAVEMODIFY";
// 		document.getElementById("headerHeight").value = document
// 				.getElementsByName("templateHeaderHeight")[0].value;
// 		document.getElementById("headerWidth").value = document
// 				.getElementsByName("templateHeaderWidth")[0].value;
// 		document.getElementById("footerHeight").value = document
// 				.getElementsByName("templateFooterHeight")[0].value;
// 		document.getElementById("footerWidth").value = document
// 				.getElementsByName("templateFooterWidth")[0].value;
// 		document.getElementById("pageHeight").value = document
// 				.getElementsByName("templatePageHeight")[0].value;
// 		document.getElementById("pageWidth").value = document
// 				.getElementsByName("templatePageWidth")[0].value;
		document.getElementById("templateName").value = document
				.getElementsByName("templateName1")[0].value;
	//	alert(document.getElementById("headerHeight").value);
		var parameterStr = "&templateName="
				+ document.getElementsByName("templateName1")[0].value
				+ "&headerHeight=2"
			//	+ document.getElementsByName("templateHeaderHeight")[0].value
				+ "&headerWidth=2"
			//	+ document.getElementsByName("templateHeaderWidth")[0].value
				+ "&footerHeight=2"
			//	+ document.getElementsByName("templateFooterHeight")[0].value
				+ "&footerWidth=2"
			//	+ document.getElementsByName("templateFooterWidth")[0].value
				+ "&templateSeqId="
				+ document.getElementsByName("templateSeqId")[0].value
				+ "&pageWidth=2"
			//	+ document.getElementsByName("templatePageWidth")[0].value
				+ "&pageHeight=2"
			//	+ document.getElementsByName("templatePageHeight")[0].value;
		var xhrArgs = {
			url : urlStr + parameterStr,
			sync : true,

			handleAs : "text",
			load : function(data) {

				closeDialog();
			},
			error : function(error) {
				alert(error);
			}
		};

		var deferred1 = dojo.xhrPost(xhrArgs);

	}

	function deleteElementFromDocument() {
		if (document.getElementsByName("tableId").length > 1)
			document.getElementById('modificationInfoDiv').innerHTML = "";

		var urlAjax = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=DELETEElEMENT&tableId="
				+ document.getElementsByName("tableId")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value;
		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function addVoValuesTODocument() {
		document.getElementById('modificationInfoDiv').innerHTML = "";
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=ADDVOVALUES&tableId="
				+ document.getElementsByName("tableId")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value
				+ '&voValue='
				+ document.getElementsByName("voValue")[0].value;

		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function createTable() {
		//alert(document.getElementById('diagContent').innerHTML);
		var formElement = document.getElementById("temporaryForm");
		if (document.getElementById("temporaryForm") == null) {
			formElement = document.createElement("form");
			formElement.id = "temporaryForm";
			formElement.style.display = "none";
			document.getElementsByTagName("body")[0].appendChild(formElement);
		} else {

			formElement.innerHTML = "";
		}

		formElement.innerHTML = "<input type='text' name='tableid' value='"
				+ document.getElementsByName("tableId")[0].value
				+ "'/><input type='text' name='rows' value='"
				+ document.getElementsByName("rows")[0].value
				+ "'/><input type='text' name='cols' value='"
				+ document.getElementsByName("cols")[0].value
				+ "'/><input type='text' name='border' value='"
				+ document.getElementsByName("border")[0].value
				+ "'/><input type='text' name='cellspacing' value='"
				+ document.getElementsByName("cellspacing")[0].value
				+ "'/><input type='text' name='cellpadding' value='"
				+ document.getElementsByName("cellpadding")[0].value
				+ "'/><input type='text' name='borderColor' value='"
				+ document.getElementsByName("borderColor")[0].value
				+ "'/><input type='text' name='tableAlign' value='"
				+ document.getElementsByName("tableAlign")[0].value
				+ "'/><input type='text' name='tableHeight' value='"
				+ document.getElementsByName("tableHeight")[0].value
				+ "'/><input type='text' name='tableWidth' value='"
				+ document.getElementsByName("tableWidth")[0].value + "'/>";
		document.getElementById('modificationInfoDiv').innerHTML = "";
		var urlStr = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=CREATETABLE";
		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindra"></div>';
		var xhrArgs = {
			url : urlStr,
			sync : true,
			form : "temporaryForm",
			handleAs : "text",
			load : function(data) {
				document.getElementById("DesignerDiv").style.display = "block";
				var str = data;
				document.getElementById("DesignerDiv").innerHTML = str;
				closeDialog();

			},
			error : function(error) {
				alert(error);
			}
		};

		var deferred1 = dojo.xhrPost(xhrArgs);
	}
	function modifyTable() {
		var formElement = document.getElementById("temporaryForm");
		if (document.getElementById("temporaryForm") == null) {
			formElement = document.createElement("form");
			formElement.id = "temporaryForm";
			formElement.style.display = "none";
			document.getElementsByTagName("body")[0].appendChild(formElement);
		} else {

			formElement.innerHTML = "";
		}

		formElement.innerHTML = "<input type='text' name='tableid' value='"
				+ document.getElementsByName("tableId")[0].value
				+ "'/><input type='text' name='border' value='"
				+ document.getElementsByName("border")[0].value
				+ "'/><input type='text' name='cellspacing' value='"
				+ document.getElementsByName("cellspacing")[0].value
				+ "'/><input type='text' name='cellpadding' value='"
				+ document.getElementsByName("cellpadding")[0].value
				+ "'/><input type='text' name='borderColor' value='"
				+ document.getElementsByName("borderColor")[0].value
				+ "'/><input type='text' name='tableAlign' value='"
				+ document.getElementsByName("tableAlign")[0].value
				+ "'/><input type='text' name='tableHeight' value='"
				+ document.getElementsByName("tableHeight")[0].value
				+ "'/><input type='text' name='tableWidth' value='"
				+ document.getElementsByName("tableWidth")[0].value + "'/>";
		document.getElementById('modificationInfoDiv').innerHTML = "";
		//alert(''+formElement.innerHTML);
		var urlStr = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=MODIFYTABLE";
		;

		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindra"></div>';
		var xhrArgs = {
			url : urlStr,
			sync : true,
			form : "temporaryForm",
			handleAs : "text",
			load : function(data) {
				document.getElementById("DesignerDiv").style.display = "block";
				var str = data;

				document.getElementById("DesignerDiv").innerHTML = str;
				closeDialog();
			},
			error : function(error) {
				alert(error);
			}
		};

		var deferred1 = dojo.xhrPost(xhrArgs);
	}
	function deleteTable() {
		if (document.getElementById("temporaryForm") != null)
			document.getElementById("temporaryForm").innerHTML = "";

		if (document.getElementsByName("tableId").length > 1)
			document.getElementById('modificationInfoDiv').innerHTML = "";

		var urlAjax = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=DELETETABLE&tableid="
				+ document.getElementsByName("tableId")[0].value;
		document.getElementById('modificationInfoDiv').innerHTML = "";
		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function writeLabel() {
		if (document.getElementById("temporaryForm") != null)
			document.getElementById("temporaryForm").innerHTML = "";

		document.getElementById('modificationInfoDiv').innerHTML = "";
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=WRITELABEL&tableId="
				+ document.getElementsByName("tableId")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value
				+ '&align='
				+ document.getElementsByName("align")[0].value
				+ '&fontColor='
				+ document.getElementsByName("fontColor")[0].value
				+ '&fontSize='
				+ document.getElementsByName("fontSize")[0].value
				+ '&bold='
				+ document.getElementsByName("bold")[0].value
				+ '&underLine='
				+ document.getElementsByName("underLine")[0].value
				+ '&label='
				+ document.getElementsByName("label")[0].value;
		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function addHR() {
		if (document.getElementById("temporaryForm") != null)
			document.getElementById("temporaryForm").innerHTML = "";

		document.getElementById('modificationInfoDiv').innerHTML = "";
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=ADDHR&tableId="
				+ document.getElementsByName("tableId")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value
				+ '&size='
				+ document.getElementsByName("size")[0].value
				+ '&color='
				+ document.getElementsByName("color")[0].value;

		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function deleteLabel() {
		if (document.getElementById("temporaryForm") != null)
			document.getElementById("temporaryForm").innerHTML = "";

		if (document.getElementsByName("tableId").length > 1)
			document.getElementById('modificationInfoDiv').innerHTML = "";

		var urlAjax = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=DELETELABEL&tableId="
				+ document.getElementsByName("tableId")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value;
		document.getElementById('modificationInfoDiv').innerHTML = "";
		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function addHeader() {
		if (document.getElementById("temporaryForm") != null)
			document.getElementById("temporaryForm").innerHTML = "";

		document.getElementById('modificationInfoDiv').innerHTML = "";
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=ADDHEADERTOTABLE&styleClass="
				+ document.getElementsByName("styleClass")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value
				+ '&cellspacing='
				+ document.getElementsByName("cellspacing")[0].value
				+ '&cellpadding='
				+ document.getElementsByName("cellpadding")[0].value
				+ '&tableAlign='
				+ document.getElementsByName("tableAlign")[0]
				+ '&tableHeight='
				+ document.getElementsByName("tableHeight")[0].value
				+ '&tableWidth='
				+ document.getElementsByName("tableWidth")[0].value;

		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function addFooter() {
		if (document.getElementById("temporaryForm") != null)
			document.getElementById("temporaryForm").innerHTML = "";

		document.getElementById('modificationInfoDiv').innerHTML = "";
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=ADDFOOTERTOTABLE&styleClass="
				+ document.getElementsByName("styleClass")[0].value
				+ "&rows="
				+ document.getElementsByName("rows")[0].value
				+ "&cols="
				+ document.getElementsByName("cols")[0].value
				+ '&cellspacing='
				+ document.getElementsByName("cellspacing")[0].value
				+ '&cellpadding='
				+ document.getElementsByName("cellpadding")[0].value
				+ '&tableAlign='
				+ document.getElementsByName("tableAlign")[0]
				+ '&tableHeight='
				+ document.getElementsByName("tableHeight")[0].value
				+ '&tableWidth='
				+ document.getElementsByName("tableWidth")[0].value;

		document.getElementById('modificationInfoDiv').innerHTML = "";
		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function submitPrintingTemplateDocument() {
		document.getElementById('askTemplateName').style.visibility = "visible";

	}
	function savePrintingTemplateData() {
		if (document.forms[0].addOrModifyMode.value == 'MODIFYSAVEMODE') {
			var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=SAVEMODIFY&templateName="
					+ document.forms[0].templateName.value
					+ "&addOrModifyMode="
					+ document.forms[0].addOrModifyMode.value
					+ "&templateSeqId="
					+ document.getElementById('templateSeqIdForTest').value;
			init(urlAjax);
			req.onreadystatechange = getPrintingTemplateResponse;//change this method
			req.send(null);
		} else {
			var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=ACCEPTCHANGE&templateName="
					+ document.forms[0].templateName.value;
			init(urlAjax);
			req.onreadystatechange = getPrintingTemplateResponse;
			req.send(null);
		}
	}
	function getPrintingTemplateResponse() {

		document.getElementById('askTemplateName').style.visibility = "hidden";
		if (req.readyState == 4) {
			if (req.status == 200) {

				var str = req.responseText;
				alert('Data Saved' + str);

			}
		}

	}
	function clearDivForAcceptChanges(divid) {
		document.getElementById(divid).style.visibility = "hidden";
	}
	function getTemplateDocumentAJAX() {
		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindra"></div>';
		if (req.readyState == 4) {
			if (req.status == 200) {

				var str = req.responseText;
				//alert(document.getElementById('DesignerDiv').innerHTML+"<-->"+str);
				document.getElementById('DesignerDiv').innerHTML = str;
				selectTheSelected();
				closeDialog();
			}
		}

	}

	function saveElementProperties(tableNo, rowNo, colNo, elementNo) {
		var urlStr = "/AHIMS/investigation/transaction/redesignExistingTemplateServlet.cnt?hmode=SETELEMENTPROPERTIES&tableNo="
				+ tableNo
				+ "&rowNo="
				+ rowNo
				+ "&colNo="
				+ colNo
				+ "&elementNo=" + elementNo;
		var formElement = document.getElementById("temporaryForm");
		if (document.getElementById("temporaryForm") == null) {
			formElement = document.createElement("form");
			formElement.id = "temporaryForm";
			formElement.style.display = "none";
			document.getElementsByTagName("body")[0].appendChild(formElement);
		} else {

			formElement.innerHTML = "";
		}

		if (document.getElementsByName("elementAlign") != null
				&& document.getElementsByName("elementAlign").length != 0) {
			//url+="&elementAlign="+document.getElementsByName("elementAlign")[0].value;
			formElement.innerHTML += "<input type=text name='elementAlign' value='"
					+ document.getElementsByName("elementAlign")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("elementbold") != null
				&& document.getElementsByName("elementbold").length != 0) {
			//url+="&elementbold="+document.getElementsByName("elementbold")[0].value;
			formElement.innerHTML += "<input type=text name='elementbold' value='"
					+ document.getElementsByName("elementbold")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("elementunderline") != null
				&& document.getElementsByName("elementunderline").length != 0) {
			//url+="&elementunderline="+document.getElementsByName("elementunderline")[0].value;
			formElement.innerHTML += "<input type=text name='elementunderline' value='"
					+ document.getElementsByName("elementunderline")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("elementisprintable") != null
				&& document.getElementsByName("elementisprintable").length != 0) {
			//url+="&elementisprintable="+document.getElementsByName("elementisprintable")[0].value;
			formElement.innerHTML += "<input type=text name='elementisprintable' value='"
					+ document.getElementsByName("elementisprintable")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("elementvalue") != null
				&& document.getElementsByName("elementvalue").length != 0) {
			//url+="&elementvalue="+document.getElementsByName("elementvalue")[0].value;
			formElement.innerHTML += "<input type=text name='elementvalue' value='"
					+ document.getElementsByName("elementvalue")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementdefaultValue") != null
				&& document.getElementsByName("elementdefaultValue").length != 0) {
			//url+="&elementdefaultValue="+document.getElementsByName("elementdefaultValue")[0].value;
			formElement.innerHTML += "<input type=text name='elementdefaultValue' value='"
					+ document.getElementsByName("elementdefaultValue")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementeventName") != null
				&& document.getElementsByName("elementeventName").length != 0) {
			//url+="&elementeventName="+document.getElementsByName("elementeventName")[0].value;
			formElement.innerHTML += "<input type=text name='elementeventName' value='"
					+ document.getElementsByName("elementeventName")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementValidationString") != null
				&& document.getElementsByName("elementValidationString").length != 0) {
			//url+="&elementValidationString="+document.getElementsByName("elementValidationString")[0].value;
			formElement.innerHTML += "<input type=text name='elementValidationString' value='"
					+ document.getElementsByName("elementValidationString")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementsize") != null
				&& document.getElementsByName("elementsize").length != 0) {
			//url+="&elementsize="+document.getElementsByName("elementsize")[0].value;
			formElement.innerHTML += "<input type=text name='elementsize' value='"
					+ document.getElementsByName("elementsize")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementmaxLength") != null
				&& document.getElementsByName("elementmaxLength").length != 0) {
			//url+="&elementmaxLength="+document.getElementsByName("elementmaxLength")[0].value;
			formElement.innerHTML += "<input type=text name='elementmaxLength' value='"
					+ document.getElementsByName("elementmaxLength")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementrows") != null
				&& document.getElementsByName("elementrows").length != 0) {
			//url+="&elementrows="+document.getElementsByName("elementrows")[0].value;
			formElement.innerHTML += "<input type=text name='elementrows' value='"
					+ document.getElementsByName("elementrows")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementcols") != null
				&& document.getElementsByName("elementcols").length != 0) {
			//url+="&elementcols="+document.getElementsByName("elementcols")[0].value;
			formElement.innerHTML += "<input type=text name='elementcols' value='"
					+ document.getElementsByName("elementcols")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementfontsize") != null
				&& document.getElementsByName("elementfontsize").length != 0) {
			//url+="&elementfontsize="+document.getElementsByName("elementfontsize")[0].value;
			formElement.innerHTML += "<input type=text name='elementfontsize' value='"
					+ document.getElementsByName("elementfontsize")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementfontcolor") != null
				&& document.getElementsByName("elementfontcolor").length != 0) {
			//url+="&elementfontcolor="+document.getElementsByName("elementfontcolor")[0].value;
			formElement.innerHTML += "<input type=text name='elementfontcolor' value='"
					+ document.getElementsByName("elementfontcolor")[0].value
					+ "'/>";
		}
		if (document.getElementsByName("elementeditor") != null
				&& document.getElementsByName("elementeditor").length != 0) {
			//url+="&elementeditor="+document.getElementsByName("elementeditor")[0].value;
			formElement.innerHTML += "<input type=text name='elementeditor' value='"
					+ document.getElementsByName("elementeditor")[0].value
					+ "'/>";
		}

		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindra"></div>';
		var xhrArgs = {
			url : urlStr,
			sync : true,
			form : "temporaryForm",
			handleAs : "text",
			load : function(data) {
				document.getElementById("DesignerDiv").style.display = "block";
				var str = data;
				//alert(str);
				document.getElementById("DesignerDiv").innerHTML = str;

			},
			error : function(error) {
				alert(error);
			}
		};

		var deferred1 = dojo.xhrPost(xhrArgs);

	}

	function savecolumnProperties(tableNo, rowNo, colNo) {
		var urlStr = "/AHIMS/investigation/transaction/redesignExistingTemplateServlet.cnt?hmode=SETCOLUMNPROPERTIES&tableNo="
				+ tableNo + "&rowNo=" + rowNo + "&colNo=" + colNo;
		var formElement = document.getElementById("temporaryForm");
		if (document.getElementById("temporaryForm") == null) {
			formElement = document.createElement("form");
			formElement.id = "temporaryForm";
			formElement.style.display = "none";
			document.getElementsByTagName("body")[0].appendChild(formElement);
		} else {

			formElement.innerHTML = "";
		}

		if (document.getElementsByName("columnClass") != null) {
			//url+="&columnClass="+document.getElementsByName("columnClass")[0].value;
			formElement.innerHTML += "<input type=text name='columnClass' value='"
					+ document.getElementsByName("columnClass")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("columnAlign") != null) {
			//url+="&columnAlign="+document.getElementsByName("columnAlign")[0].value;
			formElement.innerHTML += "<input type=text name='columnAlign' value='"
					+ document.getElementsByName("columnAlign")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("columnWidth") != null) {
			//url+="&columnWidth="+document.getElementsByName("columnWidth")[0].value;
			formElement.innerHTML += "<input type=text name='columnWidth' value='"
					+ document.getElementsByName("columnWidth")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("columnColSpan") != null
				&& document.getElementsByName("columnColSpan").length != 0) {
			//url+="&columnColSpan="+document.getElementsByName("columnColSpan")[0].value;
			formElement.innerHTML += "<input type=text name='columnColSpan' value='"
					+ document.getElementsByName("columnColSpan")[0].value
					+ "'/>";
		}

		if (document.getElementsByName("columnNo") != null) {
			//url+="&columnNo="+document.getElementsByName("columnNo")[0].value;
			formElement.innerHTML += "<input type=text name='columnNo' value='"
					+ document.getElementsByName("columnNo")[0].value + "'/>";
		}

		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindra"></div>';
		var xhrArgs = {
			url : urlStr,
			sync : true,
			form : "temporaryForm",
			handleAs : "text",
			load : function(data) {
				document.getElementById("DesignerDiv").style.display = "block";
				var str = data;
				//alert(str);
				document.getElementById("DesignerDiv").innerHTML = str;

			},
			error : function(error) {
				alert(error);
			}
		};

		var deferred1 = dojo.xhrPost(xhrArgs);

	}

	function submitRedesignPage(hmodeValue) {
		if (document.getElementsByName("tableNo").length > 1)
			document.getElementById('modificationInfoDiv').innerHTML == "";

		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode="
				+ hmodeValue + "&tableNo=" + document.forms[0].tableNo.value;
		if (hmodeValue == 'MERGECELLS')
			urlAjax += '&rowNo=' + document.forms[0].rowNo.value
					+ '&cell1ColNo=' + document.forms[0].cell1ColNo.value
					+ '&cell2ColNo=' + document.forms[0].cell2ColNo.value;
		else if (hmodeValue == 'EXCHANGECELLS')
			urlAjax += '&cell1RowNo=' + document.forms[0].cell1RowNo.value
					+ '&cell1ColNo=' + document.forms[0].cell1ColNo.value
					+ '&cell2RowNo=' + document.forms[0].cell2RowNo.value
					+ '&cell2ColNo=' + document.forms[0].cell2ColNo.value;
		else if (hmodeValue == 'DELETECELLS')// need to see after the work
			urlAjax += '&rowNo=' + document.forms[0].rowNo.value + '&colNo='
					+ document.forms[0].colNo.value;
		else if (hmodeValue == 'DELETEBLANKROW')//delete blank row
			urlAjax += '&rowNo=' + document.forms[0].rowNo.value;
		else if (hmodeValue == 'EXCHANGEROWS')//exchange row
			urlAjax += '&rowNo1=' + document.forms[0].rowNo1.value + '&rowNo2='
					+ document.forms[0].rowNo2.value;
		else if (hmodeValue == 'ADDROWAFTERROWNO')//add blank row
			urlAjax += '&rowNo=' + document.forms[0].rowNo.value;
		else if (hmodeValue == 'ADDCOLUMNTOEACHROWAFTERCOLUMNNO')//add blank column in each row
			urlAjax += '&colNo=' + document.forms[0].colNo.value;
		else if (hmodeValue == 'ACCEPTCHANGE')//add blank column in each row
			urlAjax += '&colNo=' + document.forms[0].colNo.value;
		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}

	function miscellaneousDialog() {

		diag = new dijit.Dialog({
			title : "Miscellaneous Property Dialog",
			content : "<div id='diagContent'>"
					+ document.getElementById('modificationInfoDiv').innerHTML
					+ "</div>",
			onCancel : closeDialog,
			draggable : true

		});
		document.getElementById('modificationInfoDiv').innerHTML = "";
		diag.show();
	}
	function closeDialog() {
		diag.destroyRecursive();

		//alert("..In closeDialog");
	}
	function selectedFunctionality(obj) {
		var isShowDialog = true;

		//var comboBoxSelectedValue=dijit.byId("functionPerformedId").get('displayedValue');
		//alert(obj);

		//alert(document.forms[0].functionPerformed.options[document.forms[0].functionPerformed.selectedIndex].value);
		if (obj == "") {
			document.getElementById('modificationInfoDiv').innerHTML = "";
		} else {
			var value = parseInt(obj) //(document.forms[0].functionPerformed.value);
			switch (value) {
			case 0:
				createDivForMergingCells();
				if (previouslySelectedColumn == null || selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("MERGECELLS");
				}

				break;
			case 1:
				createDivForExchangingtheCells();
				if (previouslySelectedColumn == null || selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("EXCHANGECELLS");
				}
				break;
			case 2:
				createDivForDeleteCells();
				if (selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("DELETEBLANKCELL");
				}
				break;
			case 3:
				createDivForDeleteBlankRow();
				if (selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("DELETEBLANKROW");
				}
				break;
			case 4:
				createDivForExchangingtheRows();
				if (previouslySelectedColumn == null || selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("EXCHANGEROWS");
				}
				break;
			case 5:
				createDivForAddBlankRow();
				if (selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("ADDROWAFTERROWNO");
				}
				break;
			case 6:
				createDivForAddColumntoEachRow();
				if (selectedColumn == null) {

				} else {
					isShowDialog = false;
					submitRedesignPage("ADDCOLUMNTOEACHROWAFTERCOLUMNNO");
				}
				break;
			case 7:
				createDivForModifyColumnProperties();
				if (selectedColumn == null) {

				} else {
					isShowDialog = false;
					ajaxCallerForColumnProperties(
							"/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=REDESIGNTEMPLATE",
							document.forms[0].tableNo.value,
							document.forms[0].rowNo.value,
							document.forms[0].colNo.value);
				}
				break;
			case 9:
				createDivForcreatingTable();
				isShowDialog = true;
				break;
			case 10:
				createDivForModifyingTable();
				isShowDialog = true;
				break;
			case 11:
				deleteDivForTable();
				isShowDialog = true;
				break;
			case 12:
				writeLabelToTable();
				isShowDialog = true;
				break;
			case 13:
				addHRToTable();
				isShowDialog = true;
				break;
			case 14:
				deleteLabelFromTable();
				if (selectedColumn == null) {

				} else {
					deleteLabel();
					isShowDialog = false;
				}
				break;
			case 15:
				addHeaderToTable();
				isShowDialog = true;
				break;
			case 16:
				addFooterToTable();
				isShowDialog = true;
				break;
			case 17:
				addVOObjectValues();
				isShowDialog = true;

				break;
			case 18:
				deleteElement();
				if (selectedColumn == null) {

				} else {
					isShowDialog = false;
					deleteElementFromDocument();
				}
				break;
			case 19:
				addRangeElement();
				isShowDialog = false;

				break;
			case 20:
				modifyTableColumnProperites();
				isShowDialog = false;
				break;
			case 21:
				replaceCurentTemplateWithExistingOne();
				isShowDialog = false;
				break;
			case -1:
				break;
			}
		}
		if (isShowDialog == true)
			miscellaneousDialog();

	}

	/* value=0*/
	function createDivForMergingCells() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Cell 1 Col. No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cell1ColNo' value='"
				+ ((previouslySelectedColumnNo == null) ? ""
						: previouslySelectedColumnNo) + "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Cell 2 Col. No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cell2ColNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"MERGECELLS\")'/>";

	}

	/* value=1*/
	function createDivForExchangingtheCells() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Cell 1 Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cell1RowNo' value='"
				+ ((previouslySelectedRowNo == null) ? ""
						: previouslySelectedRowNo) + "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Cell 1 Col. No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cell1ColNo' value='"
				+ ((previouslySelectedColumnNo == null) ? ""
						: previouslySelectedColumnNo) + "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Cell 2 Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='cell2RowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";
		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Cell 2 Col. No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='cell2ColNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"EXCHANGECELLS\")'/>";

	}
	/* value=2*/
	function createDivForDeleteCells() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Column No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colspan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='colNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"DELETEBLANKCELL\")'/>";

	}

	/* value=3*/

	function createDivForDeleteBlankRow() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"DELETEBLANKROW\")'/>";

	}
	/* value=4*/
	function createDivForExchangingtheRows() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo1' value='"
				+ ((previouslySelectedRowNo == null) ? ""
						: previouslySelectedRowNo) + "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo2' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"EXCHANGEROWS\")'/>";

	}
	/* value=5*/
	function createDivForAddBlankRow() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";
		;

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "After Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"ADDROWAFTERROWNO\")'/>";

	}
	/* value=6*/
	function createDivForAddColumntoEachRow() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "After Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='colNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='SAVE' onClick='submitRedesignPage(\"ADDCOLUMNTOEACHROWAFTERCOLUMNNO\")'/>";

	}
	function createDivForModifyColumnProperties() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='tableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "After Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='colNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='Find' onClick='ajaxCallerForColumnProperties(\"/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=REDESIGNTEMPLATE\",document.forms[0].tableNo.value,document.forms[0].rowNo.value,document.forms[0].colNo.value)'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<div id='columnElementPropertiesDiv' style='position: fixed ;bottom:0; right:0'></div>";

	}
	function addElementProperties(labTestCode, parentParameterCode,
			testparameterCode, type) {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';

		
		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Include Label";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='checkbox' name='labelInclusion' value='0' size='8' onClick='labelInclusionClick()' checked/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='labelTableNo' value='"
				+ ((previouslySelectedTable == null) ? ""
						: previouslySelectedTable) + "'/>";
		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='labelRowNo' value='"
				+ ((previouslySelectedRowNo == null) ? ""
						: previouslySelectedRowNo) + "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "After Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='labelColNo' value='"
				+ ((previouslySelectedColumnNo == null) ? ""
						: previouslySelectedColumnNo) + "' size='8'/>";

		
		
		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Include Element";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='checkbox' name='elementInclusion' value='0' size='8' onClick='elementInclusionClick()' checked/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='elementTableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='elementRowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='elementColNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		
		var rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Include Range Element";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='checkbox' name='rangeElementInclusion' value='0' size='8' onClick='rangeElementInclusionClick()' checked/>";

		rowElement = document.createElement("tr");
		//tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='rangeElementTableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
	//	tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rangeElementRowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
	//	tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rangeElementColNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='Add Element' onClick='sendDataToPositionEle(\""
				+ labTestCode
				+ "\",\""
				+ parentParameterCode
				+ "\",\""
				+ testparameterCode
				+ "\",\""
				+ type
				+ "\")'/><input type='button' class='sub5' value='Cancel' onClick='closeDialog()'/>";
		miscellaneousDialog();

	}

	function addRangeElement() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';

		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='elementTableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='elementRowNo' value='"
				+ ((previouslySelectedRowNo == null) ? ""
						: previouslySelectedRowNo) + "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='elementColumnNo' value='"
				+ ((previouslySelectedColumnNo == null) ? ""
						: previouslySelectedColumnNo) + "' size='8'/>";

		var rowElement = document.createElement("tr");

		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='rangeElementTableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Row No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rangeElementRowNo' value='"
				+ ((selectedRowNo == null) ? "" : selectedRowNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Col. No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='rangeElementColumnNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='Add Element' onClick='rangeAddition()'/><input type='button' class='sub5' value='Cancel' onClick='closeDialog()'/>";
		miscellaneousDialog();

	}
	function modifyTableColumnProperites() {
		var modificationInfoDiv = 'modificationInfoDiv';
		var divElement = document.getElementById(modificationInfoDiv);
		divElement.innerHTML = "";
		var tableElement = document.createElement("table");
		divElement.appendChild(tableElement);
		tableElement.width = '100%';

		var rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		var colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Table No";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "<input type='text' name='selectedTableNo' value='"
				+ ((selectedTable == null) ? "" : selectedTable) + "'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "Column No.";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='selectedColumnNo' value='"
				+ ((selectedColumnNo == null) ? "" : selectedColumnNo)
				+ "' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'right';
		colElement.width = '50%';
		colElement.colSpan = '1';
		colElement.innerHTML = "width";
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'left';
		colElement.colSpan = '1';
		colElement.width = '50%';
		colElement.innerHTML = "<input type='text' name='selectedColumnWidth' value='' size='8'/>";

		rowElement = document.createElement("tr");
		tableElement.appendChild(rowElement);
		colElement = document.createElement("td");
		rowElement.appendChild(colElement);
		colElement.align = 'center';
		colElement.width = '100%';
		colElement.colSpan = '2';
		colElement.innerHTML = "<input type='button' class='sub5' value='Modify Table Column' onClick='tableColumnModification()'/><input type='button' class='sub5' value='Cancel' onClick='closeDialog()'/>";
		miscellaneousDialog();

	}

	function init(url) {
		try {
			// Firefox, Opera 8.0+, Safari  
			req = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer 
			try {
				req = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					req = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		req.open("GET", url, true);
		req.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
	}

	function ajaxCallerForColumnProperties(url, tableNo, rowNo, columnNo) {
		var urlStr = url + '&' + "tableNo=" + tableNo + '&rowNo=' + rowNo
				+ '&colNo=' + columnNo;
		//this line will call the function named ajaxResponse() that will 
		var xhrArgs = {
			ul : urlStr,
			postData : "",
			handleAs : "xml",
			load : function(data) {

				var resultNodes = data.getElementsByTagName("result");
				for (var k = 0; k < resultNodes.lenght; k++) {
					var resultNode = resultNodes[i]
					document.getElementById(document.getAttribute("divid")).innerHTML = resultNode.innerHTML;

				}

			},
			error : function(error) {
				alert(error
						+ "Error while loading User Infomation please login again or may be user noth authorized to raise tests");
			}
		};

		var deferred1 = dojo.xhrGet(xhrArgs);

	}
	function fetchColumnElementProperties(tableNo, rowNo, columnNo) {

		//alert('clicked fetch');
		var url = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=REDESIGNTEMPLATE";
		var urlStr = url + '&' + "tableNo=" + tableNo + '&rowNo=' + rowNo
				+ '&colNo=' + columnNo;
		//this line will call the function named ajaxResponse() that will 
		var id = "TD" + tableNo + rowNo + columnNo;
		var xhrArgs = {
			url : urlStr,
			postData : "",
			handleAs : "xml",
			load : function(data) {
				var resultNodes = data.getElementsByTagName("result");
				for (var k = 0; k < resultNodes.length; k++) {
					var resultNode = resultNodes[k];
					document.getElementById(resultNode.getAttribute("divid")).innerHTML = resultNode
							.getAttribute("dividhtml");
				}

				showSelectedTdAsSelected(id, tableNo, rowNo, columnNo);

			},
			error : function(error) {
				alert(error
						+ "Error while loading User Infomation please login again or may be user not authorized to raise tests");
			}
		};

		var deferred1 = dojo.xhrGet(xhrArgs);

	}

	function showSelectedTdAsSelected(id, tableNo, rowNo, columnNo) {

		if (selectedColumn != null) {

			if (selectedColumn.id != id) {
				if (previouslySelectedColumn != null)
					previouslySelectedColumn.className = previouslySelectedColumnClass;

				previouslySelectedColumn = selectedColumn;
				previouslySelectedTable = selectedTable;
				previouslySelectedColumnNo = selectedColumnNo;
				previouslySelectedRowNo = selectedRowNo;
				previouslySelectedColumnClass = selectedColumnClass;
				previouslySelectedColumnId = previouslySelectedColumn.id;

				selectedColumn.className = 'previouslyHighlighted';

				selectedTable = tableNo;
				selectedColumnNo = columnNo;
				selectedRowNo = rowNo;

				selectedColumn = document.getElementById(id);
				selectedColumnId = id;
				selectedColumnClass = selectedColumn.className;
				document.getElementById(id).className = 'highlighted';

			}

		} else {
			selectedTable = tableNo;
			selectedColumnNo = columnNo;
			selectedRowNo = rowNo;

			selectedColumn = document.getElementById(id);
			selectedColumnId = id;
			selectedColumnClass = selectedColumn.className;
			document.getElementById(id).className = 'highlighted';

		}

	}

	function ajaxCallerForSettingColumnProperties(url, tableNo, rowNo, columnNo) {

		init(url + '?' + "tableNo=" + tableNo + '&rowNo=' + rowNo + '&colNo='
				+ columnNo);
		//this line will call the function named ajaxResponse() that will 

		req.onreadystatechange = ajaxResponseForColumnProperties;

		req.send(null);
	}

	function ajaxResponseForColumnProperties() {
		document.getElementById("columnElementPropertiesDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="nitin"></div>';
		document.getElementById("columnElementPropertiesDiv").style.display = "block";

		if (req.readyState == 4) {

			if (req.status == 200) {

				var str = req.responseText;
				document.getElementById("columnElementPropertiesDiv").innerHTML = str;
			}

		}
	}

	function ajaxResponseForSettingColumnProperties() {
		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="nitin"></div>';
		document.getElementById("DesignerDiv").style.display = "block";

		if (req.readyState == 4) {

			if (req.status == 200) {

				var str = req.responseText;
				//alert(str);
				document.getElementById("DesignerDiv").innerHTML = str;
			}

		}

	}

	function ajaxResponseForSettingElementProperties() {
		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindranath"></div>';
		document.getElementById("DesignerDiv").style.display = "block";

		if (req.readyState == 4) {

			if (req.status == 200) {

				var str = req.responseText;
				//alert(str);
				document.getElementById("DesignerDiv").innerHTML = str;
			}

		}

	}

	function getTestnTestGroup() {
		var strul = "<ul id='tree_menu'>";
		var strulend = "</ul>";		
		var obj = document.getElementsByName('exsistingTemplate')[0];
		var objValue = null;
		if(obj != null )
		{
				objValue = obj.value;
		}
		var formObj = document.getElementsByName('testCode')[0].value;//document.forms[0].testCode.options[document.forms[0].testCode.selectedIndex].value;
		//alert(document.getElementsByName('testCode')[0].options[document.getElementsByName('testCode')[0].selectedIndex].text);
		var urlStr = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=GETLABTESTWITHTESTGROUPDATA&testCode="
				+ formObj
				+ "&addOrModifyMode=1"
				//+ document.getElementById('addOrModifyMode').value
				+ "&exsistingTemplate="
				+ objValue
				+ "&testGroupName="+document.getElementsByName('testCode')[0].options[document.getElementsByName('testCode')[0].selectedIndex].text;
				//+ document.forms[0].testCode.options[document.forms[0].testCode.selectedIndex].text;
		var xhrArgs = {
			url : urlStr,
			sync : true,
			postData : "",
			handleAs : "text",
			load : function(data) {
				var str = data;
				document.getElementById('divNewTestList').innerHTML = strul
						+ str + strulend;
			},
			error : function(error) {
				alert(error
						+ "Error while loading User Infomation please login again or may be user noth authorized to raise tests");
			}
		};
		var deferred1 = dojo.xhrGet(xhrArgs);
	}
	
	function rangeAddition() {
		var urlStr = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=ADDRANGEELEMENT&elementRowNo="
				+ document.getElementsByName("elementRowNo")[0].value
				+ "&elementTableNo="
				+ document.getElementsByName("elementTableNo")[0].value
				+ "&elementColumnNo="
				+ document.getElementsByName("elementColumnNo")[0].value
				+ "&rangeElementTableNo="
				+ document.getElementsByName("rangeElementTableNo")[0].value
				+ "&rangeElementRowNo="
				+ document.getElementsByName("rangeElementRowNo")[0].value
				+ "&rangeElementColumnNo="
				+ document.getElementsByName("rangeElementColumnNo")[0].value;
		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="siddharth"></div>';
		document.getElementById("DesignerDiv").style.display = "block";
		var xhrArgs = {
			url : urlStr,
			sync : true,
			postData : "",
			handleAs : "text",
			load : function(data) {
				var str = data;
				//	alert("Template Successfully Updated");
				document.getElementById('DesignerDiv').innerHTML = str;
				selectTheSelected();
				closeDialog();
			},
			error : function(error) {
				alert("rangeAddition ::" + error);
				closeDialog();
			}
		};
		var deferred1 = dojo.xhrGet(xhrArgs);

	}
	function tableColumnModification() {
		var formElement = document.getElementById("temporaryForm");
		if (document.getElementById("temporaryForm") == null) {
			formElement = document.createElement("form");
			formElement.id = "temporaryForm";
			formElement.style.display = "none";
			document.getElementsByTagName("body")[0].appendChild(formElement);
		} else {

			formElement.innerHTML = "";
		}

		formElement.innerHTML = "<input type='text' name='selectedTableNo' value='"
				+ document.getElementsByName("selectedTableNo")[0].value
				+ "'/><input type='text' name='selectedColumnNo' value='"
				+ (document.getElementsByName("selectedColumnNo")[0].value)
				+ "'/><input type='text' name='selectedColumnWidth' value='"
				+ (document.getElementsByName("selectedColumnWidth")[0].value)
				+ "'/>";

		var urlStr = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=TABLECOLUMNMODIFICATION";
		document.getElementById("DesignerDiv").innerHTML = '<div><image src="/HISInvestigationG5/hisglobal/images/loading.gif" name="rabindra"></div>';
		document.getElementById("DesignerDiv").style.display = "block";
		var xhrArgs = {
			url : urlStr,
			sync : true,
			form : "temporaryForm",
			handleAs : "text",
			load : function(data) {
				var str = data;
				//	alert("Template Successfully Updated");
				document.getElementById('DesignerDiv').innerHTML = str;
				selectTheSelected();
				closeDialog();
			},
			error : function(error) {
				alert("tableColumnModification ::" + error);
				closeDialog();
			}
		};
		var deferred1 = dojo.xhrPost(xhrArgs);

	}
	function getTestnTestGroupData() {
		var formObj = document.forms[0].testCode.options[document.forms[0].testCode.selectedIndex].value;
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=GETTESTNTESTGROUPDATA&testCode="
				+ formObj
				+ "&addOrModifyMode="
				+ document.getElementById('addOrModifyMode').value;
		init(urlAjax);
		req.onreadystatechange = getTestNTestGroupDataByAJAX;
		req.send(null);
	}

	function getTestNTestGroupDataByAJAX() {
		if (req.readyState == 4) {
			if (req.status == 200) {

				var str = req.responseText;
				document.getElementById('ListDiv').innerHTML = str;

			}

		}
	}
	function getParameterDtls(index) {

		//var urlAjax="/AHIMS/investigation/transaction/CreateNewPrintingTemplateSer?hmode=GETELEMENTDETAILS&elementIndex="+index;
		//init(urlAjax);
		//req.onreadystatechange = showElementDetails;
		//req.send(null);
	}
	function showElementPropertiesOnMouseEvent(index) {

		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=GETELEMENTDETAILS&elementIndex="
				+ index;

		init(urlAjax);
		req.onreadystatechange = showElementDetails;
		req.send(null);
	}
	function showElementDetails() {
		if (req.readyState == 4) {
			if (req.status == 200) {

				var str = req.responseText.split('#')[0];
				var index = req.responseText.split('#')[1];
				document.getElementById('showElementDivId' + index).style.visibility = "visible";
				document.getElementById('showElementDivId' + index).innerHTML = str;

			}

		}
	}
	function hideElementPropertiesOnMouseOut(index) {
		document.getElementById('showElementDivId' + index).style.visibility = "hidden";
	}
	var globalIndexForEle;
	function sendTOTable(index) {
		globalIndexForEle = index;
		var elementType = document.getElementById('elementType' + index).value;
		//alert(elementType);
		if (elementType == 'label')
			document.getElementById('askColNRowForLABEL').style.visibility = "visible";
		else {
			document.getElementById('askColNRowForELEMENT').style.visibility = "visible";
		}

	}

	function sendDataToPosition(colPosition, rowPosition, tablePosition) {

		document.getElementById('askColNRowForLABEL').style.visibility = "hidden";
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=POPULATEDATAINTOTABLEFORLABEL&elementIndex="
				+ globalIndexForEle
				+ "&rowNo="
				+ rowPosition
				+ "&colNo="
				+ colPosition
				+ "&tablePosition="
				+ tablePosition
				+ "&addOrModifyMode=" + document.forms[0].addOrModifyMode.value;

		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function sendDataToPositionEle(labTestCode, parentParameterCode,
			testparameterCode, elementType) {
		var urlAjax = "/HISInvestigationG5/new_investigation/masters/DynamicReportTemplateACT.cnt?hmode=POPULATEDATAINTOTABLEFORELEMENT&parameterCode="
				+ testparameterCode
				+ "&labTestCode="
				+ labTestCode
				+ "&parentParameterCode="
				+ parentParameterCode
				+ "&elementType="
				+ elementType
				+ "&elementTablePosition="
				+ document.getElementsByName("elementTableNo")[0].value
				+ "&elementRowNo="
				+ document.getElementsByName("elementRowNo")[0].value
				+ "&elementColNo="
				+ document.getElementsByName("elementColNo")[0].value
				+ "&labelTablePosition="
				+ document.getElementsByName("labelTableNo")[0].value
				+ "&labelRowNo="
				+ document.getElementsByName("labelRowNo")[0].value
				+ "&labelColNo="
				+ document.getElementsByName("labelColNo")[0].value
				+ "&elementInclusion="
				+ document.getElementsByName("elementInclusion")[0].value
				+ "&labelInclusion="
				+ document.getElementsByName("labelInclusion")[0].value;

		init(urlAjax);
		req.onreadystatechange = getTemplateDocumentAJAX;
		req.send(null);
	}
	function clearDiv(divid) {
		document.getElementById(divid + '').style.visibility = "hidden";
	}
	function modifyPrevioiusTemplate() {

		var modifyWindow = window
				.open(
						"/HISInvestigationG5/new_investigation/masters/CreateNewPrintingTemplateSer?hmode=GETTEMPLATELIST",
						"modifyWindow",
						"status=1,scrollbars=1,width=500,height=100")
		modifyWindow.moveTo(300, 300);

	}
	function ShowSelectedPdf() {
		//alert(isConfidential);
		var url = '/HISInvestigationG5/new_investigation/masters/PDFPrintingACT.cnt?hmode=GETPRINTINGTEMPLATEVIEW&sessionName=TESTTEMPLATEDOCUMENT';
		window.open(url, "MyWindows", "200", "200");
	}
	function closecolumnElementPropertiesDiv() {
		document.getElementById('columnElementPropertiesDiv').innerHTML = '';
		document.getElementById('columnElementPropertiesDiv').style.display = 'none';
	}

	function onSelectionParameter(labTestCode, parentParameterCode,
			testparameterCode, type) {

		var elementName = type + labTestCode.substring(0, 5) + "template#";
		if (parentParameterCode == testparameterCode) {
			elementName += testparameterCode;
		} else {
			elementName += parentParameterCode + testparameterCode;
		}

		var foundElement = document.getElementById(elementName);
		if (foundElement != null) {
			if (foundElement != null) {
				(document.getElementById(foundElement.parentNode.id)).onclick(); // 
				foundElement.focus();
			}
		} else {
			addElementProperties(labTestCode, parentParameterCode,
					testparameterCode, type);
		}
	}

	function elementInclusionClick() {
		if (document.getElementsByName("elementInclusion")[0].checked == true) {
			document.getElementsByName("elementInclusion")[0].value = "0";
			document.getElementsByName("elementTableNo")[0].value = (selectedTable == null) ? ""
					: selectedTable;
			document.getElementsByName("elementRowNo")[0].value = (selectedRowNo == null) ? ""
					: selectedRowNo;
			document.getElementsByName("elementColNo")[0].value = (selectedColumnNo == null) ? ""
					: selectedColumnNo;
		} else {
			document.getElementsByName("elementInclusion")[0].value = "1";
			ocument.getElementsByName("elementTableNo")[0].value = "NA";
			document.getElementsByName("elementRowNo")[0].value = "NA";
			document.getElementsByName("elementColNo")[0].value = "NA";
		}
	}
	function rangeElementInclusionClick() {
		if (document.getElementsByName("rangeElementInclusion")[0].checked == true) {
			document.getElementsByName("rangeElementInclusion")[0].value = "0";
			document.getElementsByName("rangeElementTableNo")[0].value = (selectedTable == null) ? ""
					: selectedTable;
			document.getElementsByName("rangeElementRowNo")[0].value = (selectedRowNo == null) ? ""
					: selectedRowNo;
			document.getElementsByName("rangeElementColNo")[0].value = (selectedColumnNo == null) ? ""
					: selectedColumnNo;
		} else {
			document.getElementsByName("rangeElementInclusion")[0].value = "1";
			document.getElementsByName("rangeElementTableNo")[0].value = "NA";
			document.getElementsByName("rangeElementRowNo")[0].value = "NA";
			document.getElementsByName("rangeElementColNo")[0].value = "NA";
		}
	}
	function labelInclusionClick() {
		if (document.getElementsByName("labelInclusion")[0].checked == true) {
			document.getElementsByName("labelInclusion")[0].value = "0";
			document.getElementsByName("labelTableNo")[0].value = (previouslySelectedTable == null) ? ""
					: previouslySelectedTable;
			document.getElementsByName("labelRowNo")[0].value = (previouslySelectedRowNo == null) ? ""
					: previouslySelectedRowNo;
			document.getElementsByName("labelColNo")[0].value = (previouslySelectedColumnNo == null) ? ""
					: previouslySelectedColumnNo;
		} else {
			document.getElementsByName("labelInclusion")[0].value = "1";
			document.getElementsByName("labelTableNo")[0].value = "NA";
			document.getElementsByName("labelRowNo")[0].value = "NA";
			document.getElementsByName("labelColNo")[0].value = "NA";
		}
	}
	var replacementDialog = null;
	function replaceCurentTemplateWithExistingOne() {
		var urlStr = "/HISInvestigationG5/new_investigation/masters/createNewTemplateForTestGroup.cnt?hmode=REPLACEMENTPROCESS";
		replacementDialog = new dijit.Dialog(
				{
					title : "Replacement/Delete Printing Template Process",
					content : "<iframe src='"+urlStr+"' id='famePdf' style='width:800px;height:800px'></iframe>",
					onCancel : closereplacementDialog,
					draggable : true

				});

		replacementDialog.show();

		//openedPopoup=diag;
		if (e != null)
			dojo.stopEvent(e);

	}

	function closereplacementDialog() {
		replacementDialog.destroyRecursive();
		//document.forms[0].hmode.value="INTIALIZEREDESIGNTEMPLATE";
		//document.forms[0].submit();
	}
</script>


<body>


	<div dojoType="dijit.layout.BorderContainer" gutters="true"
		id="borderContainerOne" style="height: 105px;">
		<div dojoType="dijit.layout.ContentPane" region="top"
			style="height: 130px;">
			<table width='100%' align="center" cellspacing="1" cellpadding="0">
				<tr>
					<td width='100%' colspan="6">
						<table width='100%' align="center" cellspacing="1" cellpadding="0">
							<tr>
								<td width='120%'><his:TitleTag
										name="Re-Design The Template">
										<b><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> </font></b>
									</his:TitleTag></td>
							</tr>
						</table>


						<table width='100%' cellspacing="1">
							<tr>
								<td class='tdfonthead' colspan='1' width='25%'><div
										align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">Existing
											Template Name </font>
									</div></td>
								<td class='tdfont' colspan='5' width='75%'><div
										align="left">
										<table>
											<tr>
												<td>
												<logic:notEmpty name="EXSISTTEMPLATELIST">
														<html:select name="DynamicReportTemplateFB"
															property="exsistingTemplate" style="width:150px">
															<html:option value="-1">Select the Test</html:option>
															<html:options collection="EXSISTTEMPLATELIST"
																property="value" labelProperty="label" />
														</html:select>
													</logic:notEmpty>
													</td>
												<td>
													<button dojoType="dijit.form.Button" class='sub5' id="goId"
														onClick="">Load</button>
													<button dojoType="dijit.form.Button" class='sub5'
														id="goNew" onClick="">New</button>
												</td>
											</tr>
										</table></td>
							</tr>

							<tr>
								<td class='HEADER' colspan='6' width='100%'><his:TitleTag
										name="Test Template">
										<b><font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"></font></b>
									</his:TitleTag></td>

							</tr>
						</table>
			</table>
		</div>

	</div>
	<div dojoType="dijit.layout.BorderContainer" liveSplitters="false"
		design="sidebar" region="center" id="mainSplit" style="height: 400px;">

		<div dojoType="dijit.layout.AccordionContainer" minSize="20"
			style="width: 240px;" id="leftAccordion" region="leading"
			splitter="true">
			<div dojoType="dijit.layout.AccordionPane" title="Add Test"
				selected="true" id="addTestOrGroupId">

				<table width='100%' cellspacing="1">
					<tr>
						<td colspan='6' width='25%'><div
								style="background-color: lightgreen;">
								<html:select name="DynamicReportTemplateFB"
									property="testCode" style="width:150px">
									<html:option value="-1">Select the Test</html:option>
									<html:options
										collection="<%=InvestigationConfig.sessionOptionTestList%>"
										property="value" labelProperty="label" />
								</html:select>
							</div>
							<button dojoType="dijit.form.Button" id="goAdd" onClick="">Add</button></td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="divNewTestList" style="background-color: lightgreen;">

							</div>
						</td>
						<!-- 						<td colspan='6' width='25%'> -->
						<!-- 							<div style="background-color: lightgreen;"> -->
						<%-- 								<html:select name="DynamicReportTemplateFB" property="testCode" --%>
						<%-- 									style="width:150px"> --%>
						<%-- 									<html:option value="-1">Select the Test</html:option> --%>
						<%-- 									<html:options --%>
						<%-- 										collection="<%=new_investigation.InvestigationConfig.sessionOptionTestList%>" --%>
						<%-- 										property="value" labelProperty="label" /> --%>
						<%-- 								</html:select> --%>
						<!-- 							</div> -->
						<!-- 							<button dojoType="dijit.form.Button" id="goAdd" onClick="">Add</button> -->
						<!-- 						</td> -->
					</tr>
					<tr>
						<td colspan="2">
							<div id="divNewTestList" style="background-color: lightgreen;">

							</div>
						</td>
					</tr>
				</table>
			</div>
			<div dojoType="dijit.layout.AccordionPane"
				title="Result Entry Parameter" id='requlstParameterId'>
				<div id='resultEntryParameterListDiv'
					style="background-color: lightgreen;">
					<font color="#56A5EC"><b>No Elements Found</b></font>
				</div>
			</div>
			<div dojoType="dijit.layout.AccordionPane"
				title="Requisition Form Parameter" id='requisitionParameterId'>
				<div id='requisitionFormParameterListDiv'
					style="background-color: lightgreen;">
					<font color="#56A5EC"><b>No Elements Found</b></font>
				</div>
			</div>
			<div dojoType="dijit.layout.AccordionPane"
				title="Sample Form Parameter" id='samplParameterId'>
				<div id='sampleFormListDiv' style="background-color: lightgreen;">
					<font color="#56A5EC"><b>No Elements Found</b></font>
				</div>
			</div>
			<div dojoType="dijit.layout.AccordionPane" title="Column Property"
				id='columnPropertyId'>
				<div id="columnPropertiesDiv" style="background-color: lightgreen;"></div>
			</div>
			<div dojoType="dijit.layout.AccordionPane" title="Element Property"
				id='elementPropertyId'>
				<div id="elementListDiv" style="background-color: lightgreen"></div>
				<div id="elementPropertiesDiv" style="background-color: lightgreen;"></div>
			</div>
			<!-- <div dojoType="dijit.layout.AccordionPane" title="Miscellaneous">
			<div style="background-color: lightgreen;">
						<table width="6cm" ><tr>
									<td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('9')">Create Table</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('10')">Modify Table</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('11')">Delete Table</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('0')">Merge Cell</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('1')">Exchange Cells</a></td>
									<!-- </tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('2')">Delete cells</a></td> 
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('6')">Add Blank Column to Each Row</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('4')">Exchange Row</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('5')">Add Blank Row</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('3')">Delete Blank Row</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('12')">Write Label</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('14')">Delete Label</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('13')">Add HR</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('15')">Add Header</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('16')">Add Footer</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('17')">Add VO Values</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('18')">Delete Element</a></td>
									</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('14')">Delete Label</a></td>
									
								</tr>
								<tr>
								<td width="100%" colspan="2"><div id="modificationInfoDiv"   style="border: thick;border-color: black;display: none;"></div></td>
								</tr> 
					</table>
				</div>
			</div> -->
			<div dojoType="dijit.layout.AccordionPane" title="Style">
				<div class="moveable" dojoType="dojo.dnd.Moveable">
					<table width="100%" align="right">
						<tr>
							<td bgcolor="grey">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>color represents Test Parameters</td>
						</tr>
						<tr>
							<td bgcolor="orange">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>color represents Requisition Form Parameters</td>
						</tr>
						<tr>
							<td bgcolor="green">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>color represents Sample Form Parameters</td>
						</tr>
						<tr>
							<td bgcolor="lightgreen">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>color represents Previously Selected Column</td>
						</tr>
						<tr>
							<td bgcolor="pink">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>color represents Currently Selected Column</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div dojoType="dijit.layout.ContentPane" id='DesignerDiv'
			region="center" splitter="false">
			<h1>
				<font color="#56A5EC">RICH TEMPLATE DESIGNER</font>
			</h1>
		</div>
		<div dojoType="dijit.layout.ContentPane" id='funtionalityDiv'
			region="right" splitter="false" style="width: 80px;">
			<table width="100%">
				<tr>
					<td width="100%" colspan="2" class="tdfont"><input
						type="button" class="sub6" title="Create Table"
						onclick="selectedFunctionality('9')" value="Table  [+]" /></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Modify Table" -->
<!-- 						onclick="selectedFunctionality('10')" value="Table  [~]" /></td> -->
<!-- 				</tr> -->
				<tr>
					<td width="100%" colspan="2" class="tdfont"><input
						type="button" class="sub6" title="Delete Table"
						onclick="selectedFunctionality('11')" value="Table  [-]" /></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Merge Cell" -->
<!-- 						onclick="selectedFunctionality('0')" value="Cell   [M]" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Exchange Cells" -->
<!-- 						onclick="selectedFunctionality('1')" value="Cell   [X]" /></td> -->
<!-- 					</tr><tr><td width="100%" colspan="2" class="tdfont"><a href="#" onclick="selectedFunctionality('2')">Delete cells"/></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Add Blank Column to Each Row" -->
<!-- 						onclick="selectedFunctionality('6')" value="Column[+]" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Exchange Row" -->
<!-- 						onclick="selectedFunctionality('4')" value="  Row[X]  " /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Add Blank Row" -->
<!-- 						onclick="selectedFunctionality('5')" value="  Row[+]  " /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Delete Blank Row" -->
<!-- 						onclick="selectedFunctionality('3')" value="  Row[-]  " /></td> -->
<!-- 				</tr> -->
				<tr>
					<td width="100%" colspan="2" class="tdfont"><input
						type="button" class="sub6" title="Write Label"
						onclick="selectedFunctionality('12')" value=" Label[+] " /></td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="tdfont"><input
						type="button" class="sub6" title="Delete Label"
						onclick="selectedFunctionality('14')" value=" Label[-] " /></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Add HR" -->
<!-- 						onclick="selectedFunctionality('13')" value="  HR[+]  " /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Add Header" -->
<!-- 						onclick="selectedFunctionality('15')" value="Header[+]" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Add Footer" -->
<!-- 						onclick="selectedFunctionality('16')" value="Footer[-]" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" title="Add VO Values" -->
<!-- 						onclick="selectedFunctionality('17')" value="  VO[+]  " /></td> -->
<!-- 				</tr> -->
				<tr>
					<td width="100%" colspan="2" class="tdfont"><input
						type="button" class="sub6" title="Delete Element"
						onclick="selectedFunctionality('18')" value="Element[-]" /></td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="tdfont"><input
						type="button" class="sub6" title="Add Range Element"
						onclick="selectedFunctionality('19')" value="Range[+]" /></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" -->
<!-- 						title="Modify Width to columns in Table" -->
<!-- 						onclick="selectedFunctionality('20')" value="TableColumn[~]" /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100%" colspan="2" class="tdfont"><input -->
<!-- 						type="button" class="sub6" -->
<!-- 						title="Replace Template With Existing One" -->
<!-- 						onclick="selectedFunctionality('21')" value="Replace" /></td> -->


<!-- 				</tr> -->
				<tr>
					<td width="100%" colspan="2"><div id="modificationInfoDiv"
							style="border: thick; border-color: black; display: none;"></div></td>
				</tr>
			</table>

		</div>

	</div>
	<div dojoType="dijit.layout.BorderContainer" liveSplitters="false"
		design="bottom" region="bottom" id="mainSplit1"
		style="height: 40px; bottom: 0px;">
		<div dojoType="dijit.layout.ContentPane" region="center"
			splitter="false">
			<div align="center">
				<input type='button' class="sub5" name='acceptchange' value='Save'
					onClick="acceptChanges();" style="cursor: pointer;"> 
<!-- 					<input -->
<!-- 					type='button' class="sub5" name='viewReportGenerationType' -->
<!-- 					value='View Template' onClick="ShowSelectedPdf();" -->
<!-- 					style="cursor: pointer;"> -->
				<!-- <input type='button' class="sub5" name='ModifyPreviousTemplate' value='Modify Previous Template' onClick="modifyPrevioiusTemplate();" style="cursor:pointer;">
					<button dojoType="dijit.form.Button" id="accordButton" onClick="">Hello</button>
					 -->
			</div>
		</div>
	</div>
	<html:hidden name="DynamicReportTemplateFB" property="hmode" />
	<input type="hidden" name="addOrModifyMode" id='addOrModifyMode'
		value='' />
	<input type="hidden" name="templateSeqIdForTest"
		id='templateSeqIdForTest' value='' />
	<input type="hidden" name="headerHeight" id='headerHeight' value='' />
	<input type="hidden" name="footerHeight" id='footerHeight' value='' />
	<input type="hidden" name="headerWidth" id='headerWidth' value='' />
	<input type="hidden" name="footerWidth" id='footerWidth' value='' />
	<input type="hidden" name="pageHeight" id='pageHeight' value='' />
	<input type="hidden" name="pageWidth" id='pageWidth' value='' />
	<input type="hidden" name="templateSeqId" id='templateSeqId' value='' />
	<input type="hidden" name="templateName" id='templateName' value='' />

	<%-- <html:hidden name="DynamicReportTemplateFB" property='headerHeight' id='headerHeight' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='footerHeight' id='footerHeight' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='headerWidth' id='headerWidth' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='footerWidth' id='footerWidth' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='pageHeight' id='pageHeight' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='pageWidth' id='pageWidth' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='templateSeqId' id='templateSeqId' /> --%>
	<%-- 	<html:hidden name="DynamicReportTemplateFB" property='templateName' id='templateName' /> --%>




</body>
</html>