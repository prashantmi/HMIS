package ipd.transactions;

import ipd.IpdConfigUtil;

public class PackAdmTransBO {
	
	// call dao function for admit list of patient in hlp file
		public void getAdmitDetailIPD(PackAdmTransVO VO) {
			PackAdmTransDAO.admitdetailIPD(VO);
			if (VO.getStrMsgType().equals("1")) 
				VO.setStrMsgString(" PackAdmTransBO.getAdmitDetail() --> "
						+ VO.getStrMsgString());
		}
		public void setPatientDtl(PackAdmTransVO vo)
		{
			try
			{
				PackAdmTransDAO.setEpisodeDtl(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				if(vo.getStrCrNoValid().equals("0"))//CR No Valid
				{
					if(vo.getStrIsIntegratedWithBilling().equals("1") && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
					{
						PackAdmTransDAO.checkStatusWhetherAdvanceAmountGiven(vo);
						//if(!vo.getStrIsAdvanceAmountAtAdmissionTaken().equals("0"))
								//PatientAdmissionTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
					}
					//PatientAdmissionTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
					PackAdmTransDAO.setPatientStatus(vo);
					if(vo.getStrMsgType().equals("1"))
					{
						throw new Exception(vo.getStrMsgString());
					}
					
					if((vo.getStrPatStatusCode().equals(IpdConfigUtil.strOpdCode) || vo.getStrPatStatusCode().equals(IpdConfigUtil.strEmgCode)) && !(vo.getStrIsDead().equals("1")))
					{
						/*
						 * This function is used to bring admission advice details from admission advice table. 
						 */
						if(vo.getStrIsAdmissionOnline().equals("1"))
						{
							PackAdmTransDAO.setAdviceAdmNo(vo);
						}
						if(vo.getStrMsgType().equals("1"))
						{
							throw new Exception(vo.getStrMsgString());
						}
					}
					
					/*if(vo.getStrAdmissionCharge().equals("1"))//Admission Charges Required
					{					
						PackAdmTransDAO.setChargeVal(vo);
					}
					if(vo.getStrAdviceStatus().equals("1"))///Active Advice Generated
					{
					
						if(vo.getStrIsAdmissionOnline().equals("1"))
						{
							PackAdmTransDAO.getBedStatusDtl(vo);
						}
						PackAdmTransDAO.setConsultantName(vo);
						//PatientAdmissionTransDAO.setChargeVal(vo);
						PackAdmTransDAO.getPatDtl_Msapproval(vo);
						if(vo.getStrMsgType().equals("1"))
						{
							throw new Exception(vo.getStrMsgString());
						}
					}*/
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				vo.setStrMsgString("PackAdmTransBO.setPatientDtl---->"+e.getMessage());
				vo.setStrMsgType("1");
			}
		
		}
		public void getBillingPackageNames(PackAdmTransVO VO) {
			PackAdmTransDAO.getBillingPackageNames(VO);
			if (VO.getStrMsgType().equals("1")) 
				VO.setStrMsgString(" PackAdmTransBO.getBillingPackageNames() --> "
						+ VO.getStrMsgString());
		}
		public void insert(PackAdmTransVO voObj) 
		{
			try 
			{
				//PackAdmTransDAO.insert(voObj);
				if (voObj.getStrMsgType().equals("1")) 
				{
					throw new Exception(voObj.getStrMsgString());
				}
			} 
			catch (Exception e) 
			{
				voObj.setStrMsgString("PackAdmTransBO.insert() --> "+ e.getMessage());
				voObj.setStrMsgType("1");
			}
		}

}
