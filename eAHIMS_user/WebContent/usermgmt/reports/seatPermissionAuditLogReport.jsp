<jsp:useBean id="newseat" class="usermgmt.reports.seatPermissionAuditLogReport" scope="request"/>
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
	if(document.forms[0].seat.value=="0")
	    	document.forms[0].seat.focus();
	    	else
	if(document.forms[0].table.value=="0")
	    	document.forms[0].table.focus();
	    	else 	
	    	document.getElementById("clearId").focus();
	    	
	}
	
	

window.history.forward();





	
function submitMode(hmode,mode)
{	

if(mode=="GROUP")
    {
	document.forms[0].seat.value="0";
	document.forms[0].table.value="0";
    }
    
	document.forms[0].hmode.value = hmode;	
	document.forms[0].submit();

}

function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.form1.group.value=0;
		document.form1.seat.value=0;	
		document.form1.table.value=0;
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
<body  width="100%" topmargin="0" onload="focusOnLoad()">
<form class="topmargin" name="form1" method="post" action="seatPermissionAuditLogCnt.jsp">		

  
	<table width="100%" align="center">
     <tr> 
	<td class="ShadedSubTitleTagImage" colspan=2 align="left">Seat Permission Audit Log Report </td>
	 </tr> 
	
	
	
	 
	</table>
	 <table width="97%" align="center" class="formbg">
	
<tr>		
	<td class="label" width="50%"> 
	<div align="right"><font color='red'>*</font>Group</div>
	</td>
	<td class="control" width="50%"> 
	
	<div align="left"> 
	<select name="group" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:115px;" onChange="submitMode('TEMP','GROUP');">
   	<%=newseat.getGroupCombo()%> 
	</select>
	</div>
	
	
	</td>
</tr>
  
  
    <tr> 		
      <td class="label" width="50%"> 
        <div align="right"><font color='red'>*</font>Seat</div>
      </td>
      <td class="control" width="50%"> 
      
     
  
	 <div align="left"> 
        
         <select name="seat" style="font-family:Verdana; font-size:12px; font-style:normal; 
         height:20px; width:115px; text-decoration:none;" onChange="submitMode('TEMP','USER');">
            <%=newseat.getSeatCombo()%> 
            
          
          </select>
        </div>
	</td>
	</tr>
	   
	   <tr>		
			<td class="label"  width="40%"> 
				<div align="right"><font color='red'>*</font>Module</div>
			</td>
			<td class="control" width="60%"> 
				<div align="left"> 
					<select name="module"  onChange="submitMode('TEMP');" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
   					<%=newseat.getModuleCombo()%> 
					</select>
				</div>
			</td>
		</tr>
	   
	   <tr> 		
      <td class="label" width="50%"> 
        <div align="right"><font color='red'>*</font>Data Set</div>
      </td>
      <td class="control" width="50%"> 
      
     
  
	 <div align="left"> 
        
         <select name="table" style="font-family:Verdana; font-size:12px; font-style:normal; 
         height:20px; width:115px; text-decoration:none;" onChange="submitMode('TEMP','USER');">
            <%=newseat.getTableCombo()%> 
            
          
          </select>
        </div>
	</td>
	</tr>
<!--  Code for creating the list boxes for the seats -->      
<tr>
		<td colspan="2">
	<div class="list2">
   <table width="100%" align="center">
   
	 <tr>		
              
                            <th width='25%'><b>Data</b></th>
                  	    	<th  width='25%'><b>Status</b></th>
                  	    	<th  width='25%'><b>Entry Date</b></th>
                  	    	<th  width='25%'><b>Modified Date</b></th>
       </tr>
      
     <%=newseat.getDetailsOfTableData(request)%> 
	 
	 
	
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
   
