package UtilFunctions;

import org.joda.time.DateTime;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class captureScreenshot {

    public void takeScreenshot(WebDriver driver, String path) throws IOException {
        String suffix = DateTime.now().toString("yyyy-dd-MM-HH-mm-ss");
        TakesScreenshot ss =((TakesScreenshot)driver);
        File scrfile = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrfile,new File(path+suffix+".jpg"));
    }
}
