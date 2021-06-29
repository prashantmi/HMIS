<%--##		Modification Date		: 	16-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Modified By				:	AKASH SINGH
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.DeskMenuMasterVO"%>
<%@page import="opd.transaction.controller.util.GenericPatientProfileUTIL"%>
<%@page import="java.util.Iterator"%>
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TitleTag key="patientProfile">
</his:TitleTag>

<his:SubTitleTag key="selectToEnterParticularDetailInPatientProfile">
</his:SubTitleTag>


<his:statusNew>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<%
				GenericPatientProfileUTIL.ProfileProforma proforma = (GenericPatientProfileUTIL.ProfileProforma)session.getAttribute(OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT);
				Map map = proforma.mapProfileOptions;
				List lstMenus = (List)session.getAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
				Iterator lstMenusItr = lstMenus.iterator();
				while(lstMenusItr.hasNext())
				{
					DeskMenuMasterVO menu = (DeskMenuMasterVO)lstMenusItr.next();
					if(map.get(menu.getDeskMenuId())==null)
					{
			%>
				<tr>
					<td width="20%"  class="tdfont">
						<div align="right">
							<% 
								String onclick="selectProfileMenu(this,'"+menu.getDeskUrl()+"');";
								boolean radioDisbale = false;
								String fontColor = "green";
								if(menu.getIsValid()!=null && menu.getIsValid().trim().equals("0"))
								{
									radioDisbale = true;
									fontColor = "red";
								}
							%>
						    <% String menuname= menu.getDeskMenuName().trim();
						    System.out.println("makkkkkkk"+menuname);
							if(menuname.equalsIgnoreCase("Drug Allergies"))
								menuname="Allergies";
								%>
							 <html:radio name="GenericPatientProfileFB" property="selectedMenu" value="<%=menu.getDeskMenuId()%>" onclick="<%=onclick%>" title="<%=menu.getDeskMenuName()%>" tabindex="1" disabled="<%=radioDisbale%>"></html:radio> 
							
							&nbsp;
						</div>
	  				</td>  
					<td width="80%"  class="tdfont">
						<div align="left">	           
							<font color="<%=fontColor %>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
						<%-- 	&nbsp;<b> <%=menu.getDeskMenuName()%> </b> --%>
								&nbsp;<b> <%=menuname%> </b>
							</font>							
						</div>
		  				</td> 
				</tr>
			<%						
					}
				}
			%>
		</table>
	</his:ContentTag>
</his:statusNew>

<his:ButtonToolBarTag>
	<his:statusNew>	
		<img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) goForProfileMenu();" onclick="goForProfileMenu();" tabindex="1" />
	</his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')">
	<his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm21('PROFILEOPTIONS');">
		&nbsp;&nbsp;&nbsp;&nbsp;
  		<% String url="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPROFILE"; %>
  		<% String gurl="/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWGENRATEDPROFILE"; %>
		<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) viewProfile(event,'<%=url%>');" onclick="viewProfile(event,'<%=url%>');" tabindex="1" />
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitForm21('GENRATEPROFILE');" onclick="submitForm21('GENRATEPROFILE');" tabindex="1" />
		<!--  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateProfile(event,'<%=gurl%>');" onclick="generateProfile(event,'<%=gurl%>');" tabindex="1" />-->
	</his:statusNew>
	
	
	
</his:ButtonToolBarTag>

	<%-- <logic:iterate id="profileInvestigationVOs" name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEINVESTIGATIONVO_ARRAY%>" indexId="id" type="hisglobal.vo.ProfileInvestigationVO" >
	<html:hidden name="GenericPatientProfileFB" property="requisitionDNo" value="<%=profileInvestigationVOs.getReqDNo()%>"/>
	</logic:iterate> --%>

<html:hidden name="GenericPatientProfileFB" property="hmode" />
<html:hidden name="GenericPatientProfileFB" property="profileHTML" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="userLevel" />
<html:hidden name="GenericPatientProfileFB" property="remarks" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="isDesclaimerRequired" />

<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />
<html:hidden name="GenericPatientProfileFB" property="deskMenuName" />

<html:hidden name="GenericPatientProfileFB" property="reqDnoList"/>

<html:hidden name="GenericPatientProfileFB" property="strHiddenPatDtl"/>
<html:hidden name="GenericPatientProfileFB" property="requisitionDNo" />
<html:hidden name="GenericPatientProfileFB" property="reqDNo"/>
