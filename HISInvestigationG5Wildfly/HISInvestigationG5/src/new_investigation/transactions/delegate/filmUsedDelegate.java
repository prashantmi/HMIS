package new_investigation.transactions.delegate;

/**
 * @author C-DAC, Noida Project : HISInvestigationG5 Module 
 * @Date 20 Aug, 2008 
 * Process: Donor Registration
 * Modified By: Pawan Kumar B N
 * Modified On: 18-11-2011
 * 
 */
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.filmUsedVO;




public class filmUsedDelegate extends Delegate
{
	public filmUsedDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	 
	 
	
	//Save Logic
	
	public List  savePatientDetails(List<filmUsedVO> lstFilm,List<filmUsedVO> lstFilmAdd,List<filmUsedVO> lstFilmWaste,filmUsedVO filmvo,  UserVO _userVO,Map mp)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveFilmDetails(lstFilm,lstFilmAdd,lstFilmWaste, filmvo, _userVO,mp);
	}
	
	 
	public Map  LabCombos(filmUsedVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.LabCombos(onlinePatientvo, _UserVO);
	}
	
	public Map setPatientEssentialsOnLoad(filmUsedVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientEssentialsOnLoad(onlinePatientvo, _UserVO);
	}
	
	public Map setPatientEssentials(filmUsedVO onlinePatientvo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.setPatientEssentials(onlinePatientvo, _UserVO);
	}
	
	public Map patientDetails(filmUsedVO onlinePatientvo, List<String> reqList,UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		return serviceBO.patientDetails(onlinePatientvo, reqList, _UserVO);
	}
	

	
	
}
