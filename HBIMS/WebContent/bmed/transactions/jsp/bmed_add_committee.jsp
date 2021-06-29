<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Committee Desk</title>
<his:css src="../../../hisglobal/css/Color.css" />
<his:css src="../../../hisglobal/css/master.css" />
<his:css src="../../hisglobal/css/hisStyle.css" />
<his:css src="../../hisglobal/css/hisStyleExt.css" />
<his:css src="../../hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript"
	src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script 

language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script

> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/bmed_add_committee.js"></script>
<script language="Javascript" src="../../bmed/transactions/js/bmed_equip_condemnation_auction_desk.js"></script>
<his:javascript src="/hisglobal/js/multirow.js"/>

<!-- 
/**
 * BY shefali
 * Date of Creation : 07/11/2013
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Rajasthan
 */
 -->
 <script type="text/javascript">
 function validate()
{   

          var obj = document.getElementsByName("strComMemberName");
          alert(obj.length);
            var hisValidator = new HISValidator("stCommitteDesk");
         	hisValidator.addValidation("strCommitteeName","req","Please Committee Name");
        	hisValidator.addValidation("strCommitteeId","req","Please Enter Committee Id");
	        hisValidator.addValidation("strCommitteeEffectiveFrom","date","Please Enter committee effective from date");
	        hisValidator.addValidation("strComMemberName","req","Please Enter Member Name ");
	        hisValidator.addValidation("strComMemberEmail","req","Please Enter Email Address ");
	        hisValidator.addValidation("strComMemberMobNo","req","Please Enter Mobile Number");
         //   hisValidator.addValidation("strComMemberName","dontselect=0","Please Select Memeber Name");

           //var obj = document.getElementsByName("strComMemberName");
           //alert(obj.length);
           alert("type is>>."+document.getElementById("strComMemberName1-0").type);
           for(var i=0;i<(obj.length-1);i++)
           {
         //   if(document.getElementById("strComMemberName1-"+i).type);
            
           }  
            var retVal = hisValidator.validate(); 
          	 
          if(retVal)
          {
                 	   document.forms[0].hmode.value = "SaveMemberDetails";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}
function getValueSelected(index)
{

if (document.getElementById("strComMemberName"+index).value=="0")
 {
  alert("please select a Emp Name");
 }
}
    function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if(charCode < 48 || charCode > 57)
             return false;

          return true;
       }
       function validatePhoneNum(index)
       {
       // alert("hiii");
        var obj = document.getElementById("strComMemberMobNo"+index);
        if(obj.value.length !=10)
       { alert("in valid number");
          obj.value="";
           obj.focus();
            return false;
       }
       return true;
       }
       
  function validateEmailId(index)
{
var x=document.getElementById("strComMemberEmail"+index).value;
var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
    alert("Not a valid e-mail address");
  document.getElementById("strComMemberEmail"+index).value="";
  return false;
  }
  return true;
}
</script>
 
 
</head>
<body onload="chkRecordSaved()">
<html:form action="/transactions/EquipmentAuctionAndCondemnationDeskCNT"
	name="stCommitteDesk"
	type="bmed.transactions.controller.fb.EquipmentAuctionAndCondemnationDeskFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="EquipmentAuctionAndCondemnationDeskFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="EquipmentAuctionAndCondemnationDeskFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg" style="display: none;"><bean:write
		name="EquipmentAuctionAndCondemnationDeskFB" property="strMsgString" /></div>
	<tag:tab tabLabel="Committee Desk" selectedTab="FIRST"
		align="center"  width="TABLEWIDTH">
	</tag:tab></center>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Committee Detail &gt;&gt;</td></tr>
	</table>
		<table class="TABLEWIDTH"  align="center" width="100%">
		<tr>
			<td colspan="1" class="LABEL" style="text-align:right;"><font color='red'>*</font>Committee Name</td>
			<td colspan="1" class="CONTROL"><input type="text" name="strCommitteeName" id="strCommitteeName" />
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Committee Id</td>
			<td colspan="1" class="CONTROL"><input type="text" name="strCommitteeId" id="strCommitteeId" />
			</td>
		</tr>
		<tr>	
			<td colspan="" class="LABEL" style="text-align:right;">Committee Effective from</td>
			<td colspan="1"  class="CONTROL"><dateTag:date name="strCommitteeEffectiveFrom" id="strCommitteeEffectiveFrom"></dateTag:date> </td>
			<td colspan="1" class="LABEL" style="text-align:right;">Committee Remarks</td>
			<td colspan="1" class="CONTROL"><input type="text" name="strCommitteeRemarks" id="strCommitteeRemarks"/>
			</td>
		</tr>
    </table>
    	<his:ContentTag>
	   			   <table class="TABLEWIDTH" id="CommitteeMemberMultiRowId" align="center" width="100%">
	   			   		<tr class="HEADER">
		<td colspan="7">Committee Member Details &gt;&gt;</td></tr> 	
	   			   	
	   			   	<tr>
							<td width="10%" class="LABEL" style="vertical-align:middle">Internal/External</td>
							<td class="LABEL" style="vertical-align: middle">Member Name</td>
							<td class="LABEL" style="vertical-align: middle">Member UserId</td>
							<td class="LABEL">Member Designation</td>
							<td class="LABEL">Contact Numb</td>
							<td  class="LABEL" style="vertical-align: middle">Email ID</td>
							<td class="LABEL" style="vertical-align: middle"></td>
					</tr>
						<tr >
						<td class="LABEL">
					<div align="center">
						<select name="strMemberType" id="strMemberType1-0" onchange="getMemberDetails('1-0')"><option value="1">External</option>
                       <option value="2">Internal</option></select> 
				</div>
				</td>
				<td class="LABEL">
				<div id="strComMemberNameDiv1-0" align="center">
                <input type="text" name="strComMemberName" id="strComMemberName1-0"/>
          	    <bean:write name="EquipmentAuctionAndCondemnationDeskFB" property="strComMemberNameCmb" filter="false" />
                </div>
				</td>
				<td class="LABEL">
				<div id="strComMemberIdDiv" align="center">
				<input type="text" name="strComMemberId" id="strComMemberId1-0" value="0" readonly/>
				</div>
				</td>
				<td class="LABEL"><div align="center">
				<input type="text" name="strComMemberDesign" id="strComMemberDesign1-0" value= "0" readonly />
				</div></td>
				<td  class="LABEL"><div align="center">
				<input type="text" name="strComMemberMobNo" id="strComMemberMobNo1-0" onfocus="getValueSelected('1-0')" onkeypress="return isNumberKey(event)" onblur="validatePhoneNum('1-0')" />
				</div>
				</td>
				<td  class="LABEL">
				<div id="strComMemberEmailDiv" align="center">
				<input type="text" name="strComMemberEmail" id="strComMemberEmail1-0" onfocus="getValueSelected('1-0')" onblur="return validateEmailId('1-0');"/>
				</div>
				</td>
				<td class="LABEL">				
		    	<img src="../../hisglobal/images/plus.gif" style="cursor: pointer;" title="Add New Detail Row"
				 OnClick="addRows(new Array('strMemberType','strComMemberName','strComMemberId','strComMemberDesign','strComMemberMobNo','strComMemberEmail'),new Array('s','t','t','t','t','t'),'1','1','R');">						
			</td>					
		</tr>
		</table>
				   
          </his:ContentTag>
		<div id="id1" ></div>	
			
		
	 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" border="0" >
	<tr>
			<td align="center" colspan="2" width="25%" class="label"><br>
			<img src="../../hisglobal/images/btn-sv.png" title="Save Record"
			onClick="validate();" style="cursor: pointer;" /> <img
			src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer;"
			title="Cancel Process" onClick="cancel('LIST');" /></td>
		</tr>
	</table>
<html:hidden property="hmode" styleId="hmode"/>
<input type="hidden" name="strEquipModel" id="strEquipModel" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strEquipGroup" id="strEquipGroup" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strEquipSubGroup" id="strEquipSubGroup" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strBatchNo" id="strBatchNo" value="${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}">
<input type="hidden" name="strUidNo" id="strUidNo" value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
<input type="hidden" name="strHospitalId" id="strHospitalId" value="${EquipmentAuctionAndCondemnationDeskFB.strHospitalId}">
<input type="hidden" name="strItemId" id="strItemId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemId}">
<input type="hidden" name="strItemBrandId" id="strItemBrandId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemBrandId }">
<input type="hidden" name="strItemGroupId" id="strItemGroupId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemGroupId }">
<input type="hidden" name="strItemSubGroupId" id="strItemSubGroupId" value="${EquipmentAuctionAndCondemnationDeskFB.strItemSubGroupId}">
<input type="hidden" name="strsupplierId" id="strsupplierId" value="${EquipmentAuctionAndCondemnationDeskFB.strsupplierId}">
<input type="hidden" name="strIndentificationNo" id="strIndentificationNo" value="${EquipmentAuctionAndCondemnationDeskFB.strIndentificationNo}">
<input type="hidden" name="currentRowIndex" id="currentRowIndex" value=""/>
<input type="hidden" name="strMsgString" id="strMsgString" value="${EquipmentAuctionAndCondemnationDeskFB.strMsgString}">
<cmbPers:cmbPers />
</html:form>
<jsp:include page="bmed_add_committee_member_mutlirow.jsp"></jsp:include>
</body>
</html>

