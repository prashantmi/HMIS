package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdMedicalCampDtlVO;
import hisglobal.vo.MrdMedicalCampTeamDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

import mrd.transaction.controller.fb.MrdMedicalCampFB;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class MrdMedicalCampDATA extends ControllerDATA
{
	public static List getCampListForMedicalCamp(MrdMedicalCampDtlVO mrdMedicalCampDtlVO, UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getCampListForMedicalCamp(mrdMedicalCampDtlVO,userVO); 
	}

	public static List getCampEmpNameForMedicalCamp(UserVO userVO) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getCampEmpNameForMedicalCamp(userVO);
	}

	public static void saveCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, MrdMedicalCampTeamDtlVO[] medicalCampTeamDtlVO, UserVO userVO) {

		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		delegate.saveCampDetail(medicalCampDtlVO, medicalCampTeamDtlVO, userVO);
	}

	public static void getCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		delegate.getCampDetail(medicalCampDtlVO, userVO);
	}

	public static List<MrdMedicalCampTeamDtlVO> getCampEmpDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) 
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getCampEmpDetail( medicalCampDtlVO, userVO);
	}	
}

