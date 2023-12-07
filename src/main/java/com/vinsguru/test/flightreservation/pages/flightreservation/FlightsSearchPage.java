package com.vinsguru.test.flightreservation.pages.flightreservation;

import com.vinsguru.test.flightreservation.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage {

    @FindBy(id="passengers")
    private WebElement passengerSelect;
    @FindBy(id="search-flights")
    private WebElement flightsSearchBtn;


    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
        return this.passengerSelect.isDisplayed();
    }

    public void selectPassengers(String number){
        Select passengers = new Select(passengerSelect);
        passengers.selectByValue(number);
    }
    public void searchFlights(){
        this.flightsSearchBtn.click();
    }


}
