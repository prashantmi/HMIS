<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
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

<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	checkOS();
	if(!getSelectedSoftwareId()){
		alert("Please Select a software");
		return false;
	}
	
	//return false;
	document.forms[0].submit();
}

function checkOS(){

	var OSName="Unknown OS";
	
	if (navigator.appVersion.indexOf("Win")!=-1) OSName="Windows";
	
	if (navigator.appVersion.indexOf("Linux")!=-1) OSName="Linux";

	document.getElementsByName("clientOS")[0].value=OSName;
	
	//alert("OSName"+OSName)	

}

function getSelectedSoftwareId(){
	var ids=document.getElementsByName("selectSofware");
	var selectedId;
	var valid=true;
	//alert("ids.length "+ids.length)
	for(var i=0;i<ids.length;i++){
		//alert(ids[i].checked)
		if(ids[i].checked){
			//alert("selected "+ids[i].value)
			selectedId=ids[i].value;
			valid=true;
			break;
		}
		else{
			valid=false;
		}
	}
	document.getElementsByName("selectedSoftwareId")[0].value=selectedId;
	return valid;
}
</script>
<his:TransactionContainer>
<body>
	<form action="<his:path src='/installSoftware/installSoftwareServlet'/>" method="post">
		<his:TitleTag name="Software Installation">
			</his:TitleTag>
			 <logic:present name="<%= Config.SOFTWARE_LIST_VO %>">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="10%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="softwareName"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="softwareDesc"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=Config.SOFTWARE_LIST_VO %>" id="softwareVO" type="hisglobal.utility.installSoftware.InstallSoftwareVO" indexId="index">
								<tr>
									<td width="10%" class="tdfont">
										<div align="center">
											<input type="radio" name="selectSofware" value="<%=softwareVO.getSoftwareId()+"#"+softwareVO.getFilePath()+"#"+softwareVO.getSoftwareName()%>"/>
										</div>
									</td>
									
									<td  width="25%" style="height: 20px" class="tdfont">
										<div align="center">
											<bean:write name="softwareVO" property="softwareName"/>										
										</div>
									</td>
									<td  width="25%" class="tdfont">
										<div align="center">
											<bean:write name="softwareVO" property="softwareDesc"/>	
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>		
					</his:ContentTag>
				</logic:present>
		
		
		<input type="hidden" name="hmode" />
		<input type="hidden" name="clientOS" />
		<input type="hidden" name="selectedSoftwareId" />
		
	<his:ButtonToolBarTag>
		
		<input type="button" value="Install" onclick="return submitPage('INSTALL')"/>
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('NEW')" onkeypress="if(event.keyCode==13)submitPage('NEW')">
	</his:ButtonToolBarTag>
	</form>
</body>
</his:TransactionContainer>
</html>