package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class profilePage {

    WebDriver driver;

    @FindBy(xpath = "//h2[contains(.,'I RECOMMEND')]")
    WebElement onProfilePage;

    @FindBy(xpath = "//div[@class='pr-rec-texts-container']/a")
    WebElement company;

    @FindBy(css = "#scroller > main > div.pr-content > div > section > div > div > div > div.pr-rec-texts-container > review-star > div > svg:nth-child(4) > g > path:nth-child(1)")
    WebElement stars;

    public profilePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String verifyOnProfilePage()
    {
        return onProfilePage.getText();
    }

    public String verifyReviewDisplayed()
    {
        return company.getText();
    }

    public String verifyStars()
    {
        return stars.getCssValue("fill");
    }
}
