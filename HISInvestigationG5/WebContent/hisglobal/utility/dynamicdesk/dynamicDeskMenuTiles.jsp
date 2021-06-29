<!-- 
/**
 * @author CDAC
 */
-->

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html lang="en">
<head>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/HISInvestigationG5/investigation/js/dojotoolkit/dojo/resources/dojo.css"/>
<link rel="stylesheet" href="/HISInvestigationG5/investigation/js/dojotoolkit/dijit/themes/tundra/tundra.css"/>


<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>

<script type="text/javascript" src="/HISInvestigationG5/investigation/js/dojotoolkit/dojo/dojo.js"	djConfig="parseOnLoad: true"> </script>

<his:javascript src="/opd/js/opd_desk.js" />
<his:javascript src="/operationTheatre/js/showmodalfunctionality.js" />
<his:javascript src="/operationTheatre/js/showmodalfunctionality.js" />
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<!-- Desk tab 
 <style>
#allergies { width: 150px; height: 150px; padding: 0.5em; }
#allergies h3 { text-align: center; margin: 0; }
</style>-->
	
<script>
$(function() {
//$( "#allergies" ).resizable();
$( "#deskTabs" ).accordion();
});
</script>
<script type="text/javascript">
dojo.require("dijit.Dialog");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.Toolbar");
dojo.require("dijit.form.Button");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.AccordionPane");
</script>
<script>
function deskMenuTileOnLoad(event)
{
  	var frm=document.getElementsByTagName('*');
	for(var i in frm)
	{
		if(frm[i] && frm[i].id && frm[i].id.substr(0,11)=='tabSummary#')
		{
			var obj=document.getElementById(frm[i].id);
			var arr = obj.id.split('#');
			var tabSummary = getTabSummary(arr[1],arr[2]);
			if(tabSummary!=null)
			{
				//alert(tabSummary);
				var summHTML = "";
				for(var i=0;i<tabSummary.data.length;i++)
				{
					summHTML += tabSummary.data[i].summary;
					if(i!=tabSummary.data.length-1)	summHTML +="<br>";
				}			
				obj.innerHTML = summHTML;
			}
		}
	}
	$( "#deskTabs" ).resize();	
}
var  diag =null;

function openTab(deskMenuId, deskMenuURL)
{
	//alert("Open Dialog");
	var url = "/HISInvestigationG5/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+deskMenuURL+"&deskMenuId="+deskMenuId+"&hmode=NEW";
	if(dijit.byId('tabPopup#'+deskMenuId)==null)
	{
		//alert('tabPopup#'+deskMenuId);
		 diag = new dijit.Dialog({
		 		title : "",
		 		content: "<iframe src='"+url+"' id='frmDynamicDeskMenuPopup' style='width: 1100px;height: 1000px'></iframe>",
		 		onCancel : closeDialog,
		 		draggable : true
		 		});
		 diag.id='tabPopup#'+deskMenuId;
	}
	else
	{
		diag=dijit.byId('tabPopup#'+deskMenuId);
	}
	diag.show();
}

function closeDialog()
{
	if(diag!=null)
	{
		//diag.destroyRecursive();
		diag.hide();
	}
}

/*  AJAX Functions */
function getTabSummary(deskMenuId, deskMenuURL)
{
	var flg = false;
	var objTabSummary = null;
	var urlNew = "/HISInvestigationG5/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+deskMenuURL+"&deskMenuId="+deskMenuId+"&hmode=AJX_G_SUMMARY";
	var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			//alert("Data"+data.header +" "+data.counts);
			objTabSummary = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
            	//alert("No  Type");
            //alert(error+"Error while populating Information");
            objTabSummary = {data:[{summary:'Patient Diagnosis Detail'}]};
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return objTabSummary;
}

</script>

</head>

<body onload="deskMenuTileOnLoad(event)">

<!-- Starting Patient Details TAB div -->
<div id="divMenuTile">
	<table width="100%" height="100" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td width="34%" valign="top">
				<div id="deskTabs" class="ui-widget-content" style="vertical-align: top;">
					<h3 class="ui-widget-header" onclick="openTab(1,'DESKDIAGNOSIS')">Diagnosis</h3>
					<div id="tabSummary#1#DESKDIAGNOSIS"></div>
				</div>
			</td>
			<td width="30%" align="center">	
				<div id="investigation" class="ui-widget-content">
				<h3 class="ui-widget-header">Investigation</h3>
				<b> having allergies</b>
				</div>
			</td>
			<td width="34%" align="center">
				<div id="diagnosis" class="ui-widget-content">
				<h3 class="ui-widget-header">Allergies</h3>
				<b> having allergies</b>
				</div>
			</td>
		</tr>
		
		<tr>
			<td width="35%" align="center" onclick="goForDetail()">
				<div id="chronicDiseasis" class="ui-widget-content">
				<h3 class="ui-widget-header">Chronic Diseasis</h3>
				<b> having allergies</b>
				</div>
			</td>
			<td width="30%" align="center">	
				<div id="anc" class="ui-widget-content">
				<h3 class="ui-widget-header">ANC Details</h3>
				<b> having allergies</b>
				</div>
			</td>
			<td width="35%" align="center">
				<div id="examination" class="ui-widget-content">
				<h3 class="ui-widget-header">Examination</h3>
				<b> having allergies</b>
				</div>
			</td>
		</tr>
	</table>
</div>

<!-- Dialog boxes -->

 <div id="div1" dojoType="dijit.Dialog"></div>

</body>
</html>