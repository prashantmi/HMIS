package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class CaseSheetApprovalDATA extends ControllerDATA {
	
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
	
	public static RecordDispatchDtlVO []getRecordDispatchList(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getRecordDispatchList(recordDispatchVO,userVO);
	}

	public static Map getAllEnclosureDetails(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getEnclosureSummary(recordDispatchVO,userVO);
	}

	public static void saveHandoverDetail(
			List recordDispatchVOList, List checklistVOList, Map enclosureMap,
			String isAccepted, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveCaseSheetHandoverDetail(recordDispatchVOList,checklistVOList,enclosureMap,isAccepted,userVO);
		
	}

	public static RecordTypeCheckListMstVO[] getChecklistForHandOver(String recordType,
			String checklistMode, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getChecklistForHandOver(recordType,checklistMode,userVO);
	}
	
	public static Map getEssentialForCaseSheetHandOver(RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getEssentialForCaseSheetHandOver(recordDispatchVO,userVO);
	}

	public static RecordDispatchDtlVO[] getRecordListByPatCrNo(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getRecordListByPatCrNo(recordDispatchVO,userVO);
	}

	public static void saveCaseSheetApprovalDetail(
			List recordDispatchVOList, List<CaseSheetDtlVO> caseSheetDtlVOList, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveCaseSheetApprovalDetail(recordDispatchVOList,caseSheetDtlVOList,userVO);
	}

	public static RecordDispatchDtlVO[] getRecordListByPatAdmNo(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getRecordListByPatAdmNo(recordDispatchVO,userVO);
	}
}
