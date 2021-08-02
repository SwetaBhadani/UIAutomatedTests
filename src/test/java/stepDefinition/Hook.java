package stepDefinition;

import base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Hook extends BaseUtil {
    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {

//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        base.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("Scenario running: " + scenario.getName());
    }


    @After
    public void TearDownTest(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {

            TakesScreenshot scrShot = (TakesScreenshot) driver;
            File ssFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("Output/Failed_Screenshot/FailedScreenshot.png");
            FileUtils.copyFile(ssFile, DestFile);

            System.out.println(scenario.getName());
        } else {
            System.out.println("Scenario Passed :)");
        }

        System.out.println("Closing the browser");
        base.driver.quit();
    }
}
