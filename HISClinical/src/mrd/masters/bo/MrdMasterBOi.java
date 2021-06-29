package mrd.masters.bo;


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

public interface MrdMasterBOi {

	// Functions for Rack Shelf Master
	public Map getEssentialsForRackShelf(UserVO _uservo);
	
	public boolean saveRackShelf(RackShelfMstVO rackShelfVO, UserVO _uservo);
	
	public void modifyRackShelf(RackShelfMstVO[] updateRackShelfVO,RackShelfMstVO[] insertRackShelfVO, UserVO _uservo);
	
	public Map getRackShelfDetail(String rackId, UserVO _uservo);
	
	public Map getEssentialsForRecordTypeWiseEnclosure( UserVO _uservo);
	
	public void saveEnclosureRecord(List<RecordTypeWiseEnclosureMstVO> lstEnclosure,UserVO _userVO);
	
	public RecordTypeWiseEnclosureMstVO fetchEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO);
	
	public String getRecordTypeName(String recordTypeId,UserVO _UserVO);
	
	public boolean modifyEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO,UserVO _UserVO);
	
	public List getBuilding(UserVO userVO);
	
	public List getItemList(UserVO userVO);
	
	public List getRackType(UserVO userVO);
	
	public List getBlockList(String buildingCode,UserVO userVO);
	
	public List getFloorList(String blockId,UserVO userVO);
	
	public List getRoomList(String floorId,UserVO userVO);
	
	public boolean saveRackDetails(RackMstVO _RackMstVO,UserVO _userVO);
	
	public Map fetchRackDetails(RackMstVO _RackMstVO, UserVO _UserVO);
	
	public String getRackTypeName(String rackTypeId,UserVO _UserVO);
	
	public boolean modifyRackDetails(RackMstVO _RackMstVO,UserVO _UserVO);

	// process wise designation mapping
	
	public List getProcessType();

	public void saveProcessWiseDesig(DoctorDesigMappingVO[] insertDoctorDesigMappingVO,
			DoctorDesigMappingVO[] updateDoctorDesigMappingVO, UserVO userVO);

	public List getAssignedDesignationList(String processType, UserVO userVO);

	public List getNotAssignedDesignationList(String processType, UserVO userVO);

	
	public Map getEssentialForMrdMst(UserVO userVO);

	public List getEmployeeListBasedOnDept(String deptCode, UserVO userVO);

	public Map getEssentialForRackMst(UserVO userVO);

	public RackShelfMstVO getRackShelfDetail(RackShelfMstVO rackShelfVO,
			UserVO userVO);

	public boolean modifyRackShelf(RackShelfMstVO rackShelfVO, UserVO userVO);

	public String getRackNameByRackId(String rackId, UserVO userVO);

	public boolean saveMrdDetails(MrdMasterVO mrdMstVO, UserVO userVO);

	public Map getDataForModifyMrdMst(MrdMasterVO mrdMasterVO,
			UserVO userVO);

	public boolean saveModMrdMaster(MrdMasterVO mrdMstVO, UserVO userVO);
	
	public void saveRecordBoundingDetail(List<RecordBoundingVO> lstRecBound,UserVO userVO);
	
	public Map getEssentialForEnclosureMapping(String recordTypeId,UserVO userVO);
	
	public List getEnclosureRecordListNotMapped(String recordTypeId,UserVO userVO);
	
	
	
	public boolean saveReqPurposeMstDetail(RequestPurposeMstVO reqPurposeMstVO, UserVO userVO);
	
	public Map getDataForModifyReqPurposeMst(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO);
	
	public boolean saveModReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO);
	
	public Map getEssentialForReqPurposeMst(UserVO userVO);
	
	public boolean saveMrdCheckListMstDetail(MrdCheckListVO mrdCheckListMstVO, UserVO userVO);
	
	public Map getDataForModifyMrdCheckListMst(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO);
	
	public boolean saveModMrdCheckListMaster(MrdCheckListVO mrdCheckListMstVO, UserVO _UserVO);
	
	public boolean saveEnclosureMstDetail(EnclosureMasterVO enclosureMstVO, UserVO userVO);
	
	public Map getDataForModifyEnclosureMst(EnclosureMasterVO enclosureMstVO, UserVO _UserVO);
	
	public boolean saveModEnclosureMaster(EnclosureMasterVO enclosureMstVO, UserVO _UserVO);
	
	public Map getEssentialForRecordTypeCheckListMst(UserVO userVO);


	public Map getAllAddedCheckListByRecordType(RecordTypeCheckListMstVO vo,UserVO userVO);
	
	public List getCheckListNotMapped(RecordTypeCheckListMstVO recordTypeCheckListVO,UserVO userVO);
	
	public boolean saveRecordTypeCheckListMst(List recordTypeCheckListVOLst, UserVO _UserVO);
	
	/**
	 * get all department Unit List
	 * @param _UserVO
	 * @return
	 */
	public Map getAllDeptUnitList(UserVO _UserVO);

	public Map getTabAccessPolicy(EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO);

	public void saveTabAccessPolicy(List<EprTabAccessDtlVO> insertTabAccessDtlVOList,List<EprTabAccessDtlVO> updateTabAccessDtlVOList, Map userTabIdMap, EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO);

	public Map getUsersForTabAccess(UserVO userVO);	
}
