package startup;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Auth;
import login.CSRFTokenUtil;
import HisGlobal.Config;
import HisGlobal.HisHttpServletRequest;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;

import usermgmt.masters.umgmtSessionConfigXmlHandler;


public class Captcha extends HttpServlet{
		
	public static final int SALT_BYTE_SIZE = 24;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		setCaptcha(request,response);
	}
	public static void setCaptcha(HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		int width = 160;
		int height = 50;
		try{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
	    Graphics2D graphics2D = image.createGraphics();
	    Random r = new Random();
	    String token = Long.toString(Math.abs(r.nextLong()), 36);
	    String ch = token.substring(0,6);
	    Color c = new Color(0.6662f, 0.4569f, 0.3232f);
	    
	    graphics2D.setColor(Color.decode("#002820"));
	    graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());
	   
	    //Color c = new Color(0,0,255);
	    GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
	    graphics2D.setPaint(gp);
	    Font font=new Font("Verdana", Font.CENTER_BASELINE , 26);
	    graphics2D.setFont(font);
	    graphics2D.drawString(ch,26,33);
	    graphics2D.dispose();
	    
	    HttpSession session = objRequest.getSession(true);
	    session.setAttribute("CAPTCHA_KEY",ch);

	    OutputStream outputStream = objResponse.getOutputStream();
	    ImageIO.write(image, "jpeg", outputStream);
	    outputStream.close();
		}
		catch(Exception ee)
		{
		//	objActionSupport.addActionError("Unknown Problem Occured While Trying to fetch Captcha!");
		}
	}
}
	