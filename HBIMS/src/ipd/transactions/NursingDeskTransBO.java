package ipd.transactions;

public class NursingDeskTransBO {

	// call dao function for department combo
	public void department(NursingDeskTransVO VO) {

		NursingDeskTransDAO.department(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strMsg = VO.getStrMsgString();

			VO
					.setStrMsgString(" NursingDeskTransBO.department() --> "
							+ strMsg);
		}

	}

	// call dao function for unit combo
	public void unit(NursingDeskTransVO VO) {

		NursingDeskTransDAO.unit(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strMsg = VO.getStrMsgString();

			VO.setStrMsgString(" NursingDeskTransBO.unit() --> " + strMsg);
		}
	}
	public void unitCombo(NursingDeskTransVO VO) {

		NursingDeskTransDAO.unitCombo(VO);
		NursingDeskTransDAO.getIcuPvtBillStatus(VO);

		// if there is error
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" NursingDeskTransBO.unit() --> " + strMsg);
		}
	}
	
	// call dao function for ward combo
	public void ward(NursingDeskTransVO VO) {

		NursingDeskTransDAO.ward(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strMsg = VO.getStrMsgString();

			VO.setStrMsgString(" NursingDeskTransBO.ward() --> " + strMsg);
		}
	}

	// call dao function for room combo
	public void room(NursingDeskTransVO VO) {

		NursingDeskTransDAO.room(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strMsg = VO.getStrMsgString();

			VO.setStrMsgString(" NursingDeskTransBO.room() --> " + strMsg);
		}
	}

	// call dao function for admit list of patient in hlp file
	public void getAdmitDetail(NursingDeskTransVO VO) {
		NursingDeskTransDAO.admitdetail(VO);
		if (VO.getStrMsgType().equals("1")) 
			VO.setStrMsgString(" NursingDeskTransBO.getAdmitDetail() --> "
					+ VO.getStrMsgString());
	}
	
	
	/*
	 * Used In IPD desk When Acceptance Button is clicked 
	 * 
	 */
	public void getAdmitDetailIPD(NursingDeskTransVO VO) {
		NursingDeskTransDAO.admitdetailIPD(VO);
		if (VO.getStrMsgType().equals("1")) 
			VO.setStrMsgString(" NursingDeskTransBO.getAdmitDetail() --> "
					+ VO.getStrMsgString());
	}
	public void getAdmitDetailIPDNr(NursingDeskTransVO VO) {
		NursingDeskTransDAO.admitdetailIPDNr(VO);
		if (VO.getStrMsgType().equals("1")) 
			VO.setStrMsgString(" NursingDeskTransBO.getAdmitDetailNr() --> "
					+ VO.getStrMsgString());
	}
	
	public void getAdmitDetailIPDLeave(NursingDeskTransVO VO) {
		NursingDeskTransDAO.admitdetailIPDLeave(VO);
		if (VO.getStrMsgType().equals("1")) 
			VO.setStrMsgString(" NursingDeskTransBO.getAdmitDetail() --> "
					+ VO.getStrMsgString());
	}

	// //call dao function for chek list function in hlp file
	public void getChecklistDetail(NursingDeskTransVO vo) {

		NursingDeskTransDAO.checklistdetail(vo);
		// if there is error
		if (vo.getStrMsgType().equals("1")) {
			String strMsg = vo.getStrMsgString();

			vo.setStrMsgString(" NursingDeskTransBO.getChecklistDetail() --> "
					+ strMsg);
		}
	}

	/**
	 * This function is used to invoke NursingDeskTransDAO.getBedValues(vo) to
	 * search beds in a ward and count the no of vacant beds
	 * 
	 * @param vo
	 */
	public void setBedDetails(NursingDeskTransVO vo) {

		NursingDeskTransDAO.getBedValues(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strMsg = vo.getStrMsgString();

			vo.setStrMsgString(" NursingDeskTransBO.setBedDetails() --> "
					+ strMsg);
		}

	}

	// call dao bed detail function for populating bed combo
	public void beddetailcombo(NursingDeskTransVO vo) {
		NursingDeskTransDAO.getBed(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString(" NursingDeskTransBO.beddetailcombo() --> "
					+ vo.getStrMsgString());
	}

	// call dao function for setting admitted bed
	public void admitedbed(NursingDeskTransVO vo) {

		NursingDeskTransDAO.admitedbed(vo);
		if (vo.getStrMsgType().equals("1")) {

			String strMsg = vo.getStrMsgString();

			vo
					.setStrMsgString(" NursingDeskTransBO.admitedbed() --> "
							+ strMsg);
		}
	}

	// call DAO-->save function
	public void save(NursingDeskTransVO vo) {
		NursingDeskTransDAO.save(vo);
		if (vo.getStrMsgType().equals("1")) 
			vo.setStrMsgString(" NursingDeskTransBO.save() --> " + vo.getStrMsgString());
	}
	
	
	public void saveCancel(NursingDeskTransVO vo) {
		NursingDeskTransDAO.saveCancel(vo);
		if (vo.getStrMsgType().equals("1")) 
			vo.setStrMsgString(" NursingDeskTransBO.save() --> " + vo.getStrMsgString());
	}

	// call DAO-->notreport function
	public void notReport(NursingDeskTransVO vo) {
		NursingDeskTransDAO.notreport(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString(" NursingDeskTransBO.notreport() --> "
					+ vo.getStrMsgString());
	}
	public void bed(NursingDeskTransVO VO) 
	{
			try 
			{
				NursingDeskTransDAO.getBedCombo(VO);
				// if there is error
				if (VO.getStrMsgType().equals("1")) 
				{
					throw new Exception(VO.getStrMsgString());
				}
			} 
			catch (Exception e) 
			{
				VO.setStrMsgString(" NursingDeskTransBO.bed() --> "+ e.getMessage());
				VO.setStrMsgType("1");
			}
	}
	
	
	public void BEDCOMBOAJAX(NursingDeskTransVO VO) {

		NursingDeskTransDAO.BEDCOMBOAJAX(VO);
		// if there is error
		if (VO.getStrMsgType().equals("1")) {

			String strMsg = VO.getStrMsgString();

			VO.setStrMsgString(" NursingDeskTransBO.bed() --> " + strMsg);
		}
	}
	public void setConsultantName(NursingDeskTransVO VO) 
	{
			try 
			{
				NursingDeskTransDAO.setConsultantName(VO);
				// if there is error
				if (VO.getStrMsgType().equals("1")) 
				{
					throw new Exception(VO.getStrMsgString());
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				VO.setStrMsgString(" NursingDeskTransBO.setConsultantName() --> "+ e.getMessage());
				VO.setStrMsgType("1");
			}
	}
	public void getConsultantName(NursingDeskTransVO VO) 
	{
			try 
			{
				NursingDeskTransDAO.getConsultantName(VO);
				// if there is error
				if (VO.getStrMsgType().equals("1")) 
				{
					throw new Exception(VO.getStrMsgString());
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				VO.setStrMsgString(" NursingDeskTransBO.setConsultantName() --> "+ e.getMessage());
				VO.setStrMsgType("1");
			}
	}

}
