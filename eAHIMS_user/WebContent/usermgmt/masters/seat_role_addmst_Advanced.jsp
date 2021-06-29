<jsp:useBean id="newseat" class="usermgmt.masters.SeatRoleAddMstAdvancedBean" scope="request"/>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>

	<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
	<script language="JavaScript" src="../js/calendar.js"></script>
	
<%
String hmode = "";
String title = "";
String userSeatId	=	"";

hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";

if(hmode.equals("SAVE"))
{
	title="Add";
	
}
if(hmode.equals("UPDATE"))
{
	title = "Modify";

}
%>	
<%
String hospitalcode =(String)session.getAttribute("HOSPITAL_CODE");
System.out.println("Hospital"+hospitalcode);

String seatId =(String)session.getAttribute("SEAT_ID");
System.out.println("seat Id is"+seatId);
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Role Seat Table Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- <script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>-->




<script>


function focusOnLoad()
	{


	if(document.forms[0].group.value=="0")
	    	document.forms[0].group.focus();
		else 
	if(document.forms[0].seat.value=="0")
	   	document.forms[0].seat.focus();
	  else
	  document.getElementById("saveid").focus();
	  

}    

	
window.history.foward();

function validateSeatModuleRoleRule()
{
var noOfSelectedRoles=document.getElementById("noOfSelectedRoles").value;
var noOfUnSelectedRoles=document.getElementById("noOfUnSelectedRoles").value;
var selectedCheck=false;
var unSelectedCheck=false;
//alert(noOfSelectedRoles+"----------"+noOfUnSelectedRoles);
var concateRoleId="";
var concateModuleId="";
var arrayOfModules="";
var SMRCheck=true;
var returnCheck=true;
var dateCheck1=true;
var dateCheck2=true;
var dateCheck3=true;
var dateCheck4=true;
var dateCheck5=true;
var dateCheck6=true;


for(var i=1 ;i <= noOfSelectedRoles ; i++)
  {
  var checkBoxName="selectedCheckBox"+i;
   var fromDateName="selectedFromDate"+i;
    var toDateName="selectedToDate"+i;
     
   var checkBoxElem=document.getElementById(checkBoxName);
   var fromDateElem=document.getElementById(fromDateName);
   var toDateElem=document.getElementById(toDateName);
 
 
  var arrayOValues=checkBoxElem.value.split("#");
  
  if(checkBoxElem.checked==true)
  	{
  	
  	selectedCheck=true

if(toDateElem.value!="")
    concateRoleId+=arrayOValues[0]+"@"+fromDateElem.value+"@"+toDateElem.value+"#";
else
    concateRoleId+=arrayOValues[0]+"@"+fromDateElem.value+"@"+"NA"+"#";

    concateModuleId+=arrayOValues[1]+"#";  
  	
  	
  //	dateCheck1=compareTwodates(document.forms[0].sysDate.value,fromDateElem.value);
 
 if(toDateElem.value!="")
 	{ 
  	dateCheck2=compareTwodates(document.forms[0].sysDate.value,toDateElem.value);
  	dateCheck3=compareTwodates(fromDateElem.value,toDateElem.value);
  	}
  	
  	
  	if(dateCheck2==false)
    {
    alert("Effective To Date of "+arrayOValues[2]+" cannot be Less Than the Current Date")
    break;
    }
    else
    if(dateCheck3==false)
    {
    alert("Effective From Date of "+arrayOValues[2]+" cannot be Less Than the Effective To Date")
    break;
    }
  	
   
     
    }
 
  }//for closed of selected 

for(var i=1 ;i <= noOfUnSelectedRoles ; i++)
  {
  	var checkBoxName="unSelectedCheckBox"+i;
	var fromDateName="unSelectedFromDate"+i;
    var toDateName="unSelectedToDate"+i;
 
 
   var checkBoxElem=document.getElementById(checkBoxName);
   var fromDateElem=document.getElementById(fromDateName);
   var toDateElem=document.getElementById(toDateName);
 
 
 var arrayOValues=checkBoxElem.value.split("#");
  
  
  if(checkBoxElem.checked==true)
  	  {	
  	   
  	   unSelectedCheck=true
     
       concateModuleId+=arrayOValues[1]+"#";  
  	  
if(toDateElem.value!="")
    concateRoleId+=arrayOValues[0]+"@"+fromDateElem.value+"@"+toDateElem.value+"#";
else
    concateRoleId+=arrayOValues[0]+"@"+fromDateElem.value+"@"+"NA"+"#";
  	  
  	  
  	  
  	dateCheck4=compareTwodates(document.forms[0].sysDate.value,fromDateElem.value);
 

 
if(toDateElem.value!="")
	{
  	dateCheck5=compareTwodates(document.forms[0].sysDate.value,toDateElem.value);
	dateCheck6=compareTwodates(fromDateElem.value,toDateElem.value);
	}
	
  
    if(dateCheck4==false && dateCheck2!=false && dateCheck3!=false)
    {
    alert("Effective From Date of "+arrayOValues[2]+" cannot be Less Than the Current Date");
    break;
    }
       
    else
    if(dateCheck5==false && dateCheck2!=false && dateCheck3!=false )
    {
    alert("Effective To Date of "+arrayOValues[2]+" cannot be Less Than the Current Date");
     break;
    }
    else
    if(dateCheck6 ==false && dateCheck2!=false && dateCheck3!=false)
    {
    alert("Effective From Date of "+arrayOValues[2]+" cannot be Less Than the Effective To Date");
     break;
    }
    
    
       
   
      }
      
  }
//alert("concateRoleId-----"+concateRoleId)
//alert("concateModuleId-----"+concateModuleId)


arrayOfModules=concateModuleId.split("#");

for(var i=0 ;i < arrayOfModules.length ; i++)
{
 
   for(var j=i+1 ;j < arrayOfModules.length ; j++)
    {
 
 if(arrayOfModules[i]==arrayOfModules[j])
    SMRCheck=false;
    }
}

//alert("smrcheck---"+SMRCheck)

// alert("111111"+selectedCheck)
//   alert("2222222"+unSelectedCheck)
 
if(!(selectedCheck || unSelectedCheck))
	{
	alert("Please Select Some Role");
	returnCheck=false;
	}
else
if(!SMRCheck)
	{
	alert("Multiple Roles of a Module cannot be Selected");
    returnCheck=false;
    }    
    else
 if(!dateCheck2 || !dateCheck3 || !dateCheck4 || !dateCheck5 || !dateCheck6)   
    {
     returnCheck=false;
    }
    else    
    document.form1.selectedValues.value =concateRoleId;
    
    
return returnCheck;
}

function savePage(e,formObj,hmode)
{

 if(e.type=="click" || e.keyCode==13)
  {	
	
	

	
	if(document.getElementsByName("group")[0].selectedIndex=="0")
	     {
			alert("Group Combo is Mandatory field");
			document.form1.group.focus();
		 }
	else 
	if(document.getElementsByName("seat")[0].value=="0")
	    {
			alert("Seat Combo is Mandatory field");
			document.form1.seat.focus();
	    }
	    else
	if(!validateSeatModuleRoleRule())
		{
		//alert("false");
		}					
	else
		{
		
	document.form1.hmode.value = hmode;
	document.form1.submit();
		}
  }	
}


	
function submitMode(hmode,mode)
{	
if(mode=="GROUP")
	document.forms[0].seat.value="0";

	document.forms[0].hmode.value = hmode;	
	document.forms[0].submit();
	
}

function moveRightSingle(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	
	
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstmenu;
		secondMenuObj = document.forms[0].secondmenu;	
	}

	
	
	var totalElement1  = firstMenuObj.length
	var totalElement2  = secondMenuObj.length
	var val1 = "";
	var val2 = "";	
	var t1 = 0;
	var left="";
	var right="";
	var leftFull = "";
	var rightFull = "";	
	var check=true;
	
	for(var i=0;i<totalElement1;i++)
	{
		if(firstMenuObj.options[i].selected)
		{
		
		
			leftFull = firstMenuObj.options[i].value.split("#");
			
			
			left =leftFull[1]; 			
			   
		   for(var j=0 ;j< totalElement2 ;j++)
		      {
		    rightFull = secondMenuObj.options[j].value.split("#");
			right=rightFull[1];
			
		    
		    
		    if(left==right)
		       check=false;
		      
		      
		      }
		   
		   
													
		}
		
		if(check==false)
		break;
	}
	
	
	
	
	for(var i=0;i<totalElement1;i++)
	{
	var counter=0;
  if(firstMenuObj.options[i].selected)
       {
		
			
			leftFull = firstMenuObj.options[i].value.split("#");
			left =leftFull[1]; 
			
			//alert(leftFull);			
		//	alert("left---"+left);
			
			
			for(var j=i+1 ; j < totalElement1 ; j++)
			   {
			   
   if(firstMenuObj.options[j].selected)
                        {
		    rightFull = firstMenuObj.options[j].value.split("#");
			right=rightFull[1];
			
			//alert(rightFull);			
		//	alert("right----"+right);
			
		//	 alert("counter1----"+counter); 
			if(left==right)
			counter++;
		//	 alert("counter2----"+counter); 
			 
			 
		 
	                    }	  
			   
			   }
			   
		}	  
		
	//	 alert("counter3----"+counter); 
		 
		if(parseInt(counter) > 0)
		check=false;
		 
		 
	//	  alert("check----"+check); 
		if(check==false)
			 break;			
		   
		   
														
		
	}
	
	
	
	
if(check==true)
	
{	
	
						
	for(var i=0;i<totalElement1;i++)
	{
		if(firstMenuObj.options[i].selected)
		{
		
		
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
		
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;	
			document.forms[0].Secondvalues.value += val1;												
		}
	}
}	
if(check==false)
 alert("Multiple Roles of a Module cannot be Selected");


	deleteThis(secondMenuObj,firstMenuObj)
}



function moveRightAll(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstmenu;
		secondMenuObj = document.forms[0].secondmenu;	
	}
	
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	var check=true;
	
	var totalElement1  = firstMenuObj.length
	var totalElement2  = secondMenuObj.length
	
	
	
		for(var i=0;i<totalElement1;i++)
	{
		
	
		
			leftFull = firstMenuObj.options[i].value.split("#");
			left =leftFull[1]; 			
			
			   
		   for(var j=0 ;j< totalElement2 ;j++)
		      {
		    rightFull = secondMenuObj.options[j].value.split("#");
			right=rightFull[1];
			    
		    if(left==right)
		       check=false;
		      
		      
		      }
		 													
		
		
		if(check==false)
		break;
	}
	
	
	
	
	
	
	
	
	
	for(var i=0;i<totalElement;i++)
	{
		
			
			
			leftFull = firstMenuObj.options[i].value.split("#");
			left =leftFull[1]; 			
			
			//alert(leftFull);			
		//	alert(left);
			
			
			for(var j=i+1 ; j < totalElement ; j++)
			   {
			
			
			
			rightFull = firstMenuObj.options[j].value.split("#");
			right=rightFull[1];
			
			
		//	alert(rightFull);			
		//	alert(right);
			
			if(left==right)
			 check=false;
			 
			 
		//	  alert(check); 
			   
			   }
		if(check==false)
			 break;			
		   
		   
														
		
	}
			
			if(check==true)
			{			
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		
	} 
	         }else
	         if(check==false)
	         alert("Multiple Roles of a Module cannot be Selected");
	
	deleteThis(secondMenuObj,firstMenuObj)
}


function deleteThis(srcMenuObj,targetMenuObj)
{	
	
	
	var t =0;
	var val1 = "";
	var val2 = "";	
	
	var len  = targetMenuObj.length;

	var len2 = srcMenuObj.length;
	
		
	var a1 = new Array(len);
	var a2 = new Array(len);
	
	var a3 = new Array(len2);
	
	
	
	for(var i=0;i<len;i++)
	{		
		a1[i]= targetMenuObj.options[i].value;
		a2[i]= targetMenuObj.options[i].text;	
		
	}
	
	
	for( i=0;i<len2;i++)
	{		
		a3[i]= srcMenuObj.options[i].value;
	}
	
	
	
	targetMenuObj.length = 0;
	
	var counter = 0;
	
	
	var k = 0;
	
	var flag = 0;
		
	for(i=0;i<len;i++)
	{		
		flag = 0;
		for(k=0;k<len2;k++)
		{		
			if(a1[i]==a3[k])
			{	
				flag = 1;					
			}					
		}		
		if(flag==0)
		{
			targetMenuObj.length = (counter+1);
			targetMenuObj.options[counter].value = a1[i];
			targetMenuObj.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
	
}

function moveLeftSingle(listNo)
{
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstmenu;
		secondMenuObj = document.forms[0].secondmenu;	
	}
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
	
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		if(secondMenuObj.options[i].selected)
		{
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		}
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function moveLeftAll(listNo)
{
	
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstmenu;
		secondMenuObj = document.forms[0].secondmenu;	
	}
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.form1.group.value=0;
		document.form1.seat.value=0;	
		document.form1.hmode.value = hmode;
		document.form1.submit();
		
	}
}

function cancelForm(e)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			   document.forms[0].action="../../startup/content.jsp";
			   document.forms[0].submit();
			
		}		
	}


</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>

<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()">
<form name="form1" method="post" action="seat_role_cntMst_Advanced.jsp">		

  
	<table width="100%" align="center">
     <tr> 
	<td class="addheader" colspan=2 align="left">Advanced Seat Role Master </td>
	 </tr> 
	
	
	
	 
	</table>
	 <table width="100%" align="center">
	
<tr>		
	<td class="adddatalabel" width="50%"> 
	<div align="right"><font color='red'>*</font>Group</div>
	</td>
	<td class="adddatavalue" width="50%"> 
	<%if(hmode.equals("UPDATE"))
	{
	%>
	<input type='text' name='a' value='<%=newseat.getGroup()%>' readOnly>
	
	<%
	}
	else
	{
	%>
	<div align="left"> 
	<select name="group" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:115px;" onChange="submitMode('TEMP','GROUP');">
   	<%=newseat.getGroupCombo()%> 
	</select>
	</div>
	
	<%
	}
	%>
	</td>
</tr>
  
  
    <tr> 		
      <td class="adddatalabel" width="50%"> 
        <div align="right"><font color='red'>*</font>Seat</div>
      </td>
      <td class="adddatavalue" width="50%"> 
      
      
    <%if(hmode.equals("UPDATE"))
	{
	%>
	<input type='text'  name='b' value='<%=newseat.getSeat()%>' readOnly>
	
	<%
	}
	else
	{
	%>
	 <div align="left"> 
        
         <select name="seat" style="font-family:Verdana; font-size:12px; font-style:normal; 
         height:20px; width:115px; text-decoration:none;" onChange="submitMode('TEMP','SEAT');">
            <%=newseat.getSeatCombo()%> 
            
          
          </select>
        </div>
	
	<%
	}
	%>
       
      
</table>
<!--  Code for creating the list boxes for the seats -->
    
   
   
   
   <table width="100%" align="center">
   
	 <tr>		
      <td class="adddatalabel" width='10%'><b>Select</b></td>          
                  <td class="adddatalabel" width='30%'><font color='red'>*</font><b>Roles</b></td>
                  		<td class="adddatalabel" width='30%'><font color='red'>*</font><b>Effective From</b></td>
                        	<td class="adddatalabel" width='30%'><b>Effective To</b></td>
      </tr>
     <%=newseat.getListOfSelectedRoles(request)%> 
	 <%=newseat.getListOfUnSelectedRoles(request)%>
	 
	
	  </table>
    <table>
   <tr>
	<td class="addheader" width="100%" colspan='2' style="width: 588px"></td>
	
	</tr> 
  
   <tr> 

      <td  style="width: 1569px"> 
        <div align="center"> 
		<a 	style=cursor:hand><img id="saveid" src="../../images/Save.gif"   class="link" tabindex=0 onClick='savePage(event,form1,"SAVE")' onkeyPress='savePage(event,form1,"SAVE")'></a> 
		<a 	style=cursor:hand><img src="../../images/Clear.gif" class="link" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'></a>
        <a 	style=cursor:hand><img src="../../images/Cancel.gif" class="link" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'></a> 
       
		
        </div>
      </td></tr>
      <tr>
      <td>
      <div align="center"><font color="#FF3300" size='3'><%=newseat.getStatus()%></font></div>

    </tr>
    
  </table>
  
<input type="hidden" name="hmode">
<input type="hidden" name="seatName" value="<%=newseat.getSeatName()%>">
<%--<input type="hidden" name="seatId" value="<%=newseat.getSeatId()%>">--%>
<input type="hidden" name="module" 	value="<%=newseat.getModule()%>">
<input type="hidden" name="role" 	value="<%=newseat.getRole()%>">
<input type="hidden" name="table" 	value="<%=newseat.getTable()%>">
<input type="hidden" name="column" 	value="<%=newseat.getColumn()%>">
<input type="hidden" name="hospitalcode" value='<%=session.getAttribute("HOSPITAL_CODE")%>' />
<input type="hidden" name="seatId" value='<%=session.getAttribute("SEAT_ID")%>' />
<input type="hidden" name="selectedValues" 	value="">
<input type="hidden" name="Secondvalues" value="">
<input type="hidden" name="sysDate" value="<%=newseat.getSysdate()%>">

   				
    </FORM>
	</BODY>
</HTML>
   
