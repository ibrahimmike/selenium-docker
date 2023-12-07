package com.vinsguru.test.flightreservation.pages.flightreservation;

import com.vinsguru.test.flightreservation.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchBtn;

    public RegistrationConfirmationPage(WebDriver driver){
       super(driver);
    }
    public void gotToFlightSearch(){
        this.goToFlightSearchBtn.click();
    }
    @Override
    public boolean isAt(){
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchBtn));
        return this.goToFlightSearchBtn.isDisplayed();
    }

}
