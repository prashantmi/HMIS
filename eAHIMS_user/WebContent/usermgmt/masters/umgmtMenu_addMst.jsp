	<!--
 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2007-2008 
 ## Client's Name					: PGI
 ## Project Name					: HIMS
 ## Phase						: Development
 ## Name of Developer		 		: Mr. Ankur Mehrotra
 ## Module Name					: User Managment
 ## Date of creation					: 12/11/2008
 ## Purpose						: List Page
 ## Previous Form(Calling)				:
 ## Functions Used					: No
 ## Name of Tables used for reference 	            : 
 ## Name of Tables used for data updation/insertion	: Dynamic
 ## Next Form	(Called)				: Modify page,View Page,Report Page Depending upon hmode
 ## Date of Modification				: No
 ## Unit Tested By	& Date				: 17/11/2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :
 				if No then Detail   	:
     7). Name of Objects Used		            	:
 		i). Object Name		 	:
 		ii) Purpose				:
 		iii). No. of times called		:
     8). Any suggestion					:
     9) Other Deviation					:
 ## Remark						:
 ## Finalization Date					:
 ## Future Alteration (1)				:
 ## Any major change
 	1) Reason					:
 	2) Time in days (Hour)			:
 	3) Change Raised By				:
 	4) Tested(Y/N)				:
 	5) Remark					:
 
-->
	
	<%@ page language="java" import="java.sql.*,java.util.*" %>
	<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>
	
	<jsp:useBean id="menu" class="usermgmt.masters.UmgmtMenuMstBean" scope="request">
	</jsp:useBean>
	
	<%
	String parent="";
	String hmode = "";
	String title = "";
	hmode = request.getParameter("hmode");
	if(hmode == null)
		hmode = "SAVE";
	if(hmode.equals("SAVE"))
		title="Add";
	else
		title = "Modify";
		
		System.out.println("getMenu_level()---->"+menu.getMenu_level());
	%>
	
		<html>
		<head><title>Menu - Module </title>
			<LINK href="../../images/master.css" type="TEXT/CSS" rel=STYLESHEET>
			<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		</head>
		<script language="JavaScript" type="text/JavaScript" src="../js/Validation.js"></script>
		<script language="JavaScript" type="text/JavaScript" src="../js/functionality.js"></script>
		<script language="JavaScript">
		
function focusOnLoad()
	{

	var hmode="<%=hmode%>";
	
	//alert('hmode---'+hmode)
	
///in case of add page	
if(hmode=="SAVE")
{
		//alert('level----'+document.getElementsByName("menu_level")[0].value)
	
	
					///Level==0
	if(document.getElementsByName("menu_level")[0].value=="0")
		document.forms[0].menu_level.focus();	
		else 	 ///Level==1	
	if(document.form1.menu_level.value=="1")
		{
			
		if(document.form1.initLevel.value=="0")
				document.forms[0].initLevel.focus();
			else
	 	if(document.form1.moduleId.value=="0")
				document.forms[0].moduleId.focus();
				//else 
		/* if(document.form1.applicationtype_id.value=="0")
				document.forms[0].applicationtype_id.focus(); */
		
		}	
		else      ///Level==2
	if(document.form1.menu_level.value=="2")
			{
				
		if(document.form1.initLevel.value=="0")
				document.forms[0].initLevel.focus();
			else
	 	if(document.form1.moduleId.value=="0")
				document.forms[0].moduleId.focus();
		 	else
	   if(document.form1.menuName1.value=="")
				document.forms[0].menuName1.focus();
			//else
		/* if(document.form1.applicationtype_id.value=="0")
				document.forms[0].applicationtype_id.focus(); */
				
				
			}	
			else        ///Level==3
	if(document.form1.menu_level.value=="3")
			{
			
		  if(document.form1.initLevel.value=="0")
				document.forms[0].initLevel.focus();
				else
	 	  if(document.form1.moduleId.value=="0")
				document.forms[0].moduleId.focus();
				else 
		  if(document.form1.menuIds.value=="0")
				document.forms[0].menuIds.focus();
				else
		  if(document.form1.menuName2.value=="")
				document.forms[0].menuName2.focus();
				//else
		/* if(document.form1.applicationtype_id.value=="0")
				document.forms[0].applicationtype_id.focus();
				 */
					
			}
			else 		///Level==4
    if(document.form1.menu_level.value=="4")
			{
			
			 if(document.form1.initLevel.value=="0")
				document.forms[0].initLevel.focus();
				else
	 	  if(document.form1.moduleId.value=="0")
				document.forms[0].moduleId.focus();
				else 
		  if(document.form1.menuIds.value=="0")
				document.forms[0].menuIds.focus();
				else
		  if(document.form1.menuName3.value=="")
				document.forms[0].menuName3.focus();
				//else
		/* if(document.form1.applicationtype_id.value=="0")
				document.forms[0].applicationtype_id.focus(); */
				else 
		if(document.form1.url.value=="")
			  document.forms[0].url.focus();
			}
			
}
else	///in case of Modify page
if(hmode=="UPDATE")
	{
	document.forms[0].menu_name.focus();

	}	

	
}
		
		window.history.foward();
		
		function checkInput1(varObj,validationIndex,length,e)
		{
		
		//onkeypress = "checkInput(this,which str to be used,length applicable,event);"
			var returnValue = true;
			var applicableStr = "";
			var key;
			if (window.event)
				key = window.event.keyCode;
				
			else if (e)
				key = e.which;
			
		
			//control keys
			
			if ((key==null) || (key==0) || (key==8) ||
				(key==9) || (key==13) || (key==27) ) // Removed key == 47 By Pratichi Maheshwari
				return true; 
			var keychar = String.fromCharCode(key);
			
			//Validating Length
			if(varObj.value.length==length)
				returnValue = false;
				
				
			//Specifying the applicable character set
			if(validationIndex==1)
				applicableStr = "0123456789";
			if(validationIndex==2)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			if(validationIndex==3)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
			if(validationIndex==4)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";	
			if(validationIndex==5)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_ ";
			if(validationIndex==6)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./:_?=";
			if(validationIndex==7)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";  //used for a-z,A-Z,0-9,space
			if(validationIndex==8)// Added By Pratichi Maheshwari
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/ ";
			if(validationIndex==9)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./:_?";//used for url added by ankur
		
			var aa = applicableStr.indexOf(keychar);
			
			if(applicableStr.indexOf(keychar)== -1)
				returnValue = false;
				
							
				return returnValue; 
		
		} 
	
function resetForms1(e,form1,mode)
	{
	

		if(e.type=="click"||e.keyCode==13)
		{
		
			if(mode == "RESET")
			{ 
		   	
			document.form1.initLevel.value = 0;		
			
			
			
			if(document.form1.menu_level.value!=0)
			document.form1.moduleId.value = 0;
			
				
			document.form1.applicationtype_id.value =0;
			
			
				
			
			if(document.form1.menu_level.value==2)
			document.form1.menuName1.value = "";
			
			if(document.form1.menu_level.value==3)
			document.form1.menuName2.value ="";
			
			if(document.form1.menu_level.value==4)
			document.form1.menuName3.value = "";	
			
			//document.form1.displayInPortal[1].checked=true;	
			
			
			
			if((document.form1.menu_level.value!=1) && (document.form1.menu_level.value!=0) )	
			document.form1.url.value = "";		
            
			 
			 document.form1.menu_level.value = 0;	
			 document.form1.hmode.value ="ADD" ;
			 document.form1.submit();
			 
				//document.form1.reset();
				
			}
			else
			{
				document.form1.hmode.value = mode;			
				document.form1.submit();
			}	
		}	
	}
	
		function resetForm(e,form1,mode)
	{

		if(e.type=="click"||e.keyCode==13)
		{
			if(mode == "RESET")
				document.form1.reset();
			else
			{
				document.form1.hmode.value = mode;			
				document.form1.submit();
			}	
		}	
	}
		
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
			
if(e.type=="click"||e.keyCode==13)
	{
			if(document.form1.menu_level.value=="0")
			alert("Select level of Hierarchy");
			var url="";
			var menu="";
			//if(document.form1.displayInPortal[1].checked)
			  // document.form1.portalDisplay.value="0";
			 //if(document.form1.displayInPortal[0].checked)
			  // document.form1.portalDisplay.value="1";
			if(document.form1.menu_level.value=="1")
			{
				if(document.form1.initLevel.value=="0")
				alert("Select Root Menu");
				else if(document.form1.moduleId.value=="0")
				alert("Select Module Name");
				//else if(document.form1.applicationtype_id.value=="0")
				//alert("Select Application Id");
				else
				{
							
						document.form1.hmode.value = mode;		
						document.form1.submit();
	        	
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
				//else if(document.form1.applicationtype_id.value=="0")
				//alert("Select Application Id");
			
				
				else
				{
							
						document.form1.hmode.value = mode;		
	        			document.form1.submit();
	        		
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
				//else if(document.form1.applicationtype_id.value=="0")
				//alert("Select Application Id");
				
			
				else
				{
							
						
						document.form1.hmode.value = mode;		
	        			document.form1.submit();
	        		
				}
			}
		else if(document.form1.menu_level.value=="4")
			{
				menu=removeLeadingTrailingSpace(document.form1.menuName3.value);
				document.form1.menuName3.value = menu;
				
				if(document.form1.initLevel.value=="0")
				alert("Select Root Menu");
				else if(document.form1.moduleId.value=="0")
				alert("Select Module");
				else if(document.form1.menuIds.value=="0")
				alert("Enter Menu Name");
				else if(document.form1.menuName3.value=="")
				alert("Enter Menu Name");
				//else if(document.form1.applicationtype_id.value=="0")
				//alert("Select Application Id");
				else if(document.form1.url.value=="")
				alert("Enter the URL");
				else
				{
						
						
						document.form1.hmode.value = mode;		
	        			document.form1.submit();
	        		
				}
			}
	  	}
	 } 	
	  	function assignModeUpdate(e,form1,mode)
{
	  	
 	if(e.type=="click" || e.keyCode==13)
    {
			var menu;
			menu=removeLeadingTrailingSpace(document.form1.menu_name.value);
			document.form1.menu_name.value = menu;
			
			if(document.form1.menu_name.value=="")
				{
				alert("Enter Menu Name");
				}
				//else if(document.form1.applicationtype_id.value=="0")
				//alert("Select Application Id");
			else
			{
				document.form1.hmode.value = mode;		
	        	document.form1.submit();
			}
	}
}		
function submitpage1(e,form1,mode)
{

if(e.type=="click" || e.keyCode==13)
					{
    document.forms[0].hmode.value=mode;
	document.forms[0].submit();
                    }

}
		
		</script>
	  	<%
	 	
	 	String menu_level = request.getParameter("menu_level") ;
	  	if(menu_level==null)
			menu_level="00";
	 	%>
	 	<!-- 
	 	<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
	 	<body  width="97%" topmargin="0" onload="focusOnLoad()">
		<form name="form1" method="POST" action="umgmtMenu_cntMst.jsp">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr><td><div align="center">
	   	<table width="97%" border="0" cellspacing="1" cellpadding="0">
	            <tr>
	              <td colspan=2><font color='#000' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
	<strong>Menu Master >> <%=title%> Page</strong></font></td>
	            </tr>
	            </table>        
	         
	   <table  class="formbg" width="97%" border="0" cellspacing="1" cellpadding="0" align="center">
	            
	    <%
	    if(hmode.equals("UPDATE"))
		{
	    	%>  
			<tr><td class="ShadedSubTitleTagImage" colspan="4" align="right">
			Status<select name="isvalid" tabindex="-1">
	  		<option value="1" <%=menu.getIsvalid().equals("1")?"selected":""%> >Active</option>
	  		<option value="2" <%=menu.getIsvalid().equals("2")?"selected":""%> >InActive</option>        
	   		</select></td></tr>
			<%     
	 	}
	  	else
	    {%><tr><td class="ShadedSubTitleTagImage" colspan="4" align="right">
	  		Status<select name="isvalid" tabindex="-1">
	    	<option value="1" <%=menu.getIsvalid().equals("1")?"selected":""%> >Active</option>
	    	<option value="2" <%=menu.getIsvalid().equals("2")?"selected":""%> >InActive</option>        
	 		</select>
			</td></tr>
			<%
		}
		
		
		if(hmode.equals("SAVE"))
	    {
			%>
		    <tr><td width="48%" class="label">
		  	<div align="right"><font face="" size="" color="#333333">
		  	<font color='red'>*</font>Level of Hierarchy&nbsp;&nbsp;</font></div></td>
		  	<td colspan="2" class="control" width="52%"> <font face="" size="">
		  	<select name="menu_level" onChange="difflevel(this);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  	<%=menu.viewLevel()%>
		  	</select></font></td></tr>
		   
		    <tr><td width="48%" class="label">
			 <div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Root Menu
			 &nbsp;&nbsp;</font></div></td>
			  <td colspan="2" class="control" width="52%"> <font face="" size="">
			 <select name="initLevel" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			  <%=menu.viewInitLevel()%>
			  </select></font></td></tr>
		    <% System.out.println("menu level on add.jsp is equal to the the the the the==="+menu_level); %>
		    <%if(menu_level.equals("1"))
		    {
		    	parent=request.getParameter("initLevel");%>
			  	
			  	<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font> 
			  	Modules &nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<select name="moduleId" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menu.viewModuleId()%>                                                                                                        
			  	</select></font></td></tr><%
		  	}
		    
		    else if(menu_level.equals("2"))
		   	{
		    	parent=request.getParameter("moduleId");
		    	if(parent==null)
		    		parent="00";
		    		
		    	%>
				
			
				<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"> <font color='red'>*</font>Modules
			 	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<select name="moduleId" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menu.viewModuleId()%>                                                                                                         
			  	</select></font></td></tr>
	   		
	   			<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font> Menu Name
			  	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<input type="text" name="menuName1" onkeypress = "return checkInput1(this,7,50,event);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;"></td></tr><%
	   	
		   }
		    
		   else  if(menu_level.equals("3"))
		   {
		    	parent=request.getParameter("menuIds");
			  	if(parent==null)
		    		parent="00";
			  	%>	
		 		<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333">	<font color='red'>*</font>Modules
			   	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<select name="moduleId" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menu.viewModuleId()%>                       
			  	</select></font></td></tr>
	 		
	 			<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Intermediate
			  	Menues &nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<select name="menuIds" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menu.viewMenuIds()%>                                                                                                        
		  		</select></font></td></tr>
	  
				<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font> Menu Name
			  	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<input type="text" name="menuName2" onkeypress = "return checkInput1(this,7,50,event);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;"></td></tr><%
			}
	    
	  
		//added by Nitin Tyagi on 11-11-08
		else  if(menu_level.equals("4"))
		   {
				parent=request.getParameter("menuIds");
		    	if(parent==null)
		    		parent="00";
			  	%>	
		 		<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333">	<font color='red'>*</font>Modules
			   	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<select name="moduleId" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menu.viewModuleId()%>                       
			  	</select></font></td></tr>
	 		
	 			<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Intermediate
			  	Menues &nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<select name="menuIds" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menu.viewMenuIds()%>                                                                                                        
		  		</select></font></td></tr>
		  		<tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font> Menu name
			  	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<input type="text" name="menuName3" onkeypress = "return checkInput1(this,7,50,event);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;"></font></td></tr>	 
			  	
				<%
			}
		
	    }
		
	
	   if(hmode.equals("UPDATE"))
	    {
	    	%><tr>
	    	<td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font> Menu
			  	Name &nbsp;&nbsp;</font></div></td>
	    	<td width="52%" class="control" colspan="2">
	    	<input type="text" name="menu_name" value="<%=menu.getMenu_name()%>" onkeypress = "return checkInput1(this,7,50,event);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height: 28px;width:115px;">
			</td>
	    	</tr>
	    	<%
	    } 
	    
	    if(hmode.equals("SAVE"))
	    {
			%><tr><!--<td width="48%" class="label"><div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Class
	        Description&nbsp;&nbsp; </font></div></td>
	        <td colspan="2" class="control" width="52%"> <font face="" size="" color="#000000">
	        <select name="menuclass_id" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
	        <option value="0" selected> Select Class ID:</option>
	      </select></font></td>-->
<!-- 	        	<td width="48%" class="label"><div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Application 
	        	Type&nbsp;&nbsp; </font></div></td>
	        	<td colspan="2" class="control" width="52%"> <font face="" size="" color="#000000">
	         	<select name="applicationtype_id" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
	       	 	<option value="0" selected> Select Application Type:</option>
	       	 	<option value="1" > Clinical</option>
	       	 	<option value="2" > Non Clinical</option>
	       	 	<option value="3" > Blood Bank</option>
	       	 	<option value="4" > Drug Ware House</option>
	        	</select></font></td>-->
	        </tr>
	    	<%
	    }  if(hmode.equals("UPDATE"))
	    {
			
		String applicationtype_id1="";
		String applicationtype_id2="";
		String applicationtype_id3="";
		String applicationtype_id4="";
		String applicationtype_id5="";
		String applicationtype_id6="";
		
		//String level=menu.getApplicationtype_id();
		String level="0";
		
		if(level.equals("1"))
			applicationtype_id1="selected";
		if(level.equals("2"))
			applicationtype_id2="selected";
		if(level.equals("3"))
			applicationtype_id3="selected";
		if(level.equals("4"))
			applicationtype_id4="selected";
	
		System.out.println("level"+level);
		%>
		<!--<tr><td width="48%" class="label"><div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Class
	        Description&nbsp;&nbsp; </font></div></td>
	        <td colspan="2" class="control" width="52%"> <font face="" size="" color="#000000">
	        <select name="menuclass_id" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
	        <option value="0" selected> Select Class ID:</option>
	      </select></font></td>
	        	  <td width="48%" class="label"><div align="right"><font face="" size="" color="#333333"><font color='red'>*</font>Application
	        	Type&nbsp;&nbsp; </font></div></td>
	        	<td colspan="2" class="control" width="52%"> <font face="" size="" color="#000000">
	         	<select name="applicationtype_id"  style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
	       	 	<option value="0" > Select Application Type:</option>
<%-- 	       	 	<option value="1"  <%=applicationtype_id1 %> > Clinical</option> --%>
<%-- 	       	 	<option value="2"  <%=applicationtype_id2 %> > Non Clinical</option> --%>
<%-- 	       	 	<option value="3"  <%=applicationtype_id3 %> > Blood Bank</option> --%>
<%-- 	       	 	<option value="4"  <%=applicationtype_id4 %>> Drug Ware House</option> --%>
	        	</select></font></td> 
	        </tr>  -->
	    	<%
	    }
	    %>
        
	    <!-- <tr><td width="48%" class="label">
			  	<div align="right"><font face="" size="" color="#333333"><font color='red'>*</font> Display in portal
			  	&nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="" size="">
			  	<input type="radio" name="displayInPortal" value="1" >Yes&nbsp;<input type="radio" name="displayInPortal" value="0" checked>No
			  	</font></td></tr>  -->    
	 	<tr>
	    <td width="48%" class="label">
	    <div align="right"><font face="" size="" color="#333333">
	    <%
	      System.out.println("menu.getMenu_level()----->"+menu.getMenu_level());
	    if(menu.getMenu_level().equals("4")   ) 
	    {%>
	    <font color='red'>*</font>URL&nbsp;&nbsp;
	    <%}
	    else
	    if((!menu.getMenu_level().equals("1") ) && (!menu.getMenu_level().equals("0")) && (!menu.getMenu_level().equals("")) )
	    {
	    System.out.println("boolean result level"+(!menu_level.equals("1")));
	    %>
	    	URL&nbsp;&nbsp;
	    <%}%>
	    
	    </font></div></td>
	  <% if((!menu.getMenu_level().equals("1") ) && (!menu.getMenu_level().equals("0")) && (!menu.getMenu_level().equals("")) ) 
	   { 
	  System.out.println("boolean result level"+(menu_level.equals("1")));
	  %>
	    <td colspan="2" class="control" width="80%"><font face="" size="" color="#000000">
	    
	<input type="text" name="url" value="<%=menu.getUrl()%>" onkeypress = "return checkInput1(this,6,120,event)" size='35' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height: 26px;width:115px;">
	    </font></td>
	    <%}%>
	    </tr>
	    	
	   </table>
	     
	     <table width="97%" cellspacing="0" cellpadding="0" align="center">
		<tr class="FOOTER">
			<td colspan=2> 
			</tr>
			
			<tr>
			<td colspan=2>
	<% if(hmode.equals("SAVE"))
		{
			%>
			<div align="center" class="control_button2">
			<a style=cursor:hand class="button" tabindex="0" onClick='assignMode1(event,form1,"SAVE")' onkeyPress='assignMode1(event,form1,"SAVE")'><span class="save">Save</span></a>
			<a style=cursor:hand class="button" tabindex="0"  onClick='resetForms1(event,form1,"RESET")' onkeyPress='resetForms1(event,form1,"RESET")' ><span class="clear">Clear</span></a>
			<%
		}
	   else if(hmode.equals("UPDATE"))
	    {
		%>
		<div align="center" class="control_button2">
		<a style=cursor:hand class="button" tabindex=0 onClick='assignModeUpdate(event,form1,"UPDATE")' onkeyPress='assignModeUpdate(event,form1,"UPDATE")'><span class="save">Save</span></a>
		<%} %> 
		
	
		<a style=cursor:hand class="button" tabindex="0" onKeyPress="submitpage1(event,form1,'DEFAULT');" onClick='submitpage1(event,form1,"DEFAULT");'><span class="cancel">Cancel</span></a></div></td>
		</tr></table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="parent_id" value="<%=menu.getParent_id()%>">
	<input type="hidden" name="menu_id" value="<%=menu.getMenu_id()%>">
	<input type="hidden" name="portalDisplay" value="">
	<anticsrf:csrftoken/>	
	</form>
	</body>
	</html>
	
	
