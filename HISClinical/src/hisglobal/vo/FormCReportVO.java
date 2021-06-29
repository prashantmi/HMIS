package hisglobal.vo;

public class FormCReportVO extends ValueObject
{
	private String bankName;
	private String bankAddress;
	private String bankIdentificationNo;
	private String month;
	private String year;
	/*private String noofVolBloodDonationCamps;
	private String volInCampusMale;
	private String volInCampusFemale;
	private String totalCollectionInCampVol;
	private String volInBBMale;
	private String volInBBFemale;
	private String totalCollectionInBBVol;
	private String replaceCollectionMale;
	private String replaceCollectionFemale;
	private String totalReplaceCollection;
	private String totalCollectionBySourceMale;
	private String totalCollectionBySourceFemale;
	private String totalCollectionAll;
	private String maleInVolUnitsFound;
	private String femaleInVolUnitsFound;
	private String maleInReplaceUnitsFound;
	private String femaleInReplaceUnitsFound;
	private String maleInVolUnitsTested;
	private String femaleInVolUnitsTested;
	private String maleInReplaceUnitsTested;
	private String femaleInReplaceUnitsTested;
	private String attachedHospital;
	private String OtherHospital;
	private String expired;*/
	
	private String openingBalance;
	private String totalCollDuringMonth;
	private String bloodBagBrought;
	private String totalBagAvailable;
	//BloodBagsGiven To attached hospital
	private String volDonorCard;
	private String repNoServiceCharge;
	private String norepNoCharge;
	private String organiserCredit;
	private String totalAD;
	//bloodbags given to ohter hospitals
	private String volDonorCardOther;
	private String repPlusServiceCharge;
	private String repNoServiceChargeOther;
	private String creditDebitBasis;
	private String organiserCreditOther;
	private String totalAE;
	//Blood Units Expired
	private String totUnitExpireDuringMonth;
	private String bloodUnitSentToNational;
	//Report 4
	private String volDonorCardIssuedDuringMonth;
	private String volDonorCardOfSameBB;
	private String volDonorCardFromOtherBB;
	private String totalBB;
	public String getBankName()
	{
		return bankName;
	}
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
	public String getBankAddress()
	{
		return bankAddress;
	}
	public void setBankAddress(String bankAddress)
	{
		this.bankAddress = bankAddress;
	}
	public String getBankIdentificationNo()
	{
		return bankIdentificationNo;
	}
	public void setBankIdentificationNo(String bankIdentificationNo)
	{
		this.bankIdentificationNo = bankIdentificationNo;
	}
	public String getMonth()
	{
		return month;
	}
	public void setMonth(String month)
	{
		this.month = month;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	public String getOpeningBalance()
	{
		return openingBalance;
	}
	public void setOpeningBalance(String openingBalance)
	{
		this.openingBalance = openingBalance;
	}
	public String getTotalCollDuringMonth()
	{
		return totalCollDuringMonth;
	}
	public void setTotalCollDuringMonth(String totalCollDuringMonth)
	{
		this.totalCollDuringMonth = totalCollDuringMonth;
	}
	public String getBloodBagBrought()
	{
		return bloodBagBrought;
	}
	public void setBloodBagBrought(String bloodBagBrought)
	{
		this.bloodBagBrought = bloodBagBrought;
	}
	public String getTotalBagAvailable()
	{
		return totalBagAvailable;
	}
	public void setTotalBagAvailable(String totalBagAvailable)
	{
		this.totalBagAvailable = totalBagAvailable;
	}
	public String getVolDonorCard()
	{
		return volDonorCard;
	}
	public void setVolDonorCard(String volDonorCard)
	{
		this.volDonorCard = volDonorCard;
	}
	public String getRepNoServiceCharge()
	{
		return repNoServiceCharge;
	}
	public void setRepNoServiceCharge(String repNoServiceCharge)
	{
		this.repNoServiceCharge = repNoServiceCharge;
	}
	public String getNorepNoCharge()
	{
		return norepNoCharge;
	}
	public void setNorepNoCharge(String norepNoCharge)
	{
		this.norepNoCharge = norepNoCharge;
	}
	public String getOrganiserCredit()
	{
		return organiserCredit;
	}
	public void setOrganiserCredit(String organiserCredit)
	{
		this.organiserCredit = organiserCredit;
	}
	public String getTotalAD()
	{
		return totalAD;
	}
	public void setTotalAD(String totalAD)
	{
		this.totalAD = totalAD;
	}
	public String getVolDonorCardOther()
	{
		return volDonorCardOther;
	}
	public void setVolDonorCardOther(String volDonorCardOther)
	{
		this.volDonorCardOther = volDonorCardOther;
	}
	public String getRepPlusServiceCharge()
	{
		return repPlusServiceCharge;
	}
	public void setRepPlusServiceCharge(String repPlusServiceCharge)
	{
		this.repPlusServiceCharge = repPlusServiceCharge;
	}
	public String getRepNoServiceChargeOther()
	{
		return repNoServiceChargeOther;
	}
	public void setRepNoServiceChargeOther(String repNoServiceChargeOther)
	{
		this.repNoServiceChargeOther = repNoServiceChargeOther;
	}
	public String getCreditDebitBasis()
	{
		return creditDebitBasis;
	}
	public void setCreditDebitBasis(String creditDebitBasis)
	{
		this.creditDebitBasis = creditDebitBasis;
	}
	public String getOrganiserCreditOther()
	{
		return organiserCreditOther;
	}
	public void setOrganiserCreditOther(String organiserCreditOther)
	{
		this.organiserCreditOther = organiserCreditOther;
	}
	public String getTotalAE()
	{
		return totalAE;
	}
	public void setTotalAE(String totalAE)
	{
		this.totalAE = totalAE;
	}
	
	public String getTotUnitExpireDuringMonth()
	{
		return totUnitExpireDuringMonth;
	}
	public void setTotUnitExpireDuringMonth(String totUnitExpireDuringMonth)
	{
		this.totUnitExpireDuringMonth = totUnitExpireDuringMonth;
	}
	public String getBloodUnitSentToNational()
	{
		return bloodUnitSentToNational;
	}
	public void setBloodUnitSentToNational(String bloodUnitSentToNational)
	{
		this.bloodUnitSentToNational = bloodUnitSentToNational;
	}
	public String getVolDonorCardIssuedDuringMonth()
	{
		return volDonorCardIssuedDuringMonth;
	}
	public void setVolDonorCardIssuedDuringMonth(String volDonorCardIssuedDuringMonth)
	{
		this.volDonorCardIssuedDuringMonth = volDonorCardIssuedDuringMonth;
	}
	public String getVolDonorCardOfSameBB()
	{
		return volDonorCardOfSameBB;
	}
	public void setVolDonorCardOfSameBB(String volDonorCardOfSameBB)
	{
		this.volDonorCardOfSameBB = volDonorCardOfSameBB;
	}
	public String getVolDonorCardFromOtherBB()
	{
		return volDonorCardFromOtherBB;
	}
	public void setVolDonorCardFromOtherBB(String volDonorCardFromOtherBB)
	{
		this.volDonorCardFromOtherBB = volDonorCardFromOtherBB;
	}
	public String getTotalBB()
	{
		return totalBB;
	}
	public void setTotalBB(String totalBB)
	{
		this.totalBB = totalBB;
	}
	
	}
