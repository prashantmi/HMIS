
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/> 
<his:css src="/mrd/js/tabview.css"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript"> 
function submitForm(mode){
	//document.forms[0].hmode=mode
	///document.forms[0].submit()
	//alert(window.top.document.getElementById("frmMain"))//f2"))	
	showMenuFrame1();
	var departmentUnitCode=<%=session.getAttribute("departmentUnitCode")%>
	//window.top.document.getElementById("f2").src
	window.top.document.getElementById("frmMain").src="/HISClinical/mrd/emrDesk.cnt?isNew=1&departmentUnitCode=" + departmentUnitCode;
	
}
function submitDesk(mode)
{
	//alert("in cancel")
	//window.top.document.getElementById("f2").src
	//window.top.document.getElementById("frmMain").src="/HISClinical/opd/opdDesk.cnt";
	//document.forms[0].action="/HISClinical/opd/opdDesk.cnt"
	///document.forms[0].mode.value=mode;
	//document.forms[0].submit();
	self.close();
}


function cancelFunc()
{
	window.parent.parent.closeTab();
}

function cancelFuncDesk()
{
	
	window.parent.close();
}

function showMenuFrame1()
{	
	if(window.XMLHttpRequest) // Mozilla
	{
		window.top.document.getElementById("fs2").cols = "230,*";
		window.top.document.getElementById("f7").contentDocument.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}
	else
	{
		window.top.document.getElementById("fs2").cols = "230,*";
		window.top.document.getElementById("f7").Document.getElementById("Image").src="../hisglobal/images/arrsingle-left.png";
	}	

}
function closeEMRDeskPopup()
{
	//alert("Desk");
	
	var frm=parent.document.getElementById("emrMainFrameId");
	//showData(frm.ownerDocument);
	var frmWindow= null;	
	 
	if(window.XMLHttpRequest) // Mozilla
	{
		frmWindow=frm.ownerDocument.defaultView;
 	} 
 	else if (window.ActiveXObject)
 	{
 		frmWindow=frm.ownerDocument.parentWindow;
	}
	frmWindow.close();
}

function showData(obj)
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
}

</script>
<script type="text/javascript">  
/*
function showEMRDetails(id,tabId)
{
	var deskFrame=parent.document.getElementById("treeNodeFrameId");
	var doc='';
	
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	var frm=doc.forms[0];  
	var selectedVisit=frm.selectedVisit.value;
	var selectedUnit=frm.selectedUnit.value;
	var selectionCriteria=frm.selectionCriteria.value;
	var selectEpisodeCode=frm.episodeCode.value;
	var selectedPatAdmNo=frm.selectedPatAdmNo.value;
	// alert("selectedUnit "+selectedUnit)
 // 	alert("selectedVisit "+selectedVisit)
	var emrDoc='';
	var deskFrameEMRDetails=parent.document.getElementById("emrDetailsId");
	if(window.XMLHttpRequest)
		emrDoc=deskFrameEMRDetails.contentDocument;
	else if (window.ActiveXObject)
		emrDoc=deskFrameEMRDetails.Document;
		
		var emrFrm=emrDoc.forms[0];
		//alert("hmode emr "+emrFrm.hmode.value)
		var index=parseInt(id);
		//alert("index "+index)
		<%String str=(String)session.getAttribute("emrTabs");%>
		//alert("emrtabs "+'<%=str%>')
		var tabUrl='<%=str%>';
		var tabUrlArray=tabUrl.split('^');
		var url=tabUrlArray[index];
		//alert("url "+url)
		
		var selectedVisitArray=selectedVisit.split('$');
		emrFrm.hmode.value=url;
		//alert("emrFrm.hmode.value "+emrFrm.hmode.value)
		
		emrFrm.sendVisitDetail.value=selectedVisit;
		emrFrm.departmentUnitCode.value=selectedVisitArray[0];
		emrFrm.episodeCode.value=selectedVisitArray[1];
		emrFrm.episodeVisitNo.value=selectedVisitArray[2];
		emrFrm.selectedUnit.value=selectedUnit;
		emrFrm.selectedPatAdmNo.value=selectedPatAdmNo;
		emrFrm.tabId.value=tabId;
		emrFrm.selectedTab.value=index;
		// alert("makeNodeIpdWise  "+emrFrm.selectedPatAdmNo.value)
		if(selectionCriteria=="E")
		{
			emrFrm.episodeCode.value=selectEpisodeCode;
		} 
		// alert("sendVisitDetail "+emrFrm.sendVisitDetail.value)
		//alert("emrFrm.hmode.value "+emrFrm.hmode.value)
		emrFrm.selectionCriteria.value=selectionCriteria; 
		if( (url=="INTAKEOUTTAKE") || (url=="PROGRESSNOTES") || (url=="VITALS") )
		{
			if( (selectionCriteria=="A") || (selectionCriteria=="I") )
			{
				emrFrm.submit();
			}  
			else 
			{
				alert("Please Select Admission Number in Ipd Wise Option or All");
			}
		}
		else
		{
			emrFrm.submit();
		} 
		
		
	
}

function showMoreTabs(mode){
	if(mode==1){
		//alert("plus")
		document.getElementsByName("tabMode")[0].value=1
		document.getElementById("eprMoreTabId").style.display="block"
		document.getElementById("plusId").style.display="none"
		document.getElementById("minusId").style.display="block"
	}
	else{
		//alert("minus")
		document.getElementsByName("tabMode")[0].value=2
		document.getElementById("eprMoreTabId").style.display="none"
		document.getElementById("plusId").style.display="block"
		document.getElementById("minusId").style.display="none"
	}
}

function showMoreTabs1(mode){
	if(mode==1){
		document.getElementById("eprMoreTabIdVer").style.display="block"
		document.getElementById("upArrowId").style.display="none"
		document.getElementById("downArrowId").style.display="block"
	}
	else{
		//alert("minus")
		document.getElementById("eprMoreTabIdVer").style.display="none"
		document.getElementById("upArrowId").style.display="block"
		document.getElementById("downArrowId").style.display="none"
	}
}


function callOnload()
{ 
	<%str=(String)session.getAttribute("emrTabs");%>
		//alert("emrtabs "+'<%=str%>')
		var tabUrl='<%=str%>';
		var tabUrlArray=tabUrl.split('^');
		for(i=0;i<tabUrlArray.length;i++)
		{
			var tempTdId=document.getElementById("tdId"+i);
			//3tempTdId.style.borderStyle='groove';
		}
	var id=document.getElementsByName("selectedTab")[0].value;
	// alert("ID TABS "+id) 
	var tdId="tdId"+id;
	var obj=document.getElementById(tdId);
	//2obj.style.borderStyle='inset';
	// alert("obj "+obj)
	 //obj.style.backgroundColor ='#996666';
	 //obj.style.backgroundColor ='#EC9B53';
	 //obj.style.backgroundColor ='#C58B8B';
	//1 obj.style.backgroundColor ='#C58B8B';
	 obj.style.color ='black';
	 obj.style.backgroundImage="url('/HISClinical/hisglobal/images/LightStrip20.png')";
	 
	//alert("color "+obj.style.backgroundColor)
	
	var tabMode=document.getElementsByName("tabMode")[0].value
	//alert(tabMode)
	showMoreTabs(tabMode)
	
	//parent.document.getElementById("footerId").src="../mrd/transaction/emrFooter.jsp"
	
}  

 function setFocus(mode,index){
	if(mode==1){
		document.getElementById("tdId"+index).style.color="blue"
		//document.getElementById("tdId"+index).style.backgroundImage="url('/HISClinical/hisglobal/images/blue_back.gif')";
		
	}	
	else{
		document.getElementById("tdId"+index).style.color="black"
		//document.getElementById("tdId"+index).style.backgroundImage="url('/HISClinical/mrd/js/style01_4_o_back.gif')";
	}
	
}  

*/
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
	<%-- 
	<table width="100%" border="0" cellspacing="0" >
				<tr>
				<td>
				<div align="left" style="background-color: #E0EBEB" >
				  <logic:iterate id="emrTabMstVO" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="hisglobal.vo.EprTabMasterVO" offset="8">
						<span class="tabFront" onmouseover="setFocus(1,<%=String.valueOf(index)%>)" onmouseout="setFocus(2,<%=String.valueOf(index)%>)" 
							id="tdId<%=String.valueOf(index)%>"
							onclick="showEMRDetails('<%=String.valueOf(index.intValue())%>',<%=emrTabMstVO.getTabId() %>);" >
						<img style="height: 16" src="/HISClinical/mrd/js/style01_4_o_left.gif"/>
						<bean:write name="emrTabMstVO" property="tabName"/>
						<img style="height: 16" src="/HISClinical/mrd/js/style01_4_o_right.gif"/>
						</span>
				</logic:iterate>
				</div>
				</td>
		</tr>
		</table>
	--%>
	<%//String callFrom=session.getAttribute("") %>
	<html:hidden name="EmrCommonDeskFB" property="callFrom" value="${callFrom}"/>
	<body>
		<his:ButtonToolBarTag>
			<c:if test="${callFrom=='DIRECT'}">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor:pointer" onkeypress="if(event.keyCode==13) cancelFunc();"  onclick ="cancelFunc();">
			</c:if>
			<c:if test="${callFrom=='DESK'}">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor:pointer" onkeypress="if(event.keyCode==13) cancelFuncDesk();"  onclick ="cancelFuncDesk();">
			</c:if>
		</his:ButtonToolBarTag>
	</body>
</html>	 
