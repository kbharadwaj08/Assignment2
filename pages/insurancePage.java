package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static javafx.scene.paint.Color.rgb;

public class insurancePage {

    WebDriver driver;

    @FindBy(xpath = "//h1[contains(.,'Test Insurance Company')]")
    WebElement pageverification;

    @FindBy(css = "#reviews-section > div.review-stat-box > div.review-action.ng-enter-element > review-star > div > svg:nth-child(4) > g > path:nth-child(1)")
    WebElement reviewStar;

    @FindBy(xpath = "//span[contains(.,'Kiran')]")
    WebElement loggedInUser;

    @FindBy(xpath= "//div[contains(text(),'family against financial risks arising')]")
    WebElement myReview;

    @FindBy(xpath="//a[@class='brgm-list-it'][contains(.,'Profile')]")
    WebElement profileNav;

    public insurancePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String verifyPageNav()
    {
        return pageverification.getText();
    }

    public void scrollToReview()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public void hoverStar()
    {
        Actions act = new Actions(driver);
        act.moveToElement(reviewStar).perform();
    }

    public String getCSSValue() {
         return reviewStar.getCssValue("fill");

    }

    public void clickStar()
    {
        reviewStar.click();
    }

    public String verifyMyReview()
    {
        return myReview.getText();
    }

    public void navToUserProfile() throws InterruptedException {
        Actions act = new Actions(driver);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,0)");

        Thread.sleep(2000);
        act.moveToElement(loggedInUser).perform();
        profileNav.click();
    }

}
