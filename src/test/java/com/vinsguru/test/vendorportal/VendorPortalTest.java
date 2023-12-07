package com.vinsguru.test.vendorportal;

import com.vinsguru.test.AbstractTest;
import com.vinsguru.test.flightreservation.pages.vendorportals.DashBoardPage;
import com.vinsguru.test.flightreservation.pages.vendorportals.LoginPage;
import com.vinsguru.test.vendorportal.modal.VendorPortalTestData;
import com.vinsguru.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setTest(String testDataPath){
        loginPage = new LoginPage(driver);
        dashBoardPage = new DashBoardPage(driver);
       this.testData =  JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginToPage(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        loginPage.login(testData.userName(),testData.password());
        Assert.assertTrue(loginPage.isAt());
    }
    @Test(dependsOnMethods = "loginToPage")
    public void dashBoardTest(){
        Assert.assertTrue(dashBoardPage.isAt());
        Assert.assertEquals(dashBoardPage.getMonthlyEarning(), testData.monthlyEarning());
        dashBoardPage.searchOrderHistoryBy(testData.searchKeyWord());
        Assert.assertEquals(dashBoardPage.getSearchAmount(), testData.searchResultsCount());

    }


    @Test(dependsOnMethods = "dashBoardTest")
    public void logout(){
        DashBoardPage dbp = new DashBoardPage(driver);
        dashBoardPage.logout();
        Assert.assertTrue(loginPage.isAt());

    }


}
