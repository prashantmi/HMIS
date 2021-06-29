package investigationDesk.transactions.dao;

public interface InvestigationDaoConfig
{
	// billing
	String PROCEDURE_UPDATE_BILLING = "{call Bill_Interface.Dml_Online_Billreq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	// update billing for Sample Collection Process
		String PROCEDURE_UPDATE_BILLING_DTY_SAMPLE_COLLECTTION = "{call bill_interface.proc_update_billqty(?,?,?,?,?,?::character varying,?::character varying)}";
		
}




