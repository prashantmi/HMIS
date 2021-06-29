package new_investigation.reports.controller.utl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import new_investigation.reports.controller.data.MachineTestReportNewDATA;
import new_investigation.reports.controller.fb.MachineTestReportNewFB;
import new_investigation.vo.MachineTestReportNewVO;
public class MachineTestReportNewUTIL {


public static JsonObject AjaxGetLabList(MachineTestReportNewFB fb, HttpServletRequest request) {

		Status objStatus= new Status();

		MachineTestReportNewVO vo = new MachineTestReportNewVO();
		List<MachineTestReportNewVO> listMachineTestReportNewVO = new ArrayList<MachineTestReportNewVO>();
		Map mp=new HashMap();

		JsonObject jsonResponse = new JsonObject();
		JsonArray labListObjectRowsContainer= new JsonArray();

		try {

			UserVO userVO = ControllerUTIL.getUserVO(request);
			HelperMethods.populate(vo, fb);
			ControllerUTIL.setSysdate(request);

			mp=MachineTestReportNewDATA.AjaxGetLabList(vo, userVO);
			listMachineTestReportNewVO=(List<MachineTestReportNewVO>) mp.get("labList");

			int i=0;
			for(MachineTestReportNewVO vo2 : listMachineTestReportNewVO) {
				i++;

				JsonObject objectRow = new JsonObject();

		        objectRow.addProperty("labName", vo2.getLabName()==null?"NA":vo2.getLabName() );
		        objectRow.addProperty("labCode", vo2.getLabCode()==null?"NA":vo2.getLabCode() );

				labListObjectRowsContainer.add(objectRow);
			}

			jsonResponse.add("labList", labListObjectRowsContainer);


			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}
		return jsonResponse;

	}


public static JsonObject AjaxGetMachineList(MachineTestReportNewFB fb, HttpServletRequest request) {

	Status objStatus= new Status();

	MachineTestReportNewVO vo = new MachineTestReportNewVO();
	List<MachineTestReportNewVO> listMachineTestReportNewVO = new ArrayList<MachineTestReportNewVO>();
	Map mp=new HashMap();

	JsonObject jsonResponse = new JsonObject();
	JsonArray machineListObjectRowsContainer= new JsonArray();

	try {

		UserVO userVO = ControllerUTIL.getUserVO(request);
		HelperMethods.populate(vo, fb);
		ControllerUTIL.setSysdate(request);

		mp=MachineTestReportNewDATA.AjaxGetMachineList(vo, userVO);
		listMachineTestReportNewVO=(List<MachineTestReportNewVO>) mp.get("machineList");

		int i=0;
		for(MachineTestReportNewVO vo2 : listMachineTestReportNewVO) {
			i++;

			JsonObject objectRow = new JsonObject();


	        objectRow.addProperty("machineName", vo2.getMachineName()==null?"NA":vo2.getMachineName() );
	        objectRow.addProperty("machineId", vo2.getMachineId()==null?"NA":vo2.getMachineId() );

	        machineListObjectRowsContainer.add(objectRow);
		}

		jsonResponse.add("machineList", machineListObjectRowsContainer);


		objStatus.add(Status.TRANSINPROCESS);
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}
	return jsonResponse;

}



public static JsonObject AjaxGetMachineTestReportList(MachineTestReportNewFB fb, HttpServletRequest request) {

	Status objStatus= new Status();

	MachineTestReportNewVO vo = new MachineTestReportNewVO();
	List<MachineTestReportNewVO> listMachineTestReportNewVO = new ArrayList<MachineTestReportNewVO>();
	Map mp=new HashMap();

	JsonObject jsonResponse = new JsonObject();
	JsonArray machineTestListObjectRowsContainer= new JsonArray();

	try {

		UserVO userVO = ControllerUTIL.getUserVO(request);
		HelperMethods.populate(vo, fb);
		ControllerUTIL.setSysdate(request);

		mp=MachineTestReportNewDATA.AjaxGetMachineTestReportList(vo, userVO);
		listMachineTestReportNewVO=(List<MachineTestReportNewVO>) mp.get("machineTestList");

		int i=0;
		for(MachineTestReportNewVO vo2 : listMachineTestReportNewVO) {
			i++;

			JsonObject objectRow = new JsonObject();

			objectRow.addProperty("sno", i);
			objectRow.addProperty("crNo", vo2.getCrNo()==null?"NA":vo2.getCrNo());
			objectRow.addProperty("sampleNo", vo2.getSampleNo()==null?"NA":vo2.getSampleNo());
			objectRow.addProperty("accessionNo", vo2.getAccessionNo()==null?"NA":vo2.getAccessionNo());
			objectRow.addProperty("patientName", vo2.getPatientName()==null?"NA":vo2.getPatientName());
			objectRow.addProperty("ageGender", vo2.getAgeGender()==null?"NA":vo2.getAgeGender());
			objectRow.addProperty("sampleName", vo2.getSampleName()==null?"NA":vo2.getSampleName());
			objectRow.addProperty("groupName", vo2.getGroupName()==null?"NA":vo2.getGroupName());
			objectRow.addProperty("testName", vo2.getTestName()==null?"NA":vo2.getTestName());
			objectRow.addProperty("acceptance", vo2.getAcceptance()==null?"NA":vo2.getAcceptance());
			objectRow.addProperty("collectionDateTime", vo2.getCollectionDateTime()==null?"NA":vo2.getCollectionDateTime());


			machineTestListObjectRowsContainer.add(objectRow);
		}

		jsonResponse.add("machineTestList", machineTestListObjectRowsContainer);


		objStatus.add(Status.TRANSINPROCESS);
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}
	return jsonResponse;

}





}
