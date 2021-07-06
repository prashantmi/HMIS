package HisWeb.webservice;

import java.net.URI;
import java.text.ParseException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import HisWeb.util.opdDrDeskUtil;
import java.util.Base64;

@Path("/cims")
public class CIMSIntergration {

	@POST
	@Path("/getCimsData")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCimsData(@FormParam("xml") String JsonData) throws JSONException, ParseException {

		//System.out.println("JsonData" + JsonData);

		return Response.ok().entity(GetCimsData(JsonData)).header("Access-Control-Allow-Origin", "*").build();
		// return JsonData;
	}

	@POST
	@Path("/getMonographCimsData")
	@Produces(MediaType.APPLICATION_XML)
	public Response getMonographCimsData(@FormParam("data") String JsonData, @FormParam("cimstype") String cimstype)
			throws JSONException, ParseException {

		//System.out.println("JsonData" + JsonData);

		return Response.ok().entity(GetMonographData(JsonData, cimstype)).header("Access-Control-Allow-Origin", "*")
				.build();
		// return JsonData;
	}

	@POST
	@Path("/getAlleryCimsData")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAlleryCimsData(@FormParam("xml") String JsonData) throws JSONException, ParseException {

		//System.out.println("JsonData" + JsonData);

		return Response.ok().entity(GetAlleryData(JsonData)).header("Access-Control-Allow-Origin", "*").build();
		// return JsonData;
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://182.75.240.38:8081/RESTFTWebService/").build();
	}

	public static String GetCimsData(String data) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		 String name = "CDAC";  // Username Changes
         String password = "H748ZpDyEA"; // Password Changes
         String authString = name + ":" + password;
         String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
		
		

		String xml = "<Request>\r\n" + "<Interaction>\r\n" + "<Prescribing>\r\n"
				+ "<Product reference=\"{35051B69-F816-49D2-8A1A-1DA953855A0E}\"/>\r\n"
				+ "<Product reference=\"{59C38920-831D-42AB-B77D-09B8D0C88711}\"/>\r\n"
				+ "<Product reference=\"{35051B69-F816-49D2-8A1A-1DA953855A0E}\"/>\r\n"
				+ "<Product reference=\"{4AE068E3-F727-4EEE-9F8A-AA360427931A}\"/>\r\n"
				+ "<Product reference=\"{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}\"/>\r\n"
				+ "<Product reference=\"{9AEDF043-39EC-468F-A94B-0FCFC81DE1FF}\"/>\r\n"
				+ "<Product reference=\"{96048B6A-575C-4D10-AE5B-D45DD410BAA5}\"/>\r\n" + "</Prescribing>\r\n"
				+ "<Allergies>\r\n" + "<Molecule reference=\"{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}\"/>\r\n"
				+ "<Molecule reference=\"{47150CDB-494D-4967-ADA7-F3F343865765}\"/>\r\n"
				+ "<SubstanceClass reference=\"{8B9ECB52-CDC9-4460-AB40-6E3C7DF38B1E}\"/>\r\n"
				+ "<SubstanceClass reference=\"{9E775009-231D-728D-E034-080020E1DD8C}\"/>\r\n" + "</Allergies>\r\n"
				+ "<HealthIssueCodes>\r\n" + "	<HealthIssueCode code=\"J45\" codeType=\"ICD10\"/>\r\n"
				+ "	<HealthIssueCode code=\"K26.0\" codeType=\"ICD10\"/>\r\n"
				+ "	<HealthIssueCode code=\"I49.5\" codeType=\"ICD10\"/>\r\n" + "</HealthIssueCodes>\r\n"
				+ "<References/>\r\n" + "</Interaction>\r\n" + "</Request>" + "" + "";

		/*
		 * StringBuilder sb1 = new StringBuilder(); sb1.append("<Request>");
		 * sb1.append("<Interaction>"); sb1.append("<Prescribing>");
		 * sb1.append("<Product reference="+data+" />"); sb1.append("</Prescribing>");
		 * 
		 * sb1.append("<Allergies>"); sb1.append("<Molecule reference="+data+" />");
		 * sb1.append("<SubstanceClass reference="+data+" />");
		 * sb1.append("</Allergies>");
		 * 
		 * sb1.append("<HealthIssueCodes>");
		 * sb1.append("<HealthIssueCode code="+data+"codeType="+data+" />");
		 * sb1.append("</HealthIssueCodes>"); sb1.append("<References/>");
		 * sb1.append("</Interaction>"); sb1.append("</Request>");
		 */
		// String xml="";
		// xml =data ;

		//System.out.println("xml::::::::::::" + sb1.toString());
		
		String response = service.path("FTRequest").path("xmlrequest").type(MediaType.APPLICATION_XML).header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.TEXT_PLAIN).entity(data).post(String.class);
		//System.out.println(response);
		/*
		 * StringBuilder sb = new StringBuilder(); sb.append("<PARENT>");
		 * sb.append("<XMLREQUEST>"); sb.append(response); sb.append("</XMLREQUEST>");
		 * sb.append("<GUIDS>");
		 * sb.append("<GUID>{DAABD513-72A6-41F2-ABEC-DDAEABBC18D9}</GUID>");
		 * sb.append("</GUIDS>"); sb.append("</PARENT>");
		 * System.out.println("sb.toString()"+sb.toString());
		 */

		/*
		 * String modifiedResponse =
		 * service.path("/cimsws").path("/specificdrug").type(MediaType.TEXT_PLAIN)
		 * .accept(MediaType.TEXT_PLAIN).entity(sb.toString()).post(String.class);
		 */
		String finalresponse = service.path("FTRequest").path("htmlresult").type(MediaType.APPLICATION_XML).header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.TEXT_PLAIN).entity(response).post(String.class);
		//System.out.println(finalresponse);
		//System.out.println("finalresponse::::::::::::" + finalresponse);

		return finalresponse;

	}

	public static String GetMonographData(String data, String cimstype) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		 String name = "CDAC";  // Username Changes
         String password = "H748ZpDyEA"; // Password Changes
         String authString = name + ":" + password;
         String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
		
		
		StringBuilder sb1 = new StringBuilder();
		if (cimstype.equalsIgnoreCase("1")) {
			sb1.append("<Request>");
			sb1.append("<Content>");

			sb1.append("<GGPI reference=" + data + " />");
			sb1.append("</Content>");
			sb1.append("</Request>");
		} else if (cimstype.equalsIgnoreCase("2")) {

			sb1.append("<Request>");
			sb1.append("<Content>");

			sb1.append("<GenericItem reference=" + data + " />");
			sb1.append("</Content>");
			sb1.append("</Request>");
		} else if (cimstype.equalsIgnoreCase("3")) {

			sb1.append("<Request>");
			sb1.append("<Content>");

			sb1.append("<Product reference=" + data + " />");
			sb1.append("</Content>");
			sb1.append("</Request>");
		}
		System.out.println("xml::::::::::::" + sb1.toString());
		String response = service.path("FTRequest").path("xmlrequest").type(MediaType.APPLICATION_XML).header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.TEXT_PLAIN).entity(sb1.toString()).post(String.class);
		System.out.println(response);
		String finalresponse = service.path("FTRequest").path("htmlresult").type(MediaType.APPLICATION_XML).header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.TEXT_PLAIN).entity(response).post(String.class);
		//System.out.println(finalresponse);
		System.out.println("finalresponse::::::::::::" + finalresponse);
		
		return finalresponse;

	}

	public static String GetAlleryData(String data) {

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		 String name = "CDAC";  // Username Changes
         String password = "H748ZpDyEA"; // Password Changes
         String authString = name + ":" + password;
         String authStringEnc = new String(Base64.getEncoder().encode(authString.getBytes()));
		

		String xml = "<Request>\r\n" + "<Interaction>\r\n"
				+ "<Prescribing> <GGPI reference=\"{B3E6B75E-9519-6AE7-E034-080020E1DD8C}\"/> <GGPI reference=\"{BF33752F-6062-0589-E034-0003BA299378}\"/> <GGPI reference=\"{B3E696CF-71F8-6518-E034-080020E1DD8C}\"/>\r\n"
				+ "</Prescribing>\r\n"
				+ "<Allergies> <Molecule reference=\"{5143B2B1-DBED-45A2-8308-61636C3A2F0B}\"/> <Molecule reference=\"{7142A19F-EDE2-4A7C-A30B-06AB60B6B71F}\"/> <Molecule reference=\"{74DCDDAF-9349-430F-A2F2-B7F2BC5CD8AA}\"/> <SubstanceClass reference=\"{8B9ECB52-CDC9-4460-AB40-6E3C7DF38B1E}\"/> <SubstanceClass reference=\"{9E775009-231D-728D-E034-080020E1DD8C}\"/>\r\n"
				+ "</Allergies>\r\n" + "<References/>\r\n" + "</Interaction>\r\n" + "</Request>" + "" + "";

		// String xml="";
//	xml =data ;
		String response = service.path("/FTRequest").path("xmlrequest").type(MediaType.APPLICATION_XML).header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.TEXT_PLAIN).entity(data).post(String.class);

	/*	StringBuilder sb = new StringBuilder();
		sb.append("<PARENT>");
		sb.append("<XMLREQUEST>");
		sb.append(response);
		sb.append("</XMLREQUEST>");
		sb.append("<GUIDS>");
		sb.append("<GUID>{B3E6B75E-9519-6AE7-E034-080020E1DD8C}</GUID>");
		sb.append("<GUID>{BF33752F-6062-0589-E034-0003BA299378}</GUID>");
		sb.append("<GUID>{B3E696CF-71F8-6518-E034-080020E1DD8C}</GUID>");
		sb.append("</GUIDS>");
		sb.append("</PARENT>");

		String modifiedResponse = service.path("/FTRequest").path("/specificdrug").type(MediaType.TEXT_PLAIN)
				.accept(MediaType.TEXT_PLAIN).entity(sb.toString()).post(String.class);
*/
		String finalresponse = service.path("/FTRequest").path("htmlresult").type(MediaType.APPLICATION_XML).header("Authorization", "Basic " + authStringEnc)
				.accept(MediaType.TEXT_PLAIN).entity(response).post(String.class);
		//System.out.println(finalresponse);

		return finalresponse;

	}

}
