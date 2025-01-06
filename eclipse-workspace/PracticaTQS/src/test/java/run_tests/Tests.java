package run_tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/resources/features", 
		glue="steps"
)
public class Tests extends AbstractTestNGCucumberTests {

}
