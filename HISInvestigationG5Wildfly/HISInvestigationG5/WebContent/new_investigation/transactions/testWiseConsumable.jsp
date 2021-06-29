<!-- 
 
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: JATIN KUMAR
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : TEST WISE CONSUMABLE ACTION
 ## Purpose						        : 
 ## Date of Creation					: 06/09/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 

  -->
  <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.vo.template.TestWiseConsumableVO"%>

<%@page
	import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB"%>
	
<%@page import="new_investigation.vo.LabTestVO"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>

<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
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
							
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
 
<his:css src="/hisglobal/css/icon.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
 <his:css src="/hisglobal/css/Cannedstyle.css" />
 <link rel="stylesheet" href="/new_investigation/css/Date/site-demos.css"> 
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonUtility.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<his:javascript src="/bloodbank/js/bloodRequisition.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/ckeditor/ckeditor.js"/>
<his:javascript src="/new_investigation/js/wysiwyg.js"/>
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />


<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	 
	 
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>



<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.TestWiseConsumableFB"%>

 
 
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<%@page import="new_investigation.InvestigationConfig"%>

<html>
<style>
 .multiselect {
        width: 200px;
    }
    .selectBox {
        position: relative;
    }
    .selectBox select {
        width: 100%;
        font-weight: bold;
    }
    .overSelect {
        position: absolute;
        left: 0; right: 0; top: 0; bottom: 0;
    }
    #checkboxes {
        display: none;
        border: 1px #dadada solid;
        z-index:99;
        position:absolute;
        background-color: rgb(18, 112, 170);
        width:200px;
    }
    #checkboxes label {
        display: block;
    }
    #checkboxes label:hover 
    {
        background-color: #1e90ff;
     }
#blanket {
   background-color:#111;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:2px;
   left:0px;
   width:100%;
}

#popUpDiv5 {
	position:absolute;
	  
	background:#CCE6FF;
	width:400px;
	height:400px;
	border:5px solid #000;
	z-index: 9002;
}

#popUpDiv5 a {position:relative; top:1px; left:20px}

</style>

<script type="text/javascript">
function appendDeleteIndex(value)
{
	var delIndexElement=document.getElementsByName("deleteIndex")[0];
	var values=delIndexElement.value.split(",");
	var flag=false;
	for(i=0;i<values.length;i++)
		{
			if(values[i]==value)
				{
					flag=true;
					break;
				}
			
		}
	if(!flag)
		{
			delIndexElement.value=delIndexElement.value+","+value;
			
		}
}
function deleteDeleteIndex(value)
{
	var delIndexElement=document.getElementsByName("deleteIndex")[0];
	var values=delIndexElement.value.split(",");
	var flag=false;
	for(i=0;i<values.length;i++)
		{
			if(values[i]==value)
				{
					values[i]=-1;
					flag=true;
					break;
				}
		}
	if(flag)
		{
			delIndexElement.value=values.toString();
			
		}
}
function toggleTr(lotNo,obj,delIndex)
{
		if(obj.checked)
			{
				document.getElementById(lotNo+"#tr").style.display="";
				
				deleteDeleteIndex(delIndex);
			}
		else
			{
				document.getElementById(lotNo+"#tr").style.display="none";
				appendDeleteIndex(delIndex);
			}
	
}
function deleteOption(obj)
{
	//lot#itemName
	var values=obj.getAttribute("alt").split("#");
	var delIndexElement=document.getElementsByName("deleteIndex")[0];
	
	var lotNo=values[0];
	lotNoString="'"+lotNo+"'";
	var itemName=values[1];
	var delIndex=values[2];
	appendDeleteIndex(delIndex);
	var element=document.getElementById("checkboxes");
	var html="<label for="+lotNo+"><input id="+lotNo+" onclick=toggleTr('"+lotNo+"',this,"+delIndex+") type='checkbox' />"+itemName+"("+lotNo+")</label><br>";
	element.innerHTML=element.innerHTML+html;
	document.getElementById(lotNo+"#tr").style.display="none";
	
}
function selectAllChkBox(obj)
{
	if(obj.checked){
		var defaultItem=document.getElementsByName("defaultQuantity");	
	var selectedItem=document.getElementsByName("selectedItem");
	for(i=0;i<selectedItem.length;i++)
		{
			selectedItem[i].checked=true;
			defaultItem[i].readOnly=false;
		}
	var elements=document.getElementsByName('tempSelectedItem');
	var quantity=document.getElementsByName('defaultQuantity');
	var i=0;
	for(i=0;i<elements.length;i++)
	{
		if(!quantity[selectedItem.length+i].disabled){
	
			elements[i].checked=true;
		}
	}}
	else
	{
		var defaultItem=document.getElementsByName("defaultQuantity");
		var selectedItem=document.getElementsByName("selectedItem");
		for(i=0;i<selectedItem.length;i++)
			{
				selectedItem[i].checked=false;
				defaultItem[i].readOnly=true;
			}
		var elements=document.getElementsByName('tempSelectedItem');
		var quantity=document.getElementsByName('defaultQuantity');
		var i=0;
		for(i=0;i<elements.length;i++)
		{
			if(!quantity[i].disabled){
		
				elements[i].checked=false;
			}	
	}
	}
}
function validateSaveFinal(mode)
{
	var quantity=document.getElementsByName('defaultQuantity');
	var length=document.getElementsByName('defaultQuantity').length;
	var elements=document.getElementsByName('tempSelectedItem');
	var selectedItem=document.getElementsByName("selectedItem");
	var deleteIndex=document.getElementsByName("deleteIndex")[0].value.split(",");
	var flag=true;
	
	for(i=0;i<selectedItem.length;i++)
		{
			if(selectedItem[i].checked)
				{
					flag=false;
				}
			
		}
	for(i=0;i<elements.length;i++)
	{
		if(elements[i].checked)
			{
			flag=false;
			
			}
	
	
	}
	for(i=0;i<deleteIndex.length;i++)
		{
			if(deleteIndex[i]!=""&&deleteIndex[i]!="-1")
				{
				flag=false;
				}
		
		}
	if(flag)
		{
			alert("Please Select any item");
			return false;
		}
	for(i=0;i<selectedItem.length;i++)
		{
			if(selectedItem[i].checked)
				{
					if(quantity[i].value=="")
						{
							alert ("Please Enter Quantity");
							quantity[i].focus();
							return false;
						}
				}
			
		}
	
	for(i=0;i<elements.length;i++)
	{
		var elementLength=document.getElementsByName("selectedItem").length+i;
		//elementLength=elementLength+i;
		if(elements[i].checked)
			{
				if(!quantity[elementLength].disabled)
					{
						if(quantity[elementLength].value=="")
							{
							
						alert ("Please Enter Quantity");
						quantity[elementLength].focus();
						return false;
							}
					}
			}
		
	}
	submitPage(mode);
	
}
	
	
function showItems(obj)
{


	
	if(obj.checked)
		{
		 var r = confirm("Are You Sure to Add  Item");
			if(r==true)
				{
		obj.setAttribute("checked","true");
		var outerHTML=document.getElementById(obj.id+"#trTemp").outerHTML;
		document.getElementById(obj.id+"#trTemp").outerHTML=null;
		document.getElementById("contents").innerHTML=document.getElementById("contents").innerHTML+outerHTML;
			//document.getElementById(obj.id+"#tr").style.display="";
			document.getElementById(obj.id+"#quan").removeAttribute("disabled");
			document.getElementById(obj.id+"#quan").removeAttribute("value");
			document.getElementById(obj.id+"#lot").removeAttribute("disabled");
			document.getElementById(obj.id+"#trCheck").checked=true;
				}
			else
				{
				//alert(obj.id);
				document.getElementById(obj.id).checked=false;
				//document.getElementById(obj.id+"#trCheck").checked=false;
				//document.getElementById(obj.id+"#trCheck").removeAttribute("checked");
				}
		}
	else{
		obj.removeAttribute("checked");
		var outerHTML=document.getElementById(obj.id+"#trTemp").outerHTML;
		document.getElementById(obj.id+"#trTemp").outerHTML=null;
		document.getElementById("tempContents").innerHTML=document.getElementById("tempContents").innerHTML+outerHTML;
		//document.getElementById(obj.id+"#tr").style.display="none";
		
		document.getElementById(obj.id+"#quan").disabled="disabled";
		document.getElementById(obj.id+"#lot").disabled="disabled";
		document.getElementById(obj.id+"#trCheck").removeAttribute("checked");
	}
	
}
var expanded = false;
function showCheckboxes() {
	
    var checkboxes = document.getElementById("checkboxes");
    if (!expanded) {
        checkboxes.style.display = "block";
        expanded = true;
    } else {
        checkboxes.style.display = "none";
        expanded = false;
    }
}
function setValues()
{
	 var testName=document.getElementById("testName");
	var selectedChk=document.getElementsByName("selectedCheckbox")[0];
		testName.innerHTML=selectedChk.value.split("#")[6]
		testName.innerHTML=testName.innerHTML.substring(0,testName.innerHTML.length-1);
	 
	
	
	
}
function getValues()
{
		
}
 function assignValues(obj)
{
	var values=obj.value.split("#");
	var manufacture=values[1];
	var expiry=values[2];
	var otherItemId=values[3];
	document.getElementById(otherItemId+"#manuf").innerHTML=manufacture;
	document.getElementById(otherItemId+"#expiry").innerHTML=expiry;
	
} 
function validateSave(mode)
{
	
	var elements=document.getElementsByName("lotNo");
	for(var i=0;i<elements.length;i++)
		{
		if(elements[i].value!=""){
			
			var values=elements[i].value.split("#");
			var otherItemId=values[0];
		if(document.getElementById(otherItemId+"#quan").value=="")
			{
				alert('Please Enter Value of Default Quantity ');
				document.getElementById(otherItemId+"#quan").focus();
				return false;
			}
			
		}
			
		}
	submitPage(mode);
}

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function showInstructions5(inst)
{
	//alert(inst);
	
	deleteTableInst();
	if(inst=="NA" || inst=="null")
		addRowToTableInst("No Instruction Available");
		else
	addRowToTableInst(inst);
	
		
	
	
	popup("popUpDiv5");
	
	
	}
	
	
	function closeInstructions()
{
	popup("popUpDiv5");

	
	}
	
	
function addRowToTableInst(inst)
{
	
	//alert("d");
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
//	var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	
	
   
	tabRow.appendChild(td1); 
	//tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
}



var sizeee=0;
var counter=1;
function showBrowse(crno,reqno,sampleno,sampleno1)
{
	var finalsampleno=sampleno+""+sampleno1;
	var shwbrowse="shwbrowse"+finalsampleno;
	alert(sampleno);
	alert(sampleno1);
	alert( finalsampleno);

	var uploadshw="uploadshw"+finalsampleno;
	var addtabl="addtabl"+finalsampleno;
	alert(crno);
	document.getElementsByName('sizeOfFile').value=0;
	document.getElementById(shwbrowse).style.display='none';
	document.getElementById(uploadshw).style.display='';
	document.getElementById(addtabl).style.display='';
	}

 function addfile(crno,reqno,reqno1,sampleno,sampleno1)
{
	 
	 
	 //alert(sampleno);
	 //alert(sampleno1);
	// alert(reqno1)	
	 ///alert(aa1);
		
	 var finalsampleno=sampleno+""+sampleno1;
	 //alert(crno);
	// alert(reqno);
	 document.getElementById("divfile"+finalsampleno).style.display='';
	var table = document.getElementById("filetable"+finalsampleno);
	var table1 = document.getElementById("filerow"+finalsampleno);
	//alert(table);
/* if(document.getElementsByName("sizeOfFile").value==undefined)
	document.getElementsByName("sizeOfFile").value=0;
	
 */
 var sizee=document.getElementsByName("sizeOfFile").value;
 
//alert(sizee);
var row = table1.insertCell(counter);
var f=1;
var finalreqno=reqno+""+reqno1;
var crreqnoFile1=crno+"#"+finalreqno;

row.innerHTML = "<font color='#FF0000' size='4'>"+"File "+counter+":</font><input type='file' name='file["+sizeee+"]'><input type='hidden' name='crreqnoFile' value='"+crreqnoFile1+"'>";
counter++;

document.getElementById('hidetbl'+finalsampleno).style.display='';
document.getElementsByName("sizeOfFile").value++;
sizeee++;
}

 
 function hidefile(sampleno,sampleno1)
 {
//alert("hh");
var finalsampleno=sampleno+""+sampleno1;
if(counter>1)
	{
counter--;
	 document.getElementById("filerow"+finalsampleno).deleteCell(counter);
	 sizeee--;
	}
if(counter==1)
	{
	document.getElementById("divfile"+finalsampleno).style.display='none';
	document.getElementsByName("hideminus")[0].style.display='none';
	}
	 
 }	 
 
CKEDITOR.config.keystrokes = [];
setInterval(function() {
	///alert(document.getElementsByName("chkSamplePatient")[0].value); 
    
 }, 5000);
function labBased()
{
	 if(!validateTodayOrDate())
	 {return false;}
	if(document.getElementsByName!="-1")
	{
		
		document.getElementsByName('onLoadValue')[0].value="ONLOAD";
	document.getElementsByName('hmode')[0].value="GETDETAILS";
     document.forms[0].submit();
	
	}
	}


function hidePatDetails(k)
{
document.getElementById("showhide"+k).style.display="none";
document.getElementById("hide"+k).style.display="none";
document.getElementById("show"+k).style.display="";	
}
   
   
function onClickGO(hospitalCode)
{
	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     document.getElementsByName('onLoadValue')[0].value="";
     
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
        	  
        			  
              
              if(document.getElementsByName("labCode")[0].value=="-1")
    	      {
    	     	alert("Select Lab   Name ... ");
    		    document.getElementsByName("labCode")[0].focus();
    		    return false;
    	      }
	        // testWiseCode
 /* testGroupCode
 
 fromSampleNo
 fromLabNo
 toLabNo
 toSampleNo */
 var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); showOnLoad
 
 
 if(genTypeValue=="P")
	{
	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
     {
    	alert("Select Name  ");
	    document.getElementsByName("tempPatCRNo")[0].focus();
	    return false;
     }  
 
	}
if(genTypeValue=="T")
	{
	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
     {
    	alert("Select Test Name ");
	    document.getElementsByName("testWiseCode")[0].focus();
	    return false;
     }
 
	}
if(genTypeValue=="L")
	{
	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
     {
    	alert("Select From Lab No ");
	    document.getElementsByName("fromLabNo")[0].focus();
	    return false;
     }
 
	 if(document.getElementsByName("toLabNo")[0].value=="-1")
     {
    	alert("Select To Lab No ");
	    document.getElementsByName("toLabNo")[0].focus();
	    return false;
     }
	}
if(genTypeValue=="S")
	{
	  
	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
     {
    	alert("Select From Sample No  ");
	    document.getElementsByName("fromSampleNo")[0].focus();
	    return false;
     }

	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
     {
    	alert("Select To Sample No ");
	    document.getElementsByName("toSampleNo")[0].focus();
	    return false;
     }
	 
	}  


if(genTypeValue=="TG")
{
	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
     {
    	alert("Select Test Group  Name ");
	    document.getElementsByName("testGroupCodeWise")[0].focus();
	    return false;
     }
	 
}  //allPatient

              
        	        document.getElementsByName('hmode')[0].value="GETDETAILS";
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
					           if(document.getElementsByName("labCode")[0].value=="-1")
					       	{
					       		alert("Select Lab Name ");
					       		 
					       		document.getElementsByName("labCode")[0].focus();
					       		return false;
					      	}
					       	 
					           
					           var genTypeValue=document.getElementsByName('generationType')[0].value;
					       	//alert("onload"+genTypeValue); showOnLoad
					        
					        
					        if(genTypeValue=="P")
					       	{
					       	 if(document.getElementsByName("tempPatCRNo")[0].value=="-1")
					            {
					           	alert("Select Name ");
					       	    document.getElementsByName("tempPatCRNo")[0].focus();
					       	    return false;
					            }  
					        
					       	}
					       if(genTypeValue=="T")
					       	{
					       	 if(document.getElementsByName("testWiseCode")[0].value=="-1")
					            {
					           	alert("Select Test  Name ");
					       	    document.getElementsByName("testWiseCode")[0].focus();
					       	    return false;
					            }
					        
					       	}
					       if(genTypeValue=="L")
					       	{
					       	 if(document.getElementsByName("fromLabNo")[0].value=="-1")
					            {
					           	alert("Select From Lab No");
					       	    document.getElementsByName("fromLabNo")[0].focus();
					       	    return false;
					            }
					        
					       	 if(document.getElementsByName("toLabNo")[0].value=="-1")
					            {
					           	alert("Select To Lab No ");
					       	    document.getElementsByName("toLabNo")[0].focus();
					       	    return false;
					            }
					       	}
				    	       if(genTypeValue=="S")
					       	{
					       	  
					       	 if(document.getElementsByName("fromSampleNo")[0].value=="-1")
					            {
					           	alert("Select From Sample No");
					       	    document.getElementsByName("fromSampleNo")[0].focus();
					       	    return false;
					            }

					       	 if(document.getElementsByName("toSampleNo")[0].value=="-1")
					            {
					           	alert("Select To Sample No");
					       	    document.getElementsByName("toSampleNo")[0].focus();
					       	    return false;
					            }
					       	 
					       	}  


					       if(genTypeValue=="TG")
					       {
					       	 if(document.getElementsByName("testGroupCodeWise")[0].value=="-1")
					            {
					           	alert("Select Test Group  Name ");
					       	    document.getElementsByName("testGroupCodeWise")[0].focus();
					       	    return false;
					            }
					       	 
					       }  //allPatient
					           
					       	document.getElementsByName('hmode')[0].value="GETDETAILS";
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


function editRemarks(crno,reqno)

{
	
	alert(crno);
	alert(reqno);
	alert(document.getElementById(crno+"#"+reqno).value);
	
	
}



//call this function to generate auto complete lists in runtime by passing the value of object list, id of the input tag and hidden tag. puneet.
function generateAuto(objList,autoId,hiddenId)
{//alert("generate list");
	setInterval(function() {
	    $(autoId).autocomplete({
	    	source:objList,
	        select: function(event, ui) {
	        	$(hiddenId).val(ui.item.value); 
	            event.preventDefault();  
	            $(autoId).val(ui.item.label); 
	        },

	    focus: function(event, ui) { 
	           event.preventDefault(); 
	           $(autoId).val(ui.item.label);}
	     });
	    }, 1000);
	
	
	}

function displaySamplePatDetails()
{	
	var count=0;
	document.getElementsByName('isPatDetailPage')[0].value="1";
	
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
	//alert(concatenateChkBoxVal);
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	
	/*  var cannedVal=document.getElementsByName("cannedLabCode")[0].value;
	 var details=  getcannedDetail(cannedVal);
	 document.getElementsByName("cannedDetails")[0].value=details;
	 alert(details);
	 alert(document.getElementsByName("cannedDetails")[0].value); */
 	document.forms[0].submit();
 	
	}
function ValidateSameCrNo(obj)
{
	
	if(obj.checked)
	{
		document.getElementById('nextDiv').style.display="";
                
	}
  else
     	{
	  document.getElementById('gob').style.display="";
      	document.getElementById('cancel').style.display="";
      	
          }
	 
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
			    		 
			    	alert("please select same cr no");
			    	document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	return false;
			    	} 
				}
		}
	}
 
}
function displayButton(obj)
{
	
	if(obj.checked)
	{
		//document.getElementById('nextDiv').style.display="";
		displaySamplePatDetails();
                
	}
  else
     	{
	  document.getElementById('gob').style.display="";
      	document.getElementById('cancel').style.display="";
      	
          }
	 
	
	
	
 
}
function showPatDetails(k)
{
	//alert(k);
document.getElementById("showhide"+k).style.display="";
document.getElementById("hide"+k).style.display="";
document.getElementById("show"+k).style.display="none";	
	
}

function hidePatDetails(k)
{
	//alert(k);
document.getElementById("showhide"+k).style.display="none";
document.getElementById("hide"+k).style.display="none";
document.getElementById("show"+k).style.display="";	
}

function submitFor()
{
	document.getElementsByName('showStatus')[0].value='0';
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}
	
	
function cancelFunc()
{
	window.parent.closeTab();
}


function selectAll()
{	var cbs=document.getElementsByName('chkResultEntryPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onSave();
	}
function onSave()
{
	var count=0;
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkResultEntryPatient');
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	//alert(cbs.length);
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{
         
		//var values=document.getElementById(i+"chkBOx").value;
		//alert("The value is"+values);
		count++;	
	        var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;
        	//alert(checkBoxId);
        	// var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	 
        	 var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
        	 
        	 
        	// alert(splitTheCheckBoxId);
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {

             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
                 
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                   id=get_tags[k].id;
                   //alert("id for "+k+"    "+id);
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	  hidddentext="hidden";
              
                // alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
                 // 	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
                  //  alert("parameter value    "+parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                      if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              }
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";
                    
                    if(id.contains("auto"))    
                  	  {
                
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                  	 else
                  		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                  	  } 
                    else
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                          name+='#'+resultValidationTemplateValue+'#'+values;
                          
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                       
                   }
                          
                          
		          
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       {
        	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
     		//alert("The value is"+values);
        	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
       	   name=get_tags[j].name;
        	 
        	// alert("inside here");
        	//alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
            	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
	         	   {
				      alert("Enter the field Focussed");
				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
			          return false;
		           }
	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
              var multiValue="";
              var resultEntryTemplateValue;
	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
              for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
	        	  {
	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
	        	      multiValue+="$";   	  
	        	  
	        	  }
	        	 resultEntryTemplateValue=multiValue
	        	  }
	          
	        else
	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
		   
	        
	    //    alert(resultEntryTemplateValue);
	        name+='#'+resultEntryTemplateValue+'#'+values;
	          
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
        
         
    
        // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
      	//	alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	 
 	              
 	              if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           }
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
              
              if(resultEntryTemplateValue.length>12000)
            	  {
            	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
            	  return false;
            	  
            	  
            	  }
	          name+='#'+resultEntryTemplateValue+'#'+values;
          
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
		          name+='#'+resultEntryTemplateValue+'#'+values;
	          
 	        	  
 	        	  }
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

     	       
     	       } 
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value);
	
	/* for(j;j<reqNO.length;j++)
		{ 
		
	
	document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
    alert(document.getElementsByName('resultEntryTemplateValue').value);
	document.getElementsByName('parameterCode').value=parameterCode[j];
    document.getElementsByName('parantParameterCode').value=parantParameter[j];
    document.getElementsByName('requisitionDNo').value=reqNO[j];
		} */
		
		if(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=="")
			{
			alert("Invalid form. Modify form from Test Parameter Master.");
			return false;
			
			}
	if(count==0)
   	{
   	  alert("please select a Atleast One record");
   	  return false;
   	}
	 
	
	document.getElementsByName('showStatus')[0].value='0';


	document.getElementsByName('hmode')[0].value="SAVE";
	
	document.forms[0].submit();
	 
   return true;
	 
  }
  function saveValues()
  {
	  	document.getElementsByName('showStatus')[0].value='0';
		document.getElementsByName('hmode')[0].value="SAVE";
		document.forms[0].submit();

  }
  
function modifyAll()
{	var cbs=document.getElementsByName('chkResultEntryPatient');

	
for(var i=0; i < cbs.length; i++)
cbs[i].checked=true;
	
	onModify();
	}
function onModify()
{
	
	var count=0;
	var concatenateChkBoxVal="";
	var cbs =document.getElementsByName('chkResultEntryPatient');
	
	 var name;
	  var splitTemplateValue;
	  var reqNO=[];
	  var parameterValue=[];
	  var parameterCode=[];
	  var parantParameter=[];
	  var resultEntryTemplateValue=[];
	//alert(cbs.length);
	for(var i=0; i < cbs.length; i++)
	{
	if(cbs[i].checked)
    	{
         
		//var values=document.getElementById(i+"chkBOx").value;
		//alert("The value is"+values);
		count++;	
	        var k=0;
           //alert(document.getElementById(i+'templateValue').getElementsByTagName("input"));
             
//          
//         alert("get_tags length"+get_tags.length);
//            for (var i=0; i<get_tags.length; i++) {
//         	    // assigns style properties
//         	     var name=get_tags[i].name;
//         	     alert("i"+name);
//         	  }
        	 var checkBoxId=cbs[i].id;
        	
        	 //var splitTheCheckBoxId=checkBoxId.substr(0,2);
        	  var splitTheCheckBoxId=checkBoxId.replace('chkBOx', '');
           
             for(k;k<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input").length;k++)
        	    {
             	
                 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
                 
                 get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input");
                   name=get_tags[k].name;
                   id=get_tags[k].id;
                   hiddenid="hiddenid"+id;
                   defaultid="default"+hiddenid;
              	  typ=get_tags[k].type;
              	  hidddentext="hidden";
              
               //  alert("type is " + typ);
                   if(typ!=hidddentext)
                   {
                  	 
            //      	 alert("not hidden");
                   splitTemplateValue=name.split("#");
                   reqNO.push(splitTemplateValue[0]);
                    parameterValue=splitTemplateValue[3];
                 //   alert("parameter value    "+parameterValue);
                    parameterCode.push(parameterValue.substring(0, 5));
                    parantParameter.push(parameterValue.substring(9,18));
                  
                      
                //  alert("parameterValue"+parameterValue+"reqno"+reqNO+"parameter"+parantParameter);
                 
                      if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value=="")
                             {
                                    alert("Enter the field Focussed");
                                    document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].focus();
                                 return false;
                              }
                 
                         // document.getElementsByName('resultEntryTemplateValue').value.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);       
                    //  resultEntryTemplateValue.push(document.getElementById(i+'templateValue').getElementsByTagName("input")[k].value);
                    var resultValidationTemplateValue="";
                    
                    if(id.contains("auto"))    
                  	  {
                  
                  	 if( document.getElementById(hiddenid).value!=null && document.getElementById(hiddenid).value!="" )
                  	  resultValidationTemplateValue=document.getElementById(hiddenid).value;
                  	 else
                  		 resultValidationTemplateValue=document.getElementById(defaultid).value;
                  	  } 
                    else
                      resultValidationTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("input")[k].value;
                          name+='#'+resultValidationTemplateValue+'#'+values;
                          
                       concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
                       concatenateChkBoxVal+='@';
                       
                   }
                          
                          
			
		          
		          
	             }
        // alert(document.getElementById(i+'templateValue').getElementsByTagName("select").length);
       // alert("select value"+document.getElementById(i+'templateValue').getElementsByTagName("select")[0].value);
        var j=0;
         for(j;j<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select").length;j++)
    	       { 	 var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
        		//alert("The value is"+values);
         	  get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select");
        	   name=get_tags[j].name;
         	 
         	// alert("inside here");
         //	alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length);
             	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="-1" || document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].focus();
 			          return false;
 		           }
 	          //   document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
               var multiValue="";
               var resultEntryTemplateValue;
 	        if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1")
               for (var kk=0;kk<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].length ;kk++)
 	        	  {
 	        	  //&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
 	        	 if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].selected==true)
 	        	  {//  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
 	        	   	  multiValue+=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value;
 	        	      multiValue+="$";   	  
 	        	  
 	        	  }
 	        	 resultEntryTemplateValue=multiValue
 	        	  }
 	          
 	        else
 	       	resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].value;
 		   
 	        
 	        //alert(resultEntryTemplateValue);
 	        name+='#'+resultEntryTemplateValue+'#'+values;
 	          
      		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
     			concatenateChkBoxVal+='@';

    	       
    	       } 
         
         
        // alert("text Area value"+document.getElementById(i+'templateValue').getElementsByTagName("textarea")[0].value);
         var n=0;
          for(n;n<document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea").length;n++)
     	       {
 	             
        	  var values=document.getElementById(splitTheCheckBoxId+"chkBOx").value;
      	//	alert("The value is"+values);
        	  //alert("inside here");
 	             get_tags=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea");
 	        	   name=get_tags[n].name;
 	         	 
 	              
 	              if(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value=="")
 	         	   {
 				      alert("Enter the field Focussed");
 				      document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].focus();
 			          return false;
 		           }
 	           //  document.getElementsByName('resultEntryTemplateValue')[0].value=document.getElementById(i+'templateValue').getElementsByTagName("input")[j].value;	 
                
 	         //  alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id);
 	         var id1 = document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].id;
 	           var editor = CKEDITOR.instances[id1];
 	       
 	         
 	           if(editor!=null){
 	        	 
 	        	  
            //  alert( editor.getData() );
              
              var resultEntryTemplateValue=editor.getData();
            
              if(resultEntryTemplateValue.length>12000)
        	  {
        	  alert("Editor Data limit is 12000, including spaces. Please don't exceed the limit");
        	  return false;
        	  
        	  
        	  }
	          name+='#'+resultEntryTemplateValue+'#'+values;
          
 	          }

 	          else
 	        	  {
     	         var resultEntryTemplateValue=document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("textarea")[n].value;
		          name+='#'+resultEntryTemplateValue+'#'+values;
	          
 	        	  
 	        	  }
     		  	concatenateChkBoxVal =concatenateChkBoxVal.concat(name);
    			concatenateChkBoxVal+='@';

     	       
     	       } 
        
       
         
         }
   
    
     }
	

	//alert("Finally concatenateChkBoxValues For Save "+concatenateChkBoxVal);
	
	
	document.getElementsByName('resultEntryTemplateValueWithHash')[0].value=concatenateChkBoxVal;
	//alert(document.getElementsByName('resultEntryTemplateValueWithHash')[0].value);
	
	/* for(j;j<reqNO.length;j++)
		{ 
		
	
	document.getElementsByName('resultEntryTemplateValue').value=resultEntryTemplateValue[j];
    alert(document.getElementsByName('resultEntryTemplateValue').value);
	document.getElementsByName('parameterCode').value=parameterCode[j];
    document.getElementsByName('parantParameterCode').value=parantParameter[j];
    document.getElementsByName('requisitionDNo').value=reqNO[j];
		} */
	if(count==0)
   	{
   	  alert("please select a Atleast One record");
   	  return false;
   	}
	 
	
	document.getElementsByName('showStatus')[0].value='0';


	document.getElementsByName('hmode')[0].value="MODIFY";
	
	document.forms[0].submit();
	 
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

  
  
  
  
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function getDetails(obj)
{
	
	
	document.getElementsByName('generationType')[0].value=obj.value;
	
	 if(document.getElementsByName("labCode")[0].value=="-1")
     {//testGroupWise
    	alert("Select Lab   Name ... ");

    	document.getElementsByName("patientWise")[0].checked = true;
    	document.getElementsByName("allPatient")[0].checked = false;
    	  document.getElementsByName("testGroupWise")[0].checked = false;
    	  document.getElementsByName("testWise")[0].checked = false;
    	  document.getElementsByName("sampleNoWise")[0].checked = false;
    	  document.getElementsByName("labNoWise")[0].checked = false;
	    document.getElementsByName("labCode")[0].focus();
	    return false;
     }
 //alert(document.getElementsByName('generationType')[0].value);
 document.getElementsByName('hmode')[0].value='GETTYPEWISEDETAIL';
	document.forms[0].submit();
	//document.getElementsByName('hmode')[0].value='GETTYPEWISEDETAIL';
	///document.forms[0].submit();
	  if(obj.value=="P")
		{
		  
document.getElementById("patientwise").style.display="";	
document.getElementById("patientwisename").style.display="";	

document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	
document.getElementById("samplenowise").style.display="none";	
		}
	if(obj.value=="T")
		{
		   
	document.getElementById("testwise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";	

	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
		}
	if(obj.value=="L")
		{
		 
	document.getElementById("labnowise").style.display="";	
	document.getElementById("labnowise2").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
		}
	if(obj.value=="S")
		{
		  
	document.getElementById("samplenowise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	
		}  
	
	if(obj.value=="TG")
	{
	  
		document.getElementById("testGrpWise").style.display="";
document.getElementById("samplenowise").style.display="none";	
document.getElementById("samplenowise2").style.display="none";	
document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";	
	document.getElementById("patientwisename").style.display="none";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	

	}  
	
	
	if(obj.value=="AP")
	{
	  
		document.getElementById("testGrpWise").style.display="none";
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";	
	document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	

	 
	labBased();
	}
	
}

function checkEntryType()
{
 
	
	var genTypeValue=document.getElementsByName('generationType')[0].value;
	//alert("onload"+genTypeValue); showOnLoad
	 if(genTypeValue=='')
		 {
		 document.getElementById("showOnLoad").style.display="";	
		 document.getElementById("patient").checked = true;
		 }
	 
	 if(genTypeValue=="P")
		{
		  
document.getElementById("patientwise").style.display="";	
document.getElementById("patientwisename").style.display="";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	
document.getElementById("samplenowise").style.display="none";	
document.getElementById("samplenowise2").style.display="none";	
document.getElementById("toLabSampleNo").style.display="none";	
		}
	if(genTypeValue=="T")
		{
		   
	document.getElementById("testwise").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="none";	
		}
	if(genTypeValue=="L")
		{
		  
	document.getElementById("labnowise").style.display="";	
	document.getElementById("labnowise2").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="";	
		}
	if(genTypeValue=="S")
		{
		  
	document.getElementById("samplenowise").style.display="";	
	document.getElementById("samplenowise2").style.display="";	
	document.getElementById("toLabSampleNo").style.display="";	
		document.getElementById("patientwise").style.display="none";	
		document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	
		}  
	
	
	if(genTypeValue=="TG")
	{
	  
		document.getElementById("testGrpWise").style.display="";
document.getElementById("samplenowise").style.display="none";	
document.getElementById("samplenowise2").style.display="none";	
document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";	
	document.getElementById("patientwisename").style.display="none";
document.getElementById("testwise").style.display="none";	
document.getElementById("labnowise").style.display="none";	
document.getElementById("labnowise2").style.display="none";	

	}  //allPatient
	
	if(genTypeValue=="AP")
	{
	  
		document.getElementById("testGrpWise").style.display="none";
	document.getElementById("samplenowise").style.display="none";	
	document.getElementById("samplenowise2").style.display="none";	
	document.getElementById("toLabSampleNo").style.display="none";	
	document.getElementById("patientwise").style.display="none";
	document.getElementById("patientwisename").style.display="none";
	document.getElementById("testwise").style.display="none";	
	document.getElementById("labnowise").style.display="none";	
	document.getElementById("labnowise2").style.display="none";	
	}
	
	
	 
	}
	function editDefaultQuantity(obj){
		if(obj.checked)
			{
				document.getElementsByName("defaultQuantity")[obj.value].removeAttribute("readonly");
			}
		else{
			document.getElementsByName("defaultQuantity")[obj.value].readOnly="readonly";
		}
		
	}


 function popupCallCanned()
 {
	 
	 
	 openAutocompletePopup('CANNED');
		var data=getTheLabCannedList('CANNED');
	//	alert("ajax data     "+ data);
		callCannedList(data);
		  document.getElementById("automplete-4").focus();
 }

function popupCallMacro()
{
	
	
	openAutocompletePopup('MACRO');
	var data=getTheLabCannedList('MACRO');
	callMacroList(data);
	  document.getElementById("automplete-m").focus();
	
	}
 
 
</script>
<script type="text/javascript">
        var isChecked = false;
        function allSelected() 
        {
        	document.getElementById("nextDiv").style.display=""; 
        	// this line is for toggle the check
            isChecked = !isChecked;
            //below line refers to 'jpCheckbox' class
            $('input:checkbox.jpCheckbox').attr('checked',isChecked);
            //OR,
            //$('input:checkbox.jpCheckbox').attr('checked','checked');
        }
    </script>
    
 <!-- keypad shortcut functionality -->
 
 
<script type="text/javascript">

$(document).ready(function() {

	var isCtrl=false;
	var isShift=false;
	
	$(document).keydown(function(e)
			{

		
		if(e.which==16)
			isShift=true;
		if(e.which==17)
			isCtrl=true;
	
	if(e.which!=17 && e.which!=120 && e.which!=123)//120 f9 key
		isCtrl=false;
	
	if(e.which!=16 && e.which!=120 && e.which!=123)//120 f9 key
		isShift=false;
	
	

	
	if(isCtrl && e.which==120)
		{
		
		//document.getElementById('cannedField').style.display="";
		
		popupCallCanned();
		
		isCtrl=false;
		isShift=false;
		

		   
		
		}
		
	if(isShift && e.which==120)
	{
	
	//	document.getElementById('macroField').style.display="";
		popupCallMacro();
		
	isCtrl=false;
	isShift=false;
	
	}
	
	
	if(isCtrl && e.which==123)
	{
	
	//document.getElementById('cannedField').style.display="";
	autocompleteBox_close();
	
	isCtrl=false;
	isShift=false;
	
	}
	
if(isShift && e.which==123)
{

//	document.getElementById('macroField').style.display="";
	autocompleteBox_close_macro();
	
isCtrl=false;
isShift=false;

}

		
		});
			
			
	
  
});



//autocomplete functions for canned and macro
function callCannedList(data){

//canned
 $(function() {
 	      
 	           var cannedCodes=data;
          
         
            
           /*  var obj= JSON.parse(cannedCodes);
            cannedCodes=obj;
			
          var details="";  
            alert(cannedCodes.length); 
            
            for(var kk=0;kk<cannedCodes.length;kk++)
          {   	alert("label :     "+cannedCodes[kk].label);
           
          details+=cannedCodes[kk].label+"#"+cannedCodes[kk].value+"@";
          
          } 
            */ 
            
            
            //new
            
            
            
            
            var splitCannedDetails=cannedCodes.split("@");
            var cannedDetails="";
            
            if(splitCannedDetails.length-1!=0)
   		 { 
            	 document.getElementsByName("cannedDataCount")[0].value=splitCannedDetails.length-1;
            	deleteTableCanned();
            	for(i=0;i<splitCannedDetails.length-2;i++)
   		 {
   		
   		
   	
   		 AddRowToTableAddMoreValue(splitCannedDetails[i]);
   		cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[0]+"\" }";
   		cannedDetails+=","; 
   		
   		  }
            	
            	
            	 AddRowToTableAddMoreValue(splitCannedDetails[i]);
            		cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[0]+"\" }";
            //	alert(cannedDetails.length);
            	
            	cannedDetails="["+cannedDetails.toString()+"]";
            	//alert(cannedDetails.length);
            	cannedDetails=cannedDetails.toString();
          }
   	 else
   		 {
   		
   			 alert("No Canned Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
   		
   		 
   		 }
            
            
            
            
            
            
            
            
          
            
            
            
            
           
  //       alert("inside jquery canned details label valiu      "+cannedDetails);
            var obj= JSON.parse(cannedDetails);
            cannedCodes=obj;
            
       
        	
        	
 	           setInterval(function() {
             $( "#automplete-4" ).autocomplete({
                 source: cannedCodes,
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
         
}
         
 function autovalue()
 {
	 
var aa1=0;
    
<%-- 	 Map<String,List<String>> mpAutoList = <%=(Map<String,List<String>>)session.getAttribute(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES)%>; --%>
	
<%-- 	Map<String,List<String>> mpAutoList = <%=(Map<String,List<String>>)session.getAttribute(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES)%>;
	
	
	  if(mpAutoList!=null)
	  {
		  Iterator itrAuto=mpAutoList.keySet().iterator();
	   while(itrAuto.hasNext())
	  { String autoCode=(String)itrAuto.next();
	  String autoList=mpAutoList.get(autoCode).get(0);
	  
	  var objList = JSON.parse(autoList);
	  

	setInterval(function() {
    $( autoCode).autocomplete({
       
        select: function(event, ui) {
        	source:objList,
             $('#hiddenid'+autoCode).val(ui.item.value); 
            event.preventDefault();  
            $(autoCode).val(ui.item.value); 
        },

    focus: function(event, ui) { 
           event.preventDefault(); 
           $(autoCode).val(ui.item.value);}
     });
    }, 1000);
    
	
}} --%>
 }  
//macro
function callMacroList(data){
 $(function() {
 	      
 	           var macroCodes=data;
 	           //alert(macroCodes);
 	           
 	/*           var obj1= JSON.parse(macroCodes);
 	         macroCodes=obj1;
 	         
 	        var details="";  
            alert(macroCodes.length);            
            for(var kk=0;kk<macroCodes.length;kk++)
          {   	alert("label :     "+macroCodes[kk].label);
           
          details+=macroCodes[kk].label+"#"+macroCodes[kk].value+"@";
          
          } 
             */
                      
            var splitCannedDetails=macroCodes.split("@");
             var cannedDetails="";
        	 if(splitCannedDetails.length-1!=0)
        		 { 
        		 deleteTableMacro();
        		 document.getElementsByName("macroDataCount")[0].value=splitCannedDetails.length-1;
        	 for(i=0;i<splitCannedDetails.length-2;i++)
        		 {  		    
        		
        		    	AddRowToTableAddMoreValueMacro(splitCannedDetails[i]);
        		   
        		    	
        		    	cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[1]+"\" }";
        		   		cannedDetails+=","; 
        		 	  
        		 }
        	 
        	 AddRowToTableAddMoreValueMacro(splitCannedDetails[i]);
        	 cannedDetails+="{\"label\":\""+splitCannedDetails[i].split("#")[0]+"\" ,  \"value\": \""+splitCannedDetails[i].split("#")[1]+"\" }";
        	
         	cannedDetails="["+cannedDetails.toString()+"]";
         	
         	
        		 }
        	 else
        		 {
        		
        		
        			 alert("No Macro Data Is Mapped For  "+document.getElementsByName("chkLabName")[0].value+"   Lab");
        		 
        		 }
        	 
        	 
        	//alert(cannedDetails);
        	 
        	   var obj= JSON.parse(cannedDetails);
        	   macroCodes=obj;
        	 
        	 
        	 
            
 	           setInterval(function() {
             $( "#automplete-m" ).autocomplete({
                 source: macroCodes,
                 select: function(event, ui) { 
                     $('#hiddenidm').val(ui.item.value); 
                     event.preventDefault(); 
                     $("#automplete-m").val(ui.item.label); 
                 },

             focus: function(event, ui) { 
                    event.preventDefault(); 
                    $("#automplete-m").val(ui.item.label);}
              });
             }, 1000);
          });
         
}
/* 
var parsethis="[{\"label\": \"adfwefwe\",\"value\": \"1\"  }, {\"label\": \"bwefwefwe\",\"value\": \"2\" }, {\"label\": \"cfwefweff\",\"value\": \"3\" },  {\"label\": \"dfwefwfwef\",\"value\": \"4\" } ]";
parsethis=parsethis.toString();
var objlisting=JSON.parse(parsethis);
//alert(parsethis);alert(objlisting);


 setInterval(function() {
$( "#auto100221028" ).autocomplete({
  source: objlisting,
  select: function(event, ui) { 
      $('#hiddenidauto100221028').val(ui.item.value); 
      event.preventDefault(); 
      $("#auto100221028").val(ui.item.label); 
  },

focus: function(event, ui) { 
     event.preventDefault(); 
     $("#auto100221028").val(ui.item.label);}
});
}, 1000); */






function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}


$(document).ready(function()
		{
for(var inst in CKEDITOR.instances) {


//alert(CKEDITOR.instances[inst].name);
/* 
CKEDITOR.instances[inst].setKeystroke([
                                       [ CKEDITOR.CTRL + 120,  false],
                                       [ CKEDITOR.SHIFT + 120, false]
                                       
                                       ]);

 */
// CKEDITOR.on('currentInstance', function(){alert(inst)});
 

CKEDITOR.instances[inst].on("focus",function()
	{
	//alert(this.name); always use this name and not inst name as it keeps getting incremented and thus takes up the value of the last editor
	  document.getElementsByName("currentElement")[0].value="ckeditor";
	document.getElementsByName("currentElementName")[0].value=this.name;
	document.getElementsByName("editorName")[0].value=this.name;
	  
	
	}
		)



}
		});
</script>

   
<style>
.NewTextBox {
	border:2px solid #456879;
	border-radius:10px;
	height: 22px;
	width: 330px;
}
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
  .ui-autocomplete {
    max-height: 100px;
    overflow-y: auto;
    /* prevent horizontal scrollbar */
    overflow-x: hidden;
  }
  
  
</style>




<body  onload="setValues();autovalue();checkEntryType();" >
<html:form action="/testWiseConsumableMapping"  enctype="multipart/form-data">
	<html:hidden name="TestWiseConsumableFB" property="hmode" />
	<html:hidden name="TestWiseConsumableFB" property="isPatDetailPage" />
	<html:hidden name="TestWiseConsumableFB" property="selectedCheckbox" />
	 <html:hidden name="TestWiseConsumableFB" property="showStatus" />
	 <html:hidden name="TestWiseConsumableFB" property="currentPage" />
	  <html:hidden name="TestWiseConsumableFB" property="patCRNo" />
	  	  <html:hidden name="TestWiseConsumableFB" property="sysDate" />
	  	  	  <html:hidden name="TestWiseConsumableFB" property="getSearchType" />
	  	  	   <html:hidden name="TestWiseConsumableFB" property="generationType"    />
	  	  	     <html:hidden name="TestWiseConsumableFB" property="onLoadValue"    />
	 <html:hidden name="TestWiseConsumableFB" property="sizeOfFile" value="0" />
	
	     
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	<logic:equal name="TestWiseConsumableFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
		<his:TitleTag name="Test Wise Consumable Details">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
		  <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
         %>
      <bean:define name="TestWiseConsumableFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="TestWiseConsumableFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%> 
    	 <logic:equal name="TestWiseConsumableFB" property="showStatus" value="0">
    	<his:SubTitleTag name="Details">
  			</his:SubTitleTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			    
			         
			    
			     <tr>
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LabType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="labCode"    tabindex="1"  onchange="labBased()">
				       					
				       				<html:option value="%">All</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			    <%--  <td width="25%" class="tdfont">
			     
			      <div align="right">
			               <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						   </font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="ResultEntry"/>&nbsp;
						   </font>
				     </div>
				     
			     
			      </td>
			      <td width="25%" class="tdfont">
			      
			       <html:radio name="TestWiseConsumableFB"   tabindex="1" property="newEntry" value="1"  ></html:radio>
						
						<bean:message key="newEntry"/>&nbsp;
						
						<html:radio name="TestWiseConsumableFB" tabindex="1" property="newEntry" value="2" ></html:radio>
						
						<bean:message key="entered"/>&nbsp;
			       
			       
			     </td> --%>
			     </tr>
			      <tr>            
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date  name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
			</td>
 			<td class="tdfont" width="25%">
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>&nbsp;
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					&nbsp;&nbsp;&nbsp;<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
			</td>
 		</tr>
 		 <tr  >
			    <td width="25%" class="tdfont">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="ResultEntryType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="25%" class="tdfont" colspan="3">
			      <div align="left"   >                                                                                                           
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="TestWiseConsumableFB" property="generationType" value="P">
						 
						<input type="radio"  name="patientWise"  onclick="getDetails(this)" checked="checked" value="P" />
						</logic:equal>
						<logic:notEqual name="TestWiseConsumableFB" property="generationType" value="P">
						<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)"   value="P" />
						</logic:notEqual>
						<bean:message key="PatientWise"/>
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="TestWiseConsumableFB" property="generationType" value="T">
						<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked"  value="T" />
						</logic:equal>
						<logic:notEqual name="TestWiseConsumableFB" property="generationType" value="T">
					<input type="radio" name="testWise" onclick="getDetails(this)"   value="T" />
						</logic:notEqual>
						
						<bean:message key="testWise"/>
	     		 
	     		                                                                                               
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="TestWiseConsumableFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  checked="checked" value="S" />
						</logic:equal>
						<logic:notEqual name="TestWiseConsumableFB" property="generationType" value="S">
						<input type="radio" name="sampleNoWise"  onclick="getDetails(this)"  value="S" />
						</logic:notEqual>
						
						
						<bean:message key="sampleNoWise"/>
	     		  
	     		                                                                                                       
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="TestWiseConsumableFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)" checked="checked" value="L" />
						</logic:equal>
						<logic:notEqual name="TestWiseConsumableFB" property="generationType" value="L">
						<input type="radio" name="labNoWise"  onclick="getDetails(this)"  value="L" />
						</logic:notEqual>
						
						
						<bean:message key="labNOWise"/>
						<div style="display:none" id="testName" ></div>
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="TestWiseConsumableFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)" checked="checked" value="TG" />
						</logic:equal>
						<logic:notEqual name="TestWiseConsumableFB" property="generationType" value="TG">
						<input type="radio" name="testGroupWise"  onclick="getDetails(this)"  value="TG" />
						</logic:notEqual>
						
						
						<bean:message key="testGrpWise"/>
						
						
						&nbsp;&nbsp;&nbsp;
						<logic:equal name="TestWiseConsumableFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)" checked="checked" value="AP" />
						</logic:equal>
						<logic:notEqual name="TestWiseConsumableFB" property="generationType" value="AP">
						<input type="radio" name="allPatient"  onclick="getDetails(this)"  value="AP" />
						</logic:notEqual>
						
						
						<bean:message key="allPatient"/>
	     		   </div>  
	     		    
			     </td>
			     
			     </tr>
 		     <tr>
			    <td width="25%" class="tdfont">
			    <div align="right" id="showOnLoad" style="display:none">
			    <bean:message key="crNO"/>&nbsp;
			    </div>
			        <div align="right" >
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <logic:equal name="TestWiseConsumableFB" property="generationType" value="P">
								<bean:message key="crNO"/>&nbsp;
								</logic:equal>
								 <logic:equal name="TestWiseConsumableFB" property="generationType" value="T">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="testName"/>&nbsp;
								</logic:equal>
								 <logic:equal name="TestWiseConsumableFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromLabNo"/>&nbsp;
								</logic:equal>
								 <logic:equal name="TestWiseConsumableFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="fromSampleNo"/>&nbsp;
								</logic:equal>
								<logic:equal name="TestWiseConsumableFB" property="generationType" value="TG">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="tstgrpName"/>&nbsp;
								</logic:equal>
						 </font>
				     </div>
				     
				     
				     
				  
				  
			      </td>
			      <%
			      UserVO uservo=ControllerUTIL.getUserVO(request);
			      Date todayDateobj = new Date();
					SimpleDateFormat dateob = new SimpleDateFormat("yy");
					String strDate= dateob.format(todayDateobj);
			      String hospitalCode=uservo.getHospitalCode();
			      String val=uservo.getHospitalCode()+strDate;
			      %>
			      <td width="25%" class="tdfont">
			      
			      <div align="left" id="patientwise" >                                                                                                           
					 &nbsp;&nbsp;&nbsp; <input type="text" id="textBoxCss" name="tempPatCRNo" value="<%=val%>" maxlength="15" size="20"  onkeypress="return validateNumeric(event,this)" tabindex="1"> 
				  </div>  
				   
				    <logic:present name="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="testwise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="testWiseCode"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				   <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="samplenowise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="fromSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				   <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="labnowise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="fromLabNo"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				    
				    
				     <logic:present name="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="testGrpWise" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="testGroupCodeWise"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				   
				  
			     </td>
			     <td width="25%" class="tdfont">
			     
			        <div align="left" id="patientwisename" >                                                                                                           
					 Search Name&nbsp;&nbsp;&nbsp;<input type="text" id="textBoxCss" name="tempPatName"  maxlength="20" size="20"  onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
				   </div>  
				   
				   
			        <div align="right" id="toLabSampleNo"  style="display: none;">
			         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			     <logic:equal name="TestWiseConsumableFB" property="generationType" value="L">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toLabNo"/>&nbsp;
								</logic:equal>
								 <logic:equal name="TestWiseConsumableFB" property="generationType" value="S">
								<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							      *	 
						         </font>
								<bean:message key="toSampleNo"/>&nbsp;
								</logic:equal>
								</font>
			      </div>
			      </td>
			      
			      <td width="25%" class="tdfont">
			       <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="labnowise2" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="toLabNo"    tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
				  
				  
				   <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
			      <div align="left" id="samplenowise2" style="display: none;">
			      &nbsp;&nbsp;
					   <span class="custom-dropdown small">
				 <html:select name="TestWiseConsumableFB" property="toSampleNo"    tabindex="1"  >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label"/>
				      				</html:select>
				      				</span>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			   <tr>
			   <td class="tdfont" align="left" colspan="4" width="25%">
			  <div align="center">
			  <img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')" onclick="onClickGO('<%=hospitalCode%>')" tabindex="1">
			 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"  style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc() " onclick="cancelFunc()" tabindex="1">
			     
			      </div>
			   </td>
			   </tr>  
			     </table>
			     </logic:equal>
			     </his:ContentTag>
     <%boolean flag=false; %>
  	 <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((TestWiseConsumableFB)request.getAttribute("TestWiseConsumableFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
					fbPage.setAppendInTitle("List ");
					int maxRecord=15;
					fbPage.setMaxRecords(maxRecord);
				 
				 %>
				 
				 <logic:equal name="TestWiseConsumableFB" property="showStatus" value="0">
				 		<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<% flag=true; %>
			<table   width="100%" bgcolor="#EBEBEB"   >
			<tr>
					<td width="3%" align="left"  >	
						<b> <font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif">
					<!-- <input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" /> --> </font>
	                  </b>
					</td>
					<td width="15%" align="left"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						CR NO </font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patientName"/></font></b>
					</td>
					<td width="10%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="age/gender"/></font></b>
					</td>
					<td width="15%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="departmentunit"/></font></b>
					</td>
					<td width="12%" align="left">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="TestName"/></font></b>
					</td>
					<td width="11%" align="left"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="labName"/></font></b>
					</td>
					
					<td width="8%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="sampLabNo"/></font></b>
					</td>
					
					
					<td width="12%" align="left"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="patStatus"/></font></b>
					</td>
				</tr>
			</table>
			<logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				<center>
				<font color="red" size="4">
				<bean:message key="noRecord"/></font></center>
			</logic:empty>
			<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
			<table   width="100%" bgcolor="#EBEBEB" cellspacing="0" style="border-spacing: 0;">
					<%
					 List<TestWiseConsumableVO> lstPatVO=(List<TestWiseConsumableVO>)session.getAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
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
						TestWiseConsumableVO voPat=lstPatVO.get(j);
						String  chkVal=voPat.getPatCRNo()+"#"+voPat.getRequisitionNo()+"#"+voPat.getRequisitionDNo()+"#"+voPat.getGroupCode()+"#"+voPat.getLabCode()+"#"+voPat.getTestCode()+"#"+(voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName());
						//String labCode=voPat.getLabCode();
					if(voPat.getGroupCode()!=null)
					{   
						 
					
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
                        else
                        {
                        	 firstTimeTravesall=true;	
                        }
						
						}
						 grpCode+=voPat.getRequisitionNo()+voPat.getGroupCode();
						}
						 
         				 
					 
					 

					 if(firstTimeTravesall)
			 			{
						 
					
					%>
					 
					
					<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <input type="checkbox" class="jpCheckbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" > --%>
							<input type="radio" class="jpCheckbox" name="chkSamplePatient" onclick="displayButton(this)" value='<%=chkVal%>' >
							</font>
						</td>
						<td width="15%" align="left" >
						  
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatCRNo() %></font> 
						 
				  		</td>
				  		
				  		<td width="15%" align="left"  >
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatName() %></font>
						  
				  		
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatAge()+"/"+voPat.getPatGender()%></font>
						  
				  		</td>
				  		 <td  width="15%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatUnitName() %></font>
						  
				  		</td>
				  		<td width="12%" align="left">
				  		 
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getGroupName().equals("NA")?voPat.getTestName():voPat.getGroupName() %></font>
						  
				  		</td>
				  		<td width="11%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatLabName() %></font>
						 
				  		</td>
				  		
				  		
				  		<td width="8%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=(voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo())==null?"N.A.":(voPat.getTempSampleNo()==null?voPat.getLabNo():voPat.getTempSampleNo())%></font>
						  
				  		</td>
				  		
				  		
				  		<td width="12%" align="left">
				  		 
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voPat.getPatStatus() %></font>
						  
				  		</td>
					</tr>
				
					<%}}  }%>
					
					
			</table>
			</logic:notEmpty>
			</logic:present>
			 </logic:equal>
	<%boolean pageShow=false; %>
	
	<logic:notEmpty name="<%=InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL %>">
	

	<%pageShow=true; %>
	</logic:notEmpty>
	<logic:notEmpty name="<%=InvestigationConfig.PATIENT_TEST_WISE_ITEM_CONSUMABLE %>">
	<%pageShow=true; %>
	</logic:notEmpty>
	
	<%if(pageShow){ %>
	<logic:empty name="<%=InvestigationConfig.PATIENT_TEST_WISE_ITEM_CONSUMABLE %>">
		<his:SubTitleTag name="Patient Details" >
		
		</his:SubTitleTag>
		<div>Patient Details Not Found</div>
	</logic:empty>
	<logic:notEmpty name="<%=InvestigationConfig.PATIENT_TEST_WISE_ITEM_CONSUMABLE %>">
		<his:SubTitleTag name="Patient Details" >
		
		</his:SubTitleTag>
		<table table width="100%">
		<%
		TestWiseConsumableVO[] patDetailsTestWiseConsumableVO=null;
			
			 patDetailsTestWiseConsumableVO=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.PATIENT_TEST_WISE_ITEM_CONSUMABLE);
			 	for(int i=0;i<patDetailsTestWiseConsumableVO.length;i++)
			 		
			 	{
			 		
		%>
		
		 <tr><td class="tdfont" width="15%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patientName"></bean:message></font></div></td><td class="tdfonthead" width="15%" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatName() %></td><td width="18%" class="tdfont"><div align="right"><font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="gender"></bean:message></font></div></td><td class="tdfonthead" width="18%" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatGender() %></td><td width="18%" class="tdfont"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="age"/></font></div></td><td class="tdfonthead" width="18%" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatAge() %></td></tr>
			<tr><td class="tdfont" width="15%"><div align="right">	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqNo"/></font></div></td><td class="tdfonthead" style="text-align:left" width="15%"><%=patDetailsTestWiseConsumableVO[i].getRequisitionNo() %></td><td class="tdfont" width="18%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="labName"/></font></div></td><td width="18%" class="tdfonthead" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatLabName() %></td><td class="tdfont" width="18%"><div align="right"> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="visitDate"/></font></div></td><td class="tdfonthead" width="18%" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatVisitDat() %></td></tr>
	 		 <tr><td class="tdfont" width="15%"><div align="right">	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="CrNO"/></font></div></td><td class="tdfonthead" width="15%" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatCRNo() %></td><td class="tdfont" width="18%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="UnitName"/></font></div></td><td width="18%" class="tdfonthead" style="text-align:left"><%=patDetailsTestWiseConsumableVO[i].getPatUnitName() %></td><td class="tdfont" width="18%"><div align="right"> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="testName"/></font></div></td><td class="tdfonthead" style="text-align:left" width="18%" id="testName"></td></tr>
		<%} %>
		</table>
	</logic:notEmpty>	
	<%} %>		 
	
	
	
			
		<%if(pageShow){ %>	
		 	 
		 	 
		
		<his:SubTitleTag name="Test Wise Consumable Mapping">
		
		<div class="multiselect" >
        <div class="selectBox" onclick="showCheckboxes()">
            <select >
                <option>Select Items To Add</option>
            </select>
            <div class="overSelect"></div>
        </div>
        <div id="checkboxes" onmouseout="showCheckboxes()">
        <logic:notEmpty name="<%=InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL %>">
         <%
  			 	TestWiseConsumableVO[] testWiseConsumableVO1=null;
         		boolean checked=true;
  			 	testWiseConsumableVO1=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL);
  			 	//Entry []defaultValues=(Entry[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_CONSUMABLE_LOT);
  			 	for(int i=0;i<testWiseConsumableVO1.length;i++)
  			 	{
  			 		
  			 	if(testWiseConsumableVO1[i].getItemName()!=null){
  			 	%>
  			 	
            <label for="<%=testWiseConsumableVO1[i].getLotNo()%>"><input  value="<%=i%>"  onclick="showItems(this);" type="checkbox" id="<%=testWiseConsumableVO1[i].getLotNo()%>"/><%=testWiseConsumableVO1[i].getItemName()+"("+testWiseConsumableVO1[i].getLotNo()+")"%></label><br>
            
  			 	<%}} %>
  			 		</logic:notEmpty>
        </div>
    </div>
	
  			</his:SubTitleTag>
  			 
  			 <table width="100%" border="0" cellspacing="1" id="contents" cellpadding="0" bgcolor="#EBEBEB">
  			 <tr>
  			  <td width="5%" >
  			 <input type="checkbox" id="chkbox" onclick="selectAllChkBox(this);"></input>
  			 
  			 </td> 
  			 <td width="15%"><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="itemName"></bean:message></font></b>
  			 </td>
  			 <td width="15%"><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="lotNo"></bean:message></font></b>
  			 </td>
  			 <td width="15%"><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="manufactur"></bean:message></font></b>
  			 </td>
  			 <td width="15%"><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="expiryDate"></bean:message></font></b>
  			 </td>
  			 <td width="15%"><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="itemType"></bean:message></font></b>
  			 </td>
  			 <td width="15%"><font color="RED" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> * </font><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="quantity"></bean:message></font></b>
  			 </td>
  			 <td width="5%"><b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
  			 <bean:message key="units"></bean:message></font></b>
  			 </td>
  			 
  			 </tr>
  			 <logic:notEmpty name="<%=InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE %>">
  			 <%
  			 	TestWiseConsumableVO[] testWiseConsumableVO=null;
  			 boolean visible=true;
  			 	testWiseConsumableVO=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE);
  			 	//Entry []defaultValues=(Entry[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_CONSUMABLE_LOT);
  			 	for(int i=0;i<testWiseConsumableVO.length;i++)
  			 		
  			 	{
  			 		if(testWiseConsumableVO[i].getManufacture()==null||testWiseConsumableVO[i].getManufacture().equals("null"))
  			 		{
  			 			testWiseConsumableVO[i].setManufacture("N.A.");
  			 		}
  			 %>
  			 <tr bgcolor="#ffbb99" id="<%=testWiseConsumableVO[i].getLotNo()+"#tr" %>" >
  			 <td width="5%">
  			<html:checkbox name="TestWiseConsumableFB" onclick="editDefaultQuantity(this)" property="selectedItem" value="<%=Integer.toString(i) %>"></html:checkbox>
  			<%--  <%=i+1 %> --%>
  			 </td>
  			 <td width="15%">
  			 <%=testWiseConsumableVO[i].getItemName()==null?"N.A.":testWiseConsumableVO[i].getItemName() %>
  			 </td>
  			 <td width="15%">
  			 <%String value="-1";
  			   String quan="#quan";
  			   String lotString="#lot";
  			 %>
  			 <%-- <html:select name="TestWiseConsumableFB" styleId="<%=testWiseConsumableVO[i].getOtherItemID()+lotString %>" property="lotNo"    tabindex="1"  onchange="assignValues(this)">
				<%String lotCombo="lotCombo"; %>
				<logic:empty name="<%=lotCombo+i%>">
				<option value="-1">Usable Item Not Found</option>
				</logic:empty>
				<logic:notEmpty name="<%=lotCombo+i%>">
				<html:options collection="<%=lotCombo+i%>" property="value" labelProperty="label"/>
				</logic:notEmpty>
				
				
				</html:select> --%>
				  <%=testWiseConsumableVO[i].getLotNo()==null?"N.A.":testWiseConsumableVO[i].getLotNo() %>
				  <!-- lot,man,expiry,otherItemid,storeName,itemtypeid,itemid -->
				  <input type="hidden" id='<%=testWiseConsumableVO[i].getLotNo()+"#lot"%>' name="lotNo" value='<%=testWiseConsumableVO[i].getLotNo()+"#"+testWiseConsumableVO[i].getManufacture()+"#"+testWiseConsumableVO[i].getExpiryDate()+"#"+testWiseConsumableVO[i].getOtherItemID()+"#"+testWiseConsumableVO[i].getStoreName()+"#"+testWiseConsumableVO[i].getItemTypeID()+"#"+testWiseConsumableVO[i].getItemID()+"#"+testWiseConsumableVO[i].getSlNo() %>'/>
  			 </td>
  			 <td width="15%">
  			 <div id="<%=testWiseConsumableVO[i].getLotNo()+"#manuf"%>">
  			 <%=testWiseConsumableVO[i].getManufacture()==null?"N.A.":testWiseConsumableVO[i].getManufacture() %>
  			 </div>
  			 </td>
  			 <td width="15%">
  			 <div id="<%=testWiseConsumableVO[i].getLotNo()+"#expiry" %>">
  			 <%=testWiseConsumableVO[i].getExpiryDate() %>
  			 </div>
  			 </td>
  			 <td width="15%">
  			 <%=testWiseConsumableVO[i].getItemType()==null?"N.A.":testWiseConsumableVO[i].getItemType() %>
  			 </td>
  			 <td width="15%">
  			 
  			 <html:text styleId="<%=testWiseConsumableVO[i].getLotNo()+quan%>" property="defaultQuantity" name="TestWiseConsumableFB" readonly="true" value="<%=testWiseConsumableVO[i].getDefaultQuantity() %>"></html:text>
  			 </td>
  			 <td width="5%">
  			 <%=testWiseConsumableVO[i].getUnitName()==null?"N.A.":testWiseConsumableVO[i].getUnitName() %>
  			 
  			 <html:hidden property="unitCode"/>
  			 </td>
  			 <td width="5%">
				<img class="button" src='<his:path src="/hisglobal/images/minus.gif"/>'  alt="<%=testWiseConsumableVO[i].getLotNo()+"#"+testWiseConsumableVO[i].getItemName()+"#"+i%>"   onkeypress="if(event.keyCode==13) deleteOption(this);"  tabindex="1" onclick ="deleteOption(this);" >
  			 </td>
  			 
  			 
  			 </tr>
  			 
  			 <%} %>
  			 </logic:notEmpty>
  			 </table>
  			 
  			 	 <logic:notEmpty name="<%=InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL %>">
  			 	 <table style="display:none;" width="100%"  border="0" cellspacing="1" id="tempContents" cellpadding="0" bgcolor="#EBEBEB">
  			 <%
  			 	TestWiseConsumableVO[] testWiseConsumableVO=null;
  			 boolean visible=true;
  			 	testWiseConsumableVO=(TestWiseConsumableVO[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL);
  			 	//Entry []defaultValues=(Entry[])session.getAttribute(InvestigationConfig.LIST_TEST_WISE_CONSUMABLE_LOT);
  			 	for(int i=0;i<testWiseConsumableVO.length;i++)
  			 		
  			 	{
  			 		
  			 		if(testWiseConsumableVO[i].getManufacture()==null||testWiseConsumableVO[i].getManufacture().equals("null"))
  			 		{
  			 			testWiseConsumableVO[i].setManufacture("N.A.");
  			 		}
  			 %>
  			 <tr  id="<%=testWiseConsumableVO[i].getLotNo()+"#trTemp" %>" >
  			 <td width="5%">
  			<html:checkbox name="TestWiseConsumableFB" styleId='<%=testWiseConsumableVO[i].getLotNo()+"#trCheck" %>' property="tempSelectedItem" value="<%=Integer.toString(i) %>"></html:checkbox>
  			<%--  <%=i+1 %> --%>
  			 </td>
  			 <td width="15%">
  			 <%=testWiseConsumableVO[i].getItemName()==null?"N.A.":testWiseConsumableVO[i].getItemName() %>
  			 </td>
  			 <td width="15%">
  			 <%String value="-1";
  			   String quan="#quan";
  			   String lotString="#lot";
  			 %>
  			
				  <%=testWiseConsumableVO[i].getLotNo()==null?"N.A.":testWiseConsumableVO[i].getLotNo() %>
				  <input type="hidden" disabled id='<%=testWiseConsumableVO[i].getLotNo()+"#lot"%>' name="lotNo" value='<%=testWiseConsumableVO[i].getLotNo()+"#"+testWiseConsumableVO[i].getManufacture()+"#"+testWiseConsumableVO[i].getExpiryDate()+"#"+testWiseConsumableVO[i].getOtherItemID()+"#"+testWiseConsumableVO[i].getStoreName()+"#"+testWiseConsumableVO[i].getItemTypeID()+"#"+testWiseConsumableVO[i].getItemID() %>'/>
  			 </td>
  			 <td width="15%">
  			 <div id="<%=testWiseConsumableVO[i].getLotNo()+"#manuf"%>">
  			 <%=testWiseConsumableVO[i].getManufacture()==null?"N.A.":testWiseConsumableVO[i].getManufacture() %>
  			 </div>
  			 </td>
  			 <td width="15%">
  			 <div id="<%=testWiseConsumableVO[i].getLotNo()+"#expiry" %>">
  			 <%=testWiseConsumableVO[i].getExpiryDate() %>
  			 </div>
  			 </td>
  			 <td width="15%">
  			 <%=testWiseConsumableVO[i].getItemType()==null?"N.A.":testWiseConsumableVO[i].getItemType() %>
  			 </td>
  			 <td width="15%">
  			 
  			 <html:text styleId="<%=testWiseConsumableVO[i].getLotNo()+quan%>" property="defaultQuantity" name="TestWiseConsumableFB" disabled="true" value="<%=testWiseConsumableVO[i].getDefaultQuantity() %>"></html:text>
  			 </td>
  			 <td width="5%">
  			 <%=testWiseConsumableVO[i].getUnit()==null?"N.A.":testWiseConsumableVO[i].getUnit() %>
  			 
  			 <html:hidden property="unitCode"/>
  			 </td>
  			 
  			 
  			 </tr>
  			 
  			 <%} %>
  			 </table>
  			 </logic:notEmpty>
  			 
  			 <%} %>
  			
	<%-- <table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="labName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="labName" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="testName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="testName" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="itemName" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="defaultQuantity"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="defaultQuantity" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="batchNo"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="batchNo" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="manufactur"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="manufacture" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="expiryDate"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="expiryDate" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="storeName"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="storeName" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message key="itemType"/>
									
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
								<div align="left">
								<html:text property="itemType" name="TestWiseConsumableFB"></html:text>
								</div>
						</td>
						</tr>
						</table> --%>
	
		
		
		
			 <his:ButtonToolBarTag>
				     
				        <logic:equal name="TestWiseConsumableFB" property="newEntry" value="1">
				    	 <img class="button"   src='<his:path src="/hisglobal/images/EnterResults.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				        </logic:equal>
				      
				      	<logic:equal name="TestWiseConsumableFB" property="newEntry" value="2">
				       	 <img class="button"   src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				        </logic:equal>
				        <%--  <logic:present name="<%=InvestigationConfig.LIST_TEST_WISE_ITEM_CONSUMABLE_ALL %>"> --%>
				         <%if(pageShow){ %>	
				          
				        
				          <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"    onkeypress="if(event.keyCode==13) validateSaveFinal('SAVE');"  tabindex="1" onclick ="validateSaveFinal('SAVE');" >
				         <%--  <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();"> --%>

       				<%} %>
						 <%-- </logic:present> --%>
				    <logic:present name="<%=InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO %>">
				      <!-- this logic for cancel button hide in list page -->
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'id="cancel" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				    </logic:present>
				    
				    </his:ButtonToolBarTag>
				   
		<logic:equal name="TestWiseConsumableFB" property="showStatus" value="1">		    
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
	</logic:equal> 
    <div id="divLegends" style="display:none">
	 <his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="20%">
					<font color="#ffbb99" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					   Oragne
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	Already Added
					</div>
					</font>
				</td>				
			</tr>
			<!-- <tr>
				<td width="20%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					 Ctrl + F12
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Close Canned File
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="20%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					  Shift + F9
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 Open Macro File
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="20%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					  Shift + F12
					  </div>
					</font>
				</td>
				<td width="80%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 Close Macro File
					</div>
					</font>
				</td>				
			</tr> -->
			
		</table>
	</his:ContentTag> 
	</div>
       
		 <html:hidden name="TestWiseConsumableFB" property="resultEntryTemplateValue" />	 
		  <html:hidden name="TestWiseConsumableFB" property="parameterCode" />	     
		   <html:hidden name="TestWiseConsumableFB" property="parantParameterCode" />
	  	  <html:hidden name="TestWiseConsumableFB" property="requisitionDNo" />
	     <html:hidden name="TestWiseConsumableFB" property="resultEntryTemplateValueWithHash" />
	   <html:hidden name="TestWiseConsumableFB" property="deleteIndex" value=","/>
	     	
	     <input type="hidden"  name="cannedDataCount"  value="0" /> 
	     <input type="hidden"  name="cannedDetails"  value="0" />
	      <input type="hidden"  id="hiddenid4" name="userCannedCode" /> 
	      <input type="hidden"  id="hiddenidauto100221007" name="userCannedCode" />
	      
	        <input type="hidden"  id="hiddenidauto100221028" name="userCannedCode" />
	       <input type="hidden"  id="hiddenidm" name="userMacroCode" /> 
	     <input type="hidden"  name="macroDataCount"  value="0" /> 
	     <input type="hidden"  name="macroDetails"  value="0" />
	     
	   
	 <his:status/>		    
</html:form>
</body>
</html>  