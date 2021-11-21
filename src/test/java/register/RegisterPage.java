package register;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;

public class RegisterPage extends BasePage {

    private final String URL = "https://etherscan.io/register";

    @FindBy(css = "#ContentPlaceHolder1_txtUserName")
    private WebElement usernameField;

    @FindBy(css = "#ContentPlaceHolder1_txtEmail")
    private WebElement emailField;
    
    @FindBy(css = "#ContentPlaceHolder1_txtPassword")
    private WebElement passwordField;
    
    @FindBy(css = "#ContentPlaceHolder1_txtPassword2")
    private WebElement confirmPasswordField;
    
    @FindBy(css = "#ContentPlaceHolder1_btnRegister")
    private WebElement submitButton;
    
    @FindBy(css = "#ContentPlaceHolder1_MyCheckBox")
    private WebElement termsConditions;
    
    @FindBy(css = "iframe[title=reCAPTCHA]")
    private WebElement recaptcha;
    
    @FindBy(css = ".alert-info")
    private WebElement successAlert;
	
    RegisterPage() {
        PageFactory.initElements(driver, this);
    }
    
    WebElement getUsername() {
    	return usernameField;
    }
    
    WebElement getPassword() {
    	return passwordField;
    }
    
    WebElement getEmail() {
    	return emailField;
    }
    
    WebElement getPWConfirm() {
    	return confirmPasswordField;
    }
    
    WebElement getTerms() {
    	return termsConditions;
    }
    
    void tryRegistration(String username, String email, String password, String pwconfirm, boolean terms) {
    	usernameField.sendKeys(username);
    	emailField.sendKeys(email);
    	passwordField.sendKeys(password);
    	confirmPasswordField.sendKeys(pwconfirm);
    	
    	Actions a = new Actions(driver);
    	
    	if (terms) {
    		if (termsConditions.getAttribute("checked") != "true") {
        		a.moveToElement(termsConditions, 2, 2).click().build().perform();
    		}

    	}
    	else {
    		if (termsConditions.getAttribute("checked") == "true") {
        		a.moveToElement(termsConditions, 2, 2).click().build().perform();
    		}
    	}
		clickElement(submitButton);
    }
    
    void navigateToRegister() {
		driver.get(URL);
		waitForAppReady();
		try {
			waitForElementVisible(driver.findElement(By.cssSelector("#btnCookie")), 5);
			clickElement(driver.findElement(By.cssSelector("#btnCookie")));
		} catch (NoSuchElementException e) {
			
		}
    }
    
    boolean checkRegistrationSuccess() {
    	try {
    		return successAlert.getText().equals("Your account registration has been submitted and is pending email verification ");
		} catch (NoSuchElementException e) {
			return false;
		}
    }
    
    boolean amIOnRegisterPage() {
		return driver.getCurrentUrl().equals(URL);
    }
    
    boolean fieldHasError(WebElement field) {
		return field.getAttribute("aria-invalid").equals("true");
    }
    
    boolean invalidPolicy() {
		try {
			waitForElementVisible(driver.findElement(By.xpath("//*[text()='Please accept our Terms and Conditions.']")), 2);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
    }
}
