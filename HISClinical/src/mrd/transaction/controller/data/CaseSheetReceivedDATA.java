package mrd.transaction.controller.data;

import java.util.Map;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

public class CaseSheetReceivedDATA extends ControllerDATA
{
	/**  Getting the List Of Case Sheet, Rack & Shelf List
	 * @param certificateRcvMode
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	public static Map getEssentialForCaseSheetAcceptence(UserVO userVO)
	{
		MrdEssentialDelegate  mrdDelegate= new MrdEssentialDelegate();
		return mrdDelegate.getEssentialForCaseSheetAcceptence(userVO);
	}
	
	public static Map getAllEnclosureChecklistsByRecordId(String recordId,
			UserVO userVO) {
		MrdEssentialDelegate  mrdDelegate= new MrdEssentialDelegate();
		return mrdDelegate.getAllEnclosureChecklistsByRecordId(recordId,userVO);
	}

	public static void saveCaseSheetAcceptence(
			CaseSheetDispatchVO caseSheetDispatchVO,
			String isAccept, RecordTypeWiseEnclosureMstVO[] recordTypeEnclosureVOArray, MrdRecordDtlVO mrdRecordDtlVO, RecordTypeCheckListMstVO[] recChecklistDetailVO, UserVO userVO) {
		MrdEssentialDelegate  mrdDelegate= new MrdEssentialDelegate();
		mrdDelegate.saveCaseSheetAcceptence(caseSheetDispatchVO,isAccept,recordTypeEnclosureVOArray,mrdRecordDtlVO,recChecklistDetailVO,userVO);
		
	}
}
 