package appointment.bo;

//last updated on july 06-07-06>>>priya



import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;


import vo.appointment.AppointmentParameterVO;
import vo.appointment.AppointmentSlotDtlVO;
import vo.appointment.AppointmentVO;

import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.dao.AppointmentEssentialDAO;
import appointment.dao.AppointmentDAO;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;



/**
 * @author vinita1
 * 
 */
@SuppressWarnings("unused")
public class AppointmentEssentialBO {
	
	public Map getAppointmentParameterEssentials_AJAX(UserVO _userVO,String tagView,String aptId) 
	{
		Map essentialMap = new HashMap();
		List<AppointmentVO> lstAppointment= new ArrayList<AppointmentVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try 
		{
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			lstAppointment = objEssentialDAO.getAppointmentForList(_userVO,tagView,aptId);			
			essentialMap.put(AppointmentConfig.LSTAPPOINTMENT,lstAppointment);
			if(lstAppointment!=null && lstAppointment.size()==1)
			{
				AppointmentVO objAppointmentVO=(AppointmentVO) lstAppointment.get(0);
				List lstParameter=getAppointmentParameterDtl(tx, _userVO, objAppointmentVO.getAppointmentForId(),tagView);
				essentialMap.put(AppointmentConfig.LSTPARAMETER,lstParameter);
				
			}
		}
		catch (HisRecordNotFoundException e) 
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} 
		catch (HisApplicationExecutionException e) 
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e) 
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} 
		finally 
		{
			tx.close();
		}
		return essentialMap;
	}

	@SuppressWarnings("rawtypes")
	public List getAppointmentParameterDtl(JDBCTransactionContext tx,UserVO userVO,	String appointmentForId ,String tagView) 
	{
		List lst= new ArrayList();
		AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
		lst=objEssentialDAO.getAppointmentParameterDtl(userVO, appointmentForId, tagView);
		return lst;
	}
	public List getAppointmentIdWiseParameterDetail(UserVO userVO,	String appointmentForId,String tagView) 
	{
		@SuppressWarnings("rawtypes")
		List lst= new ArrayList();
		List<Entry> lstAppointment= new ArrayList<Entry>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try 
		{
			tx.begin();
			lst=getAppointmentParameterDtl(tx, userVO, appointmentForId ,tagView);
		}
		catch (HisRecordNotFoundException e) 
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} 
		catch (HisApplicationExecutionException e) 
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e) 
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} 
		finally 
		{
			tx.close();
		}
		return lst;
	}
	public Map  getAppointmentParameterValueDtl(UserVO userVO,AppointmentParameterVO objAppointmentParameterVO,String tagView, String parentParameterActualValue){
		Map mp= new HashMap();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			mp=objEssentialDAO.getAppointmentParameterValueDtl(userVO, objAppointmentParameterVO ,tagView,parentParameterActualValue);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return mp;
	}

	public String getActualParaRefernceId(UserVO userVO,String appointmentForId, String allActualParameterId) {
		String actualParaReferenceId=null;	
		JDBCTransactionContext tx = new JDBCTransactionContext();
			try {
				tx.begin();
				AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
				actualParaReferenceId=objEssentialDAO.getActualParaRefernceId(userVO, appointmentForId ,allActualParameterId);
				
			}
			catch (HisRecordNotFoundException e) {
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			} catch (HisApplicationExecutionException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException("Error, Contact System Administrator");
			}

			catch (HisDataAccessException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException("Error, Contact System Administrator");
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException("Error, Contact System Administrator");
			} finally {
				tx.close();
			}
			return actualParaReferenceId;
		}
	
	public List getGenderList(){
		List lst= new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			lst=objEssentialDAO.getGenderList();
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return lst;
	}

	public String getAppointmentTypeList(String actualParameterReferenceId,	UserVO userVO) {
		String strResult="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			strResult=objEssentialDAO.getAppointmentTypeList(actualParameterReferenceId,userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return strResult;
	}

	public NewAppointmentVO SaveNewAppointment(NewAppointmentVO objNewAppointmentVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentDAO objNewAppointmentDAO = new AppointmentDAO(tx);
			objNewAppointmentVO=objNewAppointmentDAO.saveNewAppointmentSlotWise(objNewAppointmentVO, userVO);
			System.out.println("saveNEW---AppointmentBO");
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return objNewAppointmentVO;
	}

	public Map<String,NewAppointmentVO> getRescheduleAppointmentList(UserVO userVO,NewAppointmentVO objNewAppointmentVO) {
		Map <String,NewAppointmentVO> mp= new HashMap<String,NewAppointmentVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			mp=objEssentialDAO.getAppointmentList(userVO,objNewAppointmentVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return mp;
		
	}

	public NewAppointmentVO ConfirmAppointment(	NewAppointmentVO objNewAppointmentVO, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentDAO objNewAppointmentDAO = new AppointmentDAO(tx);
			objNewAppointmentVO=objNewAppointmentDAO.ConfirmAppointment(objNewAppointmentVO, userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return objNewAppointmentVO;
	}
	
	
	
public RescheduleCancelAppointmentVO saveRescheduleCancelAppointment(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			System.out.println("APPOIntmentEsssBO");
			tx.begin();
			AppointmentDAO objRescheduleCancelAppointmentDAO = new AppointmentDAO(tx);
			objRescheduleCancelAppointmentVO=objRescheduleCancelAppointmentDAO.saveRescheduleCancelAppointment(objRescheduleCancelAppointmentVO, userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return objRescheduleCancelAppointmentVO;
	}

public RescheduleCancelAppointmentVO cancelAppointment(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO, UserVO userVO) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	System.out.println("CancelAppointment::BO");
	try {
		tx.begin();
		AppointmentDAO objRescheduleCancelAppointmentDAO = new AppointmentDAO(tx);
		objRescheduleCancelAppointmentVO=objRescheduleCancelAppointmentDAO.cancelAppointment(objRescheduleCancelAppointmentVO, userVO);
	}
	catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	} catch (HisApplicationExecutionException e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException("Error, Contact System Administrator");
	}

	catch (HisDataAccessException e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisDataAccessException("Error, Contact System Administrator");
	} catch (Exception e) {
		e.printStackTrace();
		tx.rollback();
		throw new HisApplicationExecutionException("Error, Contact System Administrator");
	} finally {
		tx.close();
	}
	return objRescheduleCancelAppointmentVO;
}

	

	
	

	public Map<String,RescheduleCancelAppointmentVO> getPreviousAppointmentList(   RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO,UserVO userVO) {
		Map <String,RescheduleCancelAppointmentVO> mp= new HashMap<String,RescheduleCancelAppointmentVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		System.out.println("AppointmentBO::getPreviousAppointmentList");
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			mp=objEssentialDAO.getPreviousAppointmentList(userVO,objRescheduleCancelAppointmentVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return mp;
	}
	public Map<String,NewAppointmentVO> getAppointmentList(UserVO userVO,NewAppointmentVO objNewAppointmentVO) {
		Map <String,NewAppointmentVO> mp= new HashMap<String,NewAppointmentVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			mp=objEssentialDAO.getAppointmentList(userVO,objNewAppointmentVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	
	public Map searchAppointmentNoWise(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap<>();
		try
		{
			tx.begin();
			PatientVO objPatientVO_p = null;
			AppointmentDAO objRescheduleCancelAppointmentDAO = new AppointmentDAO(tx);
//			objRescheduleCancelAppointmentVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
            System.out.println("Appointment Bo ::searchPatientByAppointmentNo");
			//objRescheduleCancelAppointmentVO_p = objRescheduleCancelAppointmentDAO.searchAppointmentNoWise(objRescheduleCancelAppointmentVO_p, objUserVO_p);
            mp = objRescheduleCancelAppointmentDAO.getAppointmentDtl(objUserVO_p, objRescheduleCancelAppointmentVO_p);

			String fname = "(Unknown)" + objRescheduleCancelAppointmentVO_p.getPatFirstName();

		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Eror, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Eror, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return mp;
	
	}
	
	@SuppressWarnings("static-access")
	public List getDepartmentList(UserVO _userVO){
		List lst= new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO_p=new HisDAO("Appointment", "AppointmnetBO");
		try {
			tx.begin();
			//RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);					
			//lst = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,"0","1","1");
			
			DepartmentDAO objEssentialDAO = new DepartmentDAO();	
			lst = objEssentialDAO.getDepartmentList(hisDAO_p, _userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return lst;
	}

	
	public WebRowSet makeSlotsData(UserVO objUserVO_p,AppointmentParameterVO appParaVO)
	{
		WebRowSet ws=null;
		try
		{
			ws=AppointmentEssentialDAO.makeSlotsData(objUserVO_p, appParaVO);			
		}		
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			
		}		
		return ws;
	}
	
	public String checkSlotAvailibilty(UserVO objUserVO_p,AppointmentSlotDtlVO appParaVO)
	{
		String ws="";
		try
		{
			ws=AppointmentEssentialDAO.checkSlotAvailibilty(objUserVO_p, appParaVO);			
		}		
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			
		}		
		return ws;
	}
	
	public WebRowSet getShiftDetailsForApt(UserVO objUserVO_p,Map<String,String> aptDetails)
	{
		WebRowSet rowSet=null;
		try
		{
			rowSet=AppointmentEssentialDAO.getShiftDetailsForApt(objUserVO_p, aptDetails);			
		}		
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			
		}		
		return rowSet;
	}
	
	public RescheduleCancelAppointmentVO cancelShift(RescheduleCancelAppointmentVO objRescheduleCancelAppointmentVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		System.out.println("cancelShift::BO");
		try {
			tx.begin();
			AppointmentDAO objRescheduleCancelAppointmentDAO = new AppointmentDAO(tx);
			objRescheduleCancelAppointmentVO=objRescheduleCancelAppointmentDAO.cancelShift(objRescheduleCancelAppointmentVO, userVO,"1");
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return objRescheduleCancelAppointmentVO;
	}
	
	public String cancelSlots(List<RescheduleCancelAppointmentVO> _lstobjRescheduleCancelAppointmentVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		System.out.println("cancelSlots::BO");
		String _erMsg="",_exShift="",sNo="";
		int i=1;
		try {
			tx.begin();
			AppointmentDAO objRescheduleCancelAppointmentDAO = new AppointmentDAO(tx);			
			
			for (RescheduleCancelAppointmentVO rescheduleCancelAppointmentVO : _lstobjRescheduleCancelAppointmentVO) {
				

				if(i==1 || !_exShift.equals(rescheduleCancelAppointmentVO.getShiftId())){
					rescheduleCancelAppointmentVO=objRescheduleCancelAppointmentDAO.cancelShift(rescheduleCancelAppointmentVO, userVO,"2");
					_exShift=rescheduleCancelAppointmentVO.getShiftId();
					sNo=rescheduleCancelAppointmentVO.getsNo();
					i++;
				}
				rescheduleCancelAppointmentVO.setsNo(sNo);
				rescheduleCancelAppointmentVO=objRescheduleCancelAppointmentDAO.cancelSlot(rescheduleCancelAppointmentVO, userVO,"1");

				
				_erMsg=rescheduleCancelAppointmentVO.getErrorMessage();
			}
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return _erMsg;
	}
	
	public String getDayWiseAptCountDetail(AptDskCalendarVO aptDskCalendarVO,UserVO _userVO) {
		String strResult="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			strResult=objEssentialDAO.getDayWiseAptCountDetail(aptDskCalendarVO, _userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return strResult;
	}
	
	public String getDayWiseAptDetail(AptDskCalendarVO aptDskCalendarVO,UserVO _userVO) {
		String strResult="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			AppointmentEssentialDAO objEssentialDAO = new AppointmentEssentialDAO(tx);
			strResult=objEssentialDAO.getDayWiseAptDetail(aptDskCalendarVO, _userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return strResult;
	}

}//end class
