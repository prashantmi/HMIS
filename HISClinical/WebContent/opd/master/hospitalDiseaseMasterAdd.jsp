
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/map.css"> -->
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">

<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">


<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/map.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head> 

<script type="text/javascript">

window.onload = function()
{
	
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};

	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	//autoselectSNOMEDCT( '1', 'ACTIVE', 'DISORDER', 'SYNONYMS', 10, callback_cpt);
	//selectSNOMEDCTauto('ACTIVE','DISORDER','SYNONYMS','10','61000189103','1',callbck_index);
	selectSNOMEDCTauto('ACTIVE','DISORDER','SYNONYMS','10','null','1',callbck_index);
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	document.getElementsByName("mappedICDCode").value="";
	document.getElementsByName("mappedICDName").value="";
	document.getElementsByName("mappedSnomed").value="";
	document.getElementsByName("mappedSnomedName").value="";
//snomd new

}

function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	//var arr=selectedSNOMEDTerm.split(",");
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str); alert(str1);
	 
	document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
		}
	else
		{
		document.getElementsByName("prefferedTerm")[0].value="";
		document.getElementsByName("conceptId")[0].value="";
		}
}


function selectValue(value, callback) {
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	

}



//snomd new end


/* function snomedctIdDtl()
{
 var alertId= document.getElementsByName("patAlertId")[0].value;
 if(alertId == "-1")
 {
 	document.getElementById("somecetIddata").innerHTML =" ";
 }
 else
 { 
	 var id=alertId.split("#");
	 var somedId=id[1];
	  //alert(alertId);
	  //alert(somedId);
	 document.getElementsByName("snomedCtId")[0].value=somedId;
	 document.getElementById("somecetIddata").innerHTML =somedId;
}
 return true;

} */



function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode;
	//alert(document.getElementsByName('hmode')[0].value);
	if(document.getElementsByName('hmode')[0].value=="SAVE" || document.getElementsByName('hmode')[0].value=="MODIFYSAVE")
		{
			if(validateData())
			{
				//alert(document.getElementsByName('hmode')[0].value);
				mapSelected();		
				document.forms[0].submit();
			}
		}	
	else
			document.forms[0].submit();
}

function validateData()
{
	//alert(document.getElementsByName("diseaseName")[0].value);
	if(document.getElementsByName("diseaseName")[0].value=="")
	{
		alert("Please Enter Disease Name");
		document.getElementsByName("diseaseName")[0].focus();
		return false;
	}
	
	//alert(document.getElementsByName("diseaseType")[0].value);
	if(document.getElementsByName("diseaseType")[0].value=="" || document.getElementsByName("diseaseType")[0].value=="-1")
	{
		alert("Please Select Disease Type");
		document.getElementsByName("diseaseType")[0].focus();
		return false;
	}
	//alert(document.getElementsByName("mappedICDCode").length);
	if(document.getElementsByName("mappedICDCode").length==0)
	{
					
				alert("Please Select ICD Code");
				document.getElementsByName("icdCode")[0].focus();
				return false;
	}
	
		//alert(document.getElementsByName("mappedSnomed").length);
		if(document.getElementsByName("mappedSnomed").length==0)
		{
			
				alert("Please Select Snomed Concept");
				document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
				return false;
			
		}
		
		
		//alert(document.getElementsByName("hmode")[0].value);
		if(document.getElementsByName("hmode")[0].value=="MODIFYSAVE")
		{
			//alert(document.getElementsByName("mappedICDCodeOLD").length);
			if(document.getElementsByName("mappedICDCodeOLD").length==0)
			{
				alert("Please Select ICD Code");
				document.getElementsByName("icdCode")[0].focus();
				return false;
			}
		}
		
		if(document.getElementsByName("hmode")[0].value=="MODIFYSAVE")
		{
			//alert(document.getElementsByName("mappedSnomedIDOLD").length);
			if(document.getElementsByName("mappedSnomedIDOLD").length==0)
			{
				alert("Please Select Snomed Concept");
				document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
				return false;
			}
		}
		return true;
}

function mapSelected()
{
	var temp;
	//alert(document.getElementsByName("hmode")[0].value);
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedICDCode").length;i++)	
		{
			if(i==0)
			temp = document.getElementsByName("mappedICDCode")[i].value;
		else
			temp=temp+'#'+document.getElementsByName("mappedICDCode")[i].value;
		}
		document.getElementsByName("selIcdArray")[0].value=temp;
		//alert(document.getElementsByName("selIcdArray")[0].value);
	}
	else if(document.getElementsByName("hmode")[0].value=="MODIFYSAVE")
	{
		temp="";
		for(var i=0;i<document.getElementsByName("mappedICDCodeOLD").length;i++)	
		{
			if(i==0)
				temp = document.getElementsByName("mappedICDCodeOLD")[i].value;
			else
				temp=temp+'#'+document.getElementsByName("mappedICDCodeOLD")[i].value;
		}		
		for(var i=0;i<document.getElementsByName("mappedICDCode").length;i++)	
		{
			temp=temp+'#'+document.getElementsByName("mappedICDCode")[i].value;
		}
		document.getElementsByName("selIcdArray")[0].value=temp;
		
	}
	//alert(document.getElementsByName("selIcdArray")[0].value);
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedICDName").length;i++)	
		{
			if(i==0)
			temp = document.getElementsByName("mappedICDName")[i].value;
		else
			temp=temp+'#'+document.getElementsByName("mappedICDName")[i].value;
		}
		document.getElementsByName("selIcdNameArray")[0].value=temp;
		//alert(document.getElementsByName("selIcdNameArray")[0].value);
	}
	else if(document.getElementsByName("hmode")[0].value=="MODIFYSAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedIcdNameOLD").length;i++)	
		{
			if(i==0)
				temp = document.getElementsByName("mappedIcdNameOLD")[i].value;
			else
				temp=temp+'#'+document.getElementsByName("mappedIcdNameOLD")[i].value;
		}
		for(var i=0;i<document.getElementsByName("mappedICDName").length;i++)	
		{
			temp=temp+'#'+document.getElementsByName("mappedICDName")[i].value;
		}
		document.getElementsByName("selIcdNameArray")[0].value=temp;	
		//alert("hi"+document.getElementsByName("selIcdNameArray")[0].value);
	}
	//alert(document.getElementsByName("selIcdNameArray")[0].value);
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedSnomed").length;i++)	
		{
			if(i==0)
			temp = document.getElementsByName("mappedSnomed")[i].value;
		else
			temp=temp+'#'+document.getElementsByName("mappedSnomed")[i].value;
		}
		document.getElementsByName("selsnomedArray")[0].value=temp;
		//alert(document.getElementsByName("selsnomedArray")[0].value);
	}
	else if(document.getElementsByName("hmode")[0].value=="MODIFYSAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedSnomedIDOLD").length;i++)	
		{
			if(i==0)
				temp = document.getElementsByName("mappedSnomedIDOLD")[i].value;
			else
				temp=temp+'#'+document.getElementsByName("mappedSnomedIDOLD")[i].value;
		}	
		for(var i=0;i<document.getElementsByName("mappedSnomed").length;i++)	
		{	
				temp=temp+'#'+document.getElementsByName("mappedSnomed")[i].value;
		}//alert("temp:"+temp);
		document.getElementsByName("selsnomedArray")[0].value=temp;
		//alert(document.getElementsByName("selsnomedArray")[0].value);
	}
	//alert(document.getElementsByName("selsnomedArray")[0].value);	
	if(document.getElementsByName("hmode")[0].value=="SAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedSnomedName").length;i++)	
		{
			if(i==0)
			temp = document.getElementsByName("mappedSnomedName")[i].value;
		else
			temp=temp+'#'+document.getElementsByName("mappedSnomedName")[i].value;
		}
		document.getElementsByName("selSnomedNameArray")[0].value=temp;
		//alert(document.getElementsByName("selSnomedNameArray")[0].value);
	}
	else if(document.getElementsByName("hmode")[0].value=="MODIFYSAVE")
	{
		
		for(var i=0;i<document.getElementsByName("mappedSnomedTermOLD").length;i++)	
		{
			if(i==0)
				temp = document.getElementsByName("mappedSnomedTermOLD")[i].value;
			else
				temp=temp+'#'+document.getElementsByName("mappedSnomedTermOLD")[i].value;
		}		
		for(var i=0;i<document.getElementsByName("mappedSnomedName").length;i++)	
		{	
				temp=temp+'#'+document.getElementsByName("mappedSnomedName")[i].value;
		//alert("temp:"+temp);
		}
		document.getElementsByName("selSnomedNameArray")[0].value=temp;
		//alert(document.getElementsByName("selSnomedNameArray")[0].value);
	}
	//alert(document.getElementsByName("selSnomedNameArray")[0].value);
		return true;
}
function validateAddICD()
{
	
	if(document.getElementsByName("icdCode")[0].value=="" || document.getElementsByName("icdCode")[0].value=="-1")
	{
		alert("Please Select The ICD Code");
		document.getElementsByName("icdCode")[0].focus();
		return false;		
	}
	var elemMappedICDs = document.getElementsByName("mappedICDCode");
	for(var i=0; i<elemMappedICDs.length;i++)
	{
		if(elemMappedICDs[i].value==document.getElementsByName("icdCode")[0].value)
			{
			alert("ICD Code alreddy mapped..");
			document.getElementsByName("icdCode")[0].focus();
			return false;
			}
	}
	
	if(document.getElementsByName("hmode")[0].value=="MODIFY")
	{
		var elemSelectedICDs = document.getElementsByName("mappedICDCodeOLD");
		for(var i=0; i<elemSelectedICDs.length;i++)
		{
			if(elemSelectedICDs[i].value.trim()==document.getElementsByName("icdCode")[0].value.trim())
				{
				alert("ICD Code alreddy mapped..");
				document.getElementsByName("icdCode")[0].focus();
				return false;
				}
		}
	}
	//document.getElementsByName("icdName")[0].value=document.getElementsByName("icdCode")[0].text;
	//alert(document.getElementsByName("icdCode")[0].text);
	return true;
}	
function mapICD()
{
	var elemDiv = document.getElementById("divMappedICD");
	var icdCode = document.getElementsByName("icdCode")[0].value;
	var icdName = document.getElementsByName("icdCode")[0].options[document.getElementsByName("icdCode")[0].selectedIndex].text;
	
	var htmlCode = "<table id='tblmapICD#"+icdCode+"'cellpadding='0' cellspacing='1' align='left'><tr><td>"+icdName+"<input type='hidden' name='mappedICDCode' value='"+icdCode+"'/><input type='hidden' name='mappedICDName' value='"+icdName+"'/></td><td><img src='/HIS/hisglobal/images/minus.gif' style='cursor:pointer;vertical-align: middle;' onclick ='removeICD("+icdCode+");' onkeypress='if(event.keyCode==13) removeICD("+icdCode+");'/></td></tr></table>"
	elemDiv.innerHTML += htmlCode;
}	
function removeICD(tid)
{
	//alert("Remove ICD"+tid);
	var elem = document.getElementById("tblmapICD#"+tid);

	if (typeof elem != 'undefined')
	{
	  elem.parentNode.removeChild(elem);
	}
}
function validateAdd()
{
		
		if(document.getElementsByName("txt-snomed-ct-search_1")[0].value=="")
		{
			alert("Please Select The Snomed Concept");
			document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
			return false;		
		}
		
		var elemMappedSnomeds = document.getElementsByName("mappedSnomed");
		for(var i=0; i<elemMappedSnomeds.length;i++)
		{
			if(elemMappedSnomeds[i].value==document.getElementsByName("conceptId")[0].value)
				{
				alert("Snomed Concept alreddy mapped..");
				document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
				return false;
				}
		}
		
		if(document.getElementsByName("hmode")[0].value=="MODIFY")
		{
			var elemSelectedSnomeds = document.getElementsByName("mappedSnomedIDOLD");
			for(var i=0; i<elemSelectedSnomeds.length;i++)
			{
				if(elemSelectedSnomeds[i].value==document.getElementsByName("conceptId")[0].value)
					{
					alert("Snomed Concept alreddy mapped..");
					document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
					return false;
					}
			}
		}
	return true;
}

 function snomedtoICDMapping(conceptID)
{
	
	 $.ajax({
		 
			url : createFHashAjaxQuery("/AHIMSG5/snomedct/csnoserv/api/map/icdmap?id="+conceptID ) ,
			type : 'GET' ,
			
			datatype : "json" ,
			 async : false ,
			  success : function(data) {
				var obj =  jQuery.parseJSON(data); 
				
		     	  if(obj.mapStatus == 'MAP_FOUND' || obj.mapStatus== 'INDETERMINATE_MAP')
		     	  { 
		     	   for(var i=0;i<obj.mapGroup.length;i++)
					{
					  var icdCode=obj.mapGroup[i].mappedICDCode;
				      searchICD(icdCode);
				      
				    }
		     	  }
			   },
			   
		error : function(data)
		{
    	alert('request failed :');
		}  });
} 


function searchICD(icdCode)
{
	var elemDiv = document.getElementById("divMappedICD");
	var opts =  document.getElementsByName("icdCode")[0];
	var icdName="";
	var comboval="";
	//alert(opts.length);
	//alert(icdCode);
	// var icdarr=icdCode.split('.');
	//var icd=icdarr[0];
	//alert(trimData(icdCode));
	
	for(var i = 0 ; i < opts.length; i++)
	{
		 comboval =opts.options[i].value;
		 //alert(opts.options[i].value);
		if(trimData(icdCode)==trimData(comboval))
		{
	//	document.getElementsByName("icdCode")[0].selectedIndex=i;
		// alert("matched"+i);
		
		// icdName =  opts.options[document.getElementsByName("icdCode")[0].selectedIndex].text;
	   	 icdName =  opts.options[i].text; 
	   	 var htmlCode = "<table id='tblmapICD#"+icdCode+"'cellpadding='0' cellspacing='1' align='left'><tr><td>"+icdName+"<input type='hidden' name='mappedICDCode' value='"+icdCode+"'/><input type='hidden' name='mappedICDName' value='"+icdName+"'/></td><td><img src='/HIS/hisglobal/images/minus.gif' style='cursor:pointer;vertical-align: middle;' onclick ='removeICD("+icdCode+");' onkeypress='if(event.keyCode==13) removeICD("+icdCode+");'/></td></tr></table>"
		 elemDiv.innerHTML += htmlCode;
		  // alert(icdName);
 	     break;
	 }
	}
	  	
}


function mapSnomed()
{
	var elemDiv = document.getElementById("divMappedSnomed");
	var preTerm = document.getElementsByName("prefferedTerm")[0].value;
	var conceptID = document.getElementsByName("conceptId")[0].value;
	//alert("preTerm "+preTerm+"& Concept ID "+conceptID);
	//alert("hi");
	var htmlCode = "<table id='tblmapSnomed#"+conceptID+"'cellpadding='0' cellspacing='1' align='left'><tr><td>"+preTerm+"<input type='hidden' name='mappedSnomed' value='"+conceptID+"'/><input type='hidden' name='mappedSnomedName' value='"+preTerm+"'/></td><td><img src='/HIS/hisglobal/images/minus.gif' style='cursor:pointer;vertical-align: middle;' onclick ='removeSnomed();' onkeypress='if(event.keyCode==13) removeSnomed();'/></td></tr></table>"
	elemDiv.innerHTML += htmlCode;
	
	snomedtoICDMapping(conceptID);
	
	
}	



function removeSnomed()
{
	//alert("Remove Snomed Concept");
}

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenAlertName")[0].value=obj1;
	document.getElementsByName("hiddenPatAlertId")[0].value=obj2;
	submitForm('DELETEROW');
}

function revokeAlerts(obj1,obj2)
{
	//alert(obj1);
	var alertId=obj1;
	var index=obj2;
	document.getElementsByName("revokeAlertId")[0].value=alertId;
	document.getElementsByName("index")[0].value=index;
	if(document.getElementsByName("revokeRemarks")[index].value=="")
	{
		alert("Please Enter The Revoke Remark");
		document.getElementsByName("revokeRemarks")[index].focus();
		return false;
	}
	else
	{
		submitForm('REVOKE');
	}	
}

function getAllAlerts(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/opd/genericPatientAlert.cnt?hmode=ALLALERT&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,800);
}

function showDayCalculator(event)
{
	var path='/HISClinical/opd/genericPatientAlert.cnt?hmode=DAYCAL';
	openPopup(createFHashAjaxQuery(path),event,200,500);
}

function checkDuration()// in years
{
	var days=0;
	var dob=document.getElementsByName("dob")[0].value;
	var sDate=document.getElementsByName("sysDate")[0].value;
	var dobArray=dob.split("-");
	
	var dobday=dobArray[0];
    var dobmonth=dobArray[1];
    var dobyear=dobArray[2];
    var dobdate=new Date(dobmonth +" "+ dobday+" "+dobyear);
    
    var sysArray=sDate.split("-");
	
	var sysday=sysArray[0];
    var sysmonth=sysArray[1];
    var sysyear=sysArray[2];
    var sysdate=new Date(sysmonth +" "+ sysday+" "+sysyear);
   
    days=(sysdate-dobdate)/86400000;
    document.getElementsByName("dayOnAge")[0].value=Math.floor(days/365);
    return (days/365);
}

function setRevokeRemark(index)
{
	if(document.getElementsByName("revokeChronics")[index].checked)
		document.getElementsByName("revokeRemarks")[index].disabled = false;
	else
	{
		document.getElementsByName("revokeRemarks")[index].value = "";
		document.getElementsByName("revokeRemarks")[index].disabled = true;
	}
}



</script>
<html:form action="/master/hospitalDiseaseMasterNew">
 


<his:ContentTag>
	<logic:equal name="HospitalDiseaseMasterFB" property="hmode" value="ADD">
	<his:TitleTag name="Hospital Disease Master>>ADD">
</his:TitleTag>
		<table width="100%" cellpadding="0" cellspacing="1" align="center">
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="diseaseName"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="60%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:text name="HospitalDiseaseMasterFB" property="diseaseName"  onkeypress="return validateAlphaNumOnly(this,event)" style="vertical-align: middle;"  ></html:text>
					</div>
				</td>
				
			</tr>
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="disType"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="60%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:select name="HospitalDiseaseMasterFB" property="diseaseType" style="width:25%;"  >
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=OpdConfig.EssentialBO_LIST_DISEASE_TYPE%>"	property="value" labelProperty="label" />
						</html:select>
					</div>
				</td>
			</tr>
			
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="icdCode"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="60%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:select name="HospitalDiseaseMasterFB" property="icdCode" style="width:25%;" >
						<html:option value="-1">Select Value</html:option>
					 	<html:options collection="<%=OpdConfig.EssentialBO_LIST_ICD_DISEASE%>"	property="value" labelProperty="label" />
					 	
						</html:select>
						<html:hidden name="HospitalDiseaseMasterFB" property="icdName"></html:hidden>
					
					
						<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer;vertical-align: middle;" onclick ="if(validateAddICD()) mapICD();" onkeypress="if(event.keyCode==13) if(validateAddICD()) mapICD();">
					</div>
				</td>	
			</tr>
			<tr>
				<td>
					<div align="left" id="divMappedICD">
					</div>
				</td>
			</tr>
			
				
					<!-- <span id="mapResult"></span> -->
			
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="snomedCtId"/>
							</b>	
						</font>
					</div>				
				</td>
				<td width="30%"  class="tdfont">
						
					<div id="divSnomed" style="display:block">	
					<html:hidden 	name="HospitalDiseaseMasterFB" property="prefferedTerm" ></html:hidden>
					<html:hidden name="HospitalDiseaseMasterFB" property="conceptId" ></html:hidden>
					<div align="left" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:80%;color:#000000;" type="text">
					<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer;vertical-align: middle;" onclick ="if(validateAdd()) mapSnomed()" onkeypress="if(event.keyCode==13) if(validateAdd()) mapSnomed();">
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
					
						
					</div>
				</td>			
				<!-- <td class="tdfont" align="right" width="30%">
					<button id="mapConceptId" onclick=" setValuesForMap(); validate(); " type="button" style="height:25px; width:25%; cursor:pointer;vertical-align: middle;"> Map ICD </button>
							
				</td> -->
			</tr>
			<tr>
				<td>
					<div align="left" id="divMappedSnomed">
					</div>
				</td>
			</tr>
			
			
			
			
			
			<tr>
				<td width="40%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remark"/></b>	
						</font>
					</div>
				</td>
				<td width="60%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:textarea name="HospitalDiseaseMasterFB" property="remarks" tabindex="1" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" style="vertical-align: middle;"></html:textarea>
					</div>
				</td>					
			</tr>	
			
		</table>
		</logic:equal>	 
		
<logic:equal name="HospitalDiseaseMasterFB" property="hmode" value="MODIFY">
	<his:TitleTag name="Hospital Disease Master>>Modify">
</his:TitleTag>
		<table width="100%" cellpadding="0" cellspacing="1" align="center">
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="diseaseName"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:text name="HospitalDiseaseMasterFB" property="diseaseName"  onkeypress="return validateAlphaNumOnly(this,event)" style="vertical-align: middle;"></html:text>
					</div>
				</td>
				
			</tr>
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="disType"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:select name="HospitalDiseaseMasterFB" property="diseaseType" style="width:25%;"  >
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=OpdConfig.EssentialBO_LIST_DISEASE_TYPE%>"
							property="value" labelProperty="label" />
						</html:select>
					</div>
				</td>
			</tr>
			
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="icdCode"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" style="vertical-align: middle;" colspan="2">	
					<div align="left">
						<html:select name="HospitalDiseaseMasterFB" property="icdCode" style="width:25%;"  >
						<html:option value="-1">Select Value</html:option>
					 	<html:options collection="<%=OpdConfig.EssentialBO_LIST_ICD_DISEASE%>"
							property="value" labelProperty="label" />
					 	
						</html:select>
						<html:hidden name="HospitalDiseaseMasterFB" property="icdName"></html:hidden>
					
					
						<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer;vertical-align: middle;" onclick ="if(validateAddICD()) mapICD();" onkeypress="if(event.keyCode==13) if(validateAddICD()) mapICD();">
					</div>
				</td>	
			</tr>
			<tr>
				<td colspan="3">
					<div align="left" id="divMappedICD">
					<logic:present name="<%=OpdConfig.EssentialBO_LIST_ICD_MAPPED %>">
			<logic:iterate id="objEntry" name="<%=OpdConfig.EssentialBO_LIST_ICD_MAPPED %>" type="hisglobal.utility.Entry">
				<%=objEntry.getLabel()%><input type='hidden' name='mappedICDCodeOLD' value="<%=objEntry.getValue()%>"/>
				<input type='hidden' name='mappedIcdNameOLD' value="<%=objEntry.getLabel()%>"/>
				<img lass="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style='cursor:pointer;vertical-align: middle;' onclick ='removeICD("+icdCode+");' onkeypress='if(event.keyCode==13) removeICD("+icdCode+");'/>
			</logic:iterate>
		</logic:present>	

					
					</div>
				</td>
			</tr>
				
			
			
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="snomedCtId"/>
							</b>	
						</font>
					</div>				
				</td>
				<td width="50%"  class="tdfont" colspan="2">
						
					<div id="divSnomed" style="display:block">	
					<html:hidden 	name="HospitalDiseaseMasterFB" property="prefferedTerm" ></html:hidden>
					<html:hidden name="HospitalDiseaseMasterFB" property="conceptId" ></html:hidden>
					<div align="left" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:25%;color:#000000;" type="text">
					<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer;vertical-align: middle;" onclick ="if(validateAdd()) mapSnomed()" onkeypress="if(event.keyCode==13) if(validateAdd()) mapSnomed();">
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
					
						
					</div>
				</td>			
				
			</tr>
			<tr>
				<td colspan="3">
					<div align="left" id="divMappedSnomed">
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					<div align="left" id="divMappedICD">
					<logic:present name="<%=OpdConfig.EssentialBO_LIST_SNOMED_MAPPED %>">
			<logic:iterate id="objEntry" name="<%=OpdConfig.EssentialBO_LIST_SNOMED_MAPPED %>" type="hisglobal.utility.Entry">
				<%=objEntry.getLabel()%> <input type='hidden' name='mappedSnomedIDOLD' value="<%=objEntry.getValue()%>"/>
				<input type='hidden' name='mappedSnomedTermOLD' value="<%=objEntry.getLabel()%>"/>
				<img lass="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style='cursor:pointer;vertical-align: middle;' onclick ='removeSnomed()' onkeypress='if(event.keyCode==13) removeSnomed();'/>
			</logic:iterate>
		</logic:present>	

					
					</div>
				</td>
			</tr>
				
			<tr>
				<td width="50%"  class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remark"/></b>	
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" style="vertical-align: middle;" >	
					<div align="left">
						<html:textarea name="HospitalDiseaseMasterFB" property="remarks" tabindex="1" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" style="vertical-align: middle;"></html:textarea>
					</div>
				</td>					
			</tr>
			<tr>
				
			</tr>
		</table>
		</logic:equal>	 		
		 
	</his:ContentTag>	
	
	                                                                   
	<his:ButtonToolBarTag>
		
		 
		  <logic:equal name="HospitalDiseaseMasterFB" property="hmode" value="ADD">
			      <img class='button' id="butSave" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="submitForm('SAVE');" onkeypress="if(event.keyCode==13) submitForm('SAVE');" tabindex="1">
				</logic:equal>
				
				<logic:notEqual name="HospitalDiseaseMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="HospitalDiseaseMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('MODIFYSAVE')" onclick="submitForm('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="submitForm('ADD')" onkeypress="if(event.keyCode==13) submitForm('ADD');" tabindex="1">
		
		<his:statusUnsuccessfull>
	  		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1">
		</his:statusUnsuccessfull>
		
	</his:ButtonToolBarTag>
<his:status></his:status>
<html:hidden name="HospitalDiseaseMasterFB" property="hmode" />
<html:hidden name="HospitalDiseaseMasterFB" property="revokeAlertId"/>
<html:hidden name="HospitalDiseaseMasterFB" property="index"/>
<html:hidden name="HospitalDiseaseMasterFB" property="snomedCtId"/>
<html:hidden name="HospitalDiseaseMasterFB" property="diseaseName" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="patAlertId" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="icdName" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="conceptName" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="selIcdArray" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="selsnomedArray" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="selIcd" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="selsnomed" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="diseaseID" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="selIcdNameArray" ></html:hidden>
<html:hidden name="HospitalDiseaseMasterFB" property="selSnomedNameArray" ></html:hidden>
 <input type="hidden" name="count" />
 <input type="hidden" name="mapResult" id="mapResult" />

</html:form>


