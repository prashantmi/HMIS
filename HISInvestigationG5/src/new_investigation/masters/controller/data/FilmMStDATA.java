package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.TestNewMasterVO;

public class FilmMStDATA {

	public static void saveFilm(FilmMstVO testNewMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveFilm(testNewMasterVO, _UserVO);
	}

	public static Map fetchFilm(FilmMstVO testNewMasterVO, UserVO _UserVO,String filmid,String slno)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchFilm(testNewMasterVO, _UserVO,filmid,slno);
	}
	
	
	public static Map fetchFilmD(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchFilmD(testNewMasterVO, _UserVO);
	}
	
	public static void savemodifyFilm(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyFilm(testNewMasterVO, _UserVO);
	}
	
}
