<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.MrdMedicalCampFB"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date;"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
var selectedMedicalCamp=0;
function getSelectedCamp(obj)
{
	selectedMedicalCamp=0;
	selectedMedicalCamp= obj.value;
	document.getElementsByName("strCampId").value =selectedMedicalCamp;
}

function addCampDetail()
{
	document.getElementsByName("hmode")[0].value="ADDCAMPDETAIL"
	document.forms[0].submit();
}
function getCampDetail()
{
	if(selectedMedicalCamp != "0"){
	document.getElementsByName("hmode")[0].value="GETCAMPETAIL"
	document.getElementsByName("requestId")[0].value=selectedMedicalCamp;
	document.forms[0].submit();
	}
	else {alert("Please Select a Camp to View")}	
}

function modifyCampDetail()
{
	if((document.getElementsByName("strCampId").value==null) || (document.getElementsByName("strCampId").value==""))
	{
		alert("Please select a Camp !")	
		return false;
	}
	document.getElementsByName("hmode")[0].value="MODIFYCAMPDETAIL"
	document.forms[0].submit();
}
function viewCampDetail()
{
	document.getElementsByName("hmode")[0].value="VIEWCAMPDETAIL"
	document.forms[0].submit();
}
</script>
<body>
<%String sysdate=(String)WebUTIL.getSysdate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<his:TransactionContainer>
<html:form action="/medicalCamp">
	<his:TitleTag name="Medical Camp">
	</his:TitleTag>
	<%MrdMedicalCampFB fb=(MrdMedicalCampFB)pageContext.findAttribute("mrdMedicalCampFB"); %>
		<his:statusNew>
			<logic:present name="<%=MrdConfig.MRD_MEDICAL_CAMP_VO_LIST%>">
				<his:SubTitleTag name="Camp List">
				</his:SubTitleTag>
				<his:ContentTag>
		     		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="select"/></b>
								</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="campName"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="campVenue"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="campReqDate"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="campStartDate"/>
									</b>	
									</font>
								</div>
							</td>
						</tr>
					<logic:iterate id="mrdMedicalCampVO" name="<%=MrdConfig.MRD_MEDICAL_CAMP_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdMedicalCampDtlVO">
			 		 <tr>
						<td class="tdfontheadExam">
						<div align="center">
						
						<html:radio name="mrdMedicalCampFB" property="strCampId" value="<%=mrdMedicalCampVO.getStrCampId()%>" onclick="getSelectedCamp(this)" tabindex="1"/>
						</div>
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdMedicalCampVO" property="strCampName"/>
					     </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdMedicalCampVO" property="strCampVenue"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdMedicalCampVO" property="strCampRequisitionDate"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdMedicalCampVO" property="strCampStartDateTime"/>
					    	</font>
					     </div>   
						</td>
					 </tr>
              	</logic:iterate>
                </table>
            	</his:ContentTag>
			</logic:present>
		</his:statusNew>
		<his:ButtonToolBarTag>
			<his:statusNew>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer" tabindex="1" onclick =" addCampDetail()" onkeypress="if(event.keyCode==13)addCampDetail()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick =" modifyCampDetail()" onkeypress="if(event.keyCode==13)modifyCampDetail()">
				
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-view.png"/>'  style="cursor:pointer" tabindex="1" onclick="viewCampDetail()" onkeypress="if(event.keyCode==13)viewCampDetail()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusNew>
			
		</his:ButtonToolBarTag>

	<html:hidden name="mrdMedicalCampFB" property="hmode"/>
	<html:hidden name="mrdMedicalCampFB" property="strCampId"/>
	<input type="hidden" name="sysdate" value="<%=sysdate %>">	
</html:form>
<his:status/>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
