<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/time.js"/>


<%@ page import ="java.util.*,registration.*,hisglobal.vo.*, registration.master.controller.util.*, hisglobal.persistence.*, registration.master.controller.fb.*" %>
<script>
	function moveUP(str){
		//alert(str);
		elem = document.getElementsByName("moveStr")[0];
		elem.value=str;
		submitForm('MOVE_UP');
	}
	
	function moveDOWN(str){
		//alert(str);
		elem = document.getElementsByName("moveStr")[0];	
		elem.value=str;
		submitForm('MOVE_DOWN');
	}
</script>
<script>
	function validateIt(){
		//alert("Validate");
		return true;
	}
</script>
<his:TransactionContainer>
<his:ContentTag>
<%System.out.println("deptRosterShiftSequencingTile_________________1"); %>

<bean:define name="<%=RegistrationConfig.DEPT_SHIFT_ROSTER_VO%>" id="deptShiftRosterVO"/>
<% if(!((DeptShiftRosterVO)deptShiftRosterVO).isEmpty()){ %>
<logic:notEmpty name="<%=RegistrationConfig.DEPT_SHIFT_ROSTER_VO%>" property="colDeptDayOfWeekRoster">
<%System.out.println("deptRosterShiftSequencingTile_________________2"); %>
<table width="100%" cellspacing="1">
	<logic:iterate name="<%=RegistrationConfig.DEPT_SHIFT_ROSTER_VO%>" property="colDeptDayOfWeekRoster" id="deptDayOfWeekRoster">
				<%System.out.println("deptRosterShiftSequencingTile_________________3"); %>
					
		<%System.out.println("deptRosterShiftSequencingTile_________________4"); %>
		<logic:iterate name="deptDayOfWeekRoster" property="colDeptDayOfWeekShiftRoster" id="deptDayOfWeekShiftRoster">
					<bean:define name="deptDayOfWeekRoster" property="dayOfWeek" type="java.lang.String" id="dOwID"/>

					<%String tmpRowDay = "dOw"+dOwID; %>	
		<%System.out.println("deptRosterShiftSequencingTile_________________5"); %>
			<%Collection deptDayOfWeekShiftWeekOfMonth=new ArrayList(); %>
					<bean:define name="deptDayOfWeekShiftRoster" property="colDeptDayOfWeekShiftWeekOfMonth" id="_deptDayOfWeekShiftWeekOfMonth"/>
					<%deptDayOfWeekShiftWeekOfMonth = (Collection)_deptDayOfWeekShiftWeekOfMonth; %>
			<%System.out.println("deptRosterShiftSequencingTile_________________6"); %>
			<tr>
				<td colspan="16">
					<%String shiftCode= ""; %>
					<logic:notEmpty  name="deptDayOfWeekShiftRoster" property="shiftCode" >
						<bean:define name="deptDayOfWeekShiftRoster" property="shiftCode" type="java.lang.String" id="_shiftCode"/>
						<%shiftCode = (String)_shiftCode; %>
					</logic:notEmpty>
					
					<his:SubTitleTag>
						<his:name>
						 
						</his:name>
						<table width="100%">
							<tr>
								<td align="left">
									<strong>
									<bean:message key='<%=tmpRowDay%>'/>
									&nbsp;&nbsp;
									<bean:write name="deptDayOfWeekShiftRoster" property="shift"/>
									</strong>
								</td>
							</tr>
						</table>
						
					</his:SubTitleTag>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead">
				<div align="center">
				<b>
				<bean:message key="SNo"/>
				</b>
				</div>
				</td>
				<td colspan="3" class="tdfonthead"><center><bean:message key="nth1"/></center></td>
				<td colspan="3" class="tdfonthead"><center><bean:message key="nth2"/></center></td>
				<td colspan="3" class="tdfonthead"><CENTER><bean:message key="nth3"/></CENTER></td>
				<td colspan="3" class="tdfonthead"><CENTER><bean:message key="nth4"/></CENTER></td>
				<td colspan="3" class="tdfonthead"><CENTER><bean:message key="nth5"/></CENTER></td>
			</tr>
			<tr>
			<%
				int numberOfWoM = 5;//for the time been static
				//create 5 empty iterator for each shift
				Iterator itr[] = new Iterator[numberOfWoM];
				int[] currCounter = new int[numberOfWoM];
				int[] maxCounter = new int[numberOfWoM];
				for(int i=0; i<numberOfWoM; i++){
					itr[i]=new ArrayList().iterator();
					currCounter[i] = 0;
					maxCounter[i]=0;
				}
				
				Collection colDeptDayOfWeekShiftWeekOfMonth = (Collection)deptDayOfWeekShiftWeekOfMonth;
				Iterator itr_1 = colDeptDayOfWeekShiftWeekOfMonth.iterator();
				
				//assign iterator for the Corresponding DeptUnit col
				System.out.println("deptRosterShiftSequencingTile_________________7");
				while(itr_1.hasNext()){
					Object obj = itr_1.next();
					System.out.println("itr_1.next().getClass():  "+obj.getClass() );
					DeptShiftRosterVO.DeptDayOfWeekShiftWeekOfMonthVO dDoWSWoMVO=(DeptShiftRosterVO.DeptDayOfWeekShiftWeekOfMonthVO)obj;
					int wOm = Integer.parseInt(dDoWSWoMVO.getWeekOfMonth())-1;
					Collection colDU = dDoWSWoMVO.getColDeptUnit();
					itr[wOm]=colDU.iterator();
					maxCounter[wOm]=colDU.size();
				}
				System.out.println("deptRosterShiftSequencingTile_________________8");
				boolean flag= false;
				int sNo=1;
				while (true){
					Collection colRow= new ArrayList();
					//add SerialNumber to cOllection
					colRow.add(sNo+"");
					sNo++;
					System.out.println("deptRosterShiftSequencingTile_________________9");
					flag=false;
					for(int i=0; i<numberOfWoM; i++){
						String deptUnit="";
						String deptUnitCode="";
						if(itr[i].hasNext()){
							System.out.println("deptRosterShiftSequencingTile_________________10");
							Object obj_1 = itr[i].next();
							System.out.println("deptRosterShiftSequencingTile_________________10: obj_1.getClass():  "+obj_1.getClass());
							DeptShiftRosterVO.DeptUnitVO deptUnitVO = (DeptShiftRosterVO.DeptUnitVO) obj_1;
							deptUnit = deptUnitVO.getDepartmentUnit();
							deptUnitCode= deptUnitVO.getDepartmentUnitCode();
							currCounter[i]++;
							flag=true;
						}
						System.out.println("deptRosterShiftSequencingTile_________________11");
						//display the deptUnit
						//onUpDown(dOwID_shiftCode_i_deptUnitCode

						System.out.println("deptRosterShiftSequencingTile_________________12");
						String str2Pass = "";
							if(deptUnitCode!=""){
								if(currCounter[i]==1){
									str2Pass="";
								}else{
									System.out.println("deptRosterShiftSequencingTile_________________13");
									str2Pass=dOwID+"_"+shiftCode+"_"+(i+1)+"_"+deptUnitCode;
								}
							
							}
						colRow.add(str2Pass);// empty string if nO button to show
						colRow.add(deptUnit);

						System.out.println("deptRosterShiftSequencingTile_________________14");
						String str2Pass_ = "";
						if(deptUnitCode!=""){
							if(currCounter[i]>=maxCounter[i]){
								str2Pass_="";
							}else
								str2Pass_=dOwID+"_"+shiftCode+"_"+(i+1)+"_"+deptUnitCode;	
						}
						
						colRow.add(str2Pass_);
					}
					if(flag==true){
						Iterator itrCol = colRow.iterator();
						for(int colNo=0; itrCol.hasNext(); colNo++){
					%>
							<td class="tdfont"><center>
					<%
							String str;
							switch(colNo){
								case(0)://1st Col
					%>
									<%=(String)itrCol.next() %>
					<%				break;
								case(1)://2nd Col... ArrOw
								case(4)://5th Col
								case(7)://8th Col
								case(10)://11th Col
								case(13)://14th Col
									str=(String)itrCol.next();
									if(str!=""){
//										 insert the up arrOw image
					%>
								
								<img class="button" src='<his:path src="/hisglobal/images/up-arr.png"/>' tabindex="1" border="0" style="cursor:pointer" onClick='moveUP("<%=str%>");' onkeypress="if(event.keyCode==13) moveUP('<%=str%>');">
					<%				}
									break;
								case(2)://3rd Col
								case(5)://6th Col
								case(8)://9th Col
								case(11)://12th Col
								case(14)://15th Col
					%>
								<%=(String)itrCol.next() %>
					<%				break;			
								case(3)://4th Col
								case(6)://7th Col
								case(9)://10th Col
								case(12)://13th Col
								case(15)://16th Col
									str=(String)itrCol.next();
									if(str!=""){
//										 insert the down arrOw image
					%>
								<img class="button" src='<his:path src="/hisglobal/images/down-arr.png"/>' border="0" style="cursor:pointer" onClick='moveDOWN("<%=str%>");' onkeypress="if(event.keyCode==13) moveDOWN('<%=str%>');">
					<%				}
									break;								
							}
				%>
							</center></td>
				<%		
						}
					}else
						break;
				%>
					</tr>
				<%		
				}
			%>
		</logic:iterate>		
	</logic:iterate>
</table>
<%System.out.println("deptRosterShiftSequencingTile_________________15"); %>
</logic:notEmpty>
<%} %>
</his:ContentTag>
</his:TransactionContainer>
<his:status/>

<input type="hidden" name="moveStr"/>