
<!-- 
/**
 * @copyright CDAC
 * @developer Hruday Meher
 * @lastModified Pargya Sharma  08-Jul-2010
 */
-->

<% 
try
{
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.EpisodeDiagnosisVO"%>
<%@page import="opd.*,opd.transaction.controller.fb.OpdDiagnosisFB"%>

<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>


<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:javascript src="/opd/js/desk_episode_diagnosis.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">

<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<script type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>

<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<script type="text/javascript">

//snomd new

/*
* setValue() function is called in the callback function.This function is used to display the return value in the HTML control.In
* index.html page,the value is displayed in the "snomedcttext" textarea.The result includes- Concept id, Description-id and Description  
* param selectedSNOMEDTerm(var)-is the concept selected by the user from the search result list 
* 
*/
// http://10.226.2.97:8080/csnoserv/api/lookup/relationship?id=9826008&relation=finding%20site&direction=source

var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};
function findingSiteForDisease(snmdctid)
{
	//alert("hi");
	var elemSiteCombo = document.getElementById("seldiagnosticSiteId");
	elemSiteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemSiteCombo.appendChild(op);

	
  var prefTermArray="";
  var obj="";
	// SCTID: 609328004, Allergic disposition (disorder)
	// SCTID: 418634005, Allergic reaction to substance (disorder)
	// SCTID: 282100009, Adverse reaction to substance (disorder)to substance (disorder)
	// SCTID: 421961002  Hypersensitivity reaction (disorder) 
	// SCTID: 419511003  Propensity to adverse reactions to drug (disorder) 
		
			
		$.ajax({
			
			url :createFHashAjaxQuery( "/AHIMSG5/snomedct/csnoserv/api/lookup/relationship?id="+snmdctid+"&relation=finding site&direction=source"),
			type : 'GET',
			
			 datatype: "json",
			 async: false,
			  success : function(data) {
				 // alert(data);
				//obj=JSON.parse(data);
						obj = eval(data);
						if(obj != null && obj !='undefined')
						{
							if (obj.length > 0) 
							{
						document.getElementById("divfindingsitesnomed").style.display="none";
						document.getElementById("divfindingsite").style.display="";
							}
							else
								{
								document.getElementById("divfindingsitesnomed").style.display="";
								document.getElementById("divfindingsite").style.display="none";
								}
						}
			//	alert(obj.length); 
			//	alert(obj); 
				//alert(obj[0].fullySpecifiedName);
				
				
	},
error: function(data)
{
    alert('request failed :');
}

});
		if(obj != null && obj !='undefined')
		{
			if (obj.length > 0) 
			{
				for(var i=0;i<obj.length;i++)
					{
					//alert("inside snomed");
				//	alert(obj[i]);
					prefTermArray=getSnomedTerm(obj[i].id);
					op=document.createElement("option");
					op.value=obj[i].id;
					op.innerHTML=prefTermArray;
					elemSiteCombo.appendChild(op);
					//alert(prefTermArray);
					}
			} 
			
		}


			
 
			
}


 function setfreeText()
{
	
	 document.getElementsByName("remarks")[0].value=document.getElementsByName("txt-snomed-ct-search_4")[0].value;   //for free text

	
} 


function getSnomedTerm(id)
{
	var pt = ""; 
	var obj = "";
	
	//alert(id);
	//alert("insidesnomedterm");
	
	$.ajax({
		url : createFHashAjaxQuery("/AHIMSG5/snomedct/csnoserv/api/lookup/concept?id="+id+"&langrefset=us"),
		type : 'GET',
		 datatype: "json",
		 async: false,
		  success : function(data) {
			  
			  try{
			//alert(data);
			//  alert(data.length)
			
					obj=JSON.parse(data);
		    //obj =  eval(data);
		  //  alert(obj.length);
			//alert("obj length"+obj.descriptions.length); 
		//	alert(obj.descriptions[0].isPreferredTerm);
			//alert(objpreTrm[0].term); 
			//alert(obj[0].fullySpecifiedName);
			//pt=objpreTrm[0].term;
			if(obj.descriptions.length != null && obj.descriptions.length !='undefined')
		    {
			if (obj.descriptions.length > 0) 
			{
				//alert("inside");
			for(var j=0;j<obj.descriptions.length;j++)
			{	
				//alert(obj.descriptions[j].isPreferredTerm);
			if(obj.descriptions[j].isPreferredTerm=='1' && obj.descriptions[j].typeId=='SYNONYM')
			{
		       pt=obj.descriptions[j].term;
		       break;
			}
			}
			}}
			  }
			  catch(e)
			  {
				  alert(e.message);
			  }
			
			
},
error: function(data)
{
alert('request failed :');
}

	});
	

	return pt;

}

function setSnomedSiteName()
{
	var obj=document.getElementById("seldiagnosticSiteId");
//	alert(obj.options[obj.selectedIndex].text);
	//alert(obj.options[obj.selectedIndex].value);
	document.getElementsByName("snomedCTDiagnosisSiteName")[0].value=obj.options[obj.selectedIndex].text;
	document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value=obj.options[obj.selectedIndex].value;

}
		
		
function setValue1(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
//	var arr=selectedSNOMEDTerm.split(",");
//	var str=arr[0];
//	var str1=arr[1];
	
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str[0]); alert(str1);
	
	document.getElementsByName("snomedCTDiagnosisName")[0].value=str;
	document.getElementsByName("snomedCTDiagnosisCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_1")[0].value=str;
	document.getElementById("somectIddata").innerHTML =str1;
	//findingSiteForDisease(str1);
	document.getElementById("divfindingsitesnomed").style.display="";
	document.getElementById("divfindingsite").style.display="none";

		}
	else
		{
		document.getElementsByName("snomedCTDiagnosisName")[0].value="";
		document.getElementsByName("snomedCTDiagnosisCode")[0].value="";
		 document.getElementById("somectIddata").innerHTML ="";
		}
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
}


function setValue2(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	
	//	var arr=selectedSNOMEDTerm.split(",");
	//	var str=arr[0];
	//	var str1=arr[1];
	//alert(str[0]); alert(str1);
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;

	
	document.getElementsByName("snomedCTDiagnosisSiteName")[0].value=str;
	document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_2")[0].value=str;
	//document.getElementById("somectIddata").innerHTML =str1;
	//findingSiteForDisease(str1);
		}
	else
		{
		document.getElementsByName("snomedCTDiagnosisSiteName")[0].value="";
		document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	
}


function setValue4(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	
	//	var arr=selectedSNOMEDTerm.split(",");
	//	var str=arr[0];
	//	var str1=arr[1];
	//alert(str[0]); alert(str1);
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;

	
	//document.getElementsByName("snomedPTRemarks")[0].value=str;
	//document.getElementsByName("snomedCIdRemarks")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_4")[0].value=str;
	
	 targetPrefferedTerm=document.getElementsByName("snomedPTRemarks")[0];  //preffered term
	 targetConceptId=document.getElementsByName("snomedCIdRemarks")[0];   //concept Id
	 document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks")[0].value + ' ' + str;
	 

 
    if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
	else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
	
    if(targetConceptId.value=="")  targetConceptId.value = str1;
	else targetConceptId.value = targetConceptId.value + "#" + str1;
	
    targetPrefferedTerm="";
    targetConceptId="";

	 
	 
	//document.getElementById("somectIddata").innerHTML =str1;
	//findingSiteForDisease(str1);
		}
	else
		{
		document.getElementsByName("snomedPTRemarks")[0].value="";
		document.getElementsByName("snomedCIdRemarks")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	$("#conceptdiv_4").hide();
	$("#norecorddiv_4").hide();
	
}




/* function selectValue(value, callback) {
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	

} */


function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    //alert("hi");
   
   // txt-snomed-ct-search_4
   document.getElementsByName("snomedPTRemarks")[0].value="";  //preffered term
	document.getElementsByName("snomedCIdRemarks")[0].value="";  //concept Id
	document.getElementsByName("remarks")[0].value="";
	
    
    //  document.getElementById(ClientsID.txtantcedentcausecptid).value = "";
    //  document.getElementById(ClientsID.txtccptid).value = "";


});


</script>


<his:TitleTag>
	<his:name>
		<bean:message key="patient" />
		<bean:message key="diagonosis" />
	</his:name>
</his:TitleTag>
<%int count=0;%>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
<his:statusTransactionInProcess>


<%
String unitDiagnosisCodeType = ((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).getUnitDiagnosisCodeType();
if(unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_ICD_CODE))
{
	((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
	((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).setIcdNHospitalFlagValue(OpdConfig.CHOICE_ICD_CODE);
}
else if(unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_HOSPITAL_CODE))
{
	((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).setIcdNHospitalFlag(OpdConfig.CHOICE_HOSPITAL_CODE);
	((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).setIcdNHospitalFlagValue(OpdConfig.CHOICE_HOSPITAL_CODE);

}
else if(unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_ICDO_CODE))
{
	((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).setIcdNHospitalFlag(OpdConfig.CHOICE_ICDO_CODE);
	((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).setIcdNHospitalFlagValue(OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE);

}	
	EpisodeDiagnosisVO[] prevDiag = (EpisodeDiagnosisVO[]) session.getAttribute(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
	if (prevDiag!=null && prevDiag.length != 0)
	{ 
%>
	<his:SubTitleTagBroad name="Previous Diagnosis Detail">
		<%	EpisodeDiagnosisVO[] stopDiag = (EpisodeDiagnosisVO[]) session.getAttribute(OpdConfig.STOP_DIAGNOSIS_VO);
			if (stopDiag!=null && stopDiag.length != 0)
			{
		%>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
					<a style="cursor:pointer" onclick="openPopup(createFHashAjaxQuery('/HISClinical/opd/opdDiagnosis.cnt?hmode=SHOWALL&unitDiagnosisCodeType='+document.forms[0].unitDiagnosisCodeType.value),event,400,900);" >	
						<bean:message key="revokedDiagnosis" />
					</a>
				</b>
			</font>
				<!-- <img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/arrdouble-right.png"/>'
				 alt="Show All" title="Show All" 
				 onclick="openPopup('/HISClinical/opd/opdDiagnosis.cnt?hmode=SHOWALL&unitDiagnosisCodeType='+document.forms[0].unitDiagnosisCodeType.value,event,400,900);"> -->
		<%
			}
		%>
	</his:SubTitleTagBroad>

	<%	EpisodeDiagnosisVO[] latestDiag = (EpisodeDiagnosisVO[]) session.getAttribute(OpdConfig.Latest_DIAGNOSIS_VO);
		if(latestDiag.length !=0){
	%>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<!--<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="0">
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="icdCode" />
								</b>
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="diagnosisName" />
								</b>
							</font>
						</div>
					</td>
				</logic:equal>
				
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="1">
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="hospitalDiagnosisCode" />
								</b>
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="ayushDiagnosisName" />
								</b>
							</font>
						</div>
					</td>
				</logic:equal>-->
				
				
			
			
				
				<%-- <td width="8%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>
								<bean:message key="disease" />
								<bean:message key="code" />
							</b>
						</font>
					</div>
				</td> --%>
				
				<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="disease" />
								</b>
						</font>
					</div>
				</td>
<!-- 				<td width="15%" class="tdfonthead"> -->
<!-- 					<div align="center"> -->
<!-- 						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
<!-- 							<b> -->
<%-- 								<bean:message key="diagnosisCodeType" /> --%>
<!-- 							</b> -->
<!-- 						</font> -->
<!-- 					</div> -->
<!-- 				</td> -->
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="diagonosisType" />
							</b>
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="diagnosisSite" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font id="fntStar" color="#FF0000"></font>
								<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
				
				<logic:notEqual name="OpdDiagnosisFB" property="episodeVisitNo" value="<%=latestDiag[0].getEpisodeVisitNo() %>">
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000"></font>
									<bean:message key="repeatDiagnosis" />
								</b>
							</font>
						</div>
					</td>
				</logic:notEqual>
				<logic:equal name="OpdDiagnosisFB" property="episodeVisitNo" value="<%=latestDiag[0].getEpisodeVisitNo() %>">
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="canceled" />
								</b>
							</font>
						</div>
					</td>
				</logic:equal>
				
			</tr>
			
			<logic:iterate id="prevDiagVO" indexId="idx" name="<%=OpdConfig.Latest_DIAGNOSIS_VO%>" type="hisglobal.vo.EpisodeDiagnosisVO">
				<tr>
				
					<%-- <td class="tdfont">
						<div align="center">
							
							<% String path = "/HISClinical/opd/opdDiagnosis.cnt?hmode=PREVIOUS&selectedDiagCode=" + (String) prevDiagVO.getDiagnosticCode(); %>
							<a style="cursor: pointer" onclick="getPrevDiagnosisDetail(event,'<%= path%>')"> <%=prevDiagVO.getDiagnosticCode()%> </a>
							
								
						</div>
					</td> --%>
					
					<td class="tdfont">
						<div align="center">
						<% String path1 = "/HISClinical/opd/opdDiagnosis.cnt?hmode=PREVIOUS&selectedDiagCode=" + (String) prevDiagVO.getDiagnosticCode(); %>
							<a style="cursor: pointer" onclick="getPrevDiagnosisDetail(event,'<%= path1%>')"> <%=prevDiagVO.getDignosisName()%> </a>
						</div>
					</td>
<!-- 					<td class="tdfont"> -->
<!-- 						<div align="center"> -->
<%-- 							<%=prevDiagVO.getDiagnosisCodeLabel()%>  --%>
<%-- 							<html:hidden name="OpdDiagnosisFB" property="addDiagnosisCodeType" value="<%=prevDiagVO.getDiagnosisCodeType() %>"/> --%>
<!-- 						</div> -->
<!-- 					</td> -->
					<logic:equal name="OpdDiagnosisFB" property="episodeVisitNo" value="<%=prevDiagVO.getEpisodeVisitNo() %>">
						<html:hidden name="OpdDiagnosisFB" property="addDiagnosisCode" value="<%=prevDiagVO.getDiagnosticCode()%>" />
						<html:hidden name="OpdDiagnosisFB" property="addDiagnosis" value="<%=prevDiagVO.getDiagnosticCode()%>" />
						<td class="tdfont">
							<div align="center">
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_ICD %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" disabled="true" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_ICD%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_ICD%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>	
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_HOSPITAL %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" disabled="true" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>		
								
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" disabled="true" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>	
								
								<!-- Added by Vasu on 23.Oct.2018 for ICD-O Integration -->
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_ICDO %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" disabled="true" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
							<logic:notEqual name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED %>">	
						
								<html:select name="OpdDiagnosisFB" property="addDiagnosisSite" style="width:100px;" disabled="true" value='<%=prevDiagVO.getDiagnosisSite()==null?"-1":prevDiagVO.getDiagnosisSite()%>'>
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.DIAGNOSIS_SITE_LIST%>" >
									<html:options collection="<%=OpdConfig.DIAGNOSIS_SITE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
								</logic:notEqual>
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED %>">	
								<html:textarea name="OpdDiagnosisFB" property="addDiagnosisSite" rows="1" cols="20"  value='<%=prevDiagVO.getDiagnosisSiteLabel()==null?"":prevDiagVO.getDiagnosisSiteLabel()%>' disabled="true">
								</html:textarea>
								</logic:equal>
							</div>
						</td>
						
						
						
						<td class="tdfont">
							<div align="center">
								<html:textarea name="OpdDiagnosisFB" property="addDiagnosisRemarks" 
									value='<%=(prevDiagVO.getRemarks()==null)?"":prevDiagVO.getRemarks() %>' 
									disabled="true" rows="1" cols="20" onkeypress="return (CheckMaxLength(event,this,'50') && validateAlphaNumOnly(this,event))"></html:textarea>
							</div>
						</td>
						<!--<td class="tdfont">
							<div align="center">
								<logic:equal name="prevDiagVO" property="isRepeat" value="<%=OpdConfig.DIAGNOSIS_IS_REPEAT_NEW %>">
									 NEW 
								</logic:equal>
								<logic:equal name="prevDiagVO" property="isRepeat" value="<%=OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT %>">
									REPEATED
								</logic:equal>
								<logic:equal name="prevDiagVO" property="isRepeat" value="<%=OpdConfig.DIAGNOSIS_IS_REPEAT_STOP %>">
									STOPPED
								</logic:equal>
							</div>
						</td>-->
						<td class="tdfont">
							<div align="center">
							<%if(prevDiagVO.getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_NEW)){  %>
								<img class="button" id="revokeButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Minus.png"/>'  title="Cancel Diagnosis" onclick="revokeDiagnosis('<%=prevDiagVO.getDiagnosticCode() %>','<%=prevDiagVO.getDiagnosisCodeType() %>')">
								<%} %>
							</div>
						</td>	
					</logic:equal>

					<logic:notEqual name="OpdDiagnosisFB" property="episodeVisitNo" value="<%=prevDiagVO.getEpisodeVisitNo() %>">
						<html:hidden name="OpdDiagnosisFB" property="addDiagnosisCode" value="<%=prevDiagVO.getDiagnosticCode()%>" />
						<html:hidden name="OpdDiagnosisFB" property="addDiagnosis" value="<%=prevDiagVO.getDiagnosticCode()%>" />
						<td class="tdfont">
							<div align="center">
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_ICD %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_ICD%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_ICD%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>	
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_HOSPITAL %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>
								
									<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED %>">	
									<html:select name="OpdDiagnosisFB" property="addDiagnosticTypeCode" value="<%=prevDiagVO.getDiagnosticTypeCode()%>">
									<logic:present name="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" >
										<html:options collection="<%=OpdConfig.DIAGNOSIS_LIST_HOSPITAL%>" property="value" labelProperty="label" />
									</logic:present>
									</html:select>
								</logic:equal>
								
								
								
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
							<logic:notEqual name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED %>">	
								<html:select name="OpdDiagnosisFB" property="addDiagnosisSite" style="width:100px;" value='<%=prevDiagVO.getDiagnosisSite()==null?"-1":prevDiagVO.getDiagnosisSite()%>'>
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.DIAGNOSIS_SITE_LIST%>" >
									<html:options collection="<%=OpdConfig.DIAGNOSIS_SITE_LIST%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
								</logic:notEqual>
								
								<logic:equal name="prevDiagVO" property="diagnosisCodeType" value="<%=OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED %>">	
								<html:text name="OpdDiagnosisFB" property="addDiagnosisSite" style="width:100px;" value='<%=prevDiagVO.getDiagnosisSiteLabel()==null?"":prevDiagVO.getDiagnosisSiteLabel()%>' disabled="disabled">
								</html:text>
								</logic:equal>
								
							</div>
						</td>
						<td class="tdfont">
							<div id="divPrevRemark" align="center">
								<html:textarea name="OpdDiagnosisFB" property="addDiagnosisRemarks" 
									value='<%=(prevDiagVO.getRemarks()==null)?"":prevDiagVO.getRemarks() %>' 
									tabindex="1" rows="1" cols="20" onkeypress="return (CheckMaxLength(event,this,'50') && validateAlphaNumOnly(this,event))"></html:textarea>
							</div>
							<div id="divPrevCancelRemark" align="center" style="display: none;">
								<html:textarea name="OpdDiagnosisFB" property="addDiagnosisCancelRemarks" 
									tabindex="1" rows="1" cols="20" onkeypress="return (CheckMaxLength(event,this,'50') && validateAlphaNumOnly(this,event))"></html:textarea>
							</div>
						</td>
	
						<td class="tdfont">
							<div align="center">
								<input name="chkPrev" type="checkbox" value="<%=idx.toString()%>" onclick="selectDeselectPrev(this)" checked="checked" />
							</div>
						</td>
					</logic:notEqual>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
<%
	}}
	if (session.getAttribute(OpdConfig.REPEAT_DIAGNOSIS_VO) != null)
	{
		System.out.println("Repeat" + session.getAttribute(OpdConfig.REPEAT_DIAGNOSIS_VO));
%>
	<his:SubTitleTag name="Repeated Previous Diagnosis">
	</his:SubTitleTag>

	<table id="repeatTableId" style="position: relative;" width="100%">
		<logic:iterate id="repeatVO" name="<%=OpdConfig.REPEAT_DIAGNOSIS_VO%>" indexId="idx" type="hisglobal.vo.EpisodeDiagnosisVO">
			<tr>
				<td width="10%" class="tdfont">
					<div align="center"><%=repeatVO.getDiagnosticCode()%></div>
				</td>
				<td width="30%" class="tdfont">
					<div align="center"><%=repeatVO.getDignosisName()%></div>
				</td>
				<td width="20%" class="tdfont">
					<div align="center"><%=repeatVO.getDiagnosticTypeName()%></div>
				</td>
				<td width="30%" class="tdfont">
					<div align="center"><%=repeatVO.getRemarks()%></div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center"></div>
				</td>
			</tr>
		</logic:iterate>
	</table>
<%
	}
%>

	<his:SubTitleTagBroad name="Patient Diagnosis Detail">
		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<tr>
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE %>">
					<td width="75%" >
						<div align="left">
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="0" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="icdCode" />
								</b>
							</font>
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="1" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');" ></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="hospitalCode" />
								</b>
							</font>
							
							 <html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="4" onclick="snomd();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="snomedCtId" />
								</b>
							</font>
						</div>
					</td>
					<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue"/>
				</logic:equal>	
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_ICD_AND_HOSPITAL_DEFAULT_CODE %>">
					<td width="75%" >
						<div align="left">
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="0" onclick="showIdcHospitalDiagnosis(); submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="icdCode" />
								</b>
							</font>
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="1" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');" ></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="hospitalCode" />
								</b>
							</font>
							
						<%-- 	<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="4" onclick="snomd();submitForm('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="snomedCtId" />
								</b>
							</font> --%>
							
						</div>
					</td>
					<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue"/>
				</logic:equal>
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_ALL_ICD_HOSPITAL_SNOMED %>">
					<td width="75%" >
						<div align="left">
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="0" onclick=" showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="icdCode" />
								</b>
							</font>
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="1" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="hospitalCode" />
								</b>
							</font>
							
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="4" onclick="snomd();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									 <bean:message key="snomedCtId" /> 
								</b>
							</font>
						</div>
					</td>
					<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" />
				</logic:equal>	
				
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_ALL_AND_SNOMED_DEFAULT %>">
					<td width="75%" >
						<div align="left">
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="0" onclick=" showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%-- <bean:message key="icdCode" /> --%>ICD-10
								</b>
							</font>
							<%-- <html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="1" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="hospitalCode" />
								</b>
							</font> --%>
							
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="4" onclick="snomd();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%-- <bean:message key="snomedCtId" /> --%>SNOMED-CT
								</b>
							</font>
							
							<!-- FOR ICD-O DEFAULT ICD Added by Vasu on 15-10-2018-->
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="9" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<%-- <bean:message key="icdOCode" /> --%>ICD-O
								</b>
							</font> 
							
						</div>
					</td>
					<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" />
				</logic:equal>	
				
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_HOSPITAL_AND_SNOMED_DEFAULT%>">
					<td width="75%" >
						<div align="left">
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="0" onclick=" showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');" style="display:none"></html:radio>
							
							
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="1" onclick="showIdcHospitalDiagnosis();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="hospitalCode" />
								</b>
							</font>
							
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="4" onclick="snomd();submitForm21('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="snomedCtId" />
								</b>
							</font>
						</div>
					</td>
					<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" />
				</logic:equal>	
				
				
				
				
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
					<td width="75%" >
						<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_ICD_CODE %>"/>
					</td>
				</logic:equal>
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">
					<td width="75%" >
						<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>"/>
					</td>
				</logic:equal>
				
				<logic:equal name="OpdDiagnosisFB" property="unitDiagnosisCodeType" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>">
					<td width="75%" >
						<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>"/>
					</td>
				</logic:equal>
				
				<%-- <!-- FOR ICD-O DEFAULT ICD Added by Vasu on 08-10-2018-->
				
			 	<%
						if ((unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_ICDO_DEFAULT_ICD_CODE))||(unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_ICD_DEFAULT_ICDO_CODE)))
						{
							System.out.println("-----------------------In 5,6(1)-----------------------------");
				%>
					<td width="75%" >
						<div align="left">
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="0" onclick="submitForm('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="icdCode" /></b>
							</font>
							<html:radio name="OpdDiagnosisFB" property="icdNHospitalFlag" value="4" onclick="showIdcHospitalDiagnosis();submitForm('NEWCHANGE');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="icdOCode" /></b>
							</font>
						</div>
					</td>
					<html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" />
			
				<%		} else if (unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_ICD_CODE)||unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_HOSPITAL_CODE)||unitDiagnosisCodeType.equalsIgnoreCase(OpdConfig.CHOICE_ICDO_CODE))
						{
				%><html:hidden name="OpdDiagnosisFB" property="icdNHospitalFlagValue" />
				<%
						}
				%> --%>
				 
				
				<td width="25%" >
					<div align="center">
						<img class="button" id="searchICD" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openPopup('/HISClinical/opd/opdDiagnosis.cnt?hmode=POPUP&icdNHospitalFlagValue='+document.forms[0].icdNHospitalFlagValue.value,event,300,600);">
					</div>
				</td>		
			</tr>
		</table>		
	</his:SubTitleTagBroad>

	<his:ContentTag>
		<table id="diagnosisTable" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="icdCode" />
								</b>
							</font>
						</div>
					</td>
					<td width="22%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>
								<bean:message key="disease" /></b>
							</font>
						</div>
					</td>
				</logic:equal>
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="diseaseCode" />
								</b>
							</font>
						</div>
					</td>
					<td width="22%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="disease" />
								</b>
							</font>
						</div>
					</td>
				</logic:equal>
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>">
				<%-- 	<td width="15%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
										<bean:message key="diseaseCode"/>
								</b>
							</font>
						</div>
					</td> --%>
					<td width="22%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="disease" />
								</b>
							</font>
						</div>
					</td>
				</logic:equal>
				
				<!-- Added by Vasu on 18.Oct.2018 for ICD-O Integration -->
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICDO_CODE %>">
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="sitecode" />
								</b>
							</font>
						</div>
					</td>
					<td width="22%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>
								<bean:message key="sitename" /></b>
							</font>
						</div>
					</td>
					<td width="8%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="morphCode" />
								</b>
							</font>
						</div>
					</td>
					<td width="22%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>
								<bean:message key="morphology" /></b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="diagonosisType" />
							</b>
						</font>
					</div>
				</td>
				</logic:equal>
				
				<!-- End Vasu -->
				
				
			<logic:notEqual name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICDO_CODE %>">
				
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="diagonosisType" />
							</b>
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="diagnosisSite" />
							</b>
						</font>
					</div>
				</td>
				</logic:notEqual>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b> <bean:message key="remark" /></b>
						</font>
					</div>
				</td>
				<td class="tdfonthead"></td>			
			</tr>
			<%
				String noOfRows = ((OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB")).getNumberOfRow();
				int totalRows = Integer.parseInt(noOfRows);
				for (int x = 0; x < totalRows; x++)
				{
			%>
			<tr>
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICD_CODE %>">
					<td class="tdfont">
						<div id="divIcdCodeList" style="display: none; position: absolute;">
							<select name="icdCodeList" id="icdCodeList" multiple="multiple" size="4">
							</select>
						</div>
						<div align="center" id='divFirstICD#<%=x%>'>
							<!--<html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="icdCode" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getIcdCode()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text>-->

							<html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="icdCode" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getIcdCode()[x] %>' 
								onchange = "setCorrICDCodeName(this)"
								></html:text>
							<script type="text/javascript">
								setSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("icdCode")[<%=x%>],elemDiseaseNameCodeList,NewDropDownSearch.SEARCH_TYPE["START"],true,true);
							</script>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<!-- <html:text name="OpdDiagnosisFB" property="dignosisName" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDignosisName()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'LABLE');" 
								onblur="callOnBlur(this)"></html:text> --> 
							
							<html:text name="OpdDiagnosisFB" property="dignosisName" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDignosisName()[x] %>' 
								onchange = "setCorrICDCodeName(this)"
								></html:text>
																
							<script type="text/javascript">
								setSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("dignosisName")[<%=x%>],elemDiseaseCodeNameList,NewDropDownSearch.SEARCH_TYPE["START"],true,false);
							</script>
						</div>
						
						
					</td>
				</logic:equal>
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_HOSPITAL_CODE %>">
					<td class="tdfont">
						<div id="divHospitalCodeList" style="display: none; position: absolute;">
							<select name="hospitalCodeList" id="hospitalCodeList" multiple="multiple" size="4">
							</select>
						</div>

						<div id="divDiseaseNameCodeList" style="display: none; position: absolute;">
							<select name="diseaseNameCodeList" id="diseaseNameCodeList" multiple="multiple" size="4">
								<logic:iterate name="<%=OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST%>" id="list">
									<option value="<bean:write name='list' property='label'/>" > <bean:write name='list' property='value'/></option>
								</logic:iterate>
							</select>
						</div>
						<div align="center">
							<!-- <html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="hospitalDiagnosisCode" tabindex="1"
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getHospitalDiagnosisCode()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text> --> 

							<html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="hospitalDiagnosisCode" tabindex="1"
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getHospitalDiagnosisCode()[x] %>'
								onchange = "setCorrICDCodeName(this)"
								></html:text>
								
							<script type="text/javascript">
								setHosSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("hospitalDiagnosisCode")[<%=x%>],document.getElementsByName("diseaseNameCodeList")[0],NewDropDownSearch.SEARCH_TYPE["START"],true,true);
							</script>
						</div>
					</td>
					<td class="tdfont">
						<div id="divDiseaseCodeNameList" style="display: none; position: absolute;">
							<select name="diseaseCodeNameList" id="diseaseCodeNameList" multiple="multiple" size="4">
								<logic:iterate name="<%=OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST%>" id="list">
									<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
								</logic:iterate>
							</select>
						</div>
						<div align="center">
							<!-- <html:text name="OpdDiagnosisFB" property="hospitalDiagnosisName" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getHospitalDiagnosisName()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'LABLE');" 
								onblur="callOnBlur(this)"></html:text> --> 

							<html:text name="OpdDiagnosisFB" property="hospitalDiagnosisName" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getHospitalDiagnosisName()[x] %>' 
								onchange = "setCorrICDCodeName(this)"
								></html:text>

							<script type="text/javascript">
								setHosSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("hospitalDiagnosisName")[<%=x%>],document.getElementsByName("diseaseCodeNameList")[0],NewDropDownSearch.SEARCH_TYPE["START"],true,false);
							</script>
						</div>
					</td>
				</logic:equal>
				
				
				
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>">
			          <!-- <td width="15%" class="tdfont" style="vertical-align: middle;">	 -->
					<div align="center" id="somectIddata" style="display:none">
						
					</div>				
				<!-- </td>
		 -->
			 <td width="22%" class="tdfont" style="vertical-align: middle;">	
				 	<div align="center" id="divsnomedCTDiagnosisName">
					<html:hidden name="OpdDiagnosisFB" property="snomedCTDiagnosisName" value="" ></html:hidden></div>
					<div align="center" id="divsnomedCTDiagnosisCode">
					<html:hidden name="OpdDiagnosisFB" property="snomedCTDiagnosisCode" value="" ></html:hidden></div>
					
					<div align="center" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:95%;color:#000000;" type="text">
					</div>
					 <div id="norecorddiv_1">
					 <label style="display: inline;" id="reccnt">No. of records : </label> 
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
					 </div>         
                     <div class="concept" id="conceptdiv_1">                 
                    </div>
                         </div>
					
					</div>
					
					</td>
					</logic:equal>
			        <logic:notEqual name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICDO_CODE %>">
			         
							<td class="tdfont">
					<%
						OpdDiagnosisFB dd = (OpdDiagnosisFB) pageContext.findAttribute("OpdDiagnosisFB");
						System.out.println("fb=================" + dd);
						System.out.println("getDiagonisticTypeCode=================" + dd.getDiagonisticTypeCode());
					%>
					
					<div align="center" id="divdiagnostictype">
						<html:select name="OpdDiagnosisFB" tabindex="1" styleId="diagnostictypeId"  property="diagonisticTypeCode" value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiagonisticTypeCode()[x] %>' >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.DIAGNOSIS_LIST%>" >
							<html:options collection="<%=RegistrationConfig.DIAGNOSIS_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				
				</logic:notEqual>
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>">
				 <td width="22%" class="tdfont" style="vertical-align: middle;">	
				 	<div align="center" id="divsnomedCTDiagnosisSiteName">
					<html:hidden name="OpdDiagnosisFB" property="snomedCTDiagnosisSiteName" value="" ></html:hidden></div>
					<div align="center" id="divsnomedCTDiagnosisSiteCode">
					<html:hidden name="OpdDiagnosisFB" property="snomedCTDiagnosisSiteCode" value="" ></html:hidden></div>
					
					<div align="center" id="divfindingsitesnomed" style="display:none" >
				    <div id="dialog-form_2" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_2"  style="width:95%;color:#000000;" type="text">
					</div>
					 <div id="norecorddiv_2">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_2">                 
                    </div>
                         </div>
					
					</div> 
					
					<div align="center" id="divfindingsite" style="display:none">
						<html:select name="OpdDiagnosisFB" tabindex="1" styleId="seldiagnosticSiteId"  property="selSnomedCTDiagnosisSiteCode" onchange="setSnomedSiteName()" >
							<html:option value="-1">Select Value</html:option>
					</html:select>
					</div>
						
					</td>
					
						

					<td class="tdfont">
					<html:hidden name="OpdDiagnosisFB" property="snomedPTRemarks" value="" ></html:hidden>
					<html:hidden name="OpdDiagnosisFB" property="snomedCIdRemarks" value="" ></html:hidden>
					<html:hidden name="OpdDiagnosisFB" property="remarks" value="" ></html:hidden>
						<%-- <html:textarea name="OpdEpisodeAllergyFB" property="adviceText" rows="2" cols="30" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"  tabindex='1'>
						</html:textarea> --%>
						<textarea name="txt-snomed-ct-search_4" rows="3" cols="40" id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX" value="" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" style="width:100%;" autocomplete="off"></textarea>
	
						</td>
					
				
				</logic:equal>
				
				<!-- Added by Vasu on 18.Oct.2018 for ICD-O Integration -->
				<logic:equal name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICDO_CODE %>">
				<td class="tdfont">				
						<div align="center" id='divFirstICD#<%=x%>'>
							<!--<html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="diseaseCode" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiseaseCode()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text>-->
							<html:hidden name="OpdDiagnosisFB" property="diseaseSiteId" 
							 value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiseaseSiteId()[x] %>'/>
							<html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="diseaseCode" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiseaseCode()[x] %>' 
								onchange = "setCorrSiteName(this)" onblur="callOnBlur()"
								></html:text>
							<script type="text/javascript">
								setSerachDropDownLists();
								//setDiseaseSiteSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("diseaseCode")[<%=x%>],elemDiseaseSiteCodeList,NewDropDownSearch.SEARCH_TYPE["START"],true,true);
							</script>
						</div>
					</td>
					<td class="tdfont">
										
						<div align="center">
							<!-- <html:text name="OpdDiagnosisFB" property="diseaseSite" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiseaseSite()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'LABLE');" 
								onblur="callOnBlur(this)"></html:text> --> 
							
							<html:text name="OpdDiagnosisFB" property="diseaseSite" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiseaseSite()[x] %>' 
								onchange = "setCorrSiteName(this)" onblur="callOnBlur()"
								></html:text>
																
							<script type="text/javascript">
								 setSerachDropDownLists();
								//setDiseaseSiteSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("diseaseSite")[<%=x%>],elemDiseaseSiteNameList,NewDropDownSearch.SEARCH_TYPE["START"],true,false);
							</script>
						</div>
										
					</td>
					
					<td class="tdfont">
						<div id="divMorphologyList" style="display: none; position: absolute;">
							<select name="morphologyNameList" id="morphologyNameList" multiple="multiple" size="4">
							</select>
						</div>
						<div align="center" id='divFirstMOR#<%=x%>'>
							<!--<html:text name="OpdDiagnosisFB" maxlength="6" size="6" property="morphCode" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getMorphCode()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'CODE');" 
								onblur="callOnBlur()"></html:text>-->

							<html:text name="OpdDiagnosisFB" maxlength="8" size="9" property="morphCode" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getMorphCode()[x] %>' 
								onchange = "setCorrMorphCodeName(this)" onblur="callOnBlur()"
								></html:text>
							<script type="text/javascript">
								setSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("morphCode")[<%=x%>],elemMorphologyCodeList,NewDropDownSearch.SEARCH_TYPE["START"],true,true);
							</script>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<!-- <html:text name="OpdDiagnosisFB" property="morphTitle" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getMorphTitle()[x] %>' 
								onkeypress="return validateAlphaNumOnly(this,event)" 
								onkeyup="gettext(event,this,'LABLE');" 
								onblur="callOnBlur(this)"></html:text> --> 
							
							<html:text name="OpdDiagnosisFB" property="morphTitle" maxlength="100" size="30" tabindex="1" 
								value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getMorphTitle()[x] %>' 
								onchange = "setCorrMorphCodeName(this)" onblur="callOnBlur()"
								></html:text>
																
							<script type="text/javascript">
								setSerachDropDownLists();
								NewDropDownSearch.setup(document.getElementsByName("morphTitle")[<%=x%>],elemMorphologyNameList,NewDropDownSearch.SEARCH_TYPE["START"],true,false);
							</script>
						</div>
										
					</td>
					
					<td class="tdfont">
					
					<div align="center">
						<html:select name="OpdDiagnosisFB" tabindex="1" property="diagonisticTypeCode" value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiagonisticTypeCode()[x] %>' >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.DIAGNOSIS_LIST%>" >
							<html:options collection="<%=RegistrationConfig.DIAGNOSIS_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				</logic:equal>
				<!-- End Vasu -->
				
				<logic:notEqual name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>">
				<logic:notEqual name="OpdDiagnosisFB" property="icdNHospitalFlag" value="<%=OpdConfig.CHOICE_ICDO_CODE %>">
				<td class="tdfont">
					<div align="center" id="divdiagnosticsite">
						<html:select name="OpdDiagnosisFB" tabindex="1" style="width:100px;" styleId="diagnosisSiteId" property="diagnosisSite" value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getDiagnosisSite()[x] %>' >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.DIAGNOSIS_SITE_LIST%>" >
							<html:options collection="<%=OpdConfig.DIAGNOSIS_SITE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				
				</logic:notEqual>
				<td class="tdfont">
					<div align="center" id="divremarks">
					
						<html:textarea name="OpdDiagnosisFB" property="remarks" 
							value='<%=((OpdDiagnosisFB)pageContext.findAttribute("OpdDiagnosisFB")).getRemarks()[x] %>' 
							tabindex="1" rows="1" cols="40" onkeypress="return (validateTextArea(event,this,'50') && validateAlphaNumOnly(this,event))"></html:textarea>
					</div>
				</td>
				</logic:notEqual>
				<td class="tdfont">
					<div align="center">
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' tabindex='1' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13)if(validateAdd())  AddRowToTable();;" onclick="if(validateAdd()) AddRowToTable(); ">
					</div>
				</td>
				
			</tr>
			<%
				}
			%>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<%-- <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='1' onclick="submitForm('SAVE');" onkeypress="if(event.keyCode==13)submitForm('SAVE');">
		 --%>
		<%--  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")> --%>
		<img class='button' src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" tabindex='1' onclick="validate();" onkeypress="if(event.keyCode==13)validate();;")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:statusTransactionInProcess>
	<his:statusDone>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusDone>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>

<div id="sid" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="selid" size="10" tabindex="1" onKeyDown="hideta(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
</div>
<html:hidden name="OpdDiagnosisFB" property="hmode" />
<html:hidden name="OpdDiagnosisFB" property="patCrNo" />
<html:hidden name="OpdDiagnosisFB" property="departmentUnitCode" />
<html:hidden name="OpdDiagnosisFB" property="selectedCode" />
<html:hidden name="OpdDiagnosisFB" property="numberOfRow" />
<html:hidden name="OpdDiagnosisFB" property="unitDiagnosisCodeType" />
<html:hidden name="OpdDiagnosisFB" property="diagnosisCodeType" />
<html:hidden name="OpdDiagnosisFB" property="comboOptionString" />
<html:hidden name="OpdDiagnosisFB" property="repeatedDiagnosisLength" />
<html:hidden name="OpdDiagnosisFB" property="revokeDiagCode" />
<html:hidden name="OpdDiagnosisFB" property="episodeVisitNo" />
<html:hidden name="OpdDiagnosisFB" property="revokeDiagicdNHospitalFlag" />
<html:hidden name="OpdDiagnosisFB" property="comboDiagnosisSite" />




<%
}
catch(Exception e)
{
	e.printStackTrace();
}


%>
