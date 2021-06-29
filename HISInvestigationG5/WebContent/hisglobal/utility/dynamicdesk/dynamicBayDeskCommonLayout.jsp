<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<his:css src="/hisglobal/css/hisStyle.css"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />

<script>
var dynaDeskRowsCount=5;
var dynaDeskColsCount=3;
var initialDynaDeskRowsWidths = null;
var dynaDeskRowsWidths = null; //[7,3,80,3,7];
var dynaDeskRowsWidthsStyle = null; //['P','P','P','P','P']; // 'P' for Percentge, 'F' for Fixed, 'PX' in pixels (px)
var initialDynaDeskColsWidths = [15,70,15];
var dynaDeskColsWidths = [15,70,15];

function getSetRowsWidth()
{
	var rowsWidths = document.getElementById("frmsetRows").rows;
	var arrRowsWidths = rowsWidths.split(",");
	if(arrRowsWidths.length!=5)
	{
		alert("Wrong Rows Span for the Desk.. Please Correct it");
		return;
	}
	initialDynaDeskRowsWidths = new Array(arrRowsWidths.length);
	dynaDeskRowsWidths = new Array(arrRowsWidths.length);
	dynaDeskRowsWidthsStyle = new Array(arrRowsWidths.length);
	for(var i=0;i<arrRowsWidths.length;i++)
	{
		var wdt = trimData(arrRowsWidths[i]), wid=null;
		if(wdt.indexOf("%")>-1 && wdt.indexOf("%")==(wdt.length-1))
		{
			dynaDeskRowsWidthsStyle[i]='P';
			wdt = wdt.replace("%","");
		}
		else if( (i==0 || i==4) && wdt.toUpperCase().indexOf("PX")>-1 && wdt.toUpperCase().indexOf("PX")==(wdt.length-2))
		{
			dynaDeskRowsWidthsStyle[i]='PX';
			wdt = wdt.replace("px","");wdt = wdt.replace("Px","");
			wdt = wdt.replace("pX","");wdt = wdt.replace("PX","");
		}
		else if(i==0 || i==4)
		{
			dynaDeskRowsWidthsStyle[i]='F';
		}
		try
		{
			wid = trimData(trimLeftZero(parseInt(wdt)));
		}
		catch(e)
		{
			alert("Wrong Rows Span for the Desk.. Please Correct it");
			return;
		}
		if(wid==null || (typeof wid).toUpperCase()!='NUMBER' || wid<0)
		{
			alert("Wrong Rows Span for the Desk.. Please Correct it");
			return;
		}
		initialDynaDeskRowsWidths[i] = wid;
		dynaDeskRowsWidths[i] = wid;
	}
	setDynamicDeskRows();
}

/*function setRowsWidth(widths)
{
	if(widths.length && widths.length==5)
	{
		var total=0;
		for(var i=0;i<5;i++) total+=widths[i];
		if(total==100)
		{
			dynaDeskRowsWidths=widths;
			setDynamicDeskRows();
		}			
	}
	return false;
}*/

function checkGetSetRowsWidth()
{
	if(initialDynaDeskRowsWidths==null || dynaDeskRowsWidths==null || dynaDeskRowsWidthsStyle==null)
		getSetRowsWidth();
}

function setDynamicDeskRows()
{
	var rr = "";
	if(initialDynaDeskRowsWidths==null || dynaDeskRowsWidths==null || dynaDeskRowsWidthsStyle==null)
		getSetRowsWidth();
	for(var i=0;i<5;i++)
	{
		rr+=dynaDeskRowsWidths[i]
		if(dynaDeskRowsWidthsStyle[i]=='P')
			rr+="%,";
		else if(dynaDeskRowsWidthsStyle[i]=='PX')
			rr+="px,";
		else if(dynaDeskRowsWidthsStyle[i]=='F')
			rr+=",";
	}
	rr=rr.substring(0,rr.length-1);
	document.getElementById("frmsetRows").rows = rr;
}

function setDynamicDeskCols()
{
	var rr = "";
	for(var i=0;i<3;i++) rr+=dynaDeskColsWidths[i]+"%,";
	rr=rr.substring(0,rr.length-1);
	document.getElementById("frmsetCols").cols = rr;
}

function removeLeftMenu()
{
	dynaDeskColsWidths[1]+=dynaDeskColsWidths[0];
	dynaDeskColsWidths[0]=0;
	setDynamicDeskCols();
}

function removeRightMenu()
{
	dynaDeskColsWidths[1]+=dynaDeskColsWidths[2];
	dynaDeskColsWidths[2]=0;
	setDynamicDeskCols();
}

function removeTopMenu()
{	
	checkGetSetRowsWidth();
	dynaDeskRowsWidths[2]+=dynaDeskRowsWidths[1];
	dynaDeskRowsWidths[1]=0;
	setDynamicDeskRows();
}

function removeBottomMenu()
{
	checkGetSetRowsWidth();
	dynaDeskRowsWidths[2]+=dynaDeskRowsWidths[3];
	dynaDeskRowsWidths[3]=0;
	setDynamicDeskRows();
}

function hideLeftMenu()
{
	dynaDeskColsWidths[1]+=dynaDeskColsWidths[0]-1;
	dynaDeskColsWidths[0]=1;
	setDynamicDeskCols();
}

function hideRightMenu()
{
	dynaDeskColsWidths[1]+=dynaDeskColsWidths[2]-1;
	dynaDeskColsWidths[2]=1;
	setDynamicDeskCols();
}

function hideTopMenu()
{
	checkGetSetRowsWidth();
	dynaDeskRowsWidths[2]+=dynaDeskRowsWidths[1]-2;
	dynaDeskRowsWidths[1]=2;
	setDynamicDeskRows();
}

function hideBottomMenu()
{
	checkGetSetRowsWidth();
	dynaDeskRowsWidths[2]+=dynaDeskRowsWidths[3]-2;
	dynaDeskRowsWidths[3]=2;
	setDynamicDeskRows();
}

function showLeftMenu()
{
	if(dynaDeskColsWidths[0]!=initialDynaDeskColsWidths[0])
	{
		dynaDeskColsWidths[1]+=dynaDeskColsWidths[0]-initialDynaDeskColsWidths[0];
		dynaDeskColsWidths[0]=initialDynaDeskColsWidths[0];
		setDynamicDeskCols();
	}
}

function showRightMenu()
{
	if(dynaDeskColsWidths[2]!=initialDynaDeskColsWidths[2])
	{
		dynaDeskColsWidths[1]+=dynaDeskColsWidths[2]-initialDynaDeskColsWidths[2];
		dynaDeskColsWidths[2]=initialDynaDeskColsWidths[2];
		setDynamicDeskCols();
	}
}

function showTopMenu()
{
	checkGetSetRowsWidth();
	if(dynaDeskRowsWidths[1]!=initialDynaDeskRowsWidths[1])
	{
		dynaDeskRowsWidths[2]+=dynaDeskRowsWidths[1]-initialDynaDeskRowsWidths[1];
		dynaDeskRowsWidths[1]=initialDynaDeskRowsWidths[1];
		setDynamicDeskRows();
	}
}

function showBottomMenu()
{
	checkGetSetRowsWidth();
	if(dynaDeskRowsWidths[3]!=initialDynaDeskRowsWidths[3])
	{
		dynaDeskRowsWidths[2]+=dynaDeskRowsWidths[3]-initialDynaDeskRowsWidths[3];
		dynaDeskRowsWidths[3]=initialDynaDeskRowsWidths[3];
		setDynamicDeskRows();
	}
}

window.onload = function ()
{
	getSetRowsWidth();
	// For Hiding User Mgmt Menus and Spanning Desk to Full Screen
	hideMenuFrame();
}
</script>


<%
	String rowsSpan ="7%,3%,80%,3%,7%";
	if(session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN) != null)
		rowsSpan = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ROWS_SPAN);
	System.out.println("rowsSpan>>>>>>>"+rowsSpan);
%>
<frameset id="frmsetRows" rows="<%=rowsSpan%>" border="0" bordercolor="#FFFFFF" framespacing="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

	<frame id="frmDynamicDeskHeader" name="frmDynamicDeskHeader" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/header.cnt" 
		scrolling="no" noresize="noresize" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frame id="frmDynamicDeskTopMenus" name="frmDynamicDeskTopMenus" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/menus.cnt?deskMode=TOP" 
		scrolling="auto" noresize="noresize" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frameset id="frmsetCols" cols="15%,70%,15%" border="0" bordercolor="#FFFFFF" framespacing="0" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">

		<frame id="frmDynamicDeskLeftMenus" name="frmDynamicDeskLeftMenus" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/menus.cnt?deskMode=LEFT" 
			scrolling="auto" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

		<frame id="frmDynamicDeskCenter" name="frmDynamicDeskCenter" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/centerBayDesk.cnt" 
			scrolling="auto" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

		<frame id="frmDynamicDeskRightMenus" name="frmDynamicDeskRightMenus" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/menus.cnt?deskMode=RIGHT" 
			scrolling="auto" frameborder="0" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	</frameset>
	
	<frame id="frmDynamicDeskBottomMenus" name="frmDynamicDeskBottomMenus" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/menus.cnt?deskMode=BOTTOM" 
		scrolling="auto" noresize="noresize" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">

	<frame id="frmDynamicDeskFooter" name="frmDynamicDeskFooter" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/footer.cnt" 
		scrolling="no" noresize="noresize" marginwidth="0" marginheight="0" border="0" topmargin="0" leftmargin="0">
</frameset>
