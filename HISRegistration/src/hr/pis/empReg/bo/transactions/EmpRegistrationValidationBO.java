package hr.pis.empReg.bo.transactions;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.UserVO;
import hr.pis.common.dao.masters.CadreDAO;
import vo.hr.pis.common.masters.CadreVO;

public class EmpRegistrationValidationBO {

	public boolean saveCadreDtl(CadreVO objVOCadre_p, String strMode_p,
			UserVO userVo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		// boolean bExistStatus = true;
		boolean bExistStatus = CadreDAO.chkCadreDuplicate(objVOCadre_p,
				strMode_p, hisDAO, userVo);

		if (objVOCadre_p.getStrMsgType() != null
				&& objVOCadre_p.getStrMsgType().equals("1")) {

			String strErr = objVOCadre_p.getStrMsgString();

			objVOCadre_p.setStrMsgString("PisMasterBO.insertQuery() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			CadreDAO.saveCadreDetails(objVOCadre_p, strMode_p, hisDAO, userVo);

			if (objVOCadre_p.getStrMsgType() != null
					&& objVOCadre_p.getStrMsgType().equals("1")) {

				String strErr = objVOCadre_p.getStrMsgString();

				objVOCadre_p.setStrMsgString("PisMasterBO.insertQuery() --> "
						+ strErr);
			}
		}
		return bExistStatus;
	}

	public boolean updateCadreDtl(CadreVO objVOCadre_p, String strMode_p,
			UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");

		hisDAO = new HisDAO("Reg", "regbo");
		// boolean bExistStatus = true;
		boolean bExistStatus = CadreDAO.chkCadreDuplicate(objVOCadre_p,
				strMode_p, hisDAO, uservo);

		if (objVOCadre_p.getStrMsgType() != null
				&& objVOCadre_p.getStrMsgType().equals("1")) {

			String strErr = objVOCadre_p.getStrMsgString();

			objVOCadre_p.setStrMsgString("PisMasterBO.updateCadreDtl() --> "
					+ strErr);
		}
		if (bExistStatus == true) {
			CadreDAO.updateRequestStatus(objVOCadre_p, hisDAO, uservo);

			if (objVOCadre_p.getStrMsgType() != null
					&& objVOCadre_p.getStrMsgType().equals("1")) {

				String strErr = objVOCadre_p.getStrMsgString();

				objVOCadre_p
						.setStrMsgString("PisMasterBO.updateCadreDtl() --> "
								+ strErr);
			}
		}
		return bExistStatus;
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 */
	public CadreVO modifyRecordCadreMst(CadreVO vo) {
		System.out.println("PisMasterBO>> modifyRecordCadreMst");
		CadreVO OccupationVO_p = null;
		try {
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			OccupationVO_p = CadreDAO.modifyRecord(vo, hisDAO);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("pisMasterBO.modifyRecord(vo) --> "
						+ vo.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return OccupationVO_p;
	}

}
