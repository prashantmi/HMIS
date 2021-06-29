package registration.dao;

import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;

import java.util.List;

public interface YellowSlipEntryDAOi {

	public void save(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO);
	
	public void updateIsValid(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO);
	
	public List getYellowSlipEntryByUser(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO);
	
	public EpisodeVO getYellowSlipEntryByCRNoEpisodeNo(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO) ;
	
	public List <EpisodeDiagnosisVO> getYellowSlipEntryDiagnosisDtl(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO);
}
