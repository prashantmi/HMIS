
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta charset=utf-8>
<title>Acceptance List Page</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../ipd/js/nursingdesk_trans.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HBIMS/ipd/js/bootstrap.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script type='text/javascript'>
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
//,document.forms[0].nNoOfRow.focus()  on body onload(deleted due to error in IE.)
</script>
</head>

<body onLoad= "reset(), addRows(new Array('strArticleName','strQuantity','strBelongingRemark'),new Array('t','t','t'),'1','1','I');">
<html:form action="/transactions/NursingDeskTransCNT" method="post">

<div class="normalMsg" id="normalMsg" ><bean:write name="nursingDeskBean" property="strNormalMsg"/></div>
	<div class="errMsg" id ="errMsg"><bean:write name="nursingDeskBean" property="strErrorMsg"/></div>
<tag:tab tabLabel="Patient Acceptance " selectedTab="FIRST" align="center"
	width="TABLEWIDTH">
</tag:tab>
	
		
<table class =  "TABLEWIDTH" align ='center' cellspacing ='1px'>
   
	<tr class="HEADER">
		<td colspan="6">&nbsp;</td>

	</tr>
</table>
	
	
	
	<table class =  "TABLEWIDTH" align ='center' cellspacing ='1px'>
	<tr>
	<td width = "25%" class="LABEL">Department</td>
	<td  width = "25%" class ="CONTROL" ><select name = 'strDepartment' onChange = "fununit('UNIT'); setWardRoom();">
	<bean:write   name   = "nursingDeskBean"  property = "strdeptproperty"  filter ="false"/>
	</select> </td> 
   <td width = "25%" class="LABEL">Unit</td>
   <td width = "25%" class ="CONTROL" colspan ="3" ><div id = "unitId"><select name = 'strUnit'>
  <option value="0">Select Value</option>
	</select>  </div></td> 
   
	</tr>
	
	<tr >
	<td width = "25%" class="LABEL">Ward</td>
	<td  width = "25%" class ="CONTROL"  ><div id = "wardId"><select name = 'strWard'>
 <option value="0">Select Value</option>
	</select></div> </td> 
	<td  width = "25%" class="LABEL">Room</td>
   <td width = "25%" class ="CONTROL"  colspan ="3"><div id = "roomId"><select name = 'strRoom' >
	 <option value="0">Select Value</option></select></div> </td> 

	</tr>
	</table>
		
	<div id = 'divBelonging' style = 'display:none' class = 'popup' align = 'center' >
	<table border="0" width="100"  cellspacing="1px"   >
	
	<tr class ="HEADER">
			<td>Belongings Details</td>
			<td align ='right'>
			<img src="../../hisglobal/images/FrStopAutoHide.png" onClick ='closebelonging();'   />
			
			</td>
		</tr>
	<tr>
	<td class="LABEL" colspan ="2">
			Enter no of row <input type="text" name="nNoOfRow" class='txtFldSmall' onKeyUp="return addArticleRows(this,event);">
	</td>
	</tr>
		<tr>

			<td colspan="6" valign="TOP">
			
			<table width="100%" cellspacing ='1px'>
				<tr>
					<td width="45%" class="multiLabel">Name</td>
					<td width="10%" class="multiLabel">Qty</td>
					<td width="50%" class="multiLabel">Remark</td>
					
					<td width="2%" class="multiLabel"><img
						src="../../hisglobal/images/plus.gif" 
						onClick="addRows(new Array('strArticleName','strQuantity','strBelongingRemark'),new Array('t','t','t'),'1','1','R');"></td>

				</tr>
			</table>
			
			<div id="id1"></div>
			

			</td>
		</tr>
		<tr><td colspan='4' align="center">
		<img src='../../hisglobal/images/btn-ok.png'  style='cursor:hand;cursor:pointer;'  title ='save data' onClick ='OkBelonging();' /> 
		 <img src='../../hisglobal/images/btn-ccl.png' style='cursor:hand;cursor:pointer;'  title ='clear data' onClick ="delRow();" />
		 </td></tr>
    	</table>
	</div>
	
  <div id = "admitdetail"  style ='display:block'><bean:write  name=  "nursingDeskBean"  property =  "strAdmitDetailProperty"  filter = 'false'/></div>

 <div id = 'divChecklist' align ='center'>
	<bean:write name  =  "nursingDeskBean"  property = "strChecklistProperty" filter ='false'  />
    </div>
		
<table border="0" class="TABLEWIDTH" align="center" cellspacing="0px" cellpadding="0px">
		<tr class="FOOTER">
		<td width='3%'><div id="plusHelpId" align="center"><img
						src="../../hisglobal/images/plus.gif" name="plusHelp"
						align="middle"
						onclick="showHelpDetails();" /></div>
					<div id="minusHelpId" style="display: none" align="center"><img
						src="../../hisglobal/images/minus.gif" name="minusHelp"
							onclick="hideHelpDetails();"> </div></td>
						<td><div align="left">Help</div></td>
		<td colspan="6">&nbsp;<font size="2" color="red">*</font> Mandatory Fields </td>

	</tr>
	</table>
	<div id="HelpId" style="display:none">
			<table border="0" class="TABLEWIDTH" align="center" bgcolor="#000000" cellspacing="1px" cellpadding="1px">
			<tr >
			<td class="CONTROL" style="background-color:#FFFFFF;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <button style="background-color:#CFE7E2;height: 20px;width: 20px;" disabled></button>&nbsp;Patient transfered from another ward 
			</td>
			</tr>
			<tr >
			<td class="CONTROL" style="background-color:#FFFFFF;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <button style="background-color:#7AB6C4;height: 20px;width: 20px;" disabled> </button>&nbsp;Outside Patient 
			</td>
			</tr>
			<tr >
			<td class="CONTROL" style="background-color:#FFFFFF;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <button style="background-color:#F1ECE2;height: 20px;width: 20px;" disabled> </button>&nbsp;New Admission 
			</td>
			</tr>
			</table>
			</div>
	<table border="0" class="TABLEWIDTH" align="center">
	<tr>
		<td align="center">
		<!-- <img src="../../hisglobal/images/btn-sv.png"  style='cursor:hand;cursor:pointer;'  title ='save record' onClick ="validate1('SAVE');"   />
		<img src="../../hisglobal/images/NotReprted.png "  style='cursor:hand;cursor:pointer;'  title ='Not Reporting ' onclick="validate1('NOTREPORT');" />
		<img src="../../hisglobal/images/btn-clr.png"  style='cursor:hand;cursor:pointer;'  title ='clear values' onclick="cancel('CLEAR');"/>
		 <img src="../../hisglobal/images/btn-ccl.png"  style='cursor:hand;cursor:pointer;'  title ='return to main page' onclick="cancel('cancel');"/>
		  -->
			 <a href="#" class="button" id="" onClick="validate1('SAVE');" ><span class="save">Save</span></a>
			 <a href="#" class="button" id="" onclick="validate1('NOTREPORT');" ><span class="save">Not Reported</span></a>
			 <a href="#" class="button"	onclick="cancel('CLEAR')"><span class="clear">Clear</span></a> 
			 <a href="#" class="button" onclick="cancel('cancel');"><span class="cancel">Cancel</span></a>
		</td>
	</tr>
	</table>




  		 <input type="hidden" name="hmode">
		<input type = 'hidden' name = 'hiddencrno' >
		<input type ='hidden' name = 'hiddenadmno'>
	  	<input type ='hidden' name = 'hiddenbed'>
	    <input type ='hidden' name = 'hiddennursecheck'>
	   	<input type = 'hidden' name = 'hiddenchkremark'>
	   	<input type = 'hidden'  name = 'hiddenflag'>
	    <input type ='hidden' name = 'hiddenbelonging'>
	    <input type = 'hidden'  name ='strhiddendepartment'>
	    <input type = 'hidden' name = 'strhiddendunit'>
	    <input type = 'hidden' name = 'strhiddenward'>
	    <input type ='hidden' name = 'strhiddenRoom'>
	    <input type ='hidden' name = 'stroldradio'>
	    <input type ='hidden' name = 'strhtransinflag'>
	     <input type ='hidden' name = 'strhmoveno' value="">
	     <input type ='hidden' name = 'delRowsNo' id="delRowsNo" value="0">
	     
	      

</html:form>
<jsp:include page="multirow_article_ipd.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
