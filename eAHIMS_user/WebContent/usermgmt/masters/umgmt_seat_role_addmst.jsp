<jsp:useBean id="newseat" class="usermgmt.masters.UmgmtSeatRoleAddMstBean" scope="request"/>

<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

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
<script type="text/javascript">
window.history.forward();
</script>
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
	    	document.forms[0].firstmenu.focus();
	    	

}    




function savePage(e,formObj,hmode)
{

 if(e.type=="click" || e.keyCode==13)
  {	
	var listObj = document.form1.secondmenu;
	var str ="";
	var concateValue="";
	for(var i=0;i<listObj.length;i++)
	{
	  concateValue=listObj[i].value.split("#");
		str += concateValue[0]+"^";	
	}	
	

	
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
  if (str=="")
      {
				alert("Data in Right List Box must exist");	
				document.form1.secondmenu.focus();
	  }		
					
	else
		{
	document.form1.selectedValues.value =str;	
	document.form1.hmode.value = hmode;
	document.form1.submit();
		}
  }	
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
function submitMode(hmode)
{	

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
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
</head>
<!--  
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()">-->
<body  width="100%" topmargin="0" onload="focusOnLoad()">
<form name="form1" method="post" action="umgmt_seat_role_cntMst.jsp">		

  
	<table  class="topmargin" width="97%" align="center" cellspacing="0" cellpadding="0">
     <tr> 
	<td class="ShadedSubTitleTagImage" colspan=2 align="left">Seat Role Master </td>
	 </tr> 
	 
	</table>
	<table class="formbg" width="97%" align="center" border="0" cellspacing="0"	cellpadding="0">
	
<tr>		
	<td class="label" width="50%"> 
	<div align="right"><font color='red'>*</font>Group</div>
	</td>
	<td class="control" width="50%"> 
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
	<select name="group" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:115px;" onChange="submitMode('TEMP');">
   	<%=newseat.getGroupCombo()%> 
	</select>
	</div>
	
	<%
	}
	%>
	</td>
</tr>
  
  
    <tr> 		
      <td class="label" width="50%"> 
        <div align="right"><font color='red'>*</font>Seat</div>
      </td>
      <td class="control" width="50%"> 
      
      
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
         height:20px; width:115px; text-decoration:none;" onChange="submitMode('TEMP');">
            <%=newseat.getSeatCombo()%> 
            
          
          </select>
        </div>
	
	<%
	}
	%>
       
      
</table>
<!--  Code for creating the list boxes for the seats -->
   
   <table class="formbg" width="97%" cellspacing="0" cellpadding="0" align="center">
	 <tr class="ShadedSubtitletagImage">
	      <td align="center" colspan="4" width='100%'><font color='red'>*</font>Roles</td>
      </tr>
	 <tr>		
      <td class="control" width='20%'>         
      </td>
      <td class="control" width='20%'> 
        <div align="left"> 
  
       <%=newseat.getColumnCombo()%> 
                   
         
        </div>
      </td>
	   <td class="control"  width='20%'> 
        <div align="left"> 
			       
			  <img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
              <img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll("2");'/> 	
              <br>
              <br>
              <img src="../../images/back4.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
              <img src="../../images/backAll4.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
               
        </div>
      </td>
	  <td class="control"  width='20%'> 
          <div align="left"> 
      
          <%= newseat.getsecondColumnCombo(request)%>          
 
       
         </div>
      </td>
	  
    </tr>
	  </table>

  
     <table width="97%" cellspacing="0" cellpadding="0" align="center">
		<tr class="FOOTER">
			<td colspan=2>
		</tr>
	
		<tr>
			<td colspan=2>
				<div align="center" class="control_button2"> 
					<a 	style=cursor:hand class="button" tabindex=0 onClick='savePage(event,form1,"SAVE")' onkeyPress='savePage(event,form1,"SAVE")'><span class="save">Save</span></a> 
	  				<a 	style=cursor:hand class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="clear">Clear</span></a>
        			<a 	style=cursor:hand class="button" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a> 
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
 <anticsrf:csrftoken/>

   				
    </FORM>
	</BODY>
</HTML>
   
