package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

import registration.bo.delegate.PatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class OpdPatientBelongingDATA extends ControllerDATA {
	
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO){
	    
		PatientDelegate  patientDelegate= new PatientDelegate();
		_patVO=patientDelegate.searchPatientByCrNo(_patVO,_userVO);
		return _patVO;
	}
	
	public static Map getPatientBelongingEssentials(String patCrNo, UserVO _userVO){
	    
		OpdEssentialDelegate  opdEssentialDelegate= new OpdEssentialDelegate();
		return opdEssentialDelegate.getPatientBelongingEssentials(patCrNo,_userVO);
		
	}
	
	public static void saveBelongingDetails(PatientBelongingVO[] _patBelongingVO,UserVO _userVO){
	    
		OpdPatientDelegate  opdDelegate= new OpdPatientDelegate();
		 opdDelegate.saveBelongingDetails(_patBelongingVO,_userVO);
		
	}
	
	public static void saveBelongingHandoverDetails(PatientBelongingVO[] _patBelongingVO,UserVO _userVO){
	    
		OpdPatientDelegate  opdDelegate= new OpdPatientDelegate();
		 opdDelegate.saveBelongingHandoverDetails(_patBelongingVO,_userVO);
		
	}
	
	public static PatientBelongingVO[] modifyBelongingDetails(PatientBelongingVO _patBelongingVO,String _oldItemCode,String _collectionDate,UserVO _userVO){
	    
		OpdPatientDelegate  opdDelegate= new OpdPatientDelegate();
		return opdDelegate.modifyBelongingDetails(_patBelongingVO,_oldItemCode,_collectionDate,_userVO);
		
	}

}
