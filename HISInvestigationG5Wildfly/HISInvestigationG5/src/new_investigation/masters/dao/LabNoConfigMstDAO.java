package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import new_investigation.InvestigationConfig;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.LabNoConfigMasterVO;

public class LabNoConfigMstDAO extends DataAccessObject implements LabNoConfigMstDAOi
{

	public LabNoConfigMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}




	public void createLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="";
	
		if(labnoconfig_VO.getAcceptanceAreawise().equals("0"))
		queryKey= "INSERT.HIVT_LABNO_CONF_MST";
		else
		queryKey= "INSERT.HIVT_LABNO_CONF_MST_AREA_WISE";
		
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
			
			if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
			populateMAP.put(sq.next(), labnoconfig_VO.getSampleAcceptanceAreaCode());
			
			populateMAP.put(sq.next(), labnoconfig_VO.getAcceptanceAreawise());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getPatientType());
			populateMAP.put(sq.next(), labnoconfig_VO.getInitializationType());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabNoFormat());
			populateMAP.put(sq.next(), labnoconfig_VO.getSeqDigit());
			populateMAP.put(sq.next(), labnoconfig_VO.getFromSeries());
			populateMAP.put(sq.next(), labnoconfig_VO.getToSeries());
		/*	populateMAP.put(sq.next(), labnoconfig_VO.getInitDate());
			populateMAP.put(sq.next(), labnoconfig_VO.getReinitDate());*/
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());



		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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



	public void fetchCheckListLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LABNO_CONF_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), labnoconfig_VO.getSeqNo());
		populateMap.put(sq.next(), labnoconfig_VO.getLabCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labnoconfig_VO ,rs);
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
	}


	// for Updating The old Record
	public void updateLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="";
				
	    queryKey="UPDATE.HIVT_LABNO_CONF_MST";
		
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
			if(labnoconfig_VO.getAcceptanceAreawise().equals("0"))
		      labnoconfig_VO.setSampleAcceptanceAreaCode(null);
			
			populateMAP.put(sq.next(), labnoconfig_VO.getSampleAcceptanceAreaCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getAcceptanceAreawise());
			populateMAP.put(sq.next(), labnoconfig_VO.getInitializationType());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabNoFormat());
			populateMAP.put(sq.next(), labnoconfig_VO.getSeqDigit());
			populateMAP.put(sq.next(), labnoconfig_VO.getFromSeries());
			populateMAP.put(sq.next(), labnoconfig_VO.getToSeries());
		/*	populateMAP.put(sq.next(), labnoconfig_VO.getInitDate());
			populateMAP.put(sq.next(), labnoconfig_VO.getReinitDate());*/
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			
			populateMAP.put(sq.next(), labnoconfig_VO.getSeqNo());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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



	
	public String checkDuplicateLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="";
		
		if(labnoconfig_VO.getAcceptanceAreawise().equals("0"))
		queryKey="CHECKDUPLICATE.HIVT_LABNO_CONF_MST";
		
		if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
		queryKey="CHECKDUPLICATE.HIVT_LABNO_CONF_MST_AREA_WISE";	
		
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
			populateMAP.put(sq.next(), labnoconfig_VO.getPatientType());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getTestCode());
			if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
			populateMAP.put(sq.next(), labnoconfig_VO.getSampleAcceptanceAreaCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}


	// For lab combo

	public List getlabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlocationName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_LABNO_CONF_MST";

            //chandan
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());


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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstlocationName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstlocationName;
	}


	// For test combo

	public List gettestCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstwardName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_COMBO.HIVT_LABNO_CONF_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		

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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstwardName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstwardName;
	}

	public List getTest(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstTest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UNMAPPED_TEST.HIVT_LABNO_CONF_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), labnoconfig_VO.getLabCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		//changed by chandan
/*		populateMap.put(sq.next(), labnoconfig_VO.getLabCode());
		populateMap.put(sq.next(), labnoconfig_VO.getPatientType());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
*/
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstTest=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return lstTest;
	}
	
	
	/*public  List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _UserVO)
	{
		String query = "";		
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST";
		Sequence sq = new Sequence();

		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
	//	populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
					populateMAP);
			rs.beforeFirst();
			
			 if (!rs.next())
	            {
	                throw new HisRecordNotFoundException("No Sample Collection Area Found");
	            }
	            else
	            {
	                rs.beforeFirst();
	                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
	                Inv_SampleCollectionVO InvSampleCollectionVO=null;
	                while (rs.next()) {
	                	InvSampleCollectionVO=new Inv_SampleCollectionVO();
	                    HelperMethods.populateVOfrmRS(InvSampleCollectionVO, rs);
	                    lstInvSampleCollectionVO.add(InvSampleCollectionVO);
	                }
	            }
		}
		catch (HisRecordNotFoundException e)
		{

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("" + e);
		}
		return lstInvSampleCollectionVO;

	}*/
	

	public List getSampleCollectionArea(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlocationName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST";

            //chandan
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		//populateMap.put(sq.next(), _UserVO.getHospitalCode());

		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
	//	populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), _UserVO.getUserSeatId());
	
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstlocationName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstlocationName;
	}


	public String checkModifyDuplicateLabNoConfigWithoutAreaWise(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="";
		
		
		
		
		queryKey="CHECKDUPLICATE.HIVT_LABNO_CONF_MST_WITHOUT_AREA_WISE";	
		
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
			populateMAP.put(sq.next(), labnoconfig_VO.getPatientType());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getTestCode());
			//if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
			//populateMAP.put(sq.next(), labnoconfig_VO.getSampleAcceptanceAreaCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	
	public String checkModifyDuplicateLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="";
		
		
		
		if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
		queryKey="CHECKDUPLICATE.HIVT_LABNO_CONF_MST_AREA_WISE";	
		
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
			populateMAP.put(sq.next(), labnoconfig_VO.getPatientType());
			populateMAP.put(sq.next(), labnoconfig_VO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labnoconfig_VO.getTestCode());
		if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
		populateMAP.put(sq.next(), labnoconfig_VO.getSampleAcceptanceAreaCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}

}
