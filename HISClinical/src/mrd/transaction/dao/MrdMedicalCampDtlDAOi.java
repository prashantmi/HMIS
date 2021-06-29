package mrd.transaction.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.MrdMedicalCampDtlVO;
import hisglobal.vo.MrdMedicalCampTeamDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

import mrd.transaction.controller.fb.MrdMedicalCampFB;

public interface MrdMedicalCampDtlDAOi {
	
	public List<MrdMedicalCampDtlVO> getCampListForMedicalCamp(MrdMedicalCampDtlVO mrdMedicalCampDtlVO,UserVO userVO);

	public List getCampEmpNameForMedicalCamp(UserVO userVO);

	public String saveCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO);

	public void saveCampEmpDetail(MrdMedicalCampTeamDtlVO medicalCampTeamDtlVO, UserVO userVO, HisDAO dao, String medicalCampNo, String strMode_p);

	public void getCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO);

	public List<MrdMedicalCampTeamDtlVO> getCampEmpDetail(MrdMedicalCampDtlVO medicalCampDtlVO,UserVO userVO);

	public void updateCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO,UserVO userVO);

	//public String generateCampNo(UserVO userVO);

}
