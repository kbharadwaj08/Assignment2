package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class postReviewPage {

    WebDriver driver;

    @FindBy(xpath = "//h4[contains(.,'Your review has been posted.')]")
    WebElement reviewPosted;

    @FindBy(xpath = "//p[contains(.,'hassles related to')]")
    WebElement submittedReview;

    @FindBy(xpath= "//div[text()='Continue']")
    WebElement continueButton;

    public postReviewPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyReviewPosted()
    {
        return reviewPosted.getText();
    }

    public String validateSubmittedReview()
    {
       return submittedReview.getText();
    }

    public void clickContinue()
    {
        continueButton.click();
    }
}
