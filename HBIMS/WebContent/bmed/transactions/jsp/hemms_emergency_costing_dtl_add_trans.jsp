<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%--  
/**
 * @author Partha P Chattaraj and T. Saratkumar
 * Date of Creation : 13-Aug-2013
 * Date of Modification :  
 * Version : 
 * Module  : HEMMS Product 1.0
 */
 --%>
<html>
<head>

<his:css src="/hisglobal/css/Color.css" />

<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:css src="/hisglobal/css/newpopup.css" />

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/newpopup.js"/>

<script language="JavaScript"
	src="/HEMMS_ODISHA/bmed/transactions/js/hemms_emergency_costing_dtl_add_trans.js"></script>

<script type="text/javascript">

</script>

</head>
 
  
<body marginheight="0" marginwidth="0">
<html:form name="hemmsEmergencyCostingDtlFB"
	action="/transactions/HemmsEmergencyCostingDtlACTION"
	type="bmed.transactions.controller.fb.HemmsEmergencyCostingDtlFB" enctype="multipart/form-data">
	<his:TransactionContainer>
		<div id="TitleHide1" style="display:block;">		
	         <his:TitleTag name="Emergency Costing Data Detail">
	         </his:TitleTag>
	    </div> 
	   <%
         
	    String fl=(String)request.getAttribute("flag");
         try
         {
	       if(fl.equals(null) || fl.equals(""))
	       {
		      fl="0";
	       }
         }
         catch(Exception e)
         {
	        fl="0";
          }
         int flag=Integer.parseInt(fl);
         System.out.println("Flag ID "+String.valueOf(flag));
         int counter=0;
         String cntval=request.getParameter("counter");
         try
         {
	      if(cntval.equals(null) || cntval.equals(""))
	      {
			cntval="0";
	      }
	     else
	      {
			counter=Integer.parseInt(cntval);
			counter++;
			}
      }
           catch(Exception e)
         {
	       cntval="0";
          }
      counter=flag;	
      System.out.println("flag::"+flag);
  %>
  <input type="hidden" name="tselected">
  <input type="hidden" name="counter" value="<%=counter%>">
  <input type="hidden" name="flag" value="<%=flag%>">
	   <his:ContentTag>
				
				  <table class="TABLE_STYLE">
						<tr>
							<td colspan="1" width="20%" class="LABEL_TD">Date</td>
							<td width="20%" class="CONTROL_TD"><div id="costingDateDiv"><dateTag:date
						name="strCostingDate" value="${hemmsEmergencyCostingDtlFB.strCostingDate}"></dateTag:date></div></td>
						<input type="hidden" name="strCostingDate"/>		
						<td  width="15%" colspan="1" class="LABEL_TD">Month</td>
							<td width="15%" class="CONTROL_TD">
							<div id="costingMonthDiv"><input type="text"
						class="TEXT_FIELD_NORMAL" name="strCostingMonth" maxlength="10" value="${hemmsEmergencyCostingDtlFB.strCostingMonth}"
						onkeypress="return validateData(event,5)"/></div>
							</td>
							<input type="hidden" name="strCostingMonth"/>
						<td width="15%" colspan="1" class="LABEL_TD">Year</td>
							<td width="15%" class="CONTROL_TD"><div id="costingYearDiv"><input type="text"
						class="TEXT_FIELD_NORMAL" name="strCostingYear" maxlength="4" value="${hemmsEmergencyCostingDtlFB.strCostingYear}"
						onkeypress="return validateData(event,5)"/></div></td>
						<input type="hidden" value="" name="strCostingYear"/>						
						</tr>
						<tr>
							<td width="15%" colspan="5" class="LABEL_TD">Outward No.</td>
							<td width="15%" colspan="1" class="CONTROL_TD">
							<div id="outwardNoDiv">
							<input type="text" class="TEXT_FIELD_NORMAL" name="strOutwardNo" maxlength="50"
						 value="${hemmsEmergencyCostingDtlFB.strOutwardNo}" onkeypress="return validateData(event,9);"/>
							</div>
							</td>
							<input type="hidden" name="strOutwardNo" value="${hemmsEmergencyCostingDtlFB.strOutwardNo}"/>
						</tr>
						<tr>
							<td width="30%" colspan="2" class="LABEL_TD">Name of Office</td>
							<td width="15%" colspan="4" class="CONTROL_TD">
							<div id="officeNameDiv">
								<select name="strOfficeName" class='COMBO_NORMAL' onchange=""  >
									<bean:write name="hemmsEmergencyCostingDtlFB" property="strOfficeNameCmb" filter="false" />
								</select>
							</div>
							</td>
							<input type="hidden" name="strOfficeName" value="${hemmsEmergencyCostingDtlFB.strOfficeName}"/>
						</tr>
					</table>
		</his:ContentTag>	
	   			<his:ContentTag>
	   			<div style="overflow: scroll;">
				   <table class="TABLE_STYLE" border="1px" bordercolor="black" cellspacing="0px" cellpadding="0px">
						<tr>
							<td width="10%" colspan="1" class="LABEL_TD">Sub-Vouchers</td>
							<td width="45%" colspan="3" class="CONTROL_TD" style="text-align: center;">
							<div id="subVoucherDiv">
							<input type="text" class="TEXT_FIELD_MAX" name="strSubVoucher" maxlength="50"
						value="${hemmsEmergencyCostingDtlFB.strSubVoucher}"  onkeypress="return validateData(event,9);"/>
							</div>
							</td>
							<td width="30%" colspan="3" rowspan="2" class="LABEL_TD" style="text-align: center;">Detail of costing with Authorized No. and Date</td>
							<td width="15%" colspan="1" class="LABEL_TD">
							<input type="radio" name="strPlanMode" value="1"/>Plan
							<input type="radio" name="strPlanMode" value="2"/>Non-Plan</td>
						</tr>
						<tr>
							<td width="10%" colspan="1" class="LABEL_TD"><div align="center">Sr. No.</div></td>
							<td width="15%" colspan="1" class="LABEL_TD"><div align="center">Given Date</div></td>
							<td width="15%" colspan="1" class="LABEL_TD"><div align="center">Item Name</div></td>
							<td width="15%" colspan="1" class="LABEL_TD"><div align="center">Purpose</div></td>
							<td width="15%" colspan="1" class="LABEL_TD"><div align="center">Rupees</div></td>
						</tr>
					<%
        			try{
        			%>	
        		<tr>
        		<td width="80%" colspan="8" class="LABEL_TD">
        		<div align="right"><img src="../../hisglobal/images/plus.gif" style="cursor:hand" width="17" height="18" onclick='<%="addrow("+counter+")"%>'></div>
	      	   </td>
	      	   </tr>
	      	   <%	
    
        }catch(Exception e)
        	{
	     		System.out.println("error before logic"+e);
	    	}
        System.out.println("Counter Data = "+counter);   
	    if(counter!=0)
		{
	    	
			int temp_cnt=1;
			int sequence=1;
			int m=0;
			try{
				%>
				<logic:iterate name='hemmsEmergencyCostingDtlFB' property='rows' id='record' indexId='k'> 
			       <%
					System.out.println("flag in iterate::"+flag);
				  	
 	   			 	   	
		 	   	
		 	   	String idString0="datePickerId"+(2*m);
		 	   	String idIconString0="iconId"+(2*m);
		 	   	
		 	   		String idString1="datePickerId"+(2*m+1);
		 	   	String idIconString1="iconId"+(2*m+1);
			 	   	
			 %>
			<tr>
				<td width="10%" colspan="1" class="LABEL_TD"><div align="center">
				<div align="center">
				<html:text name="record" property="strSerialNo" readonly="true" size="5" value='<%=""+sequence%>'></html:text>
				</div></td>
				<td width="15%" colspan="1" class="LABEL_TD"><div align="center">
				<html:text name="record" property="strGivenDate" maxlength="11" onkeypress="return validateData(event,16);"></html:text>
				</div><div align="center">DD-Mon-YYYY</div></td>
				<td width="15%" colspan="1" class="LABEL_TD">
				<div id="itemNameDiv" align="center">
				<html:select name="record" property="strItemName" onchange=""  >
				<bean:write name="hemmsEmergencyCostingDtlFB" property="strItemNameCmb" filter="false" />
				</html:select>
				</div>
				</td>
				<td width="15%" colspan="1" class="LABEL_TD">
				<div id="purposeDiv" align="center">
				<html:select name="record" property="strPurpose" onchange=""  >
				<html:option value="Supplier">Supplier</html:option>				
				<html:option value="Services">Services</html:option>
				<html:option value="Others">Others</html:option>
				</html:select>
				</div>
				</td>
				<td width="30%" colspan="3" class="LABEL_TD"><div align="center">
				<html:textarea name="record" rows="1" cols="50" property="strCostingDetail" onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></html:textarea>
				</div></td>
				<td width="15%" colspan="1" class="LABEL_TD"><div align="center">
				<html:text name="record" property="strAmount" maxlength="12" onkeypress="return validateData(event,7);"></html:text>
				</div>
				<div  align="right"><img src="../../hisglobal/images/minus.gif" style="cursor:hand" 
				width="17" height="18" alt="Remove Row" onclick="delrow(<%=k%>);"></div>
				</td>
			</tr>	
			<%
   			temp_cnt++;
		   sequence++;
   	    %>
  		</logic:iterate>
  
  		<%
		 
			}//try ends
			catch(Exception e)
				{
					System.out.println("erroe in logic iterate-->" + e);
				}
			}//if ends
       %>   
                  </table>
                  </div>
                  </his:ContentTag>
                <his:ContentTag>
				   <table class="TABLE_STYLE" border="1px" bordercolor="black" cellspacing="0px" cellpadding="0px">
						<tr>
							<td width="30%" colspan="2" class="LABEL_TD" style="text-align: left;"></td>
							<td width="50%" colspan="3" class="LABEL_TD" style="text-align: left;">Total Amount</td>
							<td width="20%" colspan="1" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" name="strTotalAmount" maxlength="12"
						value=""  onkeypress="return validateData(event,7);"/>
							</td>
						</tr>
						<tr>
							<td width="30%" colspan="2" class="LABEL_TD" style="text-align: center;"></td>
							<td width="50%" colspan="3" class="LABEL_TD" style="text-align: left;">Rupee in Words</td>
							<td width="20%" colspan="1" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MAX" name="strTotalAmountInWords" maxlength="100"
						value=""  onkeypress="return validateData(event,9);"/>
							</td>
						</tr>
                  </table>
                  </his:ContentTag>
         <his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="HEADER">
					<td colspan="6">DDO Details</td>
				</tr>
			
				<tr >
					<td width="30%" colspan="3" class="LABEL_TD">Amount Received</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strAmountReceived" maxlength="12" value=""
						onkeypress="return validateData(event,7)"/>
					</td>
					<td width="20%" colspan="1" rowspan="4" class="LABEL_TD">HEAD OF ACCOUNT</td>
						<td width="20%" colspan="2" rowspan="4" class="CONTROL_TD">
						<input type="text"
						class="TEXT_FIELD_MAX" name="strHeadOfAccount" maxlength="50" value=""
						onkeypress="return validateData(event,11)"/>
						</td>
				</tr>
				<tr>
					<td width="30%" colspan="3" class="LABEL_TD">Place</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strDDOPlace" maxlength="50" value=""
						onkeypress="return validateData(event,11)"/>
					</td>
				</tr>
				<tr>
					<td width="30%" colspan="3" class="LABEL_TD">Date</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<dateTag:date
						name="strDDODate" value=""></dateTag:date>
					</td>
				</tr>
				<tr>
					<td width="30%" colspan="3" class="LABEL_TD">Drawing and Disbursing Officer</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_MAX" name="strDDOName" maxlength="50" value=""
						onkeypress="return validateData(event,11)"/>
					</td>
				</tr>
				<tr><td colspan="6" width="100%">
				<table width="100%" border="1">
				<tr >
					<td width="30%" colspan="3" class="LABEL_TD">Rupees of Emergency Amount</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strEmergencyAmount" maxlength="12" value=""
						onkeypress="return validateData(event,7)"/>
					</td>
					<td width="20%" colspan="1" class="LABEL_TD">Administrative Department</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_MAX" name="strAdminDept" maxlength="50" value=""
						onkeypress="return validateData(event,11)"/>
					</td>
				</tr>
				<tr >
					<td width="30%" colspan="3" class="LABEL_TD">Finance budget in Rupee for 2013-2014</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strFinanceBudgetAmt" maxlength="12" value=""
						onkeypress="return validateData(event,7)"/>
					</td>
					<td width="20%" colspan="1" class="LABEL_TD">Demand No.</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_MAX" name="strDemandNo" maxlength="50" value=""
						onkeypress="return validateData(event,8)"/>
					</td>
				</tr>
				
				<tr >
					<td width="30%" colspan="3" class="LABEL_TD">Total amount with outward amt in rupee</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strTotalAmtWithOutwrd" maxlength="12" value=""
						onkeypress="return validateData(event,7)"/>
					</td>
					<td width="20%" colspan="1" class="LABEL_TD">Major Head</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
						<div id="majorHeadId">
						<select name="strMajorHead" class='COMBO_NORMAL' onchange=""  >
							<bean:write name="hemmsEmergencyCostingDtlFB" property="strMajorHeadCmb" filter="false" />
						</select>
						</div>
					</td>
				</tr>
				<tr >
					<td width="30%" colspan="3" class="LABEL_TD">Amount of completed work in Rupee</td>
					<td width="30%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strCompletedWorkAmt" maxlength="12" value=""
						onkeypress="return validateData(event,7)"/>
					</td>
					<td width="20%" colspan="1" class="LABEL_TD">Minor Head</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
						<div id="minorHeadId">
						<select name="strMinorHead" class='COMBO_NORMAL' onchange=""  >
							<bean:write name="hemmsEmergencyCostingDtlFB" property="strMinorHeadCmb" filter="false" />
						</select>
						</div>
					</td>
				</tr>
				<tr >
					<td width="30%" colspan="3" rowspan="3" class="LABEL_TD">Balance in Rupee</td>
					<td width="30%" colspan="1" rowspan="3" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_NORMAL" name="strBalance" maxlength="12" value=""
						onkeypress="return validateData(event,7)"/>
					</td>
					<td width="20%" colspan="1" class="LABEL_TD">Sub-Head</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
					<div id="subHeadId">
						<select name="strSubHead" class='COMBO_NORMAL' onchange=""  >
							<bean:write name="hemmsEmergencyCostingDtlFB" property="strSubHeadCmb" filter="false" />
						</select>
					</div>
					</td>
				</tr>
				<tr >
					<td width="20%" colspan="1" class="LABEL_TD">Detailed Head</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
					<div id="detailHeadId">
						<select name="strDetailHead" class='COMBO_NORMAL' onchange=""  >
							<bean:write name="hemmsEmergencyCostingDtlFB" property="strDetailHeadCmb" filter="false" />
						</select>
					</div>	
					</td>
				</tr>
				<tr >
					<td width="20%" colspan="1" class="LABEL_TD">(Object of Expenditure)</td>
						<td width="20%" colspan="1" class="CONTROL_TD">
					<input type="text"
						class="TEXT_FIELD_MAX" name="strObjectExpenditure" maxlength="25" value=""
						onkeypress="return validateData(event,11)"/>
					</td>
				</tr>
				</table>
				</td></tr>
			</table>
		</his:ContentTag>	
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-sv.png"
				onClick="return validate1();" />
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="ClearPage();">
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write
			name="hemmsEmergencyCostingDtlFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="hemmsEmergencyCostingDtlFB" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write
			name="hemmsEmergencyCostingDtlFB" property="strNormalMsg" /></div>

		<input type="hidden" name="hmode" id="hmode"/>
		<input type="hidden" name="strCheck" value="0" />
		<input type="hidden" name="strIsCancel" value="0" />
		
		<html:hidden property="strOfficeNameCmb" value="${hemmsEmergencyCostingDtlFB.strOfficeNameCmb}"/>
		<html:hidden property="strItemNameCmb" value="${hemmsEmergencyCostingDtlFB.strItemNameCmb}"/>
		<html:hidden property="strMajorHeadCmb" value="${hemmsEmergencyCostingDtlFB.strMajorHeadCmb}"/>
		<html:hidden property="strMinorHeadCmb" value="${hemmsEmergencyCostingDtlFB.strMinorHeadCmb}"/>
		<html:hidden property="strSubHeadCmb" value="${hemmsEmergencyCostingDtlFB.strSubHeadCmb}"/>
		<html:hidden property="strDetailHeadCmb" value="${hemmsEmergencyCostingDtlFB.strDetailHeadCmb}"/>

		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>

				<div id="strRenew" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
	</his:TransactionContainer>
</html:form>
</body>
</html>