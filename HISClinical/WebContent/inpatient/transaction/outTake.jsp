<!-- 
/**
 * @copyright CDAC
 * @developer Hruday Meher
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="hisglobal.vo.PatIntakeOutDtlVO"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="inpatient.InpatientConfig"%>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtool.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<his:javascript src="/registration/js/registration.js" />
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function openHtml()
{
	window.open(createFHashAjaxQuery("miniPaint-master/index.jsp"));
	}
var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

var callbck_index =function(ret_OUT){setValue(ret_OUT);};




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
 clear(id);
 
 //  document.getElementById(ClientsID.txtantcedentcausecptid).value = "";
 //  document.getElementById(ClientsID.txtccptid).value = "";


});

function clear(id)
{
	//alert(id); 
	var elmtid=id.split('_');
	var trgt=elmtid[1];
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 document.getElementById(id).value="";
	
	 if(trgt=="2")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTintakeRemarks")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdintakeRemarks")[0];  //concept Id
		  document.getElementsByName("intakeRemarks")[0].value="";
		
		}
	 if(trgt=="1")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTRemarks")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdRemarks")[0];  //concept Id
		  document.getElementsByName("remarks")[0].value="";
		
		}
	 
		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}
window.onload = function()
{
	//if(document.getElementsByName("remarks")[0].value!="") document.getElementById("txt-snomed-ct-search_1").value=document.getElementsByName("remarks")[0].value+';';
	//if(document.getElementsByName("intakeRemarks")[0].value!="") document.getElementById("txt-snomed-ct-search_2").value=document.getElementsByName("intakeRemarks")[0].value+';';
	//alert(document.getElementById("txt-snomed-ct-search_2").value);
	load1('2',''); 
	load1('1','');
	
	
		
}

function load1(elmtId,semantictag)
{
	
	///alert("inside load");
	//alert(elmtId+','+semantictag);
	 
	  if(elmtId=="2")
		{

		 id="intakeRemarks";
		} 
	 if(elmtId=="1")
		{
		 id="remarks";
		}
	
	 setTarget(id);
	
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};


	if(elmtId==null || elmtId==undefined)
		{
		elmtId="1"; semantictag="DISORDER";
	
		}
	//elmtId="2";
	//alert(elmtId);
	//alert("1");
	selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	//alert("2");
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
 	 $("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
	
/*	$("#conceptdiv_3").hide();
	$("#norecorddiv_3").hide();
	$("#conceptdiv_4").hide();
	$("#norecorddiv_4").hide(); */
	 
	

}

function setValue(selectedSNOMEDTerm)
{
	//alert("hi");
	var target=document.getElementsByName("targetId")[0].value;
	
		
	var targetPrefferedTerm="";
	var targetConceptId="";
	
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	{
//		alert(selectedSNOMEDTerm);		
		/* var arr=selectedSNOMEDTerm.split("@@@");
		var str=arr[0];
		var str1=arr[1];
		 */
		 var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
		
	
	

	  if(target == "intakeRemarks") //visitNotes
		{
		//alert("inside vist note");	  
		 targetPrefferedTerm=document.getElementsByName("snomdPTintakeRemarks")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomdCIdintakeRemarks")[0];  //concept Id
			   
			  if(document.getElementsByName("intakeRemarks")[0].value=="") document.getElementsByName("intakeRemarks")[0].value = str;
			 else document.getElementsByName("intakeRemarks")[0].value=document.getElementsByName("intakeRemarks")[0].value + '; ' + str;
			

	
					 
		} 
	 if(target == "remarks") //visitNotes
		{
		//alert("inside vist note");	  
		 targetPrefferedTerm=document.getElementsByName("snomdPTRemarks")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomdCIdRemarks")[0];  //concept Id
			   
			  if(document.getElementsByName("remarks")[0].value=="") document.getElementsByName("remarks")[0].value = str;
			else document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks")[0].value + '; ' + str;
			 
				 
		}
	 
//alert(targetPrefferedTerm.value)
//alert(targetConceptI.value)
	 
	 if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "#" + str1;

}
}

function setfreeText()
{

	   document.getElementsByName("intakeRemarks")[0].value=document.getElementsByName("txt-snomed-ct-search_2")[0].value;  //for free text
	   document.getElementsByName("remarks")[0].value=document.getElementsByName("txt-snomed-ct-search_1")[0].value;  //for free text

		
}

function setTarget(id)
{
	//alert(id)
	
	 if(id=="intakeRemarks")
	{
		//alert(document.getElementsByName("targetId")[0].value)
	document.getElementsByName("targetId")[0].value="intakeRemarks";} 
	if(id=="remarks")
	{
		//alert(document.getElementsByName("targetId")[0].value)
	document.getElementsByName("targetId")[0].value="remarks";}
	
	
	
}


function validateAdd()
{
	if(checkDate()>1)
	{
		alert("Out Date Cannot be Less Than " + document.getElementsByName("prevDate")[0].value+"!");
		document.getElementsByName("outTakeDate")[0].focus();
		return false;
	}
	if(checkDate()<1 && checkDate()!=0)
	{
		alert("Out Date cannot be Greater Than Current Date!");
		document.getElementsByName("outTakeDate")[0].focus();
		return false;
	}
	if(!checkTime())
	{
		return false;
	}
	if(document.getElementsByName("inTakeOutParaId")[0].value=="-1")
	{
		alert("Please Select the Out Parameter!");
		document.getElementsByName("inTakeOutParaId")[0].focus();
		return false;
	}
	/*if(document.getElementsByName("outTakeRouteId")[0].value=="-1")
	{
		alert("Please Select The Route!");
		document.getElementsByName("outTakeRouteId")[0].focus();
		return false;
	}*/
	if(document.getElementsByName("volume")[0].value=="")
	{
		alert("Please Enter The Output Volume!");
		document.getElementsByName("volume")[0].focus();
		return false;
	}
	
	setfreeText();
	return true;
} 


function getOutTakeDetail(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/patientOutTake.cnt?hmode=VIEWOUTTAKE&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,700);
}

function getInTakeDetail(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/patientOutTake.cnt?hmode=VIEWINTAKE&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,700);
}

function deleteRow(obj1,obj2)
{
	
	document.getElementsByName("hiddenParaId")[0].value=obj1;
	document.getElementsByName("hiddenParaName")[0].value=obj2;
	submitForm('DELETEROW')
	
}

function checkDate()
{
	var valid=true;
	var days=0;
	var a=document.getElementsByName("outTakeDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
    days=((bdate-adate)/86400000);
   
    return days;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function checkTime()
{
	var days=checkDate();
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var minute=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var valid=true;
	
	if(days==0)
	{
		if(document.getElementsByName("outTakeTimeHr")[0].value=="")
		{
			alert("Please Enter the Output Hour");
			document.getElementsByName("outTakeTimeHr")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("outTakeTimeHr")[0].value)) > hour)
		{
			alert("Hour Cannot be Greater than "+ hour);
			document.getElementsByName("outTakeTimeHr")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("outTakeTimeMin")[0].value=="")
		{
			alert("Please Enter the Output Minute");
			document.getElementsByName("outTakeTimeMin")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("outTakeTimeMin")[0].value)) > parseInt("59"))
		{
			alert("Minute Cannot be Greater than 59");
			document.getElementsByName("outTakeTimeMin")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("outTakeTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("outTakeTimeMin")[0].value)) > minute)
		{
			alert("Minute Cannot be Greater than "+minute);
			document.getElementsByName("outTakeTimeMin")[0].focus();
			valid=false;
		}
	}
	else
	{
		if(days==1)
		{
			if(document.getElementsByName("outTakeTimeHr")[0].value=="")
			{
				alert("Please Enter the Output Hour");
				document.getElementsByName("outTakeTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("outTakeTimeHr")[0].value)) > parseInt("23"))
			{
				alert("Hour Cannot be Greater than 23");
				document.getElementsByName("outTakeTimeHr")[0].focus();
				valid=false;
			}
			else if(document.getElementsByName("outTakeTimeMin")[0].value=="")
			{
				alert("Please Enter the Output Minute");
				document.getElementsByName("outTakeTimeMin")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("outTakeTimeMin")[0].value)) > parseInt("59"))
			{
				alert("Minute Cannot be Greater than 59");
				document.getElementsByName("outTakeTimeMin")[0].focus();
				valid=false;
			}
		}
		if(days>1)
		{
			alert("Out Date Cannot be Less Than " + document.getElementsByName("prevDate")[0].value);
			document.getElementsByName("outTakeDate")[0].focus();
			valid=false;
		}
		if(days<1 && days!=0)
		{
			alert("Out Date Cannot be Greater Than Today");
			document.getElementsByName("outTakeDate")[0].focus();
			valid=false;
		}
	}
	return valid;
}

function validateIntakeAdd()
{
	if(checkIntakeDate()>1)
	{
		alert("Intake Date Cannot be Less Than " + document.getElementsByName("prevDate")[0].value);
		document.getElementsByName("inTakeDate")[0].focus();
		return false;
	}
	if(checkIntakeDate()<1 && checkIntakeDate()!=0)
	{
		alert("Intake Date Cannot be Greater Than Today");
		document.getElementsByName("inTakeDate")[0].focus();
		return false;
	}
	if(!checkIntakeTime())
	{
		return false;
	}
	if(document.getElementsByName("intakeParaId")[0].value=="-1")
	{
		alert("Please Select the In Parameter");
		document.getElementsByName("intakeParaId")[0].focus();
		return false;
	}
	/*if(document.getElementsByName("inTakeRouteId")[0].value=="-1")
	{
		alert("Please Select The Route");
		document.getElementsByName("inTakeRouteId")[0].focus();
		return false;
	}*/
	if(document.getElementsByName("intakeVolume")[0].value=="")
	{
		alert("Please Enter the Intake Volume");
		document.getElementsByName("intakeVolume")[0].focus();
		return false;
	}
	return true;
}

function checkIntakeDate()
{
	var valid=true;
	var days=0;
	var a=document.getElementsByName("inTakeDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
    days=((bdate-adate)/86400000);
   
    return days;
}

function checkIntakeTime()
{
	var days=checkIntakeDate();
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var minute=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var valid=true;
	
	if(days==0)
	{
		if(document.getElementsByName("inTakeTimeHr")[0].value=="")
		{
			alert("Please Enter the Intake Hour");
			document.getElementsByName("inTakeTimeHr")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("inTakeTimeHr")[0].value)) > hour)
		{
			alert("Hour Cannot be Greater than "+ hour);
			document.getElementsByName("inTakeTimeHr")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("inTakeTimeMin")[0].value=="")
		{
			alert("Please Enter the Intake Minute");
			document.getElementsByName("inTakeTimeMin")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("inTakeTimeMin")[0].value)) > parseInt("59"))
		{
			alert("Minute Cannot be Greater than 59");
			document.getElementsByName("inTakeTimeMin")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("inTakeTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("inTakeTimeMin")[0].value)) > minute)
		{
			alert("Minute Cannot be Greater than "+minute);
			document.getElementsByName("inTakeTimeMin")[0].focus();
			valid=false;
		}
	}
	else
	{
		if(days==1)
		{
			if(document.getElementsByName("inTakeTimeHr")[0].value=="")
			{
				alert("Please Enter the Intake Hour");
				document.getElementsByName("inTakeTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("inTakeTimeHr")[0].value)) > parseInt("23"))
			{
				alert("Hour Cannot be Greater than 23");
				document.getElementsByName("inTakeTimeHr")[0].focus();
				valid=false;
			}
			else if(document.getElementsByName("inTakeTimeMin")[0].value=="")
			{
				alert("Please Enter the Intake Minute");
				document.getElementsByName("inTakeTimeMin")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("inTakeTimeMin")[0].value)) > parseInt("59"))
			{
				alert("Minute Cannot be Greater than 59");
				document.getElementsByName("inTakeTimeMin")[0].focus();
				valid=false;
			}
		}
		if(days>1)
		{
			alert("Intake Date Cannot be Less Than " + document.getElementsByName("prevDate")[0].value);
			document.getElementsByName("inTakeDate")[0].focus();
			valid=false;
		}
		if(days<1 && days!=0)
		{
			alert("Intake Date Cannot be Greater Than Today");
			document.getElementsByName("inTakeDate")[0].focus();
			valid=false;
		}
	}
	return valid;
}

function deleteIntakeRow(obj1,obj2)
{
	
	document.getElementsByName("hiddenParaId")[0].value=obj1;
	document.getElementsByName("hiddenParaName")[0].value=obj2;
	submitForm('DELETEINTAKEROW')
	
}

function validateSave()
{
	var outPara=document.getElementsByName("inTakeOutParaId")[0].value;
	var outRoute=document.getElementsByName("outTakeRouteId")[0].value;
	var outVolume=document.getElementsByName("volume")[0].value;
	var inPara=document.getElementsByName("intakeParaId")[0].value;
	var inRoute=document.getElementsByName("inTakeRouteId")[0].value;
	var inVolume=document.getElementsByName("intakeVolume")[0].value;
	var intakeVO=document.getElementsByName("intakeVOExistFlag")[0].value;
	var outtakeVO=document.getElementsByName("outtakeVOExistFlag")[0].value;
	
	
	
	/*if(intakeVO == "1" || outtakeVO == "1")
	{alert("if");
		
		if(outPara!='-1' || outRoute!='-1' || outVolume!="")
		{
			if(!validateAdd() ) return false;
		}
		if(inPara!='-1' || inRoute!='-1' || inVolume!="")
		{
			if(!validateIntakeAdd()) return false;
		}
	}
	else
	{alert("else");*/
		if(outPara=='-1' && outRoute=='-1' && outVolume=="" && inPara=='-1' && inRoute=='-1' && inVolume=="")
		{
			alert("Please Enter a Record");
			return false;
		}
		else
		{
			if(outPara!='-1' || outRoute!='-1' || outVolume!="")
			{
				if(!validateAdd() ) return false;
			
			}
			if(inPara!='-1' || inRoute!='-1' || inVolume!="")
			{
				if(!validateIntakeAdd()) return false;
			
			}
		}
	//}
		setfreeText();
	return true;
		//submitForm('SAVE')
}

function hideUnhide(imgObj)
{
	var divObj=document.getElementById("intakeOuttakeSummary");
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function openSummaryPopup(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/patientOutTake.cnt?hmode=ALLSUMMARY&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,300,700);
}
</script>
<bean:define name="OutTakeFB" property="outTakeDate" id="toDate" type="java.lang.String"/>
	<% if(toDate==null||toDate.equalsIgnoreCase(""))
	   {
			toDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	   }	 
					
	%>
	<html:hidden name="OutTakeFB" property="sysDate" value="<%=toDate%>"/>			
<his:TitleTag name="Intake / Output Detail">
</his:TitleTag>


<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />

<his:SubTitleTag name="Output Detail">
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<%String path="/HISClinical/inpatient/patientOutTake.cnt?hmode=VIEW&selectedCrNo="; %>
							<a style="cursor:pointer" onclick="getOutTakeDetail(event,'<%= path%>')" >
      							<bean:message key="viewOutTakeDtl"/>
      						</a>
						</b>
					</font>		
				</div>
			</td>
		</tr>
	</table>
	
</his:SubTitleTag>

<logic:equal name="OutTakeFB" property="isIntakeOuttakeTimeBased" value="<%=InpatientConfig.INTAKE_OUTTAKE_TIME_BASED_YES %>">
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
		<tr>
			<td width="16%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="date"/>
						</b>
					</font>
				</div>
			</td>
			<td width="19%" class="tdfonthead" nowrap="nowrap">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="time"/>
							<bean:message key="timeFormat"/>
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="outPara"/>
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="route"/>
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="volume"/>(ml)
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remarks"/>
						</b>
					</font>
				</div>
			</td>
			<td width="5%" class="tdfonthead"></td>
		</tr>
		<tr>
			<td width="16%" class="tdfont">
				<div align="center">
					<his:date name="outTakeDate" dateFormate="%d-%b-%Y" value="<%=toDate %>" ></his:date>
				</div>
			</td>
			
			<td width="19%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="outTakeTimeHr" size="3" maxlength="2" onkeypress="return validateNumeric(event)"></html:text>
					:&nbsp;<html:text name="OutTakeFB" property="outTakeTimeMin" size="3" maxlength="2" onkeypress="return validateNumeric(event)"></html:text>
				</div>
			</td>
			<td width="15%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="inTakeOutParaId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="15%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="outTakeRouteId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_OUT_ROUTE_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_OUT_ROUTE_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="10%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="volume" maxlength="3" size="5" onkeypress="return validateNumeric(event)"></html:text>
				</div>
			</td>
				 <td width="20%" class="tdfont">
			
						<div align="center">
						  <html:hidden name="OutTakeFB" property="snomdPTRemarks" ></html:hidden>
					        <html:hidden name="OutTakeFB" property="snomdCIdRemarks" ></html:hidden>
					         <html:hidden name="OutTakeFB" property="remarks" ></html:hidden>  
					         	          <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_1"   style="width:90%;color:#000000;" type="text" onfocus="load1('1','');">
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
					      
							<!-- <textarea name="txt-snomed-ct-search_1" rows="1" cols="55" id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" value=""  onkeypress="load1('1','');" style="width:88%;" autocomplete="off"></textarea>
 -->					</div>
					
					</td> 
		 <%-- 	<td width="15%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="remarks" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
				</div>
			</td>	 --%> 
			<td width="5%" class="tdfont">
				<div align="center">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm('ADDROW') ;" onclick="if(validateAdd()) submitForm('ADDROW')">
				</div>
			</td>		
		</tr>
	</table>
</his:ContentTag>
<%if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL)!=null){ %>
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<logic:iterate id="addedOutTakeDtl" name="<%=InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL %>" type="hisglobal.vo.PatIntakeOutDtlVO">
			<tr>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=addedOutTakeDtl.getOutTakeDate() %>
					</div>
				</td>
				<td class="tdfont" width="19%" >
					<div align="center">
						<%=addedOutTakeDtl.getOutTakeTime() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedOutTakeDtl.getInTakeOutParaName() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedOutTakeDtl.getRouteName() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedOutTakeDtl.getVolume() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedOutTakeDtl.getRemarks() %>
					</div>
				</td>
				<td class="tdfont" width="5%" >
					<div align="center">
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=addedOutTakeDtl.getInTakeOutParaId() %>','<%=addedOutTakeDtl.getInTakeOutParaName() %>') ;" onclick=" deleteRow('<%=addedOutTakeDtl.getInTakeOutParaId() %>','<%=addedOutTakeDtl.getInTakeOutParaName() %>')">
						<html:hidden name="OutTakeFB" property="hiddenParaId"/>
						<html:hidden name="OutTakeFB" property="hiddenParaName"/>
					</div>
				</td>
			</tr>
		</logic:iterate>
	</table>	
</his:ContentTag>
<%} %>
</logic:equal>

<logic:equal name="OutTakeFB" property="isIntakeOuttakeTimeBased" value="<%=InpatientConfig.INTAKE_OUTTAKE_TIME_BASED_NO %>">
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="outPara"/>
						</b>
					</font>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="route"/>
						</b>
					</font>
				</div>
			</td>
			<td width="20%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="volume"/>(ml)
						</b>
					</font>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remarks"/>
						</b>
					</font>
				</div>
			</td>
			<td width="5%" class="tdfonthead"></td>
		</tr>
		<tr>
			
			<html:hidden name="OutTakeFB" property="outTakeDate" value="<%=toDate %>"/>
			<html:hidden name="OutTakeFB" property="outTakeTimeHr"/>
			<html:hidden name="OutTakeFB" property="outTakeTimeMin"/>
			<td width="25%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="inTakeOutParaId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="outTakeRouteId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_OUT_ROUTE_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_OUT_ROUTE_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="20%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="volume" maxlength="3" size="5" onkeypress="return validateNumeric(event)"></html:text>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="remarks" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
				</div>
			</td>	
			<td width="5%" class="tdfont">
				<div align="center">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm('ADDROW') ;" onclick="if(validateAdd()) submitForm('ADDROW')">
				</div>
			</td>		
		</tr>
	</table>
</his:ContentTag>
<%if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL)!=null){ %>
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<logic:iterate id="addedOutTakeDtl" name="<%=InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL %>" type="hisglobal.vo.PatIntakeOutDtlVO">
			<tr>
				
				<td class="tdfont" width="25%" >
					<div align="center">
						<%=addedOutTakeDtl.getInTakeOutParaName() %>
					</div>
				</td>
				<td class="tdfont" width="25%" >
					<div align="center">
						<%=addedOutTakeDtl.getRouteName() %>
					</div>
				</td>
				<td class="tdfont" width="20%" >
					<div align="center">
						<%=addedOutTakeDtl.getVolume() %>
					</div>
				</td>
				<td class="tdfont" width="25%" >
					<div align="center">
						<%=addedOutTakeDtl.getRemarks() %>
					</div>
				</td>
				<td class="tdfont" width="5%" >
					<div align="center">
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=addedOutTakeDtl.getInTakeOutParaId() %>','<%=addedOutTakeDtl.getInTakeOutParaName() %>') ;" onclick=" deleteRow('<%=addedOutTakeDtl.getInTakeOutParaId() %>','<%=addedOutTakeDtl.getInTakeOutParaName() %>')">
						<html:hidden name="OutTakeFB" property="hiddenParaId"/>
						<html:hidden name="OutTakeFB" property="hiddenParaName"/>
					</div>
				</td>
			</tr>
		</logic:iterate>
	</table>	
</his:ContentTag>
<%} %>
</logic:equal>

<his:SubTitleTag name="Intake Detail">
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getInTakeDetail(event)" >
      							<bean:message key="viewInTakeDtl"/>
      						</a>
						</b>
					</font>		
				</div>
			</td>
		</tr>
	</table>
</his:SubTitleTag>

<logic:equal name="OutTakeFB" property="isIntakeOuttakeTimeBased" value="<%=InpatientConfig.INTAKE_OUTTAKE_TIME_BASED_YES %>">
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
		<tr>
			<td width="16%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="date"/>
						</b>
					</font>
				</div>
			</td>
			<td width="19%" class="tdfonthead" nowrap="nowrap">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="time"/>
							<bean:message key="timeFormat"/>
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="inPara"/>
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="route"/>
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="volume"/>(ml)
						</b>
					</font>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remarks"/>
						</b>
					</font>
				</div>
			</td>
			<td width="5%" class="tdfonthead"></td>
		</tr>
		<tr>
		
			<td width="16%" class="tdfont">
				<div align="center">
					<his:date name="inTakeDate" dateFormate="%d-%b-%Y" value="<%=toDate %>" ></his:date>
				</div>
			</td>
			
			<td width="19%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="inTakeTimeHr" size="3" maxlength="2" onkeypress="return validateNumeric(event)"></html:text>
					:&nbsp;<html:text name="OutTakeFB" property="inTakeTimeMin" size="3" maxlength="2" onkeypress="return validateNumeric(event)"></html:text>
				</div>
			</td>
			<td width="15%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="intakeParaId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="15%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="inTakeRouteId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_IN_ROUTE_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_IN_ROUTE_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="10%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="intakeVolume" maxlength="3" size="5" onkeypress="return validateNumeric(event)"></html:text>
				
				</div>
			</td>
			 <td width="20%" class="tdfont">
			
						<div align="center">
						  <html:hidden name="OutTakeFB" property="snomdPTintakeRemarks" ></html:hidden>
					        <html:hidden name="OutTakeFB" property="snomdCIdintakeRemarks" ></html:hidden>
					         <html:hidden name="OutTakeFB" property="intakeRemarks" ></html:hidden> 
					          <div id="dialog-form_2" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_2"   style="width:90%;color:#000000;" type="text" onfocus="load1('2','');">
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
					      
							<!-- <textarea name="txt-snomed-ct-search_2" rows="1" cols="55" id="txt-snomed-ct-search_2" class="clearable ui-autocomplete-input x onX" value=""  onkeypress="load1('2','');" style="width:88%;" autocomplete="off"></textarea>
 -->					</div>
					
					</td> 
			<%--  <td width="15%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="intakeRemarks" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
				</div>
			</td>  --%>
			<td width="5%" class="tdfont">
				<div align="center">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' onkeypress="if(event.keyCode==13)if(validateIntakeAdd()) submitForm('ADDINTAKEROW') ;" onclick="if(validateIntakeAdd()) submitForm('ADDINTAKEROW')">
				</div>
			</td>		
		</tr>
	</table>
</his:ContentTag>		
<%if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL)!=null){ %>
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<logic:iterate id="addedInTakeDtl" name="<%=InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL %>" type="hisglobal.vo.PatIntakeOutDtlVO">
			<tr>
				<td class="tdfont" width="16%" >
					<div align="center">
						<%=addedInTakeDtl.getOutTakeDate() %>
					</div>
				</td>
				<td class="tdfont" width="19%" >
					<div align="center">
						<%=addedInTakeDtl.getOutTakeTime() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedInTakeDtl.getInTakeOutParaName() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedInTakeDtl.getRouteName() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedInTakeDtl.getVolume() %>
					</div>
				</td>
				<td class="tdfont" width="15%" >
					<div align="center">
						<%=addedInTakeDtl.getRemarks() %>
					</div>
				</td>
				<td class="tdfont" width="5%" >
					<div align="center">
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteIntakeRow('<%=addedInTakeDtl.getInTakeOutParaId() %>','<%=addedInTakeDtl.getInTakeOutParaName() %>') ;" onclick=" deleteIntakeRow('<%=addedInTakeDtl.getInTakeOutParaId() %>','<%=addedInTakeDtl.getInTakeOutParaName() %>')">
						<html:hidden name="OutTakeFB" property="hiddenParaId"/>
						<html:hidden name="OutTakeFB" property="hiddenParaName"/>
					</div>
				</td>
			</tr>
		</logic:iterate>
	</table>	
</his:ContentTag>
<%} %>
</logic:equal>

<logic:equal name="OutTakeFB" property="isIntakeOuttakeTimeBased" value="<%=InpatientConfig.INTAKE_OUTTAKE_TIME_BASED_NO %>">
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="inPara"/>
						</b>
					</font>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="route"/>
						</b>
					</font>
				</div>
			</td>
			<td width="20%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
							<bean:message key="volume"/>(ml)
						</b>
					</font>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remarks"/>
						</b>
					</font>
				</div>
			</td>
			<td width="5%" class="tdfonthead"></td>
		</tr>
		<tr>
			
			<html:hidden name="OutTakeFB" property="inTakeDate" value="<%=toDate %>"/>
			<html:hidden name="OutTakeFB" property="inTakeTimeHr"/>
			<html:hidden name="OutTakeFB" property="inTakeTimeMin"/>
			<td width="25%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="intakeParaId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
					<html:select name="OutTakeFB"  property="inTakeRouteId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ESSENTIAL_IN_ROUTE_LIST%>">
						<html:options collection="<%= InpatientConfig.ESSENTIAL_IN_ROUTE_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="20%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="intakeVolume" maxlength="3" size="5" onkeypress="return validateNumeric(event)"></html:text>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
					<html:text name="OutTakeFB" property="intakeRemarks" maxlength="50" onkeypress="return validateAlphaNumericOnly(event)"></html:text>
				</div>
			</td>	
			<td width="5%" class="tdfont">
				<div align="center">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' onkeypress="if(event.keyCode==13)if(validateIntakeAdd()) submitForm('ADDINTAKEROW') ;" onclick="if(validateIntakeAdd()) submitForm('ADDINTAKEROW')">
				</div>
			</td>		
		</tr>
	</table>
</his:ContentTag>		
<%if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL)!=null){ %>
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<logic:iterate id="addedInTakeDtl" name="<%=InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL %>" type="hisglobal.vo.PatIntakeOutDtlVO">
			<tr>
				<td class="tdfont" width="25%" >
					<div align="center">
						<%=addedInTakeDtl.getInTakeOutParaName() %>
					</div>
				</td>
				<td class="tdfont" width="25%" >
					<div align="center">
						<%=addedInTakeDtl.getRouteName() %>
					</div>
				</td>
				<td class="tdfont" width="20%" >
					<div align="center">
						<%=addedInTakeDtl.getVolume() %>
					</div>
				</td>
				<td class="tdfont" width="25%" >
					<div align="center">
						<%=addedInTakeDtl.getRemarks() %>
					</div>
				</td>
				<td class="tdfont" width="5%" >
					<div align="center">
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteIntakeRow('<%=addedInTakeDtl.getInTakeOutParaId() %>','<%=addedInTakeDtl.getInTakeOutParaName() %>') ;" onclick=" deleteIntakeRow('<%=addedInTakeDtl.getInTakeOutParaId() %>','<%=addedInTakeDtl.getInTakeOutParaName() %>')">
						<html:hidden name="OutTakeFB" property="hiddenParaId"/>
						<html:hidden name="OutTakeFB" property="hiddenParaName"/>
					</div>
				</td>
			</tr>
		</logic:iterate>
	</table>	
</his:ContentTag>
<%} %>
</logic:equal>

<his:SubTitleTagBroad name="Intake / Output Summary">
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<div align="right" >
					  <img class='button' src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  style=cursor:pointer onclick ="openSummaryPopup(event)" onkeypress="if(event.keyCode==13) openSummaryPopup(event)")>
					<img  tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
				</div>
			</td>
		</tr>
	</table>	
</his:SubTitleTagBroad>
<div id="intakeOuttakeSummary" style="display: none;">
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="50%" valign="top">
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="50%" colspan="2" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="intakeSummary"/>
									</font>
								</div>
							</td>
						</tr>	
						<logic:iterate id="patIntakeSummary" name="<%=InpatientConfig.PATIENT_INTAKE_SUMMARY %>" type="hisglobal.vo.PatIntakeOutDtlVO">
							<tr>
								<td width="50%" class="tdfont">
									<div align="center">
										<%=patIntakeSummary.getInTakeOutParaName() %>
									</div>
								</td>
								<td width="50%" class="tdfont">
									<div align="center">
										<%=patIntakeSummary.getVolume() %> ML
									</div>
								</td>
							</tr>
						</logic:iterate>
						<%PatIntakeOutDtlVO[] patIntakeOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.PATIENT_INTAKE_SUMMARY); 
							if(patIntakeOutDtlVO.length==0){
							%>
								<tr>
									<td width="50%" colspan="2" class="tdfont">
										<div align="center">
											<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="OutTakeFB" property="intakeSummaryStatus"/>
											</font>	
										</div>
									</td>
								</tr>
							<%} %>					
					</table>
				</his:ContentTag>
			</td>
			<td width="50%" valign="top">
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="50%" colspan="2" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="outtakeSummary"/>
									</font>
								</div>
							</td>
						</tr>	
						<logic:iterate id="patOuttakeSummary" name="<%=InpatientConfig.PATIENT_OUTTAKE_SUMMARY %>" type="hisglobal.vo.PatIntakeOutDtlVO">
							<tr>
								<td width="50%" class="tdfont">
									<div align="center">
										<%=patOuttakeSummary.getInTakeOutParaName() %>
									</div>
								</td>
								<td width="50%" class="tdfont">
									<div align="center">
										<%=patOuttakeSummary.getVolume() %> ML
									</div>
								</td>
							</tr>
						</logic:iterate>
							<%PatIntakeOutDtlVO[] patOuttakeOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.PATIENT_OUTTAKE_SUMMARY); 
							if(patOuttakeOutDtlVO.length==0){
							%>
								<tr>
									<td width="50%" colspan="2" class="tdfont">
										<div align="center">
											<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="OutTakeFB" property="outtakeSummaryStatus"/>
											</font>	
										</div>
									</td>
								</tr>
							<%} %>
					</table>
				</his:ContentTag>
			</td>
		</tr>
		<tr>
			<td width="50%" valign="top">
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="totalIntake"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="OutTakeFB" property="totalIntake"/> ML
									</font>	
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</td>
			<td width="50%" valign="top">
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="totalOuttake"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="center" >
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="OutTakeFB" property="totalOuttake"/> ML
									</font>	
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</td>
		</tr>
	</table>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="balance"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="75%" class="tdfont">
				<div align="left" >
					<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<bean:write name="OutTakeFB" property="balance"/> ML
					</font>	
				</div>
			</td>
		</tr>
	</table>
</his:ContentTag>
</div>
<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="if(validateSave())submitForm21('SAVE')" onkeypress="if(event.keyCode==13) if(validateSave())submitForm21('SAVE')">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          
</his:ButtonToolBarTag>
<html:hidden name="OutTakeFB" property="hmode"/>
<html:hidden name="OutTakeFB" property="patCrNo"/>
<html:hidden name="OutTakeFB" property="hiddenTimeHr"/>
<html:hidden name="OutTakeFB" property="hiddenTimeMin"/>
<html:hidden name="OutTakeFB" property="intakeVOExistFlag"/>
<html:hidden name="OutTakeFB" property="outtakeVOExistFlag"/>
<html:hidden name="OutTakeFB" property="prevDate"/>
<html:hidden name="OutTakeFB" property="isIntakeOuttakeTimeBased"/>
<input type="hidden" name="targetId" />
