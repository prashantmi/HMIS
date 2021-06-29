package medicalboard.transactions.dao;

import java.util.HashMap;
import java.util.Map;
import hisglobal.utility.Sequence;
import hisglobal.vo.HandOverDetailVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import medicalboard.MedicalBoardConfig;

public class MbRequisitionDetailDAO extends DataAccessObject implements MbRequisitionDetailDAOi{

	public MbRequisitionDetailDAO(TransactionContext _tx) {
		super(_tx);
	}

	public MedicalBoardRequisitionVO create(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO) {
		
		ResultSet rs=null;
		String queryRequistionNo = "";
		String query="";
		Map populateMAP = new HashMap();
		Map populateMAPRequisitionNo=new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKeyRequsitionNo = "CREATE_REQUISITION_ID";
		String queryKey = "INSERT.HMBT_REQUISITION_DTL";
		String requisitionNo=null;
		try
		{
			queryRequistionNo=HelperMethodsDAO.getQuery(filename, queryKeyRequsitionNo);
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{					
			  Sequence sq=new Sequence();
			  populateMAPRequisitionNo.put(sq.next(),_userVO.getHospitalCode());
		       rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),queryRequistionNo,populateMAPRequisitionNo);
		       if(!rs.next()){
		    	   throw new HisRecordNotFoundException("Requistion Number Not Generated");		 	    	 	    	 
		 	     }
		       else
		       rs.beforeFirst(); 
		       rs.next();
		       requisitionNo=rs.getString(1);
		       mRequisitionVO.setReqID(requisitionNo);
						
			populateMapValues(mRequisitionVO,_userVO,populateMAP);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return mRequisitionVO;
	}
	

	public static void populateMapValues(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO,Map populateMAP )
	{
		String examDate=mRequisitionVO.getExamDate();
        Sequence sq = new Sequence();
		
        populateMAP.put(sq.next(), mRequisitionVO.getReqID());
		populateMAP.put(sq.next(), MedicalBoardConfig.SLNO );
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), mRequisitionVO.getCertificateTypeID());
		populateMAP.put(sq.next(), mRequisitionVO.getPatCrNo());
		populateMAP.put(sq.next(), mRequisitionVO.getRequestFrom());
		populateMAP.put(sq.next(), mRequisitionVO.getDesignation());
		populateMAP.put(sq.next(), mRequisitionVO.getOrgID());
		populateMAP.put(sq.next(), mRequisitionVO.getOrgTypeID());
		populateMAP.put(sq.next(), mRequisitionVO.getOrgName());
		populateMAP.put(sq.next(), mRequisitionVO.getOrgAddress());
		populateMAP.put(sq.next(), mRequisitionVO.getReqStatus());
		populateMAP.put(sq.next(), mRequisitionVO.getExamDate());
		populateMAP.put(sq.next(), mRequisitionVO.getApprovedDate());
		populateMAP.put(sq.next(), mRequisitionVO.getApprovedBy());
		populateMAP.put(sq.next(), mRequisitionVO.getBoardTypeID());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), mRequisitionVO.getCIDNo());
	}
	
	
	public void update(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO) {
		
		String query="";
		Map populateMap = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey = "UPDATE.EXAM_DATE.HMBT_REQUISITION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{					
			Sequence sq=new Sequence();
			
			populateMap.put(sq.next(), mRequisitionVO.getExamDate());
			populateMap.put(sq.next(), mRequisitionVO.getReqID());
			populateMap.put(sq.next(), mRequisitionVO.getSlNo());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			  
		    HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void updateReqStatus(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO) 
	{
		String query="";
		Map populateMap = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey = "UPDATE.REQ_STATUS.HMBT_REQUISITION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{					
			Sequence sq=new Sequence();
			
			populateMap.put(sq.next(), mRequisitionVO.getReqStatus());
			populateMap.put(sq.next(), mRequisitionVO.getBoardNo());
			populateMap.put(sq.next(), mRequisitionVO.getReqID());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	public void updatePostEntryDetail(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO){
		
		String query="";
		Map populateMap = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey = "UPDATE.CERTIFICATE_POST_ENTRY_DTL.HMBT_REQUISITION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{					
			Sequence sq=new Sequence();
			
			populateMap.put(sq.next(), mRequisitionVO.getReqStatus());
			populateMap.put(sq.next(), mRequisitionVO.getApprovedDate());
			populateMap.put(sq.next(), mRequisitionVO.getApprovedBy());
			//populateMap.put(sq.next(), mRequisitionVO.getResult());
			populateMap.put(sq.next(), mRequisitionVO.getCertificateResult());
			//populateMap.put(sq.next(), mRequisitionVO.getOpinion());
			populateMap.put(sq.next(), mRequisitionVO.getFinalRemark());
			populateMap.put(sq.next(), mRequisitionVO.getIsVerified());
			populateMap.put(sq.next(), mRequisitionVO.getOpinionCode());//new add
			populateMap.put(sq.next(), mRequisitionVO.getReqID());
			populateMap.put(sq.next(), mRequisitionVO.getSlNo());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
			System.out.println("updateApprovalDetail() :"+i+" record updated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void updateApprovalDetail(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO)
	{
		String query="";
		Map populateMap = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey = "UPDATE.CERTIFICATE_APPROVAL_DTL.HMBT_REQUISITION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{					
			Sequence sq=new Sequence();
			
			populateMap.put(sq.next(), mRequisitionVO.getReqStatus());
			populateMap.put(sq.next(), mRequisitionVO.getApprovedDate());
			populateMap.put(sq.next(), mRequisitionVO.getApprovedBy());
			//populateMap.put(sq.next(), mRequisitionVO.getResult());
			populateMap.put(sq.next(), mRequisitionVO.getCertificateResult());
			//populateMap.put(sq.next(), mRequisitionVO.getOpinion());
			populateMap.put(sq.next(), mRequisitionVO.getFinalRemark());
			populateMap.put(sq.next(), mRequisitionVO.getIsVerified());
			populateMap.put(sq.next(), mRequisitionVO.getOpinionCode());//new add
			populateMap.put(sq.next(), mRequisitionVO.getReqID());
			populateMap.put(sq.next(), mRequisitionVO.getSlNo());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			
			int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
			System.out.println("updateApprovalDetail() :"+i+" record updated");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public void updateReqStatusHandOver(HandOverDetailVO handOverDetailVO,UserVO _userVO)
	{
		String query="";
		Map populateMap = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey = "UPDATE.REQ_STATUS_HAND_OVER.HMBT_REQUISITION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{					
			Sequence sq=new Sequence();
			
			populateMap.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER);
			populateMap.put(sq.next(), handOverDetailVO.getRequisitionId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
}
