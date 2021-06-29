<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %> 
<%@page import="org.apache.struts.tiles.ComponentContext"%>
<%@ page import ="hisglobal.presentation.*;" %>
<html:html> 
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
	<title><tiles:getAsString name="title"/></title> 	
	<script>
	  // Global Variables Start
      window.onload=function(){		 
      		 valFromHr=document.getElementsByName('fromHour')[0].value;
			 valToHr=document.getElementsByName('toHour')[0].value;
			 valToMin=document.getElementsByName('toMin')[0].value;
    		 valFromMin=document.getElementsByName('fromMin')[0].value;
    }		       
  //   Global Variables End
  
	//window.history.forward()
	function clearReport(){
    document.getElementsByName('reportMode')[0].value="NEW";  	   	    
	     document.forms[0].submit();
    }
    function cancelReport(){
    document.getElementsByName('mode')[0].value="CANCEL";  	   	    
	     document.forms[0].submit();
    }
    
	function submitReport(){
	   val=document.getElementsByName('mode')[0].value;
	   
	   
	    if(val=="DEPARTMENTWISEOUTPATIENTSREPORT")
       {
       	if(departmentWiseOutPatientReportValidtion())
       	{
       		document.forms[0].submit();
       	}
       }
       else
       if(val=="FOLLOWUPPATIENTSREPORT")
       {
       	if(followUpPatientsReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       else
       if(val=="LISTOFPOORFREEPATIENTSREPORT")
	   {
	   	if(listOfPoorFreePatientsHandler())
	   	{
	   		document.forms[0].submit();
	   	}
	   }
	    else
       if(val=="FEESCOLLECTEDFOROPDSPECIALCLINICREPORT")
	   {
	   	if(deptUnitWiseCashCollectionReportHandler())
	   	{
	   		document.forms[0].submit();
	   	}
	   }
	     else
       if(val=="SPECIALCLINICPATSTATREPORT")
       {
       	if(specialClinicUnitPatStatisticsReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       else
       if(val=="NOOFANONYMOUSPATIENTLISTREPORT")
	   {
	   	if(noOfAnonymousPatientListReportHandler())
	   	{
	   		document.forms[0].submit();
	   	}
	   }
	    else
       if(val=="ANONYMOUSPATANDDETWHOBROUGHTREPORT")
       {
       	if(anonymousPatAndDetWhoBroughtReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
        else
       if(val=="MLCPATIENTSLISTPGIREPORT")
       {
       	if(mlcPatientsListPgiReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
	   else
       if(val=="SERVICEUNITWISEREVISITPATREPORT")
       {
       	if(serviceUnitWiseRevisitPatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       else
        if(val=="SERVICEUNITWISENEWREGREPORT")
       {
       	if(serviceUnitWiseNewRegReportHandler())
       	{
       		document.forms[0].submit();
       	}
       } 
        else
        if(val=="AGEWISEREPORT")
       {
       	if(ageWiseReportHandler())
       	{
       		document.forms[0].submit();
       	}
       } 
		else      
       if(val=="HOSPITALPATSTATREPORT")
       {
       	if(newhospitalPatStatReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
	   else       
       if(val=="HOSPITALPATSTATSTATEREPORT")
       {
      if(newhospitalPatStatStateReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
 	  else       
       if(val=="CONSULTANTROSTERREPORT")
       {
      if(consultantRosterReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
      else       
       if(val=="DEPTWISELISTOFPATIENSREPORT")
       {
      if(deptWiseListOfNewPatientsReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
      else       
       if(val=="DEPTWISEUSERWISEPATLIST")
       {
      if(deptWiseUserWiseListOfPatientsReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
      else       
       if(val=="FREENEWDEPTVISITREPORT")
       {
      if(freeNewDeptVisitReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }
       else       
       if(val=="DEPARTMENTPERCENTAGEREPORT")
       {
      if(departmentPercentWiseReportHandler())
       	{
       	
       		document.forms[0].submit();
       	}
       }
      else       
       if(val=="SPECIALOUTPATIENTSTATISTICS")
       {
      if(specialOutPtientReportHandler())
       	{
       	
       		document.forms[0].submit();
       	}
       }
       
       else       
       if(val=="HOSPITALWISEPATLIST")
       {
      if(hospitalWisePatientListingReportHandler())
       	{
       	
       		document.forms[0].submit();
       	}
       }
       else       
       if(val=="RELIGIONCATEGORYWISEPATLIST")
       {
      		if(religionCategoryWisePatientListingReportHandler())
       	{
       	
       		document.forms[0].submit();
       	}
       }
        	  		 	 	  	     	  	 	  	 	 	 
		else if(val=="PATIENTLISTINGREPORT")    
		{
			if(patientListingReportHandler())
			{
				document.forms[0].submit();
			}
		}
		
	  	else if(val=="LISTOFPATIENTSREFERINOUTREPORT")
       {
       	if(listOfPatientsReferInOutReportHandler())
       	{
       		document.forms[0].submit();
       	}
       }   	 
		 	  	  		 	 	  	     	  	 	  	 	 	 
				
		else if(val=="USERWISEREGISTRATION")    
		{
			if(userWiseRegistrationReportHandler())
			{
				document.forms[0].submit();
			}
		}
		
		    else       
       if(val=="CATEGORYWISEUSERWISEREGISTRATION")
       {
      if(categoryWiseUserWiseRegistrationReportHandler())
       	{
       	
       		document.forms[0].submit();
       	}
       }
        else
         if(val=="PATWISEVISITDTLRPT")    
       {
        if(patWiseVisitDtlReportHandler()){
             document.forms[0].submit();
           }
      
       }
         else       
             if(val=="MCTSPATLIST")
             {
      	      if(mctsPatListHandler())
      	       	{
      	       	
      	       		document.forms[0].submit();
      	       	}
             }
         
         
      else if(val=="AMBULANCEWISECASESBROUGHT")    
		{
			if(ambulanceWiseCasesBroughtReportHandler())
			{
				document.forms[0].submit();
			}
		}	 	 	  	  		 	 	  	     	  	 	  	 	 	 
	 }
	 
		 
	 function validateGraphOrText(){
	 if(document.getElementsByName('graphOrText')[0].selectedIndex==0){
         alert("Please select Report Type Option");
         document.getElementsByName('graphOrText')[0].focus(); 
         return false;   
         }
         else
         return true;
    }
  function validateTextual(){
      if(document.getElementsByName('reportType')[0].selectedIndex==0){
          alert("Please select Acrobat Reader or HTML");
          document.getElementsByName('reportType')[0].focus();
          return false;   
         }
         else
         return true;
    }
    
    
    function validiateGraphical(){
      if(document.getElementsByName('chartType')[0].selectedIndex==0){
          alert("Please select Chart Type");
          document.getElementsByName('chartType')[0].focus();
          return false;   
         }
         else
         return true;
    }
    
    function validateUserCombo() 
    {
    	if(document.getElementsByName('user')[0].selectedIndex==0)
    	{
    		alert("Please select User Option");
    		document.getElementsByName('user')[0].focus();
    		return false;
    	}
    	else
    		return true;
    }
    
    function  validatePatCatCombo()
    {
    	if(document.getElementsByName('patCatCode')[0].selectedIndex==0)
    	{
    		alert("Please select Patient Category");
    		document.getElementsByName('patCatCode')[0].focus();
    		return false;
    	}
    	else
    		return true;
    }
    
    function validiatePatListing(){
      if(document.getElementsByName('patListing')[0].selectedIndex==0){
          alert("Please select the type of Patient");
          document.getElementsByName('patListing')[0].focus();
          return false;   
         }
         else
         	return true;
    }
    
    function validateTodayOrDate(){
 //   alert("inside validateTodayOrDate")
     success=false;        	   
           if(document.getElementsByName('choice')[0]){
			if(document.getElementsByName('choice')[0].checked){
                  
                if(validateHrMin())
                {
                  success=true;     
                 }
            }
            }
            //case Date
            if(document.getElementsByName('choice')[1]){
            if(document.getElementsByName('choice')[1].checked){
           //    alert("inside date check")
               valFromDate=document.getElementsByName('fromDate')[0];
	           valToDate=document.getElementsByName('toDate')[0];
	           valSysDate=document.getElementsByName('sysDate')[0];
	           
	           
	        if(compareDateCall(valFromDate,valToDate,2,"from date","to date") && compareDateCall(valToDate,valSysDate,2,"to date","system date"))
               {
                success=true;
               }    
               
            }  
            }
            return success;        
    } 
    

     
function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" can not be greater than "+l2);
	valid = false;
	}
}

else
valid=false;

return valid;
}



function compareDate(frDate,toDate,mode)
{
	var frValue, toValue,frYear, frMon, frDay,sts = 0;

	//alert("compare date " + frDate);
	//alert("compare date " + toDate);
	if (frDate.value == "" || frDate == null)
	{
		//alert("if,,,,,,, 1");
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		//alert("else1");
		if (isDate(frDate,mode) == true)
		{
		//alert("if 22222222");
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;

			if (isDate(toDate,mode) == true)
			{
		//	alert("if in else");
				if (frYear > intYear)
					sts = 1;
				else
				{
					if (frYear == intYear)
					{
						if (frMon > intMon)
							sts = 1;
						else
						{
							if (frMon == intMon)
							{
								if (frDay > intDay)
									sts = 1;
							}
						}
					}
				}
			}
			else
			{
			//alert("false");
				toDate.focus();
				return false;
			}
		}
		else
		{
		//	alert("false");
			frDate.focus();
			return false;
		}
	}

	if (sts == 1)
	{
		if (frDate.value == "" || frDate == null)	{
			//validating current date with toDate
		//	alert("if................................blank");
			//getMsg(5,toDate.name);
			}
		else
			//alert(frDate.name + " is greater than " + toDate.name);

		frDate.focus();
		return false;
	}

	return true;

}



	 function compareTime(){ 
	    if(valFromHr > valToHr)
            {
              alert("From hr cannot be greater than to hour");
              document.getElementsByName('fromHour')[0].focus();            
              return false;
            }
            if(valFromHr == valToHr)
            {
               if(valFromMin >= valToMin)
               {
                 alert ("From min should be less than to min");
                 document.getElementsByName('fromMin')[0].focus(); 
                 return false;                            
               }
            }
            return true;
        }   
	
	function validateHrMin(){
	   success=false;
	   if(validateFromHr() && validateFromMin() && validateToHr() && validateToMin()){
	       if(compareTime()){
    	       success=true;
    	   }    	    
	    }       
      return success ;
	}
	
     
     
	function validateFromHr(){	
	 valFromHr=document.getElementsByName('fromHour')[0].value;
      if(valFromHr==null||valFromHr==""){	  	       
	      alert("Please Enter value in From Hr Field");	  	      
	      document.getElementsByName('fromHour')[0].focus();
	      return false;     
	  }
	  if(valFromHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('fromHour')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
        
    function validateToHr(){	 
      valToHr=document.getElementsByName('toHour')[0].value;
	  if(valToHr==null||valToHr==""){	  	       
	      alert("Please Enter value in To Hr Field");	  	      
	      document.getElementsByName('toHour')[0].focus();
	      return false;     
	  }
	  if(valToHr>23){
	    alert("Hr Value canont be greater than 23");
	    document.getElementsByName('toHour')[0].focus();
	    return false;     
	  }
	  return true;	  
	} 
	
	function validateToMin(){	 
      valToMin=document.getElementsByName('toMin')[0].value;
	  if(valToMin==null||valToMin==""){	  	       
	      alert("Please Enter value in To Min Field");	  	      
	      document.getElementsByName('toMin')[0].focus();
	      return false;     
	  }
	  if(valToMin>59){
	    alert("To Min Value canont be greater than 59");
	    document.getElementsByName('toMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}
	
	function validiateGenderCombo(){
	if(document.getElementsByName('patGenderCode')[0].selectedIndex==0){
          alert("Please select Gender option");
          document.getElementsByName('patGenderCode')[0].focus();
          return false;   
         }
         else
         return true;
	}
	function validateFromMin(){	 
      valFromMin=document.getElementsByName('fromMin')[0].value;
	  if(valFromMin==null||valFromMin==""){	  	       
	      alert("Please Enter value in From Min Field");	  	      
	      document.getElementsByName('fromMin')[0].focus();
	      return false;     
	  }
	  if(valFromMin>59){
	    alert("From Min Value canont be greater than 59");
	    document.getElementsByName('fromMin')[0].focus();
	    return false;     
	  }
	  return true;	  
	}
	
	function validateGraphForAgeWiseReport(){
	if(validiateGraphical() && validiateGenderCombo()&& validateDepartmentCombo()&& validatePrimCatCombo() )
          return true;
         else
          return false; 
    }
    function validateGraphForCategoryWiseReport()
    {
    	if(validiateGraphical() && validiateGenderCombo() && validateUserCombo() && validatePatCatCombo())
    		return true;
    	else
    		return false;
    }
    
    function validateGraphForEmergencyRegPatReport(){
	 if( validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }
    
     function validateGraphForGroupWiseCashCollReport(){
	  if( validiateGraphical())
          return true;
         else
          return false; 
    }
    
    function validateGraphForMlcPatientListReport(){
	 if( validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }
    
     function validateGraphForBroughtDeadPatientReport(){
	 if( validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }
    
     function validateGraphForPinCodeWiseReport(){
	 if( validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }

    function validateGraphForDepartmentWiseRegPatReport(){
	 if(validateDepartmentCombo())
          return true;
         else
          return false; 
    }
    
    function validateGraphForDepartmentWiseTotalPatReport(){
	 if(validateDepartmentCombo() && validiateGraphical())
          return true;
         else
          return false; 
    }
    
    function validateGraphForRoomWiseTotalPatReport(){
	 if(validateRoomCombo() && validiateGraphical())
          return true;
         else
          return false; 
    }
    
    function validateGraphForTotalPatStatReport(){
	 if( validatePrimCatCombo() && validiateGraphical())
          return true;
         else
          return false; 
    }
    
    
    function validateGraphUserWiseCashCollReport(){
	 if( validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }
    
     function validateGraphUserWisePatListReport(){
	 if( validatePrimCatCombo()&& validateSecCatCombo())
          return true;
         else
          return false; 
    }
    
    function validateDepartmentCombo(){	
      if(document.getElementsByName('departmentCode')[0].selectedIndex==0){
         alert("Please select department");
         document.getElementsByName('departmentCode')[0].focus();
         return false;
         }
         else
         return true;       
       }
       
    function validateRoomCombo(){	
     if(document.getElementsByName('roomCode')[0].selectedIndex==0){
         alert("Please select room");
         document.getElementsByName('roomCode')[0].focus();
         return false;
         }
         else
         return true;       
       }
    
    function validatePrimCatCombo(){
     if(document.getElementsByName('patPrimaryCatCode')[0].selectedIndex==0){
         alert("Please select Primary Category");
         document.getElementsByName('patPrimaryCatCode')[0].focus();
         return false;
         }
         else
         return true;       
       }    
    
    function validateSecCatCombo(){	
     if(document.getElementsByName('patSecondaryCatCode')[0].selectedIndex==0){
         alert("Please select Secondary Category");
         document.getElementsByName('patSecondaryCatCode')[0].focus();
         return false;
         }
         else
         return true;       
       }    
       
       function moveToRightBox(elem, evt){
		val=elem.value;
		maxLength =elem.getAttribute('maxlength');
		i=0;
		if(val.length==maxLength){
			for(i=0; i<prevValue.length;i++)
				if(prevValue.charAt(i)!=val.charAt(i))
					break;
		}
		if(i==maxLength-1){
			if(elem.name == 'fromHour'){
				document.getElementsByName("fromMin")[0].focus();
			}else if(elem.name == 'fromMin'){
				document.getElementsByName("toHour")[0].focus();
			}else if(elem.name == 'toHour'){
				document.getElementsByName("toMin")[0].focus();
			}
		}	
	}
	
	function setPreviosValue(elem, evt){
			prevValue = elem.value;
	}  
	function checkTodayDatewise()
	{
	if(document.getElementsByName('choice')[0]){
	if(document.getElementsByName('choice')[0].checked)  
		showdivtoday()
	else
		showdivdatewise()
	}
	}

	window.onload=function(){
		checkTodayDatewise();
	}  
	 
</script>
</head>
 
<body> 
<his:TransactionContainer>
<html:form action="/report1/report">
<%WebUTIL.setAttributeInSession(request,"mode",request.getParameter("mode"));  %>
<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);

String header=(String)compContext.getAttribute("header");
String body=(String)compContext.getAttribute("body");
String footer=(String)compContext.getAttribute("footer");

%>
<jsp:include page="<%=header %>" flush="true"></jsp:include>
<jsp:include page="<%=body %>" flush="true"></jsp:include>
<jsp:include page="<%=footer %>" flush="true"></jsp:include>
</html:form>
<his:status/>
</his:TransactionContainer>
</body> 
</html:html> 
