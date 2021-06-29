<%-- <%try{ %> --%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%> 
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/> 
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/mrd/js/tabview.css"/>
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/mrd/js/ehrData.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<%@page import="mrd.MrdConfig"%>
<%@page import="ehr.EHRConfig" %>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<script type="text/javascript"> 
 
 /*
function showAllImagesPopUp(e)
{
	 openPopup('<his:path src="/mrd/emrDesk.cnt?hmode=ALLIMAGES&imageIndex=0"/>',e,500,900);
}
*/

function showHideDivision(id,imageShowId,mageHideId)
{
	// alert("show "+document.getElementById(id).style.display)
	 
	if( (document.getElementById(id).style.display)=="none")
	{
	// alert("none  ccc")
		document.getElementById(imageShowId).style.display="none";
		document.getElementById(mageHideId).style.display="block";
		document.getElementById(id).style.display="block";
	}
	else
	{
	// alert("block  ccc")
		document.getElementById(mageHideId).style.display="none";
		document.getElementById(imageShowId).style.display="block";
		document.getElementById(id).style.display="none";
	}
}


function showEMRDetails(elemTD,id,tabId)
{
	if(elemTD.title=="Inactive") return;
	var deskFrame=parent.document.getElementById("treeNodeFrameId");
	var doc='';
	
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	var frm=doc.forms[0]; 
	var selectedVisit=frm.episodeVisitNo.value;
	//alert("selectedVisit"+selectedVisit);
	var selectedUnit=frm.selectedUnit.value;
	var selectionCriteria=frm.selectionCriteria.value;
	var selectEpisodeCode=frm.episodeCode.value;
	var selectedPatAdmNo=frm.selectedPatAdmNo.value;
	var hosCode=frm.hosCode.value;
	 //alert("selectedUnit "+selectedUnit)
 	//alert("selectedVisit "+selectedVisit)
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
		emrFrm.episodeCode.value=selectEpisodeCode;
		emrFrm.episodeVisitNo.value=selectedVisit;
		//alert("episodeVisitNo" +emrFrm.episodeVisitNo.value);
		emrFrm.selectedUnit.value=selectedUnit;
		emrFrm.selectedPatAdmNo.value=selectedPatAdmNo;
		emrFrm.hosCode.value=hosCode;
		emrFrm.tabId.value=tabId;
		emrFrm.selectedTab.value=index;
		 //alert("makeNodeIpdWise  "+emrFrm.selectedPatAdmNo.value)
		if(selectionCriteria=="E")
		{
			emrFrm.episodeCode.value=selectEpisodeCode;
			emrFrm.selectedUnit.value=selectEpisodeCode.substring(0,5) ;
		} 
		 //alert("sendVisitDetail "+emrFrm.sendVisitDetail.value)
		//alert("emrFrm.hmode.value "+emrFrm.hmode.value)
		emrFrm.selectionCriteria.value=selectionCriteria; 
		if( (url=="INTAKEOUTTAKE") || (url=="PROGRESSNOTES") || (url=="VITALS") )
		{
			if( (selectionCriteria=="All") || (selectionCriteria=="A") || (selectionCriteria=="H") || (selectionCriteria=="I"))
			{
				emrFrm.submit();
			}  
			else 
			{
				alert("Please Select Ipd Wise Option,Admission Number,Hospital Name or All");
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
		//document.getElementById("eprMoreTabId").style.display="block"
		document.getElementById("plusId").style.display="none"
		document.getElementById("minusId").style.display="block"
	}
	else{
		//alert("minus")
		document.getElementsByName("tabMode")[0].value=2
		//document.getElementById("eprMoreTabId").style.display="none"
		document.getElementById("plusId").style.display="block"
		document.getElementById("minusId").style.display="none"
	}
}

/*
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
*/
</script>

<%

%>
<script type="text/javascript">   


var emrTabArr = '<%=(String)session.getAttribute("emrTabs")%>';

var currentTabId = '';
var deskFrame=parent.document.getElementById("treeNodeFrameId");
var req;
if(window.XMLHttpRequest)
	req = 1;
else if(window.ActiveXObject)
	req=2;


function callEHRObjAJAX()
{
	var patEHRObj = null;
	var jsonText="";
	$.ajax({
		   url: createFHashAjaxQuery("/HISClinical/mrd/emrDesk.cnt?hmode=PATDTLAJAX"),  
		    type : 'GET',
		    async: false,
			success: function(data) {
				try{
				//alert(data);
				if(data=='-1')
					patEHRObj = "";//callEHRObjAJAX();
				else
					jsonText = JSON.stringify(data);
				patEHRObj=JSON.parse(jsonText);
				}
				catch(e)
				{
					//alert(e);
					//patEHRObj = callEHRObjAJAX();
				}
		      },
		      error: function(data)
		      {
		    	    //alert('request failed :');
				}
		});
	
	// alert("patEHRObj=="+patEHRObj);

	return patEHRObj;
}
	
function callOnload()
{
	//alert(parent.parent.patEHRObj);
	//var a = document.getElementsByName("selectionCriteria")[0].value;
	//alert(a);
	//if(a!="All")
		//{
		//alert("selection criteria");
	var patEHRObj = callEHRObjAJAX();
	
	//toggleActivateOnload(parent.parent.patEHRObj);
	//alert(patEHRObj);
	toggleActivateOnload(patEHRObj);
		
	
	setCurrent(); 
	//alert("color "+obj.style.backgroundColor)
		//}
	var tabMode=document.getElementsByName("tabMode")[0].value
	//alert(tabMode)
	//showMoreTabs(tabMode)//incase to display more tabs with click of plus button
	
	//parent.document.getElementById("footerId").src="../mrd/transaction/emrFooter.jsp"
}  

function pausecomp(ms) {
	ms += new Date().getTime();
	while (new Date() < ms){}
	} 


function toggleActivateOnload(patEHRObj)
{
	//var a = document.getElementsByName("hmode")[0].value;
	//alert(a);
	//alert(patEHRObj);
	 if(patEHRObj==null)
		<%--  patEHRObj= JSON.parse('<%=session.getAttribute(EHRConfig.EHR_JSON_OBJECT_FIELD_NAME_PATIENT_DATA)%>'); --%>
	     //alert(patEHRObj.length);
	 	//console.log(patEHRObj);
	 	
	 	//Added by prachi 
	 	 //var mode = "GETEMRDATATHROUGHAJAX";
	 	patEHRObj = function(){
		 	//alert("ajax calling");
		 	var response = null;
	 	    $.ajax({
			url: createFHashAjaxQuery('/HISClinical/mrd/emrDesk.cnt?hmode=GETEMRDATATHROUGHAJAX'),  
		    type : 'GET',
		    datatype: "json",
		    async : false,
			success: function(data) {
			 //alert(data);
			
			 response = data;
			 //alert(response);
			//var arr = JSON.parse(response);
			//alert(arr);
			//alert("Parsed JSON:"+JSON.parse(response))
			//return JSON.parse(response);
			
             },
		      error: function(data)
		      {
		    	    alert('request failed :');
		    	}
		
		});
		return JSON.parse(response);
	 }();
	 	
	 	//alert(patEHRObj);	
	var tabUrlArray=emrTabArr.split('^');
	// alert(tabUrlArray);
	var deskFrame=parent.document.getElementById("treeNodeFrameId");
	//alert(tabUrlArray.length);
	var req;
	var mode = 1;
	if(window.XMLHttpRequest)
		req = 1;
	else if(window.ActiveXObject)
		req=2;
	
	for(i=1;i<tabUrlArray.length;i++)
	{
		if(patEHRObj==null)
		{
			flagActive = 0;
		
		}
		else
		{
			var flagActive = setEMRTabsActiveStatus(mode, patEHRObj,tabUrlArray[i],deskFrame,req);
		}
		var obj=document.getElementById("tdEMRTree_PreImg_"+tabUrlArray[i]);
		var obj1=document.getElementById("tdEMRTree_PostImg_"+tabUrlArray[i]);	
		var obj2=document.getElementById("tdId"+tabUrlArray[i]);
		//alert(flagActive+"::"+tabUrlArray[i]);
		if(flagActive==0)
		{
			obj.style.display = "none";
			obj1.style.display = "none";
			obj2.className = 'tabInactive';
			obj2.title="Inactive";
		}
		else
		{
			obj.style.display = "";
			obj1.style.display = "";
			obj2.className = 'tabFront';
			obj2.title="Active";
		}
	}
	setCurrent(); 
}

function setCurrent()
{
	var tabUrlArray=emrTabArr.split('^');
	
	var id=document.getElementsByName("selectedTab")[0].value;
	for(i=0;i<tabUrlArray.length;i++)
	{
		if(id==i)
			var tdId="tdId"+tabUrlArray[i];
	}
	
	currentTabId = tdId;
	var obj=document.getElementById(tdId);
	 obj.className ='tabCurrent';
}

 function setFocus(elemTD,mode,index){
	
	 if(elemTD.title=="Inactive") return;
	if(mode==1){		
		document.getElementById("tdId"+index).className="tabHighlight"
		//document.getElementById("tdId"+index).style.backgroundImage="url('/AHIMS/hisglobal/images/blue_back.gif')";
		
	}	
	else{
		if(currentTabId=="tdId"+index)
			document.getElementById("tdId"+index).className="tabCurrent";
		else
			document.getElementById("tdId"+index).className="tabFront";
		//document.getElementById("tdId"+index).style.backgroundImage="url('/AHIMS/mrd/js/style01_4_o_back.gif')";
		}
	
	
}  
</script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body onload="callOnload();" > 
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="0" >
				<tr>
				<%
					List lst=(List)session.getAttribute(MrdConfig.EMR_TABS_ARRAY);
					int nTabinRow = 9;
					int rows = (int)(Math.ceil((double)lst.size()/nTabinRow));
					%>

				<td width="97%" nowrap="nowrap">
				<div align="left">
				<%
					for(int i=0;i<rows;i++)
					{
				%>
				<table cellpadding="0" cellspacing="0" style="margin-top: 2px">
				<tr>
				  <logic:iterate id="emrTabMstVO" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="hisglobal.vo.EprTabMasterVO" length="<%=Integer.toString(nTabinRow)%>" offset="<%=Integer.toString(i*nTabinRow)%>">
				  		<% if(emrTabMstVO.getTabUrl().equals("PERSONALPROFILE")){ %>
						<td id='tdEMRTree_PreImg_<%=emrTabMstVO.getTabUrl()%>' style="background-color: #086FA6; vertical-align: top;"><img style="height: 12; width: 12; vertical-align: top;" src="/HISClinical/hisglobal/images/tl.gif"/></td>
						<td onmouseover="setFocus(this,1,'<%=emrTabMstVO.getTabUrl()%>')" onmouseout="setFocus(this,2,'<%=emrTabMstVO.getTabUrl()%>')" 
							id="tdId<%=emrTabMstVO.getTabUrl()%>" class="tabFront"
							onclick="showEMRDetails(this,'<%=String.valueOf(index.intValue())%>',<%=emrTabMstVO.getTabId() %>);">
							&nbsp;<bean:write name="emrTabMstVO" property="tabName"/>&nbsp;
						</td>
						<td id='tdEMRTree_PostImg_<%=emrTabMstVO.getTabUrl()%>' style="background-color: #086FA6; vertical-align: top;"><img style="height: 12; width: 12; vertical-align: top;" src="/HISClinical/hisglobal/images/tr.gif"/></td>
						<td>&nbsp;</td>
						<%}
	               else
	               {%>
						<td id='tdEMRTree_PreImg_<%=emrTabMstVO.getTabUrl()%>' style="background-color: #086FA6; vertical-align: top;display: none;"><img style="height: 12; width: 12; vertical-align: top;" src="/HISClinical/hisglobal/images/tl.gif"/></td>
						<td onmouseover="setFocus(this,1,'<%=emrTabMstVO.getTabUrl()%>')" onmouseout="setFocus(this,2,'<%=emrTabMstVO.getTabUrl()%>')" 
							id="tdId<%=emrTabMstVO.getTabUrl()%>" class="tabInactive" title="Inactive"
							onclick="showEMRDetails(this,'<%=String.valueOf(index.intValue())%>',<%=emrTabMstVO.getTabId() %>);">
							&nbsp;<bean:write name="emrTabMstVO" property="tabName"/>&nbsp;
						</td>
						<td id='tdEMRTree_PostImg_<%=emrTabMstVO.getTabUrl()%>' style="background-color: #086FA6; vertical-align: top;display: none;"><img style="height: 12; width: 12; vertical-align: top;" src="/HISClinical/hisglobal/images/tr.gif"/></td>
						<td>&nbsp;</td>
						<%}
	                     %>
				 </logic:iterate>
				</tr>
				</table>
				<%	} %>
				</div>
				</td>
				</tr>
			</table>
		</his:ContentTag>
				
	<html:hidden name="EmrCommonDeskFB" property="selectedTab" />
	<html:hidden name="EmrCommonDeskFB" property="emrTabs"/>
	<html:hidden name="EmrCommonDeskFB" property="sendVisitDetail"/>
	<html:hidden name="EmrCommonDeskFB" property="episodeCode"/>
	<html:hidden name="EmrCommonDeskFB" property="episodeVisitNo"/>
	<html:hidden name="EmrCommonDeskFB" property="departmentUnitCode"/>
	<html:hidden name="EmrCommonDeskFB" property="selectionCriteria" />
	<html:hidden name="EmrCommonDeskFB" property="patCrNo" />
	<html:hidden name="EmrCommonDeskFB" property="hmode"/>
	<html:hidden name="EmrCommonDeskFB" property="selectedUnit" />
	<html:hidden name="EmrCommonDeskFB" property="selectedPatAdmNo" />
	<html:hidden name="EmrCommonDeskFB" property="tabId" />
	<html:hidden name="EmrCommonDeskFB" property="tabMode" />
	<html:hidden name="EmrCommonDeskFB" property="sortOn" />
	<html:hidden name="EmrCommonDeskFB" property="hosCode" />
	</body>
</html>
<%-- <%}catch(Exception e){e.printStackTrace();}}%> --%>