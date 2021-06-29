<!--
Developed By: Anshul Vaid
Date:16 July 2012
For: cdac Noida
Project Name : Ayush


-->

<jsp:useBean id="beanObj" class="usermgmt.masters.UmgmtModuleSubscriptionBean_Mst" scope="request">
<jsp:setProperty name="beanObj" property="*"/>
</jsp:useBean>

<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>

<%@ page import = "java.util.*" %>
<script>
window.history.foward();
</script>
<html>
<head>



<%
String strSeatId=(String)session.getAttribute("SEAT_ID");
//String strSeatId="10001";
//System.out.println("strSeatId---->"+strSeatId);
String strHospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	
beanObj.setHospital_code(strHospitalCode);

System.out.println("combo val of hosp is"+beanObj.getStrHospCombo());

//System.out.println("strHospitalCode---->"+strHospitalCode);
if(strHospitalCode == null || strSeatId==null )
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}

beanObj.setSeatId(strSeatId);
beanObj.setIsvalid("1");

//Declare Variable
boolean retValue 		= true;
List AL_List;

String 	status      	= "";

String 	add_page 		= "umgmtModuleSubscriptionAdd_Mst.jsp";



String 	cnt_page 		= "umgmtModuleSubscriptionCnt_Mst.jsp";


String 	result_page 	= "result.jsp";

/*
TextArea	--> "T"
CheckBox	--> "C"
Option		--> "O"
Default		--> TextBox(No need to specify)
*/

//View Functionality
List	viewHdr			=	new ArrayList();
Map viewControl 		= 	new HashMap();

//Header
viewHdr.add("Group Name");
viewHdr.add("Group Associated");
viewHdr.add("Effective Date");
//Control Type
String 	mode         	= request.getParameter("hmode");
System.out.println("mode in controller IS "+mode);
System.out.println(" hmode is "+request.getParameter("hmode")); 
System.out.println("combo val of hosp is"+beanObj.getStrHospCombo());

if(mode == null || mode.equals("DEFAULT"))
{		
%>
	<jsp:forward page="<%=add_page%>">
	     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
	     	<jsp:param name="Name" value="Result >" />
		<jsp:param name="mode" value="DEFAULT" />
		
		</jsp:forward>
<%
}			
if(mode.equals("TEMP"))
{

	 try
	 {
		 %>
		 
<jsp:forward page = "<%=add_page%>"/>


<div align="left">
   <%=beanObj.getListOfModules(request)%>
   </div>
 
<%
     }
catch(Exception e)
{
	
	
}

}
if(mode.equals("EDIT"))
{
	System.out.println("INSIDE MODE EDIT");		

}
if(mode.equals("UPDATE"))
{		
	if (retValue)
	{			
%>
		<jsp:forward page="<%=add_page%>"/>
<%	
	}
	else
	{
		
%>	
		<jsp:forward page="<%=result_page%>">
			<jsp:param name = "mode" value="DEFAULT"/>
			<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
			<jsp:param name = "Name" value="Error Page >>"/>
			<jsp:param name = "status" value="<%=beanObj.getStatus()%>"/>			
		</jsp:forward>
<%	
	}
}
if(mode.equals("DELETE"))
{
	
	%>
	<jsp:forward page="<%=result_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="status" value="<%=status%>" />
		<jsp:param name="mode" value="DEFAULT" />
	</jsp:forward>
<%
}	


if(mode.equals("SAVE"))
{
	System.out.println("inside save mode");
	
	
	retValue=beanObj.insertRecord(request);		
	
	if (retValue)
	{


	
				
%>			
		<jsp:forward page="<%=result_page%>">
     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
     	<jsp:param name="Name" value="Result >" />
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="status" value="<%=beanObj.getStatus()%>" />
	</jsp:forward>
<%	
	}
	else
	{
		
%>	
		<jsp:forward page="<%=result_page%>">
		<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
		<jsp:param 	name = "Name" 		value="Error Page >>"/>
		<jsp:param 	name = "mode" 		value="DEFAULT"/>
		<jsp:param 	name = "status" 	value="<%=beanObj.getStatus()%>"/>
		</jsp:forward>
<%	
	}
}
		if(mode.equals("CLEAR"))
		{
			
			//newseat.initializeNewMode();			
			%>
						<jsp:forward page="<%=add_page%>"/>
						<div align="left">
			   <%=beanObj.getListOfModules(request)%>
			   </div>
	<%	
		}
		%>
			
</head>
</html>