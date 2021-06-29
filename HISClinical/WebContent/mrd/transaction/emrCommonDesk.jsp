<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="ehr.EHRConfig" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

var patEHRObj=null;

function closePage()
{
	self.close();
}

function clearPage()
{
	// alert("document "+document.getElementById("opdReportDeskFrame").src)
	document.getElementById("opdReportDeskFrame").src="/HISClinical/opd/report.cnt";
	
}

window.onload=function()
{
	
	hideMenuFrame();
	//setPatientEHRObj();
	
	var deskFrame=document.getElementById("emrDetailsId");
	if(window.XMLHttpRequest)
		dtlDoc=deskFrame.contentWindow;
	else if (window.ActiveXObject)
		dtlDoc=deskFrame.Window;
	//alert(patEHRObj);
	dtlDoc.toggleActivateOnload(patEHRObj);
}

/*
	on click of the node of the tree set the values according to the node of the tree
*/
function setSelected(value,id)
{
	var deskFrameEMRDetails=document.getElementById("treeNodeFrameId");
	if(window.XMLHttpRequest)
		emrDoc=deskFrameEMRDetails.contentDocument;
	else if (window.ActiveXObject)
		emrDoc=deskFrameEMRDetails.Document;
	var emrFrm=emrDoc.forms[0];
	
	if(id=='H')
	{
		emrDoc.getElementsByName("hosCode")[0].value=value;
	}
	else if(id=='O')
	{
		emrDoc.getElementsByName("hosCode")[0].value=value;
	}
	else if(id=='E')
	{	
		var arr = value.split("#");
		var hosCode=arr[0];//value.toString().substr(16,20);
		var episodeCode=arr[1];//value.toString().substr(0,8);
		emrDoc.getElementsByName("episodeCode")[0].value=episodeCode;
		emrDoc.getElementsByName("hosCode")[0].value=hosCode;
	}	
	else if(id=='U') 
	{	
		var arr = value.split("#");
		var hosCode=arr[0];//value.toString().substr(5,4);
		var deptUnitCode=arr[1];//value.toString().substr(0,5);
		emrDoc.getElementsByName("selectedUnit")[0].value=deptUnitCode;
		emrDoc.getElementsByName("hosCode")[0].value=hosCode;
	}
	else if(id=='V')
	{
		//alert(value);
		var arr = value.split("#");
		//alert(arr);
		var hosCode=arr[0];//value.toString().substr(16,20);
		var episodeCode=arr[2];//value.toString().substr(0,8);
		var selectedVisit=arr[3];//value.toString().substr(0,16);
		emrDoc.getElementsByName("episodeVisitNo")[0].value=selectedVisit;		
		emrDoc.getElementsByName("episodeCode")[0].value=episodeCode;
		emrDoc.getElementsByName("hosCode")[0].value=hosCode;
		//alert(emrDoc.getElementsByName("episodeCode")[0].value)
		//alert(emrDoc.getElementsByName("episodeVisitNo")[0].value)
	}
	else if(id=='I')
	{
		//emrDoc.getElementsByName("selectedPatAdmNo")[0].value=value;
		emrDoc.getElementsByName("hosCode")[0].value=value;
		
	}
	else if(id=='A')
	{
		var arr = value.split("#");
		var hosCode=arr[0];//value.toString().substr(10,13);
		var admissionCode=arr[1];//value.toString().substr(0,10);
		emrDoc.getElementsByName("hosCode")[0].value=hosCode;
		emrDoc.getElementsByName("selectedPatAdmNo")[0].value=admissionCode;
	}
	
	emrDoc.getElementsByName("selectionCriteria")[0].value=id;
	
	var deskFrame=document.getElementById("emrDetailsId");
	if(window.XMLHttpRequest)
		dtlDoc=deskFrame.contentWindow;
	else if (window.ActiveXObject)
		dtlDoc=deskFrame.Window;
	//alert(patEHRObj);
	dtlDoc.toggleActivateOnload(patEHRObj);
} 

function setSelectedWithHosCode(value,hosCode,id)
{
	var deskFrameEMRDetails=document.getElementById("treeNodeFrameId");
	if(window.XMLHttpRequest)
		emrDoc=deskFrameEMRDetails.contentDocument;
	else if (window.ActiveXObject)
		emrDoc=deskFrameEMRDetails.Document;
	var emrFrm=emrDoc.forms[0];
	
	if(id=='V')
	{
		
		emrDoc.getElementsByName("selectedVisit")[0].value=value;
	}
	
	if(id=='U') 
	{	
		//alert("abc::");
		//var abc=value.split('#');
		//alert("abc::"+abc[0]);
		emrDoc.getElementsByName("selectedUnit")[0].value=value;
		emrDoc.getElementsByName("hosCode")[0].value=hosCode;
		
	}
	
	if(id=='E')
	{
		emrDoc.getElementsByName("episodeCode")[0].value=value;
	}	
	if(id=='I')
	{
		emrDoc.getElementsByName("selectedPatAdmNo")[0].value=value;
	}
	if(id=='H')
	{
		emrDoc.getElementsByName("hosCode")[0].value=hosCode;
	}
	
	emrDoc.getElementsByName("selectionCriteria")[0].value=id;
	//alert("id:"+emrDoc.getElementsByName("selectionCriteria")[0].value);
} 

function op() {
     // This function is for folders that do not open pages themselves.
    // alert("op")
   }

function changeURL()
{

	var URL1=createFHashAjaxQuery("/HISClinical/mrd/emrDesk.cnt?hmode=PATDTL");
	//alert(URL1);
	 document.getElementById("patientDtlFrameId").src =URL1;
	 var URL2=createFHashAjaxQuery("/HISClinical/mrd/emrDesk.cnt?hmode=PERSONALPROFILE");
		//alert(URL2);
		 document.getElementById("emrDetailsId").src =URL2;
			var URL3=createFHashAjaxQuery("/HISClinical/mrd/emrDesk.cnt?hmode=GETNODE");
			//alert(URL3);
			 document.getElementById("treeNodeFrameId").src =URL3;
				var URL4=createFHashAjaxQuery("/HISClinical/mrd/emrDesk.cnt?hmode=HIDETREE");
				//alert(URL3);
				 document.getElementById("treeNodeHideId").src =URL4;

}

</script>

</head>




<frameset id="emrMainFrameId" rows="5%,95%" border="" bordercolor="" style="background-color: white;"
FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0" onLoad="javascript:changeURL()">

<frame id="patientDtlFrameId" src=""  scrolling="no" noresize="noresize"
   TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0"  > 
  
   
<frameset id="emrDetailFS" cols="18%,1%,*" border=""
FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >  

<frame id="treeNodeFrameId" src="" 
	style="background-color: white;" scrolling="auto" noresize="noresize"
    TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >
  
<frame id="treeNodeHideId" src="" 
	 scrolling="no" noresize="noresize" 
    TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >
<frameset id="emrDetailFrameset" rows="100%,*" border=""
FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" >  
<frame id="emrDetailsId" src=""   scrolling="auto" 
   TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" name="basefrm" style="margin-left: 30pt">

</frameset>
</frameset>
</frameset>



