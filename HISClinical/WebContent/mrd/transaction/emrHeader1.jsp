<%try{ %>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript"> 
 
function showAllImagesPopUp(e)
{
	 openPopup('<his:path src="/mrd/emrDesk.cnt?hmode=ALLIMAGES&imageIndex=0"/>',e,500,900);
}

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

</script>


<script type="text/javascript">   

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
	
	parent.document.getElementById("footerId").src="../mrd/transaction/emrFooter.jsp"
	
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
</script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<body onload="callOnload();" > 
	
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="0" >
				<tr>
				<%-- <logic:iterate id="emrTabId" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="java.lang.String" length="8"   > --%>
				<%//List list=(List)session.getAttribute(MrdConfig.EMR_TABS_ARRAY); 
				//pageContext.setAttribute("emrTabList",list);%>
				<%String width=String.valueOf(100/8)+"%"; %>
				<%---<logic:iterate id="emrTabMstVO" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="hisglobal.vo.EprTabMasterVO" length="8">
						<td  id='<%="tdId"+(String.valueOf(index.intValue()))%>' onclick="showEMRDetails('<%=String.valueOf(index.intValue())%>',<%=emrTabMstVO.getTabId() %>);"  
							style="cursor: pointer;color:#7E7E7E;border-style:groove;background-color: #E0EBEB;height: 28px;" >
							<div align="center" id="<%=String.valueOf(index.intValue())%>"  >
								<b> <font style="color: #35006A;" > <bean:write name="emrTabMstVO" property="tabName"/> </font> </b>
							</div>
						</td>
				</logic:iterate>
				--%>
<!--				<div align="left" style="background-color: #F1ECE2" >-->
				<td width="97%" nowrap="nowrap">
				<div align="left" style="background-color: #E0EBEB" >
<!--				<div align="left" style="background-color: #F1ECE2" >-->
				  <logic:iterate id="emrTabMstVO" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="hisglobal.vo.EprTabMasterVO" length="10">
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
				 
				<td class="tdfonthead" width="3%">
					<div id="plusId" align="center">
						<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' tabindex="1"
						  style="cursor:pointer" title="More Tabs" onkeypress="if(event.keyCode==13) showMoreTabs(1);" 
						  onclick ="showMoreTabs(1);">
					</div>
					<div id="minusId" align="center" style="display: none">
						<img class="button" src='<his:path src="/hisglobal/images/sign-minus.png"/>' tabindex="1"
						  style="cursor:pointer;" title="Hide Tabs" onkeypress="if(event.keyCode==13) showMoreTabs(1);" 
						  onclick ="showMoreTabs(2);">
					</div>
					
				</td>
				
				<td class="tdfonthead" width="3%">
					<div id="upArrowId" align="center">
						<img class="button" src='<his:path src="/hisglobal/images/arrow-down.png"/>' tabindex="1"
						  style="cursor:pointer" title="More Tabs" onkeypress="if(event.keyCode==13) showMoreTabs1(1);" 
						  onclick ="showMoreTabs1(1);">
					</div>
					<div id="downArrowId" align="center" style="display: none">
						<img class="button" src='<his:path src="/hisglobal/images/arrow-up.png"/>' tabindex="1"
						  style="cursor:pointer;" title="Hide Tabs" onkeypress="if(event.keyCode==13) showMoreTabs1(1);" 
						  onclick ="showMoreTabs1(2);">
					</div>
					
				</td>
							
				<%-- <c:set var="index" value="${0}">
				</c:set>
				<c:forEach items="${emrTabList}" end="8"  var="emrTabMstVO">
					<td  id="tdId ${index}" onclick="showEMRDetails('${index}',${emrTabMstVO.getTabId()})"  style="cursor: pointer;color:#7E7E7E;border-style:groove;background-color: #E0EBEB;" >
						<div align="center" id="${index}"  >
							<b> <font style="color: #35006A;" > <c:out value="${emrTabMstVO.getTabName()}"></c:out> </font> </b>
						</div>
					</td>
					<c:set var="index" value="${index+1}"/>
				</c:forEach>
				--%>
				</tr>
			</table>
			<%------------------- show tab list vertically ----------------%>
			<table width="100%" style="position: absolute;visibility: inherit" align="right">
			<tr align="right">
				<td width="80%">
				</td>
				<td align="right" width="20%"> 
				<div id="eprMoreTabIdVer" align="left" style="background-color: #E0EBEB;position: absolute;display: none;" >
						<logic:iterate id="emrTabMstVO" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="hisglobal.vo.EprTabMasterVO" offset="9">
						<table width="100%">
							<tr>
								<td width="100%">
								<span class="tabFront" onmouseover="setFocus(1,<%=String.valueOf(index)%>)" onmouseout="setFocus(2,<%=String.valueOf(index)%>)" 
								id="tdId<%=String.valueOf(index)%>"
								onclick="showEMRDetails('<%=String.valueOf(index.intValue())%>',<%=emrTabMstVO.getTabId() %>);" >
								<img style="height: 15" src="/HISClinical/mrd/js/style01_4_o_left.gif"/>
								<bean:write name="emrTabMstVO" property="tabName"/>
								<img style="height: 15" src="/HISClinical/mrd/js/style01_4_o_right.gif"/>
								</span>
								</td>
							</tr>
						
						</table>
						</logic:iterate>
					</div>
					</td>
					
			</tr>	
			</table>
			<%--////////////////////////////////////////////////////////////////--%>
				
		<div id="eprMoreTabId" style="display: none">
			<%-- 
			<table width="100%" border="0" cellspacing="0" >
			<tr  >
			<logic:iterate id="emrTabMstVO" name="<%=MrdConfig.EMR_TABS_ARRAY %>" indexId="index" type="hisglobal.vo.EprTabMasterVO" offset="8"    >
			<td  id='<%="tdId"+(String.valueOf(index.intValue()))%>' onclick="showEMRDetails('<%=String.valueOf(index.intValue())%>',<%=emrTabMstVO.getTabId() %>);" 
			 style="cursor: pointer;color:#7E7E7E;border-style:groove;background-color: #E0EBEB;height: 28px" >
			<div align="center" id="<%=String.valueOf(index.intValue())%>"  >
			
			<b> <font style="color: #35006A;" > <bean:write name="emrTabMstVO" property="tabName"/> </font> </b>
			</div>
			</td>
			</logic:iterate>
			</tr>
			</table>
			--%>
			<table width="100%" border="0" cellspacing="0" >
			<tr >
			<td width="97%" >
<!--				<div align="left" style="background-color: #F1ECE2" >-->
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
		</div>
		</his:ContentTag>
		
		<table width="100%" > 
			<tr>
				<td width="100%" class="tdfont">
					<div>
					 
					</div>
				</td>
			</tr>
		</table>
	</body>
	<html:form action="/emrDesk">
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
	
	</html:form>
</html>
<%}catch(Exception e){e.printStackTrace();}%>