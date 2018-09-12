package testBase;


import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericClass {
	
	public static final Logger log = Logger.getLogger(GenericClass.class.getName());
	private static WebDriver driver;
	public Properties properties;
	static Workbook workbook;
	static Sheet sheet;
	static WebDriverWait wait;
	
	
	public void loadData() throws IOException{
		log.info("loading log4j file");
		String log4jConfPath = "src/main/resources/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		properties = new Properties();
		File file = new File("./src/main/java/configProperties/login.properties");
		FileInputStream fis = new FileInputStream(file);
		properties.load(fis);
	}
	
	public String readPropertyData(String Data) throws IOException{
		loadData();
		String data = properties.getProperty(Data);
		return data;
	}
	
	public String getScreenshot(String result) throws IOException{
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File dest = new File("./src/main/java/screenshots/" +result + formater.format(calendar.getTime())+ ".png");
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath();
		
		
		
	}
	
	public static Object[][] getExcelData(String sheetName){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("./src/main/java/testdata/testdata.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = workbook.getSheet(sheetName);
		Object[][] data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i=0; i<sheet.getLastRowNum(); i++){
			for (int k=0; k<sheet.getRow(0).getLastCellNum(); k++){
				data [i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
		}
		return data;
	}
	
	public static void elementTobeClickable(WebDriver driver, long time, WebElement element){
		try{
			wait = new WebDriverWait(driver, time);
		    wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch(ElementNotVisibleException e){
			e.printStackTrace();
		}
		
	}
	
	public static void elementTobeVisible(WebDriver driver, long time, WebElement element){
		try{
			wait = new WebDriverWait(driver,time);
		    wait.until(ExpectedConditions.visibilityOf(element));
		} catch(ElementNotVisibleException e){
			e.printStackTrace();
		}
		
	}
	
	

}
