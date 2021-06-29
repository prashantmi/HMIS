package mrd.masters.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.EnclosureMasterVO;
import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.MrdCheckListVO;
import hisglobal.vo.MrdMasterVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.RecordBoundingVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;
import mrd.masters.bo.MrdMasterBO;
import mrd.masters.bo.MrdMasterBOi;

public class MrdMasterDelegate extends Delegate {

	public MrdMasterDelegate(){
		super(new MrdMasterBO()); ///<<Setting the service provider
	  }
	
	// Function for Rack Shelf Master
	
	public boolean saveRackShelf(RackShelfMstVO rackShelfVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.saveRackShelf(rackShelfVO, userVO);
		
	}
	
	public void modifyRackShelf(RackShelfMstVO[] updateRackShelfVO,RackShelfMstVO[] insertRackShelfVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		serviceBO.modifyRackShelf(updateRackShelfVO,insertRackShelfVO, userVO);
		
	}

	public Map getEssentialsForRackShelf(UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.getEssentialsForRackShelf(userVO);
	}

	public Map getRackShelfDetail(String rackId, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.getRackShelfDetail(rackId,userVO);
	}
	
	public Map getEssentialsForRecordTypeWiseEnclosure(UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.getEssentialsForRecordTypeWiseEnclosure(userVO);
	}
	
	public void saveEnclosureRecord(List<RecordTypeWiseEnclosureMstVO> lstEnclosure,UserVO _userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		serviceBO.saveEnclosureRecord(lstEnclosure,_userVO);
	}
	
	
	public RecordTypeWiseEnclosureMstVO fetchEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return (serviceBO.fetchEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO));
	}
	
	public String getRecordTypeName(String recordTypeId,UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		String recordTypeName=serviceBO.getRecordTypeName(recordTypeId,_UserVO);
		return recordTypeName;
	}
	
	public boolean modifyEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.modifyEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO);
		return hasFlag;
	}
	
	/* ********************************** Rack Master*******************************************/
	
	public Map getEssentialForRackMst(UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEssentialForRackMst(userVO);
	}
	
	public List getBuilding(UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getBuilding(userVO);
	}

	public List getItemList(UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getItemList(userVO);
	}
	
	public List getRackType(UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getRackType(userVO);
	}
	
	public List getBlockList(String buildingCode,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.getBlockList(buildingCode,userVO);
	}
	
	public List getFloorList(String blockId,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.getFloorList(blockId,userVO);
	}
	
	public List getRoomList(String floorId,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi) super.getServiceProvider();
		return serviceBO.getRoomList(floorId,userVO);
	}
	
	public boolean saveRackDetails(RackMstVO _RackMstVO,UserVO _userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveRackDetails(_RackMstVO,_userVO);
		return hasFlag;
	}
	
	public Map fetchRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return (serviceBO.fetchRackDetails(_RackMstVO, _UserVO));
	}
	
	public String getRackTypeName(String rackTypeId,UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		String rackTypeName=serviceBO.getRackTypeName(rackTypeId,_UserVO);
		return rackTypeName;
	}
	
	public boolean modifyRackDetails(RackMstVO _RackMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.modifyRackDetails(_RackMstVO, _UserVO);
		return hasFlag;
	}

	/* ***********************Process Wise Designation Mapping ********************************/
	public List getProcessType() {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getProcessType();
	}

	public void saveProcessWiseDesig(
			DoctorDesigMappingVO[] insertDoctorDesigMappingVO,
			DoctorDesigMappingVO[] updateDoctorDesigMappingVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		serviceBO.saveProcessWiseDesig(insertDoctorDesigMappingVO,updateDoctorDesigMappingVO, userVO);
	}

	public List getAssignedDesignationList(String processType, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getAssignedDesignationList(processType, userVO);
	}

	public List getNotAssignedDesignationList(String processType, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getNotAssignedDesignationList(processType, userVO);
	}

	public RackShelfMstVO getRackShelfDetail(RackShelfMstVO rackShelfVO,UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getRackShelfDetail(rackShelfVO, userVO);
	}

	public boolean modifyRackShelf(RackShelfMstVO rackShelfVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.modifyRackShelf(rackShelfVO, userVO);
	}

	public String getRackNameByRackId(String rackId, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getRackNameByRackId(rackId, userVO);
	}

	
	
	public Map getEssentialForMrdMst(UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEssentialForMrdMst(userVO);
	}

	public List getEmployeeListBasedOnDept(String deptCode, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEmployeeListBasedOnDept(deptCode,userVO);
	}
	
	
	public boolean saveMrdDetails(MrdMasterVO mrdMstVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveMrdDetails(mrdMstVO,userVO);
		return hasFlag;
		
	}
	
	public boolean saveModMrdMaster(MrdMasterVO mrdMstVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveModMrdMaster(mrdMstVO,userVO);
		return hasFlag;
		
	}

	public Map getDataForModifyMrdMst(MrdMasterVO mrdMasterVO,
			UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyMrdMst(mrdMasterVO,userVO);
		
	}

	
	public void saveRecordBoundingDetail(List<RecordBoundingVO> lstRecBound,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		serviceBO.saveRecordBoundingDetail(lstRecBound,userVO);
	}
	
	public Map getEssentialForEnclosureMapping(String recordTypeId,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEssentialForEnclosureMapping(recordTypeId,userVO);
	}
	
	public List getEnclosureRecordListNotMapped(String recordTypeId,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEnclosureRecordListNotMapped(recordTypeId,userVO);
	}
	

	
	public boolean saveReqPurposeMstDetail(RequestPurposeMstVO reqPurposeMstVO, UserVO userVO){
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveReqPurposeMstDetail(reqPurposeMstVO,userVO);
		return hasFlag;
		
	}
	
	public Map getDataForModifyReqPurposeMst(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO){
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyReqPurposeMst(reqPurposeMstVO,_UserVO);
		
	}
	
	public boolean saveModReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveModReqPurposeMaster(reqPurposeMstVO,_UserVO);
		return hasFlag;
	}
	
	public Map getEssentialForReqPurposeMst(UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEssentialForReqPurposeMst(userVO);
	}
	
	public boolean saveMrdCheckListMstDetail(MrdCheckListVO mrdCheckListMstVO, UserVO userVO){
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveMrdCheckListMstDetail(mrdCheckListMstVO,userVO);
		return hasFlag;
		
	}
	
	public Map getDataForModifyMrdCheckListMst(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO){
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyMrdCheckListMst(mrdCheckListMstVO,_UserVO);
		
	}
	
	public boolean saveModMrdCheckListMaster(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveModMrdCheckListMaster(mrdCheckListMstVO,_UserVO);
		return hasFlag; 
	}
	
	public boolean saveEnclosureMstDetail(EnclosureMasterVO enclosureMstVO, UserVO userVO){
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveEnclosureMstDetail(enclosureMstVO,userVO);
		return hasFlag;
		
	}
	
	public Map getDataForModifyEnclosureMst(EnclosureMasterVO enclosureMstVO, UserVO _UserVO){
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getDataForModifyEnclosureMst(enclosureMstVO,_UserVO);
		
	}
	
	public boolean saveModEnclosureMaster(EnclosureMasterVO enclosureMstVO, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveModEnclosureMaster(enclosureMstVO,_UserVO);
		return hasFlag; 
	}
	
	
	public Map getEssentialForRecordTypeCheckListMst(UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getEssentialForRecordTypeCheckListMst(userVO);
	}
	
	public Map getAllAddedCheckListByRecordType(RecordTypeCheckListMstVO vo,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getAllAddedCheckListByRecordType(vo,userVO);
	}
	
	public List getCheckListNotMapped(RecordTypeCheckListMstVO recordTypeCheckListVO,UserVO userVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getCheckListNotMapped(recordTypeCheckListVO, userVO);
	}
	
	
	public boolean saveRecordTypeCheckListMst(List recordTypeCheckListVOLst, UserVO _UserVO)
	{
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		boolean hasFlag=serviceBO.saveRecordTypeCheckListMst(recordTypeCheckListVOLst,_UserVO);
		return hasFlag; 
	}

	public Map getEssentialForTabAccessMst(UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getAllDeptUnitList(userVO);
	}

	public Map getTabAccessPolicy(
			EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getTabAccessPolicy(eprTabAccessDtlVO,userVO);
	}

	public void saveTabAccessPolicy(
			List<EprTabAccessDtlVO> insertTabAccessDtlVOList, List<EprTabAccessDtlVO> updateTabAccessDtlVOList, Map userTabIdMap, EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		serviceBO.saveTabAccessPolicy(insertTabAccessDtlVOList,updateTabAccessDtlVOList,userTabIdMap,eprTabAccessDtlVO,userVO);
	}

	public Map getUsersForTabAccess(UserVO userVO) {
		MrdMasterBOi serviceBO = (MrdMasterBOi)super.getServiceProvider();
		return serviceBO.getUsersForTabAccess(userVO);
	}
	
	

}
