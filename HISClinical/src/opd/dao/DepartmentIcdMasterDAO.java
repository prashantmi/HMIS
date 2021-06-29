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
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DepartmentIcdMasterDAO extends DataAccessObject {
		Logger log;
		
		public DepartmentIcdMasterDAO(TransactionContext _tx) {
			super(_tx);	
			log=LogManager.getLogger(this.getClass());
		}
		
	public void create(DepartmentIcdMasterVO _deptIcdMasterVO, UserVO _userVO){
			
			String query  = "";
	        Map populateMAP =new HashMap();
	        Sequence sq=new Sequence();
	        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	        String queryKey="INSERT.HGBT_DEPT_ICD_MST";
	        
	        _deptIcdMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
	        try{
	        	query =HelperMethodsDAO.getQuery(filename,queryKey);
	        	
	        }
	        catch(Exception e){
	        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	        }
	        log.info(query);
	        
	        
	        try{
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentCode());
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentUnitCode());
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDiseaseCode());
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getIcdGroupCode());
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getIcdSubgroupCode());
	        	populateMAP.put(sq.next(),_userVO.getSeatId());
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getIsValid());
	        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	        	
	        }
	        catch(Exception e){
	        	throw new HisApplicationExecutionException("DepartmentIcdMasterDAO.populateMAP::"+e);
	        }
	        try{
	        	System.out.println("---------------------================================================================"+_deptIcdMasterVO.getDepartmentCode());
	        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        	throw new HisDataAccessException("Excetion While ADDING");
	        }
	        
		}
	
	public DepartmentIcdMasterVO[] getIcdDeatilsDeptWise(DepartmentIcdMasterVO _deptIcdMasterVO, UserVO _userVO){
		DepartmentIcdMasterVO deptIcdVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.DEPTWISE.HGBT_DEPT_ICD_MST";
        
        
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
         
         populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentCode());
     	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
     	populateMAP.put(sq.next(),_userVO.getHospitalCode());
     	
     	
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next()){
 	 	    	throw new HisRecordNotFoundException("No Icd Code found.");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	    vo= HelperMethods.populateVOfrmRS(DepartmentIcdMasterVO.class,rs);
 	 	   deptIcdVO= new  DepartmentIcdMasterVO[vo.length];
 	 	  for(int i=0;i<vo.length;i++)
 	 	  {
 	 		 deptIcdVO[i]=(DepartmentIcdMasterVO)vo[i];
 	 	  }
 			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
         
 		return deptIcdVO;
 		}
	
	public DepartmentIcdMasterVO[] getIcdDeatilsUnitWise(DepartmentIcdMasterVO _deptIcdMasterVO, UserVO _userVO){
		DepartmentIcdMasterVO deptIcdVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.UNITWISE.HGBT_DEPT_ICD_MST";
        
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        
    	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentUnitCode());
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	
    	try{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Icd Code found.");	 	    
	 	    }
	 	    rs.beforeFirst();
	 	   
	 	    vo= HelperMethods.populateVOfrmRS(DepartmentIcdMasterVO.class,rs);
	 	   deptIcdVO= new  DepartmentIcdMasterVO[vo.length];
	 	  for(int i=0;i<vo.length;i++)
	 	  {
	 		 deptIcdVO[i]=(DepartmentIcdMasterVO)vo[i];
	 	  }
			
	 	  }
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("Application Execution Exception"+e);			 
		 }			 
        
		return deptIcdVO;
		}

	public void deleteByGroupCode(DepartmentIcdMasterVO _deptIcdMasterVO, String _groupCode,UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.BY.GROUP.HGBT_DEPT_ICD_MST";

        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        
        try{
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentCode());
        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentUnitCode());
        	populateMAP.put(sq.next(),_groupCode);
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("DepartmentIcdMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("Exception While Updating");
        }
        
	}
	
	public void deleteBySubGroupCode(DepartmentIcdMasterVO _deptIcdMasterVO, String _subGroupCode,UserVO _userVO){
		
			String query  = "";
	        Map populateMAP =new HashMap();
	        Sequence sq=new Sequence();
	        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	        String queryKey="DELETE.BY.SUBGROUP.HGBT_DEPT_ICD_MST";
	        
	        
	        try{
	        	query =HelperMethodsDAO.getQuery(filename,queryKey);
	        	
	        }
	        catch(Exception e){
	        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	        }
	        log.info(query);
	        
	        
	        try{
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentCode());
	        	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentUnitCode());
	        	populateMAP.put(sq.next(),_subGroupCode);
	        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	        	
	        }
	        catch(Exception e){
	        	throw new HisApplicationExecutionException("DepartmentIcdMasterDAO.populateMAP::"+e);
	        }
	        try{
	        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        	throw new HisDataAccessException("Excetion While Updating");
	        }
        
	}
	
	public void deleteByDiseaseCode(DepartmentIcdMasterVO _deptIcdMasterVO,String _diseaseCode,UserVO _userVO){
		
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE.BY.DISEASE.HGBT_DEPT_ICD_MST";
	    
	    
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    log.info(query);
	    
	    
	    try{
	    	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentCode());
	    	populateMAP.put(sq.next(),_deptIcdMasterVO.getDepartmentUnitCode());
	    	populateMAP.put(sq.next(),_diseaseCode);
	    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("DepartmentIcdMasterDAO.populateMAP::"+e);
	    }
	    try{
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("Excetion While Updating");
	    }
	    
}

	public DepartmentIcdMasterVO[] getDetailsByGroupCode(DepartmentIcdMasterVO _deptIcdMasterVO, UserVO _userVO){
		DepartmentIcdMasterVO deptIcdVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.BY.GROUPCODE.HGBT_ICD_DISEASE_MST";
          
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
            }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
               
    	populateMAP.put(sq.next(),_deptIcdMasterVO.getIcdGroupCode());
    	    	
    	try{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Icd Code found.");	 	    
	 	    }
	 	    rs.beforeFirst();
	 	  
	 	    vo= HelperMethods.populateVOfrmRS(DepartmentIcdMasterVO.class,rs);
	 	   deptIcdVO= new  DepartmentIcdMasterVO[vo.length];
	 	  for(int i=0;i<vo.length;i++)
	 	  {
	 		 deptIcdVO[i]=(DepartmentIcdMasterVO)vo[i];
	 	  }
		 }
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("Application Execution Exception"+e);			 
		 }			 
        
		return deptIcdVO;
		}
	
	public DepartmentIcdMasterVO[] getDetailsBySubGroupCode(DepartmentIcdMasterVO _deptIcdMasterVO, UserVO _userVO){
		DepartmentIcdMasterVO deptIcdVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.BY.SUBGROUPCODE.HGBT_ICD_DISEASE_MST";
          
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
            }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
               
    	populateMAP.put(sq.next(),_deptIcdMasterVO.getIcdSubgroupCode());
    	    	
    	try{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Icd Code found.");	 	    
	 	    }
	 	    rs.beforeFirst();
	 	  
	 	    vo= HelperMethods.populateVOfrmRS(DepartmentIcdMasterVO.class,rs);
	 	   deptIcdVO= new  DepartmentIcdMasterVO[vo.length];
	 	  for(int i=0;i<vo.length;i++)
	 	  {
	 		 deptIcdVO[i]=(DepartmentIcdMasterVO)vo[i];
	 	  }
		 }
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("Application Execution Exception"+e);			 
		 }			 
        
		return deptIcdVO;
		}

	
	public DepartmentIcdMasterVO[] getDetailsByDiseaseCode(DepartmentIcdMasterVO _deptIcdMasterVO, UserVO _userVO){
		DepartmentIcdMasterVO deptIcdVO[]= null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.BY.DISEASECODE.HGBT_ICD_DISEASE_MST";
          
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
            }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
               
    	populateMAP.put(sq.next(),_deptIcdMasterVO.getDiseaseCode());
    	    	
    	try{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException("No Icd Code found.");	 	    
	 	    }
	 	    rs.beforeFirst();
	 	  
	 	   vo= HelperMethods.populateVOfrmRS(DepartmentIcdMasterVO.class,rs);
	 	   deptIcdVO= new  DepartmentIcdMasterVO[vo.length];
	 	  for(int i=0;i<vo.length;i++)
	 	  {
	 		 deptIcdVO[i]=(DepartmentIcdMasterVO)vo[i];
	 	  }
		 }
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("Application Execution Exception"+e);			 
		 }			 
        
		return deptIcdVO;
		}

}
