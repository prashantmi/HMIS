package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class UnitImageDescMasterDAO extends DataAccessObject implements UnitImageDescMasterDAOi
{
	Logger log;
	
	public UnitImageDescMasterDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public void create(UnitImageDescMasterVO unitImageDescMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="INSERT.HOPT_UNIT_IMAGEDESC_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getUnitCode());
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getImageId());
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getColor());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),OpdConfig.SLNO);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UnitImageDescMasterDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	   	
	}
	
	public void modifySave(UnitImageDescMasterVO unitImageDescMasterVO,UserVO userVO)
	{
		String query =  "" ;
	   	Map populateMAP =new HashMap();    	
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="MODIFYSAVE.HOPT_UNIT_IMAGEDESC_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getUnitCode());
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getImageId());
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getColor());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),unitImageDescMasterVO.getUnitCode());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UnitImageDescMasterDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	   	
	}
	
	public List select(String selectedUnit,String imageid,String Slno,UserVO userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.HOPT_UNIT_IMAGEDESC_MST";
	   	
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
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),selectedUnit);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UnitImageDescMasterDAO.populateMAP::"+e);
        }
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	
	public void delete(String unittCode,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="DELETE.HOPT_UNIT_IMAGEDESC_MST";
	   	
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
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),unittCode);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UnitImageDescMasterDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }

	}
	
	// Getting Description List of Colors in String and Format
	public List<ImagePointerMasterVO> getUnitsImageColorDesc(String _unitCode, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Sequence sq = new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.COLOR_DESCS_OF_UNIT.HOPT_UNIT_IMAGEDESC_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageDescMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<ImagePointerMasterVO> lstImagesPointers = new ArrayList<ImagePointerMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ImagePointerMasterVO.class,rs);
				for(ValueObject v :vo)
					lstImagesPointers.add((ImagePointerMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : Getting Unit Image Pointers List" + e);
		}
		return lstImagesPointers;
	}
}
