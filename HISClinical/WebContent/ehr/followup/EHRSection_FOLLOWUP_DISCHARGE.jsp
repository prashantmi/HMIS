<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/popup.js" />
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
</head>
<script type="text/javascript">

function validateAdd()
{
	var valid=true;
	if(document.getElementsByName("patStatus")[0].value==<%=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED%> )
	{
		if(comboValidation(document.forms[0].dischargeStatus,"Discharge Status")
			&& validateNextVisitDate()
			&& isEmpty(document.forms[0].dischargeRemarks,"Discharge Remarks")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
	{
		if(document.getElementsByName("revokeRemarks")[0].value=="")
		{
			alert("Please Enter The Revoke Remarks");
			document.getElementsByName("revokeRemarks")[0].focus();
			valid=false;
		}
	}	
	return valid;
}

function validate_ENC_FOLLOWUP_DISCHARGE()
{
	var valid=true;
	
	
	
	<%-- if(document.getElementsByName("patStatus")[0].value==<%=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED%> )
	 { --%> 
		if(comboValidation(document.forms[0].dischargeStatus,"Discharge Type")
				&& validateNextFollowUpDate()			
		)
			//&& isEmpty(document.forms[0].dischargeRemarks,"Discharge Remarks")
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	// } 
	//alert(valid);
	return valid;
}
function validateNextVisitDate()
{
	var nextVisitDate = document.getElementsByName("nextVisitDate")[0].value;
	// alert(nextVisitDate);
	
	var selected = convertStrToDate(nextVisitDate, 'dd-Mon-yyyy');
	var currDate = document.getElementsByName("currDate")[0].value;
//	alert(currDate);
	var current = convertStrToDate(currDate, 'dd-Mon-yyyy');
	
	if(document.getElementsByName("nextVisitDateFlag")[0].checked)
	{
		/* if(selected<=current)
			{
			alert("Next visit Date can not greater than or equals to Current Date");
			return false;
			} */
		if(document.getElementsByName("nextVisitDays")[0].value=="")
		{		
			if(document.getElementsByName("dischargeStatus")[0].value==10)
				{
				return true;
				}
			else
				{			
			alert("Please Enter The no of Days");
			document.getElementsByName("nextVisitDays")[0].focus();
			return false;
				}
		}
		else
			return true;
	}
	else
	{
		return validateNextDate();
	}
}

function validateNextDate()
{
	return true;
}

function showNextVisitDateOrDays()
{
	if(document.getElementsByName("nextVisitDateFlag")[0].checked)
	{
		document.getElementById("dayLabel").style.display="block";
		document.getElementById("dayControl").style.display="block";
		document.getElementById("dateLabel").style.display="none";
		document.getElementById("dateControl").style.display="none";
	}
	 else
	{	
		document.getElementById("dayLabel").style.display="none";
		document.getElementById("dayControl").style.display="none";
		document.getElementById("dateLabel").style.display="block";
		document.getElementById("dateControl").style.display="block";
	} 
}

window.onload = function()
{
	funct();
	/* showNextVisitDateOrDays(); */
}

function funct()

{
	//alert(document.getElementsByName("dischargeStatus")[0].value);		
	
	
	//ADDED BY SWATI
     if(document.getElementsByName("dischargeStatus")[0].value=='8'){
		
		document.getElementById("followupdate").style.display=""; 

		}
	else{
          if(document.getElementById("followupdate")){
		      document.getElementById("followupdate").style.display="none";
          }
		}


	if(document.getElementsByName("dischargeStatus")[0].value=='2')
		{
	
		document.getElementsByName("nextVisitDateFlag")[0].disabled = true;
		document.getElementsByName("nextVisitDateFlag")[1].disabled = true;
		document.getElementById("dayLabel").style.display="none";
		document.getElementById("dayControl").style.display="none";
		document.getElementById("dateLabel").style.display="none";
		document.getElementById("dateControl").style.display="none"; 
		}
	else
		{
		document.getElementsByName("nextVisitDateFlag")[0].disabled = false;
		document.getElementsByName("nextVisitDateFlag")[1].disabled = false;
		}


}

   function showEmployeepopup(e,fieldToPopulate,index)
   {
	//var dept = document.getElementsByName("deptname")[0].value;
	var dept = "";
	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index+"&deptname="+dept
	openPopup(createFHashAjaxQuery(path),e,300,600);
   }

    function showEmployeePopupForPreparedBy(e,fieldToPopulate,index)
   {
	var dept = "";
	var path="/HISClinical/mrd/employeePopUp.cnt?hmode=DISCHARGEPREPAREDBY&fieldToPopulate="+fieldToPopulate+"&index="+index+"&deptname="+dept
	openPopup(createFHashAjaxQuery(path),e,300,600);
   } 

 function validateNextFollowUpDate()
 {
	 //alert(document.getElementsByName("dischargeStatus")[0].value);
	 
	 if(document.getElementsByName("dischargeStatus")[0].value=='8'){
	 
	 		var nextVisitDate = document.getElementsByName("nextVisitDate")[0].value;
			var selected = convertStrToDate(nextVisitDate, 'dd-Mon-yyyy');
			var currDate = document.getElementsByName("currDate")[0].value;
            var curr = convertStrToDate(currDate, 'dd-Mon-yyyy');
			//alert(curr);

           

           if(selected<=curr)
           {
             alert("Next Visit Date Must be greater than Today Date");
             return false;
           }

           
			
			//alert("selected:"+selected);
			//alert("currDate:"+currDate);
			
			//var d1 = Date.parse(nextVisitDate);
        	//var d2 = Date.parse(currDate);
        	
        	//alert("d1=="+d1+"==d2=="+d2);
        	
        	//var d = new Date(nextVisitDate);
	        //alert(d);
	
	
           // month = '' + (d.getMonth() + 1),
           // day = '' + d.getDate(),
            //year = d.getFullYear();

   // if (month.length < 2) month = '0' + month;
    //if (day.length < 2) day = '0' + day;

   // const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                  //  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
               //   ];
    //var mon = monthNames[d.getMonth()];

    //var selectedFollowUpDate = [day, mon, year].join('-');
        	
     // alert("selectedFollowUpDate:"+selectedFollowUpDate); 	
      //alert("currDate:"+currDate);

      /* var selectedFollow = convertStrToDate(selectedFollowUpDate, 'dd-Mon-yyyy');
      var currdate1 = convertStrToDate(currDate, 'dd-Mon-yyyy');

      alert(selectedFollow"::"+currdate1); */
         	
      
       //var date1 = Date.parse(currDate);
       //var date2 = Date.parse(selectedFollowUpDate);

   	   //alert("currDate:"+date1);
   	   //alert("selectedDate:"+date2);
   	   //alert(date1-date2);
        	/* if (date2 <= date1) {
                alert ("Next Visit Date Must be greater than Today Date");
         		document.getElementById("nextVisitDate").focus(); 
         	
         	 return false;
         	} */
	 }
	 
	 else
		 {
		 document.getElementById("nextVisitDate").value = "";
		 }

		return true;
	   //return false;
 }   




 
 function enableImage(e)
 {
   //alert(e);
   if(e=='ImgdischargeBy')
	   {
	     document.getElementById('ImgdischargeBy').style.display="block";
	   }
   if(e=='ImgdischargeApprovedBy')
	   {
	     document.getElementById('ImgdischargeApprovedBy').style.display="block";
	   } 
 }
</script>
	

		<table class="table table-condensed table-responsive">

<%-- 				<bean:define name="EHRSection_FollowupFB" property="sysDate" id="sysDate" type="java.lang.String"/> --%>
<%-- 				<bean:define name="EHRSection_FollowupFB" property="dischargeStatus" id="dischargeStatus" type="java.lang.String"/> --%>

				<% // String sysDate="";  
				    String nextDate="";
				
				%>

				
				<% 
				   
				nextDate=(String)session.getAttribute("nextVisitDate");
				System.out.println("nextDate=++###="+nextDate);
				if(nextDate==null || nextDate.equals("")){
					nextDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
					
					String dischargeDate =  WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				}
				
				%>
<%-- 				<html:hidden name="EHRSection_FollowupFB" property="sysDate" value="<%=sysDate%>"/>	 --%>
				<tr>
				<td style="font-size:1.2em;font-weight:bold;"width="100%" colspan="3">
				Follow Up&nbsp;:
				<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand; display:none;"/> 
				</td>
				</tr>
				<tr>
					<td width="15%" >
						<div align="right">
						
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="discharge" />
								
									Type
								</b>
							
						</div>
					</td>
					<td width="85%" colspan="3" >
						<div align="left">
							<html:select name="EHRSection_FollowupFB" property="dischargeStatus" tabindex="1" onchange="funct();" styleClass="form-control" style="width:200px;">
								<html:option value="-1">Select value</html:option>
								<logic:present name="<%=InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST %>">
									<html:options collection="<%=InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST %>"  property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
					
				</tr>
				<tr id="followupdate">
					<td width="15%"  >
						<div align="right" id="nextvisit">
						
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="nextVisitDate" />
								</b>
						
						</div>
					</td>
					<td width="75%"   colspan="2" >
					<%if(nextDate.equals("-")) {%>
					<div align="left" id="nextvisitdate">
					-
					</div>
					<%}else{ %>
					
						<div align="left" id="nextvisitdate">
							<%-- <html:radio name="EHRSection_FollowupFB" property="nextVisitDateFlag" value="<%=InpatientConfig.NEXT_VISIT_SELECTION_DAYS %>" onclick="showNextVisitDateOrDays()"></html:radio>Days --%>
							<%-- <html:radio name="EHRSection_FollowupFB" property="nextVisitDateFlag" value="<%=InpatientConfig.NEXT_VISIT_SELECTION_DATE %>" onclick="showNextVisitDateOrDays()"></html:radio>Date --%>
							<his:date name="nextVisitDate" dateFormate="%d-%b-%Y" value="<%=nextDate %>"></his:date>
						</div>
						<%} %>
					</td>
					
				</tr>
				
				<tr>
				   <td width="15%"  >
				     <div align="right">
						  <font color="#FF0000">*</font>
							<b>
				               <bean:message key = "dischargedBy"/>
				        
				            </b>
				            </div>
				      </td>
				    <td width="20%"   >
						<div align="left">
						    <html:hidden name="EHRSection_FollowupFB" property="requestById" />
                            <html:hidden name="EHRSection_FollowupFB" property="requestByEmpDept" />
							<html:text name="EHRSection_FollowupFB" property="requestByName" readonly = "true" styleClass="form-control" onclick="enableImage('ImgdischargeBy');"></html:text>
							  						</div>
					</td>
					<td width="75%">
					<img class="button" id = "ImgdischargeBy" style="display:none;"src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" 
							   onclick="showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" alt="Search Employee" title="Search Consultant">							
                            <html:hidden name="EHRSection_FollowupFB" property="requestByName"/>  
                            <b>                           
				             <% 
				                 String dischargeDate="";
				
				            %>
				
				            <% 	   
				            dischargeDate=(String)session.getAttribute("dischargeDate");
				            System.out.println("DischargeDate=++###="+dischargeDate);
				            if(dischargeDate==null || dischargeDate.equals("")){
				        	dischargeDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

				           }
				            
				           %>
				             <p>Discharge Date:<%= dischargeDate%></p>
				            </b>  
				            <html:hidden name="EHRSection_FollowupFB" property="dischargeDate" value="<%=dischargeDate%>"/>
					</td>
				</tr>
				 <tr>
				   <td width="15%" >
				     
						  <font color="#FF0000">*</font>
							<b>
				              Discharge Prepared By
				            </b>
				         
				   </td>
				    <td width="20%"  >
						<div align="left">
						    <html:hidden name="EHRSection_FollowupFB" property="dischargePreparedById" />
                            <html:hidden name="EHRSection_FollowupFB" property="dischargePreparedByDept" />
							<html:text name="EHRSection_FollowupFB" property="dischargePreparedByName" readonly = "true" styleClass="form-control"></html:text>
							  </div>
					</td>
					<td width="75%">
					<img class="button" src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeePopupForPreparedBy(event,'dischargePreparedById@dischargePreparedByName@dischargePreparedByDept','<%=0 %>');" 
							   onclick="showEmployeePopupForPreparedBy(event,'dischargePreparedById@dischargePreparedByName@dischargePreparedByDept','<%=0 %>');" alt="Search Employee" title="Search Consultant">							
                            <html:hidden name="EHRSection_FollowupFB" property="dischargePreparedByName"/>    
                             <b>                           
				             <% 
				                 String dischargePrepareDate="";
				
				            %>
				
				            <% 	   
				            dischargePrepareDate=(String)session.getAttribute("dischargePreparedDate");
				           System.out.println("dischargePrepareDate=++###="+dischargePrepareDate);
				           if(dischargePrepareDate==null || dischargePrepareDate.equals("")){
				        	   dischargePrepareDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

				          }
				
				           %>
				             <p>Discharge Prepare Date:<%= dischargePrepareDate%></p>
				            </b>   
				            <html:hidden name="EHRSection_FollowupFB" property="dischargePreparedDate" value="<%=dischargePrepareDate %>"/>
					</td>	
				</tr>
				<tr>
				   <td width="15%">
				<div align="right">
						  <font color="#FF0000">*</font>
							<b>
				              Discharge Approved By
				            </b>
				     </div>           
				   </td>
				    <td width="20%">
						<div align="left">
						    <html:hidden name="EHRSection_FollowupFB" property="dischargeApprovedById" />
                            <html:hidden name="EHRSection_FollowupFB" property="dischargeApprovedByDept" />
							<html:text name="EHRSection_FollowupFB" property="dischargeApprovedByName" readonly = "true" styleClass="form-control" onclick="enableImage('ImgdischargeApprovedBy');"></html:text>
							  
													</div>
					</td>
					<td width="75%">
					  <img class="button" id = "ImgdischargeApprovedBy" style="display:none;" src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" align="left" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeePopupForPreparedBy(event,'dischargeApprovedById@dischargeApprovedByName@dischargeApprovedByDept','<%=0 %>');" 
							   onclick="showEmployeePopupForPreparedBy(event,'dischargeApprovedById@dischargeApprovedByName@dischargeApprovedByDept','<%=0 %>');" alt="Search Employee" title="Search Consultant">							
                            <html:hidden name="EHRSection_FollowupFB" property="dischargeApprovedByName"/>    
					
					</td>
				</tr>					
				<tr>
					<td width="15%">
						<div align="right">
						
								<b>
									Follow Up Advice
								</b>
							</font>		
						</div>
					</td>
					<td width="75%" colspan="2"  >
						<div align="left">
						
						<div class="form-group col-xs-12 col-md-11 col-sm-12">
						<html:textarea name="EHRSection_FollowupFB" property="dischargeRemarks" rows="1" cols="75"  styleClass="form-control dischargeRemarks"  onkeypress="return validateText(event)" style="height:70px"></html:textarea>
                         <%--  <html:textarea class="clearable ui-autocomplete-input x onX form-control" name="EHRSection_FollowupFB" property="dischargeRemarks" size="2000" onkeypress="return validateText(event)" onchange="setFreeText();"></html:textarea> --%>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('.dischargeRemarks').val('');" class="btn btn-sm btn-default BtnCleanfolloUp"><i class="fa fa-times"></i></button>
							</div>
                        </div> 
                        
							<%-- <html:textarea name="EHRSection_FollowupFB" property="dischargeRemarks" rows="1" cols="75" styleClass="form-control" style="width:95%;" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"></html:textarea> --%>
						</div>
					</td>
					
				</tr>
			
		</table>

	
	
<html:hidden name="EHRSection_FollowupFB" property="hmode"/>
<html:hidden name="EHRSection_FollowupFB" property="patCrNo"/>
<html:hidden name="EHRSection_FollowupFB" property="patStatus"/>
<html:hidden name="EHRSection_FollowupFB" property="currDate"/>
<input type='hidden' name='nextVisitDateSinglePage'  value='<%=session.getAttribute("nextVisitDate") %>' />




	
<%-- <html:hidden name="EHRSection_FollowupFB" property="dischargeStatusName"/>	
 --%>	
	

