package medicalboard.transactions.dao;

import hisglobal.vo.UserVO;

import java.util.List;

public interface MbReferMappingDAOi{
	
	public List selectByCertificateType(String CertificateType,UserVO _userVO);
}	