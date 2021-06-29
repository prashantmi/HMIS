<%@page import="enquiry.*"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script>
function  closeWindow(){
	self.close();
}

</script>

	<body>
		<html:form action="/inPatientEnquiry">
		
			 <his:TitleTag name="Patient Detail">
				
			 </his:TitleTag>
			
			 <table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="17%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="patient"/><bean:message key="name"/></b></font>
					</div>
		  			</td>
					<td width="17%"  class="tdfont">
					<div align="left">	           
						&nbsp;<bean:write name="inPatientEnquiryFB" property="hrgstr_fname" />
							<bean:write name="inPatientEnquiryFB" property="hrgstr_mname" />
							<bean:write name="inPatientEnquiryFB" property="hrgstr_lname" />
		  			</td>
					<td width="17%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="crNo"/></b></font></div>
		  			</td>
					<td width="17%"  class="tdfont">
					<div align="left">	           
						&nbsp;<bean:write name="inPatientEnquiryFB" property="hrgnum_puk" />
		  			</td>
					</tr>
				</table>
			 <his:SubTitleTag name="OPD">
			 </his:SubTitleTag>
			  <his:statusTransactionInProcess>
				<his:ContentTag>
				<bean:define id="episodeEnquiryVO" name="<%=enquiryConfig.OPD_PATIENT_ENQUIRY_VO %>"/>
				
				<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="17%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="department"/></b></font></div>
		  			</td>
		  			<td width="17%" class="tdfont">
		  			<div align="left">
						&nbsp;<bean:write name="episodeEnquiryVO" property="gnum_dept_code"/>
		  			</div>
		  			</td>
		  			<td width="17%" class="tdfonthead">
		  			<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="unit"/></b></font>
		  			</div>
		  			</td>
		  			<td width="17%" class="tdfont">
		  			<div align="left">
						&nbsp;<bean:write name="episodeEnquiryVO" property="unit"/>
		  			</div>
		  			</td>
	  			</tr>
	  			<tr>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="room"/></b></font>
						</div>
		  			</td>
	  			
		  			<td width="17%"  class="tdfont">
						<div align="left">	           
							&nbsp;<bean:write name="episodeEnquiryVO" property="roomNo"/>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="lastVisitdate"/></b></font>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfont">
						<div align="left">	           
							&nbsp;<bean:write name="episodeEnquiryVO" property="lastVisitDate"/>
						</div>
		  			</td>
		  			
			  	</tr>
			</table>
			
		</his:ContentTag>
		</his:statusTransactionInProcess>
		 <his:SubTitleTag name="IPD">
		 </his:SubTitleTag>
		  	<his:statusTransactionInProcess>
				<his:ContentTag>
				
				<bean:define id="inPatientEnquiryVO" name="<%=enquiryConfig.IN_PATIENT_ENQUIRY_VO %>"/>
				 
				 <table  width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
					<td width="17%"  class="tdfonthead">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="addmissionNo"/></b></font></div>
		  			</td>
		  			<td width="17%" class="tdfont">
		  			<div align="left">
						&nbsp;<bean:write name="inPatientEnquiryVO" property="hipnum_admno"/>
		  			</div>
		  			</td>
		  			<td width="17%" class="tdfonthead">
		  			<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="admDate"/></b></font>
		  			</div>
		  			</td>
		  			<td width="17%" class="tdfont">
		  			<div align="left">
						&nbsp;<bean:write name="inPatientEnquiryVO" property="admDate"/>
		  			</div>
		  			</td>
	  			
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="dischargeDate"/></b></font>
						</div>
		  			</td>
	  			
		  			<td width="17%"  class="tdfont">
						<div align="left">	    
							&nbsp;<bean:write name="inPatientEnquiryVO" property="dischargeDate"/>       
						</div>
		  			</td>
		  			</tr>
	  				<tr>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="department"/></b></font>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfont">
						<div align="left">	  
							&nbsp;<bean:write name="inPatientEnquiryVO" property="dept"/>         
						</div>
		  			</td>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="unit"/></b></font>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfont">
						<div align="left">	    
							&nbsp;<bean:write name="inPatientEnquiryVO" property="unit"/>       
						</div>
		  			</td>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="ward"/>/Bed</b></font>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfont">
						<div align="left">	  
							&nbsp;<bean:write name="inPatientEnquiryVO" property="wardNo"/>/    
							<bean:write name="inPatientEnquiryVO" property="bedNo"/>     
						</div>
		  			</td>
		  			
			  		</tr>
	  				<tr>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="status"/></b></font>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfont">
						<div align="left">	
							&nbsp;<bean:write name="inPatientEnquiryVO" property="status"/>           
						</div>
		  			</td>
		  			<td width="17%"  class="tdfonthead">
						<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="referDetail"/></b></font>
						</div>
		  			</td>
		  			<td width="17%"  class="tdfont">
						<div align="left">	           
						</div>
		  			</td>
		  			<td width="17%"  class="tdfonthead">
						
		  			</td>
		  			<td width="17%"  class="tdfont">
						
		  			</td>
		  		</tr>
			</table>
			
			</his:ContentTag>
		 	</his:statusTransactionInProcess>
			 <his:ButtonToolBarTag>
		 		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) closeWindow();" onclick ="closeWindow();">
		 	</his:ButtonToolBarTag>
	 		<html:hidden name="inPatientEnquiryFB" property="hmode"/>
		</html:form>
	    <his:status/>
	</body>
</html>