<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<meta charset=UTF-8">
<title>Insert Title Here</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">

	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../js/IssueDetailsBackLog.js"></script> 

<style type="text/css">

.combo1Max {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-weight: normal;
	color: #000000;
	height: 20px;
	width: 330px;
}
</style>



<script type="text/javascript">
	function controlToMainPage()
	{	    
		document.forms[0].hmode.value="unspecified";
		document.forms[0].submit();
	}
</script>



</head>
<body>
<html:form action="/transactions/IssueDtlBackLogCNT.cnt"  name="issueBackLogDtl" type="mms.transactions.controller.fb.IssueDtlBackLogFB" method="post">

    <div class="errMsg"     id="errMsg"><bean:write name="issueBackLogDtl" property="strErr"/></div>
	<div class="warningMsg" id="warningMsg"><bean:write name="issueBackLogDtl" property="strWarning"/></div>
	<div class="normalMsg"  id="normalMsg"><bean:write name="issueBackLogDtl" property="strMsg"/></div>


<center>
<tag:tab tabLabel="OUTWARD BACKLOG (up to 31st March 2012)"  align="center" width="TABLEWIDTH">
	</tag:tab>
</center>
     <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4"></td>
		</tr>

		<tr>
			<td width="20%" class="LABEL"><font color="red">*</font>Store Name:</td>
			<td width="20%" class="CONTROL">
			
			     <select name="strStoreName" class="comboMax"   onChange="getIndentStoreCombo();">
                        <bean:write name="issueBackLogDtl" property="strStoreName" filter="false"/>
                 </select>
            </td>
			<td width="20%" class="LABEL"><font color="red">*</font>To Store:</td>

			<td width="20%" class="CONTROL">
			
			<div id="IndentingStore">
			     <select class='comboNormal' name='strIndentingStoreID' onchange='getVoucherCombo();'> 
		              <bean:write name="issueBackLogDtl" property="strIndentingStoreID" filter="false"/><option value="0">Select Value</option>  
		         </select>
		     </div>    
						
			  </td>
			 
		    </tr>
		    
		    <tr> 
            <td width="25%" class="LABEL"> <font color="red">*</font>Voucher No:</td>
            <td class="CONTROL" width="25%">
	             <div id="IssueNumberComboId">  
		               <select name="strVoucherNo" class="comboMax" >
		               	   <option value="0">Select Value</option>
		               </select>
	            </div>   
            </td>
            <td class="CONTROL" width="25%" id='labelId'></td>
            <td class="CONTROL" width="25%"></td>
          </tr>	   
		   
		    
        </table>
		    
	    <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
		<tr>
			
		    <td class="multiLabel" colspan="4">	<img src="../../hisglobal/images/Go.png" 
									onClick="getVoucherDetail();" style="cursor: pointer; "/></td>
		</tr>
		
		</table>
		<div id="showVoucherDiv1" style="display:none;" >    
	    <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
	        <tr class="HEADER">
	     			<td colspan="4">Voucher Details</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%" colspan='1'><font size="2" color="red">*</font>Voucher Date:</td>
				<td class="CONTROL" width="25%" colspan='1'><dateTag:date name="strVoucherDate"  id="strVoucherDate"></dateTag:date> </td>
				<td class="LABEL" width="25%" colspan='1'><font size="2" color="red">*</font>Indent No:</td>
				<td class="CONTROL" width="25%" colspan='1'><input type='text' name="strIndentNumber" id="strIndentNumber" value=''  maxlength='14'></td>
			    
			</tr>
			
			<tr>
				<td class="LABEL" width="25%" colspan='1'><font size="2" color="red">*</font>Indent Date</td>
				<td class="CONTROL" width="25%"><dateTag:date name="strVoucherIndentDate" id="strVoucherIndentDate" value=''></dateTag:date> </td>
				<td class="CONTROL" colspan="2"></td>
				
			    
			</tr>
		
	   </table>
	   </div>
	     
	     <div id="voucherDrugDivId"></div>
	     <div id="id1"></div>
	    	
  <table  class="TABLEWIDTH" align="center" cellspacing="0px" cellpadding="0px"> 
	 <tr class="FOOTER">
	    <td colspan="1" width='3%' align='center'>
	          <div id="pluslegendDivId" align="center" style="display:block;">
					<img src="../../hisglobal/images/plus.gif" 
						 onClick="view1('pluslegendDivId','minuslegendDivId','legendDivId');" 
						 style="cursor: pointer; "/>
				</div>
				<div id="minuslegendDivId" style="display:none;" align="center">
					<img src="../../hisglobal/images/minus.gif" 
						 onClick="view2('pluslegendDivId','minuslegendDivId','legendDivId');" 
						 style="cursor: pointer; "/>
				</div>
		 </td>
		 <td colspan="1" width='5%' align='left'>Legend</td>			 
		 <td colspan="1" width='92%'><font size="2" color="red">*</font>Mandatory Fields</td>
	  </tr>
  </table>	
  <div id="legendDivId" style="display:none">
  <table  class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" bgcolor='black'> 
	 <tr>
		<td align="center" style='background-color: pink;'>
	        <font size="3" color="red">*</font>
		</td>
		<td align="left" style='background-color:pink;color: black'>
	       <b>New Voucher</b>
		</td>
		<td align="center" style='background-color: pink;'>
		   <font size="3" color="red">**</font>
		</td>
		<td align="left" style='background-color:pink;color: black'>
	       <b>Verified Voucher</b>
		</td>
		
	 </tr>
  </table> 
  </div>
	    <table  class="TABLEWIDTH" align="center">
	  <tr>
		<td align="center">
			  <img style="cursor:pointer;cursor:pointer" title="Click to Save Record" src="../../hisglobal/images/Verify.gif"  onClick=" return validate1();" onkeypress="if(event.keyCode==13) validate1();"/>
              <img style="cursor:pointer;cursor:pointer" title="Click to Clear Page" src="../../hisglobal/images/btn-clr.png" name="clearImg" onclick="document.forms[0].reset(),clearMsg()" onkeypress="if(event.keyCode==13) document.forms[0].reset(),clearMsg();">
              <img style="cursor: pointer; " title="Click to Return Main Menu" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage()" onkeypress="if(event.keyCode==13) cancelPage()" />              
		</td>
	 </tr>
	</table>	
        
    <input type="hidden" name="hmode"/>
    <input type="hidden" name="strIsView" value="1"/>
    <input type="hidden" name="strCurrentDate" value="${issueBackLogDtl.strCurrentDate}"/>
    <input type="hidden" name="strModDate" value="${issueBackLogDtl.strModDate}"/>
    <input type="hidden" name="strIssueValue" value="0"/>

    <jsp:include page="issue_backLog_details.jsp"></jsp:include>
    
  </html:form>
 
</body>
</html>