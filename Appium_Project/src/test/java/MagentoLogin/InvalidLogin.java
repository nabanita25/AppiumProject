package MagentoLogin;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;


public class InvalidLogin 
{

	@Test
	public void LoginToMagenta() throws MalformedURLException, InterruptedException
	{
		DesiredCapabilities capability= new DesiredCapabilities();         
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Nabanita");         
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");         
		capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");         
		AndroidDriver driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);         
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);         
		driver.get("https://account.magento.com/customer/account/login");  
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("sdet.nivedita1@gmail.com");        
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Magento@12345"); 
		System.out.println("sdet.nivedita1@gmail.com" + "," + "Magento@12345");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//span[text()='Continue']//parent::button")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)", "");
		String message = driver.findElement(By.xpath("//div[@data-ui-id='message-error']//child::div")).getText();
		System.out.println(message);
	}



}
