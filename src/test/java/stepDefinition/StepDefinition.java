package stepDefinition;

import base.BaseUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StepDefinition extends BaseUtil {

    private BaseUtil base;
    private HomePage hp;
    Set<String> ourLocationList;

    public StepDefinition(BaseUtil base) {
        this.base = base;
        this.hp = new HomePage(driver);
    }

    @Given("As a user, I open the website")
    public void as_a_user_i_open_the_website() {

        base.driver.get("https://staging.thecollective.com/");
    }

    @When("I click on {string} link")
    public void i_click_on(String link) {
        if (link.equals("Our Location")) {
            hp.clickOnOurLocationLink();
        }

    }

    @Then("I get the locations displayed")
    public void i_get_the_locations_displayed() {
        ourLocationList = hp.getOurLocationList();
    }

    @Then("I verify the locations with the below list")
    public void i_verify_the_locations_with_the_below_list(List<String> dataTable) {
        Set<String> expectedLocationSet = new HashSet<>();
        expectedLocationSet.addAll(dataTable);
        Assert.assertEquals(ourLocationList, expectedLocationSet);
    }


    @When("I click on {string} button")
    public void i_click_on_button(String buttonName) throws InterruptedException {
        hp.clickOnButton(buttonName);
        // Thread.sleep(1000);
    }

    @When("I provide the below details")
    public void i_provide_the_below_details(List<Map<String, String>> dataTable) throws InterruptedException {

        for (Map<String, String> dataMaps : dataTable) {
            for (Map.Entry<String, String> dataMap : dataMaps.entrySet()) {
                String fieldName = dataMap.getKey();
                String fieldValue = dataMap.getValue();
                hp.fillDetailsForMoveInForMonths(fieldName, fieldValue);
            }
        }
    }

    @Then("I verify if I see below options")
    public void i_verify_if_i_see_below_options(List<String> dataTable) throws InterruptedException {

        //Putting sleep because of lag after clicking request Information
        Thread.sleep(2000);
        List<String> bookingInfoLinkTexts = hp.getBookingInfoLinkTexts();

        Assert.assertEquals(dataTable.get(0), bookingInfoLinkTexts.get(0));
        Assert.assertEquals(dataTable.get(1), bookingInfoLinkTexts.get(1));
    }


    @Then("I see error message saying {string} for invalid {string}")
    public void i_see_error_message_saying_for_invalid(String expectedErrorMessage, String infoField) {
        String actualErrorMessage = hp.getErrorMessageText(infoField);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Then("{string} button remains disabled")
    public void button_remains_disabled(String buttonName) {
        Assert.assertFalse(hp.checkEnability(buttonName));

    }
}


