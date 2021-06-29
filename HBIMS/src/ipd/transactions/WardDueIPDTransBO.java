/**
 * 
 */
package ipd.transactions;

/**
 * @author pankaj kumar
 * 
 */
public class WardDueIPDTransBO {
	public void getPatientDueList(WardDueIPDTransVO _WardDueIPDTransVO) {
		WardDueIPDTransDAO.getPatientDueList(_WardDueIPDTransVO);
		if(_WardDueIPDTransVO.getStrMsgType().equals("1"))
			_WardDueIPDTransVO
					.setStrMsg("WardDueIPDTransBO.getPatientDueList() --> "
							+ _WardDueIPDTransVO.getStrMsg());
	}

	public void getPatienteList(WardDueIPDTransVO _WardDueIPDTransVO) {
		WardDueIPDTransDAO.getPatienteList(_WardDueIPDTransVO);
		if(_WardDueIPDTransVO.getStrMsgType().equals("1"))
			_WardDueIPDTransVO
					.setStrMsg("WardDueIPDTransBO.getPatienteList() --> "
							+ _WardDueIPDTransVO.getStrMsg());
	}

	public void save(WardDueIPDTransVO _WardDueIPDTransVO) {
		WardDueIPDTransDAO.save(_WardDueIPDTransVO);
		if(_WardDueIPDTransVO.getStrMsgType().equals("1"))
			_WardDueIPDTransVO
					.setStrMsg("WardDueIPDTransBO.save() --> "
							+ _WardDueIPDTransVO.getStrMsg());
	}
}