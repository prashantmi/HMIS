package Investigation_webservice.bussiness;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import Investigation_webservice.security.AESEncryption;



public class dummy {
	public static void main(String[] args) {
       // Application.launch(args);
		
		String data1="bMIggHNYcpfe3I+W1Z1Ypg==";
		String original1=AESEncryption.encrypt("siddharth srivastav", "e203acbe42504b549");
		
		String original=AESEncryption.decrypt("cm05uZj+3Ga6rkmIbxWEgg==", "6a8757a8c2244889");
	
		System.out.println("encrypt::"+original1);
		System.out.println("decrypt::"+original);
	
		
		//printJson();
    }
    

	private static void printJson() {
		JSONArray jarray = new JSONArray();
		JSONObject jobj1 = new JSONObject();
		JSONObject jobj2 = new JSONObject();
		try{
		jobj1.put("wb", 20);
		jobj1.put("prbc", 30);
		// {"ap": [{"wb":20, "prbc":21}]}
		
		//jobj2.put("ap", jobj1);
		
		
		jarray.put(jobj1);
		jobj2.put("ap", jarray);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("jarray is "+jobj2);
		
	} 

}
