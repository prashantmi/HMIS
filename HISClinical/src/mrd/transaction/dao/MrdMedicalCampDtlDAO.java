package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.utility.Sequence;
import hisglobal.vo.MrdMedicalCampDtlVO;
import hisglobal.vo.MrdMedicalCampTeamDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.MrdMedicalCampFB;

public class MrdMedicalCampDtlDAO  extends DataAccessObject implements MrdMedicalCampDtlDAOi{

	public MrdMedicalCampDtlDAO(TransactionContext _tx) {
		super(_tx);
	}

	private int nInsertedIndex=0;
	//select All Camp which are in running MODE
	public List<MrdMedicalCampDtlVO> getCampListForMedicalCamp(MrdMedicalCampDtlVO mrdMedicalCampDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.RECORD_TO_MEDICAL_CAMP.HPMRT_MEDICAL_CAMP_DTL";
		List <MrdMedicalCampDtlVO> mrdMedicalCampDtlVOList=null;
		ResultSet rs=null;
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MrdMedicalCampDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else {
				rs.beforeFirst();
				mrdMedicalCampDtlVOList=new ArrayList<MrdMedicalCampDtlVO>();
				while (rs.next()) {
					mrdMedicalCampDtlVO=new MrdMedicalCampDtlVO();
					HelperMethods.populateVOfrmRS(mrdMedicalCampDtlVO, rs);

					System.out.println("rs values Camp Name:"+mrdMedicalCampDtlVO.getStrCampName());
					mrdMedicalCampDtlVOList.add(mrdMedicalCampDtlVO);
				}
			}
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException");
			}	
		}
		return mrdMedicalCampDtlVOList;
	}
	
	//select All Emp For Medical Camp
	public List getCampEmpNameForMedicalCamp(UserVO userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_METHOD_EMPLOYEE_LIST);

			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, MrdConfig.CAMP_RESOURCE_ALLOCATION_EMPLOYEE_NAME_PROCESSID);
			strProc.addInParameter(3, Types.VARCHAR, MrdConfig.BLOODBANK_DEPT_CODE);
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public String saveCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) 
	{
		ResultSet rs=null;
		String query = "";
		String campReqNo=null;
		Map populateMAP = new HashMap();
		Map queryKeyCampReqMap = new HashMap();
		Sequence sq1 = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKeyCampReqNo = "SELECT_MED_CAMP_NO.HPMRT_MEDICAL_CAMP_DTL";
		String queryKey = "INSERT_MED_CAMP_REQ_DETAIL.HPMRT_MEDICAL_CAMP_DTL";

		try
		{
			queryKeyCampReqNo= HelperMethodsDAO.getQuery(filename, queryKeyCampReqNo);
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
			queryKeyCampReqMap.put(sq1.next(), userVO.getHospitalCode());
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mrdMedicalCampDetailDAO.queryKeyCampReqMap::" + e);
		}
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),queryKeyCampReqNo,queryKeyCampReqMap);
			 if(!rs.next()){
		    	   throw new HisRecordNotFoundException("Medical Camp Request No. Not Genrated");		 	    	 	    	 
		 	     }
		       else
		       {
		       rs.beforeFirst(); 
		       rs.next();
		       campReqNo=rs.getString(1);
		       medicalCampDtlVO.setStrCampReqNo(campReqNo);
		}}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}	
		
		try
		{
			System.out.println("camp Start Date :"+medicalCampDtlVO.getStrCampStartDateTime());
			System.out.println("Male Pat Att :"+medicalCampDtlVO.getStrTotalMalePatientAttended());
					Sequence sq=new Sequence();
			        populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampReqNo());
			        populateMAP.put(sq.next(), MrdConfig.SLNO);
			        populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampRequisitionDate());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampStartDateTime());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampEndDateTime());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampDescription());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalFemalePatientAttended());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalMaleChildPatientAttended());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampVenue());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalFemaleChildPatientAttended());
					populateMAP.put(sq.next(), userVO.getSeatId());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalNoofPatientAttended());
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalMalePatientAttended());
					//entryDate
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrIsCampClosed());
					populateMAP.put(sq.next(), userVO.getHospitalCode());					
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					 //SYSDATE
					populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampName());			
			}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CampDetailDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return campReqNo;
	}

	public void saveCampEmpDetail(MrdMedicalCampTeamDtlVO medicalCampTeamDtlVO, UserVO userVO, HisDAO dao, String medicalCampNo, String strMode_p)  {
			
		
			// TODO Auto-generated method stub
			System.out.println("First Emp Name :"+medicalCampTeamDtlVO.getStrArrEmployeeName());
			System.out.println("Camp No :"+medicalCampNo);
			System.out.println("Emp Role :"+medicalCampTeamDtlVO.getStrArrRole());
			System.out.println("Emp Name :"+medicalCampTeamDtlVO.getStrArrEmployeeName());
			System.out.println("Emp Name :"+medicalCampTeamDtlVO.getStrArrEmployeeId());
			System.out.println("Duty remarks :"+medicalCampTeamDtlVO.getStrArrDutyRemarks());
			System.out.println("hospital Code:"+userVO.getHospitalCode());
			System.out.println("MODE :"+strMode_p);

			ResultSet rs = null;
			final String strProcName = MrdConfig.PROC_MED_CAMP_EMP_DETAIL_HPMRT_MED_CAMP_TEAM_DTL;
			final int nprocIndex;
			String strDbErr;
			int seq=0;		
			try { 
				nprocIndex = dao.setProcedure(strProcName);
				// Setting and Registering In and Out Parameters
				HisUtil.replaceNullValueWithEmptyString(medicalCampTeamDtlVO);
		
				dao.setProcInValue(nprocIndex, "p_mode", strMode_p,++seq);
				dao.setProcInValue(nprocIndex, "p_camp_id", medicalCampNo,++seq);
				dao.setProcInValue(nprocIndex, "p_sl_no", " ",++seq);
				dao.setProcInValue(nprocIndex, "p_emp_role", medicalCampTeamDtlVO.getStrArrRole(),++seq);
				dao.setProcInValue(nprocIndex, "p_emp_no", medicalCampTeamDtlVO.getStrArrEmployeeId(),++seq);
				dao.setProcInValue(nprocIndex, "p_emp_duty_remark", medicalCampTeamDtlVO.getStrArrDutyRemarks(),++seq);
				dao.setProcInValue(nprocIndex, "p_hosp_code", userVO.getHospitalCode(),++seq);
				dao.setProcInValue(nprocIndex, "p_isvalid", "1",++seq);
				dao.setProcInValue(nprocIndex, "p_seat_id", userVO.getSeatId(),++seq);
				dao.setProcOutValue(nprocIndex, "err", 1,++seq);			
				dao.executeProcedureByPosition(nprocIndex);
					// Getting out parameters
					strDbErr = dao.getString(nprocIndex, "err");

					// If Database Error Occurs, No farther processing is required.
					if (strDbErr != null && !strDbErr.equals("")
							&& !strDbErr.equals("success")) {
						throw new Exception("Data Base Error:" + strDbErr);
					}	

		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();

		}	
			finally {
				if (dao != null) {
					dao.free();
					
				}
				dao = null;
			}
	}

	public void getCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) 
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.ALL_RECORD_TO_MEDICAL_CAMP.HPMRT_MEDICAL_CAMP_DTL";
		String campId = medicalCampDtlVO.getStrCampId();
		List <MrdMedicalCampDtlVO> mrdMedicalCampDtlVOList=null;
		ResultSet rs=null;
		
		System.out.println("Camp id :"+medicalCampDtlVO.getStrCampId());
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		try
		{	
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);	
			populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampId());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MrdMedicalCampDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else {
				rs.beforeFirst();
				while (rs.next()) {
					HelperMethods.populateVOfrmRS(medicalCampDtlVO, rs);
				}
			}
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException");
			}	
		}
	}

	public List<MrdMedicalCampTeamDtlVO> getCampEmpDetail(MrdMedicalCampDtlVO medicalCampDtlVO,	UserVO userVO) 
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.ALL_EMP_RECORD_TO_MEDICAL_CAMP.HPMRT_MEDICAL_CAMP_DTL";
		ResultSet rs=null;
		
		System.out.println("Camp id :"+medicalCampDtlVO.getStrCampId());
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		try
		{	
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);	
			populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampId());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MrdMedicalCampDtlDAO.populateMAP::"+e);
		}

		List<MrdMedicalCampTeamDtlVO> alRecord = new ArrayList<MrdMedicalCampTeamDtlVO>();

		try
		{	
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else {
				rs.beforeFirst();
				ValueObject vo[] = null;
				vo = HelperMethods.populateVOfrmRS(MrdMedicalCampTeamDtlVO.class, rs);
				for (ValueObject v : vo)
					alRecord.add((MrdMedicalCampTeamDtlVO)v);
			}
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public void updateCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq1 = new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_MED_CAMP_REQ_DETAIL.HPMRT_MEDICAL_CAMP_DTL";
		System.out.println("MrdMedicalCampDtlDAO.updateCampDetail()");
		System.out.println("Camp Name  :"+medicalCampDtlVO.getStrCampName());
		System.out.println("Camp No  :"+medicalCampDtlVO.getStrCampReqNo());
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			 Sequence sq=new Sequence();
			 populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampStartDateTime());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampEndDateTime());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampDescription());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalFemalePatientAttended());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalMaleChildPatientAttended());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampVenue());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalFemaleChildPatientAttended());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalNoofPatientAttended());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrTotalMalePatientAttended());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrIsCampClosed());
				populateMAP.put(sq.next(), userVO.getSeatId());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampName());
				populateMAP.put(sq.next(), medicalCampDtlVO.getStrCampReqNo());
				populateMAP.put(sq.next(), userVO.getHospitalCode());					
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);	
			}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CampDetailDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
}
