package hisglobal.tools.imageeditor;

import hisglobal.hisconfig.Config;
import hisglobal.utility.HisFileControlUtil;

/*
 * This class is a Configuration File for Paths for the use of Image Editor 
 * in Opening from and Saving to the Image Files.
 * You have to add a IN path & a OUT path with a unique Variable Name and pass them to the Image Editor Applet
 * you can use same path for the Both IN & OUT
 * 
 *  TO make the paths accessible to the Editor Applet , CREATE Gerters and Setters. 
 * */

public class HisImageEditorConfig
{
	public static String HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM = "HISImageEditorOutputImageDataStream";

	static String inStandardpath = "C:\\PragyaInImages\\";
	static String outStandardPath = "C:\\PragyaOutImages\\";

	// OPD Desk Images
	static String opdInImagePath;
	static String opdOutImagePath;
	static
	{
		if(HisFileControlUtil.isWindowsOS())
		{
			HisImageEditorConfig.opdInImagePath = Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS;
			HisImageEditorConfig.opdOutImagePath = Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS;
		}
		else
		{
			HisImageEditorConfig.opdInImagePath = "/root" + Config.IMAGE_EXAMINATION_IMAGE_PATH_LINUX;
			HisImageEditorConfig.opdOutImagePath = "/root" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX;
		}
	}
	
	
	public static String getInStandardpath()
	{
		return inStandardpath;
	}
	public static void setInStandardpath(String inStandardpath)
	{
		HisImageEditorConfig.inStandardpath = inStandardpath;
	}
	public static String getOutStandardPath()
	{
		return outStandardPath;
	}
	public static void setOutStandardPath(String outStandardPath)
	{
		HisImageEditorConfig.outStandardPath = outStandardPath;
	}
	public static String getOpdInImagePath()
	{
		return opdInImagePath;
	}
	public static void setOpdInImagePath(String opdInImagePath)
	{
		HisImageEditorConfig.opdInImagePath = opdInImagePath;
	}
	public static String getOpdOutImagePath()
	{
		return opdOutImagePath;
	}
	public static void setOpdOutImagePath(String opdOutImagePath)
	{
		HisImageEditorConfig.opdOutImagePath = opdOutImagePath;
	}

}
