<jsp:useBean id="newseat" class="usermgmt.reports.userAuditLogReport" scope="request"/>
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
System.out.println("Hospital code in seat log"+hospitalcode);

String seatId =(String)session.getAttribute("SEAT_ID");
System.out.println("seat Id is"+seatId);
System.out.println("hmode is-----"+hmode);
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
	if(document.forms[0].userId.value=="0")
	    	document.forms[0].userId.focus();
	    	else
	    	document.getElementById("clearId").focus();
	    	
	}


window.history.forward();





	
function submitMode(hmode,mode)
{	

if(mode=="GROUP")
	document.forms[0].userId.value="0";

	document.forms[0].hmode.value = hmode;	
	document.forms[0].submit();

}


function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.form1.group.value=0;
		document.form1.userId.value=0;	
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
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
</head>
 <!-- 
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
<body onload="focusOnLoad()">
<form name="form1" method="post" action="userAuditLogCnt.jsp">		
  <div style="width:1415px;" >
	<table width="97%" align="center" class="topmargin">
     <tr > 
	<td class="ShadedSubTitleTagImage"  align="left" width="100%" >User Audit Log Report </td>
	 </tr> 
	</table>
	 <table width="97%" align="center" class="formbg">
	 
<tr>		
	<td class="label" width="50%"> 
	<div align="right"><font color='red'>*</font>Group</div>
	</td>
	<td class="control" width="50%"> 
	
	<div align="left"> 
	<select name="group" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:115px;" onChange="submitMode('DEFAULT','GROUP');">
   	<%=newseat.getGroupCombo()%> 
	</select>
	</div>
	
	
	</td>
</tr>
  
  
    <tr> 		
      <td class="label" width="50%"> 
        <div align="right"><font color='red'>*</font>User</div>
      </td>
      <td class="control" width="50%"> 
      
     
  
	 <div align="left"> 
        
         <select name="userId" style="font-family:Verdana; font-size:12px; font-style:normal; 
         height:20px; width:115px; text-decoration:none;" onChange="submitMode('DEFAULT','USER');">
            <%=newseat.getUserCombo()%> 
            
          
          </select>
        </div>
	</td>
		<td class="control" width="50%"> </td>
	</tr>
	
<!--  Code for creating the list boxes for the seats -->	       
<tr>
	<td colspan="2">
	  <div class="list2">
   <table width="97%" align="center" >
   
    <tr > 
	<td class="ShadedSubTitleTagImage" width='97%' colspan="16"><font color='white'>Current Record</font> </td>   
	
	
	 </tr> 
	 
	 <tr>					<th  width='5%'><b>Modified By</b></th>   
	 						<th  width='5%'><b>Modified Date</b></th>     
                            <th  width='10%'><b>Employee/Non-Employee</b></th>
                  	    	<th  width='10%'><b>Name</b></th>
                  	    	<th  width='5%'><b>Designation</b></th>
                        	<th  width='10%'><b>Seat Name</b></th>
      						<th  width='5%'><b>Group Name</b></th>
      						<th  width='5%'><b>Password Changed</b></th>
      						<th  width='5%'><b>Effective Date</b></th>
      						<th  width='5%'><b>Expiry Date</b></th>
      						<th  width='5%'><b>User Level</b></th>
      						<th  width='5%'><b>Status</b></th>
      						<th  width='5%'><b>Locked Status</b></th>
      						<th  width='10%'><b>Email Id</b></th>
      						<th  width='5%'><b>Mobile No.</b></th>
      						<th  width='5%'><b>Smart Card No.</b></th>
      							
      </tr>
      
      
     <%=newseat.getDetailsOfNewUsers(request)%> 
	 <%=newseat.getDetailsOfOldUsers(request)%> 	 
	
	  </table>
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
		 
		<a 	style=cursor:pointer; class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="clear">Clear</span></a>
        <a 	style=cursor:pointer; class="button" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a> 
      
        </div>
      </td></tr>
      <tr>
      <td>
      <div align="center"><font color="#FF3300" size='3'></font></div>

    </tr>
    
  </table>
<input type="hidden" name="hmode">
<input type="hidden" name="hospitalcode" value='<%=session.getAttribute("HOSPITAL_CODE")%>' />
<input type="hidden" name="seatId" value='<%=session.getAttribute("SEAT_ID")%>' />
<input type="hidden" name="sysDate" value="<%=newseat.getSysdate()%>">

   				
    </FORM>
	</BODY>
</HTML>
   
