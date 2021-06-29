package opd.dao;

import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.UserVO;

public interface EpisodeExtInvDtlDAOi 
{
	public void saveExtInvestigationDtl(EpisodeExtInvDtlVO epiExtInvDtlVO,UserVO userVO);
	
	public EpisodeExtInvDtlVO[] getAddedExternalInvDetail(String patCrNo,UserVO userVO);
	
	public EpisodeExtInvDtlVO[] getPatientExternalInvDetailEMR(String patCrNo,String [] departmentUnitArray,String accessType,UserVO userVO);
	
	/* Functions Created By Pawan Kumar B N */
	public void savePatientComplaintsDtl(EpisodeExtInvDtlVO epiExtInvDtlVO,UserVO userVO);
	
	/* Function Created By Pawan Kumar B N */
	public EpisodeExtInvDtlVO[] getAddedPatientComplaintsDetail(String patCrNo,UserVO userVO);
}
