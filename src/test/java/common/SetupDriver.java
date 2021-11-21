package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class SetupDriver {

	public static WebDriver driver;
    
    @Before
    public void setUp() throws Exception {
    	System.out.println("===Setting up!!===");
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--incognito");
        // chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--window-size=1366,768");
        driver = new ChromeDriver(chromeOptions);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void shutDown(Scenario scenario){
    	System.out.println("===Shutting down!!===");
    	
        if(scenario.isFailed()){
        	failureScreenshot(scenario);
        }
        
        driver.quit();
    }

    private void failureScreenshot(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}
