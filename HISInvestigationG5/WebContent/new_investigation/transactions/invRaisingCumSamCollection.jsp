<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page
	import="new_investigation.transactions.controller.fb.invRaisingCumSamCollectionFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%-- <%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
 --%><%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>


<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<his:javascript src="/new_investigation/js/appointment.js" />
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<%-- <his:css src="/hisglobal/css/easyui.css" /> --%>
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/jquery/bookMark.css" />
<his:css src="/hisglobal/css/jquery/search.css" />
<his:css src="/hisglobal/css/InvBookMark.css" />
<his:css src="/hisglobal/css/invPopup.css" />
<his:css src="/hisglobal/css/invRaisingCumSampleCollection.css" />
<his:css src="/hisglobal/css/newHisRadioStyle.css" />
<his:css src="/hisglobal/css/tutorstyle.css" />	
<his:css src="/hisglobal/css/flexcrollstyles.css" />
<his:css src="/hisglobal/css/raisinginvPopup.css" />
<!--styleTextBox.css
 -->


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:javascript src="/hisglobal/js/invRaisingCumSampleCollection.js" />
<his:javascript src="/hisglobal/js/flexcrolle.js" />



<!-- <script type="text/javascript" src="/HIS/appointment/js/jquery/jquery-2.1.1.min.js"></script>
 -->
<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
<script src="media/misc/datepicker1.js" type="text/javascript"></script>

<script>
$(function() {
$( "#datepicker" ).datepicker({
dateFormat: 'dd-M-yy',
showOn: "button",
buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
buttonImageOnly: true,
buttonText: "Select  "
}).datepicker("setDate", new Date());
});
</script>


<script type="text/javascript">







function getUOMList()
{
	var uomCombo = "<div align='left'><select name = 'uomCode'  ><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstUOM= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_UOM_COMBO);

	if(lstUOM!=null)
	{
	for(int i=0;i<lstUOM.size();i++)
	{
		%>
		uomCombo+="<option value = '<%=lstUOM.get(i).getValue()%>'><%=lstUOM.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	uomCombo+="</select></div>";
      return uomCombo;
}


function getContainerList()
{
	var containerCombo = "<div align='left'><select name = 'containerCode'  ><option value='-1'>Select Value</option>";
	<%java.util.List<Entry>  lstContainer= (java.util.List<Entry>)session.getAttribute(InvestigationConfig.LIST_CONTAINER_COMBO);

	if(lstContainer!=null)
	{
	for(int i=0;i<lstContainer.size();i++)
	{
		%>
		containerCombo+="<option value = '<%=lstContainer.get(i).getValue()%>'><%=lstContainer.get(i).getLabel()%></option>"
		<%
	}
	}
	%>
	containerCombo+="</select></div>";
      return containerCombo;
}



function deleteRow(Str)
	{	
	
//	alert("inside delete");
	  var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
      var tableObj=document.getElementById('tableSelectedLabTestIdHide');
  //   alert(tableObj);
      var temp=Str;
      //sendData(temp.rowIndex);  // Need To Check the Logic
      
       
      tableObj.deleteRow(temp.rowIndex);
      document.forms[0].numberOfRow.value=(numRows-1);
      
     // makeMandatory();
      
      return true;
	}

function deleteRow(index,tmpLabCode,tmpTestCode)
{	
  
	//alert("index"+index+"labCode"+tmpLabCode+"testcode"+tmpTestCode);
	var tableObj=document.getElementById('tableSelectedLabTestIdHide');
   //alert("tableObj"+tableObj);
   //alert("parseInt"+(parseInt(index)+1));
	//tableObj.deleteRow((parseInt(index)));
	//alert("Tabkle no of rows"+tableObj.rows.length+"---indx-"+index);
	//tableObj.deleteRow(((index)));
  document.getElementsByName('numberOfRow')[0].value=tableObj.rows.length-1;
  
  //submitting page with hmode as "DELETEROW"
  	document.getElementsByName('hmode')[0].value="DELETEROW";
 	document.getElementsByName('tmpLabCode')[0].value=tmpLabCode;
  	document.getElementsByName('tmpTestCode')[0].value=tmpTestCode;
    document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

  	document.forms[0].submit();
  
  //Calling AJAX
  //var tmpLabTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
  
 //var labTestCodeArray=deleteLabTestUsingAjax(tmpLabCode,tmpTestCode,tmpLabTestCodeArray);
  
 //document.getElementsByName('labTestCodeArray')[0].value=labTestCodeArray;
  
  
  
  
  
  
  
  //Code Commented As AJAX function is used for implementing below logic
  /*
  
  //Sample Code Setting
  var selectedSampleCode=document.getElementsByName('sampleInfo');
  var reqSampleCodeArray=[];
  
  for(var i=0,k=0;i<selectedSampleCode.length;i++)
	  {
	  	if(i!=index)
	  		reqSampleCodeArray[k++]=selectedSampleCode[i].value;
	  }
  
  document.getElementsByName('sampleInfo').length=0;
  
  document.getElementsByName('sampleInfo').length=reqSampleCodeArray.length;	  
  for(var k=0;k<reqSampleCodeArray.length;k++)
	  {
	  	document.getElementsByName('sampleInfo')[i].value=reqSampleCodeArray[i];
	  }
  
  
 var tmpLabTestCodeArray="";
 
 var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
 
 //Logic to update labTest*BOCodeArray value
 if(labTestCodeArray!=null&&labTestCodeArray!="")
	 {
		 var selectedLabTests=labTestCodeArray.split("@");
		 for(var i=0;i<selectedLabTests.length;i++)
			 {
			 	//Logic to Split selectedLabTest for getting lab Values
			 	var labTestArray=selectedLabTests.split("#");
			 	 var labCode=labTestArray[0];
			 	 var testCode=labTestArray[2];
			 	 if(tmpLabCode==labCode)
			 		 {
			 		 	if(tmpTestCode==testCode)
			 		 		{
			 		 		}
			 		 	else
			 		 		{
			 		 		 if(tmpLabTestCodeArray=="")
						 			tmpLabTestCodeArray=selectedLabTests[0];
						 	 else
						 			tmpLabTestCodeArray=tmpLabTestCodeArray+"@"+selectedLabTests[0];
			 		 		}
			 		 }
			 	 else
			 		 {
				 		 if(tmpLabTestCodeArray=="")
				 			tmpLabTestCodeArray=selectedLabTests[0];
				 		 else
				 			tmpLabTestCodeArray=tmpLabTestCodeArray+"@"+selectedLabTests[0];
			 		 }
			 	
			 }
		 document.getElementsByName('labTestCodeArray')[0].value=tmpLabTestCodeArray;
	 }
 
 */
 
  return true;
}



function validateRequisition()
{
	var labCodeSaved=document.getElementsByName("labCode");
	if(typeof labCodeSaved=='undefined' || labCodeSaved==null)
		{
			alert("Please Select at least one Lab Test for Raising Requisition");
			return false;
		}
}

function submitFormForBookMark(bookMarkCode,hmode)
{
	//alert(bookMarkCode);
	
	document.getElementsByName('aptTestCode')[0].value="";
	document.getElementsByName('bookMarkCode')[0].value=bookMarkCode;
	document.getElementsByName('hmode')[0].value=hmode;
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
}

function submitFormForTestGroup(hmode)
{
	document.getElementsByName('hmode')[0].value=hmode;
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
}

function submitFormToSave(hmode)
{
	document.getElementsByName('hmode')[0].value=hmode;

   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 
   
  
	document.forms[0].submit();
	
	
	/* document.forms[0].hmode.value = "SAVEIPD";
         		   	    document.forms[0].action="/HISInvestigationG5/ipd/transactions/NursingDeskTransCNT.cnt";
         		   	   	
 document.forms[0].submit();*/
	
	
}

function showBookMarkDiv(obj)
{
	//var bookMarkedDivClicked=document.getElementsByName('radioBookMark')[0].checked;
	//var bookMarkedDivClicked=document.getElementsByName('radioBookMark')[1].checked;
	
	if(obj.value=="B")
		{
		document.getElementById('bookMarkDiv').style.display="";
		document.getElementById('searchLabTestDiv').style.display="none";
		}
	else
		{
		document.getElementById('bookMarkDiv').style.display="none";
		document.getElementById('searchLabTestDiv').style.display="";
		}
	
	document.getElementsByName('isBookMark')[0].value=obj.value;
	document.getElementsByName('hmode')[0].value="CLEARLABTESTLIST";
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
	
	
}
function validateCRNoCHE(hospitalCode)
{
	
	
	 var crno =document.getElementsByName("patCrNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     
     if(hosCodeLen==3)
		{ 
        	  if(textLength==13)
	           {
        		  
        		  
        		   if(document.getElementsByName("sampleAreaCode")[0].value=="-1")
        	    	  {
        	    	  
        	    	  alert("Select Sample Collection Area");
        	    	  document.getElementsByName("sampleAreaCode")[0].focus();
        	    	  return false;
        	    	   
        	    	  
        	    	  }
        		   
        		   
         	document.getElementsByName('hmode')[0].value='GETPATDTL';
            document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

         	document.forms[0].submit();
	            }
        	   
             

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("patCrNo")[0])
	            {
	            document.getElementsByName("patCrNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
			   
			    	
			    	if(textLength==15)
						 {
			    		
			    		   if(document.getElementsByName("sampleAreaCode")[0].value=="-1")
			    	    	  {
			    	    	  
			    	    	  alert("Select Sample Collection Area");
			    	    	  document.getElementsByName("sampleAreaCode")[0].focus();
			    	    	  return false;
			    	    	   
			    	    	  
			    	    	  }
						   
						document.getElementsByName('hmode')[0].value='GETPATDTL';
						   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

						document.forms[0].submit();
					     }
					
			     else
			     {     
				   		    alert("InValid CR No");
				     		if(document.getElementsByName("patCrNo")[0]){
				            document.getElementsByName("patCrNo")[0].focus()
				                      }    
			          
			     }
          }
      
      
      
   
   return true;
}



//AJAX FUNCTIONS START

//AJax Functions
function deleteLabTestUsingAjax(labCode,TestCode,labTestCodeArray)
{
	//alert("labTestCodeArray="+labTestCodeArray);
	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL
	var finalLabTestCodeArray = deleteLabTestCodeArray(labCode,TestCode,labTestCodeArray);
	return finalLabTestCodeArray;
}

function deleteLabTestCodeArray(tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
{
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_DELETE_LABTESTCODEARRAY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&labTestCodeArray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			labTestCodeArray = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return labTestCodeArray;
}



function setSampleCodeUsingAjax(objSampleCode,labCode,TestCode,nRow)
{
	//alert(nRow);
	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	var sampleCode=objSampleCode.value;
	var sampleName=objSampleCode.options[objSampleCode.selectedIndex].innerHTML;

	//alert(sampleCode);
	labTestCodeArray=labTestCodeArray.replace(/#/g,";");
	labTestCodeArray=labTestCodeArray.replace(/&/g,"!");// As hash separator doesnot work in URL
//alert(labTestCodeArray);
	var finalLabTestCodeArray = modifyLabTestCodeArray(sampleCode,labCode,TestCode,labTestCodeArray,sampleName);
//	alert("after ajax fire "+finalLabTestCodeArray);
	document.getElementsByName('labTestCodeArray')[0].value=finalLabTestCodeArray;
	
	
	////////////////////////
	
	var chkArr=finalLabTestCodeArray.split("#");
	
	///////////////////////
	
	var uomList = document.getElementById("uomdiv"+nRow);
if(uomList!=null)
	{
	
	uomList.innerHTML="";
	
	var selectList= document.createElement("select");
	 selectList.name="uomCode";
	 
	//var newUom="<select name='uomCode' tabindex='1' >"+chkArr[18]+"</select>";
	uomList.appendChild(selectList);
	selectList.innerHTML=chkArr[18];
	}
else
	{
	
 uomList = document.getElementById("uomdivbook"+nRow);
uomList.innerHTML="";
	
	var selectList= document.createElement("select");
	 selectList.name="uomCode";
	 
	//var newUom="<select name='uomCode' tabindex='1' >"+chkArr[18]+"</select>";
	uomList.appendChild(selectList);
	selectList.innerHTML=chkArr[18];
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	var containerList = document.getElementById("containerdiv"+nRow);
if(containerList!=null)
	{
	containerList.innerHTML="";
	
	
	var selectListContainer= document.createElement("select");
	selectListContainer.name="containerCode";
	 containerList.appendChild(selectListContainer);
		selectListContainer.innerHTML=chkArr[19];
	}
	
else
	{
 containerList = document.getElementById("containerdivbook"+nRow);
containerList.innerHTML="";
	
	
	var selectListContainer= document.createElement("select");
	selectListContainer.name="containerCode";
	 containerList.appendChild(selectListContainer);
		selectListContainer.innerHTML=chkArr[19];
	
	}
	//var newContainer="<select name='containerCode' tabindex='1' >"+chkArr[19]+"</select>";
	//uomList.appendChild(newContainer);
//	alert(newContainer);
	
	var volumeValue= document.getElementById("volumediv"+nRow);
	if(volumeValue!=null)
		{
	volumeValue.innerHTML="";
	
	var ContainerVolume= document.createElement("input");
	ContainerVolume.type="text";
	ContainerVolume.name="containerVolume";
	ContainerVolume.value=chkArr[20];
	ContainerVolume.size="6";
	volumeValue.appendChild(ContainerVolume);
		}
	
	else
		{
		 volumeValue= document.getElementById("volumedivbook"+nRow);
		volumeValue.innerHTML="";
		
		var ContainerVolume= document.createElement("input");
		ContainerVolume.type="text";
		ContainerVolume.name="containerVolume";
		ContainerVolume.value=chkArr[20];
		ContainerVolume.size="6";
		volumeValue.appendChild(ContainerVolume);
		
		}
	
}

function modifyLabTestCodeArray(tmpSampleCode,tmpLabCode,tmpTestCode,tmpLabTestCodeArray,sampleName)
{
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_MODIFY_LABTESTCODEARRAY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&tmpSampleCode="+tmpSampleCode+"&labTestCodeArray="+tmpLabTestCodeArray+"&sName="+sampleName, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			labTestCodeArray = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return labTestCodeArray;
}



function setPriorityUsingAjax(objPriority,labCode,TestCode)
{
	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	var priority=objPriority.value;
	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL
	var finalLabTestCodeArray = modifyPriorityLabTestCodeArray(priority,labCode,TestCode,labTestCodeArray);
	document.getElementsByName('labTestCodeArray')[0].value=finalLabTestCodeArray;
}

function modifyPriorityLabTestCodeArray(tmpPriority,tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
{
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_MODIFY_PRIORITY";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&tmpPriority="+tmpPriority+"&labTestCodeArray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			labTestCodeArray = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return labTestCodeArray;
}



// getting test based on labCode
function setTestComboUsingAjax(labCode)
{
	var flg = false;
	var tstValArr = "";
	var _mode = "AJX_TEST_COMBO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt?hmode="+_mode+"&tmpLabCode="+labCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			tstValArr = data;
			flg = true;
		},
        error: function(error)
        {
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	document.getElementsByName('tstValArr')[0].value=tstValArr;
	//alert(document.getElementsByName('tstValArr')[0].value);
	 
	return tstValArr;
}


//getting test based on labCode
function setTestGroupUsingAjax(labCode)
{
	var flg = false;
	var tstGrpArr = "";
	var _mode = "AJX_TEST_GROUP";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt?hmode="+_mode+"&tmpLabCode="+labCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			tstGrpArr = data;
			flg = true;
		},
        error: function(error)
        {
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	document.getElementsByName('tstGrpArr')[0].value=tstGrpArr;
	
	return tstGrpArr;
}

//End AjaxFunctions
function checkAdvise()
{
	if(document.getElementsByName('advisedByName')[0].value=="" && document.getElementsByName('advisedBy')[0].value!="")
	{
		alert('Select Valid Value for Advised By!');
		document.getElementsByName('advisedBy')[0].focus();
		return false;
	}
	
	
}


//Duplicate Requisition Validation Logic

function chkRequisitionPending(labCode,testCode)
{
	var flg = false;
	var isRequisitonRaisingPresent = false;
	var _mode = "AJX_DUPLICACY_LABTEST";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&dupLabCode="+labCode+"&dupTestCode="+testCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isRequisitonRaisingPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            alert(error+"Error while populating Meal Time Information");
            isRequisitonRaisingPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isRequisitonRaisingPresent;
}




function modifyLabTestCodeArrayAptNo(appoitmentNo,tmpLabCode,tmpTestCode,tmpLabTestCodeArray)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_MODIFY_LABTESTCODEARRAY_APTNO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpLabCode="+tmpLabCode+"&tmpTestCode="+tmpTestCode+"&appoitmentNo="+appoitmentNo+"&labTestCodeArray="+tmpLabTestCodeArray, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			labTestCodeArray = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return labTestCodeArray;
}


function setAptNoUsingAjax(objaptno,labCode,TestCode)
{
	
	//alert("setAptNoUsingAjax"+objaptno+"labcode"+labCode+"testCode"+TestCode);
	var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	 
	labTestCodeArray=labTestCodeArray.replace(/#/g,";"); // As hash separator doesnot work in URL
	var finalLabTestCodeArray = modifyLabTestCodeArrayAptNo(objaptno,labCode,TestCode,labTestCodeArray);
	document.getElementsByName('labTestCodeArray')[0].value=finalLabTestCodeArray;
	
	//alert(document.getElementsByName('labTestCodeArray')[0].value);
}




function validateSave()
{
	
	//Validating Sample Combo labCodeadvisedByName
	
	
	 
	/* if(document.getElementsByName('advisedByName')[0].value=='')
		{
			alert('Select Valid Value for Advised By!');
			document.getElementsByName('advisedBy')[0].focus();
			return false;
		} */
	      //alert(document.getElementsByName('advisedByName')[0].value);
		/* if(document.getElementsByName('advisedByName')[0].value=="")
			document.getElementsByName('advisedBy')[0].value=""; */
			var labTestCodeArrayValue=document.getElementsByName('labTestCodeArray')[0].value;
			
			var ValueLabTest=labTestCodeArrayValue.split("@");
			//alert(Value.length);
			
			//alert(document.getElementsByName('labTestCodeArray')[0].value);
			//alert(ValueLabTest.length);
// 			for(i=0;i<ValueLabTest.length;i++)
// 				{
// 				var flag=false;
// 				var SplitValue=ValueLabTest[i].split("#");
// 				var labCode=SplitValue[0];
// 				var testCode=SplitValue[2];
				 
// 				//alert("labCode"+labCode+"testCode"+testCode);
// 		       var isRequisitonRaisingPresent = chkRequisitionPending(labCode,testCode);
// 		      var spliIsReqPresn=isRequisitonRaisingPresent.split(',');
				
// 		      spliIsReqPresn[0]=spliIsReqPresn[0]=="true"?true:false;
				
// 		    //  alert(isRequisitonRaisingPresent);
		      
// 				if(spliIsReqPresn[0])
// 				{
					
// 					if(spliIsReqPresn[1]=='2')
// 					alert("Sample Collection Is Not Done For This "+SplitValue[3]+"("+SplitValue[1]+")");
// 					if(spliIsReqPresn[1]=='3')
// 						alert("Packing List Generation Is Not Done For This "+SplitValue[3]+"("+SplitValue[1]+")");
// 					if(spliIsReqPresn[1]=='4')
// 						alert("Sample Acceptance Is Not Done For This "+SplitValue[3]+"("+SplitValue[1]+")");
// 					if(spliIsReqPresn[1]=='5')
// 						alert("Patient Acceptance Is Not Done For This "+SplitValue[3]+"("+SplitValue[1]+")");
// 					if(spliIsReqPresn[1]=='6')
// 						alert("Result Entry Is Not Done For This "+SplitValue[3]+"("+SplitValue[1]+")");
					 
// 					obj.value="";
// 					obj.focus();
// 					return false;
// 				}
				 
// 				}
		
		
	      document.getElementsByName('advisedByDoctorName')[0].value=document.getElementsByName('advisedByName')[0].value; 
	      
	      
if(document.getElementById('checkOnSave')==null)
{ 
		alert("Please Select Atleast One Lab For Raising!");
			return false; 
} 
	 
	
	var sampleInfo= document.getElementsByName('sampleInfo');
	var uomCode=document.getElementsByName('uomCode');
	
	var containerCode=document.getElementsByName('containerCode');
	var containerVolume=document.getElementsByName('containerVolume');
	
	
	for(var i=0;i<sampleInfo.length;i++)
		  {
		  	if(sampleInfo[i].value=="-1")
		  		{
		  			alert("Please Select Sample Code!");
		  			sampleInfo[i].focus();
		  			return false;
		  		}
		 
		  }
		  
	for(var i=0;i<uomCode.length;i++)
		  	if(uomCode[i].value=="-1")
	  		{
	  			alert("Please Select UOM!");
	  			uomCode[i].focus();
	  			return false;
	  		}
	
	for(var i=0;i<containerCode.length;i++)
			if(containerCode[i].value=="-1")
	  		{
	  			alert("Please Select Container!");
	  			containerCode[i].focus();
	  			return false;
	  		}
		  
		  
		  
		  
	
var labTestCodeArray=document.getElementsByName('labTestCodeArray')[0].value;
	
	var Value=labTestCodeArray.split("@");
	//alert(Value.length);
	
	//alert(document.getElementsByName('labTestCodeArray')[0].value);
	
	for(i=0;i<Value.length;i++)
		{
		var flag=false;
		var SplitValue=Value[i].split("#");
		var labCode=SplitValue[0];
		var testCode=SplitValue[2];
		var aptFlag=SplitValue[6];
		var divId="aptTagRow_"+labCode+"_"+testCode;
		//alert(divId);
		if(aptFlag==1&&typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined')
			{
		
		var aptSatus=createAppointment(divId);
		//alert(document.getElementsByName('appointmentRefNo')[0].value);
		//alert("apt "+aptSatus);appointmentRefNo
	 	if(!aptSatus)
			 {
			 
			return false;
			}
			}
		}
	
	 
	 var today = new Date();
	 var date=convertDateToStr(today,"dd-Mon-yyyy");
		var Time=convertDateToStr(today,"hh:mm"); 
		    //alert(date);
		    //alert(Time);

	    
	//alert(document.getElementsByName("freeSlotLabel")[0].value);
	//var freeSlotLabel=document.getElementsByName("freeSlotLabel")[0].value;
	//var arrAppointmentDate=freeSlotLabel.split(" ");
		if (typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined')
			{
			//alert("inside");	
			///alert(document.getElementsByName('slotST_'+divId+'')[0].value);
	           document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+divId+'')[0].value;

				document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+divId+'')[0].value;
				
				//createAppointment();
		//if (typeof $('[name="slotST"]').val() === "undefined")
		     
			}
			else
			{
				//alert("offline Apt Details"+document.getElementsByName('offlineAppoitmentDtl')[0].value);
				 if(document.getElementsByName('offlineAppoitmentDtl')[0].value=='')
					 {
					 
					 
				    document.getElementsByName('appointmentDate')[0].value=date;
			        document.getElementsByName('appointmentTime')[0].value=Time;
					 }
				 else
					 {
					 var dateAndTime=document.getElementsByName('offlineAppoitmentDtl')[0].value;
					 var SplitWithSpace=dateAndTime.split(' ');
					 
					 var offlineAptDate=SplitWithSpace[0];
					 var offLineAptTime=SplitWithSpace[1];
				//	 alert(offlineAptDate);
					// alert(offLineAptTime);
					 
					 document.getElementsByName('appointmentDate')[0].value=offlineAptDate;
				        document.getElementsByName('appointmentTime')[0].value=offLineAptTime;
				        document.getElementsByName('appointmentRefNo')[0].value=document.getElementsByName('offlineAptDtl')[0].value
					 
					 
					 }
			}
		 
	
	return true;
}

function setTestORTestGroup(obj)
{
	
	if(obj.value=="1")  // Test Group
	{
		document.getElementById('divSearchTestGroup').style.display="";
		document.getElementById('divSearchTest').style.display="none";
		document.getElementsByName('tstOrTestGroupValue')[0].value=obj.value;
		// alert(document.getElementsByName('tstOrTestGroupValue')[0].value);
		document.getElementsByName('hmode')[0].value="SEARCHLABWISETESTGROUPONCLICK";
		   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

		 document.forms[0].submit();
		 
		 
	}
	else  // Test
	{
		document.getElementById('divSearchTest').style.display="";
		document.getElementById('divSearchTestGroup').style.display="none";
		 document.getElementsByName('tstOrTestGroupValue')[0].value=obj.value;
		
		 document.getElementsByName('hmode')[0].value="SEARCHLABWISETEST";
		   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

		 document.forms[0].submit();
		 
	}
	
	
}

function clearVal()
{

	document.getElementsByName('searchLabName')[0].value="";
}

function onLoad()
{
	document.getElementsByName('tstGrpArr')[0].value="";
	document.getElementsByName('tstValArr')[0].value="";
}


function showAddorGOButton(obj)
{
	 
	 
	 var labCode=document.getElementsByName('searchLabName')[0].value;
	var testCode=document.getElementsByName('searchTestName')[0].value;
	var testGroupCode=document.getElementsByName('searchTestGroupName')[0].value;
	var isTestGroup=document.getElementsByName('isTestGroup')[1].checked;
	//alert(typeof isTestGroup+" "+isTestGroup);
		if(isTestGroup)
		{
			if(labCode!=null && labCode!='' && testGroupCode!=null && testGroupCode!='')
			{
				document.getElementById('addGroupButton').style.display="";
				document.getElementById('goButton').style.display="none";
			}
			else
			{
				document.getElementById('addGroupButton').style.display="none";
				document.getElementById('goButton').style.display="";
			}
			document.getElementById('addTestButton').style.display="none";
		}
		else
		{
			if(labCode!=null && labCode!='' && testCode!=null && testCode!='')
			{
				document.getElementById('addTestButton').style.display="";
				document.getElementById('goButton').style.display="none";
			}
			else
			{
	        document.getElementById('addTestButton').style.display="none";
				document.getElementById('goButton').style.display="";
			}
			document.getElementById('addGroupButton').style.display="none";
		}
}

function showAddorGOButtonForAuto(obj)
{
	var value=obj.value; 
	if(value=='')
		{
	//alert("inside here");
	// alert(value);
	   availableTests= setTestComboUsingAjax(value); 
       var list1=document.getElementsByName('tstValArr')[0].value;
      var obj = JSON.parse(list1);
       availableTests=obj;
      // alert("the obj value"+availableTests);
     
        //var availableGroups= setTestGroupUsingAjax(ui.item.value);
		
         var availableGroups= setTestGroupUsingAjax(value);
               var list2=document.getElementsByName('tstGrpArr')[0].value;
              var obj2= JSON.parse(list2);
              availableTestGroups=obj2;
        
		}
	 
	 var labCode=document.getElementsByName('searchLabName')[0].value;
	var testCode=document.getElementsByName('searchTestName')[0].value;
	var testGroupCode=document.getElementsByName('searchTestGroupName')[0].value;
	var isTestGroup=document.getElementsByName('isTestGroup')[1].checked;
	//alert(typeof isTestGroup+" "+isTestGroup);
		if(isTestGroup)
		{
			if(labCode!=null && labCode!='' && testGroupCode!=null && testGroupCode!='')
			{
				document.getElementById('addGroupButton').style.display="";
				document.getElementById('goButton').style.display="none";
			}
			else
			{
				document.getElementById('addGroupButton').style.display="none";
				document.getElementById('goButton').style.display="";
			}
			document.getElementById('addTestButton').style.display="none";
		}
		else
		{
			if(labCode!=null && labCode!='' && testCode!=null && testCode!='')
			{
				document.getElementById('addTestButton').style.display="";
				document.getElementById('goButton').style.display="none";
			}
			else
			{
	        document.getElementById('addTestButton').style.display="none";
				document.getElementById('goButton').style.display="";
			}
			document.getElementById('addGroupButton').style.display="none";
		}
}








function clearTestAndGroup()
{
	
	 
	//Clearing fields to make sure AJAX is accurate in firing
    document.getElementsByName('tstValArr')[0].value="";
    document.getElementsByName('tstGrpArr')[0].value="";
    document.getElementsByName('searchTestName')[0].value="";
		document.getElementsByName('searchTestGroupName')[0].value="";
		document.getElementsByName('searchTest')[0].value="";
		document.getElementsByName('searchTestGroup')[0].value="";
}

//JQuery Start
var availableTests;
var availableTestGroups;
         $(function() {
        	 var availableLabs =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_LABNAMES)%>;
        	 availableTests =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TESTNAMES)%>;
        	 availableTestGroups =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TESTGROUPNAMES)%>; 
        	// alert("inside here");
           // alert(availableLabs);
        	$( "#automplete-1" ).autocomplete({
               source: availableLabs,
               select: function(event, ui) { 
                   $('#hiddenid1').val(ui.item.value); 
                   event.preventDefault(); 
                   $("#automplete-1").val(ui.item.label); 
                   
                   //Clearing fields to make sure AJAX is accurate in firing
                    document.getElementsByName('tstValArr')[0].value="";
                    document.getElementsByName('tstGrpArr')[0].value="";
                    document.getElementsByName('searchTestName')[0].value="";
               		document.getElementsByName('searchTestGroupName')[0].value="";
               		document.getElementsByName('searchTest')[0].value="";
               		document.getElementsByName('searchTestGroup')[0].value="";
               availableTests= setTestComboUsingAjax(ui.item.value); 
               
              var list1=document.getElementsByName('tstValArr')[0].value;
                
             var obj = JSON.parse(list1);
               
	          availableTests=obj;
	        //  alert("the availableTests value"+availableTests);
	        
	          availableTestGroups= setTestGroupUsingAjax(ui.item.value);
               var list2=document.getElementsByName('tstGrpArr')[0].value;
               
               var obj2= JSON.parse(list2);
                 
               availableTestGroups=obj2;
             //  alert("the availableTestGroups value"+availableTestGroups);
               
               },

           focus: function(event, ui) { 
       event.preventDefault(); 
                  $("#automplete-1").val(ui.item.label);}
            });
            
         });
         
         $(function() {
        	 
            var list1=document.getElementsByName('tstValArr')[0].value;
           
        	 setInterval(function() {
         	 
            $( "#automplete-2" ).autocomplete({
                source: availableTests,
                select: function(event, ui) { 
                    $('#hiddenid2').val(ui.item.value); 
                    event.preventDefault(); 
                    $("#automplete-2").val(ui.item.label); 
                    
                    availableTests= setTestComboUsingAjax(ui.item.value); 
                },
            
            focus: function(event, ui) { 
                   event.preventDefault(); 
                   $("#automplete-2").val(ui.item.label);}
             });
         }, 1000);
         });
         
         $(function() {
	        //// var testGrpCombo=document.getElementsByName('tstGrpArr')[0].value;
	         ///	var availableTestGroups =null;
	         //	if(testGrpCombo==null||testGrpCombo=="")
	         	
	         //	else
	         	//	availableTestGroups =testGrpCombo;
	         
	           var list2=document.getElementsByName('tstGrpArr')[0].value;
            setInterval(function() {
            $( "#automplete-3" ).autocomplete({
                source: availableTestGroups,
                select: function(event, ui) { 
                    $('#hiddenid3').val(ui.item.value); 
                    event.preventDefault(); 
                    $("#automplete-3").val(ui.item.label); 
                },

            focus: function(event, ui) { 
                   event.preventDefault(); 
                   $("#automplete-3").val(ui.item.label);}
             });
            }, 1000);
         });

         
         
         $(function() {
 	        //// var testGrpCombo=document.getElementsByName('tstGrpArr')[0].value;
 	         ///	var availableTestGroups =null;
 	         //	if(testGrpCombo==null||testGrpCombo=="")
 	         	
 	         //	else
 	         	//	availableTestGroups =testGrpCombo;
 	         
 	           var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_ADVISEDBY_NAMES)%>;
            
 	            
 	           setInterval(function() {
             $( "#automplete-4" ).autocomplete({
                 source: advisedByList,
                 select: function(event, ui) { 
                     $('#hiddenid4').val(ui.item.value); 
                     event.preventDefault(); 
                     $("#automplete-4").val(ui.item.label); 
                 },

             focus: function(event, ui) { 
                    event.preventDefault(); 
                    $("#automplete-4").val(ui.item.label);}
              });
             }, 1000);
          });
         
         
         $(function() {
    	        //// var testGrpCombo=document.getElementsByName('tstGrpArr')[0].value;
    	         ///	var availableTestGroups =null;
    	         //	if(testGrpCombo==null||testGrpCombo=="")
    	         	
    	         //	else
    	         	//	availableTestGroups =testGrpCombo;
    	         
    	           var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)%>;
               
    	            
    	           setInterval(function() {
                $( "#automplete-5" ).autocomplete({
                    source: advisedByList,
                    select: function(event, ui) { 
                        $('#hiddenid5').val(ui.item.value); 
                        event.preventDefault(); 
                        $("#automplete-5").val(ui.item.label); 
                        availableTests= setTestComboUsingAjax(ui.item.value); 
                    },

                focus: function(event, ui) { 
                       event.preventDefault(); 
                       $("#automplete-5").val(ui.item.label);}
                 });
                }, 1000);
             });
         
         
         $(function() {
   	         
	           var userGroupLst=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)%>;
           
	       
	           setInterval(function() {
            $( "#automplete-6" ).autocomplete({
                source: userGroupLst,
                select: function(event, ui) { 
                    $('#hiddenid6').val(ui.item.value); 
                    event.preventDefault(); 
                    $("#automplete-6").val(ui.item.label); 
                    setUserGroupWiseDetail();
                },

            focus: function(event, ui) { 
                   event.preventDefault(); 
                   $("#automplete-6").val(ui.item.label);}
             });
            }, 1000);
         });
       
       
       
//JQuery End

$("#datepicker").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() - (1000 * 60 * 60 * 24)));

function showAptBaseByDesk()
{

	//alert("appoitmentlist");

	if(document.getElementsByName("sampleAreaCode")[0].value=="-1")
		{
		  alert("Select Sample Collection Area");
    	  document.getElementsByName("sampleAreaCode")[0].focus();
    	  document.getElementsByName("radioEpisode")[0].checked=false;
    	  return false;
    	}
	
	
	document.getElementsByName('hmode')[0].value="APTBYDESK";
 
	document.forms[0].submit();
}
  

function callThisOnload()
{
	if(document.getElementsByName("saveConfirmFlag")[0].value!="")
		{
 	var url="/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode=PRINT";

	document.getElementsByName("saveConfirmFlag")[0].value="";
	document.getElementsByName("barCodeGenSize")[0].value="";
	
	openPopuper(url,180,500);
		}
}

function openPopuper(url,height, width)
{
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
	if(!child.opener)
   		child.opener = self;
  	return child;
}



function getAppointment(){
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
	
	document.getElementsByName('hmode')[0].value='GETAPPOINTMENT';
	document.forms[0].submit();
	
}


</script>

<script>
$(function() {
$( "#datepicker" ).datepicker({
dateFormat: 'dd-M-yy',
showOn: "button",
buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
buttonImageOnly: true,
buttonText: "Select  "
}).datepicker("setDate", new Date());
});
</script>

<script>
 
</script>
<script>
$(function() {
var tabs = $( "#tabs" ).tabs();
tabs.find( ".ui-tabs-nav" ).sortable({
axis: "x",
stop: function() {
tabs.tabs( "refresh" );
}
});
});
</script>

<script>


setInterval(function() {
	
	var choices = [];
	var els = document.getElementsByName('isTestGroup');
	for (var i=0;i<els.length;i++){
	  if ( els[i].checked ) {
	    //choices.push(els[i].value);
	   // alert(els[i].value);
	    if(els[i].value=="1")  // Test Group isTestGroup
		{
			document.getElementById('divSearchTestGroup').style.display="";
			document.getElementById('divSearchTest').style.display="none";
		}
		else  // Test
		{
			document.getElementById('divSearchTest').style.display="";
			document.getElementById('divSearchTestGroup').style.display="none";
		}

	     
	  }
	}
	//alert(document.getElementsByName('isTestGroup')[0].checked.value);
	
    }, 500);
 






function showPatDetails()
{
document.getElementById("showhide").style.display="";
document.getElementById("hide").style.display="";
document.getElementById("show").style.display="none";	
	
}

function hidePatDetails()
{
document.getElementById("showhide").style.display="none";
document.getElementById("hide").style.display="none";
document.getElementById("show").style.display="";	
}
</script>

<script>
function showPatDetailsPatient()
{
document.getElementById("showhidePatient").style.display="";
document.getElementById("hidePatient").style.display="";
document.getElementById("showPatient").style.display="none";	
	
}

function hidePatDetailsPatient()
{
document.getElementById("showhidePatient").style.display="none";
document.getElementById("hidePatient").style.display="none";
document.getElementById("showPatient").style.display="";	
}
</script>
<script>
function showPatDetailsOnPagination()
{
document.getElementById("showhideOnPagination").style.display="";
document.getElementById("hideOnPagination").style.display="";
document.getElementById("showOnPagination").style.display="none";	
	
}

function hidePatDetailsOnPagination()
{
document.getElementById("showhideOnPagination").style.display="none";
document.getElementById("hideOnPagination").style.display="none";
document.getElementById("showOnPagination").style.display="";	
}

function searchLabWiseTest()
{
    // alert("inside");   SearchTestGroupName
	
   // alert(document.getElementsByName('searchTestGroupName')[0].value);
   // if(document.getElementsByName('searchTestGroupName')[0].value=='')
    //	{
    	//alert("SEARCHLABWISETEST");
    document.getElementsByName('hmode')[0].value="SEARCHLABWISETEST";
    //	}
   // else
    //	{
    	//alert("SEARCHGROUP");
    	//document.getElementsByName('hmode')[0].value="SEARCHGROUP";
    //	}
    // document.getElementById("showhideOnPagination").style.display="";
    // document.getElementById("hideOnPagination").style.display="";
   //  document.getElementById("showOnPagination").style.display="none";	
 
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
} 


function searchGroup()
{
	//alert("inside");
	document.getElementsByName('hmode')[0].value="SEARCHGROUP";
 
	//document.getElementById("showhideOnPagination").style.display="";
//document.getElementById("hideOnPagination").style.display="";
//document.getElementById("showOnPagination").style.display="none";	
 
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
}

function searchTestGroup()
{
	
	document.getElementsByName('bookMarkCode')[0].value="";
	document.getElementsByName('aptTestCode')[0].value="";
	
	document.getElementsByName('hmode')[0].value="SEARCHTEST";
	//alert("inside");  bookMarkCode
 
	//sssdocument.getElementById("showhideOnPagination").style.display="";
	//document.getElementById("hideOnPagination").style.display="";
	//document.getElementById("showOnPagination").style.display="none";	
    document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	
	document.forms[0].submit();
}


function pagesubmit()
{
	document.getElementById("showhideOnPagination").style.display="";
	document.getElementById("hideOnPagination").style.display="";
	document.getElementById("showOnPagination").style.display="none";	
	
	}

  function setDateInApoitment(patCrNo,paraId,obj,divAptTagRow)
  {
	//  alert("Working Fine"+obj.value);
	 // document.ge
	  //date=obj.value;
	   var date=document.getElementsByName('dateTag')[0].value;
	  // alert(date);
	  getAptSlotDetails(patCrNo,paraId,date,divAptTagRow,2);
  }
  
  setInterval(function() {
		///alert(document.getElementsByName("chkSamplePatient")[0].value); 
	  //alert("after Delete");
	 	fleXenv.fleXcrollMain("mycustomscroll2");
	 	fleXenv.initByClass("flexcroll");
	  
	 }, 500);     


function showCurrDetail()
{
	  
	  
	   
	//  alert("INSIDE ");
	  
	/*   
	  float: left;
background: #f1f6f6 ;
background-repeat: no-repeat;
background-position: top right;
background-color: #e0ebeb;
margin: 0 3px 0 0;
padding: 0;
	border: 2px solid #FFD700;

padding: 0px 5px; 
 
border-radius: 25px 25px 2px 2px; */ 

document.getElementById("lengendStatus").style.display="none";
	  document.getElementById('saveDiv').style.display="";
document.getElementById("currentReqDtl").style.color = "#A52A2A";
document.getElementById("currentReqDtl").style.fontFamily = "Times New Roman";
document.getElementById("currentReqDtl").style.fontSize = "14px";
document.getElementById("currentReqDtl").style.backgroundColor="#87CEFA";
document.getElementById("currentReqDtl").style.borderRadius ="20px 20px 1px 1px";
document.getElementById("currentReqDtl").style.width ="16em";
	 
	  
document.getElementById("prvReqDtl").style.color = ""; 
document.getElementById("prvReqDtl").style.fontFamily = "";
document.getElementById("prvReqDtl").style.fontSize = "";
document.getElementById("prvReqDtl").style.backgroundColor="";
document.getElementById("prvReqDtl").style.borderRadius ="";
document.getElementById("prvReqDtl").style.width ="";
	 
	
}

function showPrvDetail()
{
	   //#E0EBEB
	// alert("dd");
/*  float: left;
cololr:#fffff;
background-repeat: no-repeat;
background-position: top right;
background-color: #4682B4;
margin: 0 3px 0 0;
padding: 0;
	   
border-radius: 20px 20px 1px 1px; */

//alert("inside here");
document.getElementById("lengendStatus").style.display="";


	  
	  
	  
	      document.getElementById("prvReqDtl").style.color = "#A52A2A";
	  document.getElementById("prvReqDtl").style.fontFamily = "Times New Roman";
	  document.getElementById("prvReqDtl").style.fontSize = "14px";
	  document.getElementById("prvReqDtl").style.backgroundColor="#87CEFA";
	  document.getElementById("prvReqDtl").style.borderRadius ="20px 20px 1px 1px";
	  document.getElementById("prvReqDtl").style.width ="16.5em";
	  
	  
	   
	   

	  
	  
	  document.getElementById("currentReqDtl").style.color = "";
	  document.getElementById("currentReqDtl").style.fontFamily = "";
	  document.getElementById("currentReqDtl").style.fontSize = "";
	  document.getElementById("currentReqDtl").style.backgroundColor="";
	  document.getElementById("currentReqDtl").style.borderRadius ="";
	  document.getElementById("currentReqDtl").style.width ="";
	  
	  
	    
	  
	  
	 /*  document.getElementById("currentReqDtl").className =""; 
	    document.getElementById("prvReqDtl").className ="cssClass"; 
		    */
	  var CrNo=document.getElementsByName("patCrNo")[0].value;
	  
	  var tableObj=document.getElementById('setPrvTestDtl');
	  	var numRows=tableObj.rows.length;
	 if(numRows>1)
		 { 
	  	for(i=1;i<=numRows;i++)
		  { 
	  //	alert("total length"+numRows);
		  //alert("value of "+i);
	  if(i==numRows)
		  {
		//  alert(i);
		  //alert("call Ajax"); 
		  	setPrevTestDtlUsingAjax(CrNo);
		  return false;
		  }
	  else
		  { 
		  //alert(i);
		  document.getElementById("setPrvTestDtl").deleteRow(1); 
		  }
	  
	  
	// alert( document.getElementsByName("patCrNo")[0].value);
	 // document.getElementsByName('hmode')[0].value="SEARCHTEST";
	//document.forms[0].submit();
	    }
	  	 
	  	
		 }
	  var availableTestGroups= setPrevTestDtlUsingAjax(CrNo);
}




 


function setPrevTestDtlUsingAjax(CrNo)
{
	  
	 //alert("Inside Ajax"); 
	var flg = false;
	var tstValArr = "";
	var _mode = "AJX_PRV_TEST_DTL";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&tmpCrNo="+CrNo, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("LIST_PRVTESTDTL_AJAX"+data);
			tstValArr = data;
			flg = true;
		},
        error: function(error)
        {
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	document.getElementsByName('prvTestDtl')[0].value=tstValArr;
	
	 document.getElementById('saveDiv').style.display="none";
  
//hideSaveButton	alert(document.getElementsByName('prvTestDtl')[0].value);

var values=tstValArr.split('@');

//alert("length"+values.length);
for(i=0;i<(values.length-1);i++)
{ 
	//alert(i);
	 AddRowToTablePrv(values[i]); 
}
	 return tstValArr;
}


function AddRowToTablePrv(chkVal)
{
	// Logic to regain the Lab Test Values
	// Lab Code
	//alert("inside here"+chkArr);
	var chkArr=chkVal.split("#");
	var status=chkArr[0];
	// Lab Name
	var labName=chkArr[1];
	// Test Code
	var sampleName=chkArr[2];
	// Test Name
	var testName=chkArr[3];
	// Sample Combo String
	var sampleComboStr=chkArr[4];
	// Test Type
	var testType=chkArr[5];
	// is Appointment
	var isAppointment=chkArr[6];
	 
	// is Confidential
	var isConfidential=chkArr[7];
	// Sample Code
	var sampleCode=chkArr[8];
	// Priority
	var priority=chkArr[9];
	// Test GroupCode
	var testGroupCode=chkArr[10];
	// Test Group Type
	var testGroupType=chkArr[11];
	
	//Is Mandatory Req 
	var isMandatoryReq=chkArr[12];
	
	var reqDate=chkArr[15];
	 
	var labCode=chkArr[16];
	var testCode=chkArr[17];
	var prvReqStatus=chkArr[18];


    var pat=chkArr[30];
    var coll=chkArr[31];
    var tech= chkArr[32];       
	 
	var nRow=0;
	var tableObj=document.getElementById('setPrvTestDtl');
	var numRows=tableObj.rows.length;
	//alert("total length"+numRows);
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td5=document.createElement("TD");
	var td6=document.createElement("TD");
	var td7=document.createElement("TD");
	var td9=document.createElement("TD");
	var td8=document.createElement("TD");
	 
	
	/*  
		  2,'Requistion Raised', skyblue
		  5,'Requisition Raised',
		  3,'Sample Collected', silver
		  4,'Packing List Generated', white
		  6,'Sample/Patient Accepted', maroon
		  7,'Result Entered',   aqua
		  8,'Result Validated', purple
		  9,'Patient Rejected', fuchsia
		  10,'Test Resceduled', green
		  11,'Test Rescheduled', lime
		  12,'Sample Cancelled',  olive
		  13,'Ready For Report Printing', yellow
		  14,'Result Printed',    gold
		  15,'Test Cancelled', teal
		  16,'Test Deleted', red
		  26,'Report Generated' brown
		  
		  */
		  
		  
		  //alert(prvReqStatus);
	
		  
		  
		 var color='';
		 if(prvReqStatus=='2'||prvReqStatus=='5')
		 color='skyblue';
		 if(prvReqStatus=='3')
			 color='silver';
		 if(prvReqStatus=='4')
			 color='#CC99FF';
		 if(prvReqStatus=='6')
			 color='litemaroon';
		 if(prvReqStatus=='7')
			 color='aqua';
		 if(prvReqStatus=='8')
			 color='purple';
		 if(prvReqStatus=='9')
			 color='fuchsia';
		 if(prvReqStatus=='10'||prvReqStatus=='11')
			 color='blue';
		 if(prvReqStatus=='12')
			 color='olive';
		 if(prvReqStatus=='13')
			 color='lime';
		 if(prvReqStatus=='14')
			 color='gold';
		 if(prvReqStatus=='15')
			 color='teal';
		 if(prvReqStatus=='16')
			 color='#EB7273';
		 if(prvReqStatus=='26')
			 color='brown';
		 if(prvReqStatus=='17')
			 color='#FFA500';
		 if(prvReqStatus=='18')
			 color='#FFA599';
		 if(prvReqStatus=='55')
			 color='#9999FF';
		 

	td1.innerHTML="<div align='left'   >"+testName+" </div>";   
	td1.className='tdfonthead';
	td1.colspan="1";
	td1.style.width='15%';
	td1.style.backgroundColor=color;
		
	td2.innerHTML="<div align='left'   >"+labName+"</div>";
	td2.className='tdfonthead';
	td2.colspan="1";
	td2.style.width='15.5%';
	td2.style.backgroundColor=color;
 
		td3.innerHTML="<div align='left' > "+sampleCode+" </div>";
		
	td3.className='tdfonthead';																													
	td3.colspan="1";
	td3.style.width='15.5%';
	td3.style.backgroundColor=color;
	td5.innerHTML="<div align='left'  >"+sampleName+"</div>";
 
	td5.className='tdfonthead';
	td5.colspan="1";
	td5.style.width='15.5%';
	td5.style.backgroundColor=color;
		td6.innerHTML="<div align='left'  >"+status+"</div>";	
		td6.className='tdfonthead';
		td6.colspan="1";
		td6.style.width='16.5%';
		td6.style.backgroundColor=color;
		td7.innerHTML="<div align='left'  >"+priority+"</div>";
		  
		td7.className='tdfonthead';
	td7.colspan="1";
	td7.style.width='9%';
	td7.style.backgroundColor=color;
	td8.innerHTML="<div align='left' >"+reqDate+"</div>";
		 
		 
		 
		td8.className='tdfonthead';
	td8.colspan="1";
	td8.style.backgroundColor=color;
	td8.style.width='11%';
	
	
	td9.className='tdfonthead';
	td9.colspan="2";
	td9.style.width='2%';
	td9.style.backgroundColor=color;
	if(prvReqStatus=='2'||prvReqStatus=='5')
	{
	
	td9.innerHTML="<div align='center' ><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRowPrvReqDtl("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+")'></div>";
	}
	 
	   
	
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td5);
	tabRow.appendChild(td6);
	tabRow.appendChild(td7);
	tabRow.appendChild(td8);
	tabRow.appendChild(td9);

	document.forms[0].numberOfRow.value=numRows;
	 
}  
  
  
  
  function AddRowToTableAddMoreValue(chkVal,mandTextBoxComboName,finalMadCode)
  {
	//Logic to regain the Lab Test Values
  	// Lab Code
  	//alert("inside here"+mandTextBoxComboName);
  	
  //	document.getElementsByName('mandComboTextBoxComboNames')[0].value=mandTextBoxComboName;
  	document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value=mandTextBoxComboName;
  	
  	
  	document.getElementsByName('finalMandCode')[0].value=finalMadCode;
  	//var chkArr=chkVal.split("#");
  	//var labCode=chkArr[0];
  	// Lab Name
  //	var labName=chkArr[1];
   
  	 
  	 
  	var nRow=0;
  	var tableObj=document.getElementById('addMoreValue');
  	var numRows=tableObj.rows.length;
  	//alert("total length"+numRows);
  		nRow=numRows;
   
  	var tabRow=tableObj.insertRow(numRows);
  	tabRow.id=parseInt(nRow);
  	 
  	var td1=document.createElement("TD");
   
  	td1.innerHTML="<div align='left'>"+chkVal+" </div>";   
  	td1.className='tdfont';
  	td1.colspan="1";
  		
     
  	tabRow.appendChild(td1);  
  	 
  	 

  	document.forms[0].numberOfRow.value=numRows;
  	  }
  
  
  
  
  
  
  
  
  
  
  
   
  
  
function showAptBasedTest()
{

	//alert("appoitmentlist");
	document.getElementsByName('hmode')[0].value="APTBASEDTEST";
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	document.forms[0].submit();
}
  
var concatenateChkBoxVal="";

var concatenateChkBoxValOfAppoitmetnDate="";
  
function ValidateSameCrNo(obj)
{
	
//alert("inside here");

	if(obj.checked)
	{
	
		document.getElementById('nextDiv').style.display="";
		
                
	}
  else
     	{
	//  document.getElementById('gob').style.display="";
      //	document.getElementById('cancel').style.display="";
      	
          }
	 
	var objCurrentCheckBox=obj.value;
	//alert(obj.checked);
	if(obj.checked)
	{
		
		var Value=obj.value;
		var splitValue=Value.split("#");

		document.getElementsByName('patCrNo')[0].value=splitValue[0];
		//alert(document.getElementsByName('patCrNo')[0].value);
		
		
		concatenateChkBoxVal =concatenateChkBoxVal.concat(splitValue[1]);
		concatenateChkBoxVal+=',';
		
		
		concatenateChkBoxValOfAppoitmetnDate =concatenateChkBoxValOfAppoitmetnDate.concat(splitValue[2]);
		concatenateChkBoxValOfAppoitmetnDate+='@';
		
		
		concataptNo=concataptNo.concat(splitValue[3]);
		concataptNo+='`';
		
		 document.getElementsByName('aptTestCode')[0].value=concatenateChkBoxVal;
		 
		 document.getElementsByName('offlineAppoitMentDate')[0].value=concatenateChkBoxValOfAppoitmetnDate;
		 
		 
		 document.getElementsByName('hidAptNo')[0].value=concataptNo;
		// alert(document.getElementsByName('hidAptNo')[0].value);
		
		// alert("offlineAppoitMentDate VAlue"+document.getElementsByName('offlineAppoitMentDate')[0].value);
		 
		var cbs = document.getElementsByTagName('input');
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    		 
			    	alert("Please Select Same CR Number");
			    	document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
			    	 
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]+cbs[i].value.split("#")[1]+cbs[i].value.split("#")[3]!=objCurrentCheckBox.split("#")[0]+objCurrentCheckBox.split("#")[1]+objCurrentCheckBox.split("#")[3]))
				    	{
				    		 
				    	alert("Please Select Different Lab And Test");
				    	document.getElementById('nextDiv').style.display="none";
				    	obj.checked=false;
				    	return false;
				    	} 
				}
		}
	}
 
} 
  
  function displayAptDetails()
  {
	 
	  
	
	  document.getElementsByName('aptStatus')[0].value="1";
	  document.getElementsByName('hmode')[0].value="APTDETAIL";
	   document.forms[0].action="/HISInvestigationG5/new_investigation/invRaisingCumSamCollection.cnt"; 

	   document.forms[0].submit();
	  
  }
  
  function load(val)
  {
	  alert("working fine");
  }
  
  
  function updateReqTable(labCode,testCode)
  {
	//  alert("inside del");
  	var flg = false;
  	var isRequisitonRaisingPresent = false;
  	var _mode = "DELETEREQDTL";  
  	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode, sync:true, postData: "", handleAs: "text",
  		load: function(data) 
  		{
  			//alert(data);
  			isRequisitonRaisingPresent = data;
  			flg = true;
  		},
          error: function(error)
          {
              //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
              	//alert("No Kitchen Found");
              //alert(error+"Error while populating Meal Time Information");
              isRequisitonRaisingPresent = false;
              flg = false;
          }};

  	var objDojoAjax = dojo.xhrPost(objXHR);
  	return isRequisitonRaisingPresent;
  }
  
  function deleteRowPrvReqDtl(nrows,labCode,testCode)

  {
	 
	  var retVal = confirm("Are You Sure to Delete it?");
      if( retVal == true ){
//alert("user Wants To Continue!....");
    	  document.getElementsByName('delTestCode')[0].value=testCode;
    	  document.getElementsByName('delLabCode')[0].value=labCode;
    	 /*  document.getElementsByName('hmode')[0].value="DELETEREQDTL";
    	  document.forms[0].submit(); */
    	  updateReqTable(labCode,testCode);
    	  
    	   showPrvDetail(); 
      }
      else{
     	  //alert("User does not want to continue!....");
         return false;
      }
	 
	   
	  
  }
  
  
  function pageload()
  {
	  document.getElementById("automplete-5").focus();
	  //alert("working fine"+document.getElementsByName("testLabTestCodeWise")[0].value);
	   if(document.getElementsByName("testLabTestCodeWise")[0].value!='')
		  {
		 /// alert("inside");
	  if(document.getElementsByName("testLabTestCodeWise")[0].value=='myhisswitchTestLab')
	  {
		  document.getElementById("searchLabTestDiv").style.display="none";
		  document.getElementById("testCodeWiseSearchDiv").style.display="";
		  document.getElementById("currentReqDtl").style.color = "#A52A2A";
		  document.getElementById("currentReqDtl").style.fontFamily = "Times New Roman";
		  document.getElementById("currentReqDtl").style.fontSize = "14px";
		  document.getElementById("currentReqDtl").style.backgroundColor="#87CEFA";
		  document.getElementById("currentReqDtl").style.borderRadius ="20px 20px 1px 1px";
		  document.getElementById("currentReqDtl").style.width ="16.5em";
		   
		  
		  document.getElementById("lengendStatus").style.display="none";
		  document.getElementById("prvReqDtl").style.color = "";
		  document.getElementById("prvReqDtl").style.fontFamily = "";
		  document.getElementById("prvReqDtl").style.fontSize = "";
		  document.getElementById("prvReqDtl").style.backgroundColor="";
		  document.getElementById("prvReqDtl").style.borderRadius ="";
		  document.getElementById("prvReqDtl").style.width ="";
	  }
  if(document.getElementsByName("testLabTestCodeWise")[0].value=='myhisswitchTest')
  {
	  document.getElementById("testCodeWiseSearchDiv").style.display="none";
	  document.getElementById("searchLabTestDiv").style.display="";
	  document.getElementById("currentReqDtl").style.color = "#A52A2A";
	  document.getElementById("currentReqDtl").style.fontFamily = "Times New Roman";
	  document.getElementById("currentReqDtl").style.fontSize = "14px";
	  document.getElementById("currentReqDtl").style.backgroundColor="#87CEFA";
	  document.getElementById("currentReqDtl").style.borderRadius ="20px 20px 1px 1px";
	  document.getElementById("currentReqDtl").style.width ="16em";
	   
	  
	  document.getElementById("lengendStatus").style.display="none";
	  
	  document.getElementById("prvReqDtl").style.color = "";
	  document.getElementById("prvReqDtl").style.fontFamily = "";
	  document.getElementById("prvReqDtl").style.fontSize = "";
	  document.getElementById("prvReqDtl").style.backgroundColor="";
	  document.getElementById("prvReqDtl").style.borderRadius ="";
	  document.getElementById("prvReqDtl").style.width ="";
		  }
	  
		  }  
	   else
		   {
		   

		      document.getElementById("currentReqDtl").style.color = "#A52A2A";
		  document.getElementById("currentReqDtl").style.fontFamily = "Times New Roman";
		  document.getElementById("currentReqDtl").style.fontSize = "14px";
		  document.getElementById("currentReqDtl").style.backgroundColor="#87CEFA";
		  document.getElementById("currentReqDtl").style.borderRadius ="20px 20px 1px 1px";
		  document.getElementById("currentReqDtl").style.width ="16em";
		   
		  
		  document.getElementById("lengendStatus").style.display="none";
		  
		  document.getElementById("prvReqDtl").style.color = "";
		  document.getElementById("prvReqDtl").style.fontFamily = "";
		  document.getElementById("prvReqDtl").style.fontSize = "";
		  document.getElementById("prvReqDtl").style.backgroundColor="";
		  document.getElementById("prvReqDtl").style.borderRadius ="";
		  document.getElementById("prvReqDtl").style.width ="";
		   }
	  
	 
	  
  }  
  
  
  function getTestWiseList(val)
  {
	  
	// alert(val);
	  if(val=='myhisswitchTestLab')
		  {
		  document.getElementsByName("testLabTestCodeWise")[0].value=val;
	  document.getElementById("searchLabTestDiv").style.display="none";
	  document.getElementById("testCodeWiseSearchDiv").style.display="";
	 
	  
		  }
	  if(val=='myhisswitchTest')
	  {
		  document.getElementsByName("testLabTestCodeWise")[0].value=val;
		  document.getElementById("testCodeWiseSearchDiv").style.display="none";
		  document.getElementById("searchLabTestDiv").style.display="";
		   
	  }
	  
  }
  
  
  
  
  
//   function setTestCodeWiseDetail()
//   {
//   	 //alert(document.getElementsByName('testCodeWise')[0].value);
  	 
//   	 if(document.getElementsByName('testCodeWise')[0].value=='')
// 		{
// 			alert('Select Test Code!');
// 			document.getElementsByName('testCodeWise')[0].focus();
// 			return false;
// 		} 
//   		document.getElementsByName('hmode')[0].value="SEARCHTESTCODEWISE";
//   		 document.forms[0].submit();
  	
//   }
  
  
  
  
  
  function setTestCodeWiseDetail()
  {
  	//alert(document.getElementsByName('searchLab')[0].value);
  	 
  	
  	
  	 var testCodeList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)%>;
  	 
  	 for(k=0;k<testCodeList.length;k++)
  		{
  		
  		 if(testCodeList[k].value.toUpperCase()==document.getElementById('automplete-5').value.toUpperCase())
  			 {
  			 document.getElementsByName('testCodeWise')[0].value=document.getElementById('automplete-5').value;
  			 }
  		 }
  	 
  	 
  	 
  
  	 
  	 
  	 if(document.getElementsByName('testCodeWise')[0].value=='')
		{
			alert('Select Valid Test Code!');
			document.getElementById('automplete-5').value="";
			document.getElementById('automplete-5').focus();
		

			
			//document.getElementsByName('testCodeWise')[0].focus();
			return false;
		} 
  		document.getElementsByName('hmode')[0].value="SEARCHTESTCODEWISE";
  		 document.forms[0].submit();
  	
  }
  
  
  

  
  function setUserGroupWiseDetail()
  {
  	 var testCodeList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)%>;
  	 
  	 for(k=0;k<testCodeList.length;k++)
  		{
  			 if(testCodeList[k].value.toUpperCase()==document.getElementById('automplete-6').value.toUpperCase())
  			 {
  			 document.getElementsByName('userGroupCodeWise')[0].value=document.getElementById('automplete-6').value;
  			 }
  		 }
  	  	 
  	 if(document.getElementsByName('userGroupCodeWise')[0].value=='')
		{
			alert('Select Valid Group Code!');
			document.getElementById('automplete-6').value="";
			document.getElementById('automplete-6').focus();
			return false;
		} 
  	 
  	
  		document.getElementsByName('hmode')[0].value="SEARCHGROUPCODEWISE";
  		 document.forms[0].submit();
  	
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
             
              isSampleNoPresent = false;
              flg = false;
          }};

  	var objDojoAjax = dojo.xhrPost(objXHR);
  	return isSampleNoPresent;
  }


</script>


<!-- to check if sample no configured or not
 -->
 
 <script>

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
	
		var labCode=splitedAutoGenValue[4];
		var testCode=splitedAutoGenValue[6];
		var patType=splitedAutoGenValue[10];
		var sampleArea=document.getElementsByName("sampleAreaCode")[0].value;
		
		
		var labName=splitedAutoGenValue[11];
		var testName=splitedAutoGenValue[12];
		var sampleconfig=splitedAutoGenValue[9];
		
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
				
				var indexWithSubIndex=id.substring(0,2);
			
				document.getElementById(indexWithSubIndex+"unCheck").checked = false;
			
		    			 
			}
		}
	else
		{
		 //alert("add null");
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
		document.getElementById("saveDiv").style.display=""; 
		var checkValueForAutoGen=document.getElementById(k+""+""+j+""+index+"unCheck").value;
		var splitedAutoGenValue=checkValueForAutoGen.split("#");
		var labCode=splitedAutoGenValue[4];
		var testCode=splitedAutoGenValue[6];
		var patType=splitedAutoGenValue[10];
		var sampleArea=document.getElementsByName("sampleAreaCode")[0].value;
		
		var labName=splitedAutoGenValue[11];
		var testName=splitedAutoGenValue[12];
		var sampleconfig=splitedAutoGenValue[9];
	
		if(sampleconfig==1||sampleconfig==2)	 
		 {
			var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig,sampleArea);
			if(autoGenFormate!='null')
			{	document.getElementById(k+""+""+j+""+index+"unCheck").checked=true;
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
			{}
		}
	else
		checked=false;
}
 

function CheckAutoSampleNoFormate(LabCode,TestCode,patType,tempSampleNo,sampleAreaCode)
{

	//alert("inside Ajax"+sampleArea);
	
	var flg = false;
	var autoGenFormate = "";
	var _mode = "AJX_CHECK_AUTO_SAMPLENO_GEN";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&labCode="+LabCode+"&testCode="+TestCode+"&patType="+patType+"&tempSampleNo="+tempSampleNo+"&sampleAreaCode="+sampleAreaCode, sync:true, postData: "", handleAs: "text",
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




function saveUpdatedAppointment(){
	 
	 
	 
	 var chkBoxSample=document.getElementsByName("allotAppointment");
		var j=0;				
	 
	for(var i=0;i<chkBoxSample.length;i++)
		if(!document.getElementsByName("allotAppointment")[i].checked)
	 j++;
	 
	 
	
	if(j==chkBoxSample.length)
	{ 
		alert("Please select atleast one Record");
		return false;
	}
	
	
	
	
	for(i=0;i<chkBoxSample.length;i++)
	{
		
	//	alert("inside loop");
	var flag=false;
	if(document.getElementsByName("allotAppointment")[i].checked)
		{
		
		SplitValue=document.getElementsByName("allotAppointment")[i].value.split("#");
		
	var isAppointment=SplitValue[9];	
	var labCode=SplitValue[3];
	var testCode=SplitValue[4];
	
	if(isAppointment=="1")
	{
		var divId="aptTagRow_"+labCode+"_"+testCode;
	//alert("app based test condition");
	if(typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined')
		{
	
	var aptSatus=createAppointment(divId);
	//alert(document.getElementsByName('appointmentRefNo')[0].value);
	
	var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
	//alert("ref no is " +objaptno);
	//setAptNoUsingAjax(objaptno,labCode,testCode)
	
	//alert("apt "+aptSatus);appointmentRefNo
	if(!aptSatus)
		 {
		 
		return false;
		}
		}
	
	}
	if(isAppointment==1 && typeof document.getElementsByName('aptForDate_'+divId+'')[0] == 'undefined')
	{
	
	alert("Can't raise Test as no slots are available");
	// alert("returing false 2");
	return false;
	
	}
	
		}
	}

	
	 var today = new Date();
	 var date=convertDateToStr(today,"dd-Mon-yyyy");
		var Time=convertDateToStr(today,"hh:mm"); 
		    //alert(date);
		    //alert(Time);

	    
	//alert(document.getElementsByName("freeSlotLabel")[0].value);
	//var freeSlotLabel=document.getElementsByName("freeSlotLabel")[0].value;
	//var arrAppointmentDate=freeSlotLabel.split(" ");
		if (typeof document.getElementsByName('aptForDate_'+divId+'')[0] != 'undefined')
			{
			alert("set app date and time now");	
			///alert(document.getElementsByName('slotST_'+divId+'')[0].value);
	           document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+divId+'')[0].value;

				document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+divId+'')[0].value;
				
				//createAppointment();
		//if (typeof $('[name="slotST"]').val() === "undefined")
		     
			}
			else
			{
				//alert("offline Apt Details"+document.getElementsByName('offlineAppoitmentDtl')[0].value);
				 if(document.getElementsByName('offlineAppoitmentDtl')[0].value=='')
					 {
					 
					 
				    //alert(document.getElementsByName('appointmentDate')[0].value);
			        ///alert(document.getElementsByName('appointmentTime')[0].value);
			        
			        
			        
					 }
				 else
					 {
					// alert("false");
					 var dateAndTime=document.getElementsByName('offlineAppoitmentDtl')[0].value;
					 var SplitWithSpace=dateAndTime.split(' ');
					 
					 var offlineAptDate=SplitWithSpace[0];
					 var offLineAptTime=SplitWithSpace[1];
				//	 alert(offlineAptDate);
					// alert(offLineAptTime);
					 
					 document.getElementsByName('appointmentDate')[0].value=offlineAptDate;
				        document.getElementsByName('appointmentTime')[0].value=offLineAptTime;
					 
				        document.getElementsByName('appointmentRefNo')[0].value=document.getElementsByName('offlineAptDtl')[0].value;
				        
				       // alert(document.getElementsByName('appointmentRefNo')[0].value);
				        
					 }
			}
		 
	
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one Record");
				return false;
			}
	 
		document.getElementsByName('hmode')[0].value='SAVEAPPOINTMENT';
		document.forms[0].submit();
	 
}


</script>

<script>

$(function(){
	$("#automplete-1").keypress(function(event) {
    if (event.which == 13) {
    	searchLabWiseTest();
       }
});
});
</script>
<script>

$(function(){
	$("#automplete-2").keypress(function(event) {
	
    if (event.which == 13) {
    	
       	searchLabWiseTest();
       	
       }
});
});



</script>


<script>

$(function(){
	$("#automplete-5").keypress(function(event) {
	
    if (event.which == 13) {
    	
    	setTestCodeWiseDetail();
       	
       }
});
});


$(function(){
	$("#automplete-6").keypress(function(event) {
	
    if (event.which == 13) {
    	
    	setUserGroupWiseDetail();
       	
       }
});
});



</script>



<style>
.setAdvisedBy {
	position: fixed;
	top: 180px;
	right: 50px;
	z-index: 100;
}

 .ui-autocomplete {
    max-height: 100px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
  }
  
</style>


<style>
.scroll_div {
	height: 70px;
	overflow-y: hidden;
	overflow-x: scroll;
	text-align: justify;
	margin: 0;
	border-style: groove;
	padding: px px px px;
	scrollbar-face-color: #666669;
	scrollbar-highlight-color: #030000;
	scrollbar-3dlight-color: #030000;
	scrollbar-darkshadow-color: #030000;
	scrollbar-shadow-color: #030000;
	scrollbar-arrow-color: #030000;
	scrollbar-track-color: #030000;
	border: 1px solid  ;
margin: 0px 1px 2px 2px; 
border-radius: 10px;
box-shadow: 5px 5px 5px #888;
background-color: #ffffff;
padding-bottom: 2px;
}
</style>
<style>
#colorCycle {
	background-color: #B6DAFF;
	border:  1px solid #1277b5;
	padding-top: 4px;
	padding-right: 3px;
	padding-bottom: 5px;
	padding-left: 5px;
	color: brown;
	text-align: left;
	border-radius: 10px 1px 10px 1px;	
	animation-name: homeCycle;
	animation-duration: 6s;
	animation-direction: alternate;
	animation-iteration-count: infinite;
	
	-webkit-animation-name: homeCycle;
	-webkit-animation-duration: 6s;
	-webkit-animation-direction: alternate;
	-webkit-animation-iteration-count: infinite;
}

@
keyframes homeCycle { 0% {
	background-color: #3366CC;
}

25%{background-color:#003399;}
50%{background-color:#6699FF;}
75%{background-color:#3366CC;}
}
@
-webkit-keyframes homeCycle { 0% {
	background-color: #3366CC;
}
25%{background-color:#003399;}
50%
{background-color:
#6699FF;
}
75%
{
background-color
: 
#3366CC 
;
}
}
</style>
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


<style>
.NewTextBox {
	border:2px solid #456879;
	border-radius:10px;
	height: 22px;
	width: 330px;
}
</style>
<style>
 
.titleTagB
{
 background: #1277b5; /* Old browsers */
background: -moz-linear-gradient(-45deg,  #1277b5 0%, #a1c1ea 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, right bottom, color-stop(0%,#1277b5), color-stop(100%,#a1c1ea)); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(-45deg,  #1277b5 0%,#a1c1ea 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(-45deg,  #1277b5 0%,#a1c1ea 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(-45deg,  #1277b5 0%,#a1c1ea 100%); /* IE10+ */
background: linear-gradient(135deg,  #1277b5 0%,#a1c1ea 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1277b5', endColorstr='#a1c1ea',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
border: 2px solid #96BAEA;
    padding: 0px 5px; 
     
border-radius: 20px 20px 2px 2px;
}


.subTitleTagB
{

 background: #1277b5; /* Old browsers */
	background: -moz-linear-gradient(top,  #135d8c 0%, #1277b5 0%, #1277b5 32%, #135d8c 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#135d8c), color-stop(0%,#1277b5), color-stop(32%,#1277b5), color-stop(100%,#135d8c)); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(top,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* IE10+ */
	background: linear-gradient(to bottom,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1277b5', endColorstr='#135d8c',GradientType=0 ); /* IE6-9 */
	
	border: 2px solid #96BAEA;
    padding: 0px 5px; 
     
border-radius: 20px 20px 2px 2px;
}

 
.divStyle
{
border: px solid  ;
margin: 15px 0px;
border-radius: 10px;
box-shadow: 5px 5px 5px #888;
background-color: #ffffff;
padding-bottom: 2px;
}
.subDivStyle
{  
	text-align: justify;
	margin: 0;
	 
	padding: px px px px;
	border: px solid  ;
margin: 0px 1px 2px 2px; 
border-radius: 10px;
box-shadow: 5px 5px 5px #888;
background-color: #ffffff;
padding-bottom: 2px;
}
</style>


<style>
.cssClass{
  float: left;
    cololr:#fffff;
    background-repeat: no-repeat;
    background-position: top right;
    background-color: #4682B4;
    margin: 0 3px 0 0;
    padding: 0;
    	   
border-radius: 20px 20px 1px 1px;

}
</style>
<%
	String strdivage="\"\"";
String strdivdob="\"\""; 
String patCrNo="";  //style="width:90%;margin:auto;min-width:600px;max-width:2000px"
%>


 <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
             
               
         %>
         
         
         
<body    onload="callThisOnload();pageload();getTestWiseList('myhisswitchTest');" >
<div class="divStyle">
<script>
 
</script>
	<html:form action="/invRaisingCumSamCollection">
	
	
<div id="blanket" style="display: none"></div>
 		<div id="popUpDiv5"  style="display:none;margin-top: 0%" align="center">  
		 
		<his:TitleTag name="Instructions For Patients">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
  		</his:TitleTag>
  			
  			
 		<table width="100%" id="allInstructions">
 	             
 		<tr>
 		
 		<!-- <td class="tdfonthead" width="20%">
		<div align="left"><b>Instructions for:</b></div>
		</td>
		
		<td class="tdfonthead" width="80%">
		<div align="center"><b>Instructions</b></div>
		</td> -->
						
		</tr>
		
 		
 		</table>
 		                             
<img src='/HISInvestigationG5/hisglobal/images/ok.gif'  onClick="closeInstructions();"> 		                        
				

		</div>
	
	
 	
 	<!-- END -- TO DISPLAY INSTRUCTIONS -->
		<his:statusNew>
			<his:TitleTag key="invRaising">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			
			
			<table width="100%">
					<tr>
			
					<td class="tdfont" width="17%">
						<div align="left">
						 	<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">*</font><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="areaCombo"/>
					</font>
	        		
        		</div>
					 
					</td>
					
					
					
						<td class="tdfont" width="83%">
					 				
						 				
						 				<html:select    name="invRaisingCumSamCollectionFB" property="sampleAreaCode" tabindex="1" style="width:16%;" >					
										
										
										<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO_RAISING%>">
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO_RAISING%>"></bean:define>
												<%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
												<%} %>
 											 
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO_RAISING%>" property="value" labelProperty="label"  />
										</logic:present>
										
										<logic:notPresent name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO_RAISING%>">
											<html:option value="-1">Select Value</html:option></logic:notPresent>
																		
										</html:select> 
									
									
									
					 			</td>
					 			
					 			
					 			
			
			
			
			
			</tr>
			
			</table>
			 <his:InputCrNoInvestigationTag name="invRaisingCumSamCollectionFB">
			</his:InputCrNoInvestigationTag>
			<table width="100%">
	
			
			
				<tr>
				<%-- 
					 <td class="tdfonthead" width="25%">
						<div align="right">
							<input type="radio" name="radioEpisode" value=""
								onclick="showAptBasedTest()" />
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
						 
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="AppointmentBasedTest"/>
					</font>
        		</div>
					 
					</td>  --%>
					
				<td class="tdfont" width="25%">
						<div align="right">
							<input type="radio" name="radioEpisode" value=""
								onclick="showAptBaseByDesk()" />
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="AptBaseByDesk"/>
						</font>
						</div>
					</td> 
					
			
				<!-- 	<td class="tdfonthead" width="25%">
						<div align="right"></div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left"></div>
					</td> -->
				</tr>

			</table>





		</his:statusNew>
		<his:statusTransactionInProcess>
			<%
			/* 	String deskType = (String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE); */
				UserVO userVO=ControllerUTIL.getUserVO(request);
				/* String opdlocal=userVO.getUsrName(); */
			%>
			<logic:equal name="invRaisingCumSamCollectionFB" property="aptStatus"
				value="0">
				<logic:present name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
					<his:SubTitleTag name="Today's Appoitment Based Test">
					</his:SubTitleTag>
					<table width="100%">
						<tr>
							<td class="tdfonthead" width="10%">

								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Select" />
									</font>

								</div>
							</td>
							<td class="tdfonthead" width="18%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="CrNO" />
									</font>
								</div>
							</td>


							<td class="tdfonthead" width="18%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Name" />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="18%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Age" />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="18%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Gender" />
									</font>
								</div>
							</td>
							<td class="tdfonthead" width="18%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="AppoitmentDate/Time" />
									</font>
								</div>
							</td>

						</tr>

						<%
							List<LabTestVO> lstPatAppotmentVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APTBASED_TEST);
																										  	 
																										 	if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
																										 	{
																										 		int sizePatAptVO=lstPatAppotmentVO.size();
																										 	
																										  
																										 		for(int k=0;k<sizePatAptVO;k++)
																										 		{
																										 			LabTestVO voPatApt=lstPatAppotmentVO.get(k);
																										 		String chkVal=voPatApt.getPukNo()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getAptDate()+" "+voPatApt.getSlotTime();
						%>
						<tr>

							<td class="tdfont" width="10%">
								<div align="left">
									<input type="checkbox" name="chkSamplePatient"
										value='<%=chkVal%>' onclick="ValidateSameCrNo(this)">
								</div>
							</td>


							<td class="tdfont" width="18%">

								<div align="left">
									<%=voPatApt.getPukNo()==null?"NA":voPatApt.getPukNo()%>
								</div>
							</td>


							<td class="tdfont" width="18%">
								<div align="left">
									<%=voPatApt.getPatFirstName()+" "+voPatApt.getPatMiddleName()+" "+voPatApt.getPatLastNAme()%>
								</div>
							</td>


							<td class="tdfont" width="18%">
								<div align="left">
									<%=voPatApt.getPatAge()%>
								</div>
							</td>

							<td class="tdfont" width="18%">
								<div align="left">
									<%=voPatApt.getGender()%>
								</div>
							</td>


							<td class="tdfont" width="18%">
								<div align="left">
									<%=voPatApt.getAptDate()+"      "+voPatApt.getSlotTime()%>
								</div>
							</td>

						</tr>

						<%
							}
																	 	}
						%>
					</table>

				</logic:present>
				<logic:empty name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
					<!-- <center> -->
					<font id="colorCycl" color="red" size="4"><bean:message
							key="NoOfflineAppoitmentListFound" /> </font>
					<!-- 	</center> -->
				</logic:empty>
			</logic:equal>


			
			<!-- APPOINTMENT RAISED BY DOCTORS FROM DESK -->

			<logic:equal name="invRaisingCumSamCollectionFB" property="aptStatus"
				value="2">
				
				 <bean:define name="invRaisingCumSamCollectionFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="invRaisingCumSamCollectionFB" property="toDate" id="tDate" type="java.lang.String"/>      
				
				
					<%if(frDate==null||frDate.equalsIgnoreCase(""))
	         {  
	        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
	        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	         }
	  
			  if(tDate==null||tDate.equalsIgnoreCase(""))
			  {  
			  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
			  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			   } %>
			
			
					<table width="100%">
					
						<tr >            
									 			<td class="tdfonthead" width="20%">
									        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="fromDate"/>
														</font>
									        		</div>
									        		
												</td>
												<td class="tdfont" width="20%">
										    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
														<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
													</div>
											 		
												</td>
									 		            
									 			<td class="tdfonthead" width="20%">
									        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="toDate"/>
														</font>
									        		</div>
												</td>
												
												<td class="tdfont" width="20%">
										    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
														<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
													</div>
											 		
												</td>
												
												<td class="tdfonthead" width="20%">
									        		<div  align="left">
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  			 <img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/GoNew.png"/>'  tabindex="1" onclick ="showAptBaseByDesk()" >
														</font>
									        		</div>
									        		<html:hidden property="sampleAreaCode"/>
												</td>
												
												
								 		</tr>
							
					
					
					</table>
					
					
				<logic:present name="<%=InvestigationConfig.LIST_APT_BY_DESK%>">
					<his:SubTitleTag name="Test awaiting appointment as advised by Doctors">
					</his:SubTitleTag>
					
					<table width="100%">
						<tr>
						
						<td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</div>
							</td>
						
							<td class="tdfont" width="5%">

								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 <bean:message key="Select"/>  </font>

								</div>
								
								
							</td>
							<td class="tdfont" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="CrNO"/></font>
								</div>
							</td>


							<td class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="Name"/></font>
								</div>
							</td>
							<td class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="Age"/> </font>
								</div>
							</td>
							<td class="tdfont" width="20%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="reqDate"/> </font>
								</div>
							</td>
							
							<td class="tdfont" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="LabType"/> </font>
								</div>
							</td>
							<td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</div>
							</td>


						</tr>

						<%
							List<LabTestVO> lstPatAppotmentVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APT_BY_DESK);
																						  	 
																						 	if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
																						 	{
																						 		int sizePatAptVO=lstPatAppotmentVO.size();
																						 	
																						  
																						 		for(int k=0;k<sizePatAptVO;k++)
																						 		{
																						 			LabTestVO voPatApt=lstPatAppotmentVO.get(k);
																						 			String chkVal=voPatApt.getPatCrNo()+"#"+voPatApt.getReqNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getSampleCode()+"#"+k;
																						 			//String chkVal=voPatApt.getPukNo()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getAptDate()+" "+voPatApt.getSlotTime()+"#"+voPatApt.getAptNO()+"#"+voPatApt.getLabCode();
																						 		%>
						<tr>
 

 <td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</div>
							</td>
 
							<td class="tdfonthead" width="5%">
								<div align="left">
									<input type="checkbox" name="chkSamplePatient"
										value='<%=chkVal%>' onclick="ValidateSameCr(this)">
								</div>
							</td>
							 
							 
								<td class="tdfonthead" width="15%">
								 
								<div align="left">
									<%=voPatApt.getPatCrNo()==null?"NA":voPatApt.getPatCrNo() %>
								</div>
							</td>
								 

							<td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getPatName()%>
								</div>
							</td>


							<td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getPatAge()%>
								</div>
							</td>

							<td class="tdfonthead" width="20%">
								<div align="left">
									<%=voPatApt.getReqDate()%>
								</div>
							</td>

							<td class="tdfonthead" width="15%">
								<div align="left">
									<%=voPatApt.getLabName()%>
								</div>
							</td>

							<td class="tdfonthead" width="12.5%">
								<div align="left">
								
								</div>
							</td>

						</tr>

						<%
							}
													 	}
						%>
					</table>

				</logic:present>
				<logic:empty name="<%=InvestigationConfig.LIST_APT_BY_DESK%>">
					<center>
						<font id="colorCycl" color="red" size="4"><bean:message key="NoTestAdvised"/> </font>
				</center>
				</logic:empty>
			</logic:equal>

 
			<!-- END OF APPOINTMENT RAISED BY DOCTORS FROM DESK -->



	
			<!-- Details of APPOINTMENT based tests RAISED BY DOCTORS FROM DESK -->

			<logic:equal name="invRaisingCumSamCollectionFB" property="aptStatus"
				value="5">
		
				<logic:present name="<%=InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK%>">
					<his:SubTitleTag name="Select Appointment Dates">
					</his:SubTitleTag>
					<table width="100%">
						<tr>
						
						<td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										 <bean:message key="Select"/>  </font>
								</div>
							</td>
						
						
							<td class="tdfont" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="LabType"/> </font>
								</div>
							</td>


							<td class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="Test"/></font>
								</div>
							</td>
							<td class="tdfont" width="10%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="sampleCombo"/></font>
								</div>
							</td>
							<td class="tdfont" width="20%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="Appointment"/> </font>
								</div>
							</td>
							
							<td class="tdfont" width="15%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Sample No
									</font>
								</div>
							</td>
							<td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Sample Qty
									</font>
								</div>
							</td>
							<td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									UOM
									</font>
								</div>
							</td>
							<td class="tdfont" width="12.5%">
								<div align="left">
									<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Container
									</font>
								</div>
							</td>


						</tr>
						</table>

						<%
							List<LabTestVO> lstPatAppotmentVO=(List<LabTestVO>)session.getAttribute(InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK);
																						  	 
																						 	if(lstPatAppotmentVO!=null && lstPatAppotmentVO.size()>0 )
																						 	{
																						 		int sizePatAptVO=lstPatAppotmentVO.size();
																						 	
																						  
																						 		for(int k=0;k<sizePatAptVO;k++)
																						 		{
																						 			
																						 			LabTestVO voPatApt=lstPatAppotmentVO.get(k);
																						 			String chkVal=voPatApt.getPatCrNo()+"#"+voPatApt.getReqNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getSampleCode()+"#"+k;
																						 			
																						 			String _paraId=voPatApt.getLabCode()+"^"+voPatApt.getTestCode()+"^0^0^0^0^0";
																						 			String paraNum=voPatApt.getLabCode()+"^"+voPatApt.getTestCode()+"^0^0^0^0^0";
																						 			String groupType="0";
																						 			if(voPatApt.getGroupType()==null || voPatApt.getGroupType().equals(""))
																						 				voPatApt.setGroupType(groupType);
																						 			if(voPatApt.getGroupCode()==null || voPatApt.getGroupCode().equals(""))
																						 				voPatApt.setGroupCode("0");
																						 			String onLoadRow="getAptSlotDetails('"+voPatApt.getPatCrNo()+"','"+paraNum+"','','aptTagRow_"+voPatApt.getLabCode()+"_"+voPatApt.getTestCode()+"','2')";
																						 			String allot=voPatApt.getReqNo()+"#"+voPatApt.getPatCrNo()+"#"+voPatApt.getRequisitionDNo()+"#"+voPatApt.getLabCode()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getPriorityCode()+"#"+voPatApt.getPatType()+"#"+voPatApt.getGroupType()+"#"+voPatApt.getGroupCode()+"#"+voPatApt.getIsAppointment()+"#"+voPatApt.getSampleCode();
																						 			//String chkVal=voPatApt.getPukNo()+"#"+voPatApt.getTestCode()+"#"+voPatApt.getAptDate()+" "+voPatApt.getSlotTime()+"#"+voPatApt.getAptNO()+"#"+voPatApt.getLabCode();
																						 			 			String sampleConfiguration=voPatApt.getSampleNoConfiguration()==null?"3":voPatApt.getSampleNoConfiguration();

																						 				boolean isSampleGenAuto=false;
	 			
	 																							if(sampleConfiguration.equals("1")||sampleConfiguration.equals("2"))
	 																							isSampleGenAuto=true;  //readOnly="readonly='true'"; //
	 				
	 				
																						 	if(voPatApt.getIsAppointment().equals("1"))	
																						 	{
																						 		%>
																						 		
												<script>
							 	   <%=onLoadRow%>
						        </script>
						     <%} %>   
						        										 		
									<table width="100%">													 		
						<tr>
 

 <td class="tdfonthead" width="12.5%">
								<div align="left">
									
									<input type="checkbox" name="allotAppointment"
										value='<%=allot%>'>
								</div>
							</td>
 
							 
								<td class="tdfonthead" width="15%">
								 
								<div align="left">
									<%=voPatApt.getLabName()%>
								</div>
							</td>
								 

							<td class="tdfonthead" width="10%">
								<div align="left">
									<%=voPatApt.getTestName() %>
								</div>
							</td>


							<td class="tdfonthead" width="10%">
								<div align="left">
										<%=voPatApt.getSampleName()==null?"Patient Based":voPatApt.getSampleName()%>
								</div>
							</td>


<%if(voPatApt.getIsAppointment().equals("1"))
	
	{%>
							<td class="tdfonthead" width="20%">
									<div align="left"
															id="aptTagRow_<%=voPatApt.getLabCode()%>_<%=voPatApt.getTestCode()%>" a></div> <%--labCode+testCode--%>

														<%
														String setDate = "setDateInApoitment('"+voPatApt.getPatCrNo()+"','"+paraNum+"','this','aptTagRow_"+voPatApt.getLabCode()+"_"+voPatApt.getTestCode()+"')";
													
														%> <input type="hidden" name="dateTag"
														onchange="<%=setDate%>" id="datepicker">
							</td>
<%}else{ %>


								<td class="tdfonthead" width="20%">
									<div align="left">
										<bean:message key="NA" />
									</div>
								</td>

								<%} %>
								
								
								
									<td class="tdfonthead">
					 			<div align="left">
					 				<font >	
 
					 				<% String strOnBlurFunction="chkSampleNoDuplicacyThroughAjax(this);"; %>  
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
					 			
					 			
					 			
					 			
					 			
								
							<td class="tdfonthead" >
					 				<div align="left">
					 					<html:text name="invRaisingCumSamCollectionFB" property="sampleQnty"  maxlength="10" size="10"  value='<%=voPatApt.getSampleQnty()==null?"":voPatApt.getSampleQnty() %>'  onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
					 				</div>
					 			</td>
					 			<td class="tdfonthead">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="invRaisingCumSamCollectionFB" property="defaultUOMCode"  value="<%=voPatApt.getDefaultUOMCode() %>" tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_UOM_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_UOM_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							  </span>
					 			</div>
					 			</td>
					 			<td class="tdfonthead" >
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="invRaisingCumSamCollectionFB" property="defaultContainerCode" value="<%=voPatApt.getDefaultContainerCode() %>" tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							</span>
					 			</div>
					 			</td>

						</tr>

						<%
							}
													 	}
						%>
					</table>

				</logic:present>
				<logic:empty name="<%=InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK%>">
					<center>
						<font id="colorCycl" color="red" size="4">No Test Advised </font>
				</center>
				</logic:empty>
			</logic:equal>

 
			<!-- END OF APPOINTMENT RAISED BY DOCTORS FROM DESK -->
			
			
			



			<!-- CR NO Tag -->
			<logic:present name="<%=InvestigationConfig.PATIENT_VO%>">



				<%
					Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
				%>

				 <his:SubTitleTag name="Patient Details">  
			 <% 
			 String showDetail="showPatDetailsPatient()";
			 String hideDetail="hidePatDetailsPatient()";
			 %>
  			 <img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="showPatient"      tabindex="1" onclick ="<%=showDetail%>" >
  			<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="hidePatient" style="display: none;"     tabindex="1" onclick ="<%=hideDetail %>" >
  			</his:SubTitleTag>
			<table width="100%">
			<tr>
		 			<td class="tdfonthead" width="15%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<bean:message key="Name"/>
		 				</font>
		 				</div>
		 				
		 			</td>
		 			<td class="tdfont" width="16%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatFirstName() %>
		 					<%=lstPatVO.getPatMiddleName() %>
		 					<%=lstPatVO.getPatLastName() %>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfonthead" width="15%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="CRNo."/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCRNo() %>
		 				</font>
		 				</div>
		 			</td> 
		 			
		 			 
			</tr>
		</table>
		<div id="showhidePatient" style="display: none;">
		<table  width="100%">
		<tr>
		<td class="tdfonthead" width="15%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="Age/Gender"/> 
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="16%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatAge()+"/"+lstPatVO.getPatGenderCode()%>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfonthead" width="15%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				 	<bean:message key="PrimaryCategory"/> 
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatCategory() %>
		 				</font>
		 				</div>
		 			</td> 
				<html:hidden name="invRaisingCumSamCollectionFB"
					value="<%=lstPatVO.getPatCategoryCode()%>" property="patCatCode" />
				 </tr>
		<tr>
		<td class="tdfonthead" width="15%">
		 				<div align="right">
		 					<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 						<bean:message key="FatherName"/>  
		 				</font>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="16%"> 
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatGuardianName() %>
		 					</font>
		 				</div>
		 			</td>
		 			 <td class="tdfonthead" width="15%">
		 				<div align="right">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="MobileNo."/>  
		 				 	</font>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="16%">
		 				<div align="left">
		 				<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
		 					<%=lstPatVO.getPatMobileNo() %>
		 				</font>
		 				</div>
		 			</td> 
		 			  
		</tr>
		</table>
		</div>

			</logic:present>


			<!-- IPD Logic LIST_APTBASED_TEST -->
			<logic:equal name="invRaisingCumSamCollectionFB" property="patStatus"
				value="IPD">


				<logic:present name="<%=InvestigationConfig.LIST_ADMISSION_VO%>">
						<his:SubTitleTag name="Admission Details"></
  </his:SubTitleTag>
					<his:ContentTag>

						<%
							List<Inv_PatientAdmissionDtlVO> lstPatAdmissionVO=(List<Inv_PatientAdmissionDtlVO>)session.getAttribute(InvestigationConfig.LIST_ADMISSION_VO);
																						  	 
																						 	if(lstPatAdmissionVO!=null && lstPatAdmissionVO.size()>0 )
																						 	{
																						 		int sizePatAdmVO=lstPatAdmissionVO.size();
																						 	
																						  
																						 		for(int k=0;k<sizePatAdmVO;k++)
																						 		{
																						 			Inv_PatientAdmissionDtlVO voPatAdm=lstPatAdmissionVO.get(k);
																						 			patCrNo=voPatAdm.getPatcrno();
																									%>
																									<table width="100%">
							<tr>
								<td class="tdfonthead" width="25%">
									<div align="right"><bean:message key="AdmittedDepartmentName"/> </div>
								</td>
								<td class="tdfont" width="25%">
									<div align="left">
										<%=voPatAdm.getAdmitteddepartmentname()%>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="right"><bean:message key="WardName"/></div>
								</td>
								<td class="tdfont" width="25%">
									<div align="left">
										<%=voPatAdm.getPatwardname()%>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" width="25%">
									<div align="right"><bean:message key="RoomName"/></div>
								</td>
								<td class="tdfont" width="25%">
									<div align="left">
										<%=voPatAdm.getPatroomname()%>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="right"><bean:message key="BedName"/></div>
								</td>
								<td class="tdfont" width="25%">
									<div align="left">
										<%=voPatAdm.getBedname()%>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" width="25%">
									<div align="right"><bean:message key="ConsultantName"/></div>
								</td>
								<td class="tdfont" width="25%">
									<div align="left">
										<%=voPatAdm.getConsultantName()%>
									</div>
								</td>
								<td class="tdfonthead" width="25%">
									<div align="right"><bean:message key="Diagnosis"/></div>
								</td>
								<td class="tdfont" width="25%">
									<div align="left">
										<%=voPatAdm.getDiagnosis()%>
									</div>
								</td>
							</tr>
						</table>
						<%
							}
																						 	}
						%>

					</his:ContentTag>
					 

					<%
					/* 	if(deskType.equals("12") || deskType.equals("3"))
								{ */
					%>

					<div>
						<his:SubTitleTag name="Advised Details"></
 		                             </his:SubTitleTag>
						<fieldset
							style="border: solid 3px blue; background-color: CCE6FF;">
							<table width="100%">
								<tr>
									<td class="tdfonthead" width="20%">
										<div align="right">
											<b><bean:message key="Advisedby" /></b>
										</div>
									</td>
									<td class="tdfont" width="20%">
										<div align="left" class="ui-widget">
											<input type="text" id="automplete-4"
												name="advisedBy" size="30"
												onkeypress="if(event.keyCode==13) checkAdvise();return validateAlphaNumericOnly(event,this);clearTestAndGroup();"
												onblur="checkAdvise();">
										</div> <%
 	/* } */
					 %>
 		
									</td>
								</tr>
							</table>
						</fieldset>
					</div>
				</logic:present>
			</logic:equal>


			<!-- OPD/Emergency Logic -->
			<div id="container">



				<div id="blanket" style="display: none;"></div>
				<div id="popUpDiv" style="display: none;">
					<table id="addMoreValue" width="100%">
						<tr>
						</tr>

						<html:hidden name="invRaisingCumSamCollectionFB"
							property="mandComboTextBoxComboNamesOnPage" />

						<html:hidden name="invRaisingCumSamCollectionFB"
							property="finalMandCode" />
					</table>
					<!-- <center> -->
					<img src='/HISInvestigationG5/hisglobal/images/ok.gif' id='minusButton'
						onclick="popupIn('popUpDiv')"'>

					<!-- 	</center> -->
				</div>
				<!-- end #mainContent -->
			</div>
			<logic:notEqual name="invRaisingCumSamCollectionFB"
				property="patStatus" value="IPD">
				<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">



										
					<his:SubTitleTag name="Episode Details">
						<%
							String showDetail="showPatDetails()";
																							 String hideDetail="hidePatDetails()";
						%>
						<img class="button" title="Show Patinet Details"
							src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show"
							tabindex="1" onclick="<%=showDetail%>">
						<img class="button" title="Hide Patient Details"
							src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide"
							style="display:none;" tabindex="1" onclick="<%=hideDetail%>">

					</his:SubTitleTag>

					<his:ContentTag>
						<table width="100%">
							<tr>
								<td class="tdfont" width="5%">
									<div>
										<b><bean:message key="Select"/></b>
									</div>
								</td>
								<td class="tdfont" width="25%"><b> <bean:message key="VisitDate"/></b></td>
								<td class="tdfont" width="25%"><b>  <bean:message key="Department/Unit"/></b></td>
								<td class="tdfont" width="25%"><b> <bean:message key="Diagnosis"/></b></td>
							</tr>
						</table>
						<%
							boolean isEpisodeChecked=true;
																						     if(InvestigationConfig.IS_EPISODE_BOUND.equals(InvestigationConfig.IS_EPISODE_BOUND_FALSE))
																						    	 isEpisodeChecked=false;
																						     
																						  	 List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
																						  	 
																						 	if(lstPatEpisodeVO!=null && lstPatEpisodeVO.size()>0 )
																						 	{
																						 		boolean firstTimeTravesa=true;
																						 		if(lstPatEpisodeVO.size()==1)
																						 		{
																						 			%>
																						 			
																						 			<script type="text/javascript">
																						 			
																						 			document.getElementById("show").style.display="none";
																						 			
																						 			
																						 			</script>
																						 			
																						 			<%
																						 			
																						 		}
																						 		int sizePatEpisodeVO=lstPatEpisodeVO.size();
																						 		for(int k=0;k<sizePatEpisodeVO;k++)
																						 		{
																						 			Inv_EpisodeVO voPatEpisode=lstPatEpisodeVO.get(k);
																						 			patCrNo=voPatEpisode.getPatCRNo();
																						 		
																						 			if(firstTimeTravesa)
																						 			{
						%>
						<table width="100%">
							<tr>
								<td class="tdfont" width="5%">
									<div>
										<input type="radio" checked="checked" name="radioEpisode"
											value="<%=voPatEpisode.getPatepisodecode()%>"
											onclick="showRequisition()" />
									</div>
								</td>

								<td class="tdfont" width="25%">
									<div>
										<%=voPatEpisode.getPatvisitdate()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div>
										<%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit()%>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div>
										<%=voPatEpisode.getDiagnosis()%>
									</div>
								</td>
							</tr>
						</table>
						<%
							}
																							   
																								if(!firstTimeTravesa)
																								{
						%>

						<div id="showhide" style="display: none;">
							<table width="100%">
								<tr>
									<td class="tdfont" width="5%">
										<div>
											<input type="radio" name="radioEpisode"
												value="<%=voPatEpisode.getPatepisodecode()%>"
												onclick="showRequisition()" <%if(isEpisodeChecked){%>
												checked="checked" <%}%> />
										</div>
									</td>

									<td class="tdfont" width="25%">
										<div>
											<%=voPatEpisode.getPatvisitdate()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div>
											<%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit()%>
										</div>
									</td>
									<td class="tdfont" width="25%">
										<div>
											<%=voPatEpisode.getDiagnosis()%>
										</div>
									</td>
								</tr>
							</table>

						</div>

						<%
							}	firstTimeTravesa=false;
																							isEpisodeChecked=false; // for only one selection
																						 		}
																						 	}
						%>



					</his:ContentTag>

					<%
					/* 	if(deskType.equals("12") || deskType.equals("3"))
								{ */
					%>

					<div>
						<his:SubTitleTag name="Advised Details"></
 		                             </his:SubTitleTag>
						<fieldset
							style="border: solid 3px blue; background-color: CCE6FF;">
							<table width="100%">
								<tr>
									<td class="tdfonthead" width="20%">
										<div align="right">
											<b><bean:message key="Advisedby" /></b>
										</div>
									</td>
									<td class="tdfont" width="20%">
										<div align="left" class="ui-widget">
											<input type="text"  id="automplete-4"
												name="advisedBy" size="30"
												onkeypress="if(event.keyCode==13) checkAdvise();return validateAlphaNumericOnly(event,this);clearTestAndGroup();"
												onblur="checkAdvise();">
										</div> <%
 	/* } */
 			 %>
 			
									</td>
								</tr>
							</table>
						</fieldset>
					</div>
				</logic:present>
			</logic:notEqual>
			<%
				String hide="display:none";
								  	String display="display:block";
								  	String bookMarkDisplay="";
								  	String searchLogicDisplay="";
								  	String checked="checked='checked'";
								  	String bookMarkChecked="";
								  	String searchLogicChecked="";
								     String isBookMark=((invRaisingCumSamCollectionFB)request.getAttribute("invRaisingCumSamCollectionFB")).getIsBookMark();
									  if(isBookMark!=null&&!isBookMark.equals(""))
									  {
										  if(isBookMark.equals("B"))
										  {
									  bookMarkDisplay=display;
									  searchLogicDisplay=hide;
									  bookMarkChecked=checked;
										  }
										  else
										  {
									  bookMarkDisplay=hide;
									  searchLogicDisplay=display;
									  searchLogicChecked=checked;
										  }
									  }
			%>
			<his:ContentTag>
				<table width="100%" id="widthTable">
					<tr>
						<td   width="100%"><logic:present
								name="<%=InvestigationConfig.LIST_EPISODE_VO%>">
								<div id="tabs" align="left"  >
									<ul   >
										<li  ><a href="#tabs-1"  id="currentReqDtl" onclick="showCurrDetail();">  <img  	src='<his:path src="/hisglobal/images/goldenapple.jpg"/>' style="cursor: pointer">  Current Requisition Details  <%-- <bean:message key="CurrentRequisitionDetails"/> --%> </a></li>
										<li><a href="#tabs-2" id="prvReqDtl"  onclick="showPrvDetail();" >  <img  	src='<his:path src="/hisglobal/images/goldenapple.jpg"/>' style="cursor: pointer">  Previous Requisitons<%-- <b><bean:message key="PreviousRequisitons"/></b> --%></a></li>
										 
									</ul>
									<div id="tabs-1">

										<div id="bookMarkDiv" class="scroll_div" align="left"<%-- style=<%=bookMarkDisplay %> --%> >
											 <his:SubTitleTag name="BookMark Details"></
 		                             </his:SubTitleTag>
											<table width="100%" border="1">
												<tr align="left">

													<%
														Map<String,Map<String,List<String>>> mpBookMark=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_BOOK_MARK);
																																																																		  int countColour=1;
																																																																		  //Adding Colors for BookMark
																																																																		  List lstColors=new ArrayList();
																																																																				  lstColors.add("red");
																																																																				  lstColors.add("aqua");
																																																																				  lstColors.add("teal");
																																																																				  lstColors.add("silver");
																																																																				  lstColors.add("silver");
																																																																				  lstColors.add("white");
																																																																				  lstColors.add("blue");
																																																																				  lstColors.add("red");
																																																																				  lstColors.add("green");
																																																																				  lstColors.add("aqua");
																																																																				  lstColors.add("teal");
																																																																		  
																																																																		  if(mpBookMark!=null)
																																																																		  {
																																																																			 
																																																																			  Iterator itrBookMark=mpBookMark.keySet().iterator();
																																																																			  while(itrBookMark.hasNext())
																																																																			  {
																																																																				  String bkCodeHashName=(String)itrBookMark.next();
																																																																				  String bookMarkCode=bkCodeHashName.split("#")[0];
																																																																				  String bookMarkName=bkCodeHashName.split("#")[1];
													%>

													<td class="bookMark<%=countColour%>" width="10%"
														valign="left" align="left"
														style="width: 10px; height: 70px;">
														<div align="left"
															onclick="submitFormForBookMark('<%=bookMarkCode%>','SEARCHBOOKMARK')"
															style="border-color: red; width: 8pxheight:8px; cursor: pointer;">
															<%=bookMarkName%>
														</div>
													</td>

													<%
														Map<String,List<String>> mpLab=mpBookMark.get(bkCodeHashName);
																																																																				  Iterator itrLab=mpLab.keySet().iterator();
																																																																				  while(itrLab.hasNext())
																																																																				  {
																																																																					  String labCode=(String)itrLab.next();
																																																																					  List<String> lstTestCodes=mpLab.get(labCode);
																																																																					  int sizeTestCodes=lstTestCodes.size();
																																																																					  for(int i=0;i<sizeTestCodes;i++)
																																																																					  {
																																																																						  //Code To be added in future as per further requirement 
																																																																					  }
																																																																				  }
																																																																				  countColour++; // For Random Color generation as given in the list
																																																																			      if(countColour==10)
																																																																			    	  countColour=1;
																																																																			  }
																																																																		  }else{
													%>

													<td><bean:message key="NoBookMarkDataIsAvailable" /></td>

													<%
														}
													%>
												</tr>
											</table>
										</div>
										<div id="selLabTest">
											<%
												// Logic ofor persisting the selected Lab Test details even after page Submit
																																																					 	   String labTestArrayString=(String)((invRaisingCumSamCollectionFB)request.getAttribute("invRaisingCumSamCollectionFB")).getLabTestCodeArray();
																																																					 	   if(labTestArrayString!=null&&!labTestArrayString.equals(""))
																																																					 	   {
											%>
											<his:SubTitleTag name="Selected Lab/Test Details"></
 		 </his:SubTitleTag>
											<table width="100%" id="tableSelectedLabTestIdHide"
												cellpadding="0">
												<tr>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Test" /></b>
														</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Laboratory" /></b>
														</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Sample" /></b>
														</div>
													</td>
													<%-- <td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Mandatory" /></b>
														</div>
													</td> --%>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="Appointment" /></b>
														</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Priority" /></b>
														</div>
													</td>
													
													<td id="colorCycle" width="10%">
														<div align="left">
														Sample no
														</div>
													</td>

													<td id="colorCycle" width="10%">
														<div align="left">Volume</div>
													</td>


													<td id="colorCycle" width="10%">
														<div align="left">UOM</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">Container</div>
													</td>
													<td id="colorCycle" width="5%">
														<div align='left'></div>
													</td>
												</tr>
											</table>
											<%
												int j=0;

																																													 	   	//labTestArray=chkVal1@chkVal2..... and chkVal1=labCode#labName#testCode#testName#sampleComboString#testType#isAppointment#isConfidential#sampleCode#priority
																																													 	   String[] labTestArray=labTestArrayString.split("@");
																																													 	   	Set setLabTestCode=new HashSet();
																																													 	   for(String labTestSelected:labTestArray)
																																													 	   {
																																													 		   String[] labTestSelecetedArray=labTestSelected.split("#");
																																													 		   String labCode=labTestSelecetedArray[0];
																																													 		  	String labName=labTestSelecetedArray[1];
																																													 		 	String testCode=labTestSelecetedArray[2];
																																													 			String testName=labTestSelecetedArray[3];
																																													 			String sampleComboStr=labTestSelecetedArray[4];
																																													 			String testType=labTestSelecetedArray[5];
																																													 			String isAppointment=labTestSelecetedArray[6];
																																													 			String isConfidential=labTestSelecetedArray[7];
																																													 			String sampleCode=labTestSelecetedArray[8];
																																													 			String priority=labTestSelecetedArray[9];
																																													 			String testGrroupCode=labTestSelecetedArray[10];
																																													 			String testGrroupType=labTestSelecetedArray[11];
																																													 			String isMandatoryReq=labTestSelecetedArray[12];
																																													 			String bookMarkCode=labTestSelecetedArray[13];
																																													 		 	String offlineAppoitmentDate=labTestSelecetedArray[14];
																																													 		 	
																																													 		 	String mandTextBoxCombo=labTestSelecetedArray[15];
																																													 		 	
																																													 		 	String mandTextBoxComboNames=labTestSelecetedArray[16];
																																													 		 	
																																													 		 	
																																													 		 	String finalMandCode=labTestSelecetedArray[17];
																																													 		 	String uomComboStr=labTestSelecetedArray[18];
																																													 		 	String containerComboStr=labTestSelecetedArray[19];
																																													 		 	String defaultContainerVol=labTestSelecetedArray[20];
																																													 		 	String sampleValues=labTestSelecetedArray[21];
																																													 		 	String sampnoconfig=labTestSelecetedArray[22];
																																													 		 	 String testGroupCode=labTestSelecetedArray[23];
																																														 		 	
																																														 		 	
																																														 		 	String offlineAptNo=labTestSelecetedArray[24];
																																														 		 	
																																													 		 	 String instructionpat=labTestSelecetedArray[28]=="Patient$"?"NA":labTestSelecetedArray[28]; 
																																														 		 	instructionpat=instructionpat.replace("\r\n","<br>");
																																																 	  	 System.out.println("coup"+instructionpat);
																							
																																														 		 	
																																																 	  	 String instructioncoll=labTestSelecetedArray[29]=="Sample Collection$"?"NA":labTestSelecetedArray[29]; 
																																																 	  	instructioncoll=instructioncoll.replace("\r\n","<br>");
																																																	 	  	 System.out.println("coup"+instructioncoll);
																								
																																																	 	  	 String instructiontech=labTestSelecetedArray[30]=="Technician$"?"NA":labTestSelecetedArray[30]; 
																																																	 	  	instructiontech=instructiontech.replace("\r\n","<br>");
																																																		 	  	 System.out.println("coup"+instructiontech);
																									 
																																														 		 	
																																														 		 	if(!setLabTestCode.contains(labCode+"#"+testCode))
																																													 			{
																																													 			
																																														 			
																																													 			String _paraId=labCode+"^"+testCode+"^0^0^0^0^0";
																																													 			String paraNum=labCode+"^"+testCode+"^0^0^0^0^0";
											%>
											<%
												String onLoadRow="getAptSlotDetails('"+patCrNo+"','"+paraNum+"','','aptTagRow_"+labCode+"_"+testCode+"','2')";
																													 
																																																						 	   
																																																						 	   System.out.println("----onLoadRow------"+onLoadRow);
																																																						 	 // getAptSlotDetails(patCrNo,paraId,'',divAptTagRow);
																																																						 	       // String onLoadRow1="getAptSlotDetails("+patCrNo+","+paraId+",'','aptTagRow')"; 
																																																						 	   String call="createtable();";
											                                           if(isAppointment.equals("1")){
											%>
											<script>
							 	   <%=onLoadRow%>
						        </script>
											<%
												}
											%>
											<table width="100%" cellspacing="1" cellpadding="0">
												<tr>
													<td width="10%" class="tdfonthead">
														<div align="left">
															<%=testName%>
															<input type="hidden" name='labCode' value='<%=labCode%>'>
														</div>
													</td>
													<td width="10%" class="tdfonthead">
														<div align="left" id="checkOnSave">
															<%=labName%>
															<input type="hidden" name='testCode'
																value='<%=testCode%>'>
														</div>
													</td>
													<td width="10%" class="tdfonthead">
														<div align="left">
															<%
																if (testType.equals("P")) {
															%>
															NA
															<%
																} else {
																																					String onChangeSample = "setSampleCodeUsingAjax(this,'"
																																							+ labCode
																																							+ "','"
																																							+ testCode
																																							+ "','"
																																							+j+j+j
																																							+ "')";
															%>
															<select name='sampleInfo' tabindex='1'
																onChange="<%=onChangeSample%>">
																<%=sampleComboStr%>
															</select>
															<%
																}
															%>
														</div>
													</td>
													<%-- <td width="10%" class="tdfonthead">
														<%
															if (isMandatoryReq.equals("1")) {
														%> <html:hidden name="invRaisingCumSamCollectionFB"
															value="<%=finalMandCode%>"
															property="finalMandCodeValuesOnBookmark" /> <%
 	String[] splitedComboTextBoxValue=mandTextBoxCombo.split("&");
 												
 												int combosize;
 												
 									for(combosize=0;combosize<splitedComboTextBoxValue.length;combosize++)
 										{
 												if(combosize>0)
 												{
 													
 													String val=splitedComboTextBoxValue[combosize];
 													
 													//String setAddRowTable="load('"+val+"')";
 													 //System.out.println("----setAddRowTable------"+setAddRowTable);
 														String setAddRowTable="AddRowToTableAddMoreValue('"+splitedComboTextBoxValue[combosize]+"','"+mandTextBoxComboNames+"')";
 %> <script>
															
															
														 	   <%=setAddRowTable%>
													        </script> <%
 	} else {
 %>
														<div align="left"><%=splitedComboTextBoxValue[combosize]%></div>
														<%
															}

																							}
														%> <%
 	if (combosize > 0) {
 %> <img
														src='/HISInvestigationG5/hisglobal/images/addMore.jpg'
														onclick="popup('popUpDiv')"'> <%
 	}
 %> <%
 	} else {
 %>
														<div align="left">
															<bean:message key="NA" />
														</div> <%
 	}
 %>
													</td> --%>

													<%
														if (!offlineAppoitmentDate.equals("")
																							&& !offlineAppoitmentDate
																									.equals("null"))

																					{
													%>
													<td class="tdfonthead">
														<div align="left"><%=offlineAppoitmentDate%>

															<html:hidden name="invRaisingCumSamCollectionFB"
																value="<%=offlineAppoitmentDate%>"
																property="offlineAppoitmentDtl" />

														</div>
													</td>

													<%
														} else {
													%>
													<td width="15%" class="tdfonthead">
														<%
															if (isAppointment.equals("1")) {
														%>

														<div align="left"
															id="aptTagRow_<%=labCode%>_<%=testCode%>" a></div> <%--labCode+testCode--%>

														<%
															String setDate = "setDateInApoitment('"
																										+ patCrNo
																										+ "','"
																										+ paraNum
																										+ "','this','aptTagRow_"
																										+ labCode
																										+ "_"
																										+ testCode + "')";
														%> <input type="hidden" name="dateTag"
														onchange="<%=setDate%>" id="datepicker">
														</p> <%
 	} else {
 %>
														<div align="left">
															<bean:message key="NA" />
														</div> <%
 	}
 %>
													</td>

													<%
														}
													%>
													<td width="10%" class="tdfonthead">
														<div align="left">
															<%
																String onChangePriority = "setPriorityUsingAjax(this,'"
																									+ labCode
																									+ "','"
																									+ testCode
																									+ "')";
															%>
															<select name='priority' tabindex='1'
																onChange="<%=onChangePriority%>">
																<option
																	value="<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_NORMAL%>"><bean:message
																		key="Normal" /></option>
																<option
																	value="<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_URGENT%>"><bean:message
																		key="Urgent" /></option>
																<option
																	value="<%=InvestigationConfig.INVESTIGATION_RAISING_PRIORITY_CONFIDENTIAL%>"><bean:message
																		key="Confidential" /></option>
															</select>
														</div>
													</td>

<%if(sampnoconfig.equals("1")||sampnoconfig.equals("2"))
		{%>
	<td width="10%" class="tdfonthead">
														<div align="left">
<input type="hidden"  name="tempSampleNo" readonly="true" size="6" value="<%=sampnoconfig%>"  tabindex="1" />
		</div>
													</td><%}

else
{%>											
													
														<td width="10%" class="tdfonthead">
														<div align="left">

															<html:text name="invRaisingCumSamCollectionFB"
																property="tempSampleNo" size="6" onblur="chkSampleNoDuplicacyThroughAjax(this);" tabindex="1"></html:text>
														</div>
													</td>
													<%} %>
													
													<td width="10%" class="tdfonthead">
														<div align="left" id='<%="volumedivbook"+j+j+j%>'>

															<html:text name="invRaisingCumSamCollectionFB"
																property="containerVolume" value="<%=defaultContainerVol%>" size="6" tabindex="1"></html:text>
														</div>
													</td>


													<td width="10%" class="tdfonthead">
														<div align="left" id='<%="uomdivbook"+j+j+j%>'>
															<select name='uomCode' tabindex='1'
																>
																<%=uomComboStr%>
															</select>




															
														</div>
													</td>



													<td width="10%" class="tdfonthead">
														<div align="left" id='<%="containerdivbook"+j+j+j%>'>
															<select name='containerCode' tabindex='1'
																>
																<%=containerComboStr%>
															</select>




															
														</div>
													</td>






													<td width="4%" class="tdfonthead">
														<div align='left'>
															<%
																String strdelFunction = "deleteRow('"
																									+ j + "','" + labCode + "','"
																									+ testCode + "')";
															System.out.println("klo"+instructionpat);
															%>
															<img src='/HISInvestigationG5/hisglobal/images/minus.gif'
																id='minusButton' onClick="<%=strdelFunction%>">
																
															  <%if((instructionpat.equals("Patient$") && instructiontech.equals("Technician$") && instructioncoll.equals("Sample Collection$"))){ %>
												 
															<%} else{%>
																
																<img  class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>' id="<%=j%>showInstruction"      tabindex="1" onclick ="showInstructions5('<%=instructionpat%>','<%=instructioncoll%>','<%=instructiontech%>');">
															<%} %>	 
																<!-- //dddddd -->
																 
																		
																
														</div>
													</td>
												</tr>
												<%
													j++;
																				setLabTestCode.add(labCode + "#"
																						+ testCode);
																			}
																		}
												%>

											</table>
											<%
												} else {
											%>
											<div id="tableSelectedLabTestId" style="display: none;">
	<his:SubTitleTag name="Selected Test/Lab Details"></
</his:SubTitleTag>
	<table width="100%" id="tableSelectedLabTestIdHide" cellspacing="1" cellpadding="0">
												<tr>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Test" /></b>
														</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Laboratory" /></b>
														</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Sample" /></b>
														</div>
													</td>
												<%-- 	<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Mandatory" /></b>
														</div>
													</td> --%>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="Appointment" /></b>
														</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">
															<b><bean:message key="Priority" /></b>
														</div>
													</td>
													
													<td id="colorCycle" width="10%">
														<div align="left">
															Sample no
														</div>
													</td>

													<td id="colorCycle" width="10%">
														<div align="left">Volume</div>
													</td>


													<td id="colorCycle" width="10%">
														<div align="left">UOM</div>
													</td>
													<td id="colorCycle" width="10%">
														<div align="left">Container</div>
													</td>
													<td id="colorCycle" width="5%" >
														<div align='left'></div>
													</td>
												</tr>
											</table>
											</div>
										
											<%
												}
											%>

										</div>

										<div id="searchLabTestDiv" class="subDivStyle" style="display:none;"<%--style=<%=searchLogicDisplay %> --%>  >
										
											<his:SubTitleTag name=""> 
											<div  align="left">
											Search Laboratory And Test -------------->
											 <span style="display:inline-block; width: 58%;"></span>
<!--         <input type="checkbox" name="hisswitch"  id="myhisswitch" value="myhisswitchTestLab" onclick="getTestWiseList('myhisswitchTestLab');" >
 -->    
               <a style="color: #ffffff;cursor: pointer;" onclick="getTestWiseList('myhisswitchTestLab');" > Search Code Wise </a>
       
    </div>
    
 
 		 </his:SubTitleTag>
										
										
										
									<%-- 	<his:SubTitleTag name="Search Test And Laboratory -------------->"> 
											<div class="hisswitch">
        <input type="checkbox" name="hisswitch" class="hisswitch-checkbox" id="myhisswitch" value="myhisswitchTestLab" onclick="getTestWiseList('myhisswitchTestLab');" checked>
        <label class="hisswitch-label" for="myhisswitch">
            <span class="hisswitch-inner"></span>
            <span class="hisswitch-switch"></span>
        </label>
    </div>
     --%>
    <!-- <div class="wrapper">
  <div class="toggle_radio">
    <input type="radio" class="toggle_option" id="first_toggle" name="toggle_option" value="testLab" onclick="checkValue(this)">
    <input type="radio"   class="toggle_option" id="second_toggle" name="toggle_option" value="testCode" onclick="checkValue(this)">
    <label for="first_toggle"  ><p>Test/Lab Wise</p></label>
    <label for="second_toggle"  ><p>Test Code Wise</p></label>
   
    <div class="toggle_option_slider">
    </div>
  </div> -->
 		<%--  </his:SubTitleTag> --%>
										 

											<fieldset
												style="border: solid 3px blue; background-color: CCE6FF;">
												<legend align="left"
													style="color: red; text-align: right; font-size: 1.5em; border: 1px solid blue;"></legend>
												<table width="100%">
													<tr>
														<td class="tdfonthead" width="20%">
															<div align="right">
																<b><bean:message key="Laboratory" /></b>
															</div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-1"
																	name="searchLab" size="30"
															onselect="searchLabWiseTest()" 	onchange="searchLabWiseTest()">
															</div>
														</td>
														<td class="tdfonthead" width="20%"><logic:equal
																name="invRaisingCumSamCollectionFB"
																property="isTestGroup"
																value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>">
																<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_NO%>">
																<b><bean:message key="Test" /></b>
																<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)" checked="checked"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>">
																<b><bean:message key="TestGroup" /></b>
															</logic:equal> <logic:notEqual name="invRaisingCumSamCollectionFB"
																property="isTestGroup"
																value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>">
																<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)" checked="checked"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_NO%>">
																<b><bean:message key="Test" /></b>
																<input type="radio" name="isTestGroup"
																	onclick="setTestORTestGroup(this)"
																	value="<%=InvestigationConfig.IS_TEST_GROUP_YES%>">
																<b><bean:message key="TestGroup" /></b>
															</logic:notEqual></td>
														<td class="tdfont" width="20%">
															<div align="left" id="divSearchTest" class="ui-widget">
																<input type="text" id="automplete-2"
																	name="searchTest" size="30"
																onselect="searchLabWiseTest()" 	onchange="searchLabWiseTest()"	>

															</div>
															<div id="divSearchTestGroup" class="ui-widget"
																style="display: none">
																<input type="text" id="automplete-3"
																	name="searchTestGroup" size="30"
																	onkeypress="return validateAlphaNumericOnly(event,this)"
																	onblur="showAddorGOButton(this)">
															</div>
														</td>
														<td width="20%">
															<div id="goButtonDiv" align="left">
																<img class="button" id="addTestButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/btn-add.png"
																	style="cursor: pointer; display: none"
																	onkeypress="searchTestGroup()"
																	onclick="searchTestGroup()"> <img class="button"
																	id="addGroupButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/btn-add.png"
																	style="cursor: pointer; display: none"
																	onkeypress="searchGroup()" onclick="searchGroup()">
																<img class="button" id="goButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/GoNew.png"
																	style="cursor: pointer;"
																	onkeypress="searchLabWiseTest()"
																	onclick="searchLabWiseTest()">
															</div>
														</td>
													</tr>

												</table>


											</fieldset>
											<logic:empty
												name="<%=InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS%>">


												<logic:present
													name="<%=InvestigationConfig.LIST_LAB_WISE_TEST_DTLS%>">

													<%
														//Pagination Logic

																				PaginationFB fbPage = new PaginationFB();
																				pageContext.setAttribute("fbPagination",
																						fbPage);
																				fbPage.setCurrentPage(((invRaisingCumSamCollectionFB) request
																						.getAttribute("invRaisingCumSamCollectionFB"))
																						.getCurrentPage());
																				fbPage.setObjArrName(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
																				fbPage.setAppendInTitle("List Of Laboratory and Test");
																				int maxRecord = 30;
																				fbPage.setMaxRecords(maxRecord);
													%>
													<his:PaginationTag name="fbPagination"></his:PaginationTag>
													<%--	<his:SubTitleTag name="Laboratory and Test">

												<%
												//	String showDetail = "showPatDetailsOnPagination()";
													//					String hideDetail = "hidePatDetailsOnPagination()";
												%>
												<img class="button" title="Show Laboratory and Test Details"
													src='<his:path src="/hisglobal/images/plusinv.png"/>'
													id="showOnPagination" tabindex="1"
													onclick="<%=showDetail%>">
												<img class="button" title="Hide Laboratory and Test Details"
													src='<his:path src="/hisglobal/images/Minus.png"/>'
													id="hideOnPagination" style="display: none;" tabindex="1"
													onclick="<%=hideDetail%>"> 
													</his:SubTitleTag> --%>



													<his:ContentTag>
														<div id="showhideOnPagination">
															<table width="100%">
																<tr>
																	<td class="tdfonthead">
																		<div align="left">
																			<b><bean:message key="Select" /></b>
																		</div>
																	</td>
																	<td class="tdfonthead">
																		<div align="left">
																			<b><bean:message key="Test" /></b>
																		</div>
																	</td>
																	<td class="tdfonthead">
																		<div align="left">
																			<b><bean:message key="Laboratory" /></b>
																		</div>
																	</td>
																</tr>
																<%
																	List<LabTestVO> lstLabTestVO = (List<LabTestVO>) session
																										.getAttribute(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
																
																//to highlight the tests already raised even after page load
																String alreadyRaisedArray=(String)((invRaisingCumSamCollectionFB)request.getAttribute("invRaisingCumSamCollectionFB")).getLabTestCodeArray();
																boolean alreadyRaised=false;
																					
																
																								int i, size = 0;
																								String chkVal = "";
																								String singleSample="-1";
																								String sampleName="";
																								String isTestGroup = ((invRaisingCumSamCollectionFB) request
																										.getAttribute("invRaisingCumSamCollectionFB"))
																										.getIsTestGroup();
																								if (lstLabTestVO != null
																										&& lstLabTestVO.size() > 0)
																									size = lstLabTestVO.size();
																								int startIndex = ((Integer) request
																										.getAttribute(PaginationTag.PAGINATION_START_INDEX))
																										.intValue();
																								int endIndex = ((Integer) request
																										.getAttribute(PaginationTag.PAGINATION_END_INDEX))
																										.intValue();
																								for (int j = startIndex; j <= endIndex; j++) {
																									//int i=j-startIndex;
																									if (j < size) {
																										LabTestVO voLabTest = lstLabTestVO
																												.get(j);
																										String testGroupCode = (isTestGroup == null || isTestGroup
																												.equals("0")) ? "0"
																												: voLabTest
																														.getTestGroupCode();
																										String testGroupType = (isTestGroup == null || isTestGroup
																												.equals("0")) ? "0"
																												: voLabTest
																														.getTestGroupType();
																										if(voLabTest.getSingleSample()!=null && voLabTest.getSingleSample().equals("")==false)
																											singleSample=voLabTest.getSingleSample();
																										if(voLabTest.getsName()!=null && voLabTest.getsName().equals("")==false)
																											sampleName=voLabTest.getsName();
																										// Please Notice the Order of Hashing value is fixed, should get the value any where in future in the same order
																										// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType      //Please Ensure the corresponding Changes(In UTIL file, java script) before changing this order
																										chkVal = voLabTest.getLabCode()
																												+ "#"
																												+ voLabTest
																														.getLabName()
																												+ "#"
																												+ voLabTest
																														.getTestCode()
																												+ "#"
																												+ voLabTest
																														.getTestName()
																												+ "#"
																												+ voLabTest
																														.getSampleComboStr()
																												+ "#"
																												+ voLabTest
																														.getTestType()
																												+ "#"
																												+ voLabTest
																														.getIsAppointment()
																												+ "#"
																												+ voLabTest
																														.getIsConfidential()
																												+ "#"
																												+ singleSample
																												+ "#"
																												+ "1"
																												+ "#"
																												+ testGroupCode
																												+ "#"
																												+ testGroupType
																												+ "#"
																												+ voLabTest
																														.getIsMandatoryReq()
																												+ "#"
																												+ voLabTest
																														.getBookMarkCode()
																												+ "#"
																												+ voLabTest
																														.getOfflineAppoitMentDate()
																												+ "#"
																												+ voLabTest
																														.getSetMandTextBoxCombo()
																												+ "#"
																												+ voLabTest
																														.getMandComboTextBoxComboNames()
																												+ "#"
																												+ voLabTest
																														.getMandCode()
																												+ "#"
																												+voLabTest.getUomComboStr()
																												+"#"
																												+voLabTest.getContainerComboStr()
																												+"#"
																												+voLabTest.getDefaultContainerVol()
																												+"#"
																												+voLabTest.getSampleValues()
																												+"#"
																												+voLabTest.getSampleNoConfig()
																												+"#"+voLabTest.getMandCode()+"#"+""+"#"+voLabTest.getHideAptNo()+"#"+voLabTest.getSampleAreaCode()+"#"+voLabTest.getsName()+"#"+voLabTest.getInstructionPat()+"#"+voLabTest.getInstructionColl()+"#"+voLabTest.getInstructionTech()+"#";
																										
																										
																										//to highlight the tests already raised even after page load
																										 if(alreadyRaisedArray!=null&&!alreadyRaisedArray.equals(""))
																										 {																									 
																											String[] raisedTests=alreadyRaisedArray.split("@");
																											
																											for (String raisedTest:raisedTests)
																											{
																												if((raisedTest.split("#")[0]+raisedTest.split("#")[2]).equals(voLabTest.getLabCode()+voLabTest.getTestCode()))
																												{
																													alreadyRaised=true;
																													break;																										
																												}
																												else
																													alreadyRaised=false;
																											}
																										 }
																										
																										
																										
																%>
																<tr>
																<%
															//checkbox already selected and color changed for already raised test on page load
															if(alreadyRaised)
																{
																%>
																<td class="tdfonthead" id="td1<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestCode()%>" checked="checked"
																			name="chkLabTest" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestList(this)" />
																	</div>
																</td>
																
																
																<td class="tdfonthead" id="td2<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<%=voLabTest.getTestName()%>
																	</div>
																</td>
																<td class="tdfonthead" id="td3<%=chkVal %>" style="background-color:#ff9999">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td>
																<%}
															//if not already raised normal display
															else
																{
																
																
																%>
																	<td class="tdfonthead" id="td1<%=chkVal %>">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest.getLabCode()
													+ voLabTest.getTestCode()%>"
																			name="chkLabTest" tabindex="1" value="<%=chkVal%>"
																			onclick="showSelectedLabTestList(this)" />
																	</div>
																</td>
																
																
																<td class="tdfonthead" id="td2<%=chkVal %>">
																	<div align="left">
																		<%=voLabTest.getTestName()%>
																	</div>
																</td>
																<td class="tdfonthead" id="td3<%=chkVal %>">
																	<div align="left">
																		<%=voLabTest.getLabName()%>
																	</div>
																</td>
																
																
																<%} %>
																</tr>
																<%
																	}
																								}
																%>
															</table>
														</div>
													</his:ContentTag>
												</logic:present>

											</logic:empty>
											<logic:present
												name="<%=InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS%>">

												<%
													//Pagination Logic

																		PaginationFB fbPage = new PaginationFB();
																		pageContext
																				.setAttribute("fbPagination", fbPage);
																		fbPage.setCurrentPage(((invRaisingCumSamCollectionFB) request
																				.getAttribute("invRaisingCumSamCollectionFB"))
																				.getCurrentPage());
																		fbPage.setObjArrName(InvestigationConfig.LIST_SINGLE_LAB_WISE_GROUP_DTLS);
																		fbPage.setAppendInTitle("List Of Laboratory and TestGroup");
																		int maxRecord = 30;
																		fbPage.setMaxRecords(maxRecord);
												%>
												<his:PaginationTag name="fbPagination"></his:PaginationTag>
												<%--	<his:SubTitleTag name="Laboratory and Test">

												<%
												//	String showDetail = "showPatDetailsOnPagination()";
													//					String hideDetail = "hidePatDetailsOnPagination()";
												%>
												<img class="button" title="Show Laboratory and Test Details"
													src='<his:path src="/hisglobal/images/plusinv.png"/>'
													id="showOnPagination" tabindex="1"
													onclick="<%=showDetail%>">
												<img class="button" title="Hide Laboratory and Test Details"
													src='<his:path src="/hisglobal/images/Minus.png"/>'
													id="hideOnPagination" style="display: none;" tabindex="1"
													onclick="<%=hideDetail%>"> 
													</his:SubTitleTag> --%>



												<his:ContentTag>
													<div id="showhideOnPagination">
														<table width="100%">
															<tr>
																<td class="tdfonthead">
																	<div align="left">
																		<b><bean:message key="Select" /></b>
																	</div>
																</td>
																<td class="tdfonthead">
																	<div align="left">
																		<b><bean:message key="Test" /></b>
																	</div>
																</td>
																<td class="tdfonthead">
																	<div align="left">
																		<b><bean:message key="Laboratory" /></b>
																	</div>
																</td>
															</tr>



															<%
																/*
																						 List<ResultEntryVO> lstPatVO=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
																						int i,size=0;
																						if(lstPatVO!=null && lstPatVO.size()>0 )
																						size=lstPatVO.size();
																						int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
																						int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
																						String grpCode="";
																						for(int j=startIndex;j<=endIndex;j++)
																						{
																						//int i=j-startIndex;
																						boolean firstTimeTravesall=true;
																						if(j<size)
																						{
																						ResultEntryVO voPat=lstPatVO.get(j);
																						String  chkVal=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode();
																						if(voPat.getGroupCode()!=null)
																						{   
																						
																						grpCode+=voPat.getRequisitionNo()+voPat.getGroupCode();
																						grpCode+='&';
																						String[] SplitGrpCode=grpCode.split("&");
																						int length=SplitGrpCode.length;
																						if(SplitGrpCode.length>1)
																						for(int x=0;x<SplitGrpCode.length;x++)
																						{
																						if(SplitGrpCode[x].equals(voPat.getRequisitionNo()+voPat.getGroupCode()))
																						{
																						firstTimeTravesall=false;
																						}
																						}
																						
																						
																						}
																						

																						if(firstTimeTravesall)
																						{
																						 */
																						List<LabTestVO> lstLabTestVO = (List<LabTestVO>) session
																								.getAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
																						int i, size = 0;
																						String chkVal = "";
																						String singleSample="-1";
																						String sampleName="";
																						String isTestGroup = ((invRaisingCumSamCollectionFB) request
																								.getAttribute("invRaisingCumSamCollectionFB"))
																								.getIsTestGroup();
																						if (lstLabTestVO != null
																								&& lstLabTestVO.size() > 0)
																							size = lstLabTestVO.size();
																						int startIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_START_INDEX))
																								.intValue();
																						int endIndex = ((Integer) request
																								.getAttribute(PaginationTag.PAGINATION_END_INDEX))
																								.intValue();
																						String grpCode = "";
																						String ChkTestGroupValue = "";
																						int j=0;
																						for (int l = startIndex; l <= endIndex+1 && j<size; j++) {
																							//int i=j-startIndex;

																							boolean firstTimeTravesall = true;
																							if (j < size) {
																								LabTestVO voLabTest = lstLabTestVO
																										.get(j);
																								String testGroupCode = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupCode();
																								String testGroupType = (isTestGroup == null || isTestGroup
																										.equals("0")) ? "0"
																										: voLabTest
																												.getTestGroupType();
																								if(voLabTest.getSingleSample()!=null && voLabTest.getSingleSample().equals("")==false)
																									singleSample=voLabTest.getSingleSample();
																								if(voLabTest.getsName()!=null && voLabTest.getsName().equals("")==false)
																									sampleName=voLabTest.getsName();
																								// Please Notice the Order of Hashing value is fixed, should get the value any where in future in the same order
																								// chkVal Order LabCode#LabName#TestCode#TestName#sampleComboStr#testType#isAppointment#isConfidential#sampleCode#priority#testGroupCode#testGroupType      //Please Ensure the corresponding Changes(In UTIL file, java script) before changing this order
																								chkVal = voLabTest.getLabCode()
																										+ "#"
																										+ voLabTest.getLabName()
																										+ "#"
																										+ voLabTest.getTestCode()
																										+ "#"
																										+ voLabTest.getTestName()
																										+ "#"
																										+ voLabTest
																												.getSampleComboStr()
																										+ "#"
																										+ voLabTest.getTestType()
																										+ "#"
																										+ voLabTest
																												.getIsAppointment()
																										+ "#"
																										+ voLabTest
																												.getIsConfidential()
																										+ "#"
																										+ singleSample
																										+ "#"
																										+ "1"
																										+ "#"
																										+ testGroupCode
																										+ "#"
																										+ testGroupType
																										+ "#"
																										+ voLabTest
																												.getIsMandatoryReq()
																										+ "#"
																										+ voLabTest
																												.getBookMarkCode()
																										+ "#"
																										+ voLabTest
																												.getOfflineAppoitMentDate()
																										+ "#"
																										+ voLabTest
																												.getSetMandTextBoxCombo()
																										+ "#"
																										+ voLabTest
																												.getMandComboTextBoxComboNames()
																										+ "#"
																										+ voLabTest.getMandCode()
																										+ "#"
																												+voLabTest.getUomComboStr()
																												+"#"
																												+voLabTest.getContainerComboStr()
																												+"#"
																												+voLabTest.getDefaultContainerVol()
																												+"#"
																												+voLabTest.getSampleValues()+"#"
																														+voLabTest.getSampleNoConfig()
																														+"#"+voLabTest.getMandCode()
																												+"#"+voLabTest.getTestGroupCode()+"#"+voLabTest.getHideAptNo()+"#"
																														+voLabTest.getSampleAreaCode()+"#"+voLabTest.getsName()+"#"+voLabTest.getInstructionPat()+"#"+voLabTest.getInstructionColl()+"#"+voLabTest.getInstructionTech()+"#"	;

																								if (voLabTest.getTestGroupCode() != null) {

																									grpCode += '&';
																									String[] SplitGrpCode = grpCode
																											.split("&");
																									int length = SplitGrpCode.length;
																									if (SplitGrpCode.length > 1)
																										for (int x = 0; x < SplitGrpCode.length; x++) {
																											if (SplitGrpCode[x]
																													.equals(voLabTest
																															.getLabCode()
																															+ voLabTest
																																	.getTestGroupCode())) {
																												firstTimeTravesall = false;
																											} else {
																												firstTimeTravesall = true;
																											}
																										}

																									grpCode += voLabTest
																											.getLabCode()
																											+ voLabTest
																													.getTestGroupCode();
																								}

																								if (firstTimeTravesall) {l++;
															%>
															<tr>
																<td class="tdfont" id="grptd1<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>">
																	<div align="left">
																		<input type="checkbox"
																			id="<%=voLabTest
														.getLabCode()
														+ voLabTest
																.getTestCode()%>"
																			name="chkLabTestGroup" tabindex="1"
																			value="<%=chkVal%>"
																			onclick="showSelectedLabTestGroupList(this)" />
																	</div>
																</td>
																<td class="tdfont" id="grptd2<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>">
																	<div align="left">
																		<%=voLabTest
														.getSearchTestGroup()%>
																	</div>
																</td>
																<td class="tdfont" id="grptd3<%=voLabTest.getLabCode()
													+ voLabTest.getTestGroupCode()%>">
																	<div align="left">
																	<%=voLabTest
														.getLabName()%>
																		
																	</div>
																</td>
															</tr>
															<%
																}

																								ChkTestGroupValue += chkVal;
																								ChkTestGroupValue += "@";
															%>
															<%
																}
																						}
															%>
															<html:hidden name="invRaisingCumSamCollectionFB"
																value="<%=ChkTestGroupValue%>"
																property="labtestGroupValues" />
														</table>
													</div>
												</his:ContentTag>
											</logic:present>
										</div>
										
										<div id="testCodeWiseSearchDiv"  class="subDivStyle"  >
                              <his:SubTitleTag	name=""> 
                                 <div align="left"> Search Code Wise -------------->
                                <span style="display:inline-block; width: 60%;"></span>
      <!--   <input type="checkbox" name="hisswitchTest"  id="myhisswitchTest" value="myhisswitchTest" onclick="getTestWiseList('myhisswitchTest');" > -->
        
       
        <a style="color: #ffffff;cursor: pointer;" onclick="getTestWiseList('myhisswitchTest');" >  Search Lab/Test Wise </a>
       
      
    </div>
    
    
    <!--  <div class="wrapper">
  <div class="toggle_radio">
    <input type="radio" class="toggle_option" id="first_toggle" name="toggle_option" value="testLab" onclick="checkValue(this)">
    <input type="radio"   class="toggle_option" id="second_toggle" name="toggle_option" value="testCode" onclick="checkValue(this)">
    <label for="first_toggle"  ><p>Test/Lab Wise</p></label>
    <label for="second_toggle"  ><p>Test Code Wise</p></label>
   
    <div class="toggle_option_slider">
    </div>
  </div> -->
 		                                  </his:SubTitleTag>
 		                                  
                            <%-- 
                                  <his:SubTitleTag	name="Search Test Code Wise -------------->"> 
                                  <div class="hisswitchTest">
        <input type="checkbox" name="hisswitchTest" class="hisswitch-checkboxTest" id="myhisswitchTest" value="myhisswitchTest" onclick="getTestWiseList('myhisswitchTest');" checked>
        <label class="hisswitch-labelTest" for="myhisswitchTest">
            <span class="hisswitch-innerTest"></span>
            <span class="hisswitch-switchTest"></span>
        </label>
    </div>
     --%>
    
    <!--  <div class="wrapper">
  <div class="toggle_radio">
    <input type="radio" class="toggle_option" id="first_toggle" name="toggle_option" value="testLab" onclick="checkValue(this)">
    <input type="radio"   class="toggle_option" id="second_toggle" name="toggle_option" value="testCode" onclick="checkValue(this)">
    <label for="first_toggle"  ><p>Test/Lab Wise</p></label>
    <label for="second_toggle"  ><p>Test Code Wise</p></label>
   
    <div class="toggle_option_slider">
    </div>
  </div> -->
 		                            <%--       </his:SubTitleTag> --%>
 		                                  
 		                                  		 
												 
												<table width="100%">
													<tr>
														<td class="tdfonthead" width="20%">
															<div align="right"> <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						   </font> <b>Test Code</b></div>
														</td>
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-5"   name="searchLab"
																	size="30" 
																	onchange="setTestCodeWiseDetail();" onselect="setTestCodeWiseDetail();" >
                                                     <%--  <datalist id="cars" >
                                                    <%
                                                    String strValue=(String)session.getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE_FOR_COMBO);
                                                    
                                                  // 
                                                    String[] hh=strValue.split(",");
                                                    for(int chk=0;chk<hh.length;chk++)
                                                    {
                                                    %>
                                                    <option><%=hh[chk]%> </option>
                                                <%} %>
                                                </datalist> --%>
															</div>
														</td>
														 
														
														
															 
														 <td class="tdfonthead" width="20%">
															<div align="right"> <b><bean:message key="userGroupCodeWise"/></b></div>
														</td>
														
														<td class="tdfont" width="20%">
															<div align="left" class="ui-widget">
																<input type="text"  id="automplete-6"   name="searchLab"
																	size="30" 
																	onchange="setUserGroupWiseDetail();" onselect="setUserGroupWiseDetail();" >
                                                
															</div>
														</td>
														
														
														
														 
														<td width="20%">
														<!-- 	<div   align="left">
																<img class="button" id="goButton" tabindex="1"
																	src="/HISInvestigationG5/hisglobal/images/GoNew.png"
																	style="cursor: pointer;"
																	onclick="setTestCodeWiseDetail();" >
															</div> -->
														</td>
													</tr>

												</table>
	
	
											 
										</div>

									</div>
									
									
									
									
									
									<div id="tabs-2" >
										 <div class="subDivStyle">
										
											<his:SubTitleTag name="Test/Lab Details"></
 		                       
 		                             </his:SubTitleTag>
									
                 
											<%
												String prvTestArrayString = (String) ((invRaisingCumSamCollectionFB) request
																		.getAttribute("invRaisingCumSamCollectionFB"))
																		.getPrvTestDtl();
																if (prvTestArrayString != null
																		&& !prvTestArrayString.equals(""))
 
																	System.out.println("------------------>check"
																			+ prvTestArrayString);
											%>
											<table width="99%" cellspacing="1" cellpadding="0"  >
												<tr>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="Test"/></b>
														</div>
													</td>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="Laboratory"/></b>
														</div>
													</td>
													
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="TestType"/></b>
														</div>
													</td>
													<td id="colorCycle" width="15%">
														<div align="left">
															<b><bean:message key="SampleName"/></b>
														</div>
													</td>
													<td id="colorCycle" width="16%">
														<div align="left">
															<b><bean:message key="status"/></b>
														</div>
													</td>
													<td id="colorCycle" width="9%">
														<div align="left">
															<b><bean:message key="Priority"/></b>
														</div>
													</td>
													<td id="colorCycle" width="11%">
														<div align="left">
															<b><bean:message key="reqDate"/></b>
														</div>
													</td>
													<td id="colorCycle" width="2%">
														<div align="left">
															<b> </b>
														</div>
											
													</td>
												</tr>
											</table>
											 <div id='mycustomscroll2' class='flexcroll'>
											<table width="100%" cellspacing="1" cellpadding="0" id="setPrvTestDtl">
												<tr>
												
												</tr>
											</table>
											</div>
											 
											  </div>
											 
											 </div>
											 </div>
											 
											
							</logic:present>
							
						 </td>
						 
					</tr>
				
				</table>

			</his:ContentTag>
		</his:statusTransactionInProcess>


		<html:hidden name="invRaisingCumSamCollectionFB" property="hmode" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="patCrNo" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="currentPage" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="numberOfRow" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="selectedCheckbox" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="labCode" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="testCode" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="admissionDate" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="areaCode" />
		<html:hidden name="invRaisingCumSamCollectionFB"
			property="labTestCodeArray" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="bookMarkCode" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="patStatus" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="isBookMark" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="tmpLabCode" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="tmpTestCode" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="tmpPriority" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="tstValArr" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="tstGrpArr" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="tmpCrNo" />

		<html:hidden name="invRaisingCumSamCollectionFB" property="prvTestDtl" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="aptStatus" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="aptTestCode" />
		<html:hidden name="invRaisingCumSamCollectionFB" property="hideSaveButton" />
		<html:hidden name="invRaisingCumSamCollectionFB"
			property="offlineAppoitMentDate" />
		<html:hidden name="invRaisingCumSamCollectionFB"
			property="offlineAppoitmentDtl" />
			
				<html:hidden name="invRaisingCumSamCollectionFB"
			property="saveConfirmFlag" />
			
				<html:hidden name="invRaisingCumSamCollectionFB"
			property="barCodeGenSize" />
			
			

	


<html:hidden name="invRaisingCumSamCollectionFB" property="mandComboTextBoxComboNames" />


   <html:hidden name="invRaisingCumSamCollectionFB" property="finalMandValues" />
   
   <html:hidden name="invRaisingCumSamCollectionFB" property="tstOrTestGroupValue" />
    
    
  

		<html:hidden name="invRaisingCumSamCollectionFB"
			property="appointmentDate" />

		<html:hidden name="invRaisingCumSamCollectionFB"
			property="appointmentTime" />

<html:hidden name="invRaisingCumSamCollectionFB" property="appointmentRefNo" />
<html:hidden name="invRaisingCumSamCollectionFB"
			property="advisedByDoctorName" />
			
		<input type="hidden" id="hiddenid1" name="searchLabName">
		<input type="hidden" id="hiddenid2" name="searchTestName">
		<input type="hidden" id="hiddenid3" name="searchTestGroupName">
		<input type="hidden" id="hiddenid4" name="advisedByName">
		<input type="hidden" id="hiddenid5" name="testCodeWise">
		<input type="hidden" id="hiddenid6" name="userGroupCodeWise">
			 
			 
			 <html:hidden name="invRaisingCumSamCollectionFB"
			property="testCodeLabValue" />


<html:hidden name="invRaisingCumSamCollectionFB"
			property="delLabCode" />
			<html:hidden name="invRaisingCumSamCollectionFB"
			property="delTestCode" />

<html:hidden name="invRaisingCumSamCollectionFB" property="testLabTestCodeWise" />


<html:hidden name="invRaisingCumSamCollectionFB" property="dupLabCode" />


<html:hidden name="invRaisingCumSamCollectionFB" property="dupTestCode" />

<html:hidden name="invRaisingCumSamCollectionFB" property="appoitmentNo" />
<html:hidden name="invRaisingCumSamCollectionFB" property="hidAptNo" />



			
			<html:hidden name="invRaisingCumSamCollectionFB"
			property="tmpUomCode" /><html:hidden name="invRaisingCumSamCollectionFB"
			property="tmpContainerVolume" /><html:hidden name="invRaisingCumSamCollectionFB"
			property="tmpContainerCode" />

		<his:ButtonToolBarTag>

			<his:statusNew>
				<img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
					style="cursor: pointer"
					onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')"
					tabindex="1" onclick="submitToDesk('NEW','NEW')">
			</his:statusNew>

			<his:statusTransactionInProcess>
				<logic:equal name="invRaisingCumSamCollectionFB"
					property="aptStatus" value="0">
					<logic:present name="<%=InvestigationConfig.LIST_APTBASED_TEST%>">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-next.png"/>'
							id="nextDiv" style="cursor: pointer; display: none" tabindex="1"
							onclick="displayAptDetails();">
						<%-- 	<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');"> --%>
					</logic:present>
				</logic:equal>
				
					<logic:equal name="invRaisingCumSamCollectionFB" property="aptStatus"
					value="2">
					<logic:present name="<%=InvestigationConfig.LIST_APT_BY_DESK%>">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-next.png"/>'
							id="nextDiv1" style="cursor: pointer; display: none" tabindex="1"
							onclick="getAppointment();">
					
					</logic:present>
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">
				</logic:equal>
				
				<logic:equal name="invRaisingCumSamCollectionFB" property="aptStatus"
					value="5">
					<logic:present name="<%=InvestigationConfig.LIST_APT_DETAILS_REQ_DTL_DESK%>">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-sv.png"/>'
							id="savediv12" style="cursor: pointer;" tabindex="1"
							onclick="saveUpdatedAppointment();">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
							tabindex="1" style="cursor: pointer"
							onkeypress="if(event.keyCode==13) submitForm('NEW');"
							tabindex="1" onclick="submitForm('NEW');">
					</logic:present>
				</logic:equal>
				
				<logic:present name="<%=InvestigationConfig.LIST_EPISODE_VO%>">


					<img class="button" id="saveDiv"
						src='<his:path src="/hisglobal/images/btn-sv.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13&&validateSave()) submitFormToSave('SAVE');"
						tabindex="1" onclick="if(validateSave())submitFormToSave('SAVE');">
				 <img class="button" 
						src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1"
						onclick="submitForm('NEW');"> 

				</logic:present>

			</his:statusTransactionInProcess>

		</his:ButtonToolBarTag>


	 
 	 
				<div style="display: none" id="lengendStatus">
<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
			 
				<td width="1%" bgcolor="skyblue">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="RequistionRaised"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="silver">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="SampleCollected"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="#CC99FF">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="PackingListGenerated"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="litemaroon">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="Sample/PatientAccepted"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="aqua">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="ResultEntered"/>
					  
					</font>
				</td>		
				 			
			</tr>
			<tr>
		 
		 
				<td width="1%" bgcolor="purple">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="ResultValidated"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="fuchsia">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="PatientRejected"/>
					  
					</font>
				</td>		
				 
				<td width="1%" bgcolor="blue">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="TestRescheduled"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="olive">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="SampleCancelled"/>
					  
					</font>
				</td>	
				<td width="1%" bgcolor="lime">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="ReadyForReportPrinting"/>
					  
					</font>
				</td>					
			</tr>
			
			<tr>
		  
				
				<td width="1%" bgcolor="gold">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="reportPrinted"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="teal">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="TestCancelled"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="#EB7273">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="TestDeleted"/>
					  
					</font>
				</td>		
				<td width="1%" bgcolor="brown">
					 
				</td>
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="ReportGenerated"/>
					  
					</font>
				</td>	
				
				
				<td width="1%" bgcolor="#FFA500">
					 
				</td>
				
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="sentToMachine"/>
					  
					</font>
				</td>		
							
							
										
			</tr>
			
			
			<tr>
			
			<td width="1%" bgcolor="#FFA599">
					 
				</td>
				
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="receiveFromMachine"/>
					  
					</font>
				</td>		
				
				
				<td width="1%" bgcolor="#9999FF">
					 
				</td>
				
				<td width="19%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 
					  	 <bean:message key="testAdvised"/>
					  
					</font>
				</td>		
				
				
				
				</tr>
		</table>
	</his:ContentTag>
	</div>
	</div>
 

 

	</html:form>
<div id='flexcroll-init'></div> 
</div>
		<his:status />
</body>