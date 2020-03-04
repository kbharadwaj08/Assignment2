package tests;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class baseTest {

    //Creating a object to read and write values from the properties file
    public Properties objProp = new Properties();
    String propertiesFile = "C:\\Users\\kiran\\IdeaProjects\\Assignment2\\src\\test\\java\\config.properties";
    FileInputStream readProp;
    FileOutputStream writeProp;
    {
        try {
            readProp = new FileInputStream(propertiesFile);
            objProp.load(readProp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options;
    Logger log;

    //Method to instantiate driver and to create folder for screenshot for eacch run.
    @BeforeSuite
    public void beforeSuite() throws FileNotFoundException {

        //Initializing logger object
        log = Logger.getLogger("devpinoyLogger");

        //To create new folder for each new run with latest time stamp
        String fileSuffix = DateTime.now().toString("yyyy-dd-MM-HH-mm-ss");
        objProp.setProperty("RunCount", fileSuffix);
        String folderName = objProp.getProperty("RunCount");
        objProp.setProperty("screenshotFolder", folderName);
        new File(objProp.getProperty("screenshotPath")+folderName).mkdir();
        writeProp = new FileOutputStream(propertiesFile);
        objProp.save(writeProp,"updated Screenshot folder");
        log.debug("-Created folder for capturing screenshots-");


        //To instantiate browser and launch the application.

        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty(objProp.getProperty("browser"),objProp.getProperty("driverpath"));
        driver = new ChromeDriver(options);
        log.debug("-Chrome Browser has been initialized-");
        wait = new WebDriverWait(driver,30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(objProp.getProperty("URL"));
        log.debug("-Application is launched-");

    }


  /* @AfterSuite
    public void afterSuite() throws FileNotFoundException {

        if(null != driver)
        {
            driver.close();
            driver.quit();
            log.debug("-Chrome Browser Closed-");
        }
    }*/

}
