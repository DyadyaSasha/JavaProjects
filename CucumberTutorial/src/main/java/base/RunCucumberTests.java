package base;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"D:\\Projects\\Projects for github\\JavaProjects\\CucumberTutorial\\src\\main\\java\\base"}, format = {"pretty", "html:target/cucumber"})
public class RunCucumberTests {
}
