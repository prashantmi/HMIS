<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%
	List lstMenus=(List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL);	
%>

<script type="text/javascript">
$(document).ready( function (){
	dynamicDeskControlPanelGenerator.addControlPanelMenuDetails('1', 'divDeskMenuNonPat', 'NONPAT');
});

function showPatientList(obj)
{
	//alert(obj);
	
	//{	
		document.getElementById("frmDynamicDeskNonPatientCentric").height = "0px";
		document.getElementById("frmDynamicDeskPatientCentric").height = "0px";
		document.getElementById("frmDynamicDeskList").height = "440px";
	//}
	//else
	/*{	
		var frmLeft=parent.document.getElementById("frmDynamicDeskList");
		document.getElementById("frmDynamicDeskList").height = "0px";		
		parent.document.getElementById("frmDynamicDeskPatientCentric").height = "0px";
		document.getElementById("frmDynamicDeskNonPatientCentric").height = "0px";
	}*/
}

function showConsultationInbox(obj)
{
		//alert("Consult Inbox"+obj);
		var mode= "CONSULTATIONINBOX";
		var hmode= "NEW";
		var url = "/HISInvestigationG5/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+mode+"&hmode="+hmode;
		//alert(url);
		document.getElementById("frmDynamicDeskNonPatientCentric").src = url;
		document.getElementById("frmDynamicDeskList").height = "0px";
		document.getElementById("frmDynamicDeskPatientCentric").height = "0px";
		document.getElementById("frmDynamicDeskNonPatientCentric").height = "440px";
		
}

</script>

</head>

<body style="margin: 0px;">

	<div id="divDeskMenuPatList" class="div-table width100" style="margin: 0px;">
		<div class="div-table width100" style="margin: 0px;">
			<div class="div-table-row ">
				<div class='div-table-col width100'>
					<div class='div-table-col boxVerySmallIcon' title="Patient List" onclick="addTab('Patient List','DESKPATLIST','0','-1');" >
						<div class='div-table-col wrapper rounded innerBigboxVerySmallIcon' onmouseover='changeBGColor(this);' onmouseout='resetBGColor(this);' style='background-color: #1277B5'>
							<img src='/HIS/hisglobal/images/icons/icon-patlist.png' class='icon-imageVerySmallIcon' onmouseover='changeSize(this);' onmouseout='resetSize(this);' />
							<br>
			   			</div>
				   		<!-- <div class='div-table-col innerSmallboxVerySmallIcon'>
				   				Patient List
				   		</div> -->
					</div>
				</div>
			</div>
			<div style='height:5px;'></div><div class='title' style='height:5px;'></div>
	<%--<logic:present name="<%=DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL%>">
			<logic:iterate id="menu" name='<%=DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL%>' type="hisglobal.vo.DeskDetailVO">
				<div class="div-table-row ">
					<div class='div-table-col width100'>
						<div class='div-table-col boxVerySmallIcon' onclick="addTab('<%=menu.getDeskMenuName()%>','<%=menu.getDeskUrl()%>','<%=menu.getDeskMenuId()%>','<%=menu.getDeskMenuType()%>');" title="<%=menu.getDeskMenuName()%>">
							<div class='div-table-col wrapper rounded innerBigboxVerySmallIcon' onmouseover='changeBGColor(this);' onmouseout='resetBGColor(this);'>
								<img src='<%=menu.getDeskMenuImg()%>' class='icon-imageVerySmallIcon' onmouseover='changeSize(this);' onmouseout='resetSize(this);' />
								<br>
			   				</div>
			   				<!-- <div class='div-table-col innerSmallboxVerySmallIcon'>
			   					<%=menu.getDeskMenuName()%>
			   				</div> -->
			   			</div>
			   		</div>
				</div>
			</logic:iterate>
		</logic:present> --%>
		</div>
	</div>
	<div id="divDeskMenuNonPat" class="div-table width100" style="margin: 0px;">
	</div>
	<div id="divDeskMenuPatLeft" class="div-table width100" style="margin: 0px;">
	</div>

</body>
</html>