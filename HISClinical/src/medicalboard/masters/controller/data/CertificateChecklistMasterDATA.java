package medicalboard.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.masters.delegate.MbMasterDelegate;


public class CertificateChecklistMasterDATA
{
	
	public static Map getEssentialsForCertificateChecklist(String certificateTypeID,
			UserVO userVO) {
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return masterDelegate.getEssentialsForCertificateChecklist(certificateTypeID,userVO);
	}

	public static void saveCerificateChecklistMst(List certificateChecklist,
			UserVO userVO) {
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.saveCerificateChecklistMst(certificateChecklist,userVO);
	}



	public static void modifySaveCertificateChecklist(
			List certificateChecklist,
			List certificateChecklistUpdateVOList,
			UserVO userVO) {
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.modifySaveCertificateChecklist(certificateChecklist,certificateChecklistUpdateVOList,userVO);
		
	}

}
