package new_investigation.transactions.delegate;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import hisglobal.business.Delegate;

public class invRequisitionFormDelegate extends Delegate {

	public invRequisitionFormDelegate(Object _serviceProvider) {
		super(new InvestigationEssentialBO());
		// TODO Auto-generated constructor stub
	}

}
