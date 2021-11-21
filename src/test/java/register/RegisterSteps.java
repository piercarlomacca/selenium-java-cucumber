package register;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class RegisterSteps {
	
	RegisterPage register = new RegisterPage();

	
	@ParameterType(value = "true|True|TRUE|false|False|FALSE")
	public Boolean booleanValue(String value) {
	    return Boolean.valueOf(value);
	}
	
	@Given("I visit etherscan app registration page")
	public void i_visit_etherscan_app_registration_page() {
		register.navigateToRegister();
	}

	@When("I am on registration page")
	public void i_am_on_registration_page() {
		assertTrue(register.amIOnRegisterPage());
	}

	@When("I try to register using username {string}, email {string}, password {string}, password confirm {string}, and term and conditions {booleanValue}")
	public void i_try_to_register_using_username_email_password_password_confirm_and_term_and_conditions(String username, String email, String password, String pwconfirm, Boolean terms) {
	    register.tryRegistration(username, email, password, pwconfirm, terms);
	}

	@Then("the registration is {string}")
	public void the_registration_is(String status) throws Exception {
		
		
		if (status.equals("successful")) {
			assertTrue(register.checkRegistrationSuccess());
		}
		else if (status.equals("unsuccessful")) {
			assertFalse(register.checkRegistrationSuccess());
		}
		else {
			throw new Exception("unknown status required: " + status);
		}
	}

	@Then("an error is displayed on the field {string}")
	public void an_error_is_displayed_on_the_field(String invalidfield) throws Exception {
		switch (invalidfield) {
		case "username":
			assertTrue("username has no errors", register.fieldHasError(register.getUsername()));
			break;
		case "email":
			assertTrue("email has no errors", register.fieldHasError(register.getEmail()));
			break;
		case "password":
			assertTrue("password has no errors", register.fieldHasError(register.getPassword()));
			break;
		case "pwconfirm":
			assertTrue("pwconfirm has no errors", register.fieldHasError(register.getPWConfirm()));
			break;
		case "terms":
			assertTrue("terms has no errors", register.invalidPolicy());
			break;

		default:
			throw new Exception("unknown field");
		}
	}
}
