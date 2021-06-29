package medicalboard.transactions.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MedicalBoardReferMappingVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class MbReferMappingDAO extends DataAccessObject implements MbReferMappingDAOi{

	public MbReferMappingDAO(TransactionContext _tx) {
		super(_tx);
	}

	public List selectByCertificateType(String CertificateType,UserVO _userVO) {
		
		ResultSet rs;
		String query="";
		Map populateMAP = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey = "SELECT.BY_CERTIFICATE_TYPE.HMBT_REFER_MAPPING";
		List <MedicalBoardReferMappingVO> referMappingVOList=null;
		MedicalBoardReferMappingVO referMappingVO;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), CertificateType);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{					
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				referMappingVOList=new ArrayList<MedicalBoardReferMappingVO>();
			}
			else{
				throw new HisRecordNotFoundException("No Refer detail found");
			}
			rs.beforeFirst();
			while(rs.next()){
				referMappingVO=new MedicalBoardReferMappingVO();
				HelperMethods.populateVOfrmRS(referMappingVO, rs);
				referMappingVOList.add(referMappingVO);
			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
				throw new HisDataAccessException("HelperMethodsDAO.excecuteUpdate" + e);
		}
		return referMappingVOList;
	}
	
}
