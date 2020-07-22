package datadrivenExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class Login 
{
	
	@Test(dataProvider="logindata")
	public void loginwithbothcorrect (String uname ,String pword)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\software\\Study@@\\jar files\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);
		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);
		WebElement login =driver.findElement(By.id("btnLogin"));
		login.click();
		driver.quit();
	}
	
	String [][] data =null;

	@DataProvider(name ="logindata")
	public String [][] logindataprovider() throws BiffException, IOException
	{
		data=getexceldata();
		return data;
	}
	public String[][] getexceldata() throws IOException, BiffException
	{
		FileInputStream excel = new FileInputStream("./Data/Book1.xls");
		jxl.Workbook workbook = jxl.Workbook.getWorkbook(excel);
		jxl.Sheet sheet = workbook.getSheet(0);
		int rowcount = sheet.getRows();
		int cowcount= sheet.getColumns();

		String testData[][]=new String [rowcount-1][cowcount];
		for(int i=1;i<rowcount;i++)
		{
			for(int j=0;j<cowcount;j++)
			{
				testData [i-1][j] = sheet.getCell(j,i).getContents();
			}
		}

		return testData;
	}
}
