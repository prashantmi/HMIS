package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.ContainerMasterVO;
import hisglobal.vo.UserVO;

public interface ContainerMstDAOi
{


	public ContainerMasterVO[] getContainer(UserVO userVO);
	public String checkDuplicateContainer(ContainerMasterVO container_VO, UserVO _UserVO);
	public void createContainer(ContainerMasterVO container_VO, UserVO _UserVO);
	public void fetchCheckListContainer(ContainerMasterVO container_VO, UserVO _UserVO);
	public String checkDuplicateModifyContainer(ContainerMasterVO container_VO,UserVO _UserVO);
	public void updateContainer(ContainerMasterVO container_VO, UserVO _UserVO);
	public List getuomCombo(UserVO _UserVO);

}
