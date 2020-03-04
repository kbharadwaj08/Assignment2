package UtilFunctions;

import org.openqa.selenium.WebElement;

import java.io.*;

public class readToFile {

    public void readingToATextFile(File file, WebElement ele) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ele.sendKeys(br.readLine());
        br.close();
    }
}
