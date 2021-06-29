	
	
	
	
	
	
	
		<html>
		<head><title>Menu - Module </title>
			<LINK href="../../images/master.css" type="TEXT/CSS" rel=STYLESHEET>
			<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		</head>
		<script language="JavaScript" type="text/JavaScript" src="../js/Validation.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="../js/functionality.js"></script>

		<script language="JavaScript">
		
		
		function readNumber(event)
		{
			var pressKeyCode = window.event.keyCode;
			if(pressKeyCode > 47 && pressKeyCode < 58 )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		function difflevel(lev)
	  	{
			document.form1.hmode.value="CMB";
			document.form1.submit();
	  	}
		
		function assignMode1(e,form1,mode)
		{
			
			if(document.form1.menu_level.value=="0")
			alert("Select level of Hierarchy");
			var url="";
			var menu="";
			if(document.form1.menu_level.value=="1")
			{
				if(document.form1.initLevel.value=="0")
				alert("Select Root Menu");
				else if(document.form1.moduleId.value=="0")
				alert("Select Module Name");
				else if(document.form1.menuclass_id.value=="0")
				alert("Select Class Id");
				else
				{
					if(e.type=="click"||e.keyCode==13)
					{		
						document.form1.hmode.value = mode;		
						//document.form1.menu_name.selectedIndex = mode;
						document.form1.submit();
	        		}
				}
			}
			else if(document.form1.menu_level.value=="2")
			{
				
				menu=removeLeadingTrailingSpace(document.form1.menuName1.value);
				document.form1.menuName1.value = menu;
				
				if(document.form1.initLevel.value=="0")
				alert("Select Root Menu");
				else if(document.form1.moduleId.value=="0")
				alert("Select Module");
				else if(document.form1.menuName1.value=="")
				alert("Enter Menu Name");
				else if(document.form1.menuclass_id.value=="0")
				alert("Select Class Id");
				//else if(document.form1.url.value=="")
				//alert("Enter the URL");
				
				else
				{
					if(e.type=="click"||e.keyCode==13)
					{		
						document.form1.hmode.value = mode;		
	        			document.form1.submit();
	        		}
				}
			}
	  		else if(document.form1.menu_level.value=="3")
			{
				menu=removeLeadingTrailingSpace(document.form1.menuName2.value);
				document.form1.menuName2.value = menu;
				
				if(document.form1.initLevel.value=="0")
				alert("Select Root Menu");
				else if(document.form1.moduleId.value=="0")
				alert("Select Module");
				else if(document.form1.menuIds.value=="0")
				alert("Enter Menu Name");
				else if(document.form1.menuName2.value=="")
				alert("Enter Menu Name");
				else if(document.form1.menuclass_id.value=="0")
				alert("Select Class Id");
				else if(document.form1.url.value=="")
				alert("Enter the URL");
			
				else
				{
					if(e.type=="click"||e.keyCode==13)
					{		
						
						document.form1.hmode.value = mode;		
	        			document.form1.submit();
	        		}
				}
			}
	  	}
	  	
	  	function assignModeUpdate(e,form1,mode)
	  	{
			var menu;
			menu=removeLeadingTrailingSpace(document.form1.menu_name.value);
			document.form1.menu_name.value = menu;
			
			if(document.form1.menu_name.value=="")
				alert("Enter Menu Name");
			else
			{
				document.form1.hmode.value = mode;		
	        	document.form1.submit();
			}
		}
		</script>
	  	
	 	<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" >
		<form name="form1" method="POST" action="umgmtMenu_cntMst.jsp">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr><td><div align="center">
	   	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	            <tr>
	              <td colspan=2><font color='#000066' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>

	<strong>Menu Master >> Add Page</strong></font></td>
	            </tr>
	    <tr><td class="addheader" colspan="4" align="right">
	  		Status<select name="isvalid">
	    	<option value="1">Active</option>
	    	<option value="2">InActive</option>        
	 		</select>

			</td></tr>
			
		    <tr><td width="48%" bgcolor="#F5F3F3">
		  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333">
		  	<font color='red'>*</font>Level of Hierarchy&nbsp;&nbsp;</font></div></td>
		  	<td colspan="2" bgcolor="#F5F3F3" width="52%"> <font face="MS Sans Serif" size="2">
		  	<select name="menu_level" onChange="difflevel(this);">
		  	<option value=0>Level Of Hierarchy</option><option value=1>Level 1</option><option value=2>Level 2</option><option value=3 selected>Level 4</option>

		  	</select></font></td></tr>
		   
		    <tr><td width="48%" bgcolor="#F5F3F3">
			 <div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>Root Menu
			 &nbsp;&nbsp;</font></div></td>
			  <td colspan="2" bgcolor="#F5F3F3" width="52%"> <font face="MS Sans Serif" size="2">
			 <select name="initLevel" onChange="submitpage('CMB')">
			  null<option value='0'>Select Root Menu</option><option value='40000000'>Help</option><option value='20000000'>Reports</option><option value='10000000' selected>Services</option><option value='30000000'>Setup</option>

			  </select></font></td></tr>
		    
		    	
		 		<tr><td width="48%" bgcolor="#F5F3F3">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333">	<font color='red'>*</font>Modules
			   	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" bgcolor="#F5F3F3" width="52%"> <font face="MS Sans Serif" size="2">
			  	<select name="moduleId" onChange="submitpage('CMB')">
		  		<option value='0'>Select Module</option><option value='14700000'>Acc_Budget</option><option value='13800000'>Advertisement</option><option value='18100000'>Amit_Reg</option><option value='12300000'>Appointment</option><option value='12000000' selected>Billing</option><option value='10300000'>Blood Bank</option><option value='11800000'>Bmed</option><option value='14600000'>Budget</option><option value='16300000'>Canteen</option><option value='15900000'>Cash Section</option><option value='13700000'>Committee</option><option value='17400000'>Communitycenter</option><option value='15100000'>Compilation</option><option value='15800000'>Contingency</option><option value='10200000'>Emergency</option><option value='11500000'>Feb_Module</option><option value='11600000'>Febfinalmodule</option><option value='15300000'>Fee</option><option value='16400000'>Filetrackingsystem</option><option value='12200000'>Fts</option><option value='14500000'>Gpf</option><option value='13900000'>Guesthouse</option><option value='17800000'>Hospital Enquires</option><option value='13100000'>Hr</option><option value='17600000'>Hr Recruitment</option><option value='17500000'>Hr Training</option><option value='17000000'>Idcard</option><option value='13000000'>Investigation</option><option value='10800000'>Ipd</option><option value='16500000'>Legal</option><option value='14800000'>Loan_Advance</option><option value='15600000'>Lta</option><option value='11200000'>Manmodule</option><option value='14300000'>Meetingarrangement</option><option value='12900000'>Mis</option><option value='11900000'>Module_Cdac</option><option value='11300000'>Mtest7</option><option value='10500000'>Newmodule1234</option><option value='10700000'>Opd</option><option value='14900000'>Ot</option><option value='17900000'>Patient Enquires</option><option value='15000000'>Paybill</option><option value='14000000'>Pg</option><option value='16000000'>Pr</option><option value='15400000'>Private Grants</option><option value='17200000'>Public Grievance</option><option value='10100000'>Registration</option><option value='16100000'>Rtia</option><option value='15700000'>Rulebook</option><option value='16200000'>Sarai/Guesthouse</option><option value='16700000'>Security</option><option value='13200000'>Servicearea</option><option value='18000000'>Sms</option><option value='1000000'>Store</option><option value='11100000'>Store2</option><option value='11400000'>Store3</option><option value='16800000'>Swimming Pool</option><option value='16900000'>Tada</option><option value='15500000'>Vigilance</option><option value='16600000'>Workingwomanhostel</option>                       
			  	</select></font></td></tr>

	 		
	 			<tr><td width="48%" bgcolor="#F5F3F3">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>  Intermediate
			  	Menues &nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" bgcolor="#F5F3F3" width="52%"> <font face="MS Sans Serif" size="2">
			  	<select name="menuIds" onChange="submitpage('CMB')">
		  		null<option value='0'>Select Menus</option><option value='12001000' selected>Cash Collection</option><option value='12005000'>Client Patient Desk</option><option value='12006000'>Day End</option><option value='12003000'>Discount Approval</option><option value='12002000'>Ipd Bill Management</option><option value='12008000'>Online Bill Cancellation</option><option value='12007000'>Online Request Cancellation</option><option value='12004000'>Refund Approval</option>                                                                                                        
		  		</select></font></td></tr>

	  
				<tr><td width="48%" bgcolor="#F5F3F3">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font> Menu Name
			  	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" bgcolor="#F5F3F3" width="52%"> <font face="MS Sans Serif" size="2">
			  	<input type="text" name="menuName2" onkeypress = "return checkAlphaNumeric(this,5,50,event);"></td></tr><tr><td width="48%" bgcolor="#F5F3F3"><div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>Class
	        Description&nbsp;&nbsp; </font></div></td>
	        <td colspan="2" bgcolor="#F5F3F3" width="52%"> <font face="MS Sans Serif" size="2" color="#000000">

	        
	        <select name="menuclass_id">
	        <option value="0" selected> Select Class ID:</option>
	        <option value='I'>Intermediate</option><option value='L'>Leaf Node</option></select></font></td></tr>
	    	
	    	<tr>

	        </tr>       
        
	    	<tr><td width="48%" bgcolor="#F5F3F3">
	        <div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>Display
	        in Portal</font></div> </td>

	        <td colspan="2" bgcolor="#F5F3F3" width="52%"><font face="MS Sans Serif" size="2" color="#000000">
	        <input type="radio" name="display_order" value="1" >Yes&nbsp;
	        <input type="radio" checked="checked" name="display_order" value="1" >No
	        </font></td></tr>       
	         
	 	<tr>
	    <td width="48%" bgcolor="#F5F3F3">
	    <div align="right"><font face="MS Sans Serif" size="2" color="#333333">
	    
	    <font color='red'>*</font>URL&nbsp;&nbsp;
	    
	    
	    </font></div></td>
	    
	    <td colspan="2" bgcolor="#F5F3F3" width="80%"><font face="MS Sans Serif" size="2" color="#000000">

	    
	<input type="text" name="url" value="" onkeypress = "return checkInput(this,6,120,event)" size='35'>
	    </font></td></tr>
	    
	    
	    <tr>
	    <td width="48%" bgcolor="#F5F3F3">
	    <div align="right"><font face="MS Sans Serif" size="2" color="#333333">
	    
	    <font color='red'>*</font>Menu display position&nbsp;&nbsp;
	    
	    
	    </font></div></td>
	    
	    <td colspan="2" bgcolor="#F5F3F3" width="80%"><font face="MS Sans Serif" size="2" color="#000000">

	    
	
	    </font><font face="MS Sans Serif" size="2" color="#000000"><input type="radio" name="display_order" value="1">Horizontal&nbsp;
	        <input type="radio" checked="checked" name="display_order" value="1">Vertical</font></td></tr>
	     
	        
	 	<tr>
	    <td width="48%" bgcolor="#F5F3F3">
	    <div align="right"><font face="MS Sans Serif" size="2" color="#333333">
	    
	    <font color='red'>*</font>Javascript function name&nbsp;&nbsp;
	    
	    
	    </font></div></td>
	    
	    <td colspan="2" bgcolor="#F5F3F3" width="80%"><font face="MS Sans Serif" size="2" color="#000000">

	    
	<input type="text" name="url" value="" onkeypress = "return checkInput(this,6,120,event)" size='35'>
	    </font></td></tr>     
	    <tr bgcolor="#dee1f2">
		<td height="17" colspan="4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
	     <table width="60%" border="0" cellspacing="1" cellpadding="0"><tr>
		<td class="addtoolbar"> <div align="center">

	<a style=cursor:hand><img src="../../images/Save.gif" class="link" tabindex=0 onClick='assignMode1(event,form1,"SAVE")' onkeyPress='assignMode1(event,form1,"SAVE")'></a>
			<a style=cursor:hand><img src="../../images/Clear.gif" width="60" height="20" tabindex="0" onKeyPress="if(window.event.keyCode==13)document.form1.reset();" onClick="document.form1.reset();" ></a>
			 
		
			
		
	
		<a style=cursor:hand><img src="../../images/Cancel.gif" class="link" width="60" height="20"  tabindex="0" onKeyPress="if(window.event.keyCode==13) submitpage('DEFAULT');" onClick='submitpage("DEFAULT");'></a></div></td>
		</tr></table></table></td></tr></table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="parent_id" value="">
	<input type="hidden" name="menu_id" value="">
	</form>
	</body>

	</html>
	
	
