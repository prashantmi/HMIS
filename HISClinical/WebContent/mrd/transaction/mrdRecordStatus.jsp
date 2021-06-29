<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.vo.RequestRecordDtlVO"%>
<%@page import="hisglobal.vo.MrdRecordDtlVO"%>
<%@page import="mrd.transaction.controller.fb.RecordRequestApprovalFB"%>
<%@page import="java.util.List"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript"><!--

--></script>
<body>
<his:TransactionContainer>
<html:form action="/recordRequestApproval">
	<his:TitleTag name="Mrd Record Detail">
	</his:TitleTag>
		<his:statusRecordFound>
			<%RecordRequestApprovalFB fb=(RecordRequestApprovalFB)pageContext.findAttribute("recordRequestApprovalFB"); %>
			<logic:present name="<%=MrdConfig.MRD_RECORD_DTL_VO_LIST%>">
				<%List requestRecordDtlVOList=(List)request.getSession().getAttribute(MrdConfig.REQUEST_RECORD_VO_LIST); 
				RequestRecordDtlVO requestRecordDtlVO=null;
				for(int i=0;i<requestRecordDtlVOList.size();i++){
					requestRecordDtlVO=(RequestRecordDtlVO)requestRecordDtlVOList.get(i);
					if(requestRecordDtlVO.getMrdRecordId().equals(fb.getMrdRecordId())){
						break;
					}
				}
				request.setAttribute("requestRecordVO",requestRecordDtlVO);
				%>
				<his:ContentTag>
		     		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
						<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="crNo"/>
									</font>
								</div>
						</td>
						<td width="25%" class="tdfont">
					  	 <div align="left">
					  	 	<font color="#000000">
						     <bean:write name="requestRecordVO" property="patCrNo"/>
					    	</font>
					     </div>   
						</td>
						<td width="25%" class="tdfonthead" >
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="patient"/> <bean:message key="name"/>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
					  	 <div align="left">
					  	 	<font color="#000000">
						   	 <bean:write name="requestRecordVO" property="patName"/>
						   </font>
					     </div>   
						</td>
						</tr>
						<tr>
						<td width="25%" class="tdfonthead" >
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<logic:notEqual name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
										<bean:message key="admNo"/>
									</logic:notEqual>
									<logic:equal name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
										<bean:message key="fileNo"/>
									</logic:equal>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
					  	 <div align="left">
					  	 	<font color="#000000">
					    	
					    	 <logic:notEqual name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
								 <bean:write name="requestRecordVO" property="patAdmNo"/>
							</logic:notEqual>
							<logic:equal name="recordRequestApprovalFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
								 <bean:write name="requestRecordVO" property="fileNo"/>
							</logic:equal>
					    	</font>
					     </div>   
						</td>
						<td width="25%" class="tdfonthead" ></td>
						<td width="25%" class="tdfont" ></td>
					</tr>
				</table>
				</his:ContentTag>
				<his:SubTitleTag name="Record Status">
				</his:SubTitleTag>	
				<% List list=(List)session.getAttribute(MrdConfig.MRD_RECORD_DTL_VO_LIST);
					MrdRecordDtlVO mrdRecordDtlVO =(MrdRecordDtlVO)list.get(0); 
					pageContext.setAttribute("mrdRecordDtlVO",mrdRecordDtlVO);%>	
				<his:ContentTag>
					<% boolean disabled=false;String color="#000000"; %>
			 		 	<%if(mrdRecordDtlVO.getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ISSUE) ||
			 		 			mrdRecordDtlVO.getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_LOST) ||
			 		 			mrdRecordDtlVO.getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_DESTROY) ){
			 		 		disabled=true;
			 		 		color="red";
			 		 	}%>
		     			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mrdName"/>
								</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
							<div align="left">
								<font color="#000000">
								&nbsp;<bean:write name="mrdRecordDtlVO" property="mrdName"/>
								</font>
							</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="rack"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
						  	 <div align="left">
						  	 <font color="#000000">
						  		&nbsp;<bean:write name="mrdRecordDtlVO" property="rackLabel"/>
						     </font>
						     </div>   
							</td>
						</tr>	
						<tr>	
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="shelf"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
						  	 <div align="left">
						  	 	<font color="#000000">
							     &nbsp;<bean:write name="mrdRecordDtlVO" property="shelfName"/>
						    	</font>
						     </div>   
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="recordStatus"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
						  	 <div align="left">
						  	 	<font color="<%=color %>">
							     	 &nbsp;<%=MrdConfig.MRD_RECORD_STATUS_ARRAY[Integer.parseInt(mrdRecordDtlVO.getRecordStatus())] %>
						    	</font>
						     </div>   
							</td>
						</tr>	
						<tr>
							
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="issuedFor"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
						  	 <div align="left">
						  	 	<font color="#000000">
						    	 &nbsp;<%=(mrdRecordDtlVO.getIssuedFor()==null?"":mrdRecordDtlVO.getIssuedFor()) %>
						    	</font>
						     </div>   
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noOfRaisedRequest"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
						  	 <div align="left">
						  	 	<font color="#000000">
						    	 &nbsp;<bean:write name="mrdRecordDtlVO" property="raisedRequest"/>
						    	</font>
						     </div>   
							</td>
						</tr>	
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="location"/>
									</font>
								</div>
							</td>
						 	<td width="25%" class="tdfont">
						  	 <div align="left">
						  	 	<font color="#000000">
							     &nbsp;<bean:write name="mrdRecordDtlVO" property="rackLocation"/>
						    	</font>
						     </div>   
							</td>
							<td width="25%" class="tdfonthead" ></td>
							<td width="25%" class="tdfont" ></td>
							
					 </tr>
                </table>
            	</his:ContentTag>
			</logic:present>
			<logic:present name="<%=MrdConfig.MRD_RECORD_ISSUE_DTL_VO_LIST%>">
			<his:SubTitleTag name="Record Issue Detail">
			</his:SubTitleTag>
				<his:ContentTag>
		     		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="issueDate"/></b>
								</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="issueTo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="for"/> <bean:message key="days"/>
									</b>	
									</font>
								</div>
							</td>
						</tr>
					<logic:iterate id="mrdRecordIssueDtlVO" name="<%=MrdConfig.MRD_RECORD_ISSUE_DTL_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdRecordIssueDtlVO">
			 		 <tr>
			 		 	<td class="tdfontheadExam">
						<div align="center">
							<font color="#000000">
							<bean:write name="mrdRecordIssueDtlVO" property="issueDate"/>
							</font>
						</div>
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordIssueDtlVO" property="requestBy"/>
					     </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordIssueDtlVO" property="issuedFor"/>
					    	</font>
					     </div>   
						</td>
					 </tr>
              	</logic:iterate>
                </table>
            	</his:ContentTag>
			</logic:present>	
				
		</his:statusRecordFound>
	
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" window.close()" onkeypress="if(event.keyCode==13) window.close()">
		</his:ButtonToolBarTag>

	<html:hidden name="recordRequestApprovalFB" property="hmode"/>
	<html:hidden name="recordRequestApprovalFB"  property="requestId" />
	<html:hidden name="recordRequestApprovalFB"  property="mrdRecordId" />
	<html:hidden name="recordRequestApprovalFB"  property="recordType" />
		
</html:form>
<his:status/>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
