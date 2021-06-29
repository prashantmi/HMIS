package medicalboard.masters.dao;

import java.util.List;

import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.UserVO;

public interface CertificateTypeMstDAOi {

	public String checkDuplicateCertificateTypeName(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO);
	public void create(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO);
	public MbCertificateTypeMstVO getDataCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO);
	public void updateCertificateType(MbCertificateTypeMstVO mTypeMstVO,UserVO _UserVO);
	public void modifySaveCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO);
	public void saveDistrict(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO);
	public List  getDataDistrictIDForModify(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO);
	public List  getRemainingDistrictIDForModify(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO);
	public void updateDistrictId(MbCertificateTypeMstVO mTypeMstVO,UserVO _UserVO);
	public String checkDuplicateCertificateNameForModify(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO);
	public String getMaxCertificateTypeId(UserVO userVO);
}
