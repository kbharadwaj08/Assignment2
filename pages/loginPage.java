package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(.,'Sign Up')]")
    WebElement pageload;

    @FindBy(xpath= "(//a[contains(.,'Login')])[2]")
    WebElement loginTab;

    @FindBy(xpath = "//input[@name='em']")
    WebElement uname;

    @FindBy(xpath = "//input[@name='pw1']")
    WebElement pwd;

    @FindBy(xpath = "//span[@ng-if='!pending'][contains(.,'Login')]")
    WebElement loginButton;

    public loginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String verifyPageLoad()
    {
        return pageload.getText();
    }

    public void swtichToLogin()
    {
        loginTab.click();
    }

    public void setUsername(String username)
    {
        uname.sendKeys(username);
    }

    public void setPassword(String password)
    {
        pwd.sendKeys(password);
    }

    public void clickLogin()
    {
        loginButton.click();
    }

    public void loginAction(String username, String Password)
    {
        this.swtichToLogin();
        this.setUsername(username);
        this.setPassword(Password);
        this.clickLogin();
    }
}
