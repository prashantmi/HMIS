package medicalboard.masters.dao;

import hisglobal.vo.MBCertificateChecklistVO;
import hisglobal.vo.UserVO;

public interface CertificateChecklistMasterDAOi {

	public void insert(MBCertificateChecklistVO checklistVO,UserVO userVO);
	
	public void update(MBCertificateChecklistVO checklistVO,UserVO userVO);
	
		
}
