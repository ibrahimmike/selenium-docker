package com.vinsguru.test.flightreservation.pages.vendorportals;

import com.vinsguru.test.flightreservation.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashBoardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashBoardPage.class);

    @FindBy(id ="monthly-earning")
    private WebElement monthlyEarning;
    @FindBy(id ="annual-earning")
    private WebElement annualEarning;

    @FindBy(id ="profit-margin")
    private WebElement profitMargin;

    @FindBy(id="available-inventory")
    private WebElement availableInventory;

    @FindBy( css ="#dataTable_filter input")
    private WebElement searchInput;
    @FindBy( id = "dataTable_info")
    private WebElement dataSearchCountElement;
    @FindBy(css= "img.img-profile")
    private WebElement userProfilePicture;

    @FindBy(linkText ="Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogout;

    //xpath: //div[id='dataTable_filter']//input[type='search']
    //#logoutModal a

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }
    public String getMonthlyEarning(){
        return this.monthlyEarning.getText();
    }
    public String getAnnualEarning(){
        return this.annualEarning.getText();
    }
    public String getProfitMargin(){
        return this.profitMargin.getText();
    }
    public String getAvailableInventory(){
        return this.availableInventory.getText();
    }
    public void searchOrderHistoryBy(String input){
        this.searchInput.sendKeys(input);
    }
    public int getSearchAmount(){
       String result = this.dataSearchCountElement.getText();
       String[] arr = result.split(" ");
       int count =  Integer.parseInt(arr[5]);
       log.info("Results count {} ", count);
       return count;
    }
    public void logout(){

        this.userProfilePicture.click();
        this.wait.until(ExpectedConditions.visibilityOf(logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(modalLogout));
        this.modalLogout.click();
    }

}
