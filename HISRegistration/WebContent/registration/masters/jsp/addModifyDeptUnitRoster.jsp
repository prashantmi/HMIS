<%@page import="vo.registration.ShiftVO"%>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@page import="vo.registration.RosterMasterVO"%>
<%@page import="registration.masters.controller.util.DeptUnitRosterMstUTIL"%>
<%@page import="vo.registration.DeptUnitRosterVO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 23-Jan-2014 		-->

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="./../registration/masters/js/rosterMst.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script> 
<script>
function checkShiftWeekOfMonthConsistency(){
	
	lenControlArray = document.getElementsByName("rosterModel.week1stOfMonth").length; // length of all the chkbox arrays are gonna be same

	for(elemIdx=0; elemIdx<lenControlArray; elemIdx++){
		flagWoMChked = false; // flag will be set to tru if a checkbox in any weekOfMonth array is checked for this element of the 
		//there r 5 weeks in mOnth
		for(weekId=0; weekId<5; weekId++){ // for each week of Month
			//arrWeekOfMonth= new Array();
			switch(weekId){
				case(0):
					arrWeekOfMonth = document.getElementsByName("rosterModel.week1stOfMonth");
					break;
				case(1):
					arrWeekOfMonth = document.getElementsByName("rosterModel.week2ndOfMonth");
					break;	
				case(2):
					arrWeekOfMonth = document.getElementsByName("rosterModel.week3rdOfMonth");
					break;
				case(3):
					arrWeekOfMonth = document.getElementsByName("rosterModel.week4thOfMonth");
					break;
				case(4):
					arrWeekOfMonth = document.getElementsByName("rosterModel.week5thOfMonth");
					break;
			}
			if(arrWeekOfMonth[elemIdx].checked){  // checked in this row?
				flagWoMChked = true;
				break;
			}
		}
			
		flagShiftChked = false; // flag will be set to tru if a checkbox in any shift array is checked for this idx
			
		for(shiftId=0; shiftId<<%=DeptUnitRosterMstUTIL.getNumberOfShifts(request)%>; shiftId++){
			shiftChkName = "rosterModel.shift["+shiftId+"]";
			//shiftChkName = "rosterModel.shift";
			arrShiftChk = document.getElementsByName(shiftChkName);
				
			if(arrShiftChk[elemIdx].checked){  // checked in this row?
				flagShiftChked = true;
				break;
			}
		}
			
		if(flagShiftChked != flagWoMChked){ // Not True-True or False-False >> a wOm selected w/o selecting shift or shift is slected w/o specifying wOm
			if(flagShiftChked)
				alert("Specify Week Of month for the selected shift");
			else
				alert("Assign a shift for the selected Week Of month");
			return false;
		}
	}
	return true;
		
}

</script>
</head>
<script language="JavaScript" src="../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<body onload="">
<s:form action="DeptUnitRoster">
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="global.department"/>&nbsp;<s:text name="global.unit"/>&nbsp;<s:text name="global.shift"/>&nbsp;<s:text name="global.assignment"/>
				</div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label" style="width: 16%;">&nbsp;<s:text name="global.department"/>&nbsp;</div>
				<div class="div-table-col" style="width: 16%;"><s:select cssStyle="width: 100%" key="strDeptCode" value="%{rosterModel.strDeptCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.departmentList}"  listKey="value" listValue="label" name="rosterModel.strDeptCode" onchange="getUnit();"> </s:select>			
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="global.unit"/>&nbsp;</div>
				<div class="div-table-col" style="width: 16%;"><s:select cssStyle="width: 100%" key="strUnitCode" value="%{rosterModel.strUnitCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.unitList}" listKey="value" listValue="label" name="rosterModel.strUnitCode" onchange="getRoom();"> </s:select>			
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="global.room"/>&nbsp;</div>
				<div class="div-table-col" style="width: 16%;"><s:select cssStyle="width: 100%" key="strRoomCode" value="%{rosterModel.strRoomCode}" headerKey="-1" headerValue="Select Value" 
				 				list="%{#session.roomList}" listKey="value" listValue="label" name="rosterModel.strRoomCode" onchange="getRoster();"> </s:select>			
				</div>
			</div>	
</div>
<s:set name="noOfShifts" value="%{#session.noOfShifts}"/>
<%
int noOfShifts=DeptUnitRosterMstUTIL.getNumberOfShifts(request);
%>
<s:if test="flag=='ROSTER'">
<div class="div-table" id="rosttable">
			<div class="div-table-row ">
				<div class="div-table-col title width70 ">
						<s:text name="global.roster"/>&nbsp;<s:text name="global.details"/>
				</div>
				<div class="div-table-col title width20 ">
<%-- 						<s:text name="global.effdate"/><s:property value="DeptUnitRosterMstUTIL.getNumberOfShifts(#request)"/> --%>
				</div>
				<div class="div-table-col title width10 ">
<%-- 						<s:datetimepicker></s:datetimepicker> --%>
				</div>
			</div>
			<table width="100%" class="div-table-listing rounded width100">
			<tr class="div-table-row listHeader">
				<td colspan="1" width="17%">
				<div align="center"><s:text name="roster.daysOfWeeek"/></div>				
				</td>
				<td colspan="5" width="50%">
				<div align="center"><s:text name="roster.weekOfMonth"/></div>				
				</td>
				<td colspan="<%=noOfShifts%>" width="30%">
				<div align="center"><s:text name="global.shift"/></div>				
				</td>
			</tr>
			<tr class="div-table-row listData">
				<td colspan="1"><div align="center">&nbsp;</div></td>
				<td colspan="1"><div align="center">1st&nbsp;<s:text name="global.week"/></div></td>
				<td colspan="1"><div align="center">2nd&nbsp;<s:text name="global.week"/></div></td>
				<td colspan="1"><div align="center">3rd&nbsp;<s:text name="global.week"/></div></td>
				<td colspan="1"><div align="center">4th&nbsp;<s:text name="global.week"/></div></td>
				<td colspan="1"><div align="center">5th&nbsp;<s:text name="global.week"/></div></td>
				
				<s:set name="optionShiftVO" value="%{#session.optionShift}"/>
				<s:iterator value="%{#session.optionShift}" status="r" id="optionShiftVOId">
						<td><div align="center"><s:property value="strShiftDesc"/></div></td>
						<input type="hidden" name="startTime" value='<s:property value="strShiftStartTime"/>'/>
		  				<input type="hidden" name="endTime" value='<s:property value="strShiftEndTime"/>'/>
				</s:iterator>
			</tr>		
			
			<s:set name="DeptUnitRosterVO" value="%{#session.rosterModel}"></s:set>	
			
			<%			 
				System.out.println("--No Of Shifts -----"+noOfShifts+"-----");
				DeptUnitRosterVO rosterVO=new DeptUnitRosterVO();
				if(request.getSession().getAttribute("rosterCurrentModel")!=null)
					rosterVO=(DeptUnitRosterVO)request.getSession().getAttribute("rosterCurrentModel");
				else
					rosterVO=(DeptUnitRosterVO)request.getSession().getAttribute("rosterModel");
				for(int di=1; di<= 7; di++){ 
				//for each Day of Week	
				int noOfRows = DeptUnitRosterMstUTIL.getNumOfRows4aDOfWinJSP(rosterVO, request, di);
				//int noOfRows=1;
				for(int ri=0; ri<noOfRows; ri++){
				//for each row in a Day
		
			%>
			
			<tr class="div-table-row listData">
			<td>
			<table width="100%">
				<tr>
					<td>
					<div align="left">
						<%if(ri==0){  String tmpRowDay = "dOw"+di; 
						request.setAttribute("tmpRowDay", tmpRowDay);
						%>
						<s:text name="%{#request.tmpRowDay}"></s:text>
						<%}%>
					</div>
					</td>
					<td width="20%">
				    	<div align="left">
				    		<input type="checkbox" name="selectAll<%=di%>" value="<%=di+"_"+ri%>" onclick="selectAllWeek(this)">
				    	</div>
			   		 </td>	
				</tr>
			</table>
		</td>
	    <td>
	    	<div align="center">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterVO" property="rosterModel.week1stOfMonth" value='<%=di+"_"+ri%>'/>
      		</div>
        </td>
        <td>
	    	<div align="center">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterVO" property="rosterModel.week2ndOfMonth" value='<%=di+"_"+ri%>'/>
      		</div>
        </td>
        <td>
	    	<div align="center">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterVO" property="rosterModel.week3rdOfMonth" value='<%=di+"_"+ri%>'/>
      		</div>
        </td>
	    <td>
	    	<div align="center">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterVO" property="rosterModel.week4thOfMonth" value='<%=di+"_"+ri%>'/>
      		</div>
        </td>
	    <td>
	    	<div align="center">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterVO" property="rosterModel.week5thOfMonth" value='<%=di+"_"+ri%>'/>
      		</div>
        </td>
		<%for(int si=0; si<noOfShifts; si++){ %>
	    <td>
	      <div align="center">
	      <his:checkbox onClick="checkShiftTimeClash(this);" name="DeptUnitRosterVO" id='<%="rosterModel.shift["+si+"]"%>' property='<%="rosterModel.shift["+si+"]"%>' value='<%=di+"_"+ri%>'/>
	      </div>
        </td>
        <%}%>
		
		<td style="border: 0px;">
			<div align="center">
				<img src="../hisglobal/images/Minus.png" onclick='deleteRowData("<%=di+"_"+ri%>");'>	
			</div>
		</td>
        <td style="border: 0px;">
			<div align="center">
				<%if(ri==0){%>				
					<img src="../hisglobal/images/plus.png" onclick='addRowData("<%=di%>");'>			
				<%} %>
			</div>
		</td>
		
	</tr>
			
	<%} } 
	  for(int si=0; si<noOfShifts; si++){%>
	  <input type="hidden" name="rosterModel.shift" id='<%="rosterModel.shift("+si+")"%>' value=""/> 
	<%}%>    
	</table>
	
</div>	
</s:if>


<s:if test="flag=='ADD'">

<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
</div>
	<div class="div-table-row" align="center">
				<a href="#" class="button" onclick="submitCancel();"><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>	
					
	</div>
</div>
</s:if>
<s:else>
<div class="div-table-button">
	<div class="div-table-row" align="center">
				<a href="#" class="button" onclick="saveRoster();"><span class="save"><s:text name="save"/></span></a>
				<a href="#" class="button" onclick="submitCancel();"><span class="cancel"><s:text name="cancel"/></span></a>
				<a href="#" class="button" onclick="clearForm();"><span class="clear"><s:text name="clear"/></span></a>
				<a href="#" class="button" onclick="confirmExecuteRoster();"><span class="generate"><s:text name="generate"/></span></a>
				<a href="#" class="button" onclick="submitDelete();"><span class="delete"><s:text name="Delete"/></span></a>
	</div>
</div>
</s:else>
</div>
<s:hidden key="strDeptUnitCode" name="rosterModel.strDeptUnitCode" value="%{rosterModel.strDeptUnitCode}"></s:hidden>
<%
DeptUnitRosterVO rosterVO=(DeptUnitRosterVO)request.getSession().getAttribute("rosterModel");
%>

<input type="hidden" name="addRow"/>
<input type="hidden" name="removeRow"/>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<h4><font color="#FF0000"><s:property value="%{rosterModel.StrWarning}"/></font></h4>
<h4><s:property value="message"/></h4>


</body>
</html>