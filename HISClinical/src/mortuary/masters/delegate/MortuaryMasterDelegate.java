package mortuary.masters.delegate;

import java.util.List;
import java.util.Map;

import mortuary.masters.bo.MortuaryMasterBO;
import mortuary.masters.bo.MortuaryMasterBOi;
import hisglobal.business.Delegate;
import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.ExternalLabMasterVO;
import hisglobal.vo.ExternalLabTestMasterVO;
import hisglobal.vo.IncisionTypeMasterVO;
import hisglobal.vo.MortuaryMasterVO;
import hisglobal.vo.MortuaryAreaMasterVO;
import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;

public class MortuaryMasterDelegate extends Delegate
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public MortuaryMasterDelegate() 
	{
		super(new MortuaryMasterBO()); // /<<Setting the service provider
	}
	
	//Saving Data For Mortuary Master
	public void saveMortuaryMaster(MortuaryMasterVO mortuaryMstVO,UserVO userVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi)super.getServiceProvider();
		serviceBO.saveMortuaryMaster(mortuaryMstVO,userVO);
	}
	
	public MortuaryMasterVO getDataForModify(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getDataForModify(_MortuaryMasterVO, _UserVO));
	}
	public boolean saveModMortuaryMaster(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		boolean flag=false;
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		flag= serviceBO.saveModMortuaryMaster(_MortuaryMasterVO, _UserVO);
		return flag;
		
	}
	
	public void saveOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO userVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi)super.getServiceProvider();
		serviceBO.saveOpinionMaster(opinionMasterVO,userVO);
	}
	
	public OpinionMasterVO getDataForOpinionModify(OpinionMasterVO opinionMasterVO, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getDataForOpinionModify(opinionMasterVO, _UserVO));
	}
	public boolean saveModOpinionMaster(OpinionMasterVO opinionMasterVO, UserVO _UserVO)
	{
		boolean flag=false;
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		flag= serviceBO.saveModOpinionMaster(opinionMasterVO, _UserVO);
		return flag;
		
	}
	
	
	public void saveRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi)super.getServiceProvider();
		serviceBO.saveRoleMaster(mortuaryRoleMasterVO,userVO);
	}
	
	public MortuaryRoleMasterVO getDataForRoleModify(MortuaryRoleMasterVO mortuaryRoleMasterVO, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getDataForRoleModify(mortuaryRoleMasterVO, _UserVO));
	}
	public boolean saveModRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO, UserVO _UserVO)
	{
		boolean flag=false;
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		flag= serviceBO.saveModRoleMaster(mortuaryRoleMasterVO, _UserVO);
		return flag;
		
	}
	
	public void saveDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi)super.getServiceProvider();
		serviceBO.saveDeceasedItemMaster(deceasedItemMasterVO,userVO);
	}
	
	public DeceasedItemMasterVO getDataForDeceasedItemModify(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getDataForDeceasedItemModify(deceasedItemMasterVO, _UserVO));
	}
	public boolean saveModDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		boolean flag=false;
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		flag= serviceBO.saveModDeceasedItemMaster(deceasedItemMasterVO, _UserVO);
		return flag;
		
	}
	
	public void saveMortuaryAreaMaster(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi)super.getServiceProvider();
		serviceBO.saveMortuaryAreaMaster(mortuaryMstVO,userVO);
	}
	
	public MortuaryAreaMasterVO getDataForModify(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getDataForModify(_MortuaryMasterVO, _UserVO));
	}
	public boolean saveModMortuaryAreaMaster(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		boolean flag=false;
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		flag= serviceBO.saveModMortuaryAreaMaster(_MortuaryMasterVO, _UserVO);
		return flag;
		
	}
	public String getMortuaryName(String mortuaryCode, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getMortuaryName(mortuaryCode, _UserVO));
	}
	
	public void saveIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi)super.getServiceProvider();
		serviceBO.saveIncisionTypeMaster(incisionTypeMasterVO,userVO);
	}
	
	public IncisionTypeMasterVO getDataForModify(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getDataForModify(incisionTypeMasterVO, _UserVO));
	}
	public boolean saveModIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		boolean flag=false;
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		flag= serviceBO.saveModIncisionTypeMaster(incisionTypeMasterVO, _UserVO);
		return flag;
		
	}

		/*********************************Chamber Master***********************************************/
	
	
	public Map getChamberEssentialDetails(String[]  _controlsArray, UserVO _userVO) {
	
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getChamberEssentialDetails(_controlsArray, _userVO));
	}
	
	public boolean saveChamber(ChamberMasterVO _chamberMstVO,UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.saveChamber(_chamberMstVO,_userVO));	}
	
	
	public ChamberMasterVO getChamberDetails(ChamberMasterVO _chamberMstVO,UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getChamberDetails(_chamberMstVO, _userVO));
	}

	public boolean updateChamber(ChamberMasterVO _chamberMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.updateChamber(_chamberMstVO, _userVO));
	}
	
	
	
	
		/*********************************Chamber Rack Master***********************************************/
	
	public String getChamberRackEssentials(String  _chamberId, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getChamberRackEssentials(_chamberId,_userVO));	}
	
	
	
	public boolean saveChamberRack(ChamberRackMasterVO[] _chamberRackMstVO,UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.saveChamberRack(_chamberRackMstVO,_userVO));	}
	
	
	public ChamberRackMasterVO getChamberRackDetails(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getChamberRackDetails(_chamberRackMstVO, _userVO));
	}

	public boolean updateChamberRack(ChamberRackMasterVO _chamberRackMstVO,UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.updateChamberRack(_chamberRackMstVO, _userVO));
	}
	
	
/*********************************External Lab Test Master***********************************************/
	
		
	public boolean saveExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.saveExternalLabTestDetails(_externalLabTestMstVO,_userVO));	}
	
	
	public ExternalLabTestMasterVO getExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getExternalLabTestDetails(_externalLabTestMstVO, _userVO));
	}

	public boolean updateExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.updateExternalLabTestDetails(_externalLabTestMstVO, _userVO));
	}
	
	
	/*********************************External Lab Test Master***********************************************/
	
	
	public boolean saveExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.saveExternalLabDetails(_externalLabMstVO,_userVO));	}
	
	
	public ExternalLabMasterVO getExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getExternalLabDetails(_externalLabMstVO, _userVO));
	}

	public boolean updateExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.updateExternalLabDetails(_externalLabMstVO, _userVO));
	}

	/*********************************Preservative Master***********************************************/
	
	
	public boolean savePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.savePreservativeDetails(_preservativeMasterVO,_userVO));	}
	
	
	public PreservativeMasterVO getPreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.getPreservativeDetails(_preservativeMasterVO, _userVO));
	}

	public boolean updatePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO) {
		MortuaryMasterBOi serviceBO = (MortuaryMasterBOi) super.getServiceProvider();
		return(serviceBO.updatePreservativeDetails(_preservativeMasterVO, _userVO));
	}


}
