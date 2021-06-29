<jsp:useBean id="seat" class="usermgmt.masters.umgmtSeatMasterBean" scope="request" />
<jsp:useBean id="global" class="usermgmt.FuncLib" scope="request" />

<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>


<html>
<head>
<title>Seat Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript"></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
<script language="JavaScript" src="../js/calendar.js"></script>
<script language="JavaScript" src="../js/multirow.js"></script>
<script language="JavaScript" src="../js/util.js"></script>

<script>
function focusOnLoad()
	{
	document.forms[0].seatName.focus();	
	}

window.history.forward() ;

	/***
		a) This function is used to submit the page during the click of save button(During insertion mode).
		b) It perform some client side validations like mandatory field entered or not
	***/
	
	function submitPage(e,formObj,hmode)
	{
		
		var seatDes="";
		var isValidIpAddress; 
		seatDes=removeLeadingTrailingSpace(document.form1.seatDesc.value);
		formObj.seatDesc.value = seatDes;
		var check=true;
		var dateCheck=compareTwodates(document.forms[0].sysdate.value,formObj.effectDate.value);
		
		
//if(e.type=="click" || e.keyCode==13)
 // { 
 //isValidIpAddress=checkIpAddress();

	if(formObj.seatName.value=="")
		{
			alert("Seat Name is BLANK");
			formObj.seatName.focus();
			return false;
		}
		 
	if(formObj.seatDesc.value=="")
		{
			alert("Seat Description is BLANK");
			formObj.seatDesc.focus();
			return false;
		}
		 
	if(formObj.group.value=="0")
		{
			alert("Select Group");
			formObj.group.focus();
			return false;
		}	
		  
	if(document.form1.ipBound[1].checked)	
		{
		if(formObj.ipAddress.value=="")
		{
			alert("Enter Ip Address");
			formObj.ipAddress.focus();
			return false;
		}
		}
			 
	if(formObj.effectDate.value=="")
		{
			alert("Enter Effective Date");
			formObj.effectDate.focus();
			return false;
		}
		
	 if(!(checkIpAddress()))
	   {
	  		return  false;
	   }
	   
	   
	  			  
	 if(hmode=='SAVE' && dateCheck==false)
		{
			
			alert("Effective Date should be Greater than or equal to Current Date");
			formObj.effectDate.focus();
			return false;
	
	    }
	    else
	// if()
			{
				    document.form1.hmode.value = hmode;		
		 			document.form1.submit();
		 			
	
		    }
		// }
	}
	/***
		a) is used to check whether entered IP address valid or not?
		b) is used to check whether user enter same IP addresses in successive rows  
	***/
	
	function checkIpAddress()
	{
		var temp=document.getElementsByName("ipadd1");
		var ipaddChk=true;
		var size=temp.length-1;
		//alert(size);
		var i=0;
		var k=0;
		var ipArray=new Array(20);
		var checkDuplicateIP=true;
		var counter1=0;
		var counter2=0;
		var counter3=0;
		var check=false;
	/*
	Code used in case we allow the user to save data when he is not filling any ip address details for more than 
	1 ip adress ,but the issue is that the database is getting filled unnecessarily
	*/
	
		if(size > 1)
		{
		for(i=1;i<=size;i++)
			{
			var counter1=0;
				if(document.forms[0].ipadd1[i].value=="")
				{
					counter1++;
					counter2++;
					
				}
				if(document.forms[0].ipadd2[i].value=="")
				{
					counter1++;
					counter2++;
				}
				if(document.forms[0].ipadd3[i].value=="")
				{
					counter1++;
					counter2++;
				}
				if(document.forms[0].ipadd4[i].value=="")
				{
					counter1++;
					counter2++;
				}
				
				if(counter1==4 && check==false)
				 {
				 k=i;
				 counter3++;
				 check=true;
				 }
				 else
				if(counter1==4 && check==true)
				 {
				 counter3++;
				 
				 }
				
				 
			}
		}
	//alert(eval(counter1));	
//	alert(eval(counter2));	
//	alert(eval(counter3));
	
	//alert(eval(counter1)*eval(counter3));
	
		if((eval(counter2)==eval(counter1)*eval(counter3) &&  eval(counter2)!=0) || eval(counter2)==4 )
		{
		alert("Please enter  IP Address");
		document.forms[0].ipadd1[k].focus();
		ipaddChk=false;
		return ipaddChk;
		}		
		else	
			
		if(size>1)
		{
			for(i=1;i<=size;i++)
			{
				if(document.forms[0].ipadd1[i].value=="")
				{
					alert("Please enter valid IP Address");
					document.forms[0].ipadd1[i].focus();
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd2[i].value=="")
				{
					alert("Please enter valid IP Address");
					document.forms[0].ipadd2[i].focus();
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd3[i].value=="")
				{
					alert("Please enter valid IP Address");
					document.forms[0].ipadd3[i].focus();
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd4[i].value=="")
				{
					alert("Please enter valid IP Address");
					document.forms[0].ipadd4[i].focus();
					ipaddChk=false;
					return ipaddChk;
				}
				
			}
			for(i=1;i<=size;i++)
			{
				ipArray[i]=document.forms[0].ipadd1[i].value+"."+document.forms[0].ipadd2[i].value+"."+document.forms[0].ipadd3[i].value+"."+document.forms[0].ipadd4[i].value;
				//alert("adding---->"+ipArray[i]);
			}
			for(i=1;i<=size;i++)
			{
		//	alert("loop 1---->"+ipArray[i]);
				for(var j=i+1;j<=size;j++)
				{
				
		//		alert("loop 2--->"+ipArray[j]);
					if(ipArray[i]==ipArray[j])
					{
						alert("You have entered duplicate ip address");
						ipaddChk=false;
						checkDuplicateIP=false;
						break;
					}
					else
					{
						continue;
					}
					
					
				}
				
				if(checkDuplicateIP==false)
					   break;
			}
		}
		else
		if(size==1)
		{
			
			if(document.forms[0].ipadd1[1].value!="")
			{
				if(document.forms[0].ipadd2[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd3[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd4[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
			}
			if(document.forms[0].ipadd2[1].value!="")
			{
				
				if(document.forms[0].ipadd1[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd3[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd4[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
			}
			if(document.forms[0].ipadd3[1].value!="")
			{
				
				if(document.forms[0].ipadd1[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd2[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd4[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
			}
			if(document.forms[0].ipadd4[1].value!="")
			{
				if(document.forms[0].ipadd1[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd2[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
				if(document.forms[0].ipadd3[1].value=="")
				{
					alert("Please Enter valid IP address");
					ipaddChk=false;
					return ipaddChk;
				}
			}
			return ipaddChk;
		}
		
			return ipaddChk;
	}
	/***
		a)perform some client side validation like mandatory field entered properly or not
		b)submit the page during click of save button(Modification mode).
	**/
	function submitPageUpdate(e,formObj,hmode)
	{
		var seatDes="";		
		
		seatDes=removeLeadingTrailingSpace(document.form1.seatDesc.value);
		formObj.seatDesc.value = seatDes;
	   var check=true;	
	   
		
//if(e.type=="click" || e.keyCode==13)
 // {

	
	if(formObj.seatName.value=="")
		   {
			alert("Seat Name is Blank");
			formObj.seatName.focus();
			return false;
		   }	
		
	 if(formObj.seatDesc.value=="")
	       {
			alert("Seat Description is Blank");
			formObj.seatDesc.focus();
			return false;
		   }
		 if(document.form1.ipBound[1].checked)	
		{
		if(formObj.ipAddress.value=="")
		{
			alert("Enter Ip Address");
			formObj.ipAddress.focus();
			return false;
		}
		}
		
	 if(!checkIpAddress())
	   {
	  		return  false;
	   }
	
	   	else 
	
		{
			if(document.form1.ipBound[1].checked!=true)	
			{
			formObj.ipAddress.value = "";
			}
				document.form1.hmode.value=hmode;
				document.form1.submit();
			
		}
	// }
}	
/**
this function is used to reset values of all Html objects
**/
function resetForm(event,formObj,hmode)
{
	if(event.type=="click")
	{
		document.form1.hmode.value = hmode;
		document.form1.submit();
		
	}
}
function numericOnly(e)
{
	var key	=	e.KeyCode;
	alert(key);
	
	if(key>=48	&&	key<=57)
		return true;
	else
		return false;
}
/**
	This function is invoked during load event of form to check the mode whether it is update mode or save mode
**/
function checkModify()
{

	focusOnLoad();
	
	if(document.forms[0].hmode.value=="UPDATE")
	{
			//var objEle=document.getElementById("rowAdded1");
			//objEle.innerHTML=document.forms[0].multiModi.value;
			//objEle.style.display="block";
			if(document.forms[0].multiModiSize.value==0)
			{
				
				document.forms[0].rowIndex1.value=0;
				document.forms[0].rowLength1.value=0;
				addRows(new Array('ipadd1','ipadd2','ipadd3','ipadd4'),
			       new Array('t','t','t','t'),'1','1','R');
			}
			else
			{
				document.forms[0].rowIndex1.value=document.forms[0].multiModiSize.value;
				document.forms[0].rowLength1.value=document.forms[0].multiModiSize.value;
				document.getElementById("id1").innerHTML=document.forms[0].multiModi.value;
				addRows(new Array('ipadd1','ipadd2','ipadd3','ipadd4'),
			       new Array('t','t','t','t'),'0','1','R');
			 }
	}
	if(document.forms[0].hmode.value=="SAVE")
	{
		addRows(new Array('ipadd1','ipadd2','ipadd3','ipadd4'),
			       new Array('t','t','t','t'),'1','1','R');
	
	}
}
/**
	To check valid range of IP address 
**/
function checkIpSize(Obj)
{
	if(Obj.value<0 || Obj.value>255 )
	{
		alert("Plz Enter the value between 0 and 255");
		Obj.value="";
		return false;
	}
	
}
function checkIP(Obj)
{
	if(Obj.value<0 || Obj.value>255 )
	{
		alert("Plz Enter the value between 0 and 255");
		Obj.value="";
		return false;
	}
	
}
function fun()
{
	/*if(document.forms[0].hmode.value=="UPDATE")
	{
		//document.getElementById("rowAdded1").innerHTML
	}*/
}
function validateIPAddress(e,index)
{
			var key,keychar,str;
		
			if (window.event)
				key = window.event.keyCode;
			else
			{
				if (e)
					key = e.which;
				else
				   return true;
			}
		
			keychar = String.fromCharCode(key);
		
			// control keys
			if ((key==null) || (key==0) || (key==8) ||
				(key==9) || (key==13) || (key==27)|| (key==46))
				return true;
			else
			{
				str = getValidateStr(index)
				if (((str).indexOf(keychar) > -1))
				   return true;
				else
				   return false;
			}
		
		
}

function showIpDiv()
{
	var div = document.getElementById("ipDiv");
	if(document.form1.ipBound[1].checked==true)	
	{	
		div.style.display = "block";
	}
	else
	div.style.display = "none";
}

</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>

<!-- <body background="../../images/cdac1.psd.gif" topmargin="0" onload="checkModify();"> -->
<body  onload="checkModify();">
<form class="topmargin" name="form1" method="post" action="umgmtSeatMst_cnt.jsp">

<%
String hmode = "";
String title = "";
String userSeatId	=	"";

hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";

if(hmode.equals("SAVE"))
{
	title="Add";
	System.out.println("I m inside add");	
}
if(hmode.equals("UPDATE"))
{
	title = "Modify";
	System.out.println("I m inside modify");
}
%>
<table class="formbg" width="97%" align="center" cellspacing="0" cellpadding="0">
	<tr class="ShadedSubTitleTagImage">
		<td colspan=2>
<strong>Seat Master >> <%=title%> Page</strong></td>

	</tr>

	<!--  
	<tr>
		
		<td class="addHeaderNew" colspan=2  align="right" >Status<select name="isvalid" tabindex="-1">
			<option value='1' <%=seat.getIsvalid().equals("1")?"selected":""%>>Active</option>
			<option value='2' <%=seat.getIsvalid().equals("2")?"selected":""%>>Inactive</option>
		</select></td>

	</tr>
	
	<tr>
			<td class="addHeaderNew" colspan=2  align="right" ></td>
			</tr>-->
</table>

<table class="formbg" width="97%" align="center">

	<tr>

		<td class="LABEL" width="50%">
		<div align="right"><font color='red'>*</font>Seat Name</div>
		</td>
		<td class="CONTROL" width="50%">
		<div align="left">
		<input type="text" tabindex="1" name="seatName" value="<%=seat.getSeatName()%>" onkeypress = "return checkInput(this,3,20,event);">
		<!-- replace checkAlphaNumeric(this,3,20,event); -->
		</div>
		</td>

	</tr>

	<tr>

		<td class="LABEL" width="50%">
		<div align="right"><font color='red'>*</font>Seat Description</div>
		</td>
		<td class="CONTROL" width="50%">
		<div align="left"><input type="text" tabindex="2" name="seatDesc" value="<%=seat.getSeatDesc()%>" maxlength=20 onkeypress ="return checkInput(this,5,100,event);"></div>
		</td>

	</tr>
	<tr>

		<td class="LABEL" width="50%">
		<div align="right">Department</div>
		</td>
		<td class="CONTROL" width="50%">
		<div align="left"><select name="dept" tabindex="3" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width: 147px;"><%=seat.getDeptCombo()%></select></div>
		</td>

	</tr>
	<tr>

		<td class="LABEL"><div align="right"><font color='red'>*</font>Group Assigned</div></td>
		<% 
		if(hmode.equals("SAVE"))
		{%>
		<td class="CONTROL"><div align="left"><select name="group" tabindex="4" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width: 147px;"><%=seat.getGroupCombo()%></select></div></td>
		<%}
		else if(hmode.equals("UPDATE"))
		{%>
		<td class="CONTROL"><input type="text" name="groupName" value="<%=seat.getGroupName()%>" readonly>
		<% 	
		}%>
		
	</tr>
	
	<tr>
		<td class="LABEL"  colspan="2">
			<div align="center">
			<% 
			if(hmode.equals("SAVE"))
			{%>
				Normal Seat<input type="radio" name="ipBound" value="0" checked="checked" onclick="showIpDiv();">&nbsp;
				IP Bound Seat<input type="radio" name="ipBound" value="1" onclick="showIpDiv();" >
			<%	} 
			
			else if(hmode.equals("UPDATE"))
			{
				String ipAddress=seat.getIpAddress();
				if(!ipAddress.equals(""))
				{ %>
				Normal Seat<input type="radio" name="ipBound" value="0"  onclick="showIpDiv();">&nbsp;
				IP Bound Seat<input type="radio" name="ipBound" value="1"  checked="checked" onclick="showIpDiv();" >
			<%	} 
			else
			{ %>
			Normal Seat<input type="radio" name="ipBound" value="0" checked="checked" onclick="showIpDiv();">&nbsp;
				IP Bound Seat<input type="radio" name="ipBound" value="1" onclick="showIpDiv();" >
			
			<%}} %>
			</div>
		</td>
		
	</tr>
	</table>
	<% 
		if(hmode.equals("SAVE"))
		{%>
	<div id="ipDiv" style="display:none">
	<table class="formbg" width="97%" cellspacing="1px" align="center">
	<tr>
		<td class="LABEL" width="50%">
		<div align="right"><font color='red'>*</font>IP Address</div>
		</td>
		<td class="CONTROL" width="50%">
		<div align="left"><input type="text" tabindex="2" name="ipAddress" value="<%=seat.getIpAddress()%>" maxlength=15  onkeypress="return validateIPAddress(event,5);" ></div>
		</td>
		
	</tr>
	</table></div>
	<%} 
	else if(hmode.equals("UPDATE"))
		{
		String ipAddress=seat.getIpAddress();
		if(ipAddress!=null)
		{
		%><div id="ipDiv" style="display:show">
		<table class="formbg" width="97%" cellspacing="1px" align="center">
			<tr>
				<td class="LABEL" width="50%">
					<div align="right"><font color='red'>*</font>IP Address</div>
				</td>
				<td class="CONTROL" width="50%">
					<div align="left"><input type="text" tabindex="2" name="ipAddress" value="<%=seat.getIpAddress()%>" maxlength=15  onkeypress="return validateIPAddress(event,5);" ></div>
				</td>
			</tr>
		</table></div>
		<%} 
		else
		{seat.setIpAddress(""); %>
		<div id="ipDiv" style="display:none">
			<table class="formbg" width="97%" cellspacing="1px" align="center">
				<tr>
					<td class="LABEL" width="50%">
						<div align="right"><font color='red'>*</font>IP Address</div>
					</td>
					<td class="CONTROL" width="50%">
						<div align="left"><input type="text" tabindex="2" name="ipAddress" value="<%=seat.getIpAddress()%>" maxlength=15 onkeypress="return validateIPAddress(event,5);" ></div>
					</td>
				</tr>
			</table></div>
		<%} 
		}%>
		
		
		
	<table class="formbg" width="97%" cellspacing="1px" align="center">
	<tr>

		<td class="LABEL" width="90%">
		<div align="center">IP Address</div>
		</td>
		<td class="CONTROL" width="10%">
		<div align="left">
			<img
			src="../../images/plus.gif""
			onClick="addRows(new Array('ipadd1','ipadd2','ipadd3','ipadd4'),
			       new Array('t','t','t','t'),'1','1','R');">
		</div>
		</td>
		

	</tr>
	</table>
	<div id="id1">
	</div>
	<table class="formbg" cellspacing="1px" align="center" width="97%">
		<tr>

		<td class="LABEL" width="50%">
		<div align="right"><font color='red'>*</font>Effective Date</div>
		</td>
		<%if(hmode.equals("SAVE")) 
			{%>
				<td class="CONTROL" width="50%"><otTags:date
				name='<%="effectDate"%>' dateFormate="%d-%b-%Y"
				value='<%=seat.getSysdate()%>' /></td>
			<%}
		
		else if(hmode.equals("UPDATE"))
		{%>
		<td  class="CONTROL" width="50%"><input type="text" name="effectDate" readonly="readonly" value='<%=seat.getEffectDate()%>' ></td>
		<% }%>
		</tr>
		</table>
		
<!-- 
	<tr>
		<td class="adddatalabel" width="50%">
		<div align="right">HL-7 Code</div>
		</td>
		<td class="adddatalabel" width="50%">
		<input type="text"	name="hl7Code"  value="<%=seat.getHl7Code()%>" onkeypress = "return checkInput(this,1,10,event);">
		</td>
	</tr>


	<tr>

		<td class="addHeaderNew"  colspan=2></td>

	</tr>-->
<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2>
		<div align="center"  class="control_button2">
		<%if (hmode.equals("SAVE"))
		{%>
		<a style=cursor:pointer;  class="button" tabindex=0 onClick='submitPage(event,form1,"SAVE")'	onkeyPress='submitPage(event,form1,"SAVE")'><span class="save"> Save</span></a> 
		<a style=cursor:pointer; class="button" tabindex=0 onClick='document.form1.reset();'><span class="clear"> Clear</span></a> 
		<%}
		else if(hmode.equals("UPDATE"))
		{%>
		<a style=cursor:pointer; class="button"  tabindex=0 onClick='submitPageUpdate(event,form1,"UPDATE")' onkeyPress='submitPageUpdate(event,form1,"UPDATE")'><span class="save"> Save</span></a> 
		<% }%>
		
		<a style=cursor:pointer; class="button"  tabindex=0 onClick='resetForm(event,form1,"DEFAULT")'	onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="cancel"> Cancel</span></a>
		</div>
		</td>

	</tr>
</table>
 <input type="hidden" name="hmode" value="<%=hmode%>">
 <input type="hidden" name="prevGroup" value="<%=seat.getGroup()%>">
 <input type="hidden" name="userSeatId" 	value="<%=seat.getUserSeatId()%>">
 <input type="hidden" name="seatId"value="<%=seat.getSeatId()%>">
 <input type="hidden" name="multiModi" value="<%=seat.getStrHtlmlModi()%>">
 <input type="hidden" name="multiModiSize" value="<%=seat.getSizeMultiRow()%>">
 <input type="hidden" name="sysdate" value="<%=global.getSysdate()%>" />
  <anticsrf:csrftoken/>
 <jsp:include page="umgmtSeatMst_multirow.jsp"></jsp:include>	
 

 </FORM>
 
</BODY>
</HTML>
