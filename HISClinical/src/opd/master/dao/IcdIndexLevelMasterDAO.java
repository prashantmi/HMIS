package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
 */
public class IcdIndexLevelMasterDAO extends DataAccessObject implements IcdIndexLevelMasterDAOi
{

	Logger log;
	
	public IcdIndexLevelMasterDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	/*
	 * get Index Term Combo on the list page
	 */
	public List<Entry> getIndexTermCombo(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map  populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.INDEX_TERM.HGBT_ICD_INDEX_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		Sequence sq = new Sequence();

		try
		{
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdIndexLevelMaserDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next()) 
				throw new HisRecordNotFoundException("No Index Term Exists in database  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else 
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> listAllRecord = new ArrayList<Entry>();
		try
		{
			listAllRecord = (List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getIndexTerm" + e);
		}
		return listAllRecord;
	}
	
	
	/*
	 * To get The parent modifier combo on the Add Page
	 */
	
	public List<Entry> getParentModifierCombo(IcdIndexLevelMasterVO icdIndexLevelMasterVO,UserVO _userVO)
	{
		
		ResultSet rs = null;
		String query = "";
		Map  populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PARENT_MODIFIER.HGBT_ICD_INDEX_LEVEL_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), icdIndexLevelMasterVO.getIndexCode());
			populateMAP.put(sq.next(), icdIndexLevelMasterVO.getModifierLevel());
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdIndexLevelMaserDAO.populateMAP::" + e);
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
			else 
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> listAllRecord = new ArrayList<Entry>();
		try
		{
			listAllRecord = (List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getParentModifier" + e);
		}
		return listAllRecord;

	}
	
	/*
	 * Populating the Icd SubgroupCombo on basis of Group  
	 * 
	 * @param icdIndexLevelMasterVO the vo
	 *  @param _userVO User Detail
	 */
	public List<IcdSubgroupMasterVO> getSubgroupsByGroup(String _icdGroupCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SUBGROUP_BY_GROUP.HGBT_ICD_SUBGROUP_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _icdGroupCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
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
		List<IcdSubgroupMasterVO> lstSubGroups = new ArrayList<IcdSubgroupMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdSubgroupMasterVO.class,rs);
				for(ValueObject v :vo)
					lstSubGroups.add((IcdSubgroupMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getSubgroupsByGroup" + e);
		}
		return lstSubGroups;
	}
	
	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DISEASES_BY_SUBGROUP.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), _icdSubgroupCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
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
		List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDisease.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getSubgroupsByGroup" + e);
		}
		return lstDisease;
	}
	
	/*
	 * To Check the duplicacy in case of Add and Modify
	 */
	public boolean chkDuplicate(IcdIndexLevelMasterVO vo, UserVO userVO, String strInsertUpdate)
	{
		boolean flag=false;
		String strQuery =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String strQueryKey;
	   	
	   	try
        {
	   		if(strInsertUpdate.equals("insert"))
	   		{
		   		strQueryKey="SELECT.INSERT.CHECK.DUPLICATE.HGBT_ICD_INDEX_LEVEL_MST";	
		   		
	        	strQuery =HelperMethodsDAO.getQuery(filename,strQueryKey);
	        	
	        	populateMAP.put(sq.next(), vo.getIndexCode());
	        	populateMAP.put(sq.next(), vo.getModifierLevel());
	        	populateMAP.put(sq.next(), vo.getModifier());
	        	populateMAP.put(sq.next(), vo.getParentIndexModifierCode());
				populateMAP.put(sq.next(), userVO.getHospitalCode());
	   		}
	   		else if(strInsertUpdate.equals("update"))
	   		{
	   			strQueryKey="SELECT.UPDATE.CHECK.DUPLICATE.HGBT_ICD_INDEX_LEVEL_MST";	
	        	strQuery =HelperMethodsDAO.getQuery(filename,strQueryKey);
	        	
				populateMAP.put(sq.next(), vo.getIndexModifierID());
				populateMAP.put(sq.next(), vo.getSlNo());
				populateMAP.put(sq.next(), userVO.getHospitalCode());

	        	populateMAP.put(sq.next(), vo.getIndexCode());
	        	populateMAP.put(sq.next(), vo.getModifierLevel());
	        	populateMAP.put(sq.next(), vo.getModifier());
	        	populateMAP.put(sq.next(), vo.getParentIndexModifierCode());
				populateMAP.put(sq.next(), userVO.getHospitalCode());
   		
	   		}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting strQuery out of property file"+e);
        }
        log.info(strQuery);
        
    
       try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),strQuery,populateMAP);
        	rs.next();
        	if(rs.getInt(1)==0)
        	{
        		flag=true;
        	}
        	else
        	{
        		flag=false;
        	}
            
            return flag;
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

	
	/*
	 * To save Data on Add Page
	 */
	public void saveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		String strQuery  = "";

		String strFileName=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey="INSERT.RECORD.HGBT_ICD_INDEX_LEVEL_MST";
        Sequence sq=new Sequence();
		HashMap<Integer,Object> populateMAP=new HashMap<Integer,Object>();
		ResultSet rs = null;
		
		try
		{
			strQuery =HelperMethodsDAO.getQuery(strFileName,strQueryKey);
		}	
			
	  catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	
        	populateMAP.put(sq.next(),vo.getIndexCode());

        	populateMAP.put(sq.next(),vo.getIndexCode());
        	populateMAP.put(sq.next(),vo.getModifierLevel());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());

        	populateMAP.put(sq.next(), ( vo.getParentIndexModifierCode().equals("-1")) ? null : vo.getParentIndexModifierCode() );
        	populateMAP.put(sq.next(),vo.getModifier());
        	populateMAP.put(sq.next(),vo.getModifierLevel());
        	populateMAP.put(sq.next(), ( ( vo.getDiseaseCode().equals("-1")) ? null: vo.getDiseaseCode() ) );
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),vo.getIsWith());        	
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), ( (vo.getDualDiseaseCode().equals("-1")) ? null : vo.getDualDiseaseCode() ) );
        	populateMAP.put(sq.next(),vo.getRemark());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());

        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),strQuery,populateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
		finally 
		{
			populateMAP=null;
		}		
	}
	
	/*
	 * To modify page
	 */
	public void getModifyRecord(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		
		String strQuery = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.MODIFY.HGBT_ICD_INDEX_LEVEL_MST";
		try
		{
			strQuery = HelperMethodsDAO.getQuery(filename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting strQuery out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), vo.getIndexModifierID());
			populateMAP.put(sq.next(), vo.getSlNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, populateMAP);
		
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
				
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	/*
	 * 
	 */
	public void modifyUpdate(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		String strQuery  = "";

		String strFileName=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey="UPDATE.MODIFY.HGBT_ICD_INDEX_LEVEL_MST";
        Sequence sq=new Sequence();
		HashMap<Integer,Object> populateMAP=new HashMap<Integer,Object>();
		ResultSet rs = null;
		
		try
		{
			strQuery =HelperMethodsDAO.getQuery(strFileName,strQueryKey);
		}	
			
	  catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),vo.getIndexModifierID());
        	populateMAP.put(sq.next(),vo.getSlNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),strQuery,populateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
		finally 
		{
			populateMAP=null;
		}
	}

	
	public void modifySave(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		String strQuery  = "";

		String strFileName=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey="INSERT.MODIFY.HGBT_ICD_INDEX_LEVEL_MST";
        Sequence sq=new Sequence();
		HashMap<Integer,Object> populateMAP=new HashMap<Integer,Object>();
		ResultSet rs = null;
		
		try
		{
			strQuery =HelperMethodsDAO.getQuery(strFileName,strQueryKey);
		}	
			
	  catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	
        	populateMAP.put(sq.next(),vo.getIndexCode());

        	populateMAP.put(sq.next(),vo.getIndexModifierID());
        	
        	populateMAP.put(sq.next(),vo.getIndexModifierID());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());

        	populateMAP.put(sq.next(), vo.getParentIndexModifierCode());
        	populateMAP.put(sq.next(),vo.getModifier());
        	populateMAP.put(sq.next(),vo.getModifierLevel());
        	populateMAP.put(sq.next(), ( ( vo.getDiseaseCode().equals("-1")) ? null: vo.getDiseaseCode() ) );
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),vo.getIsWith());        	
        	populateMAP.put(sq.next(),vo.getIsValid());
        	populateMAP.put(sq.next(), ( (vo.getDualDiseaseCode().equals("-1")) ? null : vo.getDualDiseaseCode() ) );
        	populateMAP.put(sq.next(),vo.getRemark());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());

        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),strQuery,populateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
		finally 
		{
			populateMAP=null;
		}
	}

	/*
	 * To View Page
	 * 
	 */
	public void getViewRecord(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		String strQuery = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.VIEW.HGBT_ICD_INDEX_LEVEL_MST";
		try
		{
			strQuery = HelperMethodsDAO.getQuery(filename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting strQuery out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), vo.getIndexModifierID());
			populateMAP.put(sq.next(), vo.getSlNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, populateMAP);
		
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
				
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}


}
