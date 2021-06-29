package medicalboard.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MedicalBoardVerificationDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class MbVerificationDetailDAO extends DataAccessObject implements MbVerificationDetailDAOi
{
	public MbVerificationDetailDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	public void create(MedicalBoardVerificationDtlVO verificationDtlVO, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
//		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="INSERT.HMBT_VERIFICATION_DTL";
		Sequence sq = new Sequence();
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			
			populateMap.put(sq.next(), verificationDtlVO.getReqId());
			
			//sereal number
			populateMap.put(sq.next(), verificationDtlVO.getReqId());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
			populateMap.put(sq.next(), verificationDtlVO.getCertificateResult());
			populateMap.put(sq.next(), verificationDtlVO.getOpinionCode());
			populateMap.put(sq.next(), verificationDtlVO.getOpinion());
			populateMap.put(sq.next(), verificationDtlVO.getVerifyEmpId());
			populateMap.put(sq.next(), verificationDtlVO.getVerifyDate());
			populateMap.put(sq.next(), verificationDtlVO.getRemarks());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getSeatId());
			//entrydate
			populateMap.put(sq.next(), userVO.getHospitalCode());
				
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldReqExtBldBankDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
}
