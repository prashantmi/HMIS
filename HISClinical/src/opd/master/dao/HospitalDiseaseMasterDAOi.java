package opd.master.dao;


import hisglobal.vo.HospitalDiseaseMasterVO;
import hisglobal.vo.UserVO;

public interface HospitalDiseaseMasterDAOi {

	public void create(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
		
	public boolean checkDuplicateBeforeSave(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
		
	public HospitalDiseaseMasterVO fetchHospitalDiseaseModify(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
		
	public boolean  checkDuplicateBeforeModifySave(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
		
	public void modify(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
		
	public void modifyInsert(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
	
	public void MapICD(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
	
	public void mapSnomed(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);
	
	public void modifyMapping(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO);

	}


