 <!--
 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2007-2008  
 ## Client's Name					: PGI
 ## Project Name					: HIMS
 ## Phase						: Development
 ## Name of Developer		 		: Mr. Nitin Vohra
 ## Module Name					: User Managment
 ## Date of creation					: 12/11/2008
 ## Purpose						: Add Page
 ## Previous Form(Calling)				: umgmtGroupRole_cntMst.jsp
 ## Functions Used					:resetForm(),assignModeGroupRole(),getCombo()
 ## Name of Tables used for reference 	            : GBLT_GROUP_MST,GBLT_ROLE_MST,GBLT_METATABLE_TYPE_MST,GBLT_GROUP_ROLE_MST
 ## Name of Tables used for data updation/insertion	: Dynamic
 ## Next Form	(Called)				: List Page(umgmtGroupRole_lstMst.jsp),result.jsp
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
<%@ page language="java" import="java.sql.*"%>
<jsp:useBean id="beanpage" class="usermgmt.masters.UmgmtGroupRoleMstBean" scope="request">
<jsp:setProperty name="beanpage" property="*" />
</jsp:useBean>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

<%
	
String HospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	
System.out.println("HospitalCode"+HospitalCode);
beanpage.setHOSPITAL_CODE(HospitalCode);
	String hmode = "";
	String title = "";
	hmode = request.getParameter("hmode");
	
	System.out.println("mode in add of Group role-----"+hmode);
	
	
	String module = request.getParameter("module_id");
	if ( module == null ) module="";
	if(hmode == null)
		hmode ="SAVE";
	if(hmode.equals("SAVE"))
		title="Add";
	if(hmode.equals("UPDATE"))
	{
		title = "Modify";
	}
	
	String query1 	= " SELECT GNUM_GROUP_CODE , INITCAP(GSTR_GROUP_NAME) "+
			          " FROM GBLT_GROUP_MST where GNUM_ISVALID=1 and GNUM_HOSPITAL_CODE='"+HospitalCode+"' order by INITCAP(GSTR_GROUP_NAME)"; 

	/*String query2 = " SELECT gnum_module_id, INITCAP (gstr_module_name) "+
					" FROM gblt_metatable_type_mst m WHERE gbl_isvalid = 1 AND gnum_module_id NOT in "+
	    			"   ( select gnum_module_id FROM gblt_group_role_mst WHERE "+
	    			" gnum_group_code='"+beanpage.getGroupCode()+"'  and GNUM_HOSPITAL_CODE='"+HospitalCode+"' ) "+ 
	    			//" and GNUM_HOSPITAL_CODE='"+HospitalCode+"' "+
					" order by initcap(gstr_module_name) ";*/
					
    String query2 = " SELECT gnum_module_id, INITCAP (gstr_module_name) "+
					" FROM gblt_metatable_type_mst m WHERE gbl_isvalid = 1 "+
	    		    //" and GNUM_HOSPITAL_CODE='"+HospitalCode+"' "+
	    		   // "and GNUM_HOSPITAL_CODE='100' "+
					" order by initcap(gstr_module_name) ";
					
					


	/*String query3  = " SELECT GNUM_ROLE_ID , INITCAP(GSTR_ROLE_NAME) "+
			         " FROM GBLT_ROLE_MST A where GBL_ISVALID=1 and GNUM_MODULE_ID = '"+module+"'"+
			         " and GNUM_ROLE_ID not in ( select GNUM_ROLE_ID from GBLT_GROUP_ROLE_MST where "+
			         " GNUM_GROUP_CODE ='"+beanpage.getGroupCode()+"' and GNUM_MODULE_ID='"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"')"+
				  	" and GNUM_HOSPITAL_CODE='"+HospitalCode+"' "+
					" order by INITCAP(GSTR_ROLE_NAME)";*/
					
					
   String query3  = " SELECT GNUM_ROLE_ID , INITCAP(GSTR_ROLE_NAME) "+
			         " FROM GBLT_ROLE_MST A where GBL_ISVALID=1 and GNUM_MODULE_ID = '"+module+"'"+
			         " and GNUM_ROLE_ID not in ( select GNUM_ROLE_ID from GBLT_GROUP_ROLE_MST where "+
			         " GNUM_ISVALID=1 and GNUM_GROUP_CODE ='"+beanpage.getGroupCode()+"' and GNUM_MODULE_ID='"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"')"+
				  	" and GNUM_HOSPITAL_CODE='"+HospitalCode+"' "+
					" order by INITCAP(GSTR_ROLE_NAME)";
					
					
					
					
					
	/*String query4  = " SELECT GNUM_ROLE_ID , INITCAP(GSTR_ROLE_NAME) "+
   					 " FROM GBLT_ROLE_MST A where GBL_ISVALID='"+beanpage.getCombo1()+"' and GNUM_MODULE_ID = '"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"' " +
					 " and GNUM_ROLE_ID not in ( select GNUM_ROLE_ID from GBLT_GROUP_ROLE_MST where "+
			         " GNUM_ISVALID=1 and GNUM_GROUP_CODE ='"+beanpage.getGroupCode()+"' and GNUM_MODULE_ID='"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"' and gnum_role_id <> '"+beanpage.getRole_id()+"')";*/

	String query4  = " SELECT GNUM_ROLE_ID , INITCAP(GSTR_ROLE_NAME) "+
		 " FROM GBLT_ROLE_MST A where GBL_ISVALID=1 and GNUM_MODULE_ID = '"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"' " +
	 " and GNUM_ROLE_ID not in ( select GNUM_ROLE_ID from GBLT_GROUP_ROLE_MST where "+
    " GNUM_ISVALID=1 and GNUM_GROUP_CODE ='"+beanpage.getGroupCode()+"' and GNUM_MODULE_ID='"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"' and gnum_role_id <> '"+beanpage.getRole_id()+"')";

	
String query5  = " SELECT GNUM_ROLE_ID , INITCAP(GSTR_ROLE_NAME) "+
   			   " FROM GBLT_ROLE_MST A where GBL_ISVALID='"+beanpage.getCombo1()+"' and GNUM_MODULE_ID = '"+beanpage.getModule_id()+"' and GNUM_HOSPITAL_CODE='"+HospitalCode+"' " ;		         
			         

	System.out.println("query1	----->"+ query1	);
	System.out.println("query2	----->"+ query2	);
	System.out.println("query3	----->"+ query3	);
	System.out.println("query4	----->"+ query4	);
	System.out.println("query5	----->"+ query5	);
	
   %>

	<html><head><title>Group Role Master</title>
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/JavaScript" src="../js/Validation.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="../js/functionality.js"></script>
	
	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
	<script language="JavaScript" src="../js/calendar.js"></script>
	<script language="javascript">
	
	function focusOnLoad()
	{
	var hmode="<%=hmode%>"

//alert('hmode----'+hmode)
	
if(hmode=="SAVE" || hmode=="ADDPAGE")
{	
	if(document.getElementsByName("groupCode")[0].value=="0")
		{
		document.forms[0].groupCode.focus();	
		
		
		}
		else
		if(document.getElementsByName("module_id")[0].value=="0")
		{
		document.forms[0].module_id.focus();	
		
		
		}
		else
		if(document.getElementsByName("role_id")[0].value=="0")
		{
		document.forms[0].role_id.focus();	
		
		
		}
}
else
if(hmode=="UPDATE")
	{		
		document.forms[0].role_id.focus();	
	}
	
}
	
	
window.history.foward();


function resetForm(e,form1,mode)
{
var hmode="<%=hmode%>";

	if(e.type=="click"||e.keyCode==13)
	{
		if(mode == "RESET" &&  hmode=="ADDPAGE")
			{
			document.form1.groupCode.value =0 ;
			document.form1.module_id.value =0 ;
			document.form1.role_id.value =0 ;		
			}
		   else
		if(mode == "RESET" && hmode=="UPDATE")
			{
			document.form1.role_id.value =0 ;		
			}	
		else
		{
			document.form1.hmode.value = mode;			
			document.form1.submit();
		}	
	}	
}
	function assignModeGroupRole(e,mode)
	{
	
if(e.type=="click"||e.keyCode==13)
	   		{
	if(document.form1.groupCode.value=="0")
		    {
		alert("Select Group Name ");
		document.form1.groupCode.focus();
		    }
		else
	 if(document.form1.module_id.value=="0")
	        {
		alert("Select Module Name ");
			document.form1.module_id.focus();
		    } 
		else
	 if(document.form1.role_id.value=="0")
	       {
		alert("Select Role Name");
		document.form1.role_id.focus();
	       }	
		else 
	if(mode=='SAVE')
		{
			
				document.form1.hmode.value = mode;		
		 		document.form1.submit();	
		 	
		}
			else
	   		{
	   		
	   			document.form1.hmode.value = mode;		
	    		document.form1.submit();	
	    	}	
	   	}
	}
	
	</script>
	</head>
	<!-- 
	<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
	<body  width="100%" topmargin="0" onload="focusOnLoad()">
	<form name="form1" method="post" action="umgmtGroupRole_cntMst.jsp">
	<table width="97%" border="0" cellspacing="0" cellpadding="0">
		<tr><td><div align="center">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr><td colspan=2><font color='#000' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
				<strong>Group Role Master >> <%=title%> Page</strong></font></td>
			</tr><%
	 if(hmode.equals("UPDATE"))
	 {%>
	 	 <table class="formbg" width="97%" border="0" cellspacing="1" cellpadding="0" align="center">
	      <tr>
	      <td class="ShadedSubTitleTagImage" colspan=2 align="right" height="25"></td>
		</tr>
		<%     
	 }
	 else
	 {
		%><tr ><td class="ShadedSubTitleTagImage" colspan=2 align="right" height="25"></td></tr>
			
		</table>
		<table   class="formbg" width="97%" border="0" cellspacing="1" cellpadding="0" align="center">
	
	<%}%>
	<tr><td class="LABEL" width="50%"><div align="right"><font color='red'>*</font>Group Name</div></td>
	
	<%
	if(hmode.equals("UPDATE"))
	{
	%>
	<td class="CONTROL"><input type="text" tabindex="1" size="24" name="groupName" value="<%=beanpage.getGroupName()%>" readonly></td>
	<%
	}
	else
	{
	%>
	<td class="CONTROL"><%=beanpage.getCombo("groupCode",query1,beanpage.getGroupCode(),"onChange=\"submitpage('ADDPAGE')\"",1)%></td> 
	<%
	}
	%>
	
	</tr>
	<tr><td class="LABEL" width="50%"><div align="right"><font color='red'>*</font>Module Name </div></td>
	<%if(hmode.equals("UPDATE"))
	{%>
	
	<td class="CONTROL"><input type="text" tabindex="2" size="24" name="moduleName" value="<%=beanpage.getModuleName()%>" readonly></td>
	<%}
	else
	{%>
	<td class="CONTROL"><%=beanpage.getCombo("module_id",query2,beanpage.getModule_id(),"onChange=\"submitpage('ADDPAGE')\"",1)%></td>
	<% }%>	
	
	</tr>
	
	<tr><td class="LABEL">
	<div align="right"><font color='red'>*</font>Role Name </div></td>
	<%if(hmode.equals("UPDATE"))
	{%>
	
	<td class="CONTROL"><%=beanpage.getCombo("role_id",query4,beanpage.getRole_id(),"",1)%>
	<input type="hidden" name="role_IdPrevious" value="<%=beanpage.getRole_id()%>"></td>
	<%
	System.out.println("value of previos id"+beanpage.getRole_IdPrevious());
	
	}
	else
	{%>
	<td class="CONTROL"><%=beanpage.getCombo("role_id",query3,beanpage.getRole_id(),"",1)%></td>
	<%}%>
	
	
	</tr>

	</table>
		

	
	<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2>
				<div align="center" class="control_button2"> 
	<%if(hmode.equals("ADDPAGE"))
	{
	%><a style="cursor:pointer;" class="button" tabindex="0" onClick='assignModeGroupRole(event,"SAVE")' onkeyPress='assignModeGroupRole(event,"SAVE")'><span class="Save">Save</span></a>
	<%}
	else
	{
	%><a style="cursor:pointer;" class="button" tabindex="0" onClick='assignModeGroupRole(event,"UPDATE")' onkeyPress='assignModeGroupRole(event,"UPDATE")'><span class="Save">Save</span></a>
	<%}
	%>
	<a style="cursor:pointer;" class="button" tabindex=0 
		onClick='resetForm(event,form1,"RESET")' onkeyPress='resetForm(event,form1,"RESET")'><span class="clear">Clear</span></a>
			<a style="cursor:pointer;" class="button" tabindex=0
			onClick='resetForm(event,form1,"DEFAULT")'
			onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="cancel">Cancel</span></a></div>
		</td></tr></table>
			
	<input type="hidden" name="hmode">
	<anticsrf:csrftoken/>
	
	<%
	if(hmode.equals("UPDATE"))
	{
	%>
		<input type="hidden" name="groupCode" value="<%=beanpage.getGroupCode()%>">	
		<input type="hidden" name="module_id" value="<%=beanpage.getModule_id()%>">	
				
	<%
	}
	%>
	
	
	<input type="hidden" name="HOSPITAL_CODE" value="<%=HospitalCode%>">	
	
	
</form>
	</body></html>
