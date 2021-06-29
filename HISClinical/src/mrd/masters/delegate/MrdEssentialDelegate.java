package mrd.masters.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.masters.bo.MrdEssentialBO;
import mrd.masters.bo.MrdEssentialBOi;

public class MrdEssentialDelegate extends Delegate {

	public MrdEssentialDelegate(){
		super(new MrdEssentialBO()); ///<<Setting the service provider
	  }

	public List getRackNameList(UserVO _userVO) 
	{
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRackNamelist(_userVO);
	}
	
	public Map getMrdBoundingEssential(UserVO userVO)
	{
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMrdBoundingEssential(userVO);
	}
		
	public List getBoundedRecordType(String boundingMode,String boundingId,UserVO userVO)
	{
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getBoundedRecordType(boundingMode,boundingId,userVO);
	}
	
	public List getRackBasedOnMrd(String mrdCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRackBasedOnMrd(mrdCode,userVO);
	}
	
	public List getShelfBasedOnRack(String rackId,UserVO userVO)
	{
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getShelfBasedOnRack(rackId,userVO);
	}

	public Map getEprRestrictedCategoryEssentials(UserVO _uservo) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForEPRRestrictedCat(_uservo);
	}

	public void saveEprPatRestrictedCategory(
			EprRestrictedCategoryVO[] eprRestrictedCatMstVO, UserVO userVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		serviceBO.saveEprPatRestrictedCategory(eprRestrictedCatMstVO,userVO);
		
	}
	
	public Map getFormPReportEssentials(UserVO _userVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getFormPReportEssentials(_userVO);
	}
	
}
