package mrd.transaction.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class MRDRecordDtlDAO extends DataAccessObject implements MRDRecordDtlDAOi
{
	public  MRDRecordDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	// Inserting Data
	public void create(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_MRD_RECORD_DTL";
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
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordDesc());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordId());
        	populateMAP.put(sq.next(), "1");	//sl no
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordType());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRemarks());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRackId());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getShelfId());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getIsScaned());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getPutBy());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	public String generateMrdRecordId(UserVO userVO,String recordType)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="GENERATE_ID.HPMRT_MRD_RECORD_DTL";
	    
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
			populateMAP.put(sq.next(),recordType);
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
	
	public void insertRecordAcceptInMrd(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT_ACCEPT_RECORD_IN_MRD.HPMRT_MRD_RECORD_DTL";
        String mrdCode = null;
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
        	//populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordDesc());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordId());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordId());
        	populateMAP.put(sq.next(), "1");	//sl no
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordType());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRemarks());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
        	//populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdCode().equals("")?"":mrdRecordDtlVO.getMrdCode());
        	populateMAP.put(sq.next(), mrdCode);
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), mrdRecordDtlVO.getPatAdmNo());
        	//populateMAP.put(sq.next(), userVO.getIpAddress().equals("")?"":userVO.getIpAddress());
        	
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	public MrdRecordDtlVO[] getRecordListToArchivedByRecordType(String recordType,String mrdCode,UserVO userVO)
	{
		MrdRecordDtlVO[] arrmrdRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_RECORD_TOBE_ARCHIVED_IN_MRD.HPMRT_MRD_RECORD_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_STATUS_IN_MRD);
		//populateMAP.put(sq.next(), mrdCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			/*{
				throw new HisRecordNotFoundException("No Record Found");
				//arrmrdRecordVO = null;
			}
			else
			{*/
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				arrmrdRecordVO = new MrdRecordDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrmrdRecordVO[i] = (MrdRecordDtlVO) vo[i];
				}
			//}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return arrmrdRecordVO;
	}
	
	
	
	
	//added by swati
	//date:10-may-2019
	//getting dtl by adm no
	
	public MrdRecordDtlVO[] getRecordListToArchivedByAdmNo(UserVO userVO,String admNo)
	{
		MrdRecordDtlVO[] arrmrdRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_RECORD_TOBE_ARCHIVED_IN_MRD.HPMRT_ADMNO_RECORD_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("hello::::::::::");
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), admNo);
		populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_STATUS_IN_MRD);
		//populateMAP.put(sq.next(), mrdCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				/*throw new HisRecordNotFoundException("No Record Found");
				//arrmrdRecordVO = null;
			}
			else
			{*/
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				arrmrdRecordVO = new MrdRecordDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrmrdRecordVO[i] = (MrdRecordDtlVO) vo[i];
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
		return arrmrdRecordVO;
	}
	
	
	//added by swati
		//date:13-may-2019
		//getting dtl by cr no
		
		public MrdRecordDtlVO[] getRecordListToArchivedByCrNo(UserVO userVO,String admNo,String crno)
		{
			MrdRecordDtlVO[] arrmrdRecordVO = null;
			ValueObject vo[] = null;
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
			String queryKey = "GET_RECORD_TOBE_ARCHIVED_IN_MRD.HPMRT_CRNO_RECORD_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("hello::::::::::");
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			populateMAP.put(sq.next(), crno);
			populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_STATUS_IN_MRD);
			//populateMAP.put(sq.next(), mrdCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			

			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (rs.next())
				{
					/*throw new HisRecordNotFoundException("No Record Found");
					//arrmrdRecordVO = null;
				}
				else
				{*/
					rs.beforeFirst();
		
					vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
					arrmrdRecordVO = new MrdRecordDtlVO[vo.length];
					for (int i = 0; i < vo.length; i++)
					{
						arrmrdRecordVO[i] = (MrdRecordDtlVO) vo[i];
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
			return arrmrdRecordVO;
		}
		
		
	
	

	public void updateRecordArchivalDetail(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_RECORD_ARCHIVAL_DETAIL.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getRackId());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getShelfId());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getPutBy());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	

	/* *****************************************************************************
	 * Update the mrd record status on basis of record id
	 * ****************************************************************************/
	public void updateRecordStatus(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.RECORD_STATUS.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
		
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
			populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
			populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}
	
	public void updateReturnRecordArchived(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_RECORD_ARCHIVAL_DETAIL.HPMRT_MRD_RECORD_DTL";
		//String queryKey = "UPDATE_RETURN_RECORD_ARCHIVAL_DETAIL.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getRackId());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getShelfId());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getPutBy());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	public void updateReturnRecordInMrd(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_RETURN_RECORD_UNCHANGED_DETAIL.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getPutBy());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	public MrdRecordDtlVO[] getMrdRecordBasedOnShelfList(String recordType,String shelfId,UserVO userVO)
	{
		MrdRecordDtlVO[] arrMrdRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_RECORD_TOBE_MOVED.HPMRT_MRD_RECORD_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), shelfId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Record Found");
				arrMrdRecordVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				arrMrdRecordVO = new MrdRecordDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrMrdRecordVO[i] = (MrdRecordDtlVO) vo[i];
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
		return arrMrdRecordVO;
	}
	
	public void updateRecordMovementDetail(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_RECORD_MOVEMENT_DETAIL.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
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
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdCode());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getRackId());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getShelfId());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getPutBy());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}

	public MrdRecordDtlVO[] getListOfOpdFilesToMove(String _mrdCode, UserVO _userVO) {
		MrdRecordDtlVO[] arrmrdRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_OPD_FILE_FOR_MOVEMENT.HPMRT_MRD_RECORD_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		populateMAP.put(sq.next(), _mrdCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No OPD Files Found For Movement");
				//arrmrdRecordVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				arrmrdRecordVO = new MrdRecordDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrmrdRecordVO[i] = (MrdRecordDtlVO) vo[i];
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
		return arrmrdRecordVO;
	}
	
	/* *****************************************************************************
	 * Update the mrd record status  and issue flag on basis of record id
	 * ****************************************************************************/
	public void updateRecordStatusAndIssueFlag(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.RECORD_STATUS_ISSUE_FLAG.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
		
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
			populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
			populateMAP.put(sq.next(), mrdRecordDtlVO.getIssueFlag());
			populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}
	
	public MrdRecordDtlVO[] getListOfOpdFilesToReturn(String _mrdCode, UserVO _userVO) {
		MrdRecordDtlVO[] arrmrdRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_OPD_FILE_FOR_RETURN.HPMRT_MRD_RECORD_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		populateMAP.put(sq.next(), _mrdCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_ISSUE_FLAG_MOVEMENT);
		populateMAP.put(sq.next(), MrdConfig.MOVEMENT_TYPE_ISSED_FROM_MRD);
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No OPD Files Found For Return");
				//arrmrdRecordVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				arrmrdRecordVO = new MrdRecordDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrmrdRecordVO[i] = (MrdRecordDtlVO) vo[i];
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
		return arrmrdRecordVO;
	}

	public void destroyRecordDetail(MrdRecordDtlVO mrdRecordDtlVO, UserVO userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "DESTROY_RECORD_DETAIL.HPMRT_MRD_RECORD_DTL";
		Sequence sq = new Sequence();
		
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
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordStatus());
	    	 populateMAP.put(sq.next(), mrdRecordDtlVO.getMrdRecordId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	public void saveExtendDays(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT_HPMRT_MRDRECORD_ISS_EXTEND_DTL";
		Sequence sq = new Sequence();
		
		
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
			populateMAP.put(sq.next(), mrdRecordRequestVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestVO.getExtendDays());
			populateMAP.put(sq.next(), ""); //extended_req_by 
			populateMAP.put(sq.next(), mrdRecordRequestVO.getExtendReason());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getIpAddress());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}
	
	public  MrdRecordRequestDtlVO getEssentials(MrdRecordRequestDtlVO mrdRecordRequestVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_GNUM_EXTENSION_S_NO_HPMRT_MRDRECORD_ISS_EXTEND_DTL";
		Sequence sq = new Sequence();
		MrdRecordRequestDtlVO mrdRecordRequestVOO = new MrdRecordRequestDtlVO();
		
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
			populateMAP.put(sq.next(), mrdRecordRequestVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs == null && !rs.next())
			{
				//throw new HisRecordNotFoundException("No OPD Files Found For Movement");
				//arrmrdRecordVO = null;
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				mrdRecordRequestVOO.setHPMRNUM_UNDER_EXTENTION_SNO(rs.getString(1));
				
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
		return mrdRecordRequestVOO;
	}
	
	public void updateSL_NO(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_HPMRNUM_UNDER_EXTENTION_SNO_HPMRT_MRDRECORD_REQ_DTL";
		Sequence sq = new Sequence();
		
		
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
			populateMAP.put(sq.next(), mrdRecordRequestVO.getHPMRNUM_UNDER_EXTENTION_SNO());
			populateMAP.put(sq.next(), mrdRecordRequestVO.getRequestId());			
			populateMAP.put(sq.next(), userVO.getHospitalCode());			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}
}//end class
