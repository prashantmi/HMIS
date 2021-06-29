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
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;
import new_investigation.vo.LabCollectionAreaMasterVO;

public class LabCollectionAreaMstDAO extends DataAccessObject implements LabCollectionAreaMstDAOi
{

	public LabCollectionAreaMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}




	public void createLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_LAB_COLLECTION_AREA_MST";
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
			populateMAP.put(sq.next(), labcollectionarea_VO.getCollectionareaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcollectionarea_VO.getLabCode());
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



	public void fetchCheckListLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LAB_COLLECTION_AREA_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), labcollectionarea_VO.getCollectionareaCode());
		populateMap.put(sq.next(), labcollectionarea_VO.getHospitalCode());
		populateMap.put(sq.next(), labcollectionarea_VO.getLabCode());
		


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
					HelperMethods.populateVOfrmRS(labcollectionarea_VO ,rs);
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
	public void deleteLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="DELETE_VALID.HIVT_LAB_COLLECTION_AREA_MST";
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
			//populateMAP.put(sq.next(), _UserVO.getSeatId());



		
			populateMAP.put(sq.next(), _UserVO.getSeatId());

			populateMAP.put(sq.next(), labcollectionarea_VO.getCollectionareaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcollectionarea_VO.getLabCode());

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



	/// check for duplicate Container Name
	public String checkPrimaryKeyLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKPRIMARYKEY.HIVT_LAB_COLLECTION_AREA_MST";
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
			populateMAP.put(sq.next(), labcollectionarea_VO.getCollectionareaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcollectionarea_VO.getLabCode());   
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


	/// check for duplicate Container Name on Modify
	public void updateValidLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE_VALID.HIVT_LAB_COLLECTION_AREA_MST";
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
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE); 
			populateMAP.put(sq.next(), _UserVO.getSeatId());

			populateMAP.put(sq.next(), labcollectionarea_VO.getCollectionareaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcollectionarea_VO.getLabCode());
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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// For lab combo

	public List getlabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlabName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_LAB_COLLECTION_AREA_MST";

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
				lstlabName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstlabName;
	}

	public List getareaCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstareaName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.AREA_COMBO.HIVT_LAB_COLLECTION_AREA_MST";

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
				lstareaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstareaName;
	}

	public List getselectedareaCombo(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstareaName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.SELECTED_AREA_COMBO.HIVT_LAB_COLLECTION_AREA_MST";

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
				lstareaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstareaName;
	}
	
	public List getnewareaComboLeft(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstareaName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_AREA_COMBO.HIVT_LAB_COLLECTION_AREA_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), labcollectionarea_VO.getLabCode());
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
				lstareaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstareaName;
	}
	
	
	public List getselectedareaComboRight(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstareaName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_SELECTED_AREA_COMBO.HIVT_LAB_COLLECTION_AREA_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), labcollectionarea_VO.getLabCode());


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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstareaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstareaName;
	}


}
