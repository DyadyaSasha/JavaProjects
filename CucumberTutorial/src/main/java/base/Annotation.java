package base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Annotation {

    static {
        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");
    }
    private WebDriver driver;


    /**
     * выполняется перед каждым сценарием
     */
    @Before
    public void setDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("^I am on Facebook login page$")
    public void goToFacebook() throws InterruptedException {
        driver.get("https://www.facebook.com/");
    }

    @When("^I enter username as \"(.*)\"$")
    public void enterUsername(String email) throws InterruptedException {
//        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath(".//*[@id='email']")).sendKeys(email);
        Thread.sleep(5000);

    }

    @When("^I enter password as \"(.*)\"$")
    public void enterPassword(String password) throws InterruptedException {
        driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.id("loginbutton")).click();
        Thread.sleep(5000);
    }

    @Then("^Login should fail$")
    public void checkFail(){
        if(driver.getCurrentUrl().equalsIgnoreCase(
                "https://www.facebook.com/login.php?login_attempt=1&lwv=110"
        )){
            System.out.println("Test1 Pass");
        } else {
            System.out.println("Test1 Failed");
        }
    }


    @Then("^Relogin option should be available$")
    public void checkRelogin(){
        if(driver.getCurrentUrl().equalsIgnoreCase(
                "https://www.facebook.com/login.php?login_attempt=1&lwv=110"
        )){
            System.out.println("Test2 Pass");
        } else {
            System.out.println("Test2 Failed");
        }
    }

    /**
     * выполняется после каждого сценария
     */
    @After
    public void afterScenario(){
        driver.close();
    }
}
