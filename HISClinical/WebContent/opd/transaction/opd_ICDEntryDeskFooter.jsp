<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
 <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<script type="text/javascript">

var IsICDDataLoaded = false;

window.onload = function()
{
	// ICD disease Code List
	var elemCodes = document.getElementsByName("diseaseCodeList")[0];
	// Fetch ICD Codes
	var lst = getICDCodesList();
	if(lst !=null)
	{
		for(var i=0; i<lst.length; i++)
		{
			var op=document.createElement("option");
			op.value=lst[i].strICDCode;
			op.innerHTML=lst[i].strICDCode;
			elemCodes.appendChild(op);
		}
	}
	IsICDDataLoaded = true;
}

// Gettting List of ICD Codes 
function getICDCodesList()
{
	var flg = false;
	var objICDCodeList = null;
	var _mode = "AJX_G_ICDCODES";
	var objXHR = {url: "/HISClinical/opd/icdEntryForm.cnt?hmode="+_mode, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objICDCodeList = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objICDCodeList == 'undefined' || objICDCodeList==null || objICDCodeList=="" || objICDCodeList.length==0)
            	//alert("No Meal Time found for Meal Type");
            //alert(error+"Error while populating Meal Time Information");
            objICDCodeList = null;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return objICDCodeList;
}

</script>

<div id="divDiseaseCodeList" style="display: none; position: absolute;">
	<select name="diseaseCodeList" id="diseaseCodeList">
	</select>
</div>

