<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head>
<meta charset=utf-8>
<title>Client Approval Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">
  
</script>

</head>
<html:form action="transactions/ClientVerificTransCNT.cnt" method="post">


 <table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="4">Final&gt;&gt;Bill</td>
  </tr>
  <tr> 
    <td width="25%" class="LABEL">Name:</td>
    <td width="25%" class="CONTROL"><input name="strName"></td>   
    <td width="25%" class="LABEL">Address:</td>
    <td width="25%" class="LABEL"><textarea name="strAddress" rows="2"></textarea></td>   
   </tr>
  <tr> 
    <td width="25%" class="LABEL">Phone No:</td>
    <td width="25%" class="CONTROL"><input name="strPhoneNo"></td>   
    <td width="25%" class="LABEL">Book No:</td>
    <td width="25%" class="CONTROL"><input name="strBookNo"></td>   
   </tr>
   <tr>
    <td width="25%" class="LABEL">Invoice:</td>
    <td width="25%" class="CONTROL"><input name="strInvoice"></td>
    <td width="25%" class="LABEL"><font color="red">*</font>Transaction:</td>
    <td width="25%" class="CONTROL"><input name="strTransaction"></td>   
  </tr>
  <tr>
    <td width="25%" class="LABEL">Date:</td>
    <td width="25%" class="CONTROL"><date:date name="effectiveFrom" value =""> </date:date></td> 
    <td width="25%" class="LABEL">DL No:</td>
    <td width="25%" class="CONTROL"><font color="red"><input name="strDlNo"></font> </td>   
  </tr>
  <tr>
    <td width="25%" class="LABEL">Tin No:</td>
    <td width="25%" class="CONTROL"><input name="strTinNo"></td>
    <td width="25%" class="LABEL"><font color="red">*</font>GrNo &amp; Date:</td>
    <td width="25%" class="CONTROL"> <input name="strGrNoDate"></td>   
  </tr>
  <tr>
    <td width="25%" class="LABEL">NOC:</td>
    <td width="25%" class="CONTROL"><input name="strNOCNo"></td>
    <td width="25%" class="LABEL"></td>
    <td width="25%" class="CONTROL"></td>   
  </tr>
  
  </table>
  <table class="TABLEWIDTH" align="center">
  <tr> 
    <td colspan="11" class="TITLE">Services</td>
   </tr>
		<tr>
			
					<td width="10%" class="LABEL">Cat/Prodc:</td>
					<td width="10%" class="LABEL">Package:</td>
					
					<td width="10%" class="LABEL">Batch:</td>
					<td width="10%" class="LABEL">Exp:</td>
					
					<td width="10%" class="LABEL">Qty</td>
					<td width="10%" class="LABEL">QtyFinal</td>
					
					<td width="10%" class="LABEL">PTW</td>
					<td width="10%" class="LABEL">PIR</td>
					
					<td width="10%" class="LABEL">MRP</td>
					<td width="10%" class="LABEL">AMT</td>
					
					<td width="10%" class="LABEL">
    	<center>
          <img src="../../hisglobal/images/plus.gif" 
               onClick="addRows(new Array('strCatProd','strPack','strBatch','strExp','strQty','strQtyFinal','strPTW','strPIR','strMRP','strAMT'),new Array('t','t','t','t','t','t','t','t','t','t'),'1','1','R');">
        </center>


					</td>
			</tr>
	     </table>
			<div id="id1"></div>
	
     <table class="TABLEWIDTH" border="0" align="center" >
     
    <tr>
    <td width="80%" class="LABEL">Total Bill:</td>
    <td width="20%" class="CONTROL"><input class="txtFldMin" name="strBill"></td> 
    </tr>
    <tr>
    <td width="80%" class="LABEL">Total Rs :</td>
    <td width="20%" class="CONTROL"><input class="txtFldMin" name="strTotalRs"></td>   
  </tr>
     
     </table>
  
   <table class="TABLEWIDTH" border="0" align="center" >
  
  <tr class="FOOTER"> 
    <td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="right"><img name="save"   src="../../hisglobal/images/btn-sv.png" onclick=" return validate1();"></td>
			<td align="left"><img name="cancel"   src="../../hisglobal/images/btn-ccl.png" onclick="cancel('LIST');"></td>
		</tr>
	</table>

	<input type="hidden" name="hmode">
</html:form>
<jsp:include page="multirow_ClientApprovalTrans_billtrans.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex> 
</html>