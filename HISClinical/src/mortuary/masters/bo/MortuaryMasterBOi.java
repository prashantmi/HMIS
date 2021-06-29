package mortuary.masters.bo;

import java.util.List;
import java.util.Map;

import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.ExternalLabMasterVO;
import hisglobal.vo.ExternalLabTestMasterVO;
import hisglobal.vo.MortuaryMasterVO;
import hisglobal.vo.MortuaryAreaMasterVO;
import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.IncisionTypeMasterVO;
import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;

public interface MortuaryMasterBOi 
{
	
	public void saveMortuaryMaster(MortuaryMasterVO mortuaryMstVO,UserVO userVO);
	public MortuaryMasterVO getDataForModify(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO);
	public boolean saveModMortuaryMaster(MortuaryMasterVO _MortuaryMasterVO,UserVO _UserVO);
	
	public void saveOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO userVO);
	public OpinionMasterVO getDataForOpinionModify(OpinionMasterVO opinionMasterVO,UserVO userVO);
	public boolean saveModOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO userVO);
	
	public void saveRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO);
	public MortuaryRoleMasterVO getDataForRoleModify(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO);
	public boolean saveModRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO);
	
	public void saveDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	public DeceasedItemMasterVO getDataForDeceasedItemModify(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	public boolean saveModDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO);
	
	public void saveMortuaryAreaMaster(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO);
	public MortuaryAreaMasterVO getDataForModify(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO);
	public boolean saveModMortuaryAreaMaster(MortuaryAreaMasterVO _MortuaryMasterVO,UserVO _UserVO);
	
	public String getMortuaryName(String mortuaryCode,UserVO _UserVO);
	
	public void saveIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO);
	public IncisionTypeMasterVO getDataForModify(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO);
	public boolean saveModIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO _UserVO);
	
	/*********************************Chamber Master***********************************************/
	
	public Map getChamberEssentialDetails(String[]  _controlsArray, UserVO _userVO);	
	public boolean saveChamber(ChamberMasterVO _chamberMstVO,UserVO _userVO) ;
	public ChamberMasterVO getChamberDetails(ChamberMasterVO _chamberMstVO,UserVO _userVO);
	public boolean updateChamber(ChamberMasterVO _chamberMstVO, UserVO _userVO);
	
	
	/*********************************Chamber Rack Master***********************************************/


	public String getChamberRackEssentials(String  _chamberId, UserVO _userVO);
	public boolean saveChamberRack(ChamberRackMasterVO[] _chamberRackMstVO,UserVO _userVO) ;
	public ChamberRackMasterVO getChamberRackDetails(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO);
	public boolean updateChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO);
	

	
/********************************External Lab Test Master**************************************************/
	
	public boolean saveExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO) ;
	public ExternalLabTestMasterVO getExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO);
	public boolean updateExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO);
	
	
	
/********************************External Lab Master**************************************************/
	
	public boolean saveExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO) ;
	public ExternalLabMasterVO getExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO);
	public boolean updateExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO);

	
/********************************Preservative Master**************************************************/
	
	public boolean savePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO) ;
	public PreservativeMasterVO getPreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO);
	public boolean updatePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO);
	
}
