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
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<html>
<head>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">

<script language="Javascript" src="../../../billing/js/IpdBillMangTrans.js"></script> 
<script type="text/javascript">

</script>    
</head>
<body>
  <bean:write name="ipdBillManagementTransBean" property="strPatientDetailsView" filter="false"/>
  <!--<aDtl:addDtl crNo="${ipdBillManagementTransBean.strCrNo}"></aDtl:addDtl>-->
 
 <table class="TABLEWIDTH" align="center" cellspacing ="1px" style="display:none;">
  <tr> 
    <td width="5%" class="TITLE" align="center"><input type='hidden' name='button3' value="0">
    <img src="../../hisglobal/images/plus.gif"  width="15" height="15" id="plus3" style="display:block;cursor:pointer;cursor:hand" onClick="ftn33()">
    <img src="../../hisglobal/images/minus.gif" id="minus3" style="display:none;cursor:pointer;cursor:hand" onClick="ftn33()"></td>
    <td colspan="3" class="TITLE"><b>Account Detail</b></td> 
   </tr>
 </table>
 <div id = "detailsdivid3" style="display:none;">
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr>
          <bean:write name="ipdBillManagementTransBean" property="strClientAcctDtl" filter="false"/>
  </tr>
 </table>
 </div>
</body>
</html>