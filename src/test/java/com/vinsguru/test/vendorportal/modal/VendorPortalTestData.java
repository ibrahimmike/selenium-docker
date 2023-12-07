package com.vinsguru.test.vendorportal.modal;

import org.testng.annotations.BeforeMethod;

public record VendorPortalTestData(String userName,
                                   String password,
                                   String monthlyEarning,
                                   String annualEarning,
                                   String profitMargin,
                                   String AvailableInventory,
                                   String searchKeyWord,
                                   int searchResultsCount ) {


}
