<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/> 
<his:javascript src="/hisglobal/js/util.js"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
$(document).ready( function (){
	dynamicDeskControlPanelGenerator.addControlPanelMenuDetails('1', 'divDeskMenuNonPat', 'NONPAT');
	deskPatInfo = getDeskPatInfoStr();
	updateDeskMenuState("1", deskPatInfo); 	// For NON Patient Centric List Only---
});

function showPatientList(obj)
{
	//alert(obj);
	//var patList="http://localhost:8080/HISClinical/hisglobal/utility/dynamicdesk/enterNew.cnt?deskType=1#tabs-1"
	//if(obj==(patList))
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
		//document.getElementById('frmDynamicDeskNonPatientCentric').src="/HISClinical/hisglobal/images/slide2.png";
	}*/
}

function showConsultationInbox(obj)
{
		//alert("Consult Inbox"+obj);
		var mode= "CONSULTATIONINBOX";
		var hmode= "NEW";
		var url = "/HISClinical/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+mode+"&hmode="+hmode;
		//alert(url);
		document.getElementById("frmDynamicDeskNonPatientCentric").src = url;
		document.getElementById("frmDynamicDeskList").height = "0px";
		document.getElementById("frmDynamicDeskPatientCentric").height = "0px";
		document.getElementById("frmDynamicDeskNonPatientCentric").height = "440px";
		
}

</script>

</head>

<body style="margin: 0px;" onload="onMenuLoad1()">

<%-- 	<div id="divDeskMenuPatList" class="div-table width100" style="margin:0px;">
		<div class="div-table width100" style="margin:-8px;">
			<!-- <div class="div-table-row ">
				<div class='div-table-col width100'>
					<div class='boxVerySmallIcon' align="center" title="Patient List" onclick="addTab('Patient List','DESKPATLIST','0','-1');" >
						<div class='div-table-col wrapper rounded innerBigboxVerySmallIcon' onmouseover='changeBGColor(this);' onmouseout='resetBGColor(this);' style='width:72px; background-color: #FFFFFF'>
							<img src='/HIS/hisglobal/images/icons/icon-patlist.png' class='icon-imageVerySmallIcon' onmouseover='changeSize(this);' onmouseout='resetSize(this);' />
							<br>
			   			</div>
				   		<div class='div-table-col innerSmallboxVerySmallIcon'>
				   				Patient List
				   		</div>
					</div>
				</div>
			</div>
			<div style='height:5px;'></div><div class='title' style='height:5px; width:80px'></div> -->
	<logic:present name="<%=DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL%>">
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
		</logic:present>
		</div>
	</div> --%>
	<div id="divDeskLeftMenuPat" style="margin-top: 0px; width: 100%;" align="center">
		<div id="divDeskMenuNonPat" class="div-table width100" style="margin: 0px;">
		</div>
		<div id="divDeskMenuPatLeft" class="div-table width100" style="margin: 0px;">
		</div>
	</div>

<script type="text/javascript">

//added by manisha date: 16.1.2017
function onMenuLoad1()
{
	 var ht=Math.round($(parent.window).innerHeight()) + "px"; //140px- 134px || 146px  ////changed on 29-09-2015 from 146px to 176px due to TELANGANA Project Menu's Issue
    //alert('slimscroll on right menu:- '+ht);
    $('#divDeskMenuPat').slimscroll({
  
  //added by Akash Singh for changing rail color dated on 04-03-2015
    	color: '#0ff',
    	opacity: 1,
      
      railDraggable: true,
      height: ht
    });
    var ht1=Math.round($(parent.window).innerHeight()-250) + "px";
    $('#divDeskRightMenuPat').slimscroll({
      	
	     //added by Akash Singh for changing rail color dated on 04-03-2015
	      	color: '#0ff',
	      	opacity: 1,
	       
	        railDraggable: true,
	        height: ht1
	      });
    var ht1=Math.round($(parent.window).innerHeight()-250) + "px";
    $('#divDeskLeftMenuPat').slimscroll({
      	
	     //added by Akash Singh for changing rail color dated on 04-03-2015
	      	color: '#0ff',
	      	opacity: 1,
	       
	        railDraggable: true,
	        height: ht1
	      });  
}


/* //innerHeight changed by warish 07-aug-2017 priveous innerHeight is 256 and after changeHeight is -400Px
$(function(){              
    var ht=Math.round($(parent.window).innerHeight()-400) + "px"; // 256px- 250px || 262px  //changed on 29-09-2015 from 262px to 227px due to TELANGANA Project Menu's Issue
    //alert("slimScroll on Left"+ht);       
      $('#divDeskLeftMenuPat').slimscroll({
      	
     //added by Akash Singh for changing rail color dated on 04-03-2015
      	color: '#0ff',
      	opacity: 1,
       
        railDraggable: true,
        height: ht
      });
    }); */

</script>
</body>
</html>