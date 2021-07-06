package HisWeb.util;

import java.util.TreeMap;

import org.json.JSONObject;

import HisWeb.dao.getOrderID;

import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class CheckSumGenrationService {
	private static String MID = "Centre20544873698881"; 
	private static String MercahntKey = "bHlVbpTYbOev84FH";
	private static String INDUSTRY_TYPE_ID = "Retail";
	private static String CHANNLE_ID = "WAP";
	private static String WEBSITE = "APPSTAGING";
	private static String CALLBACK_URL = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";
	static String billNo=null;
	public static String getcheckSum(String amount) throws Exception
	{
		JSONObject js=new JSONObject();
		getOrderID getOrderID=new getOrderID();
		billNo=getOrderID.getPaytmOrderID();
		System.out.println(":::::::::::"+billNo);
		String checkSum=null;
		TreeMap<String,String> paramMap = new TreeMap<String,String>();
		paramMap.put("MID" , MID);
		paramMap.put("ORDER_ID" , "CDACORDER"+billNo);
		paramMap.put("CUST_ID" , "CDACCUST"+billNo);
		paramMap.put("INDUSTRY_TYPE_ID" , INDUSTRY_TYPE_ID);
		paramMap.put("CHANNEL_ID" , CHANNLE_ID);
		paramMap.put("TXN_AMOUNT" , amount);
		paramMap.put("WEBSITE" , WEBSITE);
		paramMap.put("EMAIL" , "ashu22594@gmail.com");
		paramMap.put("MOBILE_NO" , "7777777777");
		paramMap.put("CALLBACK_URL" , CALLBACK_URL+"CDACORDER"+billNo);
		
		try{
		 checkSum =  CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(MercahntKey, paramMap);
		paramMap.put("CHECKSUMHASH" , checkSum);
		System.out.println(checkSum);
		String tmp="\"";
		System.out.println("Paytm Payload: "+ paramMap);
		//request.getRequestDispatcher("NewFile.jsp").forward(request, response);
		js.put("CHECKSUMHASH", checkSum);
		js.put("ORDER_ID" , "CDACORDER"+billNo);
		js.put("CUST_ID" , "CDACCUST"+billNo);
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return js.toString();
	}
	
}
