package ipd.transactions;

import billing.transactions.CashCollectionOfflineTransDAO;
import billing.transactions.CashCollectionOfflineTransVO;

public class AdmissionAdviceTransBO {
	/**
	 * This procedure is used to invoke Dao method to bring data on the main
	 * page
	 * 
	 * @param voObj
	 */
	public void setRequiredValues(AdmissionAdviceTransVO voObj) {

		try {
			AdmissionAdviceTransDAO.checkForCrNo(voObj);
			if (!voObj.getStrMsgType().equals("2"))
			{
				if (voObj.getStrMode().equals("1"))//without desk
					//if(voObj.getStrUnitValue()!=null||!voObj.getStrUnitValue().equals("0"))
					AdmissionAdviceTransDAO.setEpisodeDtl(voObj);
				AdmissionAdviceTransDAO.setMlcNumber(voObj);
				AdmissionAdviceTransDAO.setDepartmentName(voObj);
				AdmissionAdviceTransDAO.setUnitName(voObj);
				AdmissionAdviceTransDAO.setConsultantNameDtl(voObj);
				AdmissionAdviceTransDAO.setAgeandSex(voObj);
				 AdmissionAdviceTransDAO.setWardDtl(voObj);
				AdmissionAdviceTransDAO.setTreatmentCatDtl(voObj);
				AdmissionAdviceTransDAO.setDiseaseType(voObj);
				AdmissionAdviceTransDAO.getProvisionDiagnosisDtl(voObj);
				//AdmissionAdviceTransDAO.getHospitalDiagnosisDtl(voObj);
				//AdmissionAdviceTransDAO.getIcd10DiagnosisDtl(voObj);
				AdmissionAdviceTransDAO.getPrevoiusDiagnosisDtl(voObj);
				AdmissionAdviceTransDAO.setAdmissionTypeValue(voObj);
				AdmissionAdviceTransDAO.setAdvanceAmountDTL(voObj);
				AdmissionAdviceTransDAO.setTariffNameValues(voObj);
				AdmissionAdviceTransDAO.setAccDetails(voObj);
				//AdmissionAdviceTransDAO.getBillingPackageNames(voObj);
				if (voObj.getStrMsgType().equals("1")) {
					throw new Exception(voObj.getStrMsgString());
				}
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("AdmissionAdviceTransBO.setRequiredValues() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * This combo is used to set intial detail for bed status pop up window.
	 * 
	 * @param voObj
	 */
	public void setBedStatusValues(AdmissionAdviceTransVO voObj) {

		try {

			AdmissionAdviceTransDAO.getWardValues(voObj);
			AdmissionAdviceTransDAO.setBedTypeDtl(voObj);
			AdmissionAdviceTransDAO.setRoomTypeDtl(voObj);
			AdmissionAdviceTransDAO.setWardDtlTypes(voObj);
			AdmissionAdviceTransDAO.getRoomValues(voObj);
			if (voObj.isDeptNameFound() == false) {

				AdmissionAdviceTransDAO.setDepartmentNameType(voObj);
			}

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransBO.setBedStatusValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	public void setBedStatusValues1(AdmissionAdviceTransVO voObj) {

		try {

			AdmissionAdviceTransDAO.setBedTypeDtl(voObj);
			AdmissionAdviceTransDAO.setRoomTypeDtl(voObj);
			AdmissionAdviceTransDAO.setWardDtl(voObj);
			AdmissionAdviceTransDAO.setWardDtlTypes(voObj);
			AdmissionAdviceTransDAO.setDepartmentNameType(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransBO.setBedStatusValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	/**
	 * 
	 * @param voObj
	 */
	public void setBedStatusDtls(AdmissionAdviceTransVO voObj) {

		try {

			AdmissionAdviceTransDAO.setBedStatusDtl(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransBO.setBedStatusDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	public void setBedStatusPatientDtls(AdmissionAdviceTransVO voObj) {

		try {

			AdmissionAdviceTransDAO.getBedStatusPatientDtls(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransBO.setBedStatusPatientDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	/**
	 * This procedure is used to set intial details for hospital diagnosis
	 * details display on the main screen
	 * 
	 * @param voObj
	 */
	public void setHospitalDiagnosis(AdmissionAdviceTransVO voObj) {

		try {
			AdmissionAdviceTransDAO.getHospitalDiagnosisDtl(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransBO.setHospitalDiagnosis() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	/**
	 * This procedure is used set initial details for icd diagnosis detail combo
	 * 
	 * @param voObj
	 */
	public void setIcdDiagnosis(AdmissionAdviceTransVO voObj) {

		try {

			AdmissionAdviceTransDAO.getIcd10DiagnosisDtl(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransBO.setIcdDiagnosis() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	/**
	 * This combo is used intial details for ward combo
	 * 
	 * @param vo
	 */
	public void setWardCombo(AdmissionAdviceTransVO vo) {
		try {

			AdmissionAdviceTransDAO.setWardDtl(vo);
			// AdmissionAdviceTransDAO.getWardValues(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {

			vo.setStrMsgString("AdmissionAdviceTransBO.setIcdDiagnosis() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		}
	}

	/**
	 * This procedure is set intial details for room combo
	 * 
	 * @param vo
	 */
	public void setRoomCombo(AdmissionAdviceTransVO vo) {
		try {

			AdmissionAdviceTransDAO.getRoomValues(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {

			vo.setStrMsgString("AdmissionAdviceTransBO.setIcdDiagnosis() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		}
	}

	/**
	 * This procedure is used to invoke dao method to insert data into data base
	 * 
	 * @param voObj
	 */
	public void insert(AdmissionAdviceTransVO voObj) 
	{
		String strTemp = voObj.getStrWard();
		String wardDetails[] = strTemp.trim().replace('^', '#').split("#");

		/*
		 * if (wardDetails[6].equals("0")) {
		 * 
		 * voObj.setStrApprovalStatus("1"); } else {
		 * voObj.setStrApprovalStatus("0"); }
		 */

		try 
		{
			voObj.setStrWard(wardDetails[0]);
			voObj.setStrTreatmentCategoryCode(wardDetails[1]);
			AdmissionAdviceTransDAO.insert(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("AdmissionAdviceTransBO.setBedStatusPatientDtls() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	public void setBedDetail(AdmissionAdviceTransVO vo) {
		try {
			AdmissionAdviceTransDAO.getBedValues(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("AdmissionAdviceTransBO.setBedDetail() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void setListDtls(AdmissionAdviceTransVO voObj) {

		try {
			// AdmissionAdviceTransDAO.setDepartmentName(voObj);
			AdmissionAdviceTransDAO.setUnitName(voObj);

			AdmissionAdviceTransDAO.setListDtl(voObj);

		} catch (Exception e) {

			voObj.setStrMsgString("AdmissionAdviceTransBO.setListDtls() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}

	public void setWardComboBedDetails(AdmissionAdviceTransVO vo) {
		try {
			AdmissionAdviceTransDAO.getWardValues(vo);
		} catch (Exception e) {
			vo
					.setStrMsgString("AdmissionAdviceTransBO.setWardComboBedDetails() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void CheckDuplicate(AdmissionAdviceTransVO vo) {
		try {
			AdmissionAdviceTransDAO.checkDuplicate(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("AdmissionAdviceTransBO.CheckDuplicate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void setDeptComboValues(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) {
		AdmissionAdviceTransDAO
				.setDeptComboValues(_AdmissionAdviceTransVO);
		if (_AdmissionAdviceTransVO.getStrMsgType().equals("1"))
			_AdmissionAdviceTransVO
					.setStrMsgString("AdmissionAdviceTransBO.setDeptComboValues() --> "
							+ _AdmissionAdviceTransVO.getStrMsgString());
	}
	
	public void setUnitComboValues(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) {
		AdmissionAdviceTransDAO
				.setUnitComboValues(_AdmissionAdviceTransVO);
		if (_AdmissionAdviceTransVO.getStrMsgType().equals("1"))
			_AdmissionAdviceTransVO
					.setStrMsgString("AdmissionAdviceTransBO.setUnitComboValues() --> "
							+ _AdmissionAdviceTransVO.getStrMsgString());
	}
	
	public void setAdvnaceAmountValues(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) throws Exception {
		
		AdmissionAdviceTransDAO.getAdvanceAmountDTL(_AdmissionAdviceTransVO);
		if (_AdmissionAdviceTransVO.getStrMsgType().equals("1"))
			_AdmissionAdviceTransVO
					.setStrMsgString("AdmissionAdviceTransBO.setAdvnaceAmountValues() --> "
							+ _AdmissionAdviceTransVO.getStrMsgString());
	}
	
	public void getBillingPackageNames(AdmissionAdviceTransVO VO) {
		AdmissionAdviceTransDAO.getBillingPackageNames(VO);
		if (VO.getStrMsgType().equals("1")) 
			VO.setStrMsgString(" AdmissionAdviceTransBO.getBillingPackageNames() --> "
					+ VO.getStrMsgString());
	}

}
