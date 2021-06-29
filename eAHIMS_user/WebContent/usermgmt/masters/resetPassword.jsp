	
	
	<%@page import="usermgmt.umgmtGlobal"%>
<HTML>
	<head>
	<title>Reset User Password</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
	<script language="JavaScript" src="../js/functionality.js">
	
	</script>
	<script>
	window.history.forward() ;
	</script>
	
	
	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
	<!-- 
	<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" > -->
	<body  width="100%" topmargin="0" >
	
	<script>
	
	
	function edit123()
	{
		len = checkCounter();
		
		if(len > 0)
		{
			if(len>1)
				getmsgStr(5);
			else
			{ 
				//openMyPopup();
				if ( confirm("Do You Want To Reset The Password....!!") == true )
				{	document.form1.hmode.value = "RESET";
					document.form1.submit();
				
				}
			}
		}
		else
			getmsgStr(0);
	}
	function unlockPassword()
	{
		
		
		
		len = checkCounter();
		
		if(len > 0)
		{
			if(len>1)
				getmsgStr(5);
			
			else
			{ 
				//openMyPopup();
					var chks=document.all.item("chk");
					for(var i=0;i<chks.length;i++)
					{
						if(chks[i].checked)
							{
							if(chks[i].value.split("^")[2]=="0"){
							alert("User Already Unlocked");
							return false;
							}
							}
					}
					if ( confirm("Do You Want To Unlock The User....!!") == true )
				{	document.form1.hmode.value = "UNLOCK";
					document.form1.submit();
				
				}
			}
		}
		else
			getmsgStr(0);
	}
	</script>
	
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	
	
	<%@ include file="fileA.jsp"%>
	
	<%
	
	String passw=	"";
	String pass	=	request.getParameter("pwd");
	String result=request.getParameter("reult");
	String pwdDisplay	=	"";
System.out.println("result------>"+result);	
	if(result!=null)
	{
		String displayresult="";
		displayresult	+=	"<table><tr><td colspan='2'>&nbsp;</td>";
		displayresult	+="<td colspan='2'><font color='red' size=3>"+result+"</font></td></tr></table>";
		out.print(displayresult);
		
	}
	
	//if(pass!=null)
//	{
	//	passw=pass;
		
		//pwdDisplay	+=	"<table><tr><td colspan='2'>&nbsp;</td>";
		//pwdDisplay	+="<td colspan='2'><font color='red' size=3>Your System Generated Password  is......:<b> "+passw+"</b></font></td></tr></table>";
		//out.print(pwdDisplay);
		
	
//	}
	
	
	/* These are the fields to be filled by the developer*/
	
	record_per_page			=	10;
	no_of_fields			=	5;
	no_of_primary_keys		=	3;
	page_per_block			= 	10;
	//for combo
	no_of_combo				=	0;
	
	
	
	
	pagename     =	"resetPassword.jsp";
	nextpage     =	"resetPassword_cnt.jsp";
	previouspage =	"listing_training.jsp";
	
	title         = "Reset User Password";
	table_heading = "Reset User Password";
	String HospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
	//String HospitalCode=umgmtGlobal.HospitalCode;
	System.out.println("HospitalCode============="+HospitalCode);
	query1 = " SELECT gstr_user_name, to_CHAR(gdt_effect_date,'DD-Mon-YYYY'), TO_CHAR (gdt_expiry_date, 'DD-Mon-YYYY'),DECODE(GNUM_ISVALID,'1','Active','In Active'),DECODE(gnum_islock,'0','Unlocked','Locked') from GBLT_USER_MST  a";	
			
	query2	=	"  SELECT GNUM_USERID,gstr_user_name,GNUM_ISLOCK FROM GBLT_USER_MST a";
				 
	action	=	" GNUM_ISVALID=1 ";
	
	hospitalCode="a.GNUM_HOSPITAL_CODE"; 
	System.out.println("query1"+query1);
	System.out.println("query2"+query2);
	//Header 
	//headlist.add("#");
	headlist.add("User Id");
	headlist.add("Effective Date");
	headlist.add("Expiry Date");
	headlist.add("Status");
	headlist.add("Lock Status");
	
	//Sorting Field
	Map sortmap = new HashMap();
	sortmap.put("User Id","a.GSTR_USER_NAME");
	
	
	
	//Searching Field
	Map searchmap = new HashMap();
	searchmap.put("User Id","a.GSTR_USER_NAME");
	
	
	
	
	
	%>
	
	<%@ include file="fileC.jsp"%>