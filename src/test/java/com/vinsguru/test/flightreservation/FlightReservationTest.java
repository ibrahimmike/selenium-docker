package com.vinsguru.test.flightreservation;

import com.vinsguru.test.AbstractTest;
import com.vinsguru.test.flightreservation.modal.FlightReservationTestData;
import com.vinsguru.test.flightreservation.pages.flightreservation.*;
import com.vinsguru.util.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {
    private String noOfPassengers;
    private String expectedPrice;

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameter(String testDataPath){
        testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
        this.noOfPassengers = testData.passengerCount();
        this.expectedPrice = testData.expectedPrice();

    }
    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserCredentials(testData.email(),testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.enterFirstNameLastname(testData.firstName(), testData.lastName());
        registrationPage.register();
    }
    @Test (dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.gotToFlightSearch();
    }
    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightsSearchPage fsp = new FlightsSearchPage(driver);
        Assert.assertTrue(fsp.isAt());
        fsp.selectPassengers(noOfPassengers);
        fsp.searchFlights();
    }
    @Test (dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.clickOnTheConfirmFlightBtn();
    }
    @Test (dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage fcp = new FlightConfirmationPage(driver);
        Assert.assertTrue(fcp.isAt());
        Assert.assertEquals(fcp.getPrice(),expectedPrice);
    }
    @AfterTest
    public void quiteDriver(){
        this.driver.quit();
    }

}

