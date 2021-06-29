/*
  * Copyright ©.
 */
package appointment.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import vo.appointment.AppointmentParameterVO;
import vo.appointment.AppointmentSlotDtlVO;
import vo.appointment.AppointmentVO;

import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import appointment.config.AppointmentConfig;

public class AppointmentEssentialDAO extends DataAccessObject
{

	Logger log;
	
	public AppointmentEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
	

	public List <Entry> getGenderList() 
	{
		List <Entry> alRecord = new ArrayList<Entry>();
		ResultSet rs = null;
		String query = "";
		
			
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "GETGENDER";
		alRecord.add(new Entry("Select Value", "-1"));
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		

		System.out.println("query" + query);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
			Map mpParentId= new HashMap();
			
			while(rs.next()){
				alRecord.add(new Entry(rs.getString(2),rs.getString(1)));
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return alRecord;
	}
	
	public List<AppointmentVO> getAppointmentForList(UserVO _userVO,String tagView,String aptId) {
		List <AppointmentVO> alRecord = new ArrayList<AppointmentVO>();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "APPOINTMENTLIST.TRANSACTION";
		if(tagView.equals("MASTER"))
			queryKey = "APPOINTMENTLIST.MASTER";
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		if(!tagView.equals("MASTER"))
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while(rs.next()){
				AppointmentVO objAppointmentVO= new AppointmentVO();
				objAppointmentVO.setAppointmentForId(rs.getInt(1)+ "");
				objAppointmentVO.setAppointmentName(rs.getString(2));
				objAppointmentVO.setIsTimingByAppointment(rs.getInt(3)+"");
				objAppointmentVO.setMultiAppointmentStatus(rs.getInt(4)+"");
				if(aptId!=null){
					if(objAppointmentVO.getAppointmentForId().equals(aptId))
						alRecord.add(objAppointmentVO);
				}
				else
					alRecord.add(objAppointmentVO);
				
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return alRecord;
	}


	 @SuppressWarnings("unchecked")
	public List <AppointmentParameterVO> getAppointmentParameterDtl(UserVO userVO,String appointmentForId,String tagView) 
	 {
		List <AppointmentParameterVO> alRecord = new ArrayList<AppointmentParameterVO>();
		ResultSet rs = null;
		String query = "";
		@SuppressWarnings("rawtypes")
		Map populateMAP = new HashMap();
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "APPOINTMENT.PARAMETER.DTL";
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), appointmentForId);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			@SuppressWarnings("rawtypes")
			Map mpParentId= new HashMap();
			
			while(rs.next())
			{
				AppointmentParameterVO objAppointmentParameterVO= new AppointmentParameterVO();
				
				objAppointmentParameterVO.setParameterId(rs.getInt(1) + "");
				objAppointmentParameterVO.setAppointmentForId(rs.getInt(2) + "");
				objAppointmentParameterVO.setParameterName(rs.getString(3) + "");
				objAppointmentParameterVO.setMasterQuery(rs.getString(4));
				objAppointmentParameterVO.setParentParameterId(rs.getInt(5)+"" );
				objAppointmentParameterVO.setIsParameterForSlotMake(rs.getInt(6)+"" );
				objAppointmentParameterVO.setReadOnlyTransactionQuery(rs.getString(7));
				objAppointmentParameterVO.setDisplayOrder(rs.getInt(8)+"" );
				objAppointmentParameterVO.setTransactionQuery(rs.getString(9));
				if(objAppointmentParameterVO.getParentParameterId().equals("0") || mpParentId.containsKey(objAppointmentParameterVO.getParentParameterId())){
					String parentParameterActualValue="0";
					if(mpParentId.containsKey(objAppointmentParameterVO.getParentParameterId()))
						parentParameterActualValue=(String)mpParentId.get(objAppointmentParameterVO.getParentParameterId());
						
					@SuppressWarnings("rawtypes")
					Map mp=getAppointmentParameterValueDtl(userVO, objAppointmentParameterVO, tagView,  parentParameterActualValue);
					objAppointmentParameterVO=(AppointmentParameterVO) mp.get(AppointmentConfig.OBJAPPOINTMENTPARAMETERVO);
					if(mp.containsKey(AppointmentConfig.ACTUALPARAMETERID))
						mpParentId.put(objAppointmentParameterVO.getParameterId(), mp.get(AppointmentConfig.ACTUALPARAMETERID));					
				}
				alRecord.add(objAppointmentParameterVO);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return alRecord;
	}
	
	public Map  getAppointmentParameterValueDtl(UserVO userVO,AppointmentParameterVO objAppointmentParameterVO,String tagView, String parentParameterActualValue) {
		Map mp= new HashMap();
		
		String optionHTML="<option value='-1'>Select Value</option>";
		String actualParameterId=null;
		ResultSet rs = null;
		String query = "";
		
		if(tagView.equals("MASTER"))
			query=objAppointmentParameterVO.getMasterQuery();
		else
			query=objAppointmentParameterVO.getTransactionQuery();
			
		System.out.println("query" + query);
		query=query.replaceAll(AppointmentConfig.HOSPITAL_CODE , userVO.getHospitalCode());
		query=query.replaceAll(AppointmentConfig.USER_ID, userVO.getUserSeatId());
		query=query.replaceAll(AppointmentConfig.APT_ID, objAppointmentParameterVO.getAppointmentForId());
		query=query.replaceAll("#"+objAppointmentParameterVO.getParentParameterId() +"#",parentParameterActualValue);
		/*Modify Date				: 27th Nov'14
		  Reason	(CR/PRS)		: Bug Id 7594
		  Modify By                 : Sheeldarshi */
		query=query.replaceAll(AppointmentConfig.ACTUALPARAMETER,parentParameterActualValue);
		//End:Sheeldarshi
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
			
			while(rs.next()){
				optionHTML+="<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>";
			}
			
			objAppointmentParameterVO.setOptionHTML(optionHTML);
			mp.put(AppointmentConfig.OBJAPPOINTMENTPARAMETERVO, objAppointmentParameterVO);	
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return mp;
	}


	public String getActualParaRefernceId(UserVO userVO,String appointmentForId, String allActualParameterId) 
	{
		String actualParameterRefernceId="";
		String slotDuration="";
		ResultSet rs = null;
		String query = "";
		
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "GETAPPOINTMENTPARAREFID";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		
		Map populateMAP = new HashMap();
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), appointmentForId);
		if(allActualParameterId!=null && !allActualParameterId.equals(""))
		{
			if(allActualParameterId.contains("#"))
				allActualParameterId=allActualParameterId.replace("#","$$");
			String []arrparaIds=allActualParameterId.replace("^","#").split("#");
			int i=0;
			for(;i<arrparaIds.length;i++)
			{
				if(arrparaIds[i].contains("$$"))
					arrparaIds[i]=arrparaIds[i].replace("$$","#");
				populateMAP.put(sq.next(), arrparaIds[i]);
			}
			for(i=arrparaIds.length;i<7;i++)
				populateMAP.put(sq.next(),"0");
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				actualParameterRefernceId = rs.getInt(1)+ "";
				slotDuration = rs.getInt(2)+ "";				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return actualParameterRefernceId+"^"+slotDuration;
	}

	public String  getAppointmentTypeList(String actualParameterReferenceId,UserVO userVO) {
		
		String optionHTML="<option value='-1'>Select Value</option>";
		ResultSet rs = null;
		String query = "";
		
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "GETPARAWISEAPPOINTMENTTYPES";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		

		Map populateMAP = new HashMap();
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		
		if(actualParameterReferenceId!=null && actualParameterReferenceId.contains("^"))
		{
					String []actualParameterReferenceIdWithSlot=actualParameterReferenceId.replace("^","#").split("#");
					actualParameterReferenceId=actualParameterReferenceIdWithSlot[0].length()>0?actualParameterReferenceIdWithSlot[0]:"";
					
		}
		populateMAP.put(sq.next(), actualParameterReferenceId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);			
			while(rs.next()){
				String selected="";
				if(rs.getInt(4)==1)
					selected="selected";
				optionHTML+="<option value='"+rs.getInt(1)+"' "+selected+" >"+rs.getString(2)+"</option>";
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return optionHTML;
	}

	public Map<String, NewAppointmentVO> getAppointmentList(UserVO userVO,NewAppointmentVO objNewAppointmentVO) {
		Map <String, NewAppointmentVO>mp= new HashMap<String, NewAppointmentVO>();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "GETAPPOINTMENTLIST";
		
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		

		System.out.println("query" + query);
		
		Sequence sq = new Sequence();		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), objNewAppointmentVO.getAppointmentForId());
		if(objNewAppointmentVO.getAllActualParameterId() !=null && !objNewAppointmentVO.getAllActualParameterId().equals("")){
			if(objNewAppointmentVO.getAllActualParameterId().contains("#"))
				objNewAppointmentVO.setAllActualParameterId(objNewAppointmentVO.getAllActualParameterId().replace("#","$$"));
			String []arrparaIds=objNewAppointmentVO.getAllActualParameterId().replace("^","#").split("#");
			int i=0;
			for(;i<arrparaIds.length;i++){
				if(arrparaIds[i].contains("$$"))
					arrparaIds[i]=arrparaIds[i].replace("$$","#");
				populateMAP.put(sq.next(), arrparaIds[i]);
			}
			for(i=arrparaIds.length;i<7;i++)
				populateMAP.put(sq.next(),"0");
		}
		populateMAP.put(sq.next(), objNewAppointmentVO.getAppointmentDate());

		String condition="";  
		if(objNewAppointmentVO.getListingMode()!=null){
			if(objNewAppointmentVO.getListingMode().equals("UNREG"))
				condition=  " and hrgnum_puk is null";
			if(objNewAppointmentVO.getListingMode().equals("REG"))
				condition=  " and hrgnum_puk is not null";
		}
		query =query.replace("#LISTINGMODE#", condition);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);
		
			while(rs.next()){
				NewAppointmentVO obj= new NewAppointmentVO();
				obj.setAppointmentNo(rs.getLong(1)+"");
				obj.setPatCrNo(rs.getLong(2)+"");
				obj.setEpisodeCode(rs.getInt(3)+"");
				obj.setPatFirstName(rs.getString(4));
				obj.setPatMiddleName(rs.getString(5));
				obj.setPatLastName(rs.getString(6));
				obj.setPatGuardianName(rs.getString(7));
				obj.setPatGenderCode(rs.getString(8));
				obj.setEmailId(rs.getString(9));
				obj.setMobileNo(rs.getLong(10)+"");
				obj.setAppointmentQueueNo(rs.getInt(11)+"");
				obj.setAppointmentTime(rs.getString(12));
				obj.setAppointmentStatus(rs.getInt(13)+"");
				obj.setStatusRemarks(rs.getString(14));
				obj.setSlotType(rs.getInt(15)+"");
				obj.setRemarks(rs.getString(16));
				obj.setAppointmentTypeId(rs.getInt(17)+"");
				obj.setModuleSpecificCode(rs.getString(18));
				obj.setAppointmentMode(rs.getInt(19)+"");
				obj.setModuleSpecificKeyName(rs.getString(20));
				obj.setPatAge(rs.getString(21));
				obj.setPatHusbandName(rs.getString(22));
				mp.put(obj.getAppointmentNo(), obj);				
				
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return mp;
	}

	public Map<String, RescheduleCancelAppointmentVO> getPreviousAppointmentList(																																																																																																												
			UserVO userVO,			RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO) {
		Map <String, RescheduleCancelAppointmentVO>mp= new HashMap<String, RescheduleCancelAppointmentVO>();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = AppointmentConfig.QUERY_FILE_FOR_TRANSACTIONDAO;
		String queryKey = "GETPREVIOUSAPPOINTMENTLIST";
		
		System.out.println("AppointMentEssential_DAO :: getPreviousAppointmentList");
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		
		System.out.println("query" + query);
		
		Sequence sq = new Sequence();	
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), objRescheduleCancelAppointmentVO.getPatCrNo());
		System.out.println("Appointment_dao");
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);
			//SELECT hapnum_apt_no, hrgnum_puk,hrgnum_episode_code, hapstr_pat_first_name, hapstr_pat_middle_name, hapstr_pat_last_name,hapstr_patient_fname, gstr_gender_code, hapstr_email, hapstr_mobile_no,habnum_appt_queue_no, hapstr_slot_time, hapnum_apt_status,hapstr_statusremarks, hapnum_slot_type,hapstr_remarks, hapnum_appt_type_id, hapstr_module_specific_code, hapnum_appt_mode, hapstr_mod_specific_key_name,hapstr_patage, hapstr_spousename,hapdt_appointment_date FROM hapt_appointment_dtl where gnum_isvalid=1 and gnum_hospital_code=101 and hapnum_apt_id=1 and  hapstr_actual_para_id_1=103 and hapstr_actual_para_id_2=10311 and hapstr_actual_para_id_3=0 and hapstr_actual_para_id_4=0 and hapstr_actual_para_id_5=0 and hapstr_actual_para_id_6=0 and hapstr_actual_para_id_7=0 and hapdt_appointment_date=28-Feb-2014::timestamp and hapnum_apt_status not in  (0,3)
			while(rs.next()){
				RescheduleCancelAppointmentVO obj= new RescheduleCancelAppointmentVO();
				obj.setAppointmentNo(rs.getLong(1)+"");
				obj.setPatCrNo(rs.getLong(2)+"");
				obj.setEpisodeCode(rs.getInt(3)+"");
				obj.setPatFirstName(rs.getString(4));
				obj.setPatMiddleName(rs.getString(5));
				obj.setPatLastName(rs.getString(6));
				obj.setPatGuardianName(rs.getString(7));
				obj.setPatGenderCode(rs.getString(8));
				obj.setEmailId(rs.getString(9));
				obj.setMobileNo(rs.getLong(10)+"");
				obj.setAppointmentQueueNo(rs.getInt(11)+"");
				obj.setAppointmentTime(rs.getString(12));
				obj.setAppointmentStatus(rs.getInt(13)+"");
				obj.setStatusRemarks(rs.getString(14));
				obj.setSlotType(rs.getInt(15)+"");
				obj.setRemarks(rs.getString(16));
				obj.setAppointmentTypeId(rs.getInt(17)+"");
				obj.setModuleSpecificCode(rs.getString(18));
				obj.setAppointmentMode(rs.getInt(19)+"");
				obj.setModuleSpecificKeyName(rs.getString(20));
				obj.setPatAge(rs.getString(21));
				obj.setPatHusbandName(rs.getString(22));
				obj.setAppointmentDate(rs.getString(23));
				obj.setAllActualParameterId(rs.getString(26)+"^"+rs.getString(27)+"^"+rs.getString(28)+"^"+rs.getString(29)+"^"+rs.getString(30)+"^"+rs.getString(31)+"^"+rs.getString(32));

				mp.put(obj.getAppointmentNo(), obj);				
				
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return mp;

	}

	public static WebRowSet makeSlotsData(UserVO _userVO,	AppointmentParameterVO appParaVO)
	{		
		WebRowSet ws=null;	   	
	   	HisDAO daoObj = null;
		String strErr="";
		int i=0;
		String strProcName="";
		int nProcIndex=0;
		
		try
		{			
			daoObj=new HisDAO("Appointment", "AppointmentEssentialDAO");
			strProcName = "{call pkg_appointment_transaction.proc_get_slot_schedule_dtl(?,?,?,?,? ,?,?,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "aptfor", appParaVO.getAppointmentForId(), 2);
			daoObj.setProcInValue(nProcIndex, "actutalrefid", appParaVO.getActualParameterReferenceId(), 3);
			if(appParaVO.getAppointmentForDate()!=null&&appParaVO.getAppointmentForDate()!="")
				daoObj.setProcInValue(nProcIndex, "aptdate", appParaVO.getAppointmentForDate(), 4);
			else
				daoObj.setProcInValue(nProcIndex, "aptdate", "0", 4);
			daoObj.setProcInValue(nProcIndex, "slotduration", appParaVO.getSlotDuration()+" minutes", 5);
			daoObj.setProcInValue(nProcIndex, "shiftid", "0", 6);
			daoObj.setProcInValue(nProcIndex, "hospcode", _userVO.getHospitalCode(), 7);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 9);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr=daoObj.getString(nProcIndex, "err");
			
			System.out.println("::Slot Data Parameters::");
			System.out.println("::aptfor::"+appParaVO.getAppointmentForId());
			System.out.println("::actutalrefid::"+appParaVO.getActualParameterReferenceId());
			System.out.println("::aptdate::"+appParaVO.getAppointmentForDate());
			System.out.println("::slotduration::"+appParaVO.getSlotDuration()+" minutes");
			System.out.println("::hospcode::"+_userVO.getHospitalCode());

			
			if(strErr.equals(""))
			{
				ws=daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("Size of WS"+ws.size());
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return ws;
		
	} 
	
	public static String checkSlotAvailibilty(UserVO _userVO,	AppointmentSlotDtlVO appParaVO)
	{		
		System.out.println("In side checkSlotAvailibilty");
		HisDAO daoObj = null;
		String strProcName="";
		int nProcIndex=0;
		String slotAvlFlag="1";
		
		try
		{			
			daoObj=new HisDAO("Appointment", "AppointmentEssentialDAO");
			strProcName = "{? = call PKG_APPOINTMENT_MASTERS.GETSLOTSTATUS(?,?,?,?,? ,?,?,?,?,?)}";
			 
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "1");
			daoObj.setFuncInValue(nProcIndex, 3, appParaVO.getAppointmentForId());
			daoObj.setFuncInValue(nProcIndex, 4, appParaVO.getActualParaRefId());
			daoObj.setFuncInValue(nProcIndex, 5, appParaVO.getAppointmentDate());
			daoObj.setFuncInValue(nProcIndex, 6, appParaVO.getSlotStartTime());
			daoObj.setFuncInValue(nProcIndex, 7, appParaVO.getSlotEndTime());
			daoObj.setFuncInValue(nProcIndex, 8, "0");
			daoObj.setFuncInValue(nProcIndex, 9, _userVO.getHospitalCode());
			daoObj.setFuncInValue(nProcIndex, 10, appParaVO.getShiftEndTime());
			daoObj.setFuncInValue(nProcIndex, 11, appParaVO.getShiftEndTime());
			
			
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			slotAvlFlag=daoObj.getFuncString(nProcIndex);					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return slotAvlFlag;		
	}
	
	//To get the Shift Details for the given Date,Added by Singaravelan on 19-Jan-2015
	public static WebRowSet getShiftDetailsForApt(UserVO _userVO,	Map<String,String> aptDetails)
	{		
		WebRowSet rs=null;	   	
	   	HisDAO daoObj = null;
		String strErr="";
		int i=0;
		String strProcName="";
		int nProcIndex=0;
		Map <String,Map<String, List<String>>> newMapApt=new HashMap<>(); 
		
		try
		{			
			daoObj=new HisDAO("Appointment", "AppointmentEssentialDAO");
			strProcName = "{call pkg_appointment_transaction.proc_get_shift_schedule_dtl_for_apt(?,?,?,?,?, ?,?,?,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hopdt_start_dt", aptDetails.get("aptStartDate"), 2);
			daoObj.setProcInValue(nProcIndex, "hopdt_end_dt", aptDetails.get("aptEndDate"), 3);
			daoObj.setProcInValue(nProcIndex, "gnum_dept_code", aptDetails.get("aptDeptCode"), 4);
			daoObj.setProcInValue(nProcIndex, "hgstr_unitname", aptDetails.get("aptDocName"), 5);
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", _userVO.getHospitalCode(), 6);
			daoObj.setProcInValue(nProcIndex, "gnum_seat_id", _userVO.getSeatId(), 7);
			daoObj.setProcInValue(nProcIndex, "gnum_isvalid", Config.IS_VALID_ACTIVE, 8);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 10);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr=daoObj.getString(nProcIndex, "err");
			
			if(strErr.equals("")){
				
				rs=daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("Size of WS"+rs.size());
			}		
			  	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return rs;
		
	} 
	
	@SuppressWarnings("deprecation")
	public String  getDayWiseAptCountDetail(AptDskCalendarVO _objAptDskCalendarVO,UserVO _userVO) {
		
		String eventHTML="";
		WebRowSet rs=null;	   	
	   	HisDAO daoObj = null;
	   	String strProcName="",strErr="";
		int nProcIndex=0,i=1,j=1;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String startDate,endDate="",desc="";
		
		
		try
		{	
			JSONArray ja = new JSONArray();
			JSONObject mainObj = new JSONObject();

			daoObj=new HisDAO("Appointment", "AppointmentEssentialDAO");
			strProcName = "{call pkg_appointment_transaction.proc_get_day_wise_free_booked_slots_dtl(?,?,?,?,?, ?,?,?,?,? ,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "startDate", _objAptDskCalendarVO.getStartDate(), 2);
			daoObj.setProcInValue(nProcIndex, "endDate", _objAptDskCalendarVO.getEndDate(), 3);
			daoObj.setProcInValue(nProcIndex, "paraRefId", _objAptDskCalendarVO.getParaRefId(), 4);
			daoObj.setProcInValue(nProcIndex, "paraId", _objAptDskCalendarVO.getParaId(), 5);

			daoObj.setProcInValue(nProcIndex, "aptId", _objAptDskCalendarVO.getAptId(), 6);
			daoObj.setProcInValue(nProcIndex, "slotDuration", _objAptDskCalendarVO.getSlotDuration(), 7);
			daoObj.setProcInValue(nProcIndex, "hospCode", _userVO.getHospitalCode(), 8);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId(), 9);
			daoObj.setProcInValue(nProcIndex, "isValid", Config.IS_VALID_ACTIVE, 10);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 11);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 12);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr=daoObj.getString(nProcIndex, "err");
			
					
			if(strErr.equals("")){
				
				rs=daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("Size of WS"+rs.size());
							
				while(rs.next()){
					startDate=endDate=sdf.format(new Date(rs.getString(1)));
					
					JSONObject jo = new JSONObject();

					jo.put("id", ""+i+"");
					jo.put("title", "Booked - "+rs.getString(4));
					jo.put("start", ""+startDate+"");
					jo.put("end", ""+endDate+"");
					//jo.put("allDay", false);
					jo.put("color", "#F6A77B");  
					jo.put("textColor", "#990033"); 
					jo.put("description", "Booked - "+rs.getString(4)); 

					ja.put(jo);
					i++;

					JSONObject jb = new JSONObject();

					jb.put("id", ""+i+"");
					jb.put("title", "Free - "+rs.getString(3));
					jb.put("start", ""+startDate+"");
					jb.put("end", ""+endDate+"");
					//jb.put("allDay", false);
					jb.put("color", "#C2E5C1");
					jb.put("textColor", "#990033"); 
					jb.put("description", "Free - "+rs.getString(3)); 

					ja.put(jb);
					i++;
				}

				mainObj.put("events", ja);
				eventHTML+=ja;
			}		
			  	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		
		return eventHTML;
	}
	
	
	@SuppressWarnings("deprecation")
	public String  getDayWiseAptDetail(AptDskCalendarVO _objAptDskCalendarVO,UserVO _userVO) {
		
		String eventHTML="";
		WebRowSet rs=null;	   	
	   	HisDAO daoObj = null;
	   	String strProcName="",strErr="";
		int nProcIndex=0,i=1,j=1;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String startDate,endDate="",desc="";
		
		
		try
		{	
			JSONArray ja = new JSONArray();
			JSONObject mainObj = new JSONObject();

			daoObj=new HisDAO("Appointment", "AppointmentEssentialDAO");
			strProcName = "{call pkg_appointment_transaction.proc_get_day_wise_apt_slots_dtl(?,?,?,?,?, ?,?,?,?,? ,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);
			WebUTIL.setNullToEmpty(_objAptDskCalendarVO);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "startDate", _objAptDskCalendarVO.getStartDate(), 2);
			daoObj.setProcInValue(nProcIndex, "endDate", _objAptDskCalendarVO.getEndDate(), 3);
			daoObj.setProcInValue(nProcIndex, "paraId", _objAptDskCalendarVO.getParaId(), 4);
			daoObj.setProcInValue(nProcIndex, "aptId", _objAptDskCalendarVO.getAptId(), 5);
			daoObj.setProcInValue(nProcIndex, "slotDuration", _objAptDskCalendarVO.getSlotDuration(), 6);
			daoObj.setProcInValue(nProcIndex, "hospCode", _userVO.getHospitalCode(), 7);
			daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId(), 8);
			daoObj.setProcInValue(nProcIndex, "isValid", Config.IS_VALID_ACTIVE, 9);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 11);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr=daoObj.getString(nProcIndex, "err");
			
					
			if(strErr.equals("")){
				
				rs=daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("Size of WS"+rs.size());
							
				while(rs.next()){
					startDate=endDate=sdf.format(new Date(rs.getString(1)));
					desc=" AppointmentNo : "+rs.getString(2)+"</br>"+						
						 " PatientName : "+rs.getString(3)+" ("+rs.getString(4)+"/"+rs.getString(5)+")</br>"+
						 " ContactNo : "+rs.getString(8) +"</br>"+	
						 " Slot : "+rs.getString(6) +" - "+rs.getString(7);

					JSONObject jo = new JSONObject();

					//jo.put("id", ""+i+"");
					jo.put("id", rs.getString(2));
					jo.put("title", rs.getString(2));
					//jo.put("start", ""+startDate+"T"+rs.getString(4)+":00");
					//jo.put("end", ""+endDate+"T"+rs.getString(5)+":00");
					jo.put("start", ""+startDate+"T"+rs.getString(6));
					jo.put("end", ""+endDate+"T"+rs.getString(7));
					jo.put("allDay", false);
					if(rs.getString(11).equals("3"))
						jo.put("editable", false);
					else
						jo.put("editable", true);

					if(rs.getString(11).equals("1")||rs.getString(11).equals("2")){
						jo.put("color", "#F6A77B");  
						jo.put("textColor", "#990033");
					}
					if(rs.getString(11).equals("0")){
						jo.put("color", "#B0B0B0 ");  
						//jo.put("textColor", "#990033");
					}
					jo.put("description", desc); 
					ja.put(jo);
					i++;

					/*JSONObject jb = new JSONObject();

					jb.put("id", ""+i+"");
					jb.put("title", "Free Apt- "+rs.getString(3));
					jb.put("start", ""+startDate+"");
					jb.put("end", ""+endDate+"");
					//jb.put("allDay", false);
					jb.put("color", "#C2E5C1");
					jb.put("textColor", "#8B6969"); 
					ja.put(jb);
					i++;*/
				}

				mainObj.put("events", ja);
				eventHTML+=ja;
			}		
			  	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		
		return eventHTML;
	}
	
	
}//end class
