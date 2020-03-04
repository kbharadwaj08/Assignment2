package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class postLogin {

    WebDriver driver;

    @FindBy(xpath = "//h2[contains(.,'Thank you for registering with WalletHub!')]")
    WebElement verifyLogin;

    public postLogin(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String verifyingLogin()
    {
        return verifyLogin.getText();
    }
}
