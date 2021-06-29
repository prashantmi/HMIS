package auditlogClient.serviceCall;

import hissso.services.HISServiceSO;
import hissso.ticket.HISServiceGrantTicket;

import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import application.config.HISApplicationConfig;
import auditlogClient.AuditlogClientConfig;
import auditlogClient.vo.AuditLogVO;


public class AuditlogServiceCall {



public static AuditLogVO  checkAuditLogEnabledForObject(AuditLogVO objAuditLogVO){
		
	
	
	try
	{
		if(objAuditLogVO.getStrModuleId()!=null && objAuditLogVO.getStrProcessId()!=null){
			ResteasyClient client = new ResteasyClientBuilder().build();
			String uri = AuditlogClientConfig.serviceUrl+  "/checkAuditLogEnabledForObject";
			ResteasyWebTarget target = client.target(uri);
			Response response = target.request().post(Entity.entity(objAuditLogVO, MediaType.APPLICATION_XML));
			// Read the entity
			// objHISServiceSO = response.readEntity(HISServiceSO.class);
			// objHISServiceTicket = objHISServiceSO.getObjServiceTicket();
			objAuditLogVO = response.readEntity(AuditLogVO.class);
			response.close();
		}
		else{
			System.out.println("Audit log service prerequisite failed..\n module Id and processId not specified");
			objAuditLogVO=null;
		}

		
	}
	catch (Exception e)
	{
		
		e.printStackTrace();
	}
	return objAuditLogVO;
	}
	

public static void SaveAuditLogService( AuditLogVO objAuditLogVO){
	try
	{
		if(objAuditLogVO!=null){
	
		ResteasyClient client = new ResteasyClientBuilder().build();
		String uri = AuditlogClientConfig.serviceUrl+  "/SaveAuditLogService";
			ResteasyWebTarget target = client.target(uri);
			target.request().post(Entity.entity(objAuditLogVO, MediaType.APPLICATION_XML));
			target=null;
		}
		else{
			System.out.println("Audit log service prerequisite failed..\n module Id and processId not specified");
			objAuditLogVO=null;
		}
	}catch (Exception e)
	{
		
		e.printStackTrace();
	}
  }
}
