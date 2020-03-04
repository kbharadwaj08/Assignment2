package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import UtilFunctions.*;
import java.nio.Buffer;

public class writeReviewPage {

    WebDriver driver;

    @FindBy(css= "#reviews-section > modal-dialog > div > div > write-review > review-star > div > svg:nth-child(4) > g > path:nth-child(1)")
    WebElement reviewedStar;

    @FindBy(xpath = "//span[contains(.,'Select...')]")
    WebElement dropd;

    @FindBy(xpath = "//li[contains(.,'Health Insurance')]")
    WebElement ddvalue;

    @FindBy(xpath = "//textarea[contains(@placeholder,'Write your review')]")
    WebElement writeReviewField;

    @FindBy(xpath = "//div[text()='Submit']")
    WebElement submit;

    public writeReviewPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String verifyReview()
    {
        return reviewedStar.getCssValue("fill");
    }

    public void selecDropDown()
    {
        dropd.click();
        ddvalue.click();
    }

    public void writingReview(File file) throws IOException {

        readToFile rf=new readToFile();
        rf.readingToATextFile(file,writeReviewField);

    }

    public  void submitReview()
    {
        submit.click();
    }

}
