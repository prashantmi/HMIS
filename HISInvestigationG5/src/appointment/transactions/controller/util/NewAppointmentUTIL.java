package appointment.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.SMSSender.SMSHttpPostClient;
import hisglobal.utility.SMSSender.config.SMSConfig;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;


import vo.appointment.NewAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.data.NewAppointmentDATA;



public class NewAppointmentUTIL extends ControllerUTIL
{

	public static void getGenderList(HttpServletRequest objRequest) {
		try{
			List lstGender =NewAppointmentDATA.getGenderList();
			objRequest.getSession().setAttribute(AppointmentConfig.LSTGENDER, lstGender);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void getDepartmentList(HttpServletRequest objRequest) {
		UserVO objUserVO =getUserVO(objRequest);
		try{
			List lstDepartment =NewAppointmentDATA.getDepartmentList(objUserVO);
			objRequest.getSession().setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, lstDepartment);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void getAgeType(HttpServletRequest objRequest,NewAppointmentSUP objNewAppointmentSUP_p )
	{
		List<Entry> lstAgeType = new ArrayList<Entry>();
		Entry objEntry = new Entry((String) "Years", "Yr");
		lstAgeType.add(objEntry);
		objNewAppointmentSUP_p.setPatAgeUnit("Yr");
		
		objEntry = new Entry((String) "Months", "Mth");
		lstAgeType.add(objEntry);
		
		objEntry = new Entry((String) "Weeks", "Wk");
		lstAgeType.add(objEntry);
		
		objEntry = new Entry((String) "Days", "D");
		lstAgeType.add(objEntry);
		objRequest.getSession().setAttribute(AppointmentConfig.LSTAGETYPE, lstAgeType);		
	}
	public static void getAppointmentMode(HttpServletRequest objRequest,NewAppointmentSUP objNewAppointmentSUP_p )
	{
		List<Entry> lstAppointmentMode = new ArrayList<Entry>();
		Entry objEntry = new Entry((String) "In Hospital", AppointmentConfig.APPPOINTMENT_MODE_IN_HOSPITAL);
		lstAppointmentMode.add(objEntry);
		objNewAppointmentSUP_p.setAppointmentMode(AppointmentConfig.APPPOINTMENT_MODE_IN_HOSPITAL);
		
		objEntry = new Entry((String) "By Phone", AppointmentConfig.APPPOINTMENT_MODE_BY_PHONE);
		lstAppointmentMode.add(objEntry);
		
		objRequest.getSession().setAttribute(AppointmentConfig.LSTAPPOINTMENTMODE, lstAppointmentMode);		
	}
	
@SuppressWarnings({ "unchecked", "rawtypes" })
public static void setPatientDtlByCrno( NewAppointmentSUP objNewAppointmentSUP_p,HttpServletRequest objRequest_p){
		
		System.out.println("NewAppointmentUTIL :: setPatientDtlByCrno()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objNewAppointmentSUP_p.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
		Map patDtlMap=new HashMap();
		try{
			objPatientVO=PatientVisitDATA.getPatientDtl(objPatientVO,objUserVO, visitType);
			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			patDtlMap.put(RegistrationConfig.APT_PATIENT_VO,objPatientVO);
			WebUTIL.setMapInSession(patDtlMap, objRequest_p, "NewAppointmentAction");
			objNewAppointmentSUP_p.setAfterGo("1");
			objNewAppointmentSUP_p.setMobileNo(objPatientVO.getPatAddMobileNo());
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   		{
	   			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   			patDtlMap.put(RegistrationConfig.APT_PATIENT_VO,objPatientVO);
				WebUTIL.setMapInSession(patDtlMap, objRequest_p, "NewAppointmentAction");
	   		}
	   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
		}
		catch(HisDuplicateRecordException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		objNewAppointmentSUP_p.setErrorMessage(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	public static void getActualParaIdWiseDetail(NewAppointmentSUP objNewAppointmentSUP_p,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		
		String jsonString="";
		int maxdisplayOrder=0;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			jsonString =NewAppointmentDATA.getAppointmentTypeList(objNewAppointmentSUP_p.getActualParameterReferenceId(),userVO);
			jsonString=  "{\"optionHTML\":\"" + jsonString + "\"}";
			System.out.println("json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	public static void SaveNewAppointment(	NewAppointmentSUP objNewAppointmentSUP_p,HttpServletRequest objRequest) {
		
		Status status=new Status();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			NewAppointmentVO objNewAppointmentVO= new NewAppointmentVO(); 
			HelperMethods.populate(objNewAppointmentVO, objNewAppointmentSUP_p);
			String[] actualParaId= new String[7];
			if(objNewAppointmentSUP_p.getActualParameterId()!=null &&  objNewAppointmentSUP_p.getActualParameterId().length>0){
				for(int i=0;i<objNewAppointmentSUP_p.getActualParameterId().length;i++){
					actualParaId[i]=objNewAppointmentSUP_p.getActualParameterId()[i];
				}
				for(int i=objNewAppointmentSUP_p.getActualParameterId().length; i<7;i++){
					actualParaId[i]="0";
				}
				objNewAppointmentVO.setActualParameterId(actualParaId);
				objNewAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_NEW);
				objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE);
				
				PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.APT_PATIENT_VO);
				if(objPatientVO!=null)
					HelperMethods.populate(objNewAppointmentVO, objPatientVO);
				else
					objNewAppointmentVO.setPatCrNo(null);
				objNewAppointmentVO= NewAppointmentDATA.SaveNewAppointment(objNewAppointmentVO,userVO);
				/*  ## 		Modification Log							
		 		##		Modify Date				:26thFeb'15 
		 		##		Reason	(CR/PRS)		:SMS Integration
		 		##		Modify By				:Sheeldarshi */
				
				//SMS Code Commented by Singaravelan on 20-Mar-2015

				/*SMSConfig objSMSConfig=new SMSConfig();
				String   message  = "You have successfully booked an appointment in "+objNewAppointmentSUP_p.getDepartmentUnitName()+" on "+objNewAppointmentVO.getAppointmentDate()+ " at " +objNewAppointmentVO.getAppointmentTime()+" with Reference No. "+objNewAppointmentVO.getAppointmentNo();
				SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url, objNewAppointmentVO.getMobileNo(),message);
				*///End:Sheeldarshi 
				status.add(Status.DONE,"Appointment booked successfully with reference No. "+objNewAppointmentVO.getAppointmentNo()+" " ,"");
				objRequest.getSession().removeAttribute(RegistrationConfig.APT_PATIENT_VO);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
		
	}
	public static void ConfirmAppointment(NewAppointmentSUP objNewAppointmentSUP_p,HttpServletRequest objRequest) {
		try
		{
			UserVO userVO=getUserVO(objRequest);
			NewAppointmentVO objNewAppointmentVO=(NewAppointmentVO) objRequest.getSession().getAttribute(AppointmentConfig.OBJAPPOINTMENT );
			if(objNewAppointmentVO!=null){
				objNewAppointmentVO= NewAppointmentDATA.ConfirmAppointment(objNewAppointmentVO,userVO);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		
	}
	
	//To get the New Appointmnet Shift Details, Added by Singaravelan on 19-Jan-2015
	@SuppressWarnings("rawtypes")
	public static void getShiftDetails(HttpServletRequest objRequest_p,HttpServletResponse objResponse_p){
		
		System.out.println("NewAppointmentUTIL :: getShiftDetails()");
		
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		WebRowSet rs=null;
		String shiftDataHTML="";
		//objPatientVO.setPatCrNo(objNewAppointmentSUP_p.getPatCrNo());
		String visitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
		Map patDtlMap=new HashMap();

		try{		
			
			System.out.println("-----"+objRequest_p.getParameter("deptCode")+"-----");
			System.out.println("-----"+objRequest_p.getParameter("dateChoice")+"-----");	
			System.out.println("-----"+objRequest_p.getParameter("unitChoice")+"-----");

			Map mapAptDetails=new HashMap();
			mapAptDetails.put("aptStartDate", objRequest_p.getParameter("dateChoice"));
			mapAptDetails.put("aptEndDate", objRequest_p.getParameter("dateChoice"));
			mapAptDetails.put("aptDeptCode", objRequest_p.getParameter("deptCode"));
			mapAptDetails.put("aptDocName", "%");
			
			rs=NewAppointmentDATA.getShiftDetailsForApt(mapAptDetails, objUserVO);
			shiftDataHTML=makeShiftData(objRequest_p, objResponse_p, rs);
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		status.add(Status.RECORDFOUND,"Renewal Required" ,"");
		}
		catch(HisRecordNotFoundException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
	   		if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   		{
	   			//WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   			patDtlMap.put(RegistrationConfig.APT_PATIENT_VO,objPatientVO);
				WebUTIL.setMapInSession(patDtlMap, objRequest_p, "NewAppointmentAction");
	   		}
	   		status.add(Status.UNSUCESSFULL,e.getMessage(),""); 	
		}
		catch(HisDuplicateRecordException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
	   		//objNewAppointmentSUP_p.setErrorMessage(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
	   	catch(HisNotAnOPDcaseException e){
			//e.printStackTrace();
	   		System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not Eligible For OPD" ,"");
		}
		catch(HisNotAnIPDcaseException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not eligible for IPD" ,"");
		}
		catch(HisDeadPatientException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			status.add(Status.ERROR_AE,"Not applicable, Patient is Dead" ,"");
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_AE,"Transaction Unsuccessful" ,"");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA,"Transaction Unsuccessful" ,"");
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.UNSUCESSFULL,"Transaction Unsuccessful" ,"");
		}
		finally{
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	
	public static String makeShiftData(HttpServletRequest objRequest_p,HttpServletResponse objResponse_p,WebRowSet _rs)
	{
		String strShiftData="";
		Map<String,Map<String, List<String>>> unitShiftMap=getShiftDataMap(objRequest_p, objResponse_p, _rs);
		System.out.println("---------"+unitShiftMap.size()+"---------");
		int i=0,j=0,z=unitShiftMap.size(),_inCol=0;;
		
		Map<String, List<String>> map=new HashMap<>();
		//String tab_r_header[]={};
		//String tab_apt_shift[][]={};
		String tab_r_header[]=new String[z+1];
		String tab_apt_shift[][]=new String[20][z+1];
		List<String> lstUNames=new ArrayList<>();

		for (Map.Entry<String,Map<String, List<String>>> entry : unitShiftMap.entrySet())
		{			
			//tab_apt_shift=new String[20][z]; 
			//tab_r_header=new String[z]; 
			tab_r_header[i]=entry.getKey();
			tab_apt_shift[0][i+1]=entry.getKey();
			System.out.println(entry.getKey() + "/" + entry.getValue());
			
			
			Map <String, List<String>> _tmpInmap=entry.getValue();
			int _inRow=1;
			for (Map.Entry<String, List<String>> _entry : _tmpInmap.entrySet())
			{
				System.out.println("--Inner Loop--"+_entry.getKey() + "/" + _entry.getValue());
				System.out.println("---"+_inRow+"--"+_inCol+" :"+_entry.getKey());
				System.out.println("---"+_inRow+"--"+(_inCol+1)+" :"+_entry.getValue().toString());
				
				if(i==0){
					lstUNames.add(_entry.getKey());
					tab_apt_shift[_inRow][0]=_entry.getKey();
					tab_apt_shift[_inRow][_inCol+1]=_entry.getValue().toString();
				}
				else
				{
					if(lstUNames.contains(_entry.getKey())){
						int rIndx=lstUNames.indexOf(_entry.getKey());
						tab_apt_shift[rIndx+1][_inCol+1]=_entry.getValue().toString();
					}
					else
					{
						
					}
				}
				_inRow++;
			}
			
			i++;
			_inCol++;
		}
		
		strShiftData+="<table><tbody>";
		strShiftData+="<tr>";
		for(int x=0;x<i;x++){
			strShiftData+="<td><h2>"+tab_r_header[x]+"</h2>";
			strShiftData+="</td>";
		}
		strShiftData+="<table><tbody>";
		strShiftData+="</tbody></table>";		

		try
		{
			PrintWriter writer=objResponse_p.getWriter();
			writer.write( "ID_SHIFT_DATA"+"@"+strShiftData);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		
		
		return strShiftData;
		
	}
	public static Map getShiftDataMap(HttpServletRequest objRequest_p,HttpServletResponse objResponse_p,WebRowSet _rs)
	{
		
		Map <String,Map<String, List<String>>> newMapApt=new HashMap<>(); 
		try{
			
			Map<String, List<String>> newMapUnitSlot=new HashMap<>(); 
			int x=0;
			String tmpDate="",tmpUnit="",_rsDate="",_rsUnit="",_rsSlot="";
			List shiftLst=null;
			boolean added=true;
    		if (!_rs.next()){
    			System.out.println("No records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else{
    			
    			_rs.beforeFirst();
    			while(_rs.next()){	    
    				System.out.println("----"+_rs.getString(1)+"----"+_rs.getString(2)+"----"+_rs.getString(3)+"----");
    				_rsDate=_rs.getString(1);_rsUnit=_rs.getString(2);_rsSlot=_rs.getString(3);
    				if(x==0)
    				{	
    					shiftLst=new ArrayList();
    					tmpDate=_rsDate;	tmpUnit=_rsUnit;	shiftLst.add(_rsSlot);
    					added=false;
    				}
    				if(x!=0){
	    				if(!tmpDate.equals(_rsDate)) //Records Date are different
	    				{
	    					newMapUnitSlot.put(tmpUnit, shiftLst);	newMapApt.put(tmpDate, newMapUnitSlot);
	    					tmpDate=_rsDate;
	    					//if(!tmpUnit.equals(_rsUnit)) //When the Date not same units not same
		    					tmpUnit=_rsUnit;
		    				//else {
		    					shiftLst.clear();
	    						shiftLst.add(_rsSlot);
	    						added=false;
		    				//}
	    				}
	    				else //Records having same Dates
	    				{
							if(!tmpUnit.equals(_rsUnit)) //Date Same Units Different 
			    			{
								newMapUnitSlot.put(tmpUnit, shiftLst);	newMapApt.put(tmpDate, newMapUnitSlot);
		    					tmpUnit=_rsUnit;
		    					shiftLst.clear();
	    						shiftLst.add(_rsSlot);
	    						added=false;
			    			}
							else{ //Date and Unit Same
								shiftLst.add(_rsSlot);
	    						added=false;
							}
	    				}
    				} 			
    				x++;
    			}
    			if(!added){
					newMapUnitSlot.put(tmpUnit, shiftLst);	newMapApt.put(tmpDate, newMapUnitSlot);
    			}
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();    			
    		}
    		else    		
    		throw new HisDataAccessException("AppointmnetEssentialsDAO:getShiftDetailsForApt :: "+e);
    	}
		
		return newMapApt;
	
	}
}//end of class