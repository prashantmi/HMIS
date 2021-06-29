<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>

<%@page import="opd.transaction.controller.fb.EpisodeAllergyNewFB"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/opd/opdJs/opdAjax.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">

<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>

<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">




<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/easyui.css" />
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/newHisRadioStyle.css" />
<his:css src="/hisglobal/css/tutorstyle.css" />	
<his:css src="/hisglobal/css/flexcrollstyles.css" />


<script type="text/javascript">
var lstAllergies = [];
 
 
 /*
 $(function() {
	    $( "#autocomplete-4" ).autocomplete({
	       source: lstAllergies,
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
 	document.getElementsByName("snomedCTAllergyName")[0].value=trfname;
	document.getElementsByName("snomedCTAllergyCode")[0].value=trfid;
	
 
 }
 
 
 */
	
 
 
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
    document.getElementsByName("snomdPTAllergyAdvice")[0].value="";  //preffered term
	document.getElementsByName("snomdCIdAllergyAdvice")[0].value="";  //concept Id
	document.getElementsByName("adviceText")[0].value="";
	
     
     //  document.getElementById(ClientsID.txtantcedentcausecptid).value = "";
     //  document.getElementById(ClientsID.txtccptid).value = "";


 });
 </script>
 
 
<script>

var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};
var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

window.onload = function()
{
	
	
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	//alert("hi onload");
	load1('4','');
	load1('3','FINDING');
	load1('2','BODY_STRUCTURE');
	loadSubset('1','');
	// selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index4);
	$("#dialog-form").dialog("close");
	document.getElementsByName("snomedCTAllergyName")[0].value="";
	document.getElementsByName("snomedCTAllergyCode")[0].value="";
	document.getElementsByName("snomedCTAllergySiteName")[0].value="";
	document.getElementsByName("snomedCTAllergySiteCode")[0].value="";
	document.getElementsByName("snomedCTAllergySymptomsName")[0].value="";
	document.getElementsByName("snomedCTAllergySymptomsCode")[0].value="";
	document.getElementsByName("snomdPTAllergyAdvice")[0].value="";
	document.getElementsByName("snomdCIdAllergyAdvice")[0].value="";
	//if(document.getElementsByName('hmode')[0].value=='NEW')
	
		//getSnomedAllergyTerms();
}



function getSnomedAllergyTerms()
{
	

	// SCTID: 609328004, Allergic disposition (disorder)
	// SCTID: 418634005, Allergic reaction to substance (disorder)
	// SCTID: 282100009, Adverse reaction to substance (disorder)to substance (disorder)
	// SCTID: 421961002  Hypersensitivity reaction (disorder) 
	// SCTID: 419511003  Propensity to adverse reactions to drug (disorder) 
		
	var id=["609328004","418634005","282100009","421961002","419511003"];
	//var id=["609328004"];
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
				var k = lstAllergies.length;
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
				
				lstAllergies[k++] = { label: term, value: term, id: obj[j].id };
				
				//lstAllergies[k++] = { label: obj[j].fullySpecifiedName, value: obj[j].fullySpecifiedName, id: obj[j].id };
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
	
	//alert(allergyArray.length);
}



function load2()
{
  // alert("inside load2");	
    load1('4','');
	load1('3','DISORDER');
	load1('2','BODY_STRUCTURE');
	load1('1','DISORDER');
	document.getElementsByName("snomedCTAllergyName")[0].value="";
	document.getElementsByName("snomedCTAllergyCode")[0].value="";
	document.getElementsByName("snomedCTAllergySiteName")[0].value="";
	document.getElementsByName("snomedCTAllergySiteCode")[0].value="";
	document.getElementsByName("snomedCTAllergySymptomsName")[0].value="";
	document.getElementsByName("snomedCTAllergySymptomsCode")[0].value="";
	document.getElementsByName("snomdPTAllergyAdvice")[0].value="";
	document.getElementsByName("snomdCIdAllergyAdvice")[0].value="";
}




function load1(elmtId,semantictag)
{
	//alert("inside load1");	
	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	
	if(elmtId=="4")
	{
	var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};
	setTarget('allergyAdvice');
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);
	}
	else
		{
	if(elmtId=="2")
		{
		var callbck_index =function(ret_OUT){setValue2(ret_OUT);};
		}
	
	if(elmtId=="3")
	{
	var callbck_index =function(ret_OUT){setValue3(ret_OUT);};
	}
	
	if(elmtId==null || elmtId==undefined)
		{
		elmtId="1"; semantictag="DISORDER";
	
		}
	
	
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_3").hide();
	$("#norecorddiv_3").hide();
	
	}
	//elmtId="2";
	//alert(elmtId);
	


	
}



function loadSubset(elmtId,semantictag)
{
	//alert("inside load1");	
	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	
	
	if(elmtId=="1")
		{
		var callbck_index =function(ret_OUT){setValue1(ret_OUT);};
		}
		
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','3310021000189109',elmtId,callbck_index);
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_3").hide();
	$("#norecorddiv_3").hide();
	
	
	//elmtId="2";
	//alert(elmtId);
	


	
}
	

function setValue1(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	//var arr=selectedSNOMEDTerm.split("@@@");
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str[0]); alert(str1);
	
	document.getElementsByName("snomedCTAllergyName")[0].value=str;
	document.getElementsByName("snomedCTAllergyCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_1")[0].value=str;
	//document.getElementById("somectIddata").innerHTML =str1;
		}
	else
		{
		document.getElementsByName("snomedCTAllergyName")[0].value="";
		document.getElementsByName("snomedCTAllergyCode")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
}



function setTarget(id)
{
	if(id=="allergyAdvice")
	{
	document.getElementsByName("targetId")[0].value="allergyAdvice";}
	
}

function setValue4(selectedSNOMEDTerm)
{
	
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	var targetElement=document.getElementsByName("targetId")[0].value;
	var targetPrefferedTerm="";
	var targetConceptId="";
	//alert(targetElement);
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
					
		//var arr=selectedSNOMEDTerm.split("@@@");
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
		//alert(str[0]); alert(str1);
	if(targetElement == "allergyAdvice") // Visit Reason
	{
		//alert("inside advice");
		 targetPrefferedTerm=document.getElementsByName("snomdPTAllergyAdvice")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdAllergyAdvice")[0];   //concept Id
		 document.getElementsByName("adviceText")[0].value=document.getElementsByName("adviceText")[0].value + ' ' + str;
		 
	}
	
	 
	    if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "#" + str1;
		
	    document.getElementsByName("targetId")[0].value="";
	    targetPrefferedTerm="";
	    targetConceptId="";
	
}
}



function setValue2(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
		//var arr=selectedSNOMEDTerm.split("@@@");
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
		//alert(str[0]); alert(str1);
	
	document.getElementsByName("snomedCTAllergySiteName")[0].value=str;
	document.getElementsByName("snomedCTAllergySiteCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_2")[0].value=str;
	//document.getElementById("somectIddata").innerHTML =str1;
		}
	else
		{
		document.getElementsByName("snomedCTAllergySiteName")[0].value="";
		document.getElementsByName("snomedCTAllergySiteCode")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	
}

function setValue3(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
		//var arr=selectedSNOMEDTerm.split("@@@");
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
			//alert(str[0]); alert(str1);
	
	document.getElementsByName("snomedCTAllergySymptomsName")[0].value=str;
	document.getElementsByName("snomedCTAllergySymptomsCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_3")[0].value=str;
	//document.getElementById("somectIddata").innerHTML =str1;
		}
	else
		{
		document.getElementsByName("snomedCTAllergySymptomsName")[0].value="";
		document.getElementsByName("snomedCTAllergySymptomsCode")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	$("#conceptdiv_3").hide();
	$("#norecorddiv_3").hide();
	
}




function selectValueAuto(value, callback) {
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +"@@@"+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#conceptdiv_3").hide();
	$("#norecorddiv_3").hide();
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
	

}

function selectValue(value,callback) 
{
	
	var data1 = unescape(value);

	var term1 = data1.split('###$$$');

	var returnterm_OUT = term1[1] +"@@@"+ term1[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}


function setfreeText()
{
	document.getElementsByName("adviceText")[0].value=document.getElementsByName("txt-snomed-ct-search_4")[0].value;   //for free text
	
}


</script>

<script>

function deleteRow(obj1,obj2)
{
//	alert(obj1); alert(obj2);
	document.getElementsByName("hiddenAllergyName")[0].value=obj1;
	document.getElementsByName("hiddenAllergyCode")[0].value=obj2;
	submitForm('REMOVEDETAIL');
	//submitForm('DELETEROW');
}

function checkDuration()
{	
	if(document.getElementsByName("duration")[0])
	{
		var valid= true;
		var dob = document.getElementsByName("patDOB")[0].value;
		var duration = document.getElementsByName("duration")[0].value; 
		var sysDate= document.getElementsByName("sysDate")[0].value; 
		var age= document.getElementsByName("patAge")[0].value;
		
		var sysDateArray=sysDate.split(" ");
		sysDate=sysDateArray[0];
		//alert("D.O.B ="+dob);
		//alert("SySDate ="+sysDate);
		//alert("age= "+age);
		
		//for(i=0;i<duration.length;i++)
		//{
			//alert("Duration= "+ duration);
			//if(duration>age)
			//{
				//alert("Duration cannnot be greater than Actual Age");
				//valid= false;
			//}
		//}
		var days= noOfDays(dob,sysDate);
		var calYears=days/366;
		calYears=Math.round(calYears*100)/100;
		//alert("calYears ="+calYears);
		if(duration>calYears)
			{
				alert("Duration cannnot be greater than Actual Age");
				document.getElementsByName("duration")[0].focus();
				return false;
			}
	}	
	return true;

}

function noOfDays(a,b)
{
	var valid=true;
	var days=0;
	var a_temp=a.toString();
    var aArray=a_temp.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
    var b_temp=b.toString();
    var bArray=b_temp.split("/");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
    days=((bdate-adate)/86400000);

    return days;
}





function submitAddDetail(mode)
{
	//alert(mode);
	submitForm21(mode);
}

function submitFormRemoveAllergy(mode)
{// alert("tableid===="+tableId);
	//document.getElementsByName('allergyTableId')[0].value=tableId;
	// alert(document.getElementsByName('allergyTableId')[0].value);
	//document.forms[0].numberOfTables.value=parseInt(document.forms[0].numberOfTables.value)-1
	submitForm(mode);
}


function submitFormRemoveDetail(mode){
	//var seqno=tableId.substr(tableId.indexOf("^")+1);
//	document.getElementsByName('reasonDetalRowId')[0].value=rowId;
	
	// alert(document.getElementsByName('reasonDetalRowId')[0].value);
//	document.getElementsByName('allergyTableId')[0].value=tableId;
	// alert(document.getElementsByName('allergyTableId')[0].value);
	submitForm(mode);
}

function validateAdd()
{
	
	//var tableno=tableId.substring(0,tableId.indexOf('^'))
	//var seqno=tableId.substr(tableId.indexOf("^")+1);
	var seqno=0;
	// alert("tableno==="+tableno)
	// alert("seqno===="+seqno)
	var valid="";
	var indexNO
	
	//snomedCTAllergySymptomsCode=document.getElementsByName("snomedCTAllergySymptomsCode")[seqno]
	allergyReason=document.getElementsByName("snomedCTAllergyCode")[seqno]
	//snomedCTAllergySiteCode=document.getElementsByName("snomedCTAllergySiteCode")[seqno]
	//snomdCIdAllergyAdvice=document.getElementsByName("snomdCIdAllergyAdvice")[seqno]
	allergyName=document.getElementsByName("snomedCTAllergyName")[seqno]
	
	//allergyReason		= document.getElementsByName("reason")[seqno]
	//allergyRemarks	= document.getElementsByName("remarks")[seqno]
	allergySensitivity		= document.getElementsByName("sensitivityCode")[seqno]
	duration = document.getElementsByName("duration")[seqno]
	
	if(isEmpty(allergyReason,"Allergy Name") &&
		/*isEmpty(allergyRemarks,"Remark") &&*/
		comboValidation(allergySensitivity,"Sensitivity") &&
		isEmpty(duration,"Duration in Years") && checkDuration() && isduplicateChronic(allergyReason.value,allergyName.value)
		)
		{
		setfreeText();
		valid=true;
		}
		else
		{
		valid=false
		}
	//alert(valid);
		return valid
}



function isduplicateChronic(alertId,alertName)
{
//var alertId= document.getElementsByName("patAlertId")[0].value;
//var id=alertId.split("#");
//var somedId=id[1];
//alert(alertId);
var flag=true;
//var alertName="";
///var combo=document.getElementById("patAlertId");
// alertName=combo.options[combo.selectedIndex].text;
//alertName=document.getElementsByName("alertName")[0].value;
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
	         document.getElementsByName("snomedCTAllergyCode")[0].value="";
	         document.getElementsByName("searchTariff")[0].focus();
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
	         document.getElementsByName("snomedCTAllergyCode")[0].value="";
	         document.getElementsByName("searchTariff")[0].focus();
	         return false;
	   }	
    }
 
   
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



function validateIt()
{	
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	var seqno=0
	allergyReason=document.getElementsByName("snomedCTAllergyCode")[seqno]
	allergyName=document.getElementsByName("snomedCTAllergyName")[seqno]
	var valid=false
	//var numberOfTable=document.forms[0].numberOfTables.value;
	//if(numberOfTable>0 )
	//{
		//for(i=0;i<numberOfTable;i++)
		//{
			if(isEmpty(allergyReason,"Allergy Name") &&
			/*isEmpty(document.getElementsByName("remarks")[i],"Remark") &&*/
			comboValidation(document.getElementsByName("sensitivityCode")[seqno],"Sensitivity") &&
			isEmpty(document.getElementsByName("duration")[seqno],"Duration in Years")
			&& validateAdVice() && isduplicateChronic(allergyReason.value,allergyName.value)
			)
			{
				setfreeText();
				valid=true;
		//alert("inner if true")
			}
			else
			{
			//alert("inner if false")
				valid=false
				return valid
			}
		//}
	//}
	// else
	//{
	
		//alert("Please Select an Allergy Type");
		//document.getElementsByName("allergyCode")[0].focus();
	//}
	//alert(valid)
	return valid
}

function validateAdVice()
{
	var valid=true;
	if(document.getElementsByName('adviceText')[0].value.length>500)
	 {
	 	alert("Characters in Advice should be less than 500");
	 	document.getElementsByName("adviceText")[0].focus();
	 	valid= false;
	 }
	 return valid;
}

function getAllergyDetail(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,600);
}



function addAdvice(event)
{
	var path='/HISClinical/opd/opdEpisodeAllergiesNew.cnt?hmode=ADDADVICE';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function showDayCalculator(event)
{
	
	var path=createFHashAjaxQuery('/HISClinical/opd/opdEpisodeAllergiesNew.cnt?hmode=DAYCAL');
	openPopup(createFHashAjaxQuery(path),event,200,500);
}

function revokeAllergy(obj1,obj2)
{
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	var alertId=obj1;
	var index=obj2;
	document.getElementsByName("revokeAllergyId")[0].value=alertId;
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


function setSensitivityName()
{
	//alert("hi");
	var elmt=document.getElementsByName('sensitivityCode')[0];
	var i=elmt.options.selectedIndex;
	var txt=elmt.options[i].text;
	//elmt.selectedIndex=i;
	//alert(txt);
	document.getElementsByName("sensitivity")[0].value=txt;
	//alert(document.getElementsByName("sensitivity")[0].value);
	//document.getElementsByName("sensitivityName")[0].value=;
}

</script>

<%@page
	import="hisglobal.presentation.*,hisglobal.persistence.*,opd.*"%>





	<his:TitleTag>
		<his:name>
			<bean:message key="episodeAllergies" />
		</his:name>
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	
<his:statusTransactionInProcess>
<%if(session.getAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO)!=null){ %>
		<his:SubTitleTag name="Previous Allergies Detail">
		</his:SubTitleTag>
				
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
			<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="duration"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="30%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="allergyName"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font>
							<bean:message key="revoke"/>
							<bean:message key="remark"/>
						</b>
					</font>
				</div>
	  		</td>
	  		
	  		<td width="5%"  class="tdfonthead">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="revoke"/>
						</b>
					</font>
				</div>
	  		</td>
	  		
	  	</tr>
	
		<%int count=0; %>
	<logic:iterate id="prevAllergyVO" indexId="idx" name="<%=OpdConfig.ARRAY_EPISODE_ALLERGY_VO%>" type="hisglobal.vo.PatAllergyDtlVO">
	
		<tr>
	  		<%String path="/HISClinical/opd/opdEpisodeAllergiesNew.cnt?hmode=PREVIOUS&selectedAllergyID="+(String)prevAllergyVO.getAllergyID(); %>
	  		<%-- <td class="tdfont">
	  			<div align="center">
	  				<%=prevAllergyVO.getAllergyTypeName() %>
	  			</div>
	  		</td> --%>
	  		<td class="tdfont">
	  			<div align="center">
	  				<%=prevAllergyVO.getDurationDays() %>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  				<a style="cursor:pointer" onclick="getAllergyDetail(event,'<%= path%>')" >	
	  				<%count++; %>
	  					<%= prevAllergyVO.getAllergyName()%>
	  					<input name="prevAlertId" value="<%=prevAllergyVO.getAllergyID() %>" type="hidden" />
	  				</a>	
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  				<html:text name="OpdEpisodeAllergyNewFB" property="revokeRemarks" value="" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  				<img class="button" id="revokeButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Minus.png"/>'  title="Revoke Allergy" onclick="revokeAllergy('<%=prevAllergyVO.getAllergyID()%> ','<%=idx.toString() %>')">
	  			</div>
	  		</td>
	  	</tr>
	  
	 </logic:iterate>
	<input name="count" value="<%=count%>" type="hidden" />
	</table>
	</his:ContentTag>
	
	<%} %>
	
	
	<his:SubTitleTag name="Add Allergies Detail"> </his:SubTitleTag>
	
	<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
	
	<tr>
		
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="allergyName"/></b>
				</font>
				</div>
	  		</td>
	  			  	
		  	<td width="8%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="sensitivity"/></b>
					</font>
					</div>
		  	</td>
	  		
			<td  class="tdfonthead" width="8%">
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
			<%-- <td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="remark"/></b>
				</font>
				</div>
	  		</td> --%>
	  		<td  class="tdfonthead" width="20%">	<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="symptoms"/></b></font></div></td> 
	  		<td  class="tdfonthead" width="20%">	<div align="center">	
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="allergySite"/></b></font></div></td>
	  			 
	  		
	  		  <td class="tdfonthead" width="20%"><div align="center">	
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="advice"/></b></font></div>
	  		</td>
	  		<td class="tdfonthead" width="2%"></td>
	  		 
	  	</tr>
	  	
	  	<tr>	  	
	  	
	  	<!-- <td class="tdfont" style="vertical-align: middle" style="display:none">	 -->
								
							<div align="center" id="divSearchTraiff" class="ui-widget" style="display:none">
							<input type="text" style="display: none;" id="searchTariffId" name="searchTariffId" size="30" >
					
							<input type="text"  name="searchTariff"  id="autocomplete-4" tabindex="1" size="30" >
							
							
								</div> 
						
								
	  	<!-- </td> -->
	  	
	  	        	<td class="tdfont" style="vertical-align: middle">
	  				
	  		 
	  		  
				 	<div align="center" id="divsnomedCTAllergyName">
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomedCTAllergyName" ></html:hidden></div>
					<div align="center" id="divsnomedCTAllergyCode">
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomedCTAllergyCode" ></html:hidden></div>
					<!-- </td> -->
					<div align="center"  >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1" onfocus="loadSubset('1','');"  style="width:95%;color:#000000;" type="text">
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
	  		 
	  
	  		
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
	  				<html:hidden name="OpdEpisodeAllergyNewFB" property="sensitivity" ></html:hidden>
				
		  			<html:select name="OpdEpisodeAllergyNewFB" tabindex="1" property="sensitivityCode" value='<%=((EpisodeAllergyNewFB)pageContext.findAttribute("OpdEpisodeAllergyNewFB")).getSensitivityCode() %>' onchange="setSensitivityName()" >
		  				<html:option value="-1">Select Value</html:option>	  
		  				<logic:present name="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" >
	  								<html:options  collection="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" property="value" labelProperty="label" />
	  					</logic:present>
		  			</html:select>
		  			
		  		
	  			</div>
	  		</td>
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
	  				<html:text name="OpdEpisodeAllergyNewFB" property="duration" onkeypress="return validateNumeric(event)" tabindex="1" size="6" maxlength="5"  style="vertical-align: middle"></html:text>
	  				<img class="button" src='<his:path src="/hisglobal/images/cal_duration.png"/>' style="cursor:pointer;vertical-align: middle;" title="Duration Calculator" onclick ="showDayCalculator(event)" onkeypress="if(event.keyCode==13) showDayCalculator(event);">
	  			</div>
	  		</td>
	  		<%-- <td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
		  			<html:text name="OpdEpisodeAllergyNewFB"  maxlength="500" size="25" property="remarks" style="vertical-align: middle"  		></html:text>
	  			</div>
	  		</td> --%>
	  		
	  		
	  			<td class="tdfont" style="vertical-align: middle">
	  				<%--<div align="center">  
	  			 <% String symptomsPath="/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=GETSYMPTOM&allergyTableId="+tableId; %>
	  				<a style="cursor:pointer" onclick="getSymptoms(event,'<%= symptomsPath%>')" >	
	  					<bean:message key="symptoms"/>
	  				</a>	 --%>
	  				
	  				 <div align="center" id="divsnomedCTAllergyName">
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomedCTAllergySymptomsName" ></html:hidden></div>
					<div align="center" id="divsnomedCTAllergyCode">
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomedCTAllergySymptomsCode" ></html:hidden></div>
					
					<div align="center" >
				    <div id="dialog-form_3" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_3" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_3"  style="width:95%;color:#000000;" type="text">
					</div>
					 <div id="norecorddiv_3">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
					 </div>         
                     <div class="concept" id="conceptdiv_3">                 
                    </div>
                         </div>
					
					</div>
					
					</td>
	  		
	  		
	  		<td class="tdfont" style="vertical-align: middle">
	  			<%-- <div align="center">  
	  				<% String allergySitePath="/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=ALLERGYSITE&allergyTableId="+tableId; %>
	  				<a style="cursor:pointer" onclick="getAllergySite(event,'<%= allergySitePath%>')" >
	  					<bean:message key="allergySite"/>
	  				</a>	
	  			 --%>
	  			    
		
			
				 	<div align="center" id="divsnomedCTAllergyName">
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomedCTAllergySiteName" ></html:hidden></div>
					<div align="center" id="divsnomedCTAllergyCode">
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomedCTAllergySiteCode" ></html:hidden></div>
					
					<div align="center" >
				    <div id="dialog-form_2" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_2"  style="width:95%;color:#000000;" type="text">
				<!-- 	<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_2"  style="width:95%;color:#000000;" type="text">
					
				 -->	</div>
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
					
					</td>
	  		 
	  	
	  	<td class="tdfont" style="vertical-align: middle;">
					<div align="center">
				   <html:hidden name="OpdEpisodeAllergyNewFB" property="snomdPTAllergyAdvice" ></html:hidden>
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomdCIdAllergyAdvice" ></html:hidden>
					<html:hidden name="OpdEpisodeAllergyNewFB" property="adviceText" ></html:hidden>
						<%-- <html:textarea name="OpdEpisodeAllergyNewFB" property="adviceText" rows="2" cols="30" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"  tabindex='1'>
						</html:textarea> --%>
						<textarea name="txt-snomed-ct-search_4" rows="3" cols="40" id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX" value="" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" onchange="setTarget('allergyAdvice');" style="width:100%;" autocomplete="off"></textarea>
	
						
						&nbsp;
							<%-- <img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;"
											onkeypress="if(event.keyCode==13) setTarget('allergyAdvice'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index4);" onclick="setTarget('allergyAdvice'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index4);" title="Click to add SNOMED-CT Term">							
			
						<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Select Advice" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="addAdvice(event)" onkeypress="if(event.keyCode==13) addAdvice(event)">
		 --%>			</div>
				</td>
	  	
	  	
	  			  	
					<td  class="tdfont">
				<div align="center">   
		<%-- 			<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' title="Add DETAIL" onclick="if (validateAdd('<%=tableId %>'))submitAddDetail('ADDDETAIL','<%=tableId %>')" >
		 --%>				<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' title="Add DETAIL" onclick="if (validateAdd())submitAddDetail('ADDDETAIL')" >
		
				</div>
			</td>
	  		 
	  		
	  		  	</tr>
	  	
	

<%-- 	</logic:iterate>
	</logic:notEmpty> --%>
	
	



	
	
	<%if(session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT) !=null){ %>
		<% int count=0; %>
				<logic:iterate id="addedAlert" name="<%=OpdConfig.ARR_ADDED_PATIENT_ALERT %>" type="hisglobal.vo.EpisodeAllergiesVO">
					<tr>
						<td class="tdfont"  style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getAllergyName()%>	<%count++; %>		
												</div>
												<div align="center" style="display:none"">
								<%=addedAlert.getAllergiesCode()%>
								<input name="addedAlertId" value="<%=addedAlert.getAllergiesCode() %>" type="hidden" />
							</div>
						</td>
						
						<%--  <td class="tdfont" width="10%" style="vertical-align: middle;">
						 	<div align="center" style="display:none"">
								<%=addedAlert.getAllergiesCode()%>
							</div>
						 </td>  --%>
						<td class="tdfont" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getSensitivityName()%>
							</div>
						</td>
						
						
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
						<td class="tdfont" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getDuration()%>
							</div>
						</td>
						
						
						<td class="tdfont" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getAllergySymtoms()%>
							</div>
						</td>
						
						<td class="tdfont"  style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getAllergySite()%>
							</div>
						</td>
						
						<td class="tdfont" style="vertical-align: middle;">
							<div align="center">
								<%=addedAlert.getAdvice()%>
							</div>
						</td>
						
						<%--  <td class="tdfont">
	  		<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' title="Remove Allergy" onclick="submitFormRemoveAllergy('REMOVEALLERGY')">
	 
	  		<html:hidden name="OpdEpisodeAllergyNewFB" property="hiddenAllergyCode" />
	</td> --%>
						<td class="tdfont" style="vertical-align: middle;">
							<div align="center">
								<img class="button" style="cursor:pointer;vertical-align: middle;" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=addedAlert.getAllergyName() %>','<%=addedAlert.getAllergiesCode() %>') ;" onclick=" deleteRow('<%=addedAlert.getAllergyName() %>','<%=addedAlert.getAllergiesCode() %>')">
								<html:hidden name="OpdEpisodeAllergyNewFB" property="hiddenAllergyName"  value="<%=addedAlert.getAllergyName()%>"/>
								<html:hidden name="OpdEpisodeAllergyNewFB" property="hiddenAllergyCode" value="<%=addedAlert.getAllergiesCode()%>"/>
							</div>
						</td>
						
						
				
	
						
					</tr>		
				</logic:iterate>
				<input name="count" value="<%=count%>" type="hidden" />
			<%} %>
			
			
			
			</table>
	</his:ContentTag>
<%-- 	<his:ContentTag>
	<his:SubTitleTag name="Advice">
	</his:SubTitleTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%" class="tdfont" style="vertical-align: middle;">
					<div align="center">
				   <html:hidden name="OpdEpisodeAllergyNewFB" property="snomdPTAllergyAdvice" ></html:hidden>
					<html:hidden name="OpdEpisodeAllergyNewFB" property="snomdCIdAllergyAdvice" ></html:hidden>
						<html:textarea name="OpdEpisodeAllergyNewFB" property="adviceText" rows="2" cols="90" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"  tabindex='1'>
						</html:textarea>
						&nbsp;
							<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('allergyAdvice'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index4);" onclick="setTarget('allergyAdvice'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index4);" title="Click to add SNOMED-CT Term">							
			
						<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Select Advice" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="addAdvice(event)" onkeypress="if(event.keyCode==13) addAdvice(event)">
					</div>
				</td>
			</tr>
		</table></his:ContentTag> --%>
	
</his:statusTransactionInProcess>
<his:ButtonToolBarTag>
<his:statusTransactionInProcess>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "if(checkDuration())submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
</his:statusTransactionInProcess>
<his:statusUnsuccessfull>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:statusUnsuccessfull>
</his:ButtonToolBarTag>	


<!-- <div id="divDropDown" class="hisStyle.css" style="display: block; position: absolute;">
	<select id="cboDropDown" size="10" tabindex="1" onKeyDown="onKeyDownDrop(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
</div> -->

<html:hidden name="OpdEpisodeAllergyNewFB" property="hmode"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="patCrNo"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="allergyTableId"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="reasonDetalRowId"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="numberOfTables"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="revokeAllergyId"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="index"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="hiddenPatDeadStatus"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="patDOB"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="patAge"/>
<html:hidden name="OpdEpisodeAllergyNewFB" property="sysDate"/>
<input type="hidden" name="targetId" />	


