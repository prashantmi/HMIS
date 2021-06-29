package opd.master.dao;

import java.util.HashMap;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.OpdPatientParameterVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class OPDPatientParameterDetailDAO extends DataAccessObject 
{
	Logger log;
	
	public OPDPatientParameterDetailDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public void create(OpdPatientParameterVO _PatParaVO, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HOPT_PATIENT_PARA_DTL";

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
    		populateMAP.put(sq.next(),_PatParaVO.getCrNo());
    		populateMAP.put(sq.next(),_PatParaVO.getTemplateId());
    		populateMAP.put(sq.next(),_PatParaVO.getParaId());
    		populateMAP.put(sq.next(),_PatParaVO.getParaValue());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("OPDPatientParameterDetailDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
}