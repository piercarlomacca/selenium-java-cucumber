package common;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected String BASE_URL = "https://etherscan.io";

    public BasePage() {
        this.driver = SetupDriver.driver;
    }
    
    public void waitForAppReady() {
        ExpectedCondition<WebElement> appready_condition = ExpectedConditions.presenceOfElementLocated(By.cssSelector("#logoAndNav"));
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.withMessage("App not ready after timeout");
        wait.until(appready_condition);
    }
    
    public void waitForElementVisible(WebElement element, int timeout) {
        ExpectedCondition<WebElement> elemIsVisible = ExpectedConditions.visibilityOf(element);
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.withMessage("App not ready after timeout");
        wait.until(elemIsVisible);
    }
    
    public void clickElement(WebElement element) {
    	ExpectedCondition<WebElement> elemIsClickable = ExpectedConditions.elementToBeClickable(element);
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.withMessage("Element not ready after timeout");
        wait.until(elemIsClickable);
        element.click();
    }
    
    public boolean isElementVisibleInTime(WebElement element, int timeout) {
    	try {
    		waitForElementVisible(element, timeout);
    	}
    	catch (Exception e) {
    		return false;
    	}
    	return true;
    }
    
    public void navigateToHome() {
    	this.driver.get(BASE_URL);
    	waitForAppReady();
    }

}
