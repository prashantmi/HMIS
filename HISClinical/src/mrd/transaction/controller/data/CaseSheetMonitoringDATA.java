package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CaseSheetLostFoundVO;
import hisglobal.vo.UserVO;

import java.util.List;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class CaseSheetMonitoringDATA extends ControllerDATA {
	
	public static List getDeptUnitList(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
	
	public static List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}
	
	public static List<CaseSheetDtlVO> getPatientCaseSheetDtlByCrNo(
			String patCrNo, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getPatientCaseSheetDtlByCrNo(patCrNo,userVO);
	}

	public static List<CaseSheetDtlVO> getPatientCaseSheetDtl(
			String deptUnitCode, String wardCode, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getPatientCaseSheetDtl(deptUnitCode,wardCode,userVO);
	}

	public static void updatePatientCaseSheetStatus(CaseSheetDtlVO caseSheetDtlVO,
			CaseSheetLostFoundVO caseSheetLostFoundVO, String updateType, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.updatePatientCaseSheetStatus(caseSheetDtlVO,caseSheetLostFoundVO,updateType,userVO);
	}

	public static CaseSheetLostFoundVO getPatientCaseSheetLostFoundDtl(
			CaseSheetLostFoundVO caseSheetLostFoundVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getPatientCaseSheetLostFoundDtl(caseSheetLostFoundVO,userVO);
	}

}
