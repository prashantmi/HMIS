package new_opd.bo;

import new_opd.DAO.OPDTemplateMstDAO;
import new_opd.vo.OPDTemplateMstVO;

public class OPDTemplateMstBO {

	public void getDeptCodeList(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.getDeptDtl(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
	
	public void getTemplateCatCMB(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.gettmpateCatCMB(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
	public void getTemplateListingData(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.getTemlateListingData(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
	public void saveData(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.SaveData(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
	
	public void Modifysave(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.ModifySave(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
	
	public void getTemplateNo(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.getTemplateNo(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}

	public void GETPARAMETER(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.GETPARAMETER(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.GETPARAMETER() --> "
					+ vo.getStrMsgString());
		}
	}

	public void GETDRUG(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.GETDRUG(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.GETPARAMETER() --> "
					+ vo.getStrMsgString());
		}
	}
	
	
	public void GETTEST(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.GETTEST(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.GETPARAMETER() --> "
					+ vo.getStrMsgString());
		}
	}
	
	public void GETICD(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.GETICD(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.GETPARAMETER() --> "
					+ vo.getStrMsgString());
		}
	}
	
	
	public void GETSITE(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.GETSITE(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.GETPARAMETER() --> "
					+ vo.getStrMsgString());
		}
	}
	
	public void getAutoCompletData(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.getAutoCompleteData(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
	}

	public void getTempCatList(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		OPDTemplateMstDAO.getTempCatDtl(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
	public void getDeleteRecord(OPDTemplateMstVO vo) {
		OPDTemplateMstDAO.getDeleteRecord(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("OPDTemplateMstBO.getDeptCodeList() --> "
					+ vo.getStrMsgString());
		}
		
	}
}
