package tests;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import UtilFunctions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class postingReview  extends baseTest{

    loginPage objLP;
    postLogin objPL;
    insurancePage objIP;
    writeReviewPage objRP;
    postReviewPage objPRP;
    insurancePage objIP2;
    profilePage objPP;
    captureScreenshot objSS= new captureScreenshot();
    String  sspath = objProp.getProperty("screenshotPath")+objProp.getProperty("screenshotFolder")+"\\";

    @Test
    public void postReview() throws InterruptedException, IOException {

        try {
            //logging into Application
            objLP = new loginPage(driver);
            objLP.loginAction(objProp.getProperty("appUsername"), objProp.getProperty("appPassword"));
            log.debug("-Logging into application-");

            //Verifing if the user has logged successfully
            objPL = new postLogin(driver);
            objPL.verifyingLogin().contains("Thank you for registering with WalletHub!");
            log.debug("-Logged into application, successfully!-");

            //Navigating to the insurance company page to write a review.
            driver.navigate().to(objProp.getProperty("RedirectURL"));
            objIP = new insurancePage(driver);
            String insCompany = objIP.verifyPageNav();
            insCompany.contains("Test Insurance Company"); //validating if we have landed on the correct page
            log.debug("-navigated successfully to "+objProp.getProperty("RedirectURL")+" -");

            //performing the hovering action on the stars to review
            objIP.scrollToReview();
            objIP.hoverStar();
            log.debug("-Hovering over star for posting a review-");
            Thread.sleep(2000);
            //validating if on hovering the start is highlighted with respective color
            Assert.assertTrue(objIP.getCSSValue().contains("rgb(74, 224, 225)"));
            log.debug("-Hovering over star, Successful!-");
            System.out.println(Color.fromString(objIP.getCSSValue()));
            //Clicking on the star to navigate to write and submit a review
            objIP.clickStar();
            log.debug("-clicking on star to navigate to Submit review page-");

            objRP = new writeReviewPage(driver);
            //Again validating if the earlier select start review is displayed on the Submit Review page
            Assert.assertTrue(objRP.verifyReview().contains("rgb(74, 224, 225)"));
            objRP.selecDropDown();
            File file = new File(objProp.getProperty("Review"));
            //Writing the review from a text file to the "Write Review" text field
            objRP.writingReview(file);
            objRP.submitReview();
            log.debug("-Review written and submitted successfully-");

            //Validating if the review has been submitted successfully and the message is displayed on the page.
            objPRP = new postReviewPage(driver);
            Assert.assertTrue(objPRP.verifyReviewPosted().contains("Your review has been posted."));
            log.debug("-Review posted successfully message is validated-");
            //Writing the submitted review onto a text file for comparing
            writeToFile objWF = new writeToFile();
            File file1 = new File(objProp.getProperty("SubmittedReview"));
            objWF.writingToTextFile(file1, objPRP.validateSubmittedReview());
            log.debug("-Writing the review displayed on the page to a text file for comparing-");
            //Comparing the submitted review and displayed review.
            compareFiles cf = new compareFiles();
            cf.comparingTextFiles(objProp.getProperty("Review"), objProp.getProperty("SubmittedReview"));
            log.debug("-Text files comparing successful, same review is displayed as submitted earlier-");
            objPRP.clickContinue();

            log.debug("-Insurance company page is displayed-");
            //Navigating to the Insurance company page again
            objIP2 = new insurancePage(driver);
            File file2 = new File(objProp.getProperty("SubmittedReview"));
            //Again validating if the review displayed on the page is matching with the review submitted earlier
            log.debug("-Writing the review displayed on the insurance company page to a text file for comparing-");
            objWF.writingToTextFile(file2, objIP2.verifyMyReview());
            cf.comparingTextFiles(objProp.getProperty("Review"), objProp.getProperty("SubmittedReview"));
            log.debug("-Text files comparing successful, same review is displayed as submitted earlier-");

            //Navigating to the userProfile
            objIP2.navToUserProfile();
            objPP = new profilePage(driver);
            log.debug("-Navigated to user profile screen for review validation-");

            //Validating if the User profile page has loaded successfully
            Assert.assertTrue(objPP.verifyOnProfilePage().contains("I RECOMMEND"));
            log.debug("-User profile page navigation successful-");
            //Validating if the company for which the review was submitted is being displayed
            Assert.assertTrue(objPP.verifyReviewDisplayed().contains(insCompany));
            log.debug("-The company for which review was submitted is displayed-");
            //Validating if the star selected during review is displayed the same on the user profile page.
            Assert.assertTrue(objPP.verifyStars().contains("rgb(74, 224, 225)"));
            log.debug("-Star review submitted earlier is displayed on the user profile page-");
        }
        catch (ElementNotInteractableException e)
        {
            e.printStackTrace();
            objSS.takeScreenshot(driver,sspath);
            log.debug("-ElementNotInteractableException error occured, screenshot is available at-"+sspath);
        }
        catch (ElementNotSelectableException e)
        {
            e.printStackTrace();
            objSS.takeScreenshot(driver,sspath);
            log.debug("-ElementNotSelectableException error occured, screenshot is available at-"+sspath);
        }
        catch(NoSuchElementException e)
        {
            e.printStackTrace();
            objSS.takeScreenshot(driver,sspath);
            log.debug("-NoSuchElementException error occured, screenshot is available at-"+sspath);
        }

    }

}
