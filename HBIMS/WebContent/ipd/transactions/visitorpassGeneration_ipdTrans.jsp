
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>

<html>
<head>
<meta charset=utf-8">
<title>Patient Visitor Pass</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>

<script language ="javaScript">
function validate1(){

      var hisValidator = new HISValidator("visitorpassTransBean");
      
      hisValidator.addValidation("strCrNo","req","CR No. is a Mandatory Field");
      
      if(document.forms[0].strNewRenew.value==0){
      	var o = document.getElementsByName("strchkvisit");
      
	  var passTypeObj = document.getElementsByName("strhPassType");
	 	var count= parseInt(0);
	
	var selectedCheckBoxNo="";
	  	for(var i=0;i<o.length;i++)
	  	{
		
			if(o[i].checked)
			{
				
				var chkValue=o[i].value;
				var temp=chkValue.split(',');
				var chkNo =temp[0];
				var passType = temp[1];
				
				selectedCheckBoxNo=selectedCheckBoxNo+i+",";
				passTypeObj[i].value=passType;
				//document.getElementsByName("strhPassType")[i].value=document.getElementById("strhPassType"+(i+1)).value
				document.getElementsByName("strValidUptoR")[i].value=document.getElementById("strValidUptoR"+(i+1)).value;
				document.getElementsByName("strhValidFrom")[i].value=document.getElementById("strhValidFrom"+(i+1)).value;
				document.getElementsByName("strPaidAmount")[i].value=document.getElementById("strPaidAmt0"+(i+1)).value;
				count= parseInt(count) + 1;
			}
		}
		document.forms[0].strSelectedChkNo.value=selectedCheckBoxNo;
		document.forms[0].strCount.value=count;
	
		 retVal = hisValidator.validate();
		  if(!retVal) return false;
		  if(typeof(document.forms[0].strAttendentPassCheckBox)!='undefined')
		  {
		   
		   if(document.getElementsByName("strAttendentPassCheckBox")[0].checked)
		      count=1;
		  }
		 if(count== 0)
		 {
		  alert("Atleast One Pass should be selected");
		  retVal=false;
		 }
        if(retVal){
	 		document.forms[0].hmode.value = "INSERT";
			document.forms[0].submit();
		}else{
             return false;
          }
     }else{
        retVal = hisValidator.validate();
        if(!retVal) return false;
     	var o = document.getElementsByName("strchkvisit");
	 	var count= parseInt(0);
     	for(var i=0;i<o.length;i++)
	  	{
		
			if(o[i].checked)
			{
			count= parseInt(count) + 1;
			}
     	}
       if(count == 0) 
		 {
		 alert("Atleast One Pass should be selected");
		 retVal=false;
		 }
		    if(retVal){
	 		document.forms[0].hmode.value = "INSERT";
			document.forms[0].submit();
		}else{
             return false;
          }
         }
 }
               
function goFunc(){

			if(!validateThroughRegExp(document.forms[0].strCrNo,1))
		 	{
		 		return;
		 	}
			var hisValidator = new HISValidator("visitorpassTransBean");
			hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field");
			hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength, "CR No. should be "+document.forms[0].strCrNo.maxLength+" digits");
			var retVal = hisValidator.validate();
			
			hisValidator.clearAllValidations();
			
			 if(retVal)
			  {
				 	document.forms[0].hmode.value="GO";
					document.forms[0].submit();
				 return true;
			  }
			  else
			  {
			  	return false;
			  }
}

function goFuncOnEnter(obj){
	
	var flag=validateData(obj,5);
	
	if(flag){
			if(obj.keyCode==13)
			{
				var flag1=goFunc();
				if(flag1)
				{
					document.forms[0].hmode.value="GO";
					document.forms[0].submit();
				}
				else
				{
					return false;
				}
				
			}
		}
		else
		{
			return false;
		}
}

function chkBoxClick(obj,index){
	
		 var retVal = false;
  	if(obj.checked){
	    document.forms[0].chkIndx.value=index-1; 
	    var indx=document.forms[0].chkIndx.value;
	     if(document.forms[0].strNewRenew.value==1){
	   
	      {
	 	  var from = document.getElementsByName("strhValidFrom")[indx].value;
	      var to = document.getElementsByName("strValidUpto0")[indx].value;
	    
	  	  retVal = compareDate(from,to);
	  	  if(retVal.mode==0){
	 		alert('Sorry!!! You cannot Renew,pass is still valid');
	 		return false;
		  }if(retVal.mode==1){
			alert('Sorry!!! You cannot Renew,pass is still valid');
	 		return false;
		  } 
	    }
	  }
		document.forms[0].strchkvisit.value=index;
		document.getElementById("issueid0"+index).style.display="none";
		document.getElementById("issueid1"+index).style.display="block";
		document.getElementById("fromid0"+index).style.display="none";
		document.getElementById("fromid1"+index).style.display="block";
		document.getElementById("uptoid2"+index).style.display="none";
		document.getElementById("uptoid1"+index).style.display="block";
		if(document.getElementById("strPaidAmount1"+index))
			document.getElementById("strPaidAmount1"+index).readOnly=false;
		
	}else{
		
		document.getElementById("issueid0"+index).style.display="block";
		document.getElementById("issueid1"+index).style.display="none";
		document.getElementById("fromid0"+index).style.display="block";
		document.getElementById("fromid1"+index).style.display="none";
		document.getElementById("uptoid2"+index).style.display="block";
		document.getElementById("uptoid1"+index).style.display="none";
		if(document.getElementById("strPaidAmount1"+index))
			document.getElementById("strPaidAmount1"+index).readOnly=true;
	}
	
}
function checkBox(obj,index){
	if(obj.checked){
	    document.forms[0].chkIndx.value=index-1; 
	    document.forms[0].strchkvisit.value=index;
		if(document.getElementById("strPaidAmt0"+index))
			document.getElementById("strPaidAmt0"+index).readOnly=false;
		
		
	}else{
	if(document.getElementById("strPaidAmt0"+index))
			document.getElementById("strPaidAmt0"+index).readOnly=true;
	}
	

}

function view(){
	if(document.forms[0].strCrNo.value!="")
	{
		document.forms[0].strCrNo.readOnly=true;
		document.forms[0].strNewRenew.value=document.forms[0].strNewRenewHidden.value
		
		if(document.forms[0].strNewRenew.value==0)
		{
			var obj=document.getElementById("id4");
			obj.style.display="block";
			document.getElementById("id6").style.display="none";
			document.getElementById("id5").style.display="block";	
			document.getElementById("admDtlTld").style.display="block";
			document.getElementById("mandCRId").style.display="none";
		}
		else
		{
			var obj=document.getElementById("id4");
			obj.style.display="block";
			document.getElementById("id6").style.display="block";
			document.getElementById("id5").style.display="none";
			document.getElementById("admDtlTld").style.display="block";
			document.getElementById("mandCRId").style.display="none";
		}
	}
	else
	{
		document.forms[0].strCrNo.focus();
		var obj=document.getElementById("admDtlTldglbdiv");
		obj.style.display="none";
		obj=document.getElementById("id5");
		obj.style.display="none";
	}
	
	
	if(document.forms[0].strPatStatusCode.value==13)
	{
			obj=document.getElementById("id4");
			obj.style.display="block";
			document.forms[0].strCrNo.readOnly=true;
			obj=document.getElementById("admDtlTldglbdiv");
			obj.style.display="none";
			obj=document.getElementById("id5");
			obj.style.display="none";
			document.getElementById("id6").style.display="none";
			//document.getElementById("id1").style.display="none";
	}		
	if(document.forms[0].strPatStatusCode.value==12)
	{
			obj=document.getElementById("id4");
			obj.style.display="block";
			document.forms[0].strCrNo.readOnly=true;
			obj=document.getElementById("admDtlTldglbdiv");
			obj.style.display="none";
			obj=document.getElementById("id5");
			obj.style.display="none";
			document.getElementById("id6").style.display="none";
			//document.getElementById("id1").style.display="none";
	}
	
}
function fun()
{
	document.forms[0].strCrNo.readOnly=false;
	document.forms[0].strCrNo.value="";
	document.getElementById("mandCRId").style.display="";
	document.getElementById("id4").style.display="block";
	document.forms[0].hmode.value="CLEAR";
	document.forms[0].submit();
	
}
function func()
{
	document.forms[0].strCrNo.readOnly=false;
	document.forms[0].strCrNo.value="";
	document.getElementById("mandCRId").style.display="";
	//document.getElementById("id4").innerHTML="";
	document.getElementById("id4").style.display="none";
	document.getElementById("id5").style.display="none";
	document.getElementById("id6").style.display="none";
	document.getElementById("admDtlTldglbdiv").style.display="none";
	document.getElementById("admDtlTld").style.display="none";
}
function cancelPage(){
	document.forms[0].hmode.value="INITIALPAGE";
	document.forms[0].submit();

}

function  attendancePass()
{
	if(document.getElementsByName("strAttendentPassCheckBox")[0].checked)
	{
		document.getElementsByName("strAttendentPassCheckBox")[0].value="1";
	}
	else
	{
		document.getElementsByName("strAttendentPassCheckBox")[0].value="0";
	}
}


</script>
</head>
<body onload="view();">
<html:form action="/transactions/VisitorPassTransCNT.cnt" method="post" onsubmit="return goFunc();">
<div class="errMsg" id="errMsg"><bean:write name="visitorpassTransBean" property="strMsgString"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="visitorpassTransBean" property="strNormalMsg"/></div>
<tag:tab tabLabel="Visitor Pass Details" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="0px">
		<tr class="HEADER">
			<td colspan="8">Visitor Pass</td>
			<td width="10%" align='center'>
			  <select name="strNewRenew" onchange="func();">
			    <option value="1">Renew</option>
			    <option value="0">New</option>
		      </select>
		    </td>
		</tr>
	</table>
	
	<div id="CrNoId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<crNo:crNo value="${visitorpassTransBean.strCrNo}" js="onkeypress='return goFuncOnEnter(event);return validateData(event,5);'"></crNo:crNo>
			<img style='cursor:pointer;' src="../../hisglobal/images/Go.png" align="top" onclick="goFunc();"> </td>
			
	</tr>
		</table></div>
	  <div id="id4" style="display:none;">
		<table class="TABLEWIDTH" align="center" cellspacing="1px">
			<tr><td colspan="8" class="TITLE">Patient Demographic Details</td>
			<pDtl:patDtl crNo="${visitorpassTransBean.strCrNo}" address="false"></pDtl:patDtl>
		</table> 
		</div>
			
		 <div id="admDtlTldglbdiv">
              <table class="TABLEWIDTH" align="center" cellspacing='1px'>
               <tr><td colspan="8" class="TITLE">Admission Details</td>
               </tr></table>
         </div>
         <div id="admDtlTld" style="display:none;">
               <aDtl:addDtl crNo="${visitorpassTransBean.strCrNo}"></aDtl:addDtl>
         </div>
	
	     <div id="id5">
	          <bean:write name="visitorpassTransBean" property="visitorNewPassDetail" filter="false"/> 
	   </div>
	<div id="id6">
		<bean:write name="visitorpassTransBean" property="visitorPassDetail" filter="false"/>	
	</div>
		
	<table class="TABLEWIDTH" align="center" cellspacing="0px">
	<tr class="FOOTER"> 
    <td colspan="8"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
		<td align="center">
			<logic:notEmpty name="visitorpassTransBean" property="strCrNo">
				<img style='cursor:pointer;' src="../../hisglobal/images/btn-sv.png" title="To save the Record" onClick="return validate1();">
			</logic:notEmpty>
			<img style='cursor:pointer;' src="../../hisglobal/images/btn-clr.png" title="Clear the Record" onClick="fun();">
			<img style='cursor:pointer;' src="../../hisglobal/images/btn-ccl.png" title="Go back to the Main Page" onClick="cancelPage();">
		</td>
	</tr>
</table>
<input type="hidden" name="hmode" value=""/>	
<input type="hidden" name="chkIndx" value=""/>
<input type="hidden" name="strNewRenewHidden" value="${visitorpassTransBean.strNewRenew}"/>
<input type="hidden" name="strAdmnNo" value="${visitorpassTransBean.strAdmnNo}"/>
<input type="hidden" name="strcountFree" value="${visitorpassTransBean.strcountFree}"/>
<input type="hidden" name="strcountPass" value="${visitorpassTransBean.strcountPass}"/>
<input type="hidden" name="strNoFreePass" value="${visitorpassTransBean.strNoFreePass}"/>
<input type="hidden" name="strNoPaidPass" value="${visitorpassTransBean.strNoPaidPass}"/>
<input type="hidden" name="strRFPassValidity" value="${visitorpassTransBean.strRFPassValidity}"/>
<input type="hidden" name="strRPPassValidity" value="${visitorpassTransBean.strRPPassValidity}"/>
<input type="hidden" name="strPatStatusCode" value="${visitorpassTransBean.strPatStatusCode}"/>
<input type="hidden" name="strCount" value="${visitorpassTransBean.strCount}"/>

</html:form>
	<tag:autoIndex></tag:autoIndex>
</body>
</html>