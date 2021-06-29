package bmed.global.bo;

import bmed.global.dao.MainteWarrantyContractDAO;
import bmed.global.vo.MainteWarrantyContractVO;

public class MainteWarrantyContractBO {

	public void getData(MainteWarrantyContractVO mainteWarrantyContractVO_p) throws Exception {
		try {
			MainteWarrantyContractDAO.getWarrantyData(mainteWarrantyContractVO_p);
			MainteWarrantyContractDAO.getContractData(mainteWarrantyContractVO_p);
		} catch (Exception e) {
			throw new Exception("MainteWarrantyContractBO-->getData"+e.getMessage());
		}
		
	}

}
