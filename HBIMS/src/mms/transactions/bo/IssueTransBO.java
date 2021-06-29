package mms.transactions.bo;

import mms.transactions.dao.IssueTransDAO;
import mms.transactions.dao.RequestForLPPatientDAO;
import mms.transactions.vo.IssueTransVO;
import mms.transactions.vo.RequestForLPPatientVO;

public class IssueTransBO 
{
	
	/**
	 * This method is used to GET Issue Details
	 * 
	 * @param vo
	 */
	
	public void setViewPageDtl(IssueTransVO _OfflineIssueIndentTransVO)
	{		
		IssueTransDAO.getIssueDetailTwo(_OfflineIssueIndentTransVO);
		if (_OfflineIssueIndentTransVO.getStrMsgType().equals("1"))
		{
			_OfflineIssueIndentTransVO.setStrMsgString("IssueTransBO.setViewPageDtl() --> "+ _OfflineIssueIndentTransVO.getStrMsgString());
		}
		
	}
	
	/**
	 * Gets the issue dtls init dtls.
	 * 
	 * @param vo the vo
	 * 
	 * @return the issue dtls init dtls
	 */
	public void getIssueDtlsInitDtls(IssueTransVO vo) {

		IssueTransDAO.getIssueDtlsList(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo
					.setStrMsgString("IssueTransBO.getStockItemDtlsInitDtls() --> "
							+ strErr);

		}

	}

	
	public void getStoreDtls(IssueTransVO voObj){
		
		IssueTransDAO.getStoreList(voObj);
	
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getStoreDtls()-->"+strErr);
				}
				
		}
	
     public void getStoreDtlsView(IssueTransVO voObj)
     {
		
		IssueTransDAO.getViewStoreList(voObj);
	
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getStoreDtlsView()-->"+strErr);
				}
				
		}
	
	
	
        public void getPatinetTypeCmb(IssueTransVO voObj)
        {
        IssueTransDAO.getPatinetTypeCmb(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
						String strErr = voObj.getStrMsgString();							
						voObj.setStrMsgString("IssueTransBO.getPatinetTypeCmb()-->"+strErr);
			}
				
		}
	
	public void getItemCatDtls(IssueTransVO voObj){
		
		IssueTransDAO.getItemCatDtls(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getItemCatDtls()-->"+strErr);
				}
				
		}
	public void getItemCatDtls1(IssueTransVO voObj){
		
		IssueTransDAO.getItemCatDtls1(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getItemCatDtls()-->"+strErr);
				}
				
		}
	public void issueTopatientCount(IssueTransVO voObj){
		
		IssueTransDAO.getissuetopatient(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getItemCatDtls()-->"+strErr);
				}
				
		}
	
	
      public void getCancelIssueDtls(IssueTransVO voObj)
      {
		
		IssueTransDAO.getCancelIssueDtls(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getCancelIssueDtls()-->"+strErr);
				}
				
		}
	
	public void getIssueDetail(IssueTransVO voObj){
		
		IssueTransDAO.getIssueDetail(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getIssueDetail()-->"+strErr);
				}
				
		}
	
	public void getIssueDtlPopUp(IssueTransVO voObj){
		
		IssueTransDAO.getIssueDtlPopUp(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getIssueDtlPopUp()-->"+strErr);
				}
				
		}
	
	public void getRequestDtls(IssueTransVO voObj){
		
		IssueTransDAO.getRequestDtls(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getRequestDtls()-->"+strErr);
				}
				
		}
	
	public void getRequestDetails(IssueTransVO voObj){
		
		IssueTransDAO.getRequestDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getRequestDetails()-->"+strErr);
				}
				
		}
	
	public void getDoseDetails(IssueTransVO voObj){
		
		IssueTransDAO.getDoseDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getDoseDetails()-->"+strErr);
				}
				
		}
	
	public void getFrequencyDetails(IssueTransVO voObj){
		
		IssueTransDAO.getFrequencyDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getFrequencyDetails()-->"+strErr);
				}
				
		}
	
	public void getDeptDetails(IssueTransVO voObj){
		
		IssueTransDAO.getDeptDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getDeptDetails()-->"+strErr);
				}
				
		}
	public void getUnitDetails(IssueTransVO voObj){
		
		IssueTransDAO.getUnitDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getUnitDetails()-->"+strErr);
				}
				
		}
	public void getPrescribedBy(IssueTransVO voObj){
		
		IssueTransDAO.getPrescribedBy(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getPrescribedBy()-->"+strErr);
				}
				
		}
	
	public void getItemDetails(IssueTransVO voObj){
		
		IssueTransDAO.getItemDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getItemDetails()-->"+strErr);
				}
				
		}
	
	public void getUnitCombo(IssueTransVO voObj){
		
		IssueTransDAO.getUnitCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getUnitCombo()-->"+strErr);
				}
				
		}
	public void getStoreGroupDtls(IssueTransVO voObj){
		
		IssueTransDAO.getStoreGroupList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getStoreGroupDtls()-->"+strErr);
				}
				
		}
	
	public void insert(IssueTransVO voObj){
		
		
		
		IssueTransDAO.insert(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.insert()-->"+strErr);
				}
				
		}
	
public void inserttemp(IssueTransVO voObj){
		
		
		
		IssueTransDAO.inserttemp(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.insert()-->"+strErr);
				}
				
		}
	
public void insertipd(IssueTransVO voObj){
	
	
	
	IssueTransDAO.insertipd(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("IssueTransBO.insert()-->"+strErr);
			}
			
	}
		public void insertWithoutCrNo(IssueTransVO voObj)
	{
		
		IssueTransDAO.insertWithoutCrNo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.insertWithoutCrNo()-->"+strErr);
				}
				
		}
	
		public void getGenderCombo(IssueTransVO voObj){
		
		IssueTransDAO.getGenderCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("IssueTransBO.getGenderCombo()-->"+strErr);
				}
				
		}
		
		public void getLFAccountDetail(IssueTransVO voObj)
		{
			IssueTransDAO.getLFAccountDetails(voObj);
		}
	
		public void getPatientDiagDetails(IssueTransVO vo)
		{
			IssueTransDAO.getPatientDiagDetails(vo);
			  if (vo.getStrMsgType().equals("1")) 
			  {
				vo.setStrMsgString("IssueTransBO.getPatientDiagDetails() --> "+ vo.getStrMsgString());
			  }
			  
		}
		
		public void getIcdList(IssueTransVO vo)
		{
			IssueTransDAO.getIcdList(vo);
			IssueTransDAO.getEmpList(vo);
			  if (vo.getStrMsgType().equals("1")) 
			  {
				vo.setStrMsgString("IssueTransBO.getPatientDiagDetails() --> "+ vo.getStrMsgString());
			  }
			  
		}
		
		
		public void getBilledItemDtls(IssueTransVO _OfflineIssueIndentTransVO)
		{		
			IssueTransDAO.getBilledItemDtls(_OfflineIssueIndentTransVO);
			if (_OfflineIssueIndentTransVO.getStrMsgType().equals("1"))
			{
				_OfflineIssueIndentTransVO.setStrMsgString("IssueTransBO.setViewPageDtl() --> "+ _OfflineIssueIndentTransVO.getStrMsgString());
			}
			
		}
		
		
		
		public void save(IssueTransVO _OfflineIssueIndentTransVO)
		{		
			IssueTransDAO.save(_OfflineIssueIndentTransVO);
			if (_OfflineIssueIndentTransVO.getStrMsgType().equals("1"))
			{
				_OfflineIssueIndentTransVO.setStrMsgString("IssueTransBO.setViewPageDtl() --> "+ _OfflineIssueIndentTransVO.getStrMsgString());
			}
			
		}
		public void deleteIssueDtls(IssueTransVO _OfflineIssueIndentTransVO)
		{		
			IssueTransDAO.deleteIssueDtls(_OfflineIssueIndentTransVO);
			if (_OfflineIssueIndentTransVO.getStrMsgType().equals("1"))
			{
				_OfflineIssueIndentTransVO.setStrMsgString("IssueTransBO.setViewPageDtl() --> "+ _OfflineIssueIndentTransVO.getStrMsgString());
			}
			
		}
		
		public void getTariffDtls(IssueTransVO _OfflineIssueIndentTransVO)
		{		
			IssueTransDAO.getTariffDtls(_OfflineIssueIndentTransVO);
			if (_OfflineIssueIndentTransVO.getStrMsgType().equals("1"))
			{
				_OfflineIssueIndentTransVO.setStrMsgString("IssueTransBO.setViewPageDtl() --> "+ _OfflineIssueIndentTransVO.getStrMsgString());
			}
			
		}
}
