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
import new_investigation.vo.ItemLabTestMappingMstVO;
import new_investigation.vo.LabConsumableMstVO;
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

public class ItemLabTestMappingMstDAO extends DataAccessObject {

	public ItemLabTestMappingMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	public Map fetchDetailsOfItems(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO userVO  )
	{
		
		Connection conn=super.getTransactionContext().getConnection();
		ResultSet rs=null;
		Sequence sq=new Sequence();
		String queryKey="SELECT.COMPLETE.HIVT_ITEM_USES_DTL";
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
		//hbnum_other_item_code=? and hbnum_slno=? and gnum_hospital_code=? and hbstr_lot_no=? and hivnum_laboratory_code =? and hivnum_test_code=? 
		populateMap.put(sq.next(), itemLabTestMappingMstVO.getOtherItemID());
		populateMap.put(sq.next(), itemLabTestMappingMstVO.getSlno());
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), itemLabTestMappingMstVO.getLotNo());
		
		
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
					HelperMethods.populateVOfrmRS(itemLabTestMappingMstVO ,rs);
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
	
	
	
	
	
	public void updateDetails(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="MODIFY.HIVT_ITEM_USES_DTL.ALL";
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
			
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemID());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemIdFromStore());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getEntry());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getLotNo());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getManufacture());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getExpiryDate());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getUsageStartDate());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getUsageEndDate());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getStoreID());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getUsage());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getRemarks());
			//populateMAP.put(sq.next(),itemLabTestMappingMstVO.getBatchNo());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemName());
			/*populateMAP.put(sq.next(),itemLabTestMappingMstVO.getStoreName());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getStoreItemName());*/
			
			//primary keys
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getTempOtherID());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getSlno());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getTempLotNo());
			
			
			//hbnum_other_item_code=? and hbnum_slno=? and gnum_hospital_code=? and hbstr_lot_no=? and hivnum_laboratory_code =? and hivnum_test_code=?
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ItemLabTestMappingMstDAO.populateMAP::" + e);
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
	@SuppressWarnings("unchecked")
	public void saveDetails(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="INSERT.HIVT_ITEM_USES_DTL.ALL";
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
			/*populateMAP.put(sq.next(),itemLabTestMappingMstVO.getLabCode());
			
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getTestCode());*/
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemType());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemIdFromStore().split("#")[0]);
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getEntry());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getLotNo());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getManufacture());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getExpiryDate());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getUsageStartDate());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getUsageEndDate());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getStoreID());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getUsage());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getRemarks());
	//		populateMAP.put(sq.next(),itemLabTestMappingMstVO.getBatchNo());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemName());
			/*populateMAP.put(sq.next(),itemLabTestMappingMstVO.getStoreName());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getStoreItemName());*/
			
			//primary keys
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemID());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getItemID());
			populateMAP.put(sq.next(),itemLabTestMappingMstVO.getLotNo());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
			//hbnum_other_item_code=? and hbnum_slno=? and gnum_hospital_code=? and hbstr_lot_no=? and hivnum_laboratory_code =? and hivnum_test_code=?
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ItemLabTestMappingMstDAO.populateMAP::" + e);
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
	public List getOtherItemList(UserVO userVo)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List otherItemList=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.OTHER_ID.OTHER_ITEM_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
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
				otherItemList=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return otherItemList;
		
	}
	public Map getLotDuplicacy(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO userVo)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List otherItemList=new ArrayList();
		int count=0;
		Map mp=new HashMap();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LOT_COUNT.HIVT_ITEM_USES_DTL";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),itemLabTestMappingMstVO.getLotNo());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVo.getHospitalCode());
		
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
					HelperMethods.populateVOfrmRS(itemLabTestMappingMstVO ,rs);
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
		return mp;
		
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
	public List getstorenameCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_ITEMNAME;
		String queryKey="SELECT.STORENAME_COMBO.HSTT_STORE_MST";
		
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
			populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_UserVO.getHospitalCode());       

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
	
	public List getItemsCombo(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_ITEMNAME;
		String queryKey="SELECT.HSTT_ITEM_CURRSTOCK_DTL.ITEM_DETAIL";
		
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
			populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
			populateMap.put(sq.next(),_UserVO.getHospitalCode());
			populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),itemLabTestMappingMstVO.getStoreID());       

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
}
