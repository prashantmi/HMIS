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

public class CaseSheetDispatchDATA extends ControllerDATA {
	
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
	
	public static Map getAllEnclosureDetails(CaseSheetDtlVO caseSheetDtlVO, UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllEnclosureDetails(caseSheetDtlVO,userVO); 
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
	
	
	public static RecordTypeCheckListMstVO[] getAllChecklistDetails(String checklistModeDispatchLevel,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllChecklistDetails(checklistModeDispatchLevel,userVO); 
	}
	
	public static List<CaseSheetDtlVO> getCaseSheetListToReady(String unitCode,String wardCode,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getCaseSheetListToReady(unitCode,wardCode,userVO);
	}
	
/*	public static void saveCaseSheetDispatch(CaseSheetDispatchVO[] sheetDispatchVO,CaseSheetDtlVO[] caseSheetDtlVOArray,Map enclosureMap,List<RecordTypeCheckListMstVO> checklistMstVOList, UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		 mrdDelegate.saveCaseSheet(sheetDispatchVO,caseSheetDtlVOArray,enclosureMap,checklistMstVOList,userVO);
	}*/

	public static void saveCaseSheetDispatch(
			RecordDispatchDtlVO[] dispatchVO,
			CaseSheetDtlVO[] caseSheetDtlVOArray, Map enclosureMap,
			List checklistMstVOList, UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveCaseSheet(dispatchVO,caseSheetDtlVOArray,enclosureMap,checklistMstVOList,userVO);
		
	}

	public static RecordTypeCheckListMstVO[] getCaseSheetChecklist(String checklistModeDispatchLevel,UserVO userVO) {
		MrdDelegate  mrdDelegate= new MrdDelegate();
		return mrdDelegate.getAllChecklistDetails(checklistModeDispatchLevel,userVO); 
	}

}
