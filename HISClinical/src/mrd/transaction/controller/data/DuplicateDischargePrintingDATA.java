package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DischargePrintingVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;
import registration.bo.delegate.EssentialDelegate;

public class DuplicateDischargePrintingDATA extends ControllerDATA {
	
/*	public static List getDeptUnitList(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getDeptUnitList(userVO); 
	}
	
	public static List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getWardOnBasisOfUnitCode(unitCode,userVO); 
	}*/
	
	public static List<DischargePrintingVO> getDischargePatientList(
			String deptUnitCode, String wardCode, UserVO userVO) {
		MrdEssentialDelegate  mrdEssentialDelegate= new MrdEssentialDelegate();
		return mrdEssentialDelegate.getDischargePatientList(deptUnitCode,wardCode,userVO);
	}

	public static List<DischargePrintingVO> getDischargePatientByCrNo(
			String patCrNo, UserVO userVO) {
		MrdEssentialDelegate  mrdEssentialDelegate= new MrdEssentialDelegate();
		return mrdEssentialDelegate.getDischargePatientList(patCrNo,userVO);
	}

	public static void saveDischargePrinting(
			DischargePrintingVO dischargePrintingVO, UserVO userVO) {
		MrdEssentialDelegate  mrdEssentialDelegate= new MrdEssentialDelegate();
		mrdEssentialDelegate.saveDischargePrinting(dischargePrintingVO,userVO);
	}

	public static Map getRelationsList(UserVO userVO) {
		EssentialDelegate  essentialDelegate= new EssentialDelegate();
		return essentialDelegate.getMlcDtlEssentials(userVO);
	}

	public static List<DischargePrintingVO> getRecordForDupDischargePrinting(
			String patCrNo, UserVO userVO) {
		MrdEssentialDelegate  mrdEssentialDelegate= new MrdEssentialDelegate();
		return mrdEssentialDelegate.getRecordForDupDischargePrinting(patCrNo,userVO);
	}

}
