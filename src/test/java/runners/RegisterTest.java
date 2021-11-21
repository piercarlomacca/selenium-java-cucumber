package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/register.feature"},
		plugin = {"pretty",
        "html:target/home-page.html"},
        glue = {"common",
                "register"})
public class RegisterTest {
}
