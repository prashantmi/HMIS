package opd.audioVideoPlayer;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.DataSink;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSinkException;
import javax.media.NoProcessorException;
import javax.media.NotRealizedError;
import javax.media.Processor;
import javax.media.ProcessorModel;
import javax.media.RealizeCompleteEvent;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.FileTypeDescriptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.AudioVideoMasterVO;

public class HisAudioVideoUTIL extends ControllerUTIL implements ControllerListener
{
	DataSink sink;
	static Processor processor;
	static MediaLocator dest = null;
	static Processor processor1 = null;

	public static void getAudioVideoEssentials(HisAudioVideoFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session =_rq.getSession();
		AudioVideoMasterVO[] audioVideoMasterVO = null;
		try
		{
			setSysdate(_rq);
			
			String departmentUnitCode = "-1";
			if(session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE).equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK))
				departmentUnitCode = (String) WebUTIL.getSession(_rq).getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
			
			audioVideoMasterVO = HisAudioVideoDATA.getAudioVideoEssentials(departmentUnitCode, getUserVO(_rq));

			WebUTIL.setAttributeInSession(_rq, OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST, audioVideoMasterVO);

			objStatus.add(Status.NEW);

		}
		/*
		 * String[] arrayFilePath=new String[13];
		 * arrayFilePath[0]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"MOV00254.MPG";
		 * arrayFilePath[1]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"balloo01.MPG";
		 * arrayFilePath[2]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"superheatingwater.MPG";
		 * arrayFilePath[3]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"AajaMahi_RDB.avi";
		 * arrayFilePath[4]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"steve irwin death.avi";
		 * arrayFilePath[5]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"BellyTableBump.avi";
		 * arrayFilePath[6]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"Lesson9.Data Type Methods.mov";
		 * arrayFilePath[7]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"Goaaaaaal.mpeg";
		 * arrayFilePath[8]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"MOV00473.mpg";
		 * arrayFilePath[9]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"leave office.mpg";
		 * arrayFilePath[10]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"Avenging_elephant.wmv";
		 * arrayFilePath[11]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"Croc.wmv";
		 * arrayFilePath[12]=OpdConfig.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"Lion.asf";
		 * 
		 * 
		 * _fb.setFilePath(arrayFilePath);
		 */
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.FAILURE, e.getMessage(), "");

		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);

		}
	}

	public static void getAudioVideoFile(HisAudioVideoFB _fb, HttpServletRequest _rq, HttpServletResponse response)
	{
		Status objStatus = new Status();
		String filePath = _fb.getSelectedFile();
		System.out.println("selected file" + filePath);
		WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER, filePath);
		objStatus.add(Status.INPROCESS);
		WebUTIL.setStatus(_rq, objStatus);
	}

	public void setAudioVideoUpload(HisAudioVideoFB _fb, HttpServletRequest _rq)
	{
		// FormFile sourceFile=_fb.getUploadedFileName();

		MediaLocator source = new MediaLocator("ftp://administrator:hisregistration@10.103.0.20/dir/MOV00254.MPG");
		Format formats[] = new Format[2];
		formats[0] = new AudioFormat(AudioFormat.IMA4);
		formats[1] = new VideoFormat(VideoFormat.CINEPAK);
		FileTypeDescriptor outputType = new FileTypeDescriptor(FileTypeDescriptor.MPEG);
		ProcessorModel pm = new ProcessorModel(source, formats, outputType);
		try
		{
			// processor = Manager.createProcessor(new
			// URL("ftp://administrator:hisregistration@10.103.0.20/dir/MOV00254.MPG"));
			processor1 = Manager.createRealizedProcessor(pm);
			processor1.addControllerListener(this);
			sink = Manager.createDataSink(processor1.getDataOutput(), dest);
			sink.open();
			sink.start();
			// wait(10000l);
			dest = new MediaLocator("ftp://administrator:hisregistration@10.103.0.20/dir/new.mpg");

			System.out.println("");
		}
		catch (NoProcessorException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public synchronized void controllerUpdate(ControllerEvent event)
	{
		if (event instanceof RealizeCompleteEvent)
		{
			try
			{
				sink = Manager.createDataSink(processor1.getDataOutput(), dest);
				sink.open();
				sink.start();
			}
			catch (NoDataSinkException e)
			{
				e.printStackTrace();
			}
			catch (NotRealizedError e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}
}
