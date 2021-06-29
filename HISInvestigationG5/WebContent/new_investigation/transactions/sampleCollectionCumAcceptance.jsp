<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.SampleCollectionCumAcceptanceVO"%>
<%@page import="new_investigation.transactions.controller.fb.SampleCollectionCumAcceptanceFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>


<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/drop.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />


<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<style>
#blanket {
   background-color:#111;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:0px;
   left:0px;
   width:100%;
}

#popUpDiv {
	position:absolute;
	  
	background:#CCE6FF;
	width:400px;
	height:250px;
	border:5px solid #000;
	z-index: 9002;
}

#popUpDiv a {position:relative; top:20px; left:20px}
</style> 

<script type="text/javascript" >

function showInstructions(inst,collInst)
{
    //	alert(inst.split('#')[1]);
    //	alert(collInst.split('#')[1]);
	deleteTableInst();

	if(collInst.split('#')[1]=='' && inst.split('#')[1]!='')
{
		
		addRowToTableInst(inst);
		
}
	if(collInst.split('#')[1]!='' && inst.split('#')[1]=='')
{
		
			addRowToTableInst(collInst);
}
			
	if(collInst.split('#')[1]!='' && inst.split('#')[1]!='')
        {      
		addRowToTableInst(inst);
		addRowToTableInst(collInst);
		}
		
/* 	if( (inst.split('#')[1]==null || collInst.split('#')[1]=='') && (collInst.split('#')[1]==null || inst.split('#')[1]==''))
		addRowToTableInst('-#No Instructions Available');
 */	popup("popUpDiv");
	
	}
	
	
function closeInstructions()
{
	popup("popUpDiv");

	
	}
	
	
function addRowToTableInst(inst)
{
	
	
	var nRow=0;
	var tableObj=document.getElementById('allInstructions');
	
	var tr=document.createElement("TR");
	 
	//tr.setAttribute("id","setPdf");
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst.split('#')[0]+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	inst.split('#')[1].replace("\r\n","<br>");
	td2.innerHTML="<div align='left' >"+inst.split('#')[1]+"</div>";   
	td2.className='tdfont';
	td2.colspan="1";
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
}



function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function httpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		initReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			 request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		{
			initReq(reqType,url,asynch);
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
}

function initReq(reqType,url,isAsynch)
{
	//alert("inside init request");
 /* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
 /* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	var queryString;
	request.send(queryString);
}

function ValidateSameCrNo(obj)
{
	
	document.getElementById('goButton').style.display="none";
	
	document.getElementById('nextDiv').style.display="";
	
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    	alert("Please Select Same CR No.");
			    	//document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
				}
		}
	}
	//displaySamplePatDetails();
}
function showPatList ()
{
	document.getElementById('goButton').style.display="none";
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('hmode')[0].value='GETPATLIST';
	document.forms[0].submit();
	}
	
function submitFor()
{
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('isSampleAreaSelected')[0].value="0";
	document.getElementsByName('sampleAreaCode')[0].value="-1";
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}	
	
function displaySamplePatDetails()
{	
	var count=0;
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }
  	//}
	  
 
      }
	
	if(count==0)
   	{
   	alert("please select a record");
   	return false;
   	}
	
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	document.forms[0].submit();
	}

function handleResponse()
{
	//alert("inside Response");
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			//alert("response sucessfull");
		}
		else
			alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	}
}

function showSearchDiv(obj)
{
	if(obj.value==-1)
	{
		document.getElementById('goButton').style.display="none";
	}
	else
	{
		document.getElementById('goButton').style.display="";
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
		
		document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
	
	
	//var sampleAreaCode=document.getElementByName("sampleAreaCode")[0].value;
}

function onClickSave(obj)
{
	if(obj.checked)document.getElementById("saveDiv").style.display=""; 
	else document.getElementById("saveDiv").style.display="none";
}

function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

//AJax Functions for checking Duplicacy
function chkSampleNoDuplicacyThroughAjax(obj,event,index)
{
	var sampleNo=obj.value;

	var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;

		var isSampleNoPresent = chkSampleNo(sampleNo,sampleAreaCode);
		
		isSampleNoPresent=isSampleNoPresent=="true"?true:false;
		
		if(isSampleNoPresent)
		{
			alert("Sample Number already present");
			obj.value="";
			obj.focus();
			return false;
		}
		
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	for(var i=0;i<chkBoxSample.length;i++)
		{
		    var chkIndex=chkBoxSample[i].value.split("#")[8];
			var tmpSampleNo=document.getElementsByName("sampleNo")[chkIndex].value;
			
			if(sampleNo==tmpSampleNo&&sampleNo!=""&&index!=chkIndex)
			{
				alert("Sample Number Already Present");
				obj.value="";
				obj.focus();
				return false;
			}
		}
	
	return true;
}

function chkSampleNo(sampleNo,sampleAreaCode)
{
	var flg = false;
	var isSampleNoPresent = false;
	var _mode = "AJX_DUPLICACY_SAMPLENO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&strSampleNo="+sampleNo+"&sampleAreaCode="+sampleAreaCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isSampleNoPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isSampleNoPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isSampleNoPresent;
}



//End AjaxFunctions

function validateSampleCollection()
{
		var chkBoxSample=document.getElementsByName("chkSamplePatient");
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one billed Patient");
				return false;
			}
		for(var i=0;i<chkBoxSample.length;i++)
			{
			    var index=chkBoxSample[i].value.split("#")[8];
				var sampleNo=document.getElementsByName("sampleNo")[index].value;
				var sampleQnty=document.getElementsByName("sampleQnty")[index].value;
				var defaultUOMCode=document.getElementsByName("defaultUOMCode")[index].value;
				var defaultContainerCode=document.getElementsByName("defaultContainerCode")[index].value;
				
				if(sampleNo==null||sampleNo==""||sampleNo=='')
				{
					alert("Please Enter Daily Sample No");
					document.getElementsByName("sampleNo")[index].focus();
					return false;
				}
				if(sampleQnty==null||sampleQnty==""||sampleQnty=='')
				{
					alert("Please Enter Daily Sample No");
					document.getElementsByName("sampleQnty")[index].focus();
					return false;
				}
				if(defaultUOMCode=="-1")
					{
						alert("Please select Unit of Measurement");
						document.getElementsByName("defaultUOMCode")[index].focus();
						return false;
					}
				else if(defaultContainerCode=="-1")
				{
					alert("Please select Container");
					document.getElementsByName("defaultContainerCode")[index].focus();
					return false;
				}
			}
		return true;
}


//new function to select all
function onSelectAll(obj)
{	
	var selectAll=document.getElementsByName("chkSamplePatientLab");

	
	for(var i=0;i<selectAll.length;i++)
		{
		
		if(obj.checked)
			{
		if(!selectAll[i].checked)
			{
			
			selectAll[i].checked=true;
			onClickReqNoChkBox(selectAll[i],1,1);
			}
		else
			;
		
		}
		
	else
		{
		
		selectAll[i].checked=false;
		
		var subBox=document.getElementsByName("chkSamplePatient");
		
		for(var k=0;k<subBox.length;k++)
		{
			
			
			subBox[i].checked=false;
			
			
		}
		
		
		}
		
		
		}
}

function onClickReqNoChkBox(obj,k,j)
{
	var reqNo=obj.value;
	var checked=true;
	if(obj.checked)
		{
		checked=true;
		document.getElementById("saveDiv").style.display=""; 
		}
	else
		checked=false;
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	for(var i=0;i<chkBoxSample.length;i++)
		{
		 
		//chkBoxSample[i].checked=false;
		var checkValueForAutoGen= chkBoxSample[i].value;
		var splitedAutoGenValue=checkValueForAutoGen.split("#");
		//alert(checkValueForAutoGen);
		var labCode=splitedAutoGenValue[4];
		var testCode=splitedAutoGenValue[6];
		var patType=splitedAutoGenValue[10];
		//var tempSampleNo=splitedAutoGenValue[9];
		var sampleArea=document.getElementsByName("sampleAreaCode")[0].value;
	
		var labName=splitedAutoGenValue[11];
		var testName=splitedAutoGenValue[12];
		var sampleconfig=splitedAutoGenValue[9];
		//alert(sampleconfig);
		//alert(autoGenFormate); 
	if(sampleconfig==1||sampleconfig==2)	 
		 {
		var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig,sampleArea);
	if(autoGenFormate!='null')
		{
		var SplittmpReqVal=chkBoxSample[i].value.split("#");
		var tmpReqNo=SplittmpReqVal[1]+SplittmpReqVal[2];
			
			if(reqNo==tmpReqNo)
				chkBoxSample[i].checked=checked;
			var autoValue="#";
			autoValue+=autoGenFormate;
			document.getElementsByName("chkSamplePatient")[i].value+=autoValue;
		}
		else
			{
			var SplittmpReqVal=chkBoxSample[i].value.split("#");
			var tmpReqNo=SplittmpReqVal[1]+SplittmpReqVal[2];
			if(sampleconfig==1&&reqNo==tmpReqNo)
			alert("No Sample Number Formate Is Configured For    "+  testName  + "("+ labName +")   Please Configure From Sample Number Configuration Master ");
			 
			if(sampleconfig==2&&reqNo==tmpReqNo)
				alert("No Sample Number Formate Is Configured For  "+  testName  + " ( "+  labName  +") Lab  Please Configure From Collection Area Sample Number Configuration Master ");
			//if(reqNo==tmpReqNo)
				chkBoxSample[i].checked=false;
			var cbs =document.getElementsByName('chkSamplePatientLab');
		     
				var id=cbs[i].id;
				 //alert(id);
				var indexWithSubIndex=id.substring(0,2);
				//alert(cbs[indexWithSubIndex+"unCheck"]);
				document.getElementById(indexWithSubIndex+"unCheck").checked = false;
				//cbs[indexWithSubIndex+"unCheck"].id.checked=false;
		    			 
			}
		}
	else
		{
		var autoValue="#null#null#null#null#null#null#null#null#null#null#null#null#null";
		autoValue+=autoGenFormate;
		document.getElementsByName("chkSamplePatient")[i].value+=autoValue;
		var SplittmpReqVal=chkBoxSample[i].value.split("#");
		var tmpReqNo=SplittmpReqVal[1]+SplittmpReqVal[2];
		if(reqNo==tmpReqNo)
			chkBoxSample[i].checked=checked;
		}
		}//for Loop
}

function checkAutoGen(obj,k,j,index)
{
	
	 
	if(obj.checked)
		{
		checked=true;
	 
		
		//alert(k++j+index+"unCheck");
		document.getElementById("saveDiv").style.display=""; 
		var checkValueForAutoGen=document.getElementById(k+""+""+j+""+index+"unCheck").value;
		var splitedAutoGenValue=checkValueForAutoGen.split("#");
		//alert(checkValueForAutoGen);
		var labCode=splitedAutoGenValue[4];
		var testCode=splitedAutoGenValue[6];
		var patType=splitedAutoGenValue[10];
		//var tempSampleNo=splitedAutoGenValue[9];
		var sampleArea=document.getElementsByName("sampleAreaCode")[0].value;
		var labName=splitedAutoGenValue[11];
		var testName=splitedAutoGenValue[12];
		var sampleconfig=splitedAutoGenValue[9];
	//	alert(sampleconfig);
		if(sampleconfig==1||sampleconfig==2)	 
		 {
			var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig,sampleArea);
		
			if(autoGenFormate!='null')
			{
			 
				
				document.getElementById(k+""+""+j+""+index+"unCheck").checked=true;
				var autoValue="#";
				autoValue+=autoGenFormate;
				document.getElementById(k+""+""+j+""+index+"unCheck").value+=autoValue;
			}
			else
				{
				 
				if(sampleconfig==1)
				alert("No Sample Number Formate Is Configured For    "+  testName  + "("+ labName +")   Please Configure From Sample Number Configuration Master ");
				 
				if(sampleconfig==2)
					alert("No Sample Number Formate Is Configured For  "+  testName  + " ( "+  labName  +") Lab  Please Configure From Collection Area Sample Number Configuration Master ");
				 
				document.getElementById(k+""+""+j+""+index+"unCheck").checked=false;
				 
			    			 
				}
		 
		 
		 }
		else
			{
			
			}
		
		}
	else
		checked=false;
}


//select all patients and check for diff patients

function validateSameCrNoAllSelected(obj)
{
	document.getElementById('goButton').style.display="none";
	document.getElementById('nextDiv').style.display="";
	
	var objCurrentCheckBox=obj.value;
	//alert(obj.value);
	if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		
		
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			    	if(objCurrentCheckBox=="on")
			    		objCurrentCheckBox=cbs[i].value;
			    	
			    	if(!cbs[i].checked)
			    		cbs[i].checked=true;
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    	alert("The list contains different CR Nos. Please select same CR Nos.");
			    	//document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	cbs[i].checked=false;
			    	
			    	document.getElementById('goButton').style.display="";
			    	
			    	
			    	
			    	return false;
			    	} 
				}
		}
	}
	
	
	}
	
	
	
	
 

function CheckAutoSampleNoFormate(LabCode,TestCode,patType,tempSampleNo,sampleArea)
{

	//alert("inside Ajax"+LabCode+TestCode+patType);
	
	var flg = false;
	var autoGenFormate = "";
	var _mode = "AJX_CHECK_AUTO_SAMPLENO_GEN";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollectionCumAcceptance.cnt?hmode="+_mode+"&labCode="+LabCode+"&testCode="+TestCode+"&patType="+patType+"&tempSampleNo="+tempSampleNo+"&sampleAreaCode="+sampleArea, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			autoGenFormate = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return autoGenFormate;
}





function onClickGO(hospitalCode)
{
	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;

     if(!validateTodayOrDate())
      	 {return false;}
       
     if(hosCodeLen==3)
		{ 
          if (textLength==5||textLength==13||textLength==0)
	        {
             
        	  document.getElementsByName("patCRNo")[0].value="";
        	 
        	  if(textLength==13)
	           {
	             document.getElementsByName("patCRNo")[0].value=crno;
	            }
                  
             document.getElementById('goButton').style.display="none";
         	document.getElementsByName('showStatus')[0].value="0";
         	document.getElementsByName('hmode')[0].value='GETPATLIST';
         	document.forms[0].submit();
        	   
             }

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("tempPatCRNo")[0])
	            {
	            document.getElementsByName("tempPatCRNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
			    if (textLength==7||textLength==15||textLength==0)
				 {
			  	  document.getElementsByName("patCRNo")[0].value="";
			    	
			    	if(textLength==15)
						 {
						 document.getElementsByName("patCRNo")[0].value=crno;
						 }
					         
					 document.getElementById('goButton').style.display="none";
						document.getElementsByName('showStatus')[0].value="0";
						document.getElementsByName('hmode')[0].value='GETPATLIST';
						document.forms[0].submit();
					     }
					
			     else
			     {     
				   		    alert("InValid CR No");
				     		if(document.getElementsByName("tempPatCRNo")[0]){
				            document.getElementsByName("tempPatCRNo")[0].focus()
				                      }    
			          
			     }
          }

     
  	
   return true;
}

function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}

function showSearchDivOnLoad()
{
	
	//var length = $('#sampleAreaCode').children('option').length;

//alert("jquery"+length);

	var check=document.getElementsByName("sampleAreaCode")[0].length;
	
	//alert("javascripts"+check);
	//var length = $('#sampleAreaCode > option').length;
	
	
	if(check!=1)
	{
		document.getElementById('goButton').style.display="";
    }
	else
	{
		document.getElementById('goButton').style.display="";
	document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
		document.getElementsByName('hmode')[0].value='GETPATLIST';
		//document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
}

function cancelFunc()
{
	window.parent.closeTab();
}


</script>
<style>
.textBoxCss {
    background: white;
    color: #135d8c;
    width: 180px;
    padding: 4px 10px 4px 15px;
    border-radius: 20px;
    box-shadow: 0 1px 0 #ccc inset;
    transition: 500ms all ease;
    outline: 0;
}
</style>
 

<% String strdivage="\"\"";
String strdivdob="\"\""; %>

 <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String frDate="";
              String tDate="";
               
         %>


<body onload="showSearchDivOnLoad()">

<html:form action="/sampleCollectionCumAcceptance">

<!-- START -- TO DISPLAY INSTRUCTIONS -->
 	
 		
 		
 		<div id="blanket" style="display: none;"></div>
 		<div id="popUpDiv"  style="display:none;">  
		 
		<his:TitleTag name="Instructions">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
  		</his:TitleTag>
  			
  			
 		<table width="100%" id="allInstructions">
 	             
 		<tr>
 		
 		<td class="tdfonthead" width="20%">
		<div align="left"><b>Instructions for:</b></div>
		</td>
		
		<td class="tdfonthead" width="80%">
		<div align="center"><b>Instructions</b></div>
		</td>
						
		</tr>
 		</table>
 		 <br>  
 		   <div align="center">                          
 		 <img src='/HISInvestigationG5/hisglobal/images/ok.gif'
			id='closeInst' onclick="closeInstructions();">                       
					</div>

		</div>
	
	
 	
 	<!-- END -- TO DISPLAY INSTRUCTIONS -->
  
 <his:TitleTag name="Online Sample Collection Cum Acceptance"> 	 
<%--   <his:insertDateTag/> --%>
  </his:TitleTag> 
<%
		String mobileNo="";
  		 String emailId="";
  		String patAddress="";
  		
  		if(session.getAttribute(Config.SELECTED_FROM_DATE) !=null)
  		 	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SELECTED_FROM_DATE), "dd-MMM-yyyy");
  			
  		if(session.getAttribute(Config.SELECTED_TO_DATE) !=null)
  			tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SELECTED_TO_DATE), "dd-MMM-yyyy");
  		
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	//frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
        	Calendar cal = Calendar.getInstance();
        	cal.setTime(dt);
        	cal.add(Calendar.DATE, -7);
        	Date dateBefore7Days = cal.getTime();
        	frDate = WebUTIL.getCustomisedSysDate(dateBefore7Days, "dd-MMM-yyyy");
        	System.out.println("From Date, 7 days before current date   : "+frDate);
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%>  
 
 
 
  
 	<logic:equal name="SampleCollectionCumAcceptanceFB" property="showStatus" value="0">
		  <his:SubTitleTag name="Sample Details"></
		  </his:SubTitleTag>
 		<his:ContentTag>
	 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<table width="100%" >
				 			<tr>
					 			<td class="tdfont" width="25%">
					 				<div align="right">
					 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:message key="sampleColl"/> &nbsp;
													 </font>
					 					
					 				</div>
					 			</td>
					 			<td class="tdfonthead" width="25%" >
					 		<div align="left">
					 			<logic:notEqual name="SampleCollectionCumAcceptanceFB" property="isSampleAreaSelected" value="1">
						 				  <span class="custom-dropdown small">
						 				<html:select    name="SampleCollectionCumAcceptanceFB" property="sampleAreaCode" tabindex="1"  onchange="showSearchDiv(this)">					
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
												<%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
													 <html:option value="-2">All</html:option> 
												<%} %>
 											 
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
										</span>
									</logic:notEqual>
									 <logic:equal name="SampleCollectionCumAcceptanceFB" property="isSampleAreaSelected" value="1">
									 	<div align="left">
					 					  <bean:write name="SampleCollectionCumAcceptanceFB" property="sampleAreaName"/>
					 					  <html:hidden name="SampleCollectionCumAcceptanceFB" property="sampleAreaCode"/>
					 					</div>
									 </logic:equal>
									 </div>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfonthead" width="25%"></td>
				 			 </tr>	
				 			 </table>
				 			 <logic:equal name="SampleCollectionCumAcceptanceFB" property="isSampleAreaSelected" value="1">
						 			 <table width="100%" >
						 			   <%
			    
							      UserVO uservo=ControllerUTIL.getUserVO(request);
						 			  Date todayDateobj = new Date();
						 			   SimpleDateFormat dateob = new SimpleDateFormat("yy");
										String strDate= dateob.format(todayDateobj);
								      String hospitalCode=uservo.getHospitalCode();
								      String val=uservo.getHospitalCode()+strDate;
							      
			                           %>
						 			  		<tr>
										    <td width="25%" class="tdfont">
										        <div align="right">
										             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
														 
													 </font> 
													 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
															<bean:message key="crNO"/>&nbsp;
													 </font>
											     </div>
										      </td>
										      <td width="25%" class="tdfonthead">
										      <div align="left">                                                                                                           
											 <input type="text" class="textBoxCss" name="tempPatCRNo" value="<%=val %>" maxlength="15" size="20" onkeypress="return validateNumeric(event,this)" tabindex="1" />
											  </div>
										     </td>
										     <td width="25%" class="tdfont">
										         
										      </td>
										      <td width="25%" class="tdfonthead">
										       
										     </td>
										     </tr>
						 			 
											<tr>            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="fromDate"/>
														</font>
									        		</div>
									        		
												</td>
												<td class="tdfonthead" width="25%">
										    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
														<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
													</div>
											 		
												</td>
									 		            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="toDate"/>
														</font>
									        		</div>
												</td>
												
												<td class="tdfonthead" width="25%">
										    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
														<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
													</div>
											 		
												</td>
								 		</tr>
								</table>
						</logic:equal>
				</logic:present>
		</his:ContentTag>
			</logic:equal>
	<his:statusTransactionInProcess>
		<his:ContentTag>
			 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((SampleCollectionCumAcceptanceFB)request.getAttribute("SampleCollectionCumAcceptanceFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
	<logic:equal name="SampleCollectionCumAcceptanceFB" property="showStatus" value="0">
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO %>">
			
			<div align="center">
			  <logic:equal name="SampleCollectionCumAcceptanceFB" property="isSampleAreaSelected" value="1">
				     	<logic:notEqual name="SampleCollectionCumAcceptanceFB" property="showStatus" value="1">
				     	<%
				     	
				     	UserVO uservo=ControllerUTIL.getUserVO(request);
					      String hospitalCode=uservo.getHospitalCode();
				     	%>
				    	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')" onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
				     
				    
				    	</logic:notEqual>
				    		
				    </logic:equal>
			
			<!--start-- back button when multiple sample collection areas present and you have to go back to combo list -->
				 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
						<%if(patSampleCollection.size()>1){ %>
									<img class="button" src='<his:path src="/hisglobal/images/back_tab.png"/>' id="saveDiv"  tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
						<%} %>
						</logic:present>
			<!--end--   back button when multiple sample collection areas present and you have to go back to combo list -->
			
			</div>
			
			<his:SubTitleTag name="Sample Collected Details"></
  			</his:SubTitleTag>
			<table   width= "100%" bgcolor="#EBEBEB"  >
				<tr>
			
	<%-- 				<td width="5%" align="left">	
					<b>	<font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="select"/></font></b>
					</td>
					 --%>
					
						 <td align="left" width ="5%">
						
						 
				 			    <div align="left"> <input type="checkbox" name="allSelectLab" onChange="validateSameCrNoAllSelected(this)" tabindex="1"> </div>
				 			    </td>
				 		    
				 			    
				 			    
					<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="crNO"/></font></b>
					</td>
				<%-- 	<td width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqNo"/></font></b>
					</td> --%>
					<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="labName"/></font>
						</b>	
					</td>
					<td width="15%" align="left">
						<b> 
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqDate"/></font>
					     </b>
					</td>	
						<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patientName"/></font></b>
					</td>
					<td width="5%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="age" /></font></b>
					</td>
					<td width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patStatus"/></font></b>
					</td>
					<td width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="departmentunit"/></font></b>
					</td>
					<td width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="visitDate"/></font></b>
					</td>
			
				</tr>
			 	 
			
			 
			
			<logic:notEmpty name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO %>">
			 		 
					<%
					 List<SampleCollectionCumAcceptanceVO> lstSamCVO=(List<SampleCollectionCumAcceptanceVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
			 		
					 int  size=0;
			 		 
			 		if(lstSamCVO!=null && lstSamCVO.size()>0 )
			 			size=lstSamCVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
	 		
					for(int j=startIndex;j<=endIndex;j++)
					{
						//int i=j-startIndex;
						 
					if(j<size)
									{
						SampleCollectionCumAcceptanceVO voSam=lstSamCVO.get(j);
					
					String chkVal=voSam.getPatCRNo()+"#"+voSam.getRequisitionNo()+"#"+voSam.getLabCode()+"#"+voSam.getSampleCode()+"#"+j+"#"+voSam.getTestCode();
					%>
					<tr>
					
						<td width="5%" align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" tabindex="1">
							</font>
						</td>
						<td width="10%" align="left">
						 <div align="left">
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatCRNo() %></font> 
						 </div>
				  		</td>
				  		
				  		<%-- <td width="15%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionNo() %></font>
						 </div>
				  		
				  		</td> --%>
				  		<td width="10%" align="left">
				  		 
				  		
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getLabName() %></font>
						 </div>
				  		</td>
				  		<td width="15%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionDate() %></font>
						 </div>
				  		
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatName() %></font>
						 </div>
				  		</td>
				  		<td width="5%" align="left">
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatAge() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatStatus()%></font>
						 </div>
				  		</td>
			  		<td width="15%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatDeptName()+"/"+voSam.getPatUnitName() %></font>
						 </div>
				  		</td>
				  		<td width="15%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatVisitDate()%></font>
						 </div>
				  		</td>
					</tr>
					<%}  }%>
			</logic:notEmpty>
				 <logic:empty name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO%>">
				
				<center>
				<font color="red" size="4">
				<bean:message key="noSampleColl"/></font></center>
			</logic:empty>
			</table>
			</table>
			</logic:present>
			</logic:equal>
			</his:ContentTag>
			<!-- New Logic -->
			<logic:present name="<%=InvestigationConfig.SELECTED_PAT_DETAILS %>">
			<his:ContentTag>
			<his:SubTitleTag name="Patient Details"></
  			</his:SubTitleTag>
			<%
			SampleCollectionCumAcceptanceVO voSampleCollection= (SampleCollectionCumAcceptanceVO)session.getAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
			
			if(voSampleCollection.getPatStatus()!=null)
			{
			%>
			
			<%
				String staffImage = (String) session.getAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE);
				//System.out.println("staffImage jsp : "+staffImage);
				
			%>
			
			<div id="ipddDiv">
			<table width="100%">
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<bean:message key="crNO"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 					<%=voSampleCollection.getPatCRNo() %>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<bean:message key="patientName"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 					<%=voSampleCollection.getPatName() %>
		 				</div>
		 			</td> 
			</tr>
			 <tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 				<bean:message key="age/gender"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 					<%=voSampleCollection.getPatAge()+"/"+voSampleCollection.getPatGenderCode() %>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<bean:message key="patStatus"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 					<%=voSampleCollection.getPatStatus() %>
		 				</div>
		 			</td>
			</tr>
			<tr>
		 		<%--	<td class="tdfonthead" width="25%">
		 				<div align="right">
		 					Consultant Name
		 				</div> 
		 			</td>
		 			<td class="tdfont" width="25%">
		 				<div align="left">
		 					<%=voSampleCollection.getPatOrderByDoc() %>
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"></td>
		 			<td class="tdfont" width="25%"></td>
		 			 <td class="tdfonthead" width="25%">
		 				<div align="right">
		 					Diagnosis
		 				</div>
		 			</td>
		 			<td class="tdfont" width="25%">
		 				<div align="left">
		 					<%=patDtlVO.getDiagnosis() %>
		 				</div>
		 			</td> --%>
			</tr> 
			
			<%if(staffImage != "")
			{
			%>
			
			 <tr>
			 		<td class="tdfont" width="25%">
		 				<div align="right">
		 					Staff Dependent's Photo
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<img id="imageid"  src="data:image/png;base64,<%=staffImage%>"  style="width:100px; height:100px;" border="1" >
		 				</div>
		 			</td>
			</tr>
			 <%}  %>
		
		</table>
		
		</div>
			
			
			
			<%  mobileNo=(voSampleCollection.getPatMobile()==null?"":voSampleCollection.getPatMobile());
			 	emailId=(voSampleCollection.getPatEmail()==null?"":voSampleCollection.getPatEmail());
			 	patAddress=(voSampleCollection.getPatAddress()==null?"":voSampleCollection.getPatAddress());
			if(voSampleCollection.getPatStatus().equals("IPD"))
			{
			%>
			
			
			
			<his:SubTitleTag name="Ipd Details"></
  			</his:SubTitleTag>
			<div id="ipddDiv">
				<table width="100%">
				<tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="admitdept"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%"> 
			 				<div align="left">
			 					<%=voSampleCollection.getPatDeptName() %>
			 				</div>
			 			</td>
			 			 <td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="wardName"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatWardName() %>
			 				</div>
			 			</td> 
				</tr>
				 <tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="roomName"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatRoomName()%>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="bedName"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatBedName()==null?"":voSampleCollection.getPatBedName()) %>
			 				</div>
			 			</td>
				</tr>
				<tr>
			 			<td class="tdfonthead" width="25%">
			 				<div align="right">
			 				
<bean:message key="consultant"/>
			 				</div> 
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatOrderByDoc()==null?"":voSampleCollection.getPatOrderByDoc()) %>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%"></td>
			 			<td class="tdfonthead" width="25%"></td>
			 			<%-- <td class="tdfonthead" width="25%">
			 				<div align="right">
			 					Diagnosis
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="left">
			 					<%=patDtlVO.getDiagnosis() %>
			 				</div>
			 			</td> --%>
				</tr> 
			
			</table>
			
			</div>
			<%}  %>
			<% 
			
			String patcetcode="";
			if(voSampleCollection.getPatCategoryCode()!=null )
			{
				patcetcode=voSampleCollection.getPatCategoryCode();
			}
			%>
				<html:hidden name="SampleCollectionCumAcceptanceFB" property="patCategoryCode"  value="<%=patcetcode %>"/>
			<%
			if(voSampleCollection.getPatStatus().equals("OPD"))
			{
			%>
			<his:SubTitleTag name="Opd Details"></
  			</his:SubTitleTag>
			 <div id="opdEmerencyDIV">
			 	<table width="100%">
				<tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 				<bean:message key="departmentunit"/>&nbsp;
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%"> 
			 				<div align="left">
			 					<%=(voSampleCollection.getPatUnitName()==null?"":voSampleCollection.getPatUnitName())+"/"+voSampleCollection.getPatUnitName() %>
			 				</div>
			 			</td>
			 			 <td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="age/gender"/>&nbsp;
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatAge()+"/"+voSampleCollection.getPatGenderCode() %>
			 				</div>
			 			</td> 
				</tr>
				 <tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					
<bean:message key="visitDate"/>&nbsp;
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatVisitDate()%>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="patCat"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatCategory()==null?"":voSampleCollection.getPatCategory()) %>
			 				</div>
			 			</td>
				</tr>
				<tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="consultant"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatOrderByDoc()==null?"":voSampleCollection.getPatOrderByDoc()) %>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%"></td>
			 			<td class="tdfonthead" width="25%"></td>
			 			<%-- <td class="tdfonthead" width="25%">
			 				<div align="right">
			 					Diagnosis
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="left">
			 					<%=patDtlVO.getDiagnosis() %>
			 				</div>
			 			</td> --%>
				</tr> 
			
			</table>
			 </div>
			<%} %>
			<%} %>
			</his:ContentTag>
		</logic:present>
		
			
		<logic:notEqual name="SampleCollectionCumAcceptanceFB" property="showStatus" value="0">
		<his:ContentTag>
			<table width="100%">
			
				<tr>
					<td class="tdfont" width="25%">
						<div>
							<bean:message key="mobile"/>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div>
						<input type="text" class="textBoxCss" name="patMobile"   maxlength="10" size="15" value="<%=mobileNo %>" onkeypress="return validateNumeric(event)" tabindex="1" />
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div>
							<bean:message key="mail"/>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div>
						<input type="text" class="textBoxCss" name="patEmail"   maxlength="40" size="40" value="<%=emailId %>" onkeypress="return validateEmai(this)" tabindex="1" />
						
						</div>
					</td>
			</tr>
			 
			</table>
			</his:ContentTag>
		</logic:notEqual>
 		
 			<logic:notEmpty name="<%=InvestigationConfig.MAP_PAT_SAMPLE_BILLED %>">
 		<!--  Billed Details -->
	 		<logic:present name="<%=InvestigationConfig.MAP_PAT_SAMPLE_BILLED %>">
 		<his:ContentTag>
 		<his:SubTitleTag name="Billed Details"></
  			</his:SubTitleTag>
	 		<table width="100%" >
	 			<tr >
	 					<%-- 		<td class="tdfont" >
					 				<div align="left">
					 					<bean:message key="select"/>&nbsp;
					 				</div>
					 			</td> --%>
					 				 <td class="tdfont" width="3%">
				 			    <div align="right"> <input type="checkbox" name="allSelectLab" onChange="onSelectAll(this)" > </div>
				 			    </td>
				 			    
					 			<td class="tdfont" width="10%">
					 				<div align="left">
					 					 <bean:message key="labName"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" width="17%">
					 				<div align="left">
					 				<bean:message key="TestName"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 				<bean:message key="machine"/>&nbsp;
					 				</div>
					 			</td>
					 			
					 			
					 		<%-- 	<td class="tdfonthead" >
					 			<div align="left">
					 				<bean:message key="reqNo"/>&nbsp; 
					 				</div>
					 			</td> --%>
					 			<td class="tdfont" width="10%">
					 			<div align="left">
					 					<bean:message key="reqDate"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" width="10%">
					 			<div align="left">
					 					<bean:message key="specimen"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" width="10%">
					 			<div align="left">
					 					<bean:message key="sampleNo"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" width="5%">
					 			<div align="left">
					 				Lab Number
					 				</div>
					 			</td>
					 			<td class="tdfont" width="10%">
					 			<div align="left">
					 					<bean:message key="collVoll"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" width="10%">
					 			<div align="left">
					 				<bean:message key="uom"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" width="10%">
					 			<div align="left">
					 				<bean:message key="container"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" width="5%">
					 			<div align="left">
					 				<bean:message key="priority"/>
					 				</div>
					 			</td>
					 			<!-- added by krishnan nema on 22/10/2018 -->
					 			
					 			
				 			 </tr>
	 		<%
	 		Map<String,List<SampleCollectionCumAcceptanceVO>> mpBilled= (Map<String,List<SampleCollectionCumAcceptanceVO>>)session.getAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
 			SampleCollectionCumAcceptanceVO voPatDetails= (SampleCollectionCumAcceptanceVO)session.getAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);

	 		
	 		
	 		String testMachineCombo=(String)session.getAttribute(InvestigationConfig.LIST_MACHINE_COMBO);
			String testMachineString=(String)session.getAttribute(InvestigationConfig.LIST_MACHINE_STRING);
			String[] splitTestMachineString=null;
			if(testMachineString!=null && testMachineString.equals("")==false)
			splitTestMachineString=testMachineString.split("@");
			else
				;
			
			
	 		Iterator itr=mpBilled.keySet().iterator();
	 		int index=0;
	 		boolean sameReqNo=false;
	 		int j=0;
	 		while(itr.hasNext())
	 		{
	 			
	 			boolean firstTimeTravesa=true;
	 			String hashBasedValue=(String)itr.next();
	 			
	 			String[] arrHashBasedValue=hashBasedValue.split("#");
	 			
	 			String reqNo=arrHashBasedValue[0];
	 			String labCode=arrHashBasedValue[1];
	 			String sampleCode=arrHashBasedValue[2];
	 			 String reqNoAndSampleCode=reqNo+sampleCode;	 
	 			List<SampleCollectionCumAcceptanceVO> lstVOSample=mpBilled.get(hashBasedValue);
	 			int rowSpanSize=lstVOSample.size();
	 			rowSpanSize=1;
	 			
	 			
	 			
	 			if(lstVOSample.size()>1)
	 				sameReqNo=true;
	 			String color="blue";
	 			int priorityCode=1;
	 			
	 			for(int k=0;k<lstVOSample.size();k++)
	 			{
	 			SampleCollectionCumAcceptanceVO voSampleCollection=lstVOSample.get(k);
	 			if(voSampleCollection.getPriorityCode()!=null)   // Normal;Urgent;Confidential
	 			{
	 				priorityCode=Integer.parseInt(voSampleCollection.getPriorityAllCode());
	 				switch(priorityCode)
	 				{
		 				case 1: color="blue";  // Normal Priority;
		 				        break;
		 				case 2: color="red";  // Urgent Priority;
					        	break;
		 				case 3: color="brown";  // Confidential Priority;
					        	break;
					    default: color="blue";
					    		break;
	 				}
	 			}
	 			
	 			String sampleConfiguration=voSampleCollection.getSampleNoConfiguration()==null?"3":voSampleCollection.getSampleNoConfiguration();
	 			boolean isSampleGenAuto=false;
	 			String readOnly="";
	 			if(sampleConfiguration.equals(InvestigationConfig.SAMPLE_COLLECTION_MODE)||sampleConfiguration.equals(InvestigationConfig.SAMPLE_COLLECTION_CUM_ACCEPTANCE_MODE))
	 				isSampleGenAuto=true;  //readOnly="readonly='true'"; //
	 				if(firstTimeTravesa)
		 			{
	 			
	 		%>
				 		
				 			<tr>
				 			    <%String chkBox="onClickReqNoChkBox(this,"+k+","+j+")"; %>
				 			    <td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="3%">
				 			    <div> <input type="checkbox" name="chkSamplePatientLab" id="<%=k%><%=j%>unCheck" onChange="<%=chkBox%>" value="<%=reqNoAndSampleCode%>"> </div>
				 			    </td>
					 			<td  class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 				<div align="left">
					 				<font color="<%=color%>">	<%=voSampleCollection.getLabName() %> </font>
					 				</div>
					 			</td>
					 			<td width="23%"> 
						 			<div align="left">
							 			<table>
											 	<% int x=0;
											 	for(SampleCollectionCumAcceptanceVO voSample:lstVOSample)  // For Iterating Test of Same Sample,lab
									 			   { 
											 		String chkSampleVal=voSample.getPatCRNo()+"#"+voSample.getRequisitionNo()+"#"+voSample.getSampleCode()+"#"+voSample.getRequisitionDNo()+"#"+voSample.getLabCode()+"#"+voSample.getBillNo()+"#"+voSample.getTestCode()+"#"+voSample.getSampleName()+"#"+index+"#"+sampleConfiguration+"#"+voSample.getPatType()+"#"+voSample.getLabName()+"#"+voSample.getTestName()+"$"+voPatDetails.getPatName()+"$"+voPatDetails.getPatAge()+"$"+voPatDetails.getPatGenderCode();
											 	
											 		String defaultMachineId="-1";
										 			String testWiseMachineCombo="";
										 			
										 			if(splitTestMachineString!=null)
										 			 for(int kk=0;kk<splitTestMachineString.length;kk++)
										 			{
										 				String testcodes=splitTestMachineString[kk].split("#")[2];
									 					String[] testcode=testcodes.split("\\$\\$");
									 					
										 				for(int k1=0;k1<testcode.length;k1++)
										 				{
										 					
										 				
										 				if(testcode[k1].equals(voSample.getTestCode()))
										 				{
										 					defaultMachineId=splitTestMachineString[kk].split("#")[0];
										 					
										 					
										 					int idPlace=testMachineCombo.lastIndexOf(defaultMachineId);
										 					
										 					String part1=testMachineCombo.substring(0, idPlace+7);
										 					part1=part1+" selected ";
										 					String part2=testMachineCombo.substring(idPlace+7, testMachineCombo.length());
										 					
										 					testWiseMachineCombo=part1.concat(part2);
										 					
										 				}
										 			
										 				}
										 				
										 			} 
											 		if(testWiseMachineCombo.equals(""))
											 			testWiseMachineCombo=testMachineCombo;
											 		
											 		
											 		%>
											 			 <tr>
											 			   <%String subChkBox="checkAutoGen(this,"+k+","+j+","+x+")"; %>
											 			   	<td class="tdfonthead" width="1%">
											 			    	<div> <input type="checkbox" id="<%=k%><%=j%><%=x%>unCheck" name="chkSamplePatient"  onclick="<%=subChkBox%>" value="<%=chkSampleVal%>"> </div> 
											 			    </td>
												 			<td class="tdfonthead" colspan="10" width="17%">
													 				<div align=left>
													 				  <font color="<%=color%>">		<%=(voSample.getTestName()==null?"":voSample.getTestName()) %> </font>
													 				
													 				
													 				<%
													 				String patInstr= voSample.getPatInstruct()==null?"NA":voSample.getPatInstruct(); 
													 				String collInstr=voSample.getCollInstruct()==null?"NA":voSample.getCollInstruct();	
													 				System.out.println("chaa"+patInstr);
													 				System.out.println("chaa"+collInstr);
													 				patInstr=patInstr.replace("\r\n","<br>");
													 				collInstr=collInstr.replace("\r\n","<br>");
													 				%>
													 				<%
													 					if( patInstr.equalsIgnoreCase("Patient#") && collInstr.equalsIgnoreCase("Sample Collection#") )
													 					{
													 				%>
													 				
													 				 <% }
													 					else{%>
													 						<img align="right" class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'       tabindex="1" onclick ="showInstructions('<%=patInstr%>','<%=collInstr%>');">			
													 					<%} %>
													 				
													 				</div>
													 		 </td>
													 		 
													 		 <td class="tdfonthead" colspan="10" width="5%">
				 		   <div align="left" >
		 			   <span class="custom-dropdown small">
		 			   <select id="<%=k%>machine" name='testBasedMachine' tabindex='1'>
		 					<%=testWiseMachineCombo%>
		 			     </select>
		 			     </span>
		 			     </div>
				 		</td>
				 		
				 		
				 		
				 		
											 			 </tr>
									 			 <%++x;} %>
											 	
											 	
											 	

							 			 </table>
						 			 </div>
					 			</td>
					 			<%-- <td class="tdfont" rowspan="<%=rowSpanSize%>">
					 			<div align="left">
					 					<font color="<%=color%>">	 <%=voSampleCollection.getRequisitionNo() %> </font>
					 				</div>
					 			</td> --%>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getRequisitionDate() %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 			<div align="left">
					 				<font color="<%=color%>">		<%=(voSampleCollection.getSampleName()==null?"":voSampleCollection.getSampleName()) %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 			<div align="left">
					 				<font color="<%=color%>">	
 
					 				<% String strOnBlurFunction="chkSampleNoDuplicacyThroughAjax(this,event,"+index+");"; %>  
       			 			 
                                    
                                    <% if(isSampleGenAuto)
                                    {
                                    %>
                                     <input type="hidden"  name="sampleNo" readonly="true"  maxlength="10" size="10" value="<%=sampleConfiguration%>"   onkeypress="validateNumeric(event);"   tabindex="1" /> 
					 					<%}else{ %>
					 		 <input type="text"  name="sampleNo"  maxlength="10" size="10"    onkeypress="validateNumeric(event);" onblur="<%=strOnBlurFunction %>" tabindex="1" /> 
					 					
					 					<%} %> 
					 				</font>
					 				</div>
					 			</td>
					 			
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="5%">
					 			<div align="left">
					 				<input type="text"  name="labNumber"  maxlength="10" size="10"    onkeypress="validateNumeric(event);"  tabindex="1" />
					 				</div>
					 			</td>
					 			
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 				<div align="left">
					 					<html:text name="SampleCollectionCumAcceptanceFB" property="sampleQnty"  maxlength="10" size="10"  value='<%=voSampleCollection.getSampleQnty()==null?"":voSampleCollection.getSampleQnty() %>'  onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="SampleCollectionCumAcceptanceFB" property="defaultUOMCode"  value="<%=voSampleCollection.getDefaultUOMCode() %>" tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_UOM_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_UOM_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							  </span>
					 			</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="10%">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="SampleCollectionCumAcceptanceFB" property="defaultContainerCode" value="<%=voSampleCollection.getDefaultContainerCode() %>" tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							</span>
					 			</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" width="5%">
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getPriorityAll()==null?"":voSampleCollection.getPriorityAll() %> </font>
					 				</div>
					 				<html:hidden name="SampleCollectionCumAcceptanceFB" value="<%=voSampleCollection.getPriorityCode() %>"  property="priorityCode"/>
					 				
					 			</td>
					 			<!-- added by krishnan nema on 22/10/2018 -->
					 			
				 			 </tr>
		<%index++;
		if(sameReqNo)firstTimeTravesa=false;}}
	 			++j;}%>
								</table>
					</his:ContentTag>
				</logic:present>
 </logic:notEmpty>
 		<!--  Un Billed Details -->
 <logic:notEmpty name="<%=InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED %>">
	 	<logic:present name="<%=InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED %>">
 		<his:ContentTag>
 		<his:SubTitleTag name="Unbilled Details"></
  			</his:SubTitleTag>
	 		<table width="100%" >
	 			<tr >
					 			<td class="tdfont" >
					 				<div align="left">
					 					<bean:message key="labName"/>&nbsp;
					 				</div>
					 			</td>
					 			<%-- <td class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="reqNo"/>&nbsp; 
					 				</div>
					 			</td> --%>
					 			<td class="tdfont" >
					 			<div align="left">
					 					<bean:message key="reqDate"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 					<bean:message key="TestName"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 					
									<bean:message key="priority"/>
					 				</div>
					 			</td>
				 			 </tr>
	 		<%
	 		Map<String,List<SampleCollectionCumAcceptanceVO>> mpUnBilled= (Map<String,List<SampleCollectionCumAcceptanceVO>>)session.getAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
	 		Iterator itr=mpUnBilled.keySet().iterator();
	 		int index=0;
	 		while(itr.hasNext())
	 		{
	 			String hashBasedValue=(String)itr.next();
	 			
	 			String[] arrHashBasedValue=hashBasedValue.split("#");
	 			
	 			String reqNo=arrHashBasedValue[0];
	 			String labCode=arrHashBasedValue[1];
	 			String sampleCode=arrHashBasedValue[2];
	 			
	 			List<SampleCollectionCumAcceptanceVO> lstVOSample=mpUnBilled.get(hashBasedValue);
	 			int rowSpanSize=lstVOSample.size();
	 			
	 			if(rowSpanSize!=0)
	 				rowSpanSize=rowSpanSize-1;
	 		
	 		for(int k=0;k<lstVOSample.size();k++)
	 		{
	 			SampleCollectionCumAcceptanceVO voSampleCollection=lstVOSample.get(k);
	 			String color="blue";
	 			int priorityCode=1;
	 			if(voSampleCollection.getPriorityAllCode()!=null)   // Normal;Urgent;Confidential
	 			{
	 				priorityCode=Integer.parseInt(voSampleCollection.getPriorityAllCode());
	 				switch(priorityCode)
	 				{
		 				case 1: color="blue";  // Normal Priority;
		 				        break;
		 				case 2: color="red";  // Urgent Priority;
					        	break;
		 				case 3: color="brown";  // Confidential Priority;
					        	break;
					    default: color="blue";
					    		break;
	 				}
	 			}
	 			//String chkSampleVal=voSampleCollection.getPatCRNo()+"#"+voSampleCollection.getRequisitionNo()+"#"+voSampleCollection.getSampleCode()+"#"+voSampleCollection.getRequisitionDNo();
	 		%>
				 		
				 			<tr>
					 			<td  class="tdfonthead" >
					 				<div align="left">
					 				<font color="<%=color%>">	<%=voSampleCollection.getLabName() %> </font>
					 				</div>
					 			</td>
					 			<%-- <td class="tdfont">
					 			<div align="left">
					 					<font color="<%=color%>">	 <%=voSampleCollection.getRequisitionNo() %> </font>
					 				</div>
					 			</td> --%>
					 			<td class="tdfonthead" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getRequisitionDate() %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getTestName() %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getPriorityAll()==null?"":voSampleCollection.getPriorityAll() %> </font>
					 				</div>
					 			</td>
				 			 </tr>
		<%} }%>
								</table>
				</his:ContentTag>
		</logic:present>
 
 	</logic:notEmpty>
 </his:statusTransactionInProcess>
 
 
			<html:hidden name="SampleCollectionCumAcceptanceFB" property="hmode" />
			<html:hidden name="SampleCollectionCumAcceptanceFB" property="selectedCheckbox" />
			 <html:hidden name="SampleCollectionCumAcceptanceFB" property="showStatus" />
			<html:hidden name="SampleCollectionCumAcceptanceFB" property="sampleAreaName" />
			<html:hidden name="SampleCollectionCumAcceptanceFB" property="sampleAreaCode" />
			<html:hidden name="SampleCollectionCumAcceptanceFB" property="isSampleAreaSelected" />
			 <html:hidden name="SampleCollectionCumAcceptanceFB" property="currentPage" />
              <html:hidden name="SampleCollectionCumAcceptanceFB" property="patCRNo" />
                            <html:hidden name="SampleCollectionCumAcceptanceFB" property="sysDate" />
                            
                            
                            
                            <html:hidden name="SampleCollectionCumAcceptanceFB" property="labCode" />
                            <html:hidden name="SampleCollectionCumAcceptanceFB" property="testCode" />
                            <html:hidden name="SampleCollectionCumAcceptanceFB" property="patType" />
               <html:hidden name="SampleCollectionCumAcceptanceFB" property="tempSampleNo"/>
                
				  <his:ButtonToolBarTag>
				
				    
				  
				     <logic:notEqual name="SampleCollectionCumAcceptanceFB" property="isSampleAreaSelected" value="1">
				      <div id="goButton"></div>
				    	
				    </logic:notEqual>
				    <his:statusTransactionInProcess>
				     	<img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   onclick ="displaySamplePatDetails();"  tabindex="1">
				   	 	<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"  style="cursor:pointer;display:none" onkeypress="if(event.keyCode==13&&validateSampleCollection()) submitForm('SAVE');"  tabindex="1" onclick ="if(validateSampleCollection())submitForm('SAVE');" >
				   	 	
				   	 	<logic:equal name="SampleCollectionCumAcceptanceFB" property="showStatus" value="1">
				   	 	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="saveDiv"  tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				   	 	</logic:equal>
				 
				    </his:statusTransactionInProcess>
				   	  <logic:notEqual name="SampleCollectionCumAcceptanceFB" property="showStatus" value="1">
				   	  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;display:none" onkeypress="if(event.keyCode==13) showPatList()" onclick="showPatList()" tabindex="1">
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">
				    </logic:notEqual>
				    
				    
				</his:ButtonToolBarTag>
				
				<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="show"/> </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="blue"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	<bean:message key="normalReq"/>
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					  <bean:message key="red"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  <bean:message key="urgentReq"/> 
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="brown" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					   <bean:message key="brown"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					<bean:message key="Confidential"/>
					</div>
					</font>
				</td>				
			</tr>
			
		</table>
	</his:ContentTag>
	</div>
       

<his:status/>
  
</html:form>
</body>