package opd.dao;

import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface OpdPatientImageDtlDAOi
{
	// Creating Initial Entry Record
	public String createInitialEntry(OpdPatientImageDtlVO _VO, UserVO _UserVO);
	
	// Removing Initial Entry Record
	public void removeInitialEntries(OpdPatientImageDtlVO _VO, UserVO _UserVO);
	
	// Selecting Maximum Serial of given CRNO
	public String selectMaxSerialOfCRNo(String _CrNo, UserVO _UserVO);
	
	// Updating Record After Final Saving
	public void updateFinalRecord(OpdPatientImageDtlVO _VO, UserVO _UserVO);
	
	// Getting Images List of Patient in given Episode Visit
	public List<OpdPatientImageDtlVO> getOPDPatOldImagesList(UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);	

	// Saving Record
	public String save(OpdPatientImageDtlVO _vo, UserVO _userVO);

	// Updating Record For Modification
	public void updateOld(OpdPatientImageDtlVO _vo, UserVO _userVO);

	// Getting Images Log Detail By Image File Name
	public OpdPatientImageDtlVO getImagesLogDetail(String _imageFileName, UserVO _userVO);
}
