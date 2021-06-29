<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>
<%@ taglib uri="/WEB-INF/onLineReqDiscountDtl.tld" prefix="DisDtl"%>
<html>
<head>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../../billing/js/IpdBillMangTrans.js"></script>
<script type="text/javascript">

</script>    
</head>
<body>
 <table class="TABLEWIDTH" align="center">
  <tr>  
    <td width="5%" class="TITLE" align="center"><input type='hidden' name='button1' value="1">
    <img src="../../hisglobal/images/plus.gif"   id="plus1" style="display:block;" onClick="ftn11()">
    <img src="../../hisglobal/images/minus.gif"  id="minus1" style="display:none;" onClick="ftn11()"></td>
    <td colspan="3" class="TITLE" align="left"><b>Patient Detail</b></td>   
  </tr>
 </table>
 <div id = "detailsdivid1" style="display:none;">
  <table class="TABLEWIDTH" align="center">
  <tr>
        <bean:write name="ipdBillManagementTransBean" property="strPatientDetailsView" filter="false"/>
  </tr>
 </table>
 </div>
 
 
 <table class="TABLEWIDTH" align="center">
  <tr> 
    <td width="5%" class="TITLE" align="center"><input type='hidden' name='button3' value="1">
    <img src="../../hisglobal/images/plus.gif"   id="plus3" style="display:block;" onClick="ftn33()">
    <img src="../../hisglobal/images/minus.gif"  id="minus3" style="display:none;" onClick="ftn33()"></td>
    <td colspan="3" class="TITLE"><b>Account Detail</b></td> 
   </tr>
 </table>
 <div id = "detailsdivid3" style="display:none;">
  <table class="TABLEWIDTH" align="center">
  <tr>
          <bean:write name="ipdBillManagementTransBean" property="strClientAcctDtl" filter="false"/>
  </tr>
 </table>
 </div>
 
 

  <table class="TABLEWIDTH" border="0" align="center" >
    <tr class="TITLE">  
    <td colspan="4">Add Client Detail</td>
    </tr>
  </table>	

 <table class="TABLEWIDTH" align="center">
  <tr>
        <bean:write name="ipdBillManagementTransBean" property="strClientApprovalDtl" filter="false"/>
  </tr>
 </table>
 <table class="TABLEWIDTH" border="0" align="center" >
    <tr class="TITLE">  
    <td colspan="4">Add Client Acct Dtl</td>
    </tr>
  </table>	
 <table class="TABLEWIDTH" align="center">
  <tr>
        <bean:write name="ipdBillManagementTransBean" property="strClientAcctDtl" filter="false"/>
  </tr>
 </table>
 <table class="TABLEWIDTH" border="0" align="center" >
    <tr class="TITLE"> 
    <td colspan="4"></td>
    </tr>
  </table>	
   <tag:autoIndex></tag:autoIndex>  
</body>
</html>