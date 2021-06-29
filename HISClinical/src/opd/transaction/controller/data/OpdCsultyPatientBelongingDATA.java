package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import registration.bo.delegate.PatientDelegate;

public class OpdCsultyPatientBelongingDATA extends ControllerDATA
{
	//* Getting Patient Belonging Essentails
	public static Map getPatientBelongingEssentials(String patCrNo, UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		return opdEssentialDelegate.getPatientBelongingEssentials(patCrNo, _userVO);
	}

	//* Getting Last Episode in Emergency 
	public static EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		return delegate.getLastEpisodeInEmergency(_patVO, _userVO);
	}	

	//* Getting Last Episode Detail 
	public static EpisodeVO retrieveByEpisodeNo(EpisodeVO _epiVO, UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		return delegate.retrieveByEpisodeNo(_epiVO, _userVO);
	}	

	//* Saving Belongings Detail
	public static void saveBelongingDetails(PatientBelongingVO[] _patBelongingVO, UserVO _userVO)
	{
		OpdPatientDelegate opdDelegate = new OpdPatientDelegate();
		opdDelegate.saveBelongingDetails(_patBelongingVO, _userVO);
	}

	//* Saving Hand Over Deatils
	public static void saveBelongingHandoverDetails(PatientBelongingVO[] _patBelongingVO, UserVO _userVO)
	{
		OpdPatientDelegate opdDelegate = new OpdPatientDelegate();
		opdDelegate.saveBelongingHandoverDetails(_patBelongingVO, _userVO);
	}

	//* Modifying Belonging Detail
	public static PatientBelongingVO[] modifyBelongingDetails(PatientBelongingVO _patBelongingVO, String _oldItemCode, String _collectionDate, UserVO _userVO)
	{
		OpdPatientDelegate opdDelegate = new OpdPatientDelegate();
		return opdDelegate.modifyBelongingDetails(_patBelongingVO, _oldItemCode, _collectionDate, _userVO);
	}
}
