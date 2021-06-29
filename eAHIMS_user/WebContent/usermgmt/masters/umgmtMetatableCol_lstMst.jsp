<%@page import="login.CSRFTokenUtil"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

<jsp:useBean id="roleMstId" class="usermgmt.masters.UmgmtMetatableColMstBean" scope="request" />
<jsp:setProperty name="roleMstId" property="*" />



<%
String seatId = (String)session.getAttribute("SEAT_ID");
String hospitalcode =(String)session.getAttribute("HOSPITAL_CODE");
System.out.println("Hospital"+hospitalcode);
roleMstId.setHospitalcode(hospitalcode);
//*if(seatId == null)
//{		
//	%!-->
		//<jsp:forward page="umgmtWarningPage.jsp"/>		
//	<!--%
//}*/
%>


<html>
<head>
<title>User Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="../../css/hisStyle.css" type="text/css">
<script language="JavaScript" src="../js/user.js"></script>
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>

<script language="JavaScript">


function focusOnLoad()
	{
//alert('table---'+document.forms[0].table[0].value);
//alert('table2---'+document.getElementsByName('table')[0].value);

	if(document.forms[0].mstTable.value=="-1")
	    	document.forms[0].mstTable.focus();
		else 
	if(document.forms[0].dataSetName.value=="")
	    	document.forms[0].dataSetName.focus();
		else			
	if(document.forms[0].colName.value=="-1")
	    	document.forms[0].colName.focus();
	    	else 
	if(document.forms[0].dispCol[0].value=="-1")
	    	document.forms[0].dispCol.focus();
	 
	    	

}  

window.history.foward();
	
var nameArray=new Array("moduleId","mstTable","colName","dispCol");


function submitpage1(e,form1,mode)
{

if(e.type=="click" || e.keyCode==13)
					{
	if(document.form1.mstTable.value=="-1")
		    {
		alert("Select Table Name ");
		document.form1.mstTable.focus();
		    }
		else
		if(document.form1.dataSetName.value=="")
		    {
		alert("Enter DataSet Name ");
		document.form1.dataSetName.focus();
		    }
		else
	 if(document.form1.colName.value=="-1")
	        {
		alert("Select Column Name ");
			document.form1.colName.focus();
		    } 
		else
	 if(document.form1.dispCol.value=="-1")
	       {
		alert("Select Display Column");
		document.form1.dispCol.focus();
	       }	
		else 
          {					
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
          } 

   }

}

function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.location.href="umgmtMetatableCol_lstMst.jsp";
		
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
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
</head>

<%
String 	cnt_page 		= "umgmtMetatableCol_lstMst.jsp";
String 	result_page 	= "result.jsp";
	String saveVal=request.getParameter("hmode");
	if(saveVal!=null && saveVal.equals("SAVE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{	
		roleMstId.save();
		roleMstId.setMstTable("");
		roleMstId.setColName("");
		roleMstId.setDispCol("");
		
	%>
	<jsp:forward page="<%=result_page%>">
	     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
	     	<jsp:param name="Name" value="Result >>" />
		<jsp:param name="hmode" value="DEFAULT" />
		<jsp:param name="status" value="<%=roleMstId.getMessage()%>" />
		</jsp:forward>
	
	<% 
		}
		else
		{
			%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
			<%
		}
	}
		

%>

<!--  
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()">-->
<body  width="100%" topmargin="0" onload="focusOnLoad()">
<form class="topmargin" name="form1" method="post" action="" >
<table class='formbg' width="97%" border="0" cellpadding="0" cellspacing="0" align=center>

    <tr>
      <td class="ShadedSubTitleTagImage" colspan="2">Meta Table Column Mapping</td>
    </tr>

    <tr>
      <td class="label" width="50%">
        <div align="right"><font color='red'>*</font>Table Name</div>
      </td>
      
      <td class="control" width="50%">
        <div align="left">
          <select name="mstTable" class=cbo2 onChange="document.form1.submit();" >
            <option value="-1">Select Value</option>
            <%=roleMstId.getMstTableOptions(roleMstId.getMstTable())%>
          </select>
        </div>
      </td>
    </tr>
    
    
     <tr>
      <td class="label" width="50%">
        <div align="right"><font color='red'>*</font>DataSet Name</div>
      </td>
      
      <td class="control" width="50%">
        <div align="left">
           <input type="text" name="dataSetName" maxlength="50" size="19" onkeypress ="return checkInput(this,5,100,event);">
        </div>
      </td>
    </tr>
    

    <tr>
      <td class="label" width="21%">
        <div align="right"><font color='red'>*</font>Column Name</div>
      </td>

      <td class="control" width="79%">
        <div align="left">
          <select name="colName" class=cbo2>
            <option value="-1">Select Value</option>
            <%=roleMstId.getColNameOptions(roleMstId.getMstTable(),roleMstId.getColName())%>
          </select>
        </div>
      </td>
    </tr>

    <tr>
      <td class="label" width="21%">
        <div align="right"><font color='red'>*</font>Display Column</div>
      </td>

      <td class="control" width="79%">
        <div align="left">
          <select name="dispCol" class=cbo2>
            <option value="-1">Select Value</option>
            <%=roleMstId.getAllColumnOptions(roleMstId.getMstTable())%>
          </select>
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
      	<a 	style=cursor:pointer; class="button" tabindex=0 onClick="submitpage1(event,form1,'SAVE');" onKeyPress="submitpage1(event,form1,'SAVE');"><span class="save">Save</span></a>
        <a 	style=cursor:pointer; class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="clear">Clear</span></a>
        <a 	style=cursor:pointer; class="button" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a> 
    
        </div>
       </td>
    </tr>
    <tr>
    <td><div align="center"><font color="#FF3300" size='3'><%=roleMstId.getMessage() %></font></td>
    </tr>
  </table>

  <input type="hidden" name="hmode" value="" />
  <input type="hidden" name="seatId" value='<%=session.getAttribute("SEAT_ID")%>' />
  <input type="hidden" name="hospitalcode" value='<%=session.getAttribute("HOSPITAL_CODE")%>' />
  <anticsrf:csrftoken/>
</form>
</body>
</html>

