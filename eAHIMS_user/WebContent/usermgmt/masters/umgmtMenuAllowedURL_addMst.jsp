	<!--
 /***************************Start of program*****************************\
e
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
	<jsp:useBean id="menuAllowed" class="usermgmt.masters.UmgmtMenuAllowedURL_MstBean" scope="request">
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
		
		System.out.println("getMenu_level()---->"+menuAllowed.getMenu_level());
	%>
	
		<html>
		<head><title>Menu Allowed URL Master </title>
<!-- 			<LINK href="../../images/master.css" type="TEXT/CSS" rel=STYLESHEET>
 -->			<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
 				 <link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
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
				else 
		  if(document.form1.menuIds.value=="0")
				document.forms[0].menuIds.focus();
				else
		  if(document.form1.url.value=="")
			  document.forms[0].url.focus();
				// else 
		//if(document.form1.menuclass_id.value=="0")
			//	document.forms[0].menuclass_id.focus();
		
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
		  if(document.form1.menuIds.value=="0")
				document.forms[0].menuIds.focus();
				else
		  if(document.form1.url.value=="")
			  document.forms[0].url.focus();
	//	if(document.form1.menuclass_id.value=="0")
	//			document.forms[0].menuclass_id.focus();
				
				
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
		  if(document.form1.url.value=="")
			  document.forms[0].url.focus();
				
					
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
		  if(document.form1.url.value=="")
			  document.forms[0].url.focus();
		 // if(document.form1.menuName3.value=="")
			//	document.forms[0].menuName3.focus();
			//	else
		//if(document.form1.menuclass_id.value=="0")
		//		document.forms[0].menuclass_id.focus();
		//		else 
	
			}
			
}
else	///in case of Modify page
if(hmode=="UPDATE")
	{
	document.forms[0].menu_name.focus();

	}	

	
}
		
		window.history.forward();
		
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
		  	//document.form1.menu_level.value=2;
			document.form1.initLevel.value = 0;		
			//if(document.form1.menu_level.value!=0)
			document.form1.moduleId.value = 0;
			//document.form1.menuclass_id.value =0;
			//if(document.form1.menu_level.value==2)
			//document.form1.menuName1.value = "";
			//if(document.form1.menu_level.value==3)
			//document.form1.menuName2.value ="";
			//if(document.form1.menu_level.value==4)
			//document.form1.menuName3.value = "";	
			//document.form1.displayInPortal[1].checked=true;	
			
			document.form1.menuIds.value=0;
			document.form1.url.value="";
			if((document.form1.menu_level.value!=1) && (document.form1.menu_level.value!=0) )	
			document.form1.url.value = "";		
           	// document.form1.menu_level.value = 0;	
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
			
			var url="";
			var menuAllowed="";
			
			
				
				menuAllowed=removeLeadingTrailingSpace(document.form1.url.value);
				document.form1.url.value = menuAllowed;
				
				if(document.form1.initLevel.value=="0")
				alert("Select Root Menu");
				else if(document.form1.moduleId.value=="0")
				alert("Select Module");
				else if(document.form1.menuIds.value=="0")
				alert("Enter Menu Name");
				else if(document.form1.url.value=="")
				alert("Enter the URL");
				else
				{
							
						document.form1.hmode.value = mode;		
	        			document.form1.submit();
	        		
				}
			}
	  		
	  	}
	
	  	function assignModeUpdate(e,form1,mode)
{
	  	
 	if(e.type=="click" || e.keyCode==13)
    {
			var menuAllowed;
			menuAllowed=removeLeadingTrailingSpace(document.form1.url.value);
			document.form1.url.value = menuAllowed;
			
			if(document.form1.url.value=="")
				alert("Enter Allowed URL");
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
	 	<body  width="100%" topmargin="0" onload="focusOnLoad()">
		<form name="form1" method="POST" action="umgmtMenuAllowedURL_cntMst.jsp">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr><td><div align="center">
	   	<table class="formbg" width="100%" border="0" cellspacing="1" cellpadding="0">
	            <tr>
	             <td class="ShadedSubTitleTagImage" colspan=2 align="left">
	<strong>Menu Allowed URL Master >> <%=title%> Page</strong></td>
	            </tr>
	   
			
		
		<% 
		if(hmode.equals("SAVE"))
	    {
			%>
		 <!--    <tr display="none"><td width="48%" class="adddatalabelNewRight">
		  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333">
		  	<font color='red'>*</font>Level of Hierarchy&nbsp;&nbsp;</font></div></td>
		  	<td colspan="2" class="adddatavalueNew" width="52%"> <font face="MS Sans Serif" size="2">
		  	<select name="menu_level" onChange="difflevel(this);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  <%--	<%=menuAllowed.viewLevel()%>  --%>
		  	</select></font></td></tr>  -->
		   
		    <tr><td width="48%" class="label">
			 <div align="right"><font color='red'>*</font>Root Menu
			 &nbsp;&nbsp;</div></td>
			  <td colspan="2" class="control" width="52%"> <font face="MS Sans Serif" size="2">
			 <select name="initLevel" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			  <%=menuAllowed.viewInitLevel()%>
			  </select></font></td></tr>
		    <tr><td width="48%" class="label">
		    <div align="right"> <font color='red'>*</font>Modules
			 	&nbsp;&nbsp;</div></td>
			  	<td colspan="2" class="control" width="52%"> 
			  	<select name="moduleId" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menuAllowed.viewModuleId()%>                                                                                                         
			  	</select></td></tr>
			  	<tr><td width="48%" class="label">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>
			  	Menu &nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="MS Sans Serif" size="2">
			  	<select name="menuIds" onChange="submitpage('CMB')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
		  		<%=menuAllowed.viewMenuIds()%>                                                                                                        
		  		</select></font></td></tr>
		  	  
		  		 <tr><td width="48%" class="label">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>
			  	Allowed URL &nbsp;&nbsp;</font></div></td>
			  	<td colspan="2" class="control" width="52%"> <font face="MS Sans Serif" size="2">
			  	<input type="text" name="url" value="<%=menuAllowed.getUrl()%>" onkeypress = "return checkInput1(this,6,120,event)" size='35' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height: 26px;width:115px;">
	            </font></td>
	    
		  		 </tr>
	        
		   <%
	    }
	   if(hmode.equals("UPDATE"))
	    {
	    	%>
	    	<tr>
	    	<td width="48%" class="label">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font> Module
			  	Name &nbsp;&nbsp;</font></div></td>
	    	<td width="52%" class="control" colspan="2">
	    	<%=menuAllowed.getParent_name()%>
			</td>
	    	</tr>
	    	<tr>
	    	<td width="48%" class="label">
			  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font> Menu
			  	Name &nbsp;&nbsp;</font></div></td>
	    	<td width="52%" class="control" colspan="2">
	    	<%=menuAllowed.getMenu_name()%>
			</td>
	    	</tr>

	  		 <tr><td width="48%" class="label">
		  	<div align="right"><font face="MS Sans Serif" size="2" color="#333333"><font color='red'>*</font>
		  	Allowed URL &nbsp;&nbsp;</font></div></td>
		  	<td colspan="2" class="control" width="52%"> <font face="MS Sans Serif" size="2">
		  	<input type="text" name="url" value="<%=menuAllowed.getUrl()%>" onkeypress = "return checkInput1(this,6,120,event)" size='35' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height: 26px;width:115px;">
		  	<input type="hidden" name="parent_id" value="<%=menuAllowed.getUrl()%>"/>
		  	<input type="hidden" name="menuIds" value="<%=menuAllowed.getMenu_id()%>"/>
            </font></td>
    
	  		 </tr>
	    	<%
	    } 
	    
	    	%>     
	 	
	    
	   <tr class="FOOTER">

		<td colspan=2>
	</tr>
	
		
	     </table></td></tr></table>
	     <table width="100%" border="0" cellspacing="1" cellpadding="0">
	     <tr>
		<td class="addtoolbar" style="width: 881px"  align="center"> 
	<% if(hmode.equals("SAVE"))
		{
			%>
				<div align="center" class="control_button2">
			<!-- <a style=cursor:hand><img src="../../images/Save.gif" class="link" tabindex=0 onClick='assignMode1(event,form1,"SAVE")' onkeyPress='assignMode1(event,form1,"SAVE")'></a> -->
			<a 	style=cursor:hand class="button" tabindex=0 onClick='assignMode1(event,form1,"SAVE")' onkeyPress='assignMode1(event,form1,"SAVE")'><span class="save">Save</span></a>
			<!-- <a style=cursor:hand><img src="../../images/Clear.gif" tabindex="0"  onClick='resetForms1(event,form1,"RESET")' onkeyPress='resetForms1(event,form1,"RESET")' ></a> -->
			<a 	style=cursor:hand class="button" tabindex=0 onClick='resetForms1(event,form1,"RESET")' onkeyPress='resetForms1(event,form1,"RESET")'><span class="clear">Clear</span></a>
			</div>
			<%
		}
	   else if(hmode.equals("UPDATE"))
	    {
		%>
		<div align="center" class="control_button2">
		<!-- <a style=cursor:hand><img src="../../images/Save.gif" class="link" tabindex=0 onClick='assignModeUpdate(event,form1,"UPDATE")' onkeyPress='assignModeUpdate(event,form1,"UPDATE")'></a> -->
		<a 	style=cursor:hand class="button" tabindex=0 onClick='assignModeUpdate(event,form1,"UPDATE")' onkeyPress='assignModeUpdate(event,form1,"UPDATE")'><span class="save">Save</span></a>
		</div>
		<%} %> 
		
			
		
	<div align="center" class="control_button2">
		<!-- <a style=cursor:hand><img src="../../images/Cancel.gif" class="link" tabindex="0" onKeyPress="submitpage1(event,form1,'DEFAULT');" onClick='submitpage1(event,form1,"DEFAULT");'></a></div></td> -->
        <a 	style=cursor:hand class="button" tabindex=0 onClick='submitpage1(event,form1,"DEFAULT");' onkeyPress='submitpage1(event,form1,"DEFAULT");'><span class="cancel">Cancel</span></a> 
		</div>
		</tr></table>
	<input type="hidden" name="hmode">
	<input type="hidden" name="parent_id" value="<%=menuAllowed.getParent_id()%>">
	<input type="hidden" name="menu_id" value="<%=menuAllowed.getMenu_id()%>">
	
	<anticsrf:csrftoken/>	
	</form>
	</body>
	</html>
	
	
