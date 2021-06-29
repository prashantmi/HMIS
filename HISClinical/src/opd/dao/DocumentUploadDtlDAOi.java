package opd.dao;

import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.UserVO;

public interface DocumentUploadDtlDAOi {
	public DocumentUploadDtlVO create(DocumentUploadDtlVO documentUploadDtlVO , UserVO _userVO);
	public DocumentUploadDtlVO[] getDocumentDetailsByCrNo(String patCrNo,String episodeCode,UserVO _userVO);
	public DocumentUploadDtlVO[] getDocumentDetailsByEpisode(EpisodeVO _episodeVO,UserVO _userVO);
	public DocumentUploadDtlVO[] getDocumentDetailsByMlcEpisode(MlcVO _mlcVO,UserVO _userVO);
	public String getContCrNoWise(String _patCrNo,UserVO _userVO);
	public void revokeDocument(DocumentUploadDtlVO documentUploadDtlVO , UserVO _userVO);
}
