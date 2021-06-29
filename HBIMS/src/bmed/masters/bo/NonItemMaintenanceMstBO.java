package bmed.masters.bo;

import bmed.masters.dao.NonItemMaintenanceMstDAO;
import bmed.masters.vo.NonItemMaintenanceMstVO;

public class NonItemMaintenanceMstBO {

	public void initializeAdd(NonItemMaintenanceMstVO vo) {

		NonItemMaintenanceMstDAO.initializeAdd(vo);
		//NonItemMaintenanceMstDAO.setWrsBuildingCodeOptions(vo);
		//NonItemMaintenanceMstDAO.setWrsMaintenanceIdOptions(vo);
		NonItemMaintenanceMstDAO.setWrsMaintenancePeriodUnitIdOptions(vo);
		NonItemMaintenanceMstDAO.setWrsDeptIdOptionsOptions(vo);
		NonItemMaintenanceMstDAO.setWrsNonItemOptions(vo);

		if (vo.getStrMsgType() != null && vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("NonItemMaintenanceMstBO.initializeAdd(vo)--> "
					+ vo.getStrMsgString());

		}

	}

//	public void setBlockCodeOptions(NonItemMaintenanceMstVO vo)
//			throws Exception {
//
//		try {
//			NonItemMaintenanceMstDAO.setWrsBlockCodeOptions(vo);
//		} catch (Exception ex) {
//			throw new Exception(
//					"NonItemMaintenanceMstBO.setBlockCodeOptions(vo)--> "
//							+ ex.getMessage());
//		}
//
//	}

//	public void setFloorIdOptions(NonItemMaintenanceMstVO vo) throws Exception {
//		try {
//			NonItemMaintenanceMstDAO.setWrsFloorIdOptions(vo);
//		} catch (Exception ex) {
//			throw new Exception(
//					"NonItemMaintenanceMstBO.setFloorIdOptions(vo)--> "
//							+ ex.getMessage());
//		}
//
//	}

//	public void setRoomIdOptions(NonItemMaintenanceMstVO vo) throws Exception {
//		try {
//			NonItemMaintenanceMstDAO.setWrsRoomIdOptions(vo);
//		} catch (Exception ex) {
//			throw new Exception(
//					"NonItemMaintenanceMstBO.setRoomIdOptions(vo)--> "
//							+ ex.getMessage());
//		}
//
//	}

	public void setTaskOptions(NonItemMaintenanceMstVO vo) throws Exception {
		try {
			NonItemMaintenanceMstDAO.setWrsLeftTaskIdOptions(vo);
			NonItemMaintenanceMstDAO.setWrsRightTaskIdOptions(vo);
		} catch (Exception ex) {
			throw new Exception(
					"NonItemMaintenanceMstBO.setTaskOptions(vo)--> "
							+ ex.getMessage());
		}

	}

	public void insert(NonItemMaintenanceMstVO vo) throws Exception {

		try {
			NonItemMaintenanceMstDAO.checkDuplicate(vo);

			if (vo.isfExistStatus()) {
				vo.setStrWarningMsg("Data Already Exists!");
				
			} else {
				NonItemMaintenanceMstDAO.insert(vo);
				vo.setStrNormalMsg("Data Successfully Inserted.");
			}
		} catch (Exception e) {
			throw new Exception("NonItemMaintenanceMstBO.insert(vo)--> "
					+ e.getMessage());
		}


	}

	public void setMaintenanceOptions(NonItemMaintenanceMstVO vo)
			throws Exception {
		try {
			NonItemMaintenanceMstDAO.setWrsMaintenanceIdOptions(vo);
		} catch (Exception ex) {
			throw new Exception(
					"NonItemMaintenanceMstBO.setTaskOptions(vo)--> "
							+ ex.getMessage());
		}

	}

	public void initializeModify(NonItemMaintenanceMstVO vo) throws Exception {

		try {
			NonItemMaintenanceMstDAO.initializeModify(vo);
			NonItemMaintenanceMstDAO.setWrsDeptIdOptionsOptions(vo);
			NonItemMaintenanceMstDAO.setWrsMaintenancePeriodUnitIdOptions(vo);
			NonItemMaintenanceMstDAO.setWrsLeftTaskIdOptions(vo);
			NonItemMaintenanceMstDAO.setWrsRightTaskIdOptions(vo);
		} catch (Exception ex) {
			throw new Exception(
					"NonItemMaintenanceMstBO.initializeModify(vo)--> "
							+ ex.getMessage());
		}

	}

	public void update(NonItemMaintenanceMstVO vo) throws Exception {
		try {
			NonItemMaintenanceMstDAO.update(vo);
		
		} catch (Exception ex) {
			throw new Exception("NonItemMaintenanceMstBO.update(vo)--> "
					+ ex.getMessage());
		}

	}

	public void initializeView(NonItemMaintenanceMstVO vo) throws Exception {
		try {
			NonItemMaintenanceMstDAO.initializeView(vo);
		} catch (Exception ex) {
			throw new Exception(
					"NonItemMaintenanceMstBO.initializeView(vo)--> "
							+ ex.getMessage());
		}

	}

}
