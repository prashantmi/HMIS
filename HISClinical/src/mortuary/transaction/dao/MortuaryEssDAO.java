package mortuary.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import opd.dao.OpdDaoConfig;

//import oracle.jdbc.driver.OracleTypes;
import mortuary.MortuaryConfig;
import registration.RegistrationConfig;
import registration.dao.RegistrationDaoConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class MortuaryEssDAO extends DataAccessObject implements MortuaryEssDAOi
{
	public MortuaryEssDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Getting The List of Dead Patient Send To Mortuary 
	public PatientDeathDetailVO[] getInHouseDeadPatientList(UserVO userVO)
	{
		PatientDeathDetailVO[] arrDeadPatListVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DEAD_PATIENT_SEND_TO_MORTUARY.HPMRT_PAT_DEATH_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Dead Patient Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatientDeathDetailVO.class, rs);
			arrDeadPatListVO = new PatientDeathDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrDeadPatListVO[i] = (PatientDeathDetailVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeadPatListVO;
	}
	
	//Getting The List of Peon on The Basis of Department Code Who Brought The Dead Body
	public List getEmployeeListDeptProcessWise(String processId,String deptCode,UserVO userVO)
	{
		
			String errorMsg = "";
			ResultSet rs = null;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				Procedure strProc = new Procedure(MortuaryDaoConfig.PROCEDURE_GET_PEON_LIST);
				strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
				strProc.addInParameter(2, Types.VARCHAR,processId);
				strProc.addInParameter(3, Types.VARCHAR, deptCode);
				strProc.addOutParameter(4, Types.VARCHAR);
				//strProc.addOutParameter(5, OracleTypes.CURSOR);
				strProc.addOutParameter(5, Types.REF);
				strProc.execute(conn);
				errorMsg = (String) strProc.getParameterAt(4);
				rs = (ResultSet) strProc.getParameterAt(5);

			}
			catch (Exception e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			// log.error(query + "\n");
			/*
			 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
			 */
			
			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			finally
			{
				if (rs != null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
			return alRecord;
		}
	
	//Getting Deceased Address
	public AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		AddressVO patAddressVO=new AddressVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="SELECT.PATIENT.HRGT_PATIENT_ADD_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),crNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(),"1");
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patAddressVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patAddressVO;
	}
	
	//Getting deceased Police Verification Detail
	public PoliceVerificationDtlVO getPoliceVerificationDetail(String mlcNo,UserVO userVO)
	{
		PoliceVerificationDtlVO policeVerVO=new PoliceVerificationDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="GET_POLICE_VERIFICATION_BY_MLCNO.HRGT_POLICE_VERIFICATION_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),mlcNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				policeVerVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(policeVerVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return policeVerVO;
	}
	
	//Counting the Capacity of Rack
	public String countMaxCapacityOfRack(String rackId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="COUNT_DEAD_BODY_IN_RACK.HMRT_CHAMBER_RACK_MST";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),rackId);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	//Updating the Chamber Rack Status
	public void updateChamberStatus(String chamberStatus, String rackId,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		 Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_RACK_STATUS.HMRT_CHAMBER_RACK_MST";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
			populateMAP.put(sq.next(),chamberStatus);
			populateMAP.put(sq.next(),rackId);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	//Getting Deceased Existing Image
	public PatientImageDtlVO[] getDeceasedExistingImage(String crNo,UserVO userVO)
	{
		PatientImageDtlVO[] arrDeceasedImageVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DECEASED_IMAGE.HRGT_PATIENT_IMAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

	//	populateMAP.put(sq.next(), "1080800001924");
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrDeceasedImageVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PatientImageDtlVO.class, rs);
				arrDeceasedImageVO = new PatientImageDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedImageVO[i] = (PatientImageDtlVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedImageVO;
	}
	
	public DeceasedDetailVO[] getDeceasedListInStreacher(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DECEASED_IN_STREACHER.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Deceased in Stretcher");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	
	public void updateBodyStatus(String bodyStatus, String deceasedNo,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		 Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_DECEASED_BODY_STATUS.HMRT_DECEASED_DTL";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
			populateMAP.put(sq.next(),bodyStatus);
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	public DeceasedDetailVO[] getDeceasedListToHandover(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DECEASED_IN_STORAGE.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_CHAMBER);
		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Deceased In Stretcher Or Chamber Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	
	public void updateBodyStatusNIssueTime(String bodyStatus, String deceasedNo,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		 Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_DECEASED_BODY_STATUS_N_ISSUETIME.HMRT_DECEASED_DTL";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
			populateMAP.put(sq.next(),bodyStatus);
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
	public String getChamberRackName(String deceasedNo,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="GET_CHAMBER_N_RACK_NAME.HMRT_CHAMBER_RACK_MST";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public PatientDeathDetailVO getDeceasedHandoverDetail(String crNo,UserVO userVO)
	{
		PatientDeathDetailVO patDeathVO=new PatientDeathDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="GET_PATIENT_HANDOVER_DETAIL.HPMRT_PAT_DEATH_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),crNo);
		//populateMap.put(sq.next(),crNo);
		//populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(), RegistrationConfig.DEAD_BODY_HANDOVER_TO_MORTUARY);
		populateMap.put(sq.next(),MortuaryConfig.BODY_STATUS_HANDOVER);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				patDeathVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patDeathVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patDeathVO;
	}
	
	public DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO)
	{
		DeceasedRelativeDtlVO[] arrRelativeVO=null;
		ValueObject vo[] = null;
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="GET_EXISTING_RELATIVE.HMRT_DECEASED_RELATIVE_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(),MortuaryConfig.DECEASED_RELATIVE_FLAG_STORAGE);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				arrRelativeVO=null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedRelativeDtlVO.class, rs);
				arrRelativeVO = new DeceasedRelativeDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrRelativeVO[i] = (DeceasedRelativeDtlVO) vo[i];
				}
			}	
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return arrRelativeVO;
	}
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemRequest(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DECEASED_FOR_POSTMORTEM_REQUEST.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_CHAMBER);
		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_REQUEST_WAVEOFF);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_CANCELED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Deceased In Stretcher Or Chamber Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	
	public DeceasedDetailVO[] getDeceasedListForPostmortemEntry(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DECEASED_FOR_POSTMORTEM_ENTRY.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_APPROVED);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Postmortem Request Raised ");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	

	public String getMortuaryName(String mortuaryCode,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="SELECT_MORTUARYNAME.HMRT_MORTUARY_MST";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),mortuaryCode);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	public PatientDeathDetailVO getExistingInjuryDetail(String crNo,UserVO userVO)
	{
		PatientDeathDetailVO deathVO=new PatientDeathDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="GET_EXISTING_INJURY_DETAIL.HPMRT_PAT_DEATH_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),crNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				deathVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deathVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return deathVO;
	}
	
	public DeceasedDetailVO[] getUnknownBodyList(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_UNKNOWN_N_UNCLAIMED_BODY.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_MORTUARY_OR_STREACHER);
		populateMAP.put(sq.next(), MortuaryConfig.BODY_STATUS_IN_CHAMBER);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.UNIDENTIFIED_BODE_YES);
		populateMAP.put(sq.next(), MortuaryConfig.IS_BODY_CLAIMED_NO);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unknown Or Unclaimed Body Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	
	public String getPatientIdMark(String crNo,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="GET_UNKNOWN_PATIENT_ID_MARK.HRGT_PATIENT_DTL";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),crNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public List getTemplateIdForPostmoprtemEntry(String autopsyCatId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_TEMPLATE_ID.HMRT_CAT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), autopsyCatId);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MortuaryEssDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public String getAutopsyCategoryId(String dob,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="GET_AUTOPSY_CAT_ID.HMRT_AUTOPSY_CAT_MST";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),dob);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public DeceasedDetailVO[] getFinalOpinionToBeApproved(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_OPINION_TO_BE_APPROVED.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Opinion Found To Be Approved");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	

	public List getGenderList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_ALL_GENDER.GBLT_GENDER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());  // Anant Patel
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Anant Patel
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Mortuary Area Found");
			}*/

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MortuaryEssDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getMaritilStatusList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_ALL_MARITIAL_STATUS.GBLT_MARITAL_STATUS_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Mortuary Area Found");
			}*/

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MortuaryEssDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public String getPatientDOB(String age,String ageUnit,UserVO userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_DOB);
			proc.addInParameter(1, Types.NUMERIC, age);
			proc.addInParameter(2, Types.VARCHAR, ageUnit);
			/*proc.addOutParameter(3, Types.VARCHAR);
			proc.addOutParameter(4, OracleTypes.CURSOR);
			
			proc.execute(conn);
			errorMsg = (String) proc.getParameterAt(3);
			rs = (ResultSet) proc.getParameterAt(4);*/
			
			proc.setReturnType(Types.TIMESTAMP);
			java.sql.Timestamp DOB = (java.sql.Timestamp) proc.execute(conn);

			SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
			sf.applyPattern("dd/MMM/yyyy");
			String date = sf.format(DOB);
			
			return date;
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
	}
	public DeceasedDetailVO[] getWaveoffDetails(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_WAVEOFF_DETAILS.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_TYPE_FORENSIC);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_APPROVED);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Waveoff Detail Found");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}

	public String getDeceasedNoByPostmortemId(String postmortemId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="GET_DECEASED_NO_BY_POSTMORTEM_ID.HMRT_POSTMORTEM_REQ_DTL";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),postmortemId);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public DeceasedDetailVO[] getPostmortemReadyToHandover(UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_POSTMORTE_READY_TO_HANDOVER.HMRT_POSTMORTEM_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Postmortem Ready To Handover");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrDeceasedVO;
	}
	
	public ConsentMappingMasterVO[] getConsentMappingDetail(UserVO userVO)
	{
		ConsentMappingMasterVO[] arrConsenttMappingVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "SELECT_CONSENT_MAPPING_DETAIL.HGBT_CONSENT_MAPPING";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MortuaryConfig.CONSENT_SERVICE_TYPE_ID_CLINICAL_AUTOPSY);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Postmortem Ready To Handover");
				arrConsenttMappingVO = null;
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(ConsentMappingMasterVO.class, rs);
				arrConsenttMappingVO = new ConsentMappingMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrConsenttMappingVO[i] = (ConsentMappingMasterVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrConsenttMappingVO;
	}

	///Generate Consent
	public void generatConsent(PostmortemRequestDetailVO postmortemRequestDtlVO,UserVO userVO)
	{
		ResultSet rs = null;
		String errorMsg = null;							
		
		try
		{
			Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GENERATE_CONSENT);
			strProc.addInParameter(1, Types.NUMERIC, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.NUMERIC, userVO.getSeatId());
			strProc.addInParameter(3, Types.VARCHAR, userVO.getUserEmpID());
			strProc.addInParameter(4, Types.NUMERIC, MortuaryConfig.CONSENT_SERVICE_TYPE_ID_CLINICAL_AUTOPSY);//service Type
			strProc.addInParameter(5, Types.VARCHAR, postmortemRequestDtlVO.getPostmortemType());//service Id
			strProc.addInParameter(6, Types.VARCHAR, postmortemRequestDtlVO.getPostmortemId());
			strProc.addInParameter(7, Types.NUMERIC, "");
			strProc.addInParameter(8, Types.NUMERIC, "");
			strProc.addInParameter(9, Types.NUMERIC, "");
			
			

			strProc.execute(super.getTransactionContext().getConnection());
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
	}
	
	///Consent Received
	public void updateConsentReceivedStatus(DeceasedRelativeDtlVO relativeVO,PostmortemRequestDetailVO postmortemRequestDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="UPDATE_CONSENT_RECEIVED_STATUS.HGBT_CONSENT_DTL";
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		  
		try
		{
			populateMAP.put(sq.next(), MortuaryConfig.CONSENT_RECEIVED);
			populateMAP.put(sq.next(), userVO.getSeatId());//received By
			populateMAP.put(sq.next(), MortuaryConfig.CONSENT_GIVEN_BY_RELATIVE);
			populateMAP.put(sq.next(), relativeVO.getRelativeName());
			populateMAP.put(sq.next(), relativeVO.getRelativeAddress());
			populateMAP.put(sq.next(), "");//Id mark
			populateMAP.put(sq.next(), relativeVO.getRelativeCode());
			populateMAP.put(sq.next(), relativeVO.getRelativeContactNo());
			populateMAP.put(sq.next(), postmortemRequestDtlVO.getPostmortemId());
			populateMAP.put(sq.next(), MortuaryConfig.CONSENT_SERVICE_TYPE_ID_CLINICAL_AUTOPSY);
			populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_TYPE_CLINICAL);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO:populateMap::"+e);
		}
		try
		{
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		} 
	}
	
}
