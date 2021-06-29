<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
  <%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
  <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
  <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String file_path=request.getParameter("filePath");
if(file_path==null)
	file_path="def";

%>
<html>
<head>
<meta charset=utf-8>
<title>List Generation</title>
<link href="../../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script type="text/javascript">
function view_file()
{
	
	var name=document.getElementById('file_path').value;
	
	//alert(name);
	if(name!="def" )
	{
	 	var url = (document.URL);
		var pos = url.indexOf("/registration");
		var temp = url.substring(0,pos);
		//alert("temp:"+temp);
		var path=temp+name;
		//alert(path);
		window.open(path);
	
	}	
	
}

function Submit(mode)
{	
 //alert("mode="+mode);
 document.getElementById('hmode').value=mode;
 document.form1.submit();

		
}


function validate1()
{ 

//alert("checked :"+document.forms[0].strchkApproved.checked);

if( document.forms[0].strchkApproved.checked==true){

//alert("first value==");
 document.getElementById("divApprovedWait").style.display="block";
 document.forms[0].hmode.value = "LISTAPPROVEWAIT";
}
if( document.forms[0].strchkNotApproved.checked==true){

//alert("second value==");
 document.getElementById("divNotApproved").style.display="block";
 document.forms[0].hmode.value = "LISTNOTAPPROVED";
}
if( document.forms[0].strchkCancel.checked==true){

//alert("third value==");
 document.getElementById("divCancel").style.display="block";
 document.forms[0].hmode.value = "LISTCANCELMODE";
}


//alert("mode=="+document.forms[0].hmode.value);
 document.forms[0].submit();
}

function print(){
//alert("inside print");
 document.getElementById("divListHeading").style.display="none";
 document.getElementById("divDateBlock").style.display="none";
 document.getElementById("divfooter").style.display="none";

}
function cancelPrint(){
//alert("inside cancelPrint");
 document.getElementById("divListHeading").style.display="block";
 document.getElementById("divDateBlock").style.display="block";
 document.getElementById("divfooter").style.display="block";

}



</script>
</head>

<body>
<html:form	action="/transactions/MsApprovalTransCNT" method="post">
	<div class="normalMsg" id="normalMsg"></div>
<tag:tab tabLabel="List Generation" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab>
<table border="0" class="TABLEWIDTH" align="center">
 <tr><td colspan ="4" align='right'>
 <img src='../../hisglobal/images/btn-pnt.png' onClick = 'print();' />
 <img src='../../hisglobal/images/btn-ccl.png' onclick='cancelPrint();'/>
  </td>
  </tr>
  <tr>
  <td colspan ="4">
  <div id ="divListHeading">
  <table border="0" width="100%" align="center">
  <tr class="HEADER" >
   <td colspan="2">List Generation </td>
  <td colspan="2">Date&nbsp;&nbsp;<bean:write name="msApprovalTransBean" property ="strCtDate"/></td>
  </tr>
 <tr>
  <td width="15%" class="CONTROL">Approved and Wait List<input type="checkbox"  name ="strchkApproved" value ="0"></td>
  <td width="15%" class="CONTROL" nowrap>Not Approved <input type="checkbox"  name = "strchkNotApproved" value ="1" ></td>
  <td width="15%" class="CONTROL">Cancel <input type="checkbox"  name = "strchkCancel" value  ="2"></td>
  </tr>

   
  </table>
  </div>
  </td>
  </tr>
  
<tr>
<td  colspan ='4'>
<div id ="divApprovedWait"  >
<bean:write  name ="msApprovalTransBean" property ="strAllocatedList" filter ="false"/>
</div>
</td>
</tr>
<tr>
<td colspan ="4">
<div id ="divNotApproved"  >
<bean:write  name ="msApprovalTransBean" property ="strNotApprovedData" filter ="false"/>
</div>
</td>
</tr>
<tr>
<td colspan ="4">
<div id ="divCancel"  >
<bean:write  name ="msApprovalTransBean" property ="strCancelData" filter ="false"/>
</div>
</td>
</tr>
<tr>
<td colspan ='6'>
<div id ="divDateBlock">
 <table border="0" width="100%" align="center">
 <tr>
<td class="LABEL">Date</td>
<td colspan="3" class="CONTROL"><date:date
				name="strListDate"
				value="${msApprovalTransBean.strCtDate}"></date:date></td>
</tr>
<tr CLASS ="HEADER"><td colspan="4"  >&nbsp;</td></tr>
</table>
</div>
</td>
</tr>
       	
<tr>
<td colspan ="4">
<div id ="divfooter">
<table border="0" width="100%" align="center">
  <tr>
   <td align="center"><img src="../../hisglobal/images/Generate.gif" onClick='Submit("REPORT");'/>
   <img src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"/>
   </td>
   </tr>
   </table>
   </div>
   </td>
    </tr>
   
   </table>   
    
 <input type='hidden' id="file_path" name='file_path' value='<%=file_path%>'>
 <input type="hidden" name="hmode">
  

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
