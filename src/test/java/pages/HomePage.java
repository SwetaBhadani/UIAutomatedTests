package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@id=\"link_index_0\"]")
    WebElement ourLocationLink;

    @FindBy(xpath = "//*[@class=\"navigation__primary-second-link\"]//*[@class=\"navigation__primary-second-title\"]")
    List<WebElement> ourLocationList;

    @FindBy(xpath = "//button[text()=\"Move in for months\"]")
    WebElement moveInForMonthsButton;

    @FindBy(xpath = "//input[@name=\"NAME\"]")
    WebElement nameField;

    @FindBy(xpath = "//input[@name=\"PHONE\"]")
    WebElement contactNumberField;

    @FindBy(xpath = "//div[@class=\"field-wrapper label-animate\"]//*[@name=\"EMAIL\"]")
    WebElement emailAddressField;

    @FindBy(xpath = "//input[@name=\"LOCATION\"]")
    WebElement locationList;

    @FindBy(xpath = "//button[@class=\"input-date__trigger\"]")
    WebElement dateInput;

    @FindBy(xpath = "//button[@class=\"btn--dark-green collective-community__button\"]")
    WebElement dateSelectButton;

    @FindBy(xpath = "//button[contains(text(),\"Request Information\")]")
    WebElement requestInfoForStayForMonths;

    @FindBy(xpath = "//input[@id=\"field-MOVEINDATE\"]")
    WebElement moveInDate;

    @FindBy(xpath = "//div[@class=\"booking-info\"]/a[@class=\"booking-sidebar__cta btn--white booking-sidebar__cta-icon\"]")
    List<WebElement> bookingInfoLinks;

//    @FindBy(xpath = "")
//    WebElement invalidPhoneTextElement;
//
//    @FindBy(xpath = "")
//    WebElement invalidEMailTextElement;

    public void applyFilter(String filterName, String filterValueString) {

        WebElement selectedFilter = driver.findElement(By.xpath("//button[text()='" + filterName + "']"));
        selectedFilter.click();

        WebElement selectedFilterValue = driver.findElement(By.xpath("//button[text()='" + filterValueString + "']"));
        selectedFilterValue.click();

    }

    public void clickOnOurLocationLink() {
        ourLocationLink.click();
    }

    public Set<String> getOurLocationList() {
        Set<String> locationSet = new HashSet<>();
        for (int i = 0; i < ourLocationList.size(); i++) {
            String location = ourLocationList.get(i).getText();
            locationSet.add(location);
        }
        System.out.println(locationSet);
        return locationSet;
    }

    public void clickOnButton(String buttonName) {


//        "//button[contains(text(), '" + buttonName + "')]"
        WebElement buttonElement = driver.findElement(By.xpath("//button[contains(text(), '" + buttonName + "')]"));
        buttonElement.click();
    }


    public void fillDetailsForMoveInForMonths(String fieldName, String fieldValue) throws InterruptedException {
//        Thread.sleep(1000);
        if (fieldName.equals("Full Name")) {
            nameField.sendKeys(fieldValue);
        } else if (fieldName.equals("Contact Number")) {
            contactNumberField.sendKeys(fieldValue);
        } else if (fieldName.equals("Email Address")) {
            emailAddressField.sendKeys(fieldValue);
        } else if (fieldName.equals("Location")) {
            locationList.click();
            String locationOptionSelectXpath = String.format("//ul//*[text()=\"%s\"]", fieldValue);
            WebElement locationOptionSelected = driver.findElement(By.xpath(locationOptionSelectXpath));
            locationOptionSelected.click();
        } else if (fieldName.equals("Move In")) {

            dateInput.click();
            String[] moveInDateArray = fieldValue.split(" ");
            String day = moveInDateArray[0].substring(0, moveInDateArray[0].length() - 2);
            String dateSelectionStringXpath = String.format("//*[@class=\"input-date field-wrapper label-animate\"]//div[text()=\"%s\"]", day);

            WebElement dateSelectionStringWE = driver.findElement(By.xpath(dateSelectionStringXpath));
            dateSelectionStringWE.click();
            clickOnButton("Select");
        }
    }

    public List<String> getBookingInfoLinkTexts() {

        List<String> bookingInfoLinkTextList = new ArrayList<>();
        for (int i = 0; i < bookingInfoLinks.size(); i++) {
            bookingInfoLinkTextList.add(bookingInfoLinks.get(i).getText());
        }
        return bookingInfoLinkTextList;
    }


    public String getErrorMessageText(String infoField) {
        String errorMessageXPath = String.format("//label[text()=\"* %s\"]//following-sibling::span", infoField);
        String errorMessageText = driver.findElement(By.xpath(errorMessageXPath)).getText();
        return errorMessageText;
    }

    public boolean checkEnability(String buttonName) {
        if (buttonName.equals("Request Information")) {
            Boolean buttonEnability = requestInfoForStayForMonths.isEnabled();
            return buttonEnability;
        }

        return false;
    }
}