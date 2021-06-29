package opd.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DepartmentHosDisMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DepartmentHosDisMasterDAO extends DataAccessObject {
		Logger log;
		
		public DepartmentHosDisMasterDAO(TransactionContext _tx) {
			super(_tx);	
			log=LogManager.getLogger(this.getClass());
		}
		
	/**Function used to save the record
	 * @param _deptHosDisVO
	 * @param _userVO
	 */
	public void save(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_DEPT_HOSDIS_MST";

		_deptHosDisVO.setIsValid(Config.IS_VALID_ACTIVE);
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentCode());
        	populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentUnitCode());
        	populateMAP.put(sq.next(),_deptHosDisVO.getHospitalDiseaseCode());
        	populateMAP.put(sq.next(),_userVO.getSeatId());
        	populateMAP.put(sq.next(),_deptHosDisVO.getIsValid());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	
        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"DepartmentHosDisMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("Exception While ADDING");
		}

	}
	
	/**Function used to check whether the specified record which wants to add
	 * already exist in the database
	 * @param _deptHosDisVO
	 * @param _userVO
	 * @return
	 */
	public boolean exist(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO) {

		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.EXIST.HGBT_DEPT_HOSDISEASE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), _deptHosDisVO.getHospitalDiseaseCode());
			populateMAP.put(sq.next(), _deptHosDisVO.getDepartmentCode());
			populateMAP.put(sq.next(), _deptHosDisVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"DepartmentHosDisMasterDAO.populateMAP::" + e);
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

		boolean flag=false;
		try
		{
			if(!rs.next())	return flag=false;
			rs.first();
			if(rs.getInt(1)>0)
				flag=true;
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return flag;
	}

	/**Function used to update that record and sets its field Gnum_IsValid equals to 1
	 * in the database
	 * @param _deptHosDisVO
	 * @param _userVO
	 */
	public void updateActive(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.ACTIVE_RECORD.HGBT_DEPT_HOSDISEASE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(),_deptHosDisVO.getHospitalDiseaseCode());
			populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentCode());
			populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"DepartmentHosDisMasterDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("Excetion While Updating");
		}

	}	
	
	/**Getting the Hospital Diseases List Department wise
	 * @param _deptHosDisVO
	 * @param _userVO
	 * @return
	 */
	public DepartmentHosDisMasterVO[] getHosDisDeatilsDeptWise(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO){
		DepartmentHosDisMasterVO deptHosDisVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.DEPTWISE.HGBT_DEPT_HOSDIS_MST";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
       
    	 try{
         	query =HelperMethodsDAO.getQuery(filename,queryKey);
         	
         }
         catch(Exception e){
         	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
         }
         
         populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentCode());
     	 populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
     	 populateMAP.put(sq.next(),_userVO.getHospitalCode());
     	
     	
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next()){
 	 	    	throw new HisRecordNotFoundException("No Hospital Disease found.");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo= HelperMethods.populateVOfrmRS(DepartmentHosDisMasterVO.class,rs);
 	 	  deptHosDisVO= new  DepartmentHosDisMasterVO[vo.length];
 	 	  for(int i=0;i<vo.length;i++)
 	 	  {
 	 		deptHosDisVO[i]=(DepartmentHosDisMasterVO)vo[i];
 	 	  }
 			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
         
 		return deptHosDisVO;
 		}
	
	/**Getting the hospital diseases List Unit wise
	 * @param _deptHosDisVO
	 * @param _userVO
	 * @return
	 */
	public DepartmentHosDisMasterVO[] getHosDisDeatilsUnitWise(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO){
		DepartmentHosDisMasterVO deptHosDisVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.UNITWISE.HGBT_DEPT_HOSDIS_MST";
        
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        
    	populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentUnitCode());
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	
    	try{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Hospital Disease found.");	 	    
	 	    }
	 	    rs.beforeFirst();
	 	   
	 	    vo= HelperMethods.populateVOfrmRS(DepartmentHosDisMasterVO.class,rs);
	 	   deptHosDisVO= new  DepartmentHosDisMasterVO[vo.length];
	 	  for(int i=0;i<vo.length;i++)
	 	  {
	 		 deptHosDisVO[i]=(DepartmentHosDisMasterVO)vo[i];
	 	  }
			
	 	  }
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("Application Execution Exception"+e);			 
		 }			 
        
		return deptHosDisVO;
		}

	//Function used for deleting the Hospital Diseases
	public void deleteByDiseaseCode(DepartmentHosDisMasterVO _deptHosDisVO,String _diseaseCode,UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE.BY.DISEASE.HGBT_DEPT_HOSDIS_MST";
	    
	    
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    log.info(query);
	    
	    
	    try{
	    	populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentCode());
	    	populateMAP.put(sq.next(),_deptHosDisVO.getDepartmentUnitCode());
	    	populateMAP.put(sq.next(),_diseaseCode);
	    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DepartmentHosDisMasterDAO.populateMAP::"+e);
	    }
	    try{
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("Excetion While Updating");
	    }
	    
}

		
	public DepartmentHosDisMasterVO[] getDetailsByDiseaseCode(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO){
		DepartmentHosDisMasterVO deptHosDisVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.BY.DISEASECODE.HGBT_HOSDIS_MST";
          
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
            }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
               
    	populateMAP.put(sq.next(),_deptHosDisVO.getHospitalDiseaseCode());
    	    	
    	try{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Hospital Disease Code Found.");	 	    
	 	    }
	 	    rs.beforeFirst();
	 	  
	 	    vo= HelperMethods.populateVOfrmRS(DepartmentHosDisMasterVO.class,rs);
	 	   deptHosDisVO= new  DepartmentHosDisMasterVO[vo.length];
	 	  for(int i=0;i<vo.length;i++)
	 	  {
	 		 deptHosDisVO[i]=(DepartmentHosDisMasterVO)vo[i];
	 	  }
		 }
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("Application Execution Exception"+e);			 
		 }			 
        
		return deptHosDisVO;
		}

}
