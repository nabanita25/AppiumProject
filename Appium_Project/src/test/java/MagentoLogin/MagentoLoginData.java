package MagentoLogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MagentoLoginData {
  
	@Test
  
  public void LoginToMagenta() throws MalformedURLException, InterruptedException, IOException
	{
		DesiredCapabilities capability= new DesiredCapabilities();         
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Nabanita");         
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");         
		capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");         
		AndroidDriver driver= new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);         
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);         
		driver.get("https://account.magento.com/customer/account/login");  
		//Thread.sleep(1000);
				
		File src=new File("C:\\Users\\NabanitaSarker\\Desktop\\SDET\\Appium_Data.xls"); 
		FileInputStream finput = new FileInputStream(src); 
		HSSFWorkbook workbook = new HSSFWorkbook(finput);
		
		HSSFSheet sheet= workbook.getSheetAt(0); 
		int rowCount = sheet.getLastRowNum();
		int rowCount1 = sheet.getLastRowNum()+1;
        System.out.println("The number of rows in the sheet are : " + rowCount1);
        
		for (int row=0; row<=rowCount; row++)
		{
			String username = sheet.getRow(row).getCell(0).getStringCellValue();
			driver.findElement(By.xpath("//*[@id=\"email\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(username); 
            
            String password = sheet.getRow(row).getCell(1).getStringCellValue();
            driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password); 
            
            driver.hideKeyboard();
    		driver.findElement(By.xpath("//span[text()='Continue']//parent::button")).click();
    		System.out.println(username + " , " + password);
    		
    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		js.executeScript("window.scrollBy(0,-250)", "");
    		String message = driver.findElement(By.xpath("//div[@data-ui-id='message-error']//child::div")).getText();
    		System.out.println(message);
    		
		}
	}
}
