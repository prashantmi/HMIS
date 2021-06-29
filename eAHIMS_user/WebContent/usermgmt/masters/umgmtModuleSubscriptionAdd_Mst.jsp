<jsp:useBean id="beanObj"
	class="usermgmt.masters.UmgmtModuleSubscriptionBean_Mst"
	scope="request" />
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%
	String hmode = "";
	String title = "";
	String userSeatId = "";


	hmode = request.getParameter("hmode");
	if (hmode == null)
		hmode = "SAVE";

	if (hmode.equals("SAVE")) {
		title = "Add";

	}
	if (hmode.equals("UPDATE")) {
		title = "Modify";

	}
	title = request.getParameter("tile") == null ? "Add" : request
			.getParameter("tile");

	System.out.println("title----." + title);

String hospitalcode =(String)session.getAttribute("HOSPITAL_CODE");
System.out.println("Hospital"+hospitalcode);

String seatId =(String)session.getAttribute("SEAT_ID");
System.out.println("seat Id is"+seatId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Module Subscription Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js"
	type="text/JavaScript"></script>
<script language="JavaScript" src="../js/functionality.js"
	type="text/JavaScript"></script>
<style type="text/css">

@import url(../../css/calendar-blue2.css);
</style>
<script language="JavaScript" src="../js/calendar.js"></script>



<script language="JavaScript" type="text/JavaScript"><!--
 function focusOnLoad()
{

	if(document.forms[0].strHospCombo.value=="0")
	 {document.forms[0].strHospCombo.focus();}
}    
  
function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{   
	//alert("hmode is" +hmode);
	   
	   //document.form1.strHospCombo.value="0";	    
		document.form1.hmode.value = hmode;
		document.form1.submit();
		
	}
}

function submitMode(hmode)
{
//alert("hmode is" +hmode);

	document.forms[0].hmode.value = hmode;
	//alert("hmode is" +hmode);
	
	document.forms[0].submit();
	//alert("hmode is" +hmode);
	
}

function getModuleSubs(these)
{

document.forms[0].strHospCombo.value=these.value;
document.form1.strSelectedHosp.value=these.value; 

//alert(document.form1.strSelectedHosp.value);
submitMode("TEMP");
//alert(these.value);

}

function cancelForm(e)
{
		if(e.type=="click"||e.keyCode==13)
		{
			   document.forms[0].action="../../startup/content.jsp";
			   document.forms[0].submit();
			
		}		
}

function savePage(e,formObj,hmode)
{
    //alert("mode is"+hmode);
	var listObj = document.form1.strCheckbox;
	var str =document.form1.strCheckbox.length;
 
	var strCheckedList="";
	var strUnCheckedList="";
	//alert(str);
	var checkedval="";
	var abc="";
	//alert(document.form1.strCheckbox[0].checked);
	for(var i=0 ;i<str;i++)
	{
	//for checking if dates are enterd or not 
	
	
	  if(document.form1.strCheckbox[i].checked) 
        {
        if(document.form1.strEffectFrom[i].value=="")
	{
	alert("enter the From date ");
	document.form1.strEffectFrom[i].focus();
	return false;
	}
	if(document.form1.strEffectTo[i].value=="")
	{
	alert("enter the To date ");
	 document.form1.strEffectFrom[i].focus();
	 return false;
	}
          if(strCheckedList=="")
             {
                  strCheckedList=document.forms[0].strCheckbox[i].value+'@'+i;
			 }		
		  else
			 {      
			 strCheckedList=strCheckedList+'^'+document.forms[0].strCheckbox[i].value+'@'+i;
     		 }
     	 }       
        else
         {
           
                    if(strUnCheckedList=="")
                           {
                                strUnCheckedList=document.forms[0].strCheckbox[i].value+'@'+i;
                           }
                     else
                           {
                                strUnCheckedList=strUnCheckedList+'^'+document.forms[0].strCheckbox[i].value+'@'+i;
                           }
                }
 
                 
         }      
	
	

	 //alert("checked list "+strCheckedList);
	// alert("Un checked list "+strUnCheckedList);
	
	document.form1.strCheckedList.value =strCheckedList;	
	document.form1.strUnCheckedList.value =strUnCheckedList;	
	
	document.forms[0].hmode.value = hmode;
	document.forms[0].submit();
      }
  function checkIfmandatory(valueOfObj)
  {
  var strTotalList=valueOfObj.value.split("@");
    var strHospCodewithMod=strTotalList[0];
           var strHospCode=strHospCodewithMod;
           var strMandatoryVal='1';
            var strIsManDatory=trim(strTotalList[1]).split(',')[0];
           
           
           //alert('mandatory value is:::::::   '+strIsManDatory);
         
           if(strIsManDatory=='1')
                {
                 //alert("equal");
                     alert("This is mandatory module.You can't unsubscribe it.");
                    
                     valueOfObj.checked=true;
                      valueOfObj.focus();
                     return false;
               
                }
            else
              {
              
               //alert('not equal');
              		return true;
            		 
              }
            
            
  }
   
function checkIfmandatoryOld(valueOfObj)
{
			alert(valueOfObj.value);
 			var strTotalList=valueOfObj.value.split("@");
           alert(strTotalList.length);
           var strHospCodewithMod=strTotalList[0];
           var strHospCode=strHospCodewithMod;
           var strMandatoryVal="1";
           alert(strHospCode);
           
           var strIsManDatory=strTotalList[1];
           
           alert('mandatory value is:::::'+strTotalList[1]);
         
           if(strTotalList[1]=="1")
                {
                  alert('not equal');
              		return true;
               
                }
            else
              {
            		 alert("equal");
                     alert("cannot alter a mandatory field");
                     valueOfObj.focus();
                     return false;
              }
            
              }
function trim(str){
    return str.split(' ').join();
}


</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
</head>

<body width="100%" topmargin="0" onload="focusOnLoad()">
<form name="form1" method="post" action="umgmtModuleSubscriptionCnt_Mst.jsp">



<table width="100%" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="addHeaderNew" colspan=2 align="left">Module
		Subscription Form</td>


	</tr>
</table>

<table width="100%" align="center" border="0" cellspacing="1"
	cellpadding="0">




	<tr>
		<td class="adddatalabelNewRight" nowrap width='50%'>
		<div align="right"><font color='red'>*</font>Hospital&nbsp;&nbsp;</div>
		</td>
		<td class="adddatavalueNew" nowrap width='50%'>
		<div align="left"><select name="strHospCombo" onChange="getModuleSubs(this);"
			style="font-family: Verdana; font-size: 12px; font-style: normal; text-decoration: none; height: 20px; width: 115px;" >
		<%=beanObj.getHospitalCombo()%>
		</select></div>
		</td>
	</tr>
</table>


<table width="100%" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td class="addHeaderNew" colspan=2 align="left">Select Modules
		for Subscription</td>


	</tr>
	<tr>
		<td class="adddatalabelNewRight" nowrap width='50%'></td>
	</tr>
</table>





<table width="100%" align="center">
	<tr>
		
		
		
	</tr>
	<tr class="ShadedSubTitleTagImage" height="17">

		<td width='25%'><b>List of Modules</b></td>
		<td width='25%'><b>Subscribe / Un Subscribe</b></td>
		<td width='25%'><b>From Date</b></td>
		<td width='25%'><b>To Date</b></td>

	</tr>
	 <div align="left"> 
           	<%=beanObj.getListOfModules(request)%>
</table>


<table width="100%" align="center" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4">

		<div align="center">
		<a 	style=cursor:hand><img src="../../images/btn-sv.png"   class="link" tabindex=0 onClick='savePage(event,form1,"SAVE")' onkeyPress='savePage(event,form1,"SAVE")'></a> 
			<a style="cursor: hand"><img src="../../images/btn-clr.png"
			class="link" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")'
			onkeyPress='resetForm(event,form1,"DEFAULT")'></a> <a
			style="cursor: hand"><img src="../../images/btn-ccl.png"
			class="link" tabindex=0 onClick='cancelForm(event)'
			onkeyPress='cancelForm(event)'></a></div>

		</td>

	</tr>
</table>

 <input type="hidden" name="title" value=<%=title%>> 
 <input type="hidden" name="hmode">
 <input type="hidden" name="strSelectedHosp" value="">
 <input type="hidden" name="strCheckedList" value="">
 <input type="hidden" name="strUnCheckedList" value="">
  <input type="hidden" name="strFomDate" value="">
   <input type="hidden" name="strToDate" value="">
<input type="hidden" name="seatId" value='<%=session.getAttribute("SEAT_ID")%>' />





</FORM>
</BODY>
</HTML>
