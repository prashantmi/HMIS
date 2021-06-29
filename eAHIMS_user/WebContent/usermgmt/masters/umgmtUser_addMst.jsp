	<jsp:useBean id="user" class="usermgmt.masters.UmgmtUserMstBean" scope="request" />
	<jsp:useBean id="global" class="usermgmt.FuncLib" scope="request" />
	
	<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
	<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>
	
	<%String hospitalCode= (String)session.getAttribute("HOSPITAL_CODE"); 
	user.setHospitalCode(hospitalCode); %> 
	 <%@ page import="java.util.*"%>
	 <%@page import="usermgmt.masters.umgmtSessionMgtConfigUtil"%>
	 <%
	 ///For getting the password configuration Strength
	 umgmtSessionMgtConfigUtil myUtil = new umgmtSessionMgtConfigUtil();
	 List lst = myUtil.getPreviousValues();
	 //List lst = myUtil.getPreviousValues(hospitalCode);
	 String passwordStrengthMsg="";
	
	 
	 if(lst.get(7).equals("2"))
		 passwordStrengthMsg="Password Must Contain atleast one Alphabet and one number";
	 	else
	 if(lst.get(7).equals("3"))
		 passwordStrengthMsg="Password Must Contain atleast one Alphabet,one number and one Special Character";
	 
	 
	 
	 %>
	 
	 
	<%
	System.out.println("initally in add page value fo service---->"+user.getMyService());
	user.Popup(request);
	String empcheck="";
	String empcheck1="";
	String empcheck2="";
	empcheck1=user.getEmp();
	empcheck2=user.getEmp_new();
	if(empcheck1.equals(""))
	{
	empcheck=empcheck2;
	}else
	if(empcheck2.equals(""))
	{
	empcheck=empcheck1;
	}
	String result_update="";
	System.out.println("initally in add page value of empcheck--->"+empcheck);
	String result=request.getParameter("emp");
	System.out.println("result initally -->"+result);
	if(result==null)
	{
		result="1";
	}
	else if(result.equals(""))
	{
		result="1";
	}
	String type=null;
	String hmode = "";
	String title = "";
	hmode = request.getParameter("hmode");
	
	System.out.println("hmode on loading"+hmode);
	System.out.println("result on loading------->"+result);
	System.out.println("user.getEmp()------->"+user.getEmp());
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
	String isCheckedScanUser="";
	if(user.getScanUser().equals("2"))
			isCheckedScanUser="checked";
	%>
	<html><head><title>User Master</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

	
	<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/empPopup.js" type="text/JavaScript"></script>
	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
	<script language="JavaScript" src="../js/calendar.js"></script>
	<script language="JavaScript" src="../js/util.js" type="text/JavaScript"></script>
	<script language="JavaScript" src="../js/sha.js"></script>
	
	
	 
	<script>
	
function focusOnLoad()
{
	var hmode="<%=hmode%>";
	
	//alert('hmode---'+hmode);
	
if(hmode=="SAVE")
	{	
	if(document.getElementsByName("emp")[0].checked==true)
		document.forms[0].userName.focus();
		else
	if(document.getElementsByName("emp")[1].checked==true)
		document.forms[0].ufullName.focus();
	}
	else
if(hmode=="UPDATE")
	{	
	if(document.getElementsByName("emp")[0].checked==true)
		document.forms[0].userLevel.focus();
		else
	if(document.getElementsByName("emp")[1].checked==true)
		document.forms[0].ufullName.focus();
	}
				
}
	
	window.history.forward() ;
	
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
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./:_?";
			if(validationIndex==7)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";  //used for a-z,A-Z,0-9,space
			if(validationIndex==8)// Added By Pratichi Maheshwari
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/ ";
			if(validationIndex==9)// Added By Ankur
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			var aa = applicableStr.indexOf(keychar);
			
			if(applicableStr.indexOf(keychar)== -1)
				returnValue = false;
				
							
				return returnValue; 
		
		} 
	
	 
	function onLoad()
	{
	focusOnLoad();
	
		<% String groupList=user.groupOptions();%>
	  var Group="<%=groupList%>";
	  if(Group=="" )	{
			alert("No Record for Group found ");
		}
		//to populate Gender Combo
	  <% String genderList = user.genderOptions();%>
	  var Group="<%=genderList%>";
	  if(Group=="" )	{
			alert("No Record for Gender found ");
		}
			
		<% String hospitalList=user.hospitalNameOptions();%>
	  var Hospital="<%=hospitalList%>";
	  <% user.hospitalName();%>
		
	}
	
	function detail1(groupNo)
	  	{  
	  	if(groupNo.value!=0)
	  	 {
			ajaxFunction("umgmtUserMstResponce.jsp?hmode=SEAT&gid="+document.forms[0].groupNo.value +"&hospitalCode="+document.getElementsByName("hospitalCode")[0].value ,"1")
		  }else{
		     document.getElementById("seatAssociateId").innerHTML="<select name='userSeatNo' style='font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;'><option value='0'>Select Seat</option></select>";
		  }
		}
	
	
	function getAjaxResponse(res,mode)
	{
	    if(mode=="1")
		{
			document.getElementById("seatAssociateId").innerHTML="<select  name='userSeatNo' style='font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;' ><option value='0'>Select Seat</option>"+res+"</select>";
			if(res.length==6 )	{
			alert("No Record for seat found");
		}
		}
	}
	  	
	function openPopup1()
	{
	
		var url = "empPopUp.jsp?hospitalCode="+document.getElementsByName("hospitalCode")[0].value ;
		
		myPopup = window.open(url,'popupWindow','width=600,height=300,scrollbars=yes');
		if (!myPopup.opener)
			 myPopup.opener = self;
	}
	function openPopup2()
	{
	    var menuIds=document.form1.menuIdSelected.value;
		var url = "addPopUp.jsp?hospitalCode=100&menuids="+menuIds ;
		myPopup = window.open(url,'popupWindow','width=600,height=300,scrollbars=yes');
		if (!myPopup.opener)
			 myPopup.opener = self;
	}
	function matchPassword()
	{
		var userPwd	=	document.form1.userPwd.value;
		var conPwd	=	document.form1.conPwd.value;
		if(userPwd != conPwd)
		{
			alert("Check Your Confirm PASSWORD,Confirm Password Must Be Equal To Password");
			document.form1.conPwd.select();
			return false;
		}
		return true;
	}
	
	function assignMode1(e,form1,mode)
	{
		var userfullname	=	"";
		var desig			=	"";
		var dateCheck; 
		var dateCheck1;
		var dateCheck2;
		
		userfullname	=	removeLeadingTrailingSpace(document.form1.ufullName.value);
		
		document.form1.ufullName.value=userfullname;
		desig			=	removeLeadingTrailingSpace(document.form1.designation.value);
		document.form1.designation.value=desig;
		dateCheck		=	compareTwodates(document.forms[0].sysdate.value,document.forms[0].effDate.value);
		dateCheck1		=	compareTwodates(document.forms[0].effDate.value,document.forms[0].expDate.value);
		dateCheck2		=	compareTwodates(document.forms[0].sysdate.value,document.forms[0].expDate.value);
		
		if(e.type=="click"||e.keyCode==13)
			{
		
		if(document.form1.ufullName.value=="")
		{
			alert("User Name Cannot Be Blank ");
			document.form1.ufullName.focus();
			return false;
		}
	    if(document.getElementsByName('userName')[0].value=="")
		{
			alert("User Id Cannot Be Blank");
			document.getElementsByName('userName')[0].focus();
			return false;
		}
	    if(document.getElementsByName('genderBound')[0].value=="0")
		{
			alert("Please Select Any Gender!!");
			document.getElementsByName('genderBound')[0].focus();
			return false;
		}
		 if(document.form1.userPwd.value==""){
			alert("Password Cannot Be Blank ");
			document.form1.userPwd.focus();
			return false;
		}
		 if(document.form1.conPwd.value==""){
			alert("Confirm Password Must be Equal To The Password");
			document.form1.conPwd.focus();
			return false;
		}
		 if(document.form1.userPwd.value==document.form1.userName.value){
			alert("Enter A Different Password ,Which Should Not be Equal To UserId ");
			document.form1.userPwd.focus();
			return false;
		}
	    if(document.form1.userLevel.value=="0") {
			alert("User Level Not Selected ");
			document.form1.userLevel[0].focus();	
			return false;
		}
		 if(document.form1.groupNo.value=="0" ||document.form1.groupNo.value=="" )	{
			alert("Group Not Selected ");
			document.form1.groupNo[0].focus();	
			return false;
		}
		 /*if(document.form1.userSeatNo.value=="0" ||document.form1.userSeatNo.value=="")	{
			alert("Seat Associates Not Selected  ");
			document.form1.userSeatNo[0].focus();	
			return false;
		}*/
		 if(document.form1.effDate.value=="")	{
		
			alert(" Effective Date Cannot Be Blank");
			document.form1.effDate.focus();
			return false;
		}
		 if(document.form1.expDate.value==""){
			alert("Expiry Date Cannot Be Blank ");
			document.form1.expDate.focus();
			return false;
		}
		 if(dateCheck==false){
			alert("Effective Date cannot be less than the Current Date");
			return false;
		}
		 if(dateCheck1==false){
			alert("Expiry Date cannot be less than Effective Date");
			return false;
		}
		else
		if(dateCheck2==false){
			alert("Expiry Date cannot be less than the Current Date");
			return false;
		}
	if(!validatePasswordStrength())
		{
		var passwStrength=document.getElementsByName("passwStrength")[0].value
		
		if(passwStrength=="2")
			alert("PassWord Should Be a Combination of Alphabets and Numbers");
			else
		if(passwStrength=="3")
			alert("PassWord Should Be a Combination of Alphabets,Numbers and a Special Character");
					
		return false;
		}
		else
		{
			
					
				if(mode=="SAVE")
					{	
						var pwd=matchPassword(); 
						
						if(pwd ==true )
						{
							var saltedPass =  document.form1.userPwd.value + document.form1.userName.value;
							var hashObj = new jsSHA(saltedPass, "ASCII");
							try 
							{
								hashedPass = hashObj.getHash("SHA-1", "HEX");	
							}
							catch(e)
							{
								isSuccess = false;	
							}
			
							 document.form1.userPwd.value = hashedPass;
							 document.form1.conPwd.value =  hashedPass;
							 
								document.form1.hmode.value = mode;			
								document.form1.submit();
						}
					}
					else
					{
						document.form1.hmode.value = mode;			
						document.form1.submit();
					}
				}	
			}
			return;
		}
		
		
		function checkScanUser()
		{
				
		if(document.getElementById("scanUserFlag").checked)
		{
		
		   document.form1.scanUser.value = 2;	
		 }
		   else
		   {
		   
		
		    document.form1.scanUser.value = 1;	
		 
		   }
		
		}
		
		
	function validatePasswordStrength()
	{
 	var passwStrength=document.getElementsByName("passwStrength")[0].value
	
	var userPwd=document.getElementsByName("userPwd")[0].value;
	
	var	alphabetStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var numericStr="0123456789";
	var specialStr="~`!@#$%^&*()_-+={}[];:'<>,.?/\|\"";
	
	var alphabetCheck=false;
	var numericCheck=false;
	var specialCharCheck=false;

if(passwStrength=="2" || passwStrength=="3" )
{	
	
	for(var i=0; i< userPwd.length; i++)
		{
		
		///////Checking for whetehr the password contains alphabets////////
		if(alphabetStr.indexOf(userPwd.charAt(i))!=-1)
			{
			alphabetCheck=true;
			}
		
		///////Checking for whetehr the password contains Numbers////////
			
		if(numericStr.indexOf(userPwd.charAt(i))!=-1)
			{
			numericCheck=true;
			}
			
			
			///////Checking for whetehr the password contains Special Characters or not ////////
			
		if(specialStr.indexOf(userPwd.charAt(i))!=-1)
			{
			specialCharCheck=true;
			}
			
				
			
		
		}//for closed 
}//if closed 
	


	
	//alert('alphabetCheck----'+alphabetCheck)
	//alert('numericCheck-----'+numericCheck)
	
	//Cases for Password Strength 
	
	
	//Case1:No constraint   						  	      passwStrength-->1
	//Case2:Alphabets && Numbers							  passwStrength-->2
	//Case3:Alphabets , Numbers && Special characters 		  passwStrength-->3
	
	
	if(passwStrength=="1")
			return true;
			else	
	if(passwStrength=="2" && alphabetCheck==true && numericCheck==true)
			return true;
			else
	if(passwStrength=="3" && alphabetCheck==true && numericCheck==true && specialCharCheck==true)
			return true;
			else	
			return false;
	
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
	
	function validateFinalSubmit()
	{
	
	   var userfullname	=	"";
		var desig			=	"";
		var dateCheck; 
		var dateCheck1;
		var dateCheck2;
		
		userfullname	=	removeLeadingTrailingSpace(document.form1.ufullName.value);
		
		document.form1.ufullName.value=userfullname;
	
		desig			=	removeLeadingTrailingSpace(document.form1.designation.value);
		
		document.form1.designation.value=desig;
		dateCheck		=	compareTwodates (document.forms[0].sysdate.value,document.forms[0].effDate.value);
		dateCheck1		=	compareTwodates(document.forms[0].effDate.value,document.forms[0].expDate.value);
		dateCheck2		=	compareTwodates(document.forms[0].sysdate.value,document.forms[0].expDate.value);
	
		if(document.form1.ufullName.value=="")
		{
			alert("User Name Cannot Be Blank ");
			document.form1.ufullName.focus();
			return false;
		}
		if(document.form1.userLevel.value=="0") {
			alert("User Level Not Selected ");
			document.form1.userLevel[0].focus();	
			return false;
		}
		 if(document.form1.groupNo.value=="0" || document.form1.groupNo.value=="")	{
			alert("Group Not Selected  ");
			document.form1.groupNo[0].focus();	
			return false;
		}
		/* if(document.form1.userSeatNo.value=="0" || document.form1.userSeatNo.value=="")	{
			alert("Seat Associates Not Selected  ");
			document.form1.userSeatNo[0].focus();	
			return false;
		}*/
		 if(document.form1.expDate.value==""){
			alert("Expiry Date Cannot Be Blank");
			document.form1.expDate.focus();
			return false;
		}
		 if(dateCheck1==false){
			alert("Expiry Date cannot be less than the Effective Date");
			return false;
		}
		if(dateCheck2==false){
			alert("Expiry Date cannot be less than the Current Date");
			return false;
		}
	  return  true;  
	}
	
function submitPage()
{
	       document.form1.hmode.value="UPDATE";
		   document.form1.submit();
	}	   
		 	
function finalSubmit(e)
{
if(e.type=="click"||e.keyCode==13)
			{
		//	alert("validateFinalSubmit--"+!validateFinalSubmit())
	///		alert("validateMailId--"+!validateMailId())
				
	if (validateFinalSubmit() && validateEmail()) 
		submitPage();
		else
	 	return;
	
	        } 
}

function submitPageForMenu1()
{
	document.form1.hmode.value='MENU_UPDATE';
	document.form1.submit();
}	
function submitPageForMenu2()
{
	document.form1.hmode.value='MENU_ADD';
	document.form1.submit();
}


	function fun()
	{
		document.form1.hmode.value="ADD";
		document.form1.submit();
	}
	
	
	function empTypeUpdate()
	{
		document.form1.hmode.value="EDIT_TEMP";
		document.form1.submit();
	}
	function readNumber()
	{   
	    var pressKeyCode = window.event.keyCode;
	   	if(pressKeyCode > 47 && pressKeyCode < 58 )
			return true;
	    else
			return false;
	}
	function minCheck()
	{
		var o=document.getElementsByName("userPwd");
		var passLen=(o[o.length-1].value).length;
		if(passLen<6)
		{
			alert('Your password is less than 6 digit');
			document.forms[0].userPwd.value="";
			document.forms[0].userPwd.focus();
		}
	}
	


function validateEmail(){
	var valid=true;
	var email=document.getElementsByName("emailId")[0].value;
	//alert(email.length)
	if(email.length>0){
		var s=email.split("@");
		var index=email.indexOf("@");
		if(s.length!=2){
			valid=false;
		}
		else{
			//alert(email.lastIndexOf(".")- email.indexOf(".",index))
			//alert(email.length - email.lastIndexOf("."))
			if(s[1].split(".").length>2){
				if((email.length - email.lastIndexOf("."))==3 && ((email.lastIndexOf(".")- (email.indexOf(".",index))==3))){
					valid=true;
				}else if((email.length - email.lastIndexOf("."))==2 && ((email.lastIndexOf(".")- (email.indexOf(".",index))==2))){
					valid=true;
				}else{
					valid=false;
				}
				
			}
			else{
				if((email.length - email.lastIndexOf("."))==4 || (email.length - email.lastIndexOf("."))==3){
					valid=true
				}
				else{
					valid=false;
				}
			}	
			if((email.indexOf(".",index)- index)==1){
				valid=false;
			}
		}
	}
	if(!valid){
		alert("Enter valid email")
		document.getElementsByName("emailId")[0].focus();
	}	
	return valid;
}
	
function setHospitalCode(obj)
{
	
}
</script>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>
	<!-- 
	<body onload="onLoad();"  background="../../images/cdac1.psd.gif" width="100%" topmargin="0" >  -->
<body onload="onLoad();"  width="100%" topmargin="0" >
<form name="form1" method="post" action="umgmtUser_cntMst.jsp">
	<table width="97%" align="center"  border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td colspan=2><font color='black' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
				<strong>User Master >> <%=title%> Page</strong></font></td>
		</tr>
	</table>
	<table class="formbg" width="97%" align="center"  border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td class="ShadedSubTitleTagImage" colspan=2 align="right">Status <select name="isValid" tabindex="-1">
				<option value="1" <%=user.getIsValid().equals("1")?"selected":""%> >Active</option>
				<option value="2" <%=user.getIsValid().equals("2")?"selected":""%> >InActive</option></select>
			</td>
		</tr>
	</table>
	<table class="formbg" width="97%" align="center"  border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td class="label"><div align="right">Hospital Name</div></td>
			<td class="control"><div align="left">
			<select name="hospitalCode"  style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:250px;" disabled="true" onchange="setHospitalCode(this);">
				<option value="<%=user.getHospitalCode()%>"><%=user.getHospitalName()%></option>
					<%=hospitalList%></select>
				
					
					</div>
					
					
					</td></tr>
		<tr>
		<%
		System.out.println("empid-fdfffg-->"+user.getEmpNo());
		if(hmode.equals("UPDATE"))
	   	 {
	   	 	%>
		
		<%  
        }
        System.out.println("empid--->"+user.getEmpNo());	
        
        //incase of update of a non- employee	
		if(hmode.equals("UPDATE") && user.getEmpNo().equals("Non-Employee")) 
		{
		 	result_update=request.getParameter("emp");		
			System.out.println("result_update initally -->"+result_update);
			if(result_update==null)
			{
				result_update="2";
			}
			else
  			 if(result_update.equals(""))
			{
				result_update="2";
			}
	
			System.out.println("inside update non-emp to emp--->"+user.getEmpNo());
			String sel1 = "";
			String sel2 = "";
			if(result_update.equals("1"))
			{
				sel1 = "checked";
			}
			else if(result_update.equals("2"))
			{
				sel2 = "checked";
			}
		
		%>	
		
			<td class='label'><div align='right'>Employee
				<input type='radio' value='1' name='emp' <%=sel1%> onClick='empTypeUpdate();'></div>
			</td>
			<td class='label'><div align='left'>Non Employee
 				<input type='radio' value='2' name='emp'  <%=sel2%>  onClick='empTypeUpdate();'></div>
 			</td>
 		</tr>
		<%     
	    }
	    else
	    if(hmode.equals("SAVE"))
		{
		%>
	<!-- <tr>
			<td class="addHeaderNew" colspan=2 align="right">Status<select name="isValid" tabindex="-1">
				<option value="1" <%=user.getIsValid().equals("1")?"selected":""%> >Active</option>
				<option value="2" <%=user.getIsValid().equals("2")?"selected":""%> >InActive</option>
				</select>
			</td>
		</tr> -->	
		<tr>
		<%
			String sel1 = "";
			String sel2 = "";
			if(result.equals("1"))
			{
				sel1 = "checked";
			}
			else if(result.equals("2"))
			{
				sel2 = "checked";
			}
		%>
		
			<td class='label'><div align='right'>Employee
				<input type='radio' value='1' name='emp' <%=sel1%> onClick='fun();'>
	 			</div>
	 		</td>
	 		<td class='label'><div align='left'>Non Employee
 				<input type='radio' value='2' name='emp'  <%=sel2%>  onClick='fun();'></div>
 			</td>
 		</tr>
 		<%
 		}
		
		if(result.equals("1")&& hmode.equals("SAVE"))
		{
			System.out.println("inside if in save mode empno"+user.getEmpNo());
		%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>Employee No</div>	</td>
			<td class="control">
				<div align="left"><input type="text" name="empNo"value="<%=user.getEmpNo()%>" readonly>
					 <input type="button" name="popup" 	value="..." onClick="openPopup1();" ></div>
			</td>
		</tr>
		<%
		} 
		if(hmode.equals("UPDATE") && !user.getEmpNo().equals("Non-Employee"))
		{
		%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>Employee No</div>	</td>
			<td class="control">
				<div align="left"><input type="text" name="empNo" value="<%=user.getEmpNo()%>" readonly> 
					<input type="button" name="popup" value="..." onClick="openPopup1();" ></div>
			</td>
		</tr>
		<%
		}
		else
		if(hmode.equals("UPDATE") && user.getEmpNo().equals("Non-Employee") && result_update.equals("2"))
		{
		%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>Employee No</div>	</td>
			<td class="control">
				<div align="left"><input type="text" name="empNo" value="<%=user.getEmpNo()%>" readonly></div>
			</td>
		</tr>
		<%
		}
		else
		if(hmode.equals("UPDATE") && user.getEmpNo().equals("Non-Employee") && result_update.equals("1"))
		{
		%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>Employee No</div>	</td>
			<td class="control">
				<div align="left"><input type="text" name="empNo" value="<%=user.getEmpNo()%>" readonly>
			 	<input type="button" name="popup" 	value="..." onClick="openPopup1();" ></div>
			</td>
		</tr>
		<%
		}
		 if(hmode.equals("SAVE"))
		  {
		   if(result.equals("1") || user.getEmp().equals("1") )
		   {
			System.out.println("inside if in save mode ufullname"+user.getUfullName());
			System.out.println("inside if in save mode degignation"+user.getDesignation());
		 %>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>User/Employee Name</div></td>
			<td class="control"><div align="left">
				<input type="text" name="ufullName" size='20' maxlength="50" value="<%=user.getUfullName()%>" readonly ></div>
			</td>
		</tr>
		<tr>
			<td class="label"><div align="right">Designation</div></td>
			<td class="control"><div align="left">
				<input type="text" name="designation" value="<%=user.getDesignation()%>" readonly  maxlength="50"></div></td>
		</tr>	
		<%}
		System.out.println("result----"+result);
		System.out.println("emp----"+user.getEmp());
		  if(result.equals("2") || user.getEmp().equals("2"))
		   {
		   %>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>User/Employee Name</div></td>
			<td class="control"><div align="left">
				<input type="text" name="ufullName" size='20' maxlength="50" value="<%=user.getUfullName()%>" onkeypress = "return checkInput(this,7,50,event);" ></div>
			</td>
		</tr>
		<tr>
			<td class="label"><div align="right">Designation</div></td>
			<td class="control"><div align="left">
				<input type="text" name="designation" value="<%=user.getDesignation()%>" maxlength=50 onkeypress = "return checkInput(this,7,50,event);"></div></td>
		</tr>	
			<%}}  %>
			<%
			 if(hmode.equals("UPDATE"))
			  {
			  System.out.println("INSIDE UPDATE VALUE OF EMP----->"+user.getEmp());
			  System.out.println("INSIDE UPDATE VALUE OF EMP----->"+user.getEmp_new());
			    System.out.println("INSIDE UPDATE VALUE OF RESULT----->"+result);
			   if(user.getEmp().equals("1") || user.getEmp_new().equals("1") || !user.getEmpNo().equals("Non-Employee") )
			   {
				System.out.println("inside if in save mode ufullname"+user.getUfullName());
				System.out.println("inside if in save mode degignation"+user.getDesignation());
			 %>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>User/Employee Name</div></td>
			<td class="control"><div align="left">
				<input type="text" name="ufullName" size='20'  maxlength=50 value="<%=user.getUfullName()%>" readonly ></div>
			</td>
		</tr>
		<tr>
			<td class="label"><div align="right">Designation</div></td>
			<td class="control"><div align="left">
				<input type="text" name="designation" maxlength=50 value="<%=user.getDesignation()%>" readonly ></div></td>
		</tr>	
			<%}
			else
			  if(user.getEmp().equals("2") || user.getEmp_new().equals("2") || user.getEmpNo().equals("Non-Employee"))
			   {%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>User/Employee Name</div></td>
			<td class="control"><div align="left">
				<input type="text" name="ufullName" size='20' maxlength="50" value="<%=user.getUfullName()%>" onkeypress = "return checkInput(this,7,50,event);" ></div>
			</td>
		</tr>
		<tr>
			<td class="label"><div align="right">Designation</div></td>
			<td class="control"><div align="left">
				<input type="text" name="designation" value="<%=user.getDesignation()%>"  maxlength=50  onkeypress = "return checkInput(this,7,50,event);"></div></td>
		</tr>	
				<%  
		   	}}%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>Gender</div>
			</td>
			<td class="control"><div align="left">
				<select name="genderBound" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;" >
					<option value="0">Select Gender</option>
						<%=genderList%></select></div>
			</td>
		</tr>   	
		   	<%
	         if(hmode.equals("UPDATE"))
            	{
          	%>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>User Id</div>	</td>
			<td class="control">
				<div align="left"><input type="text" name="userName" value="<%=user.getUserName()%>"  onkeypress = "return checkInput(this,3,20,event);"  readonly></div>
			</td>
		</tr>
			<%
           	}
	        if(hmode.equals("SAVE"))
	         {
	          %>
        <tr>
        	<td class="label"><div align="right"><font color='red'>*</font>User Id</div>
        	</td>
        	<td class="control">
				<div align="left"><input type="text" name="userName"  value="<%=user.getUserName()%>"  onkeypress = "return checkInput(this,3,20,event);"></div>
			</td>
		</tr>   
		<tr>
	     	<td class="label">
	    		<div align="right"><font color='red'>*</font>Password</div>
	    	</td>
	     	<td class="control">
	     		<div align="left"><input type="password" name="userPwd"	maxlength="10"  value="<%=user.getUserPwd()%>"    onchange="minCheck();" > <font color='red'>(6 to 10 characters)<br>
		     		The Password is case sensitive.<br> <%=passwordStrengthMsg %></font></div>
		   </td>
      	</tr>
	    <tr>
	     	<td class="label">
	    		<div align="right"><font color='red'>*</font>Confirm Password</div>
	     	</td>
	    	<td class="control">
		    	<div align="left"><input type="password" name="conPwd" maxlength="10" value="<%=user.getConPwd()%>" ></div>
			</td>
		</tr>
	<% } %>
	<%
		String userType1="";
		String userType2="";
		String userType3="";
		String userType4="";
		String userType5="";
		String userType6="";
		
		String userType=user.getScanUser();
		
		if(userType.equals("1"))
			userType1="selected";
		if(userType.equals("2"))
			userType2="selected";
		if(userType.equals("3"))
			userType3="selected";
		if(userType.equals("4"))
			userType4="selected";
		if(userType.equals("5"))
			userType5="selected";
		if(userType.equals("6"))
			userType6="selected";
		
		%>
		<tr>
	   		<td class="label"><font color='red'>*</font>User Type</td>
			<td>	
				<select name="statusCode"  style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;">
					<option value="1" <%=userType1%>>Web Application User</option>
					<option value="2" <%=userType2%>>Scan User</option>
					<option value="3" <%=userType3%>>Machine Interface User</option>
					<option value="4" <%=userType4%>>Machine Module Admin</option>
					<option value="5" <%=userType5%>>Doctor</option>
					<option value="6" <%=userType6%>>Health Worker</option>
				</select>
			</td>
		</tr>
			<%
		String level1="";
		String level2="";
		String level3="";
		String level4="";
		String level5="";
		String level6="";
		
		String level=user.getUserLevel();
		
		if(level.equals("1"))
			level1="selected";
		if(level.equals("2"))
			level2="selected";
		if(level.equals("3"))
			level3="selected";
		if(level.equals("4"))
			level4="selected";
		if(level.equals("5"))
			level5="selected";
		if(level.equals("6"))
			level6="selected";
		
		%>
	
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>User Level</div>
			</td>
			<td class="control"><select name="userLevel" siz="30" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;">
				<option value="1" <%=level1%>>1 (Lowest Level)</option>
		 		<option value="2" <%=level2%>>2</option>
				<option value="3" <%=level3%>>3</option>
				<option value="4" <%=level4%>>4</option>
				<option value="5" <%=level5%>>5</option>
				<option value="6" <%=level6%>>6 (Highest Level)</option></select>
			</td>
		</tr>
		<tr>
			<td class="label"><div align="right"><font color='red'>*</font>Group</div>
			</td>
			<td class="control"><div align="left">
				<select name="groupNo" onChange="detail1(this);" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;" >
					<option value="0">Select Group</option>
						<%=groupList%></select></div>
			</td>
		</tr>
		
		<%
		String chkNo = "";
		String chkYes = "";
		
		String chkNoLdap = "";
		String chkNoPortal = "";
		String chkYesLdap = "";
		String chkYesPortal = "";
		
		String ldapUserVal = "0";
		String portalUserVal = "0";
           if(hmode.equals("SAVE"))
	         {
        	   chkNoLdap= chkNoPortal = "checked";
	      	 }else{
	      		if(portalUserVal.equals(user.getIsPortalUser())){
	      			chkNoPortal = "checked";
	      		}else{
	      			chkYesPortal = "checked";
	      		}
	      		if(ldapUserVal.equals(user.getIsLDAPUser())){
	      			chkNoLdap = "checked";
	      		}else{
	      			chkYesLdap="checked";
	      		}
	      	  }
	          %>
		<tr>
			<td class="label">
				Is Portal User
	 		</td>
	 		<td class="label">
	 			<div align="left">
				<input type="radio" value="0" name="isPortalUser" <%=chkNoPortal%> >NO
	 			<input type="radio" value="1" name="isPortalUser" <%=chkYesPortal%> >YES
 				</div>
 			</td>
 		</tr>
		<tr>
			<td class="label">
				Is LDAP User
	 		</td>
	 		<td class="label">
	 			<div align="left">
				<input type="radio" value="0" name="isLDAPUser" <%=chkNoLdap%> >NO
	 			<input type="radio" value="1" name="isLDAPUser" <%=chkYesLdap%> >YES
 				</div>
 			</td>
 		</tr>
		
		
	   	<tr>
			<td class="label">
				<div align="right">
			<!-- <font color='red'>*</font> -->Seat Associates</div>
			</td>
			<td class="control">
				<div align="left" id="seatAssociateId"><select name="userSeatNo" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:147px;" >
					<option value="0">Select Seat</option>
						<%=user.seatOptions()%></select></div>
			</td>
		</tr>
		<tr>
			<td class="label" width="50%"><div align="right"><font color='red'>*</font>Effective Date</div></td>
			<%
			if(hmode.equals("SAVE")) 
			{
			%>
			<td class="control" width="50%">
				<otTags:date name='effDate' dateFormate="%d-%b-%Y"  value='<%=user.getSysdate()%>' />
			</td>
			<%
			}else if(hmode.equals("UPDATE"))
			{
			%>
			<td class="control"><input typ="text" name="effDate" value='<%=user.getEffDate()%>' readonly></td>
			<% }%>
		</tr>
		<tr>
			<td class="label" width="50%"><div align="right"><font color='red'>*</font>Expiry Date</div>
			</td>
			<td class="control" width="50%"><otTags:date name='<%="expDate"%>' dateFormate="%d-%b-%Y"
				value="<%=user.getExpDate()%>" />
			</td>
		</tr>
	
		<tr>
			<td class="label" width="50%"><div align="right">Mobile No</div></td>
			<td class="control"><div align="left"><input type="text" maxlength=10 name="mobileNo"
			 	value='<%=user.getMobileNo()%>' onkeypress="return checkInput(this,1,15,event);"></div>
			</td>
		</tr>
	
		<tr>
			<td class="label" width="50%"><div align="right">Email Id</div>
			</td>
	 		<td class="control"><div align="left">
	 			<input type="text" size='20'name="emailId"  value='<%=user.getEmailId()%>' onkeypress="return getValidateStr(1);"   ></div>
	 			</td>
    	</tr>
	    <%-- <tr>
			<td class="label"><div align="right">Smart Card No</div></td>
			<td class="control"><div align="left">
				<input type="text" name="smartCardNo" size='20' value="<%=user.getSmartCardNo()%>" onkeypress = "return checkInput(this,7,50,event);"></div>
			</td>
		</tr> --%>
		<%
		String checkvalue=user.CheckValue();
		System.out.println("checkvalue------------->"+checkvalue);
		System.out.println("finally in get My service------------>"+user.getMyService());
		String isChecked=""; 
		String goButtonDisplay="style=display:none";
		String goButtonDisplayUpdate="style=display:none";
		String isCheckedUpdate=""; 
		ArrayList menulist=null;
	
		if(user.getMyService().equals("1")  )
		{
			isChecked="checked";
			goButtonDisplay="style=display";
		}
		System.out.println("ischeckd------------->"+isChecked);
		if(checkvalue!="" && user.getMyService().equals("2") )
		{
		//isCheckedUpdate="checked";
		//goButtonDisplayUpdate="style=display";
			System.out.println("isCheckedUpdate1------------->"+isCheckedUpdate);
		}
		else			
		if(checkvalue!="")
		{
			isCheckedUpdate="checked";
			goButtonDisplayUpdate="style=display";
			System.out.println("isCheckedUpdate2------------->"+isCheckedUpdate);
		}else		
		if(user.getMyService().equals("1"))
		{
			isCheckedUpdate="checked";
			goButtonDisplayUpdate="style=display";
			System.out.println("isCheckedUpdate3------------->"+isCheckedUpdate);
		}
		System.out.println("isCheckedUpdate final------------->"+isCheckedUpdate);
		%>	
		<%-- <tr>
			<td class='label' width='50%'><div align='right'>MyServices </td>
			<td width='50%' class='control' style="width: 554px">
			<%
			 if(hmode.equals("UPDATE"))
			{  user.setEmp(empcheck);
			 %>
				<div align="left"><input type='checkbox' name='myService' <%=isCheckedUpdate %> onclick= "submitPageForMenu1();">
				<a style="cursor:hand"><input id="myMenuPopup"  <%=goButtonDisplayUpdate %> type="button" name="myservicespopup" value="..." onclick="openPopup2();"></a>
				</div>
			<%
			}
			 if(hmode.equals("SAVE"))
			{user.setEmp(empcheck);
			%>
				<div align="left"><input type='checkbox' name='myService' <%=isChecked %> onclick= "submitPageForMenu2();">
	 			<a style="cursor:hand"><input id="myMenuPopup"  <%=goButtonDisplay %> type="button" name="myservicespopup" value="..." onclick="openPopup2();"></a> 
	 			</div>
	 		<%
      		}
      		%>
	 		</td>	
	 		</tr> --%>
	 		
	 		<%-- <tr>
			<td class='label' width='50%'><div align='right'>Scan User </td>
			<td width='50%' class='control' style="width: 554px">
			<div align="left"><input type='checkbox' name='scanUserFlag' id='scanUserFlag' <%=isCheckedScanUser%> onclick= "checkScanUser();">
				</div>
			
	 		</td>	
	 		</tr> --%>
		</table>
		<table width="97%" cellspacing="0" cellpadding="0" align="center">
			<tr class="FOOTER">
				<td colspan=2>
			</tr>
	
			<tr>
				<td colspan=2>
			<div  align="center"  class="control_button2">
		<%
		 if(hmode.equals("UPDATE"))
		{
		%>
		<a style=cursor:pointer; class="button" tabindex=0  onkeypress="finalSubmit(event);"
	     	onClick="finalSubmit(event);"><span class="save">Save</span></a> 
		<%
		}
		if(hmode.equals("SAVE"))
		{
		%>
	   			<a style=cursor:pointer;  class="button" tabindex=0
					onClick='return assignMode1(event,form1,"<%=hmode%>");' onkeyPress='return assignMode1(event,form1,"<%=hmode%>");'><span class="save">Save</span></a> 
				<a style=cursor:pointer;  class="button" tabindex=0 
					onClick='document.form1.reset();' onkeyPress='resetForm(event,form1,"RESET")'><span class="reset">Reset</span></a> 
		<%
		}
		%>
				<a style=cursor:pointer; class="button" tabindex=0
					onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="cancel">Cancel</span></a></div>
			</td>
		</tr>
	</table>
	
				<input type="hidden" name="hospitalCode" value="<%=user.getHospitalCode()%>">
				<input type ="hidden" name="userId" value="<%=user.getUserId()%>">	
				<input type ="hidden" name="oldUserSeatNo" value="<%=user.getUserSeatNo()%>">	
    			<input type="hidden" name="scanUser" value="<%=user.getScanUser()%>">
    			<input type ="hidden" name="userId" value="<%=user.getUserId()%>">	
     			<input type="hidden" name="menuIdCollection" value="<%=user.getMenuIdCollection()%>">
     			<input type="hidden" name="menuIdSelected" value="<%=checkvalue%>">
     			<input type="hidden" name="menuNameCollection" value="<%=user.getMenuNameCollection()%>"/>
     		
				<input type="hidden" name="hmode" >
				<input type="hidden" name="emp_new" value=<%=empcheck%> >
				<input type="hidden" name="sysdate" value="<%=global.getSysdate()%>"/>
				<input type="hidden" id="EmpIdNewDivId" name="EmpIdNew" value="<%=user.getEmpIdNew() %>"/>
				<input type="hidden" id="passwStrengthId" name="passwStrength" value="<%=lst.get(7)%>"/>
				<div id="menuides"></div>
				
	 <anticsrf:csrftoken/>
	
	</form>
	</body>
	</html>
