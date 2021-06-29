package opd.master.dao;

/**
 * @author  CDAC
 */

import java.util.HashMap;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.OpdTemplateParameterVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class OPDTemplateParameterMasterDAO extends DataAccessObject 
{
	Logger log;
	
	public OPDTemplateParameterMasterDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	//* Inserting Template Parameter Record
	public void create(OpdTemplateParameterVO _TmplParaVO, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HGBT_TEMPLATE_PARA_MST";

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
    		populateMAP.put(sq.next(),_TmplParaVO.getTemplateId());
    		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
    		populateMAP.put(sq.next(),_TmplParaVO.getParaId());
    		
    		populateMAP.put(sq.next(),_TmplParaVO.getParentParaId());
    		populateMAP.put(sq.next(),_TmplParaVO.getLocationId());
    		populateMAP.put(sq.next(),_TmplParaVO.getRowId());
    		populateMAP.put(sq.next(),_TmplParaVO.getColumnId());
    		populateMAP.put(sq.next(),_TmplParaVO.getValueObjId());
    		
    		populateMAP.put(sq.next(),_TmplParaVO.getValueObjProp());
    		populateMAP.put(sq.next(),_TmplParaVO.getParaValue());
    		populateMAP.put(sq.next(),_TmplParaVO.getSourceFlag());
    		populateMAP.put(sq.next(),_TmplParaVO.getTableQuery());
    		populateMAP.put(sq.next(),_TmplParaVO.getColspan());

    		populateMAP.put(sq.next(),_TmplParaVO.getIsCompulsory());
    		populateMAP.put(sq.next(),_TmplParaVO.getIsRange());
    		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    		populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::"+e);
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

	//* Deleting Template Parameter Record
	public void delete(String _tempId, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.BY_TEMP_ID.HGBT_TEMPLATE_PARA_MST";

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
    		populateMAP.put(sq.next(),_tempId);
    		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
    		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::"+e);
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
}