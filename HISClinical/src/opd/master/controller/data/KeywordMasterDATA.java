package opd.master.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.UserVO;

public class KeywordMasterDATA 
{
	public static boolean saveKeywordMstDetail(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveKeywordMstDetail(keywordMstVO, userVO);
		return hasFlag;
	}

	public static Map getDataForModifyKeywordMst(
			EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return(masterDelegate.getDataForModifyKeywordMst(keywordMstVO, userVO));
	}
	
	public static boolean saveModKeywordMstDetail(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveModKeywordMstDetail(keywordMstVO, userVO);
		return hasFlag;
	}
}
