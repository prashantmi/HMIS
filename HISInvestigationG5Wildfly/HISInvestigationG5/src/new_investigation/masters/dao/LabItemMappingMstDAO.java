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
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.LabItemMappingMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;

public class LabItemMappingMstDAO extends DataAccessObject 
{

	public LabItemMappingMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}




	



	

	







	
	
	// For lab combo

	public List getlabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlabName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_TEST_GROUP_INFO_MST";

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

	

	
	
	public List getItemsComboRight(LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstRight=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LABITEM_MST.COMBO_RIGHT";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labItemMappingMasterVO.getLabCode());
		populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		
		


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
				lstRight=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstRight;
	}
	
	
	public List getItemsComboLeft(LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstLeft=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LABITEM_MST.COMBO_LEFT";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
		populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labItemMappingMasterVO.getLabCode());
		populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		
		


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
				lstLeft=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstLeft;
	}
	
	


	//check dupliace  Record
	public Integer getCount(String otherItemCode,LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="SELECT.HIVT_LABITEM_MST.DUPLICATE.COUNT";
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
			//hivnum_laboratory_code=? and  hbnum_other_item_code=? and gnum_hospital_code=? and gnum_isvalid=?
			populateMAP.put(sq.next(), labItemMappingMasterVO.getLabCode());
			populateMAP.put(sq.next(), otherItemCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.GN_ISVALID);   
			
			
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
		return Integer.parseInt(record) ;
	}
	public void updateList(String otherItemCode,LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPATE.HIVT_LABITEM_MST.LIST";
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
			//hivnum_laboratory_code=? and  hbnum_other_item_code=? and gnum_hospital_code=? and gnum_isvalid=?
			populateMAP.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labItemMappingMasterVO.getLabCode());
			populateMAP.put(sq.next(), otherItemCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.GN_ISVALID);   
			
			
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
			 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	}
	public void InsertItemInList(String otherItemCode,LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="INSERT.HIVT_LABITEM_MST.LIST";
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
			//gnum_isvalid, hivnum_laboratory_code,hbnum_other_item_code, gnum_hospital_code=? ,gnum_seat_id,gdt_entry_date
			populateMAP.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), labItemMappingMasterVO.getLabCode());
			populateMAP.put(sq.next(), otherItemCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
			 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	}
	public void DelelteListItem(String otherItemCode,LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPATE.HIVT_LABITEM_MST.LIST";
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
			//hivnum_laboratory_code=? and  hbnum_other_item_code=? and gnum_hospital_code=? and gnum_isvalid=?
			populateMAP.put(sq.next(), InvestigationConfig.GN_ISVALID);
			populateMAP.put(sq.next(), labItemMappingMasterVO.getLabCode());
			populateMAP.put(sq.next(), otherItemCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			
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
			 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	}

	
	
}
