package new_investigation.masters.dao;

import java.util.List;




import new_investigation.vo.LabMasterVO;
import hisglobal.vo.UserVO;

public interface LocalLabMstDAOi
{


	//START LAb MASTER//

	public String checkDuplicateLocalLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void createLocalLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public void fetchLocalLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public String checkDuplicateLocalLabModify(LabMasterVO labMasterVO,UserVO _UserVO);
	public void updateLocalLab(LabMasterVO labMasterVO, UserVO _UserVO);
	public List getGlobalLabCombo(UserVO _UserVO);
	public List getLocalLabCombo(UserVO _UserVO);
	public void Populate(LabMasterVO labMasterVO, UserVO _UserVO);
	public List getlocationCombo(UserVO _UserVO);
	public List getlabInchargeCombo(UserVO _UserVO);
/*	public void updateValidLocalLab(LabMasterVO locallab_VO,UserVO _UserVO);
*/	public String checkPrimaryKeyLocalLab(LabMasterVO locallab_VO, UserVO _UserVO);
	public void updateValid(LabMasterVO locallab_VO,UserVO _UserVO);



}
