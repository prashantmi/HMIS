package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientProfileCDBurnDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

import mrd.transaction.delegate.MrdEssentialDelegate;

public class CDBurnDATA extends ControllerDATA
{

	public static List getPatientProfileByCrNo(String patCrNo, UserVO userVO) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getPatientProfileByCrNo(patCrNo,userVO);
	}

	public static void saveCDBurnDetail(
			PatientProfileCDBurnDtlVO[] patientProfileCDBurnDtlVO, UserVO userVO) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		delegate.saveCDBurnDetail(patientProfileCDBurnDtlVO,userVO);
		
	}

}
