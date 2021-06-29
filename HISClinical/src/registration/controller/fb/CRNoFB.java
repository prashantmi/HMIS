package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CRNoFB extends ActionForm
{

	protected String patCrNo;
	private String clientName;
	private String mlcNo;
	private String configFlag;

	public String getConfigFlag()
	{
		return configFlag;
	}

	public void setConfigFlag(String configFlag)
	{
		this.configFlag = configFlag;
	}

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public String getClientName()
	{
		return clientName;
	}

	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.setPatCrNo("");
	}

	public String getPatCrNo()
	{
		//System.out.println("getPatCrNo():  " + patCrNo);
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/*
	 * public void setCrNo3( java.lang.String crNo3 ) { //size 7 <== last seven digits char[] arrCrNo; char[] arrCrNo3;
	 * if(this.patCrNo==null || this.patCrNo.length() < 13) arrCrNo = new char[13]; else arrCrNo =
	 * this.patCrNo.toCharArray();
	 * 
	 * arrCrNo3 = crNo3.toCharArray();
	 * 
	 * //for(int j=arrCrNo) for(int i=0; i<8&&arrCrNo3.length==8;i++) arrCrNo[5+i]=arrCrNo3[i];
	 * 
	 * for(int i=0; i<10&&arrCrNo3.length==10;i++) arrCrNo[3+i]=arrCrNo3[i];
	 * 
	 * 
	 * this.patCrNo = new String(arrCrNo); }
	 * 
	 * public java.lang.String getCrNo3( ) { char[] arrCrNo; char[] arrCrNo3 = new char[10]; if(this.patCrNo==null ||
	 * this.patCrNo.length() < 13) arrCrNo = new char[13]; else arrCrNo = this.patCrNo.toCharArray();
	 * 
	 * for(int i=3; i<13;i++) arrCrNo3[i-3]=arrCrNo[i];
	 * 
	 * System.out.println("getCrNo3(): "+(new String(arrCrNo3)).trim());
	 * 
	 * return (new String(arrCrNo3)).trim(); }
	 * 
	 * public void setCrNo2( java.lang.String crNo2 ) { //size 7 <== last seven digits System.out.println("crno 2 :" +crNo2);
	 * char[] arrCrNo; char[] arrCrNo2; if(this.patCrNo==null || this.patCrNo.length() < 13) {arrCrNo = new char[13];
	 * System.out.println("arrCrNo if :" +arrCrNo.length); } else arrCrNo = this.patCrNo.toCharArray();
	 * System.out.println("arrCrNo :" +arrCrNo.length); arrCrNo2 = crNo2.toCharArray(); System.out.println("arrCrNo2 :"
	 * +arrCrNo2.length); //for(int j=arrCrNo) for(int i=0; i<2&&arrCrNo2.length==2;i++) { System.out.println("i :" +i);
	 * arrCrNo[3+i]=arrCrNo2[i]; System.out.println("arrCrNo[3+i] :" +arrCrNo[3+i]); } System.out.println(arrCrNo);
	 * this.patCrNo = new String(arrCrNo); }
	 * 
	 * public java.lang.String getCrNo2( ) { char[] arrCrNo; char[] arrCrNo2 = new char[2];
	 * 
	 * if(this.patCrNo==null || this.patCrNo.length() < 13) return ""; else arrCrNo = this.patCrNo.toCharArray();
	 * 
	 * for(int i=3; i<5;i++) arrCrNo2[i-3]=arrCrNo[i];
	 * 
	 * return (new String(arrCrNo2)).trim(); }
	 * 
	 * public void setCrNo1( java.lang.String crNo1 ) { //size 7 <== last seven digits char[] arrCrNo; char[] arrCrNo1;
	 * if(this.patCrNo==null || this.patCrNo.length() < 13) arrCrNo = new char[13]; else arrCrNo =
	 * this.patCrNo.toCharArray();
	 * 
	 * arrCrNo1 = crNo1.toCharArray();
	 * 
	 * //for(int j=arrCrNo) for(int i=0; i<3&&arrCrNo1.length==3;i++) arrCrNo[i]=arrCrNo1[i];
	 * 
	 * this.patCrNo = new String(arrCrNo); }
	 * 
	 * public java.lang.String getCrNo1( ) { char[] arrCrNo; char[] arrCrNo1 = new char[3];
	 * 
	 * if(this.patCrNo==null || this.patCrNo.length() < 13) return ""; else arrCrNo = this.patCrNo.toCharArray();
	 * 
	 * for(int i=0; i<3; i++) arrCrNo1[i]=arrCrNo[i];
	 * 
	 * return (new String(arrCrNo1)).trim(); }
	 */

}
