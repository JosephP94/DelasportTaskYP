package com.lazerycode.selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {

		WebDriver driver=null;
		String currentDirr=System.getProperty("user.dir")+"\\screenshots";
		
		Date current=new Date();
//	  @Override
//	  public void onTestFailure(ITestResult tr) {
//		ITestNGMethod tng = tr.getMethod() ;
//		 takeScreenShot(tr.getName());
//	    System.out.println("[--!--] "+tr.getName()+" failed");
//	    System.out.println("[--!--] Description: "+tng.getDescription());
//	    System.out.println("The current directory is "+currentDirr);
//	  }
//	    public void takeScreenShot(String methodName) {
//	    	driver=SeleniumTestsST.getDriver();
//	    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//	            try {
//					FileUtils.copyFile(scrFile, new File(currentDirr+methodName+current.getTime()+".png"));
//					System.out.println("***Placed screen shot in "+currentDirr+" ***");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//	    }
	
	
	
	
@Override
  public void onTestFailure(ITestResult tr) {
	if (System.getProperty("os.name").toString().toLowerCase().contains("windows")) {
		ITestNGMethod tng = tr.getMethod() ;
		File screenshot = new File("screenshots" + File.separator + System.currentTimeMillis() + "_" + tr.getName() + ".png");
	    if (!screenshot.exists()) {
	      new File(screenshot.getParent()).mkdirs();
	      try {
	        screenshot.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    try {
	      new FileOutputStream(screenshot).write(((TakesScreenshot) SeleniumSetup.driver).getScreenshotAs(OutputType.BYTES));
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    System.out.println("Written screenshot to " + screenshot.getAbsolutePath());
	    System.out.println("[--!--] "+tr.getName()+" failed"+"[--!--]");
	    System.out.println("[--!--] Description: "+tng.getDescription()+"[--!--]");
	}
	 else if (System.getProperty("os.name").toString().toLowerCase().contains("linux")) {
		 	ITestNGMethod tng = tr.getMethod();
		    System.out.println("[--!--] "+tr.getName()+" failed"+"[--!--]");
		    System.out.println("[--!--] Description: "+tng.getDescription()+"[--!--]");
	}
	
  }
}

