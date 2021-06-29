package appointment.transactions.controller.util;

import hisglobal.config.HISConfig;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;










//import registration.config.RegistrationConfig;
import vo.appointment.AppointmentParameterVO;
import vo.appointment.AppointmentSlotDtlVO;
import vo.appointment.AppointmentVO;
import vo.appointment.NewAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.AppointmentGlobalSUP;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
//Lucene
/*import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;*/
import appointment.transactions.controller.data.AppointmentTagDATA;
import appointment.transactions.controller.data.NewAppointmentDATA;


public class AppointmentTagUTIL extends ControllerUTIL
{

	

	public static String getAppointmentEssentials(HttpServletRequest request, String tagView,String supportClassName,String controllerName, String scriptCallBackFunctionName,String tagname,String aptId) 
	{
		String strReturn="";
		if(supportClassName==null)
		{
			supportClassName="";
		}
		try
		{
			
			request.getSession().removeAttribute(AppointmentConfig.LSTPARAMETER);
			
			if(scriptCallBackFunctionName==null)
				scriptCallBackFunctionName="";
			
			UserVO userVO=getUserVO(request);
			
			Map mp=AppointmentTagDATA.getAppointmentEssentials_AJAX(userVO,tagView,aptId);
			List lstAppointment=(List) mp.get(AppointmentConfig.LSTAPPOINTMENT);
			//System.out.println("-------GET URL SCHEME-----------"+request.getContextPath()+"------------------") ;
			strReturn += "<script type='text/javascript' src='"+AppointmentConfig.HISREG_CONTEXTPATH+"/appointment/transactions/js/AppointmentParameter.js'></script>";
			if(request.getContextPath().equals("/HISRegistration")||request.getContextPath().contains("/HISRegistration"))
				strReturn += "<script type='text/javascript' src='"+AppointmentConfig.HISREG_CONTEXTPATH+"/appointment/transactions/js/appointment.js'></script>";
			else
				strReturn += "<script type='text/javascript' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/appointment/transactions/js/appointment.js'></script>";

			strReturn += "<div class='div-table'>";
				strReturn +="<div class='div-table-row'>";
					strReturn +="<div class='div-table-col width label' style='width:50%'>";
					strReturn +="<font color='red'>*</font>Appointment For</div>";
					strReturn +="<div class='div-table-col width control' style='width:50%'>";
					if(lstAppointment!=null && lstAppointment.size()>0)
					{
						if(lstAppointment.size()>1)
						{
							strReturn +="<select name='appointmentDtl' id='appointmentDtl' style='width :145px' tabindex='1'>";
							strReturn +="<option value='-1'>Select Value</option>";
							
							for(int i=0;i<lstAppointment.size();i++)
							{
								AppointmentVO objAppointmentVO= (AppointmentVO)lstAppointment.get(i);
								String val= objAppointmentVO.getAppointmentForId() + "#"+ objAppointmentVO.getMultiAppointmentStatus() + "#" + objAppointmentVO.getIsTimingByAppointment();
								strReturn +="<option value='"+val+"' >"+objAppointmentVO.getAppointmentName()+"</option>";
							}
							strReturn +="</select>";
						}
						else
						{
							AppointmentVO objAppointmentVO= (AppointmentVO)lstAppointment.get(0);
							strReturn +="<input type='hidden' name='appointmentDtl' id='appointmentDtl' />";
							strReturn +=objAppointmentVO.getAppointmentName();
						}
					}
					
					
					strReturn += "<input type='hidden' name='tagView' value='"+tagView+"' />";
					strReturn += "<input type='hidden' name='supportClassName' value='"+supportClassName+"' />";
					strReturn += "<input type='hidden' name='controllerName' value='"+controllerName+"' />";
					strReturn += "<input type='hidden' name='scriptCallBackFunctionName' id='scriptCallBackFunctionName' value='"+scriptCallBackFunctionName+"' />";
					strReturn += "<input type='hidden' name='allActualParameterId' id='allActualParameterId' />";
					strReturn += "<input type='hidden' name='actualParameterReferenceId' id='actualParameterReferenceId' />";
					strReturn += "<input type='hidden' name='tagname'  id='tagname' value='"+tagname+"' />";
					
	
					if(supportClassName!=null && !supportClassName.equals("") )
					{
						supportClassName=supportClassName+ ".";
					}
					else
					{
						supportClassName="";
					}
					
					AppointmentVO objAppointmentVO=null;
					String paraString="";
					if(lstAppointment!=null && lstAppointment.size()==1){
						objAppointmentVO= (AppointmentVO)lstAppointment.get(0);			
						strReturn += "<input type='hidden' name='"+supportClassName+"appointmentForId' value='"+objAppointmentVO.getAppointmentForId()+"' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"appointmentName' value='"+objAppointmentVO.getAppointmentName() +"' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"multiAppointmentStatus' value='"+objAppointmentVO.getMultiAppointmentStatus() +"' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"isTimingByAppointment' value='"+objAppointmentVO.getIsTimingByAppointment() +"' />";
						List lstParameter=(List)mp.get(AppointmentConfig.LSTPARAMETER);
						paraString=makeParameterHTML(request, lstParameter, supportClassName);
					}
					else{
						strReturn += "<input type='hidden' name='"+supportClassName+"appointmentForId' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"appointmentName' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"multiAppointmentStatus' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"isTimingByAppointment' />";
					}
					strReturn +="</div></div>";
					strReturn +="<div class='div-table-row' >";
						strReturn +="<div class='div-table-col width' style='width:100%' id='ROW_PARAMETER'>";
							strReturn +=paraString;
					strReturn +="</div></div>";
			
			strReturn +="</div>"; // table ends	
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strReturn;
	}
	
	public static String makeParameterHTML(HttpServletRequest request,List lstParameter,String supportClassName) {
		String strReturn="";
		try
		{
			request.getSession().setAttribute(AppointmentConfig.LSTPARAMETER,lstParameter);
			//UserVO userVO=getUserVO(request);
			if(lstParameter!=null && lstParameter.size()>0){
				strReturn +="<div class='div-table'>";
				String emptyCol="";

				if(supportClassName==null  ){
					supportClassName="";
				}
				
				for(int i=0;i<lstParameter.size();i++){
					AppointmentParameterVO objAppointmentParameterVO= (AppointmentParameterVO) lstParameter.get(i);
					if(i%2==0)
						strReturn +="<div class='div-table-row'>";
					
					strReturn +="<div class='div-table-col width label' style='width:25%'>";
					strReturn +="<font color='red'>*</font>"+objAppointmentParameterVO.getParameterName()+"</div>";
					strReturn +="<div class='div-table-col width control' style='width:25%'>";
						strReturn +="<select name='"+supportClassName+"actualParameterId' id='ACTUALPARAMETERID_"+objAppointmentParameterVO.getParameterId()+"' style='width :145px' tabindex='1'>";
						if(objAppointmentParameterVO.getOptionHTML()!=null)
							strReturn +=objAppointmentParameterVO.getOptionHTML();
						else
							strReturn +="<option value='-1'>Select Value</option>";
						strReturn +="</select>";
						strReturn += "<input type='hidden' name='"+supportClassName+"parentParameterId' id='PARENTPARAMETERID_"+objAppointmentParameterVO.getParameterId()+"' value='"+objAppointmentParameterVO.getParentParameterId()+"' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"parameterId' id='PARAMETERID_"+objAppointmentParameterVO.getParameterId()+"' value='"+objAppointmentParameterVO.getParameterId()+"' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"parameterName' id='PARAMETERNAME_"+objAppointmentParameterVO.getParameterId()+"' value='"+objAppointmentParameterVO.getParameterName() +"' />";
						strReturn += "<input type='hidden' name='"+supportClassName+"displayOrder' id='DISPLAYORDER_"+objAppointmentParameterVO.getParameterId()+"' value='"+objAppointmentParameterVO.getDisplayOrder() +"' />";
						
					strReturn +="</div>";
					
					if(i>0 && i%2==0)
						strReturn +="</div>";
				}
				if(lstParameter.size()%2!=0)
					strReturn +="<div class='div-table-col width' style='width:25%'></div></div>";
				else
					strReturn +="</div>";
				strReturn +="</div>"; // table div ends
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return strReturn;
	}
	
	public static void getParameterHTML(AppointmentGlobalSUP objAppointmentGlobalSUP,	HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) 
	{
		try
		{
			UserVO userVO=getUserVO(objRequest);
			List lstParameter= AppointmentTagDATA.getAppointmentIdWiseParameterDetail(userVO, objAppointmentGlobalSUP.getAppointmentForId(),objAppointmentGlobalSUP.getTagView());
			String paraString=""; 
			paraString=makeParameterHTML(objRequest, lstParameter,objAppointmentGlobalSUP.getSupportClassName());
			WebUTIL.writeResponse(objResponse, paraString,null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	
	public static void getParameterComboValueHTML(AppointmentGlobalSUP objAppointmentGlobalSUP,	HttpServletRequest objRequest, HttpServletResponse objResponse,	Map mapSesion) {
		List lstParameter=(List)objRequest.getSession().getAttribute(AppointmentConfig.LSTPARAMETER);
		String jsonString="";
		int maxdisplayOrder=0;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			if(lstParameter!=null && lstParameter.size()>0){
				for(int i=0;i<lstParameter.size();i++){
					AppointmentParameterVO objAppointmentParameterVO=(AppointmentParameterVO)lstParameter.get(i);
					if(objAppointmentParameterVO.getParentParameterId().equals(objAppointmentGlobalSUP.getParentParameterId())){
						Map mp=AppointmentTagDATA.getAppointmentParameterValueDtl(userVO, objAppointmentParameterVO, objAppointmentGlobalSUP.getTagView() , objAppointmentGlobalSUP.getParentParameterActualValue());
						objAppointmentParameterVO=(AppointmentParameterVO)mp.get(AppointmentConfig.OBJAPPOINTMENTPARAMETERVO);
						jsonString+="{\""+ objAppointmentParameterVO.getParameterId() + "\":\"" + objAppointmentParameterVO.getOptionHTML() +"\"},";
						
					}
					int displayOrder=Integer.parseInt(objAppointmentParameterVO.getDisplayOrder());
					if(maxdisplayOrder<displayOrder)
						maxdisplayOrder=displayOrder;					
				}			
			}
			String actualParameterReferenceId="";
			if(maxdisplayOrder==Integer.parseInt(objAppointmentGlobalSUP.getDisplayOrder())){ //&& objAppointmentGlobalSUP.getTagView()!="MASTERS"){
				actualParameterReferenceId=AppointmentTagDATA.getActualParaRefernceId(userVO,objAppointmentGlobalSUP.getAppointmentForId(), objAppointmentGlobalSUP.getAllActualParameterId());
				jsonString+="{\"actualParameterReferenceId\":\"" + actualParameterReferenceId +"\"},";
				jsonString+="{\"isMaxParameter\":\"1\"},";
			}
			else
			{
				jsonString+="{\"actualParameterReferenceId\":\"0\"},";
				jsonString+="{\"isMaxParameter \":\"0\"},";
			}
			
			
			if(jsonString!="")
				jsonString=jsonString.substring(0, jsonString.length()-1);
			jsonString="[" +jsonString+"]";
			
			System.out.println("json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		
	}

	public static String getAppointmentListingEssentials(HttpServletRequest request, String tagView,String supportClassName,String controllerName, String scriptCallBackFunctionName,String tagname, String aptId,String listingMode){
		request.getSession().removeAttribute(AppointmentConfig.MPAPPOINTMENTLISTING);
		String strReturn="";
		String currDate=(String) request.getSession().getAttribute(HISConfig.SYSDATEOBJECT);
		if(currDate==null)
			currDate="";
		else
			currDate=currDate.split(" ")[0];
		
		strReturn+="<script type='text/javascript' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/masterutil/js/jquery/jquery.easyui.js'></script>";
		strReturn+="<script type='text/javascript' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/masterutil/js/jquery/jquery-ui.js'></script>";
		strReturn+="<script type='text/javascript' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/masterutil/js/jquery/jqueryExtValidation.js'></script>";
		strReturn+="<link rel='stylesheet' href='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/css/jqueryExtValidationToolTip.css'>";
		strReturn+="<link rel='stylesheet' href='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/css/easyui.css'>";
		strReturn+="<div class='div-table'>";
		strReturn+="<div class='div-table-row'>";
		strReturn+="<div class='div-table-col title width100'>";
		strReturn+="Appointment Listing"; 
		strReturn+="</div>";
		strReturn+="</div>";
		strReturn+=getAppointmentEssentials(request, tagView, supportClassName, controllerName, scriptCallBackFunctionName,tagname,aptId);
		strReturn+="<div class='div-table'>";
		strReturn+="<div class='div-table-row '>";
		strReturn+="<div class='div-table-col label' style='width: 25%' ><font color='red'>*</font>Appointment Date& Time</div>";
		strReturn+="<div class='div-table-col control' style='width: 25%;'>";
		strReturn+="<input name='appointmentDate' id='appointmentDate' maxlength='10' type='text' style='width: 75px;' value='"+currDate+"'>";
		strReturn+="<input type='hidden' name='listingMode' id='listingMode' value='"+listingMode+"'>";
		strReturn+="</div>";
		strReturn+="<div class='div-table-col control' style='width: 25%' >";
		strReturn+="<input type='button' id='btnAppointmentListingGo' value='GO' /> "; 
		strReturn+="</div>";			
		strReturn+="<div class='div-table-col control' style='width: 15%;'>";		 
		strReturn+="</div>";	
		strReturn+="</div>";
		strReturn+="</div>";
		
		strReturn+="<div class='div-table-row '>";
		strReturn+="<div class='div-table-col' style='width: 100%' id='DIV_TABLE' ></div>";
		strReturn+="</div>";
		strReturn+="</div>";
		strReturn+="</div>";
		
		
		return strReturn;
	}
	
	public static void getAppointmentListHTML(AppointmentGlobalSUP objAppointmentGlobalSUP,	HttpServletRequest objRequest, HttpServletResponse objResponse,	Map mapSesion) {
		String strReturn="";
		try
		{
		UserVO userVO=getUserVO(objRequest);
		NewAppointmentVO objNewAppointmentVO=new NewAppointmentVO();
		HelperMethods.populate(objNewAppointmentVO, objAppointmentGlobalSUP);
		boolean showcrnotd=true;
		if(objAppointmentGlobalSUP.getListingMode()!=null){
			if(objAppointmentGlobalSUP.getListingMode().equals("UNREG"))
				showcrnotd=false;
		}
			
		Map <String,NewAppointmentVO> mp=AppointmentTagDATA.getAppointmentList(userVO,objNewAppointmentVO);
		if(mp!=null && mp.size()>0){
			strReturn+="<div class='div-table-listing rounded'>";
				strReturn+="<div class='div-table-row listHeader' >";
					strReturn+="<div class='div-table-col ' style='width:5%'>&nbsp;</div>";
					 if(showcrnotd)
						strReturn+="<div class='div-table-col ' style='width:20%'><b>CRNO.</b></div>";
					strReturn+="<div class='div-table-col ' style='width:25%'><b>Patient Name</b></div>";
					strReturn+="<div class='div-table-col ' style='width:25%'><b>Age/Gender</b></div>";
					strReturn+="<div class='div-table-col ' style='width:20%'><b>Appt. Time</b></div>";
				strReturn+="</div>";
			
			Iterator entries = mp.entrySet().iterator();
			while (entries.hasNext()) {
			  Entry thisEntry = (Entry) entries.next();
			  NewAppointmentVO value = (NewAppointmentVO)thisEntry.getValue();
			  String crno="unreg.";
			  if(value.getPatCrNo()!=null && !value.getPatCrNo().equals("") && !value.getPatCrNo().equals("0"))
				  crno=value.getPatCrNo();
			  strReturn+="<div class='div-table-row listData'>";
			  	strReturn+="<div class='div-table-col ' style='width:5%'><input type='radio' name='appointmentNo' value='"+value.getAppointmentNo()+"' /></div>";
			  	if(showcrnotd)
			  		strReturn+="<div class='div-table-col ' style='width:20%'>"+crno+"</div>";
				strReturn+="<div class='div-table-col ' style='width:25%'>"+value.getPatFirstName() + " "+ value.getPatMiddleName()+ " "+ value.getPatLastName() +"</div>";
				strReturn+="<div class='div-table-col ' style='width:25%'>"+value.getPatAge() +"/"+value.getPatGenderCode()+"</div>";
				strReturn+="<div class='div-table-col ' style='width:20%'>"+value.getAppointmentTime()+"</div>";
			  strReturn+="</div>";
			}	
			strReturn+="</div>";
		}
		else{
			strReturn+="<font color='red'>No Record found</font>";
		}
		objRequest.getSession().setAttribute(AppointmentConfig.MPAPPOINTMENTLISTING, mp);
		WebUTIL.writeResponse(objResponse, strReturn, null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void setSelectedAppointmentObjectInSession(AppointmentGlobalSUP objAppointmentGlobalSUP,	HttpServletRequest objRequest, HttpServletResponse objResponse,	Map mapSesion) {
		String strReturn="";
		try
		{
			Map <String,NewAppointmentVO> mp=(Map)objRequest.getSession().getAttribute(AppointmentConfig.MPAPPOINTMENTLISTING );
			if(mp!=null && mp.size()>0 && mp.containsKey(objAppointmentGlobalSUP.getAppointmentNo())){
				NewAppointmentVO objNewAppointmentVO=mp.get(objAppointmentGlobalSUP.getAppointmentNo());
				objRequest.getSession().setAttribute(AppointmentConfig.OBJAPPOINTMENT,objNewAppointmentVO );
			}
			else{
				strReturn="Map empty!";
			}
			WebUTIL.writeResponse(objResponse, strReturn, null);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}

	public static String MakeAppointmentTag(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
			String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate) 
	{
		
		////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
		String strResultTag="",strResultTag2="";
		String allactualParaId="";
		Map mpAllAppointmentProcessSpecificDetails= new HashMap();
		Map mppreviousAppointmentProcessDtl= new HashMap();
		WebRowSet ws=null;
		int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
		String oldShiftId="",slotUniqueId="";
		boolean breakLoop=false;
		List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
		List<String> slotTimes=new ArrayList<>(); 
		List<String> apptDates=new ArrayList<>(); 
		String showApointmentDateonPopup="";
		try
		{	
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uniqueId=(String) sdf.format(cal.getTime());
			Map mpActualParaRefId=new HashMap();
			////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
		
			if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
				objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
			
			for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
				slotTimes.add(obj_newApt.getSlotST());
				apptDates.add(obj_newApt.getAppointmentDate());
			}
		
			UserVO userVO= ControllerUTIL.getUserVO(request);
			String bookedDate=""; 
			String BookedTime="";
			String shiftId="";
			String[] actualParaId= new String[7];
			String actualParaRefIdSlotDuration=null;
			String actualParaRefId=null;
			String slotDuration=null;
		
			////String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
			////if(init==null)
				////return "";
			
			if(paraId1==null || paraId2==null)
			{
				return "";
			}
			if(paraId3==null)paraId3= "0";
			if(paraId4==null)paraId4= "0";
			if(paraId5==null)paraId5= "0";
			if(paraId6==null)paraId6= "0";
			if(paraId7==null)paraId7= "0";
		
				  String key="APTOBJ_TAG"+tagId;
				 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
				  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
				  boolean flagExist=false;
				  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
				  {
					  if(mpNewAppointment.containsKey(key))
						  flagExist=true;
					  else
						  flagExist=false;	
				  }
				  else
				  {
					  flagExist=false;
					  ////mpNewAppointment= new HashMap();
				  }*///
				  
				  
				  if(flagExist==false  )
				  {
					  ////mpNewAppointment= new HashMap(); 
					  
						if(aptId!=null)
						{							
							actualParaId[0]=paraId1;
							actualParaId[1]=paraId2;
							actualParaId[2]=paraId3;
							actualParaId[3]=paraId4;
							actualParaId[4]=paraId5;
							actualParaId[5]=paraId6;
							actualParaId[6]=paraId7;
							allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
						    
							////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
							AppointmentParameterVO appParaObj= new AppointmentParameterVO();
							appParaObj.setAppointmentForId(aptId);				
							appParaObj.setArrActualParaId(actualParaId);
							////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

							actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
							actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
							slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
							appParaObj.setPatCrno(crno);
							appParaObj.setSlotDuration(slotDuration);
							appParaObj.setAppointmentForDate(aptDate);
							//Sheeldarshi
							appParaObj.setAppointmentDatePopup(aptDate);
							//End
							if(actualParaRefId!=null && actualParaRefId!="")
							{
								appParaObj.setActualParameterReferenceId(actualParaRefId);
								
								ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
								//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
								////Map mpDatesforActualParaRefId=null;
								////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
									////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
								////appParaObj.setAppointmentCallStatus("BY_TAG");
								
								/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
								List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
								request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
								mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
								ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
								String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
								bookedDate =availableDateTime.replace("^","#").split("#")[0];
								BookedTime =availableDateTime.replace("^","#").split("#")[1];
								shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
								
							}					
							else
							{
								return "Appointment Not Configured";										
							}										
						}						
						
						/*////objAppointmentdtlVO.setActualParaRefId(actualParaRefId);
						objAppointmentdtlVO.setAppointmentDate(bookedDate);
						//objAppointmentdtlVO.setAptReqNo(fb.getAptReqNo() );
						//System.out.println("aptReqNo----->" +objAppointmentdtlVO.getAptReqNo());
						objAppointmentdtlVO.setAptId(aptId);
						objAppointmentdtlVO.setAppointmentType((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTAPTTYPE));			
						objAppointmentdtlVO.setPatCrNo(crno);	
						objAppointmentdtlVO.setArrActualParaId(actualParaId);
						objAppointmentdtlVO.setAppointmentDate(bookedDate);
						objAppointmentdtlVO.setAppointmentStartTime(BookedTime);
						
						List <BookedSlotdtlVO> lstBookedSlotdtlVO= objAppointmentdtlVO.getLstBookedSlotdtlVO();
						if (lstBookedSlotdtlVO==null){
							lstBookedSlotdtlVO= new ArrayList();
						}
						BookedSlotdtlVO   objSlotVO=  new BookedSlotdtlVO();
						objSlotVO.setAppointmentDate(bookedDate);
						objSlotVO.setAppointmentStartTime(BookedTime);
						objSlotVO.setAppointmentDuration((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTDURATION));
						objAppointmentdtlVO.setAppointmentDuration(objSlotVO.getAppointmentDuration());
						objSlotVO.setShiftId(shiftId );
						objSlotVO.setActualParaRefId(actualParaRefId);
						//objSlotVO.setAptReqNo(fb.getAptReqNo());
						objSlotVO.setStatusofAppointment(AppointmentConfig.APPOINTMENT_STATUS_NEW);
						objSlotVO.setAppointmentType((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTAPTTYPE));
						
						int flag=0;
						if(lstBookedSlotdtlVO!=null && lstBookedSlotdtlVO.size() >0) {
							for(int i=0;i<lstBookedSlotdtlVO.size();i++){
								BookedSlotdtlVO objBookedSlotdtlVO =(BookedSlotdtlVO)lstBookedSlotdtlVO.get(i);
								if(objBookedSlotdtlVO.getAppointmentDate().equals(bookedDate)  && objBookedSlotdtlVO.getAppointmentStartTime().equals(BookedTime)){
									String sqNo= objBookedSlotdtlVO.getSqNo();
									HelperMethods.populate(objBookedSlotdtlVO, objSlotVO);
									objBookedSlotdtlVO.setSqNo(sqNo);
									flag=1;
									break;
								}
							}
						}
						if(flag==0 && objSlotVO.getAppointmentStartTime()!=null && !objSlotVO.getAppointmentStartTime().equals("")){
							lstBookedSlotdtlVO.add(objSlotVO);
						}
						objAppointmentdtlVO.setLstBookedSlotdtlVO(lstBookedSlotdtlVO);*/////
				  }
				  else
				  {
					  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
					  bookedDate=objAppointmentdtlVO.getAppointmentDate();
					  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////					  
				  }
				  
				  
				//Start:Sheeldarshi
				  /*if(null!=request.getParameter("showAppointmentDateInsidePopup"))
						showApointmentDateonPopup=(String)request.getParameter("showAppointmentDateInsidePopup");
				  				  
				  if((showApointmentDateonPopup.equals("1")||showApointmentDateonPopup.equals("")) && !aptDate.equals("")){
					strResultTag+="<div class='div-table'><div class='div-table-row'>";
					strResultTag+="<div class='div-table-col label' style='width: 25%' ><font color='red'>*</font>Appointment date</div>";
					strResultTag+="<div class='div-table-col control' style='width: 25%;'>";
					strResultTag+="<input name='appointmentDatePopup'  tabindex='1' id='appointmentDatePopup' maxlength='10' type='text' style='width: 95px;' onchange=\"openApptPopupNew()\"></div>";
					strResultTag+="<span id='aptTime1' style='display:none'><input name='appointmentTime'  tabindex='1' id='appointmentTime' maxlength='8' type='text' style='width: 75px;'></span>";
					strResultTag+="</div></div>";
					if(ws.size()<1)
					{
					strResultTag+="<div class='div-table'><div class='div-table-row'>";
					strResultTag+="<div class='div-table-col label' style='width: 50%;' ><font color='red'>No Slots Available</font></div>";
					strResultTag+="<input type='hidden' name='aptForDate' value='"+aptDate+"'>";
					
					strResultTag+="</div></div>";
					}
				  }
					*///;openApptPopup()
					//End  
			if(ws!=null && ws.size()>0)
			{
				while(ws.next() && !breakLoop)
				{
					
					if(tagView.equals("1"))//Ist Free Slot View
					{
						strResultTag+="<input type='hidden' name='allactualParameterId' value='"+allactualParaId+"'>";
						strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
						strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
						strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
						strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
						strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
						strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
						strResultTag+="<input type='hidden' name='appointmentNo' >";
						strResultTag+="<input type='hidden' name='appointmentQueueNo' >";
						strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
						strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
						//System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));
						//strResultTag+="<input type='hidden' name='showAppointmentDateInsidePopup'>";
						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
						{
							String cssClass=ws.getString(11);
							strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px'> "+ws.getString(1)+"&nbsp;"+ws.getString(8)+"&nbsp;"+ws.getString(11)+"</font></div>";
							strResultTag+="<input type='hidden' name='slotST' value='"+ws.getString(8)+"'>";
							strResultTag+="<input type='hidden' name='slotET' value='"+ws.getString(9)+"'>";
							strResultTag+="<input type='hidden' name='shiftId' value='"+ws.getString(2)+"'>";
							strResultTag+="<input type='hidden' name='aptType' value='"+ws.getString(10)+"'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openApptPopup(this)\">";
							strResultTag+="</div></td></tr></table>";							
							breakLoop=true;
						}						
					}
					else
					{
						runningNo++;
						//System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

						if(shiftcounter==0)
						{
							strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
							strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
							strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
							strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
							strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
							strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
							strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
							strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
							strResultTag+="</div></td>";
							strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
							strResultTag+="<td  class='header'  width='5%'><div align='right'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
							strResultTag+="</div></td></tr></table>";
							strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
						}
						else if(!oldShiftId.equals(ws.getString(2)))
						{
							++nextShift;
							strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
							strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
							strResultTag+="</div></td>";
							strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
							strResultTag+="<td  class='header'  width='5%'><div align='right'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
							strResultTag+="</div></td></tr></table>";
							strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
						}
							
						oldShiftId=ws.getString(2);
						shiftcounter++;
										
						
						
						if(cnt%5==0)
						{
							rowcount++;						
							if(cnt!=0)
							{
								strResultTag+="</tr><tr>";
							}
							else
							{			
								strResultTag+="<tr>";
							}
						}								
						cnt++;
							
						
						//strResultTag+="<tr>";
						String cssClass="";
						String bcssClass="";
						String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
						if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
							cssClass="Hold";
							bcssClass="Hold";
						}
						else{
							cssClass=ws.getString(11);
							bcssClass=ws.getString(11);
						}
						slotUniqueId=ws.getString(2)+""+runningNo;
						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						
							
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						
						if(ws.getString(10).equals("5"))//OverBooked Slot
							slotType="5";//Overbooked
							
						//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
						String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"')";
						
						
						/*if(shiftcounter<=4)
							strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
						else*/ if(ws.getString(10).equals("1"))//Free Slot
							strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						else if(ws.getString(10).equals("5"))//Overbooked Slot
							strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						else						
							strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						//strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
						//strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
						strResultTag+="<a>"+ws.getString(8)+"</a>";
						strResultTag+="</div></td>";
						/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
						strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
						strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
						strResultTag+="</div></td>";*/
						//strResultTag+="</tr>";					
					}
				}
				if(!tagView.equals("1"))//Whole Tag View
				{
					strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
					strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
					strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="</table>";
				
			   
					//VIP Slots logic Not Implemented Yet
					/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
					strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
					strResultTag+="<td width='10%' colspan='1'><div align='right'>";
					strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
					strResultTag+="</table>";*/
					
					strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
					strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
					strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
					//strResultTag+="<td width='10%' colspan='1'><div align='right'>";
					//strResultTag+="<img class='button' src='/HIS/hisglobal/images/cancel.png' style='cursor: pointer;'  onclick='closeVipApp()'></div></td>";
					strResultTag+="</tr>";
					strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
					strResultTag+="</tr>";
					strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
					strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
					strResultTag+="</table>";
					strResultTag+="</div>";
				}
			
		}
			
			/*if(cssClassName==null)
				cssClassName="tdfont";
		    strResultTag+="<table width='100%' cellspacing='0' cellpadding='0'><tbody><tr><td class='"+cssClassName+"' width='95%'>";
		    strResultTag+="<font color='black' size='2'><b><div id='DIVAPPTAG_"+tagId+"' align='center'>";
		    strResultTag+="&nbsp;"+bookedDate+":"+BookedTime+"</div></b></font></td>";
		    strResultTag+="<td width='5%' class='"+cssClassName+"' >" ;
		    strResultTag+="<img src='/AHIMS/hisglobal/images/A.png' tabindex='1' name='app' alt='Open Appt.' title='Click here to change the default Appointment given'	style='cursor: pointer;' onkeypress=\"if(event.keyCode==13) openAppointmentPopup(this,'"+crno+"','"+aptId+"','"+allactualParaId+"','"+tagId+"','"+actualParaRefId+"','"+aptId+"')\" onclick=\"openAppointmentPopup(this,'"+crno+"','"+aptId+"','"+allactualParaId+"','"+tagId+"','"+actualParaRefId+"','"+aptId+"')\">";
		    strResultTag+="</td></tr></tbody></table>";*/
		    
			////objAppointmentdtlVO.setMpAllAppointmentProcessSpecificDetails(mpAllAppointmentProcessSpecificDetails);
		    ////objAppointmentdtlVO.setMppreviousAppointmentProcessDtl(mppreviousAppointmentProcessDtl);		   
		    ////objAppointmentUsefullMethodsTransaction.updateContextData(servletContext,request, objAppointmentdtlVO);
		    ////objAppointmentdtlVO.setInstanceNo(tagId);
		    ////mpNewAppointment.put(key, objAppointmentdtlVO);					  
			////request.getSession().setAttribute(AppointmentConfig.APPOINMENT_MAP,mpNewAppointment);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//WebUTIL.writeResponse(response, strResultTag, null);
		try
		{
			PrintWriter writer=response.getWriter();
			//writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
			WebUTIL.writeResponse(response, "ID_PARAMETER_CODE"+"@"+strResultTag, "text/html");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return strResultTag;
	}
	
	
	
	public static String MakeAppointmentWholeTagForNewAppt(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
			String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate) 
	{
		
		////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
		String strResultTag="",strResultTag2="";
		String allactualParaId="";
		Map mpAllAppointmentProcessSpecificDetails= new HashMap();
		Map mppreviousAppointmentProcessDtl= new HashMap();
		WebRowSet ws=null;
		int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
		String oldShiftId="",slotUniqueId="";
		boolean breakLoop=false;
		List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
		List<String> slotTimes=new ArrayList<>(); 
		List<String> apptDates=new ArrayList<>(); 
		String showApointmentDateonPopup="";
		try
		{	
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uniqueId=(String) sdf.format(cal.getTime());
			Map mpActualParaRefId=new HashMap();
			////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
		
			if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
				objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
			
			for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
				slotTimes.add(obj_newApt.getSlotST());
				apptDates.add(obj_newApt.getAppointmentDate());
			}
		
			UserVO userVO= ControllerUTIL.getUserVO(request);
			String bookedDate=""; 
			String BookedTime="";
			String shiftId="";
			String[] actualParaId= new String[7];
			String actualParaRefIdSlotDuration=null;
			String actualParaRefId=null;
			String slotDuration=null;
		
			////String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
			////if(init==null)
				////return "";
			
			if(paraId1==null || paraId2==null)
			{
				return "";
			}
			if(paraId3==null)paraId3= "0";
			if(paraId4==null)paraId4= "0";
			if(paraId5==null)paraId5= "0";
			if(paraId6==null)paraId6= "0";
			if(paraId7==null)paraId7= "0";
		
				  String key="APTOBJ_TAG"+tagId;
				 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
				  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
				  boolean flagExist=false;
				  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
				  {
					  if(mpNewAppointment.containsKey(key))
						  flagExist=true;
					  else
						  flagExist=false;	
				  }
				  else
				  {
					  flagExist=false;
					  ////mpNewAppointment= new HashMap();
				  }*///
				  
				  
				  if(flagExist==false  )
				  {
					  ////mpNewAppointment= new HashMap(); 
					  
						if(aptId!=null)
						{							
							actualParaId[0]=paraId1;
							actualParaId[1]=paraId2;
							actualParaId[2]=paraId3;
							actualParaId[3]=paraId4;
							actualParaId[4]=paraId5;
							actualParaId[5]=paraId6;
							actualParaId[6]=paraId7;
							allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
						    
							////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
							AppointmentParameterVO appParaObj= new AppointmentParameterVO();
							appParaObj.setAppointmentForId(aptId);				
							appParaObj.setArrActualParaId(actualParaId);
							////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

							actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
							actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
							slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
							appParaObj.setPatCrno(crno);
							appParaObj.setSlotDuration(slotDuration);
							appParaObj.setAppointmentForDate(aptDate);
							//Sheeldarshi
							appParaObj.setAppointmentDatePopup(aptDate);
							//End
							if(actualParaRefId!=null && actualParaRefId!="")
							{
								appParaObj.setActualParameterReferenceId(actualParaRefId);
								
								ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
								//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
								////Map mpDatesforActualParaRefId=null;
								////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
									////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
								////appParaObj.setAppointmentCallStatus("BY_TAG");
								
								/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
								List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
								request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
								mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
								ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
								String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
								bookedDate =availableDateTime.replace("^","#").split("#")[0];
								BookedTime =availableDateTime.replace("^","#").split("#")[1];
								shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
								
							}					
							else
							{
								return "Appointment Not Configured";										
							}										
						}						
						
						/*////objAppointmentdtlVO.setActualParaRefId(actualParaRefId);
						objAppointmentdtlVO.setAppointmentDate(bookedDate);
						//objAppointmentdtlVO.setAptReqNo(fb.getAptReqNo() );
						//System.out.println("aptReqNo----->" +objAppointmentdtlVO.getAptReqNo());
						objAppointmentdtlVO.setAptId(aptId);
						objAppointmentdtlVO.setAppointmentType((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTAPTTYPE));			
						objAppointmentdtlVO.setPatCrNo(crno);	
						objAppointmentdtlVO.setArrActualParaId(actualParaId);
						objAppointmentdtlVO.setAppointmentDate(bookedDate);
						objAppointmentdtlVO.setAppointmentStartTime(BookedTime);
						
						List <BookedSlotdtlVO> lstBookedSlotdtlVO= objAppointmentdtlVO.getLstBookedSlotdtlVO();
						if (lstBookedSlotdtlVO==null){
							lstBookedSlotdtlVO= new ArrayList();
						}
						BookedSlotdtlVO   objSlotVO=  new BookedSlotdtlVO();
						objSlotVO.setAppointmentDate(bookedDate);
						objSlotVO.setAppointmentStartTime(BookedTime);
						objSlotVO.setAppointmentDuration((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTDURATION));
						objAppointmentdtlVO.setAppointmentDuration(objSlotVO.getAppointmentDuration());
						objSlotVO.setShiftId(shiftId );
						objSlotVO.setActualParaRefId(actualParaRefId);
						//objSlotVO.setAptReqNo(fb.getAptReqNo());
						objSlotVO.setStatusofAppointment(AppointmentConfig.APPOINTMENT_STATUS_NEW);
						objSlotVO.setAppointmentType((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTAPTTYPE));
						
						int flag=0;
						if(lstBookedSlotdtlVO!=null && lstBookedSlotdtlVO.size() >0) {
							for(int i=0;i<lstBookedSlotdtlVO.size();i++){
								BookedSlotdtlVO objBookedSlotdtlVO =(BookedSlotdtlVO)lstBookedSlotdtlVO.get(i);
								if(objBookedSlotdtlVO.getAppointmentDate().equals(bookedDate)  && objBookedSlotdtlVO.getAppointmentStartTime().equals(BookedTime)){
									String sqNo= objBookedSlotdtlVO.getSqNo();
									HelperMethods.populate(objBookedSlotdtlVO, objSlotVO);
									objBookedSlotdtlVO.setSqNo(sqNo);
									flag=1;
									break;
								}
							}
						}
						if(flag==0 && objSlotVO.getAppointmentStartTime()!=null && !objSlotVO.getAppointmentStartTime().equals("")){
							lstBookedSlotdtlVO.add(objSlotVO);
						}
						objAppointmentdtlVO.setLstBookedSlotdtlVO(lstBookedSlotdtlVO);*/////
				  }
				  else
				  {
					  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
					  bookedDate=objAppointmentdtlVO.getAppointmentDate();
					  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////					  
				  }
				  	//Start:Sheeldarshi
				  if(null!=request.getParameter("showAppointmentDateInsidePopup"))
						showApointmentDateonPopup=(String)request.getParameter("showAppointmentDateInsidePopup");
				  				  
				  if((showApointmentDateonPopup.equals("1")||showApointmentDateonPopup.equals("")) && !aptDate.equals("")){
					strResultTag+="<div class='div-table'><div class='div-table-row'>";
					strResultTag+="<div class='div-table-col label' style='width: 25%' ><font color='red'>*</font>Appointment date</div>";
					strResultTag+="<div class='div-table-col control' style='width: 25%;'>";
					strResultTag+="<input name='appointmentDatePopup'  tabindex='1' id='appointmentDatePopup' maxlength='10' type='text' style='width: 95px;' onchange=\"openApptPopupNew()\"></div>";
					strResultTag+="<span id='aptTime1' style='display:none'><input name='appointmentTime'  tabindex='1' id='appointmentTime' maxlength='8' type='text' style='width: 75px;'></span>";
					strResultTag+="</div></div>";
					if(ws.size()<1)
					{
					strResultTag+="<div class='div-table'><div class='div-table-row'>";
					strResultTag+="<div class='div-table-col label' style='width: 50%;' ><font color='red'>No Slots Available</font></div>";
					strResultTag+="<input type='hidden' name='aptForDate' value='"+aptDate+"'>";
					
					strResultTag+="</div></div>";
					}
				  }
				  else
				  {
					  strResultTag+="<input type='hidden' name='aptForDate' value='"+aptDate+"'>";
				  }
					//;openApptPopup()
					//End  
			if(ws!=null && ws.size()>0)
			{
				
				while(ws.next() && !breakLoop)
				{
					
					if(tagView.equals("1"))//Ist Free Slot View
					{
						strResultTag+="<input type='hidden' name='allactualParameterId' value='"+allactualParaId+"'>";
						strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
						strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
						strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
						strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
						strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
						strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
						strResultTag+="<input type='hidden' name='appointmentNo' >";
						strResultTag+="<input type='hidden' name='appointmentQueueNo' >";
						strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
						strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
						//System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));
						//strResultTag+="<input type='hidden' name='showAppointmentDateInsidePopup'>";
						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
						{
							String cssClass=ws.getString(11);
							strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px'> "+ws.getString(1)+"&nbsp;"+ws.getString(8)+"&nbsp;"+ws.getString(11)+"</font></div>";
							strResultTag+="<input type='hidden' name='slotST' value='"+ws.getString(8)+"'>";
							strResultTag+="<input type='hidden' name='slotET' value='"+ws.getString(9)+"'>";
							strResultTag+="<input type='hidden' name='shiftId' value='"+ws.getString(2)+"'>";
							strResultTag+="<input type='hidden' name='aptType' value='"+ws.getString(10)+"'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openApptPopup(this)\">";
							strResultTag+="</div></td></tr></table>";							
							breakLoop=true;
						}						
					}
					else
					{
						runningNo++;
						//System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

						if(shiftcounter==0)
						{
							strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
							strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
							strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
							strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
							strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
							strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
							strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
							strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
							strResultTag+="</div></td>";
							strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
							strResultTag+="<td  class='header'  width='5%'><div align='right'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
							strResultTag+="</div></td></tr></table>";
							strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
						}
						else if(!oldShiftId.equals(ws.getString(2)))
						{
							++nextShift;
							strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
							strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
							strResultTag+="</div></td>";
							strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
							strResultTag+="<td  class='header'  width='5%'><div align='right'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
							strResultTag+="</div></td></tr></table>";
							strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
						}
							
						oldShiftId=ws.getString(2);
						shiftcounter++;
										
						
						
						if(cnt%10==0)
						{
							rowcount++;						
							if(cnt!=0)
							{
								strResultTag+="</tr><tr>";
							}
							else
							{			
								strResultTag+="<tr>";
							}
						}								
						cnt++;
							
						
						//strResultTag+="<tr>";
						String cssClass="";
						String bcssClass="";
						String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
						if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
							cssClass="Hold";
							bcssClass="Hold";
						}
						else{
							cssClass=ws.getString(11);
							bcssClass=ws.getString(11);
						}
						slotUniqueId=ws.getString(2)+""+runningNo;
						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						
							
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						
						if(ws.getString(10).equals("5"))//OverBooked Slot
							slotType="5";//Overbooked
							
						//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
						String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"')";
						
						
						/*if(shiftcounter<=4)
							strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
						else*/ if(ws.getString(10).equals("1"))//Free Slot
							strResultTag+="<td title='"+title+"' onclick="+onclick+" width='10%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						else if(ws.getString(10).equals("5"))//Overbooked Slot
							strResultTag+="<td title='"+title+"' onclick="+onclick+" width='10%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						else						
							strResultTag+="<td title='"+title+"' width='10%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						//strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
						//strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
						strResultTag+="<a>"+ws.getString(8)+"</a>";
						strResultTag+="</div></td>";
						/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
						strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
						strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
						strResultTag+="</div></td>";*/
						//strResultTag+="</tr>";					
					}
				}
				if(!tagView.equals("1"))//Whole Tag View
				{
					strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
					strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
					strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="</table>";
				
			   
					//VIP Slots logic Not Implemented Yet
					/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
					strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
					strResultTag+="<td width='10%' colspan='1'><div align='right'>";
					strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
					strResultTag+="</table>";*/
					
					strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
					strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
					strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
					//strResultTag+="<td width='10%' colspan='1'><div align='right'>";
					//strResultTag+="<img class='button' src='/HIS/hisglobal/images/cancel.png' style='cursor: pointer;'  onclick='closeVipApp()'></div></td>";
					strResultTag+="</tr>";
					strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
					strResultTag+="</tr>";
					strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
					strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
					strResultTag+="</table>";
					strResultTag+="</div>";
				}
			
		}
			
			/*if(cssClassName==null)
				cssClassName="tdfont";
		    strResultTag+="<table width='100%' cellspacing='0' cellpadding='0'><tbody><tr><td class='"+cssClassName+"' width='95%'>";
		    strResultTag+="<font color='black' size='2'><b><div id='DIVAPPTAG_"+tagId+"' align='center'>";
		    strResultTag+="&nbsp;"+bookedDate+":"+BookedTime+"</div></b></font></td>";
		    strResultTag+="<td width='5%' class='"+cssClassName+"' >" ;
		    strResultTag+="<img src='/AHIMS/hisglobal/images/A.png' tabindex='1' name='app' alt='Open Appt.' title='Click here to change the default Appointment given'	style='cursor: pointer;' onkeypress=\"if(event.keyCode==13) openAppointmentPopup(this,'"+crno+"','"+aptId+"','"+allactualParaId+"','"+tagId+"','"+actualParaRefId+"','"+aptId+"')\" onclick=\"openAppointmentPopup(this,'"+crno+"','"+aptId+"','"+allactualParaId+"','"+tagId+"','"+actualParaRefId+"','"+aptId+"')\">";
		    strResultTag+="</td></tr></tbody></table>";*/
		    
			////objAppointmentdtlVO.setMpAllAppointmentProcessSpecificDetails(mpAllAppointmentProcessSpecificDetails);
		    ////objAppointmentdtlVO.setMppreviousAppointmentProcessDtl(mppreviousAppointmentProcessDtl);		   
		    ////objAppointmentUsefullMethodsTransaction.updateContextData(servletContext,request, objAppointmentdtlVO);
		    ////objAppointmentdtlVO.setInstanceNo(tagId);
		    ////mpNewAppointment.put(key, objAppointmentdtlVO);					  
			////request.getSession().setAttribute(AppointmentConfig.APPOINMENT_MAP,mpNewAppointment);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//WebUTIL.writeResponse(response, strResultTag, null);
		try
		{
			//PrintWriter writer=response.getWriter();
			//System.out.println("Size of buffer is"+response.getBufferSize());
			//System.out.println("Size of COntent is "+strResultTag.length());
			//response.setBufferSize(32*1024);
			//writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
			
			//WebUTIL.writeResponse(response, "ID_PARAMETER_CODE"+"@"+strResultTag, "text/html");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("strResultTag"+strResultTag);
		return strResultTag;
	}
	
	
	public static String MakeAppointmentTag(ServletContext servletContext,HttpServletRequest request,String crno, String aptId, String paraId1, String paraId2,
			String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName) 
	{
		
		////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
		String strResultTag="";
		String allactualParaId="";
		Map mpAllAppointmentProcessSpecificDetails= new HashMap();
		Map mppreviousAppointmentProcessDtl= new HashMap();
		WebRowSet ws=null;
		int cnt=0,rowcount=0;
		
		try
		{	
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uniqueId=(String) sdf.format(cal.getTime());
			Map mpActualParaRefId=new HashMap();
			////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
		
		
			UserVO userVO= ControllerUTIL.getUserVO(request);
			String bookedDate=""; 
			String BookedTime="";
			String shiftId="";
			String[] actualParaId= new String[7];
			String actualParaRefIdSlotDuration=null;
			String actualParaRefId=null;
			String slotDuration=null;
		
			String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
			if(init==null)
				return "";
			
			if(paraId1==null || paraId2==null)
			{
				return "";
			}
			if(paraId3==null)paraId3= "0";
			if(paraId4==null)paraId4= "0";
			if(paraId5==null)paraId5= "0";
			if(paraId6==null)paraId6= "0";
			if(paraId7==null)paraId7= "0";
		
				  String key="APTOBJ_TAG"+tagId;
				 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
				  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
				  boolean flagExist=true;
				  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
				  {
					  if(mpNewAppointment.containsKey(key))
						  flagExist=true;
					  else
						  flagExist=false;	
				  }
				  else
				  {
					  flagExist=false;
					  ////mpNewAppointment= new HashMap();
				  }*///
				  
				  
				  if(flagExist==false  )
				  {
					  ////mpNewAppointment= new HashMap(); 
					  
						if(aptId!=null)
						{							
							actualParaId[0]=paraId1;
							actualParaId[1]=paraId2;
							actualParaId[2]=paraId3;
							actualParaId[3]=paraId4;
							actualParaId[4]=paraId5;
							actualParaId[5]=paraId6;
							actualParaId[6]=paraId7;
							allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
						    
							////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
							AppointmentParameterVO appParaObj= new AppointmentParameterVO();
							appParaObj.setAppointmentForId(aptId);				
							appParaObj.setArrActualParaId(actualParaId);
							////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

							actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
							actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#")[0];
							slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#")[1];
							appParaObj.setPatCrno(crno);
							appParaObj.setSlotDuration(slotDuration);
							if(actualParaRefId!=null && actualParaRefId!="")
							{
								appParaObj.setActualParameterReferenceId(actualParaRefId);
								
								ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
								//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
								////Map mpDatesforActualParaRefId=null;
								////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
									////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
								////appParaObj.setAppointmentCallStatus("BY_TAG");
								
								/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
								List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
								request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
								mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
								ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
								String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
								bookedDate =availableDateTime.replace("^","#").split("#")[0];
								BookedTime =availableDateTime.replace("^","#").split("#")[1];
								shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
								
							}					
							else
							{
								return "Appointment Not Configured";										
							}										
						}					
						
						
						/*////objAppointmentdtlVO.setActualParaRefId(actualParaRefId);
						objAppointmentdtlVO.setAppointmentDate(bookedDate);
						//objAppointmentdtlVO.setAptReqNo(fb.getAptReqNo() );
						//System.out.println("aptReqNo----->" +objAppointmentdtlVO.getAptReqNo());
						objAppointmentdtlVO.setAptId(aptId);
						objAppointmentdtlVO.setAppointmentType((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTAPTTYPE));			
						objAppointmentdtlVO.setPatCrNo(crno);	
						objAppointmentdtlVO.setArrActualParaId(actualParaId);
						objAppointmentdtlVO.setAppointmentDate(bookedDate);
						objAppointmentdtlVO.setAppointmentStartTime(BookedTime);
						
						List <BookedSlotdtlVO> lstBookedSlotdtlVO= objAppointmentdtlVO.getLstBookedSlotdtlVO();
						if (lstBookedSlotdtlVO==null){
							lstBookedSlotdtlVO= new ArrayList();
						}
						BookedSlotdtlVO   objSlotVO=  new BookedSlotdtlVO();
						objSlotVO.setAppointmentDate(bookedDate);
						objSlotVO.setAppointmentStartTime(BookedTime);
						objSlotVO.setAppointmentDuration((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTDURATION));
						objAppointmentdtlVO.setAppointmentDuration(objSlotVO.getAppointmentDuration());
						objSlotVO.setShiftId(shiftId );
						objSlotVO.setActualParaRefId(actualParaRefId);
						//objSlotVO.setAptReqNo(fb.getAptReqNo());
						objSlotVO.setStatusofAppointment(AppointmentConfig.APPOINTMENT_STATUS_NEW);
						objSlotVO.setAppointmentType((String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.DEFAULTAPTTYPE));
						
						int flag=0;
						if(lstBookedSlotdtlVO!=null && lstBookedSlotdtlVO.size() >0) {
							for(int i=0;i<lstBookedSlotdtlVO.size();i++){
								BookedSlotdtlVO objBookedSlotdtlVO =(BookedSlotdtlVO)lstBookedSlotdtlVO.get(i);
								if(objBookedSlotdtlVO.getAppointmentDate().equals(bookedDate)  && objBookedSlotdtlVO.getAppointmentStartTime().equals(BookedTime)){
									String sqNo= objBookedSlotdtlVO.getSqNo();
									HelperMethods.populate(objBookedSlotdtlVO, objSlotVO);
									objBookedSlotdtlVO.setSqNo(sqNo);
									flag=1;
									break;
								}
							}
						}
						if(flag==0 && objSlotVO.getAppointmentStartTime()!=null && !objSlotVO.getAppointmentStartTime().equals("")){
							lstBookedSlotdtlVO.add(objSlotVO);
						}
						objAppointmentdtlVO.setLstBookedSlotdtlVO(lstBookedSlotdtlVO);*/////
						
						
					  
			
				  }
				  else
				  {
					  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
					  bookedDate=objAppointmentdtlVO.getAppointmentDate();
					  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////
					  
				  }
			if(ws!=null && ws.size()>0)
			{
				strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
				strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
				strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '1')\">";
				strResultTag+="</div></td>";
				strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;Morning&nbsp; (08:30-16:00)</b></font></div></td>";
				strResultTag+="<td  class='header'  width='5%'><div align='right'>";
				strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '1')\">";
				strResultTag+="</div></td></tr>";
				strResultTag+="<table width='100%' cellpadding='0' cellspacing='3' align='center'>";
				
				while(ws.next())
				{
					if(cnt%5==0)
					{
						rowcount++;						
						if(cnt!=0)
						{
							strResultTag+="</tr><tr>";
						}
						else
						{			
							strResultTag+="<tr>";
						}
					}								
					cnt++;
						
					
					//strResultTag+="<tr>";
					String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
					String cssClass=ws.getString(11);
					String bcssClass=ws.getString(11);
					String onclick="BookSlot('"+ws.getString(8)+"','"+ws.getString(9)+"','1','0')";
					
					strResultTag+="<td class='slotclass'  title='"+title+"'><div class='"+cssClass+"' align='center'>";
					strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
					strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
					strResultTag+="</div></td>";
					/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
					strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
					strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
					strResultTag+="</div></td>";*/
					//strResultTag+="</tr>";					
				}
			}
		   
			/*if(cssClassName==null)
				cssClassName="tdfont";
		    strResultTag+="<table width='100%' cellspacing='0' cellpadding='0'><tbody><tr><td class='"+cssClassName+"' width='95%'>";
		    strResultTag+="<font color='black' size='2'><b><div id='DIVAPPTAG_"+tagId+"' align='center'>";
		    strResultTag+="&nbsp;"+bookedDate+":"+BookedTime+"</div></b></font></td>";
		    strResultTag+="<td width='5%' class='"+cssClassName+"' >" ;
		    strResultTag+="<img src='/AHIMS/hisglobal/images/A.png' tabindex='1' name='app' alt='Open Appt.' title='Click here to change the default Appointment given'	style='cursor: pointer;' onkeypress=\"if(event.keyCode==13) openAppointmentPopup(this,'"+crno+"','"+aptId+"','"+allactualParaId+"','"+tagId+"','"+actualParaRefId+"','"+aptId+"')\" onclick=\"openAppointmentPopup(this,'"+crno+"','"+aptId+"','"+allactualParaId+"','"+tagId+"','"+actualParaRefId+"','"+aptId+"')\">";
		    strResultTag+="</td></tr></tbody></table>";*/
		    
			////objAppointmentdtlVO.setMpAllAppointmentProcessSpecificDetails(mpAllAppointmentProcessSpecificDetails);
		    ////objAppointmentdtlVO.setMppreviousAppointmentProcessDtl(mppreviousAppointmentProcessDtl);		   
		    ////objAppointmentUsefullMethodsTransaction.updateContextData(servletContext,request, objAppointmentdtlVO);
		    ////objAppointmentdtlVO.setInstanceNo(tagId);
		    ////mpNewAppointment.put(key, objAppointmentdtlVO);					  
			////request.getSession().setAttribute(AppointmentConfig.APPOINMENT_MAP,mpNewAppointment);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		return strResultTag;
	}
	
	public static String checkSlotAvailibilty(AppointmentGlobalSUP aptFB,HttpServletRequest request,HttpServletResponse response) 
	{
		String aptId="";
		String actualParaRefId="";
		String aptPara1="",aptPara2="",aptPara3="",aptPara4="",aptPara5="",aptPara6="",aptPara7="";
		String slotST="",slotET="";
		String slotType="";
		String shiftId="";
		String aptDate="";
		String slotStatus="";
		
		try
		{	
			UserVO userVO= ControllerUTIL.getUserVO(request);
			String[] actualParaId= new String[7];
			
			AppointmentSlotDtlVO appParaObj= new AppointmentSlotDtlVO();
			
			
			aptId=aptFB.getAppointmentForId();
			actualParaRefId=aptFB.getActualParameterReferenceId();
			if(aptPara1==null || aptPara2==null)
			{
				return "No Values Defined";
			}
			if(aptPara3==null)aptPara3= "0";
			if(aptPara4==null)aptPara4= "0";
			if(aptPara5==null)aptPara5= "0";
			if(aptPara6==null)aptPara6= "0";
			if(aptPara7==null)aptPara7= "0";
		
			if(aptId!=null && actualParaRefId!=null)
			{
							/*actualParaId[0]=aptPara1;
							actualParaId[1]=aptPara2;
							actualParaId[2]=aptPara3;
							actualParaId[3]=aptPara4;
							actualParaId[4]=aptPara5;
							actualParaId[5]=aptPara6;
							actualParaId[6]=aptPara7;
							appParaObj.setAppointmentForId(aptId);				
							appParaObj.setActualParameterId(actualParaId);
							appParaObj.setActualParaRefId(actualParaRefId);
							appParaObj.setParaId1(aptPara1);
							appParaObj.setParaId3(aptPara3);
							appParaObj.setParaId4(aptPara4);
							appParaObj.setParaId5(aptPara5);
							appParaObj.setParaId6(aptPara6);
							appParaObj.setParaId7(aptPara7);
							appParaObj.setShiftId(shiftId);
							appParaObj.setSlotStartTime(slotST);
							appParaObj.setSlotEndTime(slotET);
							appParaObj.setAppointmentDate(aptDate);*/
				
							appParaObj.setAppointmentForId(aptFB.getAppointmentForId());
							appParaObj.setActualParaRefId(aptFB.getActualParameterReferenceId());
							appParaObj.setAppointmentDate(aptFB.getAppointmentDate());
							appParaObj.setSlotStartTime(aptFB.getSlotST());
							appParaObj.setSlotEndTime(aptFB.getSlotET());
							appParaObj.setShiftStartTime(aptFB.getShiftST());
							appParaObj.setShiftEndTime(aptFB.getShiftET());
							
							slotStatus=AppointmentTagDATA.checkSlotAvailibilty(userVO,appParaObj);		
							
							
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		WebUTIL.writeResponse(response, slotStatus, null);
		return slotStatus;
	}
	
	//To Insert the Slot wise Appointment Details in the HAPT_APPOINTMENT_DTL, Added by Singaravelan on 22-Jan-2015
	public static void HoldSlotForNewAppointment(AppointmentGlobalSUP aptFB,HttpServletRequest objRequest,HttpServletResponse objResponse) {
		
		Status status=new Status();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			NewAppointmentVO objNewAppointmentVO= new NewAppointmentVO(); 
			List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>();
					
			HelperMethods.populate(objNewAppointmentVO, aptFB);
			objNewAppointmentVO.setAppointmentTime(aptFB.getSlotST());
						
			String[] actualParaId= new String[7];
			if(aptFB.getActualParameterId()!=null &&  aptFB.getActualParameterId().length>0){
				for(int i=0;i<aptFB.getActualParameterId().length;i++){
					actualParaId[i]=aptFB.getActualParameterId()[i];
				}
				
				for(int i=aptFB.getActualParameterId().length; i<7;i++){
					actualParaId[i]="0";
				}
				objNewAppointmentVO.setActualParameterId(actualParaId);
				objNewAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_NEW);
				if(aptFB.getSlotType().equals("4"))
					objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_VIP);
				else if(aptFB.getSlotType().equals("5"))
					objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_OVERBOOK);
				else
					objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE);
				
				//PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute("patientVO");
				if(objPatientVO!=null)
					HelperMethods.populate(objNewAppointmentVO, objPatientVO);
				else
					objNewAppointmentVO.setPatCrNo(null);
				
				//if((List<NewAppointmentVO>)WebUTIL.getSession(objRequest).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
				//	objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(objRequest).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
				
				objNewAppointmentVO_arr.add(objNewAppointmentVO);
				WebUTIL.setAttributeInSession(objRequest, AppointmentConfig.APPOINTMENT_VO_LIST, objNewAppointmentVO_arr);
				WebUTIL.setAttributeInSession(objRequest, AppointmentConfig.HOLDED_SLOT_TIME, objNewAppointmentVO.getSlotST());

				status.add(Status.DONE,"Slot Holded Successfully" ,"");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
		
	}
	
	//To Insert the Slot wise Appointment Details in the HAPT_APPOINTMENT_DTL, Added by Singaravelan on 22-Jan-2015
	public static void SaveNewAppointment(AppointmentGlobalSUP aptFB,HttpServletRequest objRequest,HttpServletResponse objResponse) {
		
		Status status=new Status();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			NewAppointmentVO objNewAppointmentVO= new NewAppointmentVO(); 
			Boolean isParsed=false;
			if((List<NewAppointmentVO>)WebUTIL.getSession(objRequest).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null){
				SaveHoldedAppointment(aptFB, objRequest, objResponse);
			}
			else
			{
				HelperMethods.populate(objNewAppointmentVO, aptFB);
				objNewAppointmentVO.setAppointmentTime(aptFB.getSlotST());
				objNewAppointmentVO.setAppointmentTypeId(aptFB.getSlotType());
				
				String[] actualParaId = new String[7],_actPId= new String[7];
				if(aptFB.getActualParameterId()!=null &&  aptFB.getActualParameterId().length>0){
					for(int i=0;i<aptFB.getActualParameterId().length;i++){
						if(aptFB.getActualParameterId()[i].indexOf("^")>0)
						{
							_actPId=aptFB.getActualParameterId()[i].replace("^", "#").split("#");							
							isParsed=true;
						}
						else
							actualParaId[i]=aptFB.getActualParameterId()[i];
					}
					
					if(!isParsed){
						for(int i=aptFB.getActualParameterId().length; i<7;i++){
							actualParaId[i]="0";
						}
					}
					if(isParsed)
						objNewAppointmentVO.setActualParameterId(_actPId);
					else
						objNewAppointmentVO.setActualParameterId(actualParaId);

					objNewAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_NEW);
					if(aptFB.getSlotType().equals("4"))
						objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_VIP);
					else if(aptFB.getSlotType().equals("5"))
						objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_OVERBOOK);
					else
						objNewAppointmentVO.setSlotType(AppointmentConfig.SLOT_TYPE_PRIOR_APPOINTMENT_FROM_APPT_MODULE);
					
					//PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
					PatientVO objPatientVO=(PatientVO) objRequest.getSession().getAttribute("patientVO");
					if(objPatientVO!=null)
						HelperMethods.populate(objNewAppointmentVO, objPatientVO);
					else
						objNewAppointmentVO.setPatCrNo(aptFB.getPatCrNo());
					objNewAppointmentVO= NewAppointmentDATA.SaveNewAppointment(objNewAppointmentVO,userVO);
					status.add(Status.DONE,"Appointment Created Successfully" ,"");
					String appointmentandQueueNo=objNewAppointmentVO.getAppointmentNo()+"#"+objNewAppointmentVO.getAppointmentQueueNo();
					WebUTIL.writeResponse(objResponse, appointmentandQueueNo,null);
					//WebUTIL.writeResponse(objResponse, objNewAppointmentVO.getAppointmentNo(),null);
			}
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
		
	}
	
	//To Insert the Slot wise Appointment Details in the HAPT_APPOINTMENT_DTL, Added by Singaravelan on 22-Jan-2015
	public static void SaveHoldedAppointment(AppointmentGlobalSUP aptFB,HttpServletRequest objRequest,HttpServletResponse objResponse) {
		
		Status status=new Status();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			NewAppointmentVO objNewAppointmentVO= new NewAppointmentVO();
			List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>();
			boolean isParsed=false;
			String _actPId[]=new String[7];
			if((List<NewAppointmentVO>)WebUTIL.getSession(objRequest).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
				objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(objRequest).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);

			for(NewAppointmentVO objNewAppointment_p:objNewAppointmentVO_arr)	
			{
				objNewAppointmentVO=objNewAppointment_p;
				for(int i=0;i<objNewAppointmentVO.getActualParameterId().length;i++){
					if(objNewAppointmentVO.getActualParameterId()[i].indexOf("^")>0)
					{
						_actPId=objNewAppointmentVO.getActualParameterId()[i].replace("^", "#").split("#");
						isParsed=true;
					}
				}
				
				if(isParsed)
					objNewAppointmentVO.setActualParameterId(_actPId);
				
				objNewAppointmentVO.setAppointmentTime(aptFB.getSlotST());
				objNewAppointmentVO.setAppointmentTypeId(aptFB.getSlotType());
				
				objNewAppointmentVO= NewAppointmentDATA.SaveNewAppointment(objNewAppointmentVO,userVO);
				status.add(Status.DONE,"Appointment Created Successfully" ,"");
				WebUTIL.writeResponse(objResponse, objNewAppointmentVO.getAppointmentNo(),null);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
		
	}
	
	public static String checkSlotAvailibiltyOnSaveTime(AppointmentGlobalSUP aptFB,HttpServletRequest request,HttpServletResponse response) 
	{
		String aptId="";
		String actualParaRefId="";
		String aptPara1="",aptPara2="",aptPara3="",aptPara4="",aptPara5="",aptPara6="",aptPara7="";
		String slotST="",slotET="";
		String slotType="";
		String shiftId="";
		String aptDate="";
		String slotStatus="";
		
		try
		{	
			UserVO userVO= ControllerUTIL.getUserVO(request);
			String[] actualParaId= new String[7];
			
			List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>();

			if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null){
				objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);


				for(NewAppointmentVO objNewAppointment_p:objNewAppointmentVO_arr)	
				{
					AppointmentSlotDtlVO appParaObj= new AppointmentSlotDtlVO();
				
				
					aptId=objNewAppointment_p.getAppointmentForId();
					actualParaRefId=objNewAppointment_p.getActualParameterReferenceId();
					if(aptPara1==null || aptPara2==null)
					{
						return "No Values Defined";
					}
					if(aptPara3==null)aptPara3= "0";
					if(aptPara4==null)aptPara4= "0";
					if(aptPara5==null)aptPara5= "0";
					if(aptPara6==null)aptPara6= "0";
					if(aptPara7==null)aptPara7= "0";
			
					if(aptId!=null && actualParaRefId!=null)
					{					
			
						appParaObj.setAppointmentForId(objNewAppointment_p.getAppointmentForId());
						appParaObj.setActualParaRefId(objNewAppointment_p.getActualParameterReferenceId());
						appParaObj.setAppointmentDate(objNewAppointment_p.getAppointmentDate());
						appParaObj.setSlotStartTime(objNewAppointment_p.getSlotST());
						appParaObj.setSlotEndTime(objNewAppointment_p.getSlotET());
						
						slotStatus=AppointmentTagDATA.checkSlotAvailibilty(userVO,appParaObj);		
						
						
					}
				}					
			}
			else
				slotStatus=checkSlotAvailibilty(aptFB, request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		WebUTIL.writeResponse(response, slotStatus, null);
		return slotStatus;
	}
	
	public static String MakeTimeTag(HttpServletRequest request,HttpServletResponse response) 
	{
		
		String strResultTag="",strResultTag2="";
		boolean breakLoop=false;
		List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>();

		try
		{	
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uniqueId=(String) sdf.format(cal.getTime());
			Map mpActualParaRefId=new HashMap();
			
			if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
				objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);

			
			if(objNewAppointmentVO_arr!=null && objNewAppointmentVO_arr.size()>0)
			{
				for(NewAppointmentVO _objNewAptVo:objNewAppointmentVO_arr)
				{
					
						strResultTag+="<input type='hidden' name='actualParameterId' value='"+_objNewAptVo.getActualParameterId()+"'>";
						strResultTag+="<input type='hidden' name='shiftST' value='"+_objNewAptVo.getShiftST()+"'>";
						strResultTag+="<input type='hidden' name='shiftET' value='"+_objNewAptVo.getShiftET()+"'>";
						strResultTag+="<input type='hidden' name='aptFor' value='"+_objNewAptVo.getAppointmentForId()+"'>";
						strResultTag+="<input type='hidden' name='actualParaRefId' value='"+_objNewAptVo.getActualParameterReferenceId()+"'>";
						strResultTag+="<input type='hidden' name='aptForDate' value='"+_objNewAptVo.getAppointmentDate()+"'>";
						strResultTag+="<div id='aptDate' style='display:none'>"+_objNewAptVo.getAppointmentDate()+"</div>";
						strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	

						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						//if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
						{
							//String cssClass=ws.getString(11);
							strResultTag+="<tr><td width='5%'><div align='center'><div id='freeSlotLabel'><font size='2px'> "+_objNewAptVo.getAppointmentDate()+"&nbsp;"+_objNewAptVo.getSlotST()+"&nbsp;Free</font></div>";
							strResultTag+="<input type='hidden' name='slotST' value='"+_objNewAptVo.getSlotST()+"'>";
							strResultTag+="<input type='hidden' name='slotET' value='"+_objNewAptVo.getSlotET()+"'>";
							strResultTag+="<input type='hidden' name='shiftId' value='"+_objNewAptVo.getShiftId()+"'>";
							strResultTag+="<input type='hidden' name='aptType' value='"+_objNewAptVo.getAppointmentTypeId()+"'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openApptPopup(this)\">";
							strResultTag+="</div></td></tr></table>";							
							breakLoop=true;
						}						
					
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//WebUTIL.writeResponse(response, strResultTag, null);
		try
		{
			PrintWriter writer=response.getWriter();
			writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		return strResultTag;
	}
	
	public static String MakeTimeSlotTag(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
			String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate) 
	{
		
		String strResultTag="";
		String allactualParaId="";
		WebRowSet ws=null;
		int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
		String oldShiftId="",slotUniqueId="";
		boolean breakLoop=false;
		List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
		List<String> slotTimes=new ArrayList<>(); 
		List<String> apptDates=new ArrayList<>(); 
		
		try
		{	
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String uniqueId=(String) sdf.format(cal.getTime());
			Map mpActualParaRefId=new HashMap();
		
			if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
				objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
			
			for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
				slotTimes.add(obj_newApt.getSlotST());
				apptDates.add(obj_newApt.getAppointmentDate());
			}
		
			UserVO userVO= ControllerUTIL.getUserVO(request);
			String bookedDate=""; 
			String BookedTime="";
			String shiftId="";
			String[] actualParaId= new String[7];
			String actualParaRefIdSlotDuration=null;
			String actualParaRefId=null;
			String slotDuration=null;			
			
			if(paraId1==null || paraId2==null)
			{
				return "";
			}
			if(paraId3==null)paraId3= "0";
			if(paraId4==null)paraId4= "0";
			if(paraId5==null)paraId5= "0";
			if(paraId6==null)paraId6= "0";
			if(paraId7==null)paraId7= "0";
		
			String key="APTOBJ_TAG"+tagId;
			boolean flagExist=false;
				  			  
				  
			if(flagExist==false){
				if(aptId!=null)
				{							
					actualParaId[0]=paraId1;
					actualParaId[1]=paraId2;
					actualParaId[2]=paraId3;
					actualParaId[3]=paraId4;
					actualParaId[4]=paraId5;
					actualParaId[5]=paraId6;
					actualParaId[6]=paraId7;
					allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
				    
					AppointmentParameterVO appParaObj= new AppointmentParameterVO();
					appParaObj.setAppointmentForId(aptId);				
					appParaObj.setArrActualParaId(actualParaId);

					actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
					actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#")[0];
					slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#")[1];
					appParaObj.setPatCrno(crno);
					appParaObj.setSlotDuration(slotDuration);
					appParaObj.setAppointmentForDate(aptDate);
					if(actualParaRefId!=null && actualParaRefId!=""){
						appParaObj.setActualParameterReferenceId(actualParaRefId);						
						ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
													
					}					
					else{
						return "Appointment Not Configured";										
					}										
				}							
			  }
			  else
			  {									  
			  }
			if(ws!=null && ws.size()>0)
			{
				while(ws.next() && !breakLoop)
				{
					
					if(tagView.equals("1"))//Ist Free Slot View
					{
						strResultTag+="<input type='hidden' name='allactualParameterId' value='"+allactualParaId+"'>";
						strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
						strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
						strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
						strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
						strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
						strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
						strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
						System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
						{
							String cssClass=ws.getString(11);
							strResultTag+="<tr><td width='5%'><div align='left'><span id='freeSlotLabel'><font size='2px'> "+ws.getString(1)+"&nbsp;<span id='freeSlotTime'>"+ws.getString(8)+"</span>&nbsp;"+ws.getString(11)+"&nbsp;</font></span>";
							strResultTag+="<input type='hidden' name='slotST' value='"+ws.getString(8)+"'>";
							strResultTag+="<input type='hidden' name='slotET' value='"+ws.getString(9)+"'>";
							strResultTag+="<input type='hidden' name='shiftId' value='"+ws.getString(2)+"'>";
							strResultTag+="<input type='hidden' name='aptType' value='"+ws.getString(10)+"'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openApptPopup(this)\">";
							strResultTag+="</div></td></tr></table>";							
							breakLoop=true;
						}						
					}
					else
					{
						runningNo++;
						System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

						if(shiftcounter==0)
						{
							strResultTag+="<input type='hidden' name='allactualParameterId' value='"+allactualParaId+"'>";
							strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
							strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
							strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
							strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
							strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
							strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
							strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
							strResultTag+="</div></td>";
							strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
							strResultTag+="<td  class='header'  width='5%'><div align='right'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
							strResultTag+="</div></td></tr></table>";
							strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
						}
						else if(!oldShiftId.equals(ws.getString(2)))
						{
							++nextShift;
							strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
							strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
							strResultTag+="</div></td>";
							strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
							strResultTag+="<td  class='header'  width='5%'><div align='right'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
							strResultTag+="</div></td></tr></table>";
							strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
						}
							
						oldShiftId=ws.getString(2);
						shiftcounter++;				
						
						if(cnt%5==0)
						{
							rowcount++;						
							if(cnt!=0)
							{
								strResultTag+="</tr><tr>";
							}
							else
							{			
								strResultTag+="<tr>";
							}
						}								
						cnt++;
		
						String cssClass="";
						String bcssClass="";
						String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
						if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
							cssClass="Hold";
							bcssClass="Hold";
						}
						else{
							cssClass=ws.getString(11);
							bcssClass=ws.getString(11);
						}
						slotUniqueId=ws.getString(2)+""+runningNo;
						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						
							
						String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
						
						if(ws.getString(10).equals("5"))//OverBooked Slot
							slotType="5";//Overbooked
							
						//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
						String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"')";
						
						
						if(shiftcounter<=4)
							strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
						else if(ws.getString(10).equals("1"))//Free Slot
							strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						else if(ws.getString(10).equals("5"))//Overbooked Slot
							strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						else						
							strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
						strResultTag+="<a>"+ws.getString(8)+"</a>";
						strResultTag+="</div></td>";
					}
				}
				if(!tagView.equals("1"))//Whole Tag View
				{
					strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
					strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
					strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="</table>";
				
					//VIP Slots logic Not Implemented Yet
					/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
					strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
					strResultTag+="<td width='10%' colspan='1'><div align='right'>";
					strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
					strResultTag+="</table>";*/
					
					strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
					strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
					strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
					strResultTag+="</tr>";
					strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
					strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
					strResultTag+="</tr>";
					strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
					strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
					strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
					strResultTag+="</table>";
					strResultTag+="</div>";
				}
			
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			PrintWriter writer=response.getWriter();
			writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		return strResultTag;
	}
	
	//For Multiple Row Appointmnet Tag Integration Added by Singaravelan on 02-May-2015
	//For Multiple Row Appointmnet Tag Integration Added by Singaravelan on 02-May-2015
		public static void MakeAppointmentTag(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
				String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate,String divrowId) 
		{
			
			////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
			String strResultTag="",strResultTag2="";
			String allactualParaId="";
			Map mpAllAppointmentProcessSpecificDetails= new HashMap();
			Map mppreviousAppointmentProcessDtl= new HashMap();
			WebRowSet ws=null;
			int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
			String oldShiftId="",slotUniqueId="";
			boolean breakLoop=false;
			List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
			List<String> slotTimes=new ArrayList<>(); 
			List<String> apptDates=new ArrayList<>(); 
			
			try
			{	
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String uniqueId=(String) sdf.format(cal.getTime());
				Map mpActualParaRefId=new HashMap();
				////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
			
				if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
					objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
				
				for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
					slotTimes.add(obj_newApt.getSlotST());
					apptDates.add(obj_newApt.getAppointmentDate());
				}
			
				UserVO userVO= ControllerUTIL.getUserVO(request);
				String bookedDate=""; 
				String BookedTime="";
				String shiftId="";
				String[] actualParaId= new String[7];
				String actualParaRefIdSlotDuration=null;
				String actualParaRefId=null;
				String slotDuration=null;
				String rowWiseId=divrowId;
			
				////String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
				////if(init==null)
					////return "";
				
				if(paraId1==null || paraId2==null)
				{
					strResultTag="";
				}
				if(paraId3==null)paraId3= "0";
				if(paraId4==null)paraId4= "0";
				if(paraId5==null)paraId5= "0";
				if(paraId6==null)paraId6= "0";
				if(paraId7==null)paraId7= "0";
			
					  String key="APTOBJ_TAG"+tagId;
					 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
					  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
					  boolean flagExist=false;
					  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
					  {
						  if(mpNewAppointment.containsKey(key))
							  flagExist=true;
						  else
							  flagExist=false;	
					  }
					  else
					  {
						  flagExist=false;
						  ////mpNewAppointment= new HashMap();
					  }*///
					  
					  
					  if(flagExist==false  )
					  {
						  ////mpNewAppointment= new HashMap(); 
						  
							if(aptId!=null)
							{							
								actualParaId[0]=paraId1;
								actualParaId[1]=paraId2;
								actualParaId[2]=paraId3;
								actualParaId[3]=paraId4;
								actualParaId[4]=paraId5;
								actualParaId[5]=paraId6;
								actualParaId[6]=paraId7;
								allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
							    
								////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
								AppointmentParameterVO appParaObj= new AppointmentParameterVO();
								appParaObj.setAppointmentForId(aptId);				
								appParaObj.setArrActualParaId(actualParaId);
								////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

								actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
								actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
								slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
								appParaObj.setPatCrno(crno);
								appParaObj.setSlotDuration(slotDuration);
								appParaObj.setAppointmentForDate(aptDate);
								if(actualParaRefId!=null && actualParaRefId!="")
								{
									appParaObj.setActualParameterReferenceId(actualParaRefId);
									
									ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
									//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
									////Map mpDatesforActualParaRefId=null;
									////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
										////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
									////appParaObj.setAppointmentCallStatus("BY_TAG");
									
									/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
									List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
									request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
									mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
									ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
									String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
									bookedDate =availableDateTime.replace("^","#").split("#")[0];
									BookedTime =availableDateTime.replace("^","#").split("#")[1];
									shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
									
								}					
								else
								{
									strResultTag= "Appointment Not Configured";											
								}										
							}						
							
						
					  }
					  else
					  {
						  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
						  bookedDate=objAppointmentdtlVO.getAppointmentDate();
						  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////					  
					  }
				if(ws!=null && ws.size()>0)
				{
					while(ws.next() && !breakLoop)
					{
						
						if(tagView.equals("1"))//Ist Free Slot View
						{
							strResultTag+="<input type='hidden' name='allactualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
							strResultTag+="<input type='hidden' name='actualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
							strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
							strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
							strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
							strResultTag+="<input type='hidden' name='actualParaRefId_"+divrowId+"' value='"+actualParaRefId+"'>";
							strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
							strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
							
							//By Mukund for opd & ipd slots 
							strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";
							
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
							//System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

							//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
							String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
							if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
							{
								String cssClass=ws.getString(11);
								strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel_"+divrowId+"'><font size='2px'> "+ws.getString(1)+"&nbsp;"+ws.getString(8)+"&nbsp;"+ws.getString(11)+"</font></div>";
								strResultTag+="<input type='hidden' name='slotST_"+divrowId+"' value='"+ws.getString(8)+"'>";
								strResultTag+="<input type='hidden' name='slotET_"+divrowId+"' value='"+ws.getString(9)+"'>";
								strResultTag+="<input type='hidden' name='shiftId_"+divrowId+"' value='"+ws.getString(2)+"'>";
								strResultTag+="<input type='hidden' name='aptType_"+divrowId+"' value='"+ws.getString(10)+"'>";
								strResultTag+="<img class='button' src='/HIS/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openDivApptPopup(this,'"+divrowId+"')\">";
								strResultTag+="</div></td></tr></table>";							
								breakLoop=true;
							}						
						}
						else
						{
							runningNo++;
							//System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

							if(shiftcounter==0)
							{
								strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
								strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
								strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
								strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
								strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
								strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
								
								//By Mukund for opd & ipd slots 
								strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";
								
								
								strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
								strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
								strResultTag+="</div></td>";
								strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
								strResultTag+="<td  class='header'  width='5%'><div align='right'>";
								strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
								strResultTag+="</div></td></tr></table>";
								strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
							}
							else if(!oldShiftId.equals(ws.getString(2)))
							{
								++nextShift;
								strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
								strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
								strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
								strResultTag+="</div></td>";
								strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
								strResultTag+="<td  class='header'  width='5%'><div align='right'>";
								strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
								strResultTag+="</div></td></tr></table>";
								strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
							}
								
							oldShiftId=ws.getString(2);
							shiftcounter++;
											
							
							
							if(cnt%5==0)
							{
								rowcount++;						
								if(cnt!=0)
								{
									strResultTag+="</tr><tr>";
								}
								else
								{			
									strResultTag+="<tr>";
								}
							}								
							cnt++;
								
							
							//strResultTag+="<tr>";
							String cssClass="";
							String bcssClass="";
							String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
							if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
								cssClass="Hold";
								bcssClass="Hold";
							}
							else{
								cssClass=ws.getString(11);
								bcssClass=ws.getString(11);
							}
							slotUniqueId=ws.getString(2)+""+runningNo;
							//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
							
								
							String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
							
							if(ws.getString(10).equals("5"))//OverBooked Slot
								slotType="5";//Overbooked
								
							//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
							String onclick="BookDivSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"','"+rowWiseId+"')";
							
							
							/*if(shiftcounter<=4)
								strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
							else*/ if(ws.getString(10).equals("1"))//Free Slot
								strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
							else if(ws.getString(10).equals("5"))//Overbooked Slot
								strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
							else						
								strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
							//strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
							//strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
							strResultTag+="<a>"+ws.getString(8)+"</a>";
							strResultTag+="</div></td>";
							/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
							strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
							strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
							strResultTag+="</div></td>";*/
							//strResultTag+="</tr>";					
						}
					}
					if(!tagView.equals("1"))//Whole Tag View
					{
						strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
						strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
						strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
						strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
						strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
						strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
						strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
						strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
						strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
						strResultTag+="</table>";
					
				   
						//VIP Slots logic Not Implemented Yet
						/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
						strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
						strResultTag+="<td width='10%' colspan='1'><div align='right'>";
						strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
						strResultTag+="</table>";*/
						
						strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
						strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
						strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
						//strResultTag+="<td width='10%' colspan='1'><div align='right'>";
						//strResultTag+="<img class='button' src='/HIS/hisglobal/images/cancel.png' style='cursor: pointer;'  onclick='closeVipApp()'></div></td>";
						strResultTag+="</tr>";
						strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
						strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
						strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
						strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
						strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
						strResultTag+="</tr>";
						strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
						strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
						strResultTag+="<img class='button' src='/HIS/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
						strResultTag+="</table>";
						strResultTag+="</div>";
					}
				
			}
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//WebUTIL.writeResponse(response, strResultTag, null);
			try
			{
				//PrintWriter writer=response.getWriter();
				//System.out.println("Size of buffer is"+response.getBufferSize());
				//System.out.println("Size of COntent is "+strResultTag.length());
				//response.setBufferSize(32*1024);
				//writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
				
				WebUTIL.writeResponse(response, "ID_PARAMETER_CODE"+"@"+strResultTag, "text/html");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//return strResultTag;
		}
		
		
		//For Multiple Row Appointmnet Tag Integration Added by Singaravelan on 02-May-2015
			public static String MakeAppointmentWholeTagForInvestigation(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
					String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate,String divrowId) 
			{
				
				////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
				String strResultTag="",strResultTag2="";
				String allactualParaId="";
				Map mpAllAppointmentProcessSpecificDetails= new HashMap();
				Map mppreviousAppointmentProcessDtl= new HashMap();
				WebRowSet ws=null;
				int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
				String oldShiftId="",slotUniqueId="";
				boolean breakLoop=false;
				List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
				List<String> slotTimes=new ArrayList<>(); 
				List<String> apptDates=new ArrayList<>(); 
				
				try
				{	
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String uniqueId=(String) sdf.format(cal.getTime());
					Map mpActualParaRefId=new HashMap();
					////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
				
					if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
						objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
					
					for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
						slotTimes.add(obj_newApt.getSlotST());
						apptDates.add(obj_newApt.getAppointmentDate());
					}
				
					UserVO userVO= ControllerUTIL.getUserVO(request);
					String bookedDate=""; 
					String BookedTime="";
					String shiftId="";
					String[] actualParaId= new String[7];
					String actualParaRefIdSlotDuration=null;
					String actualParaRefId=null;
					String slotDuration=null;
					String rowWiseId=divrowId;
				
					////String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
					////if(init==null)
						////return "";
					
					if(paraId1==null || paraId2==null)
					{
						return "";
					}
					if(paraId3==null)paraId3= "0";
					if(paraId4==null)paraId4= "0";
					if(paraId5==null)paraId5= "0";
					if(paraId6==null)paraId6= "0";
					if(paraId7==null)paraId7= "0";
				
						  String key="APTOBJ_TAG"+tagId;
						 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
						  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
						  boolean flagExist=false;
						  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
						  {
							  if(mpNewAppointment.containsKey(key))
								  flagExist=true;
							  else
								  flagExist=false;	
						  }
						  else
						  {
							  flagExist=false;
							  ////mpNewAppointment= new HashMap();
						  }*///
						  
						  
						  if(flagExist==false  )
						  {
							  ////mpNewAppointment= new HashMap(); 
							  
								if(aptId!=null)
								{							
									actualParaId[0]=paraId1;
									actualParaId[1]=paraId2;
									actualParaId[2]=paraId3;
									actualParaId[3]=paraId4;
									actualParaId[4]=paraId5;
									actualParaId[5]=paraId6;
									actualParaId[6]=paraId7;
									allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
								    
									////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
									AppointmentParameterVO appParaObj= new AppointmentParameterVO();
									appParaObj.setAppointmentForId(aptId);				
									appParaObj.setArrActualParaId(actualParaId);
									////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

									actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
									actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
									slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
									appParaObj.setPatCrno(crno);
									appParaObj.setSlotDuration(slotDuration);
									appParaObj.setAppointmentForDate(aptDate);
									if(actualParaRefId!=null && actualParaRefId!="")
									{
										appParaObj.setActualParameterReferenceId(actualParaRefId);
										
										ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
										//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
										////Map mpDatesforActualParaRefId=null;
										////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
											////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
										////appParaObj.setAppointmentCallStatus("BY_TAG");
										
										/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
										List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
										request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
										mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
										ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
										String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
										bookedDate =availableDateTime.replace("^","#").split("#")[0];
										BookedTime =availableDateTime.replace("^","#").split("#")[1];
										shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
										
									}					
									else
									{
										return "Appointment Not Configured";											
									}										
								}						
								
							
						  }
						  else
						  {
							  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
							  bookedDate=objAppointmentdtlVO.getAppointmentDate();
							  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////					  
						  }
					if(ws!=null && ws.size()>0)
					{
						while(ws.next() && !breakLoop)
						{
							
							if(tagView.equals("1"))//Ist Free Slot View
							{
								strResultTag+="<input type='hidden' name='allactualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='actualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
								strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
								strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
								strResultTag+="<input type='hidden' name='actualParaRefId_"+divrowId+"' value='"+actualParaRefId+"'>";
								strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
								strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
								strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
								//System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
								{
									String cssClass=ws.getString(11);
									strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel_"+divrowId+"'><font size='2px'> "+ws.getString(1)+"&nbsp;"+ws.getString(8)+"&nbsp;"+ws.getString(11)+"</font></div>";
									strResultTag+="<input type='hidden' name='slotST_"+divrowId+"' value='"+ws.getString(8)+"'>";
									strResultTag+="<input type='hidden' name='slotET_"+divrowId+"' value='"+ws.getString(9)+"'>";
									strResultTag+="<input type='hidden' name='shiftId_"+divrowId+"' value='"+ws.getString(2)+"'>";
									strResultTag+="<input type='hidden' name='aptType_"+divrowId+"' value='"+ws.getString(10)+"'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openDivApptPopup(this,'"+divrowId+"')\">";
									strResultTag+="</div></td></tr></table>";							
									breakLoop=true;
								}						
							}
							else
							{
								runningNo++;
								//System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

								if(shiftcounter==0)
								{
									strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
									strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
									strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
									strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
									strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
									strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
									strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
								else if(!oldShiftId.equals(ws.getString(2)))
								{
									++nextShift;
									strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
									
								oldShiftId=ws.getString(2);
								shiftcounter++;
												
								
								
								if(cnt%5==0)
								{
									rowcount++;						
									if(cnt!=0)
									{
										strResultTag+="</tr><tr>";
									}
									else
									{			
										strResultTag+="<tr>";
									}
								}								
								cnt++;
									
								
								//strResultTag+="<tr>";
								String cssClass="";
								String bcssClass="";
								String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
								if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
									cssClass="Hold";
									bcssClass="Hold";
								}
								else{
									cssClass=ws.getString(11);
									bcssClass=ws.getString(11);
								}
								slotUniqueId=ws.getString(2)+""+runningNo;
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								
									
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								
								if(ws.getString(10).equals("5"))//OverBooked Slot
									slotType="5";//Overbooked
									
								//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
								String onclick="BookDivSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"','"+rowWiseId+"')";
								
								
								/*if(shiftcounter<=4)
									strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
								else*/ if(ws.getString(10).equals("1"))//Free Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else if(ws.getString(10).equals("5"))//Overbooked Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else						
									strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								//strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
								//strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
								strResultTag+="<a>"+ws.getString(8)+"</a>";
								strResultTag+="</div></td>";
								/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
								strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
								strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
								strResultTag+="</div></td>";*/
								//strResultTag+="</tr>";					
							}
						}
						if(!tagView.equals("1"))//Whole Tag View
						{
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
							strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="</table>";
						
					   
							//VIP Slots logic Not Implemented Yet
							/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
							strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
							strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
							strResultTag+="</table>";*/
							
							strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
							//strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							//strResultTag+="<img class='button' src='/HIS/hisglobal/images/cancel.png' style='cursor: pointer;'  onclick='closeVipApp()'></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
							strResultTag+="</table>";
							strResultTag+="</div>";
						}
					
				}
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//WebUTIL.writeResponse(response, strResultTag, null);
				/*try
				{
					//PrintWriter writer=response.getWriter();
					//System.out.println("Size of buffer is"+response.getBufferSize());
					//System.out.println("Size of COntent is "+strResultTag.length());
					//response.setBufferSize(32*1024);
					//writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
					
					WebUTIL.writeResponse(response, "ID_PARAMETER_CODE"+"@"+strResultTag, "text/html");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}*/	
				return strResultTag;
			}
			/*For Creating TimeSlot  with capping of IPD and OPD*/
			public static String MakeTimeSlotTag_withCapping(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
					String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate, String apptType) 
			{
				
				String strResultTag="";
				String allactualParaId="";
				WebRowSet ws=null;
				int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
				String oldShiftId="",slotUniqueId="", opdSlotsAll="", opdSlotsBooked="", ipdSlotsAll="", ipdSlotsBooked="";
				boolean breakLoop=false, createFreeSlotFlag=false;
				List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
				List<String> slotTimes=new ArrayList<>(); 
				List<String> apptDates=new ArrayList<>(); 
				
				try
				{	
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String uniqueId=(String) sdf.format(cal.getTime());
					Map mpActualParaRefId=new HashMap();
				
					if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
						objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
					
					for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
						slotTimes.add(obj_newApt.getSlotST());
						apptDates.add(obj_newApt.getAppointmentDate());
					}
				
					UserVO userVO= ControllerUTIL.getUserVO(request);
					String bookedDate=""; 
					String BookedTime="";
					String shiftId="";
					String[] actualParaId= new String[7];
					String actualParaRefIdSlotDuration=null;
					String actualParaRefId=null;
					String slotDuration=null;			
					
					if(paraId1==null || paraId2==null)
					{
						return "";
					}
					if(paraId3==null)paraId3= "0";
					if(paraId4==null)paraId4= "0";
					if(paraId5==null)paraId5= "0";
					if(paraId6==null)paraId6= "0";
					if(paraId7==null)paraId7= "0";
				
					String key="APTOBJ_TAG"+tagId;
					boolean flagExist=false;
						  			  
						  
					if(flagExist==false){
						if(aptId!=null)
						{							
							actualParaId[0]=paraId1;
							actualParaId[1]=paraId2;
							actualParaId[2]=paraId3;
							actualParaId[3]=paraId4;
							actualParaId[4]=paraId5;
							actualParaId[5]=paraId6;
							actualParaId[6]=paraId7;
							allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
						    
							AppointmentParameterVO appParaObj= new AppointmentParameterVO();
							appParaObj.setAppointmentForId(aptId);				
							appParaObj.setArrActualParaId(actualParaId);

							actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
							actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#")[0];
							slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#")[1];
							appParaObj.setPatCrno(crno);
							appParaObj.setSlotDuration(slotDuration);
							appParaObj.setAppointmentForDate(aptDate);
							if(actualParaRefId!=null && actualParaRefId!=""){
								appParaObj.setActualParameterReferenceId(actualParaRefId);						
								ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
															
							}					
							else{
								return "Appointment Not Configured";										
							}										
						}							
					  }
					  else
					  {									  
					  }
					if(ws!=null && ws.size()>0)
					{
						while(ws.next() && !breakLoop)
						{
							opdSlotsAll=ws.getString(12);
							opdSlotsBooked=ws.getString(14);
							ipdSlotsAll=ws.getString(13);
							ipdSlotsBooked=ws.getString(15);
							
							if(tagView.equals("1"))//Ist Free Slot View
							{
								strResultTag+="<input type='hidden' name='allactualParameterId' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
								strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
								strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
								strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
								strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
								
								//By Mukund for opd & ipd slots 
								strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";

								strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
								strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
								System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));
								/***/
								if(apptType.equals("1") && Integer.parseInt(opdSlotsAll)!=0 ){//apptType -- 1 = OPD, 2 = IPD, 3 = EMG //ws.getString(12) = opdSlotsAll
									int availOpdSlots = Integer.parseInt(opdSlotsAll) - Integer.parseInt(opdSlotsBooked);
									if(availOpdSlots<=0){
										strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px' color='red'>No OPD Slots Available</font></div>";
										strResultTag+="</div></td></tr></table>";
										createFreeSlotFlag=true;
										breakLoop=true;
									}
								}else if(apptType.equals("2") && Integer.parseInt(ipdSlotsAll)!=0 ){
									int availIpdSlots = Integer.parseInt(ipdSlotsAll) - Integer.parseInt(ipdSlotsBooked);
									if(availIpdSlots<=0){
										strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px' color='red'>No IPD Slots Available</font></div>";
										strResultTag+="</div></td></tr></table>";
										createFreeSlotFlag=true;
										breakLoop=true;
									}
								}/***/
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
								{
									String cssClass=ws.getString(11);
									strResultTag+="<tr><td width='5%'><div align='left'><span id='freeSlotLabel'><font size='2px'> "+ws.getString(1)+"&nbsp;<span id='freeSlotTime'>"+ws.getString(8)+"</span>&nbsp;"+ws.getString(11)+"&nbsp;</font></span>";
									strResultTag+="<input type='hidden' name='slotST' value='"+ws.getString(8)+"'>";
									strResultTag+="<input type='hidden' name='slotET' value='"+ws.getString(9)+"'>";
									strResultTag+="<input type='hidden' name='shiftId' value='"+ws.getString(2)+"'>";
									strResultTag+="<input type='hidden' name='aptType' value='"+ws.getString(10)+"'>";
									strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openApptPopup(this)\">";
									strResultTag+="</div></td></tr></table>";							
									breakLoop=true;
								}						
							}
							else
							{
								runningNo++;
								System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

								if(shiftcounter==0)
								{
									strResultTag+="<input type='hidden' name='allactualParameterId' value='"+allactualParaId+"'>";
									strResultTag+="<input type='hidden' name='shiftST' value='"+ws.getString(4)+"'>";
									strResultTag+="<input type='hidden' name='shiftET' value='"+ws.getString(5)+"'>";
									strResultTag+="<input type='hidden' name='aptFor' value='"+aptId+"'>";
									strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
									strResultTag+="<input type='hidden' name='aptForDate' value='"+ws.getString(1)+"'>";
									strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
								else if(!oldShiftId.equals(ws.getString(2)))
								{
									++nextShift;
									strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
									
								oldShiftId=ws.getString(2);
								shiftcounter++;				
								
								if(cnt%5==0)
								{
									rowcount++;						
									if(cnt!=0)
									{
										strResultTag+="</tr><tr>";
									}
									else
									{			
										strResultTag+="<tr>";
									}
								}								
								cnt++;
				
								String cssClass="";
								String bcssClass="";
								String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
								if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
									cssClass="Hold";
									bcssClass="Hold";
								}
								else{
									cssClass=ws.getString(11);
									bcssClass=ws.getString(11);
								}
								slotUniqueId=ws.getString(2)+""+runningNo;
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								
									
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								
								if(ws.getString(10).equals("5"))//OverBooked Slot
									slotType="5";//Overbooked
									
								//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
								String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"')";
								
								
								if(shiftcounter<=4)
									strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
								else if(ws.getString(10).equals("1"))//Free Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else if(ws.getString(10).equals("5"))//Overbooked Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else						
									strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								strResultTag+="<a>"+ws.getString(8)+"</a>";
								strResultTag+="</div></td>";
							}
						}
						if(!tagView.equals("1"))//Whole Tag View
						{
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
							strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="</table>";
						
							//VIP Slots logic Not Implemented Yet
							/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
							strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
							strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
							strResultTag+="</table>";*/
							
							strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
							strResultTag+="<img class='button' src='"+AppointmentConfig.HIS_CONTEXTPATH+"/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
							strResultTag+="</table>";
							strResultTag+="</div>";
						}
					
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					PrintWriter writer=response.getWriter();
					writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}		
				return strResultTag;
			}//End : TimeSLotWIthCapping

			//For Multiple Row Appointmnet Tag Integration Added by Singaravelan on 02-May-2015
			public static String MakeAppointmentWholeTagForInvestigation_withCapping(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
					String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate,String divrowId, String apptType) 
			{
				
				////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
				String strResultTag="",strResultTag2="";
				String allactualParaId="";
				Map mpAllAppointmentProcessSpecificDetails= new HashMap();
				Map mppreviousAppointmentProcessDtl= new HashMap();
				WebRowSet ws=null;
				int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
				String oldShiftId="",slotUniqueId="", opdSlotsAll="", opdSlotsBooked="", ipdSlotsAll="", ipdSlotsBooked="";
				boolean breakLoop=false, createFreeSlotFlag=false;
				List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
				List<String> slotTimes=new ArrayList<>(); 
				List<String> apptDates=new ArrayList<>(); 
				
				try
				{	
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String uniqueId=(String) sdf.format(cal.getTime());
					Map mpActualParaRefId=new HashMap();
					////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
				
					if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
						objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
					
					for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
						slotTimes.add(obj_newApt.getSlotST());
						apptDates.add(obj_newApt.getAppointmentDate());
					}
				
					UserVO userVO= ControllerUTIL.getUserVO(request);
					String bookedDate=""; 
					String BookedTime="";
					String shiftId="";
					String[] actualParaId= new String[7];
					String actualParaRefIdSlotDuration=null;
					String actualParaRefId=null;
					String slotDuration=null;
					String rowWiseId=divrowId;
				
					////String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
					////if(init==null)
						////return "";
					
					if(paraId1==null || paraId2==null)
					{
						return "";
					}
					if(paraId3==null)paraId3= "0";
					if(paraId4==null)paraId4= "0";
					if(paraId5==null)paraId5= "0";
					if(paraId6==null)paraId6= "0";
					if(paraId7==null)paraId7= "0";
				
						  String key="APTOBJ_TAG"+tagId;
						 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
						  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
						  boolean flagExist=false;
						  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
						  {
							  if(mpNewAppointment.containsKey(key))
								  flagExist=true;
							  else
								  flagExist=false;	
						  }
						  else
						  {
							  flagExist=false;
							  ////mpNewAppointment= new HashMap();
						  }*///
						  
						  
						  if(flagExist==false  )
						  {
							  ////mpNewAppointment= new HashMap(); 
							  
								if(aptId!=null)
								{							
									actualParaId[0]=paraId1;
									actualParaId[1]=paraId2;
									actualParaId[2]=paraId3;
									actualParaId[3]=paraId4;
									actualParaId[4]=paraId5;
									actualParaId[5]=paraId6;
									actualParaId[6]=paraId7;
									allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
								    
									////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
									AppointmentParameterVO appParaObj= new AppointmentParameterVO();
									appParaObj.setAppointmentForId(aptId);				
									appParaObj.setArrActualParaId(actualParaId);
									////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

									actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
									actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
									slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
									appParaObj.setPatCrno(crno);
									appParaObj.setSlotDuration(slotDuration);
									appParaObj.setAppointmentForDate(aptDate);
									if(actualParaRefId!=null && actualParaRefId!="")
									{
										appParaObj.setActualParameterReferenceId(actualParaRefId);
										
										ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
										//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
										////Map mpDatesforActualParaRefId=null;
										////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
											////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
										////appParaObj.setAppointmentCallStatus("BY_TAG");
										
										/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
										List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
										request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
										mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
										ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
										String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
										bookedDate =availableDateTime.replace("^","#").split("#")[0];
										BookedTime =availableDateTime.replace("^","#").split("#")[1];
										shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
										
									}					
									else
									{
										return "Appointment Not Configured";											
									}										
								}						
								
							
						  }
						  else
						  {
							  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
							  bookedDate=objAppointmentdtlVO.getAppointmentDate();
							  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////					  
						  }
					if(ws!=null && ws.size()>0)
					{
						while(ws.next() && !breakLoop)
						{
							opdSlotsAll=ws.getString(12);
							opdSlotsBooked=ws.getString(14);
							ipdSlotsAll=ws.getString(13);
							ipdSlotsBooked=ws.getString(15);
							
							if(tagView.equals("1"))//Ist Free Slot View
							{
								strResultTag+="<input type='hidden' name='allactualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='actualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
								strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
								strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
								strResultTag+="<input type='hidden' name='actualParaRefId_"+divrowId+"' value='"+actualParaRefId+"'>";
								strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
								//By Mukund for opd & ipd slots 
								strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";

								strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
								strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
								//System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));
								/***/
								if(apptType.equals("1") && Integer.parseInt(opdSlotsAll)!=0 ){//apptType -- 1 = OPD, 2 = IPD, 3 = EMG //ws.getString(12) = opdSlotsAll
									int availOpdSlots = Integer.parseInt(opdSlotsAll) - Integer.parseInt(opdSlotsBooked);
									if(availOpdSlots<=0){
										strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px' color='red'>No OPD Slots Available</font></div>";
										strResultTag+="</div></td></tr></table>";
										createFreeSlotFlag=true;
										breakLoop=true;
									}
								}else if(apptType.equals("2") && Integer.parseInt(ipdSlotsAll)!=0 ){
									int availIpdSlots = Integer.parseInt(ipdSlotsAll) - Integer.parseInt(ipdSlotsBooked);
									if(availIpdSlots<=0){
										strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px' color='red'>No IPD Slots Available</font></div>";
										strResultTag+="</div></td></tr></table>";
										createFreeSlotFlag=true;
										breakLoop=true;
									}
								}/***/
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
								{
									String cssClass=ws.getString(11);
									strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel_"+divrowId+"'><font size='2px'> "+ws.getString(1)+"&nbsp;"+ws.getString(8)+"&nbsp;"+ws.getString(11)+"</font></div>";
									strResultTag+="<input type='hidden' name='slotST_"+divrowId+"' value='"+ws.getString(8)+"'>";
									strResultTag+="<input type='hidden' name='slotET_"+divrowId+"' value='"+ws.getString(9)+"'>";
									strResultTag+="<input type='hidden' name='shiftId_"+divrowId+"' value='"+ws.getString(2)+"'>";
									strResultTag+="<input type='hidden' name='aptType_"+divrowId+"' value='"+ws.getString(10)+"'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openDivApptPopup(this,'"+divrowId+"')\">";
									strResultTag+="</div></td></tr></table>";							
									breakLoop=true;
								}						
							}
							else
							{
								runningNo++;
								//System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

								if(shiftcounter==0)
								{
									strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
									strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
									strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
									strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
									strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
									strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
									//By Mukund for opd & ipd slots 
									strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";

									strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
								else if(!oldShiftId.equals(ws.getString(2)))
								{
									++nextShift;
									strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
									
								oldShiftId=ws.getString(2);
								shiftcounter++;
												
								
								
								if(cnt%5==0)
								{
									rowcount++;						
									if(cnt!=0)
									{
										strResultTag+="</tr><tr>";
									}
									else
									{			
										strResultTag+="<tr>";
									}
								}								
								cnt++;
									
								
								//strResultTag+="<tr>";
								String cssClass="";
								String bcssClass="";
								String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
								if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
									cssClass="Hold";
									bcssClass="Hold";
								}
								else{
									cssClass=ws.getString(11);
									bcssClass=ws.getString(11);
								}
								slotUniqueId=ws.getString(2)+""+runningNo;
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								
									
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								
								if(ws.getString(10).equals("5"))//OverBooked Slot
									slotType="5";//Overbooked
									
								//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
								String onclick="BookDivSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"','"+rowWiseId+"')";
								
								
								/*if(shiftcounter<=4)
									strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
								else*/ if(ws.getString(10).equals("1"))//Free Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else if(ws.getString(10).equals("5"))//Overbooked Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else						
									strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								//strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
								//strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
								strResultTag+="<a>"+ws.getString(8)+"</a>";
								strResultTag+="</div></td>";
								/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
								strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
								strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
								strResultTag+="</div></td>";*/
								//strResultTag+="</tr>";					
							}
						}
						if(!tagView.equals("1"))//Whole Tag View
						{
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
							strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="</table>";
						
					   
							//VIP Slots logic Not Implemented Yet
							/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
							strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
							strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
							strResultTag+="</table>";*/
							
							strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
							//strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							//strResultTag+="<img class='button' src='/HIS/hisglobal/images/cancel.png' style='cursor: pointer;'  onclick='closeVipApp()'></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
							strResultTag+="</table>";
							strResultTag+="</div>";
						}
					
				}
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//WebUTIL.writeResponse(response, strResultTag, null);
				/*try
				{
					//PrintWriter writer=response.getWriter();
					//System.out.println("Size of buffer is"+response.getBufferSize());
					//System.out.println("Size of COntent is "+strResultTag.length());
					//response.setBufferSize(32*1024);
					//writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
					
					WebUTIL.writeResponse(response, "ID_PARAMETER_CODE"+"@"+strResultTag, "text/html");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}*/	
				return strResultTag;
			}
			
			public static void MakeAppointmentTag_withCapping(HttpServletRequest request,HttpServletResponse response, String crno, String aptId, String paraId1, String paraId2,
					String paraId3, String paraId4, String paraId5, String paraId6,	String paraId7,String tagId,String cssClassName,String tagView,String aptDate,String divrowId, String apptType) 
			{
				
				////AppointmentUsefullMethodsTransaction objAppointmentUsefullMethodsTransaction=new AppointmentUsefullMethodsTransaction();
				String strResultTag="",strResultTag2="";
				String allactualParaId="";
				Map mpAllAppointmentProcessSpecificDetails= new HashMap();
				Map mppreviousAppointmentProcessDtl= new HashMap();
				WebRowSet ws=null;
				int cnt=0,rowcount=0,shiftcounter=0,nextShift=1,runningNo=0;
				String oldShiftId="",slotUniqueId="", opdSlotsAll="", opdSlotsBooked="", ipdSlotsAll="", ipdSlotsBooked="";
				boolean breakLoop=false, createFreeSlotFlag=false;
				List<NewAppointmentVO> objNewAppointmentVO_arr=new ArrayList<>(); 
				List<String> slotTimes=new ArrayList<>(); 
				List<String> apptDates=new ArrayList<>(); 
				
				try
				{	
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String uniqueId=(String) sdf.format(cal.getTime());
					Map mpActualParaRefId=new HashMap();
					////mpActualParaRefId=(Map)servletContext.getAttribute(AppointmentConfig.CONTEXTMAPACTUALPARAREFID);	 
				
					if((List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST)!=null)
						objNewAppointmentVO_arr=(List<NewAppointmentVO>)WebUTIL.getSession(request).getAttribute(AppointmentConfig.APPOINTMENT_VO_LIST);
					
					for(NewAppointmentVO obj_newApt :objNewAppointmentVO_arr){
						slotTimes.add(obj_newApt.getSlotST());
						apptDates.add(obj_newApt.getAppointmentDate());
					}
				
					UserVO userVO= ControllerUTIL.getUserVO(request);
					String bookedDate=""; 
					String BookedTime="";
					String shiftId="";
					String[] actualParaId= new String[7];
					String actualParaRefIdSlotDuration=null;
					String actualParaRefId=null;
					String slotDuration=null;
					String rowWiseId=divrowId;
				
					////String init=(String)request.getSession().getAttribute("INITAPPOINTMENTTAG");
					////if(init==null)
						////return "";
					
					if(paraId1==null || paraId2==null)
					{
						strResultTag="";
					}
					if(paraId3==null)paraId3= "0";
					if(paraId4==null)paraId4= "0";
					if(paraId5==null)paraId5= "0";
					if(paraId6==null)paraId6= "0";
					if(paraId7==null)paraId7= "0";
				
						  String key="APTOBJ_TAG"+tagId;
						 //// Map mpNewAppointment=(Map)request.getSession().getAttribute(AppointmentConfig.APPOINMENT_MAP);
						  ////AppointmentdtlVO objAppointmentdtlVO= new AppointmentdtlVO();
						  boolean flagExist=false;
						  /*//if(mpNewAppointment!=null && mpNewAppointment.size()>0 )
						  {
							  if(mpNewAppointment.containsKey(key))
								  flagExist=true;
							  else
								  flagExist=false;	
						  }
						  else
						  {
							  flagExist=false;
							  ////mpNewAppointment= new HashMap();
						  }*///
						  
						  
						  if(flagExist==false  )
						  {
							  ////mpNewAppointment= new HashMap(); 
							  
								if(aptId!=null)
								{							
									actualParaId[0]=paraId1;
									actualParaId[1]=paraId2;
									actualParaId[2]=paraId3;
									actualParaId[3]=paraId4;
									actualParaId[4]=paraId5;
									actualParaId[5]=paraId6;
									actualParaId[6]=paraId7;
									allactualParaId=paraId1+"^"+ paraId2+"^"+paraId3+"^"+paraId4+"^"+paraId5+"^"+paraId6+"^"+paraId7;
								    
									////request.getSession().setAttribute(AppointmentConfig.ALLACTUALPARAID_FOR_NEW , allactualParaId);
									AppointmentParameterVO appParaObj= new AppointmentParameterVO();
									appParaObj.setAppointmentForId(aptId);				
									appParaObj.setArrActualParaId(actualParaId);
									////actualParaRefId=objAppointmentUsefullMethodsTransaction.getActualParaRefId(request, appParaObj);

									actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
									actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
									slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
									appParaObj.setPatCrno(crno);
									appParaObj.setSlotDuration(slotDuration);
									appParaObj.setAppointmentForDate(aptDate);
									if(actualParaRefId!=null && actualParaRefId!="")
									{
										appParaObj.setActualParameterReferenceId(actualParaRefId);
										
										ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);
										//objAppointmentUsefullMethodsTransaction.initializeReadOnlyParaDetail(request, appParaObj);
										////Map mpDatesforActualParaRefId=null;
										////if(mpActualParaRefId!=null && mpActualParaRefId.size()>0)
											////mpDatesforActualParaRefId=(Map)mpActualParaRefId.get(actualParaRefId);
										////appParaObj.setAppointmentCallStatus("BY_TAG");
										
										/*////mppreviousAppointmentProcessDtl=AppointmentProcessDATA.getpreviousAppointmentProcessDtl(appParaObj, userVO);
										List lstpreviousAppointmentProcessDtl=(List)mppreviousAppointmentProcessDtl.get(AppointmentConfig.LSTPREVIOUSAPPTDTL);
										request.getSession().removeAttribute(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);							
										mpAllAppointmentProcessSpecificDetails=AppointmentProcessDATA.getAllAppointmentProcessSpecificDetails(appParaObj, userVO,mpDatesforActualParaRefId,lstpreviousAppointmentProcessDtl);
										ParameterScheduleVO objschVO=  ( ParameterScheduleVO)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.SELECTEDOBJPARAMETERSCHEDULEVO);
										String availableDateTime =(String)mpAllAppointmentProcessSpecificDetails.get(AppointmentConfig.AVAILABLEDATETIME);
										bookedDate =availableDateTime.replace("^","#").split("#")[0];
										BookedTime =availableDateTime.replace("^","#").split("#")[1];
										shiftId=availableDateTime.replace("^","#").split("#")[2];*/////		
										
									}					
									else
									{
										strResultTag= "Appointment Not Configured";											
									}										
								}						
								
							
						  }
						  else
						  {
							  /*////objAppointmentdtlVO=(AppointmentdtlVO) mpNewAppointment.get(key);
							  bookedDate=objAppointmentdtlVO.getAppointmentDate();
							  BookedTime=objAppointmentdtlVO.getAppointmentStartTime();*/////					  
						  }
					if(ws!=null && ws.size()>0)
					{
						while(ws.next() && !breakLoop)
						{
							opdSlotsAll=ws.getString(12);
							opdSlotsBooked=ws.getString(14);
							ipdSlotsAll=ws.getString(13);
							ipdSlotsBooked=ws.getString(15);
							
							if(tagView.equals("1"))//Ist Free Slot View
							{
								strResultTag+="<input type='hidden' name='allactualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='actualParameterId_"+divrowId+"' value='"+allactualParaId+"'>";
								strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
								strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
								strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
								strResultTag+="<input type='hidden' name='actualParaRefId_"+divrowId+"' value='"+actualParaRefId+"'>";
								strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
								strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div>";
								
								//By Mukund for opd & ipd slots 
								strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";
								
								strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='FREETAGVIEW'>";	
								//System.out.println("-TagView 1--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));
								/***/
								if(apptType.equals("1") && Integer.parseInt(opdSlotsAll)!=0 ){//apptType -- 1 = OPD, 2 = IPD, 3 = EMG //ws.getString(12) = opdSlotsAll
									int availOpdSlots = Integer.parseInt(opdSlotsAll) - Integer.parseInt(opdSlotsBooked);
									if(availOpdSlots<=0){
										strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px' color='red'>No OPD Slots Available</font></div>";
										strResultTag+="</div></td></tr></table>";
										createFreeSlotFlag=true;
										breakLoop=true;
									}
								}else if(apptType.equals("2") && Integer.parseInt(ipdSlotsAll)!=0 ){
									int availIpdSlots = Integer.parseInt(ipdSlotsAll) - Integer.parseInt(ipdSlotsBooked);
									if(availIpdSlots<=0){
										strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel'><font size='2px' color='red'>No IPD Slots Available</font></div>";
										strResultTag+="</div></td></tr></table>";
										createFreeSlotFlag=true;
										breakLoop=true;
									}
								}/***/
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								if(ws.getString(10).equals("1") || ws.getString(10).equals("5"))//Either Free Slot Or Overbooked Slot
								{
									String cssClass=ws.getString(11);
									strResultTag+="<tr><td width='5%'><div align='left'><div id='freeSlotLabel_"+divrowId+"'><font size='2px'> "+ws.getString(1)+"&nbsp;"+ws.getString(8)+"&nbsp;"+ws.getString(11)+"</font></div>";
									strResultTag+="<input type='hidden' name='slotST_"+divrowId+"' value='"+ws.getString(8)+"'>";
									strResultTag+="<input type='hidden' name='slotET_"+divrowId+"' value='"+ws.getString(9)+"'>";
									strResultTag+="<input type='hidden' name='shiftId_"+divrowId+"' value='"+ws.getString(2)+"'>";
									strResultTag+="<input type='hidden' name='aptType_"+divrowId+"' value='"+ws.getString(10)+"'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/A.png' ID='SCHEDULEAPP'	style='cursor: pointer;'  onclick=\"openDivApptPopup(this,'"+divrowId+"')\">";
									strResultTag+="</div></td></tr></table>";							
									breakLoop=true;
								}						
							}
							else
							{
								runningNo++;
								//System.out.println("-TagView 2--Shift Id-"+ws.getString(2)+"--"+ws.getString(8)+"--"+ws.getString(9));

								if(shiftcounter==0)
								{
									strResultTag+="<input type='hidden' name='actualParameterId' value='"+allactualParaId+"'>";
									strResultTag+="<input type='hidden' name='shiftST_"+divrowId+"' value='"+ws.getString(4)+"'>";
									strResultTag+="<input type='hidden' name='shiftET_"+divrowId+"' value='"+ws.getString(5)+"'>";
									strResultTag+="<input type='hidden' name='aptFor_"+divrowId+"' value='"+aptId+"'>";
									strResultTag+="<input type='hidden' name='actualParaRefId' value='"+actualParaRefId+"'>";
									strResultTag+="<input type='hidden' name='aptForDate_"+divrowId+"' value='"+ws.getString(1)+"'>";
									strResultTag+="<div id='aptDate' style='display:none'>"+ws.getString(1)+"</div><table width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_1'>";	
									
									//By Mukund for opd & ipd slots 
									strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";
									
									
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1', '"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table id='TABLESHIFT_SLOT_1' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
								else if(!oldShiftId.equals(ws.getString(2)))
								{
									++nextShift;
									strResultTag+="<table style='display:none' width='100%' cellpadding='0'  cellspacing='1' align='center' id='TABLESHIFT_"+(nextShift)+"'>";
									strResultTag+="<tr><td  class='header'  width='5%'><div align='left'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-left.png' ID='IMGBACK_1'	style='cursor: pointer;'  onclick=\"DisplayDifftentShift('-1','"+nextShift+"')\">";
									strResultTag+="</div></td>";
									strResultTag+="<td  class='header'  width='90%'><div align='center'>&nbsp;"+ws.getString(3)+"&nbsp; ("+ws.getString(4)+"-"+ws.getString(5)+")</b></font></div></td>";
									strResultTag+="<td  class='header'  width='5%'><div align='right'>";
									strResultTag+="<img class='button' src='/HIS/hisglobal/images/arrsingle-right.png'  ID='IMGFORWARD_1'	style='cursor: pointer' onclick=\"DisplayDifftentShift('1', '"+nextShift+"')\">";
									strResultTag+="</div></td></tr></table>";
									strResultTag+="<table style='display:none' id='TABLESHIFT_SLOT_"+(nextShift)+"' width='100%' cellpadding='0' cellspacing='3' align='center'>";
								}
									
								oldShiftId=ws.getString(2);
								shiftcounter++;
												
								
								
								if(cnt%5==0)
								{
									rowcount++;						
									if(cnt!=0)
									{
										strResultTag+="</tr><tr>";
									}
									else
									{			
										strResultTag+="<tr>";
									}
								}								
								cnt++;
									
								
								//strResultTag+="<tr>";
								String cssClass="";
								String bcssClass="";
								String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
								if(slotTimes.contains(ws.getString(8)) && apptDates.contains(ws.getString(1)) ){
									cssClass="Hold";
									bcssClass="Hold";
								}
								else{
									cssClass=ws.getString(11);
									bcssClass=ws.getString(11);
								}
								slotUniqueId=ws.getString(2)+""+runningNo;
								//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
								
									
								String slotType="1";//Normal Slot---Emg & Urgent Slots are not made. They are given direct appointment
								
								if(ws.getString(10).equals("5"))//OverBooked Slot
									slotType="5";//Overbooked
									
								//String onclick="BookSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+slotType+"','"+slotUniqueId+"')";					
								String onclick="BookDivSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"','"+rowWiseId+"')";
								
								
								/*if(shiftcounter<=4)
									strResultTag+="<td title='"+title+"' width='20%'><div class='Booked' align='center' id='"+slotUniqueId+"'>";
								else*/ if(ws.getString(10).equals("1"))//Free Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else if(ws.getString(10).equals("5"))//Overbooked Slot
									strResultTag+="<td title='"+title+"' onclick="+onclick+" width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								else						
									strResultTag+="<td title='"+title+"' width='20%'><div class='"+cssClass+"' align='center' id='"+slotUniqueId+"'>";
								//strResultTag+="<b class='rtopOnHold'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
								//strResultTag+="<a>"+ws.getString(8)+"</a><b class='rbottomOnHold'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
								strResultTag+="<a>"+ws.getString(8)+"</a>";
								strResultTag+="</div></td>";
								/*strResultTag+="<td class='slotclass'  title='08:30 - 08:45 Time slot on hold' onclick=BookSlot('08:45','09:00','1','0')><div class='containerAppFreeOdd' align='center'>";
								strResultTag+="<b class='rtopAppFreeOdd'><b class='r1'></b> <b class='r2'></b> <b class='r3'></b> <b class='r4'></b></b>";
								strResultTag+="<a>08:30</a><b class='rbottomAppFreeOdd'><b class='r4'></b> <b class='r3'></b> <b class='r2'></b> <b class='r1'></b></b>";
								strResultTag+="</div></td>";*/
								//strResultTag+="</tr>";					
							}
						}
						if(!tagView.equals("1"))//Whole Tag View
						{
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='COLORHELP'>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'>Color Chart</div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div class='Free' align='center'>Free Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Booked' align='center'>Booked Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Elapsed' align='center'>Elapsed Slot</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div class='Hold' align='center'>On Hold Slot</div></td></tr>";
							strResultTag+="<td width='25%' colspan='1'><div class='OverBooked' align='center'>OverBooked Slot</div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='5' class='header'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="</table>";
						
					   
							//VIP Slots logic Not Implemented Yet
							/*strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center' id='VIPURGENT'>";
							strResultTag+="<tr><td width='90%' colspan='4' class='header'><div align='left'>Add VIP/Urgent Appointment</div></td>";
							strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/avai/plus.png' id='ADDVIPURGENT'	style='cursor: pointer;'  onclick='addVipApp()'></div></td></tr>";			
							strResultTag+="</table>";*/
							
							strResultTag+="<div style='display:none' id='ADDVIPURGENTDIV' class='divBack'>";
							strResultTag+="<table width='100%' cellpadding='0'  cellspacing='1' align='center'>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='left'>VIP/Urgent Appointment</div></td>";
							//strResultTag+="<td width='10%' colspan='1'><div align='right'>";
							//strResultTag+="<img class='button' src='/HIS/hisglobal/images/cancel.png' style='cursor: pointer;'  onclick='closeVipApp()'></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='25%' colspan='1'><div align='right'>Slot Time(HH24:MI)</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><input type='text' name='vipUrgentSlotTime' id='vipUrgentSlotTimeId' maxlength='5' onblur='IsValidTime(this.value);return getTimeFormat(this);' onkeypress='givecolon(event,this);return validateNumeric(event);' size='7'></div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='right'>Slot Type</div></td>";
							strResultTag+="<td width='25%' colspan='1'><div align='left'><select name='slotType'><option value='3'>VIP</option><option value='5'>Urgent</option></select></div></td>";
							strResultTag+="</tr>";
							strResultTag+="<tr><td width='100%' colspan='4'><div align='left'><hr color='#115887'></div></td></tr>";
							strResultTag+="<tr><td width='100%' colspan='4' class='header'><div align='center'>";
							strResultTag+="<img class='button' src='/HIS/hisglobal/images/buttons/save.png' style='cursor: pointer;'  onclick='saveVipApp()'></div></td></tr>";			
							strResultTag+="</table>";
							strResultTag+="</div>";
						}
					
				}
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//WebUTIL.writeResponse(response, strResultTag, null);
				try
				{
					//PrintWriter writer=response.getWriter();
					//System.out.println("Size of buffer is"+response.getBufferSize());
					//System.out.println("Size of COntent is "+strResultTag.length());
					//response.setBufferSize(32*1024);
					//writer.write( "ID_PARAMETER_CODE"+"@"+strResultTag);
					
					WebUTIL.writeResponse(response, "ID_PARAMETER_CODE"+"@"+strResultTag, "text/html");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//return strResultTag;
			}
}//end of class