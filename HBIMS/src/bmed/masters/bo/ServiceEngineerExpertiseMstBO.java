package bmed.masters.bo;

import bmed.masters.dao.ServiceEngineerExpertiseMstDAO;
import bmed.masters.vo.ServiceEngineerExpertiseMstVO;

public class ServiceEngineerExpertiseMstBO {

	public void initializeAdd(ServiceEngineerExpertiseMstVO vo)
			throws Exception {

		try {
			/* DAO Methods calling */
			ServiceEngineerExpertiseMstDAO.initializeAdd(vo);
			ServiceEngineerExpertiseMstDAO.setWrsAvailableExpertiseOptions(vo);
		} catch (Exception e) {
			throw new Exception(
					"ServiceEngineerExpertiseMstBO.initializeAdd(vo)--> "
							+ e.getMessage());
		}
	}

	public void initializeModify(ServiceEngineerExpertiseMstVO vo)
			throws Exception {

		try {
			ServiceEngineerExpertiseMstDAO.initializeModify(vo);

		} catch (Exception ex) {
			throw new Exception(
					"ServiceEngineerExpertiseMstBO.initializeModify(vo)--> "
							+ ex.getMessage());
		}

	}

	public void insert(ServiceEngineerExpertiseMstVO vo) throws Exception {

		try {
			ServiceEngineerExpertiseMstDAO.checkDuplicateInsert(vo);

			if (vo.isfExistStatus()) {

				vo.setStrWarningMsg("Data already exists.");
				vo.setfSaveSuccessfull(false);

			} else {

				ServiceEngineerExpertiseMstDAO.insert(vo);
				vo.setStrNormalMsg("Data successfully inserted.");
				vo.setfSaveSuccessfull(true);

			}
		} catch (Exception e) {
			vo.setfSaveSuccessfull(false);
			throw new Exception("ServiceEngineerExpertiseMstBO.insert(vo)--> "
					+ e.getMessage());
		}

	}

	public void update(ServiceEngineerExpertiseMstVO vo) throws Exception {

		try {

			ServiceEngineerExpertiseMstDAO.checkDuplicateModify(vo);

			if (vo.isfExistStatus()) {

				vo.setStrWarningMsg("Data already exists.");
				vo.setfSaveSuccessfull(false);

			} else {

				ServiceEngineerExpertiseMstDAO.update(vo);
				vo.setStrNormalMsg("Data has been success fully modified.");
				vo.setfSaveSuccessfull(true);

			}
		} catch (Exception e) {
			vo.setfSaveSuccessfull(false);
			throw new Exception("ServiceEngineerExpertiseMstBO.update(vo)--> "
					+ e.getMessage());
		}
	}

}
