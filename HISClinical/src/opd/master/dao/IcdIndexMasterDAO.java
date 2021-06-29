package opd.master.dao;

import inpatient.InpatientConfig;

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
import hisglobal.vo.AbortionMethodMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdIndexMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

//import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class IcdIndexMasterDAO extends DataAccessObject implements IIcdIndexMasterDAOi
{

	Logger log;
	
	public IcdIndexMasterDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	
	
	public boolean chkDuplicateIcd(IcdIndexMasterVO vo, UserVO userVO, String strInsertUpdate)
	{
		boolean flag=false;
		String strQuery =  "" ;
	   	Map mapPopulateMap =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	
	    String strFilename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String strQueryKey;
	   	
	   	try
        {
	   		if(strInsertUpdate.equals("insert"))
	   		{
		   		strQueryKey="SELECT.INSERT.CHECK.DUPLICATE.RECORD.HGBT_ICD_INDEX_MST";	
		   		
	        	strQuery =HelperMethodsDAO.getQuery(strFilename,strQueryKey);
	        	
	        	mapPopulateMap.put(sq.next(), vo.getDiagnosticTerm());
	        	mapPopulateMap.put(sq.next(), userVO.getHospitalCode());
	   		}
	   		else if(strInsertUpdate.equals("update"))
	   		{
	   			strQueryKey="SELECT.UPDATE.CHECK.DUPLICATE.RECORD.HGBT_ICD_INDEX_MST";	
	        	strQuery =HelperMethodsDAO.getQuery(strFilename,strQueryKey);
	        	
	        	mapPopulateMap.put(sq.next(), vo.getIndexCode());
	        	//mapPopulateMap.put(sq.next(), vo.getSlNo());
	        	//mapPopulateMap.put(sq.next(), userVO.getHospitalCode());

	        	mapPopulateMap.put(sq.next(), vo.getDiagnosticTerm());
	        	mapPopulateMap.put(sq.next(), userVO.getHospitalCode());
   		
	   		}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting strQuery out of property file"+e);
        }
        log.info(strQuery);
        
    
       try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),strQuery,mapPopulateMap);
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
	 * To save Record on Add Page
	 */
	
	public void saveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{
		String strQuery  = "";

		String strFileName=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey="INSERT.RECORD.HGBT_ICD_INDEX_MST";
        Sequence sq=new Sequence();
		HashMap<Integer,Object> mapPopulateMAP=new HashMap<Integer,Object>();
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
        	
        	mapPopulateMAP.put(sq.next(),userVO.getHospitalCode());
        	mapPopulateMAP.put(sq.next(),vo.getDiagnosticTerm());
        	mapPopulateMAP.put(sq.next(),vo.getHospitalSynonym());
        	mapPopulateMAP.put(sq.next(), (( vo.getDiseaseCode()!=null && vo.getDiseaseCode().equals("-1")) ? null: vo.getDiseaseCode() ) );
        	mapPopulateMAP.put(sq.next(),userVO.getSeatId());       	
        	mapPopulateMAP.put(sq.next(), (( vo.getStrDualDiseaseCode()!=null && vo.getStrDualDiseaseCode().equals("-1")) ? null: vo.getStrDualDiseaseCode() ) );
        	mapPopulateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	mapPopulateMAP.put(sq.next(),vo.getStrRemark());
        	mapPopulateMAP.put(sq.next(),userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("mapPopulateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),strQuery,mapPopulateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
		finally 
		{
			mapPopulateMAP=null;
		}		
	}
	
	/*
	 * To modify page
	 * 
	 */
	
	public void getModifyRecord(IcdIndexMasterVO vo, UserVO userVO)
	{
		
		String strQuery = "";
		ResultSet rs;
		Map mapPopulateMAP = new HashMap();
		Sequence sq = new Sequence();

		String strFilename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.MODIFY.RECORD.HGBT_ICD_INDEX_MST";
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename,strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting strQuery out of property file" + e);
		}

		try
		{
			mapPopulateMAP.put(sq.next(), vo.getIndexCode());
			mapPopulateMAP.put(sq.next(), vo.getSlNo());
			mapPopulateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mapPopulateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulateMAP);
		
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


	public void modifySave(IcdIndexMasterVO vo, UserVO userVO)
	{
		String strQuery  = "";

		String strFileName=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey="INSERT.MODIFY.RECORD.HGBT_ICD_INDEX_MST";
        Sequence sq=new Sequence();
		HashMap<Integer,Object> mapPopulateMAP=new HashMap<Integer,Object>();
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
        	
        	mapPopulateMAP.put(sq.next(),vo.getIndexCode());
        	mapPopulateMAP.put(sq.next(),vo.getDiagnosticTerm());
        	
        	mapPopulateMAP.put(sq.next(),vo.getIndexCode());
        	mapPopulateMAP.put(sq.next(),userVO.getHospitalCode());

        	mapPopulateMAP.put(sq.next(), vo.getHospitalSynonym());
        	mapPopulateMAP.put(sq.next(), (( vo.getDiseaseCode()!=null && vo.getDiseaseCode().equals("-1")) ? null: vo.getDiseaseCode() ) );
        	mapPopulateMAP.put(sq.next(),userVO.getSeatId());
        	mapPopulateMAP.put(sq.next(), (( vo.getStrDualDiseaseCode()!=null && vo.getStrDualDiseaseCode().equals("-1")) ? null: vo.getStrDualDiseaseCode() ) );
        	mapPopulateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	mapPopulateMAP.put(sq.next(),vo.getStrRemark());
        	mapPopulateMAP.put(sq.next(),userVO.getHospitalCode());

        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("mapPopulateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),strQuery,mapPopulateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
		finally 
		{
			mapPopulateMAP=null;
		}
	}
	
	
	
	/*
	 * To Update the Record 
	 */
	public void modifyUpdate(IcdIndexMasterVO vo, UserVO userVO)
	{
		String strQuery  = "";

		String strFileName=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey="UPDATE.MODIFY.RECORD.HGBT_ICD_INDEX_MST";
        Sequence sq=new Sequence();
		HashMap<Integer,Object> mapPopulateMAP=new HashMap<Integer,Object>();
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
        	mapPopulateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	mapPopulateMAP.put(sq.next(),userVO.getSeatId());
        	mapPopulateMAP.put(sq.next(),vo.getIndexCode());
        	mapPopulateMAP.put(sq.next(),vo.getSlNo());
        	mapPopulateMAP.put(sq.next(),userVO.getHospitalCode());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("mapPopulateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),strQuery,mapPopulateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
		finally 
		{
			mapPopulateMAP=null;
		}
	}

	
	
}
