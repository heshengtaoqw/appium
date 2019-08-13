package appium;

import io.appium.java_client.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class FirstAppium {
	 private AppiumDriver  driver;
	    
	    
	  @BeforeClass
	  public void setup() throws Exception {
		  
	        DesiredCapabilities cap = new DesiredCapabilities();
	        cap.setCapability(CapabilityType.BROWSER_NAME, ""); 
	        cap.setCapability("platformName", "Android"); //指定测试平台
	        cap.setCapability("deviceName", "127.0.0.1:62001"); //指定测试机的ID,通过adb命令`adb devices`获取
	        cap.setCapability("platformVersion", "5.1"); 

	        //获取appActivity和appPackage参数值（adb shell dumpsys window w |findstr \/ |findstr name=） 将上面获取到的包名和Activity名设置为值
	        cap.setCapability("appPackage", "com.jiuqi.cam.android.phone");
	        cap.setCapability("appActivity", "com.jiuqi.cam.android.phone.activity.SplashActivity");       
	        //A new session could not be created的解决方法
	        cap.setCapability("appWaitActivity","com.jiuqi.cam.android.phone.activity.SplashActivity");
	        
	        /* 微信app登录
	         * cap.setCapability("appPackage", "com.tencent.mm");
	         * cap.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
	         * cap.setCapability("appWaitActivity","com.tencent.mm.ui.LauncherUI");
	        */
	        
	        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
	        cap.setCapability("sessionOverride", true);
	        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	        Thread.sleep(5000);
	  }

	  @Test 
	  public void plus(){      
	      //获取微信
	      //WebElement login = driver.findElement(By.id("com.tencent.mm:id/drq"));
	      //login.click();
		  
	      //获取+
	      WebElement  username = driver.findElement(By.id("com.jiuqi.cam.android.phone:id/bind_device_dialogbody_phone_edittext"));
	      username.sendKeys("13923811009");
	      WebElement verify_code = driver.findElement(By.id("com.jiuqi.cam.android.phone:id/bind_device_dialogbody_verifycode_edittext"));
	      verify_code.sendKeys("555555");
	      //获取2
	      WebElement get_verify_code = driver.findElement(By.id("com.jiuqi.cam.android.phone:id/send_phonenum_btn"));
	      get_verify_code.click();
	      //获取=
	      WebElement login_button = driver.findElement(By.id("com.jiuqi.cam.android.phone:id/send_verifycode_btn"));
	      login_button.click();
	      
	     
	  }
	  
	  @AfterClass
	  public void tearDown() throws Exception {
	      
	      driver.quit();
	      System.out.println("out testing");
	  }
}






