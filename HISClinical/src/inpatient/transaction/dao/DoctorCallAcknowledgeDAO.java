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

public class DoctorCallAcknowledgeDAO extends DataAccessObject implements DoctorCallAcknowledgeDAOi 
{

	public DoctorCallAcknowledgeDAO(TransactionContext _tx)
	{
		super(_tx);
	}	
	
	
	public DoctorCallBookVO[] getCallAcknowledgeDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		{
			DoctorCallBookVO[] docCallAcknowledgeVOArray=null;
			String query = "";
			ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			String queryKey = "SELECT_CALLACKNOWLEDGE_DETAILS.HIPD_DOCTOR_CALLBOOK_DTL";
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
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getUserEmpID());
				populateMAP.put(sq.next(), _userVO.getUserEmpID());

				
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
					docCallAcknowledgeVOArray = new DoctorCallBookVO[vo.length];
				
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						docCallAcknowledgeVOArray[i] = (DoctorCallBookVO) vo[i];
						
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
			return docCallAcknowledgeVOArray;
		}
	}

	public DoctorCallBookVO[] getAllCallsAcknowledge(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		{
			DoctorCallBookVO[] docCallAcknowledgeVOArray=null;
			String query = "";
			ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
			String queryKey = "SELECT_ALL_CALLACKNOWLEDGE_DETAILS.HIPD_DOCTOR_CALLBOOK_DTL";
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
				
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getUserEmpID());
				
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
					throw new HisRecordNotFoundException("No Record Found");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(DoctorCallBookVO.class, rs);
					System.out.println("length" + vo.length);
					docCallAcknowledgeVOArray = new DoctorCallBookVO[vo.length];
				
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						docCallAcknowledgeVOArray[i] = (DoctorCallBookVO) vo[i];
						
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
			return docCallAcknowledgeVOArray;
		}
	}

	
	public void saveDoctorCallAcknowledgeDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE_ACKNOWLEDGE_DETAILS.HIPD_DOCTOR_CALLBOOK_DTL";

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
			populateMAP.put(sq.next(), _callBookVO.getDoctorRemarks());
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _callBookVO.getCallNo());
			populateMAP.put(sq.next(), _callBookVO.getEmpNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _callBookVO.getCallDate());
						
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
	
}
