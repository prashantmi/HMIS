<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: CHANDAN GUPTA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : FILM Master
 ## Purpose						        : 
 ## Date of Creation					: 4-May-2016 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
 -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/FilmMst.js" />
<body >

	<html:form action="/masters/filmMstACTION">



		<html:hidden name="filmMstFB" property="hmode" />
			<html:hidden name="filmMstFB" property="filmid" />
		<html:hidden name="filmMstFB" property="selectedChk" />
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="filmMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Film Master">
				<%-- <his:insertDateTag/> --%>
			</his:TitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="TestName" />&nbsp;
								</font>
							</div>
						</td>
					<logic:notEqual name="filmMstFB" property="hmode" value="MODIFY">
						
						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_FILM_TESTNAME_COMBO %>">
								<div align="left">

									<html:select name="filmMstFB" style="width:58%"
										property="testCode" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_FILM_TESTNAME_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
						</logic:notEqual>
					
				<logic:equal name="filmMstFB" property="hmode" value="MODIFY">
						
						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_FILM_TESTNAME_COMBO %>">
								<div align="left">

									<html:select name="filmMstFB" style="width:58%"
										property="testCode" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_FILM_TESTNAME_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
									<html:hidden name="filmMstFB" property="testCode"/>
								</div>
							</logic:present></td>
						</logic:equal>	
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="FilmType" />&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="filmMstFB" property="filmType" maxlength="60"
									size="30" readonly="<%=this.readOnly %>"
									onkeypress=""
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="FilmSize" />&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="filmMstFB" property="filmSizeLength"
									maxlength="6" size="10" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event)"
									tabindex="1">
								</html:text>
								"X
								&nbsp;
								<html:text name="filmMstFB" property="filmSizeBreadth"
									maxlength="6" size="10" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event)"
									tabindex="1">
								</html:text>"(length X Breadth)
							</div>
						</td>
					</tr>

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
										key="nooffilm" /> &nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left">
						<html:text name="filmMstFB" property="noOfFilmReqd"
									maxlength="2" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event)"
									tabindex="1">
								</html:text>
						</div>		
					   </td>		
					</tr>
					<tr>
		              	<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="storename" />&nbsp;
							</font>
							</div>
						</td>
							<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_FILM_STORENAME_COMBO%>">
								<div align="left">

									<html:select name="filmMstFB" style="width:58%"
										property="storeid" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_FILM_STORENAME_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>			
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="itemFromStore" />&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_FILM_ITEMNAME_COMBO %>">
								<div align="left">

									<html:select name="filmMstFB" style="width:58%"
										property="itemStoreId" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_FILM_ITEMNAME_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="RED" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
									key="remarks" />&nbsp;
							</font>
						</div>
					</td>
                         <td width="50%" class="tdfont">
							<div align="left">
						<html:textarea name="filmMstFB" property="remarks"
									cols="28" tabindex="1" readonly="<%=this.readOnly%>"
								onkeypress="return CheckMaxLength(event,this,50,1)">	
					  </html:textarea>
						</div>
					</td>
					</tr>

				



						</table>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="filmMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="filmMstFB" property="hmode" value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) validatesave();"
								onclick="validatesave();" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearAddForm()"
								onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="filmMstFB" property="hmode" value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) validatemodify()"
							onclick="validatemodify()" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onkeypress="if(event.keyCode==13) clearMODIFYForm()" 
							onclick="clearMODIFYForm()"
							 tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
			<his:status />
		</his:TransactionContainer>



	</html:form>
</body>
</html>