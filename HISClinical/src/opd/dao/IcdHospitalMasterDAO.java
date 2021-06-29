package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IcdHospitalMasterDAO extends DataAccessObject  {

Logger log;
	
	public IcdHospitalMasterDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
public void updateRecordByhospDiseaseCode(IcdHospitalMasterVO _icdHospitalMasterVO,UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="UPDATE.BY.HOSPDISEASECODE.HGBT_ICD_HOSPITAL_MST";
	    
	    
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    log.info(query);
	    
	    
	    try{
	    	//populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentCode());
	    	//populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentUnitCode());
	    	//populateMAP.put(sq.next(),_diseaseCode);
	    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("IcdGroupMasterDAO.populateMAP::"+e);
	    }
	    try{
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("Excetion While Updating");
	    }
	    
}

}
