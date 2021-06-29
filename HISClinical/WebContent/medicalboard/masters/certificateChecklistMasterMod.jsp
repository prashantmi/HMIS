<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/medicalboard/js/certificateChecklistMaster.js" />
<his:javascript src="/registration/js/registration.js" />

<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<%try{ %>
 <body>
<his:TransactionContainer>
  <html:form action="/certificateChecklistMaster" > 
    	
	<his:TitleTag name="Certificate Checklist Master >> Modify">	</his:TitleTag>
  	   
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="certificateType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			     	&nbsp;<bean:write name="certificateChecklistMasterFB" property="certificateType"/>
		  			<html:hidden name="certificateChecklistMasterFB" property="certificateTypeID"/>
		  			<html:hidden name="certificateChecklistMasterFB" property="certificateType"/>
		  		   </div>
			     </td>  
		      </tr>
		  </table>
		  </his:ContentTag>
		  <logic:present name="<%=MedicalBoardConfig.CHECKLIST_ADDED_TO_CERTIFICATE_TYPE %>">
		  <his:SubTitleTag name="Previously Added Checklist">
		  </his:SubTitleTag>
		  <his:ContentTag>
		  <table width="100%" border="0" cellspacing="1" cellpadding="0">
				    <tr>
				      <td width="5%" class="tdfonthead">
					     <div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<input type="checkbox" name="selectAll" onclick="checkAll(this)">
							</font>
						  </div>
					    </td>
				      <td width="40%" class="tdfonthead">
					     <div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="checklist"/>&nbsp;</b>
							</font>
						  </div>
					    </td>
				      <td width="40%" class="tdfonthead">
					     <div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="isCompulsory"/>&nbsp;</b>
							</font>
						  </div>
					    </td>
				 </tr>
				 <logic:iterate id="certificateChecklistVO" name="<%=MedicalBoardConfig.CHECKLIST_ADDED_TO_CERTIFICATE_TYPE%>" type="hisglobal.vo.MBCertificateChecklistVO" indexId="index"> 
				  	<tr>
				      	<td width="5%" class="tdfont">
					     <div align="center">
					     	<input type="checkbox" name="selectedChecklistID" value="<%=index%>" onclick="setForEdit(this)">
					     </div>
					    </td>
				      	<td width="40%" class="tdfont">
					     <div align="center">
					     	<bean:write name="certificateChecklistVO" property="checklist"/>
					     </div>
					    </td>
				      	<td width="40%" class="tdfont">
					     <div align="center">
					     	<%String checklistCompulsoryArray[]=MedicalBoardConfig.CHECKLIST_COMPULSORY_FLAG_ARRAY; %>
					     <%--<%=checklistCompulsoryArray[Integer.parseInt(certificateChecklistVO.getIsCompulsory())] %>
					     	--%>
					     		<html:select name="certificateChecklistMasterFB" property="existingIsCompulsory" value="<%=certificateChecklistVO.getIsCompulsory() %>" styleClass="regcbo" tabindex="1" style="width:200px" disabled="true">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST %>">
								<html:options collection="<%=MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST %>" labelProperty="label" property="value"/>
								</logic:present>
								</html:select>
						 </div>
					    </td>
					</tr>  
				</logic:iterate>   
		  	</table>
		  </his:ContentTag>	
		 </logic:present> 
		  <his:SubTitleTag name="Add Checklist">
		  </his:SubTitleTag>
	  		
	  		<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				    <tr>
				      <td width="40%" class="tdfonthead">
					     <div align="center">
							<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="checklist"/>&nbsp;</b>
							</font>
						  </div>
					    </td>
				      <td width="40%" class="tdfonthead">
					     <div align="center">
							<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
						    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="isCompulsory"/>&nbsp;</b>
							</font>
						  </div>
					    </td>
				      <td width="20%" class="tdfonthead">
					     <div align="center">
							
						  </div>
					    </td>
					 </tr>
					 
				    <tr>
				      <td width="40%" class="tdfont">
					     <div align="center">
							<html:select name="certificateChecklistMasterFB" property="checklistID" styleClass="regcbo" tabindex="1" onchange="setIsCompulsory(this)" style="width:200px">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE %>">
								<html:options collection="<%=MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE %>" labelProperty="label" property="value"/>
								</logic:present>
							</html:select>
						  </div>
					    </td>
				      <td width="40%" class="tdfont">
					     <div align="center">
							<html:select name="certificateChecklistMasterFB" property="isCompulsory" styleClass="regcbo" tabindex="1" style="width:200px">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST %>">
								<html:options collection="<%=MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST %>" labelProperty="label" property="value"/>
								</logic:present>
							</html:select>
						  </div>
					    </td>
				      <td width="20%" class="tdfont">
					     <div align="center">
							<img class="button" src="/HIS/hisglobal/images/avai/plus.png" style="cursor: pointer;" onkeypress="if(event.keyCode==13) addChecklist();" onclick="addChecklist();" tabindex="1">
						  </div>
					    </td>
					 </tr>
					 
					 
					 <logic:present name="<%=MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST %>">
					 <logic:iterate id="checklistVO" name="<%=MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST %>" indexId="index" type="hisglobal.vo.MBCertificateChecklistVO">
					 	<tr>
					      	<td width="40%" class="tdfont">
						     <div align="center">
						     	<bean:write name="checklistVO" property="checklist"/>
						     </div>
						    </td>
					      	<td width="40%" class="tdfont">
						     <div align="center">
						     	<bean:write name="checklistVO" property="isCompulsoryLabel"/>
						     </div>
						    </td>
					      	<td width="20%" class="tdfont">
						     <div align="center">
						     	<img class="button" src="/HIS/hisglobal/images/avai/Minus.png" style="cursor: pointer;" onkeypress='if(event.keyCode==13) removeChecklist("<%=checklistVO.getChecklistID() %>")' onclick='removeChecklist("<%=checklistVO.getChecklistID() %>")' tabindex="1">
						     </div>
						    </td>
						</tr>     
					 </logic:iterate>
				  </logic:present>	
			  </table>
			</his:ContentTag>	    
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
<!--				 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer" onclick="finalSubmit('VIEW')" onkeypress="if(event.keyCode==13) finalSubmit('VIEW')">-->
				 
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
		<html:hidden name="certificateChecklistMasterFB" property="hmode"/>
		<html:hidden name="certificateChecklistMasterFB" property="mode"/>
		<html:hidden name="certificateChecklistMasterFB" property="checklistToRemove"/>
		<html:hidden name="certificateChecklistMasterFB" property="noOfChecklistAdded"/>
       </html:form>
       </his:TransactionContainer>
  </body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>

		     
		   
		  