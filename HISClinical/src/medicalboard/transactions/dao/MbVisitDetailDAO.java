package medicalboard.transactions.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.UserVO;
import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class MbVisitDetailDAO extends DataAccessObject{

	public MbVisitDetailDAO(TransactionContext _tx) {
		super(_tx);
	}

	public void create(MedicalBoardVisitDtlVO visitDetailVO,UserVO _userVO) {
		
		String query="";
		Map populateMAP = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey = "INSERT.HMBT_MEDICAL_VISIT_DTL";
		
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
			populateMapValues(visitDetailVO,_userVO,populateMAP);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.excecuteUpdate" + e);
		}
		
	}
	

	public static void populateMapValues(MedicalBoardVisitDtlVO visitDetailVO,UserVO _userVO,Map populateMAP )
	{
        Sequence sq = new Sequence();
		
        populateMAP.put(sq.next(), visitDetailVO.getReqID());
        populateMAP.put(sq.next(), visitDetailVO.getReqID());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), visitDetailVO.getPatCrNo());
		populateMAP.put(sq.next(), visitDetailVO.getEpisodeCode());
		populateMAP.put(sq.next(), visitDetailVO.getVisitNo());
		populateMAP.put(sq.next(), visitDetailVO.getReason());
		//populateMAP.put(sq.next(), visitDetailVO.getVisitDate());
		populateMAP.put(sq.next(), visitDetailVO.getBoardNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), visitDetailVO.getIsReferred());
		populateMAP.put(sq.next(), visitDetailVO.getIsInvestigationRaised());

	}
	
	
	public void update(MedicalBoardVisitDtlVO visitDetailVO,UserVO _userVO) {
		
		String query="";
		Map populateMAP = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey = "UPDATE.HMBT_MEDICAL_VISIT_DTL";
		
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
			Sequence seq=new Sequence();
			populateMAP.put(seq.next(), visitDetailVO.getIsReferred());
			populateMAP.put(seq.next(), visitDetailVO.getReqID());
			populateMAP.put(seq.next(), visitDetailVO.getVisitNo());
			populateMAP.put(seq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(seq.next(), _userVO.getHospitalCode());
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.excecuteUpdate" + e);
		}
		
	}
	
	public void updateIsInvRaised(MedicalBoardVisitDtlVO visitDetailVO,UserVO _userVO) {
		
		String query="";
		Map populateMAP = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey = "UPDATE.HMBNUM_IS_INV_RAISED.HMBT_MEDICAL_VISIT_DTL";
		
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
			Sequence seq=new Sequence();
			populateMAP.put(seq.next(), visitDetailVO.getIsInvestigationRaised());
			populateMAP.put(seq.next(), visitDetailVO.getReqID());
			populateMAP.put(seq.next(), visitDetailVO.getVisitNo());
			populateMAP.put(seq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(seq.next(), _userVO.getHospitalCode());
			
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.excecuteUpdate" + e);
		}
		
	}
	
	
}
