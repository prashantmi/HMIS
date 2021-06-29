<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_SampleCollectionVO"%>
<%@page import="new_investigation.transactions.controller.fb.SampleCollectionFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="new_investigation.transactions.controller.fb.SampleCollectionFB"%>

<%--
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
--%>



<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/drop.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />


<his:javascript src="/new_investigation/js/reportsValidation.js" />

<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script> -->

<!-- Added by Prashant -->
<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
 <script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />  
<!-- <script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
 -->
<!-- <script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script> -->
<!-- <script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>
 -->
<!-- <link href="media/dataTables/Responsive-2.2.2/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
<!-- <link href="media/dataTables/Buttons-1.5.6/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
 -->
<script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
 
 
<script type="text/javascript" src="media/misc/moment.min.js"></script>
<script type="text/javascript" src="media/misc/datetime-moment.js"></script>
<script src="media/common/js/commonDateOperations.js" type="text/javascript"></script>
<!-- Added by Prashant end-->

 
 
<style>
#blanket {
   background-color:#111;
   opacity: 0.65;
   *background:none;
   position:absolute;
   z-index: 9001;
   top:0px;
   left:0px;
   width:100%;
}

#popUpDiv {
	position:absolute;
	  
	background:#CCE6FF;
	width:400px;
	height:250px;
	border:5px solid #000;
	z-index: 9002;
}

#popUpDiv a {position:relative; top:20px; left:20px}</style> 

<script type="text/javascript">


function refreshh()
{

	document.getElementsByName("hmode")[0].value="NEW";
	document.getElementsByName("duplicateBarcodeGeneration")[0].value="";
	document.getElementsByName("isSampleAreaSelected")[0].value="0";
	
	document.forms[0].submit();
}

function showduplicatebarcode()
{

	document.getElementById('goButton').style.display="";
	document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
	document.getElementsByName("isSampleAreaSelected")[0].value="1";
	
	document.getElementsByName("duplicateBarcodeGeneration")[0].value="0";
	
	document.getElementsByName("hmode")[0].value="NEWDUP";
	document.forms[0].submit();
	
	}




//ashu

function barcodewissearch(obj,hoscode)
{	
	//alert(hoscode);
	//alert(obj.value);
	var val=obj.value;

	if(val.length==15)
		{

		 if(document.getElementsByName("duplicateBarcodeGeneration")[0].checked==true)
         {
			 onClickGO(hoscode,"0");
         }
		 else
			 {
		
	onClickGO(hoscode,"1");
			 }
		 }
	else
		{
alert("Please Enter Valid CrNO");
return null;
		}
}


function barcodewissearchonpaste(obj,hoscode)
{	

	var val="";
	setTimeout(function(){
		 val=obj.value;
	
    
	//alert(hoscode);
	//alert(val);
	

	if(val.length==15)
		{

		 if(document.getElementsByName("duplicateBarcodeGeneration")[0].checked==true)
         {
			// onClickGO(hoscode,"0");
         }
		 else
			 {
		
	// onClickGO(hoscode,"1");
			 }
		 }
	else
		{
alert("Please Enter Valid CrNO");
return null;
		}
	}, 0);
	 
}

</script>

<% String strdivage="\"\"";
String strdivdob="\"\""; %>

 <%
 
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String frDate="";
              String tDate="";
               
         %>
         



<body onload="callThisOnload();">

<html:form action="/sampleCollection">


  
 <his:TitleTag name="Sample Collection"> 	 
 
 <logic:notEqual name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
  <logic:notEqual name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
 	<div style="margin-right:100"><input type="radio" name="duplicateBarcodeGeneration" value=""  onclick="showduplicatebarcode()" ><span style="color:#ffff00;font-size:12" >Duplicate Barcode Printing</span> </div>
 	
	</logic:notEqual>
	</logic:notEqual>

				
<logic:equal name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
  
  <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
 <div style="margin-right:100">	<input type="radio" checked name="duplicateBarcodeGeneration" value=""  onclick="showduplicatebarcode()" ><span color="black"><span style="color:#ffff00;font-size:12" >Duplicate Barcode Printing</span> <input type="radio"  name="refreshhh" value=""  onclick="refreshh()" ><span color="black"><span style="color:#ffff00;font-size:12" >Sample Collection</span></div>
</logic:equal>
 
 <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="0">
 	<div style="margin-right:100"><input type="radio" checked name="duplicateBarcodeGeneration" value=""  onclick="showduplicatebarcode()" ><span style="color:#ffff00;font-size:12" >Duplicate Barcode Printing</span>  <input type="radio"  name="refreshhh" value=""  onclick="refreshh()" ><span color="black"><span style="color:#ffff00;font-size:12" >Sample Collection</span> </div>
</logic:equal>

</logic:equal>
	 	
	 			
 <%--  <his:insertDateTag/> --%>
  </his:TitleTag> 
  
  
<%
		 String mobileNo="";
  		 String emailId="";
  		String Patname="";
  		String patAddress="";
  		
  		//Date dtIn="";
  		if(session.getAttribute(Config.SELECTED_FROM_DATE) !=null)
  		 	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SELECTED_FROM_DATE), "dd-MMM-yyyy");
  			
  		if(session.getAttribute(Config.SELECTED_TO_DATE) !=null)
  	  		tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SELECTED_TO_DATE), "dd-MMM-yyyy");
  		
  		if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	if(dt==null)
        	{
        		dt=new Date();
        		session.setAttribute(Config.SYSDATEOBJECT,dt);
        	
        	}
        	
        	
        	//frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
        	 Calendar cal = Calendar.getInstance();
        	cal.setTime(dt);
        	cal.add(Calendar.DATE, -1);
        	Date dateBefore7Days = cal.getTime();
        	frDate = WebUTIL.getCustomisedSysDate(dateBefore7Days, "dd-MMM-yyyy");
        	System.out.println("From Date, 7 days before current date   : "+frDate); 
         }
  		
  		
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
		  
    	%>  
 
 
 
  
 	<logic:equal name="SampleCollectionFB" property="showStatus" value="0">
		  <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="16">
		   <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="10">
 		<his:ContentTag>
	 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<table width="100%" >
				 		
				 			<tr>
					 			<td class="tdfont" width="25%">
					 				<logic:notEqual name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
					 				<div align="right">
					 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:message key="sampleColl"/>&nbsp;
													 </font>
					 					
					 				</div>
					 				</logic:notEqual>
					 			</td>
					 			<td class="tdfont" width="25%">
					 				
					 				<logic:notEqual name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
					 				<logic:notEqual name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
						 				  <span class="custom-dropdown small">
						 				<html:select    name="SampleCollectionFB" property="sampleAreaCode" tabindex="1"  onchange="showSearchDiv(this)">					
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
												<%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
												<%} %>
 											 
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
										</span>
									</logic:notEqual>
									 <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
									 	<div align="left">
					 					  <bean:write name="SampleCollectionFB" property="sampleAreaName"/>
					 					  <html:hidden name="SampleCollectionFB" property="sampleAreaCode"/>
					 					</div>
									 </logic:equal>
									 </logic:notEqual>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfont" width="25%"></td>
				 			 </tr>	
				 			 </table>
				 			 <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
						 			 <table width="100%" >
						 			   <%
			    
							      UserVO uservo=ControllerUTIL.getUserVO(request);
						 			  Date todayDateobj = new Date();
						 			   SimpleDateFormat dateob = new SimpleDateFormat("yy");
										String strDate= dateob.format(todayDateobj);
								      String hospitalCode=uservo.getHospitalCode();
								      String val=uservo.getHospitalCode()+strDate;
							      
			                           %>
			                           
			                           
				 		
				 		
				 		
						 			  		<tr>
										    <td width="25%" class="tdfont">
										        <div align="right">
										             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
														 
													 </font> 
													 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:message key="crNO"/>&nbsp;
													 </font>
											     </div>
										      </td>
										      <td width="25%" class="tdfont">
										      <div align="left">                
										      <%
										      UserVO uservo11=ControllerUTIL.getUserVO(request);
										      String hospitalCodebarcode=uservo11.getHospitalCode();
									     	
										      
										      %>                                                                                           
											 <input type="text" id="barcodecrno" class="textBoxCss" name="tempPatCRNo"  placeholder="<%=val %>" maxlength="15" size="20" onpaste="barcodewissearchonpaste(this,'<%=hospitalCodebarcode%>')" onkeypress="if(event.keyCode==13) barcodewissearch(this,'<%=hospitalCodebarcode%>')" tabindex="1" />
											  </div>
										     </td>
										     <td width="25%" class="tdfont">
										         
										      </td>
										      <td width="25%" class="tdfont">
										       
					 				<div align="left">
					 			<!-- 	<input type="radio" name="duplicateBarcodeGeneration" value="0"  >Duplicate Barcode Print
					 			 -->		
					 				</div>
					 			
										     </td>
										     </tr>
						 			 
											<tr>            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' class="ftdates" style='<%=fromDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="fromDate"/>
														</font>
									        		</div>
									        		
												</td>
												<td class="tdfont" width="25%">
										    		<div id='divfromDateControl' class="ftdates" style='<%=fromDateControl %>'align="left">	               		 
														<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
													</div>
											 		
												</td>
									 		            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' class="ftdates" style='<%=toDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="toDate"/>
														</font>
									        		</div>
												</td>
												
												<td class="tdfont" width="25%">
										    		<div id='divfromDateControl' class="ftdates" style='<%=toDateControl %>'align="left">	               		 
														<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
													</div>
											 		
												</td>
								 		</tr>
								</table>
						</logic:equal>
				</logic:present>
		</his:ContentTag>
		</logic:notEqual>
		</logic:notEqual>
			</logic:equal>
			
			
			<logic:equal name="SampleCollectionFB" property="showStatus" value="3">
		  <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="16">
		   <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="10">
 		<his:ContentTag>
 		
 		<%	
 		
 	 String ttdate=(String )request.getSession().getAttribute("todate_dup");
 		String fdatee=(String )request.getSession().getAttribute("fromdate_dup");
	    /*  Date tDatee = new SimpleDateFormat("dd-MMM-YYYY").parse(ttdate);
 		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");  
 		String strDate1 = dateFormat.format(tDatee);   */
 		tDate=ttdate;
 		frDate=fdatee;
		  	%>  	
	 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<table width="100%" >
				 		
				 			<tr>
					 			<td class="tdfont" width="25%">
					 				<logic:notEqual name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
					 				<div align="right">
					 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
													<bean:message key="sampleColl"/>&nbsp;
													 </font>
					 					
					 				</div>
					 				</logic:notEqual>
					 			</td>
					 			<td class="tdfont" width="25%">
					 				
					 				<logic:notEqual name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
					 				<logic:notEqual name="SampleCollectionFB" property="isSampleAreaSelected" value="0">
						 				  <span class="custom-dropdown small">
						 				<html:select    name="SampleCollectionFB" property="sampleAreaCode" tabindex="1"  onchange="showSearchDiv(this)">					
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
												<%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
												<%} %>
 											 
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
										</span>
									</logic:notEqual>
									 <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="0">
									 	<div align="left">
					 					  <bean:write name="SampleCollectionFB" property="sampleAreaName"/>
					 					  <html:hidden name="SampleCollectionFB" property="sampleAreaCode"/>
					 					</div>
									 </logic:equal>
									 </logic:notEqual>
					 			</td>
					 			<td class="tdfont" width="25%"></td>
					 			<td class="tdfont" width="25%"></td>
				 			 </tr>	
				 			 </table>
				 			 <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="0">
						 			 <table width="100%" >
						 			   <%
			    
							      UserVO uservo=ControllerUTIL.getUserVO(request);
						 			  Date todayDateobj = new Date();
						 			   SimpleDateFormat dateob = new SimpleDateFormat("yy");
										String strDate= dateob.format(todayDateobj);
								      String hospitalCode=uservo.getHospitalCode();
								      String val=uservo.getHospitalCode()+strDate;
							      
			                           %>
			                           
			                           
				 		
				 		
				 		
						 			  		<tr>
										    <td width="25%" class="tdfont">
										        <div align="right">
										             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
														 
													 </font> 
													 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
														<bean:message key="crNO"/>&nbsp;
													 </font>
											     </div>
										      </td>
										      <td width="25%" class="tdfont">
										      <div align="left">                
										      <%
										      UserVO uservo11=ControllerUTIL.getUserVO(request);
										      String hospitalCodebarcode=uservo11.getHospitalCode();
									     	
										      
										      %>                                                                                           
											 <input type="text" id="barcodecrno" class="textBoxCss" name="tempPatCRNo"  placeholder="<%=val %>" maxlength="15" size="20" onpaste="barcodewissearchonpaste(this,'<%=hospitalCodebarcode%>')" onkeypress="if(event.keyCode==13) barcodewissearch(this,'<%=hospitalCodebarcode%>')" tabindex="1" />
											  </div>
										     </td>
										     <td width="25%" class="tdfont">
										         
										      </td>
										      <td width="25%" class="tdfont">
										       
					 				<div align="left">
					 			<!-- 	<input type="radio" name="duplicateBarcodeGeneration" value="0"  >Duplicate Barcode Print
					 			 -->		
					 				</div>
					 			
										     </td>
										     </tr>
						 			 
											<tr>            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' class="ftdates" style='<%=fromDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="fromDate"/>
														</font>
									        		</div>
									        		
												</td>
												<td class="tdfont" width="25%">
										    		<div id='divfromDateControl' class="ftdates" style='<%=fromDateControl %>'align="left">	               		 
														<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
													</div>
											 		
												</td>
									 		            
									 			<td class="tdfont" width="25%">
									        		<div id='divfromDate' class="ftdates" style='<%=toDateLabel %>' align="right">
										        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
										        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<bean:message key="toDate"/>
														</font>
									        		</div>
												</td>
												
												<td class="tdfont" width="25%">
										    		<div id='divfromDateControl' class="ftdates" style='<%=toDateControl %>'align="left">	               		 
														<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
													</div>
											 		
												</td>
								 		</tr>
								</table>
								
								<his:ButtonToolBarTag>
			
			    	
				     	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>','0')" onclick="onClickGO('<%=hospitalCode%>','0')" tabindex="1">
				    
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">
				     
				    
			
			</his:ButtonToolBarTag>
			
						</logic:equal>
				</logic:present>
		</his:ContentTag>
		
		
		
			
			
		</logic:notEqual>
		</logic:notEqual>
			</logic:equal>
	<his:statusTransactionInProcess>
		<his:ContentTag>
		<logic:equal name="SampleCollectionFB" property="showStatus" value="0">
		
		   <logic:equal name="SampleCollectionFB" property="flagforipddesk" value="10">	
		<logic:equal name="SampleCollectionFB" property="sampleAreaCode" value="">
		<table width="100%">
		<tr>
		<td>
		<font color="red" ><strong><b>Please Configure Collection Area for the respective ward</b></strong></font>
		</td>
		</tr>
		</table>
		</logic:equal>		 
		</logic:equal>
		</logic:equal>
			 
	<logic:equal name="SampleCollectionFB" property="showStatus" value="0">
	  <logic:notEqual name="SampleCollectionFB" property="sampleAreaCode" value="">
				 		<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
		 		
			<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO %>">
			<div align="center">
			
			     <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
				     	<logic:notEqual name="SampleCollectionFB" property="showStatus" value="1">
				     	<%
				     	
				     	UserVO uservo=ControllerUTIL.getUserVO(request);
					      String hospitalCode=uservo.getHospitalCode();
				     	%>
				    	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>','0')" onclick="onClickGO('<%=hospitalCode%>','0')" tabindex="1">
				    	</logic:notEqual>
				    </logic:equal>
			
			<!--start-- back button when multiple sample collection areas present and you have to go back to combo list -->
				 						 <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="10">
				 		
				 		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>">
				 		<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
						<%if(patSampleCollection.size()>1){ %>
									<img class="button" src='<his:path src="/hisglobal/images/back_tab.png"/>' id="saveDiv"  tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
						<%} %>
						</logic:present>
						</logic:notEqual>
						
			<!--end--   back button when multiple sample collection areas present and you have to go back to combo list -->
			</div>
			
			<his:SubTitleTag name="Sample To Be Collected Details"> </his:SubTitleTag>
  			<table id="DataTable1" class="table display" style="width:100%">
			<!-- <table   width= "100%" bgcolor="#EBEBEB"  > -->
			<thead>
				<tr>
			
					<%-- <td width="5%" align="left">	
					<b>	<font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="select"/></font></b>
					</th> --%>
					
					
						 <th align="left" width ="5%">
				 			    <div align="left"> <input type="checkbox" name="allSelectLab" onChange="validateSameCrNoAllSelected(this)" tabindex="1" > </div>
				 			    </th>
				 		
				 		<th width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Request No.</font></b>
					</th>	    
					
					<th width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="crNO"/></font></b>
					</th>
					
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patientName"/></font></b>
					</th>
					
					<%-- <th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqNo"/></font></b>
					</th> --%>
					<th width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="labName"/></font>
						</b>	
					</th>
					<th width="10%" align="left">
						<b> 
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqDate"/></font>
					     </b>
					</th>	
						
					<th width="5%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="age" /></font></b>
					</th>
					<th width="5%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patStatus"/></font></b>
					</th> 
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="departmentunit"/></font></b>
					</th>
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="visitDate"/></font></b>
					</th>
					
					<th width="5%" align="left">
						<b><font color="#000000" size="2"  face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="priority"/></font></b>
					</th>
			
				</tr>
			 	 </thead>
			
			<logic:notEmpty name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO %>">
			 		 <tbody>
					<%
					 List<Inv_SampleCollectionVO> lstSamCVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO);
			 		
					 int  size=0;
			 		 
			 		if(lstSamCVO!=null && lstSamCVO.size()>0 )
			 			size=lstSamCVO.size();
					/* int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					   int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
	 		
					 	for(int j=startIndex;j<=endIndex;j++) {
						//int i=j-startIndex;
						 
					 	if(j<size){
						
						 Inv_SampleCollectionVO voSam=lstSamCVO.get(j); */
						int j=0;
					for (Inv_SampleCollectionVO voSam : lstSamCVO ){
						j++;
					
					String chkVal=voSam.getPatCRNo()+"#"+voSam.getRequisitionNo()+"#"+voSam.getLabCode()+"#"+voSam.getSampleCode()+"#"+j;
					%>
					
					<tr>
					
						<td width="5%" align="left" id="chkId">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" name="chkSamplePatient" value='<%=chkVal%>' onclick="ValidateSameCrNo(this)" tabindex="1">
							</font>
						</td>
						<td width="15%" align="left" id="reqId">
						 <div align="left">
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionNo() %></font> 
						 </div>
				  		</td>
						<td width="10%" align="left">
						 <div align="left">
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatCRNo() %></font> 
						 </div>
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatName() %></font>
						 </div>
				  		</td>
				  		
				  		<%-- <td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionNo() %></font>
						 </div>
				  		
				  		</td> --%>
				  		<td width="15%" align="left">
				  		 
				  		
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getLabName() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionDate() %></font>
						 </div>
				  		
				  		</td>
				  		
				  		<td width="5%" align="left">
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getpatAge() %></font>
						 </div>
				  		</td>
				  		<td width="5%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatStatus()%></font>
						 </div>
				  		</td>
			  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatDeptName() %>-<%=voSam.getPatUnitName() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatVisitDate()%></font>
						 </div>
				  		</td>
				  		
				  		<td width="5%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
<%=voSam.getPriorityOfReq()==null?"Normal":voSam.getPriorityOfReq().contains("Confidential")?"Confidential":voSam.getPriorityOfReq().contains("Urgent")?"Urgent":"Normal" %></font>
						 </div>
				  		</td>
					</tr>
					
					<%-- <%}  }%> --%>
					<%  }%>
					</tbody>
			</logic:notEmpty>
			
			
			
				 <%-- <logic:empty name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO%>">
				
				<center>
				<font color="red" size="4">
				<bean:message key="noSampleColl"/> </font></center>
			</logic:empty> --%>
			
			</table>
			
			</logic:present>
			</logic:notEqual>
			</logic:equal>
			
			
			
			
			
			</his:ContentTag>
			 <%-- <%
				//Pagination Logic
					PaginationFB fbPage= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage);
					fbPage.setCurrentPage(((SampleCollectionFB)request.getAttribute("SampleCollectionFB")).getCurrentPage());
					fbPage.setObjArrName(InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE);
					fbPage.setAppendInTitle("List ");
					int maxRecord=10;
					fbPage.setMaxRecords(maxRecord);
				 
				 %> --%>
			
				<!-- added by chandan -->
			<logic:equal name="SampleCollectionFB" property="showStatus" value="3">
			  <logic:notEqual name="SampleCollectionFB" property="sampleAreaCode" value="">
				 		<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
		 		
			<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE %>">
			<div align="center">
			
			     
			
			<!--start-- back button when multiple sample collection areas present and you have to go back to combo list -->
				 		
			<!--end--   back button when multiple sample collection areas present and you have to go back to combo list -->
			</div>
			
		
			
			<his:SubTitleTag name="Sample Collected Details"></
  			</his:SubTitleTag>
  			<table id="DataTable2" class="table display" style="width:100%">
			<!-- <table   width= "100%" bgcolor="#EBEBEB"  > -->
			<thead>
				<tr>
			
					<%-- <td width="5%" align="left">	
					<b>	<font color="#000000" size="2" 
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="select"/></font></b>
					</th> --%>
					
					
						 <th align="left" width ="5%">
				 			    <div align="left"> <b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Select</font></b> </div>
				 			    </th>
				 			    
					
					<th width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="crNO"/></font></b>
					</th>
					
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patientName"/></font></b>
					</th>
					
					<%-- <th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqNo"/></font></b>
					</th> --%>
					<th width="15%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="labName"/></font>
						</b>	
					</th>
					
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Sample No</font>
						</b>	
					</th>
					<th width="10%" align="left">
						<b> 
						<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="reqDate"/></font>
					     </b>
					</th>	
						
					<th width="5%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="age" /></font></b>
					</th>
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="patStatus"/></font></b>
					</th> 
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="departmentunit"/></font></b>
					</th>
					<th width="10%" align="left">
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="visitDate"/></font></b>
					</th>
			
				</tr>
				
				</thead>
				
				<tbody>
			 	 <%--  <%
				//Pagination Logic
					PaginationFB fbPage1= new PaginationFB();
					pageContext.setAttribute("fbPagination",fbPage1);
					fbPage1.setCurrentPage(((SampleCollectionFB)request.getAttribute("SampleCollectionFB")).getCurrentPage());
					fbPage1.setObjArrName(InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE);
					fbPage1.setAppendInTitle("List ");
					int maxRecord1=25;
					fbPage1.setMaxRecords(maxRecord1);
				 
				 %> --%>
			
			
			
			<logic:notEmpty name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE %>">
			 		
			 		
					<%
					 List<Inv_SampleCollectionVO> lstSamCVO=(List<Inv_SampleCollectionVO>)session.getAttribute(InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE);
			 		
					 int  size=0;
			 		 
			 		if(lstSamCVO!=null && lstSamCVO.size()>0 )
			 			size=lstSamCVO.size();
					
			 		/* int startIndex1=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex1=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
	 		
					for(int j=startIndex1;j<=endIndex1;j++)
					{
						//int i=j-startIndex;
						 
					if(j<size){
						Inv_SampleCollectionVO voSam=lstSamCVO.get(j); */
						
						int j=0;
						for(Inv_SampleCollectionVO voSam : lstSamCVO){
						 j++;
						
						String chkVal="";
						if(voSam.getSugarTestCode()!=null && !voSam.getSugarTestCode().equals(""))
						{ chkVal=voSam.getPatCRNo()+"#"+voSam.getSampleName()+"#"+voSam.getTempSampleNo()+"#"+voSam.getSampleNo()+"#"+voSam.getLabName()+"#"+voSam.getRequisitionDate()+"#"+voSam.getSampleCollectionDate()+"#"+voSam.getPatName()+"#"+(voSam.getSugarTestCode()==null?"":voSam.getSugarTestCode())+"#";
						}else
						{	 chkVal=voSam.getPatCRNo()+"#"+voSam.getSampleName()+"#"+voSam.getTempSampleNo()+"#"+voSam.getSampleNo()+"#"+voSam.getLabName()+"#"+voSam.getRequisitionDate()+"#"+voSam.getSampleCollectionDate()+"#"+voSam.getPatName()+"#";		
						}
						
						String chkId="chkId_"+j;
					System.out.println("chk is "+chkId);
						%>	
					
					<tr>
					
						<td width="5%" align="left">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<input type="checkbox" name="chkSamplePatient" value='<%=chkVal%>' 
							  tabindex="1">
							</font>
						</td>
						<td width="15%" align="left">
						 <div align="left">
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatCRNo() %></font> 
						 </div>
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatName() %></font>
						 </div>
				  		</td>
				  		
				  		<%-- <td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionNo() %></font>
						 </div>
				  		
				  		</td> --%>
				  		<td width="15%" align="left">
				  		 
				  		
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getLabName() %></font>
						 </div>
				  		</td>
				  		
				  		<td width="10%" align="left">
				  		 
				  		
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getTempSampleNo()+(voSam.getSugarTestCode()==null?"":voSam.getSugarTestCode()) %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getRequisitionDate() %></font>
						 </div>
				  		
				  		</td>
				  		
				  		<td width="5%" align="left">
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getpatAge() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatStatus()%></font>
						 </div>
				  		</td>
			  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatDeptName() %>-<%=voSam.getPatUnitName() %></font>
						 </div>
				  		</td>
				  		<td width="10%" align="left">
				  		 
				  		<div align="left">
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voSam.getPatVisitDate()%></font>
						 </div>
				  		</td>
					</tr>
					
					<%-- <%}  }%> --%>
					 <%} %>
			


			
			</logic:notEmpty>
			
				</tbody>
			 </table>
			 
			<his:ButtonToolBarTag>
								<%	String val="NEW"; %>
	
     		<img class="button"
										src='<his:path src="/hisglobal/images/GO.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) barocdeDetails(this)"
										onclick="barocdeDetails(this)" tabindex="1">
			   
	             
		  
     		<img class="button"
										src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob"
										style="cursor: pointer"
										onkeypress="if(event.keyCode==13) submitForm('<%=val %>')"
										onclick="submitForm('<%=val %>')" tabindex="1">
			   
	             </his:ButtonToolBarTag>
	           
			</logic:present>
			</logic:notEqual>
			</logic:equal>
			
			
				 <%-- <logic:empty name="<%=InvestigationConfig.LIST_SAMPLE_PATIENT_VO_BARCODE%>">
				
				<center>
				<font color="red" size="4">
				<bean:message key="noSampleColl"/> </font></center>
			</logic:empty> --%>
			
			<!-- New Logic -->
			<logic:present name="<%=InvestigationConfig.SELECTED_PAT_DETAILS %>">
			  <logic:notEqual name="SampleCollectionFB" property="sampleAreaCode" value="">
			<his:ContentTag>
			<his:SubTitleTag name="Patient Details"></
  			</his:SubTitleTag>
			<%
			Inv_SampleCollectionVO voSampleCollection= (Inv_SampleCollectionVO)session.getAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
			
			if(voSampleCollection.getPatStatus()!=null)
			{
			%>
		
			
			<div id="ipddDiv">
			<table width="100%">
			<tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					<bean:message key="crNO"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%"> 
		 				<div align="left">
		 					<%=voSampleCollection.getPatCRNo() %>
		 				</div>
		 			</td>
		 			 <td class="tdfont" width="25%">
		 				<div align="right">
		 				<bean:message key="patientName"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 					<%=voSampleCollection.getPatName() %>
		 				</div>
		 			</td> 
		 			
			</tr>
			 <tr>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 				<bean:message key="patStatus"/>&nbsp;
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 					<%=voSampleCollection.getPatStatus() %>
		 				</div>
		 			</td>
		 			<td class="tdfont" width="25%">
		 				<div align="right">
		 					
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				
		 				</div>
		 			</td>
			</tr>
			<%
				String staffImage = (String) session.getAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE);
				System.out.println("staffImage jsp : "+staffImage);
				
			
			if(staffImage != "" || staffImage.equals("0"))
			{
			%>
			
			 <tr>
			 		<td class="tdfont" width="25%">
		 				<div align="right">
		 					Staff Dependent's Photo
		 				</div>
		 			</td>
		 			<td class="tdfonthead" width="25%">
		 				<div align="left">
		 				<img id="imageid"  src="data:image/png;base64,<%=staffImage%>"  style="width:100px; height:100px;" border="1" >
		 				</div>
		 			</td>
			</tr>
			 <%} 
			staffImage="";
			%>
		
		</table>
		
		</div>
			
			
			
			<%  mobileNo=(voSampleCollection.getPatMobile()==null?"":voSampleCollection.getPatMobile());
			 	emailId=(voSampleCollection.getPatEmail()==null?"":voSampleCollection.getPatEmail());
			 	Patname=(voSampleCollection.getPatName()==null?"":voSampleCollection.getPatName());
			 	patAddress=(voSampleCollection.getPatAddress()==null?"":voSampleCollection.getPatAddress());
			if(voSampleCollection.getPatStatus().equals("IPD"))
			{
			%>
		
	
			<his:SubTitleTag name="Ipd Details"></
  			</his:SubTitleTag>
			<div id="ipddDiv">
				<table width="100%">
				<tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="admitdept"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%"> 
			 				<div align="left">
			 					<%=voSampleCollection.getPatDeptName() %>
			 				</div>
			 			</td>
			 			 <td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="wardName"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatWardName() %>
			 				</div>
			 			</td> 
				</tr>
				 <tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 				<bean:message key="roomName"/>
			 							 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatRoomName()%>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 				<bean:message key="bedName"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatBedName()==null?"":voSampleCollection.getPatBedName()) %>
			 				</div>
			 			</td>
				</tr>
				<tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 				<bean:message key="consultant"/>
			 							 				</div> 
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatOrderByDoc()==null?"":voSampleCollection.getPatOrderByDoc()) %>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%"></td>
			 			<td class="tdfont" width="25%"></td>
			 		 <td class="tdfonthead" width="25%">
			 				<div align="right">
			 				 
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					 
			 				</div>
			 			</td> 
				</tr> 
			
			</table>
			
			</div>
			<%}  %>
			<% 
			if(voSampleCollection.getPatStatus().equals("OPD"))
			{
			%>
			<his:SubTitleTag name="Opd Details"></
  			</his:SubTitleTag>
			 <div id="opdEmerencyDIV">
			 	<table width="100%">
				<tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="departmentunit"/>&nbsp;
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%"> 
			 				<div align="left">
			 					<%=(voSampleCollection.getPatDeptName()==null?"":voSampleCollection.getPatDeptName())+"/"+voSampleCollection.getPatUnitName() %>
			 				</div>
			 			</td>
			 			 <td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="consultant"/>&nbsp;
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatOrderByDoc()==null?"":voSampleCollection.getPatOrderByDoc()) %>
			 				</div>
			 			</td> 
				</tr>
				 <tr>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 				<bean:message key="visitDate"/>&nbsp;
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=voSampleCollection.getPatVisitDate()%>
			 				</div>
			 			</td>
			 			<td class="tdfont" width="25%">
			 				<div align="right">
			 					<bean:message key="patCat"/>
			 				</div>
			 			</td>
			 			<td class="tdfonthead" width="25%">
			 				<div align="left">
			 					<%=(voSampleCollection.getPatCategory()==null?"":voSampleCollection.getPatCategory()) %>
			 				</div>
			 			</td>
				</tr>
				 
			
			</table>
			 </div>
			<%} %>
			<%} %>
			</his:ContentTag>
		</logic:notEqual>
		</logic:present>
		
		
		
		
		
		
		
			 <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="16">
			  <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="10">
		<logic:notEqual name="SampleCollectionFB" property="showStatus" value="0">
		<logic:notEqual name="SampleCollectionFB" property="showStatus" value="3">
		
		<his:ContentTag>
			<table width="100%">
				<tr>
					<td class="tdfont" width="25%">
						<div align="right">
						<bean:message key="mobile"/>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="left">
						<input type="text" class="textBoxCss" name="patMobile"   maxlength="10" size="15" value="<%=mobileNo %>" onkeypress="return validateNumeric(event)" tabindex="1" />
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="right">
							<bean:message key="mail"/>
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="left">
						<input type="text" class="textBoxCss" name="patEmail"   maxlength="40" size="40" value="<%=emailId %>" onkeypress="return validateEmai(this)" tabindex="1" />
						<html:hidden name="SampleCollectionFB" property="patName"  value="<%=Patname %>"/>
						</div>
					</td>
					 
					 
			</tr>
			 
			
			</table>
			</his:ContentTag>
		</logic:notEqual>
		    
		    <!-- chandann -->
		    				
		    
		    	<logic:notEqual name="SampleCollectionFB" property="statuschange" value="1">
		     <div class="subDivStyle">
                                <his:SubTitleTag name="Requisition Forms"> 
											<img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="reqformshow"      tabindex="1" onclick ="f1()" > 
										<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="reqformhide"      tabindex="1" onclick ="f2()" >
											 </his:SubTitleTag>                 
                 </div>
                           <div class="subDivStyle" id="reqformss" style="display: none">
                               <table width="100%" border="1" id="tbll">
                               <tr>
                       <td style="font-weight: bold;color: brown;">TEST NAME</td>
                      <td style="font-weight: bold;color: brown;">REQUISITION FORMS</td>
                      </tr>
                               </table>                 
                 </div>
                 </logic:notEqual>
		</logic:notEqual>
		</logic:notEqual>
		</logic:notEqual>
		
		<!-- chandan -->
		 
                       
                 
 		
 			<logic:notEmpty name="<%=InvestigationConfig.MAP_PAT_SAMPLE_BILLED %>">
 		<!--  Billed Details -->
	 		<logic:present name="<%=InvestigationConfig.MAP_PAT_SAMPLE_BILLED %>">
 		<his:ContentTag>
 		<his:SubTitleTag name="Billed Details"></
  			</his:SubTitleTag>
	 		<table width="100%" >
	 			<tr >
	 						<%-- 	<td class="tdfont" >
					 				<div align="left">
					 					<bean:message key="select"/>&nbsp;
					 				</div>
					 			</td> --%>
					 			
					 			 <td class="tdfont" >
				 			    <div align="right"> <input type="checkbox" name="allSelectLab" onChange="onSelectAll(this)" > </div>
				 			    </td>
				 			    
				 			    
					 			<td class="tdfont" >
					 				<div align="left">
					 					 <bean:message key="labName"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 				<div align="left">
					 				<bean:message key="TestName"/>&nbsp;
					 				</div>
					 			</td>
					 		<%-- 	<td class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="reqNo"/>&nbsp; 
					 				</div>
					 			</td> --%>
					 			<td class="tdfont" >
					 			<div align="left">
					 				<bean:message key="reqDate"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 					<bean:message key="specimen"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">		 				
								 <bean:message key="sampleNo"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 				<bean:message key="collVoll"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 					<bean:message key="uom"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 					<bean:message key="container"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 				<bean:message key="priority"/>
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 				Machine
					 				</div>
					 			</td>
				 		<!-- <td class="tdfont" >
					 			<div align="left">
					 				Instruction
					 				</div>
					 			</td> -->
				 			 </tr>
	 		<%
	 		Map<String,List<Inv_SampleCollectionVO>> mpBilled= (Map<String,List<Inv_SampleCollectionVO>>)session.getAttribute(InvestigationConfig.MAP_PAT_SAMPLE_BILLED);
	 		Iterator itr=mpBilled.keySet().iterator();
	 		int index=0;
	 		boolean sameReqNo=false;
	 		int j=0;
	 		while(itr.hasNext())
	 		{
	 			
	 			boolean firstTimeTravesa=true;
	 			String hashBasedValue=(String)itr.next();
	 			
	 			String[] arrHashBasedValue=hashBasedValue.split("#");
	 			
	 			String reqNo=arrHashBasedValue[0];
	 			String labCode=arrHashBasedValue[1];
	 			String sampleCode=arrHashBasedValue[2];
	 			 String reqNoAndSampleCode=reqNo+sampleCode;	 			
	 			
	 			List<Inv_SampleCollectionVO> lstVOSample=mpBilled.get(hashBasedValue);
	 			int rowSpanSize=lstVOSample.size();
	 			rowSpanSize=1;
	 			
	 			if(lstVOSample.size()>1)
	 				sameReqNo=true;
	 			 
	 			String color="blue";
	 			int priorityCode=1;
	 			
	 			for(int k=0;k<lstVOSample.size();k++)
	 			{
	 				
	 			Inv_SampleCollectionVO voSampleCollection=lstVOSample.get(k);
	 			if(voSampleCollection.getPriorityAllCode()!=null)   // Normal;Urgent;Confidential
	 			{
	 				priorityCode=Integer.parseInt(voSampleCollection.getPriorityAllCode());
	 				switch(priorityCode)
	 				{
		 				case 1: color="blue";  // Normal Priority;
		 				        break;
		 				case 2: color="red";  // Urgent Priority;
					        	break;
		 				case 3: color="brown";  // Confidential Priority;
					        	break;
					    default: color="blue";
					    		break;
	 				}
	 			}
	 			
	 			String sampleConfiguration=voSampleCollection.getSampleNoConfiguration()==null?"3":voSampleCollection.getSampleNoConfiguration();
	 			boolean isSampleGenAuto=false;
	 			String readOnly="";
	 			if(sampleConfiguration.equals("1")||sampleConfiguration.equals("2"))
	 				isSampleGenAuto=true;  //readOnly="readonly='true'"; //
	 				
	 				if(firstTimeTravesa)
		 			{
	 					String instructpat=voSampleCollection.getPatInstruct();
	 				
	 		%>
				 		<html:hidden name="SampleCollectionFB" property="instructionPat" value="<%= instructpat%>" />
				 			<tr>
				 			
				 			  <%String chkBox="onClickReqNoChkBox(this,"+k+","+j+")"; %>
				 			    <td class="tdfonthead" rowspan="<%=rowSpanSize%>">
				 			    <div> <input type="checkbox" name="chkSamplePatientLab" id="<%=k%><%=j%>unCheck" onChange="<%=chkBox%>" value="<%=reqNoAndSampleCode%>"> </div>
				 			    </td>
					 			<td  class="tdfonthead" rowspan="<%=rowSpanSize%>">
					 				<div align="left">
					 				<font color="<%=color%>">	<%=voSampleCollection.getLabName() %> </font>
					 				</div>
					 			</td>
					 			<td>
						 			<div align="left">
							 			<table>
							 			
											 	<% int x=0;
											 	for(Inv_SampleCollectionVO voSample:lstVOSample)  // For Iterating Test of Same Sample,lab
									 			   { 
											 		String chkSampleVal=voSample.getPatCRNo()+"#"+voSample.getRequisitionNo()+"#"+voSample.getSampleCode()+"#"+voSample.getRequisitionDNo()+"#"+voSample.getLabCode()+"#"+voSample.getBillNo()+"#"+voSample.getTestCode()+"#"+voSample.getSampleName()+"#"+index+"#"+sampleConfiguration+"#"+voSample.getPatType()+"#"+voSample.getLabName()+"#"+voSample.getRequisitionDate()+"#"+voSample.getTestName()+"#"+voSample.getIsrequisitionformneeded();
											 		String labCode1=voSample.getLabCode();
											 	     String testCode1=voSample.getTestCode();
											 	     String labName1=voSample.getLabName();
											 		String testName1=voSample.getTestName();
											 		String requisitionDNo=voSample.getRequisitionDNo();
																		 				
											 		//String additionaladvice=voSample.getAdvice();
											 		/* if( additionaladvice!=null )
								 					additionaladvice=additionaladvice.replace("\r\n","<br>");
											 		else
											 			additionaladvice="NA"; */
											 		%>
											 			 <tr >
											 			   <%String subChkBox="checkAutoGen(this,"+k+","+j+","+x+")"; %>
											 			   	<td class="tdfonthead" width="1%" >
											 			    	<div align="left"> <input type="checkbox" id="<%=k%><%=j%><%=x%>unCheck" name="chkSamplePatient"  onclick="<%=subChkBox%>" value="<%=chkSampleVal%>"> </div> 
											 			    </td>
												 			<td class="tdfonthead" colspan="10" width="14%">
													 				<div align=left>
													 				<font color="<%=color%>">		<%=(voSample.getTestName()==null?"NA":voSample.getTestName()) %> </font>
													 			
													 			<%
													 				String patInstr= voSample.getPatInstruct()==null?"NA":voSample.getPatInstruct(); 
													 				String collInstr=voSample.getCollInstruct()==null?"NA":voSample.getCollInstruct();	
													 				System.out.println("chaa"+patInstr);
													 				System.out.println("chaa"+collInstr);
													 				patInstr=patInstr.replace("\r\n","<br>");
													 				collInstr=collInstr.replace("\r\n","<br>");
													 				
													 				
													 				
													 				
													 				%>
													 				<%
													 					 if( patInstr.equalsIgnoreCase("Patient#") && collInstr.equalsIgnoreCase("Sample Collection#") )
													 					{ 
													 				%>
													 				
													 				 <% }
													 					 else{ %>
													 						<img align="right" class="button" title="Show Instructions"  src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>'       tabindex="1" onclick ="showInstructions('<%=patInstr%>','<%=collInstr%>');">			
													 					<% }  %>
													 					
													 				<% if(!voSample.getIsrequisitionformneeded().equals("0")) {%>	
													 				<%-- 	&nbsp&nbsp<img height='20px'  title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=testCode1%>','<%=testName1%>','<%=labCode1%>','<%=labName1%>','<%=requisitionDNo%>')"> --%>
													 					<%} %>
													 			
									 			 <%++x;} %>
									 			 	
													 					
													 					
													 				</div>
													 				
													 				
													 			
													 				
													 				
													 		 </td>
											 			 </tr>
							 			 </table>
						 			 </div>
					 			</td>
					 			<%-- <td class="tdfont" rowspan="<%=rowSpanSize%>">
					 			<div align="left">
					 					<font color="<%=color%>">	 <%=voSampleCollection.getRequisitionNo() %> </font>
					 				</div>
					 			</td> --%>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getRequisitionDate() %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=(voSampleCollection.getSampleName()==null?"":voSampleCollection.getSampleName()) %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>">
					 			<div align="left">
					 				<font color="<%=color%>">	
                                        
					 				<% String strOnBlurFunction="chkSampleNoDuplicacyThroughAjax(this,event,"+index+");"; %>  
                                    <% if(isSampleGenAuto)
                                    {
                                    %>
                                   
                                     <input type="hidden"  name="sampleNo" readonly="true"  maxlength="10" size="10" value="<%=sampleConfiguration%>"   onkeypress="validateNumeric(event);"   tabindex="1" /> 
					 					<%}else{ %>
					 		 <input type="text"  name="sampleNo"  maxlength="10" size="10"    onkeypress="validateNumeric(event);" onblur="<%=strOnBlurFunction %>" tabindex="1" /> 
					 					
					 					<%} %> 
					 				</font>
					 				</div>
					 			</td>	
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" >
					 				<div align="left">
					 					<html:text name="SampleCollectionFB" property="sampleQnty"  maxlength="10" size="10"  value='<%=voSampleCollection.getSampleQnty()==null?"":voSampleCollection.getSampleQnty() %>'  onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="SampleCollectionFB" property="defaultUOMCode"  value="<%=voSampleCollection.getDefaultUOMCode() %>" tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_UOM_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_UOM_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							  </span>
					 			</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>">
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="SampleCollectionFB" property="defaultContainerCode" value="<%=voSampleCollection.getDefaultContainerCode() %>" tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							</span>
					 			</div>
					 			</td>
					 			<td class="tdfonthead" rowspan="<%=rowSpanSize%>" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getPriorityAll()==null?"":voSampleCollection.getPriorityAll() %> </font>
					 				</div>
					 			

										<div id="blanket" style="display: none;"></div>
 		<div id="popUpDiv5"  style="display:none;" align="center">  
		 
		<his:TitleTag name="Instructions For Patients">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
  		</his:TitleTag>
  			
  			
 												
										</td>
					 			
					 		<td class="tdfonthead" >
					 			<div align="left">
	 								  <span class="custom-dropdown small">
	 								<html:select name="SampleCollectionFB" property="defaultmachineCode"  tabindex="1"  >
	       								<html:option value="-1">Select Value</html:option>
							 				 <logic:present name="<%=InvestigationConfig.LIST_MACHINEE_COMBO%>">
						 	   								<html:options collection="<%=InvestigationConfig.LIST_MACHINEE_COMBO%>" property="value" labelProperty="label"/>
						  					</logic:present>
	      							</html:select>
	      							</span>
					 			</div>
					 			</td>
								
								
					 			
				 			 </tr>
		<%index++;
		 	   if(sameReqNo)firstTimeTravesa=false;}
		 			}++j;
	 			}%>
								</table>
					</his:ContentTag>
				</logic:present>
 </logic:notEmpty>
 		<!--  Un Billed Details -->
 <logic:notEmpty name="<%=InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED %>">
	 	<logic:present name="<%=InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED %>">
 		<his:ContentTag>
 		<his:SubTitleTag name="Unbilled Details"></
  			</his:SubTitleTag>
	 		<table width="100%" >
	 			<tr >
					 			<td class="tdfont" >
					 				<div align="left">
					 					<bean:message key="labName"/>&nbsp;
					 				</div>
					 			</td>
					 			<%-- <td class="tdfonthead" >
					 			<div align="left">
					 					<bean:message key="reqNo"/>&nbsp; 
					 				</div>
					 			</td> --%>
					 			<td class="tdfont" >
					 			<div align="left">
					 				<bean:message key="reqDate"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 				<bean:message key="TestName"/>&nbsp;
					 				</div>
					 			</td>
					 			<td class="tdfont" >
					 			<div align="left">
					 				<bean:message key="priority"/>
					 				</div>
					 			</td>
				 			 </tr>
	 		<%
	 		Map<String,List<Inv_SampleCollectionVO>> mpUnBilled= (Map<String,List<Inv_SampleCollectionVO>>)session.getAttribute(InvestigationConfig.LIST_PAT_SAMPLE_UNBILLED);
	 		Iterator itr=mpUnBilled.keySet().iterator();
	 		int index=0;
	 		while(itr.hasNext())
	 		{
	 			String hashBasedValue=(String)itr.next();
	 			
	 			String[] arrHashBasedValue=hashBasedValue.split("#");
	 			
	 			String reqNo=arrHashBasedValue[0];
	 			String labCode=arrHashBasedValue[1];
	 			String sampleCode=arrHashBasedValue[2];
	 			
	 			List<Inv_SampleCollectionVO> lstVOSample=mpUnBilled.get(hashBasedValue);
	 			int rowSpanSize=lstVOSample.size();
	 			
	 			if(rowSpanSize!=0)
	 				rowSpanSize=rowSpanSize-1;
	 		
	 		for(int k=0;k<lstVOSample.size();k++)
	 		{
	 			Inv_SampleCollectionVO voSampleCollection=lstVOSample.get(k);
	 			String color="blue";
	 			int priorityCode=1;
	 			if(voSampleCollection.getPriorityAllCode()!=null)   // Normal;Urgent;Confidential
	 			{
	 				priorityCode=Integer.parseInt(voSampleCollection.getPriorityAllCode());
	 				switch(priorityCode)
	 				{
		 				case 1: color="blue";  // Normal Priority;
		 				        break;
		 				case 2: color="red";  // Urgent Priority;
					        	break;
		 				case 3: color="brown";  // Confidential Priority;
					        	break;
					    default: color="blue";
					    		break;
	 				}
	 			}
	 			//String chkSampleVal=voSampleCollection.getPatCRNo()+"#"+voSampleCollection.getRequisitionNo()+"#"+voSampleCollection.getSampleCode()+"#"+voSampleCollection.getRequisitionDNo();
	 		%>
				 		
				 			<tr>
					 			<td  class="tdfonthead" >
					 				<div align="left">
					 				<font color="<%=color%>">	<%=voSampleCollection.getLabName() %> </font>
					 				</div>
					 			</td>
					 			<%-- <td class="tdfont">
					 			<div align="left">
					 					<font color="<%=color%>">	 <%=voSampleCollection.getRequisitionNo() %> </font>
					 				</div>
					 			</td> --%>
					 			<td class="tdfonthead" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getRequisitionDate() %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getTestName() %> </font>
					 				</div>
					 			</td>
					 			<td class="tdfonthead" >
					 			<div align="left">
					 				<font color="<%=color%>">		<%=voSampleCollection.getPriorityAll()==null?"":voSampleCollection.getPriorityAll() %> </font>
					 				</div>
					 			</td>
				 			 </tr>
		<%} }%>
								</table>
				</his:ContentTag>
		</logic:present>
 
 	</logic:notEmpty>
 	
 	
 	<!-- START -- TO DISPLAY INSTRUCTIONS -->
 	
 		
 		
 		<div id="blanket" style="display: none;"></div>
 		<div id="popUpDiv"  style="display:none;">  
		 
		<his:TitleTag name="Instructions">
		<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
  		</his:TitleTag>
  			
  			
 		<table width="100%" id="allInstructions">
 	             
 		<tr>
 		
 		<td class="tdfonthead" width="20%">
		<div align="left"><b>Instructions for:</b></div>
		</td>
		
		<td class="tdfonthead" width="80%">
		<div align="center"><b>Instructions</b></div>
		</td>
						
		</tr>
 		</table>
 		 <br>  
 		   <div align="center">                          
 		 <img src='/HISInvestigationG5/hisglobal/images/ok.gif'
			id='closeInst' onclick="closeInstructions();">                       
					</div>

		</div>
	
	
 	
 	<!-- END -- TO DISPLAY INSTRUCTIONS -->
 	
 	
 	
 </his:statusTransactionInProcess>
 
 			<html:hidden name="SampleCollectionFB" property="duplicateBarcodeGeneration" />
 
			<html:hidden name="SampleCollectionFB" property="hmode" />
			<html:hidden name="SampleCollectionFB" property="flagforipddesk" />
		
			<html:hidden name="SampleCollectionFB" property="statuschange" />
			<html:hidden name="SampleCollectionFB" property="modebarcode" />
			<html:hidden name="SampleCollectionFB" property="selectedCheckbox" />
			 <html:hidden name="SampleCollectionFB" property="showStatus" />
			<html:hidden name="SampleCollectionFB" property="sampleAreaName" />
			<html:hidden name="SampleCollectionFB" property="sampleAreaCode" />
			<html:hidden name="SampleCollectionFB" property="isSampleAreaSelected" />
			 <html:hidden name="SampleCollectionFB" property="currentPage" />
              <html:hidden name="SampleCollectionFB" property="patCRNo" />
                   <html:hidden name="SampleCollectionFB" property="patCrNo" />
                            <html:hidden name="SampleCollectionFB" property="sysDate" />
                            <html:hidden name="SampleCollectionFB" property="labCode" />
                            <html:hidden name="SampleCollectionFB" property="testCode" />
                            <html:hidden name="SampleCollectionFB" property="patType" />
              <html:hidden name="SampleCollectionFB" property="tempSampleNo"/>
              
                <html:hidden name="SampleCollectionFB" property="saveConfirmFlag"/>
                   <html:hidden name="SampleCollectionFB" property="barCodeGenSize"/>
            
            
               
				     
                				 <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="16">
                 <logic:notEqual name="SampleCollectionFB" property="flagforipddesk" value="10">
				  <his:ButtonToolBarTag>
				
				    
				     
				
				     <logic:notEqual name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
				    	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;display:none" onkeypress="if(event.keyCode==13) showPatList()" onclick="showPatList()" tabindex="1">
				    </logic:notEqual>
				    <his:statusTransactionInProcess>
				     	<img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' id="nextDiv"  style="cursor:pointer;display:none"   tabindex="1" onclick ="displaySamplePatDetails();" >
				   	 	<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' id="saveDiv"  style="cursor:pointer;display:none" onkeypress="if(event.keyCode==13&&validateSampleCollection()) submitForm('SAVE');"  tabindex="1" onclick ="if(validateSampleCollection())submitForm('SAVE');" >
				   	 		
				   	 	<logic:equal name="SampleCollectionFB" property="showStatus" value="1">
				   	 	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="saveDiv"  tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFor();" tabindex="1" onclick ="submitFor();">
				   	 	</logic:equal>
				    </his:statusTransactionInProcess>
				     <logic:notEqual name="SampleCollectionFB" property="showStatus" value="1">
				     <logic:notEqual name="SampleCollectionFB" property="showStatus" value="3">
				     
				     
				     	<logic:equal name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
				
				  <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
				     	<logic:equal name="SampleCollectionFB" property="showStatus" value="0">
				     	<%
				     	
				     	UserVO uservo=ControllerUTIL.getUserVO(request);
					      String hospitalCode=uservo.getHospitalCode();
				     	%>
				    	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="goButton" style="cursor: pointer;" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>','0')" onclick="onClickGO('<%=hospitalCode%>','0')" tabindex="1">
</logic:equal>
				    </logic:equal>
				
				    </logic:equal>
				    
				    <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick ="cancelFunc();">
				    </logic:notEqual>
				     </logic:notEqual>
				     
				    
				     
				</his:ButtonToolBarTag>
				</logic:notEqual>
				</logic:notEqual>
				<logic:notEmpty name="<%=InvestigationConfig.SELECTED_PAT_DETAILS %>"> 
				<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
    <div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="blue"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					  	<bean:message key="normalReq"/>
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="red"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 <bean:message key="urgentReq"/> 
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="brown" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <div align="left">
					    <bean:message key="brown"/>
					  </div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					 <bean:message key="Confidential"/> 
					</div>
					</font>
				</td>				
			</tr>
			
		</table>
	</his:ContentTag>
	</div>
 </logic:notEmpty>
 
<his:status/>
  
</html:form>


<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<script type="text/javascript" >
var reqformtestnames="";
var reqformtestcodes="";

function f2()
{
	document.getElementById('reqformss').style.display="none";
}
function f1()
{
	document.getElementById('reqformss').style.display="";
	$("#tbll").find("tr:not(:first)").remove();

	var checktest="";
	 var overalltest="";
	 var reqnoo="";
     for(var a=0;a<document.getElementsByName("chkSamplePatient").length;a++)
 {
    	// alert(a);
    	 //alert(document.getElementsByName("chkSamplePatient")[a].value);
    	 //alert((document.getElementsByName("chkSamplePatient")[a].value).split("#")[6]);
    	 overalltest+=(document.getElementsByName("chkSamplePatient")[a].value).split("#")[6]+";";
    	 
  }


     for(var a=0;a<document.getElementsByName("chkSamplePatient").length;a++)
     { 
                //alert(a+"len"+document.getElementsByName("chkSamplePatient").length);
    	 var valuess=(document.getElementsByName("chkSamplePatient")[a].value).split("#");

    	 var reqNo=valuess[1];
    	 var testCode=valuess[6];
    	 var testName=valuess[13];
    	 var labCode=valuess[4];
    	 var labName=valuess[11];
    	 var dNo=valuess[3];
          //alert("reqNo"+reqNo);
	 	   	 		
    	 
     var table = document.getElementById("tbll");

                      //   alert("testCodetestCode"+testCode);
                  	  var mappedcheckedtest=checkreqformTestType(testCode);
                  	  
                      var mappedTest=mappedcheckedtest.split("#")[2];
                     // alert("matchtest"+matchtest);
                      var formtype=mappedcheckedtest.split("#")[1];


                      if(mappedTest=="0")
             		 {
                  		// alert("mappedTest 0");
                            // not match
                            var rowCount = table.rows.length;
           	//alert("rowCount"+rowCount);
           var row = table.insertRow(rowCount);
           row.id=testCode+"#"+reqNo;
           row.name=testName;
           row.value=labName;
           row.testt=testCode;
           reqformtestnames+=testName;
           reqformtestcodes+=testCode;


           if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
               {
                var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkSamplePatient")[a].value).split("#")[13];

           		if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

           		checktest+=testCode+"@"+reqNo+"#";
               }
           
             		 }

                      else
             		 {
                    	  if(checktest=='')
                   		 {
                        		 // 1st time null
                            //   alert("mappedTest not 0 1tym null" );
                      		    var rowCount = table.rows.length;
           	//alert("rowCount"+rowCount);
           var row = table.insertRow(rowCount);
           
     		if(mappedTest!="0")
         		{
           if(mappedTest.includes("@"))
        		row.id = testCode+"#"+reqNo;
       	   else
       		   row.id = mappedTest+"#"+reqNo;
         		}
     		else
     			row.id = testCode+"#"+reqNo;
           //row.id=testCode+"#"+reqNo;
           row.name=testName;
           row.value = labName;
    		row.testt=testCode;
           reqformtestnames+=testName;
           reqformtestcodes+=testCode;


           if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
               {
           var cell1 = row.insertCell(0);
           		var cell2 = row.insertCell(1);
           		cell1.innerHTML = (document.getElementsByName("chkSamplePatient")[a].value).split("#")[13];

           		if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

           		checktest+=testCode+"#"+mappedTest+"#";
               }
           
                   		 }
                    	  else
                        		  {
                    			  if(!mappedTest.includes("@")) // if not conatin @
                                  {
   
                                    //  alert("not contain @");
                                    //  alert("not contain @ checktest"+checktest);
                                    //  alert("not contain @ mappedTest"+mappedTest);
                                      
                    			  if(checktest.includes(mappedTest)) //find dervied test
                             	   {
//
                               	   //  alert("match test");

                    				  var flag="2";
                                      //  alert("match");
                                    //    alert("match"+matchtest);
                                       reqformtestnames+=";"+testName;
                                        reqformtestcodes+=";"+testCode;
                                        var tr ="";
                                           // alert("match"+testCode+"#"+reqNo+document.getElementById(mappedTest+"#"+reqNo)+document.getElementById(mappedTest+"#"+reqNo));

                                             if(document.getElementById(mappedTest+"#"+reqNo)==null && document.getElementById(testCode+"#"+reqNo)==null)
                                                 {
                                                     // alert("req no. change ");

                                                      var rowCount = table.rows.length;
                                  		           	//alert("rowCount"+rowCount);
                                  		           var row = table.insertRow(rowCount);
                                                          if(mappedTest!="0")
                                                      {        
                                     		         if(mappedTest.includes("@"))
                                  	        		row.id = testCode+"#"+reqNo;
                                  	       	   else
                                  	       		   row.id = mappedTest+"#"+reqNo;
                                                      }
                                                          else
                                                              {
                                                        	  row.id = testCode+"#"+reqNo;
                                                              }     
                                                          row.value = labName;
                                          				row.testt=testCode;
                                  		           reqformtestnames+=testName;
                                  		           reqformtestcodes+=testCode;
                 

                                  		           if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                                  		               {
                                  		           var cell1 = row.insertCell(0);
                                  		           		var cell2 = row.insertCell(1);
                                  		           		cell1.innerHTML = (document.getElementsByName("chkSamplePatient")[a].value).split("#")[13];

                                  		           		if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                                  		           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

                                  		           	//	checktest+=testCode+"@"+reqNo+"#";
                                  		           		checktest+=testCode+"#"+mappedTest+"#";
                                  		               }
                              		               
                                                 }
                                             else
                                                 {
                                             if(document.getElementById(mappedTest+"#"+reqNo)==null)
                                          tr = document.getElementById(testCode+"#"+reqNo);
                                          else
                                          tr = document.getElementById(mappedTest+"#"+reqNo);    

                                             row.value = tr.value+";"+labName;
                             				 row.testt = tr.testt+";"+testCode;
                             				tr.testt=row.testt;
                                        var rwname=tr.name+"#";
                                        				rwname+=testCode;
                                        				rwnamee=rwname;
                                        				tr.name=rwname;
                                        				//alert("trridname"+tr.name+"@"+rwnamee);
                                        		//		var trr=document.getElementsByName(mappedTest).value;
                                        				
                                        				var td = tr.getElementsByTagName("td");
                                        				
                                        				if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                                                       {	
                                        				for(var i=1;i<td.length;i++) {
                                        					var namee=td[0].innerHTML+",";
                                        					var nameee=td[0].innerHTML+";";
                                                            // alert(td[1].innerHTML);
                                        					namee+=testName;
                                        					nameee+=testName;
                                        					reqformtestnames=nameee;
                                        					//alert("reqformtestnames"+reqformtestnames);
                                        					//reqformtestcodes=rwnamee;
                                        	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                                                     //     alert("name"+namee);
                                                           td[0].innerHTML=namee;
                                        				    //console.log(td[i].innerHTML);
                                        				}
                                                       }
                                        				//checktest+=testCode+"#";
                                        				checktest+=testCode+"#"+mappedTest+"#";
                                                 }	
                            	   }
                    			  
                    			  else // not find
                        			  {

                    				   var rowCount = table.rows.length;
                    		           	//alert("rowCount"+rowCount);
                    		           var row = table.insertRow(rowCount);
                    		           if(mappedTest!="0")
                                       {        
                      		         if(mappedTest.includes("@"))
                   	        		row.id = testCode+"#"+reqNo;
                   	       	   else
                   	       		   row.id = mappedTest+"#"+reqNo;
                                       }
                                           else
                                               {
                                         	  row.id = testCode+"#"+reqNo;
                                               } 
                    		           row.name=testName;
                    		           row.value=labName;
                          				row.testt=testCode;
                    		           reqformtestnames+=testName;
                    		           reqformtestcodes+=testCode;


                    		           if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                    		               {
                    		           var cell1 = row.insertCell(0);
                    		           		var cell2 = row.insertCell(1);
                    		           		cell1.innerHTML = (document.getElementsByName("chkSamplePatient")[a].value).split("#")[13];

                    		           		if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                    		           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

                    		           	//	checktest+=testCode+"@"+reqNo+"#";
                    		           		checktest+=testCode+"#"+mappedTest+"#";
                    		               }

                        			  }
                                  }
                    			  else // find @ master form
                        			  {


                    				 var flag="2";
                                      //  alert("match");
                                        matchtest=testCode;
                                      //  matchtest=matchtest.replace("@","#");
                                    //    alert("match"+matchtest);
                                       reqformtestnames+=";"+testName;
                                        reqformtestcodes+=";"+testCode;
                                        if(document.getElementById(matchtest+"#"+reqNo)==null)
                                            {

                                        	 var rowCount = table.rows.length;
                         		           	//alert("rowCount"+rowCount);
                         		           var row = table.insertRow(rowCount);
                         		          if(mappedTest!="0")
                                          {        
                         		         if(mappedTest.includes("@"))
                      	        		row.id = testCode+"#"+reqNo;
                      	       	   else
                      	       		   row.id = mappedTest+"#"+reqNo;
                                          }
                                              else
                                                  {
                                            	  row.id = testCode+"#"+reqNo;
                                                  }   
                                          
                         		           row.name=testName;
                         		           row.value=labName;
                               				row.testt=testCode;
                         		           reqformtestnames+=testName;
                         		           reqformtestcodes+=testCode;


                         		           if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                         		               {
                         		           var cell1 = row.insertCell(0);
                         		           		var cell2 = row.insertCell(1);
                         		           		cell1.innerHTML = (document.getElementsByName("chkSamplePatient")[a].value).split("#")[13];

                         		           		if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                         		           			cell2.innerHTML = "<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick='ShowRequistionForm("+"\""+(testCode)+"\""+","+"\""+(testName)+"\""+","+"\""+(labCode)+"\""+","+"\""+(labName)+"\""+","+"\""+(dNo)+"\""+","+"\""+(reqNo)+"\""+")'>";

                         		           	//	checktest+=testCode+"@"+reqNo+"#";
                         		           		checktest+=testCode+"#"+mappedTest+"#";
                         		               }

                                            }
                                        else
                                            {
                                        var tr = document.getElementById(matchtest+"#"+reqNo);
                                        row.value = tr.value+","+labName;
                        				tr.value=row.value;
                        				row.testt = tr.testt+";"+testCode;
                        				tr.testt=row.testt;
                                        var rwname=tr.name+"#";
                                        				rwname+=testCode;
                                        				rwnamee=rwname;
                                        				tr.name=rwname;
                                        				//alert("trridname"+tr.name+"@"+rwnamee);
                                        		//		var trr=document.getElementsByName(mappedTest).value;
                                        				
                                        				var td = tr.getElementsByTagName("td");
                                        				
                                        				if((document.getElementsByName("chkSamplePatient")[a].value).split("#")[14]=="1")
                                                       {	
                                        				for(var i=1;i<td.length;i++) {
                                        					var namee=td[0].innerHTML+",";
                                        					var nameee=td[0].innerHTML+";";
                                                            // alert(td[1].innerHTML);
                                        					namee+=testName;
                                        					nameee+=testName;
                                        					reqformtestnames=nameee;
                                        					//alert("reqformtestnames"+reqformtestnames);
                                        					//reqformtestcodes=rwnamee;
                                        	                   //alert("td[i].innerHTML"+td[i].innerHTML);
                                                     //     alert("name"+namee);
                                                           td[0].innerHTML=namee;
                                        				    //console.log(td[i].innerHTML);
                                        				}
                                                       }
                                        				//checktest+=testCode+"#";
                                            }		

                        			  }                                 	   
                        		  }   
                          }    	 
                    		 
             		 }
                
     
}



function checkreqformTestType(TestCode)
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	
	var flg = false;
	var remarks = "";
	var _mode = "AJX_CHECK_REQFORM_TESTTYPE";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&testCode="+TestCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("checkreqformTestType"+data);
			remarks = data;
			flg = true;
		},
        error: function(error)
        {
            //remarks = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
//	alert("amppedtest"+remarks);
	return remarks;
}


function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function barocdeDetails(obj)
 {
	 var count = 0;
      for(var i=0;i<document.getElementsByName('chkSamplePatient').length;i++)
          {
        if(document.getElementsByName('chkSamplePatient')[i].checked==true)
            {
               count++;
            }
          }
      if(count==0)
          {
     alert("please select atleast one record");
          }
      else
          {
           submitForm("DUPLICATEBARCODE");
          }
          
 }

function onClickGenerationBarcode(obj)
 {

    if(obj.value==0)
	//document.getElementsByName('showStatus')[0].value="0";
    	document.getElementsByName('duplicateBarcodeGeneration')[0].value=0;
    	document.getElementsByName('hmode')[0].value='GETPATLISTBAROCDE';
    	document.forms[0].submit();
 }

/* function httpRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		initReq(reqType,url,asynch);
	}
	else if (window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			 request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		{
			initReq(reqType,url,asynch);
			// Unlikely to branch here, as IE uses will be able to use either 
			//one of the constructors
		}
		else
			alert("Your browser does not permit the use of all of this application's features!");
 	}
 	else
		alert("Your browser does not permit the use of all of this application's features!");
} */

/* function initReq(reqType,url,isAsynch)
{
	//alert("inside init request");
 // Specify the function that will handle the HTTP response 
	request.onreadystatechange=handleResponse;
	request.open(reqType,url,isAsynch);
 //set the Content-Type header for a POST request 
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	var queryString;
	request.send(queryString);
} */


// select all patients and check for diff patients

function validateSameCrNoAllSelected(obj)
{
	document.getElementById('goButton').style.display="none";
	document.getElementById('nextDiv').style.display="";
	//alert($("#DataTable1").count());
	//var objCurrentCheckBox=obj.value;
	var objCurrentCheckBox=null;
    var table = document.getElementById("DataTable1");
    var rows = table.getElementsByTagName("tr");
  //  alert(rows.length)
  if (document.getElementsByName("allSelectLab")[0].checked ==true)
{ 
  alert("Only same cr Number can be selected ....");
  
    document.getElementsByName("chkSamplePatient")[0].checked=true;
    for(var i=1; i <= rows.length; i++) 
		{
    	var firstval =document.getElementsByName("chkSamplePatient")[0].value;
    	//alert(firstval)
    	var chkval =document.getElementsByName("chkSamplePatient")[i].value;
    	//alert(chkval);
    	 if(chkval.split("#")[0]==firstval.split("#")[0])
	    	{
	    		//alert("yes");
	    		document.getElementsByName("chkSamplePatient")[i].checked=true;
	    	}
	    	else{
	    		//alert("no");
	    		document.getElementsByName("chkSamplePatient")[i].checked=false;
	    	}
		}
}
  else
	  {
	  for(var i=0; i <= rows.length; i++) 
		{
		  document.getElementsByName("chkSamplePatient")[i].checked=false;
		}
	  }
/* 	if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		
		
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			    	if(objCurrentCheckBox=="on")
			    		objCurrentCheckBox=cbs[i].value;
			    //	alert(objCurrentCheckBox)
			    	if(!cbs[i].checked)
			    		cbs[i].checked=true;
			      
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    		// alert("i="+i);
			    		// alert(cbs[i].value.split("#")[0])
			    	alert("The list contains different CR Nos. Please select same CR Nos.");
			    	
			    	obj.checked=false;
			    	cbs[i].checked=false;
			    	
			    	//document.getElementById('goButton').style.display="";
			    	
			    	
			    	
			    	return false;
			    	}
			    	 
				}
		}
	} */
	
	
	}
	
	
	
	
	
function ValidateSameCrNo(obj)
{
	
	document.getElementById('goButton').style.display="none";
	
	document.getElementById('nextDiv').style.display="";
	
	var objCurrentCheckBox=obj.value;
	//alert(obj.value);
	if(obj.checked)
	{
		
		var cbs = document.getElementsByTagName('input');
		
		for(var i=0; i < cbs.length; i++) 
		{
			    if(cbs[i].type == 'checkbox') 
			    {
			    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
			    	{
			    	alert("Please Select Same CR No.");
			    	//document.getElementById('nextDiv').style.display="none";
			    	obj.checked=false;
			    	document.getElementById('goButton').style.display="";
			    	return false;
			    	} 
				}
		}
	}
	//displaySamplePatDetails();
}
function showPatList ()
{
	document.getElementById('goButton').style.display="none";
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('hmode')[0].value='GETPATLIST';
	document.forms[0].submit();
	}
	
function submitFor()
{
	document.getElementsByName('showStatus')[0].value="0";
	document.getElementsByName('isSampleAreaSelected')[0].value="0";
	document.getElementsByName('sampleAreaCode')[0].value="-1";
	document.getElementsByName('hmode')[0].value='NEW';
	document.forms[0].submit();
	}	
	
function displaySamplePatDetails()
{	
	var count=0;
	var concatenateChkBoxVal="";
	//var cbs = document.getElementsByTagName('input');
	var cbs =document.getElementsByName('chkSamplePatient');
	for(var i=0; i < cbs.length; i++) {
	 // if(cbs[i].type == 'checkbox') 
    //{
    	
    	if(cbs[i].checked)
    	{
    		
    	count++;	
    	concatenateChkBoxVal =concatenateChkBoxVal.concat(cbs[i].value);
    	concatenateChkBoxVal+='@';
    	 }
  	//}
	  
 
      }
	
	if(count==0)
   	{
  // 	alert("please select a record");
   	return false;
   	}
	
	document.getElementsByName('selectedCheckbox')[0].value=concatenateChkBoxVal;
	document.getElementsByName('hmode')[0].value='SHOWPATDETAILS';
	document.forms[0].submit();
	}

/* function handleResponse()
{
	//alert("inside Response");
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			//alert("response sucessfull");
		}
		else
			alert("A problem occurred with communicating between the XMLHttpRequest object and the server program.");
	}
}
 */
function showSearchDiv(obj)
{
	 //alert("1");
	if(obj.value==-1)
	{
		document.getElementById('goButton').style.display="none";
	}
	else
	{
		document.getElementById('goButton').style.display="";
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
		
		
		document.getElementsByName("hmode")[0].value="NEW";
		document.forms[0].submit();
	}
	
	
}

function onClickSave(obj)
{
	if(obj.checked)document.getElementById("saveDiv").style.display=""; 
	else document.getElementById("saveDiv").style.display="none";
}

function showLegends(){
	  document.getElementById("divLegends").style.display="block"; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

//AJax Functions for checking Duplicacy
function chkSampleNoDuplicacyThroughAjax(obj,event,index)
{
	var sampleNo=obj.value;

	var sampleAreaCode=document.getElementsByName("sampleAreaCode")[0].value;

		var isSampleNoPresent = chkSampleNo(sampleNo,sampleAreaCode);
		
		isSampleNoPresent=isSampleNoPresent=="true"?true:false;
		
		if(isSampleNoPresent)
		{
			alert("Sample Number already present");
			obj.value="";
			obj.focus();
			return false;
		}
		
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	for(var i=0;i<chkBoxSample.length;i++)
		{
		    var chkIndex=chkBoxSample[i].value.split("#")[8];
			var tmpSampleNo=document.getElementsByName("sampleNo")[chkIndex].value;
			
			if(sampleNo==tmpSampleNo&&sampleNo!=""&&index!=chkIndex)
			{
				alert("Sample Number Already Present");
				obj.value="";
				obj.focus();
				return false;
			}
		}
	
	return true;
}

function chkSampleNo(sampleNo,sampleAreaCode)
{
	var flg = false;
	var isSampleNoPresent = false;
	var _mode = "AJX_DUPLICACY_SAMPLENO";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&strSampleNo="+sampleNo+"&sampleAreaCode="+sampleAreaCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isSampleNoPresent = data;
			flg = true;
		},
        error: function(error)
        {
           
            isSampleNoPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isSampleNoPresent;
}


function showInstructions(inst,collInst)
{
    	//alert(inst.split('#')[1]);
    //	alert(collInst.split('#')[1]);
	deleteTableInst();

	if(collInst.split('#')[1]=='' && inst.split('#')[1]!='')
{
		
		addRowToTableInst(inst);
		
}
	if(collInst.split('#')[1]!='' && inst.split('#')[1]=='')
{
		
			addRowToTableInst(collInst);
}
			
	if(collInst.split('#')[1]!='' && inst.split('#')[1]!='')
        {      
		addRowToTableInst(inst);
		addRowToTableInst(collInst);
		}
		
/* 	if( (inst.split('#')[1]==null || collInst.split('#')[1]=='') && (collInst.split('#')[1]==null || inst.split('#')[1]==''))
		addRowToTableInst('-#No Instructions Available');
 */	popup("popUpDiv");
	
	}
	
	
function closeInstructions()
{
	popup("popUpDiv");

	
	}
	
	
function addRowToTableInst(inst)
{
	
	
	var nRow=0;
	var tableObj=document.getElementById('allInstructions');
	
	var tr=document.createElement("TR");
	 
	//tr.setAttribute("id","setPdf");
	tableObj.appendChild(tr);
	var numRows=tableObj.rows.length;
		nRow=numRows;
 
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow);
	 
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
 
	td1.innerHTML="<div  align='left' >"+inst.split('#')[0]+"</div>";   
	td1.className='tdfont';
	td1.width="1";
	inst.split('#')[1].replace("\r\n","<br>");
	td2.innerHTML="<div align='left' >"+inst.split('#')[1]+"</div>";   
	td2.className='tdfont';
	td2.colspan="1";
   
	tabRow.appendChild(td1); 
	tabRow.appendChild(td2);  
	//document.forms[0].numberOfRow.value=numRows;
	}
	
	

function deleteTableInst()
{
	
	for(var i = document.getElementById("allInstructions").rows.length-1; i > 0; i--)
	{
		document.getElementById("allInstructions").deleteRow(i);
		
	}
	
}

//End AjaxFunctions

function validateSampleCollection()
{
		var chkBoxSample=document.getElementsByName("chkSamplePatient");
		if(chkBoxSample==null || chkBoxSample.length<1)
			{ 
				alert("Please select atleast one billed Patient");
				return false;
			}
		var cbs =document.getElementsByName('chkSamplePatient');
		for(var i=0; i < cbs.length; i++) {
		if(cbs[i].checked)
    	{
		
			    var index=chkBoxSample[i].value.split("#")[8];
				var sampleNo=document.getElementsByName("sampleNo")[index].value;
				var sampleQnty=document.getElementsByName("sampleQnty")[index].value;
				var defaultUOMCode=document.getElementsByName("defaultUOMCode")[index].value;
				var defaultContainerCode=document.getElementsByName("defaultContainerCode")[index].value;
				
				if(sampleNo==null||sampleNo==""||sampleNo=='')
				{
					alert("Please Enter Daily Sample No");
					document.getElementsByName("sampleNo")[index].focus();
					return false;
				}

				if(defaultUOMCode=="-1")
					{
						alert("Please select Unit of Measurement");
						document.getElementsByName("defaultUOMCode")[index].focus();
						return false;
					}
				else if(defaultContainerCode=="-1")
				{
					alert("Please select Container");
					document.getElementsByName("defaultContainerCode")[index].focus();
					return false;
				}
			}
    	//}
		}
		return true;
}

//new function to select all
function onSelectAll(obj)
{	
	var selectAll=document.getElementsByName("chkSamplePatientLab");

	
	for(var i=0;i<selectAll.length;i++)
		{
		
		if(obj.checked)
			{
		if(!selectAll[i].checked)
			{
			
			selectAll[i].checked=true;
			onClickReqNoChkBox(selectAll[i],1,1);
			}
		else
			;
		
		}
		
	else
		{
		
		selectAll[i].checked=false;
		
		var subBox=document.getElementsByName("chkSamplePatient");
		
		for(var k=0;k<subBox.length;k++)
		{
			subBox[i].checked=false;
		}
		}
		}
}

function onClickReqNoChkBox(obj,k,j)
{
	var reqNo=obj.value;
	var val=document.getElementsByName("instructionPat")[0].value;
   val=val.split("#")[1];
	var checked=true;
	if(obj.checked)
		{
		checked=true;
		document.getElementById("saveDiv").style.display=""; 
		}
	else
		checked=false;
	var chkBoxSample=document.getElementsByName("chkSamplePatient");
	
	
	for(var i=0;i<chkBoxSample.length;i++)
		{
		 
		//chkBoxSample[i].checked=false;
		var checkValueForAutoGen= chkBoxSample[i].value;
		var splitedAutoGenValue=checkValueForAutoGen.split("#");
	
		var labCode=splitedAutoGenValue[4];
		var testCode=splitedAutoGenValue[6];
		var patType=splitedAutoGenValue[10];
		var sampleArea=document.getElementsByName("sampleAreaCode")[0].value;
		
		
		var labName=splitedAutoGenValue[11];
		var testName=splitedAutoGenValue[12];
		var sampleconfig=splitedAutoGenValue[9];
		
	if(sampleconfig==1||sampleconfig==2)	 
		 {
		var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig,sampleArea,val);
	if(autoGenFormate!='null')
		{
		var SplittmpReqVal=chkBoxSample[i].value.split("#");
		var tmpReqNo=SplittmpReqVal[1]+SplittmpReqVal[2];
			
			if(reqNo==tmpReqNo)
				chkBoxSample[i].checked=checked;
			var autoValue="#";
			autoValue+=autoGenFormate;
			autoValue+=val+"#";
			//alert(autoValue);
			//alert(document.getElementsByName("chkSamplePatient")[i].value);
			document.getElementsByName("chkSamplePatient")[i].value+=autoValue;
		//	alert(document.getElementsByName("chkSamplePatient")[i].value);
		}
		else
			{
			var SplittmpReqVal=chkBoxSample[i].value.split("#");
			var tmpReqNo=SplittmpReqVal[1]+SplittmpReqVal[2];
			if(sampleconfig==1&&reqNo==tmpReqNo)
			alert("No Sample Number Formate Is Configured For    "+  testName  + "("+ labName +")   Please Configure From Sample Number Configuration Master ");
			 
			if(sampleconfig==2&&reqNo==tmpReqNo)
				alert("No Sample Number Formate Is Configured For  "+  testName  + " ( "+  labName  +") Lab  Please Configure From Collection Area Sample Number Configuration Master ");
			//if(reqNo==tmpReqNo)
				chkBoxSample[i].checked=false;
			var cbs =document.getElementsByName('chkSamplePatientLab');
		     
				var id=cbs[i].id;
				
				var indexWithSubIndex=id.substring(0,2);
			
				document.getElementById(indexWithSubIndex+"unCheck").checked = false;
			
		    			 
			}
		}
	else
		{
		 //alert("add null");
		 var autoValue="#null#null#null#null#null#null#null#null#null#null#null#null#null";
			autoValue+=autoGenFormate;
			document.getElementsByName("chkSamplePatient")[i].value+=autoValue;
		var SplittmpReqVal=chkBoxSample[i].value.split("#");
		var tmpReqNo=SplittmpReqVal[1]+SplittmpReqVal[2];
		if(reqNo==tmpReqNo)
			chkBoxSample[i].checked=checked;
		}
		}//for Loop
}

function checkAutoGen(obj,k,j,index)
{
	if(obj.checked)
		{
		
		checked=true;
		document.getElementById("saveDiv").style.display=""; 
		var checkValueForAutoGen=document.getElementById(k+""+""+j+""+index+"unCheck").value;
		var splitedAutoGenValue=checkValueForAutoGen.split("#");
		var labCode=splitedAutoGenValue[4];
		var testCode=splitedAutoGenValue[6];
		var patType=splitedAutoGenValue[10];
		var sampleArea=document.getElementsByName("sampleAreaCode")[0].value;
		
		var labName=splitedAutoGenValue[11];
		var testName=splitedAutoGenValue[12];
		var sampleconfig=splitedAutoGenValue[9];
	
		if(sampleconfig==1||sampleconfig==2)	 
		 {
			var autoGenFormate=CheckAutoSampleNoFormate(labCode,testCode,patType,sampleconfig,sampleArea);
			if(autoGenFormate!='null')
			{	document.getElementById(k+""+""+j+""+index+"unCheck").checked=true;
				var autoValue="#";
				autoValue+=autoGenFormate;
				document.getElementById(k+""+""+j+""+index+"unCheck").value+=autoValue;
			}
			else
				{
				if(sampleconfig==1)
				alert("No Sample Number Formate Is Configured For    "+  testName  + "("+ labName +")   Please Configure From Sample Number Configuration Master ");
				if(sampleconfig==2)
					alert("No Sample Number Formate Is Configured For  "+  testName  + " ( "+  labName  +") Lab  Please Configure From Collection Area Sample Number Configuration Master ");
				document.getElementById(k+""+""+j+""+index+"unCheck").checked=false;
				}
		 	 }
		else
			{}
		}
	else
		checked=false;
}
 

function CheckAutoSampleNoFormate(LabCode,TestCode,patType,tempSampleNo,sampleAreaCode)
{

	//alert("inside Ajax"+sampleArea);
	
	var flg = false;
	var autoGenFormate = "";
	var _mode = "AJX_CHECK_AUTO_SAMPLENO_GEN";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode="+_mode+"&labCode="+LabCode+"&testCode="+TestCode+"&patType="+patType+"&tempSampleNo="+tempSampleNo+"&sampleAreaCode="+sampleAreaCode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			autoGenFormate = data;
			flg = true;
		},
        error: function(error)
        {
            labTestCodeArray = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return autoGenFormate;
}

function onClickGO(hospitalCode,mode)
{
	var barcode=0;
	
	document.getElementsByName("modebarcode")[0].value=mode;
	
      if(document.getElementsByName("duplicateBarcodeGeneration")[0].checked==true)
          {
            barcode=1;
          }
	
	 var crno =document.getElementsByName("tempPatCRNo")[0].value;
     var textLength = crno.length;
     var hosCodeLen=hospitalCode.length;
     if(!validateTodayOrDate())
	 {return false;}
 
     if(hosCodeLen==3)
		{ 
          if (textLength==5||textLength==13||textLength==0)
	        {
             
        	  document.getElementsByName("patCRNo")[0].value="";
        	 
        	  if(textLength==13)
	           {
	             document.getElementsByName("patCRNo")[0].value=crno;
	            }
                  
             document.getElementById('goButton').style.display="none";
         	
         	if(barcode==1)
             	{
         		document.getElementsByName('showStatus')[0].value="3";
             	}
         	else
             	{
             	
         		document.getElementsByName('showStatus')[0].value="0";
         	document.getElementsByName('hmode')[0].value='GETPATLIST';
             	}
         	document.forms[0].submit();
        	   
             }

	          else
	          {     
	            alert("InValid CR No");
	            if(document.getElementsByName("tempPatCRNo")[0])
	            {
	            document.getElementsByName("tempPatCRNo")[0].focus()
	             }    
	           
	          }
       }
      if(hosCodeLen==5)
    	{ 
      	
			    if (textLength==7||textLength==15||textLength==0)
				 {
			  	  document.getElementsByName("patCRNo")[0].value="";
			    	
			    	if(textLength==15)
						 {
						 document.getElementsByName("patCRNo")[0].value=crno;
						 }
					         
					 document.getElementById('goButton').style.display="none";
					 
						if(barcode==1)
		             	{
		         		document.getElementsByName('showStatus')[0].value="3";
		         		document.getElementsByName('hmode')[0].value='GETPATLISTBAROCDE';
		             	}
		         	else
		             	{
		         		document.getElementsByName('showStatus')[0].value="0";
		         	document.getElementsByName('hmode')[0].value='GETPATLIST';
		             	}
						document.forms[0].submit();
					     }
					
			     else
			     {     
				   		    alert("InValid CR No");
				     		if(document.getElementsByName("tempPatCRNo")[0]){
				            document.getElementsByName("tempPatCRNo")[0].focus()
				                      }    
			          
			     }
          }
   return true;
}
function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}

/* function showSearchDivOnLoad()
{
	var check=document.getElementsByName("sampleAreaCode")[0].length;
	if(check!=1)
	{
		document.getElementById('goButton').style.display="";
    }
	else
	{
		document.getElementById('goButton').style.display="";
		document.getElementsByName("sampleAreaName")[0].value=document.getElementsByName("sampleAreaCode")[0].options[document.getElementsByName("sampleAreaCode")[0].selectedIndex].text;
		document.getElementsByName("isSampleAreaSelected")[0].value="1";
//		alert(document.getElementsByName("isSampleAreaSelected")[0].value);
//		document.getElementsByName('hmode')[0].value='GETPATLIST';
		//document.getElementsByName("hmode")[0].value="NEW";
//		document.forms[0].submit();
	}
	//alert(document.getElementsByName("saveConfirmFlag")[0].value);
	//alert(document.getElementsByName("barCodeGenSize")[0].value);
	 if(document.getElementsByName("saveConfirmFlag")[0].value!="")
		{
		 //alert("Print Barcode");
	//callThisOnload();
}
} */
//BARCODE GENERATION

function callThisOnload()
{

	$('#barcodecrno').focus();

	if(document.getElementsByName("flagforipddesk")!=null)
		{
		//alert(document.getElementsByName("flagforipddesk")[0].value);

		if(document.getElementsByName("flagforipddesk")[0].value=="10")
			{
			//alert("hello");

		    for(var a=0;a<document.getElementsByName("chkSamplePatient").length;a++)
		     {
		        	 //alert(document.getElementsByName("chkSamplePatient")[a].value);
		        	 //alert((document.getElementsByName("chkSamplePatient")[a].value).split("#")[6]);
		        	 document.getElementsByName("chkSamplePatient")[a].checked=true;
		        	 document.getElementsByName("flagforipddesk")[0].value="15";
		    		 		        	 

		    		      }
       	      
		    displaySamplePatDetails();

			}


		if(document.getElementsByName("duplicateBarcodeGeneration")[0]!=null && document.getElementsByName("duplicateBarcodeGeneration")[0].value=="0")
		{
		//alert("hello");

	
   	      
	 //  showduplicatebarcode();

		}
		
		}
    //alert(document.getElementsByName("modebarcode")[0].value);
	
	if(document.getElementsByName("modebarcode")[0].value=="1")
		{
	var cbs = document.getElementsByTagName('input');
	//alert(cbs);
	var objCurrentCheckBox="on";
 
	for(var i=0; i < cbs.length; i++) 
	{
		    if(cbs[i].type == 'checkbox') 
		    {
		    	if(objCurrentCheckBox=="on")
		    		objCurrentCheckBox=cbs[i].value;
		    	
		    	if(!cbs[i].checked)
		    		cbs[i].checked=true;
		      
		    	 if(cbs[i].checked && (cbs[i].value.split("#")[0]!=objCurrentCheckBox.split("#")[0]))
		    	{
		    //	alert("The list contains different CR Nos. Please select same CR Nos.");
		    	//document.getElementById('nextDiv').style.display="none";
		    	obj.checked=false;
		    	cbs[i].checked=false;
		    	
		    	document.getElementById('goButton').style.display="";
		    	
		    	
		    	
		    	return false;
		    	} 
			}
	}
	document.getElementsByName("modebarcode")[0].value="0";
	displaySamplePatDetails();
		}
	
	if(document.getElementsByName("saveConfirmFlag")[0].value!="")
		{
 	var url="/HISInvestigationG5/new_investigation/sampleCollection.cnt?hmode=PRINT";

	document.getElementsByName("saveConfirmFlag")[0].value="";
	document.getElementsByName("barCodeGenSize")[0].value="";
	
	openPopuper(url,180,500);
		}
}
function openPopuper(url,height, width)
{
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
	if(!child.opener)
   		child.opener = self;
  	return child;
}
function cancelFunc()
{
	if(document.getElementsByName("flagforipddesk")[0].value=="15")
{
	     if(parent.gotToMainTab)
	 		parent.gotToMainTab("Patient Dashboard", true);
	 	else if(parent.parent.gotToMainTab)
	 		parent.parent.gotToMainTab("Patient Dashboard", false);
	 		
		}
	else
		{
	window.parent.closeTab();
		}
}

/* function ShowRequistionForm(advice)
{
	var status=1;
	//alert(testCode);
	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
	mywindow=window.open (url1+"?advice="+advice+"&status="+status,"_blank","scrollbars=1,directories=no, status=no,width=700, height=500,top=200,left=500");
}
 */
function ShowRequistionForm(testCode,testName,labCode,labName,requisitionDNo,reqNoo)
{

	 var tr="";
	 var getData =  checkreqformTestType(testCode+"#"+reqNoo);
	 var newtest=getData.split("#")[2];
	 
	 var idd=testCode+"#"+reqNoo;
	 if(document.getElementById(idd)==null && (newtest!="0"))
		 {
		// alert("id null "+newtest);
	   tr = document.getElementById(newtest+"#"+reqNoo);
		 }
	 else
		 {
		// alert("id not null "+idd);
    	 tr = document.getElementById(idd);
		 }
    // alert("testt"+tr.testt);
    // alert("testt"+tr.value);
	 var td = tr.getElementsByTagName("td");
var nameee=td[0].innerHTML;
//alert(nameee);
testName=nameee;

    labName=tr.value;


              var overalltest="";
         for(var a=0;a<document.getElementsByName("chkSamplePatient").length;a++)
     {
        	 //alert(document.getElementsByName("chkSamplePatient")[a].value);
        	 //alert((document.getElementsByName("chkSamplePatient")[a].value).split("#")[6]);
        	 overalltest+=(document.getElementsByName("chkSamplePatient")[a].value).split("#")[6]+";";
        	 
      }
         
	// alert("overalltest"+overalltest);
	 
	 var status=1;
	var hmode="EXISTINGREQUISITIONFORMDATA";
	//alert(testCode);
	var url1="/HISInvestigationG5/new_investigation/requisitionformprocess.cnt";
	mywindow=window.open (url1+"?testCode="+testCode+"&testName="+testName+"&labCode="+labCode+"&labName="+labName+"&hmode="+hmode+"&status="+status+"&requisitionDNo="+requisitionDNo+"&reqformtestnames="+reqformtestnames+"&reqformtestcodes="+reqformtestcodes,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");
}
</script>

<script>
 /* PRASHANT SMC */
$(document).ready(function(){
	  $("#barcodecrno").on("mouseleave keypress keyup keydown change", function(){
	  
	  	var cronchange=$("#barcodecrno").val();
	  	
	    if(cronchange.length>=15)
	    { $(".ftdates").hide(); /* $(".ftdates").prop("disabled", true); */ }
	    else { $(".ftdates").show(); /* $(".ftdates").prop("disabled", false); */ }
	    });
	});
 
 
$(document).ready(
	    function setDataTable1(){
		//$.fn.dataTable.moment( 'DD-MMM-YYYY' );
	});
	
 
$(document).ready(
	    function setDataTable1(){
	    	$.fn.dataTable.moment( 'DD-MMM-YYYY' );
	    	if ($('#DataTable1').length)
	    	var table = $('#DataTable1').DataTable( {
	        	/* "dom": '<<ip><lf>rt>', */
				pageLength : 10,
				
				"language": {
				      "emptyTable": "No Data Is Available "
				    },
	            lengthMenu: [
	              [ 10, 25, 50, -1 ],
	              [ '10 rows', '25 rows', '50 rows', 'Show all' ]
	       	     ],
	             pagingType: "full_numbers",
	             rowReorder: true,
	             columnDefs: [
	             /* { orderable: true, className: 'reorder', targets: 0 }, */
	             { orderable: false, targets: 0 }
	             /*  { type: 'date', 'targets': [9] } */
	             ],
	             "deferRender": true
	     });
	});

	
$(document).ready(
	    function setDataTable2(){
	    	if ($('#DataTable2').length)
	        var table = $('#DataTable2').DataTable( {
	        	/* "dom": '<<ip><lf>rt>', */
				pageLength : 10,
				"language": {
				      "emptyTable": "No Data Is Available "
				    },
	            lengthMenu: [
	              [ 10, 25, 50, -1 ],
	              [ '10 rows', '25 rows', '50 rows', 'Show all' ]
	       	     ],
	             pagingType: "full_numbers",
	             rowReorder: true,
	             columnDefs: [
	             /* { orderable: true, className: 'reorder', targets: 0 }, */
	             { orderable: false, targets: 0 }
	             /*  { type: 'date', 'targets': [9] } */
	             ],
	             "deferRender": true
	            
	     });
	});
	

</script>
</body>