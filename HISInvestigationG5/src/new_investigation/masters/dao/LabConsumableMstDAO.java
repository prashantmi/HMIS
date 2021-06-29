package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.a.p;
import new_investigation.InvestigationConfig;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.LabConsumableMstVO;
import new_investigation.vo.LabTestSampleMstVO;
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

public class LabConsumableMstDAO extends DataAccessObject {

	public LabConsumableMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	public Map fetchDetails(LabConsumableMstVO labConsumableMstVO,UserVO userVO  )
	{
		
		Connection conn=super.getTransactionContext().getConnection();
		ResultSet rs=null;
		Sequence sq=new Sequence();
		String queryKey="SELECT.COMPLETE.HIVT_OTHER_ITEM_MST";
		String query="";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		Map populateMap=new HashMap();
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		populateMap.put(sq.next(), labConsumableMstVO.getOtherItemID());
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(), userVO.getSeatId());
		populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE );
		
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
					HelperMethods.populateVOfrmRS(labConsumableMstVO ,rs);
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
		return null;
	}
	public int checkDuplicateForModify(LabConsumableMstVO labConsumableMstVO,UserVO userVO  )
	{
		
		Connection conn=super.getTransactionContext().getConnection();
		ResultSet rs=null;
		Sequence sq=new Sequence();
		String queryKey="SELECT.NAME.HIVT_OTHER_ITEM_MST";
		String query="";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		Map populateMap=new HashMap();
		int count=0;
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		populateMap.put(sq.next(), labConsumableMstVO.getItemName());
		populateMap.put(sq.next(), labConsumableMstVO.getOtherItemID());
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE );
		
		
		
		
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
				rs.next();
				count=rs.getInt(1);
				
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
		return count;
	}
	
	
	
	
	
	
	public void updateDetails(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_OTHER_ITEM_MST";
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
			populateMAP.put(sq.next(), labConsumableMstVO.getDefaultQuantity());
			populateMAP.put(sq.next(), labConsumableMstVO.getItemIdFromStore());
			populateMAP.put(sq.next(), labConsumableMstVO.getItemName());
			populateMAP.put(sq.next(), labConsumableMstVO.getItemType());
			populateMAP.put(sq.next(), labConsumableMstVO.getUomCode());
			populateMAP.put(sq.next(), labConsumableMstVO.getOtherItemID());
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		
			
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
	
	
	public void saveDetails(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="INSERT.HIVT_OTHER_ITEM_MST";
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
			
			populateMAP.put(sq.next(), labConsumableMstVO.getDefaultQuantity());
			populateMAP.put(sq.next(), labConsumableMstVO.getItemIdFromStore());
			populateMAP.put(sq.next(), labConsumableMstVO.getItemName());
			populateMAP.put(sq.next(), labConsumableMstVO.getItemType());
			populateMAP.put(sq.next(), labConsumableMstVO.getUomCode());
			populateMAP.put(sq.next(), labConsumableMstVO.getOtherItemID());
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			
			
			
		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("LABconsumableDAO.populateMAP::" + e);
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
	public void generateKey(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="SELECT.HIVT_OTHER_ITEM_MST.MAX_KEY";
		ResultSet rs=null;
		Connection con=super.getTransactionContext().getConnection();
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
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("LABconsumableDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(con, query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(labConsumableMstVO ,rs);
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
	public List getItemNameCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_ITEMNAME;
		String queryKey="SELECT.ITEMNAME_COMBO.HSTT_ITEM_MST";
		
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);       

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lsttest;
	}
	public List getItemTypeCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_ITEMNAME;
		String queryKey="SELECT.ITEM_TYPE.HIVT_ITEMTYPE_MST";
		
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			    

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lsttest;
	}
	public List getUOMName(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List UOMcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.UOM.Lab_Test_Sample_Global_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				UOMcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return UOMcombo;
	}
	

}
