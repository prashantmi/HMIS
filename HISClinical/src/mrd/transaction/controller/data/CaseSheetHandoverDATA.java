package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class CaseSheetHandoverDATA extends ControllerDATA {
	
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
	
	//ADDED BY SWATI  ON DATE:06-MAY-2019
		//ADM NO WISE DTL
	
	public static RecordDispatchDtlVO []getCaseSheetListToReadyADMNOWISE2(
			String admno, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getCaseSheetListToReadyADMNOWISE2(admno,userVO);
	}
	
	//ADDED BY SWATI  ON DATE:06-MAY-2019
	//CR NO WISE DTL

public static RecordDispatchDtlVO []getCaseSheetListToReadyCRNOWISE2(
		String crno, UserVO userVO) {
	MrdDelegate  mrdDelegate= new MrdDelegate();
	return mrdDelegate.getCaseSheetListToReadyCRNOWISE2(crno,userVO);
}
	
	
    //added by swati
	//date:01-may-2019
		
	public static List<CaseSheetDtlVO> getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO)
	{
			MrdDelegate  mrdDelegate= new MrdDelegate();
			return mrdDelegate.getCaseSheetListToReadyADMNOWISE(admno,userVO);
	}
		
	//added by swati sagar
	//date:02-may-2019
		
	public static List<CaseSheetDtlVO> getCaseSheetListToReadyCRNOWISE(String crno,UserVO userVO)
	{
			MrdDelegate  mrdDelegate= new MrdDelegate();
			return mrdDelegate.getCaseSheetListToReadyCRNOWISE(crno,userVO);
	}
		

	public static Map getAllEnclosureDetails(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getEnclosureSummary(recordDispatchVO,userVO);
	}

	public static void saveHandoverDetail(
			List<RecordDispatchDtlVO> recordDispatchVOList, List checklistVOList, Map enclosureMap,
			String isAccepted, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveCaseSheetHandoverDetail(recordDispatchVOList,checklistVOList,enclosureMap,isAccepted,userVO);
		
	}
	/*public static void saveHandoverDetail(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveCaseSheetHandoverDetail(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,userVO);
	}*/
	

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

}
