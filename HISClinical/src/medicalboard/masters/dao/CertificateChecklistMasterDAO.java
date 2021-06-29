package medicalboard.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MBCertificateChecklistVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;


public class CertificateChecklistMasterDAO extends DataAccessObject implements CertificateChecklistMasterDAOi{

	public CertificateChecklistMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void insert(MBCertificateChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_CERTIFICATE_CHECKLIST_MST";
		
		
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
	        	populateMAP.put(sq.next(),checklistVO.getCertificateTypeID());
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),checklistVO.getCertificateTypeID());
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),checklistVO.getChecklistID());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),checklistVO.getIsCompulsory());
	        	populateMAP.put(sq.next(),userVO.getSeatId());
       	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("populateMAP::"+e);
	        }
	        try
	        {
	        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	throw new HisDataAccessException("HisDataAccessException While ADDING");
	        }
	}
	
	public void update(MBCertificateChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey="UPDATE.HMBT_CERTIFICATE_CHECKLIST_MST";
		
		
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
			populateMAP.put(sq.next(),checklistVO.getCertificateTypeID());
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),checklistVO.getChecklistID());
			populateMAP.put(sq.next(),checklistVO.getCertificateTypeID());
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),checklistVO.getChecklistID());
						
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}
	

}//end class
