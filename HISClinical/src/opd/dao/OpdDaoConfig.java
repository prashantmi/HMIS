package opd.dao;

/**
 * OpdDaoConfig is an interface that defines hard-coded values that are used for development of DAO.
 * @author AHIS
 */

public interface OpdDaoConfig
{
	String PROCEDURE_GET_PROFILE_ID = "Pkg_Mrd_Dtl.generateProfileId";

	// Generic Desk Template Tile
	String PROCEDURE_GET_PATIENT_EPISODE_CLINICAL_DATA = "GET_PAT_EPISODE_CLINICAL_DATA"; // Not In Use, Dropped
	String PROCEDURE_SAVE_PATIENT_EPISODE_CLINICAL_DATA = "PKG_EMR_DTL.SAVE_PAT_CLINICAL_RECORD";

	// Patient Treatment Detail
	String PROCEDURE_GET_ITEM_TYPE_MST = "GET_DRUG_ITEM_TYPE_LIST"; // Not in Use, Dropped
	String PROCEDURE_GENERATE_CONSENT="PKG_MRD_DTL.proc_gen_consent";
	String PROCEDURE_GET_ECONSULTANT_USER="PKG_MRD_DTL.get_Econsultant_Users";

	String PROCEDURE_GET_OPD_SCHEDULE_DATES = "Ahis_Reg.OpdSchedule";
}
