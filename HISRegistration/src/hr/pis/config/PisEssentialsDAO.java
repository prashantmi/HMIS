/** Module Name: PIS
*  Name of Process: PIS Global Configuration 
*  Name of Developer: Sh. Ashwini Mishra 
*  Name of TL: Sh. Ashwini Mishra 
*  Date of Creation: 01-08-2014
*  Last Modifier: Sh. Ashwini Mishra
*  Last Modification Date: 02-03-2015
*  
*/

package hr.pis.config;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;


import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PisEssentialsDAO extends DataAccessObject {

	public PisEssentialsDAO(TransactionContext _transactionContext) {
		super(_transactionContext);
	}
	
		/**
	 * Retrieves data for Appellation Combo.
	 * @param	_calMode provides calling mode, _query provides alternate Query,  _userVO	provides user details, _filterParam1 provides filter Parameter, _filterParam2 provides filter Parameter
	 * @return	List of the Appellation
	 */
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getAppellation(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_APPELLATION_COMBO);
	}
					
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getAppellationFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_APPELLATION_COMBO);
	}
		
		
	/**
	 * Retrieves data for Suffix Combo.
	 * @param	_calMode provides calling mode, _query provides alternate Query,  _userVO	provides user details, _filterParam1 provides filter Parameter, _filterParam2 provides filter Parameter
	 * @return	List of the Sufix 
	 */
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getSuffix(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SUFFIX_COMBO);
	}
						
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getSuffixFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SUFFIX_COMBO);
	}
		
	
	// Added by Ashwini Mishra on 01-Aug-2014
	/**
	 * Retrieves data for Last Employment Type Combo.
	 * @param	_calMode provides calling mode, _query provides alternate Query,  _userVO	provides user details, _filterParam1 provides filter Parameter, _filterParam2 provides filter Parameter
	 * @return	List of the Last Employment Type
	 */
	public List<Entry> getLastEmploymentType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LAST_EMPLOYEMENT_TYPE_COMBO);
	}
							
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getLastEmploymentTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LAST_EMPLOYEMENT_TYPE_COMBO);
	}
		
	// Added by Ashwini Mishra on 01-Aug-2014
	/**
	 * Retrieves data for Event Combo. 
	 * @param	_calMode provides calling mode, _query provides alternate Query,  _userVO	provides user details, _filterParam1 provides filter Parameter, _filterParam2 provides filter Parameter
	 * @return	List of the Event
	 */	
	public List<Entry> getEvent(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EVENT_COMBO);
	}
							
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getEventFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EVENT_COMBO);
	}
	
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getGender(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_GENDER_COMBO);
	}
								
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getGenderFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_GENDER_COMBO);
	}
		
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getMaritalStatus(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MARITAL_STATUS_COMBO);
	}
								
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getMaritalStatusFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MARITAL_STATUS_COMBO);
	}
			
		
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getBloodGrp(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_BLOOD_GRP_COMBO);
	}
								
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getBloodGrpFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_BLOOD_GRP_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public List<Entry> getReligion(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RELIGION_COMBO);
	}
								
	// Added by Ashwini Mishra on 08-Sep-2014
	public String getReligionFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RELIGION_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public List<Entry> getRelation(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RELATION_COMBO);
	}
		
	// Added by Ashwini Mishra on 08-Sep-2014
	public String getRelationFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RELATION_COMBO);
	}
	
	//
	public List<Entry> getMedCat(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PATIENT_CATEGORY_COMBO);
	}
	
	
	public String getMedCatFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PATIENT_CATEGORY_COMBO);
	}
			
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public List<Entry> getOccupation(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_OCCUPATION_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public String getOccupationFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_OCCUPATION_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public List<Entry> getCaste(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CASTE_COMBO);
	}
											
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public String getCasteFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CASTE_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 08-Sep-2014
	public List<Entry> getNationality(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_NATIONALITY_COMBO);
	}
													
	// Added by Ashwini Mishra on 08-Sep-2014
	public String getNationalityFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_NATIONALITY_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 29-Sep-2014
	public List<Entry> getHandicapType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_HANDICAP_TYPE_COMBO);
	}
													
	
	// Added by Ashwini Mishra on 29-Sep-2014
	public String getHandicapTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_HANDICAP_TYPE_COMBO);
	}
	
		
	// Added by Ashwini Mishra on 03-Mar-2015
	public List<Entry> getSalaryTypeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SALARY_TYPE_COMBO);
	}
										
	// Added by Ashwini Mishra on 03-Mar-2015
	public String getSalaryTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SALARY_TYPE_COMBO);
	}
				
		
	// Added by Ashwini Mishra on 24-Apr-2015
	public List<Entry> getCatRevCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CAT_REV_COMBO);
	}
											
	// Added by Ashwini Mishra on 24-Apr-2015
	public String getCatRevFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CAT_REV_COMBO);
	}
	
	// Added by Ashwini Mishra on 24-Apr-2015
	public List<Entry> getPayScaleCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PAY_SCALE_COMBO);
	}
												
	// Added by Ashwini Mishra on 24-Apr-2015
	public String getPayScaleFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PAY_SCALE_COMBO);
	}
	
	/**
	 * Retrieves data for Nature of Job Combo.
	 * @param	_calMode provides calling mode, _query provides alternate Query,  _userVO	provides user details, _filterParam1 provides filter Parameter, _filterParam2 provides filter Parameter
	 * @return	List of the Nature of Job
	 */
	// Added by Ashwini Mishra on 08-Sep-2014
	public List<Entry> getNatureOfJob(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_NATURE_OF_JOB_COMBO);
	}
											
	// Added by Ashwini Mishra on 08-Sep-2014
	public String getNatureOfJobFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_NATURE_OF_JOB_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 29-Sep-2014
	public List<Entry> getRecruitmentSrc(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RECRUITMENT_SRC_COMBO);
	}
												
	// Added by Ashwini Mishra on 29-Sep-2014
	public String getRecruitmentSrcFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RECRUITMENT_SRC_COMBO);
	}
		
	
	// Added by Ashwini Mishra on 17-Mar-2015
	public List<Entry> getPayScRevisionCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_PS_REVISION_COMBO);
		//return getComboList(_userVO.getHospitalCode(), strMode_p, PisDaoConfig.PROCEDURE_PS_CATEGORY_REVISION_COMBO);
	}

	// Added by Ashwini Mishra on 17-Mar-2015
	public String getPayScRevisionFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_PS_REVISION_COMBO);
	}
	
	//Process Field Value
	public List<Entry> getProcFieldValueCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_PROC_FIELD_VAL_COMBO);
		//return getComboList(_userVO.getHospitalCode(), strMode_p, PisDaoConfig.PROCEDURE_PS_CATEGORY_REVISION_COMBO);
	}
	
	//Added by Navneet Upadhyuaya May 02, 2016
	public String getEmployeeCertificateFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EMPLOYEE_CERTIFICATE_TYPE_COMBO);
	}
	
	//Added by Navneet Upadhyuaya May 02, 2016
	
	public List<Entry> getEmpCertificationType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EMPLOYEE_CERTIFICATE_TYPE_COMBO);
	}
	
	
	//Added by Navneet Upadhyuaya May 19, 2016
	public List<Entry> getCertificateNames(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SB_CERTIFICATE_NAMES_COMBO);
	}
	
	
	// Added by Manoj Kumar Singh on 16-Jun-2016
		public List<Entry> getResearchPaperVenue(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RESEARCH_PAPER_VENUE_COMBO);
		}
		
		public List<Entry> getResearchPaperVenueClassification(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RESEARCH_PAPER_VENUE_CLASSIFICATION_COMBO);
		}
		
		
		// Added by Kanika on 16-Dec-2016
				public List<Entry> getAwardType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_AWARD_TYPE);
				}
				
				public List<Entry> getAwardLevel(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_AWARD_LEVEL);
				}
		
	// Added by Manoj Kumar Singh on 16-Jun-2016
		public String getResearchPaperVenueFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RESEARCH_PAPER_VENUE_COMBO);
		}
		
		public String getResearchPaperVenueClassificationFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_RESEARCH_PAPER_VENUE_CLASSIFICATION_COMBO);
		}
			
	
	public List<Entry> getEmpOff(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		List alRecord = new ArrayList();
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_EMP_OFFICE_COMBO);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			strProc.addInParameter(3, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(4, Types.VARCHAR, "MS");
			strProc.addInParameter(5, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(6, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(7, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(9, Types.VARCHAR);
			strProc.addOutParameter(10, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(9);
			rs = (ResultSet) strProc.getParameterAt(10);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getEmpOff()" + e);
		}
		
		return alRecord;
	}
	
	
	public String getEmpOffFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		String alRecord = "";
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_EMP_OFFICE_COMBO);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			strProc.addInParameter(3, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(4, Types.VARCHAR, "MS");
			strProc.addInParameter(5, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(6, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(7, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(9, Types.VARCHAR);
			strProc.addOutParameter(10, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(9);
			rs = (ResultSet) strProc.getParameterAt(10);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsinFilterFormat(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getEmpOffFilter()" + e);
		}
		
		return alRecord;
	}
	
		
	// Added by Ashwini Mishra on 23-Jul-2014
		public List<Entry> getEstbSec(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ESTB_SEC_COMBO);
		}
													
		// Added by Ashwini Mishra on 23-Jul-2014
		public String getEstbSecFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ESTB_SEC_COMBO);
		}
		
	
	
	// Added by Ashwini Mishra on 03-Mar-2015
	public List<Entry> getSerGrp(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SER_GRP_COMBO);
	}
						
	// Added by Ashwini Mishra on 03-Mar-2015
	public String getSerGrpFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SER_GRP_COMBO);
	}
			
		
	// Added by Ashwini Mishra on 23-Jul-2014
		public List<Entry> getDesignation(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DESIG_COMBO);
		}
							
		// Added by Ashwini Mishra on 23-Jul-2014
		public String getDesignationFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DESIG_COMBO);
		}
		
		// Added by Ashwini Mishra on 26-Aug-2014
		public List<Entry> getDepartmentType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DEPT_TYPE_COMBO);
		}
							
		// Added by Ashwini Mishra on 26-Aug-2014
		public String getDepartmentTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DEPT_TYPE_COMBO);
		}
		
		// Added by Somya Tanwar on 2-June-2015
//		public List<Entry> getLocation(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
//			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LOCATION_COMBO);
//		}
	
		// Added by Ashwini Mishra on 24-Jul-2014
		public List<Entry> getDepartment(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DEPT_COMBO);
		}
							
		// Added by Ashwini Mishra on 24-Jul-2014
		public String getDepartmentFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DEPT_COMBO);
		}
		

		// Added by Ashwini Mishra on 24-Jul-2014
		public List<Entry> getCadre(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CADRE_COMBO);
		}
							
		// Added by Ashwini Mishra on 24-Jul-2014
		public String getCadreFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CADRE_COMBO);
		}
		
	
	
	// Added by Ashwini Mishra on 03-Mar-2015
	public List<Entry> getEmpClass(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EMP_CLASS_COMBO);
	}
						
	// Added by Ashwini Mishra on 03-Mar-2015
	public String getEmpClassFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EMP_CLASS_COMBO);
		}
	
	
	public List<Entry> getStatus(String _calMode, String _query,  UserVO _userVO, String _filterParam1, String _filterParam2)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		List alRecord = new ArrayList();
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_STATUS_COMBO);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			strProc.addInParameter(3, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(4, Types.VARCHAR, "STA");
			strProc.addInParameter(5, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(6, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(7, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(9, Types.VARCHAR);
			strProc.addOutParameter(10, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(9);
			rs = (ResultSet) strProc.getParameterAt(10);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getStatus()" + e);
		}
		
		return alRecord;
	}
	
	
	public List<Entry> getFinalStatus(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		List alRecord = new ArrayList();
		try
		{
			Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_FINAL_STATUS_COMBO);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			strProc.addInParameter(3, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(4, Types.VARCHAR, "FST");
			strProc.addInParameter(5, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(6, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(7, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(9, Types.VARCHAR);
			strProc.addOutParameter(10, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(9);
			rs = (ResultSet) strProc.getParameterAt(10);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getFinalStatus()" + e);
		}
		
		return alRecord;
	}
	
	
	// Added by Ashwini Mishra on 27-Aug-2014
	public List<Entry> getLanguage(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LANGUAGE_COMBO);
	}
							
	// Added by Ashwini Mishra on 27-Aug-2014
	public String getLanguageFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LANGUAGE_COMBO);
	}
		
	
		// Added by Ashwini Mishra on 26-Aug-2014
				public List<Entry> getIdentificationUnit(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_IDENTIFICATION_UNIT_COMBO);
				}
									
				// Added by Ashwini Mishra on 26-Aug-2014
				public String getIdentificationUnitFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
						return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_IDENTIFICATION_UNIT_COMBO);
					}
				
			
				
				// Added by Ashwini Mishra on 28-Aug-2014
				public List<Entry> getEmployerType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EMPLOYER_TYPE_COMBO);
				}
									
				// Added by Ashwini Mishra on 28-Aug-2014
				public String getEmployerTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
						return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_EMPLOYER_TYPE_COMBO);
					}
				
			
				
				// Added by Ashwini Mishra on 10-Oct-2014
				public List<Entry> getOfficialNum(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_OFFICIAL_NUM_COMBO);
				}
									
				// Added by Ashwini Mishra on 10-Oct-2014
				public String getOfficialNumFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
						return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_OFFICIAL_NUM_COMBO);
					}
						
	

				// Added by Ashwini Mishra on 11-Oct-2014
				public List<Entry> getBank(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_BANK_COMBO);
				}
									
				// Added by Ashwini Mishra on 11-Oct-2014
				public String getBankFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
						return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_BANK_COMBO);
					}
						
	
				// Added by Ashwini Mishra on 11-Oct-2014
				public List<Entry> getBranch(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_BRANCH_COMBO);
				}
									
				// Added by Ashwini Mishra on 11-Oct-2014
				public String getBranchFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
						return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_BRANCH_COMBO);
					}
						
	

				// Added by Ashwini Mishra on 01-Sep-2014
				public List<Entry> getTransferUnit(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_UNIT_COMBO);
				}
									
				// Added by Ashwini Mishra on 01-Sep-2014
				public String getTransferUnitFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
						return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_UNIT_COMBO);
					}
				
								
			
	
	
	
	
	
	
	
	public List getDocType(UserVO _userVO,String empNo)
	{
		ResultSet rs = null;
		
		Map populateMAP = new HashMap();
		String errorMsg = null;
		List alRecord = new ArrayList();
		
		if (alRecord == null || alRecord.size() == 0)
		{
			try
			{
				Procedure strProc = new Procedure(PisDaoConfig.PROCEDURE_GET_DOC_TYPE_COMBO);
				strProc.addInParameter(1, Types.VARCHAR, empNo);
				strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addOutParameter(3, Types.VARCHAR);
				strProc.addOutParameter(4, Types.REF);
	
				strProc.execute(super.getTransactionContext().getConnection());
				errorMsg = (String) strProc.getParameterAt(3);
				rs = (ResultSet) strProc.getParameterAt(4);
			}
			catch (HisException e)
			{
				e.printStackTrace();
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new HisDataAccessException("JoiningEssentialsDAO.getDocType(rs)" + e);
			}
		}
				
		return alRecord;

	}
	
	// Added by Ashwini Mishra on 02-Jan-2015
	public List<Entry> getLeave(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LEAVE_COMBO);
	}
			
	// Added by Ashwini Mishra on 02-Jan-2015
	public String getLeaveFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LEAVE_COMBO);
	}
		
	// Added by Ashwini Mishra on 02-Jan-2015
	public List<Entry> getLeaveType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LEAVE_TYPE_COMBO);
	}
			
	// Added by Ashwini Mishra on 02-Jan-2015
	public String getLeaveTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LEAVE_TYPE_COMBO);
	}
		
	// Added by Ashwini Mishra on 02-Jan-2015
	public List<Entry> getPeriodcity(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PERIODCITY_COMBO);
	}
			
	// Added by Ashwini Mishra on 02-Jan-2015
	public String getPeriodcityFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PERIODCITY_COMBO);
	}
			
	// Added by Ashwini Mishra on 02-Jan-2015
	public List<Entry> getLeaveAvailType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LEAVE_AVAIL_TYPE_COMBO);
	}
				
	// Added by Ashwini Mishra on 02-Jan-2015
	public String getLeaveAvailTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LEAVE_AVAIL_TYPE_COMBO);
	}
			
		
	
	
	
	// Added by Ashwini Mishra on 03-Feb-2015
	public List<Entry> getTransferMode(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_MODE_COMBO);
	}
		
	// Added by Ashwini Mishra on 03-Feb-2015
	public String getTransferModeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_MODE_COMBO);
	}
	
	// Added by Ashwini Mishra on 03-Feb-2015
	public List<Entry> getTransferType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_TYPE_COMBO);
	}
		
	// Added by Ashwini Mishra on 03-Feb-2015
	public String getTransferTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_TYPE_COMBO);
	}
	
	// Added by Ashwini Mishra on 03-Feb-2015
	public List<Entry> getTransferCriteria(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_CRITERIA_COMBO);
	}
			
	// Added by Ashwini Mishra on 03-Feb-2015
	public String getTransferCriteriaFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_CRITERIA_COMBO);
	}
	// Added by Ashwini Mishra on 31-Mar-2015
	public List<Entry> getTransferReason(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_REASON_COMBO);
	}
						
	// Added by Ashwini Mishra on 31-Mar-2015
	public String getTransferReasonFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_REASON_COMBO);
		}
	

	
	
	
	// Added by Ashwini Mishra on 16-Feb-2015
	public List<Entry> getIncWhReason(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INC_WH_REASON_COMBO);
	}
		
	// Added by Ashwini Mishra on 16-Feb-2015
	public String getIncWhReasonFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INC_WH_REASON_COMBO);
	}
		
	// Added by Ashwini Mishra on 16-Feb-2015
	public List<Entry> getIncType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INC_TYPE_COMBO);
	}
	
	// Added by Ashwini Mishra on 16-Feb-2015
	public String getIncTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INC_TYPE_COMBO);
	}
	
	
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getHospitalCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_HOSPITAL_COMBO);
	}
				
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getHospitalFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_HOSPITAL_COMBO);
	}
		
	// Added by Ashwini Mishra on 01-Aug-2014
	public List<Entry> getUserCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_USER_COMBO);
	}
			
	// Added by Ashwini Mishra on 01-Aug-2014
	public String getUserFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_USER_COMBO);
	}
		
	// Added by Ashwini Mishra on 02-Mar-2015
	public List<Entry> getModuleCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MODULE_COMBO);
	}
		
	// Added by Ashwini Mishra on 02-Mar-2015	
	public String getModuleFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MODULE_COMBO);
	}

	public List<Entry> getMonthCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MONTH_COMBO);
	}

	
	public List<Entry> getActionCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACTION_COMBO);
	}
	
	public String getActionFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACTION_COMBO);
	}
	
	// Added by Ashwini Mishra on 02-Mar-2015
	public List<Entry> getProcessTypeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROCESS_TYPE_COMBO);
	}
		
	// Added by Ashwini Mishra on 02-Mar-2015
	public String getProcessTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROCESS_TYPE_COMBO);
	}

	public List<Entry> getProcessCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROCESS_COMBO);
	}
		
	// Added by Ashwini Mishra on 02-Mar-2015
	public String getProcessFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROCESS_COMBO);
	}
		
	public List<Entry> getProcessFldCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROC_FIELD_COMBO);
	}
	
	// Added by Ashwini Mishra on 02-Mar-2015
	public String getProcessFldFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROC_FIELD_COMBO);
	}
	
	public List<Entry> getProcessFldValCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROC_FLD_VAL_COMBO);
	}

	// Added by Ashwini Mishra on 02-Mar-2015
	public String getProcessFldValFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROC_FLD_VAL_COMBO);
	}	
	
	
	public List<Entry> getTransferReqCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANSFER_REQ_COMBO);
	}
	
	

		public List<Entry> getPromotionMode(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_MODE_COMBO);
		}
	
		
		public String getPromotionModeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_MODE_COMBO);
		}
		

		public List<Entry> getSuspensionMode(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SUSPENSION_MODE_COMBO);
		}
	
		
		public String getSuspensionModeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_SUSPENSION_MODE_COMBO);
		}
		
		
		public List<Entry> getPromotionType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_TYPE_COMBO);
		}
	
		
		public String getPromotionTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_TYPE_COMBO);
		}
		
		public List<Entry> getPlaceOfPosting(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo2(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PLACE_OF_POSTING_COMBO);
		}
		
		
		public String getPlaceOfPostingFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter2(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PLACE_OF_POSTING_COMBO);
		}
	
	
		// Added by Manoj Kumar Singh on 02-Jul-2015
		public List<Entry> getStatusChangeType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_STATUS_CHANGE_TYPE_COMBO);
		}

		// Added by Somya Tanwar on 02-July-2015
		public String getMeasuringUnitTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MEASURING_UNIT_TYPE_COMBO);
		}
		
		public List<Entry> getMeasuringUnitTypeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_MEASURING_UNIT_TYPE_COMBO);
		}
		
		//Added by Somya Tanwar on 15-JULY-2015
		public String getFilterChange(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {

			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CHANGE_COMBO);
		}
		
		public String getFilterActOnProp(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACT_ON_PROP_COMBO);
		}
		
		public String getFilterImmovProp(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_IMMOV_PROP_TYPE_COMBO);
		}
		
		//Added by Somya Tanwar on 08-JULY-2015
		public List<Entry> getCountry(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COUNTRY_COMBO);
		}
		public String getCountryFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COUNTRY_COMBO);
		}
		
		//Added by Somya Tanwar on 08-JULY-2015
		public List<Entry> getState(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_STATE_COMBO);
		}
		
		public String getStateFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_STATE_COMBO);
		}
		
		
		public String getFilterDecStatus(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, PisConfig.PRO_ID_FOR_PD_IMMOVABLE_PROPERTY, PisConfig.PD_IMMOVABLE_PROPERTY_FLD1, PisDaoConfig.PROCEDURE_GET_PROC_FLD_VAL_COMBO);
		}
		
		//Added by Somya Tanwar on 21-AUGUST-2015
		public String getFilterPropStatus(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, PisConfig.PRO_ID_FOR_PD_IMMOVABLE_PROPERTY, PisConfig.PD_IMMOVABLE_PROPERTY_FLD2, PisDaoConfig.PROCEDURE_GET_PROC_FLD_VAL_COMBO);
		}
		
		//Added by Somya Tanwar on 03-SEPTEMBER-2015
		public List<Entry> getPropStatusUnitCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO,PisConfig.PRO_ID_FOR_PD_IMMOVABLE_PROPERTY, PisConfig.PD_IMMOVABLE_PROPERTY_FLD2, PisDaoConfig.PROCEDURE_GET_PROC_FLD_VAL_COMBO);
		}
		
		
		
		//Added by Somya Tanwar on 14-JULY-2015
				public List<Entry> getChangeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CHANGE_COMBO);
				}
				
				//Added by Somya Tanwar on 08-JULY-2015
				public List<Entry> getActOnPropCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACT_ON_PROP_COMBO);
				}
				//Added by Somya Tanwar on 08-JULY-2015
				public List<Entry> getImmovPropTypeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_IMMOV_PROP_TYPE_COMBO);
				}
				
				
				//Added by Somya Tanwar on 08-JULY-2015
				public List<Entry> getDistrict(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DISTRICT_COMBO);
				}

				public String getDistrictFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DISTRICT_COMBO);
				}
				//Added by Somya Tanwar on 08-JULY-2015
				public List<Entry> getPropAreaUnitCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROP_AREA_UNIT_COMBO);
				}
				//Added by Somya Tanwar on 08-JULY-2015
				public List<Entry> getLandNatureCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_LAND_NATURE_COMBO);
				}
				//Added by Somya Tanwar on 08-JULY-2015
				public List<Entry> getPropAcquiredTypeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROP_ACQUIRED_TYPE_COMBO);
				}
				//Added by Somya Tanwar on 13-JULY-2015
				public List<Entry> getCurrencyUnitCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_CURRENCY_UNIT_COMBO);
				}
				
				
				
		public List<Entry> getPromotionDueList(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_DUE_COMBO);
		}
	
		
		public String getPromotionDueListFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_DUE_COMBO);
		}
		

		public List<Entry> getPromotionScrType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_SCR_COMBO);
		}
	
		
		public String getPromotionScrTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROMOTION_SCR_COMBO);
		}


	// Added by Manoj Kumar Singh on 18-Aug-2015
	public List<Entry> getComplaintType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COMPLAIN_TYPE_COMBO);
	}
	
	public List<Entry> getComplaintCategory(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COMPLAIN_CATEGORY_COMBO);
	}
	
	public List<Entry> getComplaintSource(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COMPLAINT_SOURCE_COMBO);
	}
	
	public String getComplaintTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COMPLAIN_TYPE_COMBO);
	}
	
	public String getComplaintCategoryFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COMPLAIN_CATEGORY_COMBO);
	}
	
	public String getComplaintSourceFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COMPLAINT_SOURCE_COMBO);
	}
	
	// Added by Manoj Kumar Singh on 20-Oct-2015
	public List<Entry> getInvestigatingOfficerType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INVESTIGATING_OFFICER_TYPE_COMBO);
	}
	
	public String getInvestigatingOfficerTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
		return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INVESTIGATING_OFFICER_TYPE_COMBO);
	}

		//Added By Somya Tanwar on 09-SEPT-2015
		public String getAcrLocationFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACR_LOCATION_COMBO);
		}
		//Added By Somya Tanwar on 10-SEPT-2015
		public List<Entry> getAcrLocationCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACR_LOCATION_COMBO);
		}

		
		//Added By Somya Tanwar on 10-SEPT-2015
		public String getAcrCompStatusFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACR_COMP_STATUS_COMBO);
		}
		//Added By Somya Tanwar on 10-SEPT-2015
		public List<Entry> getAcrCompStatusCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACR_COMP_STATUS_COMBO);
		}
				
		//Added By Somya Tanwar on 10-SEPT-2015
		public String getAcrPerGradeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACR_PER_GRADE_COMBO);
		}
		//Added By Somya Tanwar on 10-SEPT-2015
		public List<Entry> getAcrPerGradeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACR_PER_GRADE_COMBO);
		}
		
		// Added by Somya Tanwar on 27-October-2015
		public List<Entry> getUniBoard(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_UNIVERSITY_BOARD_COMBO);
		}
									
		// Added by Somya Tanwar on 27-October-2015
		public String getUniBoardFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_UNIVERSITY_BOARD_COMBO);
		}
		
		// Added by Somya Tanwar on 28-October-2015
		public List<Entry> getCourseType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COURSE_TYPE_COMBO);
		}
											
		// Added by Somya Tanwar on 28-October-2015
		public String getCourseTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COURSE_TYPE_COMBO);
		}
				
		// Added by Somya Tanwar on 28-October-2015
		public List<Entry> getCourseLevel(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COURSE_LEVEL_COMBO);
		}
											
		// Added by Somya Tanwar on 28-October-2015
		public String getCourseLevelFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COURSE_LEVEL_COMBO);
		}
				
		// Added by Somya Tanwar on 28-October-2015
		public List<Entry> getCourse(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COURSE_COMBO);
		}
											
		// Added by Somya Tanwar on 28-October-2015
		public String getCourseFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_COURSE_COMBO);
		}
				
		// Added by Gurpreet Kaur on 28-Oct-2015
		public List<Entry> getActivityType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACTIVITY_TYPE_COMBO);
		}
		
		public String getActivityTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_ACTIVITY_TYPE_COMBO);
		}
		// Added by Gurpreet Kaur on 28-Oct-2015
		public List<Entry> getProceedingType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROCEEDING_TYPE_COMBO);
		}
		
		public String getProceedingTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PROCEEDING_TYPE_COMBO);
		}
		
		
		// Added by Gurpreet Kaur on 29-Jan-2016
		
		public String getTravelModeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRAVEL_MODE_COMBO);
		}
			
		public List<Entry> getTravelModeCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRAVEL_MODE_COMBO);
		}
		
		// Added by Gurpreet Kaur on 29-Jan-2016
		
		public String getTransFormFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANS_FORM_COMBO);
		}
			
		public List<Entry> getTransFormCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
			return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_TRANS_FORM_COMBO);
		}
		
		
		// Added by Somya Tanwar on 02-November-2015
				public List<Entry> getPenaltyType(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PENALTY_TYPE_COMBO);
				}
													
				// Added by Somya Tanwar on 02-November-2015
				public String getPenaltyTypeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PENALTY_TYPE_COMBO);
				}
						
				// Added by Somya Tanwar on 02-November-2015
				public List<Entry> getPenalty(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PENALTY_COMBO);
				}
													
				// Added by Somya Tanwar on 02-November-2015
				public String getPenaltyFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_PENALTY_COMBO);
				}
				// Added by Somya Tanwar on 02-November-2015
				public List<Entry> getInvolveNature(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INVOLVE_NATURE_COMBO);
				}
													
				// Added by Somya Tanwar on 02-November-2015
				public String getInvolveNatureFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_INVOLVE_NATURE_COMBO);
				}
				
		//Added By Somya Tanwar on 14-12-2015
				public List<Entry> getDemotionMode(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getCombo(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DEMOTION_MODE_COMBO);
				}
			
				
				public String getDemotionModeFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2) {
					return getFilter(_calMode, _query, _userVO, _filterParam1, _filterParam2, PisDaoConfig.PROCEDURE_GET_DEMOTION_MODE_COMBO);
				}
				
	// Added by Ashwini Mishra on 02-Mar-2015
	public List<Entry> getCombo(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2, String _procName)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		List alRecord = new ArrayList();
		try
		{
			System.out.println("_query:"+_query);
			Procedure strProc = new Procedure(_procName);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(4, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(5, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(6, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(7, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(9, Types.VARCHAR);
			strProc.addOutParameter(10, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(9);
			rs = (ResultSet) strProc.getParameterAt(10);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getIncType()" + e);
		}
		
		return alRecord;
	}
	
	// Added by Ashwini Mishra on 02-Mar-2015
	public String getFilter(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2, String _procName)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		String alRecord = "";
		try
		{
			Procedure strProc = new Procedure(_procName);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(4, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);			
			strProc.addInParameter(5, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(6, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(7, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(9, Types.VARCHAR);
			strProc.addOutParameter(10, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(9);
			rs = (ResultSet) strProc.getParameterAt(10);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsinFilterFormat(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getIncTypeFilter()" + e);
		}
		
		return alRecord;
	}
	
	public List<Entry> getCombo2(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2, String _procName)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		List alRecord = new ArrayList();
		try
		{
			System.out.println("_query:"+_query);
			Procedure strProc = new Procedure(_procName);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			//strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
			//strProc.addInParameter(4, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(3, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(4, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(5, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(6, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(7);
			rs = (ResultSet) strProc.getParameterAt(8);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getIncType()" + e);
		}
		
		return alRecord;
	}
	
	// Added by Ashwini Mishra on 02-Mar-2015
	public String getFilter2(String _calMode, String _query, UserVO _userVO, String _filterParam1, String _filterParam2, String _procName)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		//Map populateMAP = new HashMap();
		
		String errorMsg = null;
		String alRecord = "";
		try
		{
			Procedure strProc = new Procedure(_procName);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _query);
			//strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
			//strProc.addInParameter(4, Types.VARCHAR, PisConfig.SUPER_USER_HOSPITAL_CODE);			
			strProc.addInParameter(3, Types.VARCHAR, _filterParam1);
			strProc.addInParameter(4, Types.VARCHAR, _filterParam2);
			strProc.addInParameter(5, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(6, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(7);
			rs = (ResultSet) strProc.getParameterAt(8);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsinFilterFormat(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("JoiningEssentialsDAO.getIncTypeFilter()" + e);
		}
		
		return alRecord;
	}
	
	
	
	public void saveLastActionDtls(HisDAO daoObj, String controlPanelId, String controlPanelMenuId, String recordValidateStatus, UserVO objUserVO_p, String strMode_p, String tableName) {

		String strErr = "";
		int nProcIndex = 0;
		String strProcName="";
		
		try 
		{
			strProcName = "{call pkg_pis_util_func.dml_pis_last_action_dtl(?::numeric, ?::numeric, ?::numeric, ?, ?::numeric, ?::numeric, ?::numeric, ?,?)}";

			nProcIndex = daoObj.setProcedure(strProcName);			
			//HelperMethods.setNullToEmpty(objBankVO_p);
			
			///////////////
			System.out.println("PisEssentialDAO               :: saveLastActionDtls()");
			System.out.println("p_modeVal                     :"+ strMode_p);
			System.out.println("p_control_panel_id            :"+ controlPanelId);
			System.out.println("p_control_panel_menu_id       :"+ controlPanelMenuId);
			System.out.println("p_record_validate_status      :"+ recordValidateStatus);
			System.out.println("p_is_valid                    :"+ "1");
			System.out.println("p_seat_id                     :"+ objUserVO_p.getSeatId()); 
			System.out.println("p_hospital_code               :"+ objUserVO_p.getHospitalCode());
			
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", strMode_p,1);
			daoObj.setProcInValue(nProcIndex, "p_control_panel_id",controlPanelId,2);
			daoObj.setProcInValue(nProcIndex, "p_control_panel_menu_id", controlPanelMenuId,3);
			daoObj.setProcInValue(nProcIndex, "p_record_validate_status", recordValidateStatus,4);
			daoObj.setProcInValue(nProcIndex, "p_is_valid", "1",5);
			daoObj.setProcInValue(nProcIndex, "p_seat_id", objUserVO_p.getSeatId(),6); 
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", objUserVO_p.getHospitalCode(),7);
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcInValue(nProcIndex, "table_name", tableName ,9);

			daoObj.execute(nProcIndex,1);	
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
				
	}
	
}
