<!-- 
/**
 * @author CDAC
 */
--><%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.List"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="opd.OpdConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />

<his:javascript src="/opd/js/desk_mapping.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<body>
	<html:form action="/master/AddUserDeskMenuMaster.cnt">



			<his:TitleTag name="Desk Mapping Master">
			</his:TitleTag>

			<logic:equal name="UserDeskMenuMasterFB" property="isGoPressed"
				value="<%=OpdConfig.CHOICE_NO%>">
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<b><bean:message key="seldsk" /></b>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="UserDeskMenuMasterFB" property="deskType"
										onchange="dynamicdropdown(this);"
										styleClass="registrationCmb">
										<html:option value="-1">Select Value</html:option>
										<logic:present
											name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>">
											<html:options
												collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>"
												property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<b><bean:message key="mappingType" /></b>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="UserDeskMenuMasterFB" property="mappingType"
										styleClass="registrationCmb">
										<html:option value="-1">Select</html:option>
									</html:select>
								</div>
							</td>

							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>


					<his:ButtonToolBarTag>
						<span id="saveDiv"> <img class="button"
							src='<his:path src="/hisglobal/images/GO.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) goGet()" tabindex="1"
							onclick="goGet()">
						</span>
					</his:ButtonToolBarTag>
				</his:ContentTag>
			</logic:equal>

			<logic:equal name="UserDeskMenuMasterFB" property="isGoPressed"
				value="<%=OpdConfig.CHOICE_YES%>">
		<table width="100%">
				<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<b><bean:message key="seldsk" /></b>
									</div>
								</td> 
							<td width="25%" class="tdfont">
							<div align="left">

								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name="UserDeskMenuMasterFB" property="selectedDeskTypeDesc"/></b>
								</font></div>
								</td>
								
								<td width="25%" class="tdfonthead">
							<div align="Right">
										<b><bean:message key="mappingType" /></b>
									</div>
								</td><td width="25%" class="tdfont">
								<div align="left">

								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:write name="UserDeskMenuMasterFB" property="selectedMappingTypeDesc"/></b>
								</font></div>
								</td></tr>
				</table>
			
				<logic:equal name="UserDeskMenuMasterFB" property="mappingType"  value="<%=OpdConfig.DESK_MAPPING_TYPE_UNIT_WISE%>">
			

					<his:SubTitleTag name="Select Units">
					</his:SubTitleTag>
					<his:ContentTag>
						<table id="unitWise " width="100%" border="0" cellspacing="1" cellpadding="0" >
								<tr>
								<td width="15%" class="tdfonthead">
								<div align="right">
								<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message	key="deptName" />
								</font>
								</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center">
										<html:select name="UserDeskMenuMasterFB" property="deptId"
											onkeypress="if(event.keyCOde==13) deptSelected(this);"
											onchange="deptSelected(this);" styleClass="registrationCmb">
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>">
												<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="15%" class="tdfont"></td>
								<td width="35%" class="tdfonthead"></td>
							</tr>
							<tr>
								<td width="15%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:message key="deptName" />
										</font>
									</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center" style="display: none;" >
										<html:select name="UserDeskMenuMasterFB" property="mainUnitsList" style="width: inherit;" multiple="true" size="6">
												<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED%>">
													<html:options
													 	collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED%>" 
														property="value" labelProperty="label" />
												</logic:present>
										</html:select>
									</div>
									<div align="center">
										<html:select name="UserDeskMenuMasterFB" property="unitsList" multiple="true" size="6">
										</html:select>
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link"
											onClick='moveRightSingle("2");' /> <img
											src="/HIS/hisglobal/images/avai/forwardward.gif" class="link"
											onClick='moveRightAll("2");' /> <br>
										<br> <img src="/HIS/hisglobal/images/avai/back3.gif"
											class="link" onClick='moveLeftSingle("2");' /> <img
											src="/HIS/hisglobal/images/avai/backward.gif" class="link"
											onClick='moveLeftAll("2");' />
									</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center">
										<html:select name="UserDeskMenuMasterFB"
											property="selectedUnits" multiple="true" size="6">
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" class="tdfonthead"></td>
								<td width="35%" class="tdfonthead"></td>
								<td width="15%" class="tdfont"></td>
								<td width="35%" class="tdfonthead"></td>
							</tr>
						</table>
					</his:ContentTag>
				</logic:equal>

<logic:equal name="UserDeskMenuMasterFB" property="mappingType" value="<%=OpdConfig.DESK_MAPPING_TYPE_WARD_WISE%>">
					<his:SubTitleTag name="Select Wards">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuMasterFB" property="unitId" onchange="showWards();">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER%>" >
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="ward"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
							<html:select name="UserDeskMenuMasterFB" property="wardsList" multiple="true" size="6">
							<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT%>" >
							<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT%>" property="value" labelProperty="label" />
							</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("3");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("3");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("3");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuMasterFB" property="selectedWards" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
					</logic:equal>

				<logic:equal name="UserDeskMenuMasterFB" property="mappingType" value="<%=OpdConfig.DESK_MAPPING_TYPE_USER_WISE%>">
					<his:SubTitleTag name="Select Users">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							
							
							
							
							<tr>
								<td width="15%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="group" />
										</font>
									</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center">  <!-- selectedGroup(this); -->
									<html:select name="UserDeskMenuMasterFB" property="group"
											onkeypress="if(event.keyCOde==13) showSeats(); "
											onchange="showSeats(); " styleClass="registrationCmb">
																		
											<html:option value="-1">Select Value</html:option>
											<logic:present
												name="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST%>">
												<html:options
													collection="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST%>"
													property="value" labelProperty="label" />
											</logic:present>
										</html:select>
									</div>
								</td>
								<td width="15%" class="tdfont"></td>
								<td width="35%" class="tdfonthead"></td>
							</tr>
							<tr>
								<td width="15%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:message key="user" />
										</font>
									</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center"  >
										<html:select name="UserDeskMenuMasterFB" property="seatsList" style="width: inherit;" multiple="true" size="6">
												<logic:present name="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS%>">
													<html:options
													 	collection="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS%>" 
														property="value" labelProperty="label" />
												</logic:present>
										</html:select>
									</div>
									<%-- <div align="center" >
										<html:select name="UserDeskMenuMasterFB" property="seatsList" multiple="true" size="6">
										</html:select>
									</div> --%>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link"
											onClick='moveRightSingle("1");' /> <img
											src="/HIS/hisglobal/images/avai/forwardward.gif" class="link"
											onClick='moveRightAll("1");' /> <br>
										<br> <img src="/HIS/hisglobal/images/avai/back3.gif"
											class="link" onClick='moveLeftSingle("1");' /> <img
											src="/HIS/hisglobal/images/avai/backward.gif" class="link"
											onClick='moveLeftAll("1");' />
									</div>
								</td>
								<td width="35%" class="tdfonthead">
									<div align="center">
										<html:select name="UserDeskMenuMasterFB"
											property="selectedSeats" multiple="true" size="6">
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="15%" class="tdfonthead"></td>
								<td width="35%" class="tdfonthead"></td>
								<td width="15%" class="tdfont"></td>
								<td width="35%" class="tdfonthead"></td>
							</tr>
						</table>
					</his:ContentTag>
				</logic:equal>
		
		
			<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
						 <tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<b><bean:message key="selectDesk" /></b>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									
									
									
									<html:select name="UserDeskMenuMasterFB" property="deskId"
										styleClass="registrationCmb">
										<html:option value="-1">Select Value</html:option>
										<logic:present
											name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>">
											<html:options
												collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>"
												property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
							<td width="25%" class="tdfonthead"></td>
							<td width="25%" class="tdfont"></td>
						</tr>
					</table>
				</his:ContentTag> 

				<his:ButtonToolBarTag>
					<span id="saveDiv"> <img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
						style="cursor: pointer"
						onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
						tabindex="1" onclick="finalSubmit('SAVE')"> <img
						class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1"
						onclick="submitPage('CANCEL')"
						onkeypress="if(event.keyCode==13) submitPage('CANCEL')"> <img
						class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
						style="cursor: pointer" tabindex="1" onclick="submitPage('ADD')"
						onkeypress="if(event.keyCode==13) submitPage('ADD')">
					</span>
				</his:ButtonToolBarTag>
				<html:hidden name="UserDeskMenuMasterFB" property="mappingType" />
				
				<html:hidden name="UserDeskMenuMasterFB" property="deskType" />
				
			</logic:equal>



		<html:hidden name="UserDeskMenuMasterFB" property="isGoPressed" />
		<html:hidden name="UserDeskMenuMasterFB" property="hmode" />
		<html:hidden name="UserDeskMenuMasterFB" property="selectedMappingTypeDesc"/>
		<html:hidden name="UserDeskMenuMasterFB" property="selectedDeskTypeDesc"/>
	</html:form>

	<center>
		<b><his:status /></b>
	</center>

</body>

</html>





