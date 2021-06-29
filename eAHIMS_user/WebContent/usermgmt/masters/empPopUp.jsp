	<jsp:useBean id="userpop" class="usermgmt.masters.UmgmtEmpPopUpMstBean" scope="request" />
	<jsp:setProperty property="*" name="userpop" />	
	<%
		if(	pageContext.getAttribute("hospitalCode") !=null)
			userpop.setHospitalCode((String)pageContext.getAttribute("hospitalCode"));
		System.out.println("h----------------------->"+(String)pageContext.getAttribute("hospitalCode"));
	%>
	<html>
	
	<head><title>Employee Details</title></head>
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../../css/master.css" rel="stylesheet" type="text/css">
	<link href="../../css/Color.css" rel="stylesheet" type="text/css">
	
	<script language="JavaScript" src="../js/empPopup.js" type="text/JavaScript"></script>
	<script language="JavaScript" type="text/JavaScript" src="../js/Validation.js"></script>
	<script>
	
	function fun()
	{
	 if(document.getElementsByName('searchText')[0].value=="")
		{
			 alert("Search text is Blank");
			document.getElementsByName('searchText')[0].focus();
			return false;
		}
		document.forms[0].submit();
		return;
	}	
	
	function wclose()
	{
		if(document.getElementsByName('chk').length!=0)
		{
	    	var chks =document.getElementsByName('chk');
		    var checked = false;
		    for(var i=0; i<chks.length;i++)
	    	{
	    		checked= chks[i].checked || checked;
	    	}
	    if(checked==false)
	     {
	      	alert("Select a Record");
	      	return;
	     }
	    }
		//window.opener.document.forms[0].empNo.readonly=false;
		window.opener.document.forms[0].empNo.value=document.forms[0].dempno.value;
		
		//window.opener.document.forms[0].ufullName.readonly=false;
		window.opener.document.forms[0].ufullName.value=document.forms[0].dempname.value;
		
		//window.opener.document.forms[0].designation.readonly=false;
		window.opener.document.forms[0].designation.value=document.forms[0].dempdesg.value;
		//closeWindow();
		//alert("designation "+window.opener.document.forms[0].designation.value)
		//alert("fullname "+window.opener.document.forms[0].ufullName.value)
		window.close();
	}
	function ivalue(chBk,index)
	{
//	alert("inmdex"+index);
		var o=document.getElementById("chk"+index);
		
	//	alert(chBk.value);
		
		
		
		var i=chBk.value-1;
		var o1=document.getElementsByName("ncondata");
		//alert(o1);
		//alert(o1[i].value)
		var empdata=o1[i].value;
		var temp=empdata.split("^");
		/*for(var j=0;j<temp.length;j++)
		{
		alert(temp[j]);	
		}*/
		//alert(temp[0]);
		document.forms[0].dempno.value=temp[0];
		document.forms[0].dempname.value=temp[1];
		document.forms[0].dempdesg.value=temp[2];
		window.opener.document.getElementById("EmpIdNewDivId").value =temp[0];
	}	
	 
	
	</script>
	<body>
	<form name="form1" action="empPopUp.jsp">
	<table border="0" align='center'>
	<tr>
	<td class="ShadedSubTitleTagImage">Enter For Searching 
	<input type="text" name="searchText" onkeypress = "return checkInput(this,7,100,event)"></td>
	<td class="ShadedSubTitleTagImage">Search <select name="searchCombo">
	<option value="2">emp_name</option>
	<option value="1">emp_id</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor:hand; float: right;" class="button" onClick="fun()"><span class="find">Search</span></a></td></tr>
	<%
	if(userpop.getSearchText().equals("")||userpop.getSearchText()==null||userpop.getSearchCombo().equals("")||userpop.getSearchCombo()==null)
	{
		System.out.println("inside if in pop up ");
	}
	else
	{%>
		<%=userpop.getEmpInfo(userpop.getSearchText(),userpop.getSearchCombo())%>
	<%}%>
	<tr><td colspan='2'><center>
	<img src="../../images/submit_tab.gif" onClick="wclose()"></center></td></tr>
	</table>
	
	<input type='hidden' name='dempno' value=''/>
	<input type='hidden' name='dempname' value=''/>
	<input type='hidden' name='dempdesg' value=''/>
	<input type='hidden' name='hospitalCode' value='<%=userpop.getHospitalCode() %>'/>
	</form>
	</body>
	</html>