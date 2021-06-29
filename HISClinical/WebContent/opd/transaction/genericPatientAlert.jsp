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

 <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<his:javascript src="/registration/js/registration.js" />

<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js"> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
/* var lstChronic = [];

 
 $(function() {
	    $( "#autocomplete-4" ).autocomplete({
	       source: lstChronic,
	       focus: function( event, ui ) {
	           //$( "#autocomplete-4" ).val( ui.item.label );
	              return false;
	           },       
	           select: function( event, ui ) {
	           //alert("ddf");
	    	   $( "#autocomplete-4" ).val( ui.item.label);
	           $( "#searchTariffId" ).val( ui.item.id);
	           addSelectedTariff();
	           }
	    });
	 });
 
 
 
 
 function addSelectedTariff()
 {
 	//alert("hi");
 	var trfid=document.getElementsByName("searchTariffId")[0].value;
 	var trfname=document.getElementsByName("searchTariff")[0].value;
 //	alert(trfid);alert(trfname);
    document.getElementsByName("alertName")[0].value=trfname;
	document.getElementsByName("patAlertId")[0].value=trfid;

 	
 
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

<script type="text/javascript">

window.onload = function()
{
	
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};
	var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};

	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	
	selectSNOMEDCTauto('ACTIVE','','SYNONYMS','10','3310031000189109','1',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
	/*  alert("hi");
	var count=document.getElementsByName("count")[0].value;
	alert(count);
	if(count!="notfirst")
		{
		alert("inside count");
	selectSNOMEDCTauto('ACTIVE','','PREFERRED','50',callbck_index);
	count="notfirst";
		}  */
	for(var i=0;i<document.getElementsByName("revokeChronics").length;i++)
	{
		if(document.getElementsByName("revokeChronics")[i].checked)
			document.getElementsByName("revokeRemarks")[i].disabled = false;
		else
		{
			document.getElementsByName("revokeRemarks")[i].value = "";
			document.getElementsByName("revokeRemarks")[i].disabled = true;			
		}
	}
		
	//getSnomedChronicTerms();

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



function setfreeText()
{
	document.getElementsByName("remarks")[0].value=document.getElementsByName("txt-snomed-ct-search_4")[0].value;   //for free text

}

function getSnomedChronicTerms()
{
	

	// SCTID: 27624003, Chronic disease (disorder)
	
	var id=["27624003"];
	//var allergyArray; 
	var term; var conceptid;
	//var elemDropDown=document.getElementById('cboDropDown');
	for(var i=0;i<id.length;i++)
		{
	//alert("hi");		
		$.ajax({
			url : createFHashAjaxQuery("/AHIMSG5/snomedct/csnoserv/api/explore/alldescendents?id="+id[i]),
			type : 'GET',
			
			 datatype: "json",
			 async: false,
			  success : function(data) {
						var obj = eval(data);
			
				//alert(obj.length); 
				//alert(obj[0].id); alert(obj[0].fullySpecifiedName);
				//allergyArray = allergyArray + obj;
				var k = lstChronic.length;
				for(var j=0;j<obj.length;j++)
					{
				//term= term+obj[j].term;
				//conceptid=conceptid + obj[j].conceptid;
			   //  var op=document.createElement("option");
				//op.innerHTML=obj[j].fullySpecifiedName;
				//op.value= obj[j].id;
				//elemDropDown.appendChild(op);
				var term = obj[j].fullySpecifiedName;
				term = term.substring(0,term.indexOf("("));
				
				lstChronic[k++] = { label: term, value: term, id: obj[j].id };
				}
				//alert(allergyArray.length);
				//
			   },
error: function(data)
{
    alert('request failed :');
}

});
}
}


//snomd new

var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

/*
* setValue() function is called in the callback function.This function is used to display the return value in the HTML control.In
* index.html page,the value is displayed in the "snomedcttext" textarea.The result includes- Concept id, Description-id and Description  
* param selectedSNOMEDTerm(var)-is the concept selected by the user from the search result list 
* 
*/

function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
//		var arr=selectedSNOMEDTerm.split(",");
//		var str=arr[0];
//		var str1=arr[1];
		
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
//alert(str[0]); alert(str1);
	
	document.getElementsByName("alertName")[0].value=str;
	document.getElementsByName("patAlertId")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_1")[0].value=str;
	 document.getElementById("somectIddata").innerHTML =str1;
		}
	else
		{
		document.getElementsByName("alertName")[0].value="";
		document.getElementsByName("patAlertId")[0].value="";
		 document.getElementById("somectIddata").innerHTML ="";
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
	document.forms[0].submit();
}

function check()
{
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
		return true;
	else
		return false;	
}
function validateAdd(isPlus)
{
	if(check())
	{
		alert("Patient is Dead.");
		return false;		
	}	
	var revoked = false;
	if(!isPlus)
	{
		if(document.getElementsByName("revokeChronics")[0])
		{
			for(var i=0;i<document.getElementsByName("revokeChronics").length;i++)
			{
				if(document.getElementsByName("revokeChronics")[i].checked)
				{
					revoked=true;
					if(document.getElementsByName("revokeRemarks")[i].value=="")
					{
						alert("Please Enter Revoke Remarks");
						document.getElementsByName("revokeRemarks")[i].focus();			
						return false;		
					}
				}
			}
		}
	}
	
	if(document.getElementsByName("patAlertId")[0].value=="" &&
		document.getElementsByName("durationDate")[0].value=="" &&
		 document.getElementsByName("remarks")[0].value=="" && revoked == true)
		return true;

	var addCheck = false;
	if(document.getElementsByName("hiddenAlertName")[0])
	{
		if(document.getElementsByName("patAlertId")[0].value!="" 
			|| document.getElementsByName("durationDate")[0].value!="" 
			|| document.getElementsByName("remarks")[0].value!="")
			addCheck = true;
	}
	else
		addCheck = true;
	
	if(isPlus || addCheck)
	{
		if(document.getElementsByName("patAlertId")[0].value=="")
		{
			alert("Please Select The Chronic Disease");
			document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
			return false;		
		}
		if(document.getElementsByName("durationDate")[0].value=="")
		{
			alert("Please Enter The Duration in Years");
			document.getElementsByName("durationDate")[0].focus();
			return false;		
		}
		if(document.getElementsByName("durationDate")[0].value > checkDuration() )
		{
			alert("Duration Year Cannot Be Greater Than Patient Age");
			document.getElementsByName("durationDate")[0].focus();
			return false;		
		}
		/*if(document.getElementsByName("remarks")[0].value=="")
		{
			alert("Please Enter the Remarks");
			document.getElementsByName("remarks")[0].focus();
			return false;
		}*/
		var alertId= document.getElementsByName("patAlertId")[0].value;
		//var id=alertId.split("#");
		//var somedId=id[1];
		//alert(alertId);
		var flag=true;
		var alertName="";
		///var combo=document.getElementById("patAlertId");
		// alertName=combo.options[combo.selectedIndex].text;
		alertName=document.getElementsByName("alertName")[0].value;
		/* if(document.getElementsByName("patAlertName")[0])
			alertName=document.getElementsByName("patAlertName")[0].value;
		 */
		 if(document.getElementsByName("prevAlertId")[0])
			{
			 flag =checkIsDuplicate(alertId,'prevAlertId');
	    	 if(!flag)
	     	   {
	        		 alert(alertName+' is already added');
	         		//document.getElementById("somecetIddata").innerHTML =" ";
			         document.getElementsByName("patAlertId")[0].value="";
			         document.getElementsByName("txt-snomed-ct-search_1")[0].focus();  
			        // document.getElementsByName("searchTariff")[0].focus();
			         return false;
			   }	
	        }
		 if(document.getElementsByName("addedAlertId")[0])
			{
			 flag =checkIsDuplicate(alertId,'addedAlertId');
	    	 if(!flag)
	     	   {
	        		 alert(alertName+' is already added');
	         		//document.getElementById("somecetIddata").innerHTML =" ";
			         document.getElementsByName("patAlertId")[0].value="";
			         document.getElementsByName("txt-snomed-ct-search_1")[0].focus();  
			        // document.getElementsByName("searchTariff")[0].focus();
			         return false;
			   }	
	        }
		 
		   
	    	 }
	setfreeText();  
	return true;
}	


function checkIsDuplicate(snomedId,prevOrAddedlist)
{ 

	var flag=true;
	//var len=document.getElementById('assignedPatAlert');
	//alert(len);
	var len= document.getElementsByName("count")[0].value;
	//alert(len);
	//alert(document.getElementsByName("prevAlertId")[0].value);
	for (var i=0;i<len;i++)
		{
		if(document.getElementsByName(prevOrAddedlist)[i].value==snomedId)
			{
			flag=false;
			break;
			}
		}

	return flag;
}

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenAlertName")[0].value=obj1;
	document.getElementsByName("hiddenPatAlertId")[0].value=obj2;
	submitForm('DELETEROW');
}

function revokeAlerts(obj1,obj2)
{
	// alert(obj1);
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
<his:TitleTag name="Patient Chronic Disease">
</his:TitleTag>
 <bean:define name="GenericPatientAlertFB" property="sysDate" id="sDate" type="java.lang.String"/>
<%
         if(sDate==null||sDate.equalsIgnoreCase("")){  
         	sDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
%>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

<his:statusTransactionInProcess>
	
	
	<% List patAssignedAlertVO=(List)session.getAttribute(OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO);
		if(patAssignedAlertVO.size()>0){
	%>
		<his:SubTitleTag key="chronicDiseaseDtl">
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td>
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<a style="cursor:pointer" onclick="getAllAlerts(event)" >	
										<bean:message key="all"/>
										<bean:message key="chronicDisease"/>
									</a>	
								</b>
							</font>
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="chronicDisease"/>
								</b>
							</font>	
						</div>
					</td>
					<%-- <td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="snomedCtId"/>
							</b>	
						</font>
					</div>				
				</td> --%>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="duration"/>									
								</b>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="cancel"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="cancel"/>
									<bean:message key="remark"/>
								</b>
							</font>	
						</div>
					</td>
				</tr>
				<% int count=0; %>
				<logic:iterate id="assignedPatAlert" name="<%= OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO%>" type="hisglobal.vo.PatientAlertsDetailVO" indexId="idx">
					<tr>
						<td class="tdfont">
							<div align="center">
							
								<%=assignedPatAlert.getAlertName() %> <%count++; %>
								<input name="prevAlertName" value="<%=assignedPatAlert.getAlertName() %>" type="hidden" />
							</div>
						</td>
						<!-- <td class="tdfont"> -->
							<div align="center" style="display:none">
								<%=assignedPatAlert.getPatAlertId() %>
								<input name="prevAlertId" value="<%=assignedPatAlert.getPatAlertId() %>" type="hidden" />
							</div>
						<!-- </td> -->
						<td class="tdfont">
							<div align="center">
							<%=assignedPatAlert.getDurationDays() %>
								<input name="prevDurDays" value="<%=assignedPatAlert.getDurationDays() %>" type="hidden" />
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<input type="checkbox" name="revokeChronics" value="<%=assignedPatAlert.getPatAlertId()%>"
								<%if(assignedPatAlert.isCheckedRevoke()){%>checked="checked";<%} %>	onchange="setRevokeRemark(<%=idx.toString()%>)"  />
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<textarea name="revokeRemarks" tabindex="1" rows="1" cols="25" disabled="disabled" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))"><%=assignedPatAlert.getRevokeRemarks()%></textarea>
							</div>
						</td>
					</tr>
				</logic:iterate>
				<input name="count" value="<%=count%>" type="hidden" />
			</table>	
		</his:ContentTag>
	<%} %>
	
	<his:SubTitleTag key="alertsDetail">
		<%if(patAssignedAlertVO.size()<=0){ %>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getAllAlerts(event)" >	
									<bean:message key="all"/>
									<bean:message key="chronicDisease"/>
								</a>	
							</b>
						</font>
					</div>
				</td>
			</tr>
		</table>
		<%} %>
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="45%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chronicDisease"/>
							</b>	
						</font>
					</div>
				</td>
				<%-- <td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="snomedCtId"/>
							</b>	
						</font>
					</div>				
				</td> --%>
				<td width="15%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="duration"/>
								<bean:message key="dDays"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="25%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remark"/></b>	
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead">
				</td>
				
										
			</tr>
			
						
			<tr>
				<td width="45%" class="tdfont" style="vertical-align: middle;">	
				 	<div align="center">
				<%-- 		<html:select name="GenericPatientAlertFB" property="patAlertId" tabindex="1" style="vertical-align: middle;" onchange="snomedctIdDtl();">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST%>" >
							<html:options collection="<%= OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
						
				 --%>	
				 	
					
					<%-- <html:hidden name="GenericPatientAlertFB" property="patAlertId" ></html:hidden> --%>
					<html:hidden name="GenericPatientAlertFB" property="alertName" ></html:hidden>
					<html:hidden name="GenericPatientAlertFB" property="patAlertId" ></html:hidden>
					
						<!-- <div align="center" id="divSearchTraiff" class="ui-widget">
							<input type="text" style="display: none;" id="searchTariffId" name="searchTariffId" size="30" >
					
							<input type="text"  name="searchTariff"  id="autocomplete-4" tabindex="1" size="30" style="width:80%" >
							
							
								</div>  -->
					
				   <!--  <div  id="dialog-form_1" style="display:none" > -->
				     <div  id="dialog-form_1"  >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1" onfocus="selectSNOMEDCTauto('ACTIVE','','SYNONYMS','10','3310031000189109','1',callbck_index);" style="width:95%;color:#000000;" type="text">
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
					
				
				<!-- <td width="15%"  class="tdfonthead"> -->
					<div align="center" id="somectIddata" style="display:none" >
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							
							</b>
						</font>
					</div>				
				<!-- </td> -->
				<td width="15%" class="tdfont" style="vertical-align: middle;">	
					<div align="center">
						<html:text name="GenericPatientAlertFB" property="durationDate" onkeypress="return validatePositiveNumericOnly(this,event)"  maxlength="5" size="5" tabindex="1" style="vertical-align: middle;"></html:text>
						<img class="button" src='<his:path src="/hisglobal/images/cal_duration.png"/>' style="cursor:pointer;vertical-align: middle;" title="Duration Calculator " onclick ="showDayCalculator(event)" onkeypress="if(event.keyCode==13) showDayCalculator(event);">
					</div>
				</td>	
				<td width="25%" class="tdfont" style="vertical-align: middle;">	
					<div align="center">
						<%-- <html:textarea name="GenericPatientAlertFB" property="remarks" tabindex="1" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" style="vertical-align: middle;"></html:textarea>
				 --%>
				 
				
					<html:hidden name="GenericPatientAlertFB" property="snomedPTRemarks" value=""></html:hidden>
					<html:hidden name="GenericPatientAlertFB" property="snomedCIdRemarks" value=""></html:hidden>
					<html:hidden name="GenericPatientAlertFB" property="remarks" value="" ></html:hidden>
						<%-- <html:textarea name="OpdEpisodeAllergyFB" property="adviceText" rows="2" cols="30" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"  tabindex='1'>
						</html:textarea> --%>
						<textarea name="txt-snomed-ct-search_4" rows="3" cols="40" id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX" value="" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" style="width:100%;" autocomplete="off"></textarea>
	
				</td>
				
				
				
				<td width="15%" class="tdfont" style="vertical-align: middle;">	
					<div align="center">
						<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer;vertical-align: middle;" onclick ="if(validateAdd(true)) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd(true)) submitForm('ADDROW');">
					</div>
				</td>
			</tr>
			<%if(session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT) !=null){ %>
				<% int count=0; %>
				<logic:iterate id="addedAlert" name="<%=OpdConfig.ARR_ADDED_PATIENT_ALERT %>" type="hisglobal.vo.PatientAlertsDetailVO">
					<tr>
						<td class="tdfont" width="30%" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getAlertName() %><%count++;%>
								<input name="addedAlertId" value="<%=addedAlert.getPatAlertId() %>" type="hidden" />
							</div>
						</td>
						
						<!-- <td class="tdfont" width="30%" style="vertical-align: middle;">
						 -->	<div align="center" style="display:none">
						 		<%=addedAlert.getPatAlertId() %>
								
							</div>
						<!-- </td> -->
						
						<%-- <td width="15%"  class="tdfonthead">
							<div align="center" id="somecetIdAdded">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%
									String val=addedAlert.getPatAlertId();
									String [] id;
									id=val.split("#");
									String idVal=id[1];
									%>
									<b>
									<%=idVal %>
									</b>
								</font>
							</div>				
						</td> --%>
						<td class="tdfont" width="15%" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getDurationDate() %>
							</div>
						</td>
						<td class="tdfont" width="25%" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getRemarks()%>
							</div>
						</td>
						<td class="tdfont" width="15%" style="vertical-align: middle;">
							<div align="center">
								<img class="button" style="cursor:pointer;vertical-align: middle;" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=addedAlert.getAlertName() %>','<%=addedAlert.getPatAlertId() %>') ;" onclick=" deleteRow('<%=addedAlert.getAlertName() %>','<%=addedAlert.getPatAlertId() %>')">
								<html:hidden name="GenericPatientAlertFB" property="hiddenAlertName"/>
								<html:hidden name="GenericPatientAlertFB" property="hiddenPatAlertId"/>
							</div>
						</td>
					</tr>		
				</logic:iterate>
				<input name="count" value="<%=count%>" type="hidden" />
			<%} %>
		</table>
	</his:ContentTag>		

</his:statusTransactionInProcess>                                                                     
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		 <img class='button' id="butSave" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="if(validateAdd(false)){ submitForm21('SAVE'); document.getElementById('butSave').style.display='none';}" onkeypress="if(event.keyCode==13) if(validateAdd(false)){ submitForm21('SAVE'); document.getElementById('butSave').style.display='none';} " tabindex="1">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
		</his:statusTransactionInProcess>
		<his:statusUnsuccessfull>
	  		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
		</his:statusUnsuccessfull>
		
	</his:ButtonToolBarTag>


<html:hidden name="GenericPatientAlertFB" property="hmode" />
<html:hidden name="GenericPatientAlertFB" property="patCrNo" />
<html:hidden name="GenericPatientAlertFB" property="revokeAlertId"/>
<html:hidden name="GenericPatientAlertFB" property="index"/>
<html:hidden name="GenericPatientAlertFB" property="dob"/>
<html:hidden name="GenericPatientAlertFB" property="sysDate" value="<%=sDate%>"/>						
<html:hidden name="GenericPatientAlertFB" property="dayOnAge"/>
<html:hidden name="GenericPatientAlertFB" property="hiddenPatDeadStatus"/>
<html:hidden name="GenericPatientAlertFB" property="snomedCtId"/>
 <input type="hidden" name="count" />



