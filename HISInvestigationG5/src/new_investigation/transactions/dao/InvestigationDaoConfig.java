package new_investigation.transactions.dao;

public interface InvestigationDaoConfig
{
	// billing
	String PROCEDURE_UPDATE_BILLING = "{call Bill_Interface.Dml_Online_Billreq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	// update billing for Sample Collection Process
		String PROCEDURE_UPDATE_BILLING_DTY_SAMPLE_COLLECTTION = "{call bill_interface.proc_update_billqty(?,?,?,?,?,?::character varying,?::character varying)}";
		
		
	// refund
		String PROCEDURE_REFUND_BILLING = "{call bill_interface.dml_online_billreq_refund(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
}




