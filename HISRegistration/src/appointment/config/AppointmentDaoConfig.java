package appointment.config;
import registration.*;
/**
 * RegistrationDaoConfig is an interface that defines hard-coded values that are used for development of DAO.
 * @author AHIS
 */
public interface AppointmentDaoConfig extends AppointmentConfig{
	
	//Renewal Configuration Master
	String RenewalConfigProcedure_dml = "{call pkg_reg_dml.proc_hrgt_renewal_config(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
}
