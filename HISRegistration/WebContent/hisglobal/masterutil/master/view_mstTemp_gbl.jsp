
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
String temp="";
if(request.getParameter("masterName")!=null)
temp=(String)request.getParameter("masterName");
else
	temp="Master";
String masterName=temp.split("#")[0];
%>
<title><%=masterName%></title> 
</head>    
<%
String cnt_page=request.getParameter("cnt_page");
String module_id=request.getParameter("module_id");
String chk=request.getParameter("chk");

  String mode=request.getParameter("mode");
  //System.out.println("cnt_page = " + cnt_page);
%>
<script>

</script><link href="../../css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="../../css/layout.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" type="text/javascript" src="../js/master.js"></script>
<%System.out.println(request.getSession().getAttribute("cnt_page"));%>
<body onLoad="fetchViewData('<%=cnt_page%>','<%=chk %>')">

<s:form >
<div align="center" style="color: red;"><%if(request.getParameter("strMsg") != null) request.getParameter("strMsg"); %></div>		



<div class="div-table wrapper rounded">
	<div class="div-table-row ">
		<div class="div-table-col  width100">
				<DIV ID="viewdata"></DIV>
				</div>
				</div>
</div>			
	    	
<input type="hidden" name="hmode" value='<%=mode%>' >
<input type='hidden' name='chk' 	value='<%=chk%>'> 
<input type="hidden" name="cntPage" value="<%=cnt_page%>" >
<input type='hidden' name='module_id'	value='<%=module_id%>'> 
</s:form>
</body>
</html>