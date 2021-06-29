<!--
 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2008-2009 
 ## Client's Name						: Delhi
 ## Project Name						: AHIMS
 ## Phase								: Development
 ## Name of Developer		 			: Ankur Mehrotra
 ## Module Name							: User Management
 ## Date of creation					: 15-05-09
 ## Purpose								: Hospital Master
 ## Previous Form(Calling)				: login.jsp
 ## Functions Used						: getField(), updaterecord()
 ## Name of Tables used for reference 	: 
 ## Name of Tables used for data updation/insertion	: GBLT_HOSPITAL_MST
 ## Next Form	(Called)				: ChangeAdminPassword.jsp
 ## Date of Modification				: 
 ## Unit Tested By	& Date				: Renuka Singh & 17-11-2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :Y
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :Y
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :N
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :Y
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:Y
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :Y
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*"%>
<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>

<jsp:useBean id="hospitalMst" class="usermgmt.masters.HospitalMstBean" 	scope="request" >
<jsp:setProperty name="hospitalMst" property="*" />
</jsp:useBean>

<jsp:useBean id="connect" class="HisGlobal.HisMethods" scope="request" />
<jsp:useBean id="securePwd" class="login.SecurityUtil" scope="request" />
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">

<html>
<head>
<title>Welcome... to Hospital Master!!!</title>
</head>
<script type="text/javascript">
window.history.forward();

function resetForm(e,form1,mode)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			if(mode == "DEFAULT")
			{
			   document.form1.action="ChangeAdminPassword.jsp";
			   document.form1.submit();
			}	
		}		
	}

</script>
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0">
<form name="form1" method="post" action="">
<%
	//getting hospital code from the session & according to hospital code user name found & user name is set in the session.
	String username = "";
	String status = "";
	String hcode="";
	boolean bFlag = true;
	try {
		String hmode = request.getParameter("hmode");
		if (hmode == null)
			hmode = "";

		String change_pass_page = "HospitalMaster_mst.jsp";
		String 	result_page 	= "result.jsp";
		
		if(hmode.equals("CMB"))
		{	 		
			%><jsp:forward page="<%=change_pass_page%>">
			<jsp:param name = "hmode" value="SAVE"/>
			</jsp:forward>
			<%
		}
		
		if (hmode == null || hmode.equals("") || hmode.equals("INIT")) //initially hmode is set to INIT mode this condition is for getting value from session.
		{
			 hcode = (String) session.getAttribute("HOSPITAL_CODE");
			String seatId = (String) session.getAttribute("SEAT_ID");
			
					hospitalMst.setHospitalcode(hcode);
					hospitalMst.setSeatId(seatId);
					
					hospitalMst.setHospitalDetails();
			
%> <jsp:forward page="<%=change_pass_page%>">
	<jsp:param name="hmode" value="INIT" />
</jsp:forward> <%
 		}

 		if (hmode.equals("SAVE")) //for updating changed password.
 		{
 			System.out.println("hcode-------"+hcode);
 			if(CSRFTokenUtil.isValid(request))
 			{
 			bFlag = hospitalMst.updateHospitalMaster(request);
 			if (bFlag) 
 			{
 			status = "Hospital Details Modifed  Sucessfully !";
 			%> 
 		 	<jsp:forward page="<%=change_pass_page%>" >
		 	<jsp:param name = "nextpage" value="<%=change_pass_page%>"/>
			<jsp:param name="message" value="<%=status%>"/>
			</jsp:forward>
		 	<%
 			}
 			else 
 			{
 			status = "Hospital Details are not Modifed    !";
 			%> <%
 			}
 			%>
 			 <jsp:forward page="<%=change_pass_page%>" >
			 <jsp:param name = "nextpage" value="<%=change_pass_page%>"/>
			<jsp:param name="status" value="<%=status%>"/>
			<jsp:param name="hmode" value="INIT" />
	  		 </jsp:forward>
 			<%
 			}
 		}
 		else
		{
			%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
			<%
		}
 	} catch (Exception e) {
 		System.out.println("Exception inside cnt =" + e);
 		e.printStackTrace();
 	}
 %>
<table align="center">
	<tr>
		<td class="adddatalabel"><%=status%></td>
	</tr>
	<tr>
		<td class="adddatalabel">
		<div align="left"><a style="cursor: hand"><img
			src="../../images/Cancel.gif" class="link" tabindex=0
			onClick='resetForm(event,form1,"DEFAULT")'
			onkeyPress='resetForm(event,form1,"DEFAULT")'></a></div>
		</td>
	</tr>
</table>
</form>
</body>
</html>