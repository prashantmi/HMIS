package ipd.transactions;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

public class MsApprovalTransBO {

	public void DEPTUNIT(MsApprovalTransVO VO) {

		// MsApprovalTransDAO.demographicdtl(VO);
		MsApprovalTransDAO.diagnosisdtl(VO);
		MsApprovalTransDAO.consultant(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.DEPTUNIT() --> " + strErr);
		}

	}

	public void status(MsApprovalTransVO _msApprovalTransVO)throws Exception {
		MsApprovalTransDAO.patstatus(_msApprovalTransVO);
		MsApprovalTransDAO.setEpisodeDtl(_msApprovalTransVO);
		
		MsApprovalTransDAO.setDepartmentName(_msApprovalTransVO);
		MsApprovalTransDAO.setUnitName(_msApprovalTransVO);
		
		MsApprovalTransDAO.setAgeandSex(_msApprovalTransVO);
		MsApprovalTransDAO.setWardDtl(_msApprovalTransVO);
		MsApprovalTransDAO.setTreatmentCatDtl(_msApprovalTransVO);
		if(_msApprovalTransVO.getIsOffline().equals("1"))
			MsApprovalTransDAO.select(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.status() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	// //provide ordered list of approved patient
	public void PATLIST(MsApprovalTransVO VO) {

		MsApprovalTransDAO.popupallotementlist(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.PATLIST() --> " + strErr);
		}
	}

	public void go(MsApprovalTransVO VO) {

		MsApprovalTransDAO.patstatus(VO);

		MsApprovalTransDAO.deptunit(VO);

		MsApprovalTransDAO.msApprovalDetails(VO);
		MsApprovalTransDAO.seatUser(VO);

		// String category = VO.getStrPatCategory();

		MsApprovalTransDAO.mlcdtl(VO);// mlc proc to be
		MsApprovalTransDAO.updatemode(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.go() --> " + strErr);
		}
	}

	// VIEW PAGE
	public void view(MsApprovalTransVO VO) {

		MsApprovalTransDAO.patstatus(VO);
		MsApprovalTransDAO.msApprovalDetails(VO);
		MsApprovalTransDAO.viewconsultant(VO);
		MsApprovalTransDAO.seatUser(VO);

		// String category = VO.getStrPatCategory();

		MsApprovalTransDAO.mlcdtl(VO);// mlc proc to be
		//MsApprovalTransDAO.viewdiagnosisdtl(VO);
		MsApprovalTransDAO.viewapprove(VO);
		MsApprovalTransDAO.updatemode(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.view() --> " + strErr);
		}
	}

	public void cancelapproval(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.cancelapproval(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.cancelapproval() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void insert(MsApprovalTransVO _msApprovalTransVO) {
		if (!_msApprovalTransVO.getStrApprovedStatus().equals("0"))
			MsApprovalTransDAO.insert(_msApprovalTransVO);
		else
			MsApprovalTransDAO.update(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.insert() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	// upadte approval status as approved
	public void updatemsapproval(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.updatemsapproval(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.updatemsapproval() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void savewardallotement(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.savewardallotement(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.savewardallotement() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void MinReq(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.MinReq(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.MinReq() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void approvedby(MsApprovalTransVO VO) {

		MsApprovalTransDAO.seatUser(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.approvedby() --> " + strErr);
		}

	}

	// call msapprovalList function in dao
	public void msapprovalList(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.msapprovalList(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.msapprovalList() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	// Provide list of Private Ward in which ms approval is required and vacant
	// bed are available
	public void privateWard(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.privateward(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.privateWard() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}
/*
	public void prioritydata(MsApprovalTransVO VO) {

		MsApprovalTransDAO.msApprovalDetails(VO);
		MsApprovalTransDAO.deptunit(VO);
		MsApprovalTransDAO.privateward(VO);
		if (VO.getStrRoomValue() != "")
			MsApprovalTransDAO.roomdetail(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.prioritydata() --> "
					+ strErr);
		}
	}
*/
	public void cancel(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.msApprovalDetails(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.cancel() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void privateroom(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.room(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.privateroom() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	// provide list of vacant bed on the basis of ward and room
	public void bed(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.bed(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO.setStrMsgString(" MsApprovalTransBO.bed() --> "
					+ _msApprovalTransVO.getStrMsgString());
	}

	// Provide list of room on the basis of ward and in which vacant bed are
	// available
	public void room(MsApprovalTransVO VO) {
		// System.out.println("inside room");

		MsApprovalTransDAO.room(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.room() --> " + strErr);
		}
	}

	// bed details like ward type,ward code,room type, bed type ,bed code
	public void beddetail(MsApprovalTransVO VO) {

		MsApprovalTransDAO.beddetail(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.beddetail() --> " + strErr);
		}
	}
/*
	public void roomdetail(MsApprovalTransVO VO) {

		MsApprovalTransDAO.roomdetail(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.roomdetail() --> " + strErr);
		}
	}
*/
	/*
	 * this function will list patient list for not approval case
	 */

	public ArrayList<String> notapprovedlist(MsApprovalTransVO VO) {

		WebRowSet ws1 = null;
		ArrayList<String> strnotApprovepatList = null;
		try {
			ws1 = MsApprovalTransDAO.notapprovedlist(VO);
			strnotApprovepatList = new ArrayList<String>(ws1.size());
			int nTotalPatient = ws1.size();

			for (int i = 0; i < nTotalPatient; i++) {

				ws1.next();

				strnotApprovepatList.add(ws1.getString(1));
				strnotApprovepatList.add(ws1.getString(2));
				strnotApprovepatList.add(ws1.getString(3));
				strnotApprovepatList.add(ws1.getString(4));

			}

			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrMsgString());
			}
		} catch (Exception e) {
			VO.setStrMsgString(" MsApprovalTransBO.notapprovedlist() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		}
		return strnotApprovepatList;
	}

	/*
	 * this function will list patient list for cancel case
	 */
/*
	public ArrayList<String> cancellist(MsApprovalTransVO VO) {

		WebRowSet ws1 = null;
		ArrayList<String> strcancelList = null;
		try {
			ws1 = MsApprovalTransDAO.strcancelList(VO);
			strcancelList = new ArrayList<String>(ws1.size());
			int nTotalPatient = ws1.size();

			for (int i = 0; i < nTotalPatient; i++) {

				ws1.next();

				strcancelList.add(ws1.getString(1));
				strcancelList.add(ws1.getString(2));
				strcancelList.add(ws1.getString(3));
				strcancelList.add(ws1.getString(4));

			}

			// if there is error
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrMsgString());
			}
		} catch (Exception e) {
			VO.setStrMsgString(" MsApprovalTransBO.strcancelList() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
		}
		return strcancelList;
	}
*/
	public boolean generateApproveList(MsApprovalTransVO VO) {
		boolean fretvalue = true;

		MsApprovalTransDAO dao = new MsApprovalTransDAO();
		fretvalue = dao.generateApproveList(VO);

		if (VO.getStrMsgType().equals("1")) {

			String strErr = VO.getStrMsgString();
			VO.setStrMsgString(" MsApprovalTransBO.generateApproveList() --> "
					+ strErr);
		}
		return fretvalue;
	}

	public void getDataFromPatAdmission(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.getDataFromPatAdmission(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.getDataFromPatAdmission() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}
	
	public void getDataFromMSApproval(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.getDataFromMSApproval(_msApprovalTransVO);
		MsApprovalTransDAO.getProvisionalDiagnosis(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.getDataFromMSApproval() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	/**
	 * This function invoke MsApprovalTransDAO's getDataFromBooking to bring details from admission advice table
	 * @param _msApprovalTransVO
	 */	
	public void getDataFromBooking(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.getDataFromBooking(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("4"))
				
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.getDataFromBooking() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void getEmployeeCombo(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.getEmployeeCombo(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.getEmployeeCombo() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void getFormDetails(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.getFormDetails(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.getFormDetails() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}

	public void verifyCrNo(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.verifyCrNo(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO.setStrMsgString(" MsApprovalTransBO.verifyCrNo() --> "+ _msApprovalTransVO.getStrMsgString());
	}

	public void patApprovedList(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.patApprovedList(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.patApprovedList() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}
	
	public void getListValues(MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO.getListValues(_msApprovalTransVO);

		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString(" MsApprovalTransBO.patApprovedList() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}
	public void setUnitComboValues(
			MsApprovalTransVO _msApprovalTransVO) {
		MsApprovalTransDAO
				.setUnitComboValues(_msApprovalTransVO);
		if (_msApprovalTransVO.getStrMsgType().equals("1"))
			_msApprovalTransVO
					.setStrMsgString("MsApprovalTransBO.setUnitComboValues() --> "
							+ _msApprovalTransVO.getStrMsgString());
	}
	public void CheckDuplicate(MsApprovalTransVO vo) {
		try {
			MsApprovalTransDAO.checkDuplicate(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("AdmissionAdviceTransBO.CheckDuplicate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void setBedStatusValues(MsApprovalTransVO voObj) {

		try {

			MsApprovalTransDAO.getWardValues(voObj);
			MsApprovalTransDAO.setBedTypeDtl(voObj);
			MsApprovalTransDAO.setRoomTypeDtl(voObj);
			MsApprovalTransDAO.setWardDtlTypes(voObj);
			MsApprovalTransDAO.getRoomValues(voObj);
			if (voObj.isDeptNameFound() == false) {

				MsApprovalTransDAO.setDepartmentNameType(voObj);
			}

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("MsApprovalTransBO.setBedStatusValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}
	public void setWardCombo(MsApprovalTransVO vo) {
		try {

			MsApprovalTransDAO.setWardDtl(vo);
			// AdmissionAdviceTransDAO.getWardValues(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {

			vo.setStrMsgString("MsApprovalTransBO.setIcdDiagnosis() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		}
	}
	public void setDeptComboValues(
			MsApprovalTransVO vo) {
		MsApprovalTransDAO
				.setDeptComboValues(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("MsApprovalTransBO.setDeptComboValues() --> "
							+ vo.getStrMsgString());
	}
}
