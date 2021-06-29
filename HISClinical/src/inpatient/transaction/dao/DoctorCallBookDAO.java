package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DoctorCallBookDAO extends DataAccessObject 
{

	public DoctorCallBookDAO(TransactionContext _tx)
	{
		super(_tx);
	}	
	
	public void saveDoctorCallDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "INSERT.HIPD_DOCTOR_CALLBOOK_DTL";

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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getIpAddress());
			populateMAP.put(sq.next(), _callBookVO.getCallRemarks());
			populateMAP.put(sq.next(), InpatientConfig.CALL_TYPE_MANUAL);
			populateMAP.put(sq.next(), _callBookVO.getIsDocCallByPeon());
			populateMAP.put(sq.next(), _callBookVO.getIsDocCallbyPhone());
			populateMAP.put(sq.next(), _callBookVO.getWardCode());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPeonRemarks());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPhoneRemarks());
			populateMAP.put(sq.next(), _callBookVO.getPatAdmnNo());
			if(_callBookVO.getUserType().equals("1"))
			{
				populateMAP.put(sq.next(), _callBookVO.getEmpNo());
				populateMAP.put(sq.next(), _callBookVO.getToConsultantName());
				populateMAP.put(sq.next(), _callBookVO.getUserType());
			
			}
			else
			{	
				populateMAP.put(sq.next(), _callBookVO.getEmpNo());
				populateMAP.put(sq.next(), _callBookVO.getToConsultantName());
				populateMAP.put(sq.next(), _callBookVO.getUserType());
				
			}
			//populateMAP.put(sq.next(), _callBookVO.getEmpNo());
			populateMAP.put(sq.next(), _callBookVO.getPatCrNo());
			populateMAP.put(sq.next(), _callBookVO.getEmpNo());
			populateMAP.put(sq.next(), _callBookVO.getPeonEmpNo());
			populateMAP.put(sq.next(), _UserVO.getUserEmpID());
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPeonStatus());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPhoneStatus());
			populateMAP.put(sq.next(), _callBookVO.getCallPriority());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void ModifyDoctorCallDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.HIPD_DOCTOR_CALLBOOK_DTL";

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
			
			populateMAP.put(sq.next(), _callBookVO.getCallRemarks());
			populateMAP.put(sq.next(), _callBookVO.getIsDocCallByPeon());
			populateMAP.put(sq.next(), _callBookVO.getIsDocCallbyPhone());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPeonStatus());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPhoneStatus());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPeonRemarks());
			populateMAP.put(sq.next(), _callBookVO.getDocCallByPhoneRemarks());
			populateMAP.put(sq.next(), _callBookVO.getPeonEmpNo());
			populateMAP.put(sq.next(), _callBookVO.getCallNo());
			populateMAP.put(sq.next(), _callBookVO.getEmpNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public DoctorCallBookVO[] getCallBookDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		{
			DoctorCallBookVO[] docCallBookVOArray=null;
			String query = "";
			ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			String queryKey = "SELECT_CALLBOOK_DETAILS.HIPD_DOCTOR_CALLBOOK_DTL";
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
				populateMAP.put(sq.next(), doctorCallBookVO.getPatCrNo());
				populateMAP.put(sq.next(), doctorCallBookVO.getPatAdmnNo());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DonorDtlDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				
				if(!rs.next())
				{
					throw new HisRecordNotFoundException("No Donor Found");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(DoctorCallBookVO.class, rs);
					System.out.println("length" + vo.length);
					docCallBookVOArray = new DoctorCallBookVO[vo.length];
				
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						docCallBookVOArray[i] = (DoctorCallBookVO) vo[i];
						
					}
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Call List Found");	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return docCallBookVOArray;
		}
	}

	public DoctorCallBookVO[] getAllCalls(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		{
			DoctorCallBookVO[] docCallBookVOArray=null;
			String query = "";
			ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			String queryKey = "SELECT_ALL_CALLBOOK_DETAILS.HIPD_DOCTOR_CALLBOOK_DTL";
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
				populateMAP.put(sq.next(), doctorCallBookVO.getPatCrNo());
				populateMAP.put(sq.next(), doctorCallBookVO.getPatAdmnNo());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DonorDtlDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				
				if(!rs.next())
				{
					throw new HisRecordNotFoundException("No Donor Found");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(DoctorCallBookVO.class, rs);
					System.out.println("length" + vo.length);
					docCallBookVOArray = new DoctorCallBookVO[vo.length];
				
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						docCallBookVOArray[i] = (DoctorCallBookVO) vo[i];
						
					}
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Call List Found");	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return docCallBookVOArray;
		}
	}

	
	public DoctorCallBookVO getCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		{
			DoctorCallBookVO callBookVO=new DoctorCallBookVO();
			String query = "";
			//ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			//Connection conn=super.getTransactionContext().getConnection();
			
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			String queryKey = "SELECT_CALLDETAILS.HIPD_DOCTOR_CALLBOOK_DTL";
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
				populateMAP.put(sq.next(), doctorCallBookVO.getCallNo());
				populateMAP.put(sq.next(), doctorCallBookVO.getEmpNo());
				populateMAP.put(sq.next(), doctorCallBookVO.getCallDateFormat());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DonorDtlDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				rs.beforeFirst();
				if (rs.next())
				{
					HelperMethods.populateVOfrmRS(callBookVO,rs);
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Call List Found");	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return callBookVO;
		}
	}
	
	public DoctorCallBookVO[] getOnCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		{
			DoctorCallBookVO[] docCallBookVOArray=null;
			String query = "";
			ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			String queryKey = "SELECT_ONCALL_DETAILS.HIPD_DOCTOR_CALLBOOK_DTL";
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
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), doctorCallBookVO.getEmpNo());
				populateMAP.put(sq.next(), doctorCallBookVO.getWardCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DonorDtlDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				
				if(!rs.next())
				{
					//throw new HisRecordNotFoundException("No Donor Found");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(DoctorCallBookVO.class, rs);
					System.out.println("length" + vo.length);
					docCallBookVOArray = new DoctorCallBookVO[vo.length];
				
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						docCallBookVOArray[i] = (DoctorCallBookVO) vo[i];
						
					}
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Call List Found");	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return docCallBookVOArray;
		}
	}
	
	public void updateRoundNoDetail(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE_ROUND_NO.HIPD_DOCTOR_CALLBOOK_DTL";
		
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
		
			populateMAP.put(sq.next(), _callBookVO.getRoundDate());
			populateMAP.put(sq.next(), _callBookVO.getWardCode());
			populateMAP.put(sq.next(), _callBookVO.getRoundDate());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _callBookVO.getEmpNo());
			populateMAP.put(sq.next(), _callBookVO.getCallNo());
			populateMAP.put(sq.next(), _callBookVO.getCallDate());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	//addedBy:NehaRajgariya Date:1sept2016
	public String getSmsFlagDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO) {
		// TODO Auto-generated method stub
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.HPMRT_CONFIG_FLAG_MST";
		String smsFlag = null;
		ResultSet rs;
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
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DonorDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Donor Found");
			}
			else
			{
				smsFlag = rs.getString(1);
					
			}
				
			
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException("No Call List Found");	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }
		return smsFlag;
	}
	
}
